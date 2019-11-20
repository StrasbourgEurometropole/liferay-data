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
<liferay-frontend:management-bar includeCheckBox="false" searchContainerId="agendaExportsSearchContainer">

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
				orderColumns='<%= new String[] {"userName","title","modified-date","status"} %>'
				portletURL="${agendaExportsURL}" />
		</liferay-frontend:management-bar-filters>

</liferay-frontend:management-bar>

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
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
					<portlet:param name="toExport" value="false" />
					<portlet:param name="agendaExportId" value="${agendaExport.agendaExportId}" />
					<portlet:param name="returnURL" value="${agendaExportsURL}" />
					<portlet:param name="mvcPath" value="/agenda-export-bo-edit-agenda-export.jsp" />
				</liferay-portlet:renderURL>

				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
                <liferay-portlet:renderURL varImpl="exportAgendaExportURL">
                    <portlet:param name="tab" value="agendaExports" />
                    <portlet:param name="cmd" value="editAgendaExports" />
                    <portlet:param name="toExport" value="true" />
                    <portlet:param name="agendaExportId" value="${agendaExport.agendaExportId}" />
                    <portlet:param name="returnURL" value="${agendaExportsURL}" />
                    <portlet:param name="mvcPath" value="/agenda-export-bo-edit-agenda-export.jsp" />
                </liferay-portlet:renderURL>

				<%-- Colonne : userName --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editAgendaExportURL}" name="userName" truncate="true" orderable="true"
					value="${agendaExport.userName}" />

                <%-- Colonne : titre --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
														 href="${editAgendaExportURL}" name="title" truncate="true" orderable="true"
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

					    <liferay-ui:icon message="eu.agenda.export.export" url="${exportAgendaExportURL}" />

						<c:if test="${dc.hasPermission('EDIT_AGENDA_EXPORT') and dc.canEditAdminContent(agendaExport.agendaExportId) and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editAgendaExportURL}" />
						</c:if>
						
						<%-- URL : definit le lien vers la page de duplication d'une entite --%>
						<liferay-portlet:renderURL varImpl="copyAgendaExportURL">
							<portlet:param name="cmd" value="copyAgendaExports" />
							<portlet:param name="mvcPath" value="/agenda-export-bo-copy-agenda-export.jsp" />
							<portlet:param name="agendaExportId" value="${agendaExport.agendaExportId}" />
							<portlet:param name="returnURL" value="${agendaExportsURL}" />
						</liferay-portlet:renderURL>
						<liferay-ui:icon message="duplicate" url="${copyAgendaExportURL}" />
						
						<liferay-portlet:actionURL name="deleteAgendaExport"
							var="deleteAgendaExportURL">
							<portlet:param name="cmd" value="deleteAgendaExport" />
							<portlet:param name="tab" value="agendaExports" />
							<portlet:param name="agendaExportId" value="${agendaExport.agendaExportId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_AGENDA_EXPORT') and dc.canEditAdminContent(agendaExport.agendaExportId) and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deleteAgendaExportURL}"/>
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
