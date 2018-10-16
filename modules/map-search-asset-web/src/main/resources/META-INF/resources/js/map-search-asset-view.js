// Initialisation des variables de références
var leafletMap = null;
var markersCluster = null;

var projects = null;
var participations = null;
var petitions = null;
var events = null;

var projectMarkers = [];
var participationMarkers = [];
var petitionMarkers = [];
var eventMarkers = [];

var selectedProjectIds = [];
var selectedParticipationIds = [];
var selectedPetitionIds = [];
var selectedEventIds = [];

var entityType = {
		PROJECT : 'project',
		PARTICIPATION : 'participation',
		PETITION : 'petition',
		INITIATIVE : 'initiative',
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
    
    var entityName = (typeof entityName !== 'undefined') ? entityName : entityType.PROJECT;
    
	switch (entityName) {
		case entityType.PROJECT:
			if (projectMarkers != null) {
				projectMarkers.forEach(function(projectMarker) {
					leafletMap.removeLayer(projectMarker);
				});
				projectMarkers = [];
			}
			break;
			
		case entityType.PARTICIPATION:
			if (participationMarkers != null) {
				participationMarkers.forEach(function(participationMarker) {
					leafletMap.removeLayer(participationMarker);
				});
				participationMarkers = [];
			}
			break;
			
		case entityType.EVENT:
			if (eventMarkers != null) {
				eventMarkers.forEach(function(eventMarker) {
					leafletMap.removeLayer(eventMarker);
				});
				eventMarkers = [];
			}
		    break;
	}
}

/**
 * Mise à jour de la liste des filtres
 */ 
function updateFilterElements() {
	
    removeFilterElements();
	
	if (projects != null) {
		projects.forEach(function(project, index) {
			$("fieldset[id='projects_fieldset']").append(
				"<div>" + 
                    "<input type='checkbox' id='project_" + index + "' class='hide-checkbox' value='" + project.id + "'>" +
                    "<label for='project_" + index + "'>" + project.title + "</label>" +
                "</div>"
			);
			if (selectedProjectIds.indexOf(project.id) != -1) {
				$("input[id='project_" + index + "']").prop('checked', true);
			}
		});
	}

	if (participations != null) {
		participations.forEach(function(participation, index) {
			$("fieldset[id='participations_fieldset']").append(
				"<div>" + 
                    "<input type='checkbox' id='participation_" + index + "' class='hide-checkbox' value='" + participation.id + "'>" +
                    "<label for='participation_" + index + "'>" + participation.title + "</label>" +
                "</div>"
			);
			if (selectedParticipationIds.indexOf(participation.id) != -1) {
				$("input[id='participation_" + index + "']").prop('checked', true);
			}
		});
	}

	if (petitions != null) {
		petitions.forEach(function(petition, index) {
			$("fieldset[id='petitions_fieldset']").append(
				"<div>" + 
	                "<input type='checkbox' id='petition_" + index + "' class='hide-checkbox' value='" + petition.id + "'>" +
	                "<label for='petition_" + index + "'>" + petition.title + "</label>" +
	            "</div>"
			);
			if (selectedPetitionIds.indexOf(petition.id) != -1) {
				$("input[id='petition_" + index + "']").prop('checked', true);
			}
		});
	}

	if (events != null) {
		events.forEach(function(event, index) {
			$("fieldset[id='events_fieldset']").append(
				"<div>" + 
                    "<input type='checkbox' id='event_" + index + "' class='hide-checkbox' value='" + event.id + "'>" +
                    "<label for='event_" + index + "'>" + event.title.fr_FR + "</label>" +
                "</div>"
			);
			if (selectedEventIds.indexOf(event.id) != -1) {
				$("input[id='event_" + index + "']").prop('checked', true);
			}
		});
	}

}

/**
 * Mise à jour de la liste des filtres
 * @param entityName Nom de l'entité à partir duquel mettre à jour
 */
function updateMarkerElements(entityName) {
    
    var entityName = (typeof entityName !== 'undefined') ? entityName : entityType.PROJECT;
	removeMarkerElements(entityName);
	
	switch (entityName) {
		case entityType.PROJECT:
			if (projects != null) {
				// Parcours des projets
				projects.forEach(function(project) {
					// Le projet doit-il être affiché ?
					if (project.isMarkeable) {
						// Parcours des lieux du projet
						project.placitPlaces.forEach(function(placitPlace) {
							// Le lieux est-il repérable ?
							if (placitPlace.mercatorY != 0 && placitPlace.mercatorX != 0) {
								// Création du marqueur
								var marker = getProjectMarker(project, [placitPlace.mercatorY, placitPlace.mercatorX]);
								// Ajout du marqueur sur la carte
								marker.addTo(leafletMap);
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
					if (participation.isMarkeable) {
						participation.placitPlaces.forEach(function(placitPlace) {
							if (placitPlace.mercatorY != 0 && placitPlace.mercatorX != 0) {
								var marker = getParticipationMarker(participation, [placitPlace.mercatorY, placitPlace.mercatorX]);
								marker.addTo(leafletMap);
								participationMarkers.push(marker);
							}
						});
					}
				});
			}
			
		case entityType.PETITION:
			// Même processus que l'entité Project
			if (petitions != null) {
				petitions.forEach(function(petition, index) {
					if (petition.isMarkeable) {
						petition.placitPlaces.forEach(function(placitPlace) {
							if (placitPlace.mercatorY != 0 && placitPlace.mercatorX != 0) {
								var marker = getPetitionMarker(petition, [placitPlace.mercatorY, placitPlace.mercatorX]);
								marker.addTo(leafletMap);
								petitionMarkers.push(marker);
							}
						});
					}
				});
			}
	
		case entityType.EVENT:
			// Même processus que l'entité Project
			if (events != null) {
				events.forEach(function(event, index) {
					if (event.isMarkeable) {
						if (event.mercatorY != 0 && event.mercatorX != 0) {
							var marker = getEventMarker(event);
							marker.addTo(leafletMap);
							eventMarkers.push(marker);
						}
					}
				});
			}
	}
}

/**
 * Renvoi la liste des IDs des entités demandées
 * @param entityName Nom de l'entité à sonder
 * @return liste des IDs sous forme d'une chaine de caractères séparée par des ","
 */
function getSelectedMarkerElements(entityName) {
	var results = "";
	
	$("input[id^='" + entityName + "_']:checked").each(function() {
		results =  results.concat(this.value, ',');
	});
	
	return results;
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
    
    // Cachage du bouton de zoom de map
    $('.leaflet-control-fullscreen-button').hide();
});

/**
 * Lors d'une selection de quartier
 */
$('#district').change(function() {
	var selectedDistrictId = this.value;
	
	saveSelectedFilters();
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(changeDistrictSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_map_search_asset_MapSearchAssetPortlet_selectedDistrictId : selectedDistrictId
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	projects = data.projects;
                	participations = data.participations;
                	petitions = data.petitions;
                	events = data.events;
                	
                	updateFilterElements();
			 	}
			 }
		});
	});
});





