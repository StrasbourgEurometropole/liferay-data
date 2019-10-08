<%@ include file="/notification-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.notification.model.Notification"%>
<c:set var="notif" value="${dc.notification}" />

<liferay-portlet:renderURL varImpl="notificationsURL">
	<portlet:param name="tab" value="notifications" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteNotification" var="deleteNotificationURL">
	<portlet:param name="cmd" value="deleteNotification" />
	<portlet:param name="tab" value="notifications" />
	<portlet:param name="notificationId"
		value="${not empty notif ? notif.notificationId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveNotification" varImpl="saveNotificationURL">
	<portlet:param name="cmd" value="saveNotification" />
	<portlet:param name="tab" value="notifications" />
</liferay-portlet:actionURL>


<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveNotificationURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${notif}"
			model="<%=Notification.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="notificationId" type="hidden" />

			<aui:fieldset collapsed="false" collapsible="true"
				label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="description" />

				<aui:input name="url">
					<aui:validator name="url" errorMessage="URL non valide" />
				</aui:input>
				
				<aui:select name="typeId" label="type" required="true">
					<aui:option value="" label="" />
					<c:forEach items="${dc.types}" var="category">
						<aui:option value="${category.categoryId}" selected="${notif.typeId eq  category.categoryId}">
							${category.getTitle(locale)}
						</aui:option>
					</c:forEach>
				</aui:select>
				
			</aui:fieldset>

			<aui:fieldset collapsed="true" collapsible="true"
				label="publication">
				
				<aui:input name="publicationDate" />
                <jsp:useBean id="nextMonth" class="java.util.GregorianCalendar">
                    ${nextMonth.add(2,1)}
                </jsp:useBean>
				<aui:input name="expirationDate" value="${not empty notif ? expirationDate : nextMonth}" />
				
			</aui:fieldset>

		</aui:fieldset-group>
		
		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_NOTIFICATION') and empty notif or dc.hasPermission('EDIT_NOTIFICATION') and not empty notif) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:input type="hidden" name="workflowAction" value="" />
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish"
							value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft"
							value="save-as-draft" />
				</c:if>
			</c:if>
			<c:if test="${not empty notif and dc.hasPermission('DELETE_NOTIFICATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>
<liferay-util:html-bottom>
	<script
		src="/o/notification-bo/js/notification-bo-edit-notification.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteNotificationURL}';
		}
	}
</aui:script>