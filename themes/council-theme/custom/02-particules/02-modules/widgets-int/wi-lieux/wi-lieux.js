var page_limit = 12;
/**
 * @description Construction de la division en page du widget lieu
 * @param {JqueryObject} widget - Widget Lieux à construire
 */
function buildLieux(widget){
    var wi = {
        $widget: $(widget),
        $list: $(widget).find('.seu-wi-lieux-list'),
        $items: $(widget).find('.seu-wi-lieux-item'),
        $pagin_item: $(widget).find('.seu-pagin-item.seu-is-active')
    }

    // Récupérer le nombre de page
    wi.items_count = wi.$items.length;
    wi.page_count = Math.ceil(wi.items_count / 12);
    // Création de la pagination
    for (var index = 2; index <= wi.page_count; index++) {
        var $pagin_item = wi.$pagin_item.clone();
        $pagin_item.removeClass('seu-is-active')
            .insertBefore(wi.$widget.find('.seu-pagin-next'))
                .find('button')
                .attr('data-page', index)
                .attr('title', Liferay.Language.get('eu.go-to-page') + ' ' +index)
                    .find('.seu-btn-text')
                    .html(index);
    }
    // Création d'une string représentant le Node , avec attribution des data-page
    wi.node = '<ul class="seu-wi-lieux-list seu-visible unstyled" data-page="1">';
    wi.$items.each(function(index, element){
        if( (index % 12 == 0) && index > 0){
            var current_page = Math.ceil( (index + 1) / 12);
            wi.node += '</ul><ul class="seu-wi-lieux-list unstyled" data-page="'+current_page+'">'; 
        }
        wi.node += element.outerHTML; 
    });
    wi.node += '</ul>';
    // Placement du node et supression de la liste originale
    wi.$list.after(wi.node);
    wi.$list.remove();
    // Si une seule page : on masque la pagination
    if (wi.page_count == 1) {
        $('.seu-media-bottom', wi.$widget).hide();
    }

    return wi;
}

/**
 * @description Affiche la page ayant l'index demandé
 * @param {Object} wi - Objet de configuration du widget Lieux
 * @param {Int} index - Index de la page cible
 */
function lieuxGoToPage(wi, index){
    if(index <= wi.page_count && index >= 1){
        wi.$widget.find('.seu-wi-lieux-list.seu-visible').removeClass('seu-visible');
        wi.$widget.find('.seu-wi-lieux-list[data-page="'+index+'"]').addClass('seu-visible');
        wi.$widget.find('.seu-pagin-item')

        // Gestion des états prev/next pagination
        if(index == 1){
            wi.$widget.find('[data-action="prev"]').parent('.seu-pagin-item').addClass('seu-disabled');
        }else{
            wi.$widget.find('[data-action="prev"]').parent('.seu-pagin-item').removeClass('seu-disabled');
        }
        if(index == wi.page_count){
            wi.$widget.find('[data-action="next"]').parent('.seu-pagin-item').addClass('seu-disabled');
        }else{
            wi.$widget.find('[data-action="next"]').parent('.seu-pagin-item').removeClass('seu-disabled');
        }
        // Gestion numéro de page pagination
        wi.$widget.find('.seu-pagin-item.seu-is-active').removeClass('seu-is-active');
        wi.$widget.find('.seu-pagin-item button[data-page="'+index+'"]').parent('.seu-pagin-item').addClass('seu-is-active');
    }else{
        console.log('erreur pas d');
    }
}
/**
 * @description Récupère la page courante en se basant sur l'état de la pagination
 * @param {*} wi 
 */
function lieuxGetCurrentPage(wi){
    var page = parseInt(wi.$widget.find('.seu-pagin-item.seu-is-active button').attr('data-page'));
    return page;
}

if($('.seu-wi-lieux').length){
    $(document).ready(function(){
        $('.seu-wi-lieux').each(function(index, widget){
            var wi = buildLieux(widget);
            lieuxGoToPage(wi, 1);
            wi.$widget.find('[data-action="next"]').on('click', function(){
                lieuxGoToPage(wi, lieuxGetCurrentPage(wi) + 1);
            });
            wi.$widget.find('[data-action="prev"]').on('click', function(){
                lieuxGoToPage(wi, lieuxGetCurrentPage(wi) - 1);
            });
            wi.$widget.find('.seu-pagin-item button[data-page]').on('click', function(){
                var target = $(this).attr('data-page'); 
                lieuxGoToPage(wi, target);
            })
        });
    });
}