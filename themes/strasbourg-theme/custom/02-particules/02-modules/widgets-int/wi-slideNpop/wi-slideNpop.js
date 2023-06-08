(function ($) {
    $(document).ready(function(){
        if($('.seu-wi-slideNpop').length){

            $('.seu-wi-slideNpop').each(function(index, element){        
                $(this).find('.slide .slider').clone().appendTo($(this).find('.pop-area')); // Duplique le slider pour le slider full "pop"

                var snp = {
                    $slide: $(this).find('.slide'),
                    $pop: $(this).find('.pop'),

                    $slide_slider: $(this).find('.slide .slider'),
                    $slide_prev: $(this).find('.slide .seu-owl-prev'),
                    $slide_next: $(this).find('.slide .seu-owl-next'),

                    $pop_slider: $(this).find('.pop .slider'),
                    $pop_prev: $(this).find('.pop .seu-owl-prev'),
                    $pop_next: $(this).find('.pop .seu-owl-next'),
                    $pop_current_count: $(element).find('.pop-current'),
                    $pop_total_count: $(element).find('.pop-total'),
                    $pop_title: $(element).find('.pop-title'),
                    $pop_description: $(element).find('.pop-description'),
                    
                    majPopInfos: function(index, count, snp){ // Maj le titre/description/compteur
                        snp.$pop_title.text(snp.$pop_slider.find('.owl-item:eq('+index+') li').attr('data-title'));
                        snp.$pop_description.text(snp.$pop_slider.find('.owl-item:eq('+index+') li').attr('data-description'));
                        snp.$pop_current_count.text(index+1);
                        snp.$pop_total_count.text(count);
                    },

                    slide_config: {
                        items: 1,
                        nav: false,
                        smartSpeed: 1000,
                        mouseDrag: false,
                        onInitialized: function(e){ 
                            manageNavigationDisplay(e, snp.$slide_prev, snp.$slide_next);
                            attachCustomNavEvents(snp.$slide_slider, snp.$slide_prev, snp.$slide_next);
                            // Attach pop event
                            snp.$slide_slider.find('.owl-item li').on('click', function(){
                                var index = $(this).parent().index();
                                snp.majPopInfos(index, snp.$pop_slider.find('.owl-item').length, snp);
                                snp.$pop_slider.trigger('to.owl.carousel', [index, 0]);
                                var fake_event = {
                                    "item": {
                                        "index": index,
                                        "count": snp.$pop_slider.find('.owl-item').length
                                    }
                                };
                                manageNavigationDisplay(fake_event, snp.$pop_prev, snp.$pop_next);
                                setTimeout(function(){
                                    snp.$pop.addClass('opened');
                                    snp.$pop_slider.trigger('refresh.owl.carousel'); // Force le resize d'une façon bizarre pour la maj des dimensions
                                    $('html').addClass('seu-no-scroll');
                                }, 20);
                            });
                        },
                        onTranslate : function(e){
                            manageNavigationDisplay(e, snp.$slide_prev, snp.$slide_next);
                        }
                    },

                    pop_config: {
                        items: 1,
                        smartSpeed: 1,
                        nav: false,
                        mouseDrag: false,
                        onInitialized: function(e){
                            manageNavigationDisplay(e, snp.$pop_prev, snp.$pop_next);
                            attachCustomNavEvents(snp.$pop_slider, snp.$pop_prev, snp.$pop_next);
                        },
                        onTranslated: function(e){
                            snp.majPopInfos(e.item.index, snp.$pop_slider.find('.owl-item').length, snp);
                        },
                        onTranslate : function(e){
                            manageNavigationDisplay(e, snp.$pop_prev, snp.$pop_next);
                        },
                        onRefresh: function(e){
                            manageNavigationDisplay(e, snp.$pop_prev, snp.$pop_next);
                        }
                    }
                };

                
                // Gestion de la partie "slide"
                    snp.$slide_slider.addClass('owl-carousel').owlCarousel(snp.slide_config);

                // Gestion de la partie "pop"
                    snp.$pop_slider.addClass('owl-carousel').owlCarousel(snp.pop_config);
                    snp.majPopInfos(0, snp.$pop_slider.find('.owl-item').length, snp);
                    snp.$pop.find('.pop-close').on('click', function(){ // Fermeture du pop
                        $('html').removeClass('seu-no-scroll');
                        snp.$pop.removeClass('opened');
                    });
            });

        }
    })

 }(jQuery));