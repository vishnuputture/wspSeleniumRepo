chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        if (request.type === 'userActions') {
            console.log('Received userActions message in background script:', request.events);
        }
        return true;
    });
