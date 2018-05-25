define._amd = define.amd;
define.amd = false;

/**

 * Initialisation de variables de configuration transverses au site

 */



var breakpoint_small, breakpoint_large, jqueryValidateConf, owl_options_int;

var canScrollMagic = true;



(function ($) {

    breakpoint_small = 767;

    breakpoint_large = 1279;

    jqueryValidateConf = {

        highlight: function(element, errorClass, validClass){

            $(element).parents('.form-entry').addClass(errorClass).removeClass(validClass); 

        },

        unhighlight: function(element, errorClass, validClass){

            $(element).parents('.form-entry').addClass(validClass).removeClass(errorClass); 

        },

        showErrors: function(errorMap, errorList) {

            $(".form-errors-summary").html("Your form contains "

            + this.numberOfInvalids()

            + " errors, see details below.");

            this.defaultShowErrors();

        }

    }

}(jQuery));


/**
 * Owl carousel
 * @version 2.1.6
 * @author Bartosz Wojciechowski
 * @author David Deutsch
 * @license The MIT License (MIT)
 * @todo Lazy Load Icon
 * @todo prevent animationend bubling
 * @todo itemsScaleUp
 * @todo Test Zepto
 * @todo stagePadding calculate wrong active classes
 */
;(function($, window, document, undefined) {

	/**
	 * Creates a carousel.
	 * @class The Owl Carousel.
	 * @public
	 * @param {HTMLElement|jQuery} element - The element to create the carousel for.
	 * @param {Object} [options] - The options
	 */
	function Owl(element, options) {

		/**
		 * Current settings for the carousel.
		 * @public
		 */
		this.settings = null;

		/**
		 * Current options set by the caller including defaults.
		 * @public
		 */
		this.options = $.extend({}, Owl.Defaults, options);

		/**
		 * Plugin element.
		 * @public
		 */
		this.$element = $(element);

		/**
		 * Proxied event handlers.
		 * @protected
		 */
		this._handlers = {};

		/**
		 * References to the running plugins of this carousel.
		 * @protected
		 */
		this._plugins = {};

		/**
		 * Currently suppressed events to prevent them from beeing retriggered.
		 * @protected
		 */
		this._supress = {};

		/**
		 * Absolute current position.
		 * @protected
		 */
		this._current = null;

		/**
		 * Animation speed in milliseconds.
		 * @protected
		 */
		this._speed = null;

		/**
		 * Coordinates of all items in pixel.
		 * @todo The name of this member is missleading.
		 * @protected
		 */
		this._coordinates = [];

		/**
		 * Current breakpoint.
		 * @todo Real media queries would be nice.
		 * @protected
		 */
		this._breakpoint = null;

		/**
		 * Current width of the plugin element.
		 */
		this._width = null;

		/**
		 * All real items.
		 * @protected
		 */
		this._items = [];

		/**
		 * All cloned items.
		 * @protected
		 */
		this._clones = [];

		/**
		 * Merge values of all items.
		 * @todo Maybe this could be part of a plugin.
		 * @protected
		 */
		this._mergers = [];

		/**
		 * Widths of all items.
		 */
		this._widths = [];

		/**
		 * Invalidated parts within the update process.
		 * @protected
		 */
		this._invalidated = {};

		/**
		 * Ordered list of workers for the update process.
		 * @protected
		 */
		this._pipe = [];

		/**
		 * Current state information for the drag operation.
		 * @todo #261
		 * @protected
		 */
		this._drag = {
			time: null,
			target: null,
			pointer: null,
			stage: {
				start: null,
				current: null
			},
			direction: null
		};

		/**
		 * Current state information and their tags.
		 * @type {Object}
		 * @protected
		 */
		this._states = {
			current: {},
			tags: {
				'initializing': [ 'busy' ],
				'animating': [ 'busy' ],
				'dragging': [ 'interacting' ]
			}
		};

		$.each([ 'onResize', 'onThrottledResize' ], $.proxy(function(i, handler) {
			this._handlers[handler] = $.proxy(this[handler], this);
		}, this));

		$.each(Owl.Plugins, $.proxy(function(key, plugin) {
			this._plugins[key.charAt(0).toLowerCase() + key.slice(1)]
				= new plugin(this);
		}, this));

		$.each(Owl.Workers, $.proxy(function(priority, worker) {
			this._pipe.push({
				'filter': worker.filter,
				'run': $.proxy(worker.run, this)
			});
		}, this));

		this.setup();
		this.initialize();
	}

	/**
	 * Default options for the carousel.
	 * @public
	 */
	Owl.Defaults = {
		items: 3,
		loop: false,
		center: false,
		rewind: false,

		mouseDrag: true,
		touchDrag: true,
		pullDrag: true,
		freeDrag: false,

		margin: 0,
		stagePadding: 0,

		merge: false,
		mergeFit: true,
		autoWidth: false,

		startPosition: 0,
		rtl: false,

		smartSpeed: 250,
		fluidSpeed: false,
		dragEndSpeed: false,

		responsive: {},
		responsiveRefreshRate: 200,
		responsiveBaseElement: window,

		fallbackEasing: 'swing',

		info: false,

		nestedItemSelector: false,
		itemElement: 'div',
		stageElement: 'div',

		refreshClass: 'owl-refresh',
		loadedClass: 'owl-loaded',
		loadingClass: 'owl-loading',
		rtlClass: 'owl-rtl',
		responsiveClass: 'owl-responsive',
		dragClass: 'owl-drag',
		itemClass: 'owl-item',
		stageClass: 'owl-stage',
		stageOuterClass: 'owl-stage-outer',
		grabClass: 'owl-grab'
	};

	/**
	 * Enumeration for width.
	 * @public
	 * @readonly
	 * @enum {String}
	 */
	Owl.Width = {
		Default: 'default',
		Inner: 'inner',
		Outer: 'outer'
	};

	/**
	 * Enumeration for types.
	 * @public
	 * @readonly
	 * @enum {String}
	 */
	Owl.Type = {
		Event: 'event',
		State: 'state'
	};

	/**
	 * Contains all registered plugins.
	 * @public
	 */
	Owl.Plugins = {};

	/**
	 * List of workers involved in the update process.
	 */
	Owl.Workers = [ {
		filter: [ 'width', 'settings' ],
		run: function() {
			this._width = this.$element.width();
		}
	}, {
		filter: [ 'width', 'items', 'settings' ],
		run: function(cache) {
			cache.current = this._items && this._items[this.relative(this._current)];
		}
	}, {
		filter: [ 'items', 'settings' ],
		run: function() {
			this.$stage.children('.cloned').remove();
		}
	}, {
		filter: [ 'width', 'items', 'settings' ],
		run: function(cache) {
			var margin = this.settings.margin || '',
				grid = !this.settings.autoWidth,
				rtl = this.settings.rtl,
				css = {
					'width': 'auto',
					'margin-left': rtl ? margin : '',
					'margin-right': rtl ? '' : margin
				};

			!grid && this.$stage.children().css(css);

			cache.css = css;
		}
	}, {
		filter: [ 'width', 'items', 'settings' ],
		run: function(cache) {
			var width = (this.width() / this.settings.items).toFixed(3) - this.settings.margin,
				merge = null,
				iterator = this._items.length,
				grid = !this.settings.autoWidth,
				widths = [];

			cache.items = {
				merge: false,
				width: width
			};

			while (iterator--) {
				merge = this._mergers[iterator];
				merge = this.settings.mergeFit && Math.min(merge, this.settings.items) || merge;

				cache.items.merge = merge > 1 || cache.items.merge;

				widths[iterator] = !grid ? this._items[iterator].width() : width * merge;
			}

			this._widths = widths;
		}
	}, {
		filter: [ 'items', 'settings' ],
		run: function() {
			var clones = [],
				items = this._items,
				settings = this.settings,
				// TODO: Should be computed from number of min width items in stage
				view = Math.max(settings.items * 2, 4),
				size = Math.ceil(items.length / 2) * 2,
				repeat = settings.loop && items.length ? settings.rewind ? view : Math.max(view, size) : 0,
				append = '',
				prepend = '';

			repeat /= 2;

			while (repeat--) {
				// Switch to only using appended clones
				clones.push(this.normalize(clones.length / 2, true));
				append = append + items[clones[clones.length - 1]][0].outerHTML;
				clones.push(this.normalize(items.length - 1 - (clones.length - 1) / 2, true));
				prepend = items[clones[clones.length - 1]][0].outerHTML + prepend;
			}

			this._clones = clones;

			$(append).addClass('cloned').appendTo(this.$stage);
			$(prepend).addClass('cloned').prependTo(this.$stage);
		}
	}, {
		filter: [ 'width', 'items', 'settings' ],
		run: function() {
			var rtl = this.settings.rtl ? 1 : -1,
				size = this._clones.length + this._items.length,
				iterator = -1,
				previous = 0,
				current = 0,
				coordinates = [];

			while (++iterator < size) {
				previous = coordinates[iterator - 1] || 0;
				current = this._widths[this.relative(iterator)] + this.settings.margin;
				coordinates.push(previous + current * rtl);
			}

			this._coordinates = coordinates;
		}
	}, {
		filter: [ 'width', 'items', 'settings' ],
		run: function() {
			var padding = this.settings.stagePadding,
				coordinates = this._coordinates,
				css = {
					'width': Math.ceil(Math.abs(coordinates[coordinates.length - 1])) + padding * 2,
					'padding-left': padding || '',
					'padding-right': padding || ''
				};

			this.$stage.css(css);
		}
	}, {
		filter: [ 'width', 'items', 'settings' ],
		run: function(cache) {
			var iterator = this._coordinates.length,
				grid = !this.settings.autoWidth,
				items = this.$stage.children();

			if (grid && cache.items.merge) {
				while (iterator--) {
					cache.css.width = this._widths[this.relative(iterator)];
					items.eq(iterator).css(cache.css);
				}
			} else if (grid) {
				cache.css.width = cache.items.width;
				items.css(cache.css);
			}
		}
	}, {
		filter: [ 'items' ],
		run: function() {
			this._coordinates.length < 1 && this.$stage.removeAttr('style');
		}
	}, {
		filter: [ 'width', 'items', 'settings' ],
		run: function(cache) {
			cache.current = cache.current ? this.$stage.children().index(cache.current) : 0;
			cache.current = Math.max(this.minimum(), Math.min(this.maximum(), cache.current));
			this.reset(cache.current);
		}
	}, {
		filter: [ 'position' ],
		run: function() {
			this.animate(this.coordinates(this._current));
		}
	}, {
		filter: [ 'width', 'position', 'items', 'settings' ],
		run: function() {
			var rtl = this.settings.rtl ? 1 : -1,
				padding = this.settings.stagePadding * 2,
				begin = this.coordinates(this.current()) + padding,
				end = begin + this.width() * rtl,
				inner, outer, matches = [], i, n;

			for (i = 0, n = this._coordinates.length; i < n; i++) {
				inner = this._coordinates[i - 1] || 0;
				outer = Math.abs(this._coordinates[i]) + padding * rtl;

				if ((this.op(inner, '<=', begin) && (this.op(inner, '>', end)))
					|| (this.op(outer, '<', begin) && this.op(outer, '>', end))) {
					matches.push(i);
				}
			}

			this.$stage.children('.active').removeClass('active');
			this.$stage.children(':eq(' + matches.join('), :eq(') + ')').addClass('active');

			if (this.settings.center) {
				this.$stage.children('.center').removeClass('center');
				this.$stage.children().eq(this.current()).addClass('center');
			}
		}
	} ];

	/**
	 * Initializes the carousel.
	 * @protected
	 */
	Owl.prototype.initialize = function() {
		this.enter('initializing');
		this.trigger('initialize');

		this.$element.toggleClass(this.settings.rtlClass, this.settings.rtl);

		if (this.settings.autoWidth && !this.is('pre-loading')) {
			var imgs, nestedSelector, width;
			imgs = this.$element.find('img');
			nestedSelector = this.settings.nestedItemSelector ? '.' + this.settings.nestedItemSelector : undefined;
			width = this.$element.children(nestedSelector).width();

			if (imgs.length && width <= 0) {
				this.preloadAutoWidthImages(imgs);
			}
		}

		this.$element.addClass(this.options.loadingClass);

		// create stage
		this.$stage = $('<' + this.settings.stageElement + ' class="' + this.settings.stageClass + '"/>')
			.wrap('<div class="' + this.settings.stageOuterClass + '"/>');

		// append stage
		this.$element.append(this.$stage.parent());

		// append content
		this.replace(this.$element.children().not(this.$stage.parent()));

		// check visibility
		if (this.$element.is(':visible')) {
			// update view
			this.refresh();
		} else {
			// invalidate width
			this.invalidate('width');
		}

		this.$element
			.removeClass(this.options.loadingClass)
			.addClass(this.options.loadedClass);

		// register event handlers
		this.registerEventHandlers();

		this.leave('initializing');
		this.trigger('initialized');
	};

	/**
	 * Setups the current settings.
	 * @todo Remove responsive classes. Why should adaptive designs be brought into IE8?
	 * @todo Support for media queries by using `matchMedia` would be nice.
	 * @public
	 */
	Owl.prototype.setup = function() {
		var viewport = this.viewport(),
			overwrites = this.options.responsive,
			match = -1,
			settings = null;

		if (!overwrites) {
			settings = $.extend({}, this.options);
		} else {
			$.each(overwrites, function(breakpoint) {
				if (breakpoint <= viewport && breakpoint > match) {
					match = Number(breakpoint);
				}
			});

			settings = $.extend({}, this.options, overwrites[match]);
			if (typeof settings.stagePadding === 'function') {
				settings.stagePadding = settings.stagePadding();
			}
			delete settings.responsive;

			// responsive class
			if (settings.responsiveClass) {
				this.$element.attr('class',
					this.$element.attr('class').replace(new RegExp('(' + this.options.responsiveClass + '-)\\S+\\s', 'g'), '$1' + match)
				);
			}
		}

		this.trigger('change', { property: { name: 'settings', value: settings } });
		this._breakpoint = match;
		this.settings = settings;
		this.invalidate('settings');
		this.trigger('changed', { property: { name: 'settings', value: this.settings } });
	};

	/**
	 * Updates option logic if necessery.
	 * @protected
	 */
	Owl.prototype.optionsLogic = function() {
		if (this.settings.autoWidth) {
			this.settings.stagePadding = false;
			this.settings.merge = false;
		}
	};

	/**
	 * Prepares an item before add.
	 * @todo Rename event parameter `content` to `item`.
	 * @protected
	 * @returns {jQuery|HTMLElement} - The item container.
	 */
	Owl.prototype.prepare = function(item) {
		var event = this.trigger('prepare', { content: item });

		if (!event.data) {
			event.data = $('<' + this.settings.itemElement + '/>')
				.addClass(this.options.itemClass).append(item)
		}

		this.trigger('prepared', { content: event.data });

		return event.data;
	};

	/**
	 * Updates the view.
	 * @public
	 */
	Owl.prototype.update = function() {
		var i = 0,
			n = this._pipe.length,
			filter = $.proxy(function(p) { return this[p] }, this._invalidated),
			cache = {};

		while (i < n) {
			if (this._invalidated.all || $.grep(this._pipe[i].filter, filter).length > 0) {
				this._pipe[i].run(cache);
			}
			i++;
		}

		this._invalidated = {};

		!this.is('valid') && this.enter('valid');
	};

	/**
	 * Gets the width of the view.
	 * @public
	 * @param {Owl.Width} [dimension=Owl.Width.Default] - The dimension to return.
	 * @returns {Number} - The width of the view in pixel.
	 */
	Owl.prototype.width = function(dimension) {
		dimension = dimension || Owl.Width.Default;
		switch (dimension) {
			case Owl.Width.Inner:
			case Owl.Width.Outer:
				return this._width;
			default:
				return this._width - this.settings.stagePadding * 2 + this.settings.margin;
		}
	};

	/**
	 * Refreshes the carousel primarily for adaptive purposes.
	 * @public
	 */
	Owl.prototype.refresh = function() {
		this.enter('refreshing');
		this.trigger('refresh');

		this.setup();

		this.optionsLogic();

		this.$element.addClass(this.options.refreshClass);

		this.update();

		this.$element.removeClass(this.options.refreshClass);

		this.leave('refreshing');
		this.trigger('refreshed');
	};

	/**
	 * Checks window `resize` event.
	 * @protected
	 */
	Owl.prototype.onThrottledResize = function() {
		window.clearTimeout(this.resizeTimer);
		this.resizeTimer = window.setTimeout(this._handlers.onResize, this.settings.responsiveRefreshRate);
	};

	/**
	 * Checks window `resize` event.
	 * @protected
	 */
	Owl.prototype.onResize = function() {
		if (!this._items.length) {
			return false;
		}

		if (this._width === this.$element.width()) {
			return false;
		}

		if (!this.$element.is(':visible')) {
			return false;
		}

		this.enter('resizing');

		if (this.trigger('resize').isDefaultPrevented()) {
			this.leave('resizing');
			return false;
		}

		this.invalidate('width');

		this.refresh();

		this.leave('resizing');
		this.trigger('resized');
	};

	/**
	 * Registers event handlers.
	 * @todo Check `msPointerEnabled`
	 * @todo #261
	 * @protected
	 */
	Owl.prototype.registerEventHandlers = function() {
		if ($.support.transition) {
			this.$stage.on($.support.transition.end + '.owl.core', $.proxy(this.onTransitionEnd, this));
		}

		if (this.settings.responsive !== false) {
			this.on(window, 'resize', this._handlers.onThrottledResize);
		}

		if (this.settings.mouseDrag) {
			this.$element.addClass(this.options.dragClass);
			this.$stage.on('mousedown.owl.core', $.proxy(this.onDragStart, this));
			this.$stage.on('dragstart.owl.core selectstart.owl.core', function() { return false });
		}

		if (this.settings.touchDrag){
			this.$stage.on('touchstart.owl.core', $.proxy(this.onDragStart, this));
			this.$stage.on('touchcancel.owl.core', $.proxy(this.onDragEnd, this));
		}
	};

	/**
	 * Handles `touchstart` and `mousedown` events.
	 * @todo Horizontal swipe threshold as option
	 * @todo #261
	 * @protected
	 * @param {Event} event - The event arguments.
	 */
	Owl.prototype.onDragStart = function(event) {
		var stage = null;

		if (event.which === 3) {
			return;
		}

		if ($.support.transform) {
			stage = this.$stage.css('transform').replace(/.*\(|\)| /g, '').split(',');
			stage = {
				x: stage[stage.length === 16 ? 12 : 4],
				y: stage[stage.length === 16 ? 13 : 5]
			};
		} else {
			stage = this.$stage.position();
			stage = {
				x: this.settings.rtl ?
					stage.left + this.$stage.width() - this.width() + this.settings.margin :
					stage.left,
				y: stage.top
			};
		}

		if (this.is('animating')) {
			$.support.transform ? this.animate(stage.x) : this.$stage.stop()
			this.invalidate('position');
		}

		this.$element.toggleClass(this.options.grabClass, event.type === 'mousedown');

		this.speed(0);

		this._drag.time = new Date().getTime();
		this._drag.target = $(event.target);
		this._drag.stage.start = stage;
		this._drag.stage.current = stage;
		this._drag.pointer = this.pointer(event);

		$(document).on('mouseup.owl.core touchend.owl.core', $.proxy(this.onDragEnd, this));

		$(document).one('mousemove.owl.core touchmove.owl.core', $.proxy(function(event) {
			var delta = this.difference(this._drag.pointer, this.pointer(event));

			$(document).on('mousemove.owl.core touchmove.owl.core', $.proxy(this.onDragMove, this));

			if (Math.abs(delta.x) < Math.abs(delta.y) && this.is('valid')) {
				return;
			}

			event.preventDefault();

			this.enter('dragging');
			this.trigger('drag');
		}, this));
	};

	/**
	 * Handles the `touchmove` and `mousemove` events.
	 * @todo #261
	 * @protected
	 * @param {Event} event - The event arguments.
	 */
	Owl.prototype.onDragMove = function(event) {
		var minimum = null,
			maximum = null,
			pull = null,
			delta = this.difference(this._drag.pointer, this.pointer(event)),
			stage = this.difference(this._drag.stage.start, delta);

		if (!this.is('dragging')) {
			return;
		}

		event.preventDefault();

		if (this.settings.loop) {
			minimum = this.coordinates(this.minimum());
			maximum = this.coordinates(this.maximum() + 1) - minimum;
			stage.x = (((stage.x - minimum) % maximum + maximum) % maximum) + minimum;
		} else {
			minimum = this.settings.rtl ? this.coordinates(this.maximum()) : this.coordinates(this.minimum());
			maximum = this.settings.rtl ? this.coordinates(this.minimum()) : this.coordinates(this.maximum());
			pull = this.settings.pullDrag ? -1 * delta.x / 5 : 0;
			stage.x = Math.max(Math.min(stage.x, minimum + pull), maximum + pull);
		}

		this._drag.stage.current = stage;

		this.animate(stage.x);
	};

	/**
	 * Handles the `touchend` and `mouseup` events.
	 * @todo #261
	 * @todo Threshold for click event
	 * @protected
	 * @param {Event} event - The event arguments.
	 */
	Owl.prototype.onDragEnd = function(event) {
		var delta = this.difference(this._drag.pointer, this.pointer(event)),
			stage = this._drag.stage.current,
			direction = delta.x > 0 ^ this.settings.rtl ? 'left' : 'right';

		$(document).off('.owl.core');

		this.$element.removeClass(this.options.grabClass);

		if (delta.x !== 0 && this.is('dragging') || !this.is('valid')) {
			this.speed(this.settings.dragEndSpeed || this.settings.smartSpeed);
			this.current(this.closest(stage.x, delta.x !== 0 ? direction : this._drag.direction));
			this.invalidate('position');
			this.update();

			this._drag.direction = direction;

			if (Math.abs(delta.x) > 3 || new Date().getTime() - this._drag.time > 300) {
				this._drag.target.one('click.owl.core', function() { return false; });
			}
		}

		if (!this.is('dragging')) {
			return;
		}

		this.leave('dragging');
		this.trigger('dragged');
	};

	/**
	 * Gets absolute position of the closest item for a coordinate.
	 * @todo Setting `freeDrag` makes `closest` not reusable. See #165.
	 * @protected
	 * @param {Number} coordinate - The coordinate in pixel.
	 * @param {String} direction - The direction to check for the closest item. Ether `left` or `right`.
	 * @return {Number} - The absolute position of the closest item.
	 */
	Owl.prototype.closest = function(coordinate, direction) {
		var position = -1,
			pull = 30,
			width = this.width(),
			coordinates = this.coordinates();

		if (!this.settings.freeDrag) {
			// check closest item
			$.each(coordinates, $.proxy(function(index, value) {
				// on a left pull, check on current index
				if (direction === 'left' && coordinate > value - pull && coordinate < value + pull) {
					position = index;
				// on a right pull, check on previous index
				// to do so, subtract width from value and set position = index + 1
				} else if (direction === 'right' && coordinate > value - width - pull && coordinate < value - width + pull) {
					position = index + 1;
				} else if (this.op(coordinate, '<', value)
					&& this.op(coordinate, '>', coordinates[index + 1] || value - width)) {
					position = direction === 'left' ? index + 1 : index;
				}
				return position === -1;
			}, this));
		}

		if (!this.settings.loop) {
			// non loop boundries
			if (this.op(coordinate, '>', coordinates[this.minimum()])) {
				position = coordinate = this.minimum();
			} else if (this.op(coordinate, '<', coordinates[this.maximum()])) {
				position = coordinate = this.maximum();
			}
		}

		return position;
	};

	/**
	 * Animates the stage.
	 * @todo #270
	 * @public
	 * @param {Number} coordinate - The coordinate in pixels.
	 */
	Owl.prototype.animate = function(coordinate) {
		var animate = this.speed() > 0;

		this.is('animating') && this.onTransitionEnd();

		if (animate) {
			this.enter('animating');
			this.trigger('translate');
		}

		if ($.support.transform3d && $.support.transition) {
			this.$stage.css({
				transform: 'translate3d(' + coordinate + 'px,0px,0px)',
				transition: (this.speed() / 1000) + 's'
			});
		} else if (animate) {
			this.$stage.animate({
				left: coordinate + 'px'
			}, this.speed(), this.settings.fallbackEasing, $.proxy(this.onTransitionEnd, this));
		} else {
			this.$stage.css({
				left: coordinate + 'px'
			});
		}
	};

	/**
	 * Checks whether the carousel is in a specific state or not.
	 * @param {String} state - The state to check.
	 * @returns {Boolean} - The flag which indicates if the carousel is busy.
	 */
	Owl.prototype.is = function(state) {
		return this._states.current[state] && this._states.current[state] > 0;
	};

	/**
	 * Sets the absolute position of the current item.
	 * @public
	 * @param {Number} [position] - The new absolute position or nothing to leave it unchanged.
	 * @returns {Number} - The absolute position of the current item.
	 */
	Owl.prototype.current = function(position) {
		if (position === undefined) {
			return this._current;
		}

		if (this._items.length === 0) {
			return undefined;
		}

		position = this.normalize(position);

		if (this._current !== position) {
			var event = this.trigger('change', { property: { name: 'position', value: position } });

			if (event.data !== undefined) {
				position = this.normalize(event.data);
			}

			this._current = position;

			this.invalidate('position');

			this.trigger('changed', { property: { name: 'position', value: this._current } });
		}

		return this._current;
	};

	/**
	 * Invalidates the given part of the update routine.
	 * @param {String} [part] - The part to invalidate.
	 * @returns {Array.<String>} - The invalidated parts.
	 */
	Owl.prototype.invalidate = function(part) {
		if ($.type(part) === 'string') {
			this._invalidated[part] = true;
			this.is('valid') && this.leave('valid');
		}
		return $.map(this._invalidated, function(v, i) { return i });
	};

	/**
	 * Resets the absolute position of the current item.
	 * @public
	 * @param {Number} position - The absolute position of the new item.
	 */
	Owl.prototype.reset = function(position) {
		position = this.normalize(position);

		if (position === undefined) {
			return;
		}

		this._speed = 0;
		this._current = position;

		this.suppress([ 'translate', 'translated' ]);

		this.animate(this.coordinates(position));

		this.release([ 'translate', 'translated' ]);
	};

	/**
	 * Normalizes an absolute or a relative position of an item.
	 * @public
	 * @param {Number} position - The absolute or relative position to normalize.
	 * @param {Boolean} [relative=false] - Whether the given position is relative or not.
	 * @returns {Number} - The normalized position.
	 */
	Owl.prototype.normalize = function(position, relative) {
		var n = this._items.length,
			m = relative ? 0 : this._clones.length;

		if (!this.isNumeric(position) || n < 1) {
			position = undefined;
		} else if (position < 0 || position >= n + m) {
			position = ((position - m / 2) % n + n) % n + m / 2;
		}

		return position;
	};

	/**
	 * Converts an absolute position of an item into a relative one.
	 * @public
	 * @param {Number} position - The absolute position to convert.
	 * @returns {Number} - The converted position.
	 */
	Owl.prototype.relative = function(position) {
		position -= this._clones.length / 2;
		return this.normalize(position, true);
	};

	/**
	 * Gets the maximum position for the current item.
	 * @public
	 * @param {Boolean} [relative=false] - Whether to return an absolute position or a relative position.
	 * @returns {Number}
	 */
	Owl.prototype.maximum = function(relative) {
		var settings = this.settings,
			maximum = this._coordinates.length,
			iterator,
			reciprocalItemsWidth,
			elementWidth;

		if (settings.loop) {
			maximum = this._clones.length / 2 + this._items.length - 1;
		} else if (settings.autoWidth || settings.merge) {
			iterator = this._items.length;
			reciprocalItemsWidth = this._items[--iterator].width();
			elementWidth = this.$element.width();
			while (iterator--) {
				reciprocalItemsWidth += this._items[iterator].width() + this.settings.margin;
				if (reciprocalItemsWidth > elementWidth) {
					break;
				}
			}
			maximum = iterator + 1;
		} else if (settings.center) {
			maximum = this._items.length - 1;
		} else {
			maximum = this._items.length - settings.items;
		}

		if (relative) {
			maximum -= this._clones.length / 2;
		}

		return Math.max(maximum, 0);
	};

	/**
	 * Gets the minimum position for the current item.
	 * @public
	 * @param {Boolean} [relative=false] - Whether to return an absolute position or a relative position.
	 * @returns {Number}
	 */
	Owl.prototype.minimum = function(relative) {
		return relative ? 0 : this._clones.length / 2;
	};

	/**
	 * Gets an item at the specified relative position.
	 * @public
	 * @param {Number} [position] - The relative position of the item.
	 * @return {jQuery|Array.<jQuery>} - The item at the given position or all items if no position was given.
	 */
	Owl.prototype.items = function(position) {
		if (position === undefined) {
			return this._items.slice();
		}

		position = this.normalize(position, true);
		return this._items[position];
	};

	/**
	 * Gets an item at the specified relative position.
	 * @public
	 * @param {Number} [position] - The relative position of the item.
	 * @return {jQuery|Array.<jQuery>} - The item at the given position or all items if no position was given.
	 */
	Owl.prototype.mergers = function(position) {
		if (position === undefined) {
			return this._mergers.slice();
		}

		position = this.normalize(position, true);
		return this._mergers[position];
	};

	/**
	 * Gets the absolute positions of clones for an item.
	 * @public
	 * @param {Number} [position] - The relative position of the item.
	 * @returns {Array.<Number>} - The absolute positions of clones for the item or all if no position was given.
	 */
	Owl.prototype.clones = function(position) {
		var odd = this._clones.length / 2,
			even = odd + this._items.length,
			map = function(index) { return index % 2 === 0 ? even + index / 2 : odd - (index + 1) / 2 };

		if (position === undefined) {
			return $.map(this._clones, function(v, i) { return map(i) });
		}

		return $.map(this._clones, function(v, i) { return v === position ? map(i) : null });
	};

	/**
	 * Sets the current animation speed.
	 * @public
	 * @param {Number} [speed] - The animation speed in milliseconds or nothing to leave it unchanged.
	 * @returns {Number} - The current animation speed in milliseconds.
	 */
	Owl.prototype.speed = function(speed) {
		if (speed !== undefined) {
			this._speed = speed;
		}

		return this._speed;
	};

	/**
	 * Gets the coordinate of an item.
	 * @todo The name of this method is missleanding.
	 * @public
	 * @param {Number} position - The absolute position of the item within `minimum()` and `maximum()`.
	 * @returns {Number|Array.<Number>} - The coordinate of the item in pixel or all coordinates.
	 */
	Owl.prototype.coordinates = function(position) {
		var multiplier = 1,
			newPosition = position - 1,
			coordinate;

		if (position === undefined) {
			return $.map(this._coordinates, $.proxy(function(coordinate, index) {
				return this.coordinates(index);
			}, this));
		}

		if (this.settings.center) {
			if (this.settings.rtl) {
				multiplier = -1;
				newPosition = position + 1;
			}

			coordinate = this._coordinates[position];
			coordinate += (this.width() - coordinate + (this._coordinates[newPosition] || 0)) / 2 * multiplier;
		} else {
			coordinate = this._coordinates[newPosition] || 0;
		}

		coordinate = Math.ceil(coordinate);

		return coordinate;
	};

	/**
	 * Calculates the speed for a translation.
	 * @protected
	 * @param {Number} from - The absolute position of the start item.
	 * @param {Number} to - The absolute position of the target item.
	 * @param {Number} [factor=undefined] - The time factor in milliseconds.
	 * @returns {Number} - The time in milliseconds for the translation.
	 */
	Owl.prototype.duration = function(from, to, factor) {
		if (factor === 0) {
			return 0;
		}

		return Math.min(Math.max(Math.abs(to - from), 1), 6) * Math.abs((factor || this.settings.smartSpeed));
	};

	/**
	 * Slides to the specified item.
	 * @public
	 * @param {Number} position - The position of the item.
	 * @param {Number} [speed] - The time in milliseconds for the transition.
	 */
	Owl.prototype.to = function(position, speed) {
		var current = this.current(),
			revert = null,
			distance = position - this.relative(current),
			direction = (distance > 0) - (distance < 0),
			items = this._items.length,
			minimum = this.minimum(),
			maximum = this.maximum();

		if (this.settings.loop) {
			if (!this.settings.rewind && Math.abs(distance) > items / 2) {
				distance += direction * -1 * items;
			}

			position = current + distance;
			revert = ((position - minimum) % items + items) % items + minimum;

			if (revert !== position && revert - distance <= maximum && revert - distance > 0) {
				current = revert - distance;
				position = revert;
				this.reset(current);
			}
		} else if (this.settings.rewind) {
			maximum += 1;
			position = (position % maximum + maximum) % maximum;
		} else {
			position = Math.max(minimum, Math.min(maximum, position));
		}

		this.speed(this.duration(current, position, speed));
		this.current(position);

		if (this.$element.is(':visible')) {
			this.update();
		}
	};

	/**
	 * Slides to the next item.
	 * @public
	 * @param {Number} [speed] - The time in milliseconds for the transition.
	 */
	Owl.prototype.next = function(speed) {
		speed = speed || false;
		this.to(this.relative(this.current()) + 1, speed);
	};

	/**
	 * Slides to the previous item.
	 * @public
	 * @param {Number} [speed] - The time in milliseconds for the transition.
	 */
	Owl.prototype.prev = function(speed) {
		speed = speed || false;
		this.to(this.relative(this.current()) - 1, speed);
	};

	/**
	 * Handles the end of an animation.
	 * @protected
	 * @param {Event} event - The event arguments.
	 */
	Owl.prototype.onTransitionEnd = function(event) {

		// if css2 animation then event object is undefined
		if (event !== undefined) {
			event.stopPropagation();

			// Catch only owl-stage transitionEnd event
			if ((event.target || event.srcElement || event.originalTarget) !== this.$stage.get(0)) {
				return false;
			}
		}

		this.leave('animating');
		this.trigger('translated');
	};

	/**
	 * Gets viewport width.
	 * @protected
	 * @return {Number} - The width in pixel.
	 */
	Owl.prototype.viewport = function() {
		var width;
		if (this.options.responsiveBaseElement !== window) {
			width = $(this.options.responsiveBaseElement).width();
		} else if (window.innerWidth) {
			width = window.innerWidth;
		} else if (document.documentElement && document.documentElement.clientWidth) {
			width = document.documentElement.clientWidth;
		} else {
			throw 'Can not detect viewport width.';
		}
		return width;
	};

	/**
	 * Replaces the current content.
	 * @public
	 * @param {HTMLElement|jQuery|String} content - The new content.
	 */
	Owl.prototype.replace = function(content) {
		this.$stage.empty();
		this._items = [];

		if (content) {
			content = (content instanceof jQuery) ? content : $(content);
		}

		if (this.settings.nestedItemSelector) {
			content = content.find('.' + this.settings.nestedItemSelector);
		}

		content.filter(function() {
			return this.nodeType === 1;
		}).each($.proxy(function(index, item) {
			item = this.prepare(item);
			this.$stage.append(item);
			this._items.push(item);
			this._mergers.push(item.find('[data-merge]').addBack('[data-merge]').attr('data-merge') * 1 || 1);
		}, this));

		this.reset(this.isNumeric(this.settings.startPosition) ? this.settings.startPosition : 0);

		this.invalidate('items');
	};

	/**
	 * Adds an item.
	 * @todo Use `item` instead of `content` for the event arguments.
	 * @public
	 * @param {HTMLElement|jQuery|String} content - The item content to add.
	 * @param {Number} [position] - The relative position at which to insert the item otherwise the item will be added to the end.
	 */
	Owl.prototype.add = function(content, position) {
		var current = this.relative(this._current);

		position = position === undefined ? this._items.length : this.normalize(position, true);
		content = content instanceof jQuery ? content : $(content);

		this.trigger('add', { content: content, position: position });

		content = this.prepare(content);

		if (this._items.length === 0 || position === this._items.length) {
			this._items.length === 0 && this.$stage.append(content);
			this._items.length !== 0 && this._items[position - 1].after(content);
			this._items.push(content);
			this._mergers.push(content.find('[data-merge]').addBack('[data-merge]').attr('data-merge') * 1 || 1);
		} else {
			this._items[position].before(content);
			this._items.splice(position, 0, content);
			this._mergers.splice(position, 0, content.find('[data-merge]').addBack('[data-merge]').attr('data-merge') * 1 || 1);
		}

		this._items[current] && this.reset(this._items[current].index());

		this.invalidate('items');

		this.trigger('added', { content: content, position: position });
	};

	/**
	 * Removes an item by its position.
	 * @todo Use `item` instead of `content` for the event arguments.
	 * @public
	 * @param {Number} position - The relative position of the item to remove.
	 */
	Owl.prototype.remove = function(position) {
		position = this.normalize(position, true);

		if (position === undefined) {
			return;
		}

		this.trigger('remove', { content: this._items[position], position: position });

		this._items[position].remove();
		this._items.splice(position, 1);
		this._mergers.splice(position, 1);

		this.invalidate('items');

		this.trigger('removed', { content: null, position: position });
	};

	/**
	 * Preloads images with auto width.
	 * @todo Replace by a more generic approach
	 * @protected
	 */
	Owl.prototype.preloadAutoWidthImages = function(images) {
		images.each($.proxy(function(i, element) {
			this.enter('pre-loading');
			element = $(element);
			$(new Image()).one('load', $.proxy(function(e) {
				element.attr('src', e.target.src);
				element.css('opacity', 1);
				this.leave('pre-loading');
				!this.is('pre-loading') && !this.is('initializing') && this.refresh();
			}, this)).attr('src', element.attr('src') || element.attr('data-src') || element.attr('data-src-retina'));
		}, this));
	};

	/**
	 * Destroys the carousel.
	 * @public
	 */
	Owl.prototype.destroy = function() {

		this.$element.off('.owl.core');
		this.$stage.off('.owl.core');
		$(document).off('.owl.core');

		if (this.settings.responsive !== false) {
			window.clearTimeout(this.resizeTimer);
			this.off(window, 'resize', this._handlers.onThrottledResize);
		}

		for (var i in this._plugins) {
			this._plugins[i].destroy();
		}

		this.$stage.children('.cloned').remove();

		this.$stage.unwrap();
		this.$stage.children().contents().unwrap();
		this.$stage.children().unwrap();

		this.$element
			.removeClass(this.options.refreshClass)
			.removeClass(this.options.loadingClass)
			.removeClass(this.options.loadedClass)
			.removeClass(this.options.rtlClass)
			.removeClass(this.options.dragClass)
			.removeClass(this.options.grabClass)
			.attr('class', this.$element.attr('class').replace(new RegExp(this.options.responsiveClass + '-\\S+\\s', 'g'), ''))
			.removeData('owl.carousel');
	};

	/**
	 * Operators to calculate right-to-left and left-to-right.
	 * @protected
	 * @param {Number} [a] - The left side operand.
	 * @param {String} [o] - The operator.
	 * @param {Number} [b] - The right side operand.
	 */
	Owl.prototype.op = function(a, o, b) {
		var rtl = this.settings.rtl;
		switch (o) {
			case '<':
				return rtl ? a > b : a < b;
			case '>':
				return rtl ? a < b : a > b;
			case '>=':
				return rtl ? a <= b : a >= b;
			case '<=':
				return rtl ? a >= b : a <= b;
			default:
				break;
		}
	};

	/**
	 * Attaches to an internal event.
	 * @protected
	 * @param {HTMLElement} element - The event source.
	 * @param {String} event - The event name.
	 * @param {Function} listener - The event handler to attach.
	 * @param {Boolean} capture - Wether the event should be handled at the capturing phase or not.
	 */
	Owl.prototype.on = function(element, event, listener, capture) {
		if (element.addEventListener) {
			element.addEventListener(event, listener, capture);
		} else if (element.attachEvent) {
			element.attachEvent('on' + event, listener);
		}
	};

	/**
	 * Detaches from an internal event.
	 * @protected
	 * @param {HTMLElement} element - The event source.
	 * @param {String} event - The event name.
	 * @param {Function} listener - The attached event handler to detach.
	 * @param {Boolean} capture - Wether the attached event handler was registered as a capturing listener or not.
	 */
	Owl.prototype.off = function(element, event, listener, capture) {
		if (element.removeEventListener) {
			element.removeEventListener(event, listener, capture);
		} else if (element.detachEvent) {
			element.detachEvent('on' + event, listener);
		}
	};

	/**
	 * Triggers a public event.
	 * @todo Remove `status`, `relatedTarget` should be used instead.
	 * @protected
	 * @param {String} name - The event name.
	 * @param {*} [data=null] - The event data.
	 * @param {String} [namespace=carousel] - The event namespace.
	 * @param {String} [state] - The state which is associated with the event.
	 * @param {Boolean} [enter=false] - Indicates if the call enters the specified state or not.
	 * @returns {Event} - The event arguments.
	 */
	Owl.prototype.trigger = function(name, data, namespace, state, enter) {
		var status = {
			item: { count: this._items.length, index: this.current() }
		}, handler = $.camelCase(
			$.grep([ 'on', name, namespace ], function(v) { return v })
				.join('-').toLowerCase()
		), event = $.Event(
			[ name, 'owl', namespace || 'carousel' ].join('.').toLowerCase(),
			$.extend({ relatedTarget: this }, status, data)
		);

		if (!this._supress[name]) {
			$.each(this._plugins, function(name, plugin) {
				if (plugin.onTrigger) {
					plugin.onTrigger(event);
				}
			});

			this.register({ type: Owl.Type.Event, name: name });
			this.$element.trigger(event);

			if (this.settings && typeof this.settings[handler] === 'function') {
				this.settings[handler].call(this, event);
			}
		}

		return event;
	};

	/**
	 * Enters a state.
	 * @param name - The state name.
	 */
	Owl.prototype.enter = function(name) {
		$.each([ name ].concat(this._states.tags[name] || []), $.proxy(function(i, name) {
			if (this._states.current[name] === undefined) {
				this._states.current[name] = 0;
			}

			this._states.current[name]++;
		}, this));
	};

	/**
	 * Leaves a state.
	 * @param name - The state name.
	 */
	Owl.prototype.leave = function(name) {
		$.each([ name ].concat(this._states.tags[name] || []), $.proxy(function(i, name) {
			this._states.current[name]--;
		}, this));
	};

	/**
	 * Registers an event or state.
	 * @public
	 * @param {Object} object - The event or state to register.
	 */
	Owl.prototype.register = function(object) {
		if (object.type === Owl.Type.Event) {
			if (!$.event.special[object.name]) {
				$.event.special[object.name] = {};
			}

			if (!$.event.special[object.name].owl) {
				var _default = $.event.special[object.name]._default;
				$.event.special[object.name]._default = function(e) {
					if (_default && _default.apply && (!e.namespace || e.namespace.indexOf('owl') === -1)) {
						return _default.apply(this, arguments);
					}
					return e.namespace && e.namespace.indexOf('owl') > -1;
				};
				$.event.special[object.name].owl = true;
			}
		} else if (object.type === Owl.Type.State) {
			if (!this._states.tags[object.name]) {
				this._states.tags[object.name] = object.tags;
			} else {
				this._states.tags[object.name] = this._states.tags[object.name].concat(object.tags);
			}

			this._states.tags[object.name] = $.grep(this._states.tags[object.name], $.proxy(function(tag, i) {
				return $.inArray(tag, this._states.tags[object.name]) === i;
			}, this));
		}
	};

	/**
	 * Suppresses events.
	 * @protected
	 * @param {Array.<String>} events - The events to suppress.
	 */
	Owl.prototype.suppress = function(events) {
		$.each(events, $.proxy(function(index, event) {
			this._supress[event] = true;
		}, this));
	};

	/**
	 * Releases suppressed events.
	 * @protected
	 * @param {Array.<String>} events - The events to release.
	 */
	Owl.prototype.release = function(events) {
		$.each(events, $.proxy(function(index, event) {
			delete this._supress[event];
		}, this));
	};

	/**
	 * Gets unified pointer coordinates from event.
	 * @todo #261
	 * @protected
	 * @param {Event} - The `mousedown` or `touchstart` event.
	 * @returns {Object} - Contains `x` and `y` coordinates of current pointer position.
	 */
	Owl.prototype.pointer = function(event) {
		var result = { x: null, y: null };

		event = event.originalEvent || event || window.event;

		event = event.touches && event.touches.length ?
			event.touches[0] : event.changedTouches && event.changedTouches.length ?
				event.changedTouches[0] : event;

		if (event.pageX) {
			result.x = event.pageX;
			result.y = event.pageY;
		} else {
			result.x = event.clientX;
			result.y = event.clientY;
		}

		return result;
	};

	/**
	 * Determines if the input is a Number or something that can be coerced to a Number
	 * @protected
	 * @param {Number|String|Object|Array|Boolean|RegExp|Function|Symbol} - The input to be tested
	 * @returns {Boolean} - An indication if the input is a Number or can be coerced to a Number
	 */
	Owl.prototype.isNumeric = function(number) {
		return !isNaN(parseFloat(number));
	};

	/**
	 * Gets the difference of two vectors.
	 * @todo #261
	 * @protected
	 * @param {Object} - The first vector.
	 * @param {Object} - The second vector.
	 * @returns {Object} - The difference.
	 */
	Owl.prototype.difference = function(first, second) {
		return {
			x: first.x - second.x,
			y: first.y - second.y
		};
	};

	/**
	 * The jQuery Plugin for the Owl Carousel
	 * @todo Navigation plugin `next` and `prev`
	 * @public
	 */
	$.fn.owlCarousel = function(option) {
		var args = Array.prototype.slice.call(arguments, 1);

		return this.each(function() {
			var $this = $(this),
				data = $this.data('owl.carousel');

			if (!data) {
				data = new Owl(this, typeof option == 'object' && option);
				$this.data('owl.carousel', data);

				$.each([
					'next', 'prev', 'to', 'destroy', 'refresh', 'replace', 'add', 'remove'
				], function(i, event) {
					data.register({ type: Owl.Type.Event, name: event });
					data.$element.on(event + '.owl.carousel.core', $.proxy(function(e) {
						if (e.namespace && e.relatedTarget !== this) {
							this.suppress([ event ]);
							data[event].apply(this, [].slice.call(arguments, 1));
							this.release([ event ]);
						}
					}, data));
				});
			}

			if (typeof option == 'string' && option.charAt(0) !== '_') {
				data[option].apply(data, args);
			}
		});
	};

	/**
	 * The constructor for the jQuery Plugin
	 * @public
	 */
	$.fn.owlCarousel.Constructor = Owl;

})(window.Zepto || window.jQuery, window, document);

/*! jQuery UI - v1.11.4 - 2017-05-13
* http://jqueryui.com
* Includes: core.js, widget.js, mouse.js, position.js, autocomplete.js, datepicker.js, menu.js
* Copyright jQuery Foundation and other contributors; Licensed MIT */

(function(t){"function"==typeof define&&define.amd?define(["jquery"],t):t(jQuery)})(function(t){function e(e,s){var n,o,a,r=e.nodeName.toLowerCase();return"area"===r?(n=e.parentNode,o=n.name,e.href&&o&&"map"===n.nodeName.toLowerCase()?(a=t("img[usemap='#"+o+"']")[0],!!a&&i(a)):!1):(/^(input|select|textarea|button|object)$/.test(r)?!e.disabled:"a"===r?e.href||s:s)&&i(e)}function i(e){return t.expr.filters.visible(e)&&!t(e).parents().addBack().filter(function(){return"hidden"===t.css(this,"visibility")}).length}function s(t){for(var e,i;t.length&&t[0]!==document;){if(e=t.css("position"),("absolute"===e||"relative"===e||"fixed"===e)&&(i=parseInt(t.css("zIndex"),10),!isNaN(i)&&0!==i))return i;t=t.parent()}return 0}function n(){this._curInst=null,this._keyEvent=!1,this._disabledInputs=[],this._datepickerShowing=!1,this._inDialog=!1,this._mainDivId="ui-datepicker-div",this._inlineClass="ui-datepicker-inline",this._appendClass="ui-datepicker-append",this._triggerClass="ui-datepicker-trigger",this._dialogClass="ui-datepicker-dialog",this._disableClass="ui-datepicker-disabled",this._unselectableClass="ui-datepicker-unselectable",this._currentClass="ui-datepicker-current-day",this._dayOverClass="ui-datepicker-days-cell-over",this.regional=[],this.regional[""]={closeText:"Done",prevText:"Prev",nextText:"Next",currentText:"Today",monthNames:["January","February","March","April","May","June","July","August","September","October","November","December"],monthNamesShort:["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],dayNames:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],dayNamesShort:["Sun","Mon","Tue","Wed","Thu","Fri","Sat"],dayNamesMin:["Su","Mo","Tu","We","Th","Fr","Sa"],weekHeader:"Wk",dateFormat:"mm/dd/yy",firstDay:0,isRTL:!1,showMonthAfterYear:!1,yearSuffix:""},this._defaults={showOn:"focus",showAnim:"fadeIn",showOptions:{},defaultDate:null,appendText:"",buttonText:"...",buttonImage:"",buttonImageOnly:!1,hideIfNoPrevNext:!1,navigationAsDateFormat:!1,gotoCurrent:!1,changeMonth:!1,changeYear:!1,yearRange:"c-10:c+10",showOtherMonths:!1,selectOtherMonths:!1,showWeek:!1,calculateWeek:this.iso8601Week,shortYearCutoff:"+10",minDate:null,maxDate:null,duration:"fast",beforeShowDay:null,beforeShow:null,onSelect:null,onChangeMonthYear:null,onClose:null,numberOfMonths:1,showCurrentAtPos:0,stepMonths:1,stepBigMonths:12,altField:"",altFormat:"",constrainInput:!0,showButtonPanel:!1,autoSize:!1,disabled:!1},t.extend(this._defaults,this.regional[""]),this.regional.en=t.extend(!0,{},this.regional[""]),this.regional["en-US"]=t.extend(!0,{},this.regional.en),this.dpDiv=o(t("<div id='"+this._mainDivId+"' class='ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all'></div>"))}function o(e){var i="button, .ui-datepicker-prev, .ui-datepicker-next, .ui-datepicker-calendar td a";return e.delegate(i,"mouseout",function(){t(this).removeClass("ui-state-hover"),-1!==this.className.indexOf("ui-datepicker-prev")&&t(this).removeClass("ui-datepicker-prev-hover"),-1!==this.className.indexOf("ui-datepicker-next")&&t(this).removeClass("ui-datepicker-next-hover")}).delegate(i,"mouseover",a)}function a(){t.datepicker._isDisabledDatepicker(u.inline?u.dpDiv.parent()[0]:u.input[0])||(t(this).parents(".ui-datepicker-calendar").find("a").removeClass("ui-state-hover"),t(this).addClass("ui-state-hover"),-1!==this.className.indexOf("ui-datepicker-prev")&&t(this).addClass("ui-datepicker-prev-hover"),-1!==this.className.indexOf("ui-datepicker-next")&&t(this).addClass("ui-datepicker-next-hover"))}function r(e,i){t.extend(e,i);for(var s in i)null==i[s]&&(e[s]=i[s]);return e}t.ui=t.ui||{},t.extend(t.ui,{version:"1.11.4",keyCode:{BACKSPACE:8,COMMA:188,DELETE:46,DOWN:40,END:35,ENTER:13,ESCAPE:27,HOME:36,LEFT:37,PAGE_DOWN:34,PAGE_UP:33,PERIOD:190,RIGHT:39,SPACE:32,TAB:9,UP:38}}),t.fn.extend({scrollParent:function(e){var i=this.css("position"),s="absolute"===i,n=e?/(auto|scroll|hidden)/:/(auto|scroll)/,o=this.parents().filter(function(){var e=t(this);return s&&"static"===e.css("position")?!1:n.test(e.css("overflow")+e.css("overflow-y")+e.css("overflow-x"))}).eq(0);return"fixed"!==i&&o.length?o:t(this[0].ownerDocument||document)},uniqueId:function(){var t=0;return function(){return this.each(function(){this.id||(this.id="ui-id-"+ ++t)})}}(),removeUniqueId:function(){return this.each(function(){/^ui-id-\d+$/.test(this.id)&&t(this).removeAttr("id")})}}),t.extend(t.expr[":"],{data:t.expr.createPseudo?t.expr.createPseudo(function(e){return function(i){return!!t.data(i,e)}}):function(e,i,s){return!!t.data(e,s[3])},focusable:function(i){return e(i,!isNaN(t.attr(i,"tabindex")))},tabbable:function(i){var s=t.attr(i,"tabindex"),n=isNaN(s);return(n||s>=0)&&e(i,!n)}}),t("<a>").outerWidth(1).jquery||t.each(["Width","Height"],function(e,i){function s(e,i,s,o){return t.each(n,function(){i-=parseFloat(t.css(e,"padding"+this))||0,s&&(i-=parseFloat(t.css(e,"border"+this+"Width"))||0),o&&(i-=parseFloat(t.css(e,"margin"+this))||0)}),i}var n="Width"===i?["Left","Right"]:["Top","Bottom"],o=i.toLowerCase(),a={innerWidth:t.fn.innerWidth,innerHeight:t.fn.innerHeight,outerWidth:t.fn.outerWidth,outerHeight:t.fn.outerHeight};t.fn["inner"+i]=function(e){return void 0===e?a["inner"+i].call(this):this.each(function(){t(this).css(o,s(this,e)+"px")})},t.fn["outer"+i]=function(e,n){return"number"!=typeof e?a["outer"+i].call(this,e):this.each(function(){t(this).css(o,s(this,e,!0,n)+"px")})}}),t.fn.addBack||(t.fn.addBack=function(t){return this.add(null==t?this.prevObject:this.prevObject.filter(t))}),t("<a>").data("a-b","a").removeData("a-b").data("a-b")&&(t.fn.removeData=function(e){return function(i){return arguments.length?e.call(this,t.camelCase(i)):e.call(this)}}(t.fn.removeData)),t.ui.ie=!!/msie [\w.]+/.exec(navigator.userAgent.toLowerCase()),t.fn.extend({focus:function(e){return function(i,s){return"number"==typeof i?this.each(function(){var e=this;setTimeout(function(){t(e).focus(),s&&s.call(e)},i)}):e.apply(this,arguments)}}(t.fn.focus),disableSelection:function(){var t="onselectstart"in document.createElement("div")?"selectstart":"mousedown";return function(){return this.bind(t+".ui-disableSelection",function(t){t.preventDefault()})}}(),enableSelection:function(){return this.unbind(".ui-disableSelection")},zIndex:function(e){if(void 0!==e)return this.css("zIndex",e);if(this.length)for(var i,s,n=t(this[0]);n.length&&n[0]!==document;){if(i=n.css("position"),("absolute"===i||"relative"===i||"fixed"===i)&&(s=parseInt(n.css("zIndex"),10),!isNaN(s)&&0!==s))return s;n=n.parent()}return 0}}),t.ui.plugin={add:function(e,i,s){var n,o=t.ui[e].prototype;for(n in s)o.plugins[n]=o.plugins[n]||[],o.plugins[n].push([i,s[n]])},call:function(t,e,i,s){var n,o=t.plugins[e];if(o&&(s||t.element[0].parentNode&&11!==t.element[0].parentNode.nodeType))for(n=0;o.length>n;n++)t.options[o[n][0]]&&o[n][1].apply(t.element,i)}};var l=0,h=Array.prototype.slice;t.cleanData=function(e){return function(i){var s,n,o;for(o=0;null!=(n=i[o]);o++)try{s=t._data(n,"events"),s&&s.remove&&t(n).triggerHandler("remove")}catch(a){}e(i)}}(t.cleanData),t.widget=function(e,i,s){var n,o,a,r,l={},h=e.split(".")[0];return e=e.split(".")[1],n=h+"-"+e,s||(s=i,i=t.Widget),t.expr[":"][n.toLowerCase()]=function(e){return!!t.data(e,n)},t[h]=t[h]||{},o=t[h][e],a=t[h][e]=function(t,e){return this._createWidget?(arguments.length&&this._createWidget(t,e),void 0):new a(t,e)},t.extend(a,o,{version:s.version,_proto:t.extend({},s),_childConstructors:[]}),r=new i,r.options=t.widget.extend({},r.options),t.each(s,function(e,s){return t.isFunction(s)?(l[e]=function(){var t=function(){return i.prototype[e].apply(this,arguments)},n=function(t){return i.prototype[e].apply(this,t)};return function(){var e,i=this._super,o=this._superApply;return this._super=t,this._superApply=n,e=s.apply(this,arguments),this._super=i,this._superApply=o,e}}(),void 0):(l[e]=s,void 0)}),a.prototype=t.widget.extend(r,{widgetEventPrefix:o?r.widgetEventPrefix||e:e},l,{constructor:a,namespace:h,widgetName:e,widgetFullName:n}),o?(t.each(o._childConstructors,function(e,i){var s=i.prototype;t.widget(s.namespace+"."+s.widgetName,a,i._proto)}),delete o._childConstructors):i._childConstructors.push(a),t.widget.bridge(e,a),a},t.widget.extend=function(e){for(var i,s,n=h.call(arguments,1),o=0,a=n.length;a>o;o++)for(i in n[o])s=n[o][i],n[o].hasOwnProperty(i)&&void 0!==s&&(e[i]=t.isPlainObject(s)?t.isPlainObject(e[i])?t.widget.extend({},e[i],s):t.widget.extend({},s):s);return e},t.widget.bridge=function(e,i){var s=i.prototype.widgetFullName||e;t.fn[e]=function(n){var o="string"==typeof n,a=h.call(arguments,1),r=this;return o?this.each(function(){var i,o=t.data(this,s);return"instance"===n?(r=o,!1):o?t.isFunction(o[n])&&"_"!==n.charAt(0)?(i=o[n].apply(o,a),i!==o&&void 0!==i?(r=i&&i.jquery?r.pushStack(i.get()):i,!1):void 0):t.error("no such method '"+n+"' for "+e+" widget instance"):t.error("cannot call methods on "+e+" prior to initialization; "+"attempted to call method '"+n+"'")}):(a.length&&(n=t.widget.extend.apply(null,[n].concat(a))),this.each(function(){var e=t.data(this,s);e?(e.option(n||{}),e._init&&e._init()):t.data(this,s,new i(n,this))})),r}},t.Widget=function(){},t.Widget._childConstructors=[],t.Widget.prototype={widgetName:"widget",widgetEventPrefix:"",defaultElement:"<div>",options:{disabled:!1,create:null},_createWidget:function(e,i){i=t(i||this.defaultElement||this)[0],this.element=t(i),this.uuid=l++,this.eventNamespace="."+this.widgetName+this.uuid,this.bindings=t(),this.hoverable=t(),this.focusable=t(),i!==this&&(t.data(i,this.widgetFullName,this),this._on(!0,this.element,{remove:function(t){t.target===i&&this.destroy()}}),this.document=t(i.style?i.ownerDocument:i.document||i),this.window=t(this.document[0].defaultView||this.document[0].parentWindow)),this.options=t.widget.extend({},this.options,this._getCreateOptions(),e),this._create(),this._trigger("create",null,this._getCreateEventData()),this._init()},_getCreateOptions:t.noop,_getCreateEventData:t.noop,_create:t.noop,_init:t.noop,destroy:function(){this._destroy(),this.element.unbind(this.eventNamespace).removeData(this.widgetFullName).removeData(t.camelCase(this.widgetFullName)),this.widget().unbind(this.eventNamespace).removeAttr("aria-disabled").removeClass(this.widgetFullName+"-disabled "+"ui-state-disabled"),this.bindings.unbind(this.eventNamespace),this.hoverable.removeClass("ui-state-hover"),this.focusable.removeClass("ui-state-focus")},_destroy:t.noop,widget:function(){return this.element},option:function(e,i){var s,n,o,a=e;if(0===arguments.length)return t.widget.extend({},this.options);if("string"==typeof e)if(a={},s=e.split("."),e=s.shift(),s.length){for(n=a[e]=t.widget.extend({},this.options[e]),o=0;s.length-1>o;o++)n[s[o]]=n[s[o]]||{},n=n[s[o]];if(e=s.pop(),1===arguments.length)return void 0===n[e]?null:n[e];n[e]=i}else{if(1===arguments.length)return void 0===this.options[e]?null:this.options[e];a[e]=i}return this._setOptions(a),this},_setOptions:function(t){var e;for(e in t)this._setOption(e,t[e]);return this},_setOption:function(t,e){return this.options[t]=e,"disabled"===t&&(this.widget().toggleClass(this.widgetFullName+"-disabled",!!e),e&&(this.hoverable.removeClass("ui-state-hover"),this.focusable.removeClass("ui-state-focus"))),this},enable:function(){return this._setOptions({disabled:!1})},disable:function(){return this._setOptions({disabled:!0})},_on:function(e,i,s){var n,o=this;"boolean"!=typeof e&&(s=i,i=e,e=!1),s?(i=n=t(i),this.bindings=this.bindings.add(i)):(s=i,i=this.element,n=this.widget()),t.each(s,function(s,a){function r(){return e||o.options.disabled!==!0&&!t(this).hasClass("ui-state-disabled")?("string"==typeof a?o[a]:a).apply(o,arguments):void 0}"string"!=typeof a&&(r.guid=a.guid=a.guid||r.guid||t.guid++);var l=s.match(/^([\w:-]*)\s*(.*)$/),h=l[1]+o.eventNamespace,c=l[2];c?n.delegate(c,h,r):i.bind(h,r)})},_off:function(e,i){i=(i||"").split(" ").join(this.eventNamespace+" ")+this.eventNamespace,e.unbind(i).undelegate(i),this.bindings=t(this.bindings.not(e).get()),this.focusable=t(this.focusable.not(e).get()),this.hoverable=t(this.hoverable.not(e).get())},_delay:function(t,e){function i(){return("string"==typeof t?s[t]:t).apply(s,arguments)}var s=this;return setTimeout(i,e||0)},_hoverable:function(e){this.hoverable=this.hoverable.add(e),this._on(e,{mouseenter:function(e){t(e.currentTarget).addClass("ui-state-hover")},mouseleave:function(e){t(e.currentTarget).removeClass("ui-state-hover")}})},_focusable:function(e){this.focusable=this.focusable.add(e),this._on(e,{focusin:function(e){t(e.currentTarget).addClass("ui-state-focus")},focusout:function(e){t(e.currentTarget).removeClass("ui-state-focus")}})},_trigger:function(e,i,s){var n,o,a=this.options[e];if(s=s||{},i=t.Event(i),i.type=(e===this.widgetEventPrefix?e:this.widgetEventPrefix+e).toLowerCase(),i.target=this.element[0],o=i.originalEvent)for(n in o)n in i||(i[n]=o[n]);return this.element.trigger(i,s),!(t.isFunction(a)&&a.apply(this.element[0],[i].concat(s))===!1||i.isDefaultPrevented())}},t.each({show:"fadeIn",hide:"fadeOut"},function(e,i){t.Widget.prototype["_"+e]=function(s,n,o){"string"==typeof n&&(n={effect:n});var a,r=n?n===!0||"number"==typeof n?i:n.effect||i:e;n=n||{},"number"==typeof n&&(n={duration:n}),a=!t.isEmptyObject(n),n.complete=o,n.delay&&s.delay(n.delay),a&&t.effects&&t.effects.effect[r]?s[e](n):r!==e&&s[r]?s[r](n.duration,n.easing,o):s.queue(function(i){t(this)[e](),o&&o.call(s[0]),i()})}}),t.widget;var c=!1;t(document).mouseup(function(){c=!1}),t.widget("ui.mouse",{version:"1.11.4",options:{cancel:"input,textarea,button,select,option",distance:1,delay:0},_mouseInit:function(){var e=this;this.element.bind("mousedown."+this.widgetName,function(t){return e._mouseDown(t)}).bind("click."+this.widgetName,function(i){return!0===t.data(i.target,e.widgetName+".preventClickEvent")?(t.removeData(i.target,e.widgetName+".preventClickEvent"),i.stopImmediatePropagation(),!1):void 0}),this.started=!1},_mouseDestroy:function(){this.element.unbind("."+this.widgetName),this._mouseMoveDelegate&&this.document.unbind("mousemove."+this.widgetName,this._mouseMoveDelegate).unbind("mouseup."+this.widgetName,this._mouseUpDelegate)},_mouseDown:function(e){if(!c){this._mouseMoved=!1,this._mouseStarted&&this._mouseUp(e),this._mouseDownEvent=e;var i=this,s=1===e.which,n="string"==typeof this.options.cancel&&e.target.nodeName?t(e.target).closest(this.options.cancel).length:!1;return s&&!n&&this._mouseCapture(e)?(this.mouseDelayMet=!this.options.delay,this.mouseDelayMet||(this._mouseDelayTimer=setTimeout(function(){i.mouseDelayMet=!0},this.options.delay)),this._mouseDistanceMet(e)&&this._mouseDelayMet(e)&&(this._mouseStarted=this._mouseStart(e)!==!1,!this._mouseStarted)?(e.preventDefault(),!0):(!0===t.data(e.target,this.widgetName+".preventClickEvent")&&t.removeData(e.target,this.widgetName+".preventClickEvent"),this._mouseMoveDelegate=function(t){return i._mouseMove(t)},this._mouseUpDelegate=function(t){return i._mouseUp(t)},this.document.bind("mousemove."+this.widgetName,this._mouseMoveDelegate).bind("mouseup."+this.widgetName,this._mouseUpDelegate),e.preventDefault(),c=!0,!0)):!0}},_mouseMove:function(e){if(this._mouseMoved){if(t.ui.ie&&(!document.documentMode||9>document.documentMode)&&!e.button)return this._mouseUp(e);if(!e.which)return this._mouseUp(e)}return(e.which||e.button)&&(this._mouseMoved=!0),this._mouseStarted?(this._mouseDrag(e),e.preventDefault()):(this._mouseDistanceMet(e)&&this._mouseDelayMet(e)&&(this._mouseStarted=this._mouseStart(this._mouseDownEvent,e)!==!1,this._mouseStarted?this._mouseDrag(e):this._mouseUp(e)),!this._mouseStarted)},_mouseUp:function(e){return this.document.unbind("mousemove."+this.widgetName,this._mouseMoveDelegate).unbind("mouseup."+this.widgetName,this._mouseUpDelegate),this._mouseStarted&&(this._mouseStarted=!1,e.target===this._mouseDownEvent.target&&t.data(e.target,this.widgetName+".preventClickEvent",!0),this._mouseStop(e)),c=!1,!1},_mouseDistanceMet:function(t){return Math.max(Math.abs(this._mouseDownEvent.pageX-t.pageX),Math.abs(this._mouseDownEvent.pageY-t.pageY))>=this.options.distance},_mouseDelayMet:function(){return this.mouseDelayMet},_mouseStart:function(){},_mouseDrag:function(){},_mouseStop:function(){},_mouseCapture:function(){return!0}}),function(){function e(t,e,i){return[parseFloat(t[0])*(p.test(t[0])?e/100:1),parseFloat(t[1])*(p.test(t[1])?i/100:1)]}function i(e,i){return parseInt(t.css(e,i),10)||0}function s(e){var i=e[0];return 9===i.nodeType?{width:e.width(),height:e.height(),offset:{top:0,left:0}}:t.isWindow(i)?{width:e.width(),height:e.height(),offset:{top:e.scrollTop(),left:e.scrollLeft()}}:i.preventDefault?{width:0,height:0,offset:{top:i.pageY,left:i.pageX}}:{width:e.outerWidth(),height:e.outerHeight(),offset:e.offset()}}t.ui=t.ui||{};var n,o,a=Math.max,r=Math.abs,l=Math.round,h=/left|center|right/,c=/top|center|bottom/,u=/[\+\-]\d+(\.[\d]+)?%?/,d=/^\w+/,p=/%$/,f=t.fn.position;t.position={scrollbarWidth:function(){if(void 0!==n)return n;var e,i,s=t("<div style='display:block;position:absolute;width:50px;height:50px;overflow:hidden;'><div style='height:100px;width:auto;'></div></div>"),o=s.children()[0];return t("body").append(s),e=o.offsetWidth,s.css("overflow","scroll"),i=o.offsetWidth,e===i&&(i=s[0].clientWidth),s.remove(),n=e-i},getScrollInfo:function(e){var i=e.isWindow||e.isDocument?"":e.element.css("overflow-x"),s=e.isWindow||e.isDocument?"":e.element.css("overflow-y"),n="scroll"===i||"auto"===i&&e.width<e.element[0].scrollWidth,o="scroll"===s||"auto"===s&&e.height<e.element[0].scrollHeight;return{width:o?t.position.scrollbarWidth():0,height:n?t.position.scrollbarWidth():0}},getWithinInfo:function(e){var i=t(e||window),s=t.isWindow(i[0]),n=!!i[0]&&9===i[0].nodeType;return{element:i,isWindow:s,isDocument:n,offset:i.offset()||{left:0,top:0},scrollLeft:i.scrollLeft(),scrollTop:i.scrollTop(),width:s||n?i.width():i.outerWidth(),height:s||n?i.height():i.outerHeight()}}},t.fn.position=function(n){if(!n||!n.of)return f.apply(this,arguments);n=t.extend({},n);var p,g,m,_,v,b,y=t(n.of),w=t.position.getWithinInfo(n.within),k=t.position.getScrollInfo(w),x=(n.collision||"flip").split(" "),C={};return b=s(y),y[0].preventDefault&&(n.at="left top"),g=b.width,m=b.height,_=b.offset,v=t.extend({},_),t.each(["my","at"],function(){var t,e,i=(n[this]||"").split(" ");1===i.length&&(i=h.test(i[0])?i.concat(["center"]):c.test(i[0])?["center"].concat(i):["center","center"]),i[0]=h.test(i[0])?i[0]:"center",i[1]=c.test(i[1])?i[1]:"center",t=u.exec(i[0]),e=u.exec(i[1]),C[this]=[t?t[0]:0,e?e[0]:0],n[this]=[d.exec(i[0])[0],d.exec(i[1])[0]]}),1===x.length&&(x[1]=x[0]),"right"===n.at[0]?v.left+=g:"center"===n.at[0]&&(v.left+=g/2),"bottom"===n.at[1]?v.top+=m:"center"===n.at[1]&&(v.top+=m/2),p=e(C.at,g,m),v.left+=p[0],v.top+=p[1],this.each(function(){var s,h,c=t(this),u=c.outerWidth(),d=c.outerHeight(),f=i(this,"marginLeft"),b=i(this,"marginTop"),D=u+f+i(this,"marginRight")+k.width,T=d+b+i(this,"marginBottom")+k.height,I=t.extend({},v),M=e(C.my,c.outerWidth(),c.outerHeight());"right"===n.my[0]?I.left-=u:"center"===n.my[0]&&(I.left-=u/2),"bottom"===n.my[1]?I.top-=d:"center"===n.my[1]&&(I.top-=d/2),I.left+=M[0],I.top+=M[1],o||(I.left=l(I.left),I.top=l(I.top)),s={marginLeft:f,marginTop:b},t.each(["left","top"],function(e,i){t.ui.position[x[e]]&&t.ui.position[x[e]][i](I,{targetWidth:g,targetHeight:m,elemWidth:u,elemHeight:d,collisionPosition:s,collisionWidth:D,collisionHeight:T,offset:[p[0]+M[0],p[1]+M[1]],my:n.my,at:n.at,within:w,elem:c})}),n.using&&(h=function(t){var e=_.left-I.left,i=e+g-u,s=_.top-I.top,o=s+m-d,l={target:{element:y,left:_.left,top:_.top,width:g,height:m},element:{element:c,left:I.left,top:I.top,width:u,height:d},horizontal:0>i?"left":e>0?"right":"center",vertical:0>o?"top":s>0?"bottom":"middle"};u>g&&g>r(e+i)&&(l.horizontal="center"),d>m&&m>r(s+o)&&(l.vertical="middle"),l.important=a(r(e),r(i))>a(r(s),r(o))?"horizontal":"vertical",n.using.call(this,t,l)}),c.offset(t.extend(I,{using:h}))})},t.ui.position={fit:{left:function(t,e){var i,s=e.within,n=s.isWindow?s.scrollLeft:s.offset.left,o=s.width,r=t.left-e.collisionPosition.marginLeft,l=n-r,h=r+e.collisionWidth-o-n;e.collisionWidth>o?l>0&&0>=h?(i=t.left+l+e.collisionWidth-o-n,t.left+=l-i):t.left=h>0&&0>=l?n:l>h?n+o-e.collisionWidth:n:l>0?t.left+=l:h>0?t.left-=h:t.left=a(t.left-r,t.left)},top:function(t,e){var i,s=e.within,n=s.isWindow?s.scrollTop:s.offset.top,o=e.within.height,r=t.top-e.collisionPosition.marginTop,l=n-r,h=r+e.collisionHeight-o-n;e.collisionHeight>o?l>0&&0>=h?(i=t.top+l+e.collisionHeight-o-n,t.top+=l-i):t.top=h>0&&0>=l?n:l>h?n+o-e.collisionHeight:n:l>0?t.top+=l:h>0?t.top-=h:t.top=a(t.top-r,t.top)}},flip:{left:function(t,e){var i,s,n=e.within,o=n.offset.left+n.scrollLeft,a=n.width,l=n.isWindow?n.scrollLeft:n.offset.left,h=t.left-e.collisionPosition.marginLeft,c=h-l,u=h+e.collisionWidth-a-l,d="left"===e.my[0]?-e.elemWidth:"right"===e.my[0]?e.elemWidth:0,p="left"===e.at[0]?e.targetWidth:"right"===e.at[0]?-e.targetWidth:0,f=-2*e.offset[0];0>c?(i=t.left+d+p+f+e.collisionWidth-a-o,(0>i||r(c)>i)&&(t.left+=d+p+f)):u>0&&(s=t.left-e.collisionPosition.marginLeft+d+p+f-l,(s>0||u>r(s))&&(t.left+=d+p+f))},top:function(t,e){var i,s,n=e.within,o=n.offset.top+n.scrollTop,a=n.height,l=n.isWindow?n.scrollTop:n.offset.top,h=t.top-e.collisionPosition.marginTop,c=h-l,u=h+e.collisionHeight-a-l,d="top"===e.my[1],p=d?-e.elemHeight:"bottom"===e.my[1]?e.elemHeight:0,f="top"===e.at[1]?e.targetHeight:"bottom"===e.at[1]?-e.targetHeight:0,g=-2*e.offset[1];0>c?(s=t.top+p+f+g+e.collisionHeight-a-o,(0>s||r(c)>s)&&(t.top+=p+f+g)):u>0&&(i=t.top-e.collisionPosition.marginTop+p+f+g-l,(i>0||u>r(i))&&(t.top+=p+f+g))}},flipfit:{left:function(){t.ui.position.flip.left.apply(this,arguments),t.ui.position.fit.left.apply(this,arguments)},top:function(){t.ui.position.flip.top.apply(this,arguments),t.ui.position.fit.top.apply(this,arguments)}}},function(){var e,i,s,n,a,r=document.getElementsByTagName("body")[0],l=document.createElement("div");e=document.createElement(r?"div":"body"),s={visibility:"hidden",width:0,height:0,border:0,margin:0,background:"none"},r&&t.extend(s,{position:"absolute",left:"-1000px",top:"-1000px"});for(a in s)e.style[a]=s[a];e.appendChild(l),i=r||document.documentElement,i.insertBefore(e,i.firstChild),l.style.cssText="position: absolute; left: 10.7432222px;",n=t(l).offset().left,o=n>10&&11>n,e.innerHTML="",i.removeChild(e)}()}(),t.ui.position,t.widget("ui.menu",{version:"1.11.4",defaultElement:"<ul>",delay:300,options:{icons:{submenu:"ui-icon-carat-1-e"},items:"> *",menus:"ul",position:{my:"left-1 top",at:"right top"},role:"menu",blur:null,focus:null,select:null},_create:function(){this.activeMenu=this.element,this.mouseHandled=!1,this.element.uniqueId().addClass("ui-menu ui-widget ui-widget-content").toggleClass("ui-menu-icons",!!this.element.find(".ui-icon").length).attr({role:this.options.role,tabIndex:0}),this.options.disabled&&this.element.addClass("ui-state-disabled").attr("aria-disabled","true"),this._on({"mousedown .ui-menu-item":function(t){t.preventDefault()},"click .ui-menu-item":function(e){var i=t(e.target);!this.mouseHandled&&i.not(".ui-state-disabled").length&&(this.select(e),e.isPropagationStopped()||(this.mouseHandled=!0),i.has(".ui-menu").length?this.expand(e):!this.element.is(":focus")&&t(this.document[0].activeElement).closest(".ui-menu").length&&(this.element.trigger("focus",[!0]),this.active&&1===this.active.parents(".ui-menu").length&&clearTimeout(this.timer)))},"mouseenter .ui-menu-item":function(e){if(!this.previousFilter){var i=t(e.currentTarget);i.siblings(".ui-state-active").removeClass("ui-state-active"),this.focus(e,i)}},mouseleave:"collapseAll","mouseleave .ui-menu":"collapseAll",focus:function(t,e){var i=this.active||this.element.find(this.options.items).eq(0);e||this.focus(t,i)},blur:function(e){this._delay(function(){t.contains(this.element[0],this.document[0].activeElement)||this.collapseAll(e)})},keydown:"_keydown"}),this.refresh(),this._on(this.document,{click:function(t){this._closeOnDocumentClick(t)&&this.collapseAll(t),this.mouseHandled=!1}})},_destroy:function(){this.element.removeAttr("aria-activedescendant").find(".ui-menu").addBack().removeClass("ui-menu ui-widget ui-widget-content ui-menu-icons ui-front").removeAttr("role").removeAttr("tabIndex").removeAttr("aria-labelledby").removeAttr("aria-expanded").removeAttr("aria-hidden").removeAttr("aria-disabled").removeUniqueId().show(),this.element.find(".ui-menu-item").removeClass("ui-menu-item").removeAttr("role").removeAttr("aria-disabled").removeUniqueId().removeClass("ui-state-hover").removeAttr("tabIndex").removeAttr("role").removeAttr("aria-haspopup").children().each(function(){var e=t(this);e.data("ui-menu-submenu-carat")&&e.remove()}),this.element.find(".ui-menu-divider").removeClass("ui-menu-divider ui-widget-content")},_keydown:function(e){var i,s,n,o,a=!0;switch(e.keyCode){case t.ui.keyCode.PAGE_UP:this.previousPage(e);break;case t.ui.keyCode.PAGE_DOWN:this.nextPage(e);break;case t.ui.keyCode.HOME:this._move("first","first",e);break;case t.ui.keyCode.END:this._move("last","last",e);break;case t.ui.keyCode.UP:this.previous(e);break;case t.ui.keyCode.DOWN:this.next(e);break;case t.ui.keyCode.LEFT:this.collapse(e);break;case t.ui.keyCode.RIGHT:this.active&&!this.active.is(".ui-state-disabled")&&this.expand(e);break;case t.ui.keyCode.ENTER:case t.ui.keyCode.SPACE:this._activate(e);break;case t.ui.keyCode.ESCAPE:this.collapse(e);break;default:a=!1,s=this.previousFilter||"",n=String.fromCharCode(e.keyCode),o=!1,clearTimeout(this.filterTimer),n===s?o=!0:n=s+n,i=this._filterMenuItems(n),i=o&&-1!==i.index(this.active.next())?this.active.nextAll(".ui-menu-item"):i,i.length||(n=String.fromCharCode(e.keyCode),i=this._filterMenuItems(n)),i.length?(this.focus(e,i),this.previousFilter=n,this.filterTimer=this._delay(function(){delete this.previousFilter},1e3)):delete this.previousFilter}a&&e.preventDefault()},_activate:function(t){this.active.is(".ui-state-disabled")||(this.active.is("[aria-haspopup='true']")?this.expand(t):this.select(t))},refresh:function(){var e,i,s=this,n=this.options.icons.submenu,o=this.element.find(this.options.menus);this.element.toggleClass("ui-menu-icons",!!this.element.find(".ui-icon").length),o.filter(":not(.ui-menu)").addClass("ui-menu ui-widget ui-widget-content ui-front").hide().attr({role:this.options.role,"aria-hidden":"true","aria-expanded":"false"}).each(function(){var e=t(this),i=e.parent(),s=t("<span>").addClass("ui-menu-icon ui-icon "+n).data("ui-menu-submenu-carat",!0);i.attr("aria-haspopup","true").prepend(s),e.attr("aria-labelledby",i.attr("id"))}),e=o.add(this.element),i=e.find(this.options.items),i.not(".ui-menu-item").each(function(){var e=t(this);s._isDivider(e)&&e.addClass("ui-widget-content ui-menu-divider")}),i.not(".ui-menu-item, .ui-menu-divider").addClass("ui-menu-item").uniqueId().attr({tabIndex:-1,role:this._itemRole()}),i.filter(".ui-state-disabled").attr("aria-disabled","true"),this.active&&!t.contains(this.element[0],this.active[0])&&this.blur()},_itemRole:function(){return{menu:"menuitem",listbox:"option"}[this.options.role]},_setOption:function(t,e){"icons"===t&&this.element.find(".ui-menu-icon").removeClass(this.options.icons.submenu).addClass(e.submenu),"disabled"===t&&this.element.toggleClass("ui-state-disabled",!!e).attr("aria-disabled",e),this._super(t,e)},focus:function(t,e){var i,s;this.blur(t,t&&"focus"===t.type),this._scrollIntoView(e),this.active=e.first(),s=this.active.addClass("ui-state-focus").removeClass("ui-state-active"),this.options.role&&this.element.attr("aria-activedescendant",s.attr("id")),this.active.parent().closest(".ui-menu-item").addClass("ui-state-active"),t&&"keydown"===t.type?this._close():this.timer=this._delay(function(){this._close()},this.delay),i=e.children(".ui-menu"),i.length&&t&&/^mouse/.test(t.type)&&this._startOpening(i),this.activeMenu=e.parent(),this._trigger("focus",t,{item:e})},_scrollIntoView:function(e){var i,s,n,o,a,r;this._hasScroll()&&(i=parseFloat(t.css(this.activeMenu[0],"borderTopWidth"))||0,s=parseFloat(t.css(this.activeMenu[0],"paddingTop"))||0,n=e.offset().top-this.activeMenu.offset().top-i-s,o=this.activeMenu.scrollTop(),a=this.activeMenu.height(),r=e.outerHeight(),0>n?this.activeMenu.scrollTop(o+n):n+r>a&&this.activeMenu.scrollTop(o+n-a+r))},blur:function(t,e){e||clearTimeout(this.timer),this.active&&(this.active.removeClass("ui-state-focus"),this.active=null,this._trigger("blur",t,{item:this.active}))},_startOpening:function(t){clearTimeout(this.timer),"true"===t.attr("aria-hidden")&&(this.timer=this._delay(function(){this._close(),this._open(t)},this.delay))},_open:function(e){var i=t.extend({of:this.active},this.options.position);clearTimeout(this.timer),this.element.find(".ui-menu").not(e.parents(".ui-menu")).hide().attr("aria-hidden","true"),e.show().removeAttr("aria-hidden").attr("aria-expanded","true").position(i)},collapseAll:function(e,i){clearTimeout(this.timer),this.timer=this._delay(function(){var s=i?this.element:t(e&&e.target).closest(this.element.find(".ui-menu"));s.length||(s=this.element),this._close(s),this.blur(e),this.activeMenu=s},this.delay)},_close:function(t){t||(t=this.active?this.active.parent():this.element),t.find(".ui-menu").hide().attr("aria-hidden","true").attr("aria-expanded","false").end().find(".ui-state-active").not(".ui-state-focus").removeClass("ui-state-active")},_closeOnDocumentClick:function(e){return!t(e.target).closest(".ui-menu").length},_isDivider:function(t){return!/[^\-\u2014\u2013\s]/.test(t.text())},collapse:function(t){var e=this.active&&this.active.parent().closest(".ui-menu-item",this.element);e&&e.length&&(this._close(),this.focus(t,e))},expand:function(t){var e=this.active&&this.active.children(".ui-menu ").find(this.options.items).first();e&&e.length&&(this._open(e.parent()),this._delay(function(){this.focus(t,e)}))},next:function(t){this._move("next","first",t)},previous:function(t){this._move("prev","last",t)},isFirstItem:function(){return this.active&&!this.active.prevAll(".ui-menu-item").length},isLastItem:function(){return this.active&&!this.active.nextAll(".ui-menu-item").length},_move:function(t,e,i){var s;this.active&&(s="first"===t||"last"===t?this.active["first"===t?"prevAll":"nextAll"](".ui-menu-item").eq(-1):this.active[t+"All"](".ui-menu-item").eq(0)),s&&s.length&&this.active||(s=this.activeMenu.find(this.options.items)[e]()),this.focus(i,s)},nextPage:function(e){var i,s,n;return this.active?(this.isLastItem()||(this._hasScroll()?(s=this.active.offset().top,n=this.element.height(),this.active.nextAll(".ui-menu-item").each(function(){return i=t(this),0>i.offset().top-s-n}),this.focus(e,i)):this.focus(e,this.activeMenu.find(this.options.items)[this.active?"last":"first"]())),void 0):(this.next(e),void 0)},previousPage:function(e){var i,s,n;return this.active?(this.isFirstItem()||(this._hasScroll()?(s=this.active.offset().top,n=this.element.height(),this.active.prevAll(".ui-menu-item").each(function(){return i=t(this),i.offset().top-s+n>0}),this.focus(e,i)):this.focus(e,this.activeMenu.find(this.options.items).first())),void 0):(this.next(e),void 0)},_hasScroll:function(){return this.element.outerHeight()<this.element.prop("scrollHeight")},select:function(e){this.active=this.active||t(e.target).closest(".ui-menu-item");var i={item:this.active};this.active.has(".ui-menu").length||this.collapseAll(e,!0),this._trigger("select",e,i)},_filterMenuItems:function(e){var i=e.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g,"\\$&"),s=RegExp("^"+i,"i");return this.activeMenu.find(this.options.items).filter(".ui-menu-item").filter(function(){return s.test(t.trim(t(this).text()))})}}),t.widget("ui.autocomplete",{version:"1.11.4",defaultElement:"<input>",options:{appendTo:null,autoFocus:!1,delay:300,minLength:1,position:{my:"left top",at:"left bottom",collision:"none"},source:null,change:null,close:null,focus:null,open:null,response:null,search:null,select:null},requestIndex:0,pending:0,_create:function(){var e,i,s,n=this.element[0].nodeName.toLowerCase(),o="textarea"===n,a="input"===n;
this.isMultiLine=o?!0:a?!1:this.element.prop("isContentEditable"),this.valueMethod=this.element[o||a?"val":"text"],this.isNewMenu=!0,this.element.addClass("ui-autocomplete-input").attr("autocomplete","off"),this._on(this.element,{keydown:function(n){if(this.element.prop("readOnly"))return e=!0,s=!0,i=!0,void 0;e=!1,s=!1,i=!1;var o=t.ui.keyCode;switch(n.keyCode){case o.PAGE_UP:e=!0,this._move("previousPage",n);break;case o.PAGE_DOWN:e=!0,this._move("nextPage",n);break;case o.UP:e=!0,this._keyEvent("previous",n);break;case o.DOWN:e=!0,this._keyEvent("next",n);break;case o.ENTER:this.menu.active&&(e=!0,n.preventDefault(),this.menu.select(n));break;case o.TAB:this.menu.active&&this.menu.select(n);break;case o.ESCAPE:this.menu.element.is(":visible")&&(this.isMultiLine||this._value(this.term),this.close(n),n.preventDefault());break;default:i=!0,this._searchTimeout(n)}},keypress:function(s){if(e)return e=!1,(!this.isMultiLine||this.menu.element.is(":visible"))&&s.preventDefault(),void 0;if(!i){var n=t.ui.keyCode;switch(s.keyCode){case n.PAGE_UP:this._move("previousPage",s);break;case n.PAGE_DOWN:this._move("nextPage",s);break;case n.UP:this._keyEvent("previous",s);break;case n.DOWN:this._keyEvent("next",s)}}},input:function(t){return s?(s=!1,t.preventDefault(),void 0):(this._searchTimeout(t),void 0)},focus:function(){this.selectedItem=null,this.previous=this._value()},blur:function(t){return this.cancelBlur?(delete this.cancelBlur,void 0):(clearTimeout(this.searching),this.close(t),this._change(t),void 0)}}),this._initSource(),this.menu=t("<ul>").addClass("ui-autocomplete ui-front").appendTo(this._appendTo()).menu({role:null}).hide().menu("instance"),this._on(this.menu.element,{mousedown:function(e){e.preventDefault(),this.cancelBlur=!0,this._delay(function(){delete this.cancelBlur});var i=this.menu.element[0];t(e.target).closest(".ui-menu-item").length||this._delay(function(){var e=this;this.document.one("mousedown",function(s){s.target===e.element[0]||s.target===i||t.contains(i,s.target)||e.close()})})},menufocus:function(e,i){var s,n;return this.isNewMenu&&(this.isNewMenu=!1,e.originalEvent&&/^mouse/.test(e.originalEvent.type))?(this.menu.blur(),this.document.one("mousemove",function(){t(e.target).trigger(e.originalEvent)}),void 0):(n=i.item.data("ui-autocomplete-item"),!1!==this._trigger("focus",e,{item:n})&&e.originalEvent&&/^key/.test(e.originalEvent.type)&&this._value(n.value),s=i.item.attr("aria-label")||n.value,s&&t.trim(s).length&&(this.liveRegion.children().hide(),t("<div>").text(s).appendTo(this.liveRegion)),void 0)},menuselect:function(t,e){var i=e.item.data("ui-autocomplete-item"),s=this.previous;this.element[0]!==this.document[0].activeElement&&(this.element.focus(),this.previous=s,this._delay(function(){this.previous=s,this.selectedItem=i})),!1!==this._trigger("select",t,{item:i})&&this._value(i.value),this.term=this._value(),this.close(t),this.selectedItem=i}}),this.liveRegion=t("<span>",{role:"status","aria-live":"assertive","aria-relevant":"additions"}).addClass("ui-helper-hidden-accessible").appendTo(this.document[0].body),this._on(this.window,{beforeunload:function(){this.element.removeAttr("autocomplete")}})},_destroy:function(){clearTimeout(this.searching),this.element.removeClass("ui-autocomplete-input").removeAttr("autocomplete"),this.menu.element.remove(),this.liveRegion.remove()},_setOption:function(t,e){this._super(t,e),"source"===t&&this._initSource(),"appendTo"===t&&this.menu.element.appendTo(this._appendTo()),"disabled"===t&&e&&this.xhr&&this.xhr.abort()},_appendTo:function(){var e=this.options.appendTo;return e&&(e=e.jquery||e.nodeType?t(e):this.document.find(e).eq(0)),e&&e[0]||(e=this.element.closest(".ui-front")),e.length||(e=this.document[0].body),e},_initSource:function(){var e,i,s=this;t.isArray(this.options.source)?(e=this.options.source,this.source=function(i,s){s(t.ui.autocomplete.filter(e,i.term))}):"string"==typeof this.options.source?(i=this.options.source,this.source=function(e,n){s.xhr&&s.xhr.abort(),s.xhr=t.ajax({url:i,data:e,dataType:"json",success:function(t){n(t)},error:function(){n([])}})}):this.source=this.options.source},_searchTimeout:function(t){clearTimeout(this.searching),this.searching=this._delay(function(){var e=this.term===this._value(),i=this.menu.element.is(":visible"),s=t.altKey||t.ctrlKey||t.metaKey||t.shiftKey;(!e||e&&!i&&!s)&&(this.selectedItem=null,this.search(null,t))},this.options.delay)},search:function(t,e){return t=null!=t?t:this._value(),this.term=this._value(),t.length<this.options.minLength?this.close(e):this._trigger("search",e)!==!1?this._search(t):void 0},_search:function(t){this.pending++,this.element.addClass("ui-autocomplete-loading"),this.cancelSearch=!1,this.source({term:t},this._response())},_response:function(){var e=++this.requestIndex;return t.proxy(function(t){e===this.requestIndex&&this.__response(t),this.pending--,this.pending||this.element.removeClass("ui-autocomplete-loading")},this)},__response:function(t){t&&(t=this._normalize(t)),this._trigger("response",null,{content:t}),!this.options.disabled&&t&&t.length&&!this.cancelSearch?(this._suggest(t),this._trigger("open")):this._close()},close:function(t){this.cancelSearch=!0,this._close(t)},_close:function(t){this.menu.element.is(":visible")&&(this.menu.element.hide(),this.menu.blur(),this.isNewMenu=!0,this._trigger("close",t))},_change:function(t){this.previous!==this._value()&&this._trigger("change",t,{item:this.selectedItem})},_normalize:function(e){return e.length&&e[0].label&&e[0].value?e:t.map(e,function(e){return"string"==typeof e?{label:e,value:e}:t.extend({},e,{label:e.label||e.value,value:e.value||e.label})})},_suggest:function(e){var i=this.menu.element.empty();this._renderMenu(i,e),this.isNewMenu=!0,this.menu.refresh(),i.show(),this._resizeMenu(),i.position(t.extend({of:this.element},this.options.position)),this.options.autoFocus&&this.menu.next()},_resizeMenu:function(){var t=this.menu.element;t.outerWidth(Math.max(t.width("").outerWidth()+1,this.element.outerWidth()))},_renderMenu:function(e,i){var s=this;t.each(i,function(t,i){s._renderItemData(e,i)})},_renderItemData:function(t,e){return this._renderItem(t,e).data("ui-autocomplete-item",e)},_renderItem:function(e,i){return t("<li>").text(i.label).appendTo(e)},_move:function(t,e){return this.menu.element.is(":visible")?this.menu.isFirstItem()&&/^previous/.test(t)||this.menu.isLastItem()&&/^next/.test(t)?(this.isMultiLine||this._value(this.term),this.menu.blur(),void 0):(this.menu[t](e),void 0):(this.search(null,e),void 0)},widget:function(){return this.menu.element},_value:function(){return this.valueMethod.apply(this.element,arguments)},_keyEvent:function(t,e){(!this.isMultiLine||this.menu.element.is(":visible"))&&(this._move(t,e),e.preventDefault())}}),t.extend(t.ui.autocomplete,{escapeRegex:function(t){return t.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g,"\\$&")},filter:function(e,i){var s=RegExp(t.ui.autocomplete.escapeRegex(i),"i");return t.grep(e,function(t){return s.test(t.label||t.value||t)})}}),t.widget("ui.autocomplete",t.ui.autocomplete,{options:{messages:{noResults:"No search results.",results:function(t){return t+(t>1?" results are":" result is")+" available, use up and down arrow keys to navigate."}}},__response:function(e){var i;this._superApply(arguments),this.options.disabled||this.cancelSearch||(i=e&&e.length?this.options.messages.results(e.length):this.options.messages.noResults,this.liveRegion.children().hide(),t("<div>").text(i).appendTo(this.liveRegion))}}),t.ui.autocomplete,t.extend(t.ui,{datepicker:{version:"1.11.4"}});var u;t.extend(n.prototype,{markerClassName:"hasDatepicker",maxRows:4,_widgetDatepicker:function(){return this.dpDiv},setDefaults:function(t){return r(this._defaults,t||{}),this},_attachDatepicker:function(e,i){var s,n,o;s=e.nodeName.toLowerCase(),n="div"===s||"span"===s,e.id||(this.uuid+=1,e.id="dp"+this.uuid),o=this._newInst(t(e),n),o.settings=t.extend({},i||{}),"input"===s?this._connectDatepicker(e,o):n&&this._inlineDatepicker(e,o)},_newInst:function(e,i){var s=e[0].id.replace(/([^A-Za-z0-9_\-])/g,"\\\\$1");return{id:s,input:e,selectedDay:0,selectedMonth:0,selectedYear:0,drawMonth:0,drawYear:0,inline:i,dpDiv:i?o(t("<div class='"+this._inlineClass+" ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all'></div>")):this.dpDiv}},_connectDatepicker:function(e,i){var s=t(e);i.append=t([]),i.trigger=t([]),s.hasClass(this.markerClassName)||(this._attachments(s,i),s.addClass(this.markerClassName).keydown(this._doKeyDown).keypress(this._doKeyPress).keyup(this._doKeyUp),this._autoSize(i),t.data(e,"datepicker",i),i.settings.disabled&&this._disableDatepicker(e))},_attachments:function(e,i){var s,n,o,a=this._get(i,"appendText"),r=this._get(i,"isRTL");i.append&&i.append.remove(),a&&(i.append=t("<span class='"+this._appendClass+"'>"+a+"</span>"),e[r?"before":"after"](i.append)),e.unbind("focus",this._showDatepicker),i.trigger&&i.trigger.remove(),s=this._get(i,"showOn"),("focus"===s||"both"===s)&&e.focus(this._showDatepicker),("button"===s||"both"===s)&&(n=this._get(i,"buttonText"),o=this._get(i,"buttonImage"),i.trigger=t(this._get(i,"buttonImageOnly")?t("<img/>").addClass(this._triggerClass).attr({src:o,alt:n,title:n}):t("<button type='button'></button>").addClass(this._triggerClass).html(o?t("<img/>").attr({src:o,alt:n,title:n}):n)),e[r?"before":"after"](i.trigger),i.trigger.click(function(){return t.datepicker._datepickerShowing&&t.datepicker._lastInput===e[0]?t.datepicker._hideDatepicker():t.datepicker._datepickerShowing&&t.datepicker._lastInput!==e[0]?(t.datepicker._hideDatepicker(),t.datepicker._showDatepicker(e[0])):t.datepicker._showDatepicker(e[0]),!1}))},_autoSize:function(t){if(this._get(t,"autoSize")&&!t.inline){var e,i,s,n,o=new Date(2009,11,20),a=this._get(t,"dateFormat");a.match(/[DM]/)&&(e=function(t){for(i=0,s=0,n=0;t.length>n;n++)t[n].length>i&&(i=t[n].length,s=n);return s},o.setMonth(e(this._get(t,a.match(/MM/)?"monthNames":"monthNamesShort"))),o.setDate(e(this._get(t,a.match(/DD/)?"dayNames":"dayNamesShort"))+20-o.getDay())),t.input.attr("size",this._formatDate(t,o).length)}},_inlineDatepicker:function(e,i){var s=t(e);s.hasClass(this.markerClassName)||(s.addClass(this.markerClassName).append(i.dpDiv),t.data(e,"datepicker",i),this._setDate(i,this._getDefaultDate(i),!0),this._updateDatepicker(i),this._updateAlternate(i),i.settings.disabled&&this._disableDatepicker(e),i.dpDiv.css("display","block"))},_dialogDatepicker:function(e,i,s,n,o){var a,l,h,c,u,d=this._dialogInst;return d||(this.uuid+=1,a="dp"+this.uuid,this._dialogInput=t("<input type='text' id='"+a+"' style='position: absolute; top: -100px; width: 0px;'/>"),this._dialogInput.keydown(this._doKeyDown),t("body").append(this._dialogInput),d=this._dialogInst=this._newInst(this._dialogInput,!1),d.settings={},t.data(this._dialogInput[0],"datepicker",d)),r(d.settings,n||{}),i=i&&i.constructor===Date?this._formatDate(d,i):i,this._dialogInput.val(i),this._pos=o?o.length?o:[o.pageX,o.pageY]:null,this._pos||(l=document.documentElement.clientWidth,h=document.documentElement.clientHeight,c=document.documentElement.scrollLeft||document.body.scrollLeft,u=document.documentElement.scrollTop||document.body.scrollTop,this._pos=[l/2-100+c,h/2-150+u]),this._dialogInput.css("left",this._pos[0]+20+"px").css("top",this._pos[1]+"px"),d.settings.onSelect=s,this._inDialog=!0,this.dpDiv.addClass(this._dialogClass),this._showDatepicker(this._dialogInput[0]),t.blockUI&&t.blockUI(this.dpDiv),t.data(this._dialogInput[0],"datepicker",d),this},_destroyDatepicker:function(e){var i,s=t(e),n=t.data(e,"datepicker");s.hasClass(this.markerClassName)&&(i=e.nodeName.toLowerCase(),t.removeData(e,"datepicker"),"input"===i?(n.append.remove(),n.trigger.remove(),s.removeClass(this.markerClassName).unbind("focus",this._showDatepicker).unbind("keydown",this._doKeyDown).unbind("keypress",this._doKeyPress).unbind("keyup",this._doKeyUp)):("div"===i||"span"===i)&&s.removeClass(this.markerClassName).empty(),u===n&&(u=null))},_enableDatepicker:function(e){var i,s,n=t(e),o=t.data(e,"datepicker");n.hasClass(this.markerClassName)&&(i=e.nodeName.toLowerCase(),"input"===i?(e.disabled=!1,o.trigger.filter("button").each(function(){this.disabled=!1}).end().filter("img").css({opacity:"1.0",cursor:""})):("div"===i||"span"===i)&&(s=n.children("."+this._inlineClass),s.children().removeClass("ui-state-disabled"),s.find("select.ui-datepicker-month, select.ui-datepicker-year").prop("disabled",!1)),this._disabledInputs=t.map(this._disabledInputs,function(t){return t===e?null:t}))},_disableDatepicker:function(e){var i,s,n=t(e),o=t.data(e,"datepicker");n.hasClass(this.markerClassName)&&(i=e.nodeName.toLowerCase(),"input"===i?(e.disabled=!0,o.trigger.filter("button").each(function(){this.disabled=!0}).end().filter("img").css({opacity:"0.5",cursor:"default"})):("div"===i||"span"===i)&&(s=n.children("."+this._inlineClass),s.children().addClass("ui-state-disabled"),s.find("select.ui-datepicker-month, select.ui-datepicker-year").prop("disabled",!0)),this._disabledInputs=t.map(this._disabledInputs,function(t){return t===e?null:t}),this._disabledInputs[this._disabledInputs.length]=e)},_isDisabledDatepicker:function(t){if(!t)return!1;for(var e=0;this._disabledInputs.length>e;e++)if(this._disabledInputs[e]===t)return!0;return!1},_getInst:function(e){try{return t.data(e,"datepicker")}catch(i){throw"Missing instance data for this datepicker"}},_optionDatepicker:function(e,i,s){var n,o,a,l,h=this._getInst(e);return 2===arguments.length&&"string"==typeof i?"defaults"===i?t.extend({},t.datepicker._defaults):h?"all"===i?t.extend({},h.settings):this._get(h,i):null:(n=i||{},"string"==typeof i&&(n={},n[i]=s),h&&(this._curInst===h&&this._hideDatepicker(),o=this._getDateDatepicker(e,!0),a=this._getMinMaxDate(h,"min"),l=this._getMinMaxDate(h,"max"),r(h.settings,n),null!==a&&void 0!==n.dateFormat&&void 0===n.minDate&&(h.settings.minDate=this._formatDate(h,a)),null!==l&&void 0!==n.dateFormat&&void 0===n.maxDate&&(h.settings.maxDate=this._formatDate(h,l)),"disabled"in n&&(n.disabled?this._disableDatepicker(e):this._enableDatepicker(e)),this._attachments(t(e),h),this._autoSize(h),this._setDate(h,o),this._updateAlternate(h),this._updateDatepicker(h)),void 0)},_changeDatepicker:function(t,e,i){this._optionDatepicker(t,e,i)},_refreshDatepicker:function(t){var e=this._getInst(t);e&&this._updateDatepicker(e)},_setDateDatepicker:function(t,e){var i=this._getInst(t);i&&(this._setDate(i,e),this._updateDatepicker(i),this._updateAlternate(i))},_getDateDatepicker:function(t,e){var i=this._getInst(t);return i&&!i.inline&&this._setDateFromField(i,e),i?this._getDate(i):null},_doKeyDown:function(e){var i,s,n,o=t.datepicker._getInst(e.target),a=!0,r=o.dpDiv.is(".ui-datepicker-rtl");if(o._keyEvent=!0,t.datepicker._datepickerShowing)switch(e.keyCode){case 9:t.datepicker._hideDatepicker(),a=!1;break;case 13:return n=t("td."+t.datepicker._dayOverClass+":not(."+t.datepicker._currentClass+")",o.dpDiv),n[0]&&t.datepicker._selectDay(e.target,o.selectedMonth,o.selectedYear,n[0]),i=t.datepicker._get(o,"onSelect"),i?(s=t.datepicker._formatDate(o),i.apply(o.input?o.input[0]:null,[s,o])):t.datepicker._hideDatepicker(),!1;case 27:t.datepicker._hideDatepicker();break;case 33:t.datepicker._adjustDate(e.target,e.ctrlKey?-t.datepicker._get(o,"stepBigMonths"):-t.datepicker._get(o,"stepMonths"),"M");break;case 34:t.datepicker._adjustDate(e.target,e.ctrlKey?+t.datepicker._get(o,"stepBigMonths"):+t.datepicker._get(o,"stepMonths"),"M");break;case 35:(e.ctrlKey||e.metaKey)&&t.datepicker._clearDate(e.target),a=e.ctrlKey||e.metaKey;break;case 36:(e.ctrlKey||e.metaKey)&&t.datepicker._gotoToday(e.target),a=e.ctrlKey||e.metaKey;break;case 37:(e.ctrlKey||e.metaKey)&&t.datepicker._adjustDate(e.target,r?1:-1,"D"),a=e.ctrlKey||e.metaKey,e.originalEvent.altKey&&t.datepicker._adjustDate(e.target,e.ctrlKey?-t.datepicker._get(o,"stepBigMonths"):-t.datepicker._get(o,"stepMonths"),"M");break;case 38:(e.ctrlKey||e.metaKey)&&t.datepicker._adjustDate(e.target,-7,"D"),a=e.ctrlKey||e.metaKey;break;case 39:(e.ctrlKey||e.metaKey)&&t.datepicker._adjustDate(e.target,r?-1:1,"D"),a=e.ctrlKey||e.metaKey,e.originalEvent.altKey&&t.datepicker._adjustDate(e.target,e.ctrlKey?+t.datepicker._get(o,"stepBigMonths"):+t.datepicker._get(o,"stepMonths"),"M");break;case 40:(e.ctrlKey||e.metaKey)&&t.datepicker._adjustDate(e.target,7,"D"),a=e.ctrlKey||e.metaKey;break;default:a=!1}else 36===e.keyCode&&e.ctrlKey?t.datepicker._showDatepicker(this):a=!1;a&&(e.preventDefault(),e.stopPropagation())},_doKeyPress:function(e){var i,s,n=t.datepicker._getInst(e.target);return t.datepicker._get(n,"constrainInput")?(i=t.datepicker._possibleChars(t.datepicker._get(n,"dateFormat")),s=String.fromCharCode(null==e.charCode?e.keyCode:e.charCode),e.ctrlKey||e.metaKey||" ">s||!i||i.indexOf(s)>-1):void 0},_doKeyUp:function(e){var i,s=t.datepicker._getInst(e.target);if(s.input.val()!==s.lastVal)try{i=t.datepicker.parseDate(t.datepicker._get(s,"dateFormat"),s.input?s.input.val():null,t.datepicker._getFormatConfig(s)),i&&(t.datepicker._setDateFromField(s),t.datepicker._updateAlternate(s),t.datepicker._updateDatepicker(s))}catch(n){}return!0},_showDatepicker:function(e){if(e=e.target||e,"input"!==e.nodeName.toLowerCase()&&(e=t("input",e.parentNode)[0]),!t.datepicker._isDisabledDatepicker(e)&&t.datepicker._lastInput!==e){var i,n,o,a,l,h,c;i=t.datepicker._getInst(e),t.datepicker._curInst&&t.datepicker._curInst!==i&&(t.datepicker._curInst.dpDiv.stop(!0,!0),i&&t.datepicker._datepickerShowing&&t.datepicker._hideDatepicker(t.datepicker._curInst.input[0])),n=t.datepicker._get(i,"beforeShow"),o=n?n.apply(e,[e,i]):{},o!==!1&&(r(i.settings,o),i.lastVal=null,t.datepicker._lastInput=e,t.datepicker._setDateFromField(i),t.datepicker._inDialog&&(e.value=""),t.datepicker._pos||(t.datepicker._pos=t.datepicker._findPos(e),t.datepicker._pos[1]+=e.offsetHeight),a=!1,t(e).parents().each(function(){return a|="fixed"===t(this).css("position"),!a}),l={left:t.datepicker._pos[0],top:t.datepicker._pos[1]},t.datepicker._pos=null,i.dpDiv.empty(),i.dpDiv.css({position:"absolute",display:"block",top:"-1000px"}),t.datepicker._updateDatepicker(i),l=t.datepicker._checkOffset(i,l,a),i.dpDiv.css({position:t.datepicker._inDialog&&t.blockUI?"static":a?"fixed":"absolute",display:"none",left:l.left+"px",top:l.top+"px"}),i.inline||(h=t.datepicker._get(i,"showAnim"),c=t.datepicker._get(i,"duration"),i.dpDiv.css("z-index",s(t(e))+1),t.datepicker._datepickerShowing=!0,t.effects&&t.effects.effect[h]?i.dpDiv.show(h,t.datepicker._get(i,"showOptions"),c):i.dpDiv[h||"show"](h?c:null),t.datepicker._shouldFocusInput(i)&&i.input.focus(),t.datepicker._curInst=i))}},_updateDatepicker:function(e){this.maxRows=4,u=e,e.dpDiv.empty().append(this._generateHTML(e)),this._attachHandlers(e);var i,s=this._getNumberOfMonths(e),n=s[1],o=17,r=e.dpDiv.find("."+this._dayOverClass+" a");r.length>0&&a.apply(r.get(0)),e.dpDiv.removeClass("ui-datepicker-multi-2 ui-datepicker-multi-3 ui-datepicker-multi-4").width(""),n>1&&e.dpDiv.addClass("ui-datepicker-multi-"+n).css("width",o*n+"em"),e.dpDiv[(1!==s[0]||1!==s[1]?"add":"remove")+"Class"]("ui-datepicker-multi"),e.dpDiv[(this._get(e,"isRTL")?"add":"remove")+"Class"]("ui-datepicker-rtl"),e===t.datepicker._curInst&&t.datepicker._datepickerShowing&&t.datepicker._shouldFocusInput(e)&&e.input.focus(),e.yearshtml&&(i=e.yearshtml,setTimeout(function(){i===e.yearshtml&&e.yearshtml&&e.dpDiv.find("select.ui-datepicker-year:first").replaceWith(e.yearshtml),i=e.yearshtml=null},0))},_shouldFocusInput:function(t){return t.input&&t.input.is(":visible")&&!t.input.is(":disabled")&&!t.input.is(":focus")},_checkOffset:function(e,i,s){var n=e.dpDiv.outerWidth(),o=e.dpDiv.outerHeight(),a=e.input?e.input.outerWidth():0,r=e.input?e.input.outerHeight():0,l=document.documentElement.clientWidth+(s?0:t(document).scrollLeft()),h=document.documentElement.clientHeight+(s?0:t(document).scrollTop());return i.left-=this._get(e,"isRTL")?n-a:0,i.left-=s&&i.left===e.input.offset().left?t(document).scrollLeft():0,i.top-=s&&i.top===e.input.offset().top+r?t(document).scrollTop():0,i.left-=Math.min(i.left,i.left+n>l&&l>n?Math.abs(i.left+n-l):0),i.top-=Math.min(i.top,i.top+o>h&&h>o?Math.abs(o+r):0),i},_findPos:function(e){for(var i,s=this._getInst(e),n=this._get(s,"isRTL");e&&("hidden"===e.type||1!==e.nodeType||t.expr.filters.hidden(e));)e=e[n?"previousSibling":"nextSibling"];return i=t(e).offset(),[i.left,i.top]},_hideDatepicker:function(e){var i,s,n,o,a=this._curInst;!a||e&&a!==t.data(e,"datepicker")||this._datepickerShowing&&(i=this._get(a,"showAnim"),s=this._get(a,"duration"),n=function(){t.datepicker._tidyDialog(a)},t.effects&&(t.effects.effect[i]||t.effects[i])?a.dpDiv.hide(i,t.datepicker._get(a,"showOptions"),s,n):a.dpDiv["slideDown"===i?"slideUp":"fadeIn"===i?"fadeOut":"hide"](i?s:null,n),i||n(),this._datepickerShowing=!1,o=this._get(a,"onClose"),o&&o.apply(a.input?a.input[0]:null,[a.input?a.input.val():"",a]),this._lastInput=null,this._inDialog&&(this._dialogInput.css({position:"absolute",left:"0",top:"-100px"}),t.blockUI&&(t.unblockUI(),t("body").append(this.dpDiv))),this._inDialog=!1)},_tidyDialog:function(t){t.dpDiv.removeClass(this._dialogClass).unbind(".ui-datepicker-calendar")},_checkExternalClick:function(e){if(t.datepicker._curInst){var i=t(e.target),s=t.datepicker._getInst(i[0]);(i[0].id!==t.datepicker._mainDivId&&0===i.parents("#"+t.datepicker._mainDivId).length&&!i.hasClass(t.datepicker.markerClassName)&&!i.closest("."+t.datepicker._triggerClass).length&&t.datepicker._datepickerShowing&&(!t.datepicker._inDialog||!t.blockUI)||i.hasClass(t.datepicker.markerClassName)&&t.datepicker._curInst!==s)&&t.datepicker._hideDatepicker()}},_adjustDate:function(e,i,s){var n=t(e),o=this._getInst(n[0]);this._isDisabledDatepicker(n[0])||(this._adjustInstDate(o,i+("M"===s?this._get(o,"showCurrentAtPos"):0),s),this._updateDatepicker(o))},_gotoToday:function(e){var i,s=t(e),n=this._getInst(s[0]);this._get(n,"gotoCurrent")&&n.currentDay?(n.selectedDay=n.currentDay,n.drawMonth=n.selectedMonth=n.currentMonth,n.drawYear=n.selectedYear=n.currentYear):(i=new Date,n.selectedDay=i.getDate(),n.drawMonth=n.selectedMonth=i.getMonth(),n.drawYear=n.selectedYear=i.getFullYear()),this._notifyChange(n),this._adjustDate(s)},_selectMonthYear:function(e,i,s){var n=t(e),o=this._getInst(n[0]);o["selected"+("M"===s?"Month":"Year")]=o["draw"+("M"===s?"Month":"Year")]=parseInt(i.options[i.selectedIndex].value,10),this._notifyChange(o),this._adjustDate(n)},_selectDay:function(e,i,s,n){var o,a=t(e);t(n).hasClass(this._unselectableClass)||this._isDisabledDatepicker(a[0])||(o=this._getInst(a[0]),o.selectedDay=o.currentDay=t("a",n).html(),o.selectedMonth=o.currentMonth=i,o.selectedYear=o.currentYear=s,this._selectDate(e,this._formatDate(o,o.currentDay,o.currentMonth,o.currentYear)))},_clearDate:function(e){var i=t(e);this._selectDate(i,"")},_selectDate:function(e,i){var s,n=t(e),o=this._getInst(n[0]);i=null!=i?i:this._formatDate(o),o.input&&o.input.val(i),this._updateAlternate(o),s=this._get(o,"onSelect"),s?s.apply(o.input?o.input[0]:null,[i,o]):o.input&&o.input.trigger("change"),o.inline?this._updateDatepicker(o):(this._hideDatepicker(),this._lastInput=o.input[0],"object"!=typeof o.input[0]&&o.input.focus(),this._lastInput=null)},_updateAlternate:function(e){var i,s,n,o=this._get(e,"altField");o&&(i=this._get(e,"altFormat")||this._get(e,"dateFormat"),s=this._getDate(e),n=this.formatDate(i,s,this._getFormatConfig(e)),t(o).each(function(){t(this).val(n)}))},noWeekends:function(t){var e=t.getDay();return[e>0&&6>e,""]},iso8601Week:function(t){var e,i=new Date(t.getTime());return i.setDate(i.getDate()+4-(i.getDay()||7)),e=i.getTime(),i.setMonth(0),i.setDate(1),Math.floor(Math.round((e-i)/864e5)/7)+1},parseDate:function(e,i,s){if(null==e||null==i)throw"Invalid arguments";if(i="object"==typeof i?""+i:i+"",""===i)return null;var n,o,a,r,l=0,h=(s?s.shortYearCutoff:null)||this._defaults.shortYearCutoff,c="string"!=typeof h?h:(new Date).getFullYear()%100+parseInt(h,10),u=(s?s.dayNamesShort:null)||this._defaults.dayNamesShort,d=(s?s.dayNames:null)||this._defaults.dayNames,p=(s?s.monthNamesShort:null)||this._defaults.monthNamesShort,f=(s?s.monthNames:null)||this._defaults.monthNames,g=-1,m=-1,_=-1,v=-1,b=!1,y=function(t){var i=e.length>n+1&&e.charAt(n+1)===t;return i&&n++,i},w=function(t){var e=y(t),s="@"===t?14:"!"===t?20:"y"===t&&e?4:"o"===t?3:2,n="y"===t?s:1,o=RegExp("^\\d{"+n+","+s+"}"),a=i.substring(l).match(o);if(!a)throw"Missing number at position "+l;return l+=a[0].length,parseInt(a[0],10)},k=function(e,s,n){var o=-1,a=t.map(y(e)?n:s,function(t,e){return[[e,t]]}).sort(function(t,e){return-(t[1].length-e[1].length)});if(t.each(a,function(t,e){var s=e[1];return i.substr(l,s.length).toLowerCase()===s.toLowerCase()?(o=e[0],l+=s.length,!1):void 0}),-1!==o)return o+1;throw"Unknown name at position "+l},x=function(){if(i.charAt(l)!==e.charAt(n))throw"Unexpected literal at position "+l;l++};for(n=0;e.length>n;n++)if(b)"'"!==e.charAt(n)||y("'")?x():b=!1;else switch(e.charAt(n)){case"d":_=w("d");break;case"D":k("D",u,d);break;case"o":v=w("o");break;case"m":m=w("m");break;case"M":m=k("M",p,f);break;case"y":g=w("y");break;case"@":r=new Date(w("@")),g=r.getFullYear(),m=r.getMonth()+1,_=r.getDate();break;case"!":r=new Date((w("!")-this._ticksTo1970)/1e4),g=r.getFullYear(),m=r.getMonth()+1,_=r.getDate();break;case"'":y("'")?x():b=!0;break;default:x()}if(i.length>l&&(a=i.substr(l),!/^\s+/.test(a)))throw"Extra/unparsed characters found in date: "+a;if(-1===g?g=(new Date).getFullYear():100>g&&(g+=(new Date).getFullYear()-(new Date).getFullYear()%100+(c>=g?0:-100)),v>-1)for(m=1,_=v;;){if(o=this._getDaysInMonth(g,m-1),o>=_)break;m++,_-=o}if(r=this._daylightSavingAdjust(new Date(g,m-1,_)),r.getFullYear()!==g||r.getMonth()+1!==m||r.getDate()!==_)throw"Invalid date";return r},ATOM:"yy-mm-dd",COOKIE:"D, dd M yy",ISO_8601:"yy-mm-dd",RFC_822:"D, d M y",RFC_850:"DD, dd-M-y",RFC_1036:"D, d M y",RFC_1123:"D, d M yy",RFC_2822:"D, d M yy",RSS:"D, d M y",TICKS:"!",TIMESTAMP:"@",W3C:"yy-mm-dd",_ticksTo1970:1e7*60*60*24*(718685+Math.floor(492.5)-Math.floor(19.7)+Math.floor(4.925)),formatDate:function(t,e,i){if(!e)return"";var s,n=(i?i.dayNamesShort:null)||this._defaults.dayNamesShort,o=(i?i.dayNames:null)||this._defaults.dayNames,a=(i?i.monthNamesShort:null)||this._defaults.monthNamesShort,r=(i?i.monthNames:null)||this._defaults.monthNames,l=function(e){var i=t.length>s+1&&t.charAt(s+1)===e;return i&&s++,i},h=function(t,e,i){var s=""+e;if(l(t))for(;i>s.length;)s="0"+s;return s},c=function(t,e,i,s){return l(t)?s[e]:i[e]},u="",d=!1;if(e)for(s=0;t.length>s;s++)if(d)"'"!==t.charAt(s)||l("'")?u+=t.charAt(s):d=!1;else switch(t.charAt(s)){case"d":u+=h("d",e.getDate(),2);break;case"D":u+=c("D",e.getDay(),n,o);break;case"o":u+=h("o",Math.round((new Date(e.getFullYear(),e.getMonth(),e.getDate()).getTime()-new Date(e.getFullYear(),0,0).getTime())/864e5),3);break;case"m":u+=h("m",e.getMonth()+1,2);break;case"M":u+=c("M",e.getMonth(),a,r);break;case"y":u+=l("y")?e.getFullYear():(10>e.getYear()%100?"0":"")+e.getYear()%100;break;case"@":u+=e.getTime();break;case"!":u+=1e4*e.getTime()+this._ticksTo1970;break;case"'":l("'")?u+="'":d=!0;break;default:u+=t.charAt(s)}return u},_possibleChars:function(t){var e,i="",s=!1,n=function(i){var s=t.length>e+1&&t.charAt(e+1)===i;return s&&e++,s};for(e=0;t.length>e;e++)if(s)"'"!==t.charAt(e)||n("'")?i+=t.charAt(e):s=!1;else switch(t.charAt(e)){case"d":case"m":case"y":case"@":i+="0123456789";break;case"D":case"M":return null;case"'":n("'")?i+="'":s=!0;break;default:i+=t.charAt(e)}return i},_get:function(t,e){return void 0!==t.settings[e]?t.settings[e]:this._defaults[e]},_setDateFromField:function(t,e){if(t.input.val()!==t.lastVal){var i=this._get(t,"dateFormat"),s=t.lastVal=t.input?t.input.val():null,n=this._getDefaultDate(t),o=n,a=this._getFormatConfig(t);try{o=this.parseDate(i,s,a)||n}catch(r){s=e?"":s}t.selectedDay=o.getDate(),t.drawMonth=t.selectedMonth=o.getMonth(),t.drawYear=t.selectedYear=o.getFullYear(),t.currentDay=s?o.getDate():0,t.currentMonth=s?o.getMonth():0,t.currentYear=s?o.getFullYear():0,this._adjustInstDate(t)}},_getDefaultDate:function(t){return this._restrictMinMax(t,this._determineDate(t,this._get(t,"defaultDate"),new Date))},_determineDate:function(e,i,s){var n=function(t){var e=new Date;return e.setDate(e.getDate()+t),e},o=function(i){try{return t.datepicker.parseDate(t.datepicker._get(e,"dateFormat"),i,t.datepicker._getFormatConfig(e))}catch(s){}for(var n=(i.toLowerCase().match(/^c/)?t.datepicker._getDate(e):null)||new Date,o=n.getFullYear(),a=n.getMonth(),r=n.getDate(),l=/([+\-]?[0-9]+)\s*(d|D|w|W|m|M|y|Y)?/g,h=l.exec(i);h;){switch(h[2]||"d"){case"d":case"D":r+=parseInt(h[1],10);break;case"w":case"W":r+=7*parseInt(h[1],10);break;case"m":case"M":a+=parseInt(h[1],10),r=Math.min(r,t.datepicker._getDaysInMonth(o,a));break;case"y":case"Y":o+=parseInt(h[1],10),r=Math.min(r,t.datepicker._getDaysInMonth(o,a))}h=l.exec(i)}return new Date(o,a,r)},a=null==i||""===i?s:"string"==typeof i?o(i):"number"==typeof i?isNaN(i)?s:n(i):new Date(i.getTime());return a=a&&"Invalid Date"==""+a?s:a,a&&(a.setHours(0),a.setMinutes(0),a.setSeconds(0),a.setMilliseconds(0)),this._daylightSavingAdjust(a)},_daylightSavingAdjust:function(t){return t?(t.setHours(t.getHours()>12?t.getHours()+2:0),t):null},_setDate:function(t,e,i){var s=!e,n=t.selectedMonth,o=t.selectedYear,a=this._restrictMinMax(t,this._determineDate(t,e,new Date));t.selectedDay=t.currentDay=a.getDate(),t.drawMonth=t.selectedMonth=t.currentMonth=a.getMonth(),t.drawYear=t.selectedYear=t.currentYear=a.getFullYear(),n===t.selectedMonth&&o===t.selectedYear||i||this._notifyChange(t),this._adjustInstDate(t),t.input&&t.input.val(s?"":this._formatDate(t))},_getDate:function(t){var e=!t.currentYear||t.input&&""===t.input.val()?null:this._daylightSavingAdjust(new Date(t.currentYear,t.currentMonth,t.currentDay));return e},_attachHandlers:function(e){var i=this._get(e,"stepMonths"),s="#"+e.id.replace(/\\\\/g,"\\");e.dpDiv.find("[data-handler]").map(function(){var e={prev:function(){t.datepicker._adjustDate(s,-i,"M")},next:function(){t.datepicker._adjustDate(s,+i,"M")},hide:function(){t.datepicker._hideDatepicker()},today:function(){t.datepicker._gotoToday(s)},selectDay:function(){return t.datepicker._selectDay(s,+this.getAttribute("data-month"),+this.getAttribute("data-year"),this),!1},selectMonth:function(){return t.datepicker._selectMonthYear(s,this,"M"),!1},selectYear:function(){return t.datepicker._selectMonthYear(s,this,"Y"),!1}};t(this).bind(this.getAttribute("data-event"),e[this.getAttribute("data-handler")])})},_generateHTML:function(t){var e,i,s,n,o,a,r,l,h,c,u,d,p,f,g,m,_,v,b,y,w,k,x,C,D,T,I,M,P,S,N,H,A,z,O,E,W,F,L,R=new Date,Y=this._daylightSavingAdjust(new Date(R.getFullYear(),R.getMonth(),R.getDate())),B=this._get(t,"isRTL"),j=this._get(t,"showButtonPanel"),q=this._get(t,"hideIfNoPrevNext"),K=this._get(t,"navigationAsDateFormat"),U=this._getNumberOfMonths(t),V=this._get(t,"showCurrentAtPos"),X=this._get(t,"stepMonths"),$=1!==U[0]||1!==U[1],G=this._daylightSavingAdjust(t.currentDay?new Date(t.currentYear,t.currentMonth,t.currentDay):new Date(9999,9,9)),J=this._getMinMaxDate(t,"min"),Q=this._getMinMaxDate(t,"max"),Z=t.drawMonth-V,te=t.drawYear;if(0>Z&&(Z+=12,te--),Q)for(e=this._daylightSavingAdjust(new Date(Q.getFullYear(),Q.getMonth()-U[0]*U[1]+1,Q.getDate())),e=J&&J>e?J:e;this._daylightSavingAdjust(new Date(te,Z,1))>e;)Z--,0>Z&&(Z=11,te--);for(t.drawMonth=Z,t.drawYear=te,i=this._get(t,"prevText"),i=K?this.formatDate(i,this._daylightSavingAdjust(new Date(te,Z-X,1)),this._getFormatConfig(t)):i,s=this._canAdjustMonth(t,-1,te,Z)?"<a class='ui-datepicker-prev ui-corner-all' data-handler='prev' data-event='click' title='"+i+"'><span class='ui-icon ui-icon-circle-triangle-"+(B?"e":"w")+"'>"+i+"</span></a>":q?"":"<a class='ui-datepicker-prev ui-corner-all ui-state-disabled' title='"+i+"'><span class='ui-icon ui-icon-circle-triangle-"+(B?"e":"w")+"'>"+i+"</span></a>",n=this._get(t,"nextText"),n=K?this.formatDate(n,this._daylightSavingAdjust(new Date(te,Z+X,1)),this._getFormatConfig(t)):n,o=this._canAdjustMonth(t,1,te,Z)?"<a class='ui-datepicker-next ui-corner-all' data-handler='next' data-event='click' title='"+n+"'><span class='ui-icon ui-icon-circle-triangle-"+(B?"w":"e")+"'>"+n+"</span></a>":q?"":"<a class='ui-datepicker-next ui-corner-all ui-state-disabled' title='"+n+"'><span class='ui-icon ui-icon-circle-triangle-"+(B?"w":"e")+"'>"+n+"</span></a>",a=this._get(t,"currentText"),r=this._get(t,"gotoCurrent")&&t.currentDay?G:Y,a=K?this.formatDate(a,r,this._getFormatConfig(t)):a,l=t.inline?"":"<button type='button' class='ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all' data-handler='hide' data-event='click'>"+this._get(t,"closeText")+"</button>",h=j?"<div class='ui-datepicker-buttonpane ui-widget-content'>"+(B?l:"")+(this._isInRange(t,r)?"<button type='button' class='ui-datepicker-current ui-state-default ui-priority-secondary ui-corner-all' data-handler='today' data-event='click'>"+a+"</button>":"")+(B?"":l)+"</div>":"",c=parseInt(this._get(t,"firstDay"),10),c=isNaN(c)?0:c,u=this._get(t,"showWeek"),d=this._get(t,"dayNames"),p=this._get(t,"dayNamesMin"),f=this._get(t,"monthNames"),g=this._get(t,"monthNamesShort"),m=this._get(t,"beforeShowDay"),_=this._get(t,"showOtherMonths"),v=this._get(t,"selectOtherMonths"),b=this._getDefaultDate(t),y="",k=0;U[0]>k;k++){for(x="",this.maxRows=4,C=0;U[1]>C;C++){if(D=this._daylightSavingAdjust(new Date(te,Z,t.selectedDay)),T=" ui-corner-all",I="",$){if(I+="<div class='ui-datepicker-group",U[1]>1)switch(C){case 0:I+=" ui-datepicker-group-first",T=" ui-corner-"+(B?"right":"left");
break;case U[1]-1:I+=" ui-datepicker-group-last",T=" ui-corner-"+(B?"left":"right");break;default:I+=" ui-datepicker-group-middle",T=""}I+="'>"}for(I+="<div class='ui-datepicker-header ui-widget-header ui-helper-clearfix"+T+"'>"+(/all|left/.test(T)&&0===k?B?o:s:"")+(/all|right/.test(T)&&0===k?B?s:o:"")+this._generateMonthYearHeader(t,Z,te,J,Q,k>0||C>0,f,g)+"</div><table class='ui-datepicker-calendar'><thead>"+"<tr>",M=u?"<th class='ui-datepicker-week-col'>"+this._get(t,"weekHeader")+"</th>":"",w=0;7>w;w++)P=(w+c)%7,M+="<th scope='col'"+((w+c+6)%7>=5?" class='ui-datepicker-week-end'":"")+">"+"<span title='"+d[P]+"'>"+p[P]+"</span></th>";for(I+=M+"</tr></thead><tbody>",S=this._getDaysInMonth(te,Z),te===t.selectedYear&&Z===t.selectedMonth&&(t.selectedDay=Math.min(t.selectedDay,S)),N=(this._getFirstDayOfMonth(te,Z)-c+7)%7,H=Math.ceil((N+S)/7),A=$?this.maxRows>H?this.maxRows:H:H,this.maxRows=A,z=this._daylightSavingAdjust(new Date(te,Z,1-N)),O=0;A>O;O++){for(I+="<tr>",E=u?"<td class='ui-datepicker-week-col'>"+this._get(t,"calculateWeek")(z)+"</td>":"",w=0;7>w;w++)W=m?m.apply(t.input?t.input[0]:null,[z]):[!0,""],F=z.getMonth()!==Z,L=F&&!v||!W[0]||J&&J>z||Q&&z>Q,E+="<td class='"+((w+c+6)%7>=5?" ui-datepicker-week-end":"")+(F?" ui-datepicker-other-month":"")+(z.getTime()===D.getTime()&&Z===t.selectedMonth&&t._keyEvent||b.getTime()===z.getTime()&&b.getTime()===D.getTime()?" "+this._dayOverClass:"")+(L?" "+this._unselectableClass+" ui-state-disabled":"")+(F&&!_?"":" "+W[1]+(z.getTime()===G.getTime()?" "+this._currentClass:"")+(z.getTime()===Y.getTime()?" ui-datepicker-today":""))+"'"+(F&&!_||!W[2]?"":" title='"+W[2].replace(/'/g,"&#39;")+"'")+(L?"":" data-handler='selectDay' data-event='click' data-month='"+z.getMonth()+"' data-year='"+z.getFullYear()+"'")+">"+(F&&!_?"&#xa0;":L?"<span class='ui-state-default'>"+z.getDate()+"</span>":"<a class='ui-state-default"+(z.getTime()===Y.getTime()?" ui-state-highlight":"")+(z.getTime()===G.getTime()?" ui-state-active":"")+(F?" ui-priority-secondary":"")+"' href='#'>"+z.getDate()+"</a>")+"</td>",z.setDate(z.getDate()+1),z=this._daylightSavingAdjust(z);I+=E+"</tr>"}Z++,Z>11&&(Z=0,te++),I+="</tbody></table>"+($?"</div>"+(U[0]>0&&C===U[1]-1?"<div class='ui-datepicker-row-break'></div>":""):""),x+=I}y+=x}return y+=h,t._keyEvent=!1,y},_generateMonthYearHeader:function(t,e,i,s,n,o,a,r){var l,h,c,u,d,p,f,g,m=this._get(t,"changeMonth"),_=this._get(t,"changeYear"),v=this._get(t,"showMonthAfterYear"),b="<div class='ui-datepicker-title'>",y="";if(o||!m)y+="<span class='ui-datepicker-month'>"+a[e]+"</span>";else{for(l=s&&s.getFullYear()===i,h=n&&n.getFullYear()===i,y+="<select class='ui-datepicker-month' data-handler='selectMonth' data-event='change'>",c=0;12>c;c++)(!l||c>=s.getMonth())&&(!h||n.getMonth()>=c)&&(y+="<option value='"+c+"'"+(c===e?" selected='selected'":"")+">"+r[c]+"</option>");y+="</select>"}if(v||(b+=y+(!o&&m&&_?"":"&#xa0;")),!t.yearshtml)if(t.yearshtml="",o||!_)b+="<span class='ui-datepicker-year'>"+i+"</span>";else{for(u=this._get(t,"yearRange").split(":"),d=(new Date).getFullYear(),p=function(t){var e=t.match(/c[+\-].*/)?i+parseInt(t.substring(1),10):t.match(/[+\-].*/)?d+parseInt(t,10):parseInt(t,10);return isNaN(e)?d:e},f=p(u[0]),g=Math.max(f,p(u[1]||"")),f=s?Math.max(f,s.getFullYear()):f,g=n?Math.min(g,n.getFullYear()):g,t.yearshtml+="<select class='ui-datepicker-year' data-handler='selectYear' data-event='change'>";g>=f;f++)t.yearshtml+="<option value='"+f+"'"+(f===i?" selected='selected'":"")+">"+f+"</option>";t.yearshtml+="</select>",b+=t.yearshtml,t.yearshtml=null}return b+=this._get(t,"yearSuffix"),v&&(b+=(!o&&m&&_?"":"&#xa0;")+y),b+="</div>"},_adjustInstDate:function(t,e,i){var s=t.drawYear+("Y"===i?e:0),n=t.drawMonth+("M"===i?e:0),o=Math.min(t.selectedDay,this._getDaysInMonth(s,n))+("D"===i?e:0),a=this._restrictMinMax(t,this._daylightSavingAdjust(new Date(s,n,o)));t.selectedDay=a.getDate(),t.drawMonth=t.selectedMonth=a.getMonth(),t.drawYear=t.selectedYear=a.getFullYear(),("M"===i||"Y"===i)&&this._notifyChange(t)},_restrictMinMax:function(t,e){var i=this._getMinMaxDate(t,"min"),s=this._getMinMaxDate(t,"max"),n=i&&i>e?i:e;return s&&n>s?s:n},_notifyChange:function(t){var e=this._get(t,"onChangeMonthYear");e&&e.apply(t.input?t.input[0]:null,[t.selectedYear,t.selectedMonth+1,t])},_getNumberOfMonths:function(t){var e=this._get(t,"numberOfMonths");return null==e?[1,1]:"number"==typeof e?[1,e]:e},_getMinMaxDate:function(t,e){return this._determineDate(t,this._get(t,e+"Date"),null)},_getDaysInMonth:function(t,e){return 32-this._daylightSavingAdjust(new Date(t,e,32)).getDate()},_getFirstDayOfMonth:function(t,e){return new Date(t,e,1).getDay()},_canAdjustMonth:function(t,e,i,s){var n=this._getNumberOfMonths(t),o=this._daylightSavingAdjust(new Date(i,s+(0>e?e:n[0]*n[1]),1));return 0>e&&o.setDate(this._getDaysInMonth(o.getFullYear(),o.getMonth())),this._isInRange(t,o)},_isInRange:function(t,e){var i,s,n=this._getMinMaxDate(t,"min"),o=this._getMinMaxDate(t,"max"),a=null,r=null,l=this._get(t,"yearRange");return l&&(i=l.split(":"),s=(new Date).getFullYear(),a=parseInt(i[0],10),r=parseInt(i[1],10),i[0].match(/[+\-].*/)&&(a+=s),i[1].match(/[+\-].*/)&&(r+=s)),(!n||e.getTime()>=n.getTime())&&(!o||e.getTime()<=o.getTime())&&(!a||e.getFullYear()>=a)&&(!r||r>=e.getFullYear())},_getFormatConfig:function(t){var e=this._get(t,"shortYearCutoff");return e="string"!=typeof e?e:(new Date).getFullYear()%100+parseInt(e,10),{shortYearCutoff:e,dayNamesShort:this._get(t,"dayNamesShort"),dayNames:this._get(t,"dayNames"),monthNamesShort:this._get(t,"monthNamesShort"),monthNames:this._get(t,"monthNames")}},_formatDate:function(t,e,i,s){e||(t.currentDay=t.selectedDay,t.currentMonth=t.selectedMonth,t.currentYear=t.selectedYear);var n=e?"object"==typeof e?e:this._daylightSavingAdjust(new Date(s,i,e)):this._daylightSavingAdjust(new Date(t.currentYear,t.currentMonth,t.currentDay));return this.formatDate(this._get(t,"dateFormat"),n,this._getFormatConfig(t))}}),t.fn.datepicker=function(e){if(!this.length)return this;t.datepicker.initialized||(t(document).mousedown(t.datepicker._checkExternalClick),t.datepicker.initialized=!0),0===t("#"+t.datepicker._mainDivId).length&&t("body").append(t.datepicker.dpDiv);var i=Array.prototype.slice.call(arguments,1);return"string"!=typeof e||"isDisabled"!==e&&"getDate"!==e&&"widget"!==e?"option"===e&&2===arguments.length&&"string"==typeof arguments[1]?t.datepicker["_"+e+"Datepicker"].apply(t.datepicker,[this[0]].concat(i)):this.each(function(){"string"==typeof e?t.datepicker["_"+e+"Datepicker"].apply(t.datepicker,[this].concat(i)):t.datepicker._attachDatepicker(this,e)}):t.datepicker["_"+e+"Datepicker"].apply(t.datepicker,[this[0]].concat(i))},t.datepicker=new n,t.datepicker.initialized=!1,t.datepicker.uuid=(new Date).getTime(),t.datepicker.version="1.11.4",t.datepicker});
/*!
 * ScrollMagic v2.0.5 (2015-04-29)
 * The javascript library for magical scroll interactions.
 * (c) 2015 Jan Paepke (@janpaepke)
 * Project Website: http://scrollmagic.io
 * 
 * @version 2.0.5
 * @license Dual licensed under MIT license and GPL.
 * @author Jan Paepke - e-mail@janpaepke.de
 *
 * @file ScrollMagic main library.
 */
/**
 * @namespace ScrollMagic
 */
(function (root, factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD. Register as an anonymous module.
		define(factory);
	} else if (typeof exports === 'object') {
		// CommonJS
		module.exports = factory();
	} else {
		// Browser global
		root.ScrollMagic = factory();
	}
}(this, function () {
	"use strict";

	var ScrollMagic = function () {
		_util.log(2, '(COMPATIBILITY NOTICE) -> As of ScrollMagic 2.0.0 you need to use \'new ScrollMagic.Controller()\' to create a new controller instance. Use \'new ScrollMagic.Scene()\' to instance a scene.');
	};

	ScrollMagic.version = "2.0.5";

	// TODO: temporary workaround for chrome's scroll jitter bug
	window.addEventListener("mousewheel", function () {});

	// global const
	var PIN_SPACER_ATTRIBUTE = "data-scrollmagic-pin-spacer";

	/**
	 * The main class that is needed once per scroll container.
	 *
	 * @class
	 *
	 * @example
	 * // basic initialization
	 * var controller = new ScrollMagic.Controller();
	 *
	 * // passing options
	 * var controller = new ScrollMagic.Controller({container: "#myContainer", loglevel: 3});
	 *
	 * @param {object} [options] - An object containing one or more options for the controller.
	 * @param {(string|object)} [options.container=window] - A selector, DOM object that references the main container for scrolling.
	 * @param {boolean} [options.vertical=true] - Sets the scroll mode to vertical (`true`) or horizontal (`false`) scrolling.
	 * @param {object} [options.globalSceneOptions={}] - These options will be passed to every Scene that is added to the controller using the addScene method. For more information on Scene options see {@link ScrollMagic.Scene}.
	 * @param {number} [options.loglevel=2] Loglevel for debugging. Note that logging is disabled in the minified version of ScrollMagic.
	 ** `0` => silent
	 ** `1` => errors
	 ** `2` => errors, warnings
	 ** `3` => errors, warnings, debuginfo
	 * @param {boolean} [options.refreshInterval=100] - Some changes don't call events by default, like changing the container size or moving a scene trigger element.  
	 This interval polls these parameters to fire the necessary events.  
	 If you don't use custom containers, trigger elements or have static layouts, where the positions of the trigger elements don't change, you can set this to 0 disable interval checking and improve performance.
	 *
	 */
	ScrollMagic.Controller = function (options) {
/*
	 * ----------------------------------------------------------------
	 * settings
	 * ----------------------------------------------------------------
	 */
		var
		NAMESPACE = 'ScrollMagic.Controller',
			SCROLL_DIRECTION_FORWARD = 'FORWARD',
			SCROLL_DIRECTION_REVERSE = 'REVERSE',
			SCROLL_DIRECTION_PAUSED = 'PAUSED',
			DEFAULT_OPTIONS = CONTROLLER_OPTIONS.defaults;

/*
	 * ----------------------------------------------------------------
	 * private vars
	 * ----------------------------------------------------------------
	 */
		var
		Controller = this,
			_options = _util.extend({}, DEFAULT_OPTIONS, options),
			_sceneObjects = [],
			_updateScenesOnNextCycle = false,
			// can be boolean (true => all scenes) or an array of scenes to be updated
			_scrollPos = 0,
			_scrollDirection = SCROLL_DIRECTION_PAUSED,
			_isDocument = true,
			_viewPortSize = 0,
			_enabled = true,
			_updateTimeout, _refreshTimeout;

/*
	 * ----------------------------------------------------------------
	 * private functions
	 * ----------------------------------------------------------------
	 */

		/**
		 * Internal constructor function of the ScrollMagic Controller
		 * @private
		 */
		var construct = function () {
			for (var key in _options) {
				if (!DEFAULT_OPTIONS.hasOwnProperty(key)) {
					log(2, "WARNING: Unknown option \"" + key + "\"");
					delete _options[key];
				}
			}
			_options.container = _util.get.elements(_options.container)[0];
			// check ScrollContainer
			if (!_options.container) {
				log(1, "ERROR creating object " + NAMESPACE + ": No valid scroll container supplied");
				throw NAMESPACE + " init failed."; // cancel
			}
			_isDocument = _options.container === window || _options.container === document.body || !document.body.contains(_options.container);
			// normalize to window
			if (_isDocument) {
				_options.container = window;
			}
			// update container size immediately
			_viewPortSize = getViewportSize();
			// set event handlers
			_options.container.addEventListener("resize", onChange);
			_options.container.addEventListener("scroll", onChange);

			_options.refreshInterval = parseInt(_options.refreshInterval) || DEFAULT_OPTIONS.refreshInterval;
			scheduleRefresh();

			log(3, "added new " + NAMESPACE + " controller (v" + ScrollMagic.version + ")");
		};

		/**
		 * Schedule the next execution of the refresh function
		 * @private
		 */
		var scheduleRefresh = function () {
			if (_options.refreshInterval > 0) {
				_refreshTimeout = window.setTimeout(refresh, _options.refreshInterval);
			}
		};

		/**
		 * Default function to get scroll pos - overwriteable using `Controller.scrollPos(newFunction)`
		 * @private
		 */
		var getScrollPos = function () {
			return _options.vertical ? _util.get.scrollTop(_options.container) : _util.get.scrollLeft(_options.container);
		};

		/**
		 * Returns the current viewport Size (width vor horizontal, height for vertical)
		 * @private
		 */
		var getViewportSize = function () {
			return _options.vertical ? _util.get.height(_options.container) : _util.get.width(_options.container);
		};

		/**
		 * Default function to set scroll pos - overwriteable using `Controller.scrollTo(newFunction)`
		 * Make available publicly for pinned mousewheel workaround.
		 * @private
		 */
		var setScrollPos = this._setScrollPos = function (pos) {
			if (_options.vertical) {
				if (_isDocument) {
					window.scrollTo(_util.get.scrollLeft(), pos);
				} else {
					_options.container.scrollTop = pos;
				}
			} else {
				if (_isDocument) {
					window.scrollTo(pos, _util.get.scrollTop());
				} else {
					_options.container.scrollLeft = pos;
				}
			}
		};

		/**
		 * Handle updates in cycles instead of on scroll (performance)
		 * @private
		 */
		var updateScenes = function () {
			if (_enabled && _updateScenesOnNextCycle) {
				// determine scenes to update
				var scenesToUpdate = _util.type.Array(_updateScenesOnNextCycle) ? _updateScenesOnNextCycle : _sceneObjects.slice(0);
				// reset scenes
				_updateScenesOnNextCycle = false;
				var oldScrollPos = _scrollPos;
				// update scroll pos now instead of onChange, as it might have changed since scheduling (i.e. in-browser smooth scroll)
				_scrollPos = Controller.scrollPos();
				var deltaScroll = _scrollPos - oldScrollPos;
				if (deltaScroll !== 0) { // scroll position changed?
					_scrollDirection = (deltaScroll > 0) ? SCROLL_DIRECTION_FORWARD : SCROLL_DIRECTION_REVERSE;
				}
				// reverse order of scenes if scrolling reverse
				if (_scrollDirection === SCROLL_DIRECTION_REVERSE) {
					scenesToUpdate.reverse();
				}
				// update scenes
				scenesToUpdate.forEach(function (scene, index) {
					log(3, "updating Scene " + (index + 1) + "/" + scenesToUpdate.length + " (" + _sceneObjects.length + " total)");
					scene.update(true);
				});
				if (scenesToUpdate.length === 0 && _options.loglevel >= 3) {
					log(3, "updating 0 Scenes (nothing added to controller)");
				}
			}
		};

		/**
		 * Initializes rAF callback
		 * @private
		 */
		var debounceUpdate = function () {
			_updateTimeout = _util.rAF(updateScenes);
		};

		/**
		 * Handles Container changes
		 * @private
		 */
		var onChange = function (e) {
			log(3, "event fired causing an update:", e.type);
			if (e.type == "resize") {
				// resize
				_viewPortSize = getViewportSize();
				_scrollDirection = SCROLL_DIRECTION_PAUSED;
			}
			// schedule update
			if (_updateScenesOnNextCycle !== true) {
				_updateScenesOnNextCycle = true;
				debounceUpdate();
			}
		};

		var refresh = function () {
			if (!_isDocument) {
				// simulate resize event. Only works for viewport relevant param (performance)
				if (_viewPortSize != getViewportSize()) {
					var resizeEvent;
					try {
						resizeEvent = new Event('resize', {
							bubbles: false,
							cancelable: false
						});
					} catch (e) { // stupid IE
						resizeEvent = document.createEvent("Event");
						resizeEvent.initEvent("resize", false, false);
					}
					_options.container.dispatchEvent(resizeEvent);
				}
			}
			_sceneObjects.forEach(function (scene, index) { // refresh all scenes
				scene.refresh();
			});
			scheduleRefresh();
		};

		/**
		 * Send a debug message to the console.
		 * provided publicly with _log for plugins
		 * @private
		 *
		 * @param {number} loglevel - The loglevel required to initiate output for the message.
		 * @param {...mixed} output - One or more variables that should be passed to the console.
		 */
		var log = this._log = function (loglevel, output) {
			if (_options.loglevel >= loglevel) {
				Array.prototype.splice.call(arguments, 1, 0, "(" + NAMESPACE + ") ->");
				_util.log.apply(window, arguments);
			}
		};
		// for scenes we have getters for each option, but for the controller we don't, so we need to make it available externally for plugins
		this._options = _options;

		/**
		 * Sort scenes in ascending order of their start offset.
		 * @private
		 *
		 * @param {array} ScenesArray - an array of ScrollMagic Scenes that should be sorted
		 * @return {array} The sorted array of Scenes.
		 */
		var sortScenes = function (ScenesArray) {
			if (ScenesArray.length <= 1) {
				return ScenesArray;
			} else {
				var scenes = ScenesArray.slice(0);
				scenes.sort(function (a, b) {
					return a.scrollOffset() > b.scrollOffset() ? 1 : -1;
				});
				return scenes;
			}
		};

		/**
		 * ----------------------------------------------------------------
		 * public functions
		 * ----------------------------------------------------------------
		 */

		/**
		 * Add one ore more scene(s) to the controller.  
		 * This is the equivalent to `Scene.addTo(controller)`.
		 * @public
		 * @example
		 * // with a previously defined scene
		 * controller.addScene(scene);
		 *
		 * // with a newly created scene.
		 * controller.addScene(new ScrollMagic.Scene({duration : 0}));
		 *
		 * // adding multiple scenes
		 * controller.addScene([scene, scene2, new ScrollMagic.Scene({duration : 0})]);
		 *
		 * @param {(ScrollMagic.Scene|array)} newScene - ScrollMagic Scene or Array of Scenes to be added to the controller.
		 * @return {Controller} Parent object for chaining.
		 */
		this.addScene = function (newScene) {
			if (_util.type.Array(newScene)) {
				newScene.forEach(function (scene, index) {
					Controller.addScene(scene);
				});
			} else if (newScene instanceof ScrollMagic.Scene) {
				if (newScene.controller() !== Controller) {
					newScene.addTo(Controller);
				} else if (_sceneObjects.indexOf(newScene) < 0) {
					// new scene
					_sceneObjects.push(newScene); // add to array
					_sceneObjects = sortScenes(_sceneObjects); // sort
					newScene.on("shift.controller_sort", function () { // resort whenever scene moves
						_sceneObjects = sortScenes(_sceneObjects);
					});
					// insert Global defaults.
					for (var key in _options.globalSceneOptions) {
						if (newScene[key]) {
							newScene[key].call(newScene, _options.globalSceneOptions[key]);
						}
					}
					log(3, "adding Scene (now " + _sceneObjects.length + " total)");
				}
			} else {
				log(1, "ERROR: invalid argument supplied for '.addScene()'");
			}
			return Controller;
		};

		/**
		 * Remove one ore more scene(s) from the controller.  
		 * This is the equivalent to `Scene.remove()`.
		 * @public
		 * @example
		 * // remove a scene from the controller
		 * controller.removeScene(scene);
		 *
		 * // remove multiple scenes from the controller
		 * controller.removeScene([scene, scene2, scene3]);
		 *
		 * @param {(ScrollMagic.Scene|array)} Scene - ScrollMagic Scene or Array of Scenes to be removed from the controller.
		 * @returns {Controller} Parent object for chaining.
		 */
		this.removeScene = function (Scene) {
			if (_util.type.Array(Scene)) {
				Scene.forEach(function (scene, index) {
					Controller.removeScene(scene);
				});
			} else {
				var index = _sceneObjects.indexOf(Scene);
				if (index > -1) {
					Scene.off("shift.controller_sort");
					_sceneObjects.splice(index, 1);
					log(3, "removing Scene (now " + _sceneObjects.length + " left)");
					Scene.remove();
				}
			}
			return Controller;
		};

		/**
		 * Update one ore more scene(s) according to the scroll position of the container.  
		 * This is the equivalent to `Scene.update()`.  
		 * The update method calculates the scene's start and end position (based on the trigger element, trigger hook, duration and offset) and checks it against the current scroll position of the container.  
		 * It then updates the current scene state accordingly (or does nothing, if the state is already correct) – Pins will be set to their correct position and tweens will be updated to their correct progress.  
		 * _**Note:** This method gets called constantly whenever Controller detects a change. The only application for you is if you change something outside of the realm of ScrollMagic, like moving the trigger or changing tween parameters._
		 * @public
		 * @example
		 * // update a specific scene on next cycle
		 * controller.updateScene(scene);
		 *
		 * // update a specific scene immediately
		 * controller.updateScene(scene, true);
		 *
		 * // update multiple scenes scene on next cycle
		 * controller.updateScene([scene1, scene2, scene3]);
		 *
		 * @param {ScrollMagic.Scene} Scene - ScrollMagic Scene or Array of Scenes that is/are supposed to be updated.
		 * @param {boolean} [immediately=false] - If `true` the update will be instant, if `false` it will wait until next update cycle.  
		 This is useful when changing multiple properties of the scene - this way it will only be updated once all new properties are set (updateScenes).
		 * @return {Controller} Parent object for chaining.
		 */
		this.updateScene = function (Scene, immediately) {
			if (_util.type.Array(Scene)) {
				Scene.forEach(function (scene, index) {
					Controller.updateScene(scene, immediately);
				});
			} else {
				if (immediately) {
					Scene.update(true);
				} else if (_updateScenesOnNextCycle !== true && Scene instanceof ScrollMagic.Scene) { // if _updateScenesOnNextCycle is true, all connected scenes are already scheduled for update
					// prep array for next update cycle
					_updateScenesOnNextCycle = _updateScenesOnNextCycle || [];
					if (_updateScenesOnNextCycle.indexOf(Scene) == -1) {
						_updateScenesOnNextCycle.push(Scene);
					}
					_updateScenesOnNextCycle = sortScenes(_updateScenesOnNextCycle); // sort
					debounceUpdate();
				}
			}
			return Controller;
		};

		/**
		 * Updates the controller params and calls updateScene on every scene, that is attached to the controller.  
		 * See `Controller.updateScene()` for more information about what this means.  
		 * In most cases you will not need this function, as it is called constantly, whenever ScrollMagic detects a state change event, like resize or scroll.  
		 * The only application for this method is when ScrollMagic fails to detect these events.  
		 * One application is with some external scroll libraries (like iScroll) that move an internal container to a negative offset instead of actually scrolling. In this case the update on the controller needs to be called whenever the child container's position changes.
		 * For this case there will also be the need to provide a custom function to calculate the correct scroll position. See `Controller.scrollPos()` for details.
		 * @public
		 * @example
		 * // update the controller on next cycle (saves performance due to elimination of redundant updates)
		 * controller.update();
		 *
		 * // update the controller immediately
		 * controller.update(true);
		 *
		 * @param {boolean} [immediately=false] - If `true` the update will be instant, if `false` it will wait until next update cycle (better performance)
		 * @return {Controller} Parent object for chaining.
		 */
		this.update = function (immediately) {
			onChange({
				type: "resize"
			}); // will update size and set _updateScenesOnNextCycle to true
			if (immediately) {
				updateScenes();
			}
			return Controller;
		};

		/**
		 * Scroll to a numeric scroll offset, a DOM element, the start of a scene or provide an alternate method for scrolling.  
		 * For vertical controllers it will change the top scroll offset and for horizontal applications it will change the left offset.
		 * @public
		 *
		 * @since 1.1.0
		 * @example
		 * // scroll to an offset of 100
		 * controller.scrollTo(100);
		 *
		 * // scroll to a DOM element
		 * controller.scrollTo("#anchor");
		 *
		 * // scroll to the beginning of a scene
		 * var scene = new ScrollMagic.Scene({offset: 200});
		 * controller.scrollTo(scene);
		 *
		 * // define a new scroll position modification function (jQuery animate instead of jump)
		 * controller.scrollTo(function (newScrollPos) {
		 *	$("html, body").animate({scrollTop: newScrollPos});
		 * });
		 * controller.scrollTo(100); // call as usual, but the new function will be used instead
		 *
		 * // define a new scroll function with an additional parameter
		 * controller.scrollTo(function (newScrollPos, message) {
		 *  console.log(message);
		 *	$(this).animate({scrollTop: newScrollPos});
		 * });
		 * // call as usual, but supply an extra parameter to the defined custom function
		 * controller.scrollTo(100, "my message");
		 *
		 * // define a new scroll function with an additional parameter containing multiple variables
		 * controller.scrollTo(function (newScrollPos, options) {
		 *  someGlobalVar = options.a + options.b;
		 *	$(this).animate({scrollTop: newScrollPos});
		 * });
		 * // call as usual, but supply an extra parameter containing multiple options
		 * controller.scrollTo(100, {a: 1, b: 2});
		 *
		 * // define a new scroll function with a callback supplied as an additional parameter
		 * controller.scrollTo(function (newScrollPos, callback) {
		 *	$(this).animate({scrollTop: newScrollPos}, 400, "swing", callback);
		 * });
		 * // call as usual, but supply an extra parameter, which is used as a callback in the previously defined custom scroll function
		 * controller.scrollTo(100, function() {
		 *	console.log("scroll has finished.");
		 * });
		 *
		 * @param {mixed} scrollTarget - The supplied argument can be one of these types:
		 * 1. `number` -> The container will scroll to this new scroll offset.
		 * 2. `string` or `object` -> Can be a selector or a DOM object.  
		 *  The container will scroll to the position of this element.
		 * 3. `ScrollMagic Scene` -> The container will scroll to the start of this scene.
		 * 4. `function` -> This function will be used for future scroll position modifications.  
		 *  This provides a way for you to change the behaviour of scrolling and adding new behaviour like animation. The function receives the new scroll position as a parameter and a reference to the container element using `this`.  
		 *  It may also optionally receive an optional additional parameter (see below)  
		 *  _**NOTE:**  
		 *  All other options will still work as expected, using the new function to scroll._
		 * @param {mixed} [additionalParameter] - If a custom scroll function was defined (see above 4.), you may want to supply additional parameters to it, when calling it. You can do this using this parameter – see examples for details. Please note, that this parameter will have no effect, if you use the default scrolling function.
		 * @returns {Controller} Parent object for chaining.
		 */
		this.scrollTo = function (scrollTarget, additionalParameter) {
			if (_util.type.Number(scrollTarget)) { // excecute
				setScrollPos.call(_options.container, scrollTarget, additionalParameter);
			} else if (scrollTarget instanceof ScrollMagic.Scene) { // scroll to scene
				if (scrollTarget.controller() === Controller) { // check if the controller is associated with this scene
					Controller.scrollTo(scrollTarget.scrollOffset(), additionalParameter);
				} else {
					log(2, "scrollTo(): The supplied scene does not belong to this controller. Scroll cancelled.", scrollTarget);
				}
			} else if (_util.type.Function(scrollTarget)) { // assign new scroll function
				setScrollPos = scrollTarget;
			} else { // scroll to element
				var elem = _util.get.elements(scrollTarget)[0];
				if (elem) {
					// if parent is pin spacer, use spacer position instead so correct start position is returned for pinned elements.
					while (elem.parentNode.hasAttribute(PIN_SPACER_ATTRIBUTE)) {
						elem = elem.parentNode;
					}

					var
					param = _options.vertical ? "top" : "left",
						// which param is of interest ?
						containerOffset = _util.get.offset(_options.container),
						// container position is needed because element offset is returned in relation to document, not in relation to container.
						elementOffset = _util.get.offset(elem);

					if (!_isDocument) { // container is not the document root, so substract scroll Position to get correct trigger element position relative to scrollcontent
						containerOffset[param] -= Controller.scrollPos();
					}

					Controller.scrollTo(elementOffset[param] - containerOffset[param], additionalParameter);
				} else {
					log(2, "scrollTo(): The supplied argument is invalid. Scroll cancelled.", scrollTarget);
				}
			}
			return Controller;
		};

		/**
		 * **Get** the current scrollPosition or **Set** a new method to calculate it.  
		 * -> **GET**:
		 * When used as a getter this function will return the current scroll position.  
		 * To get a cached value use Controller.info("scrollPos"), which will be updated in the update cycle.  
		 * For vertical controllers it will return the top scroll offset and for horizontal applications it will return the left offset.
		 *
		 * -> **SET**:
		 * When used as a setter this method prodes a way to permanently overwrite the controller's scroll position calculation.  
		 * A typical usecase is when the scroll position is not reflected by the containers scrollTop or scrollLeft values, but for example by the inner offset of a child container.  
		 * Moving a child container inside a parent is a commonly used method for several scrolling frameworks, including iScroll.  
		 * By providing an alternate calculation function you can make sure ScrollMagic receives the correct scroll position.  
		 * Please also bear in mind that your function should return y values for vertical scrolls an x for horizontals.
		 *
		 * To change the current scroll position please use `Controller.scrollTo()`.
		 * @public
		 *
		 * @example
		 * // get the current scroll Position
		 * var scrollPos = controller.scrollPos();
		 *
		 * // set a new scroll position calculation method
		 * controller.scrollPos(function () {
		 *	return this.info("vertical") ? -mychildcontainer.y : -mychildcontainer.x
		 * });
		 *
		 * @param {function} [scrollPosMethod] - The function to be used for the scroll position calculation of the container.
		 * @returns {(number|Controller)} Current scroll position or parent object for chaining.
		 */
		this.scrollPos = function (scrollPosMethod) {
			if (!arguments.length) { // get
				return getScrollPos.call(Controller);
			} else { // set
				if (_util.type.Function(scrollPosMethod)) {
					getScrollPos = scrollPosMethod;
				} else {
					log(2, "Provided value for method 'scrollPos' is not a function. To change the current scroll position use 'scrollTo()'.");
				}
			}
			return Controller;
		};

		/**
		 * **Get** all infos or one in particular about the controller.
		 * @public
		 * @example
		 * // returns the current scroll position (number)
		 * var scrollPos = controller.info("scrollPos");
		 *
		 * // returns all infos as an object
		 * var infos = controller.info();
		 *
		 * @param {string} [about] - If passed only this info will be returned instead of an object containing all.  
		 Valid options are:
		 ** `"size"` => the current viewport size of the container
		 ** `"vertical"` => true if vertical scrolling, otherwise false
		 ** `"scrollPos"` => the current scroll position
		 ** `"scrollDirection"` => the last known direction of the scroll
		 ** `"container"` => the container element
		 ** `"isDocument"` => true if container element is the document.
		 * @returns {(mixed|object)} The requested info(s).
		 */
		this.info = function (about) {
			var values = {
				size: _viewPortSize,
				// contains height or width (in regard to orientation);
				vertical: _options.vertical,
				scrollPos: _scrollPos,
				scrollDirection: _scrollDirection,
				container: _options.container,
				isDocument: _isDocument
			};
			if (!arguments.length) { // get all as an object
				return values;
			} else if (values[about] !== undefined) {
				return values[about];
			} else {
				log(1, "ERROR: option \"" + about + "\" is not available");
				return;
			}
		};

		/**
		 * **Get** or **Set** the current loglevel option value.
		 * @public
		 *
		 * @example
		 * // get the current value
		 * var loglevel = controller.loglevel();
		 *
		 * // set a new value
		 * controller.loglevel(3);
		 *
		 * @param {number} [newLoglevel] - The new loglevel setting of the Controller. `[0-3]`
		 * @returns {(number|Controller)} Current loglevel or parent object for chaining.
		 */
		this.loglevel = function (newLoglevel) {
			if (!arguments.length) { // get
				return _options.loglevel;
			} else if (_options.loglevel != newLoglevel) { // set
				_options.loglevel = newLoglevel;
			}
			return Controller;
		};

		/**
		 * **Get** or **Set** the current enabled state of the controller.  
		 * This can be used to disable all Scenes connected to the controller without destroying or removing them.
		 * @public
		 *
		 * @example
		 * // get the current value
		 * var enabled = controller.enabled();
		 *
		 * // disable the controller
		 * controller.enabled(false);
		 *
		 * @param {boolean} [newState] - The new enabled state of the controller `true` or `false`.
		 * @returns {(boolean|Controller)} Current enabled state or parent object for chaining.
		 */
		this.enabled = function (newState) {
			if (!arguments.length) { // get
				return _enabled;
			} else if (_enabled != newState) { // set
				_enabled = !! newState;
				Controller.updateScene(_sceneObjects, true);
			}
			return Controller;
		};

		/**
		 * Destroy the Controller, all Scenes and everything.
		 * @public
		 *
		 * @example
		 * // without resetting the scenes
		 * controller = controller.destroy();
		 *
		 * // with scene reset
		 * controller = controller.destroy(true);
		 *
		 * @param {boolean} [resetScenes=false] - If `true` the pins and tweens (if existent) of all scenes will be reset.
		 * @returns {null} Null to unset handler variables.
		 */
		this.destroy = function (resetScenes) {
			window.clearTimeout(_refreshTimeout);
			var i = _sceneObjects.length;
			while (i--) {
				_sceneObjects[i].destroy(resetScenes);
			}
			_options.container.removeEventListener("resize", onChange);
			_options.container.removeEventListener("scroll", onChange);
			_util.cAF(_updateTimeout);
			log(3, "destroyed " + NAMESPACE + " (reset: " + (resetScenes ? "true" : "false") + ")");
			return null;
		};

		// INIT
		construct();
		return Controller;
	};

	// store pagewide controller options
	var CONTROLLER_OPTIONS = {
		defaults: {
			container: window,
			vertical: true,
			globalSceneOptions: {},
			loglevel: 2,
			refreshInterval: 100
		}
	};
/*
 * method used to add an option to ScrollMagic Scenes.
 */
	ScrollMagic.Controller.addOption = function (name, defaultValue) {
		CONTROLLER_OPTIONS.defaults[name] = defaultValue;
	};
	// instance extension function for plugins
	ScrollMagic.Controller.extend = function (extension) {
		var oldClass = this;
		ScrollMagic.Controller = function () {
			oldClass.apply(this, arguments);
			this.$super = _util.extend({}, this); // copy parent state
			return extension.apply(this, arguments) || this;
		};
		_util.extend(ScrollMagic.Controller, oldClass); // copy properties
		ScrollMagic.Controller.prototype = oldClass.prototype; // copy prototype
		ScrollMagic.Controller.prototype.constructor = ScrollMagic.Controller; // restore constructor
	};


	/**
	 * A Scene defines where the controller should react and how.
	 *
	 * @class
	 *
	 * @example
	 * // create a standard scene and add it to a controller
	 * new ScrollMagic.Scene()
	 *		.addTo(controller);
	 *
	 * // create a scene with custom options and assign a handler to it.
	 * var scene = new ScrollMagic.Scene({
	 * 		duration: 100,
	 *		offset: 200,
	 *		triggerHook: "onEnter",
	 *		reverse: false
	 * });
	 *
	 * @param {object} [options] - Options for the Scene. The options can be updated at any time.  
	 Instead of setting the options for each scene individually you can also set them globally in the controller as the controllers `globalSceneOptions` option. The object accepts the same properties as the ones below.  
	 When a scene is added to the controller the options defined using the Scene constructor will be overwritten by those set in `globalSceneOptions`.
	 * @param {(number|function)} [options.duration=0] - The duration of the scene. 
	 If `0` tweens will auto-play when reaching the scene start point, pins will be pinned indefinetly starting at the start position.  
	 A function retuning the duration value is also supported. Please see `Scene.duration()` for details.
	 * @param {number} [options.offset=0] - Offset Value for the Trigger Position. If no triggerElement is defined this will be the scroll distance from the start of the page, after which the scene will start.
	 * @param {(string|object)} [options.triggerElement=null] - Selector or DOM object that defines the start of the scene. If undefined the scene will start right at the start of the page (unless an offset is set).
	 * @param {(number|string)} [options.triggerHook="onCenter"] - Can be a number between 0 and 1 defining the position of the trigger Hook in relation to the viewport.  
	 Can also be defined using a string:
	 ** `"onEnter"` => `1`
	 ** `"onCenter"` => `0.5`
	 ** `"onLeave"` => `0`
	 * @param {boolean} [options.reverse=true] - Should the scene reverse, when scrolling up?
	 * @param {number} [options.loglevel=2] - Loglevel for debugging. Note that logging is disabled in the minified version of ScrollMagic.
	 ** `0` => silent
	 ** `1` => errors
	 ** `2` => errors, warnings
	 ** `3` => errors, warnings, debuginfo
	 * 
	 */
	ScrollMagic.Scene = function (options) {

/*
	 * ----------------------------------------------------------------
	 * settings
	 * ----------------------------------------------------------------
	 */

		var
		NAMESPACE = 'ScrollMagic.Scene',
			SCENE_STATE_BEFORE = 'BEFORE',
			SCENE_STATE_DURING = 'DURING',
			SCENE_STATE_AFTER = 'AFTER',
			DEFAULT_OPTIONS = SCENE_OPTIONS.defaults;

/*
	 * ----------------------------------------------------------------
	 * private vars
	 * ----------------------------------------------------------------
	 */

		var
		Scene = this,
			_options = _util.extend({}, DEFAULT_OPTIONS, options),
			_state = SCENE_STATE_BEFORE,
			_progress = 0,
			_scrollOffset = {
				start: 0,
				end: 0
			},
			// reflects the controllers's scroll position for the start and end of the scene respectively
			_triggerPos = 0,
			_enabled = true,
			_durationUpdateMethod, _controller;

		/**
		 * Internal constructor function of the ScrollMagic Scene
		 * @private
		 */
		var construct = function () {
			for (var key in _options) { // check supplied options
				if (!DEFAULT_OPTIONS.hasOwnProperty(key)) {
					log(2, "WARNING: Unknown option \"" + key + "\"");
					delete _options[key];
				}
			}
			// add getters/setters for all possible options
			for (var optionName in DEFAULT_OPTIONS) {
				addSceneOption(optionName);
			}
			// validate all options
			validateOption();
		};

/*
 * ----------------------------------------------------------------
 * Event Management
 * ----------------------------------------------------------------
 */

		var _listeners = {};
		/**
		 * Scene start event.  
		 * Fires whenever the scroll position its the starting point of the scene.  
		 * It will also fire when scrolling back up going over the start position of the scene. If you want something to happen only when scrolling down/right, use the scrollDirection parameter passed to the callback.
		 *
		 * For details on this event and the order in which it is fired, please review the {@link Scene.progress} method.
		 *
		 * @event ScrollMagic.Scene#start
		 *
		 * @example
		 * scene.on("start", function (event) {
		 * 	console.log("Hit start point of scene.");
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {number} event.progress - Reflects the current progress of the scene
		 * @property {string} event.state - The current state of the scene `"BEFORE"` or `"DURING"`
		 * @property {string} event.scrollDirection - Indicates which way we are scrolling `"PAUSED"`, `"FORWARD"` or `"REVERSE"`
		 */
		/**
		 * Scene end event.  
		 * Fires whenever the scroll position its the ending point of the scene.  
		 * It will also fire when scrolling back up from after the scene and going over its end position. If you want something to happen only when scrolling down/right, use the scrollDirection parameter passed to the callback.
		 *
		 * For details on this event and the order in which it is fired, please review the {@link Scene.progress} method.
		 *
		 * @event ScrollMagic.Scene#end
		 *
		 * @example
		 * scene.on("end", function (event) {
		 * 	console.log("Hit end point of scene.");
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {number} event.progress - Reflects the current progress of the scene
		 * @property {string} event.state - The current state of the scene `"DURING"` or `"AFTER"`
		 * @property {string} event.scrollDirection - Indicates which way we are scrolling `"PAUSED"`, `"FORWARD"` or `"REVERSE"`
		 */
		/**
		 * Scene enter event.  
		 * Fires whenever the scene enters the "DURING" state.  
		 * Keep in mind that it doesn't matter if the scene plays forward or backward: This event always fires when the scene enters its active scroll timeframe, regardless of the scroll-direction.
		 *
		 * For details on this event and the order in which it is fired, please review the {@link Scene.progress} method.
		 *
		 * @event ScrollMagic.Scene#enter
		 *
		 * @example
		 * scene.on("enter", function (event) {
		 * 	console.log("Scene entered.");
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {number} event.progress - Reflects the current progress of the scene
		 * @property {string} event.state - The current state of the scene - always `"DURING"`
		 * @property {string} event.scrollDirection - Indicates which way we are scrolling `"PAUSED"`, `"FORWARD"` or `"REVERSE"`
		 */
		/**
		 * Scene leave event.  
		 * Fires whenever the scene's state goes from "DURING" to either "BEFORE" or "AFTER".  
		 * Keep in mind that it doesn't matter if the scene plays forward or backward: This event always fires when the scene leaves its active scroll timeframe, regardless of the scroll-direction.
		 *
		 * For details on this event and the order in which it is fired, please review the {@link Scene.progress} method.
		 *
		 * @event ScrollMagic.Scene#leave
		 *
		 * @example
		 * scene.on("leave", function (event) {
		 * 	console.log("Scene left.");
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {number} event.progress - Reflects the current progress of the scene
		 * @property {string} event.state - The current state of the scene `"BEFORE"` or `"AFTER"`
		 * @property {string} event.scrollDirection - Indicates which way we are scrolling `"PAUSED"`, `"FORWARD"` or `"REVERSE"`
		 */
		/**
		 * Scene update event.  
		 * Fires whenever the scene is updated (but not necessarily changes the progress).
		 *
		 * @event ScrollMagic.Scene#update
		 *
		 * @example
		 * scene.on("update", function (event) {
		 * 	console.log("Scene updated.");
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {number} event.startPos - The starting position of the scene (in relation to the conainer)
		 * @property {number} event.endPos - The ending position of the scene (in relation to the conainer)
		 * @property {number} event.scrollPos - The current scroll position of the container
		 */
		/**
		 * Scene progress event.  
		 * Fires whenever the progress of the scene changes.
		 *
		 * For details on this event and the order in which it is fired, please review the {@link Scene.progress} method.
		 *
		 * @event ScrollMagic.Scene#progress
		 *
		 * @example
		 * scene.on("progress", function (event) {
		 * 	console.log("Scene progress changed to " + event.progress);
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {number} event.progress - Reflects the current progress of the scene
		 * @property {string} event.state - The current state of the scene `"BEFORE"`, `"DURING"` or `"AFTER"`
		 * @property {string} event.scrollDirection - Indicates which way we are scrolling `"PAUSED"`, `"FORWARD"` or `"REVERSE"`
		 */
		/**
		 * Scene change event.  
		 * Fires whenvever a property of the scene is changed.
		 *
		 * @event ScrollMagic.Scene#change
		 *
		 * @example
		 * scene.on("change", function (event) {
		 * 	console.log("Scene Property \"" + event.what + "\" changed to " + event.newval);
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {string} event.what - Indicates what value has been changed
		 * @property {mixed} event.newval - The new value of the changed property
		 */
		/**
		 * Scene shift event.  
		 * Fires whenvever the start or end **scroll offset** of the scene change.
		 * This happens explicitely, when one of these values change: `offset`, `duration` or `triggerHook`.
		 * It will fire implicitly when the `triggerElement` changes, if the new element has a different position (most cases).
		 * It will also fire implicitly when the size of the container changes and the triggerHook is anything other than `onLeave`.
		 *
		 * @event ScrollMagic.Scene#shift
		 * @since 1.1.0
		 *
		 * @example
		 * scene.on("shift", function (event) {
		 * 	console.log("Scene moved, because the " + event.reason + " has changed.)");
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {string} event.reason - Indicates why the scene has shifted
		 */
		/**
		 * Scene destroy event.  
		 * Fires whenvever the scene is destroyed.
		 * This can be used to tidy up custom behaviour used in events.
		 *
		 * @event ScrollMagic.Scene#destroy
		 * @since 1.1.0
		 *
		 * @example
		 * scene.on("enter", function (event) {
		 *        // add custom action
		 *        $("#my-elem").left("200");
		 *      })
		 *      .on("destroy", function (event) {
		 *        // reset my element to start position
		 *        if (event.reset) {
		 *          $("#my-elem").left("0");
		 *        }
		 *      });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {boolean} event.reset - Indicates if the destroy method was called with reset `true` or `false`.
		 */
		/**
		 * Scene add event.  
		 * Fires when the scene is added to a controller.
		 * This is mostly used by plugins to know that change might be due.
		 *
		 * @event ScrollMagic.Scene#add
		 * @since 2.0.0
		 *
		 * @example
		 * scene.on("add", function (event) {
		 * 	console.log('Scene was added to a new controller.');
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 * @property {boolean} event.controller - The controller object the scene was added to.
		 */
		/**
		 * Scene remove event.  
		 * Fires when the scene is removed from a controller.
		 * This is mostly used by plugins to know that change might be due.
		 *
		 * @event ScrollMagic.Scene#remove
		 * @since 2.0.0
		 *
		 * @example
		 * scene.on("remove", function (event) {
		 * 	console.log('Scene was removed from its controller.');
		 * });
		 *
		 * @property {object} event - The event Object passed to each callback
		 * @property {string} event.type - The name of the event
		 * @property {Scene} event.target - The Scene object that triggered this event
		 */

		/**
		 * Add one ore more event listener.  
		 * The callback function will be fired at the respective event, and an object containing relevant data will be passed to the callback.
		 * @method ScrollMagic.Scene#on
		 *
		 * @example
		 * function callback (event) {
		 * 		console.log("Event fired! (" + event.type + ")");
		 * }
		 * // add listeners
		 * scene.on("change update progress start end enter leave", callback);
		 *
		 * @param {string} names - The name or names of the event the callback should be attached to.
		 * @param {function} callback - A function that should be executed, when the event is dispatched. An event object will be passed to the callback.
		 * @returns {Scene} Parent object for chaining.
		 */
		this.on = function (names, callback) {
			if (_util.type.Function(callback)) {
				names = names.trim().split(' ');
				names.forEach(function (fullname) {
					var
					nameparts = fullname.split('.'),
						eventname = nameparts[0],
						namespace = nameparts[1];
					if (eventname != "*") { // disallow wildcards
						if (!_listeners[eventname]) {
							_listeners[eventname] = [];
						}
						_listeners[eventname].push({
							namespace: namespace || '',
							callback: callback
						});
					}
				});
			} else {
				log(1, "ERROR when calling '.on()': Supplied callback for '" + names + "' is not a valid function!");
			}
			return Scene;
		};

		/**
		 * Remove one or more event listener.
		 * @method ScrollMagic.Scene#off
		 *
		 * @example
		 * function callback (event) {
		 * 		console.log("Event fired! (" + event.type + ")");
		 * }
		 * // add listeners
		 * scene.on("change update", callback);
		 * // remove listeners
		 * scene.off("change update", callback);
		 *
		 * @param {string} names - The name or names of the event that should be removed.
		 * @param {function} [callback] - A specific callback function that should be removed. If none is passed all callbacks to the event listener will be removed.
		 * @returns {Scene} Parent object for chaining.
		 */
		this.off = function (names, callback) {
			if (!names) {
				log(1, "ERROR: Invalid event name supplied.");
				return Scene;
			}
			names = names.trim().split(' ');
			names.forEach(function (fullname, key) {
				var
				nameparts = fullname.split('.'),
					eventname = nameparts[0],
					namespace = nameparts[1] || '',
					removeList = eventname === '*' ? Object.keys(_listeners) : [eventname];
				removeList.forEach(function (remove) {
					var
					list = _listeners[remove] || [],
						i = list.length;
					while (i--) {
						var listener = list[i];
						if (listener && (namespace === listener.namespace || namespace === '*') && (!callback || callback == listener.callback)) {
							list.splice(i, 1);
						}
					}
					if (!list.length) {
						delete _listeners[remove];
					}
				});
			});
			return Scene;
		};

		/**
		 * Trigger an event.
		 * @method ScrollMagic.Scene#trigger
		 *
		 * @example
		 * this.trigger("change");
		 *
		 * @param {string} name - The name of the event that should be triggered.
		 * @param {object} [vars] - An object containing info that should be passed to the callback.
		 * @returns {Scene} Parent object for chaining.
		 */
		this.trigger = function (name, vars) {
			if (name) {
				var
				nameparts = name.trim().split('.'),
					eventname = nameparts[0],
					namespace = nameparts[1],
					listeners = _listeners[eventname];
				log(3, 'event fired:', eventname, vars ? "->" : '', vars || '');
				if (listeners) {
					listeners.forEach(function (listener, key) {
						if (!namespace || namespace === listener.namespace) {
							listener.callback.call(Scene, new ScrollMagic.Event(eventname, listener.namespace, Scene, vars));
						}
					});
				}
			} else {
				log(1, "ERROR: Invalid event name supplied.");
			}
			return Scene;
		};

		// set event listeners
		Scene.on("change.internal", function (e) {
			if (e.what !== "loglevel" && e.what !== "tweenChanges") { // no need for a scene update scene with these options...
				if (e.what === "triggerElement") {
					updateTriggerElementPosition();
				} else if (e.what === "reverse") { // the only property left that may have an impact on the current scene state. Everything else is handled by the shift event.
					Scene.update();
				}
			}
		}).on("shift.internal", function (e) {
			updateScrollOffset();
			Scene.update(); // update scene to reflect new position
		});

		/**
		 * Send a debug message to the console.
		 * @private
		 * but provided publicly with _log for plugins
		 *
		 * @param {number} loglevel - The loglevel required to initiate output for the message.
		 * @param {...mixed} output - One or more variables that should be passed to the console.
		 */
		var log = this._log = function (loglevel, output) {
			if (_options.loglevel >= loglevel) {
				Array.prototype.splice.call(arguments, 1, 0, "(" + NAMESPACE + ") ->");
				_util.log.apply(window, arguments);
			}
		};

		/**
		 * Add the scene to a controller.  
		 * This is the equivalent to `Controller.addScene(scene)`.
		 * @method ScrollMagic.Scene#addTo
		 *
		 * @example
		 * // add a scene to a ScrollMagic Controller
		 * scene.addTo(controller);
		 *
		 * @param {ScrollMagic.Controller} controller - The controller to which the scene should be added.
		 * @returns {Scene} Parent object for chaining.
		 */
		this.addTo = function (controller) {
			if (!(controller instanceof ScrollMagic.Controller)) {
				log(1, "ERROR: supplied argument of 'addTo()' is not a valid ScrollMagic Controller");
			} else if (_controller != controller) {
				// new controller
				if (_controller) { // was associated to a different controller before, so remove it...
					_controller.removeScene(Scene);
				}
				_controller = controller;
				validateOption();
				updateDuration(true);
				updateTriggerElementPosition(true);
				updateScrollOffset();
				_controller.info("container").addEventListener('resize', onContainerResize);
				controller.addScene(Scene);
				Scene.trigger("add", {
					controller: _controller
				});
				log(3, "added " + NAMESPACE + " to controller");
				Scene.update();
			}
			return Scene;
		};

		/**
		 * **Get** or **Set** the current enabled state of the scene.  
		 * This can be used to disable this scene without removing or destroying it.
		 * @method ScrollMagic.Scene#enabled
		 *
		 * @example
		 * // get the current value
		 * var enabled = scene.enabled();
		 *
		 * // disable the scene
		 * scene.enabled(false);
		 *
		 * @param {boolean} [newState] - The new enabled state of the scene `true` or `false`.
		 * @returns {(boolean|Scene)} Current enabled state or parent object for chaining.
		 */
		this.enabled = function (newState) {
			if (!arguments.length) { // get
				return _enabled;
			} else if (_enabled != newState) { // set
				_enabled = !! newState;
				Scene.update(true);
			}
			return Scene;
		};

		/**
		 * Remove the scene from the controller.  
		 * This is the equivalent to `Controller.removeScene(scene)`.
		 * The scene will not be updated anymore until you readd it to a controller.
		 * To remove the pin or the tween you need to call removeTween() or removePin() respectively.
		 * @method ScrollMagic.Scene#remove
		 * @example
		 * // remove the scene from its controller
		 * scene.remove();
		 *
		 * @returns {Scene} Parent object for chaining.
		 */
		this.remove = function () {
			if (_controller) {
				_controller.info("container").removeEventListener('resize', onContainerResize);
				var tmpParent = _controller;
				_controller = undefined;
				tmpParent.removeScene(Scene);
				Scene.trigger("remove");
				log(3, "removed " + NAMESPACE + " from controller");
			}
			return Scene;
		};

		/**
		 * Destroy the scene and everything.
		 * @method ScrollMagic.Scene#destroy
		 * @example
		 * // destroy the scene without resetting the pin and tween to their initial positions
		 * scene = scene.destroy();
		 *
		 * // destroy the scene and reset the pin and tween
		 * scene = scene.destroy(true);
		 *
		 * @param {boolean} [reset=false] - If `true` the pin and tween (if existent) will be reset.
		 * @returns {null} Null to unset handler variables.
		 */
		this.destroy = function (reset) {
			Scene.trigger("destroy", {
				reset: reset
			});
			Scene.remove();
			Scene.off("*.*");
			log(3, "destroyed " + NAMESPACE + " (reset: " + (reset ? "true" : "false") + ")");
			return null;
		};


		/**
		 * Updates the Scene to reflect the current state.  
		 * This is the equivalent to `Controller.updateScene(scene, immediately)`.  
		 * The update method calculates the scene's start and end position (based on the trigger element, trigger hook, duration and offset) and checks it against the current scroll position of the container.  
		 * It then updates the current scene state accordingly (or does nothing, if the state is already correct) – Pins will be set to their correct position and tweens will be updated to their correct progress.
		 * This means an update doesn't necessarily result in a progress change. The `progress` event will be fired if the progress has indeed changed between this update and the last.  
		 * _**NOTE:** This method gets called constantly whenever ScrollMagic detects a change. The only application for you is if you change something outside of the realm of ScrollMagic, like moving the trigger or changing tween parameters._
		 * @method ScrollMagic.Scene#update
		 * @example
		 * // update the scene on next tick
		 * scene.update();
		 *
		 * // update the scene immediately
		 * scene.update(true);
		 *
		 * @fires Scene.update
		 *
		 * @param {boolean} [immediately=false] - If `true` the update will be instant, if `false` it will wait until next update cycle (better performance).
		 * @returns {Scene} Parent object for chaining.
		 */
		this.update = function (immediately) {
			if (_controller) {
				if (immediately) {
					if (_controller.enabled() && _enabled) {
						var
						scrollPos = _controller.info("scrollPos"),
							newProgress;

						if (_options.duration > 0) {
							newProgress = (scrollPos - _scrollOffset.start) / (_scrollOffset.end - _scrollOffset.start);
						} else {
							newProgress = scrollPos >= _scrollOffset.start ? 1 : 0;
						}

						Scene.trigger("update", {
							startPos: _scrollOffset.start,
							endPos: _scrollOffset.end,
							scrollPos: scrollPos
						});

						Scene.progress(newProgress);
					} else if (_pin && _state === SCENE_STATE_DURING) {
						updatePinState(true); // unpin in position
					}
				} else {
					_controller.updateScene(Scene, false);
				}
			}
			return Scene;
		};

		/**
		 * Updates dynamic scene variables like the trigger element position or the duration.
		 * This method is automatically called in regular intervals from the controller. See {@link ScrollMagic.Controller} option `refreshInterval`.
		 * 
		 * You can call it to minimize lag, for example when you intentionally change the position of the triggerElement.
		 * If you don't it will simply be updated in the next refresh interval of the container, which is usually sufficient.
		 *
		 * @method ScrollMagic.Scene#refresh
		 * @since 1.1.0
		 * @example
		 * scene = new ScrollMagic.Scene({triggerElement: "#trigger"});
		 * 
		 * // change the position of the trigger
		 * $("#trigger").css("top", 500);
		 * // immediately let the scene know of this change
		 * scene.refresh();
		 *
		 * @fires {@link Scene.shift}, if the trigger element position or the duration changed
		 * @fires {@link Scene.change}, if the duration changed
		 *
		 * @returns {Scene} Parent object for chaining.
		 */
		this.refresh = function () {
			updateDuration();
			updateTriggerElementPosition();
			// update trigger element position
			return Scene;
		};

		/**
		 * **Get** or **Set** the scene's progress.  
		 * Usually it shouldn't be necessary to use this as a setter, as it is set automatically by scene.update().  
		 * The order in which the events are fired depends on the duration of the scene:
		 *  1. Scenes with `duration == 0`:  
		 *  Scenes that have no duration by definition have no ending. Thus the `end` event will never be fired.  
		 *  When the trigger position of the scene is passed the events are always fired in this order:  
		 *  `enter`, `start`, `progress` when scrolling forward  
		 *  and  
		 *  `progress`, `start`, `leave` when scrolling in reverse
		 *  2. Scenes with `duration > 0`:  
		 *  Scenes with a set duration have a defined start and end point.  
		 *  When scrolling past the start position of the scene it will fire these events in this order:  
		 *  `enter`, `start`, `progress`  
		 *  When continuing to scroll and passing the end point it will fire these events:  
		 *  `progress`, `end`, `leave`  
		 *  When reversing through the end point these events are fired:  
		 *  `enter`, `end`, `progress`  
		 *  And when continuing to scroll past the start position in reverse it will fire:  
		 *  `progress`, `start`, `leave`  
		 *  In between start and end the `progress` event will be called constantly, whenever the progress changes.
		 * 
		 * In short:  
		 * `enter` events will always trigger **before** the progress update and `leave` envents will trigger **after** the progress update.  
		 * `start` and `end` will always trigger at their respective position.
		 * 
		 * Please review the event descriptions for details on the events and the event object that is passed to the callback.
		 * 
		 * @method ScrollMagic.Scene#progress
		 * @example
		 * // get the current scene progress
		 * var progress = scene.progress();
		 *
		 * // set new scene progress
		 * scene.progress(0.3);
		 *
		 * @fires {@link Scene.enter}, when used as setter
		 * @fires {@link Scene.start}, when used as setter
		 * @fires {@link Scene.progress}, when used as setter
		 * @fires {@link Scene.end}, when used as setter
		 * @fires {@link Scene.leave}, when used as setter
		 *
		 * @param {number} [progress] - The new progress value of the scene `[0-1]`.
		 * @returns {number} `get` -  Current scene progress.
		 * @returns {Scene} `set` -  Parent object for chaining.
		 */
		this.progress = function (progress) {
			if (!arguments.length) { // get
				return _progress;
			} else { // set
				var
				doUpdate = false,
					oldState = _state,
					scrollDirection = _controller ? _controller.info("scrollDirection") : 'PAUSED',
					reverseOrForward = _options.reverse || progress >= _progress;
				if (_options.duration === 0) {
					// zero duration scenes
					doUpdate = _progress != progress;
					_progress = progress < 1 && reverseOrForward ? 0 : 1;
					_state = _progress === 0 ? SCENE_STATE_BEFORE : SCENE_STATE_DURING;
				} else {
					// scenes with start and end
					if (progress < 0 && _state !== SCENE_STATE_BEFORE && reverseOrForward) {
						// go back to initial state
						_progress = 0;
						_state = SCENE_STATE_BEFORE;
						doUpdate = true;
					} else if (progress >= 0 && progress < 1 && reverseOrForward) {
						_progress = progress;
						_state = SCENE_STATE_DURING;
						doUpdate = true;
					} else if (progress >= 1 && _state !== SCENE_STATE_AFTER) {
						_progress = 1;
						_state = SCENE_STATE_AFTER;
						doUpdate = true;
					} else if (_state === SCENE_STATE_DURING && !reverseOrForward) {
						updatePinState(); // in case we scrolled backwards mid-scene and reverse is disabled => update the pin position, so it doesn't move back as well.
					}
				}
				if (doUpdate) {
					// fire events
					var
					eventVars = {
						progress: _progress,
						state: _state,
						scrollDirection: scrollDirection
					},
						stateChanged = _state != oldState;

					var trigger = function (eventName) { // tmp helper to simplify code
						Scene.trigger(eventName, eventVars);
					};

					if (stateChanged) { // enter events
						if (oldState !== SCENE_STATE_DURING) {
							trigger("enter");
							trigger(oldState === SCENE_STATE_BEFORE ? "start" : "end");
						}
					}
					trigger("progress");
					if (stateChanged) { // leave events
						if (_state !== SCENE_STATE_DURING) {
							trigger(_state === SCENE_STATE_BEFORE ? "start" : "end");
							trigger("leave");
						}
					}
				}

				return Scene;
			}
		};


		/**
		 * Update the start and end scrollOffset of the container.
		 * The positions reflect what the controller's scroll position will be at the start and end respectively.
		 * Is called, when:
		 *   - Scene event "change" is called with: offset, triggerHook, duration 
		 *   - scroll container event "resize" is called
		 *   - the position of the triggerElement changes
		 *   - the controller changes -> addTo()
		 * @private
		 */
		var updateScrollOffset = function () {
			_scrollOffset = {
				start: _triggerPos + _options.offset
			};
			if (_controller && _options.triggerElement) {
				// take away triggerHook portion to get relative to top
				_scrollOffset.start -= _controller.info("size") * _options.triggerHook;
			}
			_scrollOffset.end = _scrollOffset.start + _options.duration;
		};

		/**
		 * Updates the duration if set to a dynamic function.
		 * This method is called when the scene is added to a controller and in regular intervals from the controller through scene.refresh().
		 * 
		 * @fires {@link Scene.change}, if the duration changed
		 * @fires {@link Scene.shift}, if the duration changed
		 *
		 * @param {boolean} [suppressEvents=false] - If true the shift event will be suppressed.
		 * @private
		 */
		var updateDuration = function (suppressEvents) {
			// update duration
			if (_durationUpdateMethod) {
				var varname = "duration";
				if (changeOption(varname, _durationUpdateMethod.call(Scene)) && !suppressEvents) { // set
					Scene.trigger("change", {
						what: varname,
						newval: _options[varname]
					});
					Scene.trigger("shift", {
						reason: varname
					});
				}
			}
		};

		/**
		 * Updates the position of the triggerElement, if present.
		 * This method is called ...
		 *  - ... when the triggerElement is changed
		 *  - ... when the scene is added to a (new) controller
		 *  - ... in regular intervals from the controller through scene.refresh().
		 * 
		 * @fires {@link Scene.shift}, if the position changed
		 *
		 * @param {boolean} [suppressEvents=false] - If true the shift event will be suppressed.
		 * @private
		 */
		var updateTriggerElementPosition = function (suppressEvents) {
			var
			elementPos = 0,
				telem = _options.triggerElement;
			if (_controller && telem) {
				var
				controllerInfo = _controller.info(),
					containerOffset = _util.get.offset(controllerInfo.container),
					// container position is needed because element offset is returned in relation to document, not in relation to container.
					param = controllerInfo.vertical ? "top" : "left"; // which param is of interest ?
				// if parent is spacer, use spacer position instead so correct start position is returned for pinned elements.
				while (telem.parentNode.hasAttribute(PIN_SPACER_ATTRIBUTE)) {
					telem = telem.parentNode;
				}

				var elementOffset = _util.get.offset(telem);

				if (!controllerInfo.isDocument) { // container is not the document root, so substract scroll Position to get correct trigger element position relative to scrollcontent
					containerOffset[param] -= _controller.scrollPos();
				}

				elementPos = elementOffset[param] - containerOffset[param];
			}
			var changed = elementPos != _triggerPos;
			_triggerPos = elementPos;
			if (changed && !suppressEvents) {
				Scene.trigger("shift", {
					reason: "triggerElementPosition"
				});
			}
		};

		/**
		 * Trigger a shift event, when the container is resized and the triggerHook is > 1.
		 * @private
		 */
		var onContainerResize = function (e) {
			if (_options.triggerHook > 0) {
				Scene.trigger("shift", {
					reason: "containerResize"
				});
			}
		};

		var _validate = _util.extend(SCENE_OPTIONS.validate, {
			// validation for duration handled internally for reference to private var _durationMethod
			duration: function (val) {
				if (_util.type.String(val) && val.match(/^(\.|\d)*\d+%$/)) {
					// percentage value
					var perc = parseFloat(val) / 100;
					val = function () {
						return _controller ? _controller.info("size") * perc : 0;
					};
				}
				if (_util.type.Function(val)) {
					// function
					_durationUpdateMethod = val;
					try {
						val = parseFloat(_durationUpdateMethod());
					} catch (e) {
						val = -1; // will cause error below
					}
				}
				// val has to be float
				val = parseFloat(val);
				if (!_util.type.Number(val) || val < 0) {
					if (_durationUpdateMethod) {
						_durationUpdateMethod = undefined;
						throw ["Invalid return value of supplied function for option \"duration\":", val];
					} else {
						throw ["Invalid value for option \"duration\":", val];
					}
				}
				return val;
			}
		});

		/**
		 * Checks the validity of a specific or all options and reset to default if neccessary.
		 * @private
		 */
		var validateOption = function (check) {
			check = arguments.length ? [check] : Object.keys(_validate);
			check.forEach(function (optionName, key) {
				var value;
				if (_validate[optionName]) { // there is a validation method for this option
					try { // validate value
						value = _validate[optionName](_options[optionName]);
					} catch (e) { // validation failed -> reset to default
						value = DEFAULT_OPTIONS[optionName];
						var logMSG = _util.type.String(e) ? [e] : e;
						if (_util.type.Array(logMSG)) {
							logMSG[0] = "ERROR: " + logMSG[0];
							logMSG.unshift(1); // loglevel 1 for error msg
							log.apply(this, logMSG);
						} else {
							log(1, "ERROR: Problem executing validation callback for option '" + optionName + "':", e.message);
						}
					} finally {
						_options[optionName] = value;
					}
				}
			});
		};

		/**
		 * Helper used by the setter/getters for scene options
		 * @private
		 */
		var changeOption = function (varname, newval) {
			var
			changed = false,
				oldval = _options[varname];
			if (_options[varname] != newval) {
				_options[varname] = newval;
				validateOption(varname); // resets to default if necessary
				changed = oldval != _options[varname];
			}
			return changed;
		};

		// generate getters/setters for all options
		var addSceneOption = function (optionName) {
			if (!Scene[optionName]) {
				Scene[optionName] = function (newVal) {
					if (!arguments.length) { // get
						return _options[optionName];
					} else {
						if (optionName === "duration") { // new duration is set, so any previously set function must be unset
							_durationUpdateMethod = undefined;
						}
						if (changeOption(optionName, newVal)) { // set
							Scene.trigger("change", {
								what: optionName,
								newval: _options[optionName]
							});
							if (SCENE_OPTIONS.shifts.indexOf(optionName) > -1) {
								Scene.trigger("shift", {
									reason: optionName
								});
							}
						}
					}
					return Scene;
				};
			}
		};

		/**
		 * **Get** or **Set** the duration option value.
		 * As a setter it also accepts a function returning a numeric value.  
		 * This is particularly useful for responsive setups.
		 *
		 * The duration is updated using the supplied function every time `Scene.refresh()` is called, which happens periodically from the controller (see ScrollMagic.Controller option `refreshInterval`).  
		 * _**NOTE:** Be aware that it's an easy way to kill performance, if you supply a function that has high CPU demand.  
		 * Even for size and position calculations it is recommended to use a variable to cache the value. (see example)  
		 * This counts double if you use the same function for multiple scenes._
		 *
		 * @method ScrollMagic.Scene#duration
		 * @example
		 * // get the current duration value
		 * var duration = scene.duration();
		 *
		 * // set a new duration
		 * scene.duration(300);
		 *
		 * // use a function to automatically adjust the duration to the window height.
		 * var durationValueCache;
		 * function getDuration () {
		 *   return durationValueCache;
		 * }
		 * function updateDuration (e) {
		 *   durationValueCache = window.innerHeight;
		 * }
		 * $(window).on("resize", updateDuration); // update the duration when the window size changes
		 * $(window).triggerHandler("resize"); // set to initial value
		 * scene.duration(getDuration); // supply duration method
		 *
		 * @fires {@link Scene.change}, when used as setter
		 * @fires {@link Scene.shift}, when used as setter
		 * @param {(number|function)} [newDuration] - The new duration of the scene.
		 * @returns {number} `get` -  Current scene duration.
		 * @returns {Scene} `set` -  Parent object for chaining.
		 */

		/**
		 * **Get** or **Set** the offset option value.
		 * @method ScrollMagic.Scene#offset
		 * @example
		 * // get the current offset
		 * var offset = scene.offset();
		 *
		 * // set a new offset
		 * scene.offset(100);
		 *
		 * @fires {@link Scene.change}, when used as setter
		 * @fires {@link Scene.shift}, when used as setter
		 * @param {number} [newOffset] - The new offset of the scene.
		 * @returns {number} `get` -  Current scene offset.
		 * @returns {Scene} `set` -  Parent object for chaining.
		 */

		/**
		 * **Get** or **Set** the triggerElement option value.
		 * Does **not** fire `Scene.shift`, because changing the trigger Element doesn't necessarily mean the start position changes. This will be determined in `Scene.refresh()`, which is automatically triggered.
		 * @method ScrollMagic.Scene#triggerElement
		 * @example
		 * // get the current triggerElement
		 * var triggerElement = scene.triggerElement();
		 *
		 * // set a new triggerElement using a selector
		 * scene.triggerElement("#trigger");
		 * // set a new triggerElement using a DOM object
		 * scene.triggerElement(document.getElementById("trigger"));
		 *
		 * @fires {@link Scene.change}, when used as setter
		 * @param {(string|object)} [newTriggerElement] - The new trigger element for the scene.
		 * @returns {(string|object)} `get` -  Current triggerElement.
		 * @returns {Scene} `set` -  Parent object for chaining.
		 */

		/**
		 * **Get** or **Set** the triggerHook option value.
		 * @method ScrollMagic.Scene#triggerHook
		 * @example
		 * // get the current triggerHook value
		 * var triggerHook = scene.triggerHook();
		 *
		 * // set a new triggerHook using a string
		 * scene.triggerHook("onLeave");
		 * // set a new triggerHook using a number
		 * scene.triggerHook(0.7);
		 *
		 * @fires {@link Scene.change}, when used as setter
		 * @fires {@link Scene.shift}, when used as setter
		 * @param {(number|string)} [newTriggerHook] - The new triggerHook of the scene. See {@link Scene} parameter description for value options.
		 * @returns {number} `get` -  Current triggerHook (ALWAYS numerical).
		 * @returns {Scene} `set` -  Parent object for chaining.
		 */

		/**
		 * **Get** or **Set** the reverse option value.
		 * @method ScrollMagic.Scene#reverse
		 * @example
		 * // get the current reverse option
		 * var reverse = scene.reverse();
		 *
		 * // set new reverse option
		 * scene.reverse(false);
		 *
		 * @fires {@link Scene.change}, when used as setter
		 * @param {boolean} [newReverse] - The new reverse setting of the scene.
		 * @returns {boolean} `get` -  Current reverse option value.
		 * @returns {Scene} `set` -  Parent object for chaining.
		 */

		/**
		 * **Get** or **Set** the loglevel option value.
		 * @method ScrollMagic.Scene#loglevel
		 * @example
		 * // get the current loglevel
		 * var loglevel = scene.loglevel();
		 *
		 * // set new loglevel
		 * scene.loglevel(3);
		 *
		 * @fires {@link Scene.change}, when used as setter
		 * @param {number} [newLoglevel] - The new loglevel setting of the scene. `[0-3]`
		 * @returns {number} `get` -  Current loglevel.
		 * @returns {Scene} `set` -  Parent object for chaining.
		 */

		/**
		 * **Get** the associated controller.
		 * @method ScrollMagic.Scene#controller
		 * @example
		 * // get the controller of a scene
		 * var controller = scene.controller();
		 *
		 * @returns {ScrollMagic.Controller} Parent controller or `undefined`
		 */
		this.controller = function () {
			return _controller;
		};

		/**
		 * **Get** the current state.
		 * @method ScrollMagic.Scene#state
		 * @example
		 * // get the current state
		 * var state = scene.state();
		 *
		 * @returns {string} `"BEFORE"`, `"DURING"` or `"AFTER"`
		 */
		this.state = function () {
			return _state;
		};

		/**
		 * **Get** the current scroll offset for the start of the scene.  
		 * Mind, that the scrollOffset is related to the size of the container, if `triggerHook` is bigger than `0` (or `"onLeave"`).  
		 * This means, that resizing the container or changing the `triggerHook` will influence the scene's start offset.
		 * @method ScrollMagic.Scene#scrollOffset
		 * @example
		 * // get the current scroll offset for the start and end of the scene.
		 * var start = scene.scrollOffset();
		 * var end = scene.scrollOffset() + scene.duration();
		 * console.log("the scene starts at", start, "and ends at", end);
		 *
		 * @returns {number} The scroll offset (of the container) at which the scene will trigger. Y value for vertical and X value for horizontal scrolls.
		 */
		this.scrollOffset = function () {
			return _scrollOffset.start;
		};

		/**
		 * **Get** the trigger position of the scene (including the value of the `offset` option).  
		 * @method ScrollMagic.Scene#triggerPosition
		 * @example
		 * // get the scene's trigger position
		 * var triggerPosition = scene.triggerPosition();
		 *
		 * @returns {number} Start position of the scene. Top position value for vertical and left position value for horizontal scrolls.
		 */
		this.triggerPosition = function () {
			var pos = _options.offset; // the offset is the basis
			if (_controller) {
				// get the trigger position
				if (_options.triggerElement) {
					// Element as trigger
					pos += _triggerPos;
				} else {
					// return the height of the triggerHook to start at the beginning
					pos += _controller.info("size") * Scene.triggerHook();
				}
			}
			return pos;
		};

		var
		_pin, _pinOptions;

		Scene.on("shift.internal", function (e) {
			var durationChanged = e.reason === "duration";
			if ((_state === SCENE_STATE_AFTER && durationChanged) || (_state === SCENE_STATE_DURING && _options.duration === 0)) {
				// if [duration changed after a scene (inside scene progress updates pin position)] or [duration is 0, we are in pin phase and some other value changed].
				updatePinState();
			}
			if (durationChanged) {
				updatePinDimensions();
			}
		}).on("progress.internal", function (e) {
			updatePinState();
		}).on("add.internal", function (e) {
			updatePinDimensions();
		}).on("destroy.internal", function (e) {
			Scene.removePin(e.reset);
		});
		/**
		 * Update the pin state.
		 * @private
		 */
		var updatePinState = function (forceUnpin) {
			if (_pin && _controller) {
				var
				containerInfo = _controller.info(),
					pinTarget = _pinOptions.spacer.firstChild; // may be pin element or another spacer, if cascading pins
				if (!forceUnpin && _state === SCENE_STATE_DURING) { // during scene or if duration is 0 and we are past the trigger
					// pinned state
					if (_util.css(pinTarget, "position") != "fixed") {
						// change state before updating pin spacer (position changes due to fixed collapsing might occur.)
						_util.css(pinTarget, {
							"position": "fixed"
						});
						// update pin spacer
						updatePinDimensions();
					}

					var
					fixedPos = _util.get.offset(_pinOptions.spacer, true),
						// get viewport position of spacer
						scrollDistance = _options.reverse || _options.duration === 0 ? containerInfo.scrollPos - _scrollOffset.start // quicker
						: Math.round(_progress * _options.duration * 10) / 10; // if no reverse and during pin the position needs to be recalculated using the progress
					// add scrollDistance
					fixedPos[containerInfo.vertical ? "top" : "left"] += scrollDistance;

					// set new values
					_util.css(_pinOptions.spacer.firstChild, {
						top: fixedPos.top,
						left: fixedPos.left
					});
				} else {
					// unpinned state
					var
					newCSS = {
						position: _pinOptions.inFlow ? "relative" : "absolute",
						top: 0,
						left: 0
					},
						change = _util.css(pinTarget, "position") != newCSS.position;

					if (!_pinOptions.pushFollowers) {
						newCSS[containerInfo.vertical ? "top" : "left"] = _options.duration * _progress;
					} else if (_options.duration > 0) { // only concerns scenes with duration
						if (_state === SCENE_STATE_AFTER && parseFloat(_util.css(_pinOptions.spacer, "padding-top")) === 0) {
							change = true; // if in after state but havent updated spacer yet (jumped past pin)
						} else if (_state === SCENE_STATE_BEFORE && parseFloat(_util.css(_pinOptions.spacer, "padding-bottom")) === 0) { // before
							change = true; // jumped past fixed state upward direction
						}
					}
					// set new values
					_util.css(pinTarget, newCSS);
					if (change) {
						// update pin spacer if state changed
						updatePinDimensions();
					}
				}
			}
		};

		/**
		 * Update the pin spacer and/or element size.
		 * The size of the spacer needs to be updated whenever the duration of the scene changes, if it is to push down following elements.
		 * @private
		 */
		var updatePinDimensions = function () {
			if (_pin && _controller && _pinOptions.inFlow) { // no spacerresize, if original position is absolute
				var
				after = (_state === SCENE_STATE_AFTER),
					before = (_state === SCENE_STATE_BEFORE),
					during = (_state === SCENE_STATE_DURING),
					vertical = _controller.info("vertical"),
					pinTarget = _pinOptions.spacer.firstChild,
					// usually the pined element but can also be another spacer (cascaded pins)
					marginCollapse = _util.isMarginCollapseType(_util.css(_pinOptions.spacer, "display")),
					css = {};

				// set new size
				// if relsize: spacer -> pin | else: pin -> spacer
				if (_pinOptions.relSize.width || _pinOptions.relSize.autoFullWidth) {
					if (during) {
						_util.css(_pin, {
							"width": _util.get.width(_pinOptions.spacer)
						});
					} else {
						_util.css(_pin, {
							"width": "100%"
						});
					}
				} else {
					// minwidth is needed for cascaded pins.
					css["min-width"] = _util.get.width(vertical ? _pin : pinTarget, true, true);
					css.width = during ? css["min-width"] : "auto";
				}
				if (_pinOptions.relSize.height) {
					if (during) {
						// the only padding the spacer should ever include is the duration (if pushFollowers = true), so we need to substract that.
						_util.css(_pin, {
							"height": _util.get.height(_pinOptions.spacer) - (_pinOptions.pushFollowers ? _options.duration : 0)
						});
					} else {
						_util.css(_pin, {
							"height": "100%"
						});
					}
				} else {
					// margin is only included if it's a cascaded pin to resolve an IE9 bug
					css["min-height"] = _util.get.height(vertical ? pinTarget : _pin, true, !marginCollapse); // needed for cascading pins
					css.height = during ? css["min-height"] : "auto";
				}

				// add space for duration if pushFollowers is true
				if (_pinOptions.pushFollowers) {
					css["padding" + (vertical ? "Top" : "Left")] = _options.duration * _progress;
					css["padding" + (vertical ? "Bottom" : "Right")] = _options.duration * (1 - _progress);
				}
				_util.css(_pinOptions.spacer, css);
			}
		};

		/**
		 * Updates the Pin state (in certain scenarios)
		 * If the controller container is not the document and we are mid-pin-phase scrolling or resizing the main document can result to wrong pin positions.
		 * So this function is called on resize and scroll of the document.
		 * @private
		 */
		var updatePinInContainer = function () {
			if (_controller && _pin && _state === SCENE_STATE_DURING && !_controller.info("isDocument")) {
				updatePinState();
			}
		};

		/**
		 * Updates the Pin spacer size state (in certain scenarios)
		 * If container is resized during pin and relatively sized the size of the pin might need to be updated...
		 * So this function is called on resize of the container.
		 * @private
		 */
		var updateRelativePinSpacer = function () {
			if (_controller && _pin && // well, duh
			_state === SCENE_STATE_DURING && // element in pinned state?
			( // is width or height relatively sized, but not in relation to body? then we need to recalc.
			((_pinOptions.relSize.width || _pinOptions.relSize.autoFullWidth) && _util.get.width(window) != _util.get.width(_pinOptions.spacer.parentNode)) || (_pinOptions.relSize.height && _util.get.height(window) != _util.get.height(_pinOptions.spacer.parentNode)))) {
				updatePinDimensions();
			}
		};

		/**
		 * Is called, when the mousewhel is used while over a pinned element inside a div container.
		 * If the scene is in fixed state scroll events would be counted towards the body. This forwards the event to the scroll container.
		 * @private
		 */
		var onMousewheelOverPin = function (e) {
			if (_controller && _pin && _state === SCENE_STATE_DURING && !_controller.info("isDocument")) { // in pin state
				e.preventDefault();
				_controller._setScrollPos(_controller.info("scrollPos") - ((e.wheelDelta || e[_controller.info("vertical") ? "wheelDeltaY" : "wheelDeltaX"]) / 3 || -e.detail * 30));
			}
		};

		/**
		 * Pin an element for the duration of the tween.  
		 * If the scene duration is 0 the element will only be unpinned, if the user scrolls back past the start position.  
		 * Make sure only one pin is applied to an element at the same time.
		 * An element can be pinned multiple times, but only successively.
		 * _**NOTE:** The option `pushFollowers` has no effect, when the scene duration is 0._
		 * @method ScrollMagic.Scene#setPin
		 * @example
		 * // pin element and push all following elements down by the amount of the pin duration.
		 * scene.setPin("#pin");
		 *
		 * // pin element and keeping all following elements in their place. The pinned element will move past them.
		 * scene.setPin("#pin", {pushFollowers: false});
		 *
		 * @param {(string|object)} element - A Selector targeting an element or a DOM object that is supposed to be pinned.
		 * @param {object} [settings] - settings for the pin
		 * @param {boolean} [settings.pushFollowers=true] - If `true` following elements will be "pushed" down for the duration of the pin, if `false` the pinned element will just scroll past them.  
		 Ignored, when duration is `0`.
		 * @param {string} [settings.spacerClass="scrollmagic-pin-spacer"] - Classname of the pin spacer element, which is used to replace the element.
		 *
		 * @returns {Scene} Parent object for chaining.
		 */
		this.setPin = function (element, settings) {
			var
			defaultSettings = {
				pushFollowers: true,
				spacerClass: "scrollmagic-pin-spacer"
			};
			settings = _util.extend({}, defaultSettings, settings);

			// validate Element
			element = _util.get.elements(element)[0];
			if (!element) {
				log(1, "ERROR calling method 'setPin()': Invalid pin element supplied.");
				return Scene; // cancel
			} else if (_util.css(element, "position") === "fixed") {
				log(1, "ERROR calling method 'setPin()': Pin does not work with elements that are positioned 'fixed'.");
				return Scene; // cancel
			}

			if (_pin) { // preexisting pin?
				if (_pin === element) {
					// same pin we already have -> do nothing
					return Scene; // cancel
				} else {
					// kill old pin
					Scene.removePin();
				}

			}
			_pin = element;

			var
			parentDisplay = _pin.parentNode.style.display,
				boundsParams = ["top", "left", "bottom", "right", "margin", "marginLeft", "marginRight", "marginTop", "marginBottom"];

			_pin.parentNode.style.display = 'none'; // hack start to force css to return stylesheet values instead of calculated px values.
			var
			inFlow = _util.css(_pin, "position") != "absolute",
				pinCSS = _util.css(_pin, boundsParams.concat(["display"])),
				sizeCSS = _util.css(_pin, ["width", "height"]);
			_pin.parentNode.style.display = parentDisplay; // hack end.
			if (!inFlow && settings.pushFollowers) {
				log(2, "WARNING: If the pinned element is positioned absolutely pushFollowers will be disabled.");
				settings.pushFollowers = false;
			}
			window.setTimeout(function () { // wait until all finished, because with responsive duration it will only be set after scene is added to controller
				if (_pin && _options.duration === 0 && settings.pushFollowers) {
					log(2, "WARNING: pushFollowers =", true, "has no effect, when scene duration is 0.");
				}
			}, 0);

			// create spacer and insert
			var
			spacer = _pin.parentNode.insertBefore(document.createElement('div'), _pin),
				spacerCSS = _util.extend(pinCSS, {
					position: inFlow ? "relative" : "absolute",
					boxSizing: "content-box",
					mozBoxSizing: "content-box",
					webkitBoxSizing: "content-box"
				});

			if (!inFlow) { // copy size if positioned absolutely, to work for bottom/right positioned elements.
				_util.extend(spacerCSS, _util.css(_pin, ["width", "height"]));
			}

			_util.css(spacer, spacerCSS);
			spacer.setAttribute(PIN_SPACER_ATTRIBUTE, "");
			_util.addClass(spacer, settings.spacerClass);

			// set the pin Options
			_pinOptions = {
				spacer: spacer,
				relSize: { // save if size is defined using % values. if so, handle spacer resize differently...
					width: sizeCSS.width.slice(-1) === "%",
					height: sizeCSS.height.slice(-1) === "%",
					autoFullWidth: sizeCSS.width === "auto" && inFlow && _util.isMarginCollapseType(pinCSS.display)
				},
				pushFollowers: settings.pushFollowers,
				inFlow: inFlow,
				// stores if the element takes up space in the document flow
			};

			if (!_pin.___origStyle) {
				_pin.___origStyle = {};
				var
				pinInlineCSS = _pin.style,
					copyStyles = boundsParams.concat(["width", "height", "position", "boxSizing", "mozBoxSizing", "webkitBoxSizing"]);
				copyStyles.forEach(function (val) {
					_pin.___origStyle[val] = pinInlineCSS[val] || "";
				});
			}

			// if relative size, transfer it to spacer and make pin calculate it...
			if (_pinOptions.relSize.width) {
				_util.css(spacer, {
					width: sizeCSS.width
				});
			}
			if (_pinOptions.relSize.height) {
				_util.css(spacer, {
					height: sizeCSS.height
				});
			}

			// now place the pin element inside the spacer	
			spacer.appendChild(_pin);
			// and set new css
			_util.css(_pin, {
				position: inFlow ? "relative" : "absolute",
				margin: "auto",
				top: "auto",
				left: "auto",
				bottom: "auto",
				right: "auto"
			});

			if (_pinOptions.relSize.width || _pinOptions.relSize.autoFullWidth) {
				_util.css(_pin, {
					boxSizing: "border-box",
					mozBoxSizing: "border-box",
					webkitBoxSizing: "border-box"
				});
			}

			// add listener to document to update pin position in case controller is not the document.
			window.addEventListener('scroll', updatePinInContainer);
			window.addEventListener('resize', updatePinInContainer);
			window.addEventListener('resize', updateRelativePinSpacer);
			// add mousewheel listener to catch scrolls over fixed elements
			_pin.addEventListener("mousewheel", onMousewheelOverPin);
			_pin.addEventListener("DOMMouseScroll", onMousewheelOverPin);

			log(3, "added pin");

			// finally update the pin to init
			updatePinState();

			return Scene;
		};

		/**
		 * Remove the pin from the scene.
		 * @method ScrollMagic.Scene#removePin
		 * @example
		 * // remove the pin from the scene without resetting it (the spacer is not removed)
		 * scene.removePin();
		 *
		 * // remove the pin from the scene and reset the pin element to its initial position (spacer is removed)
		 * scene.removePin(true);
		 *
		 * @param {boolean} [reset=false] - If `false` the spacer will not be removed and the element's position will not be reset.
		 * @returns {Scene} Parent object for chaining.
		 */
		this.removePin = function (reset) {
			if (_pin) {
				if (_state === SCENE_STATE_DURING) {
					updatePinState(true); // force unpin at position
				}
				if (reset || !_controller) { // if there's no controller no progress was made anyway...
					var pinTarget = _pinOptions.spacer.firstChild; // usually the pin element, but may be another spacer (cascaded pins)...
					if (pinTarget.hasAttribute(PIN_SPACER_ATTRIBUTE)) { // copy margins to child spacer
						var
						style = _pinOptions.spacer.style,
							values = ["margin", "marginLeft", "marginRight", "marginTop", "marginBottom"];
						margins = {};
						values.forEach(function (val) {
							margins[val] = style[val] || "";
						});
						_util.css(pinTarget, margins);
					}
					_pinOptions.spacer.parentNode.insertBefore(pinTarget, _pinOptions.spacer);
					_pinOptions.spacer.parentNode.removeChild(_pinOptions.spacer);
					if (!_pin.parentNode.hasAttribute(PIN_SPACER_ATTRIBUTE)) { // if it's the last pin for this element -> restore inline styles
						// TODO: only correctly set for first pin (when cascading) - how to fix?
						_util.css(_pin, _pin.___origStyle);
						delete _pin.___origStyle;
					}
				}
				window.removeEventListener('scroll', updatePinInContainer);
				window.removeEventListener('resize', updatePinInContainer);
				window.removeEventListener('resize', updateRelativePinSpacer);
				_pin.removeEventListener("mousewheel", onMousewheelOverPin);
				_pin.removeEventListener("DOMMouseScroll", onMousewheelOverPin);
				_pin = undefined;
				log(3, "removed pin (reset: " + (reset ? "true" : "false") + ")");
			}
			return Scene;
		};


		var
		_cssClasses, _cssClassElems = [];

		Scene.on("destroy.internal", function (e) {
			Scene.removeClassToggle(e.reset);
		});
		/**
		 * Define a css class modification while the scene is active.  
		 * When the scene triggers the classes will be added to the supplied element and removed, when the scene is over.
		 * If the scene duration is 0 the classes will only be removed if the user scrolls back past the start position.
		 * @method ScrollMagic.Scene#setClassToggle
		 * @example
		 * // add the class 'myclass' to the element with the id 'my-elem' for the duration of the scene
		 * scene.setClassToggle("#my-elem", "myclass");
		 *
		 * // add multiple classes to multiple elements defined by the selector '.classChange'
		 * scene.setClassToggle(".classChange", "class1 class2 class3");
		 *
		 * @param {(string|object)} element - A Selector targeting one or more elements or a DOM object that is supposed to be modified.
		 * @param {string} classes - One or more Classnames (separated by space) that should be added to the element during the scene.
		 *
		 * @returns {Scene} Parent object for chaining.
		 */
		this.setClassToggle = function (element, classes) {
			var elems = _util.get.elements(element);
			if (elems.length === 0 || !_util.type.String(classes)) {
				log(1, "ERROR calling method 'setClassToggle()': Invalid " + (elems.length === 0 ? "element" : "classes") + " supplied.");
				return Scene;
			}
			if (_cssClassElems.length > 0) {
				// remove old ones
				Scene.removeClassToggle();
			}
			_cssClasses = classes;
			_cssClassElems = elems;
			Scene.on("enter.internal_class leave.internal_class", function (e) {
				var toggle = e.type === "enter" ? _util.addClass : _util.removeClass;
				_cssClassElems.forEach(function (elem, key) {
					toggle(elem, _cssClasses);
				});
			});
			return Scene;
		};

		/**
		 * Remove the class binding from the scene.
		 * @method ScrollMagic.Scene#removeClassToggle
		 * @example
		 * // remove class binding from the scene without reset
		 * scene.removeClassToggle();
		 *
		 * // remove class binding and remove the changes it caused
		 * scene.removeClassToggle(true);
		 *
		 * @param {boolean} [reset=false] - If `false` and the classes are currently active, they will remain on the element. If `true` they will be removed.
		 * @returns {Scene} Parent object for chaining.
		 */
		this.removeClassToggle = function (reset) {
			if (reset) {
				_cssClassElems.forEach(function (elem, key) {
					_util.removeClass(elem, _cssClasses);
				});
			}
			Scene.off("start.internal_class end.internal_class");
			_cssClasses = undefined;
			_cssClassElems = [];
			return Scene;
		};

		// INIT
		construct();
		return Scene;
	};

	// store pagewide scene options
	var SCENE_OPTIONS = {
		defaults: {
			duration: 0,
			offset: 0,
			triggerElement: undefined,
			triggerHook: 0.5,
			reverse: true,
			loglevel: 2
		},
		validate: {
			offset: function (val) {
				val = parseFloat(val);
				if (!_util.type.Number(val)) {
					throw ["Invalid value for option \"offset\":", val];
				}
				return val;
			},
			triggerElement: function (val) {
				val = val || undefined;
				if (val) {
					var elem = _util.get.elements(val)[0];
					if (elem) {
						val = elem;
					} else {
						throw ["Element defined in option \"triggerElement\" was not found:", val];
					}
				}
				return val;
			},
			triggerHook: function (val) {
				var translate = {
					"onCenter": 0.5,
					"onEnter": 1,
					"onLeave": 0
				};
				if (_util.type.Number(val)) {
					val = Math.max(0, Math.min(parseFloat(val), 1)); //  make sure its betweeen 0 and 1
				} else if (val in translate) {
					val = translate[val];
				} else {
					throw ["Invalid value for option \"triggerHook\": ", val];
				}
				return val;
			},
			reverse: function (val) {
				return !!val; // force boolean
			},
			loglevel: function (val) {
				val = parseInt(val);
				if (!_util.type.Number(val) || val < 0 || val > 3) {
					throw ["Invalid value for option \"loglevel\":", val];
				}
				return val;
			}
		},
		// holder for  validation methods. duration validation is handled in 'getters-setters.js'
		shifts: ["duration", "offset", "triggerHook"],
		// list of options that trigger a `shift` event
	};
/*
 * method used to add an option to ScrollMagic Scenes.
 * TODO: DOC (private for dev)
 */
	ScrollMagic.Scene.addOption = function (name, defaultValue, validationCallback, shifts) {
		if (!(name in SCENE_OPTIONS.defaults)) {
			SCENE_OPTIONS.defaults[name] = defaultValue;
			SCENE_OPTIONS.validate[name] = validationCallback;
			if (shifts) {
				SCENE_OPTIONS.shifts.push(name);
			}
		} else {
			ScrollMagic._util.log(1, "[static] ScrollMagic.Scene -> Cannot add Scene option '" + name + "', because it already exists.");
		}
	};
	// instance extension function for plugins
	// TODO: DOC (private for dev)
	ScrollMagic.Scene.extend = function (extension) {
		var oldClass = this;
		ScrollMagic.Scene = function () {
			oldClass.apply(this, arguments);
			this.$super = _util.extend({}, this); // copy parent state
			return extension.apply(this, arguments) || this;
		};
		_util.extend(ScrollMagic.Scene, oldClass); // copy properties
		ScrollMagic.Scene.prototype = oldClass.prototype; // copy prototype
		ScrollMagic.Scene.prototype.constructor = ScrollMagic.Scene; // restore constructor
	};


	/**
	 * TODO: DOCS (private for dev)
	 * @class
	 * @private
	 */

	ScrollMagic.Event = function (type, namespace, target, vars) {
		vars = vars || {};
		for (var key in vars) {
			this[key] = vars[key];
		}
		this.type = type;
		this.target = this.currentTarget = target;
		this.namespace = namespace || '';
		this.timeStamp = this.timestamp = Date.now();
		return this;
	};

/*
 * TODO: DOCS (private for dev)
 */

	var _util = ScrollMagic._util = (function (window) {
		var U = {},
			i;

		/**
		 * ------------------------------
		 * internal helpers
		 * ------------------------------
		 */

		// parse float and fall back to 0.
		var floatval = function (number) {
			return parseFloat(number) || 0;
		};
		// get current style IE safe (otherwise IE would return calculated values for 'auto')
		var _getComputedStyle = function (elem) {
			return elem.currentStyle ? elem.currentStyle : window.getComputedStyle(elem);
		};

		// get element dimension (width or height)
		var _dimension = function (which, elem, outer, includeMargin) {
			elem = (elem === document) ? window : elem;
			if (elem === window) {
				includeMargin = false;
			} else if (!_type.DomElement(elem)) {
				return 0;
			}
			which = which.charAt(0).toUpperCase() + which.substr(1).toLowerCase();
			var dimension = (outer ? elem['offset' + which] || elem['outer' + which] : elem['client' + which] || elem['inner' + which]) || 0;
			if (outer && includeMargin) {
				var style = _getComputedStyle(elem);
				dimension += which === 'Height' ? floatval(style.marginTop) + floatval(style.marginBottom) : floatval(style.marginLeft) + floatval(style.marginRight);
			}
			return dimension;
		};
		// converts 'margin-top' into 'marginTop'
		var _camelCase = function (str) {
			return str.replace(/^[^a-z]+([a-z])/g, '$1').replace(/-([a-z])/g, function (g) {
				return g[1].toUpperCase();
			});
		};

		/**
		 * ------------------------------
		 * external helpers
		 * ------------------------------
		 */

		// extend obj – same as jQuery.extend({}, objA, objB)
		U.extend = function (obj) {
			obj = obj || {};
			for (i = 1; i < arguments.length; i++) {
				if (!arguments[i]) {
					continue;
				}
				for (var key in arguments[i]) {
					if (arguments[i].hasOwnProperty(key)) {
						obj[key] = arguments[i][key];
					}
				}
			}
			return obj;
		};

		// check if a css display type results in margin-collapse or not
		U.isMarginCollapseType = function (str) {
			return ["block", "flex", "list-item", "table", "-webkit-box"].indexOf(str) > -1;
		};

		// implementation of requestAnimationFrame
		// based on https://gist.github.com/paulirish/1579671
		var
		lastTime = 0,
			vendors = ['ms', 'moz', 'webkit', 'o'];
		var _requestAnimationFrame = window.requestAnimationFrame;
		var _cancelAnimationFrame = window.cancelAnimationFrame;
		// try vendor prefixes if the above doesn't work
		for (i = 0; !_requestAnimationFrame && i < vendors.length; ++i) {
			_requestAnimationFrame = window[vendors[i] + 'RequestAnimationFrame'];
			_cancelAnimationFrame = window[vendors[i] + 'CancelAnimationFrame'] || window[vendors[i] + 'CancelRequestAnimationFrame'];
		}

		// fallbacks
		if (!_requestAnimationFrame) {
			_requestAnimationFrame = function (callback) {
				var
				currTime = new Date().getTime(),
					timeToCall = Math.max(0, 16 - (currTime - lastTime)),
					id = window.setTimeout(function () {
						callback(currTime + timeToCall);
					}, timeToCall);
				lastTime = currTime + timeToCall;
				return id;
			};
		}
		if (!_cancelAnimationFrame) {
			_cancelAnimationFrame = function (id) {
				window.clearTimeout(id);
			};
		}
		U.rAF = _requestAnimationFrame.bind(window);
		U.cAF = _cancelAnimationFrame.bind(window);

		var
		loglevels = ["error", "warn", "log"],
			console = window.console || {};

		console.log = console.log ||
		function () {}; // no console log, well - do nothing then...
		// make sure methods for all levels exist.
		for (i = 0; i < loglevels.length; i++) {
			var method = loglevels[i];
			if (!console[method]) {
				console[method] = console.log; // prefer .log over nothing
			}
		}
		U.log = function (loglevel) {
			if (loglevel > loglevels.length || loglevel <= 0) loglevel = loglevels.length;
			var now = new Date(),
				time = ("0" + now.getHours()).slice(-2) + ":" + ("0" + now.getMinutes()).slice(-2) + ":" + ("0" + now.getSeconds()).slice(-2) + ":" + ("00" + now.getMilliseconds()).slice(-3),
				method = loglevels[loglevel - 1],
				args = Array.prototype.splice.call(arguments, 1),
				func = Function.prototype.bind.call(console[method], console);
			args.unshift(time);
			func.apply(console, args);
		};

		/**
		 * ------------------------------
		 * type testing
		 * ------------------------------
		 */

		var _type = U.type = function (v) {
			return Object.prototype.toString.call(v).replace(/^\[object (.+)\]$/, "$1").toLowerCase();
		};
		_type.String = function (v) {
			return _type(v) === 'string';
		};
		_type.Function = function (v) {
			return _type(v) === 'function';
		};
		_type.Array = function (v) {
			return Array.isArray(v);
		};
		_type.Number = function (v) {
			return !_type.Array(v) && (v - parseFloat(v) + 1) >= 0;
		};
		_type.DomElement = function (o) {
			return (
			typeof HTMLElement === "object" ? o instanceof HTMLElement : //DOM2
			o && typeof o === "object" && o !== null && o.nodeType === 1 && typeof o.nodeName === "string");
		};

		/**
		 * ------------------------------
		 * DOM Element info
		 * ------------------------------
		 */
		// always returns a list of matching DOM elements, from a selector, a DOM element or an list of elements or even an array of selectors
		var _get = U.get = {};
		_get.elements = function (selector) {
			var arr = [];
			if (_type.String(selector)) {
				try {
					selector = document.querySelectorAll(selector);
				} catch (e) { // invalid selector
					return arr;
				}
			}
			if (_type(selector) === 'nodelist' || _type.Array(selector)) {
				for (var i = 0, ref = arr.length = selector.length; i < ref; i++) { // list of elements
					var elem = selector[i];
					arr[i] = _type.DomElement(elem) ? elem : _get.elements(elem); // if not an element, try to resolve recursively
				}
			} else if (_type.DomElement(selector) || selector === document || selector === window) {
				arr = [selector]; // only the element
			}
			return arr;
		};
		// get scroll top value
		_get.scrollTop = function (elem) {
			return (elem && typeof elem.scrollTop === 'number') ? elem.scrollTop : window.pageYOffset || 0;
		};
		// get scroll left value
		_get.scrollLeft = function (elem) {
			return (elem && typeof elem.scrollLeft === 'number') ? elem.scrollLeft : window.pageXOffset || 0;
		};
		// get element height
		_get.width = function (elem, outer, includeMargin) {
			return _dimension('width', elem, outer, includeMargin);
		};
		// get element width
		_get.height = function (elem, outer, includeMargin) {
			return _dimension('height', elem, outer, includeMargin);
		};

		// get element position (optionally relative to viewport)
		_get.offset = function (elem, relativeToViewport) {
			var offset = {
				top: 0,
				left: 0
			};
			if (elem && elem.getBoundingClientRect) { // check if available
				var rect = elem.getBoundingClientRect();
				offset.top = rect.top;
				offset.left = rect.left;
				if (!relativeToViewport) { // clientRect is by default relative to viewport...
					offset.top += _get.scrollTop();
					offset.left += _get.scrollLeft();
				}
			}
			return offset;
		};

		/**
		 * ------------------------------
		 * DOM Element manipulation
		 * ------------------------------
		 */

		U.addClass = function (elem, classname) {
			if (classname) {
				if (elem.classList) elem.classList.add(classname);
				else elem.className += ' ' + classname;
			}
		};
		U.removeClass = function (elem, classname) {
			if (classname) {
				if (elem.classList) elem.classList.remove(classname);
				else elem.className = elem.className.replace(new RegExp('(^|\\b)' + classname.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
			}
		};
		// if options is string -> returns css value
		// if options is array -> returns object with css value pairs
		// if options is object -> set new css values
		U.css = function (elem, options) {
			if (_type.String(options)) {
				return _getComputedStyle(elem)[_camelCase(options)];
			} else if (_type.Array(options)) {
				var
				obj = {},
					style = _getComputedStyle(elem);
				options.forEach(function (option, key) {
					obj[option] = style[_camelCase(option)];
				});
				return obj;
			} else {
				for (var option in options) {
					var val = options[option];
					if (val == parseFloat(val)) { // assume pixel for seemingly numerical values
						val += 'px';
					}
					elem.style[_camelCase(option)] = val;
				}
			}
		};

		return U;
	}(window || {}));

	ScrollMagic.Scene.prototype.addIndicators = function () {
		ScrollMagic._util.log(1, '(ScrollMagic.Scene) -> ERROR calling addIndicators() due to missing Plugin \'debug.addIndicators\'. Please make sure to include plugins/debug.addIndicators.js');
		return this;
	}
	ScrollMagic.Scene.prototype.removeIndicators = function () {
		ScrollMagic._util.log(1, '(ScrollMagic.Scene) -> ERROR calling removeIndicators() due to missing Plugin \'debug.addIndicators\'. Please make sure to include plugins/debug.addIndicators.js');
		return this;
	}
	ScrollMagic.Scene.prototype.setTween = function () {
		ScrollMagic._util.log(1, '(ScrollMagic.Scene) -> ERROR calling setTween() due to missing Plugin \'animation.gsap\'. Please make sure to include plugins/animation.gsap.js');
		return this;
	}
	ScrollMagic.Scene.prototype.removeTween = function () {
		ScrollMagic._util.log(1, '(ScrollMagic.Scene) -> ERROR calling removeTween() due to missing Plugin \'animation.gsap\'. Please make sure to include plugins/animation.gsap.js');
		return this;
	}
	ScrollMagic.Scene.prototype.setVelocity = function () {
		ScrollMagic._util.log(1, '(ScrollMagic.Scene) -> ERROR calling setVelocity() due to missing Plugin \'animation.velocity\'. Please make sure to include plugins/animation.velocity.js');
		return this;
	}
	ScrollMagic.Scene.prototype.removeVelocity = function () {
		ScrollMagic._util.log(1, '(ScrollMagic.Scene) -> ERROR calling removeVelocity() due to missing Plugin \'animation.velocity\'. Please make sure to include plugins/animation.velocity.js');
		return this;
	}

	return ScrollMagic;
}));
(function ($) {
    $.fn.customSelect = function(opts) {
        if(opts == undefined){
            opts = {}
        }
        var el = this;
        var settings;
        var container;
        settings = $.extend({
            customArrow: false
        }, opts);
        function getOptionTextFromValue(select, value){
            var option = select.find('option[value="'+value+'"]');
            return option.text();
        }
        // On boucle sur les appels identiques (ne fait qu'un appel si selecteur unique)
        for (var i = 0; i <= el.length - 1; i++) {
            // Construction de la structure
            $(el[i]).wrap('<div class="customSelectContain" />');
            $(el[i]).addClass('silencedSelect');
            container = $(el[i]).parent('.customSelectContain');
            container.append('<div class="customSelect"><span class="customSelectInner"></span></div>');

            // Affichage de la flèche
            if (settings.customArrow) {
                container.find('.customSelect').append('<div class="customArrow"></div>');
            } else {
                container.find('.customSelect').addClass('arrowed');
            }

            // Ecouteur - Mise à jour/Init de la valeur du select
            if($(el[i]).find('option[selected]').length){
                container.find('.customSelectInner').html($(el[i]).find('option[selected]').text());
            }else{
                container.find('.customSelectInner').html($(el[i]).find('option:eq(0)').text());
            }
            $(el[i]).on('change ', function(e) {
                var text = getOptionTextFromValue($(this), $(this).val())
                $(this).parent('.customSelectContain').find('.customSelectInner').html(text);
            }).on('click', function(){
                $(this).parent('.customSelectContain').toggleClass('opened');
            }).on('focus', function(){
                $(this).parent('.customSelectContain').addClass('focused');
            }).on('blur',function(){
                $(this).parent('.customSelectContain').removeClass('focused');
                $(this).parent('.customSelectContain').removeClass('opened');
            });
            
            $(el[i]).on('keyup keypress', function(e) {
                if((e.keyCode >= 48 && e.keyCode <= 90) || (e.keyCode <=40 && e.keyCode >= 37) ){
                    var text = getOptionTextFromValue($(this), $(this).val())
                    $(this).parent('.customSelectContain').find('.customSelectInner').html(text);
                }else if(e.keyCode == 32){
                    if(! $(this).parent('.customSelectContain').hasClass('opened')){
                        $(this).trigger('click');
                    }
                }
            }).on('focus', function(){
                $(this).parent('.customSelectContain').addClass('focused');
            }).on('blur',function(){
                $(this).parent('.customSelectContain').removeClass('focused');
                $(this).parent('.customSelectContain').removeClass('opened');
            });
        };
    }
}(jQuery));

/* French initialisation for the jQuery UI date picker plugin. */
/* Written by Keith Wood (kbwood{at}iinet.com.au),
			  Stéphane Nahmani (sholby@sholby.net),
			  Stéphane Raimbault <stephane.raimbault@gmail.com> */
( function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define( [ "../widgets/datepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.datepicker );
	}
}( function( datepicker ) {

datepicker.regional.fr = {
	closeText: "Fermer",
	prevText: "Précédent",
	nextText: "Suivant",
	currentText: "Aujourd'hui",
	monthNames: [ "janvier", "février", "mars", "avril", "mai", "juin",
		"juillet", "août", "septembre", "octobre", "novembre", "décembre" ],
	monthNamesShort: [ "janv.", "févr.", "mars", "avr.", "mai", "juin",
		"juil.", "août", "sept.", "oct.", "nov.", "déc." ],
	dayNames: [ "dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi" ],
	dayNamesShort: [ "dim.", "lun.", "mar.", "mer.", "jeu.", "ven.", "sam." ],
	dayNamesMin: [ "D","L","M","M","J","V","S" ],
	weekHeader: "Sem.",
	dateFormat: "dd/mm/yy",
	firstDay: 1,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: "" };
datepicker.setDefaults( datepicker.regional.fr );

return datepicker.regional.fr;

} ) );

/*!
 * jQuery Validation Plugin v1.15.0
 *
 * http://jqueryvalidation.org/
 *
 * Copyright (c) 2016 Jörn Zaefferer
 * Released under the MIT license
 */
(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery"], factory );
	} else if (typeof module === "object" && module.exports) {
		module.exports = factory( require( "jquery" ) );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

$.extend( $.fn, {

	// http://jqueryvalidation.org/validate/
	validate: function( options ) {

		// If nothing is selected, return nothing; can't chain anyway
		if ( !this.length ) {
			if ( options && options.debug && window.console ) {
				console.warn( "Nothing selected, can't validate, returning nothing." );
			}
			return;
		}

		// Check if a validator for this form was already created
		var validator = $.data( this[ 0 ], "validator" );
		if ( validator ) {
			return validator;
		}

		// Add novalidate tag if HTML5.
		this.attr( "novalidate", "novalidate" );

		validator = new $.validator( options, this[ 0 ] );
		$.data( this[ 0 ], "validator", validator );

		if ( validator.settings.onsubmit ) {

			this.on( "click.validate", ":submit", function( event ) {
				if ( validator.settings.submitHandler ) {
					validator.submitButton = event.target;
				}

				// Allow suppressing validation by adding a cancel class to the submit button
				if ( $( this ).hasClass( "cancel" ) ) {
					validator.cancelSubmit = true;
				}

				// Allow suppressing validation by adding the html5 formnovalidate attribute to the submit button
				if ( $( this ).attr( "formnovalidate" ) !== undefined ) {
					validator.cancelSubmit = true;
				}
			} );

			// Validate the form on submit
			this.on( "submit.validate", function( event ) {
				if ( validator.settings.debug ) {

					// Prevent form submit to be able to see console output
					event.preventDefault();
				}
				function handle() {
					var hidden, result;
					if ( validator.settings.submitHandler ) {
						if ( validator.submitButton ) {

							// Insert a hidden input as a replacement for the missing submit button
							hidden = $( "<input type='hidden'/>" )
								.attr( "name", validator.submitButton.name )
								.val( $( validator.submitButton ).val() )
								.appendTo( validator.currentForm );
						}
						result = validator.settings.submitHandler.call( validator, validator.currentForm, event );
						if ( validator.submitButton ) {

							// And clean up afterwards; thanks to no-block-scope, hidden can be referenced
							hidden.remove();
						}
						if ( result !== undefined ) {
							return result;
						}
						return false;
					}
					return true;
				}

				// Prevent submit for invalid forms or custom submit handlers
				if ( validator.cancelSubmit ) {
					validator.cancelSubmit = false;
					return handle();
				}
				if ( validator.form() ) {
					if ( validator.pendingRequest ) {
						validator.formSubmitted = true;
						return false;
					}
					return handle();
				} else {
					validator.focusInvalid();
					return false;
				}
			} );
		}

		return validator;
	},

	// http://jqueryvalidation.org/valid/
	valid: function() {
		var valid, validator, errorList;

		if ( $( this[ 0 ] ).is( "form" ) ) {
			valid = this.validate().form();
		} else {
			errorList = [];
			valid = true;
			validator = $( this[ 0 ].form ).validate();
			this.each( function() {
				valid = validator.element( this ) && valid;
				if ( !valid ) {
					errorList = errorList.concat( validator.errorList );
				}
			} );
			validator.errorList = errorList;
		}
		return valid;
	},

	// http://jqueryvalidation.org/rules/
	rules: function( command, argument ) {

		// If nothing is selected, return nothing; can't chain anyway
		if ( !this.length ) {
			return;
		}

		var element = this[ 0 ],
			settings, staticRules, existingRules, data, param, filtered;

		if ( command ) {
			settings = $.data( element.form, "validator" ).settings;
			staticRules = settings.rules;
			existingRules = $.validator.staticRules( element );
			switch ( command ) {
			case "add":
				$.extend( existingRules, $.validator.normalizeRule( argument ) );

				// Remove messages from rules, but allow them to be set separately
				delete existingRules.messages;
				staticRules[ element.name ] = existingRules;
				if ( argument.messages ) {
					settings.messages[ element.name ] = $.extend( settings.messages[ element.name ], argument.messages );
				}
				break;
			case "remove":
				if ( !argument ) {
					delete staticRules[ element.name ];
					return existingRules;
				}
				filtered = {};
				$.each( argument.split( /\s/ ), function( index, method ) {
					filtered[ method ] = existingRules[ method ];
					delete existingRules[ method ];
					if ( method === "required" ) {
						$( element ).removeAttr( "aria-required" );
					}
				} );
				return filtered;
			}
		}

		data = $.validator.normalizeRules(
		$.extend(
			{},
			$.validator.classRules( element ),
			$.validator.attributeRules( element ),
			$.validator.dataRules( element ),
			$.validator.staticRules( element )
		), element );

		// Make sure required is at front
		if ( data.required ) {
			param = data.required;
			delete data.required;
			data = $.extend( { required: param }, data );
			$( element ).attr( "aria-required", "true" );
		}

		// Make sure remote is at back
		if ( data.remote ) {
			param = data.remote;
			delete data.remote;
			data = $.extend( data, { remote: param } );
		}

		return data;
	}
} );

// Custom selectors
$.extend( $.expr[ ":" ], {

	// http://jqueryvalidation.org/blank-selector/
	blank: function( a ) {
		return !$.trim( "" + $( a ).val() );
	},

	// http://jqueryvalidation.org/filled-selector/
	filled: function( a ) {
		var val = $( a ).val();
		return val !== null && !!$.trim( "" + val );
	},

	// http://jqueryvalidation.org/unchecked-selector/
	unchecked: function( a ) {
		return !$( a ).prop( "checked" );
	}
} );

// Constructor for validator
$.validator = function( options, form ) {
	this.settings = $.extend( true, {}, $.validator.defaults, options );
	this.currentForm = form;
	this.init();
};

// http://jqueryvalidation.org/jQuery.validator.format/
$.validator.format = function( source, params ) {
	if ( arguments.length === 1 ) {
		return function() {
			var args = $.makeArray( arguments );
			args.unshift( source );
			return $.validator.format.apply( this, args );
		};
	}
	if ( params === undefined ) {
		return source;
	}
	if ( arguments.length > 2 && params.constructor !== Array  ) {
		params = $.makeArray( arguments ).slice( 1 );
	}
	if ( params.constructor !== Array ) {
		params = [ params ];
	}
	$.each( params, function( i, n ) {
		source = source.replace( new RegExp( "\\{" + i + "\\}", "g" ), function() {
			return n;
		} );
	} );
	return source;
};

$.extend( $.validator, {

	defaults: {
		messages: {},
		groups: {},
		rules: {},
		errorClass: "error",
		pendingClass: "pending",
		validClass: "valid",
		errorElement: "label",
		focusCleanup: false,
		focusInvalid: true,
		errorContainer: $( [] ),
		errorLabelContainer: $( [] ),
		onsubmit: true,
		ignore: ":hidden",
		ignoreTitle: false,
		onfocusin: function( element ) {
			this.lastActive = element;

			// Hide error label and remove error class on focus if enabled
			if ( this.settings.focusCleanup ) {
				if ( this.settings.unhighlight ) {
					this.settings.unhighlight.call( this, element, this.settings.errorClass, this.settings.validClass );
				}
				this.hideThese( this.errorsFor( element ) );
			}
		},
		onfocusout: function( element ) {
			if ( !this.checkable( element ) && ( element.name in this.submitted || !this.optional( element ) ) ) {
				this.element( element );
			}
		},
		onkeyup: function( element, event ) {

			// Avoid revalidate the field when pressing one of the following keys
			// Shift       => 16
			// Ctrl        => 17
			// Alt         => 18
			// Caps lock   => 20
			// End         => 35
			// Home        => 36
			// Left arrow  => 37
			// Up arrow    => 38
			// Right arrow => 39
			// Down arrow  => 40
			// Insert      => 45
			// Num lock    => 144
			// AltGr key   => 225
			var excludedKeys = [
				16, 17, 18, 20, 35, 36, 37,
				38, 39, 40, 45, 144, 225
			];

			if ( event.which === 9 && this.elementValue( element ) === "" || $.inArray( event.keyCode, excludedKeys ) !== -1 ) {
				return;
			} else if ( element.name in this.submitted || element.name in this.invalid ) {
				this.element( element );
			}
		},
		onclick: function( element ) {

			// Click on selects, radiobuttons and checkboxes
			if ( element.name in this.submitted ) {
				this.element( element );

			// Or option elements, check parent select in that case
			} else if ( element.parentNode.name in this.submitted ) {
				this.element( element.parentNode );
			}
		},
		highlight: function( element, errorClass, validClass ) {
			if ( element.type === "radio" ) {
				this.findByName( element.name ).addClass( errorClass ).removeClass( validClass );
			} else {
				$( element ).addClass( errorClass ).removeClass( validClass );
			}
		},
		unhighlight: function( element, errorClass, validClass ) {
			if ( element.type === "radio" ) {
				this.findByName( element.name ).removeClass( errorClass ).addClass( validClass );
			} else {
				$( element ).removeClass( errorClass ).addClass( validClass );
			}
		}
	},

	// http://jqueryvalidation.org/jQuery.validator.setDefaults/
	setDefaults: function( settings ) {
		$.extend( $.validator.defaults, settings );
	},

	messages: {
		required: "This field is required.",
		remote: "Please fix this field.",
		email: "Please enter a valid email address.",
		url: "Please enter a valid URL.",
		date: "Please enter a valid date.",
		dateISO: "Please enter a valid date ( ISO ).",
		number: "Please enter a valid number.",
		digits: "Please enter only digits.",
		equalTo: "Please enter the same value again.",
		maxlength: $.validator.format( "Please enter no more than {0} characters." ),
		minlength: $.validator.format( "Please enter at least {0} characters." ),
		rangelength: $.validator.format( "Please enter a value between {0} and {1} characters long." ),
		range: $.validator.format( "Please enter a value between {0} and {1}." ),
		max: $.validator.format( "Please enter a value less than or equal to {0}." ),
		min: $.validator.format( "Please enter a value greater than or equal to {0}." ),
		step: $.validator.format( "Please enter a multiple of {0}." )
	},

	autoCreateRanges: false,

	prototype: {

		init: function() {
			this.labelContainer = $( this.settings.errorLabelContainer );
			this.errorContext = this.labelContainer.length && this.labelContainer || $( this.currentForm );
			this.containers = $( this.settings.errorContainer ).add( this.settings.errorLabelContainer );
			this.submitted = {};
			this.valueCache = {};
			this.pendingRequest = 0;
			this.pending = {};
			this.invalid = {};
			this.reset();

			var groups = ( this.groups = {} ),
				rules;
			$.each( this.settings.groups, function( key, value ) {
				if ( typeof value === "string" ) {
					value = value.split( /\s/ );
				}
				$.each( value, function( index, name ) {
					groups[ name ] = key;
				} );
			} );
			rules = this.settings.rules;
			$.each( rules, function( key, value ) {
				rules[ key ] = $.validator.normalizeRule( value );
			} );

			function delegate( event ) {
				var validator = $.data( this.form, "validator" ),
					eventType = "on" + event.type.replace( /^validate/, "" ),
					settings = validator.settings;
				if ( settings[ eventType ] && !$( this ).is( settings.ignore ) ) {
					settings[ eventType ].call( validator, this, event );
				}
			}

			$( this.currentForm )
				.on( "focusin.validate focusout.validate keyup.validate",
					":text, [type='password'], [type='file'], select, textarea, [type='number'], [type='search'], " +
					"[type='tel'], [type='url'], [type='email'], [type='datetime'], [type='date'], [type='month'], " +
					"[type='week'], [type='time'], [type='datetime-local'], [type='range'], [type='color'], " +
					"[type='radio'], [type='checkbox'], [contenteditable]", delegate )

				// Support: Chrome, oldIE
				// "select" is provided as event.target when clicking a option
				.on( "click.validate", "select, option, [type='radio'], [type='checkbox']", delegate );

			if ( this.settings.invalidHandler ) {
				$( this.currentForm ).on( "invalid-form.validate", this.settings.invalidHandler );
			}

			// Add aria-required to any Static/Data/Class required fields before first validation
			// Screen readers require this attribute to be present before the initial submission http://www.w3.org/TR/WCAG-TECHS/ARIA2.html
			$( this.currentForm ).find( "[required], [data-rule-required], .required" ).attr( "aria-required", "true" );
		},

		// http://jqueryvalidation.org/Validator.form/
		form: function() {
			this.checkForm();
			$.extend( this.submitted, this.errorMap );
			this.invalid = $.extend( {}, this.errorMap );
			if ( !this.valid() ) {
				$( this.currentForm ).triggerHandler( "invalid-form", [ this ] );
			}
			this.showErrors();
			return this.valid();
		},

		checkForm: function() {
			this.prepareForm();
			for ( var i = 0, elements = ( this.currentElements = this.elements() ); elements[ i ]; i++ ) {
				this.check( elements[ i ] );
			}
			return this.valid();
		},

		// http://jqueryvalidation.org/Validator.element/
		element: function( element ) {
			var cleanElement = this.clean( element ),
				checkElement = this.validationTargetFor( cleanElement ),
				v = this,
				result = true,
				rs, group;

			if ( checkElement === undefined ) {
				delete this.invalid[ cleanElement.name ];
			} else {
				this.prepareElement( checkElement );
				this.currentElements = $( checkElement );

				// If this element is grouped, then validate all group elements already
				// containing a value
				group = this.groups[ checkElement.name ];
				if ( group ) {
					$.each( this.groups, function( name, testgroup ) {
						if ( testgroup === group && name !== checkElement.name ) {
							cleanElement = v.validationTargetFor( v.clean( v.findByName( name ) ) );
							if ( cleanElement && cleanElement.name in v.invalid ) {
								v.currentElements.push( cleanElement );
								result = result && v.check( cleanElement );
							}
						}
					} );
				}

				rs = this.check( checkElement ) !== false;
				result = result && rs;
				if ( rs ) {
					this.invalid[ checkElement.name ] = false;
				} else {
					this.invalid[ checkElement.name ] = true;
				}

				if ( !this.numberOfInvalids() ) {

					// Hide error containers on last error
					this.toHide = this.toHide.add( this.containers );
				}
				this.showErrors();

				// Add aria-invalid status for screen readers
				$( element ).attr( "aria-invalid", !rs );
			}

			return result;
		},

		// http://jqueryvalidation.org/Validator.showErrors/
		showErrors: function( errors ) {
			if ( errors ) {
				var validator = this;

				// Add items to error list and map
				$.extend( this.errorMap, errors );
				this.errorList = $.map( this.errorMap, function( message, name ) {
					return {
						message: message,
						element: validator.findByName( name )[ 0 ]
					};
				} );

				// Remove items from success list
				this.successList = $.grep( this.successList, function( element ) {
					return !( element.name in errors );
				} );
			}
			if ( this.settings.showErrors ) {
				this.settings.showErrors.call( this, this.errorMap, this.errorList );
			} else {
				this.defaultShowErrors();
			}
		},

		// http://jqueryvalidation.org/Validator.resetForm/
		resetForm: function() {
			if ( $.fn.resetForm ) {
				$( this.currentForm ).resetForm();
			}
			this.invalid = {};
			this.submitted = {};
			this.prepareForm();
			this.hideErrors();
			var elements = this.elements()
				.removeData( "previousValue" )
				.removeAttr( "aria-invalid" );

			this.resetElements( elements );
		},

		resetElements: function( elements ) {
			var i;

			if ( this.settings.unhighlight ) {
				for ( i = 0; elements[ i ]; i++ ) {
					this.settings.unhighlight.call( this, elements[ i ],
						this.settings.errorClass, "" );
					this.findByName( elements[ i ].name ).removeClass( this.settings.validClass );
				}
			} else {
				elements
					.removeClass( this.settings.errorClass )
					.removeClass( this.settings.validClass );
			}
		},

		numberOfInvalids: function() {
			return this.objectLength( this.invalid );
		},

		objectLength: function( obj ) {
			/* jshint unused: false */
			var count = 0,
				i;
			for ( i in obj ) {
				if ( obj[ i ] ) {
					count++;
				}
			}
			return count;
		},

		hideErrors: function() {
			this.hideThese( this.toHide );
		},

		hideThese: function( errors ) {
			errors.not( this.containers ).text( "" );
			this.addWrapper( errors ).hide();
		},

		valid: function() {
			return this.size() === 0;
		},

		size: function() {
			return this.errorList.length;
		},

		focusInvalid: function() {
			if ( this.settings.focusInvalid ) {
				try {
					$( this.findLastActive() || this.errorList.length && this.errorList[ 0 ].element || [] )
					.filter( ":visible" )
					.focus()

					// Manually trigger focusin event; without it, focusin handler isn't called, findLastActive won't have anything to find
					.trigger( "focusin" );
				} catch ( e ) {

					// Ignore IE throwing errors when focusing hidden elements
				}
			}
		},

		findLastActive: function() {
			var lastActive = this.lastActive;
			return lastActive && $.grep( this.errorList, function( n ) {
				return n.element.name === lastActive.name;
			} ).length === 1 && lastActive;
		},

		elements: function() {
			var validator = this,
				rulesCache = {};

			// Select all valid inputs inside the form (no submit or reset buttons)
			return $( this.currentForm )
			.find( "input, select, textarea, [contenteditable]" )
			.not( ":submit, :reset, :image, :disabled" )
			.not( this.settings.ignore )
			.filter( function() {
				var name = this.name || $( this ).attr( "name" ); // For contenteditable
				if ( !name && validator.settings.debug && window.console ) {
					console.error( "%o has no name assigned", this );
				}

				// Set form expando on contenteditable
				if ( this.hasAttribute( "contenteditable" ) ) {
					this.form = $( this ).closest( "form" )[ 0 ];
				}

				// Select only the first element for each name, and only those with rules specified
				if ( name in rulesCache || !validator.objectLength( $( this ).rules() ) ) {
					return false;
				}

				rulesCache[ name ] = true;
				return true;
			} );
		},

		clean: function( selector ) {
			return $( selector )[ 0 ];
		},

		errors: function() {
			var errorClass = this.settings.errorClass.split( " " ).join( "." );
			return $( this.settings.errorElement + "." + errorClass, this.errorContext );
		},

		resetInternals: function() {
			this.successList = [];
			this.errorList = [];
			this.errorMap = {};
			this.toShow = $( [] );
			this.toHide = $( [] );
		},

		reset: function() {
			this.resetInternals();
			this.currentElements = $( [] );
		},

		prepareForm: function() {
			this.reset();
			this.toHide = this.errors().add( this.containers );
		},

		prepareElement: function( element ) {
			this.reset();
			this.toHide = this.errorsFor( element );
		},

		elementValue: function( element ) {
			var $element = $( element ),
				type = element.type,
				val, idx;

			if ( type === "radio" || type === "checkbox" ) {
				return this.findByName( element.name ).filter( ":checked" ).val();
			} else if ( type === "number" && typeof element.validity !== "undefined" ) {
				return element.validity.badInput ? "NaN" : $element.val();
			}

			if ( element.hasAttribute( "contenteditable" ) ) {
				val = $element.text();
			} else {
				val = $element.val();
			}

			if ( type === "file" ) {

				// Modern browser (chrome & safari)
				if ( val.substr( 0, 12 ) === "C:\\fakepath\\" ) {
					return val.substr( 12 );
				}

				// Legacy browsers
				// Unix-based path
				idx = val.lastIndexOf( "/" );
				if ( idx >= 0 ) {
					return val.substr( idx + 1 );
				}

				// Windows-based path
				idx = val.lastIndexOf( "\\" );
				if ( idx >= 0 ) {
					return val.substr( idx + 1 );
				}

				// Just the file name
				return val;
			}

			if ( typeof val === "string" ) {
				return val.replace( /\r/g, "" );
			}
			return val;
		},

		check: function( element ) {
			element = this.validationTargetFor( this.clean( element ) );

			var rules = $( element ).rules(),
				rulesCount = $.map( rules, function( n, i ) {
					return i;
				} ).length,
				dependencyMismatch = false,
				val = this.elementValue( element ),
				result, method, rule;

			// If a normalizer is defined for this element, then
			// call it to retreive the changed value instead
			// of using the real one.
			// Note that `this` in the normalizer is `element`.
			if ( typeof rules.normalizer === "function" ) {
				val = rules.normalizer.call( element, val );

				if ( typeof val !== "string" ) {
					throw new TypeError( "The normalizer should return a string value." );
				}

				// Delete the normalizer from rules to avoid treating
				// it as a pre-defined method.
				delete rules.normalizer;
			}

			for ( method in rules ) {
				rule = { method: method, parameters: rules[ method ] };
				try {
					result = $.validator.methods[ method ].call( this, val, element, rule.parameters );

					// If a method indicates that the field is optional and therefore valid,
					// don't mark it as valid when there are no other rules
					if ( result === "dependency-mismatch" && rulesCount === 1 ) {
						dependencyMismatch = true;
						continue;
					}
					dependencyMismatch = false;

					if ( result === "pending" ) {
						this.toHide = this.toHide.not( this.errorsFor( element ) );
						return;
					}

					if ( !result ) {
						this.formatAndAdd( element, rule );
						return false;
					}
				} catch ( e ) {
					if ( this.settings.debug && window.console ) {
						console.log( "Exception occurred when checking element " + element.id + ", check the '" + rule.method + "' method.", e );
					}
					if ( e instanceof TypeError ) {
						e.message += ".  Exception occurred when checking element " + element.id + ", check the '" + rule.method + "' method.";
					}

					throw e;
				}
			}
			if ( dependencyMismatch ) {
				return;
			}
			if ( this.objectLength( rules ) ) {
				this.successList.push( element );
			}
			return true;
		},

		// Return the custom message for the given element and validation method
		// specified in the element's HTML5 data attribute
		// return the generic message if present and no method specific message is present
		customDataMessage: function( element, method ) {
			return $( element ).data( "msg" + method.charAt( 0 ).toUpperCase() +
				method.substring( 1 ).toLowerCase() ) || $( element ).data( "msg" );
		},

		// Return the custom message for the given element name and validation method
		customMessage: function( name, method ) {
			var m = this.settings.messages[ name ];
			return m && ( m.constructor === String ? m : m[ method ] );
		},

		// Return the first defined argument, allowing empty strings
		findDefined: function() {
			for ( var i = 0; i < arguments.length; i++ ) {
				if ( arguments[ i ] !== undefined ) {
					return arguments[ i ];
				}
			}
			return undefined;
		},

		defaultMessage: function( element, rule ) {
			var message = this.findDefined(
					this.customMessage( element.name, rule.method ),
					this.customDataMessage( element, rule.method ),

					// 'title' is never undefined, so handle empty string as undefined
					!this.settings.ignoreTitle && element.title || undefined,
					$.validator.messages[ rule.method ],
					"<strong>Warning: No message defined for " + element.name + "</strong>"
				),
				theregex = /\$?\{(\d+)\}/g;
			if ( typeof message === "function" ) {
				message = message.call( this, rule.parameters, element );
			} else if ( theregex.test( message ) ) {
				message = $.validator.format( message.replace( theregex, "{$1}" ), rule.parameters );
			}

			return message;
		},

		formatAndAdd: function( element, rule ) {
			var message = this.defaultMessage( element, rule );

			this.errorList.push( {
				message: message,
				element: element,
				method: rule.method
			} );

			this.errorMap[ element.name ] = message;
			this.submitted[ element.name ] = message;
		},

		addWrapper: function( toToggle ) {
			if ( this.settings.wrapper ) {
				toToggle = toToggle.add( toToggle.parent( this.settings.wrapper ) );
			}
			return toToggle;
		},

		defaultShowErrors: function() {
			var i, elements, error;
			for ( i = 0; this.errorList[ i ]; i++ ) {
				error = this.errorList[ i ];
				if ( this.settings.highlight ) {
					this.settings.highlight.call( this, error.element, this.settings.errorClass, this.settings.validClass );
				}
				this.showLabel( error.element, error.message );
			}
			if ( this.errorList.length ) {
				this.toShow = this.toShow.add( this.containers );
			}
			if ( this.settings.success ) {
				for ( i = 0; this.successList[ i ]; i++ ) {
					this.showLabel( this.successList[ i ] );
				}
			}
			if ( this.settings.unhighlight ) {
				for ( i = 0, elements = this.validElements(); elements[ i ]; i++ ) {
					this.settings.unhighlight.call( this, elements[ i ], this.settings.errorClass, this.settings.validClass );
				}
			}
			this.toHide = this.toHide.not( this.toShow );
			this.hideErrors();
			this.addWrapper( this.toShow ).show();
		},

		validElements: function() {
			return this.currentElements.not( this.invalidElements() );
		},

		invalidElements: function() {
			return $( this.errorList ).map( function() {
				return this.element;
			} );
		},

		showLabel: function( element, message ) {
			var place, group, errorID, v,
				error = this.errorsFor( element ),
				elementID = this.idOrName( element ),
				describedBy = $( element ).attr( "aria-describedby" );

			if ( error.length ) {

				// Refresh error/success class
				error.removeClass( this.settings.validClass ).addClass( this.settings.errorClass );

				// Replace message on existing label
				error.html( message );
			} else {

				// Create error element
				error = $( "<" + this.settings.errorElement + ">" )
					.attr( "id", elementID + "-error" )
					.addClass( this.settings.errorClass )
					.html( message || "" );

				// Maintain reference to the element to be placed into the DOM
				place = error;
				if ( this.settings.wrapper ) {

					// Make sure the element is visible, even in IE
					// actually showing the wrapped element is handled elsewhere
					place = error.hide().show().wrap( "<" + this.settings.wrapper + "/>" ).parent();
				}
				if ( this.labelContainer.length ) {
					this.labelContainer.append( place );
				} else if ( this.settings.errorPlacement ) {
					this.settings.errorPlacement( place, $( element ) );
				} else {
					place.insertAfter( element );
				}

				// Link error back to the element
				if ( error.is( "label" ) ) {

					// If the error is a label, then associate using 'for'
					error.attr( "for", elementID );

					// If the element is not a child of an associated label, then it's necessary
					// to explicitly apply aria-describedby
				} else if ( error.parents( "label[for='" + this.escapeCssMeta( elementID ) + "']" ).length === 0 ) {
					errorID = error.attr( "id" );

					// Respect existing non-error aria-describedby
					if ( !describedBy ) {
						describedBy = errorID;
					} else if ( !describedBy.match( new RegExp( "\\b" + this.escapeCssMeta( errorID ) + "\\b" ) ) ) {

						// Add to end of list if not already present
						describedBy += " " + errorID;
					}
					$( element ).attr( "aria-describedby", describedBy );

					// If this element is grouped, then assign to all elements in the same group
					group = this.groups[ element.name ];
					if ( group ) {
						v = this;
						$.each( v.groups, function( name, testgroup ) {
							if ( testgroup === group ) {
								$( "[name='" + v.escapeCssMeta( name ) + "']", v.currentForm )
									.attr( "aria-describedby", error.attr( "id" ) );
							}
						} );
					}
				}
			}
			if ( !message && this.settings.success ) {
				error.text( "" );
				if ( typeof this.settings.success === "string" ) {
					error.addClass( this.settings.success );
				} else {
					this.settings.success( error, element );
				}
			}
			this.toShow = this.toShow.add( error );
		},

		errorsFor: function( element ) {
			var name = this.escapeCssMeta( this.idOrName( element ) ),
				describer = $( element ).attr( "aria-describedby" ),
				selector = "label[for='" + name + "'], label[for='" + name + "'] *";

			// 'aria-describedby' should directly reference the error element
			if ( describer ) {
				selector = selector + ", #" + this.escapeCssMeta( describer )
					.replace( /\s+/g, ", #" );
			}

			return this
				.errors()
				.filter( selector );
		},

		// See https://api.jquery.com/category/selectors/, for CSS
		// meta-characters that should be escaped in order to be used with JQuery
		// as a literal part of a name/id or any selector.
		escapeCssMeta: function( string ) {
			return string.replace( /([\\!"#$%&'()*+,./:;<=>?@\[\]^`{|}~])/g, "\\$1" );
		},

		idOrName: function( element ) {
			return this.groups[ element.name ] || ( this.checkable( element ) ? element.name : element.id || element.name );
		},

		validationTargetFor: function( element ) {

			// If radio/checkbox, validate first element in group instead
			if ( this.checkable( element ) ) {
				element = this.findByName( element.name );
			}

			// Always apply ignore filter
			return $( element ).not( this.settings.ignore )[ 0 ];
		},

		checkable: function( element ) {
			return ( /radio|checkbox/i ).test( element.type );
		},

		findByName: function( name ) {
			return $( this.currentForm ).find( "[name='" + this.escapeCssMeta( name ) + "']" );
		},

		getLength: function( value, element ) {
			switch ( element.nodeName.toLowerCase() ) {
			case "select":
				return $( "option:selected", element ).length;
			case "input":
				if ( this.checkable( element ) ) {
					return this.findByName( element.name ).filter( ":checked" ).length;
				}
			}
			return value.length;
		},

		depend: function( param, element ) {
			return this.dependTypes[ typeof param ] ? this.dependTypes[ typeof param ]( param, element ) : true;
		},

		dependTypes: {
			"boolean": function( param ) {
				return param;
			},
			"string": function( param, element ) {
				return !!$( param, element.form ).length;
			},
			"function": function( param, element ) {
				return param( element );
			}
		},

		optional: function( element ) {
			var val = this.elementValue( element );
			return !$.validator.methods.required.call( this, val, element ) && "dependency-mismatch";
		},

		startRequest: function( element ) {
			if ( !this.pending[ element.name ] ) {
				this.pendingRequest++;
				$( element ).addClass( this.settings.pendingClass );
				this.pending[ element.name ] = true;
			}
		},

		stopRequest: function( element, valid ) {
			this.pendingRequest--;

			// Sometimes synchronization fails, make sure pendingRequest is never < 0
			if ( this.pendingRequest < 0 ) {
				this.pendingRequest = 0;
			}
			delete this.pending[ element.name ];
			$( element ).removeClass( this.settings.pendingClass );
			if ( valid && this.pendingRequest === 0 && this.formSubmitted && this.form() ) {
				$( this.currentForm ).submit();
				this.formSubmitted = false;
			} else if ( !valid && this.pendingRequest === 0 && this.formSubmitted ) {
				$( this.currentForm ).triggerHandler( "invalid-form", [ this ] );
				this.formSubmitted = false;
			}
		},

		previousValue: function( element, method ) {
			return $.data( element, "previousValue" ) || $.data( element, "previousValue", {
				old: null,
				valid: true,
				message: this.defaultMessage( element, { method: method } )
			} );
		},

		// Cleans up all forms and elements, removes validator-specific events
		destroy: function() {
			this.resetForm();

			$( this.currentForm )
				.off( ".validate" )
				.removeData( "validator" )
				.find( ".validate-equalTo-blur" )
					.off( ".validate-equalTo" )
					.removeClass( "validate-equalTo-blur" );
		}

	},

	classRuleSettings: {
		required: { required: true },
		email: { email: true },
		url: { url: true },
		date: { date: true },
		dateISO: { dateISO: true },
		number: { number: true },
		digits: { digits: true },
		creditcard: { creditcard: true }
	},

	addClassRules: function( className, rules ) {
		if ( className.constructor === String ) {
			this.classRuleSettings[ className ] = rules;
		} else {
			$.extend( this.classRuleSettings, className );
		}
	},

	classRules: function( element ) {
		var rules = {},
			classes = $( element ).attr( "class" );

		if ( classes ) {
			$.each( classes.split( " " ), function() {
				if ( this in $.validator.classRuleSettings ) {
					$.extend( rules, $.validator.classRuleSettings[ this ] );
				}
			} );
		}
		return rules;
	},

	normalizeAttributeRule: function( rules, type, method, value ) {

		// Convert the value to a number for number inputs, and for text for backwards compability
		// allows type="date" and others to be compared as strings
		if ( /min|max|step/.test( method ) && ( type === null || /number|range|text/.test( type ) ) ) {
			value = Number( value );

			// Support Opera Mini, which returns NaN for undefined minlength
			if ( isNaN( value ) ) {
				value = undefined;
			}
		}

		if ( value || value === 0 ) {
			rules[ method ] = value;
		} else if ( type === method && type !== "range" ) {

			// Exception: the jquery validate 'range' method
			// does not test for the html5 'range' type
			rules[ method ] = true;
		}
	},

	attributeRules: function( element ) {
		var rules = {},
			$element = $( element ),
			type = element.getAttribute( "type" ),
			method, value;

		for ( method in $.validator.methods ) {

			// Support for <input required> in both html5 and older browsers
			if ( method === "required" ) {
				value = element.getAttribute( method );

				// Some browsers return an empty string for the required attribute
				// and non-HTML5 browsers might have required="" markup
				if ( value === "" ) {
					value = true;
				}

				// Force non-HTML5 browsers to return bool
				value = !!value;
			} else {
				value = $element.attr( method );
			}

			this.normalizeAttributeRule( rules, type, method, value );
		}

		// 'maxlength' may be returned as -1, 2147483647 ( IE ) and 524288 ( safari ) for text inputs
		if ( rules.maxlength && /-1|2147483647|524288/.test( rules.maxlength ) ) {
			delete rules.maxlength;
		}

		return rules;
	},

	dataRules: function( element ) {
		var rules = {},
			$element = $( element ),
			type = element.getAttribute( "type" ),
			method, value;

		for ( method in $.validator.methods ) {
			value = $element.data( "rule" + method.charAt( 0 ).toUpperCase() + method.substring( 1 ).toLowerCase() );
			this.normalizeAttributeRule( rules, type, method, value );
		}
		return rules;
	},

	staticRules: function( element ) {
		var rules = {},
			validator = $.data( element.form, "validator" );

		if ( validator.settings.rules ) {
			rules = $.validator.normalizeRule( validator.settings.rules[ element.name ] ) || {};
		}
		return rules;
	},

	normalizeRules: function( rules, element ) {

		// Handle dependency check
		$.each( rules, function( prop, val ) {

			// Ignore rule when param is explicitly false, eg. required:false
			if ( val === false ) {
				delete rules[ prop ];
				return;
			}
			if ( val.param || val.depends ) {
				var keepRule = true;
				switch ( typeof val.depends ) {
				case "string":
					keepRule = !!$( val.depends, element.form ).length;
					break;
				case "function":
					keepRule = val.depends.call( element, element );
					break;
				}
				if ( keepRule ) {
					rules[ prop ] = val.param !== undefined ? val.param : true;
				} else {
					$.data( element.form, "validator" ).resetElements( $( element ) );
					delete rules[ prop ];
				}
			}
		} );

		// Evaluate parameters
		$.each( rules, function( rule, parameter ) {
			rules[ rule ] = $.isFunction( parameter ) && rule !== "normalizer" ? parameter( element ) : parameter;
		} );

		// Clean number parameters
		$.each( [ "minlength", "maxlength" ], function() {
			if ( rules[ this ] ) {
				rules[ this ] = Number( rules[ this ] );
			}
		} );
		$.each( [ "rangelength", "range" ], function() {
			var parts;
			if ( rules[ this ] ) {
				if ( $.isArray( rules[ this ] ) ) {
					rules[ this ] = [ Number( rules[ this ][ 0 ] ), Number( rules[ this ][ 1 ] ) ];
				} else if ( typeof rules[ this ] === "string" ) {
					parts = rules[ this ].replace( /[\[\]]/g, "" ).split( /[\s,]+/ );
					rules[ this ] = [ Number( parts[ 0 ] ), Number( parts[ 1 ] ) ];
				}
			}
		} );

		if ( $.validator.autoCreateRanges ) {

			// Auto-create ranges
			if ( rules.min != null && rules.max != null ) {
				rules.range = [ rules.min, rules.max ];
				delete rules.min;
				delete rules.max;
			}
			if ( rules.minlength != null && rules.maxlength != null ) {
				rules.rangelength = [ rules.minlength, rules.maxlength ];
				delete rules.minlength;
				delete rules.maxlength;
			}
		}

		return rules;
	},

	// Converts a simple string to a {string: true} rule, e.g., "required" to {required:true}
	normalizeRule: function( data ) {
		if ( typeof data === "string" ) {
			var transformed = {};
			$.each( data.split( /\s/ ), function() {
				transformed[ this ] = true;
			} );
			data = transformed;
		}
		return data;
	},

	// http://jqueryvalidation.org/jQuery.validator.addMethod/
	addMethod: function( name, method, message ) {
		$.validator.methods[ name ] = method;
		$.validator.messages[ name ] = message !== undefined ? message : $.validator.messages[ name ];
		if ( method.length < 3 ) {
			$.validator.addClassRules( name, $.validator.normalizeRule( name ) );
		}
	},

	// http://jqueryvalidation.org/jQuery.validator.methods/
	methods: {

		// http://jqueryvalidation.org/required-method/
		required: function( value, element, param ) {

			// Check if dependency is met
			if ( !this.depend( param, element ) ) {
				return "dependency-mismatch";
			}
			if ( element.nodeName.toLowerCase() === "select" ) {

				// Could be an array for select-multiple or a string, both are fine this way
				var val = $( element ).val();
				return val && val.length > 0;
			}
			if ( this.checkable( element ) ) {
				return this.getLength( value, element ) > 0;
			}
			return value.length > 0;
		},

		// http://jqueryvalidation.org/email-method/
		email: function( value, element ) {

			// From https://html.spec.whatwg.org/multipage/forms.html#valid-e-mail-address
			// Retrieved 2014-01-14
			// If you have a problem with this implementation, report a bug against the above spec
			// Or use custom methods to implement your own email validation
			return this.optional( element ) || /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/.test( value );
		},

		// http://jqueryvalidation.org/url-method/
		url: function( value, element ) {

			// Copyright (c) 2010-2013 Diego Perini, MIT licensed
			// https://gist.github.com/dperini/729294
			// see also https://mathiasbynens.be/demo/url-regex
			// modified to allow protocol-relative URLs
			return this.optional( element ) || /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test( value );
		},

		// http://jqueryvalidation.org/date-method/
		date: function( value, element ) {
			return this.optional( element ) || !/Invalid|NaN/.test( new Date( value ).toString() );
		},

		// http://jqueryvalidation.org/dateISO-method/
		dateISO: function( value, element ) {
			return this.optional( element ) || /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/.test( value );
		},

		// http://jqueryvalidation.org/number-method/
		number: function( value, element ) {
			return this.optional( element ) || /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test( value );
		},

		// http://jqueryvalidation.org/digits-method/
		digits: function( value, element ) {
			return this.optional( element ) || /^\d+$/.test( value );
		},

		// http://jqueryvalidation.org/minlength-method/
		minlength: function( value, element, param ) {
			var length = $.isArray( value ) ? value.length : this.getLength( value, element );
			return this.optional( element ) || length >= param;
		},

		// http://jqueryvalidation.org/maxlength-method/
		maxlength: function( value, element, param ) {
			var length = $.isArray( value ) ? value.length : this.getLength( value, element );
			return this.optional( element ) || length <= param;
		},

		// http://jqueryvalidation.org/rangelength-method/
		rangelength: function( value, element, param ) {
			var length = $.isArray( value ) ? value.length : this.getLength( value, element );
			return this.optional( element ) || ( length >= param[ 0 ] && length <= param[ 1 ] );
		},

		// http://jqueryvalidation.org/min-method/
		min: function( value, element, param ) {
			return this.optional( element ) || value >= param;
		},

		// http://jqueryvalidation.org/max-method/
		max: function( value, element, param ) {
			return this.optional( element ) || value <= param;
		},

		// http://jqueryvalidation.org/range-method/
		range: function( value, element, param ) {
			return this.optional( element ) || ( value >= param[ 0 ] && value <= param[ 1 ] );
		},

		// http://jqueryvalidation.org/step-method/
		step: function( value, element, param ) {
			var type = $( element ).attr( "type" ),
				errorMessage = "Step attribute on input type " + type + " is not supported.",
				supportedTypes = [ "text", "number", "range" ],
				re = new RegExp( "\\b" + type + "\\b" ),
				notSupported = type && !re.test( supportedTypes.join() );

			// Works only for text, number and range input types
			// TODO find a way to support input types date, datetime, datetime-local, month, time and week
			if ( notSupported ) {
				throw new Error( errorMessage );
			}
			return this.optional( element ) || ( value % param === 0 );
		},

		// http://jqueryvalidation.org/equalTo-method/
		equalTo: function( value, element, param ) {

			// Bind to the blur event of the target in order to revalidate whenever the target field is updated
			var target = $( param );
			if ( this.settings.onfocusout && target.not( ".validate-equalTo-blur" ).length ) {
				target.addClass( "validate-equalTo-blur" ).on( "blur.validate-equalTo", function() {
					$( element ).valid();
				} );
			}
			return value === target.val();
		},

		// http://jqueryvalidation.org/remote-method/
		remote: function( value, element, param, method ) {
			if ( this.optional( element ) ) {
				return "dependency-mismatch";
			}

			method = typeof method === "string" && method || "remote";

			var previous = this.previousValue( element, method ),
				validator, data, optionDataString;

			if ( !this.settings.messages[ element.name ] ) {
				this.settings.messages[ element.name ] = {};
			}
			previous.originalMessage = previous.originalMessage || this.settings.messages[ element.name ][ method ];
			this.settings.messages[ element.name ][ method ] = previous.message;

			param = typeof param === "string" && { url: param } || param;
			optionDataString = $.param( $.extend( { data: value }, param.data ) );
			if ( previous.old === optionDataString ) {
				return previous.valid;
			}

			previous.old = optionDataString;
			validator = this;
			this.startRequest( element );
			data = {};
			data[ element.name ] = value;
			$.ajax( $.extend( true, {
				mode: "abort",
				port: "validate" + element.name,
				dataType: "json",
				data: data,
				context: validator.currentForm,
				success: function( response ) {
					var valid = response === true || response === "true",
						errors, message, submitted;

					validator.settings.messages[ element.name ][ method ] = previous.originalMessage;
					if ( valid ) {
						submitted = validator.formSubmitted;
						validator.resetInternals();
						validator.toHide = validator.errorsFor( element );
						validator.formSubmitted = submitted;
						validator.successList.push( element );
						validator.invalid[ element.name ] = false;
						validator.showErrors();
					} else {
						errors = {};
						message = response || validator.defaultMessage( element, { method: method, parameters: value } );
						errors[ element.name ] = previous.message = message;
						validator.invalid[ element.name ] = true;
						validator.showErrors( errors );
					}
					previous.valid = valid;
					validator.stopRequest( element, valid );
				}
			}, param ) );
			return "pending";
		}
	}

} );

// Ajax mode: abort
// usage: $.ajax({ mode: "abort"[, port: "uniqueport"]});
// if mode:"abort" is used, the previous request on that port (port can be undefined) is aborted via XMLHttpRequest.abort()

var pendingRequests = {},
	ajax;

// Use a prefilter if available (1.5+)
if ( $.ajaxPrefilter ) {
	$.ajaxPrefilter( function( settings, _, xhr ) {
		var port = settings.port;
		if ( settings.mode === "abort" ) {
			if ( pendingRequests[ port ] ) {
				pendingRequests[ port ].abort();
			}
			pendingRequests[ port ] = xhr;
		}
	} );
} else {

	// Proxy ajax
	ajax = $.ajax;
	$.ajax = function( settings ) {
		var mode = ( "mode" in settings ? settings : $.ajaxSettings ).mode,
			port = ( "port" in settings ? settings : $.ajaxSettings ).port;
		if ( mode === "abort" ) {
			if ( pendingRequests[ port ] ) {
				pendingRequests[ port ].abort();
			}
			pendingRequests[ port ] = ajax.apply( this, arguments );
			return pendingRequests[ port ];
		}
		return ajax.apply( this, arguments );
	};
}

}));
(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else if (typeof module === "object" && module.exports) {
		module.exports = factory( require( "jquery" ) );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: FR (French; français)
 */
$.extend( $.validator.messages, {
	required: "Ce champ est obligatoire.",
	remote: "Veuillez corriger ce champ.",
	email: "Veuillez fournir une adresse électronique valide.",
	url: "Veuillez fournir une adresse URL valide.",
	date: "Veuillez fournir une date valide.",
	dateISO: "Veuillez fournir une date valide (ISO).",
	number: "Veuillez fournir un numéro valide.",
	digits: "Veuillez fournir seulement des chiffres.",
	creditcard: "Veuillez fournir un numéro de carte de crédit valide.",
	equalTo: "Veuillez fournir encore la même valeur.",
	extension: "Veuillez fournir une valeur avec une extension valide.",
	maxlength: $.validator.format( "Veuillez fournir au plus {0} caractères." ),
	minlength: $.validator.format( "Veuillez fournir au moins {0} caractères." ),
	rangelength: $.validator.format( "Veuillez fournir une valeur qui contient entre {0} et {1} caractères." ),
	range: $.validator.format( "Veuillez fournir une valeur entre {0} et {1}." ),
	max: $.validator.format( "Veuillez fournir une valeur inférieure ou égale à {0}." ),
	min: $.validator.format( "Veuillez fournir une valeur supérieure ou égale à {0}." ),
	maxWords: $.validator.format( "Veuillez fournir au plus {0} mots." ),
	minWords: $.validator.format( "Veuillez fournir au moins {0} mots." ),
	rangeWords: $.validator.format( "Veuillez fournir entre {0} et {1} mots." ),
	letterswithbasicpunc: "Veuillez fournir seulement des lettres et des signes de ponctuation.",
	alphanumeric: "Veuillez fournir seulement des lettres, nombres, espaces et soulignages.",
	lettersonly: "Veuillez fournir seulement des lettres.",
	nowhitespace: "Veuillez ne pas inscrire d'espaces blancs.",
	ziprange: "Veuillez fournir un code postal entre 902xx-xxxx et 905-xx-xxxx.",
	integer: "Veuillez fournir un nombre non décimal qui est positif ou négatif.",
	vinUS: "Veuillez fournir un numéro d'identification du véhicule (VIN).",
	dateITA: "Veuillez fournir une date valide.",
	time: "Veuillez fournir une heure valide entre 00:00 et 23:59.",
	phoneUS: "Veuillez fournir un numéro de téléphone valide.",
	phoneUK: "Veuillez fournir un numéro de téléphone valide.",
	mobileUK: "Veuillez fournir un numéro de téléphone mobile valide.",
	strippedminlength: $.validator.format( "Veuillez fournir au moins {0} caractères." ),
	email2: "Veuillez fournir une adresse électronique valide.",
	url2: "Veuillez fournir une adresse URL valide.",
	creditcardtypes: "Veuillez fournir un numéro de carte de crédit valide.",
	ipv4: "Veuillez fournir une adresse IP v4 valide.",
	ipv6: "Veuillez fournir une adresse IP v6 valide.",
	require_from_group: "Veuillez fournir au moins {0} de ces champs.",
	nifES: "Veuillez fournir un numéro NIF valide.",
	nieES: "Veuillez fournir un numéro NIE valide.",
	cifES: "Veuillez fournir un numéro CIF valide.",
	postalCodeCA: "Veuillez fournir un code postal valide."
} );

}));
/*!
 * ScrollMagic v2.0.5 (2015-04-29)
 * The javascript library for magical scroll interactions.
 * (c) 2015 Jan Paepke (@janpaepke)
 * Project Website: http://scrollmagic.io
 * 
 * @version 2.0.5
 * @license Dual licensed under MIT license and GPL.
 * @author Jan Paepke - e-mail@janpaepke.de
 *
 * @file ScrollMagic GSAP Animation Plugin.
 *
 * requires: GSAP ~1.14
 * Powered by the Greensock Animation Platform (GSAP): http://www.greensock.com/js
 * Greensock License info at http://www.greensock.com/licensing/
 */
/**
 * This plugin is meant to be used in conjunction with the Greensock Animation Plattform.  
 * It offers an easy API to trigger Tweens or synchronize them to the scrollbar movement.
 *
 * Both the `lite` and the `max` versions of the GSAP library are supported.  
 * The most basic requirement is `TweenLite`.
 * 
 * To have access to this extension, please include `plugins/animation.gsap.js`.
 * @requires {@link http://greensock.com/gsap|GSAP ~1.14.x}
 * @mixin animation.GSAP
 */
(function (root, factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD. Register as an anonymous module.
		define(['ScrollMagic', 'TweenMax', 'TimelineMax'], factory);
	} else if (typeof exports === 'object') {
		// CommonJS
		// Loads whole gsap package onto global scope.
		require('gsap');
		factory(require('scrollmagic'), TweenMax, TimelineMax);
	} else {
		// Browser globals
		factory(root.ScrollMagic || (root.jQuery && root.jQuery.ScrollMagic), root.TweenMax || root.TweenLite, root.TimelineMax || root.TimelineLite);
	}
}(this, function (ScrollMagic, Tween, Timeline) {
	"use strict";
	var NAMESPACE = "animation.gsap";

	var
	console = window.console || {},
		err = Function.prototype.bind.call(console.error || console.log ||
		function () {}, console);
	if (!ScrollMagic) {
		err("(" + NAMESPACE + ") -> ERROR: The ScrollMagic main module could not be found. Please make sure it's loaded before this plugin or use an asynchronous loader like requirejs.");
	}
	if (!Tween) {
		err("(" + NAMESPACE + ") -> ERROR: TweenLite or TweenMax could not be found. Please make sure GSAP is loaded before ScrollMagic or use an asynchronous loader like requirejs.");
	}

/*
	 * ----------------------------------------------------------------
	 * Extensions for Scene
	 * ----------------------------------------------------------------
	 */
	/**
	 * Every instance of ScrollMagic.Scene now accepts an additional option.  
	 * See {@link ScrollMagic.Scene} for a complete list of the standard options.
	 * @memberof! animation.GSAP#
	 * @method new ScrollMagic.Scene(options)
	 * @example
	 * var scene = new ScrollMagic.Scene({tweenChanges: true});
	 *
	 * @param {object} [options] - Options for the Scene. The options can be updated at any time.
	 * @param {boolean} [options.tweenChanges=false] - Tweens Animation to the progress target instead of setting it.  
	 Does not affect animations where duration is `0`.
	 */
	/**
	 * **Get** or **Set** the tweenChanges option value.  
	 * This only affects scenes with a duration. If `tweenChanges` is `true`, the progress update when scrolling will not be immediate, but instead the animation will smoothly animate to the target state.  
	 * For a better understanding, try enabling and disabling this option in the [Scene Manipulation Example](../examples/basic/scene_manipulation.html).
	 * @memberof! animation.GSAP#
	 * @method Scene.tweenChanges
	 * 
	 * @example
	 * // get the current tweenChanges option
	 * var tweenChanges = scene.tweenChanges();
	 *
	 * // set new tweenChanges option
	 * scene.tweenChanges(true);
	 *
	 * @fires {@link Scene.change}, when used as setter
	 * @param {boolean} [newTweenChanges] - The new tweenChanges setting of the scene.
	 * @returns {boolean} `get` -  Current tweenChanges option value.
	 * @returns {Scene} `set` -  Parent object for chaining.
	 */
	// add option (TODO: DOC (private for dev))
	ScrollMagic.Scene.addOption("tweenChanges", // name
	false, // default


	function (val) { // validation callback
		return !!val;
	});
	// extend scene
	ScrollMagic.Scene.extend(function () {
		var Scene = this,
			_tween;

		var log = function () {
			if (Scene._log) { // not available, when main source minified
				Array.prototype.splice.call(arguments, 1, 0, "(" + NAMESPACE + ")", "->");
				Scene._log.apply(this, arguments);
			}
		};

		// set listeners
		Scene.on("progress.plugin_gsap", function () {
			updateTweenProgress();
		});
		Scene.on("destroy.plugin_gsap", function (e) {
			Scene.removeTween(e.reset);
		});

		/**
		 * Update the tween progress to current position.
		 * @private
		 */
		var updateTweenProgress = function () {
			if (_tween) {
				var
				progress = Scene.progress(),
					state = Scene.state();
				if (_tween.repeat && _tween.repeat() === -1) {
					// infinite loop, so not in relation to progress
					if (state === 'DURING' && _tween.paused()) {
						_tween.play();
					} else if (state !== 'DURING' && !_tween.paused()) {
						_tween.pause();
					}
				} else if (progress != _tween.progress()) { // do we even need to update the progress?
					// no infinite loop - so should we just play or go to a specific point in time?
					if (Scene.duration() === 0) {
						// play the animation
						if (progress > 0) { // play from 0 to 1
							_tween.play();
						} else { // play from 1 to 0
							_tween.reverse();
						}
					} else {
						// go to a specific point in time
						if (Scene.tweenChanges() && _tween.tweenTo) {
							// go smooth
							_tween.tweenTo(progress * _tween.duration());
						} else {
							// just hard set it
							_tween.progress(progress).pause();
						}
					}
				}
			}
		};

		/**
		 * Add a tween to the scene.  
		 * If you want to add multiple tweens, add them into a GSAP Timeline object and supply it instead (see example below).  
		 * 
		 * If the scene has a duration, the tween's duration will be projected to the scroll distance of the scene, meaning its progress will be synced to scrollbar movement.  
		 * For a scene with a duration of `0`, the tween will be triggered when scrolling forward past the scene's trigger position and reversed, when scrolling back.  
		 * To gain better understanding, check out the [Simple Tweening example](../examples/basic/simple_tweening.html).
		 *
		 * Instead of supplying a tween this method can also be used as a shorthand for `TweenMax.to()` (see example below).
		 * @memberof! animation.GSAP#
		 *
		 * @example
		 * // add a single tween directly
		 * scene.setTween(TweenMax.to("obj"), 1, {x: 100});
		 *
		 * // add a single tween via variable
		 * var tween = TweenMax.to("obj"), 1, {x: 100};
		 * scene.setTween(tween);
		 *
		 * // add multiple tweens, wrapped in a timeline.
		 * var timeline = new TimelineMax();
		 * var tween1 = TweenMax.from("obj1", 1, {x: 100});
		 * var tween2 = TweenMax.to("obj2", 1, {y: 100});
		 * timeline
		 *		.add(tween1)
		 *		.add(tween2);
		 * scene.addTween(timeline);
		 *
		 * // short hand to add a TweenMax.to() tween
		 * scene.setTween("obj3", 0.5, {y: 100});
		 *
		 * // short hand to add a TweenMax.to() tween for 1 second
		 * // this is useful, when the scene has a duration and the tween duration isn't important anyway
		 * scene.setTween("obj3", {y: 100});
		 *
		 * @param {(object|string)} TweenObject - A TweenMax, TweenLite, TimelineMax or TimelineLite object that should be animated in the scene. Can also be a Dom Element or Selector, when using direct tween definition (see examples).
		 * @param {(number|object)} duration - A duration for the tween, or tween parameters. If an object containing parameters are supplied, a default duration of 1 will be used.
		 * @param {object} params - The parameters for the tween
		 * @returns {Scene} Parent object for chaining.
		 */
		Scene.setTween = function (TweenObject, duration, params) {
			var newTween;
			if (arguments.length > 1) {
				if (arguments.length < 3) {
					params = duration;
					duration = 1;
				}
				TweenObject = Tween.to(TweenObject, duration, params);
			}
			try {
				// wrap Tween into a Timeline Object if available to include delay and repeats in the duration and standardize methods.
				if (Timeline) {
					newTween = new Timeline({
						smoothChildTiming: true
					}).add(TweenObject);
				} else {
					newTween = TweenObject;
				}
				newTween.pause();
			} catch (e) {
				log(1, "ERROR calling method 'setTween()': Supplied argument is not a valid TweenObject");
				return Scene;
			}
			if (_tween) { // kill old tween?
				Scene.removeTween();
			}
			_tween = newTween;

			// some properties need to be transferred it to the wrapper, otherwise they would get lost.
			if (TweenObject.repeat && TweenObject.repeat() === -1) { // TweenMax or TimelineMax Object?
				_tween.repeat(-1);
				_tween.yoyo(TweenObject.yoyo());
			}
			// Some tween validations and debugging helpers
			if (Scene.tweenChanges() && !_tween.tweenTo) {
				log(2, "WARNING: tweenChanges will only work if the TimelineMax object is available for ScrollMagic.");
			}

			// check if there are position tweens defined for the trigger and warn about it :)
			if (_tween && Scene.controller() && Scene.triggerElement() && Scene.loglevel() >= 2) { // controller is needed to know scroll direction.
				var
				triggerTweens = Tween.getTweensOf(Scene.triggerElement()),
					vertical = Scene.controller().info("vertical");
				triggerTweens.forEach(function (value, index) {
					var
					tweenvars = value.vars.css || value.vars,
						condition = vertical ? (tweenvars.top !== undefined || tweenvars.bottom !== undefined) : (tweenvars.left !== undefined || tweenvars.right !== undefined);
					if (condition) {
						log(2, "WARNING: Tweening the position of the trigger element affects the scene timing and should be avoided!");
						return false;
					}
				});
			}

			// warn about tween overwrites, when an element is tweened multiple times
			if (parseFloat(TweenLite.version) >= 1.14) { // onOverwrite only present since GSAP v1.14.0
				var
				list = _tween.getChildren ? _tween.getChildren(true, true, false) : [_tween],
					// get all nested tween objects
					newCallback = function () {
						log(2, "WARNING: tween was overwritten by another. To learn how to avoid this issue see here: https://github.com/janpaepke/ScrollMagic/wiki/WARNING:-tween-was-overwritten-by-another");
					};
				for (var i = 0, thisTween, oldCallback; i < list.length; i++) { /*jshint loopfunc: true */
					thisTween = list[i];
					if (oldCallback !== newCallback) { // if tweens is added more than once
						oldCallback = thisTween.vars.onOverwrite;
						thisTween.vars.onOverwrite = function () {
							if (oldCallback) {
								oldCallback.apply(this, arguments);
							}
							newCallback.apply(this, arguments);
						};
					}
				}
			}
			log(3, "added tween");

			updateTweenProgress();
			return Scene;
		};

		/**
		 * Remove the tween from the scene.  
		 * This will terminate the control of the Scene over the tween.
		 *
		 * Using the reset option you can decide if the tween should remain in the current state or be rewound to set the target elements back to the state they were in before the tween was added to the scene.
		 * @memberof! animation.GSAP#
		 *
		 * @example
		 * // remove the tween from the scene without resetting it
		 * scene.removeTween();
		 *
		 * // remove the tween from the scene and reset it to initial position
		 * scene.removeTween(true);
		 *
		 * @param {boolean} [reset=false] - If `true` the tween will be reset to its initial values.
		 * @returns {Scene} Parent object for chaining.
		 */
		Scene.removeTween = function (reset) {
			if (_tween) {
				if (reset) {
					_tween.progress(0).pause();
				}
				_tween.kill();
				_tween = undefined;
				log(3, "removed tween (reset: " + (reset ? "true" : "false") + ")");
			}
			return Scene;
		};

	});
}));
var environment, environmentChanged, windowWidth, windowHeight;
var isIE = true;
    function detectIE() {
        var ua = window.navigator.userAgent;

        // Test values; Uncomment to check result …

        // IE 10
        // ua = 'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)';
        
        // IE 11
        // ua = 'Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko';
        
        // Edge 12 (Spartan)
        // ua = 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36 Edge/12.0';
        
        // Edge 13
        // ua = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586';

        var msie = ua.indexOf('MSIE ');
        if (msie > 0) {
            // IE 10 or older => return version number
            return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
        }

        var trident = ua.indexOf('Trident/');
        if (trident > 0) {
            // IE 11 => return version number
            var rv = ua.indexOf('rv:');
            return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
        }

        var edge = ua.indexOf('Edge/');
        if (edge > 0) {
            // Edge (IE 12+) => return version number
            return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
        }

        // other browser
        isIE = false;
        return false;
    }
    function viewport() {
        var e = window, a = 'inner';
        if (!('innerWidth' in window )) {
            a = 'client';
            e = document.documentElement || document.body;
        }
        return { width : e[ a+'Width' ] , height : e[ a+'Height' ] };
    }
    function testScrollMagic(){
        if(environment != 'mobile' && $(window).height() > 720){
            $('body').addClass('can-scrollMagic');
            canScrollMagic = true;
        }else{
            $('body').removeClass('can-scrollMagic');
            canScrollMagic = false;
        }
    }

    environment = 'desktop';
    environmentChanged = false;
    windowWidth = viewport().width; // This should match your media query
    windowHeight = viewport().height; // This should match your media query

    function testEnvironment() {
        var currentEnvironment = environment;
        windowWidth = viewport().width;
        if (windowWidth <= breakpoint_large && windowWidth > breakpoint_small) {
            environment = 'tablette';
        } else if (windowWidth <= breakpoint_small) {
            environment = 'mobile';
        } else {
            environment = 'desktop';
        }
        if (currentEnvironment != environment) {
            environmentChanged = true;
            $(document).trigger( "environment:changed", [{old_environment: currentEnvironment, new_environment: environment}] );
        } else {
            environmentChanged = false;
        }
    }
(function ($) {
    detectIE();
    $(document).ready(function() {
        testEnvironment();
        testScrollMagic();
    });

    $(window).resize(function(){
        testEnvironment();
        detectIE();
        testScrollMagic();
    });
}(jQuery));

/*
 *	jQuery dotdotdot 1.8.1
 *
 *	Copyright (c) Fred Heusschen
 *	www.frebsite.nl
 *
 *	Plugin website:
 *	dotdotdot.frebsite.nl
 *
 *	Licensed under the MIT license.
 *	http://en.wikipedia.org/wiki/MIT_License
 */
!function(t,e){function n(t,e,n){var r=t.children(),o=!1;t.empty();for(var i=0,d=r.length;d>i;i++){var l=r.eq(i);if(t.append(l),n&&t.append(n),a(t,e)){l.remove(),o=!0;break}n&&n.detach()}return o}function r(e,n,i,d,l){var s=!1,c="a, table, thead, tbody, tfoot, tr, col, colgroup, object, embed, param, ol, ul, dl, blockquote, select, optgroup, option, textarea, script, style",u="script, .dotdotdot-keep";return e.contents().detach().each(function(){var h=this,f=t(h);if("undefined"==typeof h)return!0;if(f.is(u))e.append(f);else{if(s)return!0;e.append(f),!l||f.is(d.after)||f.find(d.after).length||e[e.is(c)?"after":"append"](l),a(i,d)&&(s=3==h.nodeType?o(f,n,i,d,l):r(f,n,i,d,l)),s||l&&l.detach()}}),n.addClass("is-truncated"),s}function o(e,n,r,o,d){var c=e[0];if(!c)return!1;var h=s(c),f=-1!==h.indexOf(" ")?" ":"　",p="letter"==o.wrap?"":f,g=h.split(p),v=-1,w=-1,b=0,y=g.length-1;for(o.fallbackToLetter&&0==b&&0==y&&(p="",g=h.split(p),y=g.length-1);y>=b&&(0!=b||0!=y);){var m=Math.floor((b+y)/2);if(m==w)break;w=m,l(c,g.slice(0,w+1).join(p)+o.ellipsis),r.children().each(function(){t(this).toggle().toggle()}),a(r,o)?(y=w,o.fallbackToLetter&&0==b&&0==y&&(p="",g=g[0].split(p),v=-1,w=-1,b=0,y=g.length-1)):(v=w,b=w)}if(-1==v||1==g.length&&0==g[0].length){var x=e.parent();e.detach();var C=d&&d.closest(x).length?d.length:0;x.contents().length>C?c=u(x.contents().eq(-1-C),n):(c=u(x,n,!0),C||x.detach()),c&&(h=i(s(c),o),l(c,h),C&&d&&t(c).parent().append(d))}else h=i(g.slice(0,v+1).join(p),o),l(c,h);return!0}function a(t,e){return t.innerHeight()>e.maxHeight}function i(e,n){for(;t.inArray(e.slice(-1),n.lastCharacter.remove)>-1;)e=e.slice(0,-1);return t.inArray(e.slice(-1),n.lastCharacter.noEllipsis)<0&&(e+=n.ellipsis),e}function d(t){return{width:t.innerWidth(),height:t.innerHeight()}}function l(t,e){t.innerText?t.innerText=e:t.nodeValue?t.nodeValue=e:t.textContent&&(t.textContent=e)}function s(t){return t.innerText?t.innerText:t.nodeValue?t.nodeValue:t.textContent?t.textContent:""}function c(t){do t=t.previousSibling;while(t&&1!==t.nodeType&&3!==t.nodeType);return t}function u(e,n,r){var o,a=e&&e[0];if(a){if(!r){if(3===a.nodeType)return a;if(t.trim(e.text()))return u(e.contents().last(),n)}for(o=c(a);!o;){if(e=e.parent(),e.is(n)||!e.length)return!1;o=c(e[0])}if(o)return u(t(o),n)}return!1}function h(e,n){return e?"string"==typeof e?(e=t(e,n),e.length?e:!1):e.jquery?e:!1:!1}function f(t){for(var e=t.innerHeight(),n=["paddingTop","paddingBottom"],r=0,o=n.length;o>r;r++){var a=parseInt(t.css(n[r]),10);isNaN(a)&&(a=0),e-=a}return e}if(!t.fn.dotdotdot){t.fn.dotdotdot=function(e){if(0==this.length)return t.fn.dotdotdot.debug('No element found for "'+this.selector+'".'),this;if(this.length>1)return this.each(function(){t(this).dotdotdot(e)});var o=this,i=o.contents();o.data("dotdotdot")&&o.trigger("destroy.dot"),o.data("dotdotdot-style",o.attr("style")||""),o.css("word-wrap","break-word"),"nowrap"===o.css("white-space")&&o.css("white-space","normal"),o.bind_events=function(){return o.bind("update.dot",function(e,d){switch(o.removeClass("is-truncated"),e.preventDefault(),e.stopPropagation(),typeof l.height){case"number":l.maxHeight=l.height;break;case"function":l.maxHeight=l.height.call(o[0]);break;default:l.maxHeight=f(o)}l.maxHeight+=l.tolerance,"undefined"!=typeof d&&(("string"==typeof d||"nodeType"in d&&1===d.nodeType)&&(d=t("<div />").append(d).contents()),d instanceof t&&(i=d)),g=o.wrapInner('<div class="dotdotdot" />').children(),g.contents().detach().end().append(i.clone(!0)).find("br").replaceWith("  <br />  ").end().css({height:"auto",width:"auto",border:"none",padding:0,margin:0});var c=!1,u=!1;return s.afterElement&&(c=s.afterElement.clone(!0),c.show(),s.afterElement.detach()),a(g,l)&&(u="children"==l.wrap?n(g,l,c):r(g,o,g,l,c)),g.replaceWith(g.contents()),g=null,t.isFunction(l.callback)&&l.callback.call(o[0],u,i),s.isTruncated=u,u}).bind("isTruncated.dot",function(t,e){return t.preventDefault(),t.stopPropagation(),"function"==typeof e&&e.call(o[0],s.isTruncated),s.isTruncated}).bind("originalContent.dot",function(t,e){return t.preventDefault(),t.stopPropagation(),"function"==typeof e&&e.call(o[0],i),i}).bind("destroy.dot",function(t){t.preventDefault(),t.stopPropagation(),o.unwatch().unbind_events().contents().detach().end().append(i).attr("style",o.data("dotdotdot-style")||"").removeClass("is-truncated").data("dotdotdot",!1)}),o},o.unbind_events=function(){return o.unbind(".dot"),o},o.watch=function(){if(o.unwatch(),"window"==l.watch){var e=t(window),n=e.width(),r=e.height();e.bind("resize.dot"+s.dotId,function(){n==e.width()&&r==e.height()&&l.windowResizeFix||(n=e.width(),r=e.height(),u&&clearInterval(u),u=setTimeout(function(){o.trigger("update.dot")},100))})}else c=d(o),u=setInterval(function(){if(o.is(":visible")){var t=d(o);c.width==t.width&&c.height==t.height||(o.trigger("update.dot"),c=t)}},500);return o},o.unwatch=function(){return t(window).unbind("resize.dot"+s.dotId),u&&clearInterval(u),o};var l=t.extend(!0,{},t.fn.dotdotdot.defaults,e),s={},c={},u=null,g=null;return l.lastCharacter.remove instanceof Array||(l.lastCharacter.remove=t.fn.dotdotdot.defaultArrays.lastCharacter.remove),l.lastCharacter.noEllipsis instanceof Array||(l.lastCharacter.noEllipsis=t.fn.dotdotdot.defaultArrays.lastCharacter.noEllipsis),s.afterElement=h(l.after,o),s.isTruncated=!1,s.dotId=p++,o.data("dotdotdot",!0).bind_events().trigger("update.dot"),l.watch&&o.watch(),o},t.fn.dotdotdot.defaults={ellipsis:"... ",wrap:"word",fallbackToLetter:!0,lastCharacter:{},tolerance:0,callback:null,after:null,height:null,watch:!1,windowResizeFix:!0},t.fn.dotdotdot.defaultArrays={lastCharacter:{remove:[" ","　",",",";",".","!","?"],noEllipsis:[]}},t.fn.dotdotdot.debug=function(t){};var p=1,g=t.fn.html;t.fn.html=function(n){return n!=e&&!t.isFunction(n)&&this.data("dotdotdot")?this.trigger("update",[n]):g.apply(this,arguments)};var v=t.fn.text;t.fn.text=function(n){return n!=e&&!t.isFunction(n)&&this.data("dotdotdot")?(n=t("<div />").text(n).html(),this.trigger("update",[n])):v.apply(this,arguments)}}}(jQuery),jQuery(document).ready(function(t){t(".dot-ellipsis").each(function(){var e=t(this).hasClass("dot-resize-update"),n=t(this).hasClass("dot-timer-update"),r=0,o=t(this).attr("class").split(/\s+/);t.each(o,function(t,e){var n=e.match(/^dot-height-(\d+)$/);null!==n&&(r=Number(n[1]))});var a=new Object;n&&(a.watch=!0),e&&(a.watch="window"),r>0&&(a.height=r),t(this).dotdotdot(a)})}),jQuery(window).load(function(){jQuery(".dot-ellipsis.dot-load-update").trigger("update.dot")});
/*!
 * Masonry PACKAGED v4.2.0
 * Cascading grid layout library
 * http://masonry.desandro.com
 * MIT License
 * by David DeSandro
 */

! function(t, e) { "function" == typeof define && define.amd ? define("jquery-bridget/jquery-bridget", ["jquery"], function(i) { return e(t, i) }) : "object" == typeof module && module.exports ? module.exports = e(t, require("jquery")) : t.jQueryBridget = e(t, t.jQuery) }(window, function(t, e) { "use strict";

    function i(i, r, a) {
        function h(t, e, n) { var o, r = "$()." + i + '("' + e + '")'; return t.each(function(t, h) { var u = a.data(h, i); if (!u) return void s(i + " not initialized. Cannot call methods, i.e. " + r); var d = u[e]; if (!d || "_" == e.charAt(0)) return void s(r + " is not a valid method"); var l = d.apply(u, n);
                o = void 0 === o ? l : o }), void 0 !== o ? o : t }

        function u(t, e) { t.each(function(t, n) { var o = a.data(n, i);
                o ? (o.option(e), o._init()) : (o = new r(n, e), a.data(n, i, o)) }) }
        a = a || e || t.jQuery, a && (r.prototype.option || (r.prototype.option = function(t) { a.isPlainObject(t) && (this.options = a.extend(!0, this.options, t)) }), a.fn[i] = function(t) { if ("string" == typeof t) { var e = o.call(arguments, 1); return h(this, t, e) } return u(this, t), this }, n(a)) }

    function n(t) {!t || t && t.bridget || (t.bridget = i) } var o = Array.prototype.slice,
        r = t.console,
        s = "undefined" == typeof r ? function() {} : function(t) { r.error(t) }; return n(e || t.jQuery), i }),
function(t, e) { "function" == typeof define && define.amd ? define("ev-emitter/ev-emitter", e) : "object" == typeof module && module.exports ? module.exports = e() : t.EvEmitter = e() }("undefined" != typeof window ? window : this, function() {
    function t() {} var e = t.prototype; return e.on = function(t, e) { if (t && e) { var i = this._events = this._events || {},
                n = i[t] = i[t] || []; return -1 == n.indexOf(e) && n.push(e), this } }, e.once = function(t, e) { if (t && e) { this.on(t, e); var i = this._onceEvents = this._onceEvents || {},
                n = i[t] = i[t] || {}; return n[e] = !0, this } }, e.off = function(t, e) { var i = this._events && this._events[t]; if (i && i.length) { var n = i.indexOf(e); return -1 != n && i.splice(n, 1), this } }, e.emitEvent = function(t, e) { var i = this._events && this._events[t]; if (i && i.length) { var n = 0,
                o = i[n];
            e = e || []; for (var r = this._onceEvents && this._onceEvents[t]; o;) { var s = r && r[o];
                s && (this.off(t, o), delete r[o]), o.apply(this, e), n += s ? 0 : 1, o = i[n] } return this } }, t }),
function(t, e) { "use strict"; "function" == typeof define && define.amd ? define("get-size/get-size", [], function() { return e() }) : "object" == typeof module && module.exports ? module.exports = e() : t.getSize = e() }(window, function() { "use strict";

    function t(t) { var e = parseFloat(t),
            i = -1 == t.indexOf("%") && !isNaN(e); return i && e }

    function e() {}

    function i() { for (var t = { width: 0, height: 0, innerWidth: 0, innerHeight: 0, outerWidth: 0, outerHeight: 0 }, e = 0; u > e; e++) { var i = h[e];
            t[i] = 0 } return t }

    function n(t) { var e = getComputedStyle(t); return e || a("Style returned " + e + ". Are you running this code in a hidden iframe on Firefox? See http://bit.ly/getsizebug1"), e }

    function o() { if (!d) { d = !0; var e = document.createElement("div");
            e.style.width = "200px", e.style.padding = "1px 2px 3px 4px", e.style.borderStyle = "solid", e.style.borderWidth = "1px 2px 3px 4px", e.style.boxSizing = "border-box"; var i = document.body || document.documentElement;
            i.appendChild(e); var o = n(e);
            r.isBoxSizeOuter = s = 200 == t(o.width), i.removeChild(e) } }

    function r(e) { if (o(), "string" == typeof e && (e = document.querySelector(e)), e && "object" == typeof e && e.nodeType) { var r = n(e); if ("none" == r.display) return i(); var a = {};
            a.width = e.offsetWidth, a.height = e.offsetHeight; for (var d = a.isBorderBox = "border-box" == r.boxSizing, l = 0; u > l; l++) { var c = h[l],
                    f = r[c],
                    m = parseFloat(f);
                a[c] = isNaN(m) ? 0 : m } var p = a.paddingLeft + a.paddingRight,
                g = a.paddingTop + a.paddingBottom,
                y = a.marginLeft + a.marginRight,
                v = a.marginTop + a.marginBottom,
                _ = a.borderLeftWidth + a.borderRightWidth,
                z = a.borderTopWidth + a.borderBottomWidth,
                E = d && s,
                b = t(r.width);
            b !== !1 && (a.width = b + (E ? 0 : p + _)); var x = t(r.height); return x !== !1 && (a.height = x + (E ? 0 : g + z)), a.innerWidth = a.width - (p + _), a.innerHeight = a.height - (g + z), a.outerWidth = a.width + y, a.outerHeight = a.height + v, a } } var s, a = "undefined" == typeof console ? e : function(t) { console.error(t) },
        h = ["paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "marginLeft", "marginRight", "marginTop", "marginBottom", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"],
        u = h.length,
        d = !1; return r }),
function(t, e) { "use strict"; "function" == typeof define && define.amd ? define("desandro-matches-selector/matches-selector", e) : "object" == typeof module && module.exports ? module.exports = e() : t.matchesSelector = e() }(window, function() { "use strict"; var t = function() { var t = window.Element.prototype; if (t.matches) return "matches"; if (t.matchesSelector) return "matchesSelector"; for (var e = ["webkit", "moz", "ms", "o"], i = 0; i < e.length; i++) { var n = e[i],
                o = n + "MatchesSelector"; if (t[o]) return o } }(); return function(e, i) { return e[t](i) } }),
function(t, e) { "function" == typeof define && define.amd ? define("fizzy-ui-utils/utils", ["desandro-matches-selector/matches-selector"], function(i) { return e(t, i) }) : "object" == typeof module && module.exports ? module.exports = e(t, require("desandro-matches-selector")) : t.fizzyUIUtils = e(t, t.matchesSelector) }(window, function(t, e) { var i = {};
    i.extend = function(t, e) { for (var i in e) t[i] = e[i]; return t }, i.modulo = function(t, e) { return (t % e + e) % e }, i.makeArray = function(t) { var e = []; if (Array.isArray(t)) e = t;
        else if (t && "object" == typeof t && "number" == typeof t.length)
            for (var i = 0; i < t.length; i++) e.push(t[i]);
        else e.push(t); return e }, i.removeFrom = function(t, e) { var i = t.indexOf(e); - 1 != i && t.splice(i, 1) }, i.getParent = function(t, i) { for (; t != document.body;)
            if (t = t.parentNode, e(t, i)) return t }, i.getQueryElement = function(t) { return "string" == typeof t ? document.querySelector(t) : t }, i.handleEvent = function(t) { var e = "on" + t.type;
        this[e] && this[e](t) }, i.filterFindElements = function(t, n) { t = i.makeArray(t); var o = []; return t.forEach(function(t) { if (t instanceof HTMLElement) { if (!n) return void o.push(t);
                e(t, n) && o.push(t); for (var i = t.querySelectorAll(n), r = 0; r < i.length; r++) o.push(i[r]) } }), o }, i.debounceMethod = function(t, e, i) { var n = t.prototype[e],
            o = e + "Timeout";
        t.prototype[e] = function() { var t = this[o];
            t && clearTimeout(t); var e = arguments,
                r = this;
            this[o] = setTimeout(function() { n.apply(r, e), delete r[o] }, i || 100) } }, i.docReady = function(t) { var e = document.readyState; "complete" == e || "interactive" == e ? setTimeout(t) : document.addEventListener("DOMContentLoaded", t) }, i.toDashed = function(t) { return t.replace(/(.)([A-Z])/g, function(t, e, i) { return e + "-" + i }).toLowerCase() }; var n = t.console; return i.htmlInit = function(e, o) { i.docReady(function() { var r = i.toDashed(o),
                s = "data-" + r,
                a = document.querySelectorAll("[" + s + "]"),
                h = document.querySelectorAll(".js-" + r),
                u = i.makeArray(a).concat(i.makeArray(h)),
                d = s + "-options",
                l = t.jQuery;
            u.forEach(function(t) { var i, r = t.getAttribute(s) || t.getAttribute(d); try { i = r && JSON.parse(r) } catch (a) { return void(n && n.error("Error parsing " + s + " on " + t.className + ": " + a)) } var h = new e(t, i);
                l && l.data(t, o, h) }) }) }, i }),
function(t, e) { "function" == typeof define && define.amd ? define("outlayer/item", ["ev-emitter/ev-emitter", "get-size/get-size"], e) : "object" == typeof module && module.exports ? module.exports = e(require("ev-emitter"), require("get-size")) : (t.Outlayer = {}, t.Outlayer.Item = e(t.EvEmitter, t.getSize)) }(window, function(t, e) { "use strict";

    function i(t) { for (var e in t) return !1; return e = null, !0 }

    function n(t, e) { t && (this.element = t, this.layout = e, this.position = { x: 0, y: 0 }, this._create()) }

    function o(t) { return t.replace(/([A-Z])/g, function(t) { return "-" + t.toLowerCase() }) } var r = document.documentElement.style,
        s = "string" == typeof r.transition ? "transition" : "WebkitTransition",
        a = "string" == typeof r.transform ? "transform" : "WebkitTransform",
        h = { WebkitTransition: "webkitTransitionEnd", transition: "transitionend" }[s],
        u = { transform: a, transition: s, transitionDuration: s + "Duration", transitionProperty: s + "Property", transitionDelay: s + "Delay" },
        d = n.prototype = Object.create(t.prototype);
    d.constructor = n, d._create = function() { this._transn = { ingProperties: {}, clean: {}, onEnd: {} }, this.css({ position: "absolute" }) }, d.handleEvent = function(t) { var e = "on" + t.type;
        this[e] && this[e](t) }, d.getSize = function() { this.size = e(this.element) }, d.css = function(t) { var e = this.element.style; for (var i in t) { var n = u[i] || i;
            e[n] = t[i] } }, d.getPosition = function() { var t = getComputedStyle(this.element),
            e = this.layout._getOption("originLeft"),
            i = this.layout._getOption("originTop"),
            n = t[e ? "left" : "right"],
            o = t[i ? "top" : "bottom"],
            r = this.layout.size,
            s = -1 != n.indexOf("%") ? parseFloat(n) / 100 * r.width : parseInt(n, 10),
            a = -1 != o.indexOf("%") ? parseFloat(o) / 100 * r.height : parseInt(o, 10);
        s = isNaN(s) ? 0 : s, a = isNaN(a) ? 0 : a, s -= e ? r.paddingLeft : r.paddingRight, a -= i ? r.paddingTop : r.paddingBottom, this.position.x = s, this.position.y = a }, d.layoutPosition = function() { var t = this.layout.size,
            e = {},
            i = this.layout._getOption("originLeft"),
            n = this.layout._getOption("originTop"),
            o = i ? "paddingLeft" : "paddingRight",
            r = i ? "left" : "right",
            s = i ? "right" : "left",
            a = this.position.x + t[o];
        e[r] = this.getXValue(a), e[s] = ""; var h = n ? "paddingTop" : "paddingBottom",
            u = n ? "top" : "bottom",
            d = n ? "bottom" : "top",
            l = this.position.y + t[h];
        e[u] = this.getYValue(l), e[d] = "", this.css(e), this.emitEvent("layout", [this]) }, d.getXValue = function(t) { var e = this.layout._getOption("horizontal"); return this.layout.options.percentPosition && !e ? t / this.layout.size.width * 100 + "%" : t + "px" }, d.getYValue = function(t) { var e = this.layout._getOption("horizontal"); return this.layout.options.percentPosition && e ? t / this.layout.size.height * 100 + "%" : t + "px" }, d._transitionTo = function(t, e) { this.getPosition(); var i = this.position.x,
            n = this.position.y,
            o = parseInt(t, 10),
            r = parseInt(e, 10),
            s = o === this.position.x && r === this.position.y; if (this.setPosition(t, e), s && !this.isTransitioning) return void this.layoutPosition(); var a = t - i,
            h = e - n,
            u = {};
        u.transform = this.getTranslate(a, h), this.transition({ to: u, onTransitionEnd: { transform: this.layoutPosition }, isCleaning: !0 }) }, d.getTranslate = function(t, e) { var i = this.layout._getOption("originLeft"),
            n = this.layout._getOption("originTop"); return t = i ? t : -t, e = n ? e : -e, "translate3d(" + t + "px, " + e + "px, 0)" }, d.goTo = function(t, e) { this.setPosition(t, e), this.layoutPosition() }, d.moveTo = d._transitionTo, d.setPosition = function(t, e) { this.position.x = parseInt(t, 10), this.position.y = parseInt(e, 10) }, d._nonTransition = function(t) { this.css(t.to), t.isCleaning && this._removeStyles(t.to); for (var e in t.onTransitionEnd) t.onTransitionEnd[e].call(this) }, d.transition = function(t) { if (!parseFloat(this.layout.options.transitionDuration)) return void this._nonTransition(t); var e = this._transn; for (var i in t.onTransitionEnd) e.onEnd[i] = t.onTransitionEnd[i]; for (i in t.to) e.ingProperties[i] = !0, t.isCleaning && (e.clean[i] = !0); if (t.from) { this.css(t.from); var n = this.element.offsetHeight;
            n = null }
        this.enableTransition(t.to), this.css(t.to), this.isTransitioning = !0 }; var l = "opacity," + o(a);
    d.enableTransition = function() { if (!this.isTransitioning) { var t = this.layout.options.transitionDuration;
            t = "number" == typeof t ? t + "ms" : t, this.css({ transitionProperty: l, transitionDuration: t, transitionDelay: this.staggerDelay || 0 }), this.element.addEventListener(h, this, !1) } }, d.onwebkitTransitionEnd = function(t) { this.ontransitionend(t) }, d.onotransitionend = function(t) { this.ontransitionend(t) }; var c = { "-webkit-transform": "transform" };
    d.ontransitionend = function(t) { if (t.target === this.element) { var e = this._transn,
                n = c[t.propertyName] || t.propertyName; if (delete e.ingProperties[n], i(e.ingProperties) && this.disableTransition(), n in e.clean && (this.element.style[t.propertyName] = "", delete e.clean[n]), n in e.onEnd) { var o = e.onEnd[n];
                o.call(this), delete e.onEnd[n] }
            this.emitEvent("transitionEnd", [this]) } }, d.disableTransition = function() { this.removeTransitionStyles(), this.element.removeEventListener(h, this, !1), this.isTransitioning = !1 }, d._removeStyles = function(t) { var e = {}; for (var i in t) e[i] = "";
        this.css(e) }; var f = { transitionProperty: "", transitionDuration: "", transitionDelay: "" }; return d.removeTransitionStyles = function() { this.css(f) }, d.stagger = function(t) { t = isNaN(t) ? 0 : t, this.staggerDelay = t + "ms" }, d.removeElem = function() { this.element.parentNode.removeChild(this.element), this.css({ display: "" }), this.emitEvent("remove", [this]) }, d.remove = function() { return s && parseFloat(this.layout.options.transitionDuration) ? (this.once("transitionEnd", function() { this.removeElem() }), void this.hide()) : void this.removeElem() }, d.reveal = function() { delete this.isHidden, this.css({ display: "" }); var t = this.layout.options,
            e = {},
            i = this.getHideRevealTransitionEndProperty("visibleStyle");
        e[i] = this.onRevealTransitionEnd, this.transition({ from: t.hiddenStyle, to: t.visibleStyle, isCleaning: !0, onTransitionEnd: e }) }, d.onRevealTransitionEnd = function() { this.isHidden || this.emitEvent("reveal") }, d.getHideRevealTransitionEndProperty = function(t) { var e = this.layout.options[t]; if (e.opacity) return "opacity"; for (var i in e) return i }, d.hide = function() { this.isHidden = !0, this.css({ display: "" }); var t = this.layout.options,
            e = {},
            i = this.getHideRevealTransitionEndProperty("hiddenStyle");
        e[i] = this.onHideTransitionEnd, this.transition({ from: t.visibleStyle, to: t.hiddenStyle, isCleaning: !0, onTransitionEnd: e }) }, d.onHideTransitionEnd = function() { this.isHidden && (this.css({ display: "none" }), this.emitEvent("hide")) }, d.destroy = function() { this.css({ position: "", left: "", right: "", top: "", bottom: "", transition: "", transform: "" }) }, n }),
function(t, e) { "use strict"; "function" == typeof define && define.amd ? define("outlayer/outlayer", ["ev-emitter/ev-emitter", "get-size/get-size", "fizzy-ui-utils/utils", "./item"], function(i, n, o, r) { return e(t, i, n, o, r) }) : "object" == typeof module && module.exports ? module.exports = e(t, require("ev-emitter"), require("get-size"), require("fizzy-ui-utils"), require("./item")) : t.Outlayer = e(t, t.EvEmitter, t.getSize, t.fizzyUIUtils, t.Outlayer.Item) }(window, function(t, e, i, n, o) { "use strict";

    function r(t, e) { var i = n.getQueryElement(t); if (!i) return void(h && h.error("Bad element for " + this.constructor.namespace + ": " + (i || t)));
        this.element = i, u && (this.$element = u(this.element)), this.options = n.extend({}, this.constructor.defaults), this.option(e); var o = ++l;
        this.element.outlayerGUID = o, c[o] = this, this._create(); var r = this._getOption("initLayout");
        r && this.layout() }

    function s(t) {
        function e() { t.apply(this, arguments) } return e.prototype = Object.create(t.prototype), e.prototype.constructor = e, e }

    function a(t) { if ("number" == typeof t) return t; var e = t.match(/(^\d*\.?\d*)(\w*)/),
            i = e && e[1],
            n = e && e[2]; if (!i.length) return 0;
        i = parseFloat(i); var o = m[n] || 1; return i * o } var h = t.console,
        u = t.jQuery,
        d = function() {},
        l = 0,
        c = {};
    r.namespace = "outlayer", r.Item = o, r.defaults = { containerStyle: { position: "relative" }, initLayout: !0, originLeft: !0, originTop: !0, resize: !0, resizeContainer: !0, transitionDuration: "0.4s", hiddenStyle: { opacity: 0, transform: "scale(0.001)" }, visibleStyle: { opacity: 1, transform: "scale(1)" } }; var f = r.prototype;
    n.extend(f, e.prototype), f.option = function(t) { n.extend(this.options, t) }, f._getOption = function(t) { var e = this.constructor.compatOptions[t]; return e && void 0 !== this.options[e] ? this.options[e] : this.options[t] }, r.compatOptions = { initLayout: "isInitLayout", horizontal: "isHorizontal", layoutInstant: "isLayoutInstant", originLeft: "isOriginLeft", originTop: "isOriginTop", resize: "isResizeBound", resizeContainer: "isResizingContainer" }, f._create = function() { this.reloadItems(), this.stamps = [], this.stamp(this.options.stamp), n.extend(this.element.style, this.options.containerStyle); var t = this._getOption("resize");
        t && this.bindResize() }, f.reloadItems = function() { this.items = this._itemize(this.element.children) }, f._itemize = function(t) { for (var e = this._filterFindItemElements(t), i = this.constructor.Item, n = [], o = 0; o < e.length; o++) { var r = e[o],
                s = new i(r, this);
            n.push(s) } return n }, f._filterFindItemElements = function(t) { return n.filterFindElements(t, this.options.itemSelector) }, f.getItemElements = function() { return this.items.map(function(t) { return t.element }) }, f.layout = function() { this._resetLayout(), this._manageStamps(); var t = this._getOption("layoutInstant"),
            e = void 0 !== t ? t : !this._isLayoutInited;
        this.layoutItems(this.items, e), this._isLayoutInited = !0 }, f._init = f.layout, f._resetLayout = function() { this.getSize() }, f.getSize = function() { this.size = i(this.element) }, f._getMeasurement = function(t, e) { var n, o = this.options[t];
        o ? ("string" == typeof o ? n = this.element.querySelector(o) : o instanceof HTMLElement && (n = o), this[t] = n ? i(n)[e] : o) : this[t] = 0 }, f.layoutItems = function(t, e) { t = this._getItemsForLayout(t), this._layoutItems(t, e), this._postLayout() }, f._getItemsForLayout = function(t) { return t.filter(function(t) { return !t.isIgnored }) }, f._layoutItems = function(t, e) { if (this._emitCompleteOnItems("layout", t), t && t.length) { var i = [];
            t.forEach(function(t) { var n = this._getItemLayoutPosition(t);
                n.item = t, n.isInstant = e || t.isLayoutInstant, i.push(n) }, this), this._processLayoutQueue(i) } }, f._getItemLayoutPosition = function() { return { x: 0, y: 0 } }, f._processLayoutQueue = function(t) { this.updateStagger(), t.forEach(function(t, e) { this._positionItem(t.item, t.x, t.y, t.isInstant, e) }, this) }, f.updateStagger = function() { var t = this.options.stagger; return null === t || void 0 === t ? void(this.stagger = 0) : (this.stagger = a(t), this.stagger) }, f._positionItem = function(t, e, i, n, o) { n ? t.goTo(e, i) : (t.stagger(o * this.stagger), t.moveTo(e, i)) }, f._postLayout = function() { this.resizeContainer() }, f.resizeContainer = function() { var t = this._getOption("resizeContainer"); if (t) { var e = this._getContainerSize();
            e && (this._setContainerMeasure(e.width, !0), this._setContainerMeasure(e.height, !1)) } }, f._getContainerSize = d, f._setContainerMeasure = function(t, e) { if (void 0 !== t) { var i = this.size;
            i.isBorderBox && (t += e ? i.paddingLeft + i.paddingRight + i.borderLeftWidth + i.borderRightWidth : i.paddingBottom + i.paddingTop + i.borderTopWidth + i.borderBottomWidth), t = Math.max(t, 0), this.element.style[e ? "width" : "height"] = t + "px" } }, f._emitCompleteOnItems = function(t, e) {
        function i() { o.dispatchEvent(t + "Complete", null, [e]) }

        function n() { s++, s == r && i() } var o = this,
            r = e.length; if (!e || !r) return void i(); var s = 0;
        e.forEach(function(e) { e.once(t, n) }) }, f.dispatchEvent = function(t, e, i) { var n = e ? [e].concat(i) : i; if (this.emitEvent(t, n), u)
            if (this.$element = this.$element || u(this.element), e) { var o = u.Event(e);
                o.type = t, this.$element.trigger(o, i) } else this.$element.trigger(t, i) }, f.ignore = function(t) { var e = this.getItem(t);
        e && (e.isIgnored = !0) }, f.unignore = function(t) { var e = this.getItem(t);
        e && delete e.isIgnored }, f.stamp = function(t) { t = this._find(t), t && (this.stamps = this.stamps.concat(t), t.forEach(this.ignore, this)) }, f.unstamp = function(t) { t = this._find(t), t && t.forEach(function(t) { n.removeFrom(this.stamps, t), this.unignore(t) }, this) }, f._find = function(t) { return t ? ("string" == typeof t && (t = this.element.querySelectorAll(t)), t = n.makeArray(t)) : void 0 }, f._manageStamps = function() { this.stamps && this.stamps.length && (this._getBoundingRect(), this.stamps.forEach(this._manageStamp, this)) }, f._getBoundingRect = function() { var t = this.element.getBoundingClientRect(),
            e = this.size;
        this._boundingRect = { left: t.left + e.paddingLeft + e.borderLeftWidth, top: t.top + e.paddingTop + e.borderTopWidth, right: t.right - (e.paddingRight + e.borderRightWidth), bottom: t.bottom - (e.paddingBottom + e.borderBottomWidth) } }, f._manageStamp = d, f._getElementOffset = function(t) { var e = t.getBoundingClientRect(),
            n = this._boundingRect,
            o = i(t),
            r = { left: e.left - n.left - o.marginLeft, top: e.top - n.top - o.marginTop, right: n.right - e.right - o.marginRight, bottom: n.bottom - e.bottom - o.marginBottom }; return r }, f.handleEvent = n.handleEvent, f.bindResize = function() { t.addEventListener("resize", this), this.isResizeBound = !0 }, f.unbindResize = function() { t.removeEventListener("resize", this), this.isResizeBound = !1 }, f.onresize = function() { this.resize() }, n.debounceMethod(r, "onresize", 100), f.resize = function() { this.isResizeBound && this.needsResizeLayout() && this.layout() }, f.needsResizeLayout = function() { var t = i(this.element),
            e = this.size && t; return e && t.innerWidth !== this.size.innerWidth }, f.addItems = function(t) { var e = this._itemize(t); return e.length && (this.items = this.items.concat(e)), e }, f.appended = function(t) { var e = this.addItems(t);
        e.length && (this.layoutItems(e, !0), this.reveal(e)) }, f.prepended = function(t) { var e = this._itemize(t); if (e.length) { var i = this.items.slice(0);
            this.items = e.concat(i), this._resetLayout(), this._manageStamps(), this.layoutItems(e, !0), this.reveal(e), this.layoutItems(i) } }, f.reveal = function(t) { if (this._emitCompleteOnItems("reveal", t), t && t.length) { var e = this.updateStagger();
            t.forEach(function(t, i) { t.stagger(i * e), t.reveal() }) } }, f.hide = function(t) { if (this._emitCompleteOnItems("hide", t), t && t.length) { var e = this.updateStagger();
            t.forEach(function(t, i) { t.stagger(i * e), t.hide() }) } }, f.revealItemElements = function(t) { var e = this.getItems(t);
        this.reveal(e) }, f.hideItemElements = function(t) { var e = this.getItems(t);
        this.hide(e) }, f.getItem = function(t) { for (var e = 0; e < this.items.length; e++) { var i = this.items[e]; if (i.element == t) return i } }, f.getItems = function(t) { t = n.makeArray(t); var e = []; return t.forEach(function(t) { var i = this.getItem(t);
            i && e.push(i) }, this), e }, f.remove = function(t) { var e = this.getItems(t);
        this._emitCompleteOnItems("remove", e), e && e.length && e.forEach(function(t) { t.remove(), n.removeFrom(this.items, t) }, this) }, f.destroy = function() { var t = this.element.style;
        t.height = "", t.position = "", t.width = "", this.items.forEach(function(t) { t.destroy() }), this.unbindResize(); var e = this.element.outlayerGUID;
        delete c[e], delete this.element.outlayerGUID, u && u.removeData(this.element, this.constructor.namespace) }, r.data = function(t) { t = n.getQueryElement(t); var e = t && t.outlayerGUID; return e && c[e] }, r.create = function(t, e) { var i = s(r); return i.defaults = n.extend({}, r.defaults), n.extend(i.defaults, e), i.compatOptions = n.extend({}, r.compatOptions), i.namespace = t, i.data = r.data, i.Item = s(o), n.htmlInit(i, t), u && u.bridget && u.bridget(t, i), i }; var m = { ms: 1, s: 1e3 }; return r.Item = o, r }),
function(t, e) { "function" == typeof define && define.amd ? define(["outlayer/outlayer", "get-size/get-size"], e) : "object" == typeof module && module.exports ? module.exports = e(require("outlayer"), require("get-size")) : t.Masonry = e(t.Outlayer, t.getSize) }(window, function(t, e) { var i = t.create("masonry");
    i.compatOptions.fitWidth = "isFitWidth"; var n = i.prototype; return n._resetLayout = function() { this.getSize(), this._getMeasurement("columnWidth", "outerWidth"), this._getMeasurement("gutter", "outerWidth"), this.measureColumns(), this.colYs = []; for (var t = 0; t < this.cols; t++) this.colYs.push(0);
        this.maxY = 0, this.horizontalColIndex = 0 }, n.measureColumns = function() { if (this.getContainerWidth(), !this.columnWidth) { var t = this.items[0],
                i = t && t.element;
            this.columnWidth = i && e(i).outerWidth || this.containerWidth } var n = this.columnWidth += this.gutter,
            o = this.containerWidth + this.gutter,
            r = o / n,
            s = n - o % n,
            a = s && 1 > s ? "round" : "floor";
        r = Math[a](r), this.cols = Math.max(r, 1) }, n.getContainerWidth = function() { var t = this._getOption("fitWidth"),
            i = t ? this.element.parentNode : this.element,
            n = e(i);
        this.containerWidth = n && n.innerWidth }, n._getItemLayoutPosition = function(t) { t.getSize(); var e = t.size.outerWidth % this.columnWidth,
            i = e && 1 > e ? "round" : "ceil",
            n = Math[i](t.size.outerWidth / this.columnWidth);
        n = Math.min(n, this.cols); for (var o = this.options.horizontalOrder ? "_getHorizontalColPosition" : "_getTopColPosition", r = this[o](n, t), s = { x: this.columnWidth * r.col, y: r.y }, a = r.y + t.size.outerHeight, h = n + r.col, u = r.col; h > u; u++) this.colYs[u] = a; return s }, n._getTopColPosition = function(t) { var e = this._getTopColGroup(t),
            i = Math.min.apply(Math, e); return { col: e.indexOf(i), y: i } }, n._getTopColGroup = function(t) { if (2 > t) return this.colYs; for (var e = [], i = this.cols + 1 - t, n = 0; i > n; n++) e[n] = this._getColGroupY(n, t); return e }, n._getColGroupY = function(t, e) { if (2 > e) return this.colYs[t]; var i = this.colYs.slice(t, t + e); return Math.max.apply(Math, i) }, n._getHorizontalColPosition = function(t, e) { var i = this.horizontalColIndex % this.cols,
            n = t > 1 && i + t > this.cols;
        i = n ? 0 : i; var o = e.size.outerWidth && e.size.outerHeight; return this.horizontalColIndex = o ? i + t : this.horizontalColIndex, { col: i, y: this._getColGroupY(i, t) } }, n._manageStamp = function(t) { var i = e(t),
            n = this._getElementOffset(t),
            o = this._getOption("originLeft"),
            r = o ? n.left : n.right,
            s = r + i.outerWidth,
            a = Math.floor(r / this.columnWidth);
        a = Math.max(0, a); var h = Math.floor(s / this.columnWidth);
        h -= s % this.columnWidth ? 0 : 1, h = Math.min(this.cols - 1, h); for (var u = this._getOption("originTop"), d = (u ? n.top : n.bottom) + i.outerHeight, l = a; h >= l; l++) this.colYs[l] = Math.max(d, this.colYs[l]) }, n._getContainerSize = function() { this.maxY = Math.max.apply(Math, this.colYs); var t = { height: this.maxY }; return this._getOption("fitWidth") && (t.width = this._getContainerFitWidth()), t }, n._getContainerFitWidth = function() { for (var t = 0, e = this.cols; --e && 0 === this.colYs[e];) t++; return (this.cols - t) * this.columnWidth - this.gutter }, n.needsResizeLayout = function() { var t = this.containerWidth; return this.getContainerWidth(), t != this.containerWidth }, i });
/**
 * Library megaSlider (script Custom) pour faire fonctionner les sliders Socials et Une 
 */



/**
 * Get sources, create an array for each category and save them inside the conf object
 * @param {*} source 
 * @param {*} conf 
 */
function getSources(source, conf){
    conf.source_tous = source; // Table for category 'tous'
    // Build tables for each category
    source.forEach(function(element, index) {
        var category = element.category;
        if(!conf['source_'+category]){
            conf['source_'+category] = [];
        }
        conf['source_'+category].push(element);
    }, this);
}


/**
 * Create a DomNode element, cloned from the templates in index.html and populated from category flow
 * @param {string} category       - Filter category
 * @param {Object} conf           - Configuration object
 */
function majItems(category, conf){
    var source = conf['source_'+category] !== undefined ? conf['source_'+category] : [],
        clones = [];
    source.forEach(function(element, index) {
        var item_category = element.category; // Get element category
        var $template;
        if(conf['$template_'+item_category]){
            $template = conf['$template_'+item_category]; // Get element template*
        }else{
            console.log('ERROR: Le template pour la catégorie ['+item_category+'] est introuvable');
        }
        var clone_html = setTemplateInfos(element, item_category, $template, conf); // Set informations from source element inside the cloned template
        if(element.is_Big){
            clones.unshift(clone_html);
        }else{
            clones.push(clone_html);
        }
    }, this);
    addToSlider(conf, clones);
}

/**
 * Update infos like title, description etc, inside the cloned template that will be used to populate the slider
 * @param {object} infos - Infos of the element to put in the slider
 * @param {string} category - Category of the element
 * @param {jQueryObject} $clone - Clone from category dedicated template
 * @return {jQueryObject}
 */
function setTemplateInfos(infos, category, $template, conf){
    var $new_template = $template.clone();
    if(infos.picture != undefined && infos.picture != '' && conf.hasPicture_class != undefined){
        $new_template.addClass(conf.hasPicture_class);
    }
    if(infos.ville != undefined && infos.ville != '' && conf.hasVille_class != undefined){
        $new_template.addClass(conf.hasVille_class);
        $new_template.find('.lead').attr('data-dot', 3);
    }
    if(infos.is_Big && conf.is_Big_Class != undefined){
        $new_template.addClass(conf.is_Big_Class);
    }
    var clone = $new_template[0].outerHTML;
    
    for(var info in infos){
        var toReplace = '__'+info+'__';
        toReplace = new RegExp(toReplace, "g");
        clone = clone.replace(toReplace, infos[info]);
    }
    return clone;
}

/**
 * Add elements to slider, divided into pages of 8 elements
 * @param {Object} conf 
 * @param {Array} elements - Array of all elements to add
 */
function addToSlider(conf, elements){
    var nodeStringToAdd = '<div class="'+conf.pages_class+'">';
    var itemSeparator = 3;
    if(elements[0].indexOf('big') != -1){
        itemSeparator = 4;
        nodeStringToAdd = '<div class="'+conf.pages_class + ' '+conf.is_Big_Class+'">';
    }
    if(environment == 'tablette'){
        itemSeparator = 2;
    }else if(environment == 'mobile'){
        itemSeparator = 1;
    }
    
    elements.forEach(function(element, index) {
        if(index%itemSeparator == 0 && index > 0){
            nodeStringToAdd += '</div><div class="'+conf.pages_class
            if(elements[0].indexOf('big') != -1){
                nodeStringToAdd += ' '+conf.is_Big_Class;
            }
            nodeStringToAdd += '"">';
        }
        nodeStringToAdd += element;
    }, this);
    nodeStringToAdd += '</div>';
    conf.$slider.html(nodeStringToAdd);
}


/**
 * Remove all items from slider
 * @param {Object} conf - Configuration object
 */
function removeAllItems(conf){
    conf.$slider.find('.'+conf.pages_class).remove();
}

/**
 * Build Slider 
 * @param {Object} conf - Configuration object
 */
function buildSlider(conf){
    conf.$slider.addClass('owl-carousel').owlCarousel({
        margin : 20,
        items: 1,
        smartSpeed: 800,
        onInitialized: function(e){
            manageNavigationDisplay(e, conf.$prev, conf.$next);
            attachCustomNavEvents(conf.$slider, conf.$prev, conf.$next);
        },
        onTranslate : function(e){
            manageNavigationDisplay(e, conf.$prev, conf.$next);
        }
    });
}

/**
 * Destroy Slider 
 * @param {Object} conf - Configuration object
 */
function destroySlider(conf){
    conf.$slider.trigger('destroy.owl.carousel');
}

/**
 * Suite d'appels permettant de construire un mega-slider à la une en fonction d'une catégorie
 * @param {string} category - Catégorie à afficher
 */
function megaSlider(slider, category){
    destroySlider(slider);
    removeAllItems(slider);
    majItems(category, slider);
    buildSlider(slider);
    dot();
}

// (function ($) {
    // function manageNavigationDisplay(e, $prev, $next){ // Ne fonctionne pas avec loop: true
    //     if(e.item.index == 0){
    //         $prev.addClass('disabled');
    //         $next.removeClass('disabled');
    //     }else if(e.item.index == (e.item.count - 1) || e.page.index == (e.page.count - 1)){
    //         $prev.removeClass('disabled');
    //         $next.addClass('disabled');
    //     }else{
    //         $prev.removeClass('disabled');
    //         $next.removeClass('disabled');
    //     }
    // }
    function manageNavigationDisplay(e, $prev, $next){ // Ne fonctionne pas avec loop: true
        if(e.item.index == 0){
            $prev.addClass('disabled');
        }else{
            $prev.removeClass('disabled');
        }
        if(e.item.index == (e.item.count - 1)){
            $next.addClass('disabled');
        }else{
            $next.removeClass('disabled');
        }
    }

    function updatePagination(e, $prev, $next){
        var prev_index, next_index, total_count;
        if(e.item.index == 0){
            prev_index = e.item.count - 1;
        }else{
            prev_index = e.item.index;
        }
        if(e.item.index == (e.item.count - 1)){
            next_index = 1;
        }else{
            next_index = e.item.index + 2;
        }
        total_count = e.item.count;
        $prev.find('.owl-current-page').html(prev_index);
        $next.find('.owl-current-page').html(next_index);
        
        $prev.find('.owl-total-page').html(total_count);
        $next.find('.owl-total-page').html(total_count);
    }

    function updateSliderText($slider, $title, $description, $link, index){
        var $currentSlide = $slider.find('.owl-item:eq('+index+') li');
        $title.text($currentSlide.attr('data-title'));
        $description.text($currentSlide.attr('data-description'));
        $link.attr('href', $currentSlide.attr('data-link'));
    }

    function attachCustomNavEvents($slider, $prev, $next){
        $prev.add($next).off('click');
        $prev.on('click', function(){
            $slider.trigger('stop.owl.autoplay');
            $slider.trigger('prev.owl.carousel');
        });
        $next.on('click', function(){
            $slider.trigger('stop.owl.autoplay');
            $slider.trigger('next.owl.carousel');
        });
    }
    function attachCustomPlayEvents($slider, $play){
        $play.on('click', function(){
            if($play.hasClass('pause')){
                $slider.trigger('stop.owl.autoplay');
                $play.removeClass('pause').addClass('play');
            }else{
                $slider.trigger('play.owl.autoplay');
                $play.removeClass('play').addClass('pause');
            }
        });
    }
    function fixRestartAutoplayAfterNavigationClick($slider, $prev, $next, $dots, $play){
        $next.add($prev).add($dots.find('.owl-dot')).on('click', function() {
            setTimeout(function() {
                $slider.trigger('play.owl.autoplay', [1000]);
                $slider.trigger('stop.owl.autoplay');
                $play.removeClass('pause');
            }, 500);
        });
    }
// }(jQuery));


/*! waitForImages jQuery Plugin - v2.1.0 - 2016-01-04
* https://github.com/alexanderdickson/waitForImages
* Copyright (c) 2016 Alex Dickson; Licensed MIT */
;(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // CommonJS / nodejs module
        module.exports = factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {
    // Namespace all events.
    var eventNamespace = 'waitForImages';

    // CSS properties which contain references to images.
    $.waitForImages = {
        hasImageProperties: [
            'backgroundImage',
            'listStyleImage',
            'borderImage',
            'borderCornerImage',
            'cursor'
        ],
        hasImageAttributes: ['srcset']
    };

    // Custom selector to find all `img` elements with a valid `src` attribute.
    $.expr[':']['has-src'] = function (obj) {
        // Ensure we are dealing with an `img` element with a valid
        // `src` attribute.
        return $(obj).is('img[src][src!=""]');
    };

    // Custom selector to find images which are not already cached by the
    // browser.
    $.expr[':'].uncached = function (obj) {
        // Ensure we are dealing with an `img` element with a valid
        // `src` attribute.
        if (!$(obj).is(':has-src')) {
            return false;
        }

        return !obj.complete;
    };

    $.fn.waitForImages = function () {

        var allImgsLength = 0;
        var allImgsLoaded = 0;
        var deferred = $.Deferred();

        var finishedCallback;
        var eachCallback;
        var waitForAll;

        // Handle options object (if passed).
        if ($.isPlainObject(arguments[0])) {

            waitForAll = arguments[0].waitForAll;
            eachCallback = arguments[0].each;
            finishedCallback = arguments[0].finished;

        } else {

            // Handle if using deferred object and only one param was passed in.
            if (arguments.length === 1 && $.type(arguments[0]) === 'boolean') {
                waitForAll = arguments[0];
            } else {
                finishedCallback = arguments[0];
                eachCallback = arguments[1];
                waitForAll = arguments[2];
            }

        }

        // Handle missing callbacks.
        finishedCallback = finishedCallback || $.noop;
        eachCallback = eachCallback || $.noop;

        // Convert waitForAll to Boolean
        waitForAll = !! waitForAll;

        // Ensure callbacks are functions.
        if (!$.isFunction(finishedCallback) || !$.isFunction(eachCallback)) {
            throw new TypeError('An invalid callback was supplied.');
        }

        this.each(function () {
            // Build a list of all imgs, dependent on what images will
            // be considered.
            var obj = $(this);
            var allImgs = [];
            // CSS properties which may contain an image.
            var hasImgProperties = $.waitForImages.hasImageProperties || [];
            // Element attributes which may contain an image.
            var hasImageAttributes = $.waitForImages.hasImageAttributes || [];
            // To match `url()` references.
            // Spec: http://www.w3.org/TR/CSS2/syndata.html#value-def-uri
            var matchUrl = /url\(\s*(['"]?)(.*?)\1\s*\)/g;

            if (waitForAll) {

                // Get all elements (including the original), as any one of
                // them could have a background image.
                obj.find('*').addBack().each(function () {
                    var element = $(this);

                    // If an `img` element, add it. But keep iterating in
                    // case it has a background image too.
                    if (element.is('img:has-src') &&
                        !element.is('[srcset]')) {
                        allImgs.push({
                            src: element.attr('src'),
                            element: element[0]
                        });
                    }

                    $.each(hasImgProperties, function (i, property) {
                        var propertyValue = element.css(property);
                        var match;

                        // If it doesn't contain this property, skip.
                        if (!propertyValue) {
                            return true;
                        }

                        // Get all url() of this element.
                        while (match = matchUrl.exec(propertyValue)) {
                            allImgs.push({
                                src: match[2],
                                element: element[0]
                            });
                        }
                    });

                    $.each(hasImageAttributes, function (i, attribute) {
                        var attributeValue = element.attr(attribute);
                        var attributeValues;

                        // If it doesn't contain this property, skip.
                        if (!attributeValue) {
                            return true;
                        }

                        allImgs.push({
                            src: element.attr('src'),
                            srcset: element.attr('srcset'),
                            element: element[0]
                        });
                    });
                });
            } else {
                // For images only, the task is simpler.
                obj.find('img:has-src')
                    .each(function () {
                    allImgs.push({
                        src: this.src,
                        element: this
                    });
                });
            }

            allImgsLength = allImgs.length;
            allImgsLoaded = 0;

            // If no images found, don't bother.
            if (allImgsLength === 0) {
                finishedCallback.call(obj[0]);
                deferred.resolveWith(obj[0]);
            }

            $.each(allImgs, function (i, img) {

                var image = new Image();
                var events =
                  'load.' + eventNamespace + ' error.' + eventNamespace;

                // Handle the image loading and error with the same callback.
                $(image).one(events, function me (event) {
                    // If an error occurred with loading the image, set the
                    // third argument accordingly.
                    var eachArguments = [
                        allImgsLoaded,
                        allImgsLength,
                        event.type == 'load'
                    ];
                    allImgsLoaded++;

                    eachCallback.apply(img.element, eachArguments);
                    deferred.notifyWith(img.element, eachArguments);

                    // Unbind the event listeners. I use this in addition to
                    // `one` as one of those events won't be called (either
                    // 'load' or 'error' will be called).
                    $(this).off(events, me);

                    if (allImgsLoaded == allImgsLength) {
                        finishedCallback.call(obj[0]);
                        deferred.resolveWith(obj[0]);
                        return false;
                    }

                });

                if (img.srcset) {
                    image.srcset = img.srcset;
                }
                image.src = img.src;
            });
        });

        return deferred.promise();

    };
}));


/**
 * Animate Plugin
 * @version 2.1.0
 * @author Bartosz Wojciechowski
 * @author David Deutsch
 * @license The MIT License (MIT)
 */
;(function($, window, document, undefined) {

	/**
	 * Creates the animate plugin.
	 * @class The Navigation Plugin
	 * @param {Owl} scope - The Owl Carousel
	 */
	var Animate = function(scope) {
		this.core = scope;
		this.core.options = $.extend({}, Animate.Defaults, this.core.options);
		this.swapping = true;
		this.previous = undefined;
		this.next = undefined;

		this.handlers = {
			'change.owl.carousel': $.proxy(function(e) {
				if (e.namespace && e.property.name == 'position') {
					this.previous = this.core.current();
					this.next = e.property.value;
				}
			}, this),
			'drag.owl.carousel dragged.owl.carousel translated.owl.carousel': $.proxy(function(e) {
				if (e.namespace) {
					this.swapping = e.type == 'translated';
				}
			}, this),
			'translate.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this.swapping && (this.core.options.animateOut || this.core.options.animateIn)) {
					this.swap();
				}
			}, this)
		};

		this.core.$element.on(this.handlers);
	};

	/**
	 * Default options.
	 * @public
	 */
	Animate.Defaults = {
		animateOut: false,
		animateIn: false
	};

	/**
	 * Toggles the animation classes whenever an translations starts.
	 * @protected
	 * @returns {Boolean|undefined}
	 */
	Animate.prototype.swap = function() {
		$.support.animation = (function() {
			var animationEnd = (function() {
				var element = document.body || document.documentElement,
					animEndEventNames = {
						WebkitAnimation : 'webkitAnimationEnd',
						MozAnimation    : 'animationend',
						OAnimation      : 'oAnimationEnd oanimationend',
						animation       : 'animationend'
					}, name;

				for (name in animEndEventNames) {
					if (element.style[name] !== undefined) return animEndEventNames[name];
				}
			}());

			return animationEnd ? { end: animationEnd } : false;
		})();



		if (this.core.settings.items !== 1) {
			return;
		}

		if (!$.support.animation || !$.support.transition) {
			return;
		}

		this.core.speed(0);

		var left,
			clear = $.proxy(this.clear, this),
			previous = this.core.$stage.children().eq(this.previous),
			next = this.core.$stage.children().eq(this.next),
			incoming = this.core.settings.animateIn,
			outgoing = this.core.settings.animateOut;

		if (this.core.current() === this.previous) {
			return;
		}

		if (outgoing) {
			left = this.core.coordinates(this.previous) - this.core.coordinates(this.next);
			previous.one($.support.animation.end, clear)
				.css( { 'left': left + 'px' } )
				.addClass('animated owl-animated-out')
				.addClass(outgoing);
		}

		if (incoming) {
			next.one($.support.animation.end, clear)
				.addClass('animated owl-animated-in')
				.addClass(incoming);
		}
	};

	Animate.prototype.clear = function(e) {
		$(e.target).css( { 'left': '' } )
			.removeClass('animated owl-animated-out owl-animated-in')
			.removeClass(this.core.settings.animateIn)
			.removeClass(this.core.settings.animateOut);
		this.core.onTransitionEnd();
	};

	/**
	 * Destroys the plugin.
	 * @public
	 */
	Animate.prototype.destroy = function() {
		var handler, property;

		for (handler in this.handlers) {
			this.core.$element.off(handler, this.handlers[handler]);
		}
		for (property in Object.getOwnPropertyNames(this)) {
			typeof this[property] != 'function' && (this[property] = null);
		}
	};

	$.fn.owlCarousel.Constructor.Plugins.Animate = Animate;

})(window.Zepto || window.jQuery, window, document);

/**
 * AutoHeight Plugin
 * @version 2.1.0
 * @author Bartosz Wojciechowski
 * @author David Deutsch
 * @license The MIT License (MIT)
 */
;(function($, window, document, undefined) {

	/**
	 * Creates the auto height plugin.
	 * @class The Auto Height Plugin
	 * @param {Owl} carousel - The Owl Carousel
	 */
	var AutoHeight = function(carousel) {
		/**
		 * Reference to the core.
		 * @protected
		 * @type {Owl}
		 */
		this._core = carousel;

		/**
		 * All event handlers.
		 * @protected
		 * @type {Object}
		 */
		this._handlers = {
			'initialized.owl.carousel refreshed.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.settings.autoHeight) {
					this.update();
				}
			}, this),
			'changed.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.settings.autoHeight && e.property.name == 'position'){
					this.update();
				}
			}, this),
			'loaded.owl.lazy': $.proxy(function(e) {
				if (e.namespace && this._core.settings.autoHeight
					&& e.element.closest('.' + this._core.settings.itemClass).index() === this._core.current()) {
					this.update();
				}
			}, this)
		};

		// set default options
		this._core.options = $.extend({}, AutoHeight.Defaults, this._core.options);

		// register event handlers
		this._core.$element.on(this._handlers);
	};

	/**
	 * Default options.
	 * @public
	 */
	AutoHeight.Defaults = {
		autoHeight: false,
		autoHeightClass: 'owl-height'
	};

	/**
	 * Updates the view.
	 */
	AutoHeight.prototype.update = function() {
		var start = this._core._current,
			end = start + this._core.settings.items,
			visible = this._core.$stage.children().toArray().slice(start, end),
			heights = [],
			maxheight = 0;

		$.each(visible, function(index, item) {
			heights.push($(item).height());
		});

		maxheight = Math.max.apply(null, heights);

		this._core.$stage.parent()
			.height(maxheight)
			.addClass(this._core.settings.autoHeightClass);
	};

	AutoHeight.prototype.destroy = function() {
		var handler, property;

		for (handler in this._handlers) {
			this._core.$element.off(handler, this._handlers[handler]);
		}
		for (property in Object.getOwnPropertyNames(this)) {
			typeof this[property] != 'function' && (this[property] = null);
		}
	};

	$.fn.owlCarousel.Constructor.Plugins.AutoHeight = AutoHeight;

})(window.Zepto || window.jQuery, window, document);

/**
 * Autoplay Plugin
 * @version 2.1.0
 * @author Bartosz Wojciechowski
 * @author Artus Kolanowski
 * @author David Deutsch
 * @license The MIT License (MIT)
 */
;(function($, window, document, undefined) {

	/**
	 * Creates the autoplay plugin.
	 * @class The Autoplay Plugin
	 * @param {Owl} scope - The Owl Carousel
	 */
	var Autoplay = function(carousel) {
		/**
		 * Reference to the core.
		 * @protected
		 * @type {Owl}
		 */
		this._core = carousel;

		/**
		 * The autoplay timeout.
		 * @type {Timeout}
		 */
		this._timeout = null;

		/**
		 * Indicates whenever the autoplay is paused.
		 * @type {Boolean}
		 */
		this._paused = false;

		/**
		 * All event handlers.
		 * @protected
		 * @type {Object}
		 */
		this._handlers = {
			'changed.owl.carousel': $.proxy(function(e) {
				if (e.namespace && e.property.name === 'settings') {
					if (this._core.settings.autoplay) {
						this.play();
					} else {
						this.stop();
					}
				} else if (e.namespace && e.property.name === 'position') {
					//console.log('play?', e);
					if (this._core.settings.autoplay) {
						this._setAutoPlayInterval();
					}
				}
			}, this),
			'initialized.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.settings.autoplay) {
					this.play();
				}
			}, this),
			'play.owl.autoplay': $.proxy(function(e, t, s) {
				if (e.namespace) {
					this.play(t, s);
				}
			}, this),
			'stop.owl.autoplay': $.proxy(function(e) {
				if (e.namespace) {
					this.stop();
				}
			}, this),
			'mouseover.owl.autoplay': $.proxy(function() {
				if (this._core.settings.autoplayHoverPause && this._core.is('rotating')) {
					this.pause();
				}
			}, this),
			'mouseleave.owl.autoplay': $.proxy(function() {
				if (this._core.settings.autoplayHoverPause && this._core.is('rotating')) {
					this.play();
				}
			}, this),
			'touchstart.owl.core': $.proxy(function() {
				if (this._core.settings.autoplayHoverPause && this._core.is('rotating')) {
					this.pause();
				}
			}, this),
			'touchend.owl.core': $.proxy(function() {
				if (this._core.settings.autoplayHoverPause) {
					this.play();
				}
			}, this)
		};

		// register event handlers
		this._core.$element.on(this._handlers);

		// set default options
		this._core.options = $.extend({}, Autoplay.Defaults, this._core.options);
	};

	/**
	 * Default options.
	 * @public
	 */
	Autoplay.Defaults = {
		autoplay: false,
		autoplayTimeout: 5000,
		autoplayHoverPause: false,
		autoplaySpeed: false
	};

	/**
	 * Starts the autoplay.
	 * @public
	 * @param {Number} [timeout] - The interval before the next animation starts.
	 * @param {Number} [speed] - The animation speed for the animations.
	 */
	Autoplay.prototype.play = function(timeout, speed) {
		this._paused = false;

		if (this._core.is('rotating')) {
			return;
		}

		this._core.enter('rotating');

		this._setAutoPlayInterval();
	};

	/**
	 * Gets a new timeout
	 * @private
	 * @param {Number} [timeout] - The interval before the next animation starts.
	 * @param {Number} [speed] - The animation speed for the animations.
	 * @return {Timeout}
	 */
	Autoplay.prototype._getNextTimeout = function(timeout, speed) {
		if ( this._timeout ) {
			window.clearTimeout(this._timeout);
		}
		return window.setTimeout($.proxy(function() {
			if (this._paused || this._core.is('busy') || this._core.is('interacting') || document.hidden) {
				return;
			}
			this._core.next(speed || this._core.settings.autoplaySpeed);
		}, this), timeout || this._core.settings.autoplayTimeout);
	};

	/**
	 * Sets autoplay in motion.
	 * @private
	 */
	Autoplay.prototype._setAutoPlayInterval = function() {
		this._timeout = this._getNextTimeout();
	};

	/**
	 * Stops the autoplay.
	 * @public
	 */
	Autoplay.prototype.stop = function() {
		if (!this._core.is('rotating')) {
			return;
		}

		window.clearTimeout(this._timeout);
		this._core.leave('rotating');
	};

	/**
	 * Stops the autoplay.
	 * @public
	 */
	Autoplay.prototype.pause = function() {
		if (!this._core.is('rotating')) {
			return;
		}

		this._paused = true;
	};

	/**
	 * Destroys the plugin.
	 */
	Autoplay.prototype.destroy = function() {
		var handler, property;

		this.stop();

		for (handler in this._handlers) {
			this._core.$element.off(handler, this._handlers[handler]);
		}
		for (property in Object.getOwnPropertyNames(this)) {
			typeof this[property] != 'function' && (this[property] = null);
		}
	};

	$.fn.owlCarousel.Constructor.Plugins.autoplay = Autoplay;

})(window.Zepto || window.jQuery, window, document);

/**
 * AutoRefresh Plugin
 * @version 2.1.0
 * @author Artus Kolanowski
 * @author David Deutsch
 * @license The MIT License (MIT)
 */
;(function($, window, document, undefined) {

	/**
	 * Creates the auto refresh plugin.
	 * @class The Auto Refresh Plugin
	 * @param {Owl} carousel - The Owl Carousel
	 */
	var AutoRefresh = function(carousel) {
		/**
		 * Reference to the core.
		 * @protected
		 * @type {Owl}
		 */
		this._core = carousel;

		/**
		 * Refresh interval.
		 * @protected
		 * @type {number}
		 */
		this._interval = null;

		/**
		 * Whether the element is currently visible or not.
		 * @protected
		 * @type {Boolean}
		 */
		this._visible = null;

		/**
		 * All event handlers.
		 * @protected
		 * @type {Object}
		 */
		this._handlers = {
			'initialized.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.settings.autoRefresh) {
					this.watch();
				}
			}, this)
		};

		// set default options
		this._core.options = $.extend({}, AutoRefresh.Defaults, this._core.options);

		// register event handlers
		this._core.$element.on(this._handlers);
	};

	/**
	 * Default options.
	 * @public
	 */
	AutoRefresh.Defaults = {
		autoRefresh: true,
		autoRefreshInterval: 500
	};

	/**
	 * Watches the element.
	 */
	AutoRefresh.prototype.watch = function() {
		if (this._interval) {
			return;
		}

		this._visible = this._core.$element.is(':visible');
		this._interval = window.setInterval($.proxy(this.refresh, this), this._core.settings.autoRefreshInterval);
	};

	/**
	 * Refreshes the element.
	 */
	AutoRefresh.prototype.refresh = function() {
		if (this._core.$element.is(':visible') === this._visible) {
			return;
		}

		this._visible = !this._visible;

		this._core.$element.toggleClass('owl-hidden', !this._visible);

		this._visible && (this._core.invalidate('width') && this._core.refresh());
	};

	/**
	 * Destroys the plugin.
	 */
	AutoRefresh.prototype.destroy = function() {
		var handler, property;

		window.clearInterval(this._interval);

		for (handler in this._handlers) {
			this._core.$element.off(handler, this._handlers[handler]);
		}
		for (property in Object.getOwnPropertyNames(this)) {
			typeof this[property] != 'function' && (this[property] = null);
		}
	};

	$.fn.owlCarousel.Constructor.Plugins.AutoRefresh = AutoRefresh;

})(window.Zepto || window.jQuery, window, document);

/**
 * Hash Plugin
 * @version 2.1.0
 * @author Artus Kolanowski
 * @author David Deutsch
 * @license The MIT License (MIT)
 */
;(function($, window, document, undefined) {
	'use strict';

	/**
	 * Creates the hash plugin.
	 * @class The Hash Plugin
	 * @param {Owl} carousel - The Owl Carousel
	 */
	var Hash = function(carousel) {
		/**
		 * Reference to the core.
		 * @protected
		 * @type {Owl}
		 */
		this._core = carousel;

		/**
		 * Hash index for the items.
		 * @protected
		 * @type {Object}
		 */
		this._hashes = {};

		/**
		 * The carousel element.
		 * @type {jQuery}
		 */
		this.$element = this._core.$element;

		/**
		 * All event handlers.
		 * @protected
		 * @type {Object}
		 */
		this._handlers = {
			'initialized.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.settings.startPosition === 'URLHash') {
					$(window).trigger('hashchange.owl.navigation');
				}
			}, this),
			'prepared.owl.carousel': $.proxy(function(e) {
				if (e.namespace) {
					var hash = $(e.content).find('[data-hash]').addBack('[data-hash]').attr('data-hash');

					if (!hash) {
						return;
					}

					this._hashes[hash] = e.content;
				}
			}, this),
			'changed.owl.carousel': $.proxy(function(e) {
				if (e.namespace && e.property.name === 'position') {
					var current = this._core.items(this._core.relative(this._core.current())),
						hash = $.map(this._hashes, function(item, hash) {
							return item === current ? hash : null;
						}).join();

					if (!hash || window.location.hash.slice(1) === hash) {
						return;
					}

					window.location.hash = hash;
				}
			}, this)
		};

		// set default options
		this._core.options = $.extend({}, Hash.Defaults, this._core.options);

		// register the event handlers
		this.$element.on(this._handlers);

		// register event listener for hash navigation
		$(window).on('hashchange.owl.navigation', $.proxy(function(e) {
			var hash = window.location.hash.substring(1),
				items = this._core.$stage.children(),
				position = this._hashes[hash] && items.index(this._hashes[hash]);

			if (position === undefined || position === this._core.current()) {
				return;
			}

			this._core.to(this._core.relative(position), false, true);
		}, this));
	};

	/**
	 * Default options.
	 * @public
	 */
	Hash.Defaults = {
		URLhashListener: false
	};

	/**
	 * Destroys the plugin.
	 * @public
	 */
	Hash.prototype.destroy = function() {
		var handler, property;

		$(window).off('hashchange.owl.navigation');

		for (handler in this._handlers) {
			this._core.$element.off(handler, this._handlers[handler]);
		}
		for (property in Object.getOwnPropertyNames(this)) {
			typeof this[property] != 'function' && (this[property] = null);
		}
	};

	$.fn.owlCarousel.Constructor.Plugins.Hash = Hash;

})(window.Zepto || window.jQuery, window, document);

/**
 * Navigation Plugin
 * @version 2.1.0
 * @author Artus Kolanowski
 * @author David Deutsch
 * @license The MIT License (MIT)
 */
;(function($, window, document, undefined) {
	'use strict';

	/**
	 * Creates the navigation plugin.
	 * @class The Navigation Plugin
	 * @param {Owl} carousel - The Owl Carousel.
	 */
	var Navigation = function(carousel) {
		/**
		 * Reference to the core.
		 * @protected
		 * @type {Owl}
		 */
		this._core = carousel;

		/**
		 * Indicates whether the plugin is initialized or not.
		 * @protected
		 * @type {Boolean}
		 */
		this._initialized = false;

		/**
		 * The current paging indexes.
		 * @protected
		 * @type {Array}
		 */
		this._pages = [];

		/**
		 * All DOM elements of the user interface.
		 * @protected
		 * @type {Object}
		 */
		this._controls = {};

		/**
		 * Markup for an indicator.
		 * @protected
		 * @type {Array.<String>}
		 */
		this._templates = [];

		/**
		 * The carousel element.
		 * @type {jQuery}
		 */
		this.$element = this._core.$element;

		/**
		 * Overridden methods of the carousel.
		 * @protected
		 * @type {Object}
		 */
		this._overrides = {
			next: this._core.next,
			prev: this._core.prev,
			to: this._core.to
		};

		/**
		 * All event handlers.
		 * @protected
		 * @type {Object}
		 */
		this._handlers = {
			'prepared.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.settings.dotsData) {
					this._templates.push('<div class="' + this._core.settings.dotClass + '">' +
						$(e.content).find('[data-dot]').addBack('[data-dot]').attr('data-dot') + '</div>');
				}
			}, this),
			'added.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.settings.dotsData) {
					this._templates.splice(e.position, 0, this._templates.pop());
				}
			}, this),
			'remove.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.settings.dotsData) {
					this._templates.splice(e.position, 1);
				}
			}, this),
			'changed.owl.carousel': $.proxy(function(e) {
				if (e.namespace && e.property.name == 'position') {
					this.draw();
				}
			}, this),
			'initialized.owl.carousel': $.proxy(function(e) {
				if (e.namespace && !this._initialized) {
					this._core.trigger('initialize', null, 'navigation');
					this.initialize();
					this.update();
					this.draw();
					this._initialized = true;
					this._core.trigger('initialized', null, 'navigation');
				}
			}, this),
			'refreshed.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._initialized) {
					this._core.trigger('refresh', null, 'navigation');
					this.update();
					this.draw();
					this._core.trigger('refreshed', null, 'navigation');
				}
			}, this)
		};

		// set default options
		this._core.options = $.extend({}, Navigation.Defaults, this._core.options);

		// register event handlers
		this.$element.on(this._handlers);
	};

	/**
	 * Default options.
	 * @public
	 * @todo Rename `slideBy` to `navBy`
	 */
	Navigation.Defaults = {
		nav: false,
		navText: [ 'prev', 'next' ],
		navSpeed: false,
		navElement: 'div',
		navContainer: false,
		navContainerClass: 'owl-nav',
		navClass: [ 'owl-prev', 'owl-next' ],
		slideBy: 1,
		dotClass: 'owl-dot',
		dotsClass: 'owl-dots',
		dots: true,
		dotsEach: false,
		dotsData: false,
		dotsSpeed: false,
		dotsContainer: false
	};

	/**
	 * Initializes the layout of the plugin and extends the carousel.
	 * @protected
	 */
	Navigation.prototype.initialize = function() {
		var override,
			settings = this._core.settings;

		// create DOM structure for relative navigation
		this._controls.$relative = (settings.navContainer ? $(settings.navContainer)
			: $('<div>').addClass(settings.navContainerClass).appendTo(this.$element)).addClass('disabled');

		this._controls.$previous = $('<' + settings.navElement + '>')
			.addClass(settings.navClass[0])
			.html(settings.navText[0])
			.prependTo(this._controls.$relative)
			.on('click', $.proxy(function(e) {
				this.prev(settings.navSpeed);
			}, this));
		this._controls.$next = $('<' + settings.navElement + '>')
			.addClass(settings.navClass[1])
			.html(settings.navText[1])
			.appendTo(this._controls.$relative)
			.on('click', $.proxy(function(e) {
				this.next(settings.navSpeed);
			}, this));

		// create DOM structure for absolute navigation
		if (!settings.dotsData) {
			this._templates = [ $('<div>')
				.addClass(settings.dotClass)
				.append($('<span>'))
				.prop('outerHTML') ];
		}

		this._controls.$absolute = (settings.dotsContainer ? $(settings.dotsContainer)
			: $('<div>').addClass(settings.dotsClass).appendTo(this.$element)).addClass('disabled');

		this._controls.$absolute.on('click', 'div', $.proxy(function(e) {
			var index = $(e.target).parent().is(this._controls.$absolute)
				? $(e.target).index() : $(e.target).parent().index();

			e.preventDefault();

			this.to(index, settings.dotsSpeed);
		}, this));

		// override public methods of the carousel
		for (override in this._overrides) {
			this._core[override] = $.proxy(this[override], this);
		}
	};

	/**
	 * Destroys the plugin.
	 * @protected
	 */
	Navigation.prototype.destroy = function() {
		var handler, control, property, override;

		for (handler in this._handlers) {
			this.$element.off(handler, this._handlers[handler]);
		}
		for (control in this._controls) {
			this._controls[control].remove();
		}
		for (override in this.overides) {
			this._core[override] = this._overrides[override];
		}
		for (property in Object.getOwnPropertyNames(this)) {
			typeof this[property] != 'function' && (this[property] = null);
		}
	};

	/**
	 * Updates the internal state.
	 * @protected
	 */
	Navigation.prototype.update = function() {
		var i, j, k,
			lower = this._core.clones().length / 2,
			upper = lower + this._core.items().length,
			maximum = this._core.maximum(true),
			settings = this._core.settings,
			size = settings.center || settings.autoWidth || settings.dotsData
				? 1 : settings.dotsEach || settings.items;

		if (settings.slideBy !== 'page') {
			settings.slideBy = Math.min(settings.slideBy, settings.items);
		}

		if (settings.dots || settings.slideBy == 'page') {
			this._pages = [];

			for (i = lower, j = 0, k = 0; i < upper; i++) {
				if (j >= size || j === 0) {
					this._pages.push({
						start: Math.min(maximum, i - lower),
						end: i - lower + size - 1
					});
					if (Math.min(maximum, i - lower) === maximum) {
						break;
					}
					j = 0, ++k;
				}
				j += this._core.mergers(this._core.relative(i));
			}
		}
	};

	/**
	 * Draws the user interface.
	 * @todo The option `dotsData` wont work.
	 * @protected
	 */
	Navigation.prototype.draw = function() {
		var difference,
			settings = this._core.settings,
			disabled = this._core.items().length <= settings.items,
			index = this._core.relative(this._core.current()),
			loop = settings.loop || settings.rewind;

		this._controls.$relative.toggleClass('disabled', !settings.nav || disabled);

		if (settings.nav) {
			this._controls.$previous.toggleClass('disabled', !loop && index <= this._core.minimum(true));
			this._controls.$next.toggleClass('disabled', !loop && index >= this._core.maximum(true));
		}

		this._controls.$absolute.toggleClass('disabled', !settings.dots || disabled);

		if (settings.dots) {
			difference = this._pages.length - this._controls.$absolute.children().length;

			if (settings.dotsData && difference !== 0) {
				this._controls.$absolute.html(this._templates.join(''));
			} else if (difference > 0) {
				this._controls.$absolute.append(new Array(difference + 1).join(this._templates[0]));
			} else if (difference < 0) {
				this._controls.$absolute.children().slice(difference).remove();
			}

			this._controls.$absolute.find('.active').removeClass('active');
			this._controls.$absolute.children().eq($.inArray(this.current(), this._pages)).addClass('active');
		}
	};

	/**
	 * Extends event data.
	 * @protected
	 * @param {Event} event - The event object which gets thrown.
	 */
	Navigation.prototype.onTrigger = function(event) {
		var settings = this._core.settings;

		event.page = {
			index: $.inArray(this.current(), this._pages),
			count: this._pages.length,
			size: settings && (settings.center || settings.autoWidth || settings.dotsData
				? 1 : settings.dotsEach || settings.items)
		};
	};

	/**
	 * Gets the current page position of the carousel.
	 * @protected
	 * @returns {Number}
	 */
	Navigation.prototype.current = function() {
		var current = this._core.relative(this._core.current());
		return $.grep(this._pages, $.proxy(function(page, index) {
			return page.start <= current && page.end >= current;
		}, this)).pop();
	};

	/**
	 * Gets the current succesor/predecessor position.
	 * @protected
	 * @returns {Number}
	 */
	Navigation.prototype.getPosition = function(successor) {
		var position, length,
			settings = this._core.settings;

		if (settings.slideBy == 'page') {
			position = $.inArray(this.current(), this._pages);
			length = this._pages.length;
			successor ? ++position : --position;
			position = this._pages[((position % length) + length) % length].start;
		} else {
			position = this._core.relative(this._core.current());
			length = this._core.items().length;
			successor ? position += settings.slideBy : position -= settings.slideBy;
		}

		return position;
	};

	/**
	 * Slides to the next item or page.
	 * @public
	 * @param {Number} [speed=false] - The time in milliseconds for the transition.
	 */
	Navigation.prototype.next = function(speed) {
		$.proxy(this._overrides.to, this._core)(this.getPosition(true), speed);
	};

	/**
	 * Slides to the previous item or page.
	 * @public
	 * @param {Number} [speed=false] - The time in milliseconds for the transition.
	 */
	Navigation.prototype.prev = function(speed) {
		$.proxy(this._overrides.to, this._core)(this.getPosition(false), speed);
	};

	/**
	 * Slides to the specified item or page.
	 * @public
	 * @param {Number} position - The position of the item or page.
	 * @param {Number} [speed] - The time in milliseconds for the transition.
	 * @param {Boolean} [standard=false] - Whether to use the standard behaviour or not.
	 */
	Navigation.prototype.to = function(position, speed, standard) {
		var length;

		if (!standard && this._pages.length) {
			length = this._pages.length;
			$.proxy(this._overrides.to, this._core)(this._pages[((position % length) + length) % length].start, speed);
		} else {
			$.proxy(this._overrides.to, this._core)(position, speed);
		}
	};

	$.fn.owlCarousel.Constructor.Plugins.Navigation = Navigation;

})(window.Zepto || window.jQuery, window, document);

/**
 * Video Plugin
 * @version 2.1.0
 * @author Bartosz Wojciechowski
 * @author David Deutsch
 * @license The MIT License (MIT)
 */
;(function($, window, document, undefined) {

	/**
	 * Creates the video plugin.
	 * @class The Video Plugin
	 * @param {Owl} carousel - The Owl Carousel
	 */
	var Video = function(carousel) {
		/**
		 * Reference to the core.
		 * @protected
		 * @type {Owl}
		 */
		this._core = carousel;

		/**
		 * Cache all video URLs.
		 * @protected
		 * @type {Object}
		 */
		this._videos = {};

		/**
		 * Current playing item.
		 * @protected
		 * @type {jQuery}
		 */
		this._playing = null;

		/**
		 * All event handlers.
		 * @todo The cloned content removale is too late
		 * @protected
		 * @type {Object}
		 */
		this._handlers = {
			'initialized.owl.carousel': $.proxy(function(e) {
				if (e.namespace) {
					this._core.register({ type: 'state', name: 'playing', tags: [ 'interacting' ] });
				}
			}, this),
			'resize.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.settings.video && this.isInFullScreen()) {
					e.preventDefault();
				}
			}, this),
			'refreshed.owl.carousel': $.proxy(function(e) {
				if (e.namespace && this._core.is('resizing')) {
					this._core.$stage.find('.cloned .owl-video-frame').remove();
				}
			}, this),
			'changed.owl.carousel': $.proxy(function(e) {
				if (e.namespace && e.property.name === 'position' && this._playing) {
					this.stop();
				}
			}, this),
			'prepared.owl.carousel': $.proxy(function(e) {
				if (!e.namespace) {
					return;
				}

				var $element = $(e.content).find('.owl-video');

				if ($element.length) {
					$element.css('display', 'none');
					this.fetch($element, $(e.content));
				}
			}, this)
		};

		// set default options
		this._core.options = $.extend({}, Video.Defaults, this._core.options);

		// register event handlers
		this._core.$element.on(this._handlers);

		this._core.$element.on('click.owl.video', '.owl-video-play-icon', $.proxy(function(e) {
			this.play(e);
		}, this));
	};

	/**
	 * Default options.
	 * @public
	 */
	Video.Defaults = {
		video: false,
		videoHeight: false,
		videoWidth: false
	};

	/**
	 * Gets the video ID and the type (YouTube/Vimeo/vzaar only).
	 * @protected
	 * @param {jQuery} target - The target containing the video data.
	 * @param {jQuery} item - The item containing the video.
	 */
	Video.prototype.fetch = function(target, item) {
			var type = (function() {
					if (target.attr('data-vimeo-id')) {
						return 'vimeo';
					} else if (target.attr('data-vzaar-id')) {
						return 'vzaar'
					} else {
						return 'youtube';
					}
				})(),
				id = target.attr('data-vimeo-id') || target.attr('data-youtube-id') || target.attr('data-vzaar-id'),
				width = target.attr('data-width') || this._core.settings.videoWidth,
				height = target.attr('data-height') || this._core.settings.videoHeight,
				url = target.attr('href');

		if (url) {

			/*
					Parses the id's out of the following urls (and probably more):
					https://www.youtube.com/watch?v=:id
					https://youtu.be/:id
					https://vimeo.com/:id
					https://vimeo.com/channels/:channel/:id
					https://vimeo.com/groups/:group/videos/:id
					https://app.vzaar.com/videos/:id

					Visual example: https://regexper.com/#(http%3A%7Chttps%3A%7C)%5C%2F%5C%2F(player.%7Cwww.%7Capp.)%3F(vimeo%5C.com%7Cyoutu(be%5C.com%7C%5C.be%7Cbe%5C.googleapis%5C.com)%7Cvzaar%5C.com)%5C%2F(video%5C%2F%7Cvideos%5C%2F%7Cembed%5C%2F%7Cchannels%5C%2F.%2B%5C%2F%7Cgroups%5C%2F.%2B%5C%2F%7Cwatch%5C%3Fv%3D%7Cv%5C%2F)%3F(%5BA-Za-z0-9._%25-%5D*)(%5C%26%5CS%2B)%3F
			*/

			id = url.match(/(http:|https:|)\/\/(player.|www.|app.)?(vimeo\.com|youtu(be\.com|\.be|be\.googleapis\.com)|vzaar\.com)\/(video\/|videos\/|embed\/|channels\/.+\/|groups\/.+\/|watch\?v=|v\/)?([A-Za-z0-9._%-]*)(\&\S+)?/);

			if (id[3].indexOf('youtu') > -1) {
				type = 'youtube';
			} else if (id[3].indexOf('vimeo') > -1) {
				type = 'vimeo';
			} else if (id[3].indexOf('vzaar') > -1) {
				type = 'vzaar';
			} else {
				throw new Error('Video URL not supported.');
			}
			id = id[6];
		} else {
			throw new Error('Missing video URL.');
		}

		this._videos[url] = {
			type: type,
			id: id,
			width: width,
			height: height
		};

		item.attr('data-video', url);

		this.thumbnail(target, this._videos[url]);
	};

	/**
	 * Creates video thumbnail.
	 * @protected
	 * @param {jQuery} target - The target containing the video data.
	 * @param {Object} info - The video info object.
	 * @see `fetch`
	 */
	Video.prototype.thumbnail = function(target, video) {
		var tnLink,
			icon,
			path,
			dimensions = video.width && video.height ? 'style="width:' + video.width + 'px;height:' + video.height + 'px;"' : '',
			customTn = target.find('img'),
			srcType = 'src',
			lazyClass = '',
			settings = this._core.settings,
			create = function(path) {
				icon = '<div class="owl-video-play-icon"></div>';

				if (settings.lazyLoad) {
					tnLink = '<div class="owl-video-tn ' + lazyClass + '" ' + srcType + '="' + path + '"></div>';
				} else {
					tnLink = '<div class="owl-video-tn" style="opacity:1;background-image:url(' + path + ')"></div>';
				}
				target.after(tnLink);
				target.after(icon);
			};

		// wrap video content into owl-video-wrapper div
		target.wrap('<div class="owl-video-wrapper"' + dimensions + '></div>');

		if (this._core.settings.lazyLoad) {
			srcType = 'data-src';
			lazyClass = 'owl-lazy';
		}

		// custom thumbnail
		if (customTn.length) {
			create(customTn.attr(srcType));
			customTn.remove();
			return false;
		}

		if (video.type === 'youtube') {
			path = "//img.youtube.com/vi/" + video.id + "/hqdefault.jpg";
			create(path);
		} else if (video.type === 'vimeo') {
			$.ajax({
				type: 'GET',
				url: '//vimeo.com/api/v2/video/' + video.id + '.json',
				jsonp: 'callback',
				dataType: 'jsonp',
				success: function(data) {
					path = data[0].thumbnail_large;
					create(path);
				}
			});
		} else if (video.type === 'vzaar') {
			$.ajax({
				type: 'GET',
				url: '//vzaar.com/api/videos/' + video.id + '.json',
				jsonp: 'callback',
				dataType: 'jsonp',
				success: function(data) {
					path = data.framegrab_url;
					create(path);
				}
			});
		}
	};

	/**
	 * Stops the current video.
	 * @public
	 */
	Video.prototype.stop = function() {
		this._core.trigger('stop', null, 'video');
		this._playing.find('.owl-video-frame').remove();
		this._playing.removeClass('owl-video-playing');
		this._playing = null;
		this._core.leave('playing');
		this._core.trigger('stopped', null, 'video');
	};

	/**
	 * Starts the current video.
	 * @public
	 * @param {Event} event - The event arguments.
	 */
	Video.prototype.play = function(event) {
		var target = $(event.target),
			item = target.closest('.' + this._core.settings.itemClass),
			video = this._videos[item.attr('data-video')],
			width = video.width || '100%',
			height = video.height || this._core.$stage.height(),
			html;

		if (this._playing) {
			return;
		}

		this._core.enter('playing');
		this._core.trigger('play', null, 'video');

		item = this._core.items(this._core.relative(item.index()));

		this._core.reset(item.index());

		if (video.type === 'youtube') {
			html = '<iframe width="' + width + '" height="' + height + '" src="//www.youtube.com/embed/' +
				video.id + '?autoplay=1&rel=0&v=' + video.id + '" frameborder="0" allowfullscreen></iframe>';
		} else if (video.type === 'vimeo') {
			html = '<iframe src="//player.vimeo.com/video/' + video.id +
				'?autoplay=1" width="' + width + '" height="' + height +
				'" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>';
		} else if (video.type === 'vzaar') {
			html = '<iframe frameborder="0"' + 'height="' + height + '"' + 'width="' + width +
				'" allowfullscreen mozallowfullscreen webkitAllowFullScreen ' +
				'src="//view.vzaar.com/' + video.id + '/player?autoplay=true"></iframe>';
		}

		$('<div class="owl-video-frame">' + html + '</div>').insertAfter(item.find('.owl-video'));

		this._playing = item.addClass('owl-video-playing');
	};

	/**
	 * Checks whether an video is currently in full screen mode or not.
	 * @todo Bad style because looks like a readonly method but changes members.
	 * @protected
	 * @returns {Boolean}
	 */
	Video.prototype.isInFullScreen = function() {
		var element = document.fullscreenElement || document.mozFullScreenElement ||
				document.webkitFullscreenElement;

		return element && $(element).parent().hasClass('owl-video-frame');
	};

	/**
	 * Destroys the plugin.
	 */
	Video.prototype.destroy = function() {
		var handler, property;

		this._core.$element.off('click.owl.video');

		for (handler in this._handlers) {
			this._core.$element.off(handler, this._handlers[handler]);
		}
		for (property in Object.getOwnPropertyNames(this)) {
			typeof this[property] != 'function' && (this[property] = null);
		}
	};

	$.fn.owlCarousel.Constructor.Plugins.Video = Video;

})(window.Zepto || window.jQuery, window, document);

/*!
 * Select2 4.0.3
 * https://select2.github.io
 *
 * Released under the MIT license
 * https://github.com/select2/select2/blob/master/LICENSE.md
 */
(function (factory) {
    if (typeof define === 'function' && define.amd) {
      // AMD. Register as an anonymous module.
      define(['jquery'], factory);
    } else if (typeof exports === 'object') {
      // Node/CommonJS
      factory(require('jquery'));
    } else {
      // Browser globals
      factory(jQuery);
    }
  }(function (jQuery) {
    // This is needed so we can catch the AMD loader configuration and use it
    // The inner file should be wrapped (by `banner.start.js`) in a function that
    // returns the AMD loader references.
    var S2 =
  (function () {
    // Restore the Select2 AMD loader so it can be used
    // Needed mostly in the language files, where the loader is not inserted
    if (jQuery && jQuery.fn && jQuery.fn.select2 && jQuery.fn.select2.amd) {
      var S2 = jQuery.fn.select2.amd;
    }
  var S2;(function () { if (!S2 || !S2.requirejs) {
  if (!S2) { S2 = {}; } else { require = S2; }
  /**
   * @license almond 0.3.1 Copyright (c) 2011-2014, The Dojo Foundation All Rights Reserved.
   * Available via the MIT or new BSD license.
   * see: http://github.com/jrburke/almond for details
   */
  //Going sloppy to avoid 'use strict' string cost, but strict practices should
  //be followed.
  /*jslint sloppy: true */
  /*global setTimeout: false */
  
  var requirejs, require, define;
  (function (undef) {
      var main, req, makeMap, handlers,
          defined = {},
          waiting = {},
          config = {},
          defining = {},
          hasOwn = Object.prototype.hasOwnProperty,
          aps = [].slice,
          jsSuffixRegExp = /\.js$/;
  
      function hasProp(obj, prop) {
          return hasOwn.call(obj, prop);
      }
  
      /**
       * Given a relative module name, like ./something, normalize it to
       * a real name that can be mapped to a path.
       * @param {String} name the relative name
       * @param {String} baseName a real name that the name arg is relative
       * to.
       * @returns {String} normalized name
       */
      function normalize(name, baseName) {
          var nameParts, nameSegment, mapValue, foundMap, lastIndex,
              foundI, foundStarMap, starI, i, j, part,
              baseParts = baseName && baseName.split("/"),
              map = config.map,
              starMap = (map && map['*']) || {};
  
          //Adjust any relative paths.
          if (name && name.charAt(0) === ".") {
              //If have a base name, try to normalize against it,
              //otherwise, assume it is a top-level require that will
              //be relative to baseUrl in the end.
              if (baseName) {
                  name = name.split('/');
                  lastIndex = name.length - 1;
  
                  // Node .js allowance:
                  if (config.nodeIdCompat && jsSuffixRegExp.test(name[lastIndex])) {
                      name[lastIndex] = name[lastIndex].replace(jsSuffixRegExp, '');
                  }
  
                  //Lop off the last part of baseParts, so that . matches the
                  //"directory" and not name of the baseName's module. For instance,
                  //baseName of "one/two/three", maps to "one/two/three.js", but we
                  //want the directory, "one/two" for this normalization.
                  name = baseParts.slice(0, baseParts.length - 1).concat(name);
  
                  //start trimDots
                  for (i = 0; i < name.length; i += 1) {
                      part = name[i];
                      if (part === ".") {
                          name.splice(i, 1);
                          i -= 1;
                      } else if (part === "..") {
                          if (i === 1 && (name[2] === '..' || name[0] === '..')) {
                              //End of the line. Keep at least one non-dot
                              //path segment at the front so it can be mapped
                              //correctly to disk. Otherwise, there is likely
                              //no path mapping for a path starting with '..'.
                              //This can still fail, but catches the most reasonable
                              //uses of ..
                              break;
                          } else if (i > 0) {
                              name.splice(i - 1, 2);
                              i -= 2;
                          }
                      }
                  }
                  //end trimDots
  
                  name = name.join("/");
              } else if (name.indexOf('./') === 0) {
                  // No baseName, so this is ID is resolved relative
                  // to baseUrl, pull off the leading dot.
                  name = name.substring(2);
              }
          }
  
          //Apply map config if available.
          if ((baseParts || starMap) && map) {
              nameParts = name.split('/');
  
              for (i = nameParts.length; i > 0; i -= 1) {
                  nameSegment = nameParts.slice(0, i).join("/");
  
                  if (baseParts) {
                      //Find the longest baseName segment match in the config.
                      //So, do joins on the biggest to smallest lengths of baseParts.
                      for (j = baseParts.length; j > 0; j -= 1) {
                          mapValue = map[baseParts.slice(0, j).join('/')];
  
                          //baseName segment has  config, find if it has one for
                          //this name.
                          if (mapValue) {
                              mapValue = mapValue[nameSegment];
                              if (mapValue) {
                                  //Match, update name to the new value.
                                  foundMap = mapValue;
                                  foundI = i;
                                  break;
                              }
                          }
                      }
                  }
  
                  if (foundMap) {
                      break;
                  }
  
                  //Check for a star map match, but just hold on to it,
                  //if there is a shorter segment match later in a matching
                  //config, then favor over this star map.
                  if (!foundStarMap && starMap && starMap[nameSegment]) {
                      foundStarMap = starMap[nameSegment];
                      starI = i;
                  }
              }
  
              if (!foundMap && foundStarMap) {
                  foundMap = foundStarMap;
                  foundI = starI;
              }
  
              if (foundMap) {
                  nameParts.splice(0, foundI, foundMap);
                  name = nameParts.join('/');
              }
          }
  
          return name;
      }
  
      function makeRequire(relName, forceSync) {
          return function () {
              //A version of a require function that passes a moduleName
              //value for items that may need to
              //look up paths relative to the moduleName
              var args = aps.call(arguments, 0);
  
              //If first arg is not require('string'), and there is only
              //one arg, it is the array form without a callback. Insert
              //a null so that the following concat is correct.
              if (typeof args[0] !== 'string' && args.length === 1) {
                  args.push(null);
              }
              return req.apply(undef, args.concat([relName, forceSync]));
          };
      }
  
      function makeNormalize(relName) {
          return function (name) {
              return normalize(name, relName);
          };
      }
  
      function makeLoad(depName) {
          return function (value) {
              defined[depName] = value;
          };
      }
  
      function callDep(name) {
          if (hasProp(waiting, name)) {
              var args = waiting[name];
              delete waiting[name];
              defining[name] = true;
              main.apply(undef, args);
          }
  
          if (!hasProp(defined, name) && !hasProp(defining, name)) {
              throw new Error('No ' + name);
          }
          return defined[name];
      }
  
      //Turns a plugin!resource to [plugin, resource]
      //with the plugin being undefined if the name
      //did not have a plugin prefix.
      function splitPrefix(name) {
          var prefix,
              index = name ? name.indexOf('!') : -1;
          if (index > -1) {
              prefix = name.substring(0, index);
              name = name.substring(index + 1, name.length);
          }
          return [prefix, name];
      }
  
      /**
       * Makes a name map, normalizing the name, and using a plugin
       * for normalization if necessary. Grabs a ref to plugin
       * too, as an optimization.
       */
      makeMap = function (name, relName) {
          var plugin,
              parts = splitPrefix(name),
              prefix = parts[0];
  
          name = parts[1];
  
          if (prefix) {
              prefix = normalize(prefix, relName);
              plugin = callDep(prefix);
          }
  
          //Normalize according
          if (prefix) {
              if (plugin && plugin.normalize) {
                  name = plugin.normalize(name, makeNormalize(relName));
              } else {
                  name = normalize(name, relName);
              }
          } else {
              name = normalize(name, relName);
              parts = splitPrefix(name);
              prefix = parts[0];
              name = parts[1];
              if (prefix) {
                  plugin = callDep(prefix);
              }
          }
  
          //Using ridiculous property names for space reasons
          return {
              f: prefix ? prefix + '!' + name : name, //fullName
              n: name,
              pr: prefix,
              p: plugin
          };
      };
  
      function makeConfig(name) {
          return function () {
              return (config && config.config && config.config[name]) || {};
          };
      }
  
      handlers = {
          require: function (name) {
              return makeRequire(name);
          },
          exports: function (name) {
              var e = defined[name];
              if (typeof e !== 'undefined') {
                  return e;
              } else {
                  return (defined[name] = {});
              }
          },
          module: function (name) {
              return {
                  id: name,
                  uri: '',
                  exports: defined[name],
                  config: makeConfig(name)
              };
          }
      };
  
      main = function (name, deps, callback, relName) {
          var cjsModule, depName, ret, map, i,
              args = [],
              callbackType = typeof callback,
              usingExports;
  
          //Use name if no relName
          relName = relName || name;
  
          //Call the callback to define the module, if necessary.
          if (callbackType === 'undefined' || callbackType === 'function') {
              //Pull out the defined dependencies and pass the ordered
              //values to the callback.
              //Default to [require, exports, module] if no deps
              deps = !deps.length && callback.length ? ['require', 'exports', 'module'] : deps;
              for (i = 0; i < deps.length; i += 1) {
                  map = makeMap(deps[i], relName);
                  depName = map.f;
  
                  //Fast path CommonJS standard dependencies.
                  if (depName === "require") {
                      args[i] = handlers.require(name);
                  } else if (depName === "exports") {
                      //CommonJS module spec 1.1
                      args[i] = handlers.exports(name);
                      usingExports = true;
                  } else if (depName === "module") {
                      //CommonJS module spec 1.1
                      cjsModule = args[i] = handlers.module(name);
                  } else if (hasProp(defined, depName) ||
                             hasProp(waiting, depName) ||
                             hasProp(defining, depName)) {
                      args[i] = callDep(depName);
                  } else if (map.p) {
                      map.p.load(map.n, makeRequire(relName, true), makeLoad(depName), {});
                      args[i] = defined[depName];
                  } else {
                      throw new Error(name + ' missing ' + depName);
                  }
              }
  
              ret = callback ? callback.apply(defined[name], args) : undefined;
  
              if (name) {
                  //If setting exports via "module" is in play,
                  //favor that over return value and exports. After that,
                  //favor a non-undefined return value over exports use.
                  if (cjsModule && cjsModule.exports !== undef &&
                          cjsModule.exports !== defined[name]) {
                      defined[name] = cjsModule.exports;
                  } else if (ret !== undef || !usingExports) {
                      //Use the return value from the function.
                      defined[name] = ret;
                  }
              }
          } else if (name) {
              //May just be an object definition for the module. Only
              //worry about defining if have a module name.
              defined[name] = callback;
          }
      };
  
      requirejs = require = req = function (deps, callback, relName, forceSync, alt) {
          if (typeof deps === "string") {
              if (handlers[deps]) {
                  //callback in this case is really relName
                  return handlers[deps](callback);
              }
              //Just return the module wanted. In this scenario, the
              //deps arg is the module name, and second arg (if passed)
              //is just the relName.
              //Normalize module name, if it contains . or ..
              return callDep(makeMap(deps, callback).f);
          } else if (!deps.splice) {
              //deps is a config object, not an array.
              config = deps;
              if (config.deps) {
                  req(config.deps, config.callback);
              }
              if (!callback) {
                  return;
              }
  
              if (callback.splice) {
                  //callback is an array, which means it is a dependency list.
                  //Adjust args if there are dependencies
                  deps = callback;
                  callback = relName;
                  relName = null;
              } else {
                  deps = undef;
              }
          }
  
          //Support require(['a'])
          callback = callback || function () {};
  
          //If relName is a function, it is an errback handler,
          //so remove it.
          if (typeof relName === 'function') {
              relName = forceSync;
              forceSync = alt;
          }
  
          //Simulate async callback;
          if (forceSync) {
              main(undef, deps, callback, relName);
          } else {
              //Using a non-zero value because of concern for what old browsers
              //do, and latest browsers "upgrade" to 4 if lower value is used:
              //http://www.whatwg.org/specs/web-apps/current-work/multipage/timers.html#dom-windowtimers-settimeout:
              //If want a value immediately, use require('id') instead -- something
              //that works in almond on the global level, but not guaranteed and
              //unlikely to work in other AMD implementations.
              setTimeout(function () {
                  main(undef, deps, callback, relName);
              }, 4);
          }
  
          return req;
      };
  
      /**
       * Just drops the config on the floor, but returns req in case
       * the config return value is used.
       */
      req.config = function (cfg) {
          return req(cfg);
      };
  
      /**
       * Expose module registry for debugging and tooling
       */
      requirejs._defined = defined;
  
      define = function (name, deps, callback) {
          if (typeof name !== 'string') {
              throw new Error('See almond README: incorrect module build, no module name');
          }
  
          //This module may not have dependencies
          if (!deps.splice) {
              //deps is not an array, so probably means
              //an object literal or factory function for
              //the value. Adjust args.
              callback = deps;
              deps = [];
          }
  
          if (!hasProp(defined, name) && !hasProp(waiting, name)) {
              waiting[name] = [name, deps, callback];
          }
      };
  
      define.amd = {
          jQuery: true
      };
  }());
  
  S2.requirejs = requirejs;S2.require = require;S2.define = define;
  }
  }());
  S2.define("almond", function(){});
  
  /* global jQuery:false, $:false */
  S2.define('jquery',[],function () {
    var _$ = jQuery || $;
  
    if (_$ == null && console && console.error) {
      console.error(
        'Select2: An instance of jQuery or a jQuery-compatible library was not ' +
        'found. Make sure that you are including jQuery before Select2 on your ' +
        'web page.'
      );
    }
  
    return _$;
  });
  
  S2.define('select2/utils',[
    'jquery'
  ], function ($) {
    var Utils = {};
  
    Utils.Extend = function (ChildClass, SuperClass) {
      var __hasProp = {}.hasOwnProperty;
  
      function BaseConstructor () {
        this.constructor = ChildClass;
      }
  
      for (var key in SuperClass) {
        if (__hasProp.call(SuperClass, key)) {
          ChildClass[key] = SuperClass[key];
        }
      }
  
      BaseConstructor.prototype = SuperClass.prototype;
      ChildClass.prototype = new BaseConstructor();
      ChildClass.__super__ = SuperClass.prototype;
  
      return ChildClass;
    };
  
    function getMethods (theClass) {
      var proto = theClass.prototype;
  
      var methods = [];
  
      for (var methodName in proto) {
        var m = proto[methodName];
  
        if (typeof m !== 'function') {
          continue;
        }
  
        if (methodName === 'constructor') {
          continue;
        }
  
        methods.push(methodName);
      }
  
      return methods;
    }
  
    Utils.Decorate = function (SuperClass, DecoratorClass) {
      var decoratedMethods = getMethods(DecoratorClass);
      var superMethods = getMethods(SuperClass);
  
      function DecoratedClass () {
        var unshift = Array.prototype.unshift;
  
        var argCount = DecoratorClass.prototype.constructor.length;
  
        var calledConstructor = SuperClass.prototype.constructor;
  
        if (argCount > 0) {
          unshift.call(arguments, SuperClass.prototype.constructor);
  
          calledConstructor = DecoratorClass.prototype.constructor;
        }
  
        calledConstructor.apply(this, arguments);
      }
  
      DecoratorClass.displayName = SuperClass.displayName;
  
      function ctr () {
        this.constructor = DecoratedClass;
      }
  
      DecoratedClass.prototype = new ctr();
  
      for (var m = 0; m < superMethods.length; m++) {
          var superMethod = superMethods[m];
  
          DecoratedClass.prototype[superMethod] =
            SuperClass.prototype[superMethod];
      }
  
      var calledMethod = function (methodName) {
        // Stub out the original method if it's not decorating an actual method
        var originalMethod = function () {};
  
        if (methodName in DecoratedClass.prototype) {
          originalMethod = DecoratedClass.prototype[methodName];
        }
  
        var decoratedMethod = DecoratorClass.prototype[methodName];
  
        return function () {
          var unshift = Array.prototype.unshift;
  
          unshift.call(arguments, originalMethod);
  
          return decoratedMethod.apply(this, arguments);
        };
      };
  
      for (var d = 0; d < decoratedMethods.length; d++) {
        var decoratedMethod = decoratedMethods[d];
  
        DecoratedClass.prototype[decoratedMethod] = calledMethod(decoratedMethod);
      }
  
      return DecoratedClass;
    };
  
    var Observable = function () {
      this.listeners = {};
    };
  
    Observable.prototype.on = function (event, callback) {
      this.listeners = this.listeners || {};
  
      if (event in this.listeners) {
        this.listeners[event].push(callback);
      } else {
        this.listeners[event] = [callback];
      }
    };
  
    Observable.prototype.trigger = function (event) {
      var slice = Array.prototype.slice;
      var params = slice.call(arguments, 1);
  
      this.listeners = this.listeners || {};
  
      // Params should always come in as an array
      if (params == null) {
        params = [];
      }
  
      // If there are no arguments to the event, use a temporary object
      if (params.length === 0) {
        params.push({});
      }
  
      // Set the `_type` of the first object to the event
      params[0]._type = event;
  
      if (event in this.listeners) {
        this.invoke(this.listeners[event], slice.call(arguments, 1));
      }
  
      if ('*' in this.listeners) {
        this.invoke(this.listeners['*'], arguments);
      }
    };
  
    Observable.prototype.invoke = function (listeners, params) {
      for (var i = 0, len = listeners.length; i < len; i++) {
        listeners[i].apply(this, params);
      }
    };
  
    Utils.Observable = Observable;
  
    Utils.generateChars = function (length) {
      var chars = '';
  
      for (var i = 0; i < length; i++) {
        var randomChar = Math.floor(Math.random() * 36);
        chars += randomChar.toString(36);
      }
  
      return chars;
    };
  
    Utils.bind = function (func, context) {
      return function () {
        func.apply(context, arguments);
      };
    };
  
    Utils._convertData = function (data) {
      for (var originalKey in data) {
        var keys = originalKey.split('-');
  
        var dataLevel = data;
  
        if (keys.length === 1) {
          continue;
        }
  
        for (var k = 0; k < keys.length; k++) {
          var key = keys[k];
  
          // Lowercase the first letter
          // By default, dash-separated becomes camelCase
          key = key.substring(0, 1).toLowerCase() + key.substring(1);
  
          if (!(key in dataLevel)) {
            dataLevel[key] = {};
          }
  
          if (k == keys.length - 1) {
            dataLevel[key] = data[originalKey];
          }
  
          dataLevel = dataLevel[key];
        }
  
        delete data[originalKey];
      }
  
      return data;
    };
  
    Utils.hasScroll = function (index, el) {
      // Adapted from the function created by @ShadowScripter
      // and adapted by @BillBarry on the Stack Exchange Code Review website.
      // The original code can be found at
      // http://codereview.stackexchange.com/q/13338
      // and was designed to be used with the Sizzle selector engine.
  
      var $el = $(el);
      var overflowX = el.style.overflowX;
      var overflowY = el.style.overflowY;
  
      //Check both x and y declarations
      if (overflowX === overflowY &&
          (overflowY === 'hidden' || overflowY === 'visible')) {
        return false;
      }
  
      if (overflowX === 'scroll' || overflowY === 'scroll') {
        return true;
      }
  
      return ($el.innerHeight() < el.scrollHeight ||
        $el.innerWidth() < el.scrollWidth);
    };
  
    Utils.escapeMarkup = function (markup) {
      var replaceMap = {
        '\\': '&#92;',
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        '\'': '&#39;',
        '/': '&#47;'
      };
  
      // Do not try to escape the markup if it's not a string
      if (typeof markup !== 'string') {
        return markup;
      }
  
      return String(markup).replace(/[&<>"'\/\\]/g, function (match) {
        return replaceMap[match];
      });
    };
  
    // Append an array of jQuery nodes to a given element.
    Utils.appendMany = function ($element, $nodes) {
      // jQuery 1.7.x does not support $.fn.append() with an array
      // Fall back to a jQuery object collection using $.fn.add()
      if ($.fn.jquery.substr(0, 3) === '1.7') {
        var $jqNodes = $();
  
        $.map($nodes, function (node) {
          $jqNodes = $jqNodes.add(node);
        });
  
        $nodes = $jqNodes;
      }
  
      $element.append($nodes);
    };
  
    return Utils;
  });
  
  S2.define('select2/results',[
    'jquery',
    './utils'
  ], function ($, Utils) {
    function Results ($element, options, dataAdapter) {
      this.$element = $element;
      this.data = dataAdapter;
      this.options = options;
  
      Results.__super__.constructor.call(this);
    }
  
    Utils.Extend(Results, Utils.Observable);
  
    Results.prototype.render = function () {
      var $results = $(
        '<ul class="select2-results__options" role="tree"></ul>'
      );
  
      if (this.options.get('multiple')) {
        $results.attr('aria-multiselectable', 'true');
      }
  
      this.$results = $results;
  
      return $results;
    };
  
    Results.prototype.clear = function () {
      this.$results.empty();
    };
  
    Results.prototype.displayMessage = function (params) {
      var escapeMarkup = this.options.get('escapeMarkup');
  
      this.clear();
      this.hideLoading();
  
      var $message = $(
        '<li role="treeitem" aria-live="assertive"' +
        ' class="select2-results__option"></li>'
      );
  
      var message = this.options.get('translations').get(params.message);
  
      $message.append(
        escapeMarkup(
          message(params.args)
        )
      );
  
      $message[0].className += ' select2-results__message';
  
      this.$results.append($message);
    };
  
    Results.prototype.hideMessages = function () {
      this.$results.find('.select2-results__message').remove();
    };
  
    Results.prototype.append = function (data) {
      this.hideLoading();
  
      var $options = [];
  
      if (data.results == null || data.results.length === 0) {
        if (this.$results.children().length === 0) {
          this.trigger('results:message', {
            message: 'noResults'
          });
        }
  
        return;
      }
  
      data.results = this.sort(data.results);
  
      for (var d = 0; d < data.results.length; d++) {
        var item = data.results[d];
  
        var $option = this.option(item);
  
        $options.push($option);
      }
  
      this.$results.append($options);
    };
  
    Results.prototype.position = function ($results, $dropdown) {
      var $resultsContainer = $dropdown.find('.select2-results');
      $resultsContainer.append($results);
    };
  
    Results.prototype.sort = function (data) {
      var sorter = this.options.get('sorter');
  
      return sorter(data);
    };
  
    Results.prototype.highlightFirstItem = function () {
      var $options = this.$results
        .find('.select2-results__option[aria-selected]');
  
      var $selected = $options.filter('[aria-selected=true]');
  
      // Check if there are any selected options
      if ($selected.length > 0) {
        // If there are selected options, highlight the first
        $selected.first().trigger('mouseenter');
      } else {
        // If there are no selected options, highlight the first option
        // in the dropdown
        $options.first().trigger('mouseenter');
      }
  
      this.ensureHighlightVisible();
    };
  
    Results.prototype.setClasses = function () {
      var self = this;
  
      this.data.current(function (selected) {
        var selectedIds = $.map(selected, function (s) {
          return s.id.toString();
        });
  
        var $options = self.$results
          .find('.select2-results__option[aria-selected]');
  
        $options.each(function () {
          var $option = $(this);
  
          var item = $.data(this, 'data');
  
          // id needs to be converted to a string when comparing
          var id = '' + item.id;
  
          if ((item.element != null && item.element.selected) ||
              (item.element == null && $.inArray(id, selectedIds) > -1)) {
            $option.attr('aria-selected', 'true');
          } else {
            $option.attr('aria-selected', 'false');
          }
        });
  
      });
    };
  
    Results.prototype.showLoading = function (params) {
      this.hideLoading();
  
      var loadingMore = this.options.get('translations').get('searching');
  
      var loading = {
        disabled: true,
        loading: true,
        text: loadingMore(params)
      };
      var $loading = this.option(loading);
      $loading.className += ' loading-results';
  
      this.$results.prepend($loading);
    };
  
    Results.prototype.hideLoading = function () {
      this.$results.find('.loading-results').remove();
    };
  
    Results.prototype.option = function (data) {
      var option = document.createElement('li');
      option.className = 'select2-results__option';
  
      var attrs = {
        'role': 'treeitem',
        'aria-selected': 'false'
      };
  
      if (data.disabled) {
        delete attrs['aria-selected'];
        attrs['aria-disabled'] = 'true';
      }
  
      if (data.id == null) {
        delete attrs['aria-selected'];
      }
  
      if (data._resultId != null) {
        option.id = data._resultId;
      }
  
      if (data.title) {
        option.title = data.title;
      }
  
      if (data.children) {
        attrs.role = 'group';
        attrs['aria-label'] = data.text;
        delete attrs['aria-selected'];
      }
  
      for (var attr in attrs) {
        var val = attrs[attr];
  
        option.setAttribute(attr, val);
      }
  
      if (data.children) {
        var $option = $(option);
  
        var label = document.createElement('strong');
        label.className = 'select2-results__group';
  
        var $label = $(label);
        this.template(data, label);
  
        var $children = [];
  
        for (var c = 0; c < data.children.length; c++) {
          var child = data.children[c];
  
          var $child = this.option(child);
  
          $children.push($child);
        }
  
        var $childrenContainer = $('<ul></ul>', {
          'class': 'select2-results__options select2-results__options--nested'
        });
  
        $childrenContainer.append($children);
  
        $option.append(label);
        $option.append($childrenContainer);
      } else {
        this.template(data, option);
      }
  
      $.data(option, 'data', data);
  
      return option;
    };
  
    Results.prototype.bind = function (container, $container) {
      var self = this;
  
      var id = container.id + '-results';
  
      this.$results.attr('id', id);
  
      container.on('results:all', function (params) {
        self.clear();
        self.append(params.data);
  
        if (container.isOpen()) {
          self.setClasses();
          self.highlightFirstItem();
        }
      });
  
      container.on('results:append', function (params) {
        self.append(params.data);
  
        if (container.isOpen()) {
          self.setClasses();
        }
      });
  
      container.on('query', function (params) {
        self.hideMessages();
        self.showLoading(params);
      });
  
      container.on('select', function () {
        if (!container.isOpen()) {
          return;
        }
  
        self.setClasses();
        self.highlightFirstItem();
      });
  
      container.on('unselect', function () {
        if (!container.isOpen()) {
          return;
        }
  
        self.setClasses();
        self.highlightFirstItem();
      });
  
      container.on('open', function () {
        // When the dropdown is open, aria-expended="true"
        self.$results.attr('aria-expanded', 'true');
        self.$results.attr('aria-hidden', 'false');
  
        self.setClasses();
        self.ensureHighlightVisible();
      });
  
      container.on('close', function () {
        // When the dropdown is closed, aria-expended="false"
        self.$results.attr('aria-expanded', 'false');
        self.$results.attr('aria-hidden', 'true');
        self.$results.removeAttr('aria-activedescendant');
      });
  
      container.on('results:toggle', function () {
        var $highlighted = self.getHighlightedResults();
  
        if ($highlighted.length === 0) {
          return;
        }
  
        $highlighted.trigger('mouseup');
      });
  
      container.on('results:select', function () {
        var $highlighted = self.getHighlightedResults();
  
        if ($highlighted.length === 0) {
          return;
        }
  
        var data = $highlighted.data('data');
  
        if ($highlighted.attr('aria-selected') == 'true') {
          self.trigger('close', {});
        } else {
          self.trigger('select', {
            data: data
          });
        }
      });
  
      container.on('results:previous', function () {
        var $highlighted = self.getHighlightedResults();
  
        var $options = self.$results.find('[aria-selected]');
  
        var currentIndex = $options.index($highlighted);
  
        // If we are already at te top, don't move further
        if (currentIndex === 0) {
          return;
        }
  
        var nextIndex = currentIndex - 1;
  
        // If none are highlighted, highlight the first
        if ($highlighted.length === 0) {
          nextIndex = 0;
        }
  
        var $next = $options.eq(nextIndex);
  
        $next.trigger('mouseenter');
  
        var currentOffset = self.$results.offset().top;
        var nextTop = $next.offset().top;
        var nextOffset = self.$results.scrollTop() + (nextTop - currentOffset);
  
        if (nextIndex === 0) {
          self.$results.scrollTop(0);
        } else if (nextTop - currentOffset < 0) {
          self.$results.scrollTop(nextOffset);
        }
      });
  
      container.on('results:next', function () {
        var $highlighted = self.getHighlightedResults();
  
        var $options = self.$results.find('[aria-selected]');
  
        var currentIndex = $options.index($highlighted);
  
        var nextIndex = currentIndex + 1;
  
        // If we are at the last option, stay there
        if (nextIndex >= $options.length) {
          return;
        }
  
        var $next = $options.eq(nextIndex);
  
        $next.trigger('mouseenter');
  
        var currentOffset = self.$results.offset().top +
          self.$results.outerHeight(false);
        var nextBottom = $next.offset().top + $next.outerHeight(false);
        var nextOffset = self.$results.scrollTop() + nextBottom - currentOffset;
  
        if (nextIndex === 0) {
          self.$results.scrollTop(0);
        } else if (nextBottom > currentOffset) {
          self.$results.scrollTop(nextOffset);
        }
      });
  
      container.on('results:focus', function (params) {
        params.element.addClass('select2-results__option--highlighted');
      });
  
      container.on('results:message', function (params) {
        self.displayMessage(params);
      });
  
      if ($.fn.mousewheel) {
        this.$results.on('mousewheel', function (e) {
          var top = self.$results.scrollTop();
  
          var bottom = self.$results.get(0).scrollHeight - top + e.deltaY;
  
          var isAtTop = e.deltaY > 0 && top - e.deltaY <= 0;
          var isAtBottom = e.deltaY < 0 && bottom <= self.$results.height();
  
          if (isAtTop) {
            self.$results.scrollTop(0);
  
            e.preventDefault();
            e.stopPropagation();
          } else if (isAtBottom) {
            self.$results.scrollTop(
              self.$results.get(0).scrollHeight - self.$results.height()
            );
  
            e.preventDefault();
            e.stopPropagation();
          }
        });
      }
  
      this.$results.on('mouseup', '.select2-results__option[aria-selected]',
        function (evt) {
        var $this = $(this);
  
        var data = $this.data('data');
  
        if ($this.attr('aria-selected') === 'true') {
          if (self.options.get('multiple')) {
            self.trigger('unselect', {
              originalEvent: evt,
              data: data
            });
          } else {
            self.trigger('close', {});
          }
  
          return;
        }
  
        self.trigger('select', {
          originalEvent: evt,
          data: data
        });
      });
  
      this.$results.on('mouseenter', '.select2-results__option[aria-selected]',
        function (evt) {
        var data = $(this).data('data');
  
        self.getHighlightedResults()
            .removeClass('select2-results__option--highlighted');
  
        self.trigger('results:focus', {
          data: data,
          element: $(this)
        });
      });
    };
  
    Results.prototype.getHighlightedResults = function () {
      var $highlighted = this.$results
      .find('.select2-results__option--highlighted');
  
      return $highlighted;
    };
  
    Results.prototype.destroy = function () {
      this.$results.remove();
    };
  
    Results.prototype.ensureHighlightVisible = function () {
      var $highlighted = this.getHighlightedResults();
  
      if ($highlighted.length === 0) {
        return;
      }
  
      var $options = this.$results.find('[aria-selected]');
  
      var currentIndex = $options.index($highlighted);
  
      var currentOffset = this.$results.offset().top;
      var nextTop = $highlighted.offset().top;
      var nextOffset = this.$results.scrollTop() + (nextTop - currentOffset);
  
      var offsetDelta = nextTop - currentOffset;
      nextOffset -= $highlighted.outerHeight(false) * 2;
  
      if (currentIndex <= 2) {
        this.$results.scrollTop(0);
      } else if (offsetDelta > this.$results.outerHeight() || offsetDelta < 0) {
        this.$results.scrollTop(nextOffset);
      }
    };
  
    Results.prototype.template = function (result, container) {
      var template = this.options.get('templateResult');
      var escapeMarkup = this.options.get('escapeMarkup');
  
      var content = template(result, container);
  
      if (content == null) {
        container.style.display = 'none';
      } else if (typeof content === 'string') {
        container.innerHTML = escapeMarkup(content);
      } else {
        $(container).append(content);
      }
    };
  
    return Results;
  });
  
  S2.define('select2/keys',[
  
  ], function () {
    var KEYS = {
      BACKSPACE: 8,
      TAB: 9,
      ENTER: 13,
      SHIFT: 16,
      CTRL: 17,
      ALT: 18,
      ESC: 27,
      SPACE: 32,
      PAGE_UP: 33,
      PAGE_DOWN: 34,
      END: 35,
      HOME: 36,
      LEFT: 37,
      UP: 38,
      RIGHT: 39,
      DOWN: 40,
      DELETE: 46
    };
  
    return KEYS;
  });
  
  S2.define('select2/selection/base',[
    'jquery',
    '../utils',
    '../keys'
  ], function ($, Utils, KEYS) {
    function BaseSelection ($element, options) {
      this.$element = $element;
      this.options = options;
  
      BaseSelection.__super__.constructor.call(this);
    }
  
    Utils.Extend(BaseSelection, Utils.Observable);
  
    BaseSelection.prototype.render = function () {
      var $selection = $(
        '<span class="select2-selection" role="combobox" ' +
        ' aria-haspopup="true" aria-expanded="false">' +
        '</span>'
      );
  
      this._tabindex = 0;
  
      if (this.$element.data('old-tabindex') != null) {
        this._tabindex = this.$element.data('old-tabindex');
      } else if (this.$element.attr('tabindex') != null) {
        this._tabindex = this.$element.attr('tabindex');
      }
  
      $selection.attr('title', this.$element.attr('title'));
      $selection.attr('tabindex', this._tabindex);
  
      this.$selection = $selection;
  
      return $selection;
    };
  
    BaseSelection.prototype.bind = function (container, $container) {
      var self = this;
  
      var id = container.id + '-container';
      var resultsId = container.id + '-results';
  
      this.container = container;
  
      this.$selection.on('focus', function (evt) {
        self.trigger('focus', evt);
      });
  
      this.$selection.on('blur', function (evt) {
        self._handleBlur(evt);
      });
  
      this.$selection.on('keydown', function (evt) {
        self.trigger('keypress', evt);
  
        if (evt.which === KEYS.SPACE) {
          evt.preventDefault();
        }
      });
  
      container.on('results:focus', function (params) {
        self.$selection.attr('aria-activedescendant', params.data._resultId);
      });
  
      container.on('selection:update', function (params) {
        self.update(params.data);
      });
  
      container.on('open', function () {
        // When the dropdown is open, aria-expanded="true"
        self.$selection.attr('aria-expanded', 'true');
        self.$selection.attr('aria-owns', resultsId);
  
        self._attachCloseHandler(container);
      });
  
      container.on('close', function () {
        // When the dropdown is closed, aria-expanded="false"
        self.$selection.attr('aria-expanded', 'false');
        self.$selection.removeAttr('aria-activedescendant');
        self.$selection.removeAttr('aria-owns');
  
        self.$selection.focus();
  
        self._detachCloseHandler(container);
      });
  
      container.on('enable', function () {
        self.$selection.attr('tabindex', self._tabindex);
      });
  
      container.on('disable', function () {
        self.$selection.attr('tabindex', '-1');
      });
    };
  
    BaseSelection.prototype._handleBlur = function (evt) {
      var self = this;
  
      // This needs to be delayed as the active element is the body when the tab
      // key is pressed, possibly along with others.
      window.setTimeout(function () {
        // Don't trigger `blur` if the focus is still in the selection
        if (
          (document.activeElement == self.$selection[0]) ||
          ($.contains(self.$selection[0], document.activeElement))
        ) {
          return;
        }
  
        self.trigger('blur', evt);
      }, 1);
    };
  
    BaseSelection.prototype._attachCloseHandler = function (container) {
      var self = this;
  
      $(document.body).on('mousedown.select2.' + container.id, function (e) {
        var $target = $(e.target);
  
        var $select = $target.closest('.select2');
  
        var $all = $('.select2.select2-container--open');
  
        $all.each(function () {
          var $this = $(this);
  
          if (this == $select[0]) {
            return;
          }
  
          var $element = $this.data('element');
  
          $element.select2('close');
        });
      });
    };
  
    BaseSelection.prototype._detachCloseHandler = function (container) {
      $(document.body).off('mousedown.select2.' + container.id);
    };
  
    BaseSelection.prototype.position = function ($selection, $container) {
      var $selectionContainer = $container.find('.selection');
      $selectionContainer.append($selection);
    };
  
    BaseSelection.prototype.destroy = function () {
      this._detachCloseHandler(this.container);
    };
  
    BaseSelection.prototype.update = function (data) {
      throw new Error('The `update` method must be defined in child classes.');
    };
  
    return BaseSelection;
  });
  
  S2.define('select2/selection/single',[
    'jquery',
    './base',
    '../utils',
    '../keys'
  ], function ($, BaseSelection, Utils, KEYS) {
    function SingleSelection () {
      SingleSelection.__super__.constructor.apply(this, arguments);
    }
  
    Utils.Extend(SingleSelection, BaseSelection);
  
    SingleSelection.prototype.render = function () {
      var $selection = SingleSelection.__super__.render.call(this);
  
      $selection.addClass('select2-selection--single');
  
      $selection.html(
        '<span class="select2-selection__rendered"></span>' +
        '<span class="select2-selection__arrow" role="presentation">' +
          '<b role="presentation"></b>' +
        '</span>'
      );
  
      return $selection;
    };
  
    SingleSelection.prototype.bind = function (container, $container) {
      var self = this;
  
      SingleSelection.__super__.bind.apply(this, arguments);
  
      var id = container.id + '-container';
  
      this.$selection.find('.select2-selection__rendered').attr('id', id);
      this.$selection.attr('aria-labelledby', id);
  
      this.$selection.on('mousedown', function (evt) {
        // Only respond to left clicks
        if (evt.which !== 1) {
          return;
        }
  
        self.trigger('toggle', {
          originalEvent: evt
        });
      });
  
      this.$selection.on('focus', function (evt) {
        // User focuses on the container
      });
  
      this.$selection.on('blur', function (evt) {
        // User exits the container
      });
  
      container.on('focus', function (evt) {
        if (!container.isOpen()) {
          self.$selection.focus();
        }
      });
  
      container.on('selection:update', function (params) {
        self.update(params.data);
      });
    };
  
    SingleSelection.prototype.clear = function () {
      this.$selection.find('.select2-selection__rendered').empty();
    };
  
    SingleSelection.prototype.display = function (data, container) {
      var template = this.options.get('templateSelection');
      var escapeMarkup = this.options.get('escapeMarkup');
  
      return escapeMarkup(template(data, container));
    };
  
    SingleSelection.prototype.selectionContainer = function () {
      return $('<span></span>');
    };
  
    SingleSelection.prototype.update = function (data) {
      if (data.length === 0) {
        this.clear();
        return;
      }
  
      var selection = data[0];
  
      var $rendered = this.$selection.find('.select2-selection__rendered');
      var formatted = this.display(selection, $rendered);
  
      $rendered.empty().append(formatted);
      $rendered.prop('title', selection.title || selection.text);
    };
  
    return SingleSelection;
  });
  
  S2.define('select2/selection/multiple',[
    'jquery',
    './base',
    '../utils'
  ], function ($, BaseSelection, Utils) {
    function MultipleSelection ($element, options) {
      MultipleSelection.__super__.constructor.apply(this, arguments);
    }
  
    Utils.Extend(MultipleSelection, BaseSelection);
  
    MultipleSelection.prototype.render = function () {
      var $selection = MultipleSelection.__super__.render.call(this);
  
      $selection.addClass('select2-selection--multiple');
  
      $selection.html(
        '<ul class="select2-selection__rendered"></ul>'
      );
  
      return $selection;
    };
  
    MultipleSelection.prototype.bind = function (container, $container) {
      var self = this;
  
      MultipleSelection.__super__.bind.apply(this, arguments);
  
      this.$selection.on('click', function (evt) {
        self.trigger('toggle', {
          originalEvent: evt
        });
      });
  
      this.$selection.on(
        'click',
        '.select2-selection__choice__remove',
        function (evt) {
          // Ignore the event if it is disabled
          if (self.options.get('disabled')) {
            return;
          }
  
          var $remove = $(this);
          var $selection = $remove.parent();
  
          var data = $selection.data('data');
  
          self.trigger('unselect', {
            originalEvent: evt,
            data: data
          });
        }
      );
    };
  
    MultipleSelection.prototype.clear = function () {
      this.$selection.find('.select2-selection__rendered').empty();
    };
  
    MultipleSelection.prototype.display = function (data, container) {
      var template = this.options.get('templateSelection');
      var escapeMarkup = this.options.get('escapeMarkup');
  
      return escapeMarkup(template(data, container));
    };
  
    MultipleSelection.prototype.selectionContainer = function () {
      var $container = $(
        '<li class="select2-selection__choice">' +
          '<span class="select2-selection__choice__remove" role="presentation">' +
            '&times;' +
          '</span>' +
        '</li>'
      );
  
      return $container;
    };
  
    MultipleSelection.prototype.update = function (data) {
      this.clear();
  
      if (data.length === 0) {
        return;
      }
  
      var $selections = [];
  
      for (var d = 0; d < data.length; d++) {
        var selection = data[d];
  
        var $selection = this.selectionContainer();
        var formatted = this.display(selection, $selection);
  
        $selection.append(formatted);
        $selection.prop('title', selection.title || selection.text);
  
        $selection.data('data', selection);
  
        $selections.push($selection);
      }
  
      var $rendered = this.$selection.find('.select2-selection__rendered');
  
      Utils.appendMany($rendered, $selections);
    };
  
    return MultipleSelection;
  });
  
  S2.define('select2/selection/placeholder',[
    '../utils'
  ], function (Utils) {
    function Placeholder (decorated, $element, options) {
      this.placeholder = this.normalizePlaceholder(options.get('placeholder'));
  
      decorated.call(this, $element, options);
    }
  
    Placeholder.prototype.normalizePlaceholder = function (_, placeholder) {
      if (typeof placeholder === 'string') {
        placeholder = {
          id: '',
          text: placeholder
        };
      }
  
      return placeholder;
    };
  
    Placeholder.prototype.createPlaceholder = function (decorated, placeholder) {
      var $placeholder = this.selectionContainer();
  
      $placeholder.html(this.display(placeholder));
      $placeholder.addClass('select2-selection__placeholder')
                  .removeClass('select2-selection__choice');
  
      return $placeholder;
    };
  
    Placeholder.prototype.update = function (decorated, data) {
      var singlePlaceholder = (
        data.length == 1 && data[0].id != this.placeholder.id
      );
      var multipleSelections = data.length > 1;
  
      if (multipleSelections || singlePlaceholder) {
        return decorated.call(this, data);
      }
  
      this.clear();
  
      var $placeholder = this.createPlaceholder(this.placeholder);
  
      this.$selection.find('.select2-selection__rendered').append($placeholder);
    };
  
    return Placeholder;
  });
  
  S2.define('select2/selection/allowClear',[
    'jquery',
    '../keys'
  ], function ($, KEYS) {
    function AllowClear () { }
  
    AllowClear.prototype.bind = function (decorated, container, $container) {
      var self = this;
  
      decorated.call(this, container, $container);
  
      if (this.placeholder == null) {
        if (this.options.get('debug') && window.console && console.error) {
          console.error(
            'Select2: The `allowClear` option should be used in combination ' +
            'with the `placeholder` option.'
          );
        }
      }
  
      this.$selection.on('mousedown', '.select2-selection__clear',
        function (evt) {
          self._handleClear(evt);
      });
  
      container.on('keypress', function (evt) {
        self._handleKeyboardClear(evt, container);
      });
    };
  
    AllowClear.prototype._handleClear = function (_, evt) {
      // Ignore the event if it is disabled
      if (this.options.get('disabled')) {
        return;
      }
  
      var $clear = this.$selection.find('.select2-selection__clear');
  
      // Ignore the event if nothing has been selected
      if ($clear.length === 0) {
        return;
      }
  
      evt.stopPropagation();
  
      var data = $clear.data('data');
  
      for (var d = 0; d < data.length; d++) {
        var unselectData = {
          data: data[d]
        };
  
        // Trigger the `unselect` event, so people can prevent it from being
        // cleared.
        this.trigger('unselect', unselectData);
  
        // If the event was prevented, don't clear it out.
        if (unselectData.prevented) {
          return;
        }
      }
  
      this.$element.val(this.placeholder.id).trigger('change');
  
      this.trigger('toggle', {});
    };
  
    AllowClear.prototype._handleKeyboardClear = function (_, evt, container) {
      if (container.isOpen()) {
        return;
      }
  
      if (evt.which == KEYS.DELETE || evt.which == KEYS.BACKSPACE) {
        this._handleClear(evt);
      }
    };
  
    AllowClear.prototype.update = function (decorated, data) {
      decorated.call(this, data);
  
      if (this.$selection.find('.select2-selection__placeholder').length > 0 ||
          data.length === 0) {
        return;
      }
  
      var $remove = $(
        '<span class="select2-selection__clear">' +
          '&times;' +
        '</span>'
      );
      $remove.data('data', data);
  
      this.$selection.find('.select2-selection__rendered').prepend($remove);
    };
  
    return AllowClear;
  });
  
  S2.define('select2/selection/search',[
    'jquery',
    '../utils',
    '../keys'
  ], function ($, Utils, KEYS) {
    function Search (decorated, $element, options) {
      decorated.call(this, $element, options);
    }
  
    Search.prototype.render = function (decorated) {
      var $search = $(
        '<li class="select2-search select2-search--inline">' +
          '<input class="select2-search__field" type="search" tabindex="-1"' +
          ' autocomplete="off" autocorrect="off" autocapitalize="off"' +
          ' spellcheck="false" role="textbox" aria-autocomplete="list" />' +
        '</li>'
      );
  
      this.$searchContainer = $search;
      this.$search = $search.find('input');
  
      var $rendered = decorated.call(this);
  
      this._transferTabIndex();
  
      return $rendered;
    };
  
    Search.prototype.bind = function (decorated, container, $container) {
      var self = this;
  
      decorated.call(this, container, $container);
  
      container.on('open', function () {
        self.$search.trigger('focus');
      });
  
      container.on('close', function () {
        self.$search.val('');
        self.$search.removeAttr('aria-activedescendant');
        self.$search.trigger('focus');
      });
  
      container.on('enable', function () {
        self.$search.prop('disabled', false);
  
        self._transferTabIndex();
      });
  
      container.on('disable', function () {
        self.$search.prop('disabled', true);
      });
  
      container.on('focus', function (evt) {
        self.$search.trigger('focus');
      });
  
      container.on('results:focus', function (params) {
        self.$search.attr('aria-activedescendant', params.id);
      });
  
      this.$selection.on('focusin', '.select2-search--inline', function (evt) {
        self.trigger('focus', evt);
      });
  
      this.$selection.on('focusout', '.select2-search--inline', function (evt) {
        self._handleBlur(evt);
      });
  
      this.$selection.on('keydown', '.select2-search--inline', function (evt) {
        evt.stopPropagation();
  
        self.trigger('keypress', evt);
  
        self._keyUpPrevented = evt.isDefaultPrevented();
  
        var key = evt.which;
  
        if (key === KEYS.BACKSPACE && self.$search.val() === '') {
          var $previousChoice = self.$searchContainer
            .prev('.select2-selection__choice');
  
          if ($previousChoice.length > 0) {
            var item = $previousChoice.data('data');
  
            self.searchRemoveChoice(item);
  
            evt.preventDefault();
          }
        }
      });
  
      // Try to detect the IE version should the `documentMode` property that
      // is stored on the document. This is only implemented in IE and is
      // slightly cleaner than doing a user agent check.
      // This property is not available in Edge, but Edge also doesn't have
      // this bug.
      var msie = document.documentMode;
      var disableInputEvents = msie && msie <= 11;
  
      // Workaround for browsers which do not support the `input` event
      // This will prevent double-triggering of events for browsers which support
      // both the `keyup` and `input` events.
      this.$selection.on(
        'input.searchcheck',
        '.select2-search--inline',
        function (evt) {
          // IE will trigger the `input` event when a placeholder is used on a
          // search box. To get around this issue, we are forced to ignore all
          // `input` events in IE and keep using `keyup`.
          if (disableInputEvents) {
            self.$selection.off('input.search input.searchcheck');
            return;
          }
  
          // Unbind the duplicated `keyup` event
          self.$selection.off('keyup.search');
        }
      );
  
      this.$selection.on(
        'keyup.search input.search',
        '.select2-search--inline',
        function (evt) {
          // IE will trigger the `input` event when a placeholder is used on a
          // search box. To get around this issue, we are forced to ignore all
          // `input` events in IE and keep using `keyup`.
          if (disableInputEvents && evt.type === 'input') {
            self.$selection.off('input.search input.searchcheck');
            return;
          }
  
          var key = evt.which;
  
          // We can freely ignore events from modifier keys
          if (key == KEYS.SHIFT || key == KEYS.CTRL || key == KEYS.ALT) {
            return;
          }
  
          // Tabbing will be handled during the `keydown` phase
          if (key == KEYS.TAB) {
            return;
          }
  
          self.handleSearch(evt);
        }
      );
    };
  
    /**
     * This method will transfer the tabindex attribute from the rendered
     * selection to the search box. This allows for the search box to be used as
     * the primary focus instead of the selection container.
     *
     * @private
     */
    Search.prototype._transferTabIndex = function (decorated) {
      this.$search.attr('tabindex', this.$selection.attr('tabindex'));
      this.$selection.attr('tabindex', '-1');
    };
  
    Search.prototype.createPlaceholder = function (decorated, placeholder) {
      this.$search.attr('placeholder', placeholder.text);
    };
  
    Search.prototype.update = function (decorated, data) {
      var searchHadFocus = this.$search[0] == document.activeElement;
  
      this.$search.attr('placeholder', '');
  
      decorated.call(this, data);
  
      this.$selection.find('.select2-selection__rendered')
                     .append(this.$searchContainer);
  
      this.resizeSearch();
      if (searchHadFocus) {
        this.$search.focus();
      }
    };
  
    Search.prototype.handleSearch = function () {
      this.resizeSearch();
  
      if (!this._keyUpPrevented) {
        var input = this.$search.val();
  
        this.trigger('query', {
          term: input
        });
      }
  
      this._keyUpPrevented = false;
    };
  
    Search.prototype.searchRemoveChoice = function (decorated, item) {
      this.trigger('unselect', {
        data: item
      });
  
      this.$search.val(item.text);
      this.handleSearch();
    };
  
    Search.prototype.resizeSearch = function () {
      this.$search.css('width', '25px');
  
      var width = '';
  
      if (this.$search.attr('placeholder') !== '') {
        width = this.$selection.find('.select2-selection__rendered').innerWidth();
      } else {
        var minimumWidth = this.$search.val().length + 1;
  
        width = (minimumWidth * 0.75) + 'em';
      }
  
      this.$search.css('width', width);
    };
  
    return Search;
  });
  
  S2.define('select2/selection/eventRelay',[
    'jquery'
  ], function ($) {
    function EventRelay () { }
  
    EventRelay.prototype.bind = function (decorated, container, $container) {
      var self = this;
      var relayEvents = [
        'open', 'opening',
        'close', 'closing',
        'select', 'selecting',
        'unselect', 'unselecting'
      ];
  
      var preventableEvents = ['opening', 'closing', 'selecting', 'unselecting'];
  
      decorated.call(this, container, $container);
  
      container.on('*', function (name, params) {
        // Ignore events that should not be relayed
        if ($.inArray(name, relayEvents) === -1) {
          return;
        }
  
        // The parameters should always be an object
        params = params || {};
  
        // Generate the jQuery event for the Select2 event
        var evt = $.Event('select2:' + name, {
          params: params
        });
  
        self.$element.trigger(evt);
  
        // Only handle preventable events if it was one
        if ($.inArray(name, preventableEvents) === -1) {
          return;
        }
  
        params.prevented = evt.isDefaultPrevented();
      });
    };
  
    return EventRelay;
  });
  
  S2.define('select2/translation',[
    'jquery',
    'require'
  ], function ($, require) {
    function Translation (dict) {
      this.dict = dict || {};
    }
  
    Translation.prototype.all = function () {
      return this.dict;
    };
  
    Translation.prototype.get = function (key) {
      return this.dict[key];
    };
  
    Translation.prototype.extend = function (translation) {
      this.dict = $.extend({}, translation.all(), this.dict);
    };
  
    // Static functions
  
    Translation._cache = {};
  
    Translation.loadPath = function (path) {
      if (!(path in Translation._cache)) {
        var translations = require(path);
  
        Translation._cache[path] = translations;
      }
  
      return new Translation(Translation._cache[path]);
    };
  
    return Translation;
  });
  
  S2.define('select2/diacritics',[
  
  ], function () {
    var diacritics = {
      '\u24B6': 'A',
      '\uFF21': 'A',
      '\u00C0': 'A',
      '\u00C1': 'A',
      '\u00C2': 'A',
      '\u1EA6': 'A',
      '\u1EA4': 'A',
      '\u1EAA': 'A',
      '\u1EA8': 'A',
      '\u00C3': 'A',
      '\u0100': 'A',
      '\u0102': 'A',
      '\u1EB0': 'A',
      '\u1EAE': 'A',
      '\u1EB4': 'A',
      '\u1EB2': 'A',
      '\u0226': 'A',
      '\u01E0': 'A',
      '\u00C4': 'A',
      '\u01DE': 'A',
      '\u1EA2': 'A',
      '\u00C5': 'A',
      '\u01FA': 'A',
      '\u01CD': 'A',
      '\u0200': 'A',
      '\u0202': 'A',
      '\u1EA0': 'A',
      '\u1EAC': 'A',
      '\u1EB6': 'A',
      '\u1E00': 'A',
      '\u0104': 'A',
      '\u023A': 'A',
      '\u2C6F': 'A',
      '\uA732': 'AA',
      '\u00C6': 'AE',
      '\u01FC': 'AE',
      '\u01E2': 'AE',
      '\uA734': 'AO',
      '\uA736': 'AU',
      '\uA738': 'AV',
      '\uA73A': 'AV',
      '\uA73C': 'AY',
      '\u24B7': 'B',
      '\uFF22': 'B',
      '\u1E02': 'B',
      '\u1E04': 'B',
      '\u1E06': 'B',
      '\u0243': 'B',
      '\u0182': 'B',
      '\u0181': 'B',
      '\u24B8': 'C',
      '\uFF23': 'C',
      '\u0106': 'C',
      '\u0108': 'C',
      '\u010A': 'C',
      '\u010C': 'C',
      '\u00C7': 'C',
      '\u1E08': 'C',
      '\u0187': 'C',
      '\u023B': 'C',
      '\uA73E': 'C',
      '\u24B9': 'D',
      '\uFF24': 'D',
      '\u1E0A': 'D',
      '\u010E': 'D',
      '\u1E0C': 'D',
      '\u1E10': 'D',
      '\u1E12': 'D',
      '\u1E0E': 'D',
      '\u0110': 'D',
      '\u018B': 'D',
      '\u018A': 'D',
      '\u0189': 'D',
      '\uA779': 'D',
      '\u01F1': 'DZ',
      '\u01C4': 'DZ',
      '\u01F2': 'Dz',
      '\u01C5': 'Dz',
      '\u24BA': 'E',
      '\uFF25': 'E',
      '\u00C8': 'E',
      '\u00C9': 'E',
      '\u00CA': 'E',
      '\u1EC0': 'E',
      '\u1EBE': 'E',
      '\u1EC4': 'E',
      '\u1EC2': 'E',
      '\u1EBC': 'E',
      '\u0112': 'E',
      '\u1E14': 'E',
      '\u1E16': 'E',
      '\u0114': 'E',
      '\u0116': 'E',
      '\u00CB': 'E',
      '\u1EBA': 'E',
      '\u011A': 'E',
      '\u0204': 'E',
      '\u0206': 'E',
      '\u1EB8': 'E',
      '\u1EC6': 'E',
      '\u0228': 'E',
      '\u1E1C': 'E',
      '\u0118': 'E',
      '\u1E18': 'E',
      '\u1E1A': 'E',
      '\u0190': 'E',
      '\u018E': 'E',
      '\u24BB': 'F',
      '\uFF26': 'F',
      '\u1E1E': 'F',
      '\u0191': 'F',
      '\uA77B': 'F',
      '\u24BC': 'G',
      '\uFF27': 'G',
      '\u01F4': 'G',
      '\u011C': 'G',
      '\u1E20': 'G',
      '\u011E': 'G',
      '\u0120': 'G',
      '\u01E6': 'G',
      '\u0122': 'G',
      '\u01E4': 'G',
      '\u0193': 'G',
      '\uA7A0': 'G',
      '\uA77D': 'G',
      '\uA77E': 'G',
      '\u24BD': 'H',
      '\uFF28': 'H',
      '\u0124': 'H',
      '\u1E22': 'H',
      '\u1E26': 'H',
      '\u021E': 'H',
      '\u1E24': 'H',
      '\u1E28': 'H',
      '\u1E2A': 'H',
      '\u0126': 'H',
      '\u2C67': 'H',
      '\u2C75': 'H',
      '\uA78D': 'H',
      '\u24BE': 'I',
      '\uFF29': 'I',
      '\u00CC': 'I',
      '\u00CD': 'I',
      '\u00CE': 'I',
      '\u0128': 'I',
      '\u012A': 'I',
      '\u012C': 'I',
      '\u0130': 'I',
      '\u00CF': 'I',
      '\u1E2E': 'I',
      '\u1EC8': 'I',
      '\u01CF': 'I',
      '\u0208': 'I',
      '\u020A': 'I',
      '\u1ECA': 'I',
      '\u012E': 'I',
      '\u1E2C': 'I',
      '\u0197': 'I',
      '\u24BF': 'J',
      '\uFF2A': 'J',
      '\u0134': 'J',
      '\u0248': 'J',
      '\u24C0': 'K',
      '\uFF2B': 'K',
      '\u1E30': 'K',
      '\u01E8': 'K',
      '\u1E32': 'K',
      '\u0136': 'K',
      '\u1E34': 'K',
      '\u0198': 'K',
      '\u2C69': 'K',
      '\uA740': 'K',
      '\uA742': 'K',
      '\uA744': 'K',
      '\uA7A2': 'K',
      '\u24C1': 'L',
      '\uFF2C': 'L',
      '\u013F': 'L',
      '\u0139': 'L',
      '\u013D': 'L',
      '\u1E36': 'L',
      '\u1E38': 'L',
      '\u013B': 'L',
      '\u1E3C': 'L',
      '\u1E3A': 'L',
      '\u0141': 'L',
      '\u023D': 'L',
      '\u2C62': 'L',
      '\u2C60': 'L',
      '\uA748': 'L',
      '\uA746': 'L',
      '\uA780': 'L',
      '\u01C7': 'LJ',
      '\u01C8': 'Lj',
      '\u24C2': 'M',
      '\uFF2D': 'M',
      '\u1E3E': 'M',
      '\u1E40': 'M',
      '\u1E42': 'M',
      '\u2C6E': 'M',
      '\u019C': 'M',
      '\u24C3': 'N',
      '\uFF2E': 'N',
      '\u01F8': 'N',
      '\u0143': 'N',
      '\u00D1': 'N',
      '\u1E44': 'N',
      '\u0147': 'N',
      '\u1E46': 'N',
      '\u0145': 'N',
      '\u1E4A': 'N',
      '\u1E48': 'N',
      '\u0220': 'N',
      '\u019D': 'N',
      '\uA790': 'N',
      '\uA7A4': 'N',
      '\u01CA': 'NJ',
      '\u01CB': 'Nj',
      '\u24C4': 'O',
      '\uFF2F': 'O',
      '\u00D2': 'O',
      '\u00D3': 'O',
      '\u00D4': 'O',
      '\u1ED2': 'O',
      '\u1ED0': 'O',
      '\u1ED6': 'O',
      '\u1ED4': 'O',
      '\u00D5': 'O',
      '\u1E4C': 'O',
      '\u022C': 'O',
      '\u1E4E': 'O',
      '\u014C': 'O',
      '\u1E50': 'O',
      '\u1E52': 'O',
      '\u014E': 'O',
      '\u022E': 'O',
      '\u0230': 'O',
      '\u00D6': 'O',
      '\u022A': 'O',
      '\u1ECE': 'O',
      '\u0150': 'O',
      '\u01D1': 'O',
      '\u020C': 'O',
      '\u020E': 'O',
      '\u01A0': 'O',
      '\u1EDC': 'O',
      '\u1EDA': 'O',
      '\u1EE0': 'O',
      '\u1EDE': 'O',
      '\u1EE2': 'O',
      '\u1ECC': 'O',
      '\u1ED8': 'O',
      '\u01EA': 'O',
      '\u01EC': 'O',
      '\u00D8': 'O',
      '\u01FE': 'O',
      '\u0186': 'O',
      '\u019F': 'O',
      '\uA74A': 'O',
      '\uA74C': 'O',
      '\u01A2': 'OI',
      '\uA74E': 'OO',
      '\u0222': 'OU',
      '\u24C5': 'P',
      '\uFF30': 'P',
      '\u1E54': 'P',
      '\u1E56': 'P',
      '\u01A4': 'P',
      '\u2C63': 'P',
      '\uA750': 'P',
      '\uA752': 'P',
      '\uA754': 'P',
      '\u24C6': 'Q',
      '\uFF31': 'Q',
      '\uA756': 'Q',
      '\uA758': 'Q',
      '\u024A': 'Q',
      '\u24C7': 'R',
      '\uFF32': 'R',
      '\u0154': 'R',
      '\u1E58': 'R',
      '\u0158': 'R',
      '\u0210': 'R',
      '\u0212': 'R',
      '\u1E5A': 'R',
      '\u1E5C': 'R',
      '\u0156': 'R',
      '\u1E5E': 'R',
      '\u024C': 'R',
      '\u2C64': 'R',
      '\uA75A': 'R',
      '\uA7A6': 'R',
      '\uA782': 'R',
      '\u24C8': 'S',
      '\uFF33': 'S',
      '\u1E9E': 'S',
      '\u015A': 'S',
      '\u1E64': 'S',
      '\u015C': 'S',
      '\u1E60': 'S',
      '\u0160': 'S',
      '\u1E66': 'S',
      '\u1E62': 'S',
      '\u1E68': 'S',
      '\u0218': 'S',
      '\u015E': 'S',
      '\u2C7E': 'S',
      '\uA7A8': 'S',
      '\uA784': 'S',
      '\u24C9': 'T',
      '\uFF34': 'T',
      '\u1E6A': 'T',
      '\u0164': 'T',
      '\u1E6C': 'T',
      '\u021A': 'T',
      '\u0162': 'T',
      '\u1E70': 'T',
      '\u1E6E': 'T',
      '\u0166': 'T',
      '\u01AC': 'T',
      '\u01AE': 'T',
      '\u023E': 'T',
      '\uA786': 'T',
      '\uA728': 'TZ',
      '\u24CA': 'U',
      '\uFF35': 'U',
      '\u00D9': 'U',
      '\u00DA': 'U',
      '\u00DB': 'U',
      '\u0168': 'U',
      '\u1E78': 'U',
      '\u016A': 'U',
      '\u1E7A': 'U',
      '\u016C': 'U',
      '\u00DC': 'U',
      '\u01DB': 'U',
      '\u01D7': 'U',
      '\u01D5': 'U',
      '\u01D9': 'U',
      '\u1EE6': 'U',
      '\u016E': 'U',
      '\u0170': 'U',
      '\u01D3': 'U',
      '\u0214': 'U',
      '\u0216': 'U',
      '\u01AF': 'U',
      '\u1EEA': 'U',
      '\u1EE8': 'U',
      '\u1EEE': 'U',
      '\u1EEC': 'U',
      '\u1EF0': 'U',
      '\u1EE4': 'U',
      '\u1E72': 'U',
      '\u0172': 'U',
      '\u1E76': 'U',
      '\u1E74': 'U',
      '\u0244': 'U',
      '\u24CB': 'V',
      '\uFF36': 'V',
      '\u1E7C': 'V',
      '\u1E7E': 'V',
      '\u01B2': 'V',
      '\uA75E': 'V',
      '\u0245': 'V',
      '\uA760': 'VY',
      '\u24CC': 'W',
      '\uFF37': 'W',
      '\u1E80': 'W',
      '\u1E82': 'W',
      '\u0174': 'W',
      '\u1E86': 'W',
      '\u1E84': 'W',
      '\u1E88': 'W',
      '\u2C72': 'W',
      '\u24CD': 'X',
      '\uFF38': 'X',
      '\u1E8A': 'X',
      '\u1E8C': 'X',
      '\u24CE': 'Y',
      '\uFF39': 'Y',
      '\u1EF2': 'Y',
      '\u00DD': 'Y',
      '\u0176': 'Y',
      '\u1EF8': 'Y',
      '\u0232': 'Y',
      '\u1E8E': 'Y',
      '\u0178': 'Y',
      '\u1EF6': 'Y',
      '\u1EF4': 'Y',
      '\u01B3': 'Y',
      '\u024E': 'Y',
      '\u1EFE': 'Y',
      '\u24CF': 'Z',
      '\uFF3A': 'Z',
      '\u0179': 'Z',
      '\u1E90': 'Z',
      '\u017B': 'Z',
      '\u017D': 'Z',
      '\u1E92': 'Z',
      '\u1E94': 'Z',
      '\u01B5': 'Z',
      '\u0224': 'Z',
      '\u2C7F': 'Z',
      '\u2C6B': 'Z',
      '\uA762': 'Z',
      '\u24D0': 'a',
      '\uFF41': 'a',
      '\u1E9A': 'a',
      '\u00E0': 'a',
      '\u00E1': 'a',
      '\u00E2': 'a',
      '\u1EA7': 'a',
      '\u1EA5': 'a',
      '\u1EAB': 'a',
      '\u1EA9': 'a',
      '\u00E3': 'a',
      '\u0101': 'a',
      '\u0103': 'a',
      '\u1EB1': 'a',
      '\u1EAF': 'a',
      '\u1EB5': 'a',
      '\u1EB3': 'a',
      '\u0227': 'a',
      '\u01E1': 'a',
      '\u00E4': 'a',
      '\u01DF': 'a',
      '\u1EA3': 'a',
      '\u00E5': 'a',
      '\u01FB': 'a',
      '\u01CE': 'a',
      '\u0201': 'a',
      '\u0203': 'a',
      '\u1EA1': 'a',
      '\u1EAD': 'a',
      '\u1EB7': 'a',
      '\u1E01': 'a',
      '\u0105': 'a',
      '\u2C65': 'a',
      '\u0250': 'a',
      '\uA733': 'aa',
      '\u00E6': 'ae',
      '\u01FD': 'ae',
      '\u01E3': 'ae',
      '\uA735': 'ao',
      '\uA737': 'au',
      '\uA739': 'av',
      '\uA73B': 'av',
      '\uA73D': 'ay',
      '\u24D1': 'b',
      '\uFF42': 'b',
      '\u1E03': 'b',
      '\u1E05': 'b',
      '\u1E07': 'b',
      '\u0180': 'b',
      '\u0183': 'b',
      '\u0253': 'b',
      '\u24D2': 'c',
      '\uFF43': 'c',
      '\u0107': 'c',
      '\u0109': 'c',
      '\u010B': 'c',
      '\u010D': 'c',
      '\u00E7': 'c',
      '\u1E09': 'c',
      '\u0188': 'c',
      '\u023C': 'c',
      '\uA73F': 'c',
      '\u2184': 'c',
      '\u24D3': 'd',
      '\uFF44': 'd',
      '\u1E0B': 'd',
      '\u010F': 'd',
      '\u1E0D': 'd',
      '\u1E11': 'd',
      '\u1E13': 'd',
      '\u1E0F': 'd',
      '\u0111': 'd',
      '\u018C': 'd',
      '\u0256': 'd',
      '\u0257': 'd',
      '\uA77A': 'd',
      '\u01F3': 'dz',
      '\u01C6': 'dz',
      '\u24D4': 'e',
      '\uFF45': 'e',
      '\u00E8': 'e',
      '\u00E9': 'e',
      '\u00EA': 'e',
      '\u1EC1': 'e',
      '\u1EBF': 'e',
      '\u1EC5': 'e',
      '\u1EC3': 'e',
      '\u1EBD': 'e',
      '\u0113': 'e',
      '\u1E15': 'e',
      '\u1E17': 'e',
      '\u0115': 'e',
      '\u0117': 'e',
      '\u00EB': 'e',
      '\u1EBB': 'e',
      '\u011B': 'e',
      '\u0205': 'e',
      '\u0207': 'e',
      '\u1EB9': 'e',
      '\u1EC7': 'e',
      '\u0229': 'e',
      '\u1E1D': 'e',
      '\u0119': 'e',
      '\u1E19': 'e',
      '\u1E1B': 'e',
      '\u0247': 'e',
      '\u025B': 'e',
      '\u01DD': 'e',
      '\u24D5': 'f',
      '\uFF46': 'f',
      '\u1E1F': 'f',
      '\u0192': 'f',
      '\uA77C': 'f',
      '\u24D6': 'g',
      '\uFF47': 'g',
      '\u01F5': 'g',
      '\u011D': 'g',
      '\u1E21': 'g',
      '\u011F': 'g',
      '\u0121': 'g',
      '\u01E7': 'g',
      '\u0123': 'g',
      '\u01E5': 'g',
      '\u0260': 'g',
      '\uA7A1': 'g',
      '\u1D79': 'g',
      '\uA77F': 'g',
      '\u24D7': 'h',
      '\uFF48': 'h',
      '\u0125': 'h',
      '\u1E23': 'h',
      '\u1E27': 'h',
      '\u021F': 'h',
      '\u1E25': 'h',
      '\u1E29': 'h',
      '\u1E2B': 'h',
      '\u1E96': 'h',
      '\u0127': 'h',
      '\u2C68': 'h',
      '\u2C76': 'h',
      '\u0265': 'h',
      '\u0195': 'hv',
      '\u24D8': 'i',
      '\uFF49': 'i',
      '\u00EC': 'i',
      '\u00ED': 'i',
      '\u00EE': 'i',
      '\u0129': 'i',
      '\u012B': 'i',
      '\u012D': 'i',
      '\u00EF': 'i',
      '\u1E2F': 'i',
      '\u1EC9': 'i',
      '\u01D0': 'i',
      '\u0209': 'i',
      '\u020B': 'i',
      '\u1ECB': 'i',
      '\u012F': 'i',
      '\u1E2D': 'i',
      '\u0268': 'i',
      '\u0131': 'i',
      '\u24D9': 'j',
      '\uFF4A': 'j',
      '\u0135': 'j',
      '\u01F0': 'j',
      '\u0249': 'j',
      '\u24DA': 'k',
      '\uFF4B': 'k',
      '\u1E31': 'k',
      '\u01E9': 'k',
      '\u1E33': 'k',
      '\u0137': 'k',
      '\u1E35': 'k',
      '\u0199': 'k',
      '\u2C6A': 'k',
      '\uA741': 'k',
      '\uA743': 'k',
      '\uA745': 'k',
      '\uA7A3': 'k',
      '\u24DB': 'l',
      '\uFF4C': 'l',
      '\u0140': 'l',
      '\u013A': 'l',
      '\u013E': 'l',
      '\u1E37': 'l',
      '\u1E39': 'l',
      '\u013C': 'l',
      '\u1E3D': 'l',
      '\u1E3B': 'l',
      '\u017F': 'l',
      '\u0142': 'l',
      '\u019A': 'l',
      '\u026B': 'l',
      '\u2C61': 'l',
      '\uA749': 'l',
      '\uA781': 'l',
      '\uA747': 'l',
      '\u01C9': 'lj',
      '\u24DC': 'm',
      '\uFF4D': 'm',
      '\u1E3F': 'm',
      '\u1E41': 'm',
      '\u1E43': 'm',
      '\u0271': 'm',
      '\u026F': 'm',
      '\u24DD': 'n',
      '\uFF4E': 'n',
      '\u01F9': 'n',
      '\u0144': 'n',
      '\u00F1': 'n',
      '\u1E45': 'n',
      '\u0148': 'n',
      '\u1E47': 'n',
      '\u0146': 'n',
      '\u1E4B': 'n',
      '\u1E49': 'n',
      '\u019E': 'n',
      '\u0272': 'n',
      '\u0149': 'n',
      '\uA791': 'n',
      '\uA7A5': 'n',
      '\u01CC': 'nj',
      '\u24DE': 'o',
      '\uFF4F': 'o',
      '\u00F2': 'o',
      '\u00F3': 'o',
      '\u00F4': 'o',
      '\u1ED3': 'o',
      '\u1ED1': 'o',
      '\u1ED7': 'o',
      '\u1ED5': 'o',
      '\u00F5': 'o',
      '\u1E4D': 'o',
      '\u022D': 'o',
      '\u1E4F': 'o',
      '\u014D': 'o',
      '\u1E51': 'o',
      '\u1E53': 'o',
      '\u014F': 'o',
      '\u022F': 'o',
      '\u0231': 'o',
      '\u00F6': 'o',
      '\u022B': 'o',
      '\u1ECF': 'o',
      '\u0151': 'o',
      '\u01D2': 'o',
      '\u020D': 'o',
      '\u020F': 'o',
      '\u01A1': 'o',
      '\u1EDD': 'o',
      '\u1EDB': 'o',
      '\u1EE1': 'o',
      '\u1EDF': 'o',
      '\u1EE3': 'o',
      '\u1ECD': 'o',
      '\u1ED9': 'o',
      '\u01EB': 'o',
      '\u01ED': 'o',
      '\u00F8': 'o',
      '\u01FF': 'o',
      '\u0254': 'o',
      '\uA74B': 'o',
      '\uA74D': 'o',
      '\u0275': 'o',
      '\u01A3': 'oi',
      '\u0223': 'ou',
      '\uA74F': 'oo',
      '\u24DF': 'p',
      '\uFF50': 'p',
      '\u1E55': 'p',
      '\u1E57': 'p',
      '\u01A5': 'p',
      '\u1D7D': 'p',
      '\uA751': 'p',
      '\uA753': 'p',
      '\uA755': 'p',
      '\u24E0': 'q',
      '\uFF51': 'q',
      '\u024B': 'q',
      '\uA757': 'q',
      '\uA759': 'q',
      '\u24E1': 'r',
      '\uFF52': 'r',
      '\u0155': 'r',
      '\u1E59': 'r',
      '\u0159': 'r',
      '\u0211': 'r',
      '\u0213': 'r',
      '\u1E5B': 'r',
      '\u1E5D': 'r',
      '\u0157': 'r',
      '\u1E5F': 'r',
      '\u024D': 'r',
      '\u027D': 'r',
      '\uA75B': 'r',
      '\uA7A7': 'r',
      '\uA783': 'r',
      '\u24E2': 's',
      '\uFF53': 's',
      '\u00DF': 's',
      '\u015B': 's',
      '\u1E65': 's',
      '\u015D': 's',
      '\u1E61': 's',
      '\u0161': 's',
      '\u1E67': 's',
      '\u1E63': 's',
      '\u1E69': 's',
      '\u0219': 's',
      '\u015F': 's',
      '\u023F': 's',
      '\uA7A9': 's',
      '\uA785': 's',
      '\u1E9B': 's',
      '\u24E3': 't',
      '\uFF54': 't',
      '\u1E6B': 't',
      '\u1E97': 't',
      '\u0165': 't',
      '\u1E6D': 't',
      '\u021B': 't',
      '\u0163': 't',
      '\u1E71': 't',
      '\u1E6F': 't',
      '\u0167': 't',
      '\u01AD': 't',
      '\u0288': 't',
      '\u2C66': 't',
      '\uA787': 't',
      '\uA729': 'tz',
      '\u24E4': 'u',
      '\uFF55': 'u',
      '\u00F9': 'u',
      '\u00FA': 'u',
      '\u00FB': 'u',
      '\u0169': 'u',
      '\u1E79': 'u',
      '\u016B': 'u',
      '\u1E7B': 'u',
      '\u016D': 'u',
      '\u00FC': 'u',
      '\u01DC': 'u',
      '\u01D8': 'u',
      '\u01D6': 'u',
      '\u01DA': 'u',
      '\u1EE7': 'u',
      '\u016F': 'u',
      '\u0171': 'u',
      '\u01D4': 'u',
      '\u0215': 'u',
      '\u0217': 'u',
      '\u01B0': 'u',
      '\u1EEB': 'u',
      '\u1EE9': 'u',
      '\u1EEF': 'u',
      '\u1EED': 'u',
      '\u1EF1': 'u',
      '\u1EE5': 'u',
      '\u1E73': 'u',
      '\u0173': 'u',
      '\u1E77': 'u',
      '\u1E75': 'u',
      '\u0289': 'u',
      '\u24E5': 'v',
      '\uFF56': 'v',
      '\u1E7D': 'v',
      '\u1E7F': 'v',
      '\u028B': 'v',
      '\uA75F': 'v',
      '\u028C': 'v',
      '\uA761': 'vy',
      '\u24E6': 'w',
      '\uFF57': 'w',
      '\u1E81': 'w',
      '\u1E83': 'w',
      '\u0175': 'w',
      '\u1E87': 'w',
      '\u1E85': 'w',
      '\u1E98': 'w',
      '\u1E89': 'w',
      '\u2C73': 'w',
      '\u24E7': 'x',
      '\uFF58': 'x',
      '\u1E8B': 'x',
      '\u1E8D': 'x',
      '\u24E8': 'y',
      '\uFF59': 'y',
      '\u1EF3': 'y',
      '\u00FD': 'y',
      '\u0177': 'y',
      '\u1EF9': 'y',
      '\u0233': 'y',
      '\u1E8F': 'y',
      '\u00FF': 'y',
      '\u1EF7': 'y',
      '\u1E99': 'y',
      '\u1EF5': 'y',
      '\u01B4': 'y',
      '\u024F': 'y',
      '\u1EFF': 'y',
      '\u24E9': 'z',
      '\uFF5A': 'z',
      '\u017A': 'z',
      '\u1E91': 'z',
      '\u017C': 'z',
      '\u017E': 'z',
      '\u1E93': 'z',
      '\u1E95': 'z',
      '\u01B6': 'z',
      '\u0225': 'z',
      '\u0240': 'z',
      '\u2C6C': 'z',
      '\uA763': 'z',
      '\u0386': '\u0391',
      '\u0388': '\u0395',
      '\u0389': '\u0397',
      '\u038A': '\u0399',
      '\u03AA': '\u0399',
      '\u038C': '\u039F',
      '\u038E': '\u03A5',
      '\u03AB': '\u03A5',
      '\u038F': '\u03A9',
      '\u03AC': '\u03B1',
      '\u03AD': '\u03B5',
      '\u03AE': '\u03B7',
      '\u03AF': '\u03B9',
      '\u03CA': '\u03B9',
      '\u0390': '\u03B9',
      '\u03CC': '\u03BF',
      '\u03CD': '\u03C5',
      '\u03CB': '\u03C5',
      '\u03B0': '\u03C5',
      '\u03C9': '\u03C9',
      '\u03C2': '\u03C3'
    };
  
    return diacritics;
  });
  
  S2.define('select2/data/base',[
    '../utils'
  ], function (Utils) {
    function BaseAdapter ($element, options) {
      BaseAdapter.__super__.constructor.call(this);
    }
  
    Utils.Extend(BaseAdapter, Utils.Observable);
  
    BaseAdapter.prototype.current = function (callback) {
      throw new Error('The `current` method must be defined in child classes.');
    };
  
    BaseAdapter.prototype.query = function (params, callback) {
      throw new Error('The `query` method must be defined in child classes.');
    };
  
    BaseAdapter.prototype.bind = function (container, $container) {
      // Can be implemented in subclasses
    };
  
    BaseAdapter.prototype.destroy = function () {
      // Can be implemented in subclasses
    };
  
    BaseAdapter.prototype.generateResultId = function (container, data) {
      var id = container.id + '-result-';
  
      id += Utils.generateChars(4);
  
      if (data.id != null) {
        id += '-' + data.id.toString();
      } else {
        id += '-' + Utils.generateChars(4);
      }
      return id;
    };
  
    return BaseAdapter;
  });
  
  S2.define('select2/data/select',[
    './base',
    '../utils',
    'jquery'
  ], function (BaseAdapter, Utils, $) {
    function SelectAdapter ($element, options) {
      this.$element = $element;
      this.options = options;
  
      SelectAdapter.__super__.constructor.call(this);
    }
  
    Utils.Extend(SelectAdapter, BaseAdapter);
  
    SelectAdapter.prototype.current = function (callback) {
      var data = [];
      var self = this;
  
      this.$element.find(':selected').each(function () {
        var $option = $(this);
  
        var option = self.item($option);
  
        data.push(option);
      });
  
      callback(data);
    };
  
    SelectAdapter.prototype.select = function (data) {
      var self = this;
  
      data.selected = true;
  
      // If data.element is a DOM node, use it instead
      if ($(data.element).is('option')) {
        data.element.selected = true;
  
        this.$element.trigger('change');
  
        return;
      }
  
      if (this.$element.prop('multiple')) {
        this.current(function (currentData) {
          var val = [];
  
          data = [data];
          data.push.apply(data, currentData);
  
          for (var d = 0; d < data.length; d++) {
            var id = data[d].id;
  
            if ($.inArray(id, val) === -1) {
              val.push(id);
            }
          }
  
          self.$element.val(val);
          self.$element.trigger('change');
        });
      } else {
        var val = data.id;
  
        this.$element.val(val);
        this.$element.trigger('change');
      }
    };
  
    SelectAdapter.prototype.unselect = function (data) {
      var self = this;
  
      if (!this.$element.prop('multiple')) {
        return;
      }
  
      data.selected = false;
  
      if ($(data.element).is('option')) {
        data.element.selected = false;
  
        this.$element.trigger('change');
  
        return;
      }
  
      this.current(function (currentData) {
        var val = [];
  
        for (var d = 0; d < currentData.length; d++) {
          var id = currentData[d].id;
  
          if (id !== data.id && $.inArray(id, val) === -1) {
            val.push(id);
          }
        }
  
        self.$element.val(val);
  
        self.$element.trigger('change');
      });
    };
  
    SelectAdapter.prototype.bind = function (container, $container) {
      var self = this;
  
      this.container = container;
  
      container.on('select', function (params) {
        self.select(params.data);
      });
  
      container.on('unselect', function (params) {
        self.unselect(params.data);
      });
    };
  
    SelectAdapter.prototype.destroy = function () {
      // Remove anything added to child elements
      this.$element.find('*').each(function () {
        // Remove any custom data set by Select2
        $.removeData(this, 'data');
      });
    };
  
    SelectAdapter.prototype.query = function (params, callback) {
      var data = [];
      var self = this;
  
      var $options = this.$element.children();
  
      $options.each(function () {
        var $option = $(this);
  
        if (!$option.is('option') && !$option.is('optgroup')) {
          return;
        }
  
        var option = self.item($option);
  
        var matches = self.matches(params, option);
  
        if (matches !== null) {
          data.push(matches);
        }
      });
  
      callback({
        results: data
      });
    };
  
    SelectAdapter.prototype.addOptions = function ($options) {
      Utils.appendMany(this.$element, $options);
    };
  
    SelectAdapter.prototype.option = function (data) {
      var option;
  
      if (data.children) {
        option = document.createElement('optgroup');
        option.label = data.text;
      } else {
        option = document.createElement('option');
  
        if (option.textContent !== undefined) {
          option.textContent = data.text;
        } else {
          option.innerText = data.text;
        }
      }
  
      if (data.id) {
        option.value = data.id;
      }
  
      if (data.disabled) {
        option.disabled = true;
      }
  
      if (data.selected) {
        option.selected = true;
      }
  
      if (data.title) {
        option.title = data.title;
      }
  
      var $option = $(option);
  
      var normalizedData = this._normalizeItem(data);
      normalizedData.element = option;
  
      // Override the option's data with the combined data
      $.data(option, 'data', normalizedData);
  
      return $option;
    };
  
    SelectAdapter.prototype.item = function ($option) {
      var data = {};
  
      data = $.data($option[0], 'data');
  
      if (data != null) {
        return data;
      }
  
      if ($option.is('option')) {
        data = {
          id: $option.val(),
          text: $option.text(),
          disabled: $option.prop('disabled'),
          selected: $option.prop('selected'),
          title: $option.prop('title')
        };
      } else if ($option.is('optgroup')) {
        data = {
          text: $option.prop('label'),
          children: [],
          title: $option.prop('title')
        };
  
        var $children = $option.children('option');
        var children = [];
  
        for (var c = 0; c < $children.length; c++) {
          var $child = $($children[c]);
  
          var child = this.item($child);
  
          children.push(child);
        }
  
        data.children = children;
      }
  
      data = this._normalizeItem(data);
      data.element = $option[0];
  
      $.data($option[0], 'data', data);
  
      return data;
    };
  
    SelectAdapter.prototype._normalizeItem = function (item) {
      if (!$.isPlainObject(item)) {
        item = {
          id: item,
          text: item
        };
      }
  
      item = $.extend({}, {
        text: ''
      }, item);
  
      var defaults = {
        selected: false,
        disabled: false
      };
  
      if (item.id != null) {
        item.id = item.id.toString();
      }
  
      if (item.text != null) {
        item.text = item.text.toString();
      }
  
      if (item._resultId == null && item.id && this.container != null) {
        item._resultId = this.generateResultId(this.container, item);
      }
  
      return $.extend({}, defaults, item);
    };
  
    SelectAdapter.prototype.matches = function (params, data) {
      var matcher = this.options.get('matcher');
  
      return matcher(params, data);
    };
  
    return SelectAdapter;
  });
  
  S2.define('select2/data/array',[
    './select',
    '../utils',
    'jquery'
  ], function (SelectAdapter, Utils, $) {
    function ArrayAdapter ($element, options) {
      var data = options.get('data') || [];
  
      ArrayAdapter.__super__.constructor.call(this, $element, options);
  
      this.addOptions(this.convertToOptions(data));
    }
  
    Utils.Extend(ArrayAdapter, SelectAdapter);
  
    ArrayAdapter.prototype.select = function (data) {
      var $option = this.$element.find('option').filter(function (i, elm) {
        return elm.value == data.id.toString();
      });
  
      if ($option.length === 0) {
        $option = this.option(data);
  
        this.addOptions($option);
      }
  
      ArrayAdapter.__super__.select.call(this, data);
    };
  
    ArrayAdapter.prototype.convertToOptions = function (data) {
      var self = this;
  
      var $existing = this.$element.find('option');
      var existingIds = $existing.map(function () {
        return self.item($(this)).id;
      }).get();
  
      var $options = [];
  
      // Filter out all items except for the one passed in the argument
      function onlyItem (item) {
        return function () {
          return $(this).val() == item.id;
        };
      }
  
      for (var d = 0; d < data.length; d++) {
        var item = this._normalizeItem(data[d]);
  
        // Skip items which were pre-loaded, only merge the data
        if ($.inArray(item.id, existingIds) >= 0) {
          var $existingOption = $existing.filter(onlyItem(item));
  
          var existingData = this.item($existingOption);
          var newData = $.extend(true, {}, item, existingData);
  
          var $newOption = this.option(newData);
  
          $existingOption.replaceWith($newOption);
  
          continue;
        }
  
        var $option = this.option(item);
  
        if (item.children) {
          var $children = this.convertToOptions(item.children);
  
          Utils.appendMany($option, $children);
        }
  
        $options.push($option);
      }
  
      return $options;
    };
  
    return ArrayAdapter;
  });
  
  S2.define('select2/data/ajax',[
    './array',
    '../utils',
    'jquery'
  ], function (ArrayAdapter, Utils, $) {
    function AjaxAdapter ($element, options) {
      this.ajaxOptions = this._applyDefaults(options.get('ajax'));
  
      if (this.ajaxOptions.processResults != null) {
        this.processResults = this.ajaxOptions.processResults;
      }
  
      AjaxAdapter.__super__.constructor.call(this, $element, options);
    }
  
    Utils.Extend(AjaxAdapter, ArrayAdapter);
  
    AjaxAdapter.prototype._applyDefaults = function (options) {
      var defaults = {
        data: function (params) {
          return $.extend({}, params, {
            q: params.term
          });
        },
        transport: function (params, success, failure) {
          var $request = $.ajax(params);
  
          $request.then(success);
          $request.fail(failure);
  
          return $request;
        }
      };
  
      return $.extend({}, defaults, options, true);
    };
  
    AjaxAdapter.prototype.processResults = function (results) {
      return results;
    };
  
    AjaxAdapter.prototype.query = function (params, callback) {
      var matches = [];
      var self = this;
  
      if (this._request != null) {
        // JSONP requests cannot always be aborted
        if ($.isFunction(this._request.abort)) {
          this._request.abort();
        }
  
        this._request = null;
      }
  
      var options = $.extend({
        type: 'GET'
      }, this.ajaxOptions);
  
      if (typeof options.url === 'function') {
        options.url = options.url.call(this.$element, params);
      }
  
      if (typeof options.data === 'function') {
        options.data = options.data.call(this.$element, params);
      }
  
      function request () {
        var $request = options.transport(options, function (data) {
          var results = self.processResults(data, params);
  
          if (self.options.get('debug') && window.console && console.error) {
            // Check to make sure that the response included a `results` key.
            if (!results || !results.results || !$.isArray(results.results)) {
              console.error(
                'Select2: The AJAX results did not return an array in the ' +
                '`results` key of the response.'
              );
            }
          }
  
          callback(results);
        }, function () {
          // Attempt to detect if a request was aborted
          // Only works if the transport exposes a status property
          if ($request.status && $request.status === '0') {
            return;
          }
  
          self.trigger('results:message', {
            message: 'errorLoading'
          });
        });
  
        self._request = $request;
      }
  
      if (this.ajaxOptions.delay && params.term != null) {
        if (this._queryTimeout) {
          window.clearTimeout(this._queryTimeout);
        }
  
        this._queryTimeout = window.setTimeout(request, this.ajaxOptions.delay);
      } else {
        request();
      }
    };
  
    return AjaxAdapter;
  });
  
  S2.define('select2/data/tags',[
    'jquery'
  ], function ($) {
    function Tags (decorated, $element, options) {
      var tags = options.get('tags');
  
      var createTag = options.get('createTag');
  
      if (createTag !== undefined) {
        this.createTag = createTag;
      }
  
      var insertTag = options.get('insertTag');
  
      if (insertTag !== undefined) {
          this.insertTag = insertTag;
      }
  
      decorated.call(this, $element, options);
  
      if ($.isArray(tags)) {
        for (var t = 0; t < tags.length; t++) {
          var tag = tags[t];
          var item = this._normalizeItem(tag);
  
          var $option = this.option(item);
  
          this.$element.append($option);
        }
      }
    }
  
    Tags.prototype.query = function (decorated, params, callback) {
      var self = this;
  
      this._removeOldTags();
  
      if (params.term == null || params.page != null) {
        decorated.call(this, params, callback);
        return;
      }
  
      function wrapper (obj, child) {
        var data = obj.results;
  
        for (var i = 0; i < data.length; i++) {
          var option = data[i];
  
          var checkChildren = (
            option.children != null &&
            !wrapper({
              results: option.children
            }, true)
          );
  
          var checkText = option.text === params.term;
  
          if (checkText || checkChildren) {
            if (child) {
              return false;
            }
  
            obj.data = data;
            callback(obj);
  
            return;
          }
        }
  
        if (child) {
          return true;
        }
  
        var tag = self.createTag(params);
  
        if (tag != null) {
          var $option = self.option(tag);
          $option.attr('data-select2-tag', true);
  
          self.addOptions([$option]);
  
          self.insertTag(data, tag);
        }
  
        obj.results = data;
  
        callback(obj);
      }
  
      decorated.call(this, params, wrapper);
    };
  
    Tags.prototype.createTag = function (decorated, params) {
      var term = $.trim(params.term);
  
      if (term === '') {
        return null;
      }
  
      return {
        id: term,
        text: term
      };
    };
  
    Tags.prototype.insertTag = function (_, data, tag) {
      data.unshift(tag);
    };
  
    Tags.prototype._removeOldTags = function (_) {
      var tag = this._lastTag;
  
      var $options = this.$element.find('option[data-select2-tag]');
  
      $options.each(function () {
        if (this.selected) {
          return;
        }
  
        $(this).remove();
      });
    };
  
    return Tags;
  });
  
  S2.define('select2/data/tokenizer',[
    'jquery'
  ], function ($) {
    function Tokenizer (decorated, $element, options) {
      var tokenizer = options.get('tokenizer');
  
      if (tokenizer !== undefined) {
        this.tokenizer = tokenizer;
      }
  
      decorated.call(this, $element, options);
    }
  
    Tokenizer.prototype.bind = function (decorated, container, $container) {
      decorated.call(this, container, $container);
  
      this.$search =  container.dropdown.$search || container.selection.$search ||
        $container.find('.select2-search__field');
    };
  
    Tokenizer.prototype.query = function (decorated, params, callback) {
      var self = this;
  
      function createAndSelect (data) {
        // Normalize the data object so we can use it for checks
        var item = self._normalizeItem(data);
  
        // Check if the data object already exists as a tag
        // Select it if it doesn't
        var $existingOptions = self.$element.find('option').filter(function () {
          return $(this).val() === item.id;
        });
  
        // If an existing option wasn't found for it, create the option
        if (!$existingOptions.length) {
          var $option = self.option(item);
          $option.attr('data-select2-tag', true);
  
          self._removeOldTags();
          self.addOptions([$option]);
        }
  
        // Select the item, now that we know there is an option for it
        select(item);
      }
  
      function select (data) {
        self.trigger('select', {
          data: data
        });
      }
  
      params.term = params.term || '';
  
      var tokenData = this.tokenizer(params, this.options, createAndSelect);
  
      if (tokenData.term !== params.term) {
        // Replace the search term if we have the search box
        if (this.$search.length) {
          this.$search.val(tokenData.term);
          this.$search.focus();
        }
  
        params.term = tokenData.term;
      }
  
      decorated.call(this, params, callback);
    };
  
    Tokenizer.prototype.tokenizer = function (_, params, options, callback) {
      var separators = options.get('tokenSeparators') || [];
      var term = params.term;
      var i = 0;
  
      var createTag = this.createTag || function (params) {
        return {
          id: params.term,
          text: params.term
        };
      };
  
      while (i < term.length) {
        var termChar = term[i];
  
        if ($.inArray(termChar, separators) === -1) {
          i++;
  
          continue;
        }
  
        var part = term.substr(0, i);
        var partParams = $.extend({}, params, {
          term: part
        });
  
        var data = createTag(partParams);
  
        if (data == null) {
          i++;
          continue;
        }
  
        callback(data);
  
        // Reset the term to not include the tokenized portion
        term = term.substr(i + 1) || '';
        i = 0;
      }
  
      return {
        term: term
      };
    };
  
    return Tokenizer;
  });
  
  S2.define('select2/data/minimumInputLength',[
  
  ], function () {
    function MinimumInputLength (decorated, $e, options) {
      this.minimumInputLength = options.get('minimumInputLength');
  
      decorated.call(this, $e, options);
    }
  
    MinimumInputLength.prototype.query = function (decorated, params, callback) {
      params.term = params.term || '';
  
      if (params.term.length < this.minimumInputLength) {
        this.trigger('results:message', {
          message: 'inputTooShort',
          args: {
            minimum: this.minimumInputLength,
            input: params.term,
            params: params
          }
        });
  
        return;
      }
  
      decorated.call(this, params, callback);
    };
  
    return MinimumInputLength;
  });
  
  S2.define('select2/data/maximumInputLength',[
  
  ], function () {
    function MaximumInputLength (decorated, $e, options) {
      this.maximumInputLength = options.get('maximumInputLength');
  
      decorated.call(this, $e, options);
    }
  
    MaximumInputLength.prototype.query = function (decorated, params, callback) {
      params.term = params.term || '';
  
      if (this.maximumInputLength > 0 &&
          params.term.length > this.maximumInputLength) {
        this.trigger('results:message', {
          message: 'inputTooLong',
          args: {
            maximum: this.maximumInputLength,
            input: params.term,
            params: params
          }
        });
  
        return;
      }
  
      decorated.call(this, params, callback);
    };
  
    return MaximumInputLength;
  });
  
  S2.define('select2/data/maximumSelectionLength',[
  
  ], function (){
    function MaximumSelectionLength (decorated, $e, options) {
      this.maximumSelectionLength = options.get('maximumSelectionLength');
  
      decorated.call(this, $e, options);
    }
  
    MaximumSelectionLength.prototype.query =
      function (decorated, params, callback) {
        var self = this;
  
        this.current(function (currentData) {
          var count = currentData != null ? currentData.length : 0;
          if (self.maximumSelectionLength > 0 &&
            count >= self.maximumSelectionLength) {
            self.trigger('results:message', {
              message: 'maximumSelected',
              args: {
                maximum: self.maximumSelectionLength
              }
            });
            return;
          }
          decorated.call(self, params, callback);
        });
    };
  
    return MaximumSelectionLength;
  });
  
  S2.define('select2/dropdown',[
    'jquery',
    './utils'
  ], function ($, Utils) {
    function Dropdown ($element, options) {
      this.$element = $element;
      this.options = options;
  
      Dropdown.__super__.constructor.call(this);
    }
  
    Utils.Extend(Dropdown, Utils.Observable);
  
    Dropdown.prototype.render = function () {
      var $dropdown = $(
        '<span class="select2-dropdown">' +
          '<span class="select2-results"></span>' +
        '</span>'
      );
  
      $dropdown.attr('dir', this.options.get('dir'));
  
      this.$dropdown = $dropdown;
  
      return $dropdown;
    };
  
    Dropdown.prototype.bind = function () {
      // Should be implemented in subclasses
    };
  
    Dropdown.prototype.position = function ($dropdown, $container) {
      // Should be implmented in subclasses
    };
  
    Dropdown.prototype.destroy = function () {
      // Remove the dropdown from the DOM
      this.$dropdown.remove();
    };
  
    return Dropdown;
  });
  
  S2.define('select2/dropdown/search',[
    'jquery',
    '../utils'
  ], function ($, Utils) {
    function Search () { }
  
    Search.prototype.render = function (decorated) {
      var $rendered = decorated.call(this);
  
      var $search = $(
        '<span class="select2-search select2-search--dropdown">' +
          '<input class="select2-search__field" type="search" tabindex="-1"' +
          ' autocomplete="off" autocorrect="off" autocapitalize="off"' +
          ' spellcheck="false" role="textbox" />' +
        '</span>'
      );
  
      this.$searchContainer = $search;
      this.$search = $search.find('input');
  
      $rendered.prepend($search);
  
      return $rendered;
    };
  
    Search.prototype.bind = function (decorated, container, $container) {
      var self = this;
  
      decorated.call(this, container, $container);
  
      this.$search.on('keydown', function (evt) {
        self.trigger('keypress', evt);
  
        self._keyUpPrevented = evt.isDefaultPrevented();
      });
  
      // Workaround for browsers which do not support the `input` event
      // This will prevent double-triggering of events for browsers which support
      // both the `keyup` and `input` events.
      this.$search.on('input', function (evt) {
        // Unbind the duplicated `keyup` event
        $(this).off('keyup');
      });
  
      this.$search.on('keyup input', function (evt) {
        self.handleSearch(evt);
      });
  
      container.on('open', function () {
        self.$search.attr('tabindex', 0);
  
        self.$search.focus();
  
        window.setTimeout(function () {
          self.$search.focus();
        }, 0);
      });
  
      container.on('close', function () {
        self.$search.attr('tabindex', -1);
  
        self.$search.val('');
      });
  
      container.on('focus', function () {
        if (container.isOpen()) {
          self.$search.focus();
        }
      });
  
      container.on('results:all', function (params) {
        if (params.query.term == null || params.query.term === '') {
          var showSearch = self.showSearch(params);
  
          if (showSearch) {
            self.$searchContainer.removeClass('select2-search--hide');
          } else {
            self.$searchContainer.addClass('select2-search--hide');
          }
        }
      });
    };
  
    Search.prototype.handleSearch = function (evt) {
      if (!this._keyUpPrevented) {
        var input = this.$search.val();
  
        this.trigger('query', {
          term: input
        });
      }
  
      this._keyUpPrevented = false;
    };
  
    Search.prototype.showSearch = function (_, params) {
      return true;
    };
  
    return Search;
  });
  
  S2.define('select2/dropdown/hidePlaceholder',[
  
  ], function () {
    function HidePlaceholder (decorated, $element, options, dataAdapter) {
      this.placeholder = this.normalizePlaceholder(options.get('placeholder'));
  
      decorated.call(this, $element, options, dataAdapter);
    }
  
    HidePlaceholder.prototype.append = function (decorated, data) {
      data.results = this.removePlaceholder(data.results);
  
      decorated.call(this, data);
    };
  
    HidePlaceholder.prototype.normalizePlaceholder = function (_, placeholder) {
      if (typeof placeholder === 'string') {
        placeholder = {
          id: '',
          text: placeholder
        };
      }
  
      return placeholder;
    };
  
    HidePlaceholder.prototype.removePlaceholder = function (_, data) {
      var modifiedData = data.slice(0);
  
      for (var d = data.length - 1; d >= 0; d--) {
        var item = data[d];
  
        if (this.placeholder.id === item.id) {
          modifiedData.splice(d, 1);
        }
      }
  
      return modifiedData;
    };
  
    return HidePlaceholder;
  });
  
  S2.define('select2/dropdown/infiniteScroll',[
    'jquery'
  ], function ($) {
    function InfiniteScroll (decorated, $element, options, dataAdapter) {
      this.lastParams = {};
  
      decorated.call(this, $element, options, dataAdapter);
  
      this.$loadingMore = this.createLoadingMore();
      this.loading = false;
    }
  
    InfiniteScroll.prototype.append = function (decorated, data) {
      this.$loadingMore.remove();
      this.loading = false;
  
      decorated.call(this, data);
  
      if (this.showLoadingMore(data)) {
        this.$results.append(this.$loadingMore);
      }
    };
  
    InfiniteScroll.prototype.bind = function (decorated, container, $container) {
      var self = this;
  
      decorated.call(this, container, $container);
  
      container.on('query', function (params) {
        self.lastParams = params;
        self.loading = true;
      });
  
      container.on('query:append', function (params) {
        self.lastParams = params;
        self.loading = true;
      });
  
      this.$results.on('scroll', function () {
        var isLoadMoreVisible = $.contains(
          document.documentElement,
          self.$loadingMore[0]
        );
  
        if (self.loading || !isLoadMoreVisible) {
          return;
        }
  
        var currentOffset = self.$results.offset().top +
          self.$results.outerHeight(false);
        var loadingMoreOffset = self.$loadingMore.offset().top +
          self.$loadingMore.outerHeight(false);
  
        if (currentOffset + 50 >= loadingMoreOffset) {
          self.loadMore();
        }
      });
    };
  
    InfiniteScroll.prototype.loadMore = function () {
      this.loading = true;
  
      var params = $.extend({}, {page: 1}, this.lastParams);
  
      params.page++;
  
      this.trigger('query:append', params);
    };
  
    InfiniteScroll.prototype.showLoadingMore = function (_, data) {
      return data.pagination && data.pagination.more;
    };
  
    InfiniteScroll.prototype.createLoadingMore = function () {
      var $option = $(
        '<li ' +
        'class="select2-results__option select2-results__option--load-more"' +
        'role="treeitem" aria-disabled="true"></li>'
      );
  
      var message = this.options.get('translations').get('loadingMore');
  
      $option.html(message(this.lastParams));
  
      return $option;
    };
  
    return InfiniteScroll;
  });
  
  S2.define('select2/dropdown/attachBody',[
    'jquery',
    '../utils'
  ], function ($, Utils) {
    function AttachBody (decorated, $element, options) {
      this.$dropdownParent = options.get('dropdownParent') || $(document.body);
  
      decorated.call(this, $element, options);
    }
  
    AttachBody.prototype.bind = function (decorated, container, $container) {
      var self = this;
  
      var setupResultsEvents = false;
  
      decorated.call(this, container, $container);
  
      container.on('open', function () {
        self._showDropdown();
        self._attachPositioningHandler(container);
  
        if (!setupResultsEvents) {
          setupResultsEvents = true;
  
          container.on('results:all', function () {
            self._positionDropdown();
            self._resizeDropdown();
          });
  
          container.on('results:append', function () {
            self._positionDropdown();
            self._resizeDropdown();
          });
        }
        function repositionOnSelectionChange() {
            if (!this.options.get('closeOnSelect')) {
                self._positionDropdown();
            }
        }
        container.on('select', repositionOnSelectionChange);
        container.on('unselect', repositionOnSelectionChange);
      });
  
      container.on('close', function () {
        self._hideDropdown();
        self._detachPositioningHandler(container);
      });
  
      this.$dropdownContainer.on('mousedown', function (evt) {
        evt.stopPropagation();
      });
    };
  
    AttachBody.prototype.destroy = function (decorated) {
      decorated.call(this);
  
      this.$dropdownContainer.remove();
    };
  
    AttachBody.prototype.position = function (decorated, $dropdown, $container) {
      // Clone all of the container classes
      $dropdown.attr('class', $container.attr('class'));
  
      $dropdown.removeClass('select2');
      $dropdown.addClass('select2-container--open');
  
      $dropdown.css({
        position: 'absolute',
        top: -999999
      });
  
      this.$container = $container;
    };
  
    AttachBody.prototype.render = function (decorated) {
      var $container = $('<span></span>');
  
      var $dropdown = decorated.call(this);
      $container.append($dropdown);
  
      this.$dropdownContainer = $container;
  
      return $container;
    };
  
    AttachBody.prototype._hideDropdown = function (decorated) {
      this.$dropdownContainer.detach();
    };
  
    AttachBody.prototype._attachPositioningHandler =
        function (decorated, container) {
      var self = this;
  
      var scrollEvent = 'scroll.select2.' + container.id;
      var resizeEvent = 'resize.select2.' + container.id;
      var orientationEvent = 'orientationchange.select2.' + container.id;
  
      var $watchers = this.$container.parents().filter(Utils.hasScroll);
      $watchers.each(function () {
        $(this).data('select2-scroll-position', {
          x: $(this).scrollLeft(),
          y: $(this).scrollTop()
        });
      });
  
      $watchers.on(scrollEvent, function (ev) {
        var position = $(this).data('select2-scroll-position');
        $(this).scrollTop(position.y);
      });
  
      $(window).on(scrollEvent + ' ' + resizeEvent + ' ' + orientationEvent,
        function (e) {
        self._positionDropdown();
        self._resizeDropdown();
      });
    };
  
    AttachBody.prototype._detachPositioningHandler =
        function (decorated, container) {
      var scrollEvent = 'scroll.select2.' + container.id;
      var resizeEvent = 'resize.select2.' + container.id;
      var orientationEvent = 'orientationchange.select2.' + container.id;
  
      var $watchers = this.$container.parents().filter(Utils.hasScroll);
      $watchers.off(scrollEvent);
  
      $(window).off(scrollEvent + ' ' + resizeEvent + ' ' + orientationEvent);
    };
  
    AttachBody.prototype._positionDropdown = function () {
      var $window = $(window);
  
      var isCurrentlyAbove = this.$dropdown.hasClass('select2-dropdown--above');
      var isCurrentlyBelow = this.$dropdown.hasClass('select2-dropdown--below');
  
      var newDirection = null;
  
      var offset = this.$container.offset();
  
      offset.bottom = offset.top + this.$container.outerHeight(false);
  
      var container = {
        height: this.$container.outerHeight(false)
      };
  
      container.top = offset.top;
      container.bottom = offset.top + container.height;
  
      var dropdown = {
        height: this.$dropdown.outerHeight(false)
      };
  
      var viewport = {
        top: $window.scrollTop(),
        bottom: $window.scrollTop() + $window.height()
      };
  
      var enoughRoomAbove = viewport.top < (offset.top - dropdown.height);
      var enoughRoomBelow = viewport.bottom > (offset.bottom + dropdown.height);
  
      var css = {
        left: offset.left,
        top: container.bottom
      };
  
      // Determine what the parent element is to use for calciulating the offset
      var $offsetParent = this.$dropdownParent;
  
      // For statically positoned elements, we need to get the element
      // that is determining the offset
      if ($offsetParent.css('position') === 'static') {
        $offsetParent = $offsetParent.offsetParent();
      }
  
      var parentOffset = $offsetParent.offset();
  
      css.top -= parentOffset.top;
      css.left -= parentOffset.left;
  
      if (!isCurrentlyAbove && !isCurrentlyBelow) {
        newDirection = 'below';
      }
  
      if (!enoughRoomBelow && enoughRoomAbove && !isCurrentlyAbove) {
        newDirection = 'above';
      } else if (!enoughRoomAbove && enoughRoomBelow && isCurrentlyAbove) {
        newDirection = 'below';
      }
  
      if (newDirection == 'above' ||
        (isCurrentlyAbove && newDirection !== 'below')) {
        css.top = container.top - parentOffset.top - dropdown.height;
      }
  
      if (newDirection != null) {
        this.$dropdown
          .removeClass('select2-dropdown--below select2-dropdown--above')
          .addClass('select2-dropdown--' + newDirection);
        this.$container
          .removeClass('select2-container--below select2-container--above')
          .addClass('select2-container--' + newDirection);
      }
  
      this.$dropdownContainer.css(css);
    };
  
    AttachBody.prototype._resizeDropdown = function () {
      var css = {
        width: this.$container.outerWidth(false) + 'px'
      };
  
      if (this.options.get('dropdownAutoWidth')) {
        css.minWidth = css.width;
        css.position = 'relative';
        css.width = 'auto';
      }
  
      this.$dropdown.css(css);
    };
  
    AttachBody.prototype._showDropdown = function (decorated) {
      this.$dropdownContainer.appendTo(this.$dropdownParent);
  
      this._positionDropdown();
      this._resizeDropdown();
    };
  
    return AttachBody;
  });
  
  S2.define('select2/dropdown/minimumResultsForSearch',[
  
  ], function () {
    function countResults (data) {
      var count = 0;
  
      for (var d = 0; d < data.length; d++) {
        var item = data[d];
  
        if (item.children) {
          count += countResults(item.children);
        } else {
          count++;
        }
      }
  
      return count;
    }
  
    function MinimumResultsForSearch (decorated, $element, options, dataAdapter) {
      this.minimumResultsForSearch = options.get('minimumResultsForSearch');
  
      if (this.minimumResultsForSearch < 0) {
        this.minimumResultsForSearch = Infinity;
      }
  
      decorated.call(this, $element, options, dataAdapter);
    }
  
    MinimumResultsForSearch.prototype.showSearch = function (decorated, params) {
      if (countResults(params.data.results) < this.minimumResultsForSearch) {
        return false;
      }
  
      return decorated.call(this, params);
    };
  
    return MinimumResultsForSearch;
  });
  
  S2.define('select2/dropdown/selectOnClose',[
  
  ], function () {
    function SelectOnClose () { }
  
    SelectOnClose.prototype.bind = function (decorated, container, $container) {
      var self = this;
  
      decorated.call(this, container, $container);
  
      container.on('close', function (params) {
        self._handleSelectOnClose(params);
      });
    };
  
    SelectOnClose.prototype._handleSelectOnClose = function (_, params) {
      if (params && params.originalSelect2Event != null) {
        var event = params.originalSelect2Event;
  
        // Don't select an item if the close event was triggered from a select or
        // unselect event
        if (event._type === 'select' || event._type === 'unselect') {
          return;
        }
      }
  
      var $highlightedResults = this.getHighlightedResults();
  
      // Only select highlighted results
      if ($highlightedResults.length < 1) {
        return;
      }
  
      var data = $highlightedResults.data('data');
  
      // Don't re-select already selected resulte
      if (
        (data.element != null && data.element.selected) ||
        (data.element == null && data.selected)
      ) {
        return;
      }
  
      this.trigger('select', {
          data: data
      });
    };
  
    return SelectOnClose;
  });
  
  S2.define('select2/dropdown/closeOnSelect',[
  
  ], function () {
    function CloseOnSelect () { }
  
    CloseOnSelect.prototype.bind = function (decorated, container, $container) {
      var self = this;
  
      decorated.call(this, container, $container);
  
      container.on('select', function (evt) {
        self._selectTriggered(evt);
      });
  
      container.on('unselect', function (evt) {
        self._selectTriggered(evt);
      });
    };
  
    CloseOnSelect.prototype._selectTriggered = function (_, evt) {
      var originalEvent = evt.originalEvent;
  
      // Don't close if the control key is being held
      if (originalEvent && originalEvent.ctrlKey) {
        return;
      }
  
      this.trigger('close', {
        originalEvent: originalEvent,
        originalSelect2Event: evt
      });
    };
  
    return CloseOnSelect;
  });
  
  S2.define('select2/i18n/en',[],function () {
    // English
    return {
      errorLoading: function () {
        return 'The results could not be loaded.';
      },
      inputTooLong: function (args) {
        var overChars = args.input.length - args.maximum;
  
        var message = 'Please delete ' + overChars + ' character';
  
        if (overChars != 1) {
          message += 's';
        }
  
        return message;
      },
      inputTooShort: function (args) {
        var remainingChars = args.minimum - args.input.length;
  
        var message = 'Please enter ' + remainingChars + ' or more characters';
  
        return message;
      },
      loadingMore: function () {
        return 'Loading more results…';
      },
      maximumSelected: function (args) {
        var message = 'You can only select ' + args.maximum + ' item';
  
        if (args.maximum != 1) {
          message += 's';
        }
  
        return message;
      },
      noResults: function () {
        return 'No results found';
      },
      searching: function () {
        return 'Searching…';
      }
    };
  });
  
  S2.define('select2/defaults',[
    'jquery',
    'require',
  
    './results',
  
    './selection/single',
    './selection/multiple',
    './selection/placeholder',
    './selection/allowClear',
    './selection/search',
    './selection/eventRelay',
  
    './utils',
    './translation',
    './diacritics',
  
    './data/select',
    './data/array',
    './data/ajax',
    './data/tags',
    './data/tokenizer',
    './data/minimumInputLength',
    './data/maximumInputLength',
    './data/maximumSelectionLength',
  
    './dropdown',
    './dropdown/search',
    './dropdown/hidePlaceholder',
    './dropdown/infiniteScroll',
    './dropdown/attachBody',
    './dropdown/minimumResultsForSearch',
    './dropdown/selectOnClose',
    './dropdown/closeOnSelect',
  
    './i18n/en'
  ], function ($, require,
  
               ResultsList,
  
               SingleSelection, MultipleSelection, Placeholder, AllowClear,
               SelectionSearch, EventRelay,
  
               Utils, Translation, DIACRITICS,
  
               SelectData, ArrayData, AjaxData, Tags, Tokenizer,
               MinimumInputLength, MaximumInputLength, MaximumSelectionLength,
  
               Dropdown, DropdownSearch, HidePlaceholder, InfiniteScroll,
               AttachBody, MinimumResultsForSearch, SelectOnClose, CloseOnSelect,
  
               EnglishTranslation) {
    function Defaults () {
      this.reset();
    }
  
    Defaults.prototype.apply = function (options) {
      options = $.extend(true, {}, this.defaults, options);
  
      if (options.dataAdapter == null) {
        if (options.ajax != null) {
          options.dataAdapter = AjaxData;
        } else if (options.data != null) {
          options.dataAdapter = ArrayData;
        } else {
          options.dataAdapter = SelectData;
        }
  
        if (options.minimumInputLength > 0) {
          options.dataAdapter = Utils.Decorate(
            options.dataAdapter,
            MinimumInputLength
          );
        }
  
        if (options.maximumInputLength > 0) {
          options.dataAdapter = Utils.Decorate(
            options.dataAdapter,
            MaximumInputLength
          );
        }
  
        if (options.maximumSelectionLength > 0) {
          options.dataAdapter = Utils.Decorate(
            options.dataAdapter,
            MaximumSelectionLength
          );
        }
  
        if (options.tags) {
          options.dataAdapter = Utils.Decorate(options.dataAdapter, Tags);
        }
  
        if (options.tokenSeparators != null || options.tokenizer != null) {
          options.dataAdapter = Utils.Decorate(
            options.dataAdapter,
            Tokenizer
          );
        }
  
        if (options.query != null) {
          var Query = require(options.amdBase + 'compat/query');
  
          options.dataAdapter = Utils.Decorate(
            options.dataAdapter,
            Query
          );
        }
  
        if (options.initSelection != null) {
          var InitSelection = require(options.amdBase + 'compat/initSelection');
  
          options.dataAdapter = Utils.Decorate(
            options.dataAdapter,
            InitSelection
          );
        }
      }
  
      if (options.resultsAdapter == null) {
        options.resultsAdapter = ResultsList;
  
        if (options.ajax != null) {
          options.resultsAdapter = Utils.Decorate(
            options.resultsAdapter,
            InfiniteScroll
          );
        }
  
        if (options.placeholder != null) {
          options.resultsAdapter = Utils.Decorate(
            options.resultsAdapter,
            HidePlaceholder
          );
        }
  
        if (options.selectOnClose) {
          options.resultsAdapter = Utils.Decorate(
            options.resultsAdapter,
            SelectOnClose
          );
        }
      }
  
      if (options.dropdownAdapter == null) {
        if (options.multiple) {
          options.dropdownAdapter = Dropdown;
        } else {
          var SearchableDropdown = Utils.Decorate(Dropdown, DropdownSearch);
  
          options.dropdownAdapter = SearchableDropdown;
        }
  
        if (options.minimumResultsForSearch !== 0) {
          options.dropdownAdapter = Utils.Decorate(
            options.dropdownAdapter,
            MinimumResultsForSearch
          );
        }
  
        if (options.closeOnSelect) {
          options.dropdownAdapter = Utils.Decorate(
            options.dropdownAdapter,
            CloseOnSelect
          );
        }
  
        if (
          options.dropdownCssClass != null ||
          options.dropdownCss != null ||
          options.adaptDropdownCssClass != null
        ) {
          var DropdownCSS = require(options.amdBase + 'compat/dropdownCss');
  
          options.dropdownAdapter = Utils.Decorate(
            options.dropdownAdapter,
            DropdownCSS
          );
        }
  
        options.dropdownAdapter = Utils.Decorate(
          options.dropdownAdapter,
          AttachBody
        );
      }
  
      if (options.selectionAdapter == null) {
        if (options.multiple) {
          options.selectionAdapter = MultipleSelection;
        } else {
          options.selectionAdapter = SingleSelection;
        }
  
        // Add the placeholder mixin if a placeholder was specified
        if (options.placeholder != null) {
          options.selectionAdapter = Utils.Decorate(
            options.selectionAdapter,
            Placeholder
          );
        }
  
        if (options.allowClear) {
          options.selectionAdapter = Utils.Decorate(
            options.selectionAdapter,
            AllowClear
          );
        }
  
        if (options.multiple) {
          options.selectionAdapter = Utils.Decorate(
            options.selectionAdapter,
            SelectionSearch
          );
        }
  
        if (
          options.containerCssClass != null ||
          options.containerCss != null ||
          options.adaptContainerCssClass != null
        ) {
          var ContainerCSS = require(options.amdBase + 'compat/containerCss');
  
          options.selectionAdapter = Utils.Decorate(
            options.selectionAdapter,
            ContainerCSS
          );
        }
  
        options.selectionAdapter = Utils.Decorate(
          options.selectionAdapter,
          EventRelay
        );
      }
  
      if (typeof options.language === 'string') {
        // Check if the language is specified with a region
        if (options.language.indexOf('-') > 0) {
          // Extract the region information if it is included
          var languageParts = options.language.split('-');
          var baseLanguage = languageParts[0];
  
          options.language = [options.language, baseLanguage];
        } else {
          options.language = [options.language];
        }
      }
  
      if ($.isArray(options.language)) {
        var languages = new Translation();
        options.language.push('en');
  
        var languageNames = options.language;
  
        for (var l = 0; l < languageNames.length; l++) {
          var name = languageNames[l];
          var language = {};
  
          try {
            // Try to load it with the original name
            language = Translation.loadPath(name);
          } catch (e) {
            try {
              // If we couldn't load it, check if it wasn't the full path
              name = this.defaults.amdLanguageBase + name;
              language = Translation.loadPath(name);
            } catch (ex) {
              // The translation could not be loaded at all. Sometimes this is
              // because of a configuration problem, other times this can be
              // because of how Select2 helps load all possible translation files.
              if (options.debug && window.console && console.warn) {
                console.warn(
                  'Select2: The language file for "' + name + '" could not be ' +
                  'automatically loaded. A fallback will be used instead.'
                );
              }
  
              continue;
            }
          }
  
          languages.extend(language);
        }
  
        options.translations = languages;
      } else {
        var baseTranslation = Translation.loadPath(
          this.defaults.amdLanguageBase + 'en'
        );
        var customTranslation = new Translation(options.language);
  
        customTranslation.extend(baseTranslation);
  
        options.translations = customTranslation;
      }
  
      return options;
    };
  
    Defaults.prototype.reset = function () {
      function stripDiacritics (text) {
        // Used 'uni range + named function' from http://jsperf.com/diacritics/18
        function match(a) {
          return DIACRITICS[a] || a;
        }
  
        return text.replace(/[^\u0000-\u007E]/g, match);
      }
  
      function matcher (params, data) {
        // Always return the object if there is nothing to compare
        if ($.trim(params.term) === '') {
          return data;
        }
  
        // Do a recursive check for options with children
        if (data.children && data.children.length > 0) {
          // Clone the data object if there are children
          // This is required as we modify the object to remove any non-matches
          var match = $.extend(true, {}, data);
  
          // Check each child of the option
          for (var c = data.children.length - 1; c >= 0; c--) {
            var child = data.children[c];
  
            var matches = matcher(params, child);
  
            // If there wasn't a match, remove the object in the array
            if (matches == null) {
              match.children.splice(c, 1);
            }
          }
  
          // If any children matched, return the new object
          if (match.children.length > 0) {
            return match;
          }
  
          // If there were no matching children, check just the plain object
          return matcher(params, match);
        }
  
        var original = stripDiacritics(data.text).toUpperCase();
        var term = stripDiacritics(params.term).toUpperCase();
  
        // Check if the text contains the term
        if (original.indexOf(term) > -1) {
          return data;
        }
  
        // If it doesn't contain the term, don't return anything
        return null;
      }
  
      this.defaults = {
        amdBase: './',
        amdLanguageBase: './i18n/',
        closeOnSelect: true,
        debug: false,
        dropdownAutoWidth: false,
        escapeMarkup: Utils.escapeMarkup,
        language: EnglishTranslation,
        matcher: matcher,
        minimumInputLength: 0,
        maximumInputLength: 0,
        maximumSelectionLength: 0,
        minimumResultsForSearch: 0,
        selectOnClose: false,
        sorter: function (data) {
          return data;
        },
        templateResult: function (result) {
          return result.text;
        },
        templateSelection: function (selection) {
          return selection.text;
        },
        theme: 'default',
        width: 'resolve'
      };
    };
  
    Defaults.prototype.set = function (key, value) {
      var camelKey = $.camelCase(key);
  
      var data = {};
      data[camelKey] = value;
  
      var convertedData = Utils._convertData(data);
  
      $.extend(this.defaults, convertedData);
    };
  
    var defaults = new Defaults();
  
    return defaults;
  });
  
  S2.define('select2/options',[
    'require',
    'jquery',
    './defaults',
    './utils'
  ], function (require, $, Defaults, Utils) {
    function Options (options, $element) {
      this.options = options;
  
      if ($element != null) {
        this.fromElement($element);
      }
  
      this.options = Defaults.apply(this.options);
  
      if ($element && $element.is('input')) {
        var InputCompat = require(this.get('amdBase') + 'compat/inputData');
  
        this.options.dataAdapter = Utils.Decorate(
          this.options.dataAdapter,
          InputCompat
        );
      }
    }
  
    Options.prototype.fromElement = function ($e) {
      var excludedData = ['select2'];
  
      if (this.options.multiple == null) {
        this.options.multiple = $e.prop('multiple');
      }
  
      if (this.options.disabled == null) {
        this.options.disabled = $e.prop('disabled');
      }
  
      if (this.options.language == null) {
        if ($e.prop('lang')) {
          this.options.language = $e.prop('lang').toLowerCase();
        } else if ($e.closest('[lang]').prop('lang')) {
          this.options.language = $e.closest('[lang]').prop('lang');
        }
      }
  
      if (this.options.dir == null) {
        if ($e.prop('dir')) {
          this.options.dir = $e.prop('dir');
        } else if ($e.closest('[dir]').prop('dir')) {
          this.options.dir = $e.closest('[dir]').prop('dir');
        } else {
          this.options.dir = 'ltr';
        }
      }
  
      $e.prop('disabled', this.options.disabled);
      $e.prop('multiple', this.options.multiple);
  
      if ($e.data('select2Tags')) {
        if (this.options.debug && window.console && console.warn) {
          console.warn(
            'Select2: The `data-select2-tags` attribute has been changed to ' +
            'use the `data-data` and `data-tags="true"` attributes and will be ' +
            'removed in future versions of Select2.'
          );
        }
  
        $e.data('data', $e.data('select2Tags'));
        $e.data('tags', true);
      }
  
      if ($e.data('ajaxUrl')) {
        if (this.options.debug && window.console && console.warn) {
          console.warn(
            'Select2: The `data-ajax-url` attribute has been changed to ' +
            '`data-ajax--url` and support for the old attribute will be removed' +
            ' in future versions of Select2.'
          );
        }
  
        $e.attr('ajax--url', $e.data('ajaxUrl'));
        $e.data('ajax--url', $e.data('ajaxUrl'));
      }
  
      var dataset = {};
  
      // Prefer the element's `dataset` attribute if it exists
      // jQuery 1.x does not correctly handle data attributes with multiple dashes
      if ($.fn.jquery && $.fn.jquery.substr(0, 2) == '1.' && $e[0].dataset) {
        dataset = $.extend(true, {}, $e[0].dataset, $e.data());
      } else {
        dataset = $e.data();
      }
  
      var data = $.extend(true, {}, dataset);
  
      data = Utils._convertData(data);
  
      for (var key in data) {
        if ($.inArray(key, excludedData) > -1) {
          continue;
        }
  
        if ($.isPlainObject(this.options[key])) {
          $.extend(this.options[key], data[key]);
        } else {
          this.options[key] = data[key];
        }
      }
  
      return this;
    };
  
    Options.prototype.get = function (key) {
      return this.options[key];
    };
  
    Options.prototype.set = function (key, val) {
      this.options[key] = val;
    };
  
    return Options;
  });
  
  S2.define('select2/core',[
    'jquery',
    './options',
    './utils',
    './keys'
  ], function ($, Options, Utils, KEYS) {
    var Select2 = function ($element, options) {
      if ($element.data('select2') != null) {
        $element.data('select2').destroy();
      }
  
      this.$element = $element;
  
      this.id = this._generateId($element);
  
      options = options || {};
  
      this.options = new Options(options, $element);
  
      Select2.__super__.constructor.call(this);
  
      // Set up the tabindex
  
      var tabindex = $element.attr('tabindex') || 0;
      $element.data('old-tabindex', tabindex);
      $element.attr('tabindex', '-1');
  
      // Set up containers and adapters
  
      var DataAdapter = this.options.get('dataAdapter');
      this.dataAdapter = new DataAdapter($element, this.options);
  
      var $container = this.render();
  
      this._placeContainer($container);
  
      var SelectionAdapter = this.options.get('selectionAdapter');
      this.selection = new SelectionAdapter($element, this.options);
      this.$selection = this.selection.render();
  
      this.selection.position(this.$selection, $container);
  
      var DropdownAdapter = this.options.get('dropdownAdapter');
      this.dropdown = new DropdownAdapter($element, this.options);
      this.$dropdown = this.dropdown.render();
  
      this.dropdown.position(this.$dropdown, $container);
  
      var ResultsAdapter = this.options.get('resultsAdapter');
      this.results = new ResultsAdapter($element, this.options, this.dataAdapter);
      this.$results = this.results.render();
  
      this.results.position(this.$results, this.$dropdown);
  
      // Bind events
  
      var self = this;
  
      // Bind the container to all of the adapters
      this._bindAdapters();
  
      // Register any DOM event handlers
      this._registerDomEvents();
  
      // Register any internal event handlers
      this._registerDataEvents();
      this._registerSelectionEvents();
      this._registerDropdownEvents();
      this._registerResultsEvents();
      this._registerEvents();
  
      // Set the initial state
      this.dataAdapter.current(function (initialData) {
        self.trigger('selection:update', {
          data: initialData
        });
      });
  
      // Hide the original select
      $element.addClass('select2-hidden-accessible');
      $element.attr('aria-hidden', 'true');
  
      // Synchronize any monitored attributes
      this._syncAttributes();
  
      $element.data('select2', this);
    };
  
    Utils.Extend(Select2, Utils.Observable);
  
    Select2.prototype._generateId = function ($element) {
      var id = '';
  
      if ($element.attr('id') != null) {
        id = $element.attr('id');
      } else if ($element.attr('name') != null) {
        id = $element.attr('name') + '-' + Utils.generateChars(2);
      } else {
        id = Utils.generateChars(4);
      }
  
      id = id.replace(/(:|\.|\[|\]|,)/g, '');
      id = 'select2-' + id;
  
      return id;
    };
  
    Select2.prototype._placeContainer = function ($container) {
      $container.insertAfter(this.$element);
  
      var width = this._resolveWidth(this.$element, this.options.get('width'));
  
      if (width != null) {
        $container.css('width', width);
      }
    };
  
    Select2.prototype._resolveWidth = function ($element, method) {
      var WIDTH = /^width:(([-+]?([0-9]*\.)?[0-9]+)(px|em|ex|%|in|cm|mm|pt|pc))/i;
  
      if (method == 'resolve') {
        var styleWidth = this._resolveWidth($element, 'style');
  
        if (styleWidth != null) {
          return styleWidth;
        }
  
        return this._resolveWidth($element, 'element');
      }
  
      if (method == 'element') {
        var elementWidth = $element.outerWidth(false);
  
        if (elementWidth <= 0) {
          return 'auto';
        }
  
        return elementWidth + 'px';
      }
  
      if (method == 'style') {
        var style = $element.attr('style');
  
        if (typeof(style) !== 'string') {
          return null;
        }
  
        var attrs = style.split(';');
  
        for (var i = 0, l = attrs.length; i < l; i = i + 1) {
          var attr = attrs[i].replace(/\s/g, '');
          var matches = attr.match(WIDTH);
  
          if (matches !== null && matches.length >= 1) {
            return matches[1];
          }
        }
  
        return null;
      }
  
      return method;
    };
  
    Select2.prototype._bindAdapters = function () {
      this.dataAdapter.bind(this, this.$container);
      this.selection.bind(this, this.$container);
  
      this.dropdown.bind(this, this.$container);
      this.results.bind(this, this.$container);
    };
  
    Select2.prototype._registerDomEvents = function () {
      var self = this;
  
      this.$element.on('change.select2', function () {
        self.dataAdapter.current(function (data) {
          self.trigger('selection:update', {
            data: data
          });
        });
      });
  
      this.$element.on('focus.select2', function (evt) {
        self.trigger('focus', evt);
      });
  
      this._syncA = Utils.bind(this._syncAttributes, this);
      this._syncS = Utils.bind(this._syncSubtree, this);
  
      if (this.$element[0].attachEvent) {
        this.$element[0].attachEvent('onpropertychange', this._syncA);
      }
  
      var observer = window.MutationObserver ||
        window.WebKitMutationObserver ||
        window.MozMutationObserver
      ;
  
      if (observer != null) {
        this._observer = new observer(function (mutations) {
          $.each(mutations, self._syncA);
          $.each(mutations, self._syncS);
        });
        this._observer.observe(this.$element[0], {
          attributes: true,
          childList: true,
          subtree: false
        });
      } else if (this.$element[0].addEventListener) {
        this.$element[0].addEventListener(
          'DOMAttrModified',
          self._syncA,
          false
        );
        this.$element[0].addEventListener(
          'DOMNodeInserted',
          self._syncS,
          false
        );
        this.$element[0].addEventListener(
          'DOMNodeRemoved',
          self._syncS,
          false
        );
      }
    };
  
    Select2.prototype._registerDataEvents = function () {
      var self = this;
  
      this.dataAdapter.on('*', function (name, params) {
        self.trigger(name, params);
      });
    };
  
    Select2.prototype._registerSelectionEvents = function () {
      var self = this;
      var nonRelayEvents = ['toggle', 'focus'];
  
      this.selection.on('toggle', function () {
        self.toggleDropdown();
      });
  
      this.selection.on('focus', function (params) {
        self.focus(params);
      });
  
      this.selection.on('*', function (name, params) {
        if ($.inArray(name, nonRelayEvents) !== -1) {
          return;
        }
  
        self.trigger(name, params);
      });
    };
  
    Select2.prototype._registerDropdownEvents = function () {
      var self = this;
  
      this.dropdown.on('*', function (name, params) {
        self.trigger(name, params);
      });
    };
  
    Select2.prototype._registerResultsEvents = function () {
      var self = this;
  
      this.results.on('*', function (name, params) {
        self.trigger(name, params);
      });
    };
  
    Select2.prototype._registerEvents = function () {
      var self = this;
  
      this.on('open', function () {
        self.$container.addClass('select2-container--open');
      });
  
      this.on('close', function () {
        self.$container.removeClass('select2-container--open');
      });
  
      this.on('enable', function () {
        self.$container.removeClass('select2-container--disabled');
      });
  
      this.on('disable', function () {
        self.$container.addClass('select2-container--disabled');
      });
  
      this.on('blur', function () {
        self.$container.removeClass('select2-container--focus');
      });
  
      this.on('query', function (params) {
        if (!self.isOpen()) {
          self.trigger('open', {});
        }
  
        this.dataAdapter.query(params, function (data) {
          self.trigger('results:all', {
            data: data,
            query: params
          });
        });
      });
  
      this.on('query:append', function (params) {
        this.dataAdapter.query(params, function (data) {
          self.trigger('results:append', {
            data: data,
            query: params
          });
        });
      });
  
      this.on('keypress', function (evt) {
        var key = evt.which;
  
        if (self.isOpen()) {
          if (key === KEYS.ESC || key === KEYS.TAB ||
              (key === KEYS.UP && evt.altKey)) {
            self.close();
  
            evt.preventDefault();
          } else if (key === KEYS.ENTER) {
            self.trigger('results:select', {});
  
            evt.preventDefault();
          } else if ((key === KEYS.SPACE && evt.ctrlKey)) {
            self.trigger('results:toggle', {});
  
            evt.preventDefault();
          } else if (key === KEYS.UP) {
            self.trigger('results:previous', {});
  
            evt.preventDefault();
          } else if (key === KEYS.DOWN) {
            self.trigger('results:next', {});
  
            evt.preventDefault();
          }
        } else {
          if (key === KEYS.ENTER || key === KEYS.SPACE ||
              (key === KEYS.DOWN && evt.altKey)) {
            self.open();
  
            evt.preventDefault();
          }
        }
      });
    };
  
    Select2.prototype._syncAttributes = function () {
      this.options.set('disabled', this.$element.prop('disabled'));
  
      if (this.options.get('disabled')) {
        if (this.isOpen()) {
          this.close();
        }
  
        this.trigger('disable', {});
      } else {
        this.trigger('enable', {});
      }
    };
  
    Select2.prototype._syncSubtree = function (evt, mutations) {
      var changed = false;
      var self = this;
  
      // Ignore any mutation events raised for elements that aren't options or
      // optgroups. This handles the case when the select element is destroyed
      if (
        evt && evt.target && (
          evt.target.nodeName !== 'OPTION' && evt.target.nodeName !== 'OPTGROUP'
        )
      ) {
        return;
      }
  
      if (!mutations) {
        // If mutation events aren't supported, then we can only assume that the
        // change affected the selections
        changed = true;
      } else if (mutations.addedNodes && mutations.addedNodes.length > 0) {
        for (var n = 0; n < mutations.addedNodes.length; n++) {
          var node = mutations.addedNodes[n];
  
          if (node.selected) {
            changed = true;
          }
        }
      } else if (mutations.removedNodes && mutations.removedNodes.length > 0) {
        changed = true;
      }
  
      // Only re-pull the data if we think there is a change
      if (changed) {
        this.dataAdapter.current(function (currentData) {
          self.trigger('selection:update', {
            data: currentData
          });
        });
      }
    };
  
    /**
     * Override the trigger method to automatically trigger pre-events when
     * there are events that can be prevented.
     */
    Select2.prototype.trigger = function (name, args) {
      var actualTrigger = Select2.__super__.trigger;
      var preTriggerMap = {
        'open': 'opening',
        'close': 'closing',
        'select': 'selecting',
        'unselect': 'unselecting'
      };
  
      if (args === undefined) {
        args = {};
      }
  
      if (name in preTriggerMap) {
        var preTriggerName = preTriggerMap[name];
        var preTriggerArgs = {
          prevented: false,
          name: name,
          args: args
        };
  
        actualTrigger.call(this, preTriggerName, preTriggerArgs);
  
        if (preTriggerArgs.prevented) {
          args.prevented = true;
  
          return;
        }
      }
  
      actualTrigger.call(this, name, args);
    };
  
    Select2.prototype.toggleDropdown = function () {
      if (this.options.get('disabled')) {
        return;
      }
  
      if (this.isOpen()) {
        this.close();
      } else {
        this.open();
      }
    };
  
    Select2.prototype.open = function () {
      if (this.isOpen()) {
        return;
      }
  
      this.trigger('query', {});
    };
  
    Select2.prototype.close = function () {
      if (!this.isOpen()) {
        return;
      }
  
      this.trigger('close', {});
    };
  
    Select2.prototype.isOpen = function () {
      return this.$container.hasClass('select2-container--open');
    };
  
    Select2.prototype.hasFocus = function () {
      return this.$container.hasClass('select2-container--focus');
    };
  
    Select2.prototype.focus = function (data) {
      // No need to re-trigger focus events if we are already focused
      if (this.hasFocus()) {
        return;
      }
  
      this.$container.addClass('select2-container--focus');
      this.trigger('focus', {});
    };
  
    Select2.prototype.enable = function (args) {
      if (this.options.get('debug') && window.console && console.warn) {
        console.warn(
          'Select2: The `select2("enable")` method has been deprecated and will' +
          ' be removed in later Select2 versions. Use $element.prop("disabled")' +
          ' instead.'
        );
      }
  
      if (args == null || args.length === 0) {
        args = [true];
      }
  
      var disabled = !args[0];
  
      this.$element.prop('disabled', disabled);
    };
  
    Select2.prototype.data = function () {
      if (this.options.get('debug') &&
          arguments.length > 0 && window.console && console.warn) {
        console.warn(
          'Select2: Data can no longer be set using `select2("data")`. You ' +
          'should consider setting the value instead using `$element.val()`.'
        );
      }
  
      var data = [];
  
      this.dataAdapter.current(function (currentData) {
        data = currentData;
      });
  
      return data;
    };
  
    Select2.prototype.val = function (args) {
      if (this.options.get('debug') && window.console && console.warn) {
        console.warn(
          'Select2: The `select2("val")` method has been deprecated and will be' +
          ' removed in later Select2 versions. Use $element.val() instead.'
        );
      }
  
      if (args == null || args.length === 0) {
        return this.$element.val();
      }
  
      var newVal = args[0];
  
      if ($.isArray(newVal)) {
        newVal = $.map(newVal, function (obj) {
          return obj.toString();
        });
      }
  
      this.$element.val(newVal).trigger('change');
    };
  
    Select2.prototype.destroy = function () {
      this.$container.remove();
  
      if (this.$element[0].detachEvent) {
        this.$element[0].detachEvent('onpropertychange', this._syncA);
      }
  
      if (this._observer != null) {
        this._observer.disconnect();
        this._observer = null;
      } else if (this.$element[0].removeEventListener) {
        this.$element[0]
          .removeEventListener('DOMAttrModified', this._syncA, false);
        this.$element[0]
          .removeEventListener('DOMNodeInserted', this._syncS, false);
        this.$element[0]
          .removeEventListener('DOMNodeRemoved', this._syncS, false);
      }
  
      this._syncA = null;
      this._syncS = null;
  
      this.$element.off('.select2');
      this.$element.attr('tabindex', this.$element.data('old-tabindex'));
  
      this.$element.removeClass('select2-hidden-accessible');
      this.$element.attr('aria-hidden', 'false');
      this.$element.removeData('select2');
  
      this.dataAdapter.destroy();
      this.selection.destroy();
      this.dropdown.destroy();
      this.results.destroy();
  
      this.dataAdapter = null;
      this.selection = null;
      this.dropdown = null;
      this.results = null;
    };
  
    Select2.prototype.render = function () {
      var $container = $(
        '<span class="select2 select2-container">' +
          '<span class="selection"></span>' +
          '<span class="dropdown-wrapper" aria-hidden="true"></span>' +
        '</span>'
      );
  
      $container.attr('dir', this.options.get('dir'));
  
      this.$container = $container;
  
      this.$container.addClass('select2-container--' + this.options.get('theme'));
  
      $container.data('element', this.$element);
  
      return $container;
    };
  
    return Select2;
  });
  
  S2.define('select2/compat/utils',[
    'jquery'
  ], function ($) {
    function syncCssClasses ($dest, $src, adapter) {
      var classes, replacements = [], adapted;
  
      classes = $.trim($dest.attr('class'));
  
      if (classes) {
        classes = '' + classes; // for IE which returns object
  
        $(classes.split(/\s+/)).each(function () {
          // Save all Select2 classes
          if (this.indexOf('select2-') === 0) {
            replacements.push(this);
          }
        });
      }
  
      classes = $.trim($src.attr('class'));
  
      if (classes) {
        classes = '' + classes; // for IE which returns object
  
        $(classes.split(/\s+/)).each(function () {
          // Only adapt non-Select2 classes
          if (this.indexOf('select2-') !== 0) {
            adapted = adapter(this);
  
            if (adapted != null) {
              replacements.push(adapted);
            }
          }
        });
      }
  
      $dest.attr('class', replacements.join(' '));
    }
  
    return {
      syncCssClasses: syncCssClasses
    };
  });
  
  S2.define('select2/compat/containerCss',[
    'jquery',
    './utils'
  ], function ($, CompatUtils) {
    // No-op CSS adapter that discards all classes by default
    function _containerAdapter (clazz) {
      return null;
    }
  
    function ContainerCSS () { }
  
    ContainerCSS.prototype.render = function (decorated) {
      var $container = decorated.call(this);
  
      var containerCssClass = this.options.get('containerCssClass') || '';
  
      if ($.isFunction(containerCssClass)) {
        containerCssClass = containerCssClass(this.$element);
      }
  
      var containerCssAdapter = this.options.get('adaptContainerCssClass');
      containerCssAdapter = containerCssAdapter || _containerAdapter;
  
      if (containerCssClass.indexOf(':all:') !== -1) {
        containerCssClass = containerCssClass.replace(':all:', '');
  
        var _cssAdapter = containerCssAdapter;
  
        containerCssAdapter = function (clazz) {
          var adapted = _cssAdapter(clazz);
  
          if (adapted != null) {
            // Append the old one along with the adapted one
            return adapted + ' ' + clazz;
          }
  
          return clazz;
        };
      }
  
      var containerCss = this.options.get('containerCss') || {};
  
      if ($.isFunction(containerCss)) {
        containerCss = containerCss(this.$element);
      }
  
      CompatUtils.syncCssClasses($container, this.$element, containerCssAdapter);
  
      $container.css(containerCss);
      $container.addClass(containerCssClass);
  
      return $container;
    };
  
    return ContainerCSS;
  });
  
  S2.define('select2/compat/dropdownCss',[
    'jquery',
    './utils'
  ], function ($, CompatUtils) {
    // No-op CSS adapter that discards all classes by default
    function _dropdownAdapter (clazz) {
      return null;
    }
  
    function DropdownCSS () { }
  
    DropdownCSS.prototype.render = function (decorated) {
      var $dropdown = decorated.call(this);
  
      var dropdownCssClass = this.options.get('dropdownCssClass') || '';
  
      if ($.isFunction(dropdownCssClass)) {
        dropdownCssClass = dropdownCssClass(this.$element);
      }
  
      var dropdownCssAdapter = this.options.get('adaptDropdownCssClass');
      dropdownCssAdapter = dropdownCssAdapter || _dropdownAdapter;
  
      if (dropdownCssClass.indexOf(':all:') !== -1) {
        dropdownCssClass = dropdownCssClass.replace(':all:', '');
  
        var _cssAdapter = dropdownCssAdapter;
  
        dropdownCssAdapter = function (clazz) {
          var adapted = _cssAdapter(clazz);
  
          if (adapted != null) {
            // Append the old one along with the adapted one
            return adapted + ' ' + clazz;
          }
  
          return clazz;
        };
      }
  
      var dropdownCss = this.options.get('dropdownCss') || {};
  
      if ($.isFunction(dropdownCss)) {
        dropdownCss = dropdownCss(this.$element);
      }
  
      CompatUtils.syncCssClasses($dropdown, this.$element, dropdownCssAdapter);
  
      $dropdown.css(dropdownCss);
      $dropdown.addClass(dropdownCssClass);
  
      return $dropdown;
    };
  
    return DropdownCSS;
  });
  
  S2.define('select2/compat/initSelection',[
    'jquery'
  ], function ($) {
    function InitSelection (decorated, $element, options) {
      if (options.get('debug') && window.console && console.warn) {
        console.warn(
          'Select2: The `initSelection` option has been deprecated in favor' +
          ' of a custom data adapter that overrides the `current` method. ' +
          'This method is now called multiple times instead of a single ' +
          'time when the instance is initialized. Support will be removed ' +
          'for the `initSelection` option in future versions of Select2'
        );
      }
  
      this.initSelection = options.get('initSelection');
      this._isInitialized = false;
  
      decorated.call(this, $element, options);
    }
  
    InitSelection.prototype.current = function (decorated, callback) {
      var self = this;
  
      if (this._isInitialized) {
        decorated.call(this, callback);
  
        return;
      }
  
      this.initSelection.call(null, this.$element, function (data) {
        self._isInitialized = true;
  
        if (!$.isArray(data)) {
          data = [data];
        }
  
        callback(data);
      });
    };
  
    return InitSelection;
  });
  
  S2.define('select2/compat/inputData',[
    'jquery'
  ], function ($) {
    function InputData (decorated, $element, options) {
      this._currentData = [];
      this._valueSeparator = options.get('valueSeparator') || ',';
  
      if ($element.prop('type') === 'hidden') {
        if (options.get('debug') && console && console.warn) {
          console.warn(
            'Select2: Using a hidden input with Select2 is no longer ' +
            'supported and may stop working in the future. It is recommended ' +
            'to use a `<select>` element instead.'
          );
        }
      }
  
      decorated.call(this, $element, options);
    }
  
    InputData.prototype.current = function (_, callback) {
      function getSelected (data, selectedIds) {
        var selected = [];
  
        if (data.selected || $.inArray(data.id, selectedIds) !== -1) {
          data.selected = true;
          selected.push(data);
        } else {
          data.selected = false;
        }
  
        if (data.children) {
          selected.push.apply(selected, getSelected(data.children, selectedIds));
        }
  
        return selected;
      }
  
      var selected = [];
  
      for (var d = 0; d < this._currentData.length; d++) {
        var data = this._currentData[d];
  
        selected.push.apply(
          selected,
          getSelected(
            data,
            this.$element.val().split(
              this._valueSeparator
            )
          )
        );
      }
  
      callback(selected);
    };
  
    InputData.prototype.select = function (_, data) {
      if (!this.options.get('multiple')) {
        this.current(function (allData) {
          $.map(allData, function (data) {
            data.selected = false;
          });
        });
  
        this.$element.val(data.id);
        this.$element.trigger('change');
      } else {
        var value = this.$element.val();
        value += this._valueSeparator + data.id;
  
        this.$element.val(value);
        this.$element.trigger('change');
      }
    };
  
    InputData.prototype.unselect = function (_, data) {
      var self = this;
  
      data.selected = false;
  
      this.current(function (allData) {
        var values = [];
  
        for (var d = 0; d < allData.length; d++) {
          var item = allData[d];
  
          if (data.id == item.id) {
            continue;
          }
  
          values.push(item.id);
        }
  
        self.$element.val(values.join(self._valueSeparator));
        self.$element.trigger('change');
      });
    };
  
    InputData.prototype.query = function (_, params, callback) {
      var results = [];
  
      for (var d = 0; d < this._currentData.length; d++) {
        var data = this._currentData[d];
  
        var matches = this.matches(params, data);
  
        if (matches !== null) {
          results.push(matches);
        }
      }
  
      callback({
        results: results
      });
    };
  
    InputData.prototype.addOptions = function (_, $options) {
      var options = $.map($options, function ($option) {
        return $.data($option[0], 'data');
      });
  
      this._currentData.push.apply(this._currentData, options);
    };
  
    return InputData;
  });
  
  S2.define('select2/compat/matcher',[
    'jquery'
  ], function ($) {
    function oldMatcher (matcher) {
      function wrappedMatcher (params, data) {
        var match = $.extend(true, {}, data);
  
        if (params.term == null || $.trim(params.term) === '') {
          return match;
        }
  
        if (data.children) {
          for (var c = data.children.length - 1; c >= 0; c--) {
            var child = data.children[c];
  
            // Check if the child object matches
            // The old matcher returned a boolean true or false
            var doesMatch = matcher(params.term, child.text, child);
  
            // If the child didn't match, pop it off
            if (!doesMatch) {
              match.children.splice(c, 1);
            }
          }
  
          if (match.children.length > 0) {
            return match;
          }
        }
  
        if (matcher(params.term, data.text, data)) {
          return match;
        }
  
        return null;
      }
  
      return wrappedMatcher;
    }
  
    return oldMatcher;
  });
  
  S2.define('select2/compat/query',[
  
  ], function () {
    function Query (decorated, $element, options) {
      if (options.get('debug') && window.console && console.warn) {
        console.warn(
          'Select2: The `query` option has been deprecated in favor of a ' +
          'custom data adapter that overrides the `query` method. Support ' +
          'will be removed for the `query` option in future versions of ' +
          'Select2.'
        );
      }
  
      decorated.call(this, $element, options);
    }
  
    Query.prototype.query = function (_, params, callback) {
      params.callback = callback;
  
      var query = this.options.get('query');
  
      query.call(null, params);
    };
  
    return Query;
  });
  
  S2.define('select2/dropdown/attachContainer',[
  
  ], function () {
    function AttachContainer (decorated, $element, options) {
      decorated.call(this, $element, options);
    }
  
    AttachContainer.prototype.position =
      function (decorated, $dropdown, $container) {
      var $dropdownContainer = $container.find('.dropdown-wrapper');
      $dropdownContainer.append($dropdown);
  
      $dropdown.addClass('select2-dropdown--below');
      $container.addClass('select2-container--below');
    };
  
    return AttachContainer;
  });
  
  S2.define('select2/dropdown/stopPropagation',[
  
  ], function () {
    function StopPropagation () { }
  
    StopPropagation.prototype.bind = function (decorated, container, $container) {
      decorated.call(this, container, $container);
  
      var stoppedEvents = [
      'blur',
      'change',
      'click',
      'dblclick',
      'focus',
      'focusin',
      'focusout',
      'input',
      'keydown',
      'keyup',
      'keypress',
      'mousedown',
      'mouseenter',
      'mouseleave',
      'mousemove',
      'mouseover',
      'mouseup',
      'search',
      'touchend',
      'touchstart'
      ];
  
      this.$dropdown.on(stoppedEvents.join(' '), function (evt) {
        evt.stopPropagation();
      });
    };
  
    return StopPropagation;
  });
  
  S2.define('select2/selection/stopPropagation',[
  
  ], function () {
    function StopPropagation () { }
  
    StopPropagation.prototype.bind = function (decorated, container, $container) {
      decorated.call(this, container, $container);
  
      var stoppedEvents = [
        'blur',
        'change',
        'click',
        'dblclick',
        'focus',
        'focusin',
        'focusout',
        'input',
        'keydown',
        'keyup',
        'keypress',
        'mousedown',
        'mouseenter',
        'mouseleave',
        'mousemove',
        'mouseover',
        'mouseup',
        'search',
        'touchend',
        'touchstart'
      ];
  
      this.$selection.on(stoppedEvents.join(' '), function (evt) {
        evt.stopPropagation();
      });
    };
  
    return StopPropagation;
  });
  
  /*!
   * jQuery Mousewheel 3.1.13
   *
   * Copyright jQuery Foundation and other contributors
   * Released under the MIT license
   * http://jquery.org/license
   */
  
  (function (factory) {
      if ( typeof S2.define === 'function' && S2.define.amd ) {
          // AMD. Register as an anonymous module.
          S2.define('jquery-mousewheel',['jquery'], factory);
      } else if (typeof exports === 'object') {
          // Node/CommonJS style for Browserify
          module.exports = factory;
      } else {
          // Browser globals
          factory(jQuery);
      }
  }(function ($) {
  
      var toFix  = ['wheel', 'mousewheel', 'DOMMouseScroll', 'MozMousePixelScroll'],
          toBind = ( 'onwheel' in document || document.documentMode >= 9 ) ?
                      ['wheel'] : ['mousewheel', 'DomMouseScroll', 'MozMousePixelScroll'],
          slice  = Array.prototype.slice,
          nullLowestDeltaTimeout, lowestDelta;
  
      if ( $.event.fixHooks ) {
          for ( var i = toFix.length; i; ) {
              $.event.fixHooks[ toFix[--i] ] = $.event.mouseHooks;
          }
      }
  
      var special = $.event.special.mousewheel = {
          version: '3.1.12',
  
          setup: function() {
              if ( this.addEventListener ) {
                  for ( var i = toBind.length; i; ) {
                      this.addEventListener( toBind[--i], handler, false );
                  }
              } else {
                  this.onmousewheel = handler;
              }
              // Store the line height and page height for this particular element
              $.data(this, 'mousewheel-line-height', special.getLineHeight(this));
              $.data(this, 'mousewheel-page-height', special.getPageHeight(this));
          },
  
          teardown: function() {
              if ( this.removeEventListener ) {
                  for ( var i = toBind.length; i; ) {
                      this.removeEventListener( toBind[--i], handler, false );
                  }
              } else {
                  this.onmousewheel = null;
              }
              // Clean up the data we added to the element
              $.removeData(this, 'mousewheel-line-height');
              $.removeData(this, 'mousewheel-page-height');
          },
  
          getLineHeight: function(elem) {
              var $elem = $(elem),
                  $parent = $elem['offsetParent' in $.fn ? 'offsetParent' : 'parent']();
              if (!$parent.length) {
                  $parent = $('body');
              }
              return parseInt($parent.css('fontSize'), 10) || parseInt($elem.css('fontSize'), 10) || 16;
          },
  
          getPageHeight: function(elem) {
              return $(elem).height();
          },
  
          settings: {
              adjustOldDeltas: true, // see shouldAdjustOldDeltas() below
              normalizeOffset: true  // calls getBoundingClientRect for each event
          }
      };
  
      $.fn.extend({
          mousewheel: function(fn) {
              return fn ? this.bind('mousewheel', fn) : this.trigger('mousewheel');
          },
  
          unmousewheel: function(fn) {
              return this.unbind('mousewheel', fn);
          }
      });
  
  
      function handler(event) {
          var orgEvent   = event || window.event,
              args       = slice.call(arguments, 1),
              delta      = 0,
              deltaX     = 0,
              deltaY     = 0,
              absDelta   = 0,
              offsetX    = 0,
              offsetY    = 0;
          event = $.event.fix(orgEvent);
          event.type = 'mousewheel';
  
          // Old school scrollwheel delta
          if ( 'detail'      in orgEvent ) { deltaY = orgEvent.detail * -1;      }
          if ( 'wheelDelta'  in orgEvent ) { deltaY = orgEvent.wheelDelta;       }
          if ( 'wheelDeltaY' in orgEvent ) { deltaY = orgEvent.wheelDeltaY;      }
          if ( 'wheelDeltaX' in orgEvent ) { deltaX = orgEvent.wheelDeltaX * -1; }
  
          // Firefox < 17 horizontal scrolling related to DOMMouseScroll event
          if ( 'axis' in orgEvent && orgEvent.axis === orgEvent.HORIZONTAL_AXIS ) {
              deltaX = deltaY * -1;
              deltaY = 0;
          }
  
          // Set delta to be deltaY or deltaX if deltaY is 0 for backwards compatabilitiy
          delta = deltaY === 0 ? deltaX : deltaY;
  
          // New school wheel delta (wheel event)
          if ( 'deltaY' in orgEvent ) {
              deltaY = orgEvent.deltaY * -1;
              delta  = deltaY;
          }
          if ( 'deltaX' in orgEvent ) {
              deltaX = orgEvent.deltaX;
              if ( deltaY === 0 ) { delta  = deltaX * -1; }
          }
  
          // No change actually happened, no reason to go any further
          if ( deltaY === 0 && deltaX === 0 ) { return; }
  
          // Need to convert lines and pages to pixels if we aren't already in pixels
          // There are three delta modes:
          //   * deltaMode 0 is by pixels, nothing to do
          //   * deltaMode 1 is by lines
          //   * deltaMode 2 is by pages
          if ( orgEvent.deltaMode === 1 ) {
              var lineHeight = $.data(this, 'mousewheel-line-height');
              delta  *= lineHeight;
              deltaY *= lineHeight;
              deltaX *= lineHeight;
          } else if ( orgEvent.deltaMode === 2 ) {
              var pageHeight = $.data(this, 'mousewheel-page-height');
              delta  *= pageHeight;
              deltaY *= pageHeight;
              deltaX *= pageHeight;
          }
  
          // Store lowest absolute delta to normalize the delta values
          absDelta = Math.max( Math.abs(deltaY), Math.abs(deltaX) );
  
          if ( !lowestDelta || absDelta < lowestDelta ) {
              lowestDelta = absDelta;
  
              // Adjust older deltas if necessary
              if ( shouldAdjustOldDeltas(orgEvent, absDelta) ) {
                  lowestDelta /= 40;
              }
          }
  
          // Adjust older deltas if necessary
          if ( shouldAdjustOldDeltas(orgEvent, absDelta) ) {
              // Divide all the things by 40!
              delta  /= 40;
              deltaX /= 40;
              deltaY /= 40;
          }
  
          // Get a whole, normalized value for the deltas
          delta  = Math[ delta  >= 1 ? 'floor' : 'ceil' ](delta  / lowestDelta);
          deltaX = Math[ deltaX >= 1 ? 'floor' : 'ceil' ](deltaX / lowestDelta);
          deltaY = Math[ deltaY >= 1 ? 'floor' : 'ceil' ](deltaY / lowestDelta);
  
          // Normalise offsetX and offsetY properties
          if ( special.settings.normalizeOffset && this.getBoundingClientRect ) {
              var boundingRect = this.getBoundingClientRect();
              offsetX = event.clientX - boundingRect.left;
              offsetY = event.clientY - boundingRect.top;
          }
  
          // Add information to the event object
          event.deltaX = deltaX;
          event.deltaY = deltaY;
          event.deltaFactor = lowestDelta;
          event.offsetX = offsetX;
          event.offsetY = offsetY;
          // Go ahead and set deltaMode to 0 since we converted to pixels
          // Although this is a little odd since we overwrite the deltaX/Y
          // properties with normalized deltas.
          event.deltaMode = 0;
  
          // Add event and delta to the front of the arguments
          args.unshift(event, delta, deltaX, deltaY);
  
          // Clearout lowestDelta after sometime to better
          // handle multiple device types that give different
          // a different lowestDelta
          // Ex: trackpad = 3 and mouse wheel = 120
          if (nullLowestDeltaTimeout) { clearTimeout(nullLowestDeltaTimeout); }
          nullLowestDeltaTimeout = setTimeout(nullLowestDelta, 200);
  
          return ($.event.dispatch || $.event.handle).apply(this, args);
      }
  
      function nullLowestDelta() {
          lowestDelta = null;
      }
  
      function shouldAdjustOldDeltas(orgEvent, absDelta) {
          // If this is an older event and the delta is divisable by 120,
          // then we are assuming that the browser is treating this as an
          // older mouse wheel event and that we should divide the deltas
          // by 40 to try and get a more usable deltaFactor.
          // Side note, this actually impacts the reported scroll distance
          // in older browsers and can cause scrolling to be slower than native.
          // Turn this off by setting $.event.special.mousewheel.settings.adjustOldDeltas to false.
          return special.settings.adjustOldDeltas && orgEvent.type === 'mousewheel' && absDelta % 120 === 0;
      }
  
  }));
  
  S2.define('jquery.select2',[
    'jquery',
    'jquery-mousewheel',
  
    './select2/core',
    './select2/defaults'
  ], function ($, _, Select2, Defaults) {
    if ($.fn.select2 == null) {
      // All methods that should return the element
      var thisMethods = ['open', 'close', 'destroy'];
  
      $.fn.select2 = function (options) {
        options = options || {};
  
        if (typeof options === 'object') {
          this.each(function () {
            var instanceOptions = $.extend(true, {}, options);
  
            var instance = new Select2($(this), instanceOptions);
          });
  
          return this;
        } else if (typeof options === 'string') {
          var ret;
          var args = Array.prototype.slice.call(arguments, 1);
  
          this.each(function () {
            var instance = $(this).data('select2');
  
            if (instance == null && window.console && console.error) {
              console.error(
                'The select2(\'' + options + '\') method was called on an ' +
                'element that is not using Select2.'
              );
            }
  
            ret = instance[options].apply(instance, args);
          });
  
          // Check if we should be returning `this`
          if ($.inArray(options, thisMethods) > -1) {
            return this;
          }
  
          return ret;
        } else {
          throw new Error('Invalid arguments for Select2: ' + options);
        }
      };
    }
  
    if ($.fn.select2.defaults == null) {
      $.fn.select2.defaults = Defaults;
    }
  
    return Select2;
  });
  
    // Return the AMD loader configuration so it can be used outside of this file
    return {
      define: S2.define,
      require: S2.require
    };
  }());
  
    // Autoload the jQuery bindings
    // We know that all of the modules exist above this, so we're safe
    var select2 = S2.require('jquery.select2');
  
    // Hold the AMD module references on the jQuery function that was just loaded
    // This allows Select2 to use the internal loader outside of this file, such
    // as in the language files.
    jQuery.fn.select2.amd = S2;
  
    // Return the Select2 instance for anyone who is importing it.
    return select2;
  }));
  
jQuery(function() {
    jQuery('#formFactures').submit( function(event) {
	    event.preventDefault();
	    var errorMessage = '';
	    var isValid = true;
	    // Champs obligatoires
	    if ($('input#ref').val().length === 0 ||
	          $('input#year').val().length === 0 ||
	          $('input#amount').val().length === 0 ||
	          $('input#email').val().length === 0 ||
	          $('#type_facture')[0].selectedIndex === '') {
	      isValid = false;
	      errorMessage += 'Veuillez renseigner tous les champs du formulaire.<br/>';
	    } 
	    // Type de facture
	    var selectedFacture = $('#type_facture')[0].selectedIndex;
		var type_facture = $('#type_facture')[0].options[selectedFacture].value;
	    if (selectedFacture === '') {
	      isValid = false;
	      errorMessage += 'Veuillez choisir un type de facture.<br/>';
	    } 
	    // Numéro de commande
	    if ($('input#ref').val().length < 6 || ($('input#ref').val().length < 13 && type_facture === 'water')) {
	      isValid = false;
	      errorMessage += 'Le numéro de la facture est vide ou trop court.<br/>';
	    } 
	    // Année
	    if ($('input#year').val().length !== 4) {
	      isValid = false;
	      errorMessage += 'L\'année doit contenir 4 caractères numériques.<br/>';
	    }else{
		    if(new Date().getFullYear() - 1 !== parseInt($('input#year').val()) && new Date().getFullYear() !== parseInt($('input#year').val())) {
		      isValid = false;
		      errorMessage += 'L\'année doit correspondre à l\'année en cours ou à l\'année précédente.<br/>';
	  		}
	    }
	    // Montant supérieur à 0
	    if (parseInt($('input#amount').val()) === 0) {
	    	isValid = false;
	        errorMessage += 'Le montant à payer doit obligatoirement être supérieur à 0.<br/>';
	    }
	    // Email
	    if ($('input#email').val().length === 0) {
	      isValid = false;
	      errorMessage += 'Veuillez renseigner l\'adresse mail.<br/>';
	    }else{
		    if (!/^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,6}$/.test($('input#email').val())) {
		      isValid = false;
		      errorMessage += 'L\'adresse mail n\'est pas valide.<br/>';
		    } 
		}

	    if (isValid) {
		    jQuery('.errors').hide();

			var url = window.tipiURL;
			var appCode = "";
			var clientNumber = "";
			if (type_facture == "childhood") {
				appCode = "PF";
				clientNumber = "000696";
			} else if (type_facture == "schoolRestaurant") {
				appCode = "RS";
				clientNumber = "000696";
			} else if (type_facture == "afterSchool") {
				appCode = "GA";
				clientNumber = "000696";
			} else if (type_facture == "water") {
				appCode = "EA";
				clientNumber = "007964";
			}
		  	url += 'numcli=' + clientNumber;
			if (url.indexOf('saisie=T') === -1) { // En prod
				url += '&year=' + $('input#year').val();
				url += '&refdet=' + calculateRefdet($('input#year').val(), appCode, $('input#ref').val());
			} else { // En test
				url += '&year=9999';
				url += '&refdet=999999990000000000000';
			}

			url += '&montant=' + calculatePrice();
			url += '&mel=' + $('input#email').val();
			url += '&objet=' + appCode;
			url += '&urlcl=' + window.tipiCallbackURL;
			window.open(url,'_blank','height=750, width=1050, toolbar=no, menubar=no,scrollbars=yes, resizable=yes, location=no, directories=no, status=no'); 
			return false; 
	    } else {
	      jQuery('.errors').html(errorMessage).show();
	    }
    });
});

/**
 * Formatage du parametre "price" de l'url de paiement
 */
function calculatePrice() {
  var amount = $('input#amount').val().split('.');
  var integerPrice = amount[0];
  var decimalPrice = amount[1] ? amount[1] : '0';

  while (integerPrice.length < 2) {
    integerPrice = '0' + integerPrice;
  }
  if(decimalPrice.length > 2){
  	decimalPrice = decimalPrice.substr(0,2);
  }
  else{
	  while (decimalPrice.length < 2) {
	    decimalPrice = '0' + decimalPrice;
	  }
	}

  return integerPrice + decimalPrice;
}

/**
 * Formatage du paramètre "refdet" de l'url de paiement
 */
function calculateRefdet(year, appCode, ref) {
  var refdet ;
  refdet = year;
  refdet += appCode;
  refdet += '00';
  refdet += formatRef(ref);
  return refdet;
}

/**
 * Formatage du numero de facture
 */
function formatRef(ref) 
{ 
  while (ref.length < 13) {
    ref = '0' + ref;
  }

  return ref;
}
jQuery(function() {
    jQuery('#formSuivi').submit( function() {
    	var codeSuivi = jQuery('#codeSuivi').val();
    	var urlSuivi = jQuery('#serial [type=submit]').data('url-suivi').replace("[CODE_SUIVI]", codeSuivi);
      	window.location.replace(urlSuivi);
      	return false;
    });
});
(function($){
    $(document).ready(function(){

        $('.masonry-demarche').masonry({
            itemSelector: '.demarche-topic',
            gutter: 20,
            percentPosition: true,
            transitionDuration: '0.6s'
        });

    });
}(jQuery));

(function ($) {
   gaProperty = $('[data-site-id]'); // Récupérer le code GA (généré par le CMS, soit dans l'html soit en js)


    // Html qui va populer la div des cookies
    // var cnilBannerAskConsentString = 'En poursuivant votre navigation sur ce site, vous acceptez l\'utilisation de cookies pour réaliser des statistiques de visites anonymes.';
    // // var cnilBannerOptoutString = '<button type="button" class="close" data-dismiss="alert"><span class="sr-only">Fermer</span></button>Votre opposition au dépôt des cookies a bien été prise en compte.';

    // $(document).ready( function() {
    //     $('#cookies .text').html( cnilBannerAskConsentString );
    // });

    // Fermeture du bandeau
    $('#cookies .agree-button').on('click', function(){
        document.cookie = 'hasConsent=true; '+ getCookieExpireDate() +' ; path=/';
        $('#cookies').remove();
    });

    var disableStr = 'ga-disable-' + gaProperty;

    if (document.cookie.indexOf('hasConsent=false') > -1)
        window[disableStr] = true;

    function getCookieExpireDate()
    { 
        var cookieTimeout = 34214400000;
        var date = new Date();
        date.setTime(date.getTime()+cookieTimeout);
        var expires = "; expires="+date.toGMTString();
        return expires;
    }

    function askConsent()
    {
        $('#cookies').show();
    }

    function getCookie(NomDuCookie)
    {
        if (document.cookie.length > 0)
        {        
            begin = document.cookie.indexOf(NomDuCookie+"=");
            if (begin != -1)
            {
                begin += NomDuCookie.length+1;
                end = document.cookie.indexOf(";", begin);
                if (end == -1)
                    end = document.cookie.length;
                return unescape(document.cookie.substring(begin, end)); 
            }
        }
        return null;
    }
    
    // function delCookie(name )
    // {
    //     var path = ";path=" + "/";
    //     var expiration = "Thu, 01-Jan-1970 00:00:01 GMT";
    //     var domain=";domain=" + "."+document.location.hostname;
    //     document.cookie = name + "=" + path + domain + ";expires=" + expiration;
    //     domain=document.location.hostname;
    //     domain=";domain=" + "."+domain.substring(domain.lastIndexOf(".", domain.lastIndexOf(".") - 1) + 1);
    //     document.cookie = name + "=" + path + domain + ";expires=" + expiration;
    // }
    
    // function deleteAnalyticsCookies()
    // {
    //     var cookieNames = ["__utma","__utmb","__utmc","__utmz","_ga"]
    //     for (var i=0; i<cookieNames.length; i++)
    //         delCookie(cookieNames[i])
    // }

    // function gaOptout() {
    //     document.cookie = disableStr + '=true;'+ getCookieExpireDate() +' ; path=/';       
    //     document.cookie = 'hasConsent=false;'+ getCookieExpireDate() +' ; path=/';
        
    //     $('#cnil_banner_consent div').fadeOut( function() {
    //         $('#cnil_banner_consent').html( cnilBannerOptoutString );
    //         $('#cnil_banner_consent div').fadeIn();
    //     });
        
    //     window[disableStr] = true;
    //     deleteAnalyticsCookies();
    // }

    var consentCookie =  getCookie('hasConsent');
    if (consentCookie == null)
    {
        var referrer_host = document.referrer.split('/')[2]; 
        if ( referrer_host != document.location.hostname )
        {        
            window[disableStr] = true;
            window[disableStr] = true;
            $(document).ready( function() {
                askConsent();
            });
        }
        else
            document.cookie = 'hasConsent=true; '+ getCookieExpireDate() +' ; path=/'; 
    }
}(jQuery));



(function ($) {
    $(document).ready( function() {
        $( '#skipnavigation ul li a' ).focus( function() {
            $( '#skipnavigation' ).removeClass( 'sr-only' );
        }).blur( function() {
            $( '#skipnavigation' ).addClass( 'sr-only' );
        });
    });
}(jQuery));

function destroyPopin(){
    $('#favConfirm').remove().off('clickfavConfirm');
    $('.mseu').off('click.favconfirm').removeClass('overlayed');
}
function createPopin(message, agree, deny){
    var template = '<div id="favConfirm"> \
        <div class="favMessage">##favMessage##</div> \
        <div class="favActions"> \
            <button class="btn-square--bordered--core deny"><span class="flexbox"><span class="btn-text">Annuler</span><span class="btn-arrow"></span></span></button> \
            <button class="btn-square--filled--second confirm"><span class="flexbox"><span class="btn-text">Valider</span><span class="btn-arrow"></span></span></button> \
        </div> \
    </div>';

    template = template.replace('##favMessage##', message);
    $('body').append(template);
    $('.mseu').addClass('overlayed');


    $('#favConfirm .deny').on('click.favConfirm', function(e){
        if(deny !== undefined){
            deny();
        }
        destroyPopin();
    });
    $('#favConfirm .confirm').on('click.favConfirm', function(){
        destroyPopin();
        if(agree !== undefined){
            agree();
        }
    });
}
$(document).ready(function(){
    $('.toCustomSelect, .SingleSelectHintWidget select').customSelect();
}); 

$(document).ready(function(){
    if($('input[type="date"]').length || $('input[data-type="date"]').length){
        $('input[type="date"], input[data-type="date"]').datepicker($.datepicker.regional[ "fr" ]);
    }
});
(function ($) {
	$(document).ready(function() {
		$('.webform-client-form.toValidate, .generic-form.toValidate').each(function(){
			$(this).validate({
				lang: 'fr',
				errorElement: 'span',
				errorPlacement : function(error, element){
					error.appendTo( element.parents('.form-field') );
				},
				ignore: []
			});
		});
		

		$('input[type="date"], input[data-type="date"]').datepicker($.datepicker.regional[ "fr" ]);
	}); 
}(jQuery));

(function ($) {
    $(document).ready(function(){
        var $alertSlider = $('#alert-slider');
        $alertSlider.find('.alert-list').addClass('owl-carousel').owlCarousel({
            items: 1,
            nav: true,
            loop: true,
            animateIn: 'slideInUp',
            animateOut: 'fadeOutUp',
            smartSpeed:450
        });

    });
 }(jQuery));

/**
 * MegaSlider
 * 
 * @description - Affiche X slider(s) comportant plusieurs éléments par pages, triable par catégorie. Un slider est construit automatiquement à partir d'un flux de donnée en JS au format simple:
 *  sources = [
 *  {
 *      "category": "macatégorie",
 *      "info en tout genre": "moninfo",
 *      etc...
 *  },
 * etc...
 * ]
 * 
 * A besoin pour fonctionner:
 *      - une liste d'objets de configuration
 *      - X flux de données à afficher, stocké(s) dans un objet JS (exemple ici mega_source_...  stockés dans index.html)
 *      - L'architecture html du slider
 *      - Un lot de template pour chaque catégorie d'item présent dans le slider, contenant des placeholders ##infosàremplacer## pour chaque entrée du flux de donnée (ici stockés dans index.html)
 *
 * @Lancement:
 *      - lancer d'abord getSources(monObjetSource, monObjetDeConfiguration); (à ne faire qu'une fois)
 *      - appeler megaSlider(monObjetDeConfiguration, maCategorie);
 *      - Ajouter les évènements au click sur les filtres de catégorie (optionnel)
 */


/**
 * Configuration object 
 */
$(document).ready(function(){
    var list_mega = [];
    $('.slider--mega').each(function(index, element) {

        list_mega[index] = {
            can_animate: true,
            $slider: $(element).find('.slider'),
            $prev: $(element).find('.owl-prev'),
            $next: $(element).find('.owl-next'),
            $pages: $(element).find('.page'),
            pages_class: 'page',
            hasPicture_class: 'has-picture',
            hasVille_class: 'has-ville',
            is_Big_Class: 'big',
            $items: $(element).find('.item'),
            $filters: $(element).find('.actu-filter'),
            $template_agenda: $(element).find('.event'),
            $template_actu: $(element).find('.actu'),
            $template_mag: $(element).find('.mag'),
            $loader: $(element).find('#mega-loader')
        }

        // Initialisation slider mega 
        getSources(mega_source[index], list_mega[index]);
        megaSlider(list_mega[index], 'tous');

        // Gestion des filtres slider mega
        list_mega[index].$filters.on('click', function(e){
            if(list_mega[index].can_animate){
                list_mega[index].can_animate = false;
                var $filter = $(this),
                    category = $filter.attr('data-category');
                list_mega[index].$filters.removeClass('actif');
                $filter.addClass('actif');
                list_mega[index].$slider.addClass('animate-out');
                setTimeout(function(){
                    megaSlider(list_mega[index], category);
                    list_mega[index].$slider.removeClass('animate-out');
                    list_mega[index].can_animate = true;
                }, 800);
            }
        });
    });

    // Reload des slider au resize pour le RWD
    $(window).resize(function(){
        if(environmentChanged){
            $('.slider--mega').each(function(index, element) {
                megaSlider(list_mega[index], 'tous');
            })
        }
    });
});



/**
 * Suite d'appels permettant de construire un mega-slider à la une en fonction d'une catégorie
 * @param {string} category - Catégorie à afficher
 */
function megaSlider(slider, category){
    destroySlider(slider);
    removeAllItems(slider);
    majItems(category, slider);
    buildSlider(slider);
    dot();
}

(function ($) {
    
    $(document).ready(function(){
        if($('.notification-list').length){
            $list = $('.notification-list'),
            $inputs = $list.find('input[type="checkbox"]');

            $inputs.on('change', function(){
                $notifAmount = $('.notif-amount')[0];
                var $item = $(this).closest('.notification-list__item');
                if(!$item.hasClass('notification-list__item--read')){
                    $item.addClass('notification-list__item--read');
                    if (Number($notifAmount.innerText)-1 == 0) {
                        $notifAmount.remove();
                    } else {
                        $notifAmount.innerText =Number($notifAmount.innerText)-1;
                    }
                }else{
                    $item.removeClass('notification-list__item--read');
                    if ($notifAmount == undefined) {
                        var iDiv = document.createElement('div');
                        iDiv.innerText = 1;
                        iDiv.className = 'notif-amount';
                        $('.notif-picto')[0].appendChild(iDiv);
                    } else {
                        $notifAmount.innerText =Number($notifAmount.innerText)+1;
                    }
                }
            });
        }
    });
}(jQuery));
(function ($) {

    $(document).ready(function(){
        var $notif = $('.mseu #nav-notifications');
        var $notifTrigger = $('.mseu #trigger-notifications');

        var $menu = $('.mseu #nav-menu');
        var $menuTrigger = $('.mseu #trigger-menu');  
        
        $(document).on('navside:open', function(){
            $(document).on('click.navSide', function(e){
                if(!$(e.target).parents('#nav-side').length){
                    $menu.removeClass('opened');
                    $notif.removeClass('opened');
                    $('html.mseu').removeClass('no-scroll overlayed');
                }
            });
        }).on('navside:close', function(){
            $(document).off('click.navSide');
        });

        function attachNavEvents(){
            if(environment != 'desktop' || windowHeight < 750 || $('html.mseu').hasClass('mobile') || $('html.mseu').hasClass('tablet')){
                $menu
                .off('mouseleave mouseenter focus click')
                .on('click', function(e){
                    if($menu.hasClass('opened')){
                        $menu.removeClass('opened');
                        $('html.mseu').removeClass('no-scroll overlayed');
                        $(document).trigger('navside:close');
                    }else{
                        e.preventDefault();
                        $notif.removeClass('opened');
                        $menu.addClass('opened');
                        $('html.mseu').addClass('no-scroll overlayed');
                        $(document).trigger('navside:open');
                    }
                });

                $notif
                .off('mouseenter mouseleave focus click')
                .on('click', function(){
                    if($notif.hasClass('opened')){
                        $notif.removeClass('opened');
                        $('html.mseu').removeClass('no-scroll overlayed');
                        $(document).trigger('navside:close');
                    }else{
                        $menu.removeClass('opened');
                        $notif.addClass('opened');
                        $('html.mseu').addClass('no-scroll overlayed');
                        $(document).trigger('navside:open');
                    }
                });
            }else{
                $menu.on('mouseenter', function(){
                    $menu.addClass('opened');
                    $notif.removeClass('opened');
                    $(document).trigger('navside:open');
                })
                .on('mouseleave', function(){
                    $menu.removeClass('opened');
                    $(document).trigger('navside:close');
                });

                $notif.on('mouseenter focus', function(){
                    $notif.addClass('opened');
                    $menu.removeClass('opened');
                    $(document).trigger('navside:open');
                })
                .on('mouseleave blur', function(){
                    $notif.removeClass('opened');
                    $(document).trigger('navside:close');
                });
            }
        }
        attachNavEvents();
        $(window).resize(function(){
            attachNavEvents();
        });
    });
}(jQuery)); 

$(function() {
    // Si l'utilisateur a un favoris à ajouté via son sessionStorage, on l'ajoute
    var favoriteToAdd = window.sessionStorage.getItem("favorite");
    if (favoriteToAdd && favoriteToAdd.length > 0) {
        favoriteToAdd = JSON.parse(favoriteToAdd),
        Liferay.Service(
            '/favorite.favorite/add-favorite-link',
            favoriteToAdd,
            function(obj) {
                if (obj.hasOwnProperty('success')) {
                    var favoriteButton = $('[data-type=' + favoriteToAdd.typeId + '][data-id=' + favoriteToAdd.entityId + ']');
                    if(favoriteButton.length > 0) {
                        favoriteButton.addClass('liked');
                        favoriteButton[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
                    }
                    window.sessionStorage.setItem("favorite", "");
                }
            }
        );
    }

    // On parcourt les favoris utilisateurs et on modfie les boutons correspondant sur la page
    if (window.userFavorites) {
        var i;
        for (i = 0; i < window.userFavorites.length; i++) {
            var favoriteButton = $('[data-type=' + window.userFavorites[i].typeId + '][data-id=' + window.userFavorites[i].entityId + ']')
            if (favoriteButton.length > 0) {
                favoriteButton.addClass('liked');
                favoriteButton[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
            }
        }
    }

/*
    var elements = $('.seu-add-favorites, .add-favorites, .item-misc');
    if (elements) {
        elements.each(function(i) {
            var favorite = $(this);
            if (window.userFavorites) {
                $.each(window.userFavorites, function(index, value) {
                    if (favorite.data("id") == this.entityId) {
                        favorite[0].classList.add('liked'); // TO SIMPLIFY
                        favorite[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
                    }
                });
            }
        });
    }
*/
});

$(function() {
    // Lors du clic sur un bouton "ajouter aux favoris"
    $(document).on("click", '.seu-add-favorites, .add-favorites, .item-misc', function(e) {
        e.preventDefault();
        e.stopPropagation();

        var htmlA = $(this);
        var url = $(this).data("url");
        var id = $(this).data("id");
        var groupId = $(this).data("groupId") ? $(this).data("groupId") : 0;
        var type = $(this).data("type");
        var title = $(this).data("title");

        // Si le favoris a déjà été ajouté par l'utilisateur
        if (htmlA[0].classList.contains('liked')) {
            // On appelle le WS pour supprimer le favoris
            Liferay.Service(
                '/favorite.favorite/delete-favorite-link', {
                    title: title,
                    url: url,
                    typeId: type,
                    entityId: id
                },
                function(obj) {
                    // En cas de succès, on modifie le bouton
                    if (obj.hasOwnProperty('success')) {
                        htmlA[0].classList.remove('liked');
                        htmlA[0].children[0].textContent = Liferay.Language.get('eu.add-to-favorite');
                    }
                    // Sinon on affiche un message d'erreur
                    else if (obj.hasOwnProperty('error')) {
                        if (obj['error'] == 'notConnected')
                            // Si l'utilisateur n'est pas connecté
                            window.createPopin('Veuillez vous connecter pour retirer un favori.');
                        else {
                            // Autre erreur
                            console.log(obj['error']);
                            window.createPopin('Une erreur est survenue.');
                        }
                    }
                }
            );
        } else {
            // Sinon appel du WS pour ajouter un favoris
            var favoriteToAdd = {
                title: title,
                url: url,
                typeId: type,
                entityId: id,
                entityGroupId: groupId
            };
            Liferay.Service(
                '/favorite.favorite/add-favorite-link',
                favoriteToAdd,
                function(obj) {
                    if (obj.hasOwnProperty('success')) {
                        htmlA[0].classList.add('liked');
                        htmlA[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
                    } else if (obj.hasOwnProperty('error')) {
                        if (obj['error'] == 'notConnected')
                        window.createPopin('Veuillez vous connecter pour ajouter un favori.', function() {
                            // Si l'utilisateur n'est pas connecté, on ajoute à son LocalStorage le favoris
                            // On l'ajoutera la prochaine fois qu'il arrive sur la page en étant connecté
                            window.sessionStorage.setItem("favorite", JSON.stringify(favoriteToAdd));
                            window.location = window.loginURL;
                        }, undefined, 'Se connecter', 'Annuler');
                        else {
                            console.log(obj['error']);
                            window.createPopin('Une erreur est survenue.');
                        }
                    }

                }
            );
        }
    });
});
(function ($) {
	if ($('.front').length) {
		$(document).ready(function() {
			// Event click sur le bouton scroll To en hp
			$('#banner .scrollTo').on('click', function(){
				if(canScrollMagic){
					$('html, body').animate({
						scrollTop: $('#une').offset().top - 150
					}, 1000);
				}else{
					$('html, body').animate({
						scrollTop: $('#une').offset().top + 1
					}, 1000);
				}
			});
			
			
		});

		$(window).load(function(){
			
		});
	};
}(jQuery));


$('.no-js').removeClass('no-js');
function dot(){
	(function ($) {
		$('[data-dot]').each(function(){
			var lines = $(this).attr('data-dot');
			if(lines == ''){
				$(this).dotdotdot({wrap: 'word', watch: true});
			}else{
				var height = $(this).css('line-height');
				height = lines * parseInt(height);
				height += 2;
				$(this).dotdotdot({wrap: 'word', watch: true, height: height});
			}
		});
	}(jQuery));
}
(function ($) {
	function manageStickyHeader(){
		if($('.front').length){
			if($(window).scrollTop() > 0){
				$('header').addClass('scrolled');
			}else{
				$('header').removeClass('scrolled');
				$('.scrolled-search-engine').slideUp();
			}
			if($(window).scrollTop() > ($('#banner').height())){
				$('header').addClass('scrolled-hp');
			}else{
				$('header').removeClass('scrolled-hp');
				$('.scrolled-search-engine').slideUp();
			}
		}else{
			$('header').addClass('scrolled');
			$('header').addClass('scrolled-hp');
		}
	}
	$(document).ready(function() {
		// INIT
		$('select[multiple="multiple"]').each(function(index, element){
			var placeholder = $(element).find('option[disabled]').text();
			$(element).select2({
				placeholder: placeholder,
				closeOnSelect: false
			});
		})
		
		dot();
		// Menu Lang
		$('.lang-list').on('click', function(){
			$(this).toggleClass('open');
		});
		// RESIZE
			$(window).resize(function(){
				if (environmentChanged) {
					dot();
				}
			});
			
	}); // End onReady  

	$(window).load(function(){
		manageStickyHeader();
		$(window).scroll(function(){
			manageStickyHeader();
		});
	}) //End onLoad
}(jQuery));

(function ($) {
    /*! device.js 0.2.7 */
    (function(){var a,b,c,d,e,f,g,h,i,j;b=window.device,a={},window.device=a,d=window.document.documentElement,j=window.navigator.userAgent.toLowerCase(),a.ios=function(){return a.iphone()||a.ipod()||a.ipad()},a.iphone=function(){return!a.windows()&&e("iphone")},a.ipod=function(){return e("ipod")},a.ipad=function(){return e("ipad")},a.android=function(){return!a.windows()&&e("android")},a.androidPhone=function(){return a.android()&&e("mobile")},a.androidTablet=function(){return a.android()&&!e("mobile")},a.blackberry=function(){return e("blackberry")||e("bb10")||e("rim")},a.blackberryPhone=function(){return a.blackberry()&&!e("tablet")},a.blackberryTablet=function(){return a.blackberry()&&e("tablet")},a.windows=function(){return e("windows")},a.windowsPhone=function(){return a.windows()&&e("phone")},a.windowsTablet=function(){return a.windows()&&e("touch")&&!a.windowsPhone()},a.fxos=function(){return(e("(mobile;")||e("(tablet;"))&&e("; rv:")},a.fxosPhone=function(){return a.fxos()&&e("mobile")},a.fxosTablet=function(){return a.fxos()&&e("tablet")},a.meego=function(){return e("meego")},a.cordova=function(){return window.cordova&&"file:"===location.protocol},a.nodeWebkit=function(){return"object"==typeof window.process},a.mobile=function(){return a.androidPhone()||a.iphone()||a.ipod()||a.windowsPhone()||a.blackberryPhone()||a.fxosPhone()||a.meego()},a.tablet=function(){return a.ipad()||a.androidTablet()||a.blackberryTablet()||a.windowsTablet()||a.fxosTablet()},a.desktop=function(){return!a.tablet()&&!a.mobile()},a.television=function(){var a;for(television=["googletv","viera","smarttv","internet.tv","netcast","nettv","appletv","boxee","kylo","roku","dlnadoc","roku","pov_tv","hbbtv","ce-html"],a=0;a<television.length;){if(e(television[a]))return!0;a++}return!1},a.portrait=function(){return window.innerHeight/window.innerWidth>1},a.landscape=function(){return window.innerHeight/window.innerWidth<1},a.noConflict=function(){return window.device=b,this},e=function(a){return-1!==j.indexOf(a)},g=function(a){var b;return b=new RegExp(a,"i"),d.className.match(b)},c=function(a){var b=null;g(a)||(b=d.className.replace(/^\s+|\s+$/g,""),d.className=b+" "+a)},i=function(a){g(a)&&(d.className=d.className.replace(" "+a,""))},a.ios()?a.ipad()?c("ios ipad tablet"):a.iphone()?c("ios iphone mobile"):a.ipod()&&c("ios ipod mobile"):a.android()?c(a.androidTablet()?"android tablet":"android mobile"):a.blackberry()?c(a.blackberryTablet()?"blackberry tablet":"blackberry mobile"):a.windows()?c(a.windowsTablet()?"windows tablet":a.windowsPhone()?"windows mobile":"desktop"):a.fxos()?c(a.fxosTablet()?"fxos tablet":"fxos mobile"):a.meego()?c("meego mobile"):a.nodeWebkit()?c("node-webkit"):a.television()?c("television"):a.desktop()&&c("desktop"),a.cordova()&&c("cordova"),f=function(){a.landscape()?(i("portrait"),c("landscape")):(i("landscape"),c("portrait"))},h=Object.prototype.hasOwnProperty.call(window,"onorientationchange")?"orientationchange":"resize",window.addEventListener?window.addEventListener(h,f,!1):window.attachEvent?window.attachEvent(h,f):window[h]=f,f(),"function"==typeof define&&"object"==typeof define.amd&&define.amd?define(function(){return a}):"undefined"!=typeof module&&module.exports?module.exports=a:window.device=a}).call(this);
}(jQuery));


(function ($) {
    $(document).ready(function(){
        if($('.mseu body.front').length){
            $('.delete-wi').on('click', function(){

                var $section = $(this).closest('section');
                var message = "Êtes vous sur de vouloir fermer le panneau ?";
                var serveURL = this.value;
                var agree = function(){
                    $section.addClass('deleted');

                    AUI().use('aui-io-request', function(A) {
                        A.io.request(serveURL, {
                            method : 'post'
                        });
                    });

                    setTimeout(function(){
                        $section.slideUp();
                    },100);
                };
                createPopin(message, agree);
            });
        }
    });
}(jQuery));
 
define.amd = define._amd;