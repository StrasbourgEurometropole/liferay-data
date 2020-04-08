/* Detect the scroll of the page and animate the menu */
$(window).on('scroll', function (e) {
    var st = $(this).scrollTop();

    if (st > 100) {
        $('.social-share').addClass('fadein');
    }
    else {
        $('.social-share').removeClass('fadein');
    }
});