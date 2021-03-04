/*
* Demande de connexion
*/
$(document).on("click", "[name='#Need-connexion']", function(e) {
    e.preventDefault();
    e.stopPropagation();
    $("#myModal").modal();
});

/*
* Demande d'action sur une proposition d'aide désactivée
*/
$(document).on("click", "[name='#inactive-help-proposal']", function(e) {
    e.preventDefault();
    e.stopPropagation();
    $("#inactive-help-proposal-modal").modal();
});

/**
 * Retoune le résultat
 */
function getResult(searchPage, data) {
    if(data != null){
        var nbEntries = data.entries.length;
        // affichage résultat
        var proListing = $('.pro-listing-' + searchPage);
        var row = proListing.data('row');
        proListing.html('');
        var listing;
        if(row == true) {
            listing = '<div class="row pro-wi-grid unstyled" data-page="1">';
        } else {
            listing = '<div class="pro-wi-grid unstyled" data-page="1">';
        }
        var indexGrid = 2;
        $.each(data.entries,function(index, json) {
            if(index > 0 && index % delta == 0){
                if(row == true) {
                    listing += '</div><div class="row pro-wi-grid hidden unstyled" data-page="' + indexGrid + '">';
                } else {
                    listing += '</div><div class="pro-wi-grid hidden unstyled" data-page="' + indexGrid + '">';
                }
                indexGrid++;
            }

            listing += createHelp(json.json);
            
			
        });
        listing += '</div>';
        $('.pro-listing-help').html(listing);

        // gestion de la pagination
        // selecteur de page + Label
        selecteur = '';
        if(nbEntries > delta){
            selecteur =
            '<form method="get">' +
                '<select id="change-page" name="change-page">';
            var indexPage = 1;
            for (indexPage; indexPage <= nbEntries / delta; indexPage++) {
                selecteur +=
                    '<option value="' + indexPage + '">' +
                        'Page ' + indexPage + ' ( ' + (nbEntries < (indexPage * delta) ? nbEntries : delta) + ' )' +
                    '</option>';
            }
            if((indexPage - 1) * delta < nbEntries){
                selecteur +=
                    '<option value="' + indexPage + '">' +
                        'Page ' + indexPage + ' ( ' + nbEntries + ' )' +
                    '</option>';
            }
            selecteur +=
                '</select>' +
            '</form>';
        }
        selecteur += '<p class="hidden-xs"></p>';
        $('.pro-pagination .pull-left').html(selecteur);
        $('#change-page').selectric();

        // liens de navigation
        link = '';
        if(nbEntries > delta){
            link =
            '<ul>' +
                '<!-- Lien vers la premiere page -->' +
                '<li class="pro-disabled" >' +
                    '<a href="#go-to-top" class="hidden-sm hidden-xs pro-first" title="Lien vers la premiere page du Listing" data-action="first">Premier</a>' +
                '</li>' +
                '<!-- Lien vers la page precedente page -->' +
                '<li class="pro-disabled" >' +
                    '<a href="#go-to-top" title="Lien vers la page precedente du Listing" data-action="prev">Précédent</a>' +
                '</li>' +
                '<!-- Lien vers la page suivante -->' +
                '<li>' +
                    '<a href="#go-to-top" title="Lien vers la page suivante du Listing" data-action="next">Suivant</a>' +
                '</li>' +
                '<!-- Lien vers la derniere page -->' +
                '<li>' +
                    '<a href="#go-to-top" class="hidden-sm hidden-xs pro-last" title="Lien vers la derniere page du Listing" data-action="last">Dernier</a>' +
                '</li>' +
            '</ul>';
        }
        $('.pro-pagination .pull-right').html(link);
    }
    buildPaginate();
}

/**
* Création de la vignette d'aide
*/
function createHelp(help){
    var vignette = 
        '<div class="item pro-bloc-card-help pro-theme-embryon vignette" data-linkall="a">' +
            '<div class="wrapper-card-help">' +
                (help.imageURL != "" ? 
                    '<figure role="group" class="fit-cover">' +
                        '<img src="' + help.imageURL + '?imagePreview=1" loading="lazy" width="240" height="250" alt="Image aide"/>' +
                    '</figure>'
                    :
                    ''
                ) +
                '<div>' +
                    '<div class="pro-header-help">' +
                        '<p>Aide proposée par : ' + '<strong>' + help.author + '</strong></p>' +
                        (!help.isActive ?
                            '<div class="pro-statut">' + 
                                '<span style="background : #' + help.activityStatusColor +';">' +
                                    help.activityStatusTitle +
                                '</span>' +
                            '</div>'
                        : '') +
                    '</div>' +
                    '<div class="pro-content-help">' +
                        '<div class="pro-wrapper-meta">' +
                            '<div class="pro-meta">' +
                                (help.localisationLabel != "" ? '<span>' + help.localisationLabel + '</span>' : '') +
                                (help.helpProposalTypeLabel != "" ? '<span>' + help.helpProposalTypeLabel + '</span>' : '') +
                            '</div>' +
                        '</div>' +
                        '<a href="' + homeURL + 'detail-aide/-/entity/id/' + help.id + '" title="lien de la page"><h3>' + help.title + '</h3>' +
                        '<p>'+(help.description.replaceAll(/<[^>]*>/ig, '').length > 300?help.description.replaceAll(/<[^>]*>/ig, '').substr(0,300)+"...":help.description) + '</p></a>' +
                    '</div>' +
                '</div>' +
            '</div>' +
            '<div class="pro-footer-help">' +
                '<p>Publiée le <time datetime="' + help.unformattedCreateDate + '">' + help.createDate + '</time> - ' +
                'Mise à jour le <time datetime="' + help.unformattedCreateDate + '">' + help.createDate + '</time></p>' +
            '</div>' +
        '</div>';

    return vignette;
}


/**
* Création de la pagination
*/
function buildPaginate(){
    $('.pro-search-listing').each(function(index, widget){
        var wi = {
            $widget: $(widget),
            $list: $(widget).find('.pro-wi-grid'),
            $items: $(widget).find('.vignette')
        }

        // Récupérer le nombre de page
        wi.items_count = wi.$items.length;
        wi.page_count = Math.ceil(wi.items_count / delta);

        goToPage(wi, 1);
        wi.$widget.find('[data-action="first"]').on('click', function(){
            goToPage(wi, 1);
        });
        wi.$widget.find('[data-action="prev"]').on('click', function(){
            goToPage(wi, getCurrentPage(wi) - 1);
        });
        wi.$widget.find('[data-action="next"]').on('click', function(){
            goToPage(wi, getCurrentPage(wi) + 1);
        });
        wi.$widget.find('[data-action="last"]').on('click', function(){
            goToPage(wi, wi.page_count);
        });
        wi.$widget.find('#change-page').on('change', function(){
            var target = $(this).val(); 
            goToPage(wi, target);
        })
    });
	//Permet de recharger les liens des vignettes
    th_linkAll();
}

/**
 * @description Récupère la page courante en se basant sur l'état de la pagination
 * @param {*} wi 
 */
function getCurrentPage(wi){
    var page = parseInt(wi.$widget.find('#change-page').val());
    return page;
}

/**
 * @description Affiche la page ayant l'index demandé
 * @param {Object} wi - Objet de configuration
 * @param {Int} index - Index de la page cible
 */
function goToPage(wi, index){
    if(index <= wi.page_count){
        // Gestion de l'affichage des résultats
        wi.$widget.find('.pro-wi-grid').addClass('hidden');
        wi.$widget.find('.pro-wi-grid[data-page="'+index+'"]').removeClass('hidden');

        // Gestion des états first/prev/next/last pagination
        if(index == 1){
            wi.$widget.find('[data-action="first"]').parent('li').addClass('pro-disabled');
            wi.$widget.find('[data-action="prev"]').parent('li').addClass('pro-disabled');
        }else{
            wi.$widget.find('[data-action="first"]').parent('li').removeClass('pro-disabled');
            wi.$widget.find('[data-action="prev"]').parent('li').removeClass('pro-disabled');
        }
        if(index == wi.page_count){
            wi.$widget.find('[data-action="next"]').parent('li').addClass('pro-disabled');
            wi.$widget.find('[data-action="last"]').parent('li').addClass('pro-disabled');
        }else{
            wi.$widget.find('[data-action="next"]').parent('li').removeClass('pro-disabled');
            wi.$widget.find('[data-action="last"]').parent('li').removeClass('pro-disabled');
        }

        // Gestion de l'affichage du selecteur
        wi.$widget.find('#change-page option[value="' + index + '"]').prop('selected', true);
        wi.$widget.find('#change-page').selectric();
    }

    // Gestion affichage du résultat de la pagination
    var indexDernierItemPage = index * delta;
    var pageResult = 'Affichage des résultats ' +
                    (wi.items_count > 0 ? (index > 1 ? (indexDernierItemPage - 2) : '1') : '0') + ' - ' +
                    (wi.items_count < indexDernierItemPage ? wi.items_count : indexDernierItemPage) +
                    ' parmi ' + wi.items_count;
    wi.$widget.find('.pro-pagination .pull-left .hidden-xs').text(pageResult);
    
}

/* DANS LES LISTING DE FACETTE DANS LES BARRES LATERALES, AU CLICK SUR EFFACER, ON DESELECTIONNE LES CHECKBOX ENFANTS ET LA VALEUR DE LA DATE DANS INPUT TEXT */
$('.pro-remove').on('click',function(){
    
    // Utilisé pour les recherches ajax
    if($(this).hasClass('dynamic')){
        // Renvoi la liste des entités demandées
        getSelectedEntries();
    }
});


$(document).ready(function () {
	
    $('.closefirstmodal').click(function () {
        $('#WarningClosePopup').modal('show');
    });

    $('.confirmclosed').click(function () {
        $('#WarningClosePopup').modal('hide');
        $('.pro-modal').modal('hide');
    });
    
});

/**
 * Lors d'un clic d'un lien vers une autre page d'un listing
 */
$(document).on('click', "a[href='#go-to-top']", function(e){
    e.preventDefault();
    var target = $(this).attr('href');
    scrollToAnchor(target);
});

/**
 * Lors d'un clic d'une option sur selecteur de page de listing
 */
$(document).on('change', "#change-page", function(e){
    e.preventDefault();
    scrollToAnchor("#go-to-top");
});

/**
 * @description Scroll vers l'ancre donné en paramètre avec douceur
 * @param {string} wi - ID de l'ancre vers laquelle scroller
 */
function scrollToAnchor(anchorId) {
    // Le sélecteur $(html, body) permet de corriger un bug sur chrome et safari (webkit).
    $('html, body')
       // On arrête toutes les animations en cours. 
       .stop()
       // On fait maintenant l'animation vers le haut (scrollTop) vers notre ancre target.
       .animate({scrollTop: $(anchorId).offset().top}, 1000 );
}