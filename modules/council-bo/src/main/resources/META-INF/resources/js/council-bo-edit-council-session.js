//  On garde une référence globale
var procurationAutoFields = undefined;

// Champs conditionnelles
jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_council_CouncilBOPortlet_";

	// Autofield sur les champs répétables
	AUI().use('liferay-auto-fields', function(Y) {

		if (!!document.getElementById('procuration-fields')) {
			// Création de l'autofield sur les procurations
			procurationAutoFields = new Liferay.AutoFields({
				contentBox : '#procuration-fields',
				fieldIndexes : namespace + 'procurationIndexes',
				namespace : namespace,
				url : getProcurationRowURL
			}).render();
		}

	});
});

//Soumission du formulaire
function submitForm(event) {
	procurationAutoFields.save(event.target);
	return true;
}