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
                    $notifAmount.innerText = Number($notifAmount.innerText)-1;
                    if (Number($notifAmount.innerText) == 0) {
                        $notifAmount.remove();
                        $('#no-notif-item').show();
                    } 
                    $('#notif_' + $(this).attr("id")).hide();
                    $('#notif_' + $(this).attr("id")).removeClass("new");
                    // s'il y a encore des notifs non lue, on affiche les 3 premières.
                    var $nbNotifDisplay = 0;
                    if ($notifAmount != undefined && Number($notifAmount.innerText) > 0) {
                        $('.notif-item.new').each(function(){
                            if($nbNotifDisplay < 3){
                                $(this).show();
                                $nbNotifDisplay++;
                            }
                        });
                    }
                }else{
                    $item.removeClass('notification-list__item--read');
                    if ($notifAmount == undefined) {
                        var iDiv = document.createElement('div');
                        iDiv.innerText = 1;
                        iDiv.className = 'notif-amount';
                        $('.notif-picto')[0].appendChild(iDiv);
                        $notifAmount = $('.notif-amount')[0];
                        $('#no-notif-item').hide();
                    } else {
                        $notifAmount.innerText =Number($notifAmount.innerText)+1;
                    }
                    $('#notif_' + $(this).attr("id")).addClass("new");
                    if (Number($notifAmount.innerText) <= 3) {
                        $('#notif_' + $(this).attr("id")).show();
                    }
                }
            });
        }
    });
}(jQuery));