(function ($) {
    $(document).ready(function(){
        if($('.seu-slider-int-container').length){
            $('.seu-slider-int-container .seu-slider').addClass('owl-carousel');

            var sliders_int = $('.seu-slider-int-container');
            sliders_int.each(function(index, element){        

                // Init Class custom
                var $slider = $(element).find('.owl-carousel');
                var $prev = $(element).find('.seu-owl-prev');
                var $next = $(element).find('.seu-owl-next'); 
 
                // Init Addons     
                var config_slider = {
                    margin : 20,
                    items: 1, 
                    nav: false, 
                    mosueDrag: false,
                    smartSpeed: 800,
                    onInitialized: function(e){
                        manageNavigationDisplay(e, $prev, $next);
                        attachCustomNavEvents($slider, $prev, $next);
                    },
                    onTranslate : function(e){
                        manageNavigationDisplay(e, $prev, $next);
                    }
                };

                // Build Slider
                $slider.owlCarousel(config_slider);
            });
        }
    })
}(jQuery));
