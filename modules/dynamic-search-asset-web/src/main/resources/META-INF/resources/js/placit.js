// Initialisation des variables de références
var resultEntries = [];

// 'Enum' de tous les classNames des entité pouvant être rencontrées
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
		PETITION : 'eu.strasbourg.service.project.model.Petition',
		BUDGET : 'eu.strasbourg.service.project.model.BudgetParticipatif',
		INITIATIVE : 'eu.strasbourg.service.project.model.Initiative'
}

/**
 * Supprime l'affichage de la totalité des vignettes
 */
function removeAllThumbnails() {
	$("#dynamic-search-asset-results").empty();
}

/**
 * Ajoute une vignette dans conteneur de résultats
 */
function addThumbnail(thumbnail) {
	$("#dynamic-search-asset-results").append(thumbnail);
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
 * Creation d'une vignette représentant un article donné
 */
function createArticleThumbnail(article) {
	var articleThumbnail = 
		'<div class="col-lg-4 col-sm-6 col-xs-12">' +
		    '<a href="' + article.link + '" title="Lien vers la page : ' + article.title + '" class="pro-bloc-actu">' +
		        '<div class="img">' +
		            '<figure role="group">' +
		                '<img src="' + article.imageURL + '" alt="Image article" width="360" height="174" class="fit-cover"/>' +
		            '</figure>' +
		        '</div>' +
		        '<div class="content">' +
		            '<span class="publication">Publiée le 04 décembre 2017</span>' +
		            '<h3>' + article.title + '</h3>' +
		            '<p>' + article.chapo.replace(/(<([^>]+)>)/ig,""); + '</p>' +
		            '<span class="link">Lire la suite</span>' +
		        '</div>' +
		    '</a>' +
		'</div>';
	
	addThumbnail(articleThumbnail);
}

/**
 * Creation d'une vignette représentant une vidéo donnée
 */
function createVideoThumbnail(video) {
	var videoThumbnail = 
		'<div class="col-lg-4 col-sm-6 col-xs-12">' +
		    '<div class="pro-card-video" data-linkall="a">' +
		        '<div class="pro-header">' +
		            '<figure class="fit-cover" role="group">' +
		                '<img alt="Image" width="280" height="175" src="' + video.imageURL + '">' +
		            '</figure>' +
		            '<span class="icon-ico-lecteur"></span>' +
		        '</div>' +
		        '<div class="pro-meta-avis">' +
		            '<a href="' + video.link + '" title="Vers la page ' + video.title + '"><h3>' + video.title + '</h3></a>' +
		            '<div class="pro-avis">' +
		                '<a class="pro-like">' + video.nbLikes + '</a>' +
		                '<a class="pro-dislike">' + video.nbDislikes + '</a>' +
		            '</div>' +
		            '<span class="pro-view">' + video.nbViews + ' vues</span>' +
		        '</div>' +
		    '</div>' +
		'</div>';
	
	addThumbnail(videoThumbnail);
}

/**
 * Creation d'une vignette représentant un projet donné
 */
function createProjectThumbnail(project) {
	var projectThumbnail = 
		'<div class="col-lg-4 col-sm-6 col-xs-12">' +
		    '<a href="' + project.link + '" title="Lien vers la page : ' + project.title + '" class="pro-bloc-actu">' +
		        '<div class="img">' +
		            '<figure role="group">' +
		                '<img src="' + project.imageURL + '" alt="Image article" width="360" height="174" class="fit-cover"/>' +
		            '</figure>' +
		        '</div>' +
		        '<div class="content">' +
		            '<span class="publication">Publiée le 04 décembre 2017</span>' +
		            '<h3>' + project.title + '</h3>' +
		            '<p>' + project.description.replace(/(<([^>]+)>)/ig,"") + '</p>' +
		            '<span class="link">Lire la suite</span>' +
		        '</div>' +
		    '</a>' +
		'</div>';
	
	addThumbnail(projectThumbnail);
}

/**
 * Creation d'une vignette représentant une participation donnée
 */
function createParticipationThumbnail(participation) {
	var footerContent = '';

	// Adaptation du footer de la vignette selon le statut de la participation
    switch(participation.statusCode) {
    	case "soon_arrived" :
	        footerContent = 
	            '<div class="pro-footer-participation pro-participation-soon">' + 
	                '<div class="pro-avis"><span class="pro-like">' + participation.nbLikes + '</span>' +
	                '<span class="pro-dislike">' + participation.nbDislikes + '</span></div>' + 
	                '<p>Bientôt disponible</p>' +
	            '</div>';
	        break;
    	case "finished" :
	        footerContent = 
		        '<div class="pro-footer-participation pro-participation-deadline">' +
			        '<div class="pro-avis">' +
			            '<span class="pro-like">' + participation.nbLikes + '</span>' +
			            '<span class="pro-dislike">' + participation.nbDislikes + '</span>' +
			        '</div>' +
			        '<p>Participation terminée</p>' +
			    '</div>';
	        break;
    	case "new" :
    	case "in_progress" :
    	case "soon_finished" :
    		footerContent =
    	        '<div class="pro-footer-participation">' +
    		        '<a href="' + participation.link + '#pro-link-commentaire" class="pro-form-style"' +
    		           'title="Lien vers la page détail Participation - Lien des commentaires">Réagissez...</a>' +
    		    '</div>';
    		break;
    }

    // Création du Hack CSS permettant la colorisation de la vignette selon le type de participation
    var colorHack = 
        '<style style="display: none" >' +
            '.type-color-hexa-' + participation.typeColor + '>div:before {' +
                'background:#'+ participation.typeColor + ' !important;' +
            '}' +
        '</style>';
    
    var participationThumbnail = 
    	'<div class="col-lg-4 col-sm-6 col-xs-12">' +
		    '<div class="item pro-bloc-card-participation type-color-hexa-' + participation.typeColor + '" data-linkall="a">' +
		        '<div>' +
		            '<div class="pro-header-participation">' +
		                '<figure role="group">' +
		                    '<img src="' + participation.authorImageURL + '" width="40" height="40" alt="Image de la participation"/>' +
		                '</figure>' +
		                '<p>Concertation publiée par :</p>' +
		                '<p><strong>' + participation.author + '</strong></p>' +
		                '<div class="pro-info-top-right">' +
		                    '<span class="pro-encart-theme" style="background : #' + participation.typeColor + '">' + participation.typeLabel + '</span>' +
		                '</div>' +
		            '</div>' +
		            '<div class="pro-content-participation">' +
		                '<a href="' + participation.link + '" title="Lien vers le détail de la participation">' + 
		                	'<h3>' + participation.title + '</h3>' + 
		                '</a>' +
		                '<span class="pro-time">Publiée le <time datetime="' + participation.createDate + '">' + participation.createDate + '</time>';
    if(participation.statusDetailLabel != "" && participation.statusDetailLabel != undefined)
        participationThumbnail += ' - <span class="pro-duree">' + participation.statusDetailLabel + '</span></span>' ;
    participationThumbnail +=
		            '</div>' +
		            footerContent +
		        '</div>' +
		    '</div>' +
		'</div>' + colorHack;
		
	addThumbnail(participationThumbnail);
}

/**
 * Creation d'une vignette représentant une pétition donnée
 */
function createPetitionThumbnail(petition) {
	var petitionThumbnail =
		'<div class="col-lg-4 col-sm-6 col-xs-12">' +
		    '<div class="item pro-bloc-card-petition" data-linkall="a">' +
		        '<div class="pro-header-petition">' +
		            '<figure role="group">' +
		                '<img src="' + petition.imageURL + '" width="40" height="40" alt="Arrière plan page standard"/>' +
		            '</figure>' +
		            '<p>Pétition publiée par :</p>' +
		            '<p><strong>' + petition.userName + '</strong></p>' +
		        '</div>' +
		        '<div class="pro-content-petition">' +
		            '<a href="' + petition.link + '" title="lien de la page"><h3>' + petition.title + '</h3></a>' +
		            '<span class="pro-time">Publiée le <time datetime="2018-01-10">' + petition.createDate + '</time>' +
		            '/ <span class="pro-duree">' + petition.proDureeFR + '</span></span>' +
		        '</div>' +
		        '<div class="pro-footer-petition">' +
		            '<div class="pro-progress-bar">' +
		                '<div class="pro-progress-container">' +
		                    '<div style="width:' + petition.pourcentageSignature + '%"></div>' +
		                '</div>' +
		                '<p class="pro-txt-progress"><strong>' + petition.nombreSignature + 
		                '</strong> Signataire(s) sur ' + petition.quotaSignature + ' nécessaires</p>' +
		            '</div>' +
		        '</div>' +
		    '</div>' +
		'</div>';

	addThumbnail(petitionThumbnail);
}

/**
 * Creation d'une vignette représentant un projet citoyen (Budget participatif)
 */
function createBudgetParticipatifThumbnail(bp) {
	
	// Classe CSS du statut du budget
    var cssClassBPStatus = "";
	var footer = "";
	
	if(bp.isNotDoable)
	{
		footer = "<p>Ce projet a été étudié et déclaré \"" + bp.BPStatus + "\"</p>";
		cssClassBPStatus = "pro-theme-non-faisable";
	}
	else
	{
		footer = "<p><strong>" + bp.nbSupports + "</strong> vote(s) pour ce projet</p>";
		cssClassBPStatus = "pro-theme-faisable";
	}
	
	var bpThumbnail =
		'<div class="col-lg-4 col-sm-6 col-xs-12">' +
		    '<div class="item pro-bloc-card-budget ' + cssClassBPStatus + '"  data-linkall="a">' +
		        '<div class="pro-header-budget">' +
		            '<figure role="group">' +
		                '<img src="' + bp.authorImageURL + '" width="40" height="40" alt="Arrière plan page standard"/>' +
		            '</figure>' +
		            '<p>Projet déposé par :</p>' +
		            '<p><strong>' + bp.author + '</strong></p>' +
		        '</div>' +
		        '<div class="pro-content-budget">' +
		            '<a href="' + bp.link + '" title="lien détail du projet citoyen"><h3>' + bp.title + '</h3></a>' +
		            '<span class="pro-time">Publiée le <time datetime="2018-01-10">' + bp.createDate + '</time>' +
		        '</div>' +
		        '<div class="pro-footer-budget">' + footer +		            
		        '</div>' +
		    '</div>' +
		'</div>';

	addThumbnail(bpThumbnail);
}

/**
 * Creation d'une vignette représentant une initiative donnée
 */
function createInitiativeThumbnail(initiative) {
	var initiativeThumbnail =	
		'<div class="col-lg-4 col-sm-6 col-xs-12">' +
		    '<div class="item pro-bloc-card-initiative" data-linkall="a">' +
		        '<div class="wrapper-card-initiative">' +
		        	(initiative.imageURL != "" ? 
	                    '<figure role="group" class="fit-cover">' +
	                        '<img src="' + initiative.imageURL + '" width="240" height="250" alt="Image atelier"/>' +
	                    '</figure>'
	                    :
	                    ''
	                ) +
		            '<div>' +
		                '<div class="pro-header-initiative">' +
		                    '<figure role="group">' +
		                        '<img src="' + initiative.authorImageURL + '" width="40" height="40" alt="Arrière plan page standard"/>' +
		                    '</figure>' +
		                    '<p>Atelier publié par :</p>' +
		                    '<p><strong>' + initiative.author + '</strong></p>' +
		                '</div>' +
		                '<div class="pro-content-initiative">' +
		                    '<a href="' + initiative.link + '" title="lien de la page"><h3>' + initiative.title + '</h3></a>' +
		                    '<span class="pro-time">Publié le <time datetime="' + initiative.createDate + '">' + initiative.createDate + '</time></span>' +
		                '</div>' +
		            '</div>' +
		        '</div>' +
		        '<div class="pro-footer-initiative">' +
		            '<div class="pro-avis">' +
		                '<span>' + initiative.nbHelps + '</span>' +
		            '</div>' +
		            '<p>Citoyens-nes ont proposé leur aide</p>' +
		        '</div>' +
		    '</div>' +
		'</div>';

	addThumbnail(initiativeThumbnail);
}

/**
 * Creation d'une vignette représentant un événement donné
 */
function createEventThumbnail(event) {
	var activePart = event.isUserPart ? "active" : "";
	
	var eventThumbnail =
		'<div class="col-lg-4 col-sm-6 col-xs-12">' + 
		    '<a href="' + event.link + '" title="lien de la page" class="pro-bloc-card-event"><div>' +
			    '<div class="pro-header-event">' +
			        '<span class="pro-ico"><span class="icon-ico-conference"></span></span>' +
			        '<span class="pro-time">' + event.firstDate + '</time></span>' +
			        '<p>À : ' + event.completeAddress + '</p>' +
			        '<h3>' + event.title + '</h3>' +
			    '</div>' +
			    '<div class="pro-footer-event">' +
			        '<span class="pro-btn-action ' + activePart + '">Je participe</span>' +
			        '<span class="pro-number"><strong>' + event.nbPart + '</strong> Participant(s)</span>' +
			    '</div>' +
			'</div></a>' +
		'</div>';
	
	addThumbnail(eventThumbnail);
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
                    case entityClassName.ARTICLE :
                        createArticleThumbnail(entry);
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
                    case entityClassName.BUDGET :
                        createBudgetParticipatifThumbnail(entry);
                        break;
                    case entityClassName.INITIATIVE :
                        createInitiativeThumbnail(entry);
                        break;
                    default :
                        console.warn("Aucune méthode n'a été créée dans ce tempalte pour l'affichage de ce type d'entité : " + entry.className);
                }
            }
	    });
    }
}

/**
 * Lance la recherche en AJAX et demande la mise à jour de l'affichage
 */
function searchRequest() {
	
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
	
}

/**
 * Lors d'une soumission de formulaire de recherche dynamique
 */
$('#dynamic-search-submit').click(function(event) {
	event.preventDefault();
	searchRequest();
});

/**
 * Lors d'une modification de la valeur du champ texte de recherche
 */
$('input[name=dynamic-search-keywords]').on("change paste keyup", function(event) {
	// Si la recherche dynamique est configurée et que l'utilisateur a au moins selectionné trois caractères
	if (dynamicSearch && $(this).val().length > 2) {
		searchRequest();
	}
});

/**
 * Lors d'une selection d'entité
 */
$("input[id^='dynamic_search_type_']").change(function(event) {
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
	$("input[name='dynamic-search-keywords']").val("");
});
