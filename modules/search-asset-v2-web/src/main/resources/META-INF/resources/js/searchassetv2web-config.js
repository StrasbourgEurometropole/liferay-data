const namespace = "_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_";
const namespaceAUI = "#" + namespace;

$( document ).ready(function() {
    // Selecteur choices portees
    // TODO Recuperation portees dispos
    $("select[id^='scopeIds']").each(function() {
        new Choices("#"+$(this).attr("id"), {
            removeItemButton: true
        });
    });
    // Correction problemes champs repetables
    setTimeout(function (){
        $("button.add-row").on("click", updateHandlers);
    }, 1500);
});

// jQuery(function() {});

// Correction des event handlers et correction du probleme de duplication des fieldsets
function updateHandlers() {
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
}

// WIP Mettre a jour les templates lors de la selection
function updateTemplates(e, templates) {
    console.log(e.id);
    if (this.value != false) {
        // Recuperation index
        const className = "classname_";
        const index = parseInt(this.id.slice(namespace.length + className.length));
        // Mettre a jour les templates
    }
}

// TODO Insertion Html cf maquette pour regle de prefiltrage
function addPrefilter(index) {

}