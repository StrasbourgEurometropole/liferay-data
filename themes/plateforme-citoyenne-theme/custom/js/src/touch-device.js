function isIE(){
    if (navigator.userAgent.match(/trident/gi) || navigator.appName == 'Microsoft Internet Explorer') {
        return true;
    }
    return false;
}

function isTouchDevice() {
    return 'ontouchstart' in document.documentElement;
}

function isNoHover(){
    if(isTouchDevice() && document.body.clientWidth <= 1280){
        return true;
    }
    return false;
}

if (isTouchDevice()) {
    $('.lang > .sub-menu').addClass('sub-lang-mobile');
    $('#lang-mobile').addClass('is-display');
}


var isiPad = navigator.userAgent.match(/iPad/i) != null;


if (isiPad) {
    $('body').addClass('on-ipad');
}


if (isIE()) {
    document.getElementsByTagName('body')[0].className += ' ie';
}

if (isNoHover()) {
    document.getElementsByTagName('body')[0].className += ' no-hover';
}