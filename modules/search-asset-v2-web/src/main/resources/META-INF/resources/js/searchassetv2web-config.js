const namespaceAUI = "#" + namespace;
var scopesChoicesJson = JSON.parse('{}');
var prefiltersChoicesJson = JSON.parse('{}');
var allGroupIds = [];
var vocabulariesChoicesJson = JSON.parse('{}');
var groupByChoices = JSON.parse('{}');

$( document ).ready(function() {
    // initialise les types d'asset
    $("fieldset[id^='assetType']").each(function() {
        index = $(this).attr("id").slice('assetType'.length);

        initializeBloc(index);
    });

    // MaJ des groupes
    getGroups();

    // initialise les selecteurs de vocabulaires
    $("select[id^='" + namespace + "vocabularyIds_']").each(function() {
        initializeVocabulary($(this));
    });

    // initialise le selecteur de groupement
    initializeGroupBy($(namespaceAUI + "groupBy"));
});

// Initialise le bloc de type de contenu
function initializeBloc(index) {

    // on initialise la portée
    initializeScope($(namespaceAUI + "scopeIds_" + index));
    // on la cache si c'est une procédure
    if($(namespaceAUI + "classname_" + index).val() == "searchDemarche")
        $("#scope" + index).hide();
    else{
        $("#scope" + index).show();
    }

    groupIds = $(namespaceAUI + "scopeIds_" + index).val();

    // on affiche la structure pour les contenus web
    structureId = "";
    if($(namespaceAUI + "classname_" + index).val() == "searchJournalArticle"){
        $("#structure" + index).show();
        structureSelected = $(namespaceAUI + 'structureSelectedId' + index);
        if(structureSelected != undefined)
            structureId = $(structureSelected).val();
        Liferay.Service('/strasbourg.strasbourg/get-structures-by-group-ids',
            {
                groupIds: groupIds
            },
            function(jsonStructures) {
                structureSelected = $(namespaceAUI + 'structureSelectedId' + index);
                if(structureSelected != undefined)
                    structureId = $(structureSelected).val();
                optionsStructure = "<option value=''>" + Liferay.Language.get('select-a-structure') + "</option>";
                $.each(jsonStructures, function(i, jsonStructure) {
                    optionsStructure += "<option value='" + jsonStructure.id + "' " + (jsonStructure.id==structureId?"selected":"") + ">" + jsonStructure.value + "</option>";
                });
                $(namespaceAUI + "structure_" + index).html(optionsStructure);
            }
        );
    }else
        $("#structure" + index).hide();


    // on cache le template si c'est une procédure
    if($(namespaceAUI + "classname_" + index).val() == "searchDemarche")
        $("#template-and-url" + index).hide();
    else{
        $("#template-and-url" + index).show();
        templateId = '';
        templateSelected = $(namespaceAUI + 'templateSelectedId' + index);
        if(templateSelected != undefined)
            templateId = $(templateSelected).val();
        templates = assetTemplates[$(namespaceAUI + "classname_" + index).val()];
        optionsTemplate = "<option>" + Liferay.Language.get('select-a-template') + "</option>";
        $.each(templates, function(i, template) {
            optionsTemplate += "<option value='" + template.id + "' " + (template.id==templateId?"selected":"") + ">" + template.value + "</option>";
        });
        $(namespaceAUI + "templateKey_" + index).html(optionsTemplate);

        // on cache la friendlyURL si c'est un contenu web ou un fichier
        if($(namespaceAUI + "classname_" + index).val() == "searchJournalArticle" || $(namespaceAUI + "classname_" + index).val() == "searchDocument")
            $(namespaceAUI + "friendlyUrl_" + index).hide();
        else
            $(namespaceAUI + "friendlyUrl_" + index).show();
    }

    // on initialize le préfiltre
    initializePrefilter(index);
    // on cache les préfiltres si c'est une procédure
    if($(namespaceAUI + "classname_" + index).val() == "searchDemarche")
        $("#prefilter" + index).hide();
    else{
        $("#prefilter" + index).show();
    }
}

// Creation du choices des sélecteurs de préfiltres
function initializePrefilter(index){
    groupIds = $(namespaceAUI + "scopeIds_" + index).val();
    classname = $(namespaceAUI + "classname_" + index).val();

    Liferay.Service('/strasbourg.strasbourg/get-tags-and-categories-by-group-ids-and-class-name',
        {
            groupIds: groupIds,
            className: classname
        },
        function(json) {
            $("select[id^='" + namespace + "prefilterIds_" + index + "']").each(function (){
                indexGlobal = this.id.slice(namespace.length + 'prefilterIds_'.length);
                choice = initializeChoices($(this));
                if ($(namespaceAUI + "categoriesOrTags_" + indexGlobal).val() == "tags") {
                    choice.setChoices(json.tags, "value", "label", true);
                }else{
                    choice.setChoices(json.categories, "value", "label", true);
                }
                prefilterSelected = $(namespaceAUI + 'prefilterSelectedIds' + indexGlobal);
                if(prefilterSelected.length > 0 && $(prefilterSelected).val().length > 0)
                    choice.setChoiceByValue(JSON.parse($(prefilterSelected).val()));
                prefiltersChoicesJson[indexGlobal] = choice;
            });
        }
    );
}


// Ajout bloc type de contenu
function addAssetType() {
	var nbAssetType = $(namespaceAUI + 'nbAssetType').val();
	var lastAssetType = parseInt($('input[name*=numAssetType]')[nbAssetType - 1].id
				.split(namespace + 'numAssetType')[1]) + 1;

	$('#asset-types-content').append(blocAssetType.replace(/%%INDEX%%/gi, lastAssetType));
	initializeScope($(namespaceAUI + "scopeIds_" + lastAssetType));

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbAssetType').val(parseInt(nbAssetType) + 1);
}

// Ajout bloc préfiltrage
function addPrefilter(indexAssetType) {
	var nbPrefilterByAssetType = $(namespaceAUI + 'nbPrefiltre' + indexAssetType).val();
	var lastPrefilterByAssetType = 0;
	if(nbPrefilterByAssetType > 0)
	    lastPrefilterByAssetType = parseInt($('input[name*=numPrefiltre' + indexAssetType + ']')[nbPrefilterByAssetType - 1].id
				.split(namespace + 'numPrefiltre' + indexAssetType + '_')[1]) + 1;

	$('#prefilters-content' + indexAssetType).append(blocPrefilter.replace(/%%INDEXTYPE%%/gi, indexAssetType).replace(/%%INDEXPREFILTRE%%/gi, lastPrefilterByAssetType));
	initializeNewPrefilter($(namespaceAUI + "prefilterIds_" + indexAssetType + "_" + lastPrefilterByAssetType));

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbPrefiltre' + indexAssetType).val(parseInt(nbPrefilterByAssetType) + 1);
}

// Ajout bloc critère de recherche
function addVocabulary() {
	var nbVocabularies = $(namespaceAUI + 'nbVocabularies').val();
    var lastVocabulary = 0;
	if(nbVocabularies > 0)
	    lastVocabulary = parseInt($('input[name*=numVocabulary]')[nbVocabularies - 1].id
				.split(namespace + 'numVocabulary')[1]) + 1;

	$('#vocabularies-content').append(blocVocabulary.replace(/%%INDEX%%/gi, lastVocabulary));
	initializeVocabulary($(namespaceAUI + "vocabularyIds_" + lastVocabulary));

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbVocabularies').val(parseInt(nbVocabularies) + 1);
}


// Creation du choices du sélecteur de groupes
function initializeScope(elt){
    index = elt.attr("id").slice(namespace.length + 'scopeIds_'.length);
    choice = initializeChoices(elt);
    choice.setChoices(scopesJson, "value", "label", true);
    scopeSelected = $(namespaceAUI + 'scopeSelectedIds' + index);
    if(scopeSelected.length > 0 && $(scopeSelected).val().length > 0)
        choice.setChoiceByValue(JSON.parse($(scopeSelected).val()));
    scopesChoicesJson[index] = choice;
}

// Creation du choices du nouveau sélecteur de préfiltres
function initializeNewPrefilter(elt){
    // Get indexes
    indexGlobal = elt.attr("id").slice(namespace.length + + 'prefilterIds_'.length);
    indexType = indexGlobal.split('_')[0];
    groupIds = $(namespaceAUI + "scopeIds_" + indexType).val();

    Liferay.Service('/strasbourg.strasbourg/get-tags-by-group-ids',
        {
            groupIds: groupIds
        },
        function(json) {
            choice = initializeChoices(elt);
            choice.setChoices(json, "value", "label", true);
            prefiltersChoicesJson[indexGlobal] = choice;
        }
    );
}

// Creation du choices du sélecteur de vocabulaires
function initializeVocabulary(elt){
    Liferay.Service('/strasbourg.strasbourg/get-vocabularies-by-group-ids',
        {
            groupIds: allGroupIds
        },
        function(json) {
            choice = initializeChoices(elt);
            choice.setChoices(json, "value", "label", true);
            index = elt.attr("id").slice(namespace.length + 'vocabularyIds_'.length);
            vocabularySelected = $(namespaceAUI + 'vocabularySelectedId' + index);
            if(vocabularySelected.length > 0 && $(vocabularySelected).val().length > 0)
                choice.setChoiceByValue($(vocabularySelected).val());
            vocabulariesChoicesJson[index] = choice;
        }
    );
}

// Creation du choices du sélecteur de groupBy
function initializeGroupBy(elt){
    Liferay.Service('/strasbourg.strasbourg/get-vocabularies-by-group-ids',
        {
            groupIds: allGroupIds
        },
        function(json) {
            choice = initializeChoices(elt);
            choice.setChoices([{label: '', choices: [
                  { value: '0', label: Liferay.Language.get("eu.search.asset.web.configuration.none")},
                  { value: '-1', label: Liferay.Language.get("eu.search.asset.web.configuration.content.type")}
            ]}], "value", "label", true);
            choice.setChoices(json, "value", "label", false);
            groupBySelected = $(namespaceAUI + 'groupBySelectedId');
            if(groupBySelected.length > 0 && $(groupBySelected).val().length > 0)
                choice.setChoiceByValue($(groupBySelected).val());
            groupByChoices = choice;
        }
    );
}


// Mise a jour du bloc de type de contenu lors de la selection d'un type de contenu
function updateBloc(index) {
    // Changement du label du contenu
    $($("#assetType" + index + ' a')[0]).text(Liferay.Language.get($(namespaceAUI + "classname_" + index).val()));

    // effacement de la portée
    scopes = scopesChoicesJson[index];
    scopes.removeActiveItems();
    // on la cache si c'est une procédure
    if($(namespaceAUI + "classname_" + index).val() == "searchDemarche")
        $("#scope" + index).hide();
    else
        $("#scope" + index).show();

    // vide les structures
    optionsStructure = "<option>" + Liferay.Language.get('select-a-structure') + "</option>";
    $(namespaceAUI + "structure_" + index).html(optionsStructure);
    // affiche la structure pour les contenus web
    if($(namespaceAUI + "classname_" + index).val() == "searchJournalArticle")
        $("#structure" + index).show();
    else
        $("#structure" + index).hide();

    // MaJ des templates
    optionsTemplate = "<option>" + Liferay.Language.get('select-a-template') + "</option>";
    if($(namespaceAUI + "classname_" + index).val() != "searchDemarche"){
        templates = assetTemplates[$(namespaceAUI + "classname_" + index).val()];
        $.each(templates, function(i, template) {
            optionsTemplate += "<option value='" + template.id + "'>" + template.value + "</option>";
        });
    }
    $(namespaceAUI + "templateKey_" + index).html(optionsTemplate);
    // vide la friendlyURL
    $(namespaceAUI + "friendlyUrl_" + index).val("");
    // on cache le template et la friendlyURL si c'est une procédure
    if($(namespaceAUI + "classname_" + index).val() == "searchDemarche")
        $("#template-and-url" + index).hide();
    else{
        $("#template-and-url" + index).show();
        // on cache la friendlyURL si c'est un contenu web, fichier
        if($(namespaceAUI + "classname_" + index).val() == "searchJournalArticle" || $(namespaceAUI + "classname_" + index).val() == "searchDocument")
            $(namespaceAUI + "friendlyUrl_" + index).hide();
        else
            $(namespaceAUI + "friendlyUrl_" + index).show();
    }

    // supprime et/ou réinitialise les préfiltres
    $("select[id^='" + namespace + "prefilterIds_" + index + "']").each(function (){
        indexPrefilter = $(this).attr('id').split("_"+index+"_")[1];
        if(indexPrefilter > 0)
            deletePrefilter(index, indexPrefilter);
        else{
            $(namespaceAUI + "includeOrExclude_" + index + "_" + indexPrefilter).val("true");
            $(namespaceAUI + "allOrAny_" + index + "_" + indexPrefilter).val("all");
            $(namespaceAUI + "categoriesOrTags_" + index + "_" + indexPrefilter).val("tags");
            prefilters = prefiltersChoicesJson[index + "_" + indexPrefilter];
            prefilters.removeActiveItems();
            prefilters.clearChoices();
        }
    });
    // on les cache si c'est une procédure
    if($(namespaceAUI + "classname_" + index).val() == "searchDemarche")
        $("#prefilter" + index).hide();
    else{
        $("#prefilter" + index).show();
    }

    // MaJ des critères de recherche et du regroupement
    updateVocabularyAndGroupBy();
}

// Mise a jour des préfiltres, des structures, des critères de recherche et du regroupement s'il y a lieu lors de la selection d'un groupe
function updatePrefiltersStructureVocabulariesAndGroupBy(index) {
    groupIds = $(namespaceAUI + "scopeIds_" + index).val();
    classname = $(namespaceAUI + "classname_" + index).val();

    // MaJ des structures
    if($(namespaceAUI + "classname_" + index).val() == "searchJournalArticle"){
        // on garde en memoire la structure
        structureId = $(namespaceAUI + "structure_" + index).val();
        Liferay.Service('/strasbourg.strasbourg/get-structures-by-group-ids',
            {
                groupIds: groupIds
            },
            function(jsonStructures) {
                optionsStructure = "<option value=''>" + Liferay.Language.get('select-a-structure') + "</option>";
                $.each(jsonStructures, function(i, jsonStructure) {
                    optionsStructure += "<option value='" + jsonStructure.id + "' " + (jsonStructure.id==structureId?"selected":"") + ">" + jsonStructure.value + "</option>";
                });
                $(namespaceAUI + "structure_" + index).html(optionsStructure);
            }
        );
    }

    // MaJ des préfiltres
    Liferay.Service('/strasbourg.strasbourg/get-tags-and-categories-by-group-ids-and-class-name',
        {
            groupIds: groupIds,
            className: classname
        },
        function(json) {
            $("select[id^='" + namespace + "categoriesOrTags_" + index + "']").each(function (){
                indexGlobal = this.id.slice(namespace.length + 'categoriesOrTags_'.length);
                choice = prefiltersChoicesJson[indexGlobal];
                itemsId = choice.getValue(true);
                choice.removeActiveItems();
                if ($(this).val() == "tags") {
                    choice.setChoices(json.tags, "value", "label", true);
                }else{
                    choice.setChoices(json.categories, "value", "label", true);
                }
                choice.setChoiceByValue(itemsId);

                // si plus rien n'est sélectionné et que ca n'est pas le 1er prefiltre, on le supprime
                indexPrefilter = indexGlobal.split("_")[1];
                select = $(namespaceAUI + "prefilterIds_" + indexGlobal);
                if(($(select).val() == "" || $(select).val() == null) && indexPrefilter > 0)
                    deletePrefilter(index, indexPrefilter);
            });

        }
    );

    // MaJ des critères de recherche et du regroupement
    updateVocabularyAndGroupBy();
}

// Mise a jour des préfiltres lors de la sélection du type de préfiltre (étiquette ou catégorie)
function updatePrefilter(elt){
    // Get indexes
    indexGlobal = elt.id.slice(namespace.length + 'categoriesOrTags_'.length);
    indexType = indexGlobal.split('_')[0];
    groupIds = $(namespaceAUI + "scopeIds_" + indexType).val();
    choice = prefiltersChoicesJson[indexGlobal];
    choice.removeActiveItems();
    if ($(elt).val() == "tags") {
        Liferay.Service('/strasbourg.strasbourg/get-tags-by-group-ids',
            {
                groupIds: groupIds
            },
            function(json) {
                choice.setChoices(json, "value", "label", true);
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
            }
        );
    }
}


// Suppression d'un bloc type de contenu
function deleteAssetType(index) {
	var nbAssetType = $(namespaceAUI + 'nbAssetType').val();

	$('#assetType' + index).closest('.card-horizontal').remove();

    // MaJ des critères de recherche et du regroupement
    updateVocabularyAndGroupBy();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbAssetType').val(parseInt(nbAssetType) - 1);
}

// Suppression d'un bloc critère de recherche
function deleteVocabulary(index) {
	var nbVocabularies = $(namespaceAUI + 'nbVocabularies').val();
	$('#vocabulary' + index).remove();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbVocabularies').val(parseInt(nbVocabularies) - 1);
}

// Suppression d'un bloc préfiltrage
function deletePrefilter(indexAssetType, indexPrefilter) {
	var nbPrefilterByAssetType = $(namespaceAUI + 'nbPrefiltre' + indexAssetType).val();

	$('#prefilter' + indexAssetType + "_" + indexPrefilter).remove();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbPrefiltre' + indexAssetType).val(parseInt(nbPrefilterByAssetType) - 1);
}


$(":submit").on('click', function() {
    setAssetTypeIndexes();
    setVocabularyValidators();
});

// Récupère les indexes des types et des préfiltres
function setAssetTypeIndexes() {
    var assetTypes = $('fieldset[id*=assetType]');
    var assetTypeIndexes = "";
    var prefilterIndexes = "";
    assetTypes.each(function() {
        var index = $(this).find('input[id*=' + namespace + 'numAssetType]').val();
        assetTypeIndexes += index + ",";

        setPrefilterIndexes(index);
    });
    $(namespaceAUI + 'assetTypeIndexes').val(assetTypeIndexes.substr(0, assetTypeIndexes.length -1));
}

// Récupère les indexes des préfiltres d'un type
function setPrefilterIndexes(indexAssetType) {
    var prefilters = $('div[id*=prefilter'+ indexAssetType +'_]');
    var prefilterIndexes = "";
    prefilters.each(function() {
        var index = indexAssetType + "_" + $(this).find('input[id*=' + namespace + 'numPrefiltre]').val();
        prefilterIndexes += index + ",";
    });
    $(namespaceAUI + 'prefilterIndexes' + indexAssetType).val(prefilterIndexes.substr(0, prefilterIndexes.length -1));
}

// Récupère les indexes des critères de recherche
function setVocabularyValidators() {
    var vocabularys = $('#vocabularies-content div[id^=vocabulary]');
    var vocabularyIndexes = "";
    vocabularys.each(function() {
        var index = $(this).find('input[id*=' + namespace + 'numVocabulary]').val();
        vocabularyIndexes += index + ",";
    });
    $(namespaceAUI + 'vocabularyIndexes').val(vocabularyIndexes.substr(0, vocabularyIndexes.length -1));
}


// Creation du choices vide
function initializeChoices(elt){
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
    return choice;
}

// Récupère tous les groupes de tous les types
function getGroups(){
    allGroupIds = [];
    $("select[id^='" + namespace + "scopeIds_']").each(function() {
        $($(this).val()).each(function(i, groupId) {
            if(jQuery.inArray(groupId, allGroupIds) == -1){
                allGroupIds.push(groupId);
            }
        });
    });
}

// Mise a jour des critères de recherche et du regroupement lors de la suppression d'un groupe ou de la modification ou suppression d'un type
function updateVocabularyAndGroupBy() {
    // Récupération des groupes
    getGroups();

    Liferay.Service('/strasbourg.strasbourg/get-vocabularies-by-group-ids',
        {
            groupIds: allGroupIds
        },
        function(jsonVocabularies) {
            // MaJ des vocabulaires
            $("select[id^='" + namespace + "vocabularyIds_']").each(function (){
                indexVocabulary = this.id.slice(namespace.length + 'vocabularyIds_'.length);
                choice = vocabulariesChoicesJson[indexVocabulary];
                itemId = choice.getValue(true);
                choice.removeActiveItems();
                choice.setChoices(jsonVocabularies, "value", "label", true);
                if(itemId != undefined && itemId != "")
                    choice.setChoiceByValue(itemId);

                // si le vocabulaire n'existe plus, on supprime le critère
                if($(this).val() == "" || $(this).val() == null){
                    deleteVocabulary(indexVocabulary);
                }
            });

            // MaJ du regroupement
            itemId = groupByChoices.getValue(true);
            groupByChoices.removeActiveItems();
            groupByChoices.setChoices([{label: '', choices: [
                  { value: '0', label: Liferay.Language.get("eu.search.asset.web.configuration.none")},
                  { value: '-1', label: Liferay.Language.get("eu.search.asset.web.configuration.content.type")}
            ]}], "value", "label", true);
            groupByChoices.setChoices(jsonVocabularies, "value", "label", false);
            if(itemId != undefined && itemId != "")
                groupByChoices.setChoiceByValue(itemId);

            // si le vocabulaire n'existe plus, on sélectionne 'Ne pas grouper'
            if($(namespaceAUI + "groupBy").val() == "" || $(namespaceAUI + "groupBy").val() == null){
                groupByChoices.setChoiceByValue("0");
            }
        }
    );
}