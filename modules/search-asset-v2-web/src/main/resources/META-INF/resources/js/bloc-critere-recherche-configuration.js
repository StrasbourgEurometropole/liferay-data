var blocCritereRecherche = "" +
"<div style='margin: 20px 0;'></div>" +
"<div class='vocabulary-configuration' id='critereRecherche%%INDEX%%'>" +
    "<input class='field form-control' id='" + namespace + "numCritereRecherche%%INDEX%%' name='" + namespace + "numCritereRecherche%%INDEX%%' type='hidden' value='%%INDEX%%'>" +
    "<div class='form-group form-group-inline input-select-wrapper2'>" +
        "<select class='form-control' name='" + namespace + "vocabularyChoices_%%INDEX%%' id='" + namespace + "vocabularyChoices_%%INDEX%%'>" +
            "<option value=''>" + Liferay.Language.get('select-vocabulary') +"</option>" +
        "</select>" +
    "</div>" +
    "     <div class='form-group form-group-inline input-select-wrapper'>" +
        "<label class='control-label' for='" + namespace + "vocabularyControlType_%%INDEX%%'>vocabulary-control-type_%%INDEX%%</label>" +
        "<select class='form-control' id='" + namespace + "vocabularyControlType_%%INDEX%%' name='" + namespace + "vocabularyControlType_%%INDEX%%'>" +
            "<option class='' value='list'>" + Liferay.Language.get("dropdown-list") + "</option>" +
            "<option class='' value='radio'>" + Liferay.Language.get("radio-button") + "</option>" +
            "<option class='' value='check'>" + Liferay.Language.get("checkbox") + "</option>" +
        "</select>" +
    "</div>     " +
    "<button class='form-group btn btn-icon icon icon-trash icon-2x btn-default' id='" + namespace + "meht' onclick='deleteCritereRecherche(%%INDEX%%);' type='button'></button>" +
"</div>";