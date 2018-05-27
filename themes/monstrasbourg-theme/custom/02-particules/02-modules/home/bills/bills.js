jQuery(function() {
    jQuery('#formFactures').submit( function(event) {
	    event.preventDefault();
	    var errorMessage = '';
	    var isValid = true;
	    // Champs obligatoires
	    if ($('input#ref').val().length === 0 ||
	          $('input#year').val().length === 0 ||
	          $('input#amount').val().length === 0 ||
	          $('input#email').val().length === 0 ||
	          $('#type_facture')[0].selectedIndex === 0) {
	      isValid = false;
	      errorMessage += 'Veuillez renseigner tous les champs du formulaire.<br/>';
	    } 
	    // Type de facture
	    var selectedFacture = $('#type_facture')[0].selectedIndex;
		var type_facture = $('#type_facture')[0].options[selectedFacture].value;
	    if (selectedFacture === 0) {
	      isValid = false;
	      errorMessage += 'Veuillez choisir un type de facture.<br/>';
          $('div.customSelect').addClass('error');
	    }else{
          $('div.customSelect').removeClass('error');
	    }
	    // Numéro de commande
	    if ($('input#ref').val().length < 6 || ($('input#ref').val().length < 13 && type_facture === 'water')) {
	      isValid = false;
	      errorMessage += 'Le numéro de la facture est vide ou trop court.<br/>';
	    } 
	    // Année
	    if ($('input#year').val().length !== 4) {
	      isValid = false;
	      errorMessage += 'L\'année doit contenir 4 caractères numériques.<br/>';
	    }else{
		    if(new Date().getFullYear() - 1 !== parseInt($('input#year').val()) && new Date().getFullYear() !== parseInt($('input#year').val())) {
		      isValid = false;
		      errorMessage += 'L\'année doit correspondre à l\'année en cours ou à l\'année précédente.<br/>';
	  		}
	    }
	    // Montant supérieur à 0
	    if (parseInt($('input#amount').val()) === 0) {
	    	isValid = false;
	        errorMessage += 'Le montant à payer doit obligatoirement être supérieur à 0.<br/>';
	    }
	    // Email
	    if ($('input#email').val().length === 0) {
	      isValid = false;
	      errorMessage += 'Veuillez renseigner l\'adresse mail.<br/>';
	    }else{
		    if (!/^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,6}$/.test($('input#email').val())) {
		      isValid = false;
		      errorMessage += 'L\'adresse mail n\'est pas valide.<br/>';
		    } 
		}

	    if (isValid) {
		    jQuery('.errors').hide();

			var url = window.tipiURL;
			var appCode = "";
			var clientNumber = "";
			if (type_facture == "childhood") {
				appCode = "PF";
				clientNumber = "000696";
			} else if (type_facture == "schoolRestaurant") {
				appCode = "RS";
				clientNumber = "000696";
			} else if (type_facture == "afterSchool") {
				appCode = "GA";
				clientNumber = "000696";
			} else if (type_facture == "water") {
				appCode = "EA";
				clientNumber = "007964";
			}
		  	url += 'numcli=' + clientNumber;
			if (url.indexOf('saisie=T') === -1) { // En prod
				url += '&year=' + $('input#year').val();
				url += '&refdet=' + calculateRefdet($('input#year').val(), appCode, $('input#ref').val());
			} else { // En test
				url += '&year=9999';
				url += '&refdet=999999990000000000000';
			}

			url += '&montant=' + calculatePrice();
			url += '&mel=' + $('input#email').val();
			url += '&objet=' + appCode;
			url += '&urlcl=' + window.tipiCallbackURL;
			window.open(url,'_blank','height=750, width=1050, toolbar=no, menubar=no,scrollbars=yes, resizable=yes, location=no, directories=no, status=no'); 
			return false; 
	    } else {
	      jQuery('.errors').html(errorMessage).show();
	    }
    });
});

/**
 * Formatage du parametre "price" de l'url de paiement
 */
function calculatePrice() {
  var amount = $('input#amount').val().split('.');
  var integerPrice = amount[0];
  var decimalPrice = amount[1] ? amount[1] : '0';

  while (integerPrice.length < 2) {
    integerPrice = '0' + integerPrice;
  }
  if(decimalPrice.length > 2){
  	decimalPrice = decimalPrice.substr(0,2);
  }
  else{
	  while (decimalPrice.length < 2) {
	    decimalPrice = '0' + decimalPrice;
	  }
	}

  return integerPrice + decimalPrice;
}

/**
 * Formatage du paramètre "refdet" de l'url de paiement
 */
function calculateRefdet(year, appCode, ref) {
  var refdet ;
  refdet = year;
  refdet += appCode;
  refdet += '00';
  refdet += formatRef(ref);
  return refdet;
}

/**
 * Formatage du numero de facture
 */
function formatRef(ref) 
{ 
  while (ref.length < 13) {
    ref = '0' + ref;
  }

  return ref;
}