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
			orderColumns='<%= new String[] {"last-name", "first-name", "email", "request-create-date", "nb-requests"} %>'
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
                className="eu.strasbourg.portlet.help.context.ViewHelpSeekersDisplayContext.HelpSeeker" modelVar="helpSeeker"
                keyProperty="publikUser.publikUserLiferayId" rowIdProperty="publikUserLiferayId" escapedModel="true">

                <%-- URL : definit le lien vers la page de consultation des demandes d'aide --%>
                <liferay-portlet:renderURL varImpl="viewSeekerHelpRequestsURL">
                    <portlet:param name="cmd" value="viewSeekerHelpRequests" />
                    <portlet:param name="helpSeekerId" value="${helpSeeker.publikUser.publikId}" />
                    <portlet:param name="returnURL" value="${helpSeekersURL}" />
                    <portlet:param name="mvcPath" value="/help-bo-view-seeker-help-requests.jsp" />
                </liferay-portlet:renderURL>

                <%-- Colonne : Nom --%>
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${viewSeekerHelpRequestsURL}" name="last-name" truncate="true" orderable="true">
                    <c:out value="${helpSeeker.publikUser.lastName}" escapeXml='true'/>
                </liferay-ui:search-container-column-text>

                <%-- Colonne : Prenom --%>
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${viewSeekerHelpRequestsURL}" name="first-name" truncate="true" orderable="true">
                    <c:out value="${helpSeeker.publikUser.firstName}" escapeXml='true'/>
                </liferay-ui:search-container-column-text>

                <%-- Colonne : Email --%>
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${viewSeekerHelpRequestsURL}" name="email" truncate="true" orderable="true">
                    <c:out value="${helpSeeker.publikUser.email}" escapeXml='true'/>
                </liferay-ui:search-container-column-text>

                <%-- Colonne : Nombre de demandes de l'utilisateur --%>
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${viewSeekerHelpRequestsURL}" name="nb-requests" truncate="true" orderable="true"
                    value="${helpSeeker.requestsNumber}" />

                <%-- Colonne : Date de dernière demande --%>
                <fmt:formatDate value="${helpSeeker.lastRequest.createDate}"
                    var="formattedLastRequestDate" type="date" pattern="dd/MM/yyyy HH:mm" />
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${viewSeekerHelpRequestsURL}" name="last-request-date" truncate="true" orderable="true"
                    value="${formattedLastRequestDate}" />

				<%-- URL : definit le lien vers la page de consultation des demandes d'aide --%>
				<liferay-portlet:renderURL varImpl="editHelpSeekerURL">
					<portlet:param name="cmd" value="editHelpSeeker" />
					<portlet:param name="helpSeekerId" value="${helpSeeker.publikUser.publikUserLiferayId}" />
					<portlet:param name="returnURL" value="${dc.currentURL}" />
					<portlet:param name="mvcPath" value="/oidc-bo-edit-publikuser.jsp" />
				</liferay-portlet:renderURL>
                <%-- Colonne : Actions possibles --%>
                <liferay-ui:search-container-column-text>
                    <liferay-ui:icon-menu markupView="lexicon" showWhenSingleIcon="true">
                        <liferay-ui:icon message="view-help-requests" url="${viewSeekerHelpRequestsURL}" />

						<%--
						<c:set value="${helpSeeker.publikUser.publikUserLiferayId}" var="publikId" />
						<c:if test="${dc.hasPermissionOIDC('EDIT_PUBLIKUSER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="view-user-profil" url="${dc.getPublikUserEditURL(publikId)}" />
						</c:if>
						--%>

						<%-- Suppression des justificatifs de l'etudiant --%>
						<liferay-portlet:actionURL name="deleteStudentCardImages" var="deleteStudentCardImagesURL">
							<portlet:param name="cmd" value="deleteStudentCardImages" />
							<portlet:param name="tab" value="helpSeekers" />
							<portlet:param name="studentPublikId" value="${helpSeeker.publikUser.publikId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('EDIT_HELP_REQUEST') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon-delete confirmation="delete-student-ids-confirm" message="${helpSeeker.imagesCount}" url="${deleteStudentCardImagesURL}" />
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
