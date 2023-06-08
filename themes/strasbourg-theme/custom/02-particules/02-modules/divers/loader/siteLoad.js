// JS gérant le comportement lors de la progression du chargement du site
(function ($) {
    $('#seu-loader').addClass('seu-visible');
    var site_progress = 0;
    $('body').waitForImages({ // Incrémente une variable site_progress en fonction du nombre d'image/vidéo du site chargée
        each: function(loaded, count, success) {
            if(site_progress < 100){ // Comportement à chaque actualisation de la variable
                site_progress += 80 / count;
                $('#seu-loader .seu-loaded').text(Math.floor(site_progress) + '%');
            }
        },
        waitForAll: true
    });

    $(window).on( "load",function(){ // Comportement une fois le site complètement chargé
        site_progress = 100;
        $('#seu-loader .seu-loaded').text(Math.floor(site_progress) + '%');
        setTimeout(function(){
            $('#seu-loader').fadeOut();            
            $('#seu-banner').addClass('seu-loaded');
        }, 500);
    }); 
}(jQuery)); 
