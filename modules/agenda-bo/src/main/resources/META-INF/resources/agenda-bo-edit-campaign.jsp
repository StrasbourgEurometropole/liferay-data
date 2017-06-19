<%@ include file="/agenda-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.agenda.model.Campaign"%>

<liferay-portlet:renderURL varImpl="campaignsURL">
	<portlet:param name="tab" value="campaigns" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteCampaign" var="deleteCampaignURL">
	<portlet:param name="cmd" value="deleteCampaign" />
	<portlet:param name="tab" value="campaigns" />
	<portlet:param name="campaignId"
		value="${not empty dc.campaign ? dc.campaign.campaignId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveCampaign" varImpl="saveCampaignURL">
	<portlet:param name="cmd" value="saveCampaign" />
	<portlet:param name="tab" value="campaigns" />
</liferay-portlet:actionURL>


<div class="container-fluid-1280 main-content-body">
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="image-error" message="image-error" />
	<liferay-ui:error key="copyright-error" message="copyright-error" />
	<liferay-ui:error key="managers-error" message="managers-error" />
	<liferay-ui:error key="themes-error" message="themes-error" />
	
	<aui:form action="${saveCampaignURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.campaign}" model="<%=Campaign.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="campaignId" type="hidden" />

			<aui:fieldset collapsed="false" collapsible="true" label="general">

				<aui:input name="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>
				
				<strasbourg-picker:image label="eu.default-image" name="defaultImageId"
						required="true" value="${dc.campaign.defaultImageId}" global="true" />
						
				<aui:input name="defaultImageCopyright" label="default-copyright" required="true" />

				<aui:input name="exportEnabled" type="toggle-switch"
					value="${not empty dc.campaign ? dc.campaign.exportEnabled : false}" />

				<label><liferay-ui:message key="themes" /><span class="icon-asterisk text-warning"></span></label>
				<select id="themesIds" name="<portlet:namespace />themesIds" placeholder="<liferay-ui:message key="select-themes" />" multiple >
					<c:forEach var="theme" items="${dc.themes}">
						<option value="${theme.categoryId}"
							<c:if test="${fn:contains(dc.themesIds, theme.categoryId)}">
								selected
							</c:if>
						>
							${theme.getTitle(locale)}
						</option>
					</c:forEach>
				</select>
				
				<label><liferay-ui:message key="managers" /><span class="icon-asterisk text-warning"></span></label>
				<select class="form-control" name="<portlet:namespace />managersIds"
					id="managersIds"
					placeholder="<liferay-ui:message key="select-managers" />" multiple>
				</select>
			</aui:fieldset>

			<aui:fieldset collapsed="true" collapsible="true"
				label="categorization">

				<aui:input name="tags" type="assetTags" />

			</aui:fieldset>

		</aui:fieldset-group>


		<aui:button-row>
			<c:if
				test="${(dc.hasPermission('ADD_CAMPAIGN') and empty dc.campaign or dc.hasPermission('EDIT_CAMPAIGN') and not empty dc.campaign) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:input type="hidden" name="workflowAction" value="" />
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish"
						value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit"
						name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			<c:if
				test="${not empty dc.campaign and dc.hasPermission('DELETE_CAMPAIGN') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			<liferay-portlet:renderURL varImpl="cancelURL">
				<liferay-portlet:param name="tab" value="campaigns" />
			</liferay-portlet:renderURL>
			<aui:button cssClass="btn-lg" href="${cancelURL}" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>
<liferay-util:html-bottom>
	<script>
		var managersIds = "${dc.campaign.managersIds}";
		define._amd = define.amd;
		define.amd = false;
	</script>
	<!-- Include Choices CSS -->
	<link rel="stylesheet" href="/o/agendabo/css/vendors/choices.min.css">
	<!-- Include Choices JavaScript -->
	<script src="/o/agendabo/js/vendors/choices.min.js"></script>
	<script src="/o/agendabo/js/agenda-bo-edit-campaign.js"></script>
	<script>
		define.amd = define._amd;
	</script>
</liferay-util:html-bottom>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteCampaignURL}';
		}
	}
</aui:script>