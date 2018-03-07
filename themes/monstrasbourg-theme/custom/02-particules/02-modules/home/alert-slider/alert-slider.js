(function ($) {
    $(document).ready(function(){
        var $alertSlider = $('#alert-slider');
        $alertSlider.find('.alert-list').addClass('owl-carousel').owlCarousel({
            items: 1,
            nav: true,
            loop: true,
            animateIn: 'slideInUp',
            animateOut: 'fadeOutUp',
            smartSpeed:450
        });

    });
 }(jQuery));
