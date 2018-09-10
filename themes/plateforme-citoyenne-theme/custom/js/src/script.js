// Si User et sur IE / EDGE alors on ajoute la classe IE au body
if (navigator.userAgent.match(/trident/gi) || navigator.appName == 'Microsoft Internet Explorer'){
    $('#th-global').addClass('ie');
}


// Lancement du script de ObjectFit
objectFitImages('.fit-cover img');


/* Detect the scroll of the page and animate the menu */
$(window).on('scroll', function (e) {
    var st = $(this).scrollTop();

    if (st > 100) {
        $("#th-global").addClass("is-scrolled");
        $('.social-share').addClass('fadein');
    }
    else {
        $("#th-global").removeClass("is-scrolled");
        $('.social-share').removeClass('fadein');
    }
});


var lastscrolltop = 0;
var lastIsDirTop = 0;
document.addEventListener('scroll',function(){
    var st = $(document).scrollTop();
    if(st<lastscrolltop && lastIsDirTop == 0){
        lastIsDirTop = 1;
        $("#th-global").addClass('scrolldir-top',true);
    }
    if(st>lastscrolltop && lastIsDirTop == 1){
        lastIsDirTop = 0;
        $("#th-global").removeClass('scrolldir-top',true);
    }
    lastscrolltop = st;
});

/*
$('.pro-bloc-card-event').on('click',function(e){
    e.preventDefault();
   $(this).find('pro-btn-action').toggleClass('active');
});

$('.pro-btn-signer').on('click',function(e){
    e.preventDefault();
   $(this).toggleClass('active');
});


// Call To Action -- Ajout de la Classe Active
$('.pro-btn-action').on('click',function(e){
    e.preventDefault();
    e.stopPropagation();
    $(this).toggleClass('active');
});*/


// Pour les compteurs dans les pages de détail
var textDiscover = $('.pro-compt').first().text();
var textDiscoverWrapped = '';
for (var i = 0; i != textDiscover.length; i++) {
    textDiscoverWrapped += '<span>' + textDiscover[i] + '</span>';
}
$('.pro-compt').html(textDiscoverWrapped);


// Changer le texte du bouton Suivre ce Projet - Page Détail projet
$("[href='#pro-follow-project']").click(function(e){
    e.preventDefault();
    if($(this).hasClass('active')){
        $(this).removeClass('active').text('Suivre ce projet');
    }
    else{
        $(this).addClass('active').text('Projet Suivi');
    }
});