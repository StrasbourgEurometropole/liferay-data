<%@ include file="/notif-bo-init.jsp"%>

<liferay-portlet:renderURL varImpl="notificationsURL">
	<portlet:param name="tab" value="notifications" />
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
			<liferay-ui:search-container-results results="${dc.notifications}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.notif.model.Notification"
				modelVar="notification" keyProperty="notificationId" rowIdProperty="notificationId">

                <!-- Colonne : nom du service -->
                <c:if test="${isAdminNotification || dc.hasMultipleServices()}">
                    <liferay-ui:search-container-column-text cssClass="content-column"
                        name="eu.strasbourg.notif.service.name" truncate="true"
                        value="${dc.getService(notification.serviceId)}" />
                </c:if>

                <!-- Colonne : type -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="eu.strasbourg.notif.notification.type" truncate="true"
					value="${notification.isAlert == 1 ? 'Alerte' : 'Notification'}" />

                <!-- Colonne : nature -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="eu.strasbourg.notif.nature" truncate="true"
					value="${dc.getNature(notification.natureId)}" />

                <!-- Colonne : titre -->
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="eu.strasbourg.notif.notification.title" truncate="true"
					value="${notification.getTitle(locale)}" />

                <!-- Colonne : date de début -->
				<fmt:formatDate value="${notification.startDate}"
					var="formattedStartDate" type="date" pattern="dd/MM/yyyy" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="eu.strasbourg.notif.notification.start-date" truncate="true"
					value="${formattedStartDate}" />

                <!-- Colonne : date de fin -->
				<fmt:formatDate value="${notification.endDate}"
					var="formattedEndDate" type="date" pattern="dd/MM/yyyy" />
				<liferay-ui:search-container-column-text cssClass="content-column"
					name="eu.strasbourg.notif.notification.end-date" truncate="true"
					value="${formattedEndDate}" />

                <!-- Colonne : status -->
				<liferay-ui:search-container-column-text name="status">
					<aui:workflow-status markupView="lexicon" showIcon="false"
						showLabel="false" status="${notification.status}" />
				</liferay-ui:search-container-column-text>

                <!-- ACTIONS -->
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">

						<!-- ACTION : Modifier/Voir -->
                        <liferay-portlet:renderURL varImpl="editNotificationURL">
                            <portlet:param name="cmd" value="editNotification" />
                            <portlet:param name="notificationId" value="${notification.notificationId}" />
                            <portlet:param name="returnURL" value="${notificationsURL}" />
                            <portlet:param name="mvcPath" value="/notif-bo-edit-notification.jsp" />
                        </liferay-portlet:renderURL>
						<c:if test="${dc.hasPermission('EDIT_NOTIFICATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <c:if test="${dc.canUpdateOrDeleteNotification(notification.userId)}">
                                <liferay-ui:icon message="edit" url="${editNotificationURL}" />
                            </c:if>
                            <c:if test="${not dc.canUpdateOrDeleteNotification(notification.userId)}">
                                <liferay-ui:icon message="view" url="${editNotificationURL}" />
                            </c:if>
						</c:if>

						<%-- ACTION : Dupliquer --%>
                        <liferay-portlet:renderURL varImpl="copyEditNotificationURL">
                            <portlet:param name="cmd" value="editNotification" />
                            <portlet:param name="notificationId" value="${notification.notificationId}" />
                            <portlet:param name="isDuplication" value="true" />
                            <portlet:param name="returnURL" value="${notificationsURL}" />
                            <portlet:param name="mvcPath" value="/notif-bo-edit-notification.jsp" />
                        </liferay-portlet:renderURL>
						<c:if test="${dc.hasPermission('EDIT_NOTIFICATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="duplicate" url="${copyEditNotificationURL}" />
                        </c:if>

                        <!-- ACTION : Supprimer -->
						<liferay-portlet:actionURL name="deleteNotification" var="deleteNotificationURL">
							<portlet:param name="cmd" value="deleteNotification" />
							<portlet:param name="tab" value="notifications" />
							<portlet:param name="notificationId" value="${notification.notificationId}" />
						</liferay-portlet:actionURL>
						<c:if test="${dc.hasPermission('DELETE_NOTIFICATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <c:if test="${dc.canUpdateOrDeleteNotification(notification.userId)}">
                                <liferay-ui:icon message="delete" url="javascript:areYouSure('${deleteNotificationURL}')" />
                            </c:if>
						</c:if>

					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>


<liferay-portlet:renderURL varImpl="addNotificationURL">
	<portlet:param name="cmd" value="editNotification" />
	<portlet:param name="mvcPath" value="/notif-bo-edit-notification.jsp" />
	<portlet:param name="returnURL" value="${notificationsURL}" />
</liferay-portlet:renderURL>
<c:if test="${dc.hasPermission('ADD_NOTIFICATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="Ajouter une notification" url="${addNotificationURL}" />
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