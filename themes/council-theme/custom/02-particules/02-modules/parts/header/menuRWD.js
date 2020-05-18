(function ($) {
    function openSubmenu($trigger){
        var text = $trigger.text();
        var $submenu = $trigger.next('.seu-menu-subitem');
        $submenu.find('.seu-container').prepend('<div class="seu-rwd-back"><span class="seu-picto"></span><span class="seu-text">'+text+'</span></div>');
        $submenu.addClass('opened');
        $submenu.on('click', '.seu-title', function(){
            var link = $(this).next('.seu-links');
            if(link.is(':visible')){
                link.slideUp();
            }else{
                $submenu.find('.seu-links').stop().slideUp();
                $(this).next('.seu-links').stop().slideDown();
            }
        });
    }
    function closeSubmenu($trigger){
        var $submenu = $trigger.parents('.seu-menu-subitem.opened');        
        $submenu.off('click');
        $submenu.removeClass('opened');
        setTimeout(function() {
            $trigger.remove();
        }, 500);
    }
    function toggleNoScroll(scroll){
        if(scroll){
            $('html').removeClass('seu-no-scroll');
        }else{
            $('html').addClass('seu-no-scroll');
        }
    }

    $(document).ready(function(){
        $('#seu-menu-trigger').on('click', function(){
            if( $(this).is(':checked') ){
                toggleNoScroll(false);
            }else{
                toggleNoScroll(true);
            }
        });
		$('.seu-menu-rwd-overlay').on('click', function(e){
			if(!$(e.target).hasClass('.seu-menu-panel') && !$(e.target).parents('.seu-menu-panel').length){
				$('#seu-menu-trigger').click();
			}
		});
        $('.seu-menu-panel')
        .on('click', '.seu-menu-item', function(e){
            e.preventDefault();
            openSubmenu($(this));
        })
        .on('click', '.seu-rwd-back', function(e){
            e.preventDefault();
            closeSubmenu($(this));
        });

    });
}(jQuery)); 