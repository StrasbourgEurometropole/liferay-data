function deleteZone(id) {
	var namespace = "_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_";
	var namespaceAUI = "#" + namespace;
	$('#' + id).remove();
	var nbZone = $(namespaceAUI + 'nbZone').val();
	$(namespaceAUI + 'nbZone').val(parseInt(nbZone) - 1);
}

function addZone() {
	var namespace = "_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_";
	var namespaceAUI = "#" + namespace;
	var nbZone = $(namespaceAUI + 'nbZone').val();
	var zone = 
			'	<div class="webform-layout-box zone-content zone-elt" style="display:flex;" id="' + nbZone + '"> ' +
			'		<div class="form-group input-text-wrapper" >' +
			'			<label class="control-label" for="' + namespace + 'code"> Code de la zone </label>' +
			'			<input class="field form-control" id="' + namespace + 'code" name="' + namespace + 'code" type="text" >' +
			'		</div>' +
			'		<div class="form-group input-text-wrapper">' +
			'			<label class="control-label" for="' + namespace + 'url"> URL du PDF de la zone </label>' +
			'			<input class="field form-control urlPDF " id="' + namespace + 'url" name="' + namespace + 'url" type="text" style="margin: 0px 10px;" >' +
			'		</div>' +
			'		<div class="form-group">' +
			'			<button style="margin-top: 24px;" class="btn btn-icon icon icon-trash icon-2x btn-default" type="button" onClick="deleteZone(' + nbZone + '); return false;" />' +
			'		</div>' +
			'	</div>';
	 
	$(zone).insertAfter('#newZone');
		
	$(namespaceAUI + 'nbZone').val(parseInt(nbZone) + 1);
}