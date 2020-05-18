// (function ($) {
    // function manageNavigationDisplay(e, $prev, $next){ // Ne fonctionne pas avec loop: true
    //     if(e.item.index == 0){
    //         $prev.addClass('disabled');
    //         $next.removeClass('disabled');
    //     }else if(e.item.index == (e.item.count - 1) || e.page.index == (e.page.count - 1)){
    //         $prev.removeClass('disabled');
    //         $next.addClass('disabled');
    //     }else{
    //         $prev.removeClass('disabled');
    //         $next.removeClass('disabled');
    //     }
    // }
    function manageNavigationDisplay(e, $prev, $next){ // Ne fonctionne pas avec loop: true
        if(e.item.index == 0){
            $prev.addClass('disabled');
        }else{
            $prev.removeClass('disabled');
        }
        if(e.item.index == (e.item.count - 1)){
            $next.addClass('disabled');
        }else{
            $next.removeClass('disabled');
        }
    }

    function updatePagination(e, $prev, $next){
        var prev_index, next_index, total_count;
        if(e.item.index == 0){
            prev_index = e.item.count - 1;
        }else{
            prev_index = e.item.index;
        }
        if(e.item.index == (e.item.count - 1)){
            next_index = 1;
        }else{
            next_index = e.item.index + 2;
        }
        total_count = e.item.count;
        $prev.find('.owl-current-page').html(prev_index);
        $next.find('.owl-current-page').html(next_index);
        
        $prev.find('.owl-total-page').html(total_count);
        $next.find('.owl-total-page').html(total_count);
    }

    function updateSliderText($slider, $title, $description, $link, index){
        var $currentSlide = $slider.find('.owl-item:eq('+index+') li');
        $title.text($currentSlide.attr('data-title'));
        $description.text($currentSlide.attr('data-description'));
        $link.attr('href', $currentSlide.attr('data-link'));
    }

    function attachCustomNavEvents($slider, $prev, $next){
        $prev.add($next).off('click');
        $prev.on('click', function(){
            $slider.trigger('stop.owl.autoplay');
            $slider.trigger('prev.owl.carousel');
        });
        $next.on('click', function(){
            $slider.trigger('stop.owl.autoplay');
            $slider.trigger('next.owl.carousel');
        });
    }
    function attachCustomPlayEvents($slider, $play){
        $play.on('click', function(){
            if($play.hasClass('pause')){
                $slider.trigger('stop.owl.autoplay');
                $play.removeClass('pause').addClass('play');
            }else{
                $slider.trigger('play.owl.autoplay');
                $play.removeClass('play').addClass('pause');
            }
        });
    }
    function fixRestartAutoplayAfterNavigationClick($slider, $prev, $next, $dots, $play){
        $next.add($prev).add($dots.find('.owl-dot')).on('click', function() {
            setTimeout(function() {
                $slider.trigger('play.owl.autoplay', [1000]);
                $slider.trigger('stop.owl.autoplay');
                $play.removeClass('pause');
            }, 500);
        });
    }
// }(jQuery));

