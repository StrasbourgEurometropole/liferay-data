(function ($) {

    $(document).ready(function(){
        var $notif = $('.mseu #nav-notifications');
        var $notifTrigger = $('.mseu #trigger-notifications');

        var $menu = $('.mseu #nav-menu');
        var $menuTrigger = $('.mseu #trigger-menu');  
        
        $(document).on('click', function(e){
            if(!$(e.target).parents('#nav-side').length){
                $menu.removeClass('opened');
                $notif.removeClass('opened');
                $('html.mseu').removeClass('no-scroll overlayed');
            }
        });

        function attachNavEvents(){
            if(environment != 'desktop' || windowHeight < 750 || $('html.mseu').hasClass('mobile') || $('html.mseu').hasClass('tablet')){
                $menu
                .off('mouseleave mouseenter')
                .on('click', function(){
                    if($menu.hasClass('opened')){
                        $menu.removeClass('opened');
                        $('html.mseu').removeClass('no-scroll overlayed');
                    }else{
                        $notif.removeClass('opened');
                        $menu.addClass('opened');
                        $('html.mseu').addClass('no-scroll overlayed');
                    }
                })

                $notif
                .off('mouseenter mouseleave')
                .on('click', function(){
                    if($notif.hasClass('opened')){
                        $notif.removeClass('opened');
                        $('html.mseu').removeClass('no-scroll overlayed');
                    }else{
                        $menu.removeClass('opened');
                        $notif.addClass('opened');
                        $('html.mseu').addClass('no-scroll overlayed');
                    }
                });
            }else{
                $menu.on('mouseenter', function(){
                    $menu.addClass('opened');
                    $notif.removeClass('opened');
                })
                .on('mouseleave', function(){
                    $menu.removeClass('opened');
                });

                $notif.on('mouseenter focus', function(){
                    $notif.addClass('opened');
                    $menu.removeClass('opened');
                })
                .on('mouseleave blur', function(){
                    $notif.removeClass('opened');
                });
            }
        }
        attachNavEvents();
        $(window).resize(function(){
            attachNavEvents();
        });
    });
}(jQuery)); 