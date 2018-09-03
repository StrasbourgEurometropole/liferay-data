if($('.pro-page-pacte').length > 0 || $('.pro-page-budget-participatif').length > 0){

    var footer = $('footer').offset().top;
    var windowH = $(window).height();
    var barreFixed = $('.pro-bloc-prefooter');

    $(window).on('scroll',function(){
        console.log(footer);
        if (window.pageYOffset-90 <= footer-windowH) {
            barreFixed.addClass('pro-sticky-bar');
        } else {
            barreFixed.removeClass('pro-sticky-bar');
        }
    });

}