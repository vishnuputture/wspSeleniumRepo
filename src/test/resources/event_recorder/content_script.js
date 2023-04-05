window.userActions = [];
window.hasNewEvents = false;
window.isTypingInInput = false;

initializeRecordEventsState();

const debouncedInput = debounce(function(event) {
    let xpath = getXPath(event.target);
    let id = getId(event.target)
    let name = getName(event.target);
    let cssSelector = getCssSelector(event.target);
    let text = event.target.value;
    let innerText = getInnerText(event.target);
    window.userActions.push({ type: 'input', id: id, name: name, cssSelector: cssSelector, xpath: xpath, innerText: innerText, value: text, timestamp: Date.now() });
    window.hasNewEvents = true;
    saveEventsToStorage();
    window.isTypingInInput = false;
}, 1000);

const eventListeners = {
    click: null,
    mouseover: null,
    mousedown: null,
    keydown: null,
    scroll: null,
    input: null
};
function createEventListeners() {
    eventListeners.click = function(event) {
        if (event.altKey) {
            eventHandler(event, 'validateText');
        } else {
            eventHandler(event, 'click');
        }
    }

    eventListeners.mouseover = function(event) {
        if (!hasHoverableAction(event.target)) return;
        eventHandler(event, 'mouseover');
    }

    eventListeners.input = function(event) {
        if (event.altKey) return;
        const tagName = event.target.tagName.toLowerCase();
        const inputType = event.target.type;
        if ((tagName === 'input' && (inputType === 'text' || inputType === 'search' || inputType === 'password'
                || inputType === 'email' || inputType === 'url' || inputType === 'tel' || inputType === 'number'))
            || tagName === 'textarea') {
                window.isTypingInInput = true;
                debouncedInput(event);
                highlightElement(event.target);
                console.log('Input recorded.');
        }
    }


    eventListeners.scroll = function(event) {
        //eventHandler(event, 'scroll');
    }

    eventListeners.keydown = function(event) {
        if (event.altKey) return;
        const tagName = event.target.tagName.toLowerCase();
        const inputType = event.target.type;
        const isTextInput = (tagName === 'input' && (inputType === 'text' || inputType === 'search' || inputType === 'password'
                || inputType === 'email' || inputType === 'url' || inputType === 'tel' || inputType === 'number'))
            || tagName === 'textarea';
        const isActionKey = event.key === 'Enter' || event.key === 'Tab' || (event.key.startsWith('F') && parseInt(event.key.slice(1)) >= 1 && parseInt(event.key.slice(1)) <= 12);

        if (!window.isTypingInInput && !isTextInput) {
            setTimeout(() => {
                if (window.isTypingInInput) {
                    debouncedInput.flush(event);
                }
                eventHandler(event, 'keydown');
            }, 50);
        } else if (isTextInput && isActionKey) {
            if (window.isTypingInInput) {
                debouncedInput.flush(event);
            }
            setTimeout(() => {
                eventHandler(event, 'keydown');
            }, 50);
        }
    }

    eventListeners.mousedown = function(event) {
        //eventHandler(event, 'mousedown');
    }
}
function initializeEventListeners() {
    createEventListeners();
    document.addEventListener('click', eventListeners.click, true);
    document.addEventListener('input', eventListeners.input, true);
    document.addEventListener('keydown', eventListeners.keydown, true);
    document.addEventListener('mouseover', eventListeners.mouseover, true);
    document.addEventListener('mousedown', eventListeners.mousedown, true);
    document.addEventListener('scroll', eventListeners.scroll, true);
}

function removeEventListeners() {
    document.removeEventListener('click', eventListeners.click, true);
    document.removeEventListener('input', eventListeners.input, true);
    document.removeEventListener('keydown', eventListeners.keydown, true);
    document.removeEventListener('mouseover', eventListeners.mouseover, true);
    document.removeEventListener('mousedown', eventListeners.mousedown, true);
    document.removeEventListener('scroll', eventListeners.scroll, true);
}

function initializeRecordEventsState() {
    chrome.storage.local.get('recordingState', function(result) {
        let recordEvents = result.recordingState === 'play';
        if (recordEvents) {
            initializeEventListeners();
        } else {
            removeEventListeners();
        }
    });
}

function debounce(func, wait) {
    let timeout;
    const debounced = function(...args) {
        const context = this;
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(context, args), wait);
    };
    debounced.flush = function(event) {
        clearTimeout(timeout);
        if (event) {
            func.apply(this, [event]);
        } else {
            func.apply(this);
        }
    };
    return debounced;
}

function getCssSelector(element) {
    let dataAttributes = Array.from(element.attributes).filter(attr => attr.name.startsWith('data-'));
    if (dataAttributes.length > 0) {
        return dataAttributes.map(attr => `[${attr.name}='${attr.value}']`).join('');
    }
    let path = [];
    while (element) {
        let selector = element.tagName.toLowerCase();
        if (element.className) {
            selector += "." + element.className.trim().replace(/\s+/g, ".");
        }
        path.unshift(selector);
        element = element.parentElement;
    }
    return path.join(" > ");
}
function getInnerText(element) {
    return element.textContent || element.innerText || '';
}
function getId(element) {
    return element.id;
}
function getName(element) {
    return element.name;
}
function getXPath(element) {
    let dataAttributes = Array.from(element.attributes).filter(attr => attr.name.startsWith('data-'));
    if (dataAttributes.length > 0) {
        let dataAttrXPath = dataAttributes.map(attr => `[@${attr.name}='${attr.value}']`).join('');
        return `//${element.tagName}${dataAttrXPath}`;
    }

    let path = [];
    for (; element && element.nodeType === 1; element = element.parentNode) {
        let index = 0;
        for (let sibling = element.previousSibling; sibling; sibling = sibling.previousSibling) {
            if (sibling.nodeType === 1 && sibling.tagName === element.tagName) {
                index++;
            }
        }
        let tagName = element.tagName.toLowerCase();
        let locator = `${tagName}${index > 0 ? `[${index + 1}]` : ''}`;
        path.unshift(locator);
    }
    return path.length ? '/' + path.join('/') : null;
}


function saveEventsToStorage() {
    if (!window.hasNewEvents) return;

    chrome.storage.local.get('userActions', function(result) {
        let storedUserActions = result.userActions || [];
        window.userActions = window.userActions.map((action, index) => {
            return {
                ...action,
                step: storedUserActions.length + index + 1
            };
        });
        window.userActions = storedUserActions.concat(window.userActions);
        console.log('Total Steps: ' + window.userActions.length);
        chrome.storage.local.set({ userActions: window.userActions }, function() {
            window.userActions = [];
            window.hasNewEvents = false;
        });
    });
}

function sendCapturedEvents() {
    setTimeout(() => {
        window.postMessage({ type: 'userActions', events: window.userActions }, window.location.origin);
        console.log('Sent userActions message');
    }, 1000);
}

function highlightElement(element) {
    const highlightId = 'recording-element-highlight';
    let existingHighlight = document.getElementById(highlightId);

    if (existingHighlight) {
        existingHighlight.remove();
    }

    let rect = element.getBoundingClientRect();
    let highlight = document.createElement('div');
    highlight.id = highlightId;
    highlight.style.position = 'fixed';
    highlight.style.top = rect.top + 'px';
    highlight.style.left = rect.left + 'px';
    highlight.style.width = rect.width + 'px';
    highlight.style.height = rect.height + 'px';
    highlight.style.border = '2px solid red';
    highlight.style.boxSizing = 'border-box';
    highlight.style.pointerEvents = 'none';
    highlight.style.zIndex = 99999;

    document.body.appendChild(highlight);

    setTimeout(() => {
        highlight.remove();
    }, 1000);
}

function getComputedStyles(element) {
    const computedStyles = window.getComputedStyle(element);
    let styleObject = {};

    for (let i = 0; i < computedStyles.length; i++) {
        const propertyName = computedStyles[i];
        styleObject[propertyName] = computedStyles.getPropertyValue(propertyName);
    }

    return styleObject;
}

let isHandlingEvent = false;
let lastEventTimestamp = 0;
const eventThreshold = 100;

function eventHandler(event, eventType) {
    const currentTime = Date.now();
    if (isHandlingEvent || currentTime - lastEventTimestamp < eventThreshold) {
        return;
    }
    if (isTypingInInput) {
        debouncedInput.flush(event);
    }
    lastEventTimestamp = currentTime;
    isHandlingEvent = true;
    let element = event.target;
    let xpath = getXPath(element);
    let innerText = getInnerText(element);
    let id = getId(element)
    let name = getName(element);
    let cssSelector = getCssSelector(element);
    let additionalData = {};
    if (eventType === 'keydown') {
        additionalData.key = event.key;
    } else if (eventType === 'scroll') {
        additionalData.deltaX = event.deltaX;
        additionalData.deltaY = event.deltaY;
    } else if (eventType === 'validateText') {
        let currentText = element.textContent || element.innerText || '';
        additionalData.currentText = currentText.trim()
    } else if (eventType === 'click' || eventType === 'mousedown' || eventType === 'mouseover') {
        additionalData.clientX = event.clientX;
        additionalData.clientY = event.clientY;
    }
    window.userActions.push({ type: eventType, id: id, name: name, cssSelector: cssSelector, xpath: xpath, innerText: innerText, timestamp: currentTime, ...additionalData });
    window.hasNewEvents = true;
    saveEventsToStorage();
    highlightElement(element);
    console.log(eventType + ' recorded.');
    setTimeout(() => {
        isHandlingEvent = false;
    }, eventThreshold);
}
function hasHoverableAction(element) {
    const inlineHoverStyle = element.getAttribute('style') && element.getAttribute('style').includes(':hover');
    const hasMouseOverListener = element.getAttribute('onmouseover') !== null;
    const hasTooltip = element.hasAttribute('ng-reflect-text');

    return inlineHoverStyle || hasMouseOverListener || hasTooltip;
}
chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
    if (request.type === 'startRecording') {
        initializeEventListeners();
        console.log('Start Recording Pressed');
    } else if (request.type === 'pauseRecording') {
        removeEventListeners();
        console.log('Pause Recording Pressed');
    } else if (request.type === 'stopRecording') {
        console.log('Received stopRecording message');
        removeEventListeners();
        window.postMessage({ type: 'stopRecording' }, window.location.origin);
        chrome.storage.local.get('userActions', function(result) {
            if (result.userActions) {
                console.log('Loaded user actions from storage:', result.userActions.length);
                window.userActions = result.userActions;
                sendCapturedEvents();
            } else {
                console.log('No user actions found in storage.');
            }
        });
    }
});
