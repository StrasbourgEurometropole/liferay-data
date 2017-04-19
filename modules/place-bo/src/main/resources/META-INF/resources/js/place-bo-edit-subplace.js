// Champs conditionnelles
jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;

	$(":submit").on('click', function(e) {
		setScheduleExceptionValidators();
		setPeriodValidators();
	});	

	function setPeriodValidators() {
		var allValidated = true;
		var periodLabels = $('.tab-content > div[id*=period]');
		var nbPeriodDefault = 0;
		var periodsIndexes = "";
		for (var i = 0; i < periodLabels.length; i++) {
			var periodLabel = periodLabels[i];
			var index = $(periodLabel).children('input[id*=numPeriod]').val();
			periodsIndexes += index + ",";
			var nom = $(namespaceAUI + "namePeriod" + index).val();			
			if (nom == "") {
				$('.place-period-name', $(periodLabel).parent()).show();
				allValidated = false;
			}else{
				$('.place-period-name', $(periodLabel).parent()).hide();
			}

			var labelHasValue = $(namespaceAUI + 'periodLabel' + index).val().length > 0;
			var URLHasValue = $(namespaceAUI + 'periodURL' + index).val().length > 0;
			if (labelHasValue && !URLHasValue) {
				$('.place-period-label', $(periodLabel).parent()).hide();
				$('.place-period-url', $(periodLabel).parent()).show();
				allValidated = false;
			} else {
				if (!labelHasValue && URLHasValue) {
					$('.place-period-label', $(periodLabel).parent()).show();
					$('.place-period-url', $(periodLabel).parent()).hide();
					allValidated = false;
				} else{
					$('.place-period-label', $(periodLabel).parent()).hide();
					$('.place-period-url', $(periodLabel).parent()).hide();
				}
			}
			
			var periodDefault = $(namespaceAUI + "defaultPeriod" + index).get(0).checked;
			if (periodDefault) {
				$('.place-period-start-date', $(periodLabel).parent()).hide();
				$('.place-period-end-date', $(periodLabel).parent()).hide();
				if (nbPeriodDefault == 0) {
					nbPeriodDefault++;
					$('.place-period-default', $(periodLabel).parent()).hide();
				} else {
					$('.place-period-default', $(periodLabel).parent()).show();
					allValidated = false;
				}
			} else{
				$('.place-period-default', $(periodLabel).parent()).hide();
				var startDatePeriod = $(namespaceAUI + "startDatePeriod" + index).val();
				if(startDatePeriod == ""){
					$('.place-period-start-date', $(periodLabel).parent()).show();
					allValidated = false;
				}else{
					$('.place-period-start-date', $(periodLabel).parent()).hide();
				}
				var endDatePeriod = $(namespaceAUI + "endDatePeriod" + index).val();
				if(endDatePeriod == ""){
					$('.place-period-end-date', $(periodLabel).parent()).show();
					allValidated = false;
				}else{
					$('.place-period-end-date', $(periodLabel).parent()).hide();
				}
			}
			var retour = setSlotValidators(index, periodLabel);
			if(!retour){
				allValidated = false;
			}
		}
		
		if (!allValidated) {
			event.preventDefault();
		}else{
			$(namespaceAUI + 'periodsIndexes').val(periodsIndexes.substr(0, periodsIndexes.length -1));
		}
	}

	function setSlotValidators(indexPeriod, periodLabel) {
		var allValidated = true;
		
		for (var jour = 0; jour < 7; jour++) {
			var slotsIndexes = "";
			var nbSlot = $(namespaceAUI + 'nbSlot' + indexPeriod
					+ '-' + jour).val();
			for (var indexSlot = 0; indexSlot < nbSlot; indexSlot++) {
				slotsIndexes += indexSlot + ",";
				var heureDebutHasValue = $(namespaceAUI + 'startHour' + indexPeriod
								+ '-' + jour + '-' + indexSlot).val().length > 0;
				if (!heureDebutHasValue) {
					$('#slotStartHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).show();
					allValidated = false;
				} else {
					$('#slotStartHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).hide();
				}
				var heureFinHasValue = $(namespaceAUI + 'endHour' + indexPeriod
								+ '-' + jour + '-' + indexSlot).val().length > 0;
				if (!heureFinHasValue) {
					$('#slotEndHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).show();
					allValidated = false;
				} else {
					$('#slotEndHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).hide();
				}
			}
			$(namespaceAUI + 'slotsIndexes' + indexPeriod + "-" + jour).val(slotsIndexes.substr(0, slotsIndexes.length -1));
		}

		return allValidated;
	}

	function setScheduleExceptionValidators() {
		var allValidated = true;
		var scheduleLabels = document
				.querySelectorAll('#date-fields .schedule-label');
		for (var i = 0; i < scheduleLabels.length; i++) {
			var scheduleLabel = scheduleLabels[i];
			var index = $(scheduleLabel).attr('id');
			// On ne lance la validation que si l'élément ne contient pas la
			// classe "hide"
			var startDateSchedule = $(namespaceAUI + "startDateScheduleException" + index).val();
			if (startDateSchedule != undefined
					&& $(scheduleLabel).parents('.lfr-form-row').attr('class')
							.indexOf('hide') == -1) {
				var scheduleExceptionDescription = $(
						namespaceAUI + "scheduleExceptionDescription" + index + "_fr_FR")
						.val();
				var endDateSchedule = $(namespaceAUI + "endDateScheduleException" + index).val();
				if(startDateSchedule == ""){
					var startHour = $(namespaceAUI + "startHour" + index).val();
					var endHour = $(namespaceAUI + "endHour" + index).val();
					if (endDateSchedule || scheduleExceptionDescription != "" || startHour != "" || endHour != "") {
						$('.place-schedule-start-date', $(scheduleLabel).parent()).show();
						allValidated = false;
					}else{
						$('.place-schedule-start-date', $(scheduleLabel).parent()).hide();
					}
				}else{
					if (endDateSchedule == "") {
						$('.place-schedule-end-date', $(scheduleLabel).parent()).show();
						allValidated = false;
					}else{
						$('.place-schedule-end-date', $(scheduleLabel).parent()).hide();
					}
					if (scheduleExceptionDescription == "") {
						$('.place-schedule-description', $(scheduleLabel).parent()).show();
						allValidated = false;
					}else{
						$('.place-schedule-description', $(scheduleLabel).parent()).hide();
					}
					var fermeture = $(namespaceAUI + "closed" + index).get(0).checked;
					if (!fermeture) {
						var startHour = $(namespaceAUI + "startHour" + index).val();
						var endHour = $(namespaceAUI + "endHour" + index).val();
						if(startHour == "") {
							$('.place-schedule-start-hour', $(scheduleLabel).parent()).show();
							allValidated = false;
						}else{
							$('.place-schedule-start-hour', $(scheduleLabel).parent()).hide();
						}
						if(endHour == "") {
							$('.place-schedule-end-hour', $(scheduleLabel).parent()).show();
							allValidated = false;
						}else{
							$('.place-schedule-end-hour', $(scheduleLabel).parent()).hide();
						}
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
