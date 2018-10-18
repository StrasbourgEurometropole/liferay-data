// Initialisation des variables de références
var leafletMap = null;
var markersCluster = null;

// Listes des entités
var projects = null;
var participations = null;
var petitions = null;
var events = null;

// Listes des marqueurs
var projectMarkers = [];
var participationMarkers = [];
var petitionMarkers = [];
var eventMarkers = [];

// Listes de éléments sélectionées
var selectedProjectIds = [];
var selectedParticipationIds = [];
var selectedPetitionIds = [];
var selectedEventIds = [];

var entityType = {
		PROJECT : 'project',
		PARTICIPATION : 'participation',
		PETITION : 'petition',
		EVENT : 'event'
}

/**
 * Supprime l'affichage des éléments
 */
function removeFilterElements() {
    
	$("input[id^='project_']").each(function() {
		$(this).parent().remove();
	});

	$("input[id^='participation_']").each(function() {
		$(this).parent().remove();
	});

	$("input[id^='petition_']").each(function() {
		$(this).parent().remove();
	});

	$("input[id^='event_']").each(function() {
		$(this).parent().remove();
	});

}

/**
 * Supprime l'affichage des marqueurs selon le nom de l'entité demandée
 * @notes : Récursif selon la hiérarchie des entités 
 */
function removeMarkerElements(entityName) {
    
	switch (entityName) {
		case entityType.PROJECT:
			if (projectMarkers != null) {
				projectMarkers.forEach(function(projectMarker) {
					markersCluster.removeLayer(projectMarker);
				});
				projectMarkers = [];
			}
			break;
			
		case entityType.PARTICIPATION:
			if (participationMarkers != null) {
				participationMarkers.forEach(function(participationMarker) {
					markersCluster.removeLayer(participationMarker);
				});
				participationMarkers = [];
			}
			break;
		
		case entityType.PETITION:
			if (petitionMarkers != null) {
				petitionMarkers.forEach(function(petitionMarker) {
					markersCluster.removeLayer(petitionMarker);
				});
				petitionMarkers = [];
			}
			break;
			
		case entityType.EVENT:
			if (eventMarkers != null) {
				eventMarkers.forEach(function(eventMarker) {
					markersCluster.removeLayer(eventMarker);
				});
				eventMarkers = [];
			}
		    break;
	}
}

/**
 * Mise à jour de la liste des filtres
 */ 
function updateFilterElements(onReady) {
	
	// Suppression des anciens filtres
    removeFilterElements();
    
    // Initialisation de la nouvelle sauvegarde des éléments séléctionnés
    var refreshedSelectedProjectIds = [];
    var refreshedSelectedParticipationIds = [];
    var refreshedSelectedPetitionIds = [];
    var refreshedSelectedEventIds = [];
    
    var checker;
	
	if (projects != null) {
		projects.forEach(function(project, index) {
			checker =  "";
			if (onReady || selectedProjectIds.indexOf(project.id) > -1) {
				$("input[id='project_" + index + "']").prop('checked', true);
				checker = "checked";
			}
			$("fieldset[id='projects_fieldset']").append(
				"<div>" + 
                    "<input type='checkbox' id='project_" + project.id + "' class='hide-checkbox' value='" + project.id + "' " + checker + ">" +
                    "<label for='project_" + project.id + "'>" + project.title + "</label>" +
                "</div>"
			);
		});
	}

	if (participations != null) {
		participations.forEach(function(participation, index) {
			checker =  "";
			if (onReady ||selectedParticipationIds.indexOf(participation.id) > -1) {
				refreshedSelectedParticipationIds.push(participation.id);
				checker = "checked";
			}
			$("fieldset[id='participations_fieldset']").append(
				"<div>" + 
                    "<input type='checkbox' id='participation_" + participation.id + "' class='hide-checkbox' value='" + participation.id + "' " + checker + ">" +
                    "<label for='participation_" + participation.id + "'>" + participation.title + "</label>" +
                "</div>"
			);
		});
	}

	if (petitions != null) {
		petitions.forEach(function(petition, index) {
			checker =  "";
			if (onReady ||selectedPetitionIds.indexOf(petition.id) > -1) {
				refreshedSelectedPetitionIds.push(petition.id);
				checker = "checked";
			}
			$("fieldset[id='petitions_fieldset']").append(
				"<div>" + 
	                "<input type='checkbox' id='petition_" + petition.id + "' class='hide-checkbox' value='" + petition.id + "' " + checker + ">" +
	                "<label for='petition_" + petition.id + "'>" + petition.title + "</label>" +
	            "</div>"
			);
		});
	}

	if (events != null) {
		events.forEach(function(event, index) {
			checker =  "";
			if (onReady ||selectedEventIds.indexOf(event.id) > -1) {
				refreshedSelectedEventIds.push(event.id);
				checker = "checked";
			}
			$("fieldset[id='events_fieldset']").append(
				"<div>" + 
                    "<input type='checkbox' id='event_" + event.id + "' class='hide-checkbox' value='" + event.id + "' " + checker + ">" +
                    "<label for='event_" + event.id + "'>" + event.title.fr_FR + "</label>" +
                "</div>"
			);
		});
	}
	
	// Mise à jour de la sauvegarde des éléments sélectionnés
	selectedProjectIds = refreshedSelectedProjectIds;
	selectedParticipationIds = refreshedSelectedParticipationIds;
	selectedPetitionIds = refreshedSelectedPetitionIds;
	selectedEventIds = refreshedSelectedEventIds;

}

/**
 * Mise à jour de la liste des filtres
 * @param entityName Nom de l'entité à partir duquel mettre à jour
 */
function updateMarkerElements(entityName) {
    
	removeMarkerElements(entityName);
	
	switch (entityName) {
		case entityType.PROJECT:
			if (projects != null) {
				// Parcours des projets
				projects.forEach(function(project) {
					// Le projet doit-il être affiché ?
					if (checkPrintatorState(entityType.PROJECT) && checkMarkerState(entityType.PROJECT, project.id)) {
						// Parcours des lieux du projet
						project.placitPlaces.forEach(function(placitPlace) {
							// Le lieux est-il repérable ?
							if (placitPlace.mercatorY != 0 && placitPlace.mercatorX != 0) {
								// Création du marqueur
								var marker = getProjectMarker(project, [placitPlace.mercatorY, placitPlace.mercatorX]);
								// Ajout du marqueur sur la carte
								markersCluster.addLayer(marker);
								// Ajout du marqueur dans le tableau de références
								projectMarkers.push(marker);
							}
						});
					}
				});
			}
			break;

		case entityType.PARTICIPATION:
			// Même processus que l'entité Project
			if (participations != null) {
				participations.forEach(function(participation, index) {
					if (checkPrintatorState(entityType.PARTICIPATION) && checkMarkerState(entityType.PARTICIPATION, participation.id)) {
						participation.placitPlaces.forEach(function(placitPlace) {
							if (placitPlace.mercatorY != 0 && placitPlace.mercatorX != 0) {
								var marker = getParticipationMarker(participation, [placitPlace.mercatorY, placitPlace.mercatorX]);
								markersCluster.addLayer(marker);
								participationMarkers.push(marker);
							}
						});
					}
				});
			}
			break;
			
		case entityType.PETITION:
			// Même processus que l'entité Project
			if (petitions != null) {
				petitions.forEach(function(petition, index) {
					if (checkPrintatorState(entityType.PETITION) && checkMarkerState(entityType.PETITION, petition.id)) {
						petition.placitPlaces.forEach(function(placitPlace) {
							if (placitPlace.mercatorY != 0 && placitPlace.mercatorX != 0) {
								var marker = getPetitionMarker(petition, [placitPlace.mercatorY, placitPlace.mercatorX]);
								markersCluster.addLayer(marker);
								petitionMarkers.push(marker);
							}
						});
					}
				});
			}
			break;
			
		case entityType.EVENT:
			// Même processus que l'entité Project
			if (events != null) {
				events.forEach(function(event, index) {
					if (checkPrintatorState(entityType.EVENT) && checkMarkerState(entityType.EVENT, event.id)) {
						if (event.mercatorY != 0 && event.mercatorX != 0) {
							var marker = getEventMarker(event);
							markersCluster.addLayer(marker);
							eventMarkers.push(marker);
						}
					}
				});
			}
			break;
	}
}

/**
 * Vérification de l'état de la checkbox d'affichage d'un type entité
 * @param 	entityName : Nom de l'entité à sonder
 * @returns Booléen
 */
function checkPrintatorState(entityName) {
	return $("input[id^='" + entityName + "s_printator_mk1']").is(':checked');
}

/**
 * Renvoi la liste des IDs des entités demandées
 * @param 	entityName : Nom de l'entité à sonder
 * 			entityId : PK de l'élément à sonder
 * @returns Booléen
 */
function checkMarkerState(entityName, entityId) {
	return $("input[id^='" + entityName + "_" + entityId + "']").is(':checked');
}

/**
 * Sauvegarde les éléments sélectionnés 
 */
function saveSelectedFilters() {
	
	selectedProjectIds = [];
	selectedParticipationIds = [];
	selectedPetitionIds = [];
	selectedEventIds = [];
	
	$("input[id^='project_']:checked").each(function() {
		selectedProjectIds.push(this.value);
	});
	$("input[id^='participation_']:checked").each(function() {
		selectedParticipationIds.push(this.value);
	});
	$("input[id^='petition_']:checked").each(function() {
		selectedPetitionIds.push(this.value);
	});
	$("input[id^='event_']:checked").each(function() {
		selectedEventIds.push(this.value);
	});

}

$(document).ready(function() {
	// Création de la carte au centre de strasbourg
    leafletMap = getLeafletMap();
    markersCluster = L.markerClusterGroup();
    leafletMap.addLayer(markersCluster);
    
    // Cache du bouton de zoom de map
    $('.leaflet-control-fullscreen-button').hide();
    
    // Requête d'affichage de l'ensemble des filtres et marqueurs
    refreshEntitiesSelectionByDistrict(-1, true);
});

/**
 * Mettre à jour l'ensemble des éléménts (entités, filtres, marqueurs) selon le quartier choisi
 * @param districtId Identifiant de la catégorie du teritoire demandé en filtre
 */
function refreshEntitiesSelectionByDistrict(districtId, onReady = false) {
	AUI().use('aui-io-request', function(A) {
		A.io.request(changeDistrictSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_map_search_asset_MapSearchAssetPortlet_selectedDistrictId : districtId
			},
			on: {
                success: function(e) {
                	saveSelectedFilters();
                	
                	var data = this.get('responseData');
                	projects = data.projects;
                	participations = data.participations;
                	petitions = data.petitions;
                	events = data.events;
                	
                	updateFilterElements(onReady);
                	
                	for (var key in entityType) {
                		updateMarkerElements(entityType[key]);
                	}
			 	}
			 }
		});
	});
}

/**
 * Lors d'une selection de quartier
 */
$('#district').change(function() {
	var selectedDistrictId = this.value;
	
	refreshEntitiesSelectionByDistrict(selectedDistrictId, false);
});

/**
 * Lors d'un acordeonage ou desacordeaonage d'une liste
 */
$("input[id$='_printator_mk1']").change(function() {
	var selectedEntity = this.value;
	
	updateMarkerElements(selectedEntity);
});

/**
 * Lors du clic sur une itération d'entité
 */
$(document).on('change', "fieldset[id$='_fieldset'] > div > input", function(){
	var selectedEntity = this.id.substring(0, this.id.indexOf("_"));
	
	updateMarkerElements(selectedEntity);
});
