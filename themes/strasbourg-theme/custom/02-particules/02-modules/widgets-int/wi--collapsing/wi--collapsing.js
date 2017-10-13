(function ($) {
    $(document).ready(function(){
        if($('.seu-toggle-collapse').length){
            $('.seu-toggle-collapse').on('click', function(){
                $(this).toggleClass('seu-opened')
                .parent('.seu-wi--collapsing').toggleClass('seu-opened')
                .find('.seu-collapsing-box').slideToggle();
            });
            $('.seu-wi--collapsing.seu-first-opened .seu-toggle-collapse').click();
        }
    });
 }(jQuery));