// Initialisation des variables de références
var leafletMap = null;

var projects = null;
var participations = null;
var events = null;

var entityType = {
	PROJECT : 'project',
	PARTICIPATION : 'participation',
	EVENT : 'event',
}

/**
 * Supprime l'affichage des éléments selon le nom de l'entité demandée
 * @notes : Récursif selon la hiérarchie des entités 
 */
function removeFilterElements(entityName = entityType.PROJECT) {
	switch (entityName) {
		case entityType.PROJECT:
			$("input[id^='project_']").each(function() {
				$(this).parent().remove();
			});
			removeFilterElements(entityType.PARTICIPATION);
			break;
			
		case entityType.PARTICIPATION:
			$("input[id^='participation_']").each(function() {
				$(this).parent().remove();
			});
			removeFilterElements(entityType.EVENT);
			break;
			
		case entityType.EVENT:
			$("input[id^='event_']").each(function() {
				$(this).parent().remove();
			});
		    break;
	}
}

/**
 * Mise à jour de la liste des filtres
 * @param entityName Nom de l'entité à partir duquel mettre à jour
 */ 
function updateFilterElements(entityName = entityType.PROJECT) {
	
	removeFilterElements(entityName);
	
	switch (entityName) {
		case entityType.PROJECT:
			projects.forEach(function(project, index) {
				$("fieldset[id='projects_fieldset']").append(
					"<div>" + 
                        "<input type='checkbox' id='project_" + index + "' class='hide-checkbox' value='" + project.id + "'>" +
                        "<label for='project_" + index + "'>" + project.title + "</label>" +
                    "</div>"
				);
			});
			break;
			
		case entityType.PARTICIPATION:  
			participations.forEach(function(participation, index) {
				$("fieldset[id='participations_fieldset']").append(
					"<div>" + 
                        "<input type='checkbox' id='participation_" + index + "' class='hide-checkbox' value='" + project.id + "'>" +
                        "<label for='articipation_" + index + "'>" + project.title + "</label>" +
                    "</div>"
				);
			});
			break;
			
		case entityType.EVENT:
		    break;
	}
}

/**
 * Mise à jour de la liste des filtres
 * @param entityName Nom de l'entité à partir duquel mettre à jour
 */
function updateMarkerElements(entityName = entityType.PROJECT) {
	switch (entityName) {
		case entityType.PROJECT:
			projects.forEach(function(project, index) {
				$("fieldset[id='projects_fieldset']").append(
					"<div>" + 
                        "<input type='checkbox' id='project_" + index + "' class='hide-checkbox' value='" + project.id + "'>" +
                        "<label for='project_" + index + "'>" + project.title + "</label>" +
                    "</div>"
				);
			});
			break;
			
		case entityType.PARTICIPATION:
			participations.forEach(function(participation, index) {
				$("fieldset[id='participations_fieldset']").append(
					"<div>" + 
                        "<input type='checkbox' id='participation_" + index + "' class='hide-checkbox' value='" + participation.id + "'>" +
                        "<label for='participation_" + index + "'>" + participation.title + "</label>" +
                    "</div>"
				);
			});
			break;
			
		case entityType.EVENT:
			events.forEach(function(event, index) {
				$("fieldset[id='events_fieldset']").append(
					"<div>" + 
                        "<input type='checkbox' id='event_" + index + "' class='hide-checkbox' value='" + event.id + "'>" +
                        "<label for='event_" + index + "'>" + event.title + "</label>" +
                    "</div>"
				);
			});
		    break;
	    
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

// Lors d'une selection de quartier
$('#district').change(function() {
	var selectedDistrict = this.value;
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(changeDistrictSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_map_search_asset_MapSearchAssetPortlet_selectedDistrict : selectedDistrict
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	projects = data.projects ;
                	
                	updateFilterElements(entityType.PROJECT);
			 	}
			 }
		});
	});
});

// Lors d'une selection de projet
$("fieldset[id='projects_fieldset'] input").change(function() {
	
	var selectedProjects = getSelectedMarkerElements(entityType.PROJECT);
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(changeProjectsSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : { 
				_eu_strasbourg_portlet_map_search_asset_MapSearchAssetPortlet_selectedProjects : selectedProjects
			},
			on: {
                success: function(e) {
                	
			 	}
			 }
		});
	});
});
