var namespace = "_eu_strasbourg_portlet_notif_NotifBOPortlet_";

var selectServices = document.getElementById(namespace + "service");
var selectNactures = document.getElementById(namespace + 'nature');
var selectMessages = document.getElementById(namespace + 'message');
var content = document.getElementById(namespace + 'content');
var notificationType = document.getElementById(namespace + "notificationType");
var selectBroadcastTypes = document.getElementById(namespace + 'broadcast-type');

//Initialisation de l'affichage des champs
initialiseNatures();
initialiseMessages();
initialiseContent();
initialiseBroadcastTypes();

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
    if(selectMessages.value != "0" && selectMessages.value != "" && isContribOnly){
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
}


//Soumission du formulaire
function submitForm(event) {
    // on enlève le disable pour pouvoir récupérer les infos
    content.disabled = false;
    selectBroadcastTypes.disabled = false;
}
