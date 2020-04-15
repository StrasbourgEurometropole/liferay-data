<%@ include file="/council-init.jsp" %>

<liferay-portlet:renderURL varImpl="sessionsURL">
	<portlet:param name="tab" value="sessions" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds"
		value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="addSessionURL">
	<portlet:param name="cmd" value="editSession" />
	<portlet:param name="mvcPath" value="/council-bo-edit-session.jsp" />
	<portlet:param name="returnURL" value="${sessionsURL}" />
</liferay-portlet:renderURL>


<liferay-frontend:management-bar includeCheckBox="true"
	searchContainerId="sessionsSearchContainer">

		<liferay-frontend:management-bar-filters>

			<liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
				orderByType="${dc.orderByType}"
				orderColumns='<%= new String[] {"title", "modified-date", "publication-date", "status"} %>'
				portletURL="${sessionsURL}" />
		</liferay-frontend:management-bar-filters>

</liferay-frontend:management-bar>


<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="sessionsSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.sessions}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.council.model.Session"
				modelVar="session" keyProperty="sessionId" rowIdProperty="sessionId">
				<liferay-portlet:renderURL varImpl="editSessionURL">
					<portlet:param name="cmd" value="editSession" />
					<portlet:param name="sessionId" value="${session.sessionId}" />
					<portlet:param name="returnURL" value="${sessionsURL}" />
					<portlet:param name="mvcPath" value="/council-bo-edit-session.jsp" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editSessionURL}" name="title" truncate="true"
					orderable="true" value="${session.title}" />

				<fmt:formatDate value="${session.date}"
					var="formattedPublicationDate" type="date" pattern="dd/MM/yyyy" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="publication-date" truncate="true" orderable="true"
					value="${formattedPublicationDate}" />

				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<c:if test="${dc.hasPermission('EDIT_SESSION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editSessionURL}" />
						</c:if>

						<liferay-portlet:actionURL name="deleteSession"
							var="deleteSessionURL">
							<portlet:param name="cmd" value="deleteSession" />
							<portlet:param name="tab" value="sessions" />
							<portlet:param name="sessionId" value="${session.sessionId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_SESSION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deleteSessionURL}" />
						</c:if>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if test="${dc.hasPermission('ADD_SESSION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="Ajouter une session"
			url="${addSessionURL}" />
	</liferay-frontend:add-menu>
</c:if