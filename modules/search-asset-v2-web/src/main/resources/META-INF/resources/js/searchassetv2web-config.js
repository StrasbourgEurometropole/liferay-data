const namespaceAUI = "#" + namespace;
var scopesChoicesJson = JSON.parse('{}');
var prefiltersChoicesJson = JSON.parse('{}');
var allGroupIds = [];
var vocabulariesChoicesJson = JSON.parse('{}');

$( document ).ready(function() {
    // initialise les selecteurs de groups
    $("select[id^='" + namespace + "scopeIds']").each(function() {
        initializeScope($(this));
    });

    // initialise les selecteurs de préfiltres
    $("select[id^='" + namespace + "prefilterChoices_']").each(function (){
        initializePrefilter($(this));
    });

    // MaJ des groupes
    getGroups();

    // initialise les selecteurs de vocabulaires
    if(vocabulariesControlTypes.length > 0){
        $.each(vocabulariesControlTypes, function(i, vocabularyControlType) {
            addCritereRecherche();
        });
    }
});

// Ajout bloc type de contenu
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

// Ajout bloc préfiltrage
function addPrefilter(indexAssetType) {
	var nbPrefilterByAssetType = $(namespaceAUI + 'nbPrefiltre' + indexAssetType).val();
	var lastPrefilterByAssetType = parseInt($('input[name*=numPrefiltre' + indexAssetType + ']')[nbPrefilterByAssetType - 1].id
				.split(namespace + 'numPrefiltre' + indexAssetType + '_')[1]) + 1;

	$('#prefilters-content' + indexAssetType).append(blocPrefilter.replace(/%%INDEXTYPE%%/gi, indexAssetType).replace(/%%INDEXPREFILTRE%%/gi, lastPrefilterByAssetType));
	initializePrefilter($(namespaceAUI + "prefilterChoices_" + indexAssetType + "_" + lastPrefilterByAssetType));

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbPrefiltre' + indexAssetType).val(parseInt(nbPrefilterByAssetType) + 1);
}

// Ajout bloc critère de recherche
function addCritereRecherche() {
	var nbCriteresRecherche = $(namespaceAUI + 'nbCriteresRecherche').val();
    var lastCriteresRecherche = 0;
	if(nbCriteresRecherche > 0)
	    lastCriteresRecherche = parseInt($('input[name*=numCritereRecherche]')[nbCriteresRecherche - 1].id
				.split(namespace + 'numCritereRecherche')[1]) + 1;

	$('#critereRecherche-content').append(blocCritereRecherche.replace(/%%INDEX%%/gi, lastCriteresRecherche));
	initializeVocabulary($(namespaceAUI + "vocabularyChoices_" + lastCriteresRecherche));

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbCriteresRecherche').val(parseInt(nbCriteresRecherche) + 1);
}

// Suppression d'un bloc type de contenu
function deleteAssetType(index) {
	var nbAssetType = $(namespaceAUI + 'nbAssetType').val();

	$('#assetType' + index).closest('.card-horizontal').remove();

    // MaJ des critères de recherche
    reinitializeVocabulary();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbAssetType').val(parseInt(nbAssetType) - 1);
}

// Suppression d'un bloc préfiltrage
function deletePrefilter(indexAssetType, indexPrefilter) {
	var nbPrefilterByAssetType = $(namespaceAUI + 'nbPrefiltre' + indexAssetType).val();

	$('#prefilter' + indexAssetType + "_" + indexPrefilter).remove();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbPrefiltre' + indexAssetType).val(parseInt(nbPrefilterByAssetType) - 1);
}

// Suppression d'un bloc critère de recherche
function deleteCritereRecherche(index) {
	var nbCriteresRecherche = $(namespaceAUI + 'nbCriteresRecherche').val();
	$('#critereRecherche' + index).remove();

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbCriteresRecherche').val(parseInt(nbCriteresRecherche) - 1);
}

// Creation du choices du sélecteur de groupes
function initializeScope(elt){
    index = elt.attr("id").slice(namespace.length + + 'scopeIds_'.length);
    choice = initializeChoices(elt);
    choice.setChoices(scopesJson, "value", "label", true);
    scopesChoicesJson[index] = choice;
}

// Creation du choices du sélecteur de préfiltres
function initializePrefilter(elt){
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
                choice = initializeChoices(elt);
                choice.setChoices(json, "value", "label", true);
                prefiltersChoicesJson[indexGlobal] = choice;
            }
        );
    }else{
        prefiltersChoicesJson[indexGlobal] = initializeChoices(elt);
    }

}

// Creation du choices du sélecteur de vocabulaires
function initializeVocabulary(elt){
    // Get indexes
    index = elt.attr("id").slice(namespace.length + + 'vocabularyChoices_'.length);

    Liferay.Service('/strasbourg.strasbourg/get-vocabularies-by-group-ids',
        {
            groupIds: allGroupIds
        },
        function(json) {
            choice = initializeChoices(elt);
            choice.setChoices(json, "value", "label", true);
            vocabulariesChoicesJson[index] = choice;
        }
    );
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

// Mise a jour des préfiltres lors de la sélection du type de préfiltre (étiquette ou catégorie)
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

// Réinitialise le bloc de type de contenu lors de la selection d'un type de contenu
function reinitializeBloc(index) {
    // Changement du label du contenu
    $($("#assetType" + index + ' a')[0]).text(Liferay.Language.get($(namespaceAUI + "classname_" + index).val()));

    // effacement de la portée
    // on la cache si c'est une procédure
    if($(namespaceAUI + "classname_" + index).val() == "searchDemarche")
        $("#scope" + index).hide();
    else{
        $("#scope" + index).show();
        scopes = scopesChoicesJson[index];
        scopes.removeActiveItems();
    }

    // vide les structures
    // affiche la structure pour les contenus web
    if($(namespaceAUI + "classname_" + index).val() == "searchJournalArticle"){
        $("#structure" + index).show();
        optionsStructure = "<option>" + Liferay.Language.get('select-a-structure') + "</option>";
        $(namespaceAUI + "structure_" + index).html(optionsStructure);
    }else
        $("#structure" + index).hide();


    // MaJ des templates
    // on le cache si c'est une procédure
    if($(namespaceAUI + "classname_" + index).val() == "searchDemarche")
        $("#template-and-url" + index).hide();
    else{
        $("#template-and-url" + index).show();
        templates = assetTemplates[$(namespaceAUI + "classname_" + index).val()];
        optionsTemplate = "<option>" + Liferay.Language.get('select-a-template') + "</option>";
        $.each(templates, function(i, template) {
            optionsTemplate += "<option value='" + template.id + "'>" + template.value + "</option>";
        });
        $(namespaceAUI + "templateKey_" + index).html(optionsTemplate);

        // vide la friendlyURL et on la cache si c'est un contenu web, fichier
        $(namespaceAUI + "friendlyUrl_" + index).val("");
        if($(namespaceAUI + "classname_" + index).val() == "searchJournalArticle" || $(namespaceAUI + "classname_" + index).val() == "searchDocument")
            $(namespaceAUI + "friendlyUrl_" + index).hide();
        else
            $(namespaceAUI + "friendlyUrl_" + index).show();
    }

    // efface et/ou vide les préfiltres
    // on la cache si c'est une procédure
    if($(namespaceAUI + "classname_" + index).val() == "searchDemarche")
        $("#prefilter" + index).hide();
    else{
        $("#prefilter" + index).show();
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
        });
    }
}

// Réinitialise les préfiltres et les structures s'il y a lieu lors de la selection d'un groupe
function reinitializePrefiltersStructureAndVocabularies(index) {
    groupIds = $(namespaceAUI + "scopeIds_" + index).val();

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

                // si la structure n'est plus disponible, on vide le template
                if($(namespaceAUI + "structure_" + index).val() == ""){
                    optionsTemplate = "<option>" + Liferay.Language.get('select-a-template') + "</option>";
                    $(namespaceAUI + "templateKey_" + index).html(optionsTemplate);
                }
            }
        );
    }

    // MaJ des préfiltres
    Liferay.Service('/strasbourg.strasbourg/get-tags-by-group-ids',
        {
            groupIds: groupIds
        },
        function(jsonTags) {

            classname = $(namespaceAUI + "classname_" + index).val();
            Liferay.Service('/strasbourg.strasbourg/get-categories-by-class-name-and-group-ids',
                {
                    groupIds: groupIds,
                    className: classname
                },
                function(jsonCateg) {
                    $("select[id^='" + namespace + "categoriesOrTags_" + index + "']").each(function (){
                        indexGlobal = this.id.slice(namespace.length + 'categoriesOrTags_'.length);
                        choice = prefiltersChoicesJson[indexGlobal];
                        itemsId = choice.getValue(true);
                        choice.removeActiveItems();
                        if ($(this).val() == "tags") {
                            choice.setChoices(jsonTags, "value", "label", true);
                        }else{
                            choice.setChoices(jsonCateg, "value", "label", true);
                        }
                        choice.setChoiceByValue(itemsId);
                    });
                }
            );

        }
    );

    // MaJ des critères de recherche
    reinitializeVocabulary();
}

// Réinitialise les templates lors de la selection d'une structure
function reinitializeTemplate(index) {
    structureId = $(namespaceAUI + "structure_" + index).val();
    if(structureId != ""){
        Liferay.Service('/strasbourg.strasbourg/get-templates-by-class-pk',
            {
                classPK: structureId
            },
            function(jsonTemplates) {
                optionsTemplate = "<option>" + Liferay.Language.get('select-a-template') + "</option>";
                $.each(jsonTemplates, function(i, jsonTemplate) {
                    optionsTemplate += "<option value=' " + jsonTemplate.id + " '>" + jsonTemplate.value + "</option>";
                });
                $(namespaceAUI + "templateKey_" + index).html(optionsTemplate);
            }
        );
    }else{
        optionsTemplate = "<option>" + Liferay.Language.get('select-a-template') + "</option>";
        $(namespaceAUI + "templateKey_" + index).html(optionsTemplate);
    }
}

// Réinitialise les critères de recherche lors de la suppression d'un type ou d'un groupe
function reinitializeVocabulary() {
    // MaJ des groupes
    getGroups();

    // MaJ des vocabulaires
    Liferay.Service('/strasbourg.strasbourg/get-vocabularies-by-group-ids',
        {
            groupIds: allGroupIds
        },
        function(jsonVocabularies) {
            $("select[id^='" + namespace + "vocabularyChoices_']").each(function (){
                indexVocabulary = this.id.slice(namespace.length + 'vocabularyChoices_'.length);
                choice = vocabulariesChoicesJson[indexVocabulary];
                itemId = choice.getValue(true);
                choice.removeActiveItems();
                choice.setChoices(jsonVocabularies, "value", "label", true);
                if(itemId != undefined && itemId != "")
                    choice.setChoiceByValue(itemId);

                // si le vocabulaire n'existe plus, on supprime le critère
                if($(this).val() == "" || $(this).val() == null){
                    deleteCritereRecherche(indexVocabulary);
                }
            });
        }
    );
}

// Récupère tous les groupes de tous les types
function getGroups(){
    allGroupIds = [];
    $("select[id^='" + namespace + "scopeIds']").each(function() {
        $($(this).val()).each(function(i, groupId) {
            if(jQuery.inArray(groupId, allGroupIds) == -1){
                allGroupIds.push(groupId);
            }
        });
    });
}