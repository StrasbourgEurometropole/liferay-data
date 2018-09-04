// Initialisation des variables de références
var resultEntries = null;

var entityClassName = {
		ARTICLE : 'com.liferay.journal.model.JournalArticle',
		FILE : 'com.liferay.document.library.kernel.model.DLFileEntry',
		PLACE : 'eu.strasbourg.service.place.model.Place',
		EVENT : 'eu.strasbourg.service.agenda.model.Event',
		MANIFESTATION : 'eu.strasbourg.service.agenda.model.Manifestation',
		LINK : 'eu.strasbourg.service.link.model.Link',
		ARTWORK : 'eu.strasbourg.service.artwork.model.Artwork',
		ARTWORK_COLLECTION : 'eu.strasbourg.service.artwork.model.ArtworkCollection',
		VIDEO : 'eu.strasbourg.service.video.model.Video',
		VIDEO_GALLERY : 'eu.strasbourg.service.video.model.VideoGallery',
		EDITION : 'eu.strasbourg.service.edition.model.Edition',
		EDITION_GALLERY : 'eu.strasbourg.service.edition.model.EditionGallery',
		PROJECT : 'eu.strasbourg.service.project.model.Project',
		PARTICIPATION : 'eu.strasbourg.service.project.model.Participation',
		PETITION : 'eu.strasbourg.service.project.model.Petition'
}

/**
 * Supprime l'affichage de la totalité des vignettes
 */
function removeAllThumbnails() {
	$("#dynamic-search-asset-results").empty();
}

/**
 * Renvoi la liste des types entités demandées
 * @return liste des classNames sous forme d'une chaine de caractères séparée par des ","
 */
function getSelectedClassNames() {
	var results = "";
	
	$("input[id^='dynamic_search_type_']:checked").each(function() {
		results =  results.concat(this.value, ',');
	});
	
	return results;
}

/**
 * Creation d'une vignette représentant un projet donné
 */
function createProjectThumbnail(project) {
	
}

/**
 * Creation d'une vignette représentant une participation donnée
 */
function createParticipationThumbnail(participation) {
	var footerContent = '';

    if (participation.statusCode === "soon_arrived") {
        footerContent = 
            '<div class="pro-footer-participation pro-participation-soon">' + 
                '<div class="pro-avis"><span class="pro-like">' + participation.nbLikes + '</span><span class="pro-dislike">' + participation.nbDislikes + '</span></div>' + 
                '<p>Bientôt disponible</p>' +
            '</div>';
    } else if (participation.statusCode === "finished") {
        footerContent = 
            '<div class="pro-footer-participation pro-participation-deadline">' + 
                '<div class="pro-avis"><span class="pro-like">' + participation.nbLikes + '</span><span class="pro-dislike">' + participation.nbDislikes + '</span></div>' + 
                '<p>Participation terminée, merci de votre participation</p>' +
            '</div>';
    } else {
        footerContent = '<div class="pro-footer-participation"><span class="pro-form-style">Réagissez...</span></div>';
    }

    var colorHack = 
        '<style style="display: none" >' +
            '.type-color-hexa-' + participation.typeColor + '>div:before {' +
                'background:#'+ participation.typeColor + ' !important;' +
            '}' +
        '</style>';
    
    return '<div class="pro-vignette-map-inte">' +
			    '<a href="' + participation.link + '" class="item pro-bloc-card-participation pro-theme-information type-color-hexa-' + participation.typeColor + '" data-linkall="a">' +
			    '<div>' +
			        '<div class="pro-header-participation">' + 
			            '<figure><img src="' + participation.imageURL + '" width="40" height="40" alt="Arrière plan page standard"/></figure>' +
			            '<p>Participation publiée par :</p><p><strong>' + participation.author + '</strong></p>' +
			            '<div class="pro-info-top-right"><span class="pro-encart-theme" style="background : #' + participation.typeColor + '">' + participation.typeLabel + '</span></div>' +
			        '</div>' +
			        '<div class="pro-content-participation">' +
			            '<div class="pro-meta"><span>' + participation.districtsLabel + '</span><span>' + participation.thematicsLabel + '</span>' +
			            '<span>' + participation.statusLabel + '</span><span>' + participation.projectName + '</span></div>' +
			            '<h3>' + participation.title + '</h3>' +
			            '<span class="pro-time">Publiée le <time datetime="2018-01-10">' + participation.createDate + '</time>' +
			            ' / <span class="pro-duree">' + participation.statusDetailLabel + '</span></span></div>' +
			            footerContent +
			    '</div></a>' + 
			'</div>' + 
			colorHack
}

/**
 * Creation d'une vignette représentant une pétition donnée
 */
function createPetitionThumbnail(petition) {
	return '<div class="item pro-bloc-card-petition"><a href="' + link + '">' +
			    '<div class="pro-header-petition">' +
			        '<figure role="group"></figure> ' +
			        '<p>Pétition publiée par :</p><p><strong>' + author + '</strong></p>' +
			    '</div>' +
			    '<div class="pro-content-petition">' +
			        '<h3>' + title + '</h3><p>Pétition adressée à <u>' + place + '</u></p>' +
			        '<span class="pro-time">Publiée le <time datetime="2018-01-10">' + publishDate + '</time> / ' +
			        '<span class="pro-duree">' + durationLabel + '</span></span>' +
			    '</div> ' +
			    '<div class="pro-footer-petition">' +
			        '<div class="pro-progress-bar">' +
			            '<div class="pro-progress-container"><div style="width:' + progress +'%"></div>' +
			        '</div>' +
			        '<p class="pro-txt-progress"><strong>' + nbSub + '</strong> Signataire(s) sur ' + nbGoal + ' nécessaires</p> ' +
			    '</div>' +
			'</div></a></div>'
}

/**
 * Creation d'une vignette représentant un événement donné
 */
function createEventThumbnail(event) {
	var activePart = event.isUserPart ? "active" : "";
	
	return '<div class="pro-vignette-map-inte">' + 
			    '<a href="' + event.link + '" title="lien de la page" class="pro-bloc-card-event"><div>' +
				    '<div class="pro-header-event">' +
				        '<span class="pro-ico"><span class="icon-ico-conference"></span></span>' +
				        '<span class="pro-time">Le <time datetime="2018-01-10">' + event.firstDate + '</time></span>' +
				        '<p>À : ' + event.completeAddress + '</p>' +
				        '<h3>' + event.title.fr_FR + '</h3>' +
				    '</div>' +
				    '<div class="pro-footer-event">' +
				        '<span class="pro-btn-action ' + activePart + '">Je participe</span>' +
				        '<span class="pro-number"><strong>' + event.nbPart + '</strong> Participant(s)</span>' +
				    '</div>' +
				'</div></a>' +
			'</div>';
}

/**
 * Met à jour l'affichage des vignettes de résultats de recherche en créant
 * les vignettes correpondantes aux différents types d'entités rencontrés 
 */
function updateResultThumbnails() {
	removeAllThumbnails();
	
	resultEntries.forEach(function(entry) {
		
		switch (entry.className) {
			case entityClassName.ARTICLE :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.FILE :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.PLACE :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.EVENT :
				createEventThumbnail(entry);
				break;
			case entityClassName.MANIFESTATION :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.LINK :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.ARTWORK :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.ARTWORK_COLLECTION :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.VIDEO :
				createVideoThumbnail(entry);
				break;
			case entityClassName.VIDEO_GALLERY :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.EDITION :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.EDITION_GALLERY :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
				break;
			case entityClassName.PROJECT :
				createProjectThumbnail(entry);
				break;
			case entityClassName.PARTICIPATION :
				createParticipationThumbnail(entry);
				break;
			case entityClassName.PETITION :
				createPetitionThumbnail(entry);
				break;
			default :
				console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
	});
}

/**
 * Lors d'une soumission de formulaire de recherche dynamique
 */
$('#dynamic-search-submit').click(function(event) {
	
	event.preventDefault();
	
	var selectedClassNames = getSelectedClassNames();
	var keywords = $("input[name='dynamic-search-keywords']").val();
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(searchSubmitURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_dynamic_search_asset_DynamicSearchAssetPortlet_INSTANCE_BigBrotherIsWatchingYou_selectedClassNames : selectedClassNames,
				_eu_strasbourg_portlet_dynamic_search_asset_DynamicSearchAssetPortlet_INSTANCE_BigBrotherIsWatchingYou_keywords : keywords
			},
			on: {
                success: function(e) {                	
                	resultEntries = this.get('responseData');
                	
                	updateResultThumbnails();
			 	}
			 }
		});
	});
});