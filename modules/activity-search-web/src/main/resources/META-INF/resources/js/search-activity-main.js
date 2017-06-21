

//Autocomplete des activités
var activitiesOptions = {
	appendTo: '.activity-autocomplete-input-wrapper',
	lookup: function (query, done) {
		Liferay.Service(
		  '/activity.activity/get-activities',
		  {
		    groupId: Liferay.ThemeDisplay.getScopeGroupId(),
		    name: query,
		    language: ''
		  },
		  function(obj) {
			 var suggestions = jQuery.map(
				obj, function(
						dataItem) {
					return {
						value : dataItem.title.fr_FR,
						data : dataItem.id
					};
				});

			done({
				suggestions: suggestions
			});
		  }
		)
	},
	onSelect : function(suggestion) {
		jQuery('#activity-autocomplete-hidden-value input').val(
				suggestion.data);
		jQuery('input.selected-activity').val(suggestion.value);
	}
};
jQuery('.activity-autocomplete-input-wrapper input').autocomplete(activitiesOptions);

//Autocomplete des lieux
var options = {
	appendTo: '.place-autocomplete-input-wrapper',
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
		jQuery('#place-autocomplete-hidden-value input').val(
				suggestion.data);
		jQuery('input.selected-place').val(suggestion.value);
	}
};
jQuery('.place-autocomplete-input-wrapper input').autocomplete(options);