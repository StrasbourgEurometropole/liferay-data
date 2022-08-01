var natureAutoFields = undefined; // Référence au champ nom de nature répétable (setté plus loin)
var messageAutoFields = undefined; // Référence au champ message répétable (setté plus loin)

var namespace = "_eu_strasbourg_portlet_notif_NotifBOPortlet_";

//Soumission du formulaire
function submitForm(event) {
    try{
        //Gestion des validateurs
        allValidate = true;
        $('.error').html("");

        // on met à jour les indexes des natures et messages
        natureAutoFields.save(event.target);
        messageAutoFields.save(event.target);

        // validation des/de la nature(s)
        var natureFields = document.getElementById("nature-fields");
        Array.prototype.forEach.call(natureFields.getElementsByClassName("lfr-form-row"), function(natureField) {
            // On ne lance la validation que si l'élément ne contient pas la classe "hide"
            if(!natureField.classList.contains("hide")){
                var natureName = natureField.querySelectorAll('input[id^=' + namespace + 'natureName]')[0];
                if(natureName.value == ""){
                    // on affiche le message d'erreur
                    natureField.getElementsByClassName('help-block')[0].style.display = "block";
                    // on affiche l'input en erreur
                    natureName.closest(".form-group").classList.add('has-error');
                    if(allValidate){
                        // on scroll sur l'erreur
                        natureName.scrollIntoView();
                        allValidate = false;
                    }
                }else{
                    // on masque le message d'erreur
                    natureField.getElementsByClassName('help-block')[0].style.display = "none";
                }
            }
        });

        // validation des/du message(s)
        var messageFields = document.getElementById("message-fields");
        Array.prototype.forEach.call(messageFields.getElementsByClassName("lfr-form-row"), function(messageField) {
            if(!messageField.classList.contains("hide")){
                var content = messageField.querySelectorAll('input[id^=' + namespace + 'content]')[0];
                if(content.value == ""){
                    // on affiche le message d'erreur
                    messageField.getElementsByClassName('help-block')[0].style.display = "block";
                    // on affiche l'input en erreur
                    content.closest(".form-group").classList.add('has-error');
                    if(allValidate){
                        // on scroll sur l'erreur
                        content.scrollIntoView();
                        allValidate = false;
                    }
                }if(content.value.length > 1000){
                     // on affiche le message d'erreur
                     messageField.getElementsByClassName('length-error')[0].style.display = "block";
                     // on affiche l'input en erreur
                     content.closest(".form-group").classList.add('has-error');
                     if(allValidate){
                         // on scroll sur l'erreur
                         content.scrollIntoView();
                         allValidate = false;
                     }
                }
                else{
                    // on masque le message d'erreur
                    messageField.getElementsByClassName('help-block')[0].style.display = "none";
                    messageField.getElementsByClassName('length-error')[0].style.display = "none";
                }
            }
        });

        if (!allValidate) {
           event.preventDefault();
        }
    }
    catch(err) {
        $('.error').html("<div class='alert alert-danger'><strong>Erreur JavaScript : </strong>" + err.message + ".<br> " + Liferay.Language.get('eu.strasbourg.notif.error-javascript') + "</div>");
        event.preventDefault();
    }

	return true;
}

// gestion des champs répétables
(function($) {
	// Configuration des autofields
	AUI().use('liferay-auto-fields', function(Y) {
        var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');

		if (!!document.getElementById('nature-fields')) {
			// Création de l'autofield des natures
			natureAutoFields = new Liferay.AutoFields({
				contentBox : '#nature-fields',
				fieldIndexes : namespace + 'serviceNaturesIndexes',
				namespace : namespace,
				url : getServiceNatureRowJSPURL
			}).render();
		}

		if (!!document.getElementById('message-fields')) {
			// Création de l'autofield des messages
			messageAutoFields = new Liferay.AutoFields({
				contentBox : '#message-fields',
				fieldIndexes : namespace + 'serviceMessagesIndexes',
				namespace : namespace,
				url : getServiceMessageRowJSPURL,
			}).render();
		}
	});
})(jQuery);