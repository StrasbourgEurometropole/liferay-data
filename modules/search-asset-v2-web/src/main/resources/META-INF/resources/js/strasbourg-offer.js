document.getElementById("createAlert").onclick = function(e){
    // on vérifie que l'utilisateur est connecté
    if(window.publikInternalId != undefined){
        document.getElementById("create-alert-form").style.display = "flex";
    }else{
        window.createPopin(Liferay.Language.get('log-in-to-create-alert'),function() {
            window.location = window.loginURL;
        },undefined,Liferay.Language.get('eu.language.ejob.sign.in'), Liferay.Language.get('continu-research'));
    }
};

document.getElementById("RecordAlert").onclick = function(e){
    var categoriesId = "";
    var allSelect = document.getElementsByClassName("vocabularies-offer");
    var select;
    for (select of allSelect) {
        var options = select && select.options;
        var option;
        for (option of options) {
            if (option.selected) {
                if(categoriesId != "")
                    categoriesId += ",";
                categoriesId += option.value;
            }
        }
    }
    var alertToCreate = {
        name: document.getElementById("alertName").value,
        categoriesId: categoriesId,
        keyword: document.getElementById("keyword").value,
        languageId: window.themeDisplay.getLanguageId()
    };
    Liferay.Service(
        '/ejob.alert/add-alert',
        alertToCreate,
        function(obj) {
            if (obj.hasOwnProperty('success')) {
                window.createPopinOK(Liferay.Language.get('alert-created'));
            } else if (obj.hasOwnProperty('error')) {
                switch(obj['error']){
                    case 'notConnected':
                        window.createPopin(Liferay.Language.get('log-in-to-create-alert'), function() {
                            window.location = window.loginURL;
                        }, undefined, Liferay.Language.get('eu.language.ejob.sign.in'), Liferay.Language.get('continu-research'));
                        break;
                    case 'alreadyExist':
                        window.createPopinOK(Liferay.Language.get('already-exist-alert'));
                        break;
                    case 'alertNeedsName':
                        window.createPopinOK(Liferay.Language.get('eu.language.ejob.alert.needs.name'));
                        break;
                    default:
                        console.log(obj['error']);
                        window.createPopinOK(Liferay.Language.get('error-occured'));
                }
            }

        }
    );
};