const namespaceAUI = "#" + namespace;
var prefiltersChoicesJson = JSON.parse('{}');

$( document ).ready(function() {
    $("select[id^='" + namespace + "scopeIds']").each(function() {
        initialiseScope($(this));
    });

    $("select[id^='" + namespace + "prefilterChoices_']").each(function (){
        initializePrefilter($(this));
    })
});

// WIP Mettre a jour les templates lors de la selection
function updateTemplates(index) {
    // Changement du label du contenu
    $($("#assetType" + index + ' a')[0]).text(Liferay.Language.get($(namespaceAUI + "classname_" + index).val()));
    // MaJ des templates
    templates = assetTemplates[$(namespaceAUI + "classname_" + index).val()];
    options = "<option>" + Liferay.Language.get('select-a-template') + "</option>";
    $.each(templates, function(i, template) {
        options += "<option value=' " + template.id + " '>" + template.value + "</option>";
    });
    $(namespaceAUI + "templateKey_" + index).html(options);
}

function addAssetType() {
	var nbAssetType = $(namespaceAUI + 'nbAssetType').val();
	var lastAssetType = parseInt($('input[name*=numAssetType]')[nbAssetType - 1].id
				.split(namespace + 'numAssetType')[1]) + 1;

	$('#asset-types-content').append(blocAssetType.replace(/%%INDEX%%/gi, lastAssetType));
	initialiseScope($(namespaceAUI + "scopeIds_" + lastAssetType));
	initializePrefilter($(namespaceAUI + "prefilterChoices_" + lastAssetType + "_0"));

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbAssetType').val(parseInt(nbAssetType) + 1);
}

function deleteAssetType(index) {
	var nbAssetType = $(namespaceAUI + 'nbAssetType').val();
	$('#assetType' + index).closest('.card-horizontal').remove();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbAssetType').val(parseInt(nbAssetType) - 1);
}

// TODO Insertion Html cf maquette pour regle de prefiltrage
function addPrefilter(indexAssetType) {
	var nbPrefilterByAssetType = $(namespaceAUI + 'nbPrefiltre' + indexAssetType).val();
	var lastPrefilterByAssetType = parseInt($('input[name*=numPrefiltre' + indexAssetType + ']')[nbPrefilterByAssetType - 1].id
				.split(namespace + 'numPrefiltre' + indexAssetType + '_')[1]) + 1;

	$('#prefilters-content' + indexAssetType).append(blocPrefilter.replace(/%%INDEXTYPE%%/gi, indexAssetType).replace(/%%INDEXPREFILTRE%%/gi, lastPrefilterByAssetType));
	initializePrefilter($(namespaceAUI + "prefilterChoices_" + indexAssetType + "_" + lastPrefilterByAssetType));

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbPrefiltre' + indexAssetType).val(parseInt(nbPrefilterByAssetType) + 1);
}

function deletePrefilter(indexAssetType, indexPrefilter) {
	var nbPrefilterByAssetType = $(namespaceAUI + 'nbPrefiltre' + indexAssetType).val();

	$('#prefilter' + indexAssetType + "_" + indexPrefilter).remove();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbPrefiltre' + indexAssetType).val(parseInt(nbPrefilterByAssetType) - 1);
}

function initialiseScope(elt){
    // Selecteur choices portees
    choix = new Choices("#"+elt.attr("id"), {
        removeItemButton: true,
        loadingText: 'Chargement...',
        noResultsText: 'Auncun résultat',
        noChoicesText: 'Pas de choix',
        itemSelectText: 'Cliquer pour sélectionner',
        choices: scopesJson
    });
}

// Creation choices prefiltre
function initializePrefilter(elt){
    choice = new Choices("#"+elt.attr("id"), {
        removeItemButton: true,
        loadingText: 'Chargement...',
        noResultsText: 'Auncun résultat',
        noChoicesText: 'Pas de choix',
        itemSelectText: 'Cliquer pour sélectionner',
        choices: tagsJson
    });

    let indexeGlobal = elt.attr("id").slice(namespace.length + + 'prefilterChoices_'.length);
    prefiltersChoicesJson[indexeGlobal] = choice;
}

// Mise a jour choices prefiltre
function updatePrefilterChoices(elt){
    // Get indexes
    let indexeGlobal = elt.id.slice(namespace.length + + 'categoriesOrTags_'.length);
    choice = prefiltersChoicesJson[indexeGlobal];
    choice.removeActiveItems();
    if ($(elt).val() == "tags") {
        choice.setChoices(tagsJson, "value", "label", true);
    }
    else {
        indexeType = indexeGlobal.split('_')[0];
        let classname = $(namespaceAUI + "classname_" + indexeType).val();
        let tempJson = categoriesJson[classname];
        choice.setChoices(tempJson, "value", "label", true);
    }
}