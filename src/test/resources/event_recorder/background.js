let activeTabId;
chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        if (request.type === 'getActiveTabId') {
            sendResponse({ activeTabId: activeTabId });
            console.log (activeTabId);
        } else if (request.type === 'userActions') {
            console.log('Received userActions message in background script:', request.events);
        }
        return true;
    });
chrome.action.onClicked.addListener((tab) => {
    activeTabId = tab.id;
    const url = chrome.runtime.getURL("popup.html");
    chrome.windows.create({
        url: url,
        type: "popup",
        width: 600,
        height: 800,
    });
});