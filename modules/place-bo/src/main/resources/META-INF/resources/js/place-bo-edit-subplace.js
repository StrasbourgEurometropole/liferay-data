// Champs conditionnelles
jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
    var allValidate = true;

	$(":submit").on('click', function(e) {

        try{
            allValidate = true;
            $('.error').html("");

            setScheduleExceptionValidators(e);
            setPeriodValidators(e);

            if (!allValidate) {
                event.preventDefault();
            }
        }
        catch(err) {
            $('.error').html("<div class='alert alert-danger'><strong>Erreur JavaScript : </strong>" + err.message + ".<br> Veuillez prendre une capture d'ecran et contacter le support</div>");
            event.preventDefault();
        }
	});	

	function setPeriodValidators(event) {
		var periodLabels = $('.tab-content > div[id*=period]');
		//var nbPeriodDefault = 0;
		var periodsIndexes = "";
		for (var i = 0; i < periodLabels.length; i++) {
			var periodLabel = periodLabels[i];
			var index = $(periodLabel).children('input[id*=numPeriod]').val();
			periodsIndexes += index + ",";
			setSlotValidators(index, periodLabel);
		}

        if(allValidate){
			$(namespaceAUI + 'periodsIndexes').val(periodsIndexes.substr(0, periodsIndexes.length -1));
        }
	}

	function setSlotValidators(indexPeriod, periodLabel) {
		for (var jour = 0; jour < 7; jour++) {
			var nbSlot = $(namespaceAUI + 'nbSlot' + indexPeriod
					+ '-' + jour).val();
			var slotsIndexes = "";
			for (var indexSlot = 0; indexSlot < nbSlot; indexSlot++) {
				slotsIndexes += indexSlot + ",";
				var startHour = $(namespaceAUI + 'startHour' + indexPeriod
						+ '-' + jour + '-' + indexSlot).val();
				var endHour = $(namespaceAUI + 'endHour' + indexPeriod
						+ '-' + jour + '-' + indexSlot).val();
				if(startHour == "") {
					$('#slotStartHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).show();
                    if(allValidate){
                        activePeriod(indexPeriod);
                        $('html,body').animate({scrollTop: $(namespaceAUI + 'startHour' + indexPeriod + '-' + jour + '-' + indexSlot).offset().top - 100}, 'slow');
                        allValidate = false;
                    }
				}else{
					$('#slotStartHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).hide();
				}
				if(endHour == "") {
					$('#slotEndHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).show();
                    if(allValidate){
                        activePeriod(indexPeriod);
                        $('html,body').animate({scrollTop: $(namespaceAUI + 'endHour' + indexPeriod + '-' + jour + '-' + indexSlot).offset().top - 100}, 'slow');
                        allValidate = false;
                    }
				}else{
					$('#slotEndHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).hide();
				}

				// on vérifie que l'heure de début soit < à l'heure de fin
				if(!comparHour(startHour, endHour)){
					$('#slotIncorrectHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).show();
                    if(allValidate){
                        activePeriod(indexPeriod);
                        $('html,body').animate({scrollTop: $(namespaceAUI + 'startHour' + indexPeriod + '-' + jour + '-' + indexSlot).offset().top - 100}, 'slow');
                        allValidate = false;
                    }
				}else{
					$('#slotIncorrectHour' + indexPeriod
							+ '-' + jour + '-' + indexSlot, $(periodLabel).parent()).hide();
				}
			}
			$(namespaceAUI + 'slotsIndexes' + indexPeriod + "-" + jour).val(slotsIndexes.substr(0, slotsIndexes.length -1));
		}
	}

	function setScheduleExceptionValidators(event) {
		var scheduleLabels = document
				.querySelectorAll('#date-fields .schedule-label');
		var periods = [];
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
                var startHour = $(namespaceAUI + "startHour1_" + index).val();
                var endHour = $(namespaceAUI + "endHour1_" + index).val();
				if(scheduleExceptionDescription != "" || startDateSchedule != "" || endDateSchedule != "" || startHour != "" || endHour != "") {
					if (scheduleExceptionDescription == "") {
						$('.place-schedule-description', $(scheduleLabel).parent()).show();
                        if(allValidate){
                            $('html,body').animate({scrollTop: $(namespaceAUI + "scheduleExceptionDescription" + index).offset().top - 100}, 'slow');
                            allValidate = false;
                        }
					}else{
						$('.place-schedule-description', $(scheduleLabel).parent()).hide();
					}

					if (startDateSchedule == "") {
						$('.place-schedule-start-date', $(scheduleLabel).parent()).show();
                        if(allValidate){
                            $('html,body').animate({scrollTop: $(namespaceAUI + "startDateScheduleException" + index).offset().top - 100}, 'slow');
                            allValidate = false;
                        }
					}else{
						$('.place-schedule-start-date', $(scheduleLabel).parent()).hide();
					}

					if (endDateSchedule == "") {
						$('.place-schedule-end-date', $(scheduleLabel).parent()).show();
                        if(allValidate){
                            $('html,body').animate({scrollTop: $(namespaceAUI + "endDateScheduleException" + index).offset().top - 100}, 'slow');
                            allValidate = false;
                        }
					}else{
						$('.place-schedule-end-date', $(scheduleLabel).parent()).hide();
                    }

					if (startDateSchedule != "" && endDateSchedule != "") {
						// on vérifie que cette période ne chevauche pas une autre période
						var nbPeriod = 0
						$('.place-schedule-period', $(scheduleLabel).parent()).hide();
						for (nbPeriod; nbPeriod < periods.length; nbPeriod++) {
							if(!(!(comparDatesYMD(startDateSchedule, periods[nbPeriod][1])) || (!comparDatesYMD(periods[nbPeriod][0], endDateSchedule)))){
								$('.place-schedule-period', $(scheduleLabel).parent()).show();
                                if(allValidate){
                                    $('html,body').animate({scrollTop: $(scheduleLabel).offset().top - 100}, 'slow');
                                    allValidate = false;
                                }
								break;
							}
						}

						// on vérifie que la date de début soit <= à la date de fin
						if(!comparDatesYMD(startDateSchedule, endDateSchedule)){
							$('.place-schedule-incorrect-date', $(scheduleLabel).parent()).show();
                            if(allValidate){
                                $('html,body').animate({scrollTop: $(namespaceAUI + "startDateScheduleException" + index).offset().top - 100}, 'slow');
                                allValidate = false;
                            }
						}else{
							$('.place-schedule-incorrect-date', $(scheduleLabel).parent()).hide();
						}

						periods[periods.length] = [startDateSchedule, endDateSchedule];
					}

					var fermeture = $(namespaceAUI + "closed" + index).get(0).checked;
					if (!fermeture) {
					    for(var j=1; j <= 5; j++){
                            var startHour = $(namespaceAUI + "startHour" + j + "_" + index).val();
                            var endHour = $(namespaceAUI + "endHour" + j + "_" + index).val();
                            if(j == 1 || startHour != "" || endHour != ""){
                                if(startHour == "") {
                                    $('.place-schedule-start-hour' + j, $(scheduleLabel).parent()).show();
                                    if(allValidate){
                                        $('html,body').animate({scrollTop: $(namespaceAUI + "startHour" + j + "_" + index).offset().top - 100}, 'slow');
                                        allValidate = false;
                                    }
                                }else{
                                    $('.place-schedule-start-hour' + j, $(scheduleLabel).parent()).hide();
                                }

                                if(endHour == "") {
                                    $('.place-schedule-end-hour' + j, $(scheduleLabel).parent()).show();
                                    if(allValidate){
                                        $('html,body').animate({scrollTop: $(namespaceAUI + "endHour" + j + "_" + index).offset().top - 100}, 'slow');
                                        allValidate = false;
                                    }
                                }else{
                                    $('.place-schedule-end-hour' + j, $(scheduleLabel).parent()).hide();
                                }

                                // on vérifie que l'heure de début soit < à l'heure de fin
                                if(startHour != "" && endHour != "" && !comparHour(startHour, endHour)){
                                    $('.place-schedule-incorrect-hour' + j, $(scheduleLabel).parent()).show();
                                    if(allValidate){
                                        $('html,body').animate({scrollTop: $(namespaceAUI + "startHour" + j + "_" + index).offset().top - 100}, 'slow');
                                        allValidate = false;
                                    }
                                }else{
                                    $('.place-schedule-incorrect-hour' + j, $(scheduleLabel).parent()).hide();
                                }
                            }
					    }
					}
				}
			}
		}
	}

	function activePeriod(indexPeriod){
        //cache les périodes non concernées
        $(".active", $("#period-time")).removeClass("active");
        // affiche la période concernée
        $("#onglet" + indexPeriod).addClass("active");
        $("#period" + indexPeriod).addClass("active in");
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
