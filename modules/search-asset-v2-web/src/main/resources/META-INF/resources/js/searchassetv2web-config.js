const namespace = "_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_";
const namespaceAUI = "#" + namespace;

$( document ).ready(function() {
    $("select[id^='" + namespace + "scopeIds']").each(function() {
        initialiseScope($(this));
    });

    // Correction problemes champs repetables
    /*setTimeout(function (){
        $("button.add-row").on("click", updateHandlers);
    }, 1500);*/
});

// Correction des event handlers et correction du probleme de duplication des fieldsets
/*function updateHandlers() {
    setTimeout(function () {
        // Detacher tous les event handlers puis les reattacher (pour inclure le nouveaux)
        $("button.add-row").off("click", updateHandlers);
        $("button.add-row").on("click", updateHandlers);
        // Correction des href fieldsets dupliques
        $("div[id^='asset'][id$='Title']").each(function (){
            const beginOfId = this.id.slice(0, this.id.indexOf('_')+1);
            const endOfId = this.id.slice(this.id.indexOf('_')+1);
            const fieldIndex = parseInt(endOfId);
            $(this).children().attr("href","#"+beginOfId+fieldIndex+"Content");
        });
        // Correction du Choices duplique
    }, 100);
}*/

// WIP Mettre a jour les templates lors de la selection
function updateTemplates(index) {
    // Changement du label du contenu
    $($("#assetType" + index + ' .legend')[0]).text(Liferay.Language.get($(namespaceAUI + "classname_" + index).val()));
    // MaJ des templates
    templates = assetTemplates[$(namespaceAUI + "classname_" + index).val()];
    options = "<option>Veuillez sélectionner un template</option>";
    $.each(templates, function(i, template) {
        options += "<option value=' " + template.id + " '>" + template.value + "</option>";
    });
    $(namespaceAUI + "templateKey_" + index).html(options);
}

function addAssetType() {
	var nbAssetType = $(namespaceAUI + 'nbAssetType').val();
	var lastAssetType = 0;
	if (nbAssetType > 0) {
		lastAssetType = parseInt($('input[name*=numAssetType]')[nbAssetType - 1].id
				.split(namespace + 'numAssetType')[1]) + 1;
	}

	$.ajax({
		url : getAssetTypeRowJSPURL + '&' + namespace + 'index=' + lastAssetType,
		success : function(html) {
			$('#asset-types-content').append(html);
		}
	});

	// on ajuste le nb d'assetType
	$(namespaceAUI + 'nbAssetType').val(parseInt(nbAssetType) + 1);
}

// TODO Insertion Html cf maquette pour regle de prefiltrage
function addPrefilter(index) {

}

function initialiseScope(elt){
    // Selecteur choices portees
    choix = new Choices("#"+elt.attr("id"), {
        removeItemButton: true,
        loadingText: 'Chargement...',
        noResultsText: 'Auncun résultats',
        noChoicesText: 'Pas de choix',
        itemSelectText: 'Cliquer pour sélectionner',
        choices: scopesJson
    });
}
