chrome.runtime.sendMessage({ type: 'getActiveTabId' }, (response) => {
    const activeTabId = response.activeTabId;
    function updateButtonState(state) {
        toggleDisplayStyle('play', state === 'pause' ? 'none' : '');
        toggleDisplayStyle('pause', state === 'pause' ? '' : 'none');
    }
    function toggleDisplayStyle(elementId, displayValue) {
        document.getElementById(elementId).style.display = displayValue;
    }
    function sendMessageToActiveTab(messageType) {
        chrome.tabs.query({ active: true, currentWindow: true }, () => {
            chrome.tabs.sendMessage(activeTabId, { type: messageType });
        });
    }
    function toggleRecording() {
        chrome.storage.local.get('recordingState', (result) => {
            const currentState = result.recordingState || 'play';
            const newState = currentState === 'play' ? 'pause' : 'play';
            chrome.storage.local.set({ recordingState: newState }, () => {
                updateButtonState(newState);
                sendMessageToActiveTab(newState === 'play' ? 'pauseRecording' : 'startRecording');
            });
        });
    }
    function stopRecording() {
        chrome.storage.local.set({ recordingState: 'play' }, () => {
            updateButtonState('play');
            sendMessageToActiveTab('stopRecording');
        });
    }
    function addClickListener(elementId, callback) {
        document.getElementById(elementId).addEventListener('click', callback);
    }
    addClickListener('record', toggleRecording);
    addClickListener('stop', stopRecording);
    chrome.storage.local.get('recordingState', (result) => {
        updateButtonState(result.recordingState || 'play');
    });
    function createListItem(userActions, action, step) {
        const listItem = document.createElement('li');
        listItem.setAttribute('draggable', 'true');
        listItem.setAttribute('data-step', step);
        listItem.className = 'step';

        const stepHeader = document.createElement('div');
        stepHeader.className = 'step-header';
        stepHeader.innerText = `Step ${step} Type: ${action.type}`;

        const actionDetails = document.createElement('div');
        actionDetails.className = 'action-details';
        actionDetails.innerHTML = ``;
        if (action.type === 'input')
            actionDetails.innerHTML += `<p> Value: ${action.value}`;
        if (action.type === `validateText`)
            actionDetails.innerHTML += `<p>Expected Text: ${action.currentText}`;
        actionDetails.innerHTML += `
                    <p>ID: ${action.id}</p>
                    <p>Name: ${action.name}</p>
                    <p>cssSelector: ${action.cssSelector}</p>
                    <p>xpath: ${action.xpath}</p>
                    <p>innerText: ${action.innerText}</p>
                   `;

        const removeButton = document.createElement('span');
        removeButton.innerText = 'x';
        removeButton.className = 'remove-step';
        removeButton.addEventListener('click', function () {
            const index = userActions.findIndex(function (a) {
                return a.step === step;
            });
            userActions.splice(index, 1);

            // Update the step numbers for the remaining items
            userActions.forEach(function (action, index) {
                action.step = index + 1;
            });

            chrome.storage.local.set({ userActions: userActions }, function () {
                listItem.remove();
                renderUserActions(userActions); // Re-render the list with updated step numbers
            });
        });
        const stepNameContainer = document.createElement("div");
        stepNameContainer.className = "step-name-container";
        const stepNameLabel = document.createElement("label");
        stepNameLabel.innerText = "Step Name:";
        const stepNameInput = document.createElement("input");
        stepNameInput.className = "step-name";
        stepNameInput.type = "text";
        stepNameInput.placeholder = "Input a step name"; // Add placeholder text
        stepNameInput.value = action.stepName || "";
        stepNameInput.addEventListener("focus", function () {
            stepNameInput.style.border = "1px solid #eee";
        });
        stepNameInput.addEventListener("blur", function () {
            stepNameInput.style.border = "1px solid transparent";
        });
        const stepNameCheck = document.createElement("span");
        stepNameCheck.className = "check";
        stepNameCheck.innerHTML = "&#10004;";
        stepNameCheck.addEventListener("click", function () {
            const stepName = stepNameInput.value.trim();
            if (stepName) {
                const index = userActions.findIndex(function (a) {
                    return a.step === step;
                });
                userActions[index].stepName = stepName;
                chrome.storage.local.set({ userActions: userActions }, function () {
                    renderUserActions(userActions);
                });
            }
        });

        const stepNamePencil = document.createElement("span");
        stepNamePencil.className = "pencil";
        stepNamePencil.innerHTML = "&#9998;";
        stepNamePencil.addEventListener("click", function () {
            stepNameInput.style.display = "";
            stepNameInput.value = action.stepName || "";

            stepNamePencil.style.display = "none";
            stepNameCheck.style.display = "";
        });

        if (action.stepName) {
            //stepNameInput.style.display = "none";
            stepNameCheck.style.display = "none";
        } else {
            stepNamePencil.style.display = "none";
            stepNameCheck.style.display = "";
        }

        stepNameContainer.appendChild(stepNameLabel);
        stepNameContainer.appendChild(stepNameInput);
        stepNameContainer.appendChild(stepNamePencil);
        stepNameContainer.appendChild(stepNameCheck);

        listItem.appendChild(stepHeader);
        listItem.appendChild(stepNameContainer);
        listItem.appendChild(actionDetails);
        listItem.appendChild(removeButton);
        return listItem;
    }

    function renderUserActions(userActions) {
        const eventList = document.getElementById('event-list');
        eventList.innerHTML = '';

        userActions.forEach(function (action, index) {
            const step = action.step || index + 1;
            const listItem = createListItem(userActions, action, step);
            eventList.appendChild(listItem);
        });
    }
    function initializePopup() {
        chrome.storage.local.get(["userActions", "testName"], function (result) {
            const userActions = result.userActions || [];
            renderUserActions(userActions);
            updateTestNameInput(result.testName || "");
        });

        chrome.storage.onChanged.addListener(function (changes, namespace) {
            if (namespace === 'local' && changes.hasOwnProperty('userActions')) {
                const userActions = changes.userActions.newValue;
                renderUserActions(userActions);
            }
        });

        // Add sortable functionality to event list
        $("#event-list").sortable({
            containment: "#steps-container",
            placeholder: "sortable-placeholder",
            update: function (event, ui) {
                chrome.storage.local.get('userActions', function (result) {
                    let userActions = result.userActions || [];

                    const newIndex = ui.item.index();
                    const oldIndex = userActions.findIndex(function (action) {
                        return action.step === parseInt(ui.item.attr('data-step'));
                    });

                    const movedAction = userActions.splice(oldIndex, 1)[0];
                    movedAction.step = newIndex + 1;
                    userActions.splice(newIndex, 0, movedAction);

                    userActions.forEach(function (action, index) {
                        action.step = index + 1;
                    });

                    chrome.storage.local.set({ userActions: userActions }, function () {
                        renderUserActions(userActions);
                    });
                });
            }
        });
        function updateTestNameInput(testName) {
            const input = document.getElementById("test-name");
            const check = document.getElementById("test-name-check");
            const pencil = document.getElementById("test-name-pencil");
            input.style.backgroundColor = "#222";
            input.style.color = "#eee";
            input.style.border = "1px solid transparent";
            input.style.padding = "5px";

            input.addEventListener("focus", function () {
                input.style.border = "1px solid #eee";
            });

            input.addEventListener("blur", function () {
                input.style.border = "1px solid transparent";
            });
            if (testName) {
                //input.style.display = "none";
                check.style.display = "none";
                pencil.style.display = "";

                pencil.innerText = testName; // Display the testName in the pencil element
            } else {
                input.style.display = "";
                check.style.display = "";
                pencil.style.display = "none";
            }
        }
        function saveTestName() {
            const input = document.getElementById("test-name");
            const check = document.getElementById("test-name-check");
            const pencil = document.getElementById("test-name-pencil");
            const testName = input.value.trim();

            if (testName) {
                chrome.storage.local.set({ testName: testName }, function () {
                    updateTestNameInput(testName);
                });
            }
            //pencil.innerText = testName; // Update the pencil element with the testName
            pencil.innerHTML = "&#9998;";

            pencil.style.display = "";
            check.style.display = "none";
        }

        function editTestName() {
            const pencil = document.getElementById("test-name-pencil");
            const testName = pencil.innerText;

            const input = document.getElementById("test-name");
            input.style.display = "";
            input.value = testName;

            pencil.style.display = "none";
            document.getElementById("test-name-check").style.display = "";
        }

        document.getElementById("test-name-check").addEventListener("click", saveTestName);
        document.getElementById("test-name-pencil").addEventListener("click", editTestName);

        chrome.storage.local.get("testName", function (result) {
            const testName = result.testName || "";
            updateTestNameInput(testName);
        });
    }
    initializePopup();
});
