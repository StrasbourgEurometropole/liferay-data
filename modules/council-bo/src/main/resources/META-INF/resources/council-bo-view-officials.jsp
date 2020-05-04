<%@ include file="/council-bo-init.jsp" %>

<liferay-portlet:renderURL varImpl="officialsURL">
	<portlet:param name="tab" value="officials" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="addOfficialURL">
	<portlet:param name="cmd" value="editOfficial" />
	<portlet:param name="mvcPath" value="/council-bo-edit-official.jsp" />
	<portlet:param name="returnURL" value="${officialsURL}" />
</liferay-portlet:renderURL>


<liferay-frontend:management-bar includeCheckBox="true" searchContainerId="officialsSearchContainer">
    <liferay-frontend:management-bar-filters>
        <liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
            orderByType="${dc.orderByType}"
            orderColumns='<%= new String[] {"title", "date"} %>'
            portletURL="${officialsURL}" />
    </liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="officialsSearchContainer" searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.officials}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.council.model.Official"
				modelVar="official" keyProperty="officialId" rowIdProperty="officialId">

				<liferay-portlet:renderURL varImpl="editOfficialURL">
					<portlet:param name="cmd" value="editOfficial" />
					<portlet:param name="officialId" value="${official.officialId}" />
					<portlet:param name="returnURL" value="${officialsURL}" />
					<portlet:param name="mvcPath" value="/council-bo-edit-official.jsp" />
				</liferay-portlet:renderURL>

                <!-- Colonne : Nom complet -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editOfficialURL}" name="fullName" truncate="true"
					orderable="true" value="${official.fullName}" />

                <!-- Colonne : Type municipale -->
                <liferay-ui:search-container-column-text cssClass="content-column"
                    name="isMunicipal" truncate="true"
                    orderable="false" value="${official.isMunicipal}" />

                <!-- Colonne : Type eurométropolitain -->
                <liferay-ui:search-container-column-text cssClass="content-column"
                    name="isEurometropolitan" truncate="true"
                    orderable="false" value="${official.isEurometropolitan}" />

                <!-- Colonne : Est actif ? -->
                <liferay-ui:search-container-column-text cssClass="content-column"
                    name="isActive" truncate="true"
                    orderable="false" value="${official.isActive}" />

                <!-- ACTIONS -->
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">

						<!-- ACTION : Édition -->
						<c:if test="${dc.hasPermission('EDIT_OFFICIAL') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editOfficialURL}" />
						</c:if>

                        <!-- ACTION : Supprimer -->
						<liferay-portlet:actionURL name="deleteOfficial" var="deleteOfficialURL">
							<portlet:param name="cmd" value="deleteOfficial" />
							<portlet:param name="tab" value="officials" />
							<portlet:param name="officialId" value="${official.officialId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_OFFICIAL') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deleteOfficialURL}" />
						</c:if>

					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if test="${dc.hasPermission('ADD_OFFICIAL') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="eu.council.web.add.official" url="${addOfficialURL}" />
	</liferay-frontend:add-menu>
</c:if>