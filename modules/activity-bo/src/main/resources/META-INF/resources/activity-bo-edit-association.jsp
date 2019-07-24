<%@ include file="./activity-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.activity.model.Association"%>

<liferay-portlet:renderURL varImpl="associationsURL">
	<portlet:param name="tab" value="associations" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteAssociation" var="deleteAssociationURL">
	<portlet:param name="cmd" value="deleteAssociation" />
	<portlet:param name="tab" value="associations" />
	<portlet:param name="associationId"
		value="${not empty dc.association ? dc.association.associationId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveAssociation" varImpl="saveAssociationURL">
	<portlet:param name="cmd" value="saveAssociation" />
	<portlet:param name="tab" value="associations" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:error key="name-error" message="title-error" />
	
	<aui:form action="${saveAssociationURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.association}" model="<%=Association.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="associationId" type="hidden" />

			<aui:fieldset collapsed="true" collapsible="true"
				label="general">

				<aui:input name="name">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>
				
				<aui:input name="presentation" />

                <div class="info-error" style="display: none">
                    <liferay-ui:message key="info-error" />
                </div>
					
				<aui:input name="phone" helpMessage="telephone-help" />

				<aui:input name="siteURL" >
				 	<aui:validator name="url"/>
				</aui:input>
				
				<aui:input name="mail" >
					<aui:validator name="email"/>
				</aui:input>

				<aui:input name="facebookURL" >
                    <aui:validator name="url"/>
                </aui:input>
				
			</aui:fieldset>

			<aui:fieldset collapsed="true" collapsible="true"
				label="activities">
                <!-- Hack pour ajouter une validation sur les vocabulaires obligatoires -->
                <div class="has-error">
                    <aui:input type="hidden" name="assetCategoriesValidatorInputHelper" value="placeholder">
                        <aui:validator name="custom" errorMessage="requested-vocabularies-error">
                            function (val, fieldNode, ruleValue) {
                                var validated = true;
                                var fields = document.querySelectorAll('div[id^=activity-] > .field-content');
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

					<div id="activity-fields">
						<c:if test="${empty dc.association.associationActivities}">
							<div class="lfr-form-row lfr-form-row-inline">
								<div class="row-fields">
									<liferay-util:include page="/includes/association-activity-row.jsp" servletContext="<%=application %>">
										<liferay-util:param name="index" value="1" />
									</liferay-util:include>
								</div>
							</div>
						</c:if>

						<c:forEach items="${dc.association.associationActivities}" var="activity" varStatus="status">
							<div class="lfr-form-row lfr-form-row-inline">
								<div class="row-fields">
									<liferay-util:include page="/includes/association-activity-row.jsp" servletContext="<%=application %>">
										<liferay-util:param name="index" value="${status.count}" />
										<liferay-util:param name="associationActivityId" value="${activity.associationActivityId}" />
										<liferay-util:param name="categoriesIds" value="${dc.getAssociationActivityCategoriesIds(activity.associationActivityId)}" />
									</liferay-util:include>
								</div>
							</div>
						</c:forEach>
						<c:if test="${empty dc.association.associationActivities}">
							<aui:input type="hidden" name="activityIndexes" value="1" />
						</c:if>
						<c:if test="${not empty dc.association.associationActivities}">
							<aui:input type="hidden" name="activityIndexes" value="${dc.getDefaultIndexes(fn:length(dc.association.associationActivities))}" />
						</c:if>
					</div>

			</aui:fieldset>

		</aui:fieldset-group>
		
		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_ASSOCIATION') and empty dc.association or dc.hasPermission('EDIT_ASSOCIATION') and not empty dc.association) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:input type="hidden" name="workflowAction" value="" />
				<aui:button cssClass="btn-lg" type="submit" name="publish"
					value="eu.publish" />
				<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft"
					value="save-as-draft" />
			</c:if>
			<c:if test="${not empty dc.association and dc.hasPermission('DELETE_ASSOCIATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>

<liferay-portlet:renderURL varImpl="activityRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/association-activity-row.jsp" />
</liferay-portlet:renderURL>
<liferay-util:html-top>
	<script>
		var getActivityRowURL = '${activityRowURL}';
	</script>
</liferay-util:html-top>
<liferay-util:html-bottom>
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<script>
		define.amd = define._amd;
	</script>
	<script
		src="/o/activitybo/js/activity-bo-edit-associations.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>

<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteAssociationURL}';
		}
	}
</aui:script>