 $(document).ready(function(){
	
	$('select[name=idSIGPlace]').select2({
		"language": {
		       "noResults": function(){
		           return "Aucun résultat";
		       },
		       "inputTooShort": function(){
		           return 'Veuillez entrer au moins 3 caractères';
		       },
		       "searching": function(){
		           return 'Recherche ...';
		       }
		   },
		minimumInputLength : 3,
		multiple : false,
		allowClear:true,
		placeholder: Liferay.Language.get("eu.event-choose-place"),
		ajax : {
			url : "/api/jsonws/place.place/get-places-by-name-and-language/",
			dataType : 'json',
			delay : 250,
			data : function(params) {
				return {
					name : params.term,
					language : 'fr_FR',
					p_auth : Liferay.authToken
				};
			},
			processResults : function(data, params) {
				// parse the results into the format expected by Select2
				// since we are using custom formatting functions we do not need to
				// alter the remote JSON data, except to indicate that infinite
				// scrolling can be used
				params.page = params.page || 1;
				 const results = [];
			     data.forEach(function makeResults(element, index) {
			        results.push({
			        	text: element.name.fr_FR,
			        	id: element.idSurfs
			        });
			      });
			     return {
			         results: results
			       };
			},
		},
		templateResult : formatPlace,
		templateSelection : formatPlaceSelection
	
	});
	
	function formatPlace(place) {
		const markup = `${place.text}`;
		return markup;
	}
	
	
	function formatPlaceSelection (place) {
	  return place.text;
	}


	if(idSIGPlace != null && idSIGPlace != "") {
		 var placeSelect = $('select[name=idSIGPlace]');
		 
		 Liferay.Service(
		  '/place.place/get-place-by-id-sig',
		  {
		    sigId: idSIGPlace
		  }).then(function(data) {
			 // create the option and append to Select2
		     var option = new Option(data.name.fr_FR, data.idSurfs, true, true);
		     placeSelect.append(option).trigger('change');
	
		     // manually trigger the `select2:select` event
		     placeSelect.trigger({
		         type: 'select2:select',
		         params: {
		             data: data
		         }
		     });
		  }
		);
	}
 });