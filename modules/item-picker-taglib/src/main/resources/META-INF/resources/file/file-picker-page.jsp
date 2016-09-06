<%@include file="/picker-init.jsp" %>

<%-- Boucle pour chaque langue - si on n'est pas en multilingue, une seule langue existe (le Français) --%>
<c:forEach var="currentLocale" items="${availableLocales}" varStatus="status">
	<div class="strasbourg-file-picker picker-${name} ${currentLocale} <c:if test="${currentLocale eq 'fr_FR'}">active</c:if>">
		<p>
			<label>
				<liferay-ui:message key="${label}" />
				<c:if test="${localized}">
					(${currentLocale.displayLanguage})
				</c:if>
				<c:if test="${required}">
					<span class="icon-asterisk text-warning"> 
						<span class="hide-accessible"><liferay-ui:message key="required" /></span>
					</span>
				</c:if>
			</label>
		</p>
		<p>
			<aui:button name="choose-file-${name}-${currentLocale}" id="choose-file-${name}-${currentLocale}" value="${multiple ? 'add' : 'choose'}" />
		</p>
		<%-- Champ hidden contenant les données qui seront utilisées ensuite par le contrôleur --%>
		<div class="has-error">
			<c:if test="${localized}">
				<aui:input name="${name}_${currentLocale}" type="hidden" value="${locale_filesIds[currentLocale]}">
					<c:if test="${required}">
						<aui:validator name="required"
							errorMessage="this-field-is-required" />
					</c:if>
				</aui:input>
			</c:if>
			<c:if test="${not localized}">
				<aui:input name="${name}" type="hidden" value="${locale_filesIds[currentLocale]}">
					<c:if test="${required}">
						<aui:validator name="required"
							errorMessage="this-field-is-required" />
					</c:if>
				</aui:input>
			</c:if>
		</div>
		<%--Boucle qui liste les fichiers pour la langue courante --%>
		<ul id="files-thumbnails-${name}-${currentLocale}">
			<c:forEach items="${locale_files[currentLocale]}" var="file">
				<li>
					<a href="${file.url}" target="_blank">${file.title}</a> - <a href="#" class="remove-file" data-entry-id="${file.id}">Supprimer</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<aui:script use="liferay-item-selector-dialog">
	// JS gérant l'ouverture du popup de selection du fichier
	$('#<portlet:namespace />choose-file-${name}-${currentLocale}').on('click',
		function(event) {
			var multipleSelection = ${multiple};
			var localized = ${localized};
			var fieldName = localized ? '#<portlet:namespace />${name}_${currentLocale}' : '#<portlet:namespace />${name}';
			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'itemSelected${name}${currentLocale.language}',
					on: {
						// Evénement déclenché lors de la sélection d'un fichier
						selectedItemChange: function(event) {
							var selectedItem = event.newVal;
							if (!!selectedItem && !!selectedItem.value) {
								var itemValue = JSON.parse(selectedItem.value);
								var htmlToAppend = '<li>'
									+ '<a href="' + itemValue.url + '" target="_blank">' + itemValue.title + '</a> - <a href="#" class="remove-file" data-entry-id="' + itemValue.fileEntryId + '">Supprimer</a>';
									+ '</li>';
								if (!multipleSelection) {
									$('#files-thumbnails-${name}-${currentLocale}').empty();
								}
								$('#files-thumbnails-${name}-${currentLocale}').append(htmlToAppend);
								if (!multipleSelection) {
									$(fieldName).val(itemValue.fileEntryId);
								} else {
									var currentValue = $(fieldName).val();
									var newValue = currentValue;
									if (currentValue.length > 0) {
										newValue += ',';
									}
									newValue += itemValue.fileEntryId;
									$(fieldName).val(newValue);
								}							
							}
						}
					},
					title: '<liferay-ui:message key="file"/>',
					<c:set var="itemSelectorURLVarName" value="itemSelectorURL${currentLocale.language}" />
					url: '${requestScope[itemSelectorURLVarName]}'
				}
			);
			itemSelectorDialog.open();
		}
	);
	// Suppression d'un fichier
	$('#files-thumbnails-${name}-${currentLocale}').on('click', '.remove-file', function(e) {
		e.preventDefault();
		var localized = ${localized};
		var fieldName = localized ? '#<portlet:namespace />${name}_${currentLocale}' : '#<portlet:namespace />${name}';
		var valueToRemove = $(this).data('entry-id');
		$(this).parent().remove();
		var currentValue = $(fieldName).val();
		var newValue = currentValue.replace(valueToRemove, '');
		newValue = newValue.replace(',,', ',');
		if (newValue[0] === ',') {
			newValue = newValue.slice(1);
		}
		if (newValue[newValue.length - 1] == ',') {
			newValue = newValue.slice(0, -1);
		}
		$(fieldName).val(newValue);
	});
	</aui:script>
</c:forEach>
<%-- 
	Si le champ est localisé, on masque les champs par défaut et on affiche ceux qui ont la classe "active"
	La gestion du toggle de la classe "active" est géré par le JS du module utilisateur
--%>
<c:if test="${localized}">
	<style>
		.picker-${name} {
			display: none;
		}
		.picker-${name}.active {
			display: block;
		}
	</style>
</c:if>