// Champs conditionnelles
jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_gtfs_GTFSBOPortlet_";
	var namespaceAUI = "#" + namespace;

	$(":submit").on('click', function(e) {
		setAlertValidators(e);
	});

	function setAlertValidators(event) {
		var allValidated = true;
		var alertLabels = document
				.querySelectorAll('#alert-fields .alert-label');
		for (var i = 0; i < alertLabels.length; i++) {
			var alertLabel = alertLabels[i];
			var index = $(alertLabel).attr('id');
			// On ne lance la validation que si l'élément ne contient pas la
			// classe "hide"
			var startDateAlert = $(namespaceAUI + "startDateAlert" + index).val();
			if (startDateAlert != undefined
					&& $(alertLabel).parents('.lfr-form-row').attr('class')
							.indexOf('hide') == -1) {
				var endDateAlert = $(namespaceAUI + "endDateAlert" + index).val();
				var alertLigneAndDirection = $(
						namespaceAUI + "alertLigneAndDirection" + index + "_fr_FR")
						.val();
				var alertPerturbation = $(
						namespaceAUI + "alertPerturbation" + index + "_fr_FR")
						.val();
				if(startDateAlert == ""){
					if (endDateAlert != "" || alertLigneAndDirection != "" || alertPerturbation != "") {
						$('.alert-start-date', $(alertLabel).parent()).show();
						allValidated = false;
					}else{
						$('.alert-start-date', $(alertLabel).parent()).hide();
					}
				}else{
					if (endDateAlert == "") {
						$('.alert-end-date', $(alertLabel).parent()).show();
						allValidated = false;
					}else{
						$('.alert-end-date', $(alertLabel).parent()).hide();
						
						// on vérifie que la date de début soit <= à la date de fin
						if(!comparDatesYMD(startDateAlert, endDateAlert)){
							$('.alert-incorrect-date', $(alertLabel).parent()).show();
							allValidated = false;
						}else{
							$('.alert-incorrect-date', $(alertLabel).parent()).hide();
						}
					}
					if (alertLigneAndDirection == "") {
						$('.alert-ligne-and-direction', $(alertLabel).parent()).show();
						allValidated = false;
					}else{
						$('.alert-ligne-and-direction', $(alertLabel).parent()).hide();
					}
					if (alertPerturbation == "") {
						$('.alert-perturbation', $(alertLabel).parent()).show();
						allValidated = false;
					}else{
						$('.alert-perturbation', $(alertLabel).parent()).hide();
					}
				}
			}
		}
		
		if (!allValidated) {
			event.preventDefault();
		}
	}

});

// Schedules
var autoFields = undefined; // Référence au champ répétable (setté plus loin)
(function($) {
	var namespace = "_eu_strasbourg_portlet_gtfs_GTFSBOPortlet_"; // Namespace du portlet

	// Configuration de l'autofield
	AUI().use('liferay-auto-fields', function(Y) {
		if (!!document.getElementById('alert-fields')) {
			// Création de l'autofield
			autoFields = new Liferay.AutoFields({
				contentBox : '#alert-fields',
				fieldIndexes : namespace + 'alertsIndexes',
				namespace : namespace,
				url : getalertRowJSPURL
			}).render();
		}
	});
})(jQuery);