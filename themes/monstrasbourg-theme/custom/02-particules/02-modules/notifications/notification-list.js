(function ($) {
    
    $(document).ready(function(){
        if($('.notification-list').length){
            $list = $('.notification-list'),
            $inputs = $list.find('input[type="checkbox"]');

            $inputs.on('change', function(){
                $notifAmount = $('.notif-amount')[0];
                var $item = $(this).closest('.notification-list__item');
                if(!$item.hasClass('notification-list__item--read')){
                    $item.addClass('notification-list__item--read');
                    if (Number($notifAmount.innerText)-1 == 0) {
                        $notifAmount.remove();
                    } else {
                        $notifAmount.innerText =Number($notifAmount.innerText)-1;
                    }
                }else{
                    $item.removeClass('notification-list__item--read');
                    if ($notifAmount == undefined) {
                        var iDiv = document.createElement('div');
                        iDiv.innerText = 1;
                        iDiv.className = 'notif-amount';
                        $('.notif-picto')[0].appendChild(iDiv);
                    } else {
                        $notifAmount.innerText =Number($notifAmount.innerText)+1;
                    }
                }
            });
        }
    });
}(jQuery));