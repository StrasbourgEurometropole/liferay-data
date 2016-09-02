<%@include file="/picker-init.jsp" %>

<div class="strasbourg-file-picker">
	<p>
		<label>
			<liferay-ui:message key="${label}" />
			<c:if test="${required}">
				<span class="icon-asterisk text-warning"> 
					<span class="hide-accessible"><liferay-ui:message key="required" /></span>
				</span>
			</c:if>
		</label>
	</p>
	<p>
		<aui:button name="choose-file-${name}" id="choose-file-${name}" value="${multiple ? 'add' : 'choose'}" />
	</p>
	<div class="has-error">
		<aui:input name="${name}" type="hidden" value="${value}">
			<c:if test="${required}">
				<aui:validator name="required"
					errorMessage="this-field-is-required" />
			</c:if>
		</aui:input>
	</div>
	<ul id="files-thumbnails-${name}">
		<c:forEach items="${files}" var="file">
			<li>
				<a href="${file.url}" target="_blank">${file.title}</a> - <a href="#" class="remove-file" data-entry-id="${file.id}">Supprimer</a>
			</li>
		</c:forEach>
	</ul>
</div>
<aui:script use="liferay-item-selector-dialog">
$('#<portlet:namespace />choose-file-${name}').on('click',
	function(event) {
		var multipleSelection = ${multiple};
		var itemSelectorDialog = new A.LiferayItemSelectorDialog(
			{
				eventName: 'itemSelected${name}',
				on: {
					selectedItemChange: function(event) {
						var selectedItem = event.newVal;
						if (!!selectedItem && !!selectedItem.value) {
							var itemValue = JSON.parse(selectedItem.value);
							var htmlToAppend = '<li>'
								+ '<a href="' + itemValue.url + '" target="_blank">' + itemValue.title + '</a> - <a href="#" class="remove-file" data-entry-id="' + itemValue.fileEntryId + '">Supprimer</a>';
								+ '</li>';
							if (!multipleSelection) {
								$('#files-thumbnails-${name}').empty();
							}
							$('#files-thumbnails-${name}').append(htmlToAppend);
							if (!multipleSelection) {
								$('#<portlet:namespace />${name}').val(itemValue.fileEntryId);
							} else {
								var currentValue = $('#<portlet:namespace />${name}').val();
								var newValue = currentValue;
								if (currentValue.length > 0) {
									newValue += ',';
								}
								newValue += itemValue.fileEntryId;
								$('#<portlet:namespace />${name}').val(newValue);
							}							
						}
					}
				},
				title: '<liferay-ui:message key="file"/>',
				url: '${itemSelectorURL}'
			}
		);
		itemSelectorDialog.open();
	}
);
$('#files-thumbnails-${name}').on('click', '.remove-file', function(e) {
	e.preventDefault();
	var valueToRemove = $(this).data('entry-id');
	$(this).parent().remove();
	var currentValue = $('#<portlet:namespace />${name}').val();
	var newValue = currentValue.replace(valueToRemove, '');
	newValue = newValue.replace(',,', ',');
	if (newValue[0] === ',') {
		newValue = newValue.slice(1);
	}
	if (newValue[newValue.length - 1] == ',') {
		newValue = newValue.slice(0, -1);
	}
	$('#<portlet:namespace />${name}').val(newValue);
});
</aui:script>