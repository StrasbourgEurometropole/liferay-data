<%@ include file="./activity-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.activity.model.ActivityOrganizer"%>

<liferay-portlet:renderURL varImpl="activityOrganizersURL">
	<portlet:param name="tab" value="activityOrganizers" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteActivityOrganizer" var="deleteActivityURL">
	<portlet:param name="cmd" value="deleteActivityOrganizer" />
	<portlet:param name="tab" value="activityOrganizers" />
	<portlet:param name="activityOrganizerId"
		value="${not empty dc.activityOrganizer ? dc.activityOrganizer.activityOrganizerId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveActivityOrganizer" varImpl="saveActivityOrganizerURL">
	<portlet:param name="cmd" value="saveActivityOrganizer" />
	<portlet:param name="tab" value="activityOrganizers" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:error key="name-error" message="title-error" />
	
	<aui:form action="${saveActivityOrganizerURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.activityOrganizer}" model="<%=ActivityOrganizer.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="activityOrganizerId" type="hidden" />

			<aui:fieldset collapsed="true" collapsible="true"
				label="general">

				<aui:input name="name">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>
				
				<aui:input name="presentation" />
				
				<aui:input name="tags" type="assetTags" />
				
				<aui:input name="address" />
					
				<aui:input name="phone" helpMessage="telephone-help" />
				
				<aui:input name="mail" >
					<aui:validator name="email"/>
				</aui:input>

				<aui:input name="siteURL" >
				 	<aui:validator name="url"/>
				</aui:input>
				
				<strasbourg-picker:image label="image" name="imageId"
					required="false" value="${dc.activityOrganizer.imageId}" /> 
				
			</aui:fieldset>

		</aui:fieldset-group>
		
		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_ACTIVITY_ORGANIZER') and empty dc.activityOrganizer or dc.hasPermission('EDIT_ACTIVITY_ORGANIZER') and not empty dc.activityOrganizer) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:input type="hidden" name="workflowAction" value="" />					<aui:button cssClass="btn-lg" type="submit" name="publish"
					value="eu.publish" />
				<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft"
					value="save-as-draft" />
			</c:if>
			<c:if test="${not empty dc.activityOrganizer and dc.hasPermission('DELETE_ACTIVITY_ORGANIZER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteActivityOrganizerURL}';
		}
	}
</aui:script>