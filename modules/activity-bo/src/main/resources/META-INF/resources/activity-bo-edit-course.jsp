<%@ include file="/activity-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.activity.model.ActivityCourse"%>

<liferay-portlet:renderURL varImpl="activityCoursesURL">
	<portlet:param name="tab" value="activityCourses" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteActivityCourse" var="deleteActivityCourseURL">
	<portlet:param name="cmd" value="deleteActivityCourse" />
	<portlet:param name="tab" value="activityCourses" />
	<portlet:param name="activityCourseId"
		value="${not empty dc.activityCourse ? dc.activityCourse.activityCourseId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveActivityCourse" varImpl="saveActivityCourseURL">
	<portlet:param name="cmd" value="saveActivityCourse" />
	<portlet:param name="tab" value="activityCourses" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:error key="name-error" message="name-error" />
	<liferay-ui:error key="service-error" message="service-error" />
	<liferay-ui:error key="place-error" message="place-error" />
	
	<aui:form action="${saveActivityCourseURL}" method="post" name="fm" onSubmit="submitForm(event);">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.activityCourse}" model="<%=ActivityCourse.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="activityCourseId" type="hidden" />
	
			<!-- Section généralités -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="general">

				<!-- Nom -->
				<aui:input name="name">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>
				
				<!-- Présentation -->
				<aui:input name="presentation" />
				
				<!-- Catégories -->
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
				
				<!-- Activité -->
				<strasbourg-picker:entity type="eu.strasbourg.service.activity.model.Activity" label="activity" name="activityId" 
				value="${dc.activityCourse.activityId}" multiple="false" required="true" />
				<liferay-portlet:renderURL varImpl="activitiesURL">
					<portlet:param name="tab" value="activities" />
				</liferay-portlet:renderURL>
				<a href="${activitiesURL}" target="_blank"><liferay-ui:message key="manage-activities" /></a>
				 
				<!-- Modalités -->
				<aui:input name="arrangements" />
				
				<!-- Tarif -->
				<aui:input name="price" />
				
			</aui:fieldset>
				
			<!-- Médias -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="medias">
				
				<strasbourg-picker:image label="eu.place.main-image" name="imageId"
					required="false" value="${dc.activityCourse.imageId}" global="true" />
				
				<strasbourg-picker:image label="eu.place.additional-images" name="imageIds"
					required="false" value="${dc.activityCourse.imageIds}" multiple="true" global="true" />
					
				<strasbourg-picker:entity label="eu.place.videos" name="videosIds"
					value="${dc.activityCourse.videosIds}"
					type="eu.strasbourg.service.video.model.Video"
					multiple="true" global="true" />
					
				<strasbourg-picker:file label="eu.place.documents" name="documents"
					required="false" value="${dc.activityCourse.documentsIds}" multiple="true" global="true" />
				
			</aui:fieldset>
			
			<!-- Section catégorisation -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="activity-organizer">
				
				<!-- Organisateur -->	
				<div class="otherService">
					<strasbourg-picker:entity type="eu.strasbourg.service.activity.model.ActivityOrganizer" label="eu.activity.organizer" name="organizerId"
						value="${dc.activityCourse.organizerId}" multiple="false" required="true" />
						
					<liferay-portlet:renderURL varImpl="activityOrganizersURL">
						<portlet:param name="tab" value="activityOrganizers" />
					</liferay-portlet:renderURL>
					<a href="${activityOrganizersURL}" target="_blank"><liferay-ui:message key="manage-organizers" /></a>
				</div>

			</aui:fieldset>
			
			<!-- Section lieux et horaires -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="places-and-schedules">
					
				<div id="place-fields">
					<c:if test="${empty dc.activityCourse.activityCoursePlaces}">
						<div class="lfr-form-row lfr-form-row-inline main-content-card">
							<h3><liferay-ui:message key="place" /></h3>
							<div class="row-fields">
								<liferay-util:include page="/includes/course-place-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="0" />
								</liferay-util:include>
							</div>
						</div>
					</c:if>
					<c:forEach items="${dc.activityCourse.activityCoursePlaces}" var="coursePlace" varStatus="status">
						<c:set var="coursePlace" value="${coursePlace}" scope="request"/>
						<div class="lfr-form-row lfr-form-row-inline main-content-card">
							<h3><liferay-ui:message key="place" /></h3>
							<div class="row-fields">
								<liferay-util:include page="/includes/course-place-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="${status.index}" />
								</liferay-util:include>
							</div>
						</div>
					</c:forEach>
					<aui:input type="hidden" name="placeIndexes" value="${dc.defaultPlaceIndexes}" />
				</div>
			</aui:fieldset>

		</aui:fieldset-group>

		<aui:button-row>
			<c:if test="${(dc.hasPermission('ADD_ACTIVITY_COURSE') and empty dc.activityCourse or dc.hasPermission('EDIT_ACTIVITY_COURSE') and not empty dc.activityCourse) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:input type="hidden" name="workflowAction" value="" />
				<aui:button cssClass="btn-lg" type="submit" name="publish"
						value="eu.publish" />
				<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft"
						value="save-as-draft" />
			</c:if>
			<c:if test="${not empty dc.activityCourse and dc.hasPermission('DELETE_ACTIVITY_COURSE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>

<liferay-portlet:actionURL name="getBareboneJSP" var="placeRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/course-place-row.jsp" />
</liferay-portlet:actionURL>
<liferay-portlet:actionURL name="getBareboneJSP" var="placeScheduleRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/course-place-schedule-row.jsp" />
</liferay-portlet:actionURL>
<liferay-util:html-top>
	<script>
		var editCourse = true;
		var placeRowURL = '${placeRowURL}';
		var placeScheduleRowURL = '${placeScheduleRowURL}';
	</script>
</liferay-util:html-top>
<liferay-util:html-bottom>
	<aui:script>
		define._amd = define.amd;
		define.amd = false;
	</aui:script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script>
		define.amd = define._amd;
	</script>
	<script
		src="/o/activitybo/js/activity-bo-edit-activity.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteActivityCourseURL}';
		}
	}
</aui:script>