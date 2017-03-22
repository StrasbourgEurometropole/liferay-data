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

				<aui:input name="exportEnabled" type="toggle-switch"
					value="${not empty dc.campaign ? dc.campaign.exportEnabled : false}" />

				<label><liferay-ui:message key="themes" /></label>
				<select class="form-control" name="<portlet:namespace />themesIds"
					id="themesIds"
					placeholder="<liferay-ui:message key="select-themes" />" multiple>
				</select>
				
				<label><liferay-ui:message key="managers" /></label>
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
						value="publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit"
						name="save-as-draft" value="save-as-draft" />
				</c:if>
			</c:if>
			<c:if
				test="${not empty dc.campaign and dc.hasPermission('DELETE_CAMPAIGN') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deleteCampaignURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>
<liferay-util:html-bottom>
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<!-- Include Choices CSS -->
	<link rel="stylesheet" href="/o/agendabo/css/vendors/choices.min.css">
	<!-- Include Choices JavaScript -->
	<script src="/o/agendabo/js/vendors/choices.min.js"></script>
	<script>
		define.amd = define._amd;
		Liferay.Service('/agenda.event/get-themes', function(themes) {
			var choices = [];
			var themesIds = "${dc.themesIds}";
			for (var i = 0; i < themes.length; i++) {
				choices.push({
					label: themes[i].name.fr_FR,
					value: themes[i].id,
					selected: themesIds.indexOf(themes[i].id) > -1
				});
			}
			new Choices('#themesIds', {
				removeItemButton: true,
				choices: choices
			});
		});
		
		Liferay.Service('/user/get-group-users', {
			groupId : Liferay.ThemeDisplay.getScopeGroupId()
		}, function(users) {
			var choices = [];
			var managersIds = "${dc.campaign.managersIds}";
			for (var i = 0; i < users.length; i++) {
				choices.push({
					label: users[i].firstName + " " + users[i].lastName + " (" + users[i].screenName + " - " + users[i].emailAddress + ")",
					value: users[i].userId,
					selected: managersIds.indexOf(users[i].userId) > -1
				});
			}
			new Choices('#managersIds', {
				removeItemButton: true,
				choices: choices
			});
		});
	</script>
</liferay-util:html-bottom>