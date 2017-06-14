var environment = 'desktop';
var environmentChanged = false;

function viewport() {
    var e = window,
        a = 'inner';
    if (!('innerWidth' in window)) {
        a = 'client';
        e = document.documentElement || document.body;
    }
    return {
        width: e[a + 'Width'],
        height: e[a + 'Height']
    };
}

var windowWidth = viewport().width; // This should match your media query

function testEnvironment() {
    var currentEnvironment = environment;
    windowWidth = viewport().width;
    if (windowWidth <= 1199 && windowWidth > 767) {
        environment = 'tablet';
    } else if (windowWidth <= 767) {
        environment = 'mobile';
    } else {
        environment = 'desktop';
    }
    if (currentEnvironment != environment) {
        return true;
    } else {
        return false;
    }
}