function isTouchDevice() {
    return 'ontouchstart' in document.documentElement;
}

var Ww = $(window).width();

if (isTouchDevice() || Ww < 1280) {
   	$('body').addClass('no-hover');
}

if (isTouchDevice()) {
    $('.lang > .sub-menu').addClass('sub-lang-mobile');
    $('#lang-mobile').addClass('is-display');
};