<%@ include file="/council-bo-init.jsp" %>

<liferay-portlet:renderURL varImpl="typesURL">
	<portlet:param name="tab" value="types" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="addTypeURL">
	<portlet:param name="cmd" value="editType" />
	<portlet:param name="mvcPath" value="/council-bo-edit-type.jsp" />
	<portlet:param name="returnURL" value="${typesURL}" />
</liferay-portlet:renderURL>


<liferay-frontend:management-bar includeCheckBox="true" searchContainerId="typesSearchContainer">
    <liferay-frontend:management-bar-filters>
        <liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
            orderByType="${dc.orderByType}"
            orderColumns='<%= new String[] {"title"} %>'
            portletURL="${typesURL}" />
    </liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>


<div class="container-fluid-1280 main-content-body">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "doProcessAction" dans le deleteAction de l'entite) --%>
	<liferay-ui:error key="type-has-council-error" message="type-has-council-error" />

	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="typesSearchContainer" searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.types}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.council.model.Type"
				modelVar="type" keyProperty="typeId" rowIdProperty="typeId">

				<liferay-portlet:renderURL varImpl="editTypeURL">
					<portlet:param name="cmd" value="editType" />
					<portlet:param name="typeId" value="${type.typeId}" />
					<portlet:param name="returnURL" value="${typesURL}" />
					<portlet:param name="mvcPath" value="/council-bo-edit-type.jsp" />
				</liferay-portlet:renderURL>

                <!-- Colonne : Titre -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editTypeURL}" name="title" truncate="true"
					orderable="true" value="${type.title}" />

                <!-- ACTIONS -->
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">

						<!-- ACTION : Édition -->
						<c:if test="${dc.hasPermission('EDIT_TYPE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editTypeURL}" />
						</c:if>

                        <!-- ACTION : Supprimer -->
						<liferay-portlet:actionURL name="deleteType" var="deleteTypeURL">
							<portlet:param name="cmd" value="deleteType" />
							<portlet:param name="tab" value="types" />
							<portlet:param name="typeId" value="${type.typeId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_TYPE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="delete" url="${deleteTypeURL}" />
						</c:if>

					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if test="${dc.hasPermission('ADD_TYPE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="Ajouter un type" url="${addTypeURL}" />
	</liferay-frontend:add-menu>
</c:if>