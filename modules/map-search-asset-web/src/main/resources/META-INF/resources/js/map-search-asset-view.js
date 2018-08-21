// Initialisation des variables de références
var leafletMap = null;

var projects = null;
var participations = null;
var events = null;

var projectMarkers = null;
var participationMarkers = null;
var eventMarkers = null;

var entityType = {
	PROJECT : 'project',
	PARTICIPATION : 'participation',
	EVENT : 'event',
}

/**
 * Supprime l'affichage des éléments selon le nom de l'entité demandée
 * @notes : Récursif selon la hiérarchie des entités 
 */
function removeFilterElementsFrom(entityName = entityType.PROJECT) {
	switch (entityName) {
		case entityType.PROJECT:
			$("input[id^='project_']").each(function() {
				$(this).parent().remove();
			});
			removeFilterElementsFrom(entityType.PARTICIPATION);
			break;
			
		case entityType.PARTICIPATION:
			$("input[id^='participation_']").each(function() {
				$(this).parent().remove();
			});
			removeFilterElementsFrom(entityType.EVENT);
			break;
			
		case entityType.EVENT:
			$("input[id^='event_']").each(function() {
				$(this).parent().remove();
			});
		    break;
	}
}

/**
 * Supprime l'affichage des marqueurs selon le nom de l'entité demandée
 * @notes : Récursif selon la hiérarchie des entités 
 */
function removeMarkerElementsFrom(entityName = entityType.PROJECT) {
	switch (entityName) {
		case entityType.PROJECT:
			if (projectMarkers != null) {
				projectMarkers.forEach(function(projectMarker) {
					leafletMap.removeLayer(projectMarker);
				}
			}
			break;
			
		case entityType.PARTICIPATION:
			if (participationMarkers != null) {
				participationMarkers.forEach(function(participationMarker) {
					leafletMap.removeLayer(participationMarker);
				}
			}
			removeFilterElementsFrom(entityType.EVENT);
			break;
			
		case entityType.EVENT:
			if (eventMarkers != null) {
				eventMarkers.forEach(function(eventMarker) {
					leafletMap.removeLayer(eventMarker);
				}
			}
		    break;
	}
}

/**
 * Mise à jour de la liste des filtres
 * @param entityName Nom de l'entité à partir duquel mettre à jour
 */ 
function updateFilterElements(entityName = entityType.PROJECT) {
	
	removeFilterElementsFrom(entityName);
	
	switch (entityName) {
		case entityType.PROJECT:
			if (projects != null) {
				projects.forEach(function(project, index) {
					$("fieldset[id='projects_fieldset']").append(
						"<div>" + 
	                        "<input type='checkbox' id='project_" + index + "' class='hide-checkbox' value='" + project.id + "'>" +
	                        "<label for='project_" + index + "'>" + project.title + "</label>" +
	                    "</div>"
					);
				});
				updateFilterElements(entityType.PARTICIPATION);
			}
			break;
			
		case entityType.PARTICIPATION:
			if (participations != null) {
				participations.forEach(function(participation, index) {
					$("fieldset[id='participations_fieldset']").append(
						"<div>" + 
	                        "<input type='checkbox' id='participation_" + index + "' class='hide-checkbox' value='" + participation.id + "'>" +
	                        "<label for='participation_" + index + "'>" + participation.title + "</label>" +
	                    "</div>"
					);
				});
				updateFilterElements(entityType.EVENT);
			}
			break;
			
		case entityType.EVENT:
			if (events != null) {
				events.forEach(function(event, index) {
					$("fieldset[id='events_fieldset']").append(
						"<div>" + 
	                        "<input type='checkbox' id='event_" + index + "' class='hide-checkbox' value='" + event.id + "'>" +
	                        "<label for='event_" + index + "'>" + event.title + "</label>" +
	                    "</div>"
					);
				});
			}
		    break;
	}
}

/**
 * Mise à jour de la liste des filtres
 * @param entityName Nom de l'entité à partir duquel mettre à jour
 */
function updateMarkerElements(entityName = entityType.PROJECT) {
	removeMarkerElementsFrom(entityName);
	
	switch (entityName) {
		case entityType.PROJECT:
			if (projects != null) {
				projects.forEach(function(project, index) {
					
				});
				updateMarkerElements(entityType.PARTICIPATION);
			}
			break;
			
		case entityType.PARTICIPATION:
			if (participations != null) {
				participations.forEach(function(participation, index) {
					
				});
				updateMarkerElements(entityType.EVENT);
			}
			break;
			
		case entityType.EVENT:
			if (events != null) {
				events.forEach(function(event, index) {
					
				});
			    break;
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



$(document).ready(function() {
	//Création de la carte au centre de strasbourg
    leafletMap = getLeafletMap()
});

/**
 * Lors d'une selection de quartier
 */
$('#district').change(function() {
	var selectedDistrictId = this.value;
	
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
                	events = data.events;
                	
                	updateFilterElements(entityType.PROJECT);
			 	}
			 }
		});
	});
});

/**
 * Lors d'une selection de projet
 */
$(document).on("change", "input[id^='project_']", function() {
	
	var selectedProjectIds = getSelectedMarkerElements(entityType.PROJECT);
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(changeProjectsSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_map_search_asset_MapSearchAssetPortlet_selectedProjectIds : selectedProjectIds
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	projects = data.projects;
                	participations = data.participations;
                	events = data.events;
                	
                	updateFilterElements(entityType.PARTICIPATION);
			 	}
			 }
		});
	});
});

/**
 * Lors d'une selection de participation
 */
$(document).on("change", "input[id^='participation_']", function() {
	
	var selectedParticipationIds = getSelectedMarkerElements(entityType.PARTICIPATION);
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(changeParticipationsSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : { 
				_eu_strasbourg_portlet_map_search_asset_MapSearchAssetPortlet_selectedParticipationIds : selectedParticipationIds
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	participations = data.participations;
                	events = data.events;
                	
                	updateFilterElements(entityType.EVENT);
			 	}
			 }
		});
	});
});

/**
 * Lors d'une selection d'entité sous participation
 */
$(document).on("change","fieldset[id='events_fieldset'] input", function() {
	
	var selectedEventsIds = getSelectedMarkerElements(entityType.EVENT);
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(changeSubEntitiesSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : { 
				_eu_strasbourg_portlet_map_search_asset_MapSearchAssetPortlet_selectedEventIds : selectedEventIds
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	events = data.events;
			 	}
			 }
		});
	});
});
