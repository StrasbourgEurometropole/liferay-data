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
		for (var i = 0; i < 2; i++) {
			var alertLabel = alertLabels[i];
			var index = $(alertLabel).attr('id');
			var startDateAlert = $(namespaceAUI + "startDateAlert" + index).val();
            var endDateAlert = $(namespaceAUI + "endDateAlert" + index).val();
            var alertLigneAndDirection = $(
                    namespaceAUI + "alertLigneAndDirection" + index).val();
            var alertPerturbation =
                    $( "input[id^='" + namespace + "alertPerturbation" + index + "']" ).val();
            if(startDateAlert == ""){
                if (endDateAlert != "" || alertLigneAndDirection != "" || alertPerturbation != "") {
                    $('.alert-start-date', $(alertLabel).parent()).show();
                    if (endDateAlert != "") {
                    $('.alert-end-date', $(alertLabel).parent()).show();
                    }
                    if (alertLigneAndDirection != "") {
                        $('.alert-ligne-and-direction', $(alertLabel).parent()).show();
                    }
                    if (alertPerturbation != "") {
                        $('.alert-perturbation', $(alertLabel).parent()).show();
                    }
                    allValidated = false;
                }else{
                    $('.alert-start-date', $(alertLabel).parent()).hide();
                    $('.alert-end-date', $(alertLabel).parent()).hide();
                    $('.alert-ligne-and-direction', $(alertLabel).parent()).hide();
                    $('.alert-perturbation', $(alertLabel).parent()).hide();
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
		
		if (!allValidated) {
			event.preventDefault();
		}
	}

});