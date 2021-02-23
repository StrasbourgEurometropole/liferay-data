<%@ include file="/help-bo-init.jsp"%>

<%-- URL : definit le lien avec les parametres de recherche des entites--%>
<liferay-portlet:renderURL varImpl="helpSeekersURL">
	<portlet:param name="tab" value="helpSeekers" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<%-- Composant : barre de filtres et de gestion des entite --%>
<liferay-frontend:management-bar includeCheckBox="false" searchContainerId="helpSeekersSearchContainer">

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
			orderColumns='<%= new String[] {"last-name", "first-name", "email", "banish-date", "nb-asks"} %>'
			portletURL="${helpSeekersURL}" />
	</liferay-frontend:management-bar-filters>

</liferay-frontend:management-bar>

<%-- Composant : tableau de visualisation des entites --%>
<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
	    <aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="helpSeekersSearchContainer" searchContainer="${dc.searchContainer}">

			<liferay-ui:search-container-results results="${dc.helpSeekers}" />

			<liferay-ui:search-container-row
                className="eu.strasbourg.service.oidc.model.PublikUser" modelVar="helpSeeker"
                keyProperty="publikUserLiferayId" rowIdProperty="publikUserLiferayId" escapedModel="true">

				<%-- URL : definit le lien vers la page d'edition de l'entite selectionnee --%>
				<%-- TODO : faire un lien vers le BO de OIDC -->
                <liferay-portlet:renderURL varImpl="editHelpSeekerURL">
                    <portlet:param name="cmd" value="editHelpSeeker" />
                    <portlet:param name="publikUserLiferayId" value="${helpSeeker.publikUserLiferayId}" />
                    <portlet:param name="returnURL" value="${helpSeekersURL}" />
                    <portlet:param name="mvcPath" value="/oidc-bo-edit-publikuser.jsp" />
                </liferay-portlet:renderURL>

                <%-- URL : definit le lien vers la page de consultation des aides --%>
                <liferay-portlet:renderURL varImpl="showSeekerHelpRequestsURL">
                    <portlet:param name="cmd" value="showSeekerHelpRequests" />
                    <portlet:param name="helpSeekerId" value="${helpSeeker.publikUserLiferayId}" />
                    <portlet:param name="returnURL" value="${helpSeekersURL}" />
                    <portlet:param name="mvcPath" value="/help-bo-show-seeker-hep-requests.jsp" />
                </liferay-portlet:renderURL>

                <%-- Colonne : Nom --%>
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${showSeekerHelpRequestsURL}" name="last-name" truncate="true" orderable="true"
                    value="${helpSeeker.lastName}" />

                <%-- Colonne : Prenom --%>
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${showSeekerHelpRequestsURL}" name="first-name" truncate="true" orderable="true"
                    value="${helpSeeker.firstName}" />

                <%-- Colonne : Email --%>
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${showSeekerHelpRequestsURL}" name="email" truncate="true" orderable="true"
                    value="${helpSeeker.email}" />

                <%-- Colonne : Nombre de demandes de l'utilisateur' --%>
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${showSeekerHelpRequestsURL}" name="nb-asks" truncate="true" orderable="true"
                    value="3" />

                <%-- Colonne : Actions possibles --%>
                <liferay-ui:search-container-column-text>
                    <liferay-ui:icon-menu markupView="lexicon">
                        <%-- TODO : ajouter un checker qui verifie selon OIDC BO
                            <c:if test="${dc.hasPermission('EDIT_PUBLIKUSER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                        --%>
                            <liferay-ui:icon message="view-user-profil" url="${showSeekerHelpRequestsURL}" />
                            <liferay-ui:icon message="show-help-requests" url="${showSeekerHelpRequestsURL}" />
                        <%--
                            </c:if>
                        --%>

                    </liferay-ui:icon-menu>
                </liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<%-- Iterateur --%>
			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>
