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
