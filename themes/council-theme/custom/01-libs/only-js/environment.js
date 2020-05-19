var environment, environmentChanged, windowWidth, windowHeight;
var isIE = true;
    function detectIE() {
        var ua = window.navigator.userAgent;

        // Test values; Uncomment to check result …

        // IE 10
        // ua = 'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)';
        
        // IE 11
        // ua = 'Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko';
        
        // Edge 12 (Spartan)
        // ua = 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36 Edge/12.0';
        
        // Edge 13
        // ua = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586';

        var msie = ua.indexOf('MSIE ');
        if (msie > 0) {
            // IE 10 or older => return version number
            return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
        }

        var trident = ua.indexOf('Trident/');
        if (trident > 0) {
            // IE 11 => return version number
            var rv = ua.indexOf('rv:');
            return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
        }

        var edge = ua.indexOf('Edge/');
        if (edge > 0) {
            // Edge (IE 12+) => return version number
            return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
        }

        // other browser
        isIE = false;
        return false;
    }
    function viewport() {
        var e = window, a = 'inner';
        if (!('innerWidth' in window )) {
            a = 'client';
            e = document.documentElement || document.body;
        }
        return { width : e[ a+'Width' ] , height : e[ a+'Height' ] };
    }
    function testScrollMagic(){
        if(environment != 'mobile' && $(window).height() > 720){
            $('body').addClass('seu-can-scrollMagic');
            canScrollMagic = true;
        }else{
            $('body').removeClass('seu-can-scrollMagic');
            canScrollMagic = false;
        }
    }

    environment = 'desktop';
    environmentChanged = false;
    windowWidth = viewport().width; // This should match your media query
    windowHeight = viewport().height; // This should match your media query

    function testEnvironment() {
        var currentEnvironment = environment;
        windowWidth = viewport().width;
        if (windowWidth <= breakpoint_large && windowWidth > breakpoint_small) {
            environment = 'tablette';
        } else if (windowWidth <= breakpoint_small) {
            environment = 'mobile';
        } else {
            environment = 'desktop';
        }
        if (currentEnvironment != environment) {
            environmentChanged = true;
        } else {
            environmentChanged = false;
        }
    }
(function ($) {
    detectIE();
    $(document).ready(function() {
        testEnvironment();
        testScrollMagic();
    });

    $(window).resize(function(){
        testEnvironment();
        detectIE();
        testScrollMagic();
    });
}(jQuery));