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
			<span class="taglib-icon-help lfr-portal-tooltip" data-title="<liferay-ui:message key="eu.image-picker-help" />"> <span class=""> <svg class="lexicon-icon lexicon-icon-question-circle-full" role="img" title="" viewBox="0 0 512 512">  
			<path class="lexicon-icon-outline" d="M256 0c-141.37 0-256 114.6-256 256 0 141.37 114.629 256 256 256s256-114.63 256-256c0-141.4-114.63-256-256-256zM269.605 360.769c-4.974 4.827-10.913 7.226-17.876 7.226s-12.873-2.428-17.73-7.226c-4.857-4.827-7.285-10.708-7.285-17.613 0-6.933 2.428-12.844 7.285-17.788 4.857-4.915 10.767-7.402 17.73-7.402s12.932 2.457 17.876 7.402c4.945 4.945 7.431 10.854 7.431 17.788 0 6.905-2.457 12.786-7.431 17.613zM321.038 232.506c-5.705 8.923-13.283 16.735-22.791 23.464l-12.99 9.128c-5.5 3.979-9.714 8.455-12.668 13.37-2.955 4.945-4.447 10.649-4.447 17.145v1.901h-34.202c-0.439-2.106-0.731-4.184-0.936-6.291s-0.321-4.301-0.321-6.612c0-8.397 1.901-16.413 5.705-24.079s10.24-14.834 19.309-21.563l15.185-11.322c9.070-6.7 13.605-15.009 13.605-24.869 0-3.57-0.644-7.080-1.901-10.533s-3.219-6.495-5.851-9.128c-2.633-2.633-5.969-4.71-9.977-6.291s-8.66-2.369-13.927-2.369c-5.705 0-10.561 1.054-14.571 3.16s-7.343 4.769-9.977 8.017c-2.633 3.247-4.594 7.022-5.851 11.322s-1.901 8.66-1.901 13.049c0 4.213 0.41 7.548 1.258 10.065l-39.877-1.58c-0.644-2.311-1.054-4.652-1.258-7.080-0.205-2.399-0.321-4.769-0.321-7.080 0-8.397 1.58-16.619 4.74-24.693s7.812-15.214 13.927-21.416c6.114-6.173 13.663-11.176 22.645-14.951s19.368-5.676 31.188-5.676c12.229 0 22.996 1.785 32.3 5.355 9.274 3.57 17.087 8.25 23.435 14.014 6.319 5.764 11.089 12.434 14.248 19.982s4.74 15.331 4.74 23.289c0.058 12.581-2.809 23.347-8.514 32.27z"></path>
			</svg> <span class="taglib-icon-label"> </span> </span> <span class="taglib-text hide-accessible"><liferay-ui:message key="eu.image-picker-help" /></span> </span>
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