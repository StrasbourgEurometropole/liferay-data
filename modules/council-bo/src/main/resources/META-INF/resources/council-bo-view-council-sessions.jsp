<%@ include file="/council-bo-init.jsp" %>

<liferay-portlet:renderURL varImpl="councilSessionsURL">
	<portlet:param name="tab" value="councilSessions" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="addCouncilSessionURL">
	<portlet:param name="cmd" value="editCouncilSession" />
	<portlet:param name="mvcPath" value="/council-bo-edit-council-session.jsp" />
	<portlet:param name="returnURL" value="${councilSessionsURL}" />
</liferay-portlet:renderURL>


<liferay-frontend:management-bar includeCheckBox="true"searchContainerId="councilSessionsSearchContainer">
    <liferay-frontend:management-bar-filters>
        <liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
            orderByType="${dc.orderByType}"
            orderColumns='<%= new String[] {"title", "date"} %>'
            portletURL="${councilSessionsURL}" />
    </liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>


<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="councilSessionsSearchContainer" searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.councilSessions}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.council.model.CouncilSession"
				modelVar="councilSession" keyProperty="councilSessionId" rowIdProperty="councilSessionId">

				<liferay-portlet:renderURL varImpl="editCouncilSessionURL">
					<portlet:param name="cmd" value="editCouncilSession" />
					<portlet:param name="councilSessionId" value="${councilSession.councilSessionId}" />
					<portlet:param name="returnURL" value="${councilSessionsURL}" />
					<portlet:param name="mvcPath" value="/council-bo-edit-council-session.jsp" />
				</liferay-portlet:renderURL>

                <!-- Colonne : Titre -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editCouncilSessionURL}" name="title" truncate="true"
					orderable="true" value="${councilSession.title}" />

				<fmt:formatDate value="${councilSession.date}"
					var="formattedDate" type="date" pattern="dd/MM/yyyy" />

				<!-- Colonne : Date -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="date" truncate="true" orderable="true"
					value="${formattedDate}" />

                <!-- Colonne : Président -->
                <liferay-ui:search-container-column-text cssClass="content-column"
                    name="official-leader" truncate="true" orderable="true"
                    value="${councilSession.officialLeader.fullName}" />

                <!-- ACTIONS -->
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">

						<liferay-portlet:actionURL name="getDeliberationsPDF" var="getDeliberationsPDFURL">
							<portlet:param name="cmd" value="getDeliberationsPDF" />
							<portlet:param name="tab" value="councilSessions" />
							<portlet:param name="councilSessionId" value="${councilSession.councilSessionId}" />
						</liferay-portlet:actionURL>
                        <liferay-ui:icon message="getDeliberationsPDF" url="${getDeliberationsPDFURL}" />

                        <!-- ACTION : Édition -->
						<c:if test="${dc.hasPermission('EDIT_COUNCIL_SESSION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editCouncilSessionURL}" />
						</c:if>

						<!-- RESOURCE ACTION : Export des résultats de la session -->
						<liferay-portlet:resourceURL id="exportCouncilSessionResults" var="exportCouncilSessionResultsURL"
						        copyCurrentRenderParameters="false">
                            <portlet:param name="councilSessionId" value="${councilSession.councilSessionId}" />
                        </liferay-portlet:resourceURL>
                        <liferay-ui:icon message="export-results" url="${exportCouncilSessionResultsURL}" />

                        <!-- ACTION : Supprimer -->
						<liferay-portlet:actionURL name="deleteCouncilSession" var="deleteCouncilSessionURL">
							<portlet:param name="cmd" value="deleteCouncilSession" />
							<portlet:param name="tab" value="councilSessions" />
							<portlet:param name="councilSessionId" value="${councilSession.councilSessionId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_COUNCIL_SESSION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deleteCouncilSessionURL}" />
						</c:if>

					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if test="${dc.hasPermission('ADD_COUNCIL_SESSION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="Ajouter une session" url="${addCouncilSessionURL}" />
	</liferay-frontend:add-menu>
</c:if>