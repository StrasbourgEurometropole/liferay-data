(function ($) {
    $(function () {
        if ($('.slider-une').length) {

            $('.slider-une').each(function (index, element) {

                // Initialisation des variables globales et éléments de dom
                var s = {};
                s.$root = $(this);
                s.name = 'slider-une';
                s.main = {};
                s.main.name = s.name + '-main';
                s.main.$root = s.$root.find('.'+s.main.name);
                s.main.$slider = s.main.$root.find('.'+s.main.name + '__slider');
                s.main.$slider_item = s.main.$slider.find('.'+s.main.name + '__slider-item');
                // s.hasSpeClass = s.$root.hasClass('spe');    @extend du slider pour un autre slider spé

                if (s.main.$slider_item.length > 1) { // S'il ny a qu'une slide on fait rien

                    /* Slider main : configuration
                    ============================= */


                        // Ajout d'un data-index sur tous les <li>
                        s.main.$slider_item.each(function (index, element) {
                            $(this).attr('data-index', index);
                        });

                        s.main.can_animate = true;  // Ne pas toucher
                        s.main = $.extend(s.main, {
                            // Configuration owl, voir doc en ligne https://owlcarousel2.github.io/OwlCarousel2/docs/api-options.html
                            conf: {
                                animateOut: 'customOut', // customOut,  false ou classe de animate.css
                                animateIn: 'customIn', // customIn, false ou classe de animate.css
                                items: 1,
                                nav: false,
                                loop: false,
                                smartSpeed: 250,
                                mouseDrag: false
                            },

                            /*
                                @extend du slider pour un autre slider spé, exemple de condition dans la configuration des sliders
                            conf: function(){
                                var conf = {
                                    animateOut: 'customOut', // customOut,  false ou classe de animate.css
                                    animateIn: 'customIn', // customIn, false ou classe de animate.css
                                    items: 1,
                                    nav: false,
                                    loop: true,
                                    smartSpeed: 500,
                                    mouseDrag: false
                                }
                                if(s.hasSpeClass){
                                    return $.extend(conf, {
                                        "items": 3
                                    })
                                }else{
                                    return conf;
                                }
                            }
                            */

                            // Arrows
                            arrow: {
                                has_arrow: false,
                                $prev: s.main.$root.find('.'+s.main.name + '__arrow--prev'),
                                $next: s.main.$root.find('.'+s.main.name + '__arrow--next')
                            },

                            // Pagers
                            pager: {
                                has_pager: false,
                                $current: s.main.$root.find('.'+s.main.name + '__pager--current'),
                                $total: s.main.$root.find('.'+s.main.name + '__pager--total'),
                                $prev: s.main.$root.find('.'+s.main.name + '__pager--prev'),
                                $next: s.main.$root.find('.'+s.main.name + '__pager--next')
                            },

                            // Dots
                            dot: {
                                has_dot: false,
                                $dots: s.main.$root.find('.'+s.main.name + '__dots'),
                                $dot: s.main.$root.find('.'+s.main.name + '__dot').remove()
                            },

                            // Autoplay /!\ need loop à true
                            autoplay: {
                                has_autoplay: false,
                                $playpause: s.main.$root.find('.'+s.main.name + '__playpause'),
                                start_autoplay: false,
                                interval: '', // Ne pas modifier
                                timeout: 2000
                            },

                            // Texte dynamique des slides
                            text: {
                                has_text: true,
                                $root: s.main.$root.find('.'+s.main.name + '__text'),
                                infos: [{
                                    data: "data-title",
                                    $selector: s.main.$root.find('.'+s.main.name + '__title'),
                                    type: "text"
                                }, {
                                    data: "data-description",
                                    $selector: s.main.$root.find('.'+s.main.name + '__description'),
                                    type: "text"
                                }, {
                                    data: "data-link",
                                    $selector: s.main.$root.find('.'+s.main.name + '__link'),
                                    type: "href"
                                }
                            ]
                            },

                            // Animation
                            animation: {
                                css_custom: true,
                                timeout: 125  // Temps de l'animation CSS, need en JS pour setTimeout sur la maj du texte
                            }
                        });

                    //


                    /* SlideNpop: configuration
                    ============================= */
                        s.slideNpop = {
                            has_slideNpop: false,
                            can_animate: true,  // Ne pas toucher
                            slide_slider_type: 'grid', // same, new, grid
                            name: s.name + '-slideNpop',
                            $slider_pop_close: $('<button class="'+s.main.name+'__close"></button>'),
                            conf: { // Utile qu'avec le type "new"
                                items: 1,
                                margin: 10,
                                nav: false,
                                smartSpeed: 500,
                                mouseDrag: true,
                                center: true,
                                loop: true,
                                slideBy: 2
                            }
                        };


                        // Event mode grid
                        s.$root.on('slideNpop:grid:open', function (e, datas) {
                            // Quand le slider slideNpop s'ouvre après un clic sur un item de la grid
                        });

                    // 

                    /* Thumbnail : configuration
                    ============================= */
                        s.thumbnail = {
                            has_thumbnail: true,
                            name: s.name + '-thumbnail',
                            can_animate: true, // Ne pas toucher
                            conf : {
                                items: 1,
                                margin: 20,
                                nav: false,
                                smartSpeed: 250,
                                mouseDrag: true,
                                center: false, 
                                loop: false,
                                slideBy: 1,
                                responsive: {
                                    0: {
                                        items: 1
                                    },
                                    768: {
                                        items: 2
                                    },
                                    1280: {
                                        items: 4,
                                        center: true //Liasser a true sur ce slider sinon ça foire grave
                                    }
                                }
                            }
                        };

                    // 

                    /* Events 
                    ============================= */
                    
                        s.$root.on('sliderfull:domready', function (e) {
                            
        
                            $('.slider-une-thumbnail').on('touchmove', function(e){
                                e.preventDefault();
                            });
                            // Les DOM des sliders sont prêt, c'est le bon endroit pour attacher des events
                            s.$root.find('.slider-une-main__slide').html(''); // Supprime le contenu des slide main qui servent uniquement à être dupliquée dans le slider thumbnail
                            // s.thumbnail.$slider.on('translate.owl.carousel', function(){
                            // 	console.log('transité le slider thumb');
                            // 	$(this).find('.owl-item.active');
                            // });
                            $(this).find('.owl-item.active').removeClass('current').eq(0).addClass('current');
                            dot();
                            // s.thumbnail.$slider.on('translated.owl.carousel', function(e){
                            // 	var index = getRealCurrentIndex(e);
                            // 	setClassToCurrentSlide($(this), index);						
                            // });
                            // function setClassToCurrentSlide($slider, index){
                            // 	$slider.find('.owl-item').removeClass('current').find('[data-index="'+index+'"]').addClass('current');
                            // 	dot();
                            // }
                        });

                    //

                    /* Initialisation
                    ============================= */
                    
                        masterSliderFull(s);
                    //
                }
            });
        }
    })
}(jQuery));