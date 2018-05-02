// Si User et sur IE / EDGE alors on ajoute la classe IE au body
if (navigator.userAgent.match(/trident/gi) || navigator.appName == 'Microsoft Internet Explorer'){
    $('body').addClass('ie');
}


// Lancement du script de ObjectFit
objectFitImages('.fit-cover img');


/* Detect the scroll of the page and animate the menu */
$(window).on('scroll', function (e) {
    var st = $(this).scrollTop();

    if (st > 100) {
        $("body").addClass("is-scrolled");
        $('.social-share').addClass('fadein');
    }
    else {
        $("body").removeClass("is-scrolled");
        $('.social-share').removeClass('fadein');
    }
});


$('.pro-bloc-card-event').on('click',function(){
   $(this).find('pro-btn-action').toggleClass('active');
});


// Call To Action -- Ajout de la Classe Active
$('.pro-btn-action').on('click',function(e){
    e.preventDefault();
    e.stopPropagation();
    $(this).toggleClass('active');
});


// Dans les cards expérience, on coupe les lettres du mot découvrir pour créer un effet d'animation
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