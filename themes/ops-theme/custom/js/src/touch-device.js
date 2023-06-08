function isTouchDevice() {
    return 'ontouchstart' in document.documentElement;
}

var Ww = $(window).width();

if (isTouchDevice() && Ww < 1280) {
   	$('#ops-wrapper').addClass('no-hover');
}