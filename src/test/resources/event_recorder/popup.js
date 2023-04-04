function updateButtonState(state) {
    const playButton = document.getElementById('play');
    const pauseButton = document.getElementById('pause');

    if (state === 'pause') {
        playButton.style.display = 'none';
        pauseButton.style.display = '';
    } else {
        playButton.style.display = '';
        pauseButton.style.display = 'none';
    }
}

function toggleRecording() {
    chrome.storage.local.get('recordingState', (result) => {
        const currentState = result.recordingState || 'play';
        const newState = currentState === 'play' ? 'pause' : 'play';
        chrome.storage.local.set({ recordingState: newState }, () => {
            updateButtonState(newState);
            chrome.tabs.query({ active: true, currentWindow: true }, (tabs) => {
                chrome.tabs.sendMessage(tabs[0].id, { type: newState === 'play' ? 'pauseRecording' : 'startRecording' });
            });
        });
    });
}

document.getElementById('record').addEventListener('click', toggleRecording);

document.getElementById('stop').addEventListener('click', () => {
    chrome.storage.local.set({ recordingState: 'play' }, () => {
        updateButtonState('play');
        chrome.tabs.query({ active: true, currentWindow: true }, (tabs) => {
            chrome.tabs.sendMessage(tabs[0].id, { type: 'stopRecording' });
        });
    });
});

chrome.storage.local.get('recordingState', (result) => {
    updateButtonState(result.recordingState || 'play');
});
