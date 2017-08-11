;(function ($) {

	function slideMenu(element, parameters){
		var slidemenu = this;
		slidemenu.parameters = parameters;
		slidemenu.element = $(element);
		slidemenu.offsetTop = slidemenu.element.position().top;
		slidemenu.panels = slidemenu.element.find('[data-sl-panel]');
		slidemenu.init();
	}
	slideMenu.prototype = {
		init: function(){
			slidemenu = this;
			// Set wrapper
			slidemenu.element.wrap('<div class="sl-wrapper"></div>');

			// Init Panels
			slidemenu.panels.each(function(){
				// if ($(this).attr('data-sl-deep') == 1) {
				// 	$(this).css('top',slidemenu.offsetTop+'px');
				// }else{
					$(this).css('top','0px');
				// }
				
			})

			// Set compatibility with sidy.js
			if (slidemenu.parameters.sidyCompatible) {
				$('.sidy__panel').css('overflow', 'hidden');
			};

			// Event Listeners
			// Open
			slidemenu.element.find('a[data-sl-target]').on('click', function(e){
				var id = $(this).attr('data-sl-target');
				if ($('[data-sl-panel="'+id+'"]').length) {
					e.preventDefault();
					slidemenu.openPanel(e, id, 'no options');
				}
			})
			// Close
			slidemenu.element.find('[data-sl-close]').on('click', function(e){
				var id = $(this).attr('data-sl-close');
				if ($('[data-sl-panel="'+id+'"]').length) {
					e.preventDefault();
					slidemenu.closePanel(e, id, 'no options');
				}else{
					console.log('slideMenu error: no panel with id'+ id+' (closing)');
				}
			});
		},
		openPanel: function(event, panelId, options){
			slidemenu = this;
			// Some magic with disabling multiple events.
            event.stopPropagation();
            event.preventDefault();

            var $panel = $('[data-sl-panel="'+panelId+'"]');
            $panel.addClass('opened');
		},
		closePanel: function(event, panelId, options){
			slidemenu = this;
			// Some magic with disabling multiple events.
            event.stopPropagation();
            event.preventDefault();

            var $panel = $('[data-sl-panel="'+panelId+'"]');
            $panel.removeClass('opened');
		}
	}

	$.fn.slideMenu=function(options){
		var defaults = {
			sidyCompatible: true
		};
		var parameters = $.extend(defaults, options);

		return this.each(function(){
			var el = this;
			this.offsetTop = $(this).offset().top;
			if ( !$.data( this, "plugin_slideMenu" ) ) {
				$.data( this, "plugin_slideMenu", new slideMenu(this, parameters));
			}
		})
	};
})(jQuery);


