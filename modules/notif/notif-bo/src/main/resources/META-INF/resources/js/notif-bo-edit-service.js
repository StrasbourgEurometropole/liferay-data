var natureAutoFields = undefined; // Référence au champ nom de nature répétable (setté plus loin)
var messageAutoFields = undefined; // Référence au champ message répétable (setté plus loin)


var namespace = "_eu_strasbourg_portlet_notif_NotifBOPortlet_";

//Soumission du formulaire
function submitForm(event) {
	natureAutoFields.save(event.target);
	messageAutoFields.save(event.target);
	return true;
}

// gestion des champs répétables
(function($) {
	// Configuration de l'autofield
	AUI().use('liferay-auto-fields', function(Y) {
        var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');

		if (!!document.getElementById('nature-fields')) {
			// Création de l'autofield
			natureAutoFields = new Liferay.AutoFields({
				contentBox : '#nature-fields',
				fieldIndexes : namespace + 'serviceNaturesIndexes',
				namespace : namespace,
				url : getServiceNatureRowJSPURL
			}).render();
		}

		if (!!document.getElementById('message-fields')) {
			// Création de l'autofield
			messageAutoFields = new Liferay.AutoFields({
				contentBox : '#message-fields',
				fieldIndexes : namespace + 'serviceMessagesIndexes',
				namespace : namespace,
				url : getServiceMessageRowJSPURL,
			}).render();
		}
	});
})(jQuery);