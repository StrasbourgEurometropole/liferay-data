// Initialisation des variables de références
var help = null;

var entityType = {
	HELP_PROPOSAL_TYPES : 'vocabulary_0',
	LOCALISATION : 'vocabulary_1',
	HELP_PROPOSAL_ACTIVITY_STATUS : 'vocabulary_2',
}

$(document).ready(function(){
    getSelectedEntries();
	$('#quartiers').toggle();
	$('#districtToggleButton').toggleClass("glyphicon-minus glyphicon-plus");
});

/**
 * Renvoi la liste des IDs des entités demandées
 * @param entityName Nom de l'entité à sonder
 * @return liste des IDs sous forme d'une chaine de caractères séparée par des ","
 */
function getSelectedMarkerElements(entityName) {
	var results = "";

	$("input[id*='" + entityName + "_']:checked").each(function() {
		results =  results.concat(this.value, ',');
	});

	return results;
}

/**
 * Renvoi la liste des aides demandés
 * @return
 */
function getSelectedEntries() {

	var selectedKeyWords = $('#name')[0].value;
    var selectedStartDay ;
    var selectedStartMonth ;
    var selectedStartYear;
    var selectedEndDay;
    var selectedEndMonth ;
    var selectedEndYear;
	if($('.pro-facette-date').length > 0){
        selectedStartDay = $('input[data-name="fromDay"]')[0].value;
        selectedStartMonth = $('input[data-name="fromMonth"]')[0].value;
        selectedStartYear = $('input[data-name="fromYear"]')[0].value;
        selectedEndDay = $('input[data-name="toDay"]')[0].value;
        selectedEndMonth = $('input[data-name="toMonth"]')[0].value;
        selectedEndYear = $('input[data-name="toYear"]')[0].value;
	}
	var selectedHelpProposalActivityStatus = getSelectedMarkerElements(entityType.HELP_PROPOSAL_ACTIVITY_STATUS);
	var selectedLocalisations = getSelectedMarkerElements(entityType.LOCALISATION);
	var selectedHelpProposalTypes = getSelectedMarkerElements(entityType.HELP_PROPOSAL_TYPES);

	AUI().use('aui-io-request', function(A) {
		A.io.request(helpsSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedKeyWords : selectedKeyWords,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStartDay : selectedStartDay,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStartMonth : selectedStartMonth,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStartYear : selectedStartYear,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedEndDay : selectedEndDay,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedEndMonth : selectedEndMonth,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedEndYear : selectedEndYear,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedHelpProposalActivityStatus : selectedHelpProposalActivityStatus,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedLocalisations : selectedLocalisations,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedHelpProposalTypes : selectedHelpProposalTypes,
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	getResult('help', data);
			 	}
			}
		});
	});
}

// Lors d'une recherche par mots clés
$('#name').on('input',function() {
	getSelectedEntries();
});

// Lors d'une selection d'état
$("fieldset[id='help_activity_status_fieldset'] input").change(function() {
	getSelectedEntries();
});

// Lors d'une selection d'un quartier
$("fieldset[id='localisations_fieldset'] input").change(function() {
	getSelectedEntries();
});

// Lors d'une selection d'une thématique
$("fieldset[id='types_fieldset'] input").change(function() {
	getSelectedEntries();
});

$('#districtToggleButton').click(function() {
	$('#quartiers').toggle("slide");
	$(this).toggleClass("glyphicon-minus glyphicon-plus");
});