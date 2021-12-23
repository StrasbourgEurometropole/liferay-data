var namespace = "_eu_strasbourg_portlet_notif_NotifBOPortlet_";
var namespaceAUI = "#" + namespace;

var selectServices = document.getElementById(namespace + "service");
var selectNactures = document.getElementById(namespace + 'nature');
var selectMessages = document.getElementById(namespace + 'message');
var content = document.getElementById(namespace + 'content');
var notificationType = document.getElementById(namespace + "notificationType");
var selectBroadcastTypes = document.getElementById(namespace + 'broadcast-type');
var selectDistricts = document.getElementById(namespace + 'district');
var labelUrl = document.getElementById(namespace + 'labelUrl');
var url = document.getElementById(namespace + 'url');

//Initialisation de l'affichage des champs
initialiseNatures();
initialiseMessages();
initialiseContent();
initialiseBroadcastTypes();
initialiseDistricts();

//Appel à réinitilisation de l'affichage des champs en fonction du service,
// du message et du type de notification
selectServices.onchange = function(){
    // on désélectionne le selecteur de natures
    selectNactures.options[0].selected = 'selected';
    initialiseNatures();
    // on désélectionne le selecteur de message
    selectMessages.options[0].selected = 'selected';
    initialiseMessages();
    // on vide le contenu
    content.value = "";
}
selectMessages.onchange = function(){
    if(selectMessages.value != "0" && selectMessages.value != ""){
        content.value = selectMessages.selectedOptions[0].label;
        var content_fr = document.getElementById(namespace + 'content_fr_FR');
        if(content_fr != undefined)
            content_fr.value = selectMessages.selectedOptions[0].attributes["data-content-fr"].value;
        var content_de = document.getElementById(namespace + 'content_de_DE');
        if(content_de != undefined)
            content_de.value = selectMessages.selectedOptions[0].attributes["data-content-de"].value;
        var content_en = document.getElementById(namespace + 'content_en_US');
        if(content_en != undefined)
            content_en.value = selectMessages.selectedOptions[0].attributes["data-content-en"].value;
    }else{
        content.value = "";
    }
    initialiseContent();
}
notificationType.onchange = function(){
    if(!notificationType.checked){
        // on désélectionne le sélecteur de type de diffusion
        selectBroadcastTypes.options[0].selected = 'selected';
    }
    initialiseBroadcastTypes();
}
selectBroadcastTypes.onchange = function(){
    initialiseDistricts();
}

// Transformation des champs select-multiple
new Choices('.choices-element', {
	removeItemButton: true
});

// gestion de l'affichage du sélecteur de natures
function initialiseNatures(){
    Array.prototype.forEach.call(selectNactures.children, function(option, i){
        if(option.attributes["data-service-id"] != undefined){
            if(option.attributes["data-service-id"].value == selectServices.value){
                option.style.display="block";
            }else{
                option.style.display="none";
            }
        }else{
            option.style.display="none";
        }
    });
}

// gestion de l'affichage du sélecteur de messages
function initialiseMessages(){
    Array.prototype.forEach.call(selectMessages.children, function(option, i){
        if(option.attributes["data-service-id"] != undefined){
            if(option.attributes["data-service-id"].value == selectServices.value){
                option.style.display="block";
            }else{
                option.style.display="none";
            }
        }
    });
    initialiseContent();
}

// gestion du champs Contenu
function initialiseContent(){
    if(isOnlyView || (selectMessages.value != "0" && selectMessages.value != "" && isContribOnly)){
        content.disabled = true;
    }else{
        content.disabled = false;
    }
}

// gestion de l'affichage du sélecteur de type de diffusion
function initialiseBroadcastTypes(){
    if(notificationType.checked){
        selectBroadcastTypes.options[1].style.display="block";
        selectBroadcastTypes.options[1].selected = 'selected';
        selectBroadcastTypes.disabled = true;
    }else{
        selectBroadcastTypes.options[1].style.display="none";
        selectBroadcastTypes.disabled = false;
    }
    initialiseDistricts();
    if(isOnlyView)
        selectBroadcastTypes.disabled = true;
}

// gestion du champs Contenu
function initialiseDistricts(){
    if(selectBroadcastTypes.value == "3"){
        selectDistricts.closest(".form-group").style.display = "block";
    }else{
        // on désélectionne le selecteur de natures
        selectDistricts.options[0].selected = 'selected';
        selectDistricts.closest(".form-group").style.display = "none";
    }
}

var submitButton = document.getElementsByClassName('saveButton')[0];
submitButton.onclick = function(event){
    allValidate = true;

    // Validation des champs obligatoires conditionnels
    AUI().use('liferay-form',function() {
        var rules = Liferay.Form.get(namespace + 'fm').formValidator
                .get('rules');
        if (selectBroadcastTypes.value != "3") {
            rules[namespace + 'district'].required = false;
        } else {
            rules[namespace + 'district'].required = true;
        }
    });

    var startDate = $(namespaceAUI + "startDate").val();
    var endDate = $(namespaceAUI + "endDate").val();
    if(endDate != ""){
        // on vérifie que la date de début soit <= à la date de fin
        if(!comparDatesYMD(startDate, endDate)){
            $('.incorrect-date').show();
            if(allValidate){
                $('html,body').animate({scrollTop: $(namespaceAUI + "startDate").offset().top - 100}, 'slow');
                allValidate = false;
            }
        }else{
            $('.incorrect-date').hide();
        }
    }

    var labelUrlValue = labelUrl.value;
    var urlValue = url.value;
    debugger;
    if (labelUrlValue !== "" && urlValue === "" || labelUrlValue === "" && urlValue !== "") {
        $('.incorrect-labelUrl-url').show();
        if(allValidate){
            $('html,body').animate({scrollTop: $(namespaceAUI + "content").offset().top - 100}, 'slow');
            allValidate = false;
        }
    } else {
        $('.incorrect-labelUrl-url').hide();
    }

    if (!allValidate) {
       event.preventDefault();
    }
}

//Soumission du formulaire
function submitForm(event) {
    // on enlève le disable pour pouvoir récupérer les infos
    content.disabled = false;
    selectBroadcastTypes.disabled = false;
}

function comparDatesYMD(startDate, endDate) {
	var startDay = parseInt(startDate.substr(0, 2));
	var startMonth = parseInt(startDate.substr(3, 2)) - 1;
	var startYear = parseInt(startDate.substr(6, 4));
	var date1 = new Date(startYear, startMonth, startDay, 0, 0, 0, 0);

    var endDay = parseInt(endDate.substr(0, 2));
    var endMonth = parseInt(endDate.substr(3, 2)) - 1;
    var endYear = parseInt(endDate.substr(6, 4));
    var date2 = new Date(endYear, endMonth, endDay, 0, 0, 0, 0);

	// si la date d'arrviée et superieur a la date de depart en afficher un
	// message d'erreur
	if (date1.getTime() > date2.getTime()) {
		return false;
	} else {
		return true;
	}

}
