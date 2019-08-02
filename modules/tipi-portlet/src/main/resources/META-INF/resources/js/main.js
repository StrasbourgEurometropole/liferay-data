/**
 * Gestion des événements
 */
$(function(){
  // Validation du formulaire
  $('[name=submitBill]').on('click', function(event) {
    event.preventDefault();
    var errorMessage = '';
    var isValid = true;
    // Champs obligatoires
    if ($('input#ref').val().length === 0 ||
          $('input#year').val().length === 0 ||
          $('input#integerPrice').val().length === 0 ||
          $('input#decimalPrice').val().length === 0 ||
          $('input#email').val().length === 0 ||
          $('input#emailConfirm').val().length === 0) {
      isValid = false;
      errorMessage += 'Veuillez renseigner tous les champs du formulaire.<br/>';
    } 
    // Numéro de commande
	switch($('input#code_appli').val()) {
		case 'EA':
			if ($('input#ref').val().length < 13) {
				isValid = false;
				errorMessage += 'Le numéro de la facture est vide ou fait moins de 13 caractères.<br/>';
			}
		break;
		case 'RZ':
			if ($('input#ref').val().length > 4 || $('input#ref').val().length < 1) {
				isValid = false;
				errorMessage += 'Le numéro de la facture est vide ou fait plus de 4 caractères.<br/>';
			}
		break;
		default:
			if ($('input#ref').val().length < 6) {
				isValid = false;
				errorMessage += 'Le numéro de la facture est vide ou fait moins de 6 caractères.<br/>';
			}           
	}

    if(!/^\d+$/.test($('input#ref').val())) {
      isValid = false;
      errorMessage += 'Le numéro de la facture ne doit être composé que de chiffres.<br/>';
    } 
    // Année
    if ($('input#year').val().length !== 4) {
      isValid = false;
      errorMessage += 'L\'année doit contenir 4 caractères numériques.<br/>';
    } 
    if (!/^\d+$/.test($('input#year').val())) {
      isValid = false;
      errorMessage += 'L\'année doit être un entier au format AAAA.<br/>';
    } 
    if(new Date().getFullYear() - 1 !== parseInt($('input#year').val()) && new Date().getFullYear() !== parseInt($('input#year').val())) {
      isValid = false;
      errorMessage += 'L\'année doit correspondre à l\'année en cours ou à l\'année précédente.<br/>';
    }
    // Montant
    if (!/^\d+$/.test($('input#integerPrice').val())) {
      isValid = false;
      errorMessage += 'La partie entière du montant à payer doit être obligatoirement renseignée à l\'identique du montant inscrit sur votre facture.<br/>';
    } 
    if (!/^\d+$/.test($('input#decimalPrice').val())) {
      isValid = false;
      errorMessage += 'La partie décimale du montant à payer doit être obligatoirement renseignée à l\'identique du montant inscrit sur votre facture.<br/>';
    }
    // Montant supérieur à 0
    if (parseInt($('input#integerPrice').val()) + parseInt($('input#decimalPrice').val()) === 0) {
    	isValid = false;
        errorMessage += 'Le montant à payer doit obligatoirement être supérieur à 0.<br/>';
    }
    // Partie décimale forcément avec 2 digits
    if ($('input#decimalPrice').val().length < 2) {
    	isValid = false;
        errorMessage += 'La partie décimale du montant à payer doit obligatoirement contenir deux chiffres, veuillez la renseigner à l\'identique du montant inscrit sur votre facture.<br/>';
    }
    // Email
    if ($('input#email').val() !==  $('input#emailConfirm').val()) {
      isValid = false;
      errorMessage += 'Vous avez entré deux adresses mails différentes, veuillez recommencer.<br/>';
    } 
    if ($('input#email').val().length <= 5) {
      isValid = false;
      errorMessage += 'L\'adresse mail est vide ou trop courte.<br/>';
    } 
    if (!/^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,6}$/.test($('input#email').val())) {
      isValid = false;
      errorMessage += 'L\'adresse mail n\'est pas valide.<br/>';
    } 
    if (!/^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,6}$/.test($('input#emailConfirm').val())) {
      isValid = false;
      errorMessage += 'L\'adresse mail de confirmation n\'est pas valide.<br/>';
    } 
    if ($('input#emailConfirm').val().length <= 5) {
      isValid = false;
      errorMessage += 'L\'adresse mail de confirmation est vide ou trop courte.<br/>';
    }

    if (isValid) {
      jQuery('.errors').hide();
      openBillingPage();  
    } else {
      jQuery('.errors').html(errorMessage).show();
    }
    
  });

  // Reset du formulaire
  $('.reset-button').on('click', function(event) {
    event.preventDefault();
    $('input#ref').val('');
    $('input#year').val('');
    $('input#integerPrice').val('');
    $('input#decimalPrice').val('');
    $('input#email').val('');
    $('input#emailConfirm').val('');
  });

});

/**
 * Formatage du parametre "price" de l'url de paiement
 */
function calculatePrice() {
  var integerPrice = document.tipiForm.integerPrice.value;
  var decimalPrice = document.tipiForm.decimalPrice.value;

  while (integerPrice.length < 2) {
    integerPrice = '0' + integerPrice;
  }
  while (decimalPrice.length < 2) {
    decimalPrice = '0' + decimalPrice;
  }

  return integerPrice + decimalPrice;
}

/**
 * Formatage du paramètre "refdet" de l'url de paiement
 */
function calculateRefdet() {
  var refdet ;
  refdet = document.tipiForm.year.value;
  refdet += document.tipiForm.appCode.value;
  refdet += '00';
  refdet += formatRef(document.tipiForm.ref.value);
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

/**
 * Ouverture de la popup de paiement
 */
function openBillingPage()  {
  var url;
  var price = calculatePrice();

  url = window.tipiURL;
  url += 'numcli=' + document.tipiForm.clientNumber.value;
  if (url.indexOf('saisie=T') === -1) { // En prod
    url += '&year=' + document.tipiForm.year.value;
    url += '&refdet=' + calculateRefdet();
  } else { // En test
    url += '&year=9999';
    url += '&refdet=999999990000000000000';
  }
  url += '&montant=' + calculatePrice();
  url += '&mel=' + document.tipiForm.email.value;
  url += '&objet=' + document.tipiForm.appCode.value;
  url += '&urlcl=' + window.tipiCallbackURL;
  window.open(url,'_blank','height=750, width=1050, toolbar=no, menubar=no,scrollbars=yes, resizable=yes, location=no, directories=no, status=no'); 
  return false; 
}

