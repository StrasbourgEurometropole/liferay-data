// Important : wrap it with AUI "all portlet ready" code
// Dropdown langues
(function($) {
    $('.language-menu a').on('click', function(e) {
        e.preventDefault();
        $('.language-menu').toggleClass('open');
    });
})(jQuery);


// Sticky (uniquement en desktop)
(function($) {
    // Cas où il n'y a pas de menu musée
    if ($('.museum-header').length === 0) {
        $(document).on('scroll', function() {
            if ($(this).scrollTop() > 132) {
                $('.menu-header').addClass('sticky');
                $('body').css('margin-top', '49px');
            } else {
                $('.menu-header').removeClass('sticky');
                $('body').css('margin-top', '0');
            }
        });
    } else { // Avec menu musée
        $(document).on('scroll', function() {
            if ($(this).scrollTop() > 180) {
                $('.breadcrumb-wrapper').addClass('sticky');
                $('.museum-header').addClass('sticky');
                $('body').css('margin-top', '86px');
            } else {
                $('.breadcrumb-wrapper').removeClass('sticky');
                $('.museum-header').removeClass('sticky');
                $('body').css('margin-top', '0');
            }
        });
    }
})(jQuery);


// Menu mobile
(function($) {
    /*** 
     * Run this code when the #nav-toggle link has been tapped
     * or clicked
     */
    $('.mobile-menu-toggle').on('touchstart click', function(e) {
        e.preventDefault();
        $('.mobile-menu-toggle').toggleClass("active");
        var $body = $('body'),
            $page = $('#page'),

            /* Cross browser support for CSS "transition end" event */
            transitionEnd = 'transitionend webkitTransitionEnd otransitionend MSTransitionEnd';

        /* When the toggle menu link is clicked, animation starts */
        $body.addClass('animating');

        /***
         * Determine the direction of the animation and
         * add the correct direction class depending
         * on whether the menu was already visible.
         */
        if ($body.hasClass('menu-visible')) {
            $body.addClass('right');
            $page.off('touchstart click');
        } else {
            $body.addClass('left');
            $page.on('touchstart click', function(e) {
                if (!$body.hasClass('animating')) {
                    e.preventDefault();
                    $('#nav-toggle').trigger('click');
                }
            });
        }

        /***
         * When the animation (technically a CSS transition)
         * has finished, remove all animating classes and
         * either add or remove the "menu-visible" class 
         * depending whether it was visible or not previously.
         */
        $page.on(transitionEnd, function() {
            $body.removeClass('animating left right').toggleClass('menu-visible');
            $page.off(transitionEnd);
        });
    });
})(jQuery);


// Gestion des sous-menus mobiles
(function($) {
    $('.mobile-main-menu a.submenu-toggle').on('click', function(e) {
        e.preventDefault();
        $('.mobile-main-menu .open').removeClass('open');
        $(this).next().addClass('open');
        $('.mobile-main-menu-nav').show();
    });

    $('.mobile-main-menu-nav .to-start').on('click', function(e) {
        e.preventDefault();
        $('.mobile-main-menu .open').removeClass('open');
        $('.mobile-main-menu .main-menu').addClass('open');
        $('.mobile-main-menu-nav').hide();
    });

    $('.mobile-main-menu-nav .previous').on('click', function(e) {
        e.preventDefault();
        var openedSubmenu = $('.mobile-main-menu .open');
        var nextOneToBeOpened = openedSubmenu.parent().parent();
        if (nextOneToBeOpened.hasClass('main-menu') || nextOneToBeOpened.hasClass('submenu')) {
            $('.mobile-main-menu .open').removeClass('open');
            nextOneToBeOpened.addClass('open');
        }
        if (nextOneToBeOpened.hasClass('main-menu')) {
            $('.mobile-main-menu-nav').hide();
        }
    });
})(jQuery);


// Menu "accès par public" mobile
(function($) {
    $('.accessibility-mobile-menu .has-submenu > a').on('click', function(e) {
        e.preventDefault();
        $('.accessibility-mobile-menu .access-by-public-submenu').toggleClass('open');
    });
})(jQuery);


// Carousel home
(function($) {
    $(document).ready(function() {
        if ($().owlCarousel) {
            var carousel = $('.home-carousel .owl-carousel').owlCarousel({
                items: 1,
                nav: true,
                loop: true,
                dotsContainer: '#carousel-custom-dots'
            });
            $('.home-carousel .owl-dot').click(function() {
                carousel.trigger('to.owl.carousel', [$(this).index(), 300]);
            });
            $('.home-carousel .owl-dot').hover(function() {
                var dot = $(this);
                $('.home-carousel .museum-name').text(dot.data('title'));
            });
            $('.home-carousel #carousel-custom-dots').mouseleave(function() {
                var museumName = $('.home-carousel .museum-name');
                museumName.text(museumName.data('original'));
            });
        }
    });
})(jQuery);


// Items carousel
(function($) {
    $(document).ready(function() {
        if ($().owlCarousel) {
            // Carousel news
            $('.items-carousel.news-carousel .owl-carousel').owlCarousel({
                items: 1,
                nav: true,
                stagePadding: 50,
                responsiveBaseElement: '.items-carousel.news-carousel',
                responsive: {
                    601: {
                        stagePadding: 0
                    },
                    901: {
                        stagePadding: 0,
                        items: 2
                    }
                }
            });
            // Carousel editions
            $('.items-carousel.editions-carousel .owl-carousel').owlCarousel({
                items: 1,
                nav: true,
                responsiveBaseElement: '.items-carousel.editions-carousel',
                responsive: {
                    601: {
                        items: 2
                    },
                    1000: {
                        items: 4
                    }
                }
            });
            // Carousel collections d'oeuvres
            $('.artwork-collections-carousel .owl-carousel').owlCarousel({
                items: 10,
                margin: 10,
                autoWidth: true,
                loop: true,
                responsiveBaseElement: '.items-carousel.artwork-collections-carousel',
                nav: true,
                dots: true
            });
            // Carousel détail entité
            var entityDetailCarousel = $('.entity-images-carousel .owl-carousel').owlCarousel({
                items: 5,
                margin: 5,
                autoWidth: true,
                responsiveBaseElement: '.entity-images-carousel'
            });

            var entityCarouselResizer = function() {
                if (window.innerWidth > 918) {
                    var newWidth = $('.entity-detail').width() / 2 - 20;
                    $('.entity-images-carousel').width(newWidth);
                } else {
                    $('.entity-images-carousel').width("auto");
                }
            };
            $(window).resize(function() {
                entityCarouselResizer();
            });
            entityCarouselResizer();

            $('.entity-images-carousel-next').on('click', function() {
                entityDetailCarousel.trigger('next.owl.carousel');
            });
            $('.entity-images-carousel-previous').on('click', function() {
                entityDetailCarousel.trigger('prev.owl.carousel');
            });
        }
    });
    
})(jQuery);


// Dotdotdot
(function($) {
    if ($().dotdotdot) {
        $('.items-carousel .item-content').dotdotdot({
            watch: window
        });
        $('.featured-artwork-description').dotdotdot({
            height: 40
        });
        $('.news .news-content').dotdotdot({
            height: 100
        });
    }
})(jQuery);


// Magnific popup (lightbox images)
(function($) {
    if ($().magnificPopup) {
        $('.lightbox').on('click', function(e) {
            $.magnificPopup.open({
                type: 'image',
                items: {
                    src: $(this).attr('src'),
                    title: $(this).data('title')
                },
                zoom: {
                    enabled: true
                },
                callbacks: {
                    elementParse: function(qw) {
                        qw.el = $(e.target);
                    }
                }
            });
        });
    }
})(jQuery);


// Onglets expositions
(function($) {
    $(document).ready(function() {
        $('.exhibition-tab').on('click', function(e) {
            e.preventDefault();

            $('.exhibition-tab').removeClass('active');
            $(this).addClass('active');

            var entryId = $(this).data('entry-id');
            $('.exhibition-tab-content').removeClass('active');
            $('.exhibition-tab-content[data-entry-id=' + entryId + ']').addClass('active');
        });
    });
})(jQuery);


//Formulaires de recherche
(function($) {
    // Accordéons
    $(document).ready(function() {
        $('.search-asset-portlet form legend').on('click', function() {
            if (!$(this).hasClass('open')) {
                $(this).addClass('open');
                $(this).parent().addClass('open');
                $('.vocabulary-selection-control, .asset-type-selection-control, .date-selection-control, .order-selection-control', $(this).parent()).slideToggle(150);
            } else {
                $(this).removeClass('open');
                $(this).parent().removeClass('open');
                $('.vocabulary-selection-control, .asset-type-selection-control, .date-selection-control, .order-selection-control', $(this).parent()).slideToggle(150);
            }
        });
    });
    // Cases à cocher et boutons radio
    function setCheckedClass(checkbox) {
        if ($(checkbox).is(':checked')) {
            var name = $(checkbox).attr('name');
            $('.radio [name=' + name + ']').parent('label').removeClass('checked');
            $(checkbox).parent('label').addClass('checked');
        } else {
            $(checkbox).parent('label').removeClass('checked');
        }
    }
    $('.search-asset-portlet input[type=checkbox], .search-asset-portlet input[type=radio]').on('change', function() {
        setCheckedClass(this);
    });
    $('.search-asset-portlet input[type=checkbox], .search-asset-portlet input[type=radio]').each(function() {
        setCheckedClass(this);
    });
})(jQuery);


// Validation formulaire de recherche
(function($) {
    $(document).ready(function() {
        $('#keywords-deported-form').on('submit', function(e) {
            e.preventDefault();
            var keywords = $('#keywords-deported-form input[type=text]').val();
            $('.search-asset-form input[type=hidden][name$=keywords]').val(keywords);
            $('.search-asset-form form').submit();
        });
    });
})(jQuery);


// Contenu web home : redimensionnement
(function($) {
    $(document).ready(function() {
        var museumHomeResizer = function () {
            var newTop = ($('.cover').height() - $('.cover-text').height()) / 2;
            $('.cover-text').css('top', newTop + 'px');
        };
        $(window).resize(museumHomeResizer);        
        museumHomeResizer();
    });
})(jQuery);


