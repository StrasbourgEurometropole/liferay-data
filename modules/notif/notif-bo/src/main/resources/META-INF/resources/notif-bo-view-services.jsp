<%@ include file="/notif-bo-init.jsp"%>

<liferay-portlet:renderURL varImpl="servicesURL">
	<portlet:param name="tab" value="services" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<div class="container-fluid-1280 main-content-body">

	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="notifsSearchContainer" searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.services}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.notif.model.ServiceNotif"
				modelVar="service" keyProperty="serviceId" rowIdProperty="serviceId">

				<liferay-portlet:renderURL varImpl="editServiceURL">
					<portlet:param name="cmd" value="editService" />
					<portlet:param name="serviceId" value="${service.serviceId}" />
					<portlet:param name="returnURL" value="${servicesURL}" />
					<portlet:param name="mvcPath" value="/notif-bo-edit-service.jsp" />
				</liferay-portlet:renderURL>

                <!-- Colonne : nom du service -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="eu.strasbourg.notif.service.name" truncate="true"
					href="${editServiceURL}" value="${service.name}" />

                <!-- ACTIONS -->
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">

						<!-- ACTION : Modifier -->
						<c:if test="${dc.hasPermission('EDIT_SERVICE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="eu.strasbourg.notif.edit" url="${editServiceURL}" />
						</c:if>

                        <!-- ACTION : Supprimer -->
						<liferay-portlet:actionURL name="deleteService" var="deleteServiceURL">
							<portlet:param name="cmd" value="deleteService" />
							<portlet:param name="tab" value="services" />
							<portlet:param name="serviceId" value="${service.serviceId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_SERVICE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="eu.strasbourg.notif.delete" url="javascript:areYouSure('${deleteServiceURL}')" />
						</c:if>

					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>


<liferay-portlet:renderURL varImpl="addServiceURL">
	<portlet:param name="cmd" value="editService" />
	<portlet:param name="mvcPath" value="/notif-bo-edit-service.jsp" />
	<portlet:param name="returnURL" value="${servicesURL}" />
</liferay-portlet:renderURL>
<c:if test="${dc.hasPermission('ADD_SERVICE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="Ajouter un service" url="${addServiceURL}" />
	</liferay-frontend:add-menu>
</c:if>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function areYouSure(url) {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);
			submitForm(form, url);
		}
	}
</aui:script>