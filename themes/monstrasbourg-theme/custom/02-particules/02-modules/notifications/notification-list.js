(function ($) {
    
    $(document).ready(function(){
        if($('.notification-list').length){
            $list = $('.notification-list'),
            $inputs = $list.find('input[type="checkbox"]');


            $inputs.on('change', function(){
                var $item = $(this).closest('.notification-list__item');
                if(!$item.hasClass('notification-list__item--read')){
                    $item.addClass('notification-list__item--read');
                }else{
                    $item.removeClass('notification-list__item--read');
                }
            });
        }
    });
}(jQuery));