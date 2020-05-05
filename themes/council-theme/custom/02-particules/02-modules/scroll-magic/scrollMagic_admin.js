// Js gérant les animations au scroll
(function ($) {
    if($('.seu-front').length){
        $(document).ready(function(){
            $('#seu-une .seu-slider, #seu-socials .seu-slider').addClass('animate-out');
            $('.seu-quicklinks-list').attr('data-scroll-animation', 'grid-delayed');
            $('.seu-quicklinks-list .seu-quicklink').each(function(index, element){
                $(this).addClass('delayed-element delayed-element-'+index);
            });
            $('#seu-profil').attr('data-scroll-animation', '');
            // $('.seu-territories').attr('data-scroll-animation', '');
            // Récupère tous les éléments possédant le data-scroll-animation, puis leur attribut data-animation-state=active quand ils arrivent au ratio de l'écran
            $('[data-scroll-animation], #seu-une .seu-slider, #seu-socials .seu-slider').each(function(index, element){
                element.controller = new ScrollMagic.Controller();

                new ScrollMagic.Scene({
                    triggerElement: element,
                    triggerHook: 0.75
                })
                .on('enter', function(e){
                    if(e.scrollDirection =="FORWARD" && $(element).attr('data-animation-state') != 'active'){
                        $(element).attr('data-animation-state', 'active');
                        if($(element).hasClass('animate-out')){
                            $(element).removeClass('animate-out');
                        }
                    }
                })
                .addTo(element.controller);
            });

            if(!canScrollMagic){ // Gestion des petits écrans
                $('.seu-nav-bottom').css('top', '720px');
            }
            if(!isIE){
                if(canScrollMagic){
                    // Banner Height animation
                    var banner_controller = new ScrollMagic.Controller();
                    var banner_scene = new ScrollMagic.Scene({offset: 0, duration: 700})
                        .setTween("#seu-banner", {css:{height: "720px"}, scale: 1})
                        .addTo(banner_controller);

                    // // Nav bottom animation
                    var nav_bottom_controller = new ScrollMagic.Controller();
                    var nav_bottom_scene = new ScrollMagic.Scene({offset: 0, duration: 700})
                        .setTween(".seu-nav-bottom", {css:{top: "720px"}, scale: 1})
                        .addTo(nav_bottom_controller);
                        
                    // Une Titre  Animation
                    var une_controller = new ScrollMagic.Controller();
                    var une_scene = new ScrollMagic.Scene({offset: 300, duration: 420})
                        .setTween("#seu-une", {css:{"padding-top": "190px"}, scale: 1})
                        .addTo(une_controller);
                }  


                // Banner text animation
                var banner_title_controller = new ScrollMagic.Controller();
                var banner_title_scene = new ScrollMagic.Scene({offset: 0, duration: 900})
                    .setTween("#seu-banner .seu-container", {css:{top: "80%"}, scale: 1})
                    .addTo(banner_title_controller);
                    
                // Banner text animation opacity
                var banner_title_opacity_controller = new ScrollMagic.Controller();
                var banner_title_opacity_scene = new ScrollMagic.Scene({offset: 300, duration: 300})
                    .setTween("#seu-banner .seu-container", {css:{opacity: "0"}, scale: 1})
                    .addTo(banner_title_opacity_controller);


                // Territory animation
                // var territory_controller = new ScrollMagic.Controller();
                // var territory_scene = new ScrollMagic.Scene({triggerElement: "#seu-territory .seu-links", duration: 500})
                //     .setTween(".seu-territory", {css:{transform: "translateY(0)"}, scale: 1})
                //     .addTo(territory_controller);


                // Une Background
                var une_background_controller = new ScrollMagic.Controller();
                if ($('#seu-une').length) {
                    var une_background_scene = new ScrollMagic.Scene({triggerElement: "#seu-une", duration: 1000})
                        .setTween("#seu-une .seu-une-background", {css:{"bottom": "0px"}, scale: 1})
                        .addTo(une_background_controller);
                }
            }
        });
    }
}(jQuery));

