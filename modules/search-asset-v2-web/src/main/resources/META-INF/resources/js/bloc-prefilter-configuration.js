var blocPrefilter = "" +
"<div id='prefilter%%INDEXTYPE%%_%%INDEXPREFILTRE%%'>" +
    "<input class='field form-control' id='" + namespace + "numPrefiltre%%INDEXTYPE%%_%%INDEXPREFILTRE%%' name='" + namespace + "numPrefiltre%%INDEXTYPE%%_%%INDEXPREFILTRE%%' type='hidden' value='%%INDEXPREFILTRE%%'>" +
    "<div class='form-group form-group-inline input-select-wrapper'>" +
        "<select class='form-control' id='" + namespace + "includeOrExclude_%%INDEXTYPE%%_%%INDEXPREFILTRE%%' name='" + namespace + "includeOrExclude_%%INDEXTYPE%%_%%INDEXPREFILTRE%%' title='include-or-exclude_%%INDEXTYPE%%_%%INDEXPREFILTRE%%'>" +
            "<option class='' selected='' value='contains'>" +
                Liferay.Language.get('eu.search.asset.web.configuration.contains') +
            "</option>" +
            "<option class='' value='notContains'>" +
                Liferay.Language.get('eu.search.asset.web.configuration.notContains') +
            "</option>" +
        "</select>" +
    "</div>" +
    "<div class='form-group form-group-inline input-select-wrapper'>" +
        "<select class='form-control' id='" + namespace + "allOrAny_%%INDEXTYPE%%_%%INDEXPREFILTRE%%' name='" + namespace + "allOrAny_%%INDEXTYPE%%_%%INDEXPREFILTRE%%' title='all-or-any_%%INDEXTYPE%%_%%INDEXPREFILTRE%%'>" +
            "<option class='' selected='' value='all'>" +
                Liferay.Language.get('eu.search.asset.web.configuration.all') +
            "</option>" +
            "<option class='' value='any'>" +
                Liferay.Language.get('eu.search.asset.web.configuration.any') +
            "</option>" +
        "</select>" +
    "</div>" +
    "<span class='form-group form-group-inline'>parmi</span>" +
    "<div class='form-group form-group-inline input-select-wrapper'>" +
        "<select class='form-control' id='" + namespace + "categoriesOrTags_%%INDEXTYPE%%_%%INDEXPREFILTRE%%' name='" + namespace + "%%INDEXTYPE%%_%%INDEXPREFILTRE%%' onchange='updatePrefilterChoices(this)' title='categories-or-tags_%%INDEXTYPE%%_%%INDEXPREFILTRE%%'>" +
            "<option class='' value='categories'>" +
                Liferay.Language.get('eu.search.asset.web.configuration.categories') +
            "</option>" +
            "<option class='' selected='' value='tags'>" +
                Liferay.Language.get('eu.search.asset.web.configuration.tags') +
            "</option>" +
        "</select>" +
    "</div>" +
    "<button class='btn btn-icon icon icon-trash icon-2x btn-default' id='" + namespace + "meht' onclick='deletePrefilter(%%INDEXTYPE%%,%%INDEXPREFILTRE%%);' type='button'></button>" +
    "<select class='form-control' name='" + namespace + "prefilterChoices_%%INDEXTYPE%%_%%INDEXPREFILTRE%%' id='" + namespace + "prefilterChoices_%%INDEXTYPE%%_%%INDEXPREFILTRE%%' multiple=''>" +
        "<option placeholder>" + Liferay.Language.get('select-prefilters') +"</option>" +
    "</select>" +
"</div>";