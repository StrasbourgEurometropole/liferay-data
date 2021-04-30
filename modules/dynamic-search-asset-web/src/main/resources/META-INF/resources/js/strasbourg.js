// Initialisation des variables de références
var resultEntries = [];

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
    '<a href="#" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-people"></span>' +
            '<span class="th-title">Prénoom et Nom de l\'élu</span>' +
            '<p>Fonctions</p>' +
            '<span class="th-infos th-localisation">Strasbourg</span>' +
        '</div>' +
        '<span class="th-favoris">Ajouter à mes favoris</span>' +
    '</a>';
	
	addThumbnail(officialThumbnail);
}

/**
 * Creation d'une vignette représentant une édition donnée
 */
function createEditionThumbnail(edition) {
	var editionThumbnail =
    '<a href="#" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-edition"></span>' +
            '<span class="th-title">Le Code Bar</span>' +
            '<p>Bars et restaurants</p>' +
            '<span class="th-infos th-localisation">Strasbourg</span>' +
        '</div>' +
        '<span class="th-favoris">Ajouter à mes favoris</span>' +
    '</a>';

	addThumbnail(editionThumbnail);
}

/**
 * Creation d'une vignette représentant un event donné
 */
function createEventThumbnail(event) {
	var eventThumbnail =
    '<a href="' + event.link + '" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-event"></span>' +
            '<span class="th-title">' + event.title[language] + '</span>' +
            '<p>' + entry.description.fr_FR.replace(/(<([^>]+)>)/ig,"") + '</p>' +
            '<span class="th-infos th-categorie">' + entry.types[language]+ '</span>' +
            '<span class="th-infos th-event">' + entry.schedule[language] + '</span>' +
        '</div>' +
    '<\a>' +
    '<span class="th-favoris">'+
        '<a href="#" class="seu-add-favorites"' +
        'data-type="2"' +
        'data-title="' + event.title[language] + '"' +
        'data-url="' + event.linkAbsolute + '"' +
        'data-id="' + entry.id + '">' +
            '<span>' + Liferay.Language.get('eu.add-to-favorite') + '</span>' +
        '</a>' +
    '</span>';

	addThumbnail(eventThumbnail);
}


/**
 * Creation d'une vignette représentant une manif donnée
 */
function createManifThumbnail(manif) {
	var manifThumbnail =
    '<a href="#" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-event"></span>' +
            '<span class="th-title">Titre</span>' +
            '<p>Description</p>' +
            '<span class="th-infos th-event">date</span>' +
        '</div>' +
        '<span class="th-favoris">Ajouter à mes favoris</span>' +
    '</a>';

	addThumbnail(manifThumbnail);
}

/**
 * Creation d'une vignette représentant une galerie d'édition donnée
 */
function createEditionGalleryThumbnail(editionGallery) {
	var editionGalleryThumbnail =
    '<a href="#" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-galerie"></span>' +
            '<span class="th-title">Le Code Bar</span>' +
            '<p>Bars et restaurants</p>' +
            '<span class="th-infos th-localisation">Strasbourg</span>' +
        '</div>' +
        '<span class="th-favoris">Ajouter à mes favoris</span>' +
    '</a>';

	addThumbnail(editionGalleryThumbnail);
}

/**
 * Creation d'une vignette représentant un lieu donné
 */
function createPlaceThumbnail(place) {
	var placeThumbnail =
    '<a href="#" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-lieu"></span>' +
            '<span class="th-title">Alias</span>' +
            '<p>Description</p>' +
            '<span class="th-infos th-localisation">Strasbourg</span>' +
        '</div>' +
        '<span class="th-favoris">Ajouter à mes favoris</span>' +
    '</a>';

	addThumbnail(placeThumbnail);
}

/**
 * Creation d'une vignette représentant un cours donné
 */
function createCourseThumbnail(course) {
	var courseThumbnail =
    '<a href="#" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-sport"></span>' +
            '<span class="th-title">titre</span>' +
            '<p>description</p>' +
            '<span class="th-infos th-categorie">categ</span>' +
        '</div>' +
        '<span class="th-favoris">Ajouter à mes favoris</span>' +
    '</a>';

	addThumbnail(courseThumbnail);
}

/**
 * Creation d'une vignette représentant une activité donnée
 */
function createActivityThumbnail(activity) {
	var activityThumbnail =
    '<a href="#" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-sport"></span>' +
            '<span class="th-title">titre</span>' +
            '<p>description</p>' +
            '<span class="th-infos th-categorie">categ</span>' +
        '</div>' +
        '<span class="th-favoris">Ajouter à mes favoris</span>' +
    '</a>';

	addThumbnail(activityThumbnail);
}

/**
 * Creation d'une vignette représentant un article donné
 */
function createArticleThumbnail(article) {
	var articleThumbnail =
    '<a href="#" class="th-item-result">' +
        '<div class="th-metas-left">' +
            '<span class="th-picto th-picto-document"></span>' +
            '<span class="th-title">Titre</span>' +
            '<p>Desription</p>' +
            '<span class="th-infos th-event">date publication</span>' +
        '</div>' +
        '<span class="th-favoris">Ajouter à mes favoris</span>' +
    '</a>';

	addThumbnail(articleThumbnail);
}

/**
 * Met à jour l'affichage des vignettes de résultats de recherche en créant
 * les vignettes correpondantes aux différents types d'entités rencontrés 
 */
function updateResultThumbnails() {
    removeAllThumbnails();
	resultEntries.forEach(function(entry) {
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
    });
}

/**
 * Lance la recherche en AJAX et demande la mise à jour de l'affichage
 */
function searchRequest() {

	var selectedClassNames = "eu.strasbourg.service.official.model.Official,eu.strasbourg.service.edition.model.Edition,eu.strasbourg.service.agenda.model.Event,eu.strasbourg.service.agenda.model.Manifestation,eu.strasbourg.service.edition.model.EditionGallery,eu.strasbourg.service.place.model.Place,eu.strasbourg.service.activity.model.ActivityCourse,eu.strasbourg.service.activity.model.Activity,com.liferay.journal.model.JournalArticle,";
	var keywords = $("input[name='th-search']").val();
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(searchSubmitURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_dynamic_search_asset_DynamicSearchAssetPortlet_INSTANCE_DynamicResearch_selectedClassNames : selectedClassNames,
				_eu_strasbourg_portlet_dynamic_search_asset_DynamicSearchAssetPortlet_INSTANCE_DynamicResearch_keywords : keywords
			},
			on: {
                success: function(e) {                	
                	resultEntries = this.get('responseData');

	                $("#th-overlay-nav .th-search-results .th-hide-tablet-p").html(Liferay.Language.get('eu.strasbourg.dynamic-search-strasbourg-result-search') + '<span class="th-result">' + resultEntries.length + '</span>');
                    $("#th-overlay-nav .th-search-results .th-v-tablet-p").html(Liferay.Language.get('eu.strasbourg.dynamic-search-strasbourg-result') + '<span class="th-result">' + resultEntries.length + '</span>');

                    updateResultThumbnails();
			 	}
			}
		});
	});
	
}

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
	// Si la recherche dynamique est configurée et que l'utilisateur a au moins selectionné trois caractères
	if (dynamicSearch && $(this).val().length > 2) {
		searchRequest();
	}
});

/**
 * Reinitialise le contenu du champ de recherche
 * @note Utile dans le cas d'un retour à la page précédente
 */
$(document).ready(function() {
	$("input[name='th-search']").val("");
});
