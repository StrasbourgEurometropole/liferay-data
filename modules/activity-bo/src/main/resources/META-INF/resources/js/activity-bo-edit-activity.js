// On garde une référence globale aux ²
var placeAutoFields;

jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_activity_ActivityBOPortlet_";
	
	// Lieux répétables
	AUI().use('liferay-auto-fields', function(Y) {
		if (!!document.getElementById('place-fields')) {
			// Création de l'autofield
			placeAutoFields = new Liferay.AutoFields({
				contentBox : '#place-fields',
				fieldIndexes : namespace + 'placeIndexes',
				namespace : namespace,
				url: placeRowURL
			}).render();
		}
	});
	
	//Autocomplete des lieux
	var options = {
		type : "POST",
		serviceUrl : "/api/jsonws/place.place/get-places-by-name-and-language/",
		params : {
			name : '[name]',
			language: 'fr_FR',
			p_auth: Liferay.authToken
		},
		paramName : 'name',
		transformResult : function(response) {
			return {
				suggestions : jQuery.map(
						JSON.parse(response), function(
								dataItem) {
							return {
								value : dataItem.name.fr_FR,
								data : dataItem.idSurfs
							};
						})
			};
		},
		onSelect : function(suggestion) {
			jQuery('#place-autocomplete-hidden-value input', $(this).closest('.row-fields')).val(
					suggestion.data);
			jQuery('input.selected-place', $(this).closest('.row-fields')).val(suggestion.value);
		}
	};
	jQuery('.place-autocomplete-input-wrapper').each(function() {
		options.appendTo = this;
		$('input', this).autocomplete(options);
	})
	
	// Switch entre les sélections de lieux
	$('#place-fields').on('click', '.show-autocomplete-place',  function(e) {
		e.preventDefault();
		$('.place-manual', $(this).closest('.row-fields')).hide();
		$('.place-autocomplete', $(this).closest('.row-fields')).show();
		$('.place-manual input', $(this).closest('.row-fields')).val('');
		$('.place-manual option', $(this).closest('.row-fields')).prop('selected', false);
	})
	.on('click', '.show-manual-place', function(e) {
		e.preventDefault();
		$('.place-autocomplete', $(this).closest('.row-fields')).hide();
		$('.place-manual', $(this).closest('.row-fields')).show();
		$('.place-autocomplete input', $(this).closest('.row-fields')).val('');
		$('.place-autocomplete option', $(this).closest('.row-fields')).prop('selected', false);
	});
	// A la création d'une nouvelle ligne de lieu on remet en place l'autocomplete
	$('#place-fields').on('placeCreated', function(event, index) {
		options.appendTo = '#place-autocomplete-input-wrapper-' + index;
		jQuery('#place-' + index + ' .place-autocomplete-input-wrapper input').autocomplete(
				options);
	});

});

// Horaires répétables
// Ajout
jQuery('#place-fields').on('click', '.add-schedule', function() {
	// On clone
	var clone = $(this).closest('.schedule').clone();
	clone.addClass('new-schedule');
	// En remettant les contrôles à des valeurs vides
	$('input[type=text], input[type=time]', clone).val('');
	$('option', clone).prop('selected', false);
	$('input[type=checkbox]', clone).prop('checked', false);	
	// On ajoute au DOM
	$(this).closest('.schedule').after(clone);
	// Et on met à jour les index
	var placeIndex = $(this).closest('.schedules').data('index');
	updateScheduleIndexes(placeIndex);
})
// Suppression
.on('click', '.remove-schedule', function() {
	// On supprime récupère le schedule à supprimer
	var schedule = $(this).closest('.schedule');
	var placeIndex = $(this).closest('.schedules').data('index');
	if (schedule.siblings().length > 0) {
		// S'il en reste, on supprime du DOM
		schedule.remove();
	} else {
		// Sinon on le masque tout en rendant disable tous les champs
		schedule.hide();
		$('input, select', schedule).prop('disabled', true)
		
		// Et en les vidant
		// En remettant les contrôles à des valeurs vides
		$('input[type=text], input[type=time]', schedule).val('');
		$('option', schedule).prop('selected', false);
		$('input[type=checkbox]', schedule).prop('checked', false);	
		
		// On affiche le bouton pour ajouter un premier schedule
		$('.add-first-schedule', $(this).closest('.schedules-fieldset')).show();
	}
	// Et on remet à jour les index
	updateScheduleIndexes(placeIndex);
})
// Duplication
.on('click', '.duplicate-schedule', function() {
	// On crée le clone
	var schedule = $(this).closest('.schedule');
	var clone = schedule.clone();
	clone.addClass('new-schedule');
	// On remet correctement les valeurs des périodes
	$('option', clone).each(function() {
		$(this).prop('selected', $('option[value=' + $(this).val() + ']', schedule).is(':selected'));
	});
	// On ajoute au DOM
	$(this).closest('.schedule').after(clone);
	// Et on remet à jour les index
	var placeIndex = $(this).closest('.schedules').data('index');
	updateScheduleIndexes(placeIndex);
})
// Ajout d'un premier
.on('click', '.add-first-schedule', function(e) {
	e.preventDefault();
	// On affiche le premier schedule et on remet les input enabled
	var schedule = $('.schedule', $(this).parent());
	schedule.show();
	$('input, select', schedule).prop('disabled', false);
	// On masque le bouton
	$(this).hide();
	// Et on remet à jour les index
	var placeIndex = schedule.parent().data('index');
	updateScheduleIndexes(placeIndex);
})
.on('click', '.toggle-comments', function(event) {
	event.preventDefault();
	var comments = $(this).closest('.comments');
	$('.comment[data-french=false]', comments).toggle();
	$('.toggle-comments', comments).toggle();
});

// Fonction mettant à jour les noms des champs des schedules 
// ainsi que les attributs "for" des labels
function updateScheduleIndexes(placeIndex) {
	var schedules = $('#schedules-' + placeIndex);
	var i = 0;
	$('.schedule:visible', schedules).each(function() {
		var schedule = this;
		$('input:visible, select', this).each(function() {
			var oldName = $(this).attr('name');
			var originalName = oldName.replace(/([0-9]+)$/, '');
			var newName = originalName + i;
			$(this).attr('name', newName);
			$(this).attr('id', newName);
			$('label[for=' + oldName + ']', schedule).attr('for', newName);
		});
		i++;
	});
	var scheduleCount = $('.schedule:visible', schedules).length;
	$('[name$=scheduleCount_' + placeIndex + ']').val(scheduleCount);
}

// Soumission du formulaire
function submitForm(event) {
	placeAutoFields.save(event.target);
	return true;
}
