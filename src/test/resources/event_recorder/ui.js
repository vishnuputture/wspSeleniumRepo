function renderUserActions(userActions) {
    const eventList = document.getElementById('event-list');
    eventList.innerHTML = '';

    userActions.forEach(function (action, index) {
        const step = action.step || index + 1;
        const listItem = createListItem(userActions, action, step);
        eventList.appendChild(listItem);
    });
}

function createListItem(userActions, action, step) {
    const actionTypes = ['input', 'click', 'validateText', 'mouseover', 'scroll', 'keydown', 'dragAndDrop']
    const listItem = document.createElement('li');
    listItem.setAttribute('draggable', 'true');
    listItem.setAttribute('data-step', step);
    listItem.className = 'step';

    const actionTypeSelect = document.createElement('select');
    actionTypes.forEach((actionType) => {
        const option = document.createElement('option');
        option.value = actionType;
        option.text = actionType;
        if (actionType === action.type) {
            option.selected = true;
        }
        actionTypeSelect.appendChild(option);
    });

    actionTypeSelect.addEventListener('change', () => {
        const newActionType = actionTypeSelect.value;
        const index = userActions.findIndex((a) => a.step === step);
        userActions[index].type = newActionType;
        chrome.storage.local.set({ userActions: userActions }, () => {
            renderUserActions(userActions);
        });
    });

    const stepHeader = document.createElement('div');
    stepHeader.className = 'step-header';
    const viewDetailsButton = document.createElement('button');
    viewDetailsButton.innerText = 'View Details';
    viewDetailsButton.className = 'view-details-btn';
    viewDetailsButton.addEventListener('click', () => {
        const detailsPanel = document.getElementById('details-panel');
        if (viewDetailsButton.innerText === 'View Details') {
            const closeButtons = document.getElementsByClassName('close-details-btn');
            Array.from(closeButtons).forEach(button => {
                button.className = 'view-details-btn';
                button.innerText = 'View Details';
            });
            viewDetailsButton.className = 'close-details-btn';
            viewDetailsButton.innerText = 'Close Details';
            detailsPanel.innerHTML = '';
            const detailsHtml = JSON.parse(listItem.dataset.actionDetails);
            detailsPanel.insertAdjacentHTML('beforeend', detailsHtml);
            detailsPanel.style.display = 'block';
            shiftContent(true);
        } else {
            viewDetailsButton.className = 'view-details-btn';
            viewDetailsButton.innerText = 'View Details';
            detailsPanel.style.display = 'none';
            shiftContent(false);
        }
    });

    const actionDetails = createActionDetails(action);
    const removeButton = document.createElement('span');
    removeButton.innerText = 'x';
    removeButton.className = 'remove-step';
    removeButton.addEventListener('click', function () {
        const index = userActions.findIndex(function (a) {
            return a.step === step;
        });
        userActions.splice(index, 1);
        userActions.forEach(function (action, index) {
            action.step = index + 1;
        });

        chrome.storage.local.set({ userActions: userActions }, function () {
            listItem.remove();
            renderUserActions(userActions);
        });
    });
    const stepNameContainer = document.createElement("div");
    stepNameContainer.className = "step-name-container";
    const stepNameLabel = document.createElement("label");
    stepNameLabel.innerText = "Name:";
    const stepNameInput = document.createElement("input");
    stepNameInput.className = "step-name";
    stepNameInput.type = "text";
    stepNameInput.placeholder = "Input a step name";
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
        stepNameCheck.style.display = "none";
    } else {
        stepNamePencil.style.display = "none";
        stepNameCheck.style.display = "";
    }
    stepHeader.innerHTML = `Step ${step} Type: `;
    stepHeader.appendChild(actionTypeSelect);

    stepNameContainer.appendChild(stepNameLabel);
    stepNameContainer.appendChild(stepNameInput);
    stepNameContainer.appendChild(stepNamePencil);
    stepNameContainer.appendChild(stepNameCheck);
    listItem.dataset.actionDetails = JSON.stringify(actionDetails.outerHTML);
    listItem.appendChild(stepHeader);
    listItem.appendChild(stepNameContainer);
    listItem.appendChild(viewDetailsButton);
    listItem.appendChild(removeButton);
    return listItem;
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
        },
        start: function (event, ui) {
            const draggedItemHeight = ui.item[0].offsetHeight;
            const placeholder = document.querySelector('.sortable-placeholder');
            placeholder.style.height = `${draggedItemHeight}px`;
        },
        stop: function (event, ui) {
            const placeholder = document.querySelector('.sortable-placeholder');
            placeholder.style.height = '';
        }
    });
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
        pencil.style.display = "";
        check.style.display = "none";
    }
    function editTestName() {
        const pencil = document.getElementById("test-name-pencil");
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
        check.style.display = "none";
        pencil.style.display = "";
    } else {
        input.style.display = "";
        check.style.display = "";
        pencil.style.display = "none";
    }
}
function shiftContent(isOpening) {
    const content = document.getElementById('content');
    if (content) {
        content.style.marginRight = isOpening ? '300px' : '0';
    }
}

function createActionDetails(action) {
    const actionDetails = document.createElement('table');
    actionDetails.className = 'action-details';

    const details = {
        id: action.id,
        name: action.name,
        cssSelector: action.cssSelector,
        xpath: action.xpath,
        innerText: action.innerText,
    };

    if (action.type === 'input') {
        details.value = action.value || '';
    }

    if (action.type === 'validateText') {
        details.currentText = action.currentText || '';
    }

    for (const key in details) {
        const row = document.createElement('tr');

        const keyCell = document.createElement('td');
        keyCell.innerText = `${key}:`;
        row.appendChild(keyCell);

        const valueCell = document.createElement('td');
        valueCell.innerText = details[key];
        row.appendChild(valueCell);

        actionDetails.appendChild(row);
    }

    return actionDetails;
}

document.addEventListener('click', (event) => {
    const detailsPanel = document.getElementById('details-panel');
    if (!detailsPanel.contains(event.target) && !event.target.classList.contains('view-details-btn') && !event.target.classList.contains('close-details-btn')) {
        detailsPanel.style.display = 'none';
        const closeButtons = document.getElementsByClassName('close-details-btn');
        Array.from(closeButtons).forEach(button => {
            button.className = 'view-details-btn';
            button.innerText = 'View Details';
        });
        shiftContent(false);
    }
});