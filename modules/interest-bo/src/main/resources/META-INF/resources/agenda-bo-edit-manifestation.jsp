<%@ include file="/interest-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.interest.model.Interest"%>

<liferay-portlet:renderURL varImpl="interestsURL">
	<portlet:param name="tab" value="interests" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteInterest" var="deleteInterestURL">
	<portlet:param name="cmd" value="deleteInterest" />
	<portlet:param name="tab" value="interests" />
	<portlet:param name="interestId"
		value="${not empty dc.interest ? dc.interest.interestId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveInterest" varImpl="saveInterestURL">
	<portlet:param name="cmd" value="saveInterest" />
	<portlet:param name="tab" value="interests" />
</liferay-portlet:actionURL>


<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveInterestURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.interest}"
			model="<%=Interest.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="interestId" type="hidden" />

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
								var validate = $('#_eu_strasbourg_portlet_interest_AgendaBOPortlet_description_fr_FR').val().length > 0;
								if (!validate) {
									$("#_eu_strasbourg_portlet_interest_AgendaBOPortlet_descriptionContainer").get(0).scrollIntoView();
								}
								return validate;
							}
						</aui:validator>
					</aui:input>
				</div>
				
			</aui:fieldset>

			<aui:fieldset collapsed="true" collapsible="true"
				label="categorization">
				
				<aui:input name="categories" type="assetCategories" wrapperCssClass="categories-selectors" />
				
				<!-- Hack pour ajouter une validation sur les vocabulaires obligatoires -->
				<div class="has-error">
					<aui:input type="hidden" name="assetCategoriesValidatorInputHelper" value="placeholder">
						<aui:validator name="custom" errorMessage="requested-vocabularies-error">
							function (val, fieldNode, ruleValue) {
								var validated = true;
								var fields = document.querySelectorAll('.categories-selectors > .field-content');
								for (var i = 0; i < fields.length; i++) {
									fieldContent = fields[i];
								    if ($(fieldContent).find('.icon-asterisk').length > 0
								    	&& $(fieldContent).find('input[type="hidden"]')[0].value.length == 0) {
								    	validated = false;
								    	break;
								    }
								}
								return validated;
							}
						</aui:validator>
					</aui:input>
				</div>

				<aui:input name="tags" type="assetTags" />

			</aui:fieldset>

		</aui:fieldset-group>
		
		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_INTEREST') and empty dc.interest or dc.hasPermission('EDIT_INTEREST') and not empty dc.interest) and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
			<c:if test="${not empty dc.interest and dc.hasPermission('DELETE_INTEREST') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>
<liferay-util:html-bottom>
	<script
		src="/o/interestbo/js/interest-bo-edit-manif.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteInterestURL}';
		}
	}
</aui:script>