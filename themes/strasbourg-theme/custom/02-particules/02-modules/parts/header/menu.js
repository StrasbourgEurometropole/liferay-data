function isTouchDevice() {
    return 'ontouchstart' in document.documentElement;
}

function isNoHover(){
    if(isTouchDevice() && document.body.clientWidth <= 1280){
        return true;
    }
    return false;
}

function isTabletPortraitOrSmalller(){
    if(document.body.clientWidth < 980){
        return true;
    }
    return false;
}

function isMobileOrSmaller(){
    if(document.body.clientWidth <= 600){
        return true;
    }
    return false;
}
;(function () {
	'use strict';

	/**
	 * @preserve FastClick: polyfill to remove click delays on browsers with touch UIs.
	 *
	 * @codingstandard ftlabs-jsv2
	 * @copyright The Financial Times Limited [All Rights Reserved]
	 * @license MIT License (see LICENSE.txt)
	 */

	/*jslint browser:true, node:true*/
	/*global define, Event, Node*/


	/**
	 * Instantiate fast-clicking listeners on the specified layer.
	 *
	 * @constructor
	 * @param {Element} layer The layer to listen on
	 * @param {Object} [options={}] The options to override the defaults
	 */
	function FastClick(layer, options) {
		var oldOnClick;

		options = options || {};

		/**
		 * Whether a click is currently being tracked.
		 *
		 * @type boolean
		 */
		this.trackingClick = false;


		/**
		 * Timestamp for when click tracking started.
		 *
		 * @type number
		 */
		this.trackingClickStart = 0;


		/**
		 * The element being tracked for a click.
		 *
		 * @type EventTarget
		 */
		this.targetElement = null;


		/**
		 * X-coordinate of touch start event.
		 *
		 * @type number
		 */
		this.touchStartX = 0;


		/**
		 * Y-coordinate of touch start event.
		 *
		 * @type number
		 */
		this.touchStartY = 0;


		/**
		 * ID of the last touch, retrieved from Touch.identifier.
		 *
		 * @type number
		 */
		this.lastTouchIdentifier = 0;


		/**
		 * Touchmove boundary, beyond which a click will be cancelled.
		 *
		 * @type number
		 */
		this.touchBoundary = options.touchBoundary || 10;


		/**
		 * The FastClick layer.
		 *
		 * @type Element
		 */
		this.layer = layer;

		/**
		 * The minimum time between tap(touchstart and touchend) events
		 *
		 * @type number
		 */
		this.tapDelay = options.tapDelay || 200;

		/**
		 * The maximum time for a tap
		 *
		 * @type number
		 */
		this.tapTimeout = options.tapTimeout || 700;

		if (FastClick.notNeeded(layer)) {
			return;
		}

		// Some old versions of Android don't have Function.prototype.bind
		function bind(method, context) {
			return function() { return method.apply(context, arguments); };
		}


		var methods = ['onMouse', 'onClick', 'onTouchStart', 'onTouchMove', 'onTouchEnd', 'onTouchCancel'];
		var context = this;
		for (var i = 0, l = methods.length; i < l; i++) {
			context[methods[i]] = bind(context[methods[i]], context);
		}

		// Set up event handlers as required
		if (deviceIsAndroid) {
			layer.addEventListener('mouseover', this.onMouse, true);
			layer.addEventListener('mousedown', this.onMouse, true);
			layer.addEventListener('mouseup', this.onMouse, true);
		}

		layer.addEventListener('click', this.onClick, true);
		layer.addEventListener('touchstart', this.onTouchStart, false);
		layer.addEventListener('touchmove', this.onTouchMove, false);
		layer.addEventListener('touchend', this.onTouchEnd, false);
		layer.addEventListener('touchcancel', this.onTouchCancel, false);

		// Hack is required for browsers that don't support Event#stopImmediatePropagation (e.g. Android 2)
		// which is how FastClick normally stops click events bubbling to callbacks registered on the FastClick
		// layer when they are cancelled.
		if (!Event.prototype.stopImmediatePropagation) {
			layer.removeEventListener = function(type, callback, capture) {
				var rmv = Node.prototype.removeEventListener;
				if (type === 'click') {
					rmv.call(layer, type, callback.hijacked || callback, capture);
				} else {
					rmv.call(layer, type, callback, capture);
				}
			};

			layer.addEventListener = function(type, callback, capture) {
				var adv = Node.prototype.addEventListener;
				if (type === 'click') {
					adv.call(layer, type, callback.hijacked || (callback.hijacked = function(event) {
						if (!event.propagationStopped) {
							callback(event);
						}
					}), capture);
				} else {
					adv.call(layer, type, callback, capture);
				}
			};
		}

		// If a handler is already declared in the element's onclick attribute, it will be fired before
		// FastClick's onClick handler. Fix this by pulling out the user-defined handler function and
		// adding it as listener.
		if (typeof layer.onclick === 'function') {

			// Android browser on at least 3.2 requires a new reference to the function in layer.onclick
			// - the old one won't work if passed to addEventListener directly.
			oldOnClick = layer.onclick;
			layer.addEventListener('click', function(event) {
				oldOnClick(event);
			}, false);
			layer.onclick = null;
		}
	}

	/**
	* Windows Phone 8.1 fakes user agent string to look like Android and iPhone.
	*
	* @type boolean
	*/
	var deviceIsWindowsPhone = navigator.userAgent.indexOf("Windows Phone") >= 0;

	/**
	 * Android requires exceptions.
	 *
	 * @type boolean
	 */
	var deviceIsAndroid = navigator.userAgent.indexOf('Android') > 0 && !deviceIsWindowsPhone;


	/**
	 * iOS requires exceptions.
	 *
	 * @type boolean
	 */
	var deviceIsIOS = /iP(ad|hone|od)/.test(navigator.userAgent) && !deviceIsWindowsPhone;


	/**
	 * iOS 4 requires an exception for select elements.
	 *
	 * @type boolean
	 */
	var deviceIsIOS4 = deviceIsIOS && (/OS 4_\d(_\d)?/).test(navigator.userAgent);


	/**
	 * iOS 6.0-7.* requires the target element to be manually derived
	 *
	 * @type boolean
	 */
	var deviceIsIOSWithBadTarget = deviceIsIOS && (/OS [6-7]_\d/).test(navigator.userAgent);

	/**
	 * BlackBerry requires exceptions.
	 *
	 * @type boolean
	 */
	var deviceIsBlackBerry10 = navigator.userAgent.indexOf('BB10') > 0;

	/**
	 * Determine whether a given element requires a native click.
	 *
	 * @param {EventTarget|Element} target Target DOM element
	 * @returns {boolean} Returns true if the element needs a native click
	 */
	FastClick.prototype.needsClick = function(target) {
		switch (target.nodeName.toLowerCase()) {

		// Don't send a synthetic click to disabled inputs (issue #62)
		case 'button':
		case 'select':
		case 'textarea':
			if (target.disabled) {
				return true;
			}

			break;
		case 'input':

			// File inputs need real clicks on iOS 6 due to a browser bug (issue #68)
			if ((deviceIsIOS && target.type === 'file') || target.disabled) {
				return true;
			}

			break;
		case 'label':
		case 'iframe': // iOS8 homescreen apps can prevent events bubbling into frames
		case 'video':
			return true;
		}

		return (/\bneedsclick\b/).test(target.className);
	};


	/**
	 * Determine whether a given element requires a call to focus to simulate click into element.
	 *
	 * @param {EventTarget|Element} target Target DOM element
	 * @returns {boolean} Returns true if the element requires a call to focus to simulate native click.
	 */
	FastClick.prototype.needsFocus = function(target) {
		switch (target.nodeName.toLowerCase()) {
		case 'textarea':
			return true;
		case 'select':
			return !deviceIsAndroid;
		case 'input':
			switch (target.type) {
			case 'button':
			case 'checkbox':
			case 'file':
			case 'image':
			case 'radio':
			case 'submit':
				return false;
			}

			// No point in attempting to focus disabled inputs
			return !target.disabled && !target.readOnly;
		default:
			return (/\bneedsfocus\b/).test(target.className);
		}
	};


	/**
	 * Send a click event to the specified element.
	 *
	 * @param {EventTarget|Element} targetElement
	 * @param {Event} event
	 */
	FastClick.prototype.sendClick = function(targetElement, event) {
		var clickEvent, touch;

		// On some Android devices activeElement needs to be blurred otherwise the synthetic click will have no effect (#24)
		if (document.activeElement && document.activeElement !== targetElement) {
			document.activeElement.blur();
		}

		touch = event.changedTouches[0];

		// Synthesise a click event, with an extra attribute so it can be tracked
		clickEvent = document.createEvent('MouseEvents');
		clickEvent.initMouseEvent(this.determineEventType(targetElement), true, true, window, 1, touch.screenX, touch.screenY, touch.clientX, touch.clientY, false, false, false, false, 0, null);
		clickEvent.forwardedTouchEvent = true;
		targetElement.dispatchEvent(clickEvent);
	};

	FastClick.prototype.determineEventType = function(targetElement) {

		//Issue #159: Android Chrome Select Box does not open with a synthetic click event
		if (deviceIsAndroid && targetElement.tagName.toLowerCase() === 'select') {
			return 'mousedown';
		}

		return 'click';
	};


	/**
	 * @param {EventTarget|Element} targetElement
	 */
	FastClick.prototype.focus = function(targetElement) {
		var length;

		// Issue #160: on iOS 7, some input elements (e.g. date datetime month) throw a vague TypeError on setSelectionRange. These elements don't have an integer value for the selectionStart and selectionEnd properties, but unfortunately that can't be used for detection because accessing the properties also throws a TypeError. Just check the type instead. Filed as Apple bug #15122724.
		if (deviceIsIOS && targetElement.setSelectionRange && targetElement.type.indexOf('date') !== 0 && targetElement.type !== 'time' && targetElement.type !== 'month' && targetElement.type !== 'email') {
			length = targetElement.value.length;
			targetElement.setSelectionRange(length, length);
		} else {
			targetElement.focus();
		}
	};


	/**
	 * Check whether the given target element is a child of a scrollable layer and if so, set a flag on it.
	 *
	 * @param {EventTarget|Element} targetElement
	 */
	FastClick.prototype.updateScrollParent = function(targetElement) {
		var scrollParent, parentElement;

		scrollParent = targetElement.fastClickScrollParent;

		// Attempt to discover whether the target element is contained within a scrollable layer. Re-check if the
		// target element was moved to another parent.
		if (!scrollParent || !scrollParent.contains(targetElement)) {
			parentElement = targetElement;
			do {
				if (parentElement.scrollHeight > parentElement.offsetHeight) {
					scrollParent = parentElement;
					targetElement.fastClickScrollParent = parentElement;
					break;
				}

				parentElement = parentElement.parentElement;
			} while (parentElement);
		}

		// Always update the scroll top tracker if possible.
		if (scrollParent) {
			scrollParent.fastClickLastScrollTop = scrollParent.scrollTop;
		}
	};


	/**
	 * @param {EventTarget} targetElement
	 * @returns {Element|EventTarget}
	 */
	FastClick.prototype.getTargetElementFromEventTarget = function(eventTarget) {

		// On some older browsers (notably Safari on iOS 4.1 - see issue #56) the event target may be a text node.
		if (eventTarget.nodeType === Node.TEXT_NODE) {
			return eventTarget.parentNode;
		}

		return eventTarget;
	};


	/**
	 * On touch start, record the position and scroll offset.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onTouchStart = function(event) {
		var targetElement, touch, selection;

		// Ignore multiple touches, otherwise pinch-to-zoom is prevented if both fingers are on the FastClick element (issue #111).
		if (event.targetTouches.length > 1) {
			return true;
		}

		targetElement = this.getTargetElementFromEventTarget(event.target);
		touch = event.targetTouches[0];

		if (deviceIsIOS) {

			// Only trusted events will deselect text on iOS (issue #49)
			selection = window.getSelection();
			if (selection.rangeCount && !selection.isCollapsed) {
				return true;
			}

			if (!deviceIsIOS4) {

				// Weird things happen on iOS when an alert or confirm dialog is opened from a click event callback (issue #23):
				// when the user next taps anywhere else on the page, new touchstart and touchend events are dispatched
				// with the same identifier as the touch event that previously triggered the click that triggered the alert.
				// Sadly, there is an issue on iOS 4 that causes some normal touch events to have the same identifier as an
				// immediately preceeding touch event (issue #52), so this fix is unavailable on that platform.
				// Issue 120: touch.identifier is 0 when Chrome dev tools 'Emulate touch events' is set with an iOS device UA string,
				// which causes all touch events to be ignored. As this block only applies to iOS, and iOS identifiers are always long,
				// random integers, it's safe to to continue if the identifier is 0 here.
				if (touch.identifier && touch.identifier === this.lastTouchIdentifier) {
					event.preventDefault();
					return false;
				}

				this.lastTouchIdentifier = touch.identifier;

				// If the target element is a child of a scrollable layer (using -webkit-overflow-scrolling: touch) and:
				// 1) the user does a fling scroll on the scrollable layer
				// 2) the user stops the fling scroll with another tap
				// then the event.target of the last 'touchend' event will be the element that was under the user's finger
				// when the fling scroll was started, causing FastClick to send a click event to that layer - unless a check
				// is made to ensure that a parent layer was not scrolled before sending a synthetic click (issue #42).
				this.updateScrollParent(targetElement);
			}
		}

		this.trackingClick = true;
		this.trackingClickStart = event.timeStamp;
		this.targetElement = targetElement;

		this.touchStartX = touch.pageX;
		this.touchStartY = touch.pageY;

		// Prevent phantom clicks on fast double-tap (issue #36)
		if ((event.timeStamp - this.lastClickTime) < this.tapDelay) {
			event.preventDefault();
		}

		return true;
	};


	/**
	 * Based on a touchmove event object, check whether the touch has moved past a boundary since it started.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.touchHasMoved = function(event) {
		var touch = event.changedTouches[0], boundary = this.touchBoundary;

		if (Math.abs(touch.pageX - this.touchStartX) > boundary || Math.abs(touch.pageY - this.touchStartY) > boundary) {
			return true;
		}

		return false;
	};


	/**
	 * Update the last position.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onTouchMove = function(event) {
		if (!this.trackingClick) {
			return true;
		}

		// If the touch has moved, cancel the click tracking
		if (this.targetElement !== this.getTargetElementFromEventTarget(event.target) || this.touchHasMoved(event)) {
			this.trackingClick = false;
			this.targetElement = null;
		}

		return true;
	};


	/**
	 * Attempt to find the labelled control for the given label element.
	 *
	 * @param {EventTarget|HTMLLabelElement} labelElement
	 * @returns {Element|null}
	 */
	FastClick.prototype.findControl = function(labelElement) {

		// Fast path for newer browsers supporting the HTML5 control attribute
		if (labelElement.control !== undefined) {
			return labelElement.control;
		}

		// All browsers under test that support touch events also support the HTML5 htmlFor attribute
		if (labelElement.htmlFor) {
			return document.getElementById(labelElement.htmlFor);
		}

		// If no for attribute exists, attempt to retrieve the first labellable descendant element
		// the list of which is defined here: http://www.w3.org/TR/html5/forms.html#category-label
		return labelElement.querySelector('button, input:not([type=hidden]), keygen, meter, output, progress, select, textarea');
	};


	/**
	 * On touch end, determine whether to send a click event at once.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onTouchEnd = function(event) {
		var forElement, trackingClickStart, targetTagName, scrollParent, touch, targetElement = this.targetElement;

		if (!this.trackingClick) {
			return true;
		}

		// Prevent phantom clicks on fast double-tap (issue #36)
		if ((event.timeStamp - this.lastClickTime) < this.tapDelay) {
			this.cancelNextClick = true;
			return true;
		}

		if ((event.timeStamp - this.trackingClickStart) > this.tapTimeout) {
			return true;
		}

		// Reset to prevent wrong click cancel on input (issue #156).
		this.cancelNextClick = false;

		this.lastClickTime = event.timeStamp;

		trackingClickStart = this.trackingClickStart;
		this.trackingClick = false;
		this.trackingClickStart = 0;

		// On some iOS devices, the targetElement supplied with the event is invalid if the layer
		// is performing a transition or scroll, and has to be re-detected manually. Note that
		// for this to function correctly, it must be called *after* the event target is checked!
		// See issue #57; also filed as rdar://13048589 .
		if (deviceIsIOSWithBadTarget) {
			touch = event.changedTouches[0];

			// In certain cases arguments of elementFromPoint can be negative, so prevent setting targetElement to null
			targetElement = document.elementFromPoint(touch.pageX - window.pageXOffset, touch.pageY - window.pageYOffset) || targetElement;
			targetElement.fastClickScrollParent = this.targetElement.fastClickScrollParent;
		}

		targetTagName = targetElement.tagName.toLowerCase();
		if (targetTagName === 'label') {
			forElement = this.findControl(targetElement);
			if (forElement) {
				this.focus(targetElement);
				if (deviceIsAndroid) {
					return false;
				}

				targetElement = forElement;
			}
		} else if (this.needsFocus(targetElement)) {

			// Case 1: If the touch started a while ago (best guess is 100ms based on tests for issue #36) then focus will be triggered anyway. Return early and unset the target element reference so that the subsequent click will be allowed through.
			// Case 2: Without this exception for input elements tapped when the document is contained in an iframe, then any inputted text won't be visible even though the value attribute is updated as the user types (issue #37).
			if ((event.timeStamp - trackingClickStart) > 100 || (deviceIsIOS && window.top !== window && targetTagName === 'input')) {
				this.targetElement = null;
				return false;
			}

			this.focus(targetElement);
			this.sendClick(targetElement, event);

			// Select elements need the event to go through on iOS 4, otherwise the selector menu won't open.
			// Also this breaks opening selects when VoiceOver is active on iOS6, iOS7 (and possibly others)
			if (!deviceIsIOS || targetTagName !== 'select') {
				this.targetElement = null;
				event.preventDefault();
			}

			return false;
		}

		if (deviceIsIOS && !deviceIsIOS4) {

			// Don't send a synthetic click event if the target element is contained within a parent layer that was scrolled
			// and this tap is being used to stop the scrolling (usually initiated by a fling - issue #42).
			scrollParent = targetElement.fastClickScrollParent;
			if (scrollParent && scrollParent.fastClickLastScrollTop !== scrollParent.scrollTop) {
				return true;
			}
		}

		// Prevent the actual click from going though - unless the target node is marked as requiring
		// real clicks or if it is in the whitelist in which case only non-programmatic clicks are permitted.
		if (!this.needsClick(targetElement)) {
			event.preventDefault();
			this.sendClick(targetElement, event);
		}

		return false;
	};


	/**
	 * On touch cancel, stop tracking the click.
	 *
	 * @returns {void}
	 */
	FastClick.prototype.onTouchCancel = function() {
		this.trackingClick = false;
		this.targetElement = null;
	};


	/**
	 * Determine mouse events which should be permitted.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onMouse = function(event) {

		// If a target element was never set (because a touch event was never fired) allow the event
		if (!this.targetElement) {
			return true;
		}

		if (event.forwardedTouchEvent) {
			return true;
		}

		// Programmatically generated events targeting a specific element should be permitted
		if (!event.cancelable) {
			return true;
		}

		// Derive and check the target element to see whether the mouse event needs to be permitted;
		// unless explicitly enabled, prevent non-touch click events from triggering actions,
		// to prevent ghost/doubleclicks.
		if (!this.needsClick(this.targetElement) || this.cancelNextClick) {

			// Prevent any user-added listeners declared on FastClick element from being fired.
			if (event.stopImmediatePropagation) {
				event.stopImmediatePropagation();
			} else {

				// Part of the hack for browsers that don't support Event#stopImmediatePropagation (e.g. Android 2)
				event.propagationStopped = true;
			}

			// Cancel the event
			event.stopPropagation();
			event.preventDefault();

			return false;
		}

		// If the mouse event is permitted, return true for the action to go through.
		return true;
	};


	/**
	 * On actual clicks, determine whether this is a touch-generated click, a click action occurring
	 * naturally after a delay after a touch (which needs to be cancelled to avoid duplication), or
	 * an actual click which should be permitted.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onClick = function(event) {
		var permitted;

		// It's possible for another FastClick-like library delivered with third-party code to fire a click event before FastClick does (issue #44). In that case, set the click-tracking flag back to false and return early. This will cause onTouchEnd to return early.
		if (this.trackingClick) {
			this.targetElement = null;
			this.trackingClick = false;
			return true;
		}

		// Very odd behaviour on iOS (issue #18): if a submit element is present inside a form and the user hits enter in the iOS simulator or clicks the Go button on the pop-up OS keyboard the a kind of 'fake' click event will be triggered with the submit-type input element as the target.
		if (event.target.type === 'submit' && event.detail === 0) {
			return true;
		}

		permitted = this.onMouse(event);

		// Only unset targetElement if the click is not permitted. This will ensure that the check for !targetElement in onMouse fails and the browser's click doesn't go through.
		if (!permitted) {
			this.targetElement = null;
		}

		// If clicks are permitted, return true for the action to go through.
		return permitted;
	};


	/**
	 * Remove all FastClick's event listeners.
	 *
	 * @returns {void}
	 */
	FastClick.prototype.destroy = function() {
		var layer = this.layer;

		if (deviceIsAndroid) {
			layer.removeEventListener('mouseover', this.onMouse, true);
			layer.removeEventListener('mousedown', this.onMouse, true);
			layer.removeEventListener('mouseup', this.onMouse, true);
		}

		layer.removeEventListener('click', this.onClick, true);
		layer.removeEventListener('touchstart', this.onTouchStart, false);
		layer.removeEventListener('touchmove', this.onTouchMove, false);
		layer.removeEventListener('touchend', this.onTouchEnd, false);
		layer.removeEventListener('touchcancel', this.onTouchCancel, false);
	};


	/**
	 * Check whether FastClick is needed.
	 *
	 * @param {Element} layer The layer to listen on
	 */
	FastClick.notNeeded = function(layer) {
		var metaViewport;
		var chromeVersion;
		var blackberryVersion;
		var firefoxVersion;

		// Devices that don't support touch don't need FastClick
		if (typeof window.ontouchstart === 'undefined') {
			return true;
		}

		// Chrome version - zero for other browsers
		chromeVersion = +(/Chrome\/([0-9]+)/.exec(navigator.userAgent) || [,0])[1];

		if (chromeVersion) {

			if (deviceIsAndroid) {
				metaViewport = document.querySelector('meta[name=viewport]');

				if (metaViewport) {
					// Chrome on Android with user-scalable="no" doesn't need FastClick (issue #89)
					if (metaViewport.content.indexOf('user-scalable=no') !== -1) {
						return true;
					}
					// Chrome 32 and above with width=device-width or less don't need FastClick
					if (chromeVersion > 31 && document.documentElement.scrollWidth <= window.outerWidth) {
						return true;
					}
				}

			// Chrome desktop doesn't need FastClick (issue #15)
			} else {
				return true;
			}
		}

		if (deviceIsBlackBerry10) {
			blackberryVersion = navigator.userAgent.match(/Version\/([0-9]*)\.([0-9]*)/);

			// BlackBerry 10.3+ does not require Fastclick library.
			// https://github.com/ftlabs/fastclick/issues/251
			if (blackberryVersion[1] >= 10 && blackberryVersion[2] >= 3) {
				metaViewport = document.querySelector('meta[name=viewport]');

				if (metaViewport) {
					// user-scalable=no eliminates click delay.
					if (metaViewport.content.indexOf('user-scalable=no') !== -1) {
						return true;
					}
					// width=device-width (or less than device-width) eliminates click delay.
					if (document.documentElement.scrollWidth <= window.outerWidth) {
						return true;
					}
				}
			}
		}

		// IE10 with -ms-touch-action: none or manipulation, which disables double-tap-to-zoom (issue #97)
		if (layer.style.msTouchAction === 'none' || layer.style.touchAction === 'manipulation') {
			return true;
		}

		// Firefox version - zero for other browsers
		firefoxVersion = +(/Firefox\/([0-9]+)/.exec(navigator.userAgent) || [,0])[1];

		if (firefoxVersion >= 27) {
			// Firefox 27+ does not have tap delay if the content is not zoomable - https://bugzilla.mozilla.org/show_bug.cgi?id=922896

			metaViewport = document.querySelector('meta[name=viewport]');
			if (metaViewport && (metaViewport.content.indexOf('user-scalable=no') !== -1 || document.documentElement.scrollWidth <= window.outerWidth)) {
				return true;
			}
		}

		// IE11: prefixed -ms-touch-action is no longer supported and it's recomended to use non-prefixed version
		// http://msdn.microsoft.com/en-us/library/windows/apps/Hh767313.aspx
		if (layer.style.touchAction === 'none' || layer.style.touchAction === 'manipulation') {
			return true;
		}

		return false;
	};


	/**
	 * Factory method for creating a FastClick object
	 *
	 * @param {Element} layer The layer to listen on
	 * @param {Object} [options={}] The options to override the defaults
	 */
	FastClick.attach = function(layer, options) {
		return new FastClick(layer, options);
	};


	if (typeof define === 'function' && typeof define.amd === 'object' && define.amd) {

		// AMD. Register as an anonymous module.
		define(function() {
			return FastClick;
		});
	} else if (typeof module !== 'undefined' && module.exports) {
		module.exports = FastClick.attach;
		module.exports.FastClick = FastClick;
	} else {
		window.FastClick = FastClick;
	}
}());
/*! npm.im/object-fit-images 3.1.3 */
var objectFitImages = (function () {
    'use strict';

    var OFI = 'bfred-it:object-fit-images';
    var propRegex = /(object-fit|object-position)\s*:\s*([-\w\s%]+)/g;
    var testImg = typeof Image === 'undefined' ? {style: {'object-position': 1}} : new Image();
    var supportsObjectFit = 'object-fit' in testImg.style;
    var supportsObjectPosition = 'object-position' in testImg.style;
    var supportsOFI = 'background-size' in testImg.style;
    var supportsCurrentSrc = typeof testImg.currentSrc === 'string';
    var nativeGetAttribute = testImg.getAttribute;
    var nativeSetAttribute = testImg.setAttribute;
    var autoModeEnabled = false;

    function createPlaceholder(w, h) {
        return ("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='" + w + "' height='" + h + "'%3E%3C/svg%3E");
    }

    function polyfillCurrentSrc(el) {
        if (el.srcset && !supportsCurrentSrc && window.picturefill) {
            var pf = window.picturefill._;
            // parse srcset with picturefill where currentSrc isn't available
            if (!el[pf.ns] || !el[pf.ns].evaled) {
                // force synchronous srcset parsing
                pf.fillImg(el, {reselect: true});
            }

            if (!el[pf.ns].curSrc) {
                // force picturefill to parse srcset
                el[pf.ns].supported = false;
                pf.fillImg(el, {reselect: true});
            }

            // retrieve parsed currentSrc, if any
            el.currentSrc = el[pf.ns].curSrc || el.src;
        }
    }

    function getStyle(el) {
        var style = getComputedStyle(el).fontFamily;
        var parsed;
        var props = {};
        while ((parsed = propRegex.exec(style)) !== null) {
            props[parsed[1]] = parsed[2];
        }
        return props;
    }

    function setPlaceholder(img, width, height) {
        // Default: fill width, no height
        var placeholder = createPlaceholder(width || 1, height || 0);

        // Only set placeholder if it's different
        if (nativeGetAttribute.call(img, 'src') !== placeholder) {
            nativeSetAttribute.call(img, 'src', placeholder);
        }
    }

    function onImageReady(img, callback) {
        // naturalWidth is only available when the image headers are loaded,
        // this loop will poll it every 100ms.
        if (img.naturalWidth) {
            callback(img);
        } else {
            setTimeout(onImageReady, 100, img, callback);
        }
    }

    function fixOne(el) {
        var style = getStyle(el);
        var ofi = el[OFI];
        style['object-fit'] = style['object-fit'] || 'fill'; // default value

        // Avoid running where unnecessary, unless OFI had already done its deed
        if (!ofi.img) {
            // fill is the default behavior so no action is necessary
            if (style['object-fit'] === 'fill') {
                return;
            }

            // Where object-fit is supported and object-position isn't (Safari < 10)
            if (
                !ofi.skipTest && // unless user wants to apply regardless of browser support
                supportsObjectFit && // if browser already supports object-fit
                !style['object-position'] // unless object-position is used
            ) {
                return;
            }
        }

        // keep a clone in memory while resetting the original to a blank
        if (!ofi.img) {
            ofi.img = new Image(el.width, el.height);
            ofi.img.srcset = nativeGetAttribute.call(el, "data-ofi-srcset") || el.srcset;
            ofi.img.src = nativeGetAttribute.call(el, "data-ofi-src") || el.src;

            // preserve for any future cloneNode calls
            // https://github.com/bfred-it/object-fit-images/issues/53
            nativeSetAttribute.call(el, "data-ofi-src", el.src);
            if (el.srcset) {
                nativeSetAttribute.call(el, "data-ofi-srcset", el.srcset);
            }

            setPlaceholder(el, el.naturalWidth || el.width, el.naturalHeight || el.height);

            // remove srcset because it overrides src
            if (el.srcset) {
                el.srcset = '';
            }
            try {
                keepSrcUsable(el);
            } catch (err) {
                if (window.console) {
                    console.log('http://bit.ly/ofi-old-browser');
                }
            }
        }

        polyfillCurrentSrc(ofi.img);

        el.style.backgroundImage = "url(\"" + ((ofi.img.currentSrc || ofi.img.src).replace(/"/g, '\\"')) + "\")";
        el.style.backgroundPosition = style['object-position'] || 'center';
        el.style.backgroundRepeat = 'no-repeat';

        if (/scale-down/.test(style['object-fit'])) {
            onImageReady(ofi.img, function () {
                if (ofi.img.naturalWidth > el.width || ofi.img.naturalHeight > el.height) {
                    el.style.backgroundSize = 'contain';
                } else {
                    el.style.backgroundSize = 'auto';
                }
            });
        } else {
            el.style.backgroundSize = style['object-fit'].replace('none', 'auto').replace('fill', '100% 100%');
        }

        onImageReady(ofi.img, function (img) {
            setPlaceholder(el, img.naturalWidth, img.naturalHeight);
        });
    }

    function keepSrcUsable(el) {
        var descriptors = {
            get: function get(prop) {
                return el[OFI].img[prop ? prop : 'src'];
            },
            set: function set(value, prop) {
                el[OFI].img[prop ? prop : 'src'] = value;
                nativeSetAttribute.call(el, ("data-ofi-" + prop), value); // preserve for any future cloneNode
                fixOne(el);
                return value;
            }
        };
        Object.defineProperty(el, 'src', descriptors);
        Object.defineProperty(el, 'currentSrc', {
            get: function () { return descriptors.get('currentSrc'); }
        });
        Object.defineProperty(el, 'srcset', {
            get: function () { return descriptors.get('srcset'); },
            set: function (ss) { return descriptors.set(ss, 'srcset'); }
        });
    }

    function hijackAttributes() {
        function getOfiImageMaybe(el, name) {
            return el[OFI] && el[OFI].img && (name === 'src' || name === 'srcset') ? el[OFI].img : el;
        }
        if (!supportsObjectPosition) {
            HTMLImageElement.prototype.getAttribute = function (name) {
                return nativeGetAttribute.call(getOfiImageMaybe(this, name), name);
            };

            HTMLImageElement.prototype.setAttribute = function (name, value) {
                return nativeSetAttribute.call(getOfiImageMaybe(this, name), name, String(value));
            };
        }
    }

    function fix(imgs, opts) {
        var startAutoMode = !autoModeEnabled && !imgs;
        opts = opts || {};
        imgs = imgs || 'img';

        if ((supportsObjectPosition && !opts.skipTest) || !supportsOFI) {
            return false;
        }

        // use imgs as a selector or just select all images
        if (typeof imgs === 'string') {
            imgs = document.querySelectorAll(imgs);
        } else if (!('length' in imgs)) {
            imgs = [imgs];
        }

        // apply fix to all
        for (var i = 0; i < imgs.length; i++) {
            imgs[i][OFI] = imgs[i][OFI] || {
                    skipTest: opts.skipTest
                };
            fixOne(imgs[i]);
        }

        if (startAutoMode) {
            document.body.addEventListener('load', function (e) {
                if (e.target.tagName === 'IMG') {
                    fix(e.target, {
                        skipTest: opts.skipTest
                    });
                }
            }, true);
            autoModeEnabled = true;
            imgs = 'img'; // reset to a generic selector for watchMQ
        }

        // if requested, watch media queries for object-fit change
        if (opts.watchMQ) {
            window.addEventListener('resize', fix.bind(null, imgs, {
                skipTest: opts.skipTest
            }));
        }
    }

    fix.supportsObjectFit = supportsObjectFit;
    fix.supportsObjectPosition = supportsObjectPosition;

    hijackAttributes();

    return fix;

}());
var th_dropdown = {

    $last: null,

    init: function () {
        th_dropdown.parse($('body'));
    },

    parse: function (el) {
        $('.th-dropdown > button', el).each(function () {
            $(this).on('click', function (e) {
                e.preventDefault();
                if ($(this).parent().hasClass('open')) {
                    th_dropdown.close();
                } else {
                    th_dropdown.open($(this).parent());
                }
            });
        });
    },

    open: function ($dropdown) {
        if ($dropdown == th_dropdown.$last) {
            return false;
        }
        $dropdown.addClass('open');
        setTimeout(function () {
            th_dropdown.$last = $dropdown;
            $(document).on('click', th_dropdown.closeLastOnClick);
        }, 50);
    },

    closeLastOnClick: function (e) {
        if ($(e.target).parents('.th-dropdown').first() && th_dropdown.$last && ($(e.target).parents('.th-dropdown').first()[0] == th_dropdown.$last[0])) {
            return true;
        }
        th_dropdown.close();
    },

    close: function () {
        th_dropdown.$last.removeClass('open');
        $(document).off('click', th_dropdown.closeLastOnClick);
        th_dropdown.$last = null;
    }

};
var th_overlay = {

    selector_overlay: '.overlay',
    selector_overlay_shadow: '.shadow-overlay',

    callbackOpen: [],
    callbackClose: [],
    hashList: {},

    init: function () {
        th_overlay.parseOverlayShadow($('body'));
        th_overlay.parse($('body'));
        th_overlay.openOnLoad();
    },

    addCallbackOpen: function (callback) {
        th_overlay.callbackOpen.push(callback);
    },
    addCallbackClose: function (callback) {
        th_overlay.callbackClose.push(callback);
    },

    parseOverlayShadow: function () {
        $(th_overlay.selector_overlay_shadow).on('click', function (e) {
            e.preventDefault();
            th_overlay.close();
        });
    },

    parse: function (el) {

        $(th_overlay.selector_overlay).each(function () {

            var overlayId = this.id;
            var targetOpen = $('a[href="#' + overlayId + '"]:not(.parsed-overlay):not(.close), [data-overlay-open="' + overlayId + '"]:not(.parsed-overlay)');

            if ($(this).data("hash") !== undefined) {
                th_overlay.hashList[$(this).attr("data-hash")] = overlayId;
            }

            targetOpen.addClass('parsed-overlay')
                .on('click', function (e) {
                    e.preventDefault();
                    var idOverlay = $(this).attr('href');
                    if (!idOverlay) {
                        idOverlay = $(this).attr('data-overlay-open');
                    } else {
                        idOverlay = idOverlay.replace('#', '');
                    }
                    th_overlay.open(idOverlay);
                });

            $('a[href="#' + overlayId + '"].close:not(.parsed-overlay), [data-overlay-close="' + overlayId + '"]:not(.parsed-overlay)')
                .addClass('parsed-overlay')
                .on('click', function (e) {
                    e.preventDefault();
                    var idOverlay = $(this).attr('href');
                    if (!idOverlay) {
                        idOverlay = $(this).attr('data-overlay-close');
                    } else {
                        idOverlay = idOverlay.replace('#', '');
                    }

                    if (!idOverlay) {
                        idOverlay = null;
                    }
                    e.preventDefault();

					// on vide les résultats de recherche
					$(menuOverlay._selector.searchInput).val("");
					$(menuOverlay._selector.overlayContainer + " .th-search-results .th-hide-tablet-p").html("");
					$(menuOverlay._selector.overlayContainer + " .th-search-results .th-v-tablet-p").html("");
					$(menuOverlay._selector.overlayContainer + " .th-search-results .th-hide-tablet-p").hide();
					$(menuOverlay._selector.overlayContainer + " .th-search-results .th-v-tablet-p").hide();
					$(menuOverlay._selector.overlayContainer + " .th-search-results .th-all-results").html("");

                    th_overlay.close(idOverlay);
                });
        });
    },

    openOnLoad: function () {

        var current_hash = (window.location.hash).split('#');
        var founded_overlay = false;
        if (current_hash) {
            current_hash.forEach(function (element) {
                if (typeof th_overlay.hashList[element] !== 'undefined' && !founded_overlay) {
                    th_overlay.open(th_overlay.hashList[element]);
                    founded_overlay = true;
                }
            })
        }
    },

    open: function (overlayId, openShadow, doCallback) {
        if (openShadow !== false) {
            openShadow = true;
        }
        if (doCallback !== false) {
            doCallback = true;
        }


        $(th_overlay.selector_overlay + ".open").each(function (e) {
            th_overlay.close($(this).attr('id'), false, true)
		});
		
        $('#' + overlayId).addClass('open');

        if ($('#' + overlayId + '').data('hash') !== undefined) {
            window.location.hash = $('#' + overlayId + '').data('hash');
        }

        if (openShadow == true) {
            $(th_overlay.selector_overlay_shadow).addClass('open');
        }

		// on change la valeur aria-expanded des menus et sous-menus déjà ouvert
		$(menuOverlay._selector.overlayContainer + " li.th-active>a").each(function () {
			this.setAttribute('aria-expanded', "true");
		});

        if (doCallback == true) {
            $.each(th_overlay.callbackOpen, function (k, callback) {
                callback(overlayId);
            });
        }
    },

    close: function (overlayId, closeShadow, doCallback) {

        if (closeShadow !== false) {
            closeShadow = true;
        }
        if (doCallback !== false) {
            doCallback = true;
        }

        window.location.hash = "";
        history.replaceState(null, null, ' ');


        if (overlayId) {
            $('#' + overlayId).removeClass('open');
            if ($(th_overlay.selector_overlay + '.open').length == 0 && closeShadow) {
                $(th_overlay.selector_overlay_shadow).removeClass('open');
            }
        } else {
            $(th_overlay.selector_overlay + '.open').removeClass('open');
            if (closeShadow) {
                $(th_overlay.selector_overlay_shadow).removeClass('open');
            }
        }

		// on change la valeur aria-expanded des menus et sous-menus
		$(menuOverlay._selector.overlayContainer + " [aria-expanded='true']").each(function () {
			this.setAttribute('aria-expanded', "false");
		});

        if (doCallback) {
            $.each(th_overlay.callbackClose, function (k, callback) {
                callback(overlayId);
            });
        }

    }

};
var thVheight = {

    height: 0,

    init: function (key) {
        $(window).on('resize', function () {
            thVheight.update(key)
        });
        thVheight.update(key);
    },

    update: function (force) {

        if (thVheight.detectChange() || force) {

            $('[data-vheight],[data-m-vheight]').each(function () {
                var vheight = this.getAttribute('data-vheight');
                if (document.body.clientWidth < 600 && this.getAttribute('data-m-vheight')) {
                    vheight = this.getAttribute('data-m-vheight');
                }
                if (vheight) {
                    var vheightPercent;
                    var vheightMoin = 0;
                    if (vheight.match(/\ \-\ /)) {
                        var splitted = vheight.split(' - ');
                        vheightPercent = parseInt(splitted[0]);
                        vheightMoin = parseInt(splitted[1]);
                        var newHeight = ((thVheight.height / 100) * vheightPercent) - vheightMoin;
                    } else if (vheight.match(/\ \+\ /)) {
                        var splitted = vheight.split(' + ');
                        vheightPercent = parseInt(splitted[0]);
                        vheightMoin = parseInt(splitted[1]);
                        var newHeight = ((thVheight.height / 100) * vheightPercent) + vheightMoin;
                    } else {
                        vheightPercent = parseInt(vheight);
                        var newHeight = ((thVheight.height / 100) * vheightPercent) - vheightMoin;
                    }

                    $(this).height(newHeight);
                }
            });
        }

    },
    detectChange: function () {

        var newH = document.body.clientHeight;

        if ((newH - thVheight.height) > 150 || (thVheight.height - newH) > 150) {

            thVheight.height = newH;

            return true;
        }

        return false;
    }

};
// Back Menu Niveau 1
$('.back-level-1').on('click', function () {
    if (isTabletPortraitOrSmalller()) {
		if($('#th-overlay-nav nav > ul > li.th-has-submenu.th-active>a').length > 0 )
			$('#th-overlay-nav nav > ul > li.th-has-submenu.th-active>a').get(0).setAttribute('aria-expanded', "false");
        $('#th-overlay-nav nav > ul > li.th-has-submenu.th-active').removeClass('th-active');
    }
});

// Back Menu Niveau 1
$('.back-level-2').on('click', function () {
    if (isTabletPortraitOrSmalller()) {
        $('.th-hav-level-3.th-active>a').get(0).setAttribute('aria-expanded', "false");
        $('.th-hav-level-3.th-active').removeClass('th-active');
    }
});

var menuOverlay = {
    el: null,

    _selector: {
        headerContainer: ".th-bar-in-front",
        overlayContainer: "#th-overlay-nav",
        firstLvlClass: ".th-level-1",
        secondLvlData: ".data-level-2",
        secondLvlClass: ".th-level-2",
        wrapperData: ".data-wrapper",
        wrapperClass: ".th-wrapper",
        thirdLvlData: ".data-level-3",
        thirdLvlClass: ".th-menu-niveau-3",
        submenuContainers: ".th-hav-level-3",
        searchBtn: ".th-cta-search",
        searchOverlay: ".th-has-overlay-search",
        searchInput: ".th-input-search"
    },

    init: function (el) {
        if (el) {
            menuOverlay.el = el;

            menuOverlay.initEventsFirstLevelInOverlay();
            menuOverlay.initSearch();
            menuOverlay.initEventsFirstLevel();
        }
    },

    initSearch: function () {
        $(menuOverlay._selector.searchBtn).on("click", function (e) {
            e.preventDefault();

            menuOverlay.removeClassOfFirstElements();
            menuOverlay.removeClassOfSecondElements();

            if ($(menuOverlay._selector.searchOverlay).hasClass('th-active')) {
                $(menuOverlay._selector.searchOverlay).removeClass('th-active');
            } else {
                $(menuOverlay._selector.searchOverlay).addClass('th-active');
                $(menuOverlay._selector.searchInput, menuOverlay._selector.searchOverlay).get(0).focus();
            }
        });
    },

    initEventsFirstLevel: function () {
        $(menuOverlay._selector.firstLvlClass, menuOverlay._selector.headerContainer).on("click keyup", function (e) {
            if (this.getAttribute("data-th-menu") && (e.keyCode == undefined || e.keyCode == 13 || e.keyCode == 32)) {
                e.preventDefault();
                var correspondingMenu = $(menuOverlay._selector.firstLvlClass + "[data-th-menu='" + this.getAttribute("data-th-menu") + "']", menuOverlay._selector.overlayContainer).get(0);

                if (correspondingMenu) {
					th_overlay.open("th-overlay-nav");
					
                    // Remplissage du sous menu
					menuOverlay.setSecondLevel(correspondingMenu.parentNode.querySelector(".th-submenu"));
					// Remplissage des raccourcis
					menuOverlay.setWrapper(correspondingMenu.parentNode.querySelector(".th-submenu"));

                    menuOverlay.setActiveElement(correspondingMenu, true);

					// on change la valeur aria-expanded
					correspondingMenu.setAttribute('aria-expanded', "true");
					// on change la valeur aria-expanded du premier sous menu
					correspondingMenu.parentNode.querySelector(".th-submenu .th-hav-level-3.th-active a").setAttribute('aria-expanded', "true");

					// on donne le focus au sous-menu
					correspondingMenu.focus()
                }
            }
        })
    },

    initEventsFirstLevelInOverlay: function () {
        $(menuOverlay._selector.firstLvlClass, menuOverlay._selector.overlayContainer).each(function () {
            if (this.getAttribute("data-th-menu")) {
                this.addEventListener("click", function (e) {
					e.preventDefault();
					
					// Remplissage du sous menu
					menuOverlay.setSecondLevel(this.parentNode.querySelector(".th-submenu"));
					// Remplissage des raccourcis
					menuOverlay.setWrapper(this.parentNode.querySelector(".th-submenu"));
					
                    menuOverlay.setActiveElement(this, true);

					// on change la valeur aria-expanded des menus
					$(menuOverlay._selector.firstLvlClass, menuOverlay._selector.overlayContainer).each(function () {
						if (this.getAttribute("aria-expanded") == "true") {
							this.setAttribute('aria-expanded', "false");
							// on change la valeur aria-expanded des sous-menu du menu
							$(this.parentNode.querySelectorAll(".th-submenu .th-hav-level-3>a")).each(function () {
								if (this.getAttribute("aria-expanded") == "true") {
									this.setAttribute('aria-expanded', "false");
								}
							});
						}
					});
					this.setAttribute('aria-expanded', "true");
					// on change la valeur aria-expanded du premier sous menu
					if(this.parentNode.querySelector(".th-submenu .th-hav-level-3.th-active a") != null){
						this.parentNode.querySelector(".th-submenu .th-hav-level-3.th-active a").setAttribute('aria-expanded', "true");
					}
                });
            }

            if (this.parentNode.querySelector(".th-submenu")) {
                menuOverlay.initEventsSecondLevel(this.parentNode.querySelector(".th-submenu"));
            }
        });
    },

    initEventsSecondLevel: function (el) {
        $(menuOverlay._selector.secondLvlClass, el).each(function () {
            if (this.parentNode.classList.contains("th-hav-level-3")) {

                this.addEventListener("click", function (e) {
                    e.preventDefault();

                    menuOverlay.setActiveElementDeep(this);

					// on change la valeur aria-expanded des menus
					$(menuOverlay._selector.secondLvlClass, el).each(function () {
						if (this.getAttribute("aria-expanded") == "true") {
							this.setAttribute('aria-expanded', "false");
						}
					});
					this.setAttribute('aria-expanded', "true");
                })
            }
        })
	},
	
	setSecondLevel: function(el){		
        $(menuOverlay._selector.secondLvlData, el).each(function () {
			var submenu = this;
			submenu.innerHTML = '<a aria-haspopup="true" href="' + this.getAttribute("data-url") + '" class="th-level-2" ' + 
				(this.parentNode.classList.contains("th-hav-level-3")?'aria-haspopup="true" aria-expanded="false" ':'') + 
				(this.getAttribute("data-type") == 1?'target="_blank" >':'>') +
				this.getAttribute("data-name") + '</a>';

			if (this.parentNode.classList.contains("th-hav-level-3")) {
				submenu.firstChild.addEventListener("click", function (e) {
					e.preventDefault();
					
					// Remplissage du sous-sous menu
					menuOverlay.setThirdLevel(this.parentNode.thirdLvlClass);

					menuOverlay.setActiveElementDeep(this);

					// on change la valeur aria-expanded des menus
					$(menuOverlay._selector.secondLvlClass, el).each(function () {
						if (this.getAttribute("aria-expanded") == "true") {
							this.setAttribute('aria-expanded', "false");
						}
					});
					this.setAttribute('aria-expanded', "true");
				});
			}
			
			var parent = this.parentNode;			
			parent.insertBefore(submenu.firstChild, null);
			//this.remove();
			parent.removeChild(this);
        });
	},
	
	setWrapper: function(el){		
        $(menuOverlay._selector.wrapperData, el).each(function () {
			var wrapper = this;
			wrapper.innerHTML = '<a href="' + this.getAttribute("data-url") + '" class="th-menu-image ' + 
				(this.getAttribute("data-type") == 1?'seu-external" target="_blank" >' : '" >') +
				(this.getAttribute("data-image") != ''?'<figure class="fit-cover"><img src="' + this.getAttribute("data-image") + (this.getAttribute("data-image").includes("?")?'&imagePreview=1':'?imagePreview=1') + '" width="350" height="224" alt=""/></figure>':'<div class="th-no-photo"></div>') + 
				'<div class="th-content"><span class="th-surtitre">' + this.getAttribute("data-name") + '</span>' + 
				'<span class="th-titre">' + this.getAttribute("data-description") + '</span></div></a>';
			var parent = this.parentNode;
			parent.insertBefore(wrapper.firstChild, null);
			//this.remove();
			parent.removeChild(this);
        });
	},
	
	setThirdLevel: function(el){		
        $(menuOverlay._selector.thirdLvlData, el).each(function () {
			var subsubmenu = this;
			subsubmenu.innerHTML = '<a href="' + this.getAttribute("data-url") + '" ' + 
				(this.getAttribute("data-type") == 1? 'target="_blank" >':'>') +
				this.getAttribute("data-name") + '</a>';
			subsubmenu.classList.remove("data-level-3");
        });
	},

    setActiveElementDeep: function (el) {
        menuOverlay.removeClassOfThirdElements();

        el.classList.add("th-active");
        el.parentNode.classList.add("th-active");
    },

    setActiveElement: function (el, setFirstElement) {

        el.classList.add("th-active");
        menuOverlay.removeClassOfFirstElements();
        menuOverlay.removeClassOfSecondElements();

        var parent = el.parentNode;

        if (parent.classList.contains("th-has-submenu")) {
            parent.classList.add("th-active");
        }

        if (setFirstElement) {
            var firstChild = parent.querySelector(".th-menu-niveau-2 li");

            if (firstChild && firstChild.classList.contains("th-hav-level-3")) {
                if(!isTabletPortraitOrSmalller()) {
                    menuOverlay.setActiveElementDeep(firstChild.querySelector(menuOverlay._selector.secondLvlClass));
                }
            }
        }
    },

    removeClassOfFirstElements: function () {
        $(menuOverlay._selector.firstLvlClass, menuOverlay._selector.overlayContainer).removeClass("th-active");
    },

    removeClassOfSecondElements: function () {
        $(".th-has-submenu", menuOverlay._selector.overlayContainer).removeClass("th-active");
    },

    removeClassOfThirdElements: function () {
        $(menuOverlay._selector.secondLvlClass, menuOverlay._selector.overlayContainer).removeClass("th-active");
        $(menuOverlay._selector.submenuContainers, menuOverlay._selector.overlayContainer).removeClass("th-active");
    }
};

menuOverlay.init($("#th-header").get(0));
if (isNoHover()) {
    document.getElementsByTagName('body')[0].className += ' no-hover';
}

th_dropdown.init();
th_overlay.init();
thVheight.init();

// affichage des overlays possible que a partie de 2s
setTimeout(function () {
    $('body').addClass('overlay-load');
}, 1000);

// Lancement du script de ObjectFit
objectFitImages('.fit-cover img');

// À l’ouverture
th_overlay.addCallbackOpen(function (overlayId) {
    if (overlayId === 'th-overlay-nav') {
        if (!document.body.classList.contains("th-no-scroll")) {
            document.body.classList.add("th-no-scroll")
        }
    }
});
 
// À la fermeture
th_overlay.addCallbackClose(function (overlayId) {
    if (overlayId === 'th-overlay-nav') {
        if (document.body.classList.contains("th-no-scroll")) {
            document.body.classList.remove("th-no-scroll")
        }
    }
});