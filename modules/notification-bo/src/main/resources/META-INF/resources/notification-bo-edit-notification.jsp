<%@ include file="/notification-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.notification.model.Notification"%>

<liferay-portlet:renderURL varImpl="notificationsURL">
	<portlet:param name="tab" value="notifications" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteNotification" var="deleteNotificationURL">
	<portlet:param name="cmd" value="deleteNotification" />
	<portlet:param name="tab" value="notifications" />
	<portlet:param name="notificationId"
		value="${not empty dc.notification ? dc.notification.notificationId : ''}" />
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

		<aui:model-context bean="${dc.notification}"
			model="<%=Notification.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="notificationId" type="hidden" />

			<aui:fieldset collapsed="false" collapsible="true"
				label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="description" label="required-description" />
				<!-- Hack pour ajouter une validation sur la description -->
				<div class="has-error">
					<aui:input type="hidden" name="descriptionValidatorInputHelper" value="placeholder">
						<aui:validator name="custom" errorMessage="requested-description-error">
							function (val, fieldNode, ruleValue) {
								var validate = $('#_eu_strasbourg_portlet_notification_AgendaBOPortlet_description_fr_FR').val().length > 0;
								if (!validate) {
									$("#_eu_strasbourg_portlet_notification_AgendaBOPortlet_descriptionContainer").get(0).scrollIntoView();
								}
								return validate;
							}
						</aui:validator>
					</aui:input>
				</div>
				
				<aui:select name="typeId" label="type" required="true">
					<aui:option value="" label="" />
					<c:forEach items="${dc.types}" var="category">
						<aui:option value="${category.categoryId}" selected="${dc.notification.typeId eq  category.categoryId}">
							${category.getTitle(locale)}
						</aui:option>
					</c:forEach>
				</aui:select>
				
			</aui:fieldset>

			<aui:fieldset collapsed="true" collapsible="true"
				label="publication">
				
				<aui:input name="publicationDate" />
				<aui:input name="expirationDate" />
				
			</aui:fieldset>

		</aui:fieldset-group>
		
		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_NOTIFICATION') and empty dc.notification or dc.hasPermission('EDIT_NOTIFICATION') and not empty dc.notification) and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
			<c:if test="${not empty dc.notification and dc.hasPermission('DELETE_NOTIFICATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>
<liferay-util:html-bottom>
	<script
		src="/o/notificationbo/js/notification-bo-edit-manif.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteNotificationURL}';
		}
	}
</aui:script>