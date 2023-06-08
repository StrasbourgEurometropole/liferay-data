// Si User et sur IE / EDGE alors on ajoute la classe IE au body
if (navigator.userAgent.match(/trident/gi) || navigator.appName == 'Microsoft Internet Explorer'){
    $('#ops-wrapper').addClass('ops-ie');
}

// Lancement du script Librairie Zoombox
$('.zoombox').zoombox();

// Lancement du script de ObjectFit
objectFitImages('.fit-cover img');