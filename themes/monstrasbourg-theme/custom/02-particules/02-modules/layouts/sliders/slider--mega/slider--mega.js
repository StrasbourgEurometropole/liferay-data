/**
 * MegaSlider
 * 
 * @description - Affiche X slider(s) comportant plusieurs éléments par pages, triable par catégorie. Un slider est construit automatiquement à partir d'un flux de donnée en JS au format simple:
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
 *      - une liste d'objets de configuration
 *      - X flux de données à afficher, stocké(s) dans un objet JS (exemple ici mega_source_...  stockés dans index.html)
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
$(document).ready(function(){
    var list_mega = [];
    $('.slider--mega').each(function(index, element) {

        list_mega[index] = {
            can_animate: true,
            $slider: $(element).find('.slider'),
            $prev: $(element).find('.owl-prev'),
            $next: $(element).find('.owl-next'),
            $pages: $(element).find('.page'),
            pages_class: 'page',
            hasPicture_class: 'has-picture',
            hasVille_class: 'has-ville',
            is_Big_Class: 'big',
            $items: $(element).find('.item'),
            $filters: $(element).find('.actu-filter'),
            $template_agenda: $(element).find('.event'),
            $template_actu: $(element).find('.actu'),
            $template_mag: $(element).find('.mag'),
            $loader: $(element).find('#mega-loader')
        }

        // Initialisation slider mega 
        getSources(mega_source[index], list_mega[index]);
        megaSlider(list_mega[index], 'tous');

        // Gestion des filtres slider mega
        list_mega[index].$filters.on('click', function(e){
            if(list_mega[index].can_animate){
                list_mega[index].can_animate = false;
                var $filter = $(this),
                    category = $filter.attr('data-category');
                list_mega[index].$filters.removeClass('actif');
                $filter.addClass('actif');
                list_mega[index].$slider.addClass('animate-out');
                setTimeout(function(){
                    megaSlider(list_mega[index], category);
                    list_mega[index].$slider.removeClass('animate-out');
                    list_mega[index].can_animate = true;
                }, 800);
            }
        });
    });

    // Reload des slider au resize pour le RWD
    $(window).resize(function(){
        if(environmentChanged){
            $('.slider--mega').each(function(index, element) {
                megaSlider(list_mega[index], 'tous');
            })
        }
    });
});



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
