<%@include file="/picker-init.jsp" %>

<div class="strasbourg-entity-picker">
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
	<ul id="entities-thumbnails-${name}">
		<c:forEach items="${entities}" var="entity">
			<li>
				${not empty entity.titleMap ? entity.getTitle(locale) : entity.title} - <a href="#" class="remove-entity" data-entry-id="${entity.classPK}">Supprimer</a>
			</li>
		</c:forEach>
	</ul>
	<p>
		<aui:button name="choose-entity-${name}" id="choose-entity-${name}" value="${multiple ? 'add' : 'choose'}" />
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
$('#<portlet:namespace />choose-entity-${name}').on('click',
	function(event) {
		var multipleSelection = ${multiple};
		var itemSelectorDialog = new A.LiferayItemSelectorDialog(
			{
				eventName: 'itemSelected${name}',
				on: {
					selectedItemChange: function(event) {
						var item = event.newVal;
						var items = [];
						if (multipleSelection && !!item) {
							items = item;
						} else if (!!item) {
							items.push(item);
						}
						for (var selectedItem of items) {
							var htmlToAppend = '<li>'
								+ selectedItem.title + ' - <a href="#" class="remove-entity" data-entry-id="' + selectedItem.entityId + '">Supprimer</a>';
								+ '</li>';
							if (!multipleSelection) {
								$('#entities-thumbnails-${name}').empty();
								$('#entities-thumbnails-${name}').append(htmlToAppend);
								$('#<portlet:namespace />${name}').val(selectedItem.entityId);
							} else {
								var currentValue = $('#<portlet:namespace />${name}').val();
								var newValue = currentValue;
								if (currentValue.indexOf(selectedItem.entityId) === -1) {
									if (currentValue.length > 0) {
										newValue += ',';
									}
									newValue += selectedItem.entityId;
									$('#<portlet:namespace />${name}').val(newValue);
									$('#entities-thumbnails-${name}').append(htmlToAppend);
								}
							}							
						}
					}
				},
				title: '<liferay-ui:message key="${type}"/>',
				url: '${itemSelectorURL}'
			}
		);
		itemSelectorDialog.open();
	}
);
$('#entities-thumbnails-${name}').on('click', '.remove-entity', function(e) {
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