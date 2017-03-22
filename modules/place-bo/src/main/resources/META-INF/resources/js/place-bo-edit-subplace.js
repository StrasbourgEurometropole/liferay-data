// Champs conditionnelles
jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;

	$(":submit").on('click', function(e) {
		setScheduleExceptionValidators();
		setPeriodValidators();
		setSlotValidators();
	});

	Liferay.on('allPortletsReady', setScheduleExceptionValidators);
	Liferay.on('allPortletsReady', setPeriodValidators);
	Liferay.on('allPortletsReady', setSlotValidators);
	
	function setScheduleExceptionValidators() {
		
		// Validation des champs obligatoires conditionnels
		AUI().use('liferay-form', function() {
			var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');
			var indexes = $(namespaceAUI + "shedulesExceptionsIndexes").val().split(",");
			for (var i = 0, len = indexes.length; i < len; i++) {
				var dateSchedule = $(namespaceAUI + "dateScheduleException" + indexes[i]).val();
				var fermeture = $(namespaceAUI + "closed" + indexes[i]).get(0).checked;
				if(dateSchedule != undefined){
					if(dateSchedule != ""){
						rules[namespace + 'scheduleExceptionDescription'+ indexes[i]].required = true;
						rules[namespace + 'dateScheduleException'+ indexes[i]].required = true;
						if(fermeture){
							rules[namespace + 'startHour'+ indexes[i]].required = false;
							rules[namespace + 'endHour'+ indexes[i]].required = false;
						}else{
							rules[namespace + 'startHour'+ indexes[i]].required = true;
							rules[namespace + 'endHour'+ indexes[i]].required = true;
						}
					}else{
						rules[namespace + 'startHour'+ indexes[i]].required = false;
						rules[namespace + 'endHour'+ indexes[i]].required = false;
						rules[namespace + 'scheduleExceptionDescription'+ indexes[i]].required = false;
						rules[namespace + 'dateScheduleException'+ indexes[i]].required = false;	
					}
				}
			}
			
		});
		
	}
	
	function setPeriodValidators() {
		
		// Validation des champos obligatoires conditionnels
		AUI().use('liferay-form', function() {
			var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');
			var indexes = $(namespaceAUI + "periodsIndexes").val().split(",");
			for (var i = 0, len = indexes.length; i < len; i++) {
				var nom = $(namespaceAUI + "namePeriod" + indexes[i]).val();
				if(nom != undefined){
					if(nom != ""){
						rules[namespace + 'namePeriod'+ indexes[i]].required = true;
					}else{
						rules[namespace + 'namePeriod'+ indexes[i]].required = false;
					}
					var labelHasValue = $(namespaceAUI + 'periodLabel' + indexes[i]).val().length > 0;	
					var URLHasValue = $(namespaceAUI + 'periodURL' + indexes[i]).val().length > 0;
					if(labelHasValue && !URLHasValue){
						rules[namespace + 'periodLabel'+ indexes[i]].required = false;
						rules[namespace + 'periodURL'+ indexes[i]].required = true;
					}else{ if(!labelHasValue && URLHasValue){
						rules[namespace + 'periodLabel'+ indexes[i]].required = true;
						rules[namespace + 'periodURL'+ indexes[i]].required = false;
						}else{
							rules[namespace + 'periodLabel'+ indexes[i]].required = false;
							rules[namespace + 'periodURL'+ indexes[i]].required = false;
						}
					}
				}
			}
			
		});
		
	}
	
	function setSlotValidators() {
		
		// Validation des champos obligatoires conditionnels
		AUI().use('liferay-form', function() {
			var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');
			var indexesPeriodes = $(namespaceAUI + "periodsIndexes").val().split(",");
			for (var i = 0, len = indexesPeriodes.length; i < len; i++) {
				var nom = $(namespaceAUI + "namePeriod" + indexesPeriodes[i]).val();
				if(nom != undefined){
					for (var jour = 0; jour < 7; jour++) {
						for (var indexSlot = 0; indexSlot < 3; indexSlot++) {
							var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');
							var heureDebutHasValue = $(namespaceAUI + 'startHour' + indexesPeriodes[i] + '-' + jour + '-'  + indexSlot).val().length > 0;	
							var heureFinHasValue = $(namespaceAUI + 'endHour' + indexesPeriodes[i] + '-'  + jour + '-'  + indexSlot).val().length > 0;	
							if(heureDebutHasValue && !heureFinHasValue){
								rules[namespace + 'startHour' + indexesPeriodes[i] + '-'  + jour + '-'  + indexSlot].required = false;
								rules[namespace + 'endHour' + indexesPeriodes[i] + '-'  + jour + '-'  + indexSlot].required = true;
							}else{ if(!heureDebutHasValue && heureFinHasValue){
								rules[namespace + 'startHour' + indexesPeriodes[i] + '-'  + jour + '-'  + indexSlot].required = true;
								rules[namespace + 'endHour' + indexesPeriodes[i] + '-'  + jour + '-'  + indexSlot].required = false;
								}else{
									rules[namespace + 'startHour' + indexesPeriodes[i] + '-'  + jour + '-'  + indexSlot].required = false;
									rules[namespace + 'endHour' + indexesPeriodes[i] + '-'  + jour + '-'  + indexSlot].required = false;
								}
							}
						}
					}
				}
			}
			
		});
		
	}
	
});


function affichageHeures(objet, id) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_"; 
	var namespaceAUI = "#" + namespace;
	if(objet.checked){
		$('.heure' + id).hide();
	}else{
		$('.heure' + id).show();
	}
	
}


function affichageDates(objet, id) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_"; 
	var namespaceAUI = "#" + namespace;
	if(objet.checked){
		$('.dates' + id).hide();
	}else{
		$('.dates' + id).show();
	}
		
}


function affichageOuverture(objet, id) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_"; 
	var namespaceAUI = "#" + namespace;
	if(objet.checked){
		$('.ouvertures' + id).hide();
	}else{
		$('.ouvertures' + id).show();
	}
		
}

function copyHoraire(idPeriod) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_"; 
	var namespaceAUI = "#" + namespace;
	for (var indexSlot = 0; indexSlot < 3; indexSlot++) {
		var heureDebut = $(namespaceAUI + 'startHour' + idPeriod + '-0-'  + indexSlot).val();	
		var heureFin = $(namespaceAUI + 'endHour' + idPeriod + '-0-'  + indexSlot).val();
		for (var jour = 1; jour < 7; jour++) {
			$(namespaceAUI + 'startHour' + idPeriod + '-' + jour  + '-'  + indexSlot).val(heureDebut);
			$(namespaceAUI + 'endHour' + idPeriod + '-' + jour  + '-'  + indexSlot).val(heureFin);
		}
	}
		
}

// Schedules
var autoFields = undefined; // Référence au champ répétable (setté plus loin)
(function($) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_"; 
	
	// Configuration de l'autofield
	AUI().use('liferay-auto-fields', function(Y) {
		if (!!document.getElementById('date-fields')) {
			// Création de l'autofield
			autoFields = new Liferay.AutoFields({
				contentBox : '#date-fields',
				fieldIndexes : namespace + 'shedulesExceptionsIndexes',
				namespace : namespace,
				url: getscheduleExceptionRowJSPURL
			}).render();
		}
	});
})(jQuery);

// Périodes
var autoFields = undefined; // Référence au champ répétable (setté plus loin)
(function($) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_"; 
	
	// Configuration de l'autofield
	AUI().use('liferay-auto-fields', function(Y) {
		if (!!document.getElementById('date-fields2')) {
			// Création de l'autofield
			autoFields = new Liferay.AutoFields({
				contentBox : '#date-fields2',
				fieldIndexes : namespace + 'periodsIndexes',
				namespace : namespace,
				url: getperiodRowJSPURL
			}).render();
		}
	});
})(jQuery);
