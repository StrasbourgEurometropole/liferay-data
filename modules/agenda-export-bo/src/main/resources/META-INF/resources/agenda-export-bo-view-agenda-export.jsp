<%@ include file="/META-INF/resources/agenda-export-bo-init.jsp" %>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="agendaExportsURL">
	<portlet:param name="tab" value="agendaExports" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien vers la page d'ajout/edition d'une entite --%>
<liferay-portlet:renderURL varImpl="addAgendaExportsURL">
	<portlet:param name="cmd" value="editAgendaExports" />
	<portlet:param name="mvcPath" value="/agenda-export-bo-edit-agenda-export.jsp" />
	<portlet:param name="returnURL" value="${agendaExportsURL}" />
</liferay-portlet:renderURL>



<%-- Composant : barre de filtres et de gestion des entite --%>
<liferay-frontend:management-bar includeCheckBox="true" searchContainerId="agendaExportsSearchContainer">

		<%-- Composant : partie filtres et selection --%>
		<liferay-frontend:management-bar-filters>
			<c:if test="${fn:length(dc.vocabularies) > 0}">
				<li><a>Filtrer par :</a></li>
			</c:if>
			<c:forEach var="vocabulary" items="${dc.vocabularies}">
				<liferay-frontend:management-bar-filter 
					managementBarFilterItems="${dc.getManagementBarFilterItems(vocabulary)}" 
					value="${dc.getVocabularyFilterLabel(vocabulary)}" />
			</c:forEach>

			<liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
				orderByType="${dc.orderByType}"
				orderColumns='<%= new String[] {"userName", "modified-date","status","reportings","entityName"} %>'
				portletURL="${agendaExportsURL}" />
		</liferay-frontend:management-bar-filters>

		<%-- Composant : partie gestion (affichee apres une selection) --%>
		<liferay-frontend:management-bar-action-buttons>
			<c:if test="${not dc.workflowEnabled}">
				<c:if test="${dc.hasPermission('EDIT_AGENDA_EXPORT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "publishSelection();"%>'
						icon="check" label="publish" />
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "unpublishSelection();"%>'
						icon="times" label="unpublish" />
				</c:if>
			</c:if>
			<c:if test="${dc.hasPermission('DELETE_AGENDA_EXPORT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
			<liferay-frontend:management-bar-button
				href='<%="javascript:" + renderResponse.getNamespace() + "deleteSelection();"%>'
				icon="trash" label="delete" />
			</c:if>
		</liferay-frontend:management-bar-action-buttons>
		
</liferay-frontend:management-bar>



<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="agendaExportsSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.agendaExports}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.agenda.model.AgendaExport" modelVar="agendaExport"
				keyProperty="agendaExportId" rowIdProperty="agendaExportId">
				
				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
				<liferay-portlet:renderURL varImpl="editAgendaExportURL">
					<portlet:param name="tab" value="agendaExports" />
					<portlet:param name="cmd" value="editAgendaExports" />
					<portlet:param name="agendaExportId" value="${agendaExport.agendaExportId}" />
					<portlet:param name="returnURL" value="${agendaExportsURL}" />
					<portlet:param name="mvcPath" value="/agenda-export-bo-edit-agenda-export.jsp" />
				</liferay-portlet:renderURL>

				<%-- Colonne : userName --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editAgendaExportURL}" name="userName" truncate="true" orderable="true"
					value="${agendaExport.userName}" />

				<liferay-ui:search-container-column-text cssClass="content-column"
														 href="${editAgendaExportURL}" name="agendaExport" truncate="true" orderable="true"
														 value="${agendaExport.titleCurrentValue}" />
				
				<%-- Colonne : Date de modification --%>
				<fmt:formatDate value="${agendaExport.modifiedDate}"
					var="formattedModifiedDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="modified-date" truncate="true" orderable="true"
					value="${formattedModifiedDate}" />

				<%-- Colonne : Statut --%>
				<liferay-ui:search-container-column-text name="status" orderable="true">
					<aui:workflow-status markupView="lexicon" showIcon="false"
						showLabel="false" status="${agendaExport.status}" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<c:if test="${dc.hasPermission('EDIT_AGENDA_EXPORT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editAgendaExportURL}" />
						</c:if>
						
						<%-- URL : definit le lien vers la page de duplication d'une entite --%>
						<liferay-portlet:renderURL varImpl="copyAgendaExportURL">
							<portlet:param name="cmd" value="copyAgendaExports" />
							<portlet:param name="mvcPath" value="/agenda-export-bo-copy-agenda-export.jsp" />
							<portlet:param name="agendaExportId" value="${agendaExport.agendaExportId}" />
							<portlet:param name="returnURL" value="${agendaExportsURL}" />
						</liferay-portlet:renderURL>
						<liferay-ui:icon message="copy" url="${copyAgendaExportURL}" />
						
						<liferay-portlet:actionURL name="deleteAgendaExport"
							var="deleteAgendaExportURL">
							<portlet:param name="cmd" value="deleteAgendaExport" />
							<portlet:param name="tab" value="agendaExports" />
							<portlet:param name="agendaExportId" value="${agendaExport.agendaExportId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_AGENDA_EXPORT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' url="#"/>
						</c:if>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>
				
			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if test="${dc.hasPermission('ADD_AGENDA_EXPORT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="Ajouter un Export Agenda"
			url="${addAgendaExportsURL}" />
	</liferay-frontend:add-menu>
</c:if>

<%-- URL : definit le lien vers l'action de suppression --%>
<liferay-portlet:actionURL name="selectionAction" var="deleteSelectionURL">
	<portlet:param name="tab" value="agendaExports" />
	<portlet:param name="cmd" value="delete" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien vers l'action de publication --%>
<liferay-portlet:actionURL name="selectionAction" var="publishSelectionURL">
	<portlet:param name="tab" value="agendaExports" />
	<portlet:param name="cmd" value="publish" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien vers l'action de depublication --%>
<liferay-portlet:actionURL name="selectionAction" var="unpublishSelectionURL">
	<portlet:param name="tab" value="agendaExports" />
	<portlet:param name="cmd" value="unpublish" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:actionURL>


<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-selected-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${deleteSelectionURL}');
		}
	}
	function <portlet:namespace />publishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-publish-selected-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${publishSelectionURL}');
		}
	}
	function <portlet:namespace />unpublishSelection() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-unpublish-selected-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			var selectionIdsInput = document
					.getElementsByName('<portlet:namespace />selectionIds')[0];
			selectionIdsInput.value = Liferay.Util.listCheckedExcept(form,
					'<portlet:namespace />allRowIds');

			submitForm(form, '${unpublishSelectionURL}');
		}
	}

	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteAgendaExportURL}';
		}
	}
</aui:script>
