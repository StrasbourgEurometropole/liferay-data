<%@ include file="/signalement-bo-init.jsp"%>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="signalementsURL">
	<portlet:param name="tab" value="signalements" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<%-- Composant : barre de filtres et de gestion des entite --%>
<liferay-frontend:management-bar includeCheckBox="true" searchContainerId="signalementsSearchContainer">

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
				orderColumns='<%= new String[] {"userName", "modified-date"} %>'
				portletURL="${signalementsURL}" />
		</liferay-frontend:management-bar-filters>

		<%-- Composant : partie gestion (affichee apres une selection) --%>
		<liferay-frontend:management-bar-action-buttons>
			<c:if test="${not dc.workflowEnabled}">
				<c:if test="${dc.hasPermission('EDIT_COMMENT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "publishSelection();"%>'
						icon="check" label="publish" />
					<liferay-frontend:management-bar-button
						href='<%="javascript:" + renderResponse.getNamespace() + "unpublishSelection();"%>'
						icon="times" label="unpublish" />
				</c:if>
			</c:if>
			<c:if test="${dc.hasPermission('DELETE_COMMENT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
		<liferay-ui:search-container id="signalementsSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.signalements}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.signalement.model.Signalement" modelVar="signalement"
				keyProperty="signalementId" rowIdProperty="signalementId">

				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
				<liferay-portlet:renderURL varImpl="editSignalementURL">
					<portlet:param name="cmd" value="editSignalement" />
					<portlet:param name="signalementId" value="${signalement.signalementId}" />
					<portlet:param name="returnURL" value="${signalementsURL}" />
					<portlet:param name="mvcPath" value="/signalement-bo-edit-signalement.jsp" />
				</liferay-portlet:renderURL>

				<%-- Colonne : userName --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editSignalementURL}" name="userName" truncate="true" orderable="true"
					value="${signalement.userName}" />

				<liferay-ui:search-container-column-text cssClass="content-column"
														 href="${editSignalementURL}" name="Signalementaire" truncate="true" orderable="true"
														 value="${signalement.signalement}" />

				<%-- Colonne : Date de modification--%>
				<fmt:formatDate value="${signalement.modifiedDate}"
					var="formattedModifiedDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="modified-date" truncate="true" orderable="true"
					value="${formattedModifiedDate}" />

				<%-- Colonne : Statut--%>
				<liferay-ui:search-container-column-text name="status">
					<aui:workflow-status markupView="lexicon" showIcon="false"
						showLabel="false" status="${signalement.status}" />
				</liferay-ui:search-container-column-text>

				<%-- Colonne : Type de l'entité--%>
				<liferay-ui:search-container-column-text cssClass="content-column"
                    name="Type de l'entite" truncate="true" orderable="true"
                    value="${signalement.getTypeAssetEntry()}" />

				<%-- Colonne : nom de l'entité--%>
				<liferay-ui:search-container-column-text cssClass="content-column"
                    name="Nom de l'entite" truncate="true" orderable="true"
                    value="${signalement.getAssetEntryTitle()}" />

				<%-- Colonne : lien vers la page
				<liferay-ui:search-container-column-text cssClass="content-column"
                    href="${signalement.urlProjectSignalementaire}" name="lien" truncate="true" orderable="true"
                    value="${signalement.urlProjectSignalementaire}" />
--%>
				<%-- Colonne : lien vers la page--%>
				<liferay-ui:search-container-column-text cssClass="content-column" name="localisation du signalementaire" >
				    <aui:button href="${signalement.urlProjectSignalementaire}" value="lien vers le signalementaire"/>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>
