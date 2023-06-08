(function ($) { 
    if($('.seu-dates-list').length){

        var lessHeight, moreHeight;
        var $dates_list = $('.seu-dates-list'),
            $dates_toggle = $('.seu-see-more');

        function getDatesHeights(){
            lessHeight = 0;
            moreHeight = 0;
            $dates_list.find('li').each(function(index, element){
                if(index <= 4){ // Incrémente les 5 premieres hauteurs
                    lessHeight += $(element).outerHeight();
                    moreHeight += $(element).outerHeight();
                }else{
                    moreHeight += $(element).outerHeight();
                }
            });
        }
        
        function attachDatesEvents(lessHeight, moreHeight){
            $dates_toggle
            .off('click')
            .on('click', function(){
                $dates_list.toggleClass('opened');
                $dates_toggle.toggleClass('opened');
                if($dates_list.hasClass('opened')){
                    moreDates(moreHeight);
                }else{
                    lessDates(lessHeight);
                }
            });
        }

        function moreDates(moreHeight){
            $dates_toggle.find('.seu-less').show();
            $dates_toggle.find('.seu-more').hide();
            $dates_list.height(moreHeight);
        }

        function lessDates(lessHeight){
            $dates_toggle.find('.seu-less').hide();
            $dates_toggle.find('.seu-more').show();
            $dates_list.height(lessHeight);
        }

        $(window).on( "load",function(){
            getDatesHeights();
            lessDates(lessHeight);
            attachDatesEvents(lessHeight, moreHeight);
        });

        $(window).resize(function(){
            if(environmentChanged){
                getDatesHeights();
                lessDates(lessHeight);
                attachDatesEvents(lessHeight, moreHeight);
            }
        });
    }
}(jQuery));