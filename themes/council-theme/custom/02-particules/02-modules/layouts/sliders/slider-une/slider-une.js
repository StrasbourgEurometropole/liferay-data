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

var une = {
    can_animate: true,
    $slider: $('.seu-slider-une-container .seu-slider'),
    $prev: $('.seu-slider-une-container .seu-owl-prev'),
    $next: $('.seu-slider-une-container .seu-owl-next'),
    $pages: $('.seu-slider-une-container .seu-page'),
    pages_class: 'seu-page',
    hasPicture_class: 'seu-has-picture',
    hasVille_class: 'seu-has-ville',
    is_Big_Class: 'seu-big',
    $items: $('.seu-slider-une-container .seu-item'),
    $filters: $('.seu-actu-filter'),
    $template_agenda: $('#seu-une-templates .seu-event'),
    $template_actu: $('#seu-une-templates .seu-actu'),
    $template_mag: $('#seu-une-templates .seu-mag'),
    $loader: $('#seu-une-loader')
};


$(document).ready(function(){
    if(typeof une_source !== "undefined"){
        // Initialisation slider Une
        getSources(une_source, une);
        megaSlider(une, 'tous');

        // Gestion des filtres slider Une
        une.$filters.on('click', function(e){
            if(une.can_animate){
                une.can_animate = false;
                var $filter = $(this),
                    category = $filter.attr('data-category');
                une.$filters.removeClass('seu-actif');
                $filter.addClass('seu-actif');
                une.$slider.addClass('animate-out');
                setTimeout(function(){
                    megaSlider(une, category);
                    une.$slider.removeClass('animate-out');
                    une.can_animate = true;

                    // On modfie les boutons correspondant sur la page
                    // var favoriteButton = $('[data-type=' + type + '][data-id=' + id + ']')
                    jQuery('.seu-add-favorites').each(function(index, favoriteButton) {
                        var favoriteButtonJq = jQuery(favoriteButton);
                        var entityId = Number(favoriteButtonJq.data('id'));
                        var type = Number(favoriteButtonJq.data('type'));
                        var isFavorite = false;
                        for (var i = 0; i < window.userFavorites.length; i++) {
                            if (window.userFavorites[i].entityId === entityId && window.userFavorites[i].typeId === type) {
                                isFavorite =  true;
                                break;
                            }
                        }
                        if (isFavorite) {
                            favoriteButtonJq.addClass('liked');
                            favoriteButton.textContent = Liferay.Language.get('eu.remove-from-favorite');
                        }else{
                            favoriteButtonJq.removeClass('liked');
                            favoriteButton.children[0].textContent = Liferay.Language.get('eu.add-to-favorite');
                        }
                    });

                }, 800);
            }
        });

        // Reload des slider au resize pour le RWD
        $(window).resize(function(){
            if(environmentChanged){
                megaSlider(une, 'tous');
            }
        });
    }
});
