

jQuery(function() {
    var namespace = "_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_";
    var namespaceAUI = "#" + namespace;

    $(".add-row").click(function(){
        updateFieldSets();
    });

    // TODO Mettre a jour les templates en fonction du type d'asset
    $('td[id^="' + namespaceAUI +'classname_"]').click(function() {
        // get id -> get templateKey_[id]
    });

});

// TODO Corriger la duplication autofield avec reference incorrecte dans les <aui:fieldset>
function updateFieldSets() {

}