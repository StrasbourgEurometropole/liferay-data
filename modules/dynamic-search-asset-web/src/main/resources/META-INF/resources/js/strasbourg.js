// Initialisation des variables de références
var resultEntries = [];
var myAjaxRequest;

AUI().use('aui-io-request', function(A) {
    // initialisation de la requête ajax (il ne faut pas oublier de faire un stop car il rentre dans la requête à l'initialisation)
    myAjaxRequest  = A.io.request(searchSubmitURL, {
        method : 'post',
        dataType: 'json',
        on: {
            success: function(e) {
                if(this.getFormattedData() != undefined ){
                    resultEntries = this.get('responseData');

                    if(resultEntries != null){
                        $("#th-overlay-nav .th-search-results .th-hide-tablet-p").html(Liferay.Language.get('eu.strasbourg.dynamic-search-strasbourg-result-search') + ' <span class="th-result">' + resultEntries[0].totalResult + '</span>');
                        $("#th-overlay-nav .th-search-results .th-v-tablet-p").html(Liferay.Language.get('eu.strasbourg.dynamic-search-strasbourg-result') + ' <span class="th-result">' + resultEntries[0].totalResult + '</span>');
                        $("#th-overlay-nav .th-search-results .th-hide-tablet-p").show();
                        $("#th-overlay-nav .th-search-results .th-v-tablet-p").show();

                        updateResultThumbnails();

                        if(resultEntries[0].totalResult > 100){
                            keyword = this.getFormattedData()._eu_strasbourg_portlet_dynamic_search_asset_DynamicSearchAssetPortlet_INSTANCE_DynamicResearch_keywords;
                            link = homeURL + "recherche?_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_keywords=" + keyword + "&p_p_id=eu_strasbourg_portlet_search_asset_SearchAssetPortlet&p_p_lifecycle=1";
                            $("#th-overlay-nav .th-search-results .th-all-results").append('<a href="' + link + '" class="th-item-result"><div class="th-metas-left"><span class="th-title th-more-results">' + Liferay.Language.get('eu.strasbourg.dynamic-search-strasbourg-more-result') + '</span></div></a>');
                        }
                    }else{
                        $("#th-overlay-nav .th-search-results .th-hide-tablet-p").html(Liferay.Language.get('eu.strasbourg.dynamic-search-strasbourg-result-search') + ' <span class="th-result">0</span>');
                        $("#th-overlay-nav .th-search-results .th-v-tablet-p").html(Liferay.Language.get('eu.strasbourg.dynamic-search-strasbourg-result') + ' <span class="th-result">0</span>');
                        $("#th-overlay-nav .th-search-results .th-hide-tablet-p").show();
                        $("#th-overlay-nav .th-search-results .th-v-tablet-p").show();
                        $("#th-overlay-nav .th-search-results .th-all-results").html("<div class='th-title'>Erreur</div>");
                    }
                }
            }
        }
    });
    myAjaxRequest.stop();
});


/**
 * Lors d'une soumission de formulaire de recherche dynamique
 */
$('#th-form-submit').click(function(event) {
    event.preventDefault();
    searchRequest();
});

/**
 * Lors d'une modification de la valeur du champ texte de recherche
 */
$('input[name=th-search]').on("change paste keyup", function(event) {
    // Si la recherche dynamique est configurée et que l'utilisateur a au moins saisi trois caractères
    keyword = $(this).val();
    if (dynamicSearch && keyword.length > 2) {
        setTimeout(launchAjax, 300, keyword);
    }
});

/**
* Si l'utilisateur a modifié sa saisie pendant le setTimeout, on ne fait pas la requête ajax
*/
function launchAjax(keywords){
    if(keywords == $("input[name='th-search']").val()){
        searchRequest();
    }
}

if (!window.userFavorites) {
    window.userFavorites = [];
}

// 'Enum' de tous les classNames des entité pouvant être rencontrées
var entityClassName = {
		OFFICIAL : 'eu.strasbourg.service.official.model.Official',
		EDITION : 'eu.strasbourg.service.edition.model.Edition',
		EVENT : 'eu.strasbourg.service.agenda.model.Event',
		MANIFESTATION : 'eu.strasbourg.service.agenda.model.Manifestation',
		EDITION_GALLERY : 'eu.strasbourg.service.edition.model.EditionGallery',
		PLACE : 'eu.strasbourg.service.place.model.Place',
		COURSE : 'eu.strasbourg.service.activity.model.ActivityCourse',
		ACTIVITY : 'eu.strasbourg.service.activity.model.Activity',
		ARTICLE : 'com.liferay.journal.model.JournalArticle',
}

/**
 * Supprime l'affichage de la totalité des vignettes
 */
function removeAllThumbnails() {
	$("#th-overlay-nav .th-search-results .th-all-results").empty();
}

/**
 * Ajoute une vignette dans conteneur de résultats
 */
function addThumbnail(thumbnail) {
	$("#th-overlay-nav .th-search-results .th-all-results").append(thumbnail);
}

/**
 * Creation d'une vignette représentant un élu donné
 */
function createOfficialThumbnail(official) {
	var officialThumbnail =
    '<a href="' + official.link + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-people"></span>' +
            '<span class="th-title">' + official.firstName + ' ' + official.lastName + '</span>';
    if ((official.fonctionEuro != "" && official.fonctionEuro != undefined) || (official.fonctionCity != "" && official.fonctionCity != undefined)){
        officialThumbnail +=  '<p>';
        if (official.fonctionCity != "" && official.fonctionCity != undefined)
            officialThumbnail +=  official.fonctionCity;
        if (official.fonctionEuro != "" && official.fonctionEuro != undefined && official.fonctionCity != "" && official.fonctionCity != undefined)
            officialThumbnail += ', ';
        if (official.fonctionEuro != "" && official.fonctionEuro != undefined)
            officialThumbnail += official.fonctionEuro ;
        officialThumbnail +=  '</p>';
    }
    if (official.categories != "" && official.categories != undefined){
        officialThumbnail +=
            '<span class="th-infos th-localisation">' + official.categories+ '</span>';
    }
    officialThumbnail +=
        '</div>' +
    '</a>';
	
	addThumbnail(officialThumbnail);
}

/**
 * Creation d'une vignette représentant une édition donnée
 */
function createEditionThumbnail(edition) {
    var isLiked = window.userFavorites.filter(function(favorite){
            return favorite.entityId == edition.id  &&  favorite.typeId == 4;
    });
    var description = edition.description.replace(/(<([^>]+)>)/ig,"");
	var editionThumbnail =
    '<a href="' + edition.link + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-edition"></span>' +
            '<span class="th-title">' + edition.title + '</span>' +
            '<p>' + description + '</p>' +
            '<span class="th-infos th-categorie">' + edition.categories + '</span>' +
            '<span class="th-infos th-event">' + edition.schedule + '</span>' +
        '</div>' +
        '<span class="th-favoris seu-add-favorites ' + (isLiked.length > 0 ?'liked':'') + '"' +
            'data-type="4"' +
            'data-title="' + edition.title + '"' +
            'data-url="' + edition.linkAbsolute + '"' +
            'data-id="' + edition.id + '">' +
                '<span>' + (isLiked.length > 0 ?Liferay.Language.get('eu.remove-from-favorite'):Liferay.Language.get('eu.add-to-favorite')) + '</span>' +
        '</span>'+
    '</a>';

	addThumbnail(editionThumbnail);
}

/**
 * Creation d'une vignette représentant un event donné
 */
function createEventThumbnail(event) {
    var isLiked = window.userFavorites.filter(function(favorite){
            return favorite.entityId == event.id  &&  favorite.typeId == 2;
    });
    var description = event.description.replace(/(<([^>]+)>)/ig,"");
	var eventThumbnail =
    '<a href="' + event.linkStras + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-event"></span>' +
            '<span class="th-title">' + event.title + '</span>' +
            '<p>' + description + '</p>';
    if(event.categories != "" && event.categories != undefined)
        eventThumbnail += '<span class="th-infos th-categorie">' + event.categories + '</span>';
    eventThumbnail +=
            '<span class="th-infos th-event">' + event.schedule + '</span>' +
        '</div>' +
        '<span class="th-favoris seu-add-favorites ' + (isLiked.length > 0 ?'liked':'') + '"' +
            'data-type="2"' +
            'data-title="' + event.title + '"' +
            'data-url="' + event.linkAbsolute + '"' +
            'data-id="' + event.id + '">' +
                '<span>' + (isLiked.length > 0 ?Liferay.Language.get('eu.remove-from-favorite'):Liferay.Language.get('eu.add-to-favorite')) + '</span>' +
        '</span>'+
    '<\a>';

	addThumbnail(eventThumbnail);
}


/**
 * Creation d'une vignette représentant une manif donnée
 */
function createManifThumbnail(manif) {
    var isLiked = window.userFavorites.filter(function(favorite){
            return favorite.entityId == manif.id  &&  favorite.typeId == 12;
    });
    var description = manif.description.replace(/(<([^>]+)>)/ig,"");
	var manifThumbnail =
    '<a href="' + manif.link + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-event"></span>' +
            '<span class="th-title">' + manif.title + '</span>' +
            '<p>' + description + '</p>';
    if(manif.categories != "" && manif.categories != undefined)
        manifThumbnail += '<span class="th-infos th-categorie">' + manif.categories + '</span>';
    if(manif.schedule != "" && manif.schedule != undefined)
        manifThumbnail += '<span class="th-infos th-event">' + manif.schedule + '</span>';
    manifThumbnail +=
        '</div>' +
        '<span class="th-favoris seu-add-favorites ' + (isLiked.length > 0 ?'liked':'') + '"' +
            'data-type="12"' +
            'data-title="' + manif.title + '"' +
            'data-url="' + manif.linkAbsolute + '"' +
            'data-id="' + manif.id + '">' +
                '<span>' + (isLiked.length > 0 ?Liferay.Language.get('eu.remove-from-favorite'):Liferay.Language.get('eu.add-to-favorite')) + '</span>' +
        '</span>' +
    '</a>';

	addThumbnail(manifThumbnail);
}

/**
 * Creation d'une vignette représentant une galerie d'édition donnée
 */
function createEditionGalleryThumbnail(editionGallery) {
    var isLiked = window.userFavorites.filter(function(favorite){
            return favorite.entityId == editionGallery.id  &&  favorite.typeId == 13;
    });
    var description = "";
    if(editionGallery.description != "" &&  editionGallery.description != undefined)
        description = editionGallery.description.replace(/(<([^>]+)>)/ig,"");
	var editionGalleryThumbnail =
    '<a href="' + editionGallery.link + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-edition"></span>' +
            '<span class="th-title">' + editionGallery.title + '</span>';
    if(description != "")
        editionGalleryThumbnail += '<p>' + description + '</p>';
    if(editionGallery.categories != "" && editionGallery.categories != undefined)
        editionGalleryThumbnail += '<span class="th-infos th-categorie">' + editionGallery.categories + '</span>';
    editionGalleryThumbnail +=
        '</div>' +
        '<span class="th-favoris seu-add-favorites ' + (isLiked.length > 0 ?'liked':'') + '"' +
            'data-type="13"' +
            'data-title="' + editionGallery.title + '"' +
            'data-url="' + editionGallery.linkAbsolute + '"' +
            'data-id="' + editionGallery.id + '">' +
                '<span>' + (isLiked.length > 0 ?Liferay.Language.get('eu.remove-from-favorite'):Liferay.Language.get('eu.add-to-favorite')) + '</span>' +
        '</span>' +
    '</a>';

	addThumbnail(editionGalleryThumbnail);
}

/**
 * Creation d'une vignette représentant un lieu donnéeven
 */
function createPlaceThumbnail(place) {
    var isLiked = window.userFavorites.filter(function(favorite){
            return favorite.entityId == place.id  &&  favorite.typeId == 1;
    });
	var placeThumbnail =
    '<a href="' + place.link + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-lieu"></span>' +
            '<span class="th-title">' + place.title + '</span>' +
            '<p>' + place.categories + '</p>';
    if(place.city != "" && place.city != undefined)
        placeThumbnail += '<span class="th-infos th-localisation">' + place.city + '</span>';
    placeThumbnail +=
        '</div>' +
        '<span class="th-favoris seu-add-favorites ' + (isLiked.length > 0 ?'liked':'') + '"' +
            'data-type="1"' +
            'data-title="' + place.title + '"' +
            'data-url="' + place.linkAbsolute + '"' +
            'data-id="' + place.id + '">' +
                '<span>' + (isLiked.length > 0 ?Liferay.Language.get('eu.remove-from-favorite'):Liferay.Language.get('eu.add-to-favorite')) + '</span>' +
        '</span>' +
    '</a>';

	addThumbnail(placeThumbnail);
}

/**
 * Creation d'une vignette représentant un cours donné
 */
function createCourseThumbnail(course) {
    var isLiked = window.userFavorites.filter(function(favorite){
            return favorite.entityId == course.id  &&  favorite.typeId == 11;
    });
	var courseThumbnail =
    '<a href="' + course.link + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-sport"></span>' +
            '<span class="th-title">' + course.title + '</span>' +
            '<span class="th-infos th-categorie">' + course.categories + '</span>' +
        '</div>' +
        '<span class="th-favoris seu-add-favorites ' + (isLiked.length > 0 ?'liked':'') + '"' +
            'data-type="11"' +
            'data-title="' + course.title + '"' +
            'data-url="' + course.linkAbsolute + '"' +
            'data-id="' + course.id + '">' +
                '<span>' + (isLiked.length > 0 ?Liferay.Language.get('eu.remove-from-favorite'):Liferay.Language.get('eu.add-to-favorite')) + '</span>' +
        '</span>' +
    '</a>';

	addThumbnail(courseThumbnail);
}

/**
 * Creation d'une vignette représentant une activité donnée
 */
function createActivityThumbnail(activity) {
    var isLiked = window.userFavorites.filter(function(favorite){
            return favorite.entityId == activity.id  &&  favorite.typeId == 10;
    });
    var description = "";
    if(activity.description != "" &&  activity.description != undefined)
        description = activity.description.replace(/(<([^>]+)>)/ig,"");
	var activityThumbnail =
    '<a href="' + activity.link + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-sport"></span>' +
            '<span class="th-title">' + activity.title + '</span>';
    if(description != "")
        activityThumbnail += '<p>' + description + '</p>';
    activityThumbnail +=
            '<span class="th-infos th-categorie">' + activity.categories + '</span>' +
        '</div>' +
        '<span class="th-favoris seu-add-favorites ' + (isLiked.length > 0 ?'liked':'') + '"' +
            'data-type="10"' +
            'data-title="' + activity.title + '"' +
            'data-url="' + activity.linkAbsolute + '"' +
            'data-id="' + activity.id + '">' +
                '<span>' + (isLiked.length > 0 ?Liferay.Language.get('eu.remove-from-favorite'):Liferay.Language.get('eu.add-to-favorite')) + '</span>' +
        '</span>' +
    '</a>';

	addThumbnail(activityThumbnail);
}

/**
 * Creation d'une vignette représentant un article donné
 */
function createArticleThumbnail(article) {
    var isLiked = window.userFavorites.filter(function(favorite){
            return favorite.entityId == article.id  &&  favorite.typeId == article.type;
    });
    var chapo = "";
    if(article.chapo != "" &&  article.chapo != undefined)
        chapo = article.chapo.replace(/(<([^>]+)>)/ig,"");
	var articleThumbnail =
    '<a href="' + article.link + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-document"></span>' +
            '<span class="th-title">' + article.title + '</span>';
    if(chapo != "")
        articleThumbnail += '<p>' + chapo + '</p>';
    if(article.categories != "" && article.categories != undefined)
        articleThumbnail += '<span class="th-infos th-categorie">' + article.categories + '</span>';
    articleThumbnail +=
            '<span class="th-infos th-event">' + article.modifiedDate + '</span>' +
        '</div>' +
        '<span class="th-favoris seu-add-favorites ' + (isLiked.length > 0 ?'liked':'') + '"' +
            'data-type="' + article.type + '"' +
            'data-title="' + article.title + '"' +
            'data-url="' + article.link + '"' +
            'data-group-id="' + article.groupId + '"' +
            'data-id="' + article.id + '">' +
                '<span>' + (isLiked.length > 0 ?Liferay.Language.get('eu.remove-from-favorite'):Liferay.Language.get('eu.add-to-favorite')) + '</span>' +
        '</span>' +
    '</a>';

	addThumbnail(articleThumbnail);
}

/**
 * Met à jour l'affichage des vignettes de résultats de recherche en créant
 * les vignettes correpondantes aux différents types d'entités rencontrés 
 */
function updateResultThumbnails() {
    removeAllThumbnails();

	if(resultEntries != null){
        resultEntries.forEach(function(entry) {
            if(entry.className != undefined){
                switch (entry.className) {
                    case entityClassName.OFFICIAL :
                        createOfficialThumbnail(entry);
                        break;
                    case entityClassName.EDITION :
                        createEditionThumbnail(entry);
                        break;
                    case entityClassName.EVENT :
                        createEventThumbnail(entry);
                        break;
                    case entityClassName.MANIFESTATION :
                        createManifThumbnail(entry);
                        break;
                    case entityClassName.EDITION_GALLERY :
                        createEditionGalleryThumbnail(entry);
                        break;
                    case entityClassName.PLACE :
                        createPlaceThumbnail(entry);
                        break;
                    case entityClassName.COURSE :
                        createCourseThumbnail(entry);
                        break;
                    case entityClassName.ACTIVITY :
                        createActivityThumbnail(entry);
                        break;
                    case entityClassName.ARTICLE :
                        createArticleThumbnail(entry);
                        break;
                    default :
                        console.warn("Aucune méthode n'a été créée dans ce template pour l'affichage de ce type d'entité : " + entry.className);
                }
            }
        });
    }
}

/**
 * Lance la recherche en AJAX et demande la mise à jour de l'affichage
 */
function searchRequest() {
	AUI().use('aui-io-request', function(A) {
	    // arrête l'ancien appel s'il n'est pas fini
        $("#th-overlay-nav .th-search-results .th-all-results").remove(".loading-animation, .loading-small-animation");
        myAjaxRequest.stop();
        if($("#th-overlay-nav .th-search-results .th-hide-tablet-p").text().length > 0 ){
            $("#th-overlay-nav .th-search-results .th-all-results").prepend('<div class="loading-small-animation"><div></div></div>');
        }else
            $("#th-overlay-nav .th-search-results .th-all-results").html('<div class="loading-animation"><div></div></div>');
        myAjaxRequest.set('data', {
            _eu_strasbourg_portlet_dynamic_search_asset_DynamicSearchAssetPortlet_INSTANCE_DynamicResearch_keywords : $("input[name='th-search']").val()
        });
        myAjaxRequest.start();
	});

}

/**
 * Reinitialise le contenu du champ de recherche
 * @note Utile dans le cas d'un retour à la page précédente
 */
$(document).ready(function() {
	$("input[name='th-search']").val("");
	$("#th-overlay-nav .th-search-results .th-hide-tablet-p").hide();
    $("#th-overlay-nav .th-search-results .th-v-tablet-p").hide();
});
