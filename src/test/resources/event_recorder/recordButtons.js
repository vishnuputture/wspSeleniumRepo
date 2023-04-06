chrome.runtime.sendMessage({ type: 'getActiveTabId' }, (response) => {
    const activeTabId = response.activeTabId;
    function sendMessageToActiveTab(messageType) {
        chrome.tabs.query({ active: true, currentWindow: true }, () => {
            chrome.tabs.sendMessage(activeTabId, { type: messageType });
        });
    }
    function toggleRecording() {
        chrome.storage.local.get('recordingState', (result) => {
            const currentState = result.recordingState || 'recordButton';
            const newState = currentState === 'recordButton' ? 'pause' : 'recordButton';
            chrome.storage.local.set({ recordingState: newState }, () => {
                updateButtonState(newState);
                sendMessageToActiveTab(newState === 'recordButton' ? 'pauseRecording' : 'startRecording');
            });
        });
    }
    function stopRecording() {
        chrome.storage.local.set({ recordingState: 'recordButton' }, () => {
            updateButtonState('recordButton');
            sendMessageToActiveTab('stopRecording');
        });
    }
    function addClickListener(elementId, callback) {
        document.getElementById(elementId).addEventListener('click', callback);
    }
    function updateButtonState(state) {
        toggleDisplayStyle('recordButton', state === 'pause' ? 'none' : '');
        toggleDisplayStyle('pause', state === 'pause' ? '' : 'none');
        document.getElementById('pauseTooltip').innerText = state === 'pause' ? 'Record' : 'Pause';
    }

    function toggleDisplayStyle(elementId, displayValue) {
        document.getElementById(elementId).style.display = displayValue;
    }


    addClickListener('record', toggleRecording);
    addClickListener('stop', stopRecording);

    chrome.storage.local.get('recordingState', (result) => {
        updateButtonState(result.recordingState || 'recordButton');
    });


    initializePopup();
});
