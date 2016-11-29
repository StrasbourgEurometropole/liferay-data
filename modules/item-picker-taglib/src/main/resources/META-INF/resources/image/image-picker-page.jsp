<%@include file="/picker-init.jsp" %>

<div class="strasbourg-image-picker">
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
	<div id="images-thumbnails-${name}">
		<c:forEach items="${files}" var="file">
			<div class="image-thumbnail figure figure-rounded">
				<a href="#" class="remove-image" data-entry-id="${file.id}"></a>
				<img src="${file.url}" class="img-responsive" />
				<div class="figcaption-bottom">${file.title}</div>
			</div>
		</c:forEach>
	</div>
	<p>
		<aui:button name="choose-image-${name}" id="choose-image-${name}" value="${multiple ? 'add' : 'choose'}" />
	</p>
	<div class="has-error">
		<aui:input name="${name}" type="hidden" value="${value}">
			<c:if test="${required}">
				<aui:validator name="required"
					errorMessage="this-field-is-required" />
			</c:if>
		</aui:input>
	</div>
</div>
<aui:script use="liferay-item-selector-dialog">
$('#<portlet:namespace />choose-image-${name}').on('click',
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
							var htmlToAppend = '<div class="image-thumbnail figure figure-rounded">'
									+ '<a href="#" class="remove-image" data-entry-id="' + itemValue.fileEntryId + '"></a>'
									+ '<img src="' + itemValue.url + '" class="img-responsive" />'
									+ '<div class="figcaption-bottom">' + itemValue.title + '</div>'
									+ '</div>';
							if (!multipleSelection) {
								$('#images-thumbnails-${name}').empty();
							}
							$('#images-thumbnails-${name}').append(htmlToAppend);
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
				title: '<liferay-ui:message key="image"/>',
				url: '${itemSelectorURL}'
			}
		);
		itemSelectorDialog.open();
	}
);
$('#images-thumbnails-${name}').on('click', '.remove-image', function(e) {
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
<style>
	.strasbourg-image-picker .figure {
		overflow: hidden;
	}
	
	.strasbourg-image-picker .image-thumbnail {
		max-width: 220px;
		display: inline-block;
		margin-right: 20px;
	}
	.strasbourg-image-picker a.remove-image {
	    position: absolute;
	    width: 24px;
	    height: 24px;
	    background: #e84747;
	    right: 5px;
	    top: 5px;
	    border-radius: 5px;
	}
	.strasbourg-image-picker a.remove-image:before,
	.strasbourg-image-picker a.remove-image:after {
	    content: "";
	    position: absolute;
	    width: 18px;
	    height: 3px;
	    background: white;
	    top: 10px;
	    left: 3px;
	}
	.strasbourg-image-picker a.remove-image:before {
	    transform: rotate(45deg);
	}
	.strasbourg-image-picker a.remove-image:after {
	    transform: rotate(-45deg);
	}
</style>