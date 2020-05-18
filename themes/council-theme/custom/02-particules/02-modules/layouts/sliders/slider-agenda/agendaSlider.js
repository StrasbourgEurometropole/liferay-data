(function ($) {
    $(document).ready(function(){
        if($('.seu-agenda-slider-container').length) {
            $('.seu-agenda-slider-container .seu-slider').addClass('owl-carousel');

            var agendaSliders = $('.seu-agenda-slider-container');
            agendaSliders.each(function(index, element) {      
                
                var $slider = $(element).find('.owl-carousel');
                var $prev = $(element).find('.seu-owl-prev');
                var $next = $(element).find('.seu-owl-next'); 

                $('.seu-agenda-slider-container .owl-carousel').owlCarousel({
                    responsiveBaseElement: '.seu-container-left',
                    margin:10,
                    nav: false, 
                    mouseDrag: false,
                    smartSpeed: 800,
                    responsive: {
                        0: {
                            items: 1,
                            stagePadding: 60,
                        },
                        768: {
                            items: 2,
                            stagePadding: 100,
                        }
                    },
                    onInitialized: function(e){
                        manageNavigationDisplay(e, $prev, $next);
                        attachCustomNavEvents($slider, $prev, $next);
                    },
                    onTranslate : function(e){
                        manageNavigationDisplay(e, $prev, $next);
                    }
                });
            });
        }
    });
}(jQuery));