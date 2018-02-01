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
 *      - un objet de configuration
 *      - un flux de donnée à afficher, stocké dans un objet JS (exemple ici mega_source  stockés dans index.html)
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

var mega = {
    can_animate: true,
    $slider: $('.slider-mega-container .slider'),
    $prev: $('.slider-mega-container .owl-prev'),
    $next: $('.slider-mega-container .owl-next'),
    $pages: $('.slider-mega-container .page'),
    pages_class: 'page',
    hasPicture_class: 'has-picture',
    hasVille_class: 'has-ville',
    is_Big_Class: 'big',
    $items: $('.slider-mega-container .item'),
    $filters: $('.actu-filter'),
    $template_agenda: $('#mega-templates .event'),
    $template_actu: $('#mega-templates .actu'),
    $template_mag: $('#mega-templates .mag'),
    $loader: $('#mega-loader')
};

/**
 * Suite d'appels permettant de construire un mega-slider à la une en fonction d'une catégorie
 * @param {string} category - Catégorie à afficher
 */
function megaSlider(slider, category){
    destroySlider(slider);
    removeAllItems(slider);
    majItems(category, slider);
    buildSlider(slider);
    dot();
}
if(mega.$slider.length){
    $(document).ready(function(){
        // Initialisation slider mega 
        getSources(mega_source, mega);
        megaSlider(mega, 'tous');

        // Gestion des filtres slider mega
        mega.$filters.on('click', function(e){
            if(mega.can_animate){
                mega.can_animate = false;
                var $filter = $(this),
                    category = $filter.attr('data-category');
                mega.$filters.removeClass('actif');
                $filter.addClass('actif');
                mega.$slider.addClass('animate-out');
                setTimeout(function(){
                    megaSlider(mega, category);
                    mega.$slider.removeClass('animate-out');
                    mega.can_animate = true;
                }, 800);
            }
        });

        // Reload des slider au resize pour le RWD
        $(window).resize(function(){
            if(environmentChanged){
                megaSlider(mega, 'tous');
        }
    });
});
    
}
