var blocVocabulary = "" +
"<div class='vocabulary-configuration' id='vocabulary%%INDEX%%'>" +
    "<input class='field form-control' id='" + namespace + "numVocabulary%%INDEX%%' name='" + namespace + "numVocabulary%%INDEX%%' type='hidden' value='%%INDEX%%'>" +
    "<div class='form-group form-group-inline input-select-wrapper2'>" +
        "<select class='form-control' name='" + namespace + "vocabularyIds_%%INDEX%%' id='" + namespace + "vocabularyIds_%%INDEX%%'>" +
            "<option value='false'>" + Liferay.Language.get('select-vocabulary') +"</option>" +
        "</select>" +
    "</div>" +
    "     <div class='form-group form-group-inline input-select-wrapper'>" +
        "<select class='form-control' id='" + namespace + "vocabularyControlType_%%INDEX%%' name='" + namespace + "vocabularyControlType_%%INDEX%%'>" +
            "<option class='' value='list'>" + Liferay.Language.get("dropdown-list") + "</option>" +
            "<option class='' value='radio'>" + Liferay.Language.get("radio-button") + "</option>" +
            "<option class='' value='check'>" + Liferay.Language.get("checkbox") + "</option>" +
        "</select>" +
    "</div>     " +
    "<button class='form-group btn btn-icon icon icon-trash icon-2x btn-default' id='" + namespace + "meht' onclick='deleteVocabulary(%%INDEX%%);' type='button'></button>" +
"</div>";