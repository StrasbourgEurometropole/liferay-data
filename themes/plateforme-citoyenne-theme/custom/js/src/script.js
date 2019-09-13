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
document.addEventListener('scroll', function () {
    var st = $(document).scrollTop();
    if (st < lastscrolltop && lastIsDirTop == 0) {
        lastIsDirTop = 1;
        $("#th-global").addClass('scrolldir-top', true);
    }
    if (st > lastscrolltop && lastIsDirTop == 1) {
        lastIsDirTop = 0;
        $("#th-global").removeClass('scrolldir-top', true);
    }
    lastscrolltop = st;
});


$('.pro-bloc-card-event').on('click', function (e) {
    e.preventDefault();
    $(this).find('pro-btn-action').toggleClass('active');
});

$('.pro-btn-signer').on('click', function (e) {
    e.preventDefault();
    $(this).toggleClass('active');
});


// Call To Action -- Ajout de la Classe Active
$('.pro-btn-action').on('click', function (e) {
    if(!$(this).hasClass('pro-btn-disabled')) {
        e.preventDefault();
        e.stopPropagation();
        $(this).toggleClass('active');
    }
});


// Disabled CTA
$('.pro-btn-disabled').on('click', function (e) {
    e.preventDefault();
    e.stopPropagation();
});

// Pour les compteurs dans les pages de détail
var textDiscover = $('.pro-compt').first().text();
var textDiscoverWrapped = '';
for (var i = 0; i != textDiscover.length; i++) {
    textDiscoverWrapped += '<span>' + textDiscover[i] + '</span>';
}
$('.pro-compt').html(textDiscoverWrapped);


// Changer le texte du bouton Suivre ce Projet - Page Détail projet
$("[href='#pro-follow-project']").click(function (e) {
    if(!$(this).hasClass('pro-btn-disabled')){
        e.preventDefault();
        if ($(this).hasClass('active')) {
            $(this).removeClass('active').text('Suivre ce projet');
        }
        else {
            $(this).addClass('active').text('Projet Suivi');
        }
    }
});


if ($("[href='#backtop']").length) {
    var scrollTrigger = 200, // px
        backToTop = function () {
            var scrollTop = $(window).scrollTop();
            if (scrollTop > scrollTrigger) {
                $("[href='#backtop']").addClass('show');
            } else {
                $("[href='#backtop']").removeClass('show');
            }
        };
    backToTop();
    $(window).on('scroll', function () {
        backToTop();
    });
    $("[href='#backtop']").on('click', function (e) {
        e.preventDefault();
        $('html,body').animate({
            scrollTop: 0
        }, 700);
    });
}


$("[href='#pro-onglet-account']").on('click', function (e) {
    e.preventDefault();
    $('#pro-onglet-activite').addClass('pro-hide');
    $('#pro-onglet-account').removeClass('pro-hide');
});

$("[href='#pro-onglet-activite']").on('click', function (e) {
    e.preventDefault();
    $('#pro-onglet-activite').removeClass('pro-hide');
    $('#pro-onglet-account').addClass('pro-hide');
});


$('.pro-title-dashboard > h1, .pro-title-dashboard > h2').each(function () {
    var widthTitle = $(this).width() + 60;
    $(this).next().css({'width': 'calc(100% - ' + widthTitle + 'px)'});
});


$('.modal-dialog').each(function(){
    new SimpleBar($(this)[0]);
});

