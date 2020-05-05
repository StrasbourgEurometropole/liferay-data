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
            orderColumns='<%= new String[] {"full-name"} %>'
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
					href="${editOfficialURL}" name="full-name" truncate="true"
					orderable="true" value="${official.fullName}" />

				<!-- Colonne : Email -->
                <liferay-ui:search-container-column-text cssClass="content-column"
                    href="${editOfficialURL}" name="email" truncate="true"
                    orderable="false" value="${official.email}" />

                <!-- Colonne : Type municipale -->
                <liferay-ui:search-container-column-text cssClass="content-column"
                    name="is-municipal" truncate="true"
                    orderable="false" value="${official.isMunicipal ? 'Oui' : 'Non'}" />

                <!-- Colonne : Type eurométropolitain -->
                <liferay-ui:search-container-column-text cssClass="content-column"
                    name="is-eurometropolitan" truncate="true"
                    orderable="false" value="${official.isEurometropolitan ? 'Oui' : 'Non'}" />

                <!-- Colonne : Est actif ? -->
                <liferay-ui:search-container-column-text cssClass="content-column"
                    name="is-active" truncate="true"
                    orderable="false" value="${official.isActive ? 'Oui' : 'Non'}" />

                <!-- ACTIONS -->
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">

						<!-- ACTION : Édition -->
						<c:if test="${dc.hasPermission('EDIT_OFFICIAL') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editOfficialURL}" />
						</c:if>

					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>

	<!-- ACTION RESOURCE : Export des élus-->
    <liferay-portlet:resourceURL var="exportOfficialsURL" id="exportOfficials" />
	<form method="POST" action="${exportOfficialsURL}">
        <aui:button-row>
            <aui:button cssClass="btn-lg" type="submit" value="eu.council.bo.export.officials" />
        </aui:button-row>
    </form>

</div>

<c:if test="${dc.hasPermission('ADD_OFFICIAL') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="eu.council.bo.add.official" url="${addOfficialURL}" />
	</liferay-frontend:add-menu>
</c:if>