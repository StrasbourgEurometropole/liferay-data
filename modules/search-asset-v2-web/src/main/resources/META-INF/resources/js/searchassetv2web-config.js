const namespaceAUI = "#" + namespace;
var scopesChoicesJson = JSON.parse('{}');
var prefiltersChoicesJson = JSON.parse('{}');

$( document ).ready(function() {
    $("select[id^='" + namespace + "scopeIds']").each(function() {
        initializeScope($(this));
    });

    $("select[id^='" + namespace + "prefilterChoices_']").each(function (){
        initializePrefilter($(this));
    })
});

// Ajout bloc assetType
function addAssetType() {
	var nbAssetType = $(namespaceAUI + 'nbAssetType').val();
	var lastAssetType = parseInt($('input[name*=numAssetType]')[nbAssetType - 1].id
				.split(namespace + 'numAssetType')[1]) + 1;

	$('#asset-types-content').append(blocAssetType.replace(/%%INDEX%%/gi, lastAssetType));
	initializeScope($(namespaceAUI + "scopeIds_" + lastAssetType));
	initializePrefilter($(namespaceAUI + "prefilterChoices_" + lastAssetType + "_0"));

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbAssetType').val(parseInt(nbAssetType) + 1);
}

// Suppression d'un bloc assetType
function deleteAssetType(index) {
	var nbAssetType = $(namespaceAUI + 'nbAssetType').val();
	$('#assetType' + index).closest('.card-horizontal').remove();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbAssetType').val(parseInt(nbAssetType) - 1);
}

// Creation choices scope
function initializeScope(elt){
    // Selecteur choices portees
    choice = new Choices("#"+elt.attr("id"), {
        removeItemButton: true,
        loadingText: 'Chargement...',
        noResultsText: 'Auncun résultat',
        noChoicesText: 'Pas de choix',
        itemSelectText: 'Cliquer pour sélectionner',
        searchResultLimit: 30,
        choices: scopesJson
    });

    index = elt.attr("id").slice(namespace.length + + 'scopeIds_'.length);
    scopesChoicesJson[index] = choice;
}

// Ajout bloc prefiltrage
function addPrefilter(indexAssetType) {
	var nbPrefilterByAssetType = $(namespaceAUI + 'nbPrefiltre' + indexAssetType).val();
	var lastPrefilterByAssetType = parseInt($('input[name*=numPrefiltre' + indexAssetType + ']')[nbPrefilterByAssetType - 1].id
				.split(namespace + 'numPrefiltre' + indexAssetType + '_')[1]) + 1;

	$('#prefilters-content' + indexAssetType).append(blocPrefilter.replace(/%%INDEXTYPE%%/gi, indexAssetType).replace(/%%INDEXPREFILTRE%%/gi, lastPrefilterByAssetType));
	initializePrefilter($(namespaceAUI + "prefilterChoices_" + indexAssetType + "_" + lastPrefilterByAssetType));

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbPrefiltre' + indexAssetType).val(parseInt(nbPrefilterByAssetType) + 1);
}

// Suppression d'un bloc prefiltrage
function deletePrefilter(indexAssetType, indexPrefilter) {
	var nbPrefilterByAssetType = $(namespaceAUI + 'nbPrefiltre' + indexAssetType).val();

	$('#prefilter' + indexAssetType + "_" + indexPrefilter).remove();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbPrefiltre' + indexAssetType).val(parseInt(nbPrefilterByAssetType) - 1);
}

// Creation choices prefiltre
function initializePrefilter(elt){
    choice = new Choices("#"+elt.attr("id"), {
        removeItemButton: true,
        loadingText: 'Chargement...',
        noResultsText: 'Auncun résultat',
        noChoicesText: 'Pas de choix',
        itemSelectText: 'Cliquer pour sélectionner',
        searchResultLimit: 30,
        sorter: function(a, b) {
            if(a.customProperties != undefined)
                return a.customProperties.random < b.customProperties.random ? -1 : a.customProperties.random > b.customProperties.random;
        }
    });

    // Get indexes
    indexGlobal = elt.attr("id").slice(namespace.length + + 'prefilterChoices_'.length);
    indexType = indexGlobal.split('_')[0];
    groupIds = $(namespaceAUI + "scopeIds_" + indexType).val();
    if (groupIds != "") {
        Liferay.Service('/strasbourg.strasbourg/get-tags-by-group-ids',
            {
                groupIds: groupIds
            },
            function(json) {
                choice.setChoices(json, "value", "label", true);
            }
        );
    }

    prefiltersChoicesJson[indexGlobal] = choice;
}

// Mise a jour choices prefiltre
function updatePrefilterChoices(elt){
    // Get indexes
    indexGlobal = elt.id.slice(namespace.length + 'categoriesOrTags_'.length);
    indexType = indexGlobal.split('_')[0];
    groupIds = $(namespaceAUI + "scopeIds_" + indexType).val();
    choice = prefiltersChoicesJson[indexGlobal];
    itemsId = choice.getValue(true);
    choice.removeActiveItems();
    if ($(elt).val() == "tags") {
        Liferay.Service('/strasbourg.strasbourg/get-tags-by-group-ids',
            {
                groupIds: groupIds
            },
            function(json) {
                choice.setChoices(json, "value", "label", true);
                choice.setChoiceByValue(itemsId);
            }
        );
    }
    else {
        classname = $(namespaceAUI + "classname_" + indexType).val();

        Liferay.Service('/strasbourg.strasbourg/get-categories-by-class-name-and-group-ids',
            {
                groupIds: groupIds,
                className: classname
            },
            function(json) {
                choice.setChoices(json, "value", "label", true);
                choice.setChoiceByValue(itemsId);
            }
        );
    }
}

// Réinitialise les templates et les préfiltres lors de la selection d'un type d'asset
function reinitializeBloc(index) {
    // Changement du label du contenu
    $($("#assetType" + index + ' a')[0]).text(Liferay.Language.get($(namespaceAUI + "classname_" + index).val()));

    // MaJ des templates
    templates = assetTemplates[$(namespaceAUI + "classname_" + index).val()];
    options = "<option>" + Liferay.Language.get('select-a-template') + "</option>";
    $.each(templates, function(i, template) {
        options += "<option value=' " + template.id + " '>" + template.value + "</option>";
    });
    $(namespaceAUI + "templateKey_" + index).html(options);

    // vide la friendlyURL
    $(namespaceAUI + "friendlyUrl_" + index).val("");

    // effacement de la portée
    scopes = scopesChoicesJson[index];
    scopes.removeActiveItems();

    // vide les préfiltres
    $("select[id^='" + namespace + "prefilterChoices_" + index + "']").each(function (){
        indexPrefilter = $(this).attr('id').split("_"+index+"_")[1];
        if(indexPrefilter > 0)
            deletePrefilter(index, indexPrefilter);
        else{
            $(namespaceAUI + "categoriesOrTags_" + index + "_" + indexPrefilter).val("tags");
            prefilters = prefiltersChoicesJson[index + "_" + indexPrefilter];
            prefilters.removeActiveItems();
            prefilters.clearChoices();
        }
    })
}

// Réinitialise les préfiltres lors de la selection d'un groupe
function reinitializePrefilter(index) {
    groupIds = $(namespaceAUI + "scopeIds_" + index).val();
    Liferay.Service('/strasbourg.strasbourg/get-tags-by-group-ids',
        {
            groupIds: groupIds
        },
        function(json) {
            $("select[id^='" + namespace + "categoriesOrTags_" + index + "']").each(function (){
                if ($(this).val() == "tags") {
                    indexGlobal = this.id.slice(namespace.length + 'categoriesOrTags_'.length);
                    choice = prefiltersChoicesJson[indexGlobal];
                    itemsId = choice.getValue(true);
                    choice.removeActiveItems();
                    choice.setChoices(json, "value", "label", true);
                    choice.setChoiceByValue(itemsId);
                }
            })
        }
    );
}