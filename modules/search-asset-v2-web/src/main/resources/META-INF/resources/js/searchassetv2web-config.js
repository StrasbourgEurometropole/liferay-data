

function addAssetType() {
    var namespace = "_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_";
    var namespaceAUI = "#" + namespace;

    $.ajax({
        url : getassetTypeRowJSPURL,
        success : function(html) {
            $('.asset-types-content').append(html);
        }
    });

}