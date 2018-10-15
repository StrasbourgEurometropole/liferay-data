<%@ include file="/oidc-bo-init.jsp"%>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="publikUsersURL">
	<portlet:param name="tab" value="publikUsers" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<%-- Composant : definit la liste des messages d'erreur --%>
<liferay-ui:error key="anonym-user-unfound" message="anonym-user-unfound" />
<liferay-ui:error key="no-anonym-user-id" message="no-anonym-user-id" />
<liferay-ui:error key="user-unfound" message="user-unfound" />
<liferay-ui:error key="no-user-id" message="no-user-id" />
<%-- Composant : definit la liste des messages de réalisation --%>
<liferay-ui:success key="anonymised" message="anonymised" />

<%-- Composant : barre de filtres et de gestion des entite --%>
<liferay-frontend:management-bar includeCheckBox="true" searchContainerId="publikUsersSearchContainer">

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
			orderColumns='<%= new String[] {"last-name", "first-name", "email", "banish-date"} %>'
			portletURL="${publikUsersURL}" />
	</liferay-frontend:management-bar-filters>
		
</liferay-frontend:management-bar>

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="publikUsersSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.publikUsers}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.oidc.model.PublikUser" modelVar="publikUser"
				keyProperty="publikUserLiferayId" rowIdProperty="publikUserLiferayId">
				
				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
				<liferay-portlet:renderURL varImpl="editPublikUserURL">
					<portlet:param name="cmd" value="editPublikUser" />
					<portlet:param name="publikUserLiferayId" value="${publikUser.publikUserLiferayId}" />
					<portlet:param name="returnURL" value="${publikUsersURL}" />
					<portlet:param name="mvcPath" value="/oidc-bo-edit-publikuser.jsp" />
				</liferay-portlet:renderURL>

				<%-- Colonne : Nom --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editPublikUserURL}" name="last-name" truncate="true" orderable="true"
					value="${publikUser.lastName}" />
				
				<%-- Colonne : Prenom --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editPublikUserURL}" name="first-name" truncate="true" orderable="true"
					value="${publikUser.firstName}" />
				
				<%-- Colonne : Email --%>
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editPublikUserURL}" name="email" truncate="true" orderable="true"
					value="${publikUser.email}" />
				
				<%-- Colonne : Date de bannissement --%>
				<fmt:formatDate value="${publikUser.banishDate}"
					var="formattedBanishDate" type="date" pattern="dd/MM/yyyy HH:mm" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="banish-date" truncate="true" orderable="true"
					value="${formattedBanishDate}" />
				
				<%-- URL : definit le lien vers la page d'historique de l'entite selectionnee --%>
				<liferay-portlet:resourceURL var="historicPublikUserURL" id="exportHistoricText">
					<portlet:param name="publikUserLiferayId" value="${publikUser.publikUserLiferayId}" />
				</liferay-portlet:resourceURL>
				
				<%-- URL : definit le lien vers la page d'anonymisation de l'entite selectionnee --%>
				<liferay-portlet:renderURL varImpl="anonymisedInfosURL">
					<portlet:param name="cmd" value="anonymisedUser" />
					<portlet:param name="publikUserLiferayId" value="${publikUser.publikUserLiferayId}" />
				</liferay-portlet:renderURL>

				<%-- Colonne : Actions possibles --%>
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<c:if test="${dc.hasPermission('EDIT_PUBLIKUSER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editPublikUserURL}" />
						</c:if>
						<liferay-ui:icon message="historic" url="${historicPublikUserURL}" />
						<liferay-ui:icon message="anonymised" url="${anonymisedInfosURL}" />
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

