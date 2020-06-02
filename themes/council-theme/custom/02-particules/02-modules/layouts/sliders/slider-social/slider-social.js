/**
 * MegaSlider
 * 
 * @description - Affiche un slider comportant plusieurs éléments par pages, triable par catégorie. Le slider est construit automatiquement à partir d'un flux de donnée en JS au format simple:
 *  sources = [
 *  {
 *      "category": "macatégorie",
 *      "info en tout genre": "moninfo",
 *      etc...
 *  },
 * etc...
 * ]
 * 
 * A besoin pour fonctionner:
 *      - un objet de configuration (ici une et social)
 *      - un flux de donnée à afficher, stocké dans un objet JS (exemple ici une_source et social_source stockés dans index.html)
 *      - L'architecture html du slider
 *      - Un lot de template pour chaque catégorie d'item présent dans le slider, contenant des placeholders ##infosàremplacer## pour chaque entrée du flux de donnée (ici stockés dans index.html)
 *
 * @Lancement:
 *      - lancer d'abord getSources(monObjetSource, monObjetDeConfiguration); (à ne faire qu'une fois)
 *      - appeler megaSlider(monObjetDeConfiguration, maCategorie);
 *      - Ajouter les évènements au click sur les filtres de catégorie (optionnel)
 */


/**
 * Configuration object 
 */
var social = {
    can_animate: true,
    $slider: $('.seu-slider-social-container .seu-slider'),
    $prev: $('.seu-slider-social-container .seu-owl-prev'),
    $next: $('.seu-slider-social-container .seu-owl-next'),
    $pages: $('.seu-slider-social-container .seu-page'),
    pages_class: 'seu-page',
    hasPicture_class: 'seu-has-picture',
    hasVille_class: 'seu-has-ville',
    is_Big_Class: 'seu-big',
    $items: $('.seu-slider-social-container .seu-item'),
    $filters: $('.seu-social-filter'),
    $template_twitter: $('#seu-social-templates .seu-item'),
    $template_facebook: $('#seu-social-templates .seu-item'),
    $template_instagram: $('#seu-social-templates .seu-item'),
    $template_dailymotion: $('#seu-social-templates .seu-item'),
    $loader: $('#seu-social-loader')
};


$(document).ready(function(){
    if(typeof social_source !== "undefined"){
        // Init slider Social
        getSources(social_source, social);
        megaSlider(social, 'tous');


        // Gestion des filtres slider Social
        social.$filters.on('click', function(e){
            if(social.can_animate){
                social.can_animate = false;
                var $filter = $(this),
                    category = $filter.attr('data-category');
                social.$filters.removeClass('seu-actif');
                $filter.addClass('seu-actif');
                social.$slider.addClass('animate-out');
                setTimeout(function(){
                    megaSlider(social, category);
                    social.$slider.removeClass('animate-out');
                    social.can_animate = true;
                }, 800); 
            }
        }); 

        // Reload des slider au resize pour le RWD
        $(window).resize(function(){
            if(environmentChanged){
                megaSlider(social, 'tous');
            }
        });
    }
});