<%@page import="java.util.Date"%>
<%@ include file="/place-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.place.model.Place"%>

<liferay-portlet:renderURL varImpl="placesURL">
	<portlet:param name="tab" value="places" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deletePlace" var="deletePlaceURL">
	<portlet:param name="cmd" value="deletePlace" />
	<portlet:param name="tab" value="places" />
	<portlet:param name="placeId"
		value="${not empty dc.place ? dc.place.placeId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="savePlace" varImpl="savePlaceURL">
	<portlet:param name="cmd" value="savePlace" />
</liferay-portlet:actionURL>

<liferay-portlet:renderURL var="manageSubPlacesURL">
	<portlet:param name="tab" value="subPlaces" />
</liferay-portlet:renderURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${savePlaceURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.place}" model="<%=Place.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="placeId" type="hidden" />

			<!-- Informations gÃÂ©ographique -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="geographic-information">
				
					<div class="col-md-6">
						
						<aui:input name="SIGid2" type="hidden" value="${dc.place.SIGid}" />
						
						<aui:input name="SIGid" disabled="true" />
						
						<aui:input name="name2" type="hidden" value="${dc.place.name}" />
					
						<aui:input name="name" disabled="true" />
						
						<aui:input name="addressStreet2" type="hidden" value="${dc.place.addressStreet}" />
						
						<aui:input name="addressStreet" disabled="true" />
						
						<aui:input name="addressComplement2" type="hidden" value="${dc.place.addressComplement}" />
						
						<aui:input name="addressComplement" disabled="true" />
						
						<aui:input name="addressDistribution2" type="hidden" value="${dc.place.addressDistribution}" />
						
						<aui:input name="addressDistribution" disabled="true" />
						
						<aui:input name="addressZipCode2" type="hidden" value="${dc.place.addressZipCode}" />
						
						<aui:input name="addressZipCode" disabled="true" />
						
						<aui:input name="addressCountry2" type="hidden" value="${dc.place.addressCountry}" />
						
						<aui:input name="addressCountry" disabled="true" />
						
					</div>
					
					<div class="col-md-6">
					
						<label><liferay-ui:message key="mercator-coordinates" /></label><br>
						
						<aui:input name="mercatorX2" type="hidden" value="${dc.place.mercatorX}" />
						
						<aui:input name="mercatorX" disabled="true" />
						
						<aui:input name="mercatorY2" type="hidden" value="${dc.place.mercatorY}" />
						
						<aui:input name="mercatorY" disabled="true" />
						
						<label><liferay-ui:message key="rgf93" /></label><br>
						
						<aui:input name="RGF93X2" type="hidden" value="${dc.place.RGF93X}" />
						
						<aui:input name="RGF93X" disabled="true" />
						
						<aui:input name="RGF93Y2" type="hidden" value="${dc.place.RGF93Y}" />
						
						<aui:input name="RGF93Y" disabled="true" />
						
					</div>
				
			</aui:fieldset>
			
			<!-- Categorisation -->
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
				
			<!-- Description -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="description">
						
				<aui:input name="alias">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>
				
				<aui:input name="presentation" />
				<!-- Hack pour ajouter une validation sur la description -->
				<div class="has-error">
					<aui:input type="hidden" name="presentationValidatorInputHelper" value="placeholder">
						<aui:validator name="custom" errorMessage="requested-presentation-error">
							function (val, fieldNode, ruleValue) {
								var validate = $('#_eu_strasbourg_portlet_place_PlaceBOPortlet_presentation_fr_FR').val().length > 0;
								if (!validate) {
									$("#_eu_strasbourg_portlet_place_PlaceBOPortlet_presentationContainer").get(0).scrollIntoView();
								}
								return validate;
							}
						</aui:validator>
					</aui:input>
				</div>
				
				<aui:input name="serviceAndActivities"   />
				<!-- Hack pour ajouter une validation sur les services et activitÃÂ©s -->
				<div class="has-error">
					<aui:input type="hidden" name="serviceAndActivitiesValidatorInputHelper" value="placeholder" />
				</div>
				
				<aui:input name="characteristics" helpMessage="characteristics-help" />
				<!-- Hack pour ajouter une validation sur les caractéristiques -->
				<div class="has-error">
					<aui:input type="hidden" name="characteristicsValidatorInputHelper" value="placeholder" />
				</div>
				
			</aui:fieldset>
				
			<!-- Médias -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="media">
				
				<strasbourg-picker:image label="eu.place.main-image" name="imageId"
					required="false" value="${dc.place.imageId}" global="true" />
				
				<strasbourg-picker:image label="eu.place.additional-images" name="imageIds"
					required="false" value="${dc.place.imageIds}" multiple="true" global="true" />
					
				<strasbourg-picker:entity label="eu.place.videos" name="videosIds"
					value="${dc.place.videosIds}"
					type="eu.strasbourg.service.video.model.Video"
					multiple="true" global="true" />
					
				<strasbourg-picker:file label="eu.place.documents" name="documents"
					required="false" value="${dc.place.documentsIds}" multiple="true" global="true" />
				
			</aui:fieldset>
				
			<!-- Contact -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="contact">
					
						<aui:input name="phone" helpMessage="telephone-help" />
						
						<aui:input name="mail" helpMessage="email-help" >
							<aui:validator name="email"/>
						</aui:input>
						
						<div id="site">
							<aui:input name="siteLabel" >
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							
							<aui:input name="siteURL" >
							 	<aui:validator name="url"/>
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
						</div>
						
						<aui:input name="facebookLabel" >
					        <aui:validator name="require" errorMessage="this-field-is-required" />
						</aui:input>
						
						<aui:input name="facebookURL">
							<aui:validator name="url"/>
							<aui:validator name="require" errorMessage="this-field-is-required" />
						</aui:input>		

			</aui:fieldset>
				
			<!-- Accés -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="acces">
				
				<aui:input name="access" label="access-mod" helpMessage="access-mod-help" />
				<!-- Hack pour ajouter une validation sur le mode d'accÃ¨s -->
				<div class="has-error">
					<aui:input type="hidden" name="accessValidatorInputHelper" value="placeholder"/>
				</div>		
				
				<strasbourg-picker:file label="eu.place.access-map" name="accesMap"
					required="false" value="${dc.place.accesMap}" localized="true" multiple="false" global="true" />
				
				<aui:input name="accessForDisabled"  />
				<!-- Hack pour ajouter une validation sur le service aux personnes handicapÃÂ©es -->
				<div class="has-error">
					<aui:input type="hidden" name="accessForDisabledValidatorInputHelper" value="placeholder"/>
				</div>	
						
				<div class="checkbox">
					<div style="position:relative"><aui:input name="accessForBlind" type="checkbox" value="${dc.place.accessForBlind}" helpMessage="acces-for-disabled-help" /></div>
					<div style="position:relative"><aui:input name="accessForDeaf" type="checkbox" value="${dc.place.accessForDeaf}" helpMessage="acces-for-disabled-help" /></div>
					<div style="position:relative"><aui:input name="accessForWheelchair" type="checkbox" value="${dc.place.accessForWheelchair}" helpMessage="acces-for-disabled-help" /></div>
					<div style="position:relative"><aui:input name="accessForElder" type="checkbox" value="${dc.place.accessForElder}" helpMessage="acces-for-disabled-help" /></div>
					<div style="position:relative"><aui:input name="accessForDeficient" type="checkbox" value="${dc.place.accessForDeficient}" helpMessage="acces-for-disabled-help" /></div>
				</div>
				
			</aui:fieldset>
				
			<!-- Horaires -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="horaire">
				
				<!-- Périodes & horaires -->
				<aui:fieldset collapsed="false" collapsible="true"
					label="period-time">
				
					<div id="date-fields2">
						<c:if test="${empty dc.place.periods}">
							<div class="lfr-form-row lfr-form-row-inline period" id="1">
								<div class="row-fields">
									<liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
										<liferay-util:param name="index" value="1" />
									</liferay-util:include>
								</div>
							</div>
						</c:if>
					
						<c:forEach items="${dc.place.periods}" var="period" varStatus="status">
							<div class="lfr-form-row lfr-form-row-inline period" id="${status.count}">
								<div class="row-fields">
									<fmt:formatDate value="${period.startDate}" pattern="yyyy-MM-dd" type="date" var="formattedStartDate"/>
									<fmt:formatDate value="${period.endDate}" pattern="yyyy-MM-dd" type="date" var="formattedEndDate"/>
									<liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
										<liferay-util:param name="index" value="${status.count}" />
										<liferay-util:param name="name" value="${period.name}" />
										<liferay-util:param name="defaultPeriod" value="${period.defaultPeriod}" />
										<liferay-util:param name="startDate" value="${formattedStartDate}" />
										<liferay-util:param name="endDate" value="${formattedEndDate}" />
										<liferay-util:param name="linkLabel" value="${period.linkLabel}" />
										<liferay-util:param name="linkURL" value="${period.linkURL}" />
										<liferay-util:param name="alwaysOpen" value="${period.alwaysOpen}" />
										<liferay-util:param name="periodId" value="${period.periodId}" />
										<liferay-util:param name="nbSlot" value="${fn:length(period.slots)}" />
										<c:set var="slotJour" value="" />
										<c:set var="slotStartHour" value="" />
										<c:set var="slotEndHour" value="" />
										<c:forEach items="${period.slots}" var="slot">
											<c:if test="${not empty slotJour}">
												<c:set var="slotJour" value="${slotJour},${slot.dayOfWeek}" />
												<c:set var="slotStartHour" value="${slotStartHour},${slot.startHour}" />
												<c:set var="slotEndHour" value="${slotEndHour},${slot.endHour}" />
											</c:if>
											<c:if test="${empty slotJour}">
												<c:set var="slotJour" value="${slot.dayOfWeek}" />
												<c:set var="slotStartHour" value="${slot.startHour}" />
												<c:set var="slotEndHour" value="${slot.endHour}" />
											</c:if>
										</c:forEach>
										<liferay-util:param name="slotJours" value="${slotJour}" />
										<liferay-util:param name="slotStartHours" value="${slotStartHour}" />
										<liferay-util:param name="slotEndHours" value="${slotEndHour}" />
									</liferay-util:include>
								</div>
							</div>
						</c:forEach>
						<c:if test="${empty dc.place.periods}">
							<aui:input type="hidden" name="periodsIndexes" value="1" />
						</c:if>
						<c:if test="${not empty dc.place.periods}">
							<aui:input type="hidden" name="periodsIndexes" value="${dc.getDefaultIndexes(fn:length(dc.place.periods))}" />
						</c:if>
					</div>
					
				</aui:fieldset>
					
				<!-- Fermetures exceptionnelles -->
				<aui:fieldset collapsed="false" collapsible="true"
					label="exceptional-schedule">
				
					<div id="date-fields">
						<c:if test="${empty dc.place.scheduleExceptions}">
							<div class="lfr-form-row lfr-form-row-inline">
								<div class="row-fields">
									<liferay-util:include page="/includes/exceptional-schedule-row.jsp" servletContext="<%=application %>">
										<liferay-util:param name="index" value="1" />
									</liferay-util:include>
								</div>
							</div>
						</c:if>
					
						<c:forEach items="${dc.place.scheduleExceptions}" var="scheduleException" varStatus="status">
							<div class="lfr-form-row lfr-form-row-inline">
								<div class="row-fields">
									<fmt:formatDate value="${scheduleException.startDate}" pattern="yyyy-MM-dd" type="date" var="formattedStartDate"/>
									<fmt:formatDate value="${scheduleException.endDate}" pattern="yyyy-MM-dd" type="date" var="formattedEndDate"/>
									<liferay-util:include page="/includes/exceptional-schedule-row.jsp" servletContext="<%=application %>">
										<liferay-util:param name="index" value="${status.count}" />
										<liferay-util:param name="startHour" value="${scheduleException.startHour}" />
										<liferay-util:param name="endHour" value="${scheduleException.endHour}" />
										<liferay-util:param name="comment" value="${scheduleException.comment}" />
										<liferay-util:param name="startDate" value="${formattedStartDate}" />
										<liferay-util:param name="endDate" value="${formattedEndDate}" />
										<liferay-util:param name="closed" value="${scheduleException.closed}" />
									</liferay-util:include>
								</div>
							</div>
						</c:forEach>
						<c:if test="${empty dc.place.scheduleExceptions}">
							<aui:input type="hidden" name="shedulesExceptionsIndexes" value="1" />
						</c:if>
						<c:if test="${not empty dc.place.scheduleExceptions}">
							<aui:input type="hidden" name="shedulesExceptionsIndexes" value="${dc.getDefaultIndexes(fn:length(dc.place.scheduleExceptions))}" />
						</c:if>
					</div>
					
				</aui:fieldset>
				
				<!-- Horaires particuliers -->
				<div class="schedule-exception">
					<aui:input name="exceptionalSchedule" label="exceptionals-schedules" />
					<!-- Hack pour ajouter une validation sur les horaires particuliers -->
					<div class="has-error">
						<aui:input type="hidden" name="exceptionalScheduleValidatorInputHelper" value="placeholder"/>
					</div>
				</div>	
					
			</aui:fieldset>
				
			<!-- Informations complémentaires -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="add-information">
				
				<aui:input name="displayEvents" type="toggle-switch" value="${not empty dc.place ? dc.place.displayEvents : false}" />
				
				<aui:input name="additionalInformation" label="required-additionalInformation"  />
				<!-- Hack pour ajouter une validation sur les inforrmations complémentaires -->
				<div class="has-error">
					<aui:input type="hidden" name="additionalInformationValidatorInputHelper" value="placeholder"/>
				</div>	
				
			</aui:fieldset>
				
			<!-- Fréquentation temps réel -->
			
			<c:if test="${dc.place.isEnabled()}">
				<aui:fieldset collapsed="false" collapsible="true"
					label="attendance" >
					<aui:input name="RTExternalId" />
					
					<c:if test="${empty dc.place.periods}">
						<div class="lfr-form-row lfr-form-row-inline" id="attendance1">
							<div class="row-fields">
								<div class="attendance-label" id="1" onCLick="$('.attendance-content1').toggle();">
									<label><liferay-ui:message key="period" /> 1</label>
								</div>
								<div class="attendance-content1" >
									<aui:input type="text" name="RTGreenThreshold1" label="rtgreen-threshold"  />
									<aui:input type="text" name="RTOrangeThreshold1" label="rtorange-threshold"  />
									<aui:input type="text" name="RTRedThreshold1" label="rtred-threshold"  />
									<aui:input type="text" name="RTMaxThreshold1" label="rtmax-threshold"  />
								</div>
							</div>
						</div>
					</c:if>
							
					<c:forEach items="${dc.place.periods}" var="period" varStatus="status">
						<div class="lfr-form-row lfr-form-row-inline" id="attendance${status.count}">
							<div class="row-fields">
								<div class="attendance-label" id="${status.count}" onCLick="$('.attendance-content' + ${status.count}).toggle();">
									<label><liferay-ui:message key="period" /> ${status.count} : ${period.nameCurrentValue}</label>
								</div>
								<div class="attendance-content${status.count}" >
									<aui:input type="text" name="RTGreenThreshold${status.count}" label="rtgreen-threshold" value="${period.RTGreenThreshold}" />
									<aui:input type="text" name="RTOrangeThreshold${status.count}" label="rtorange-threshold" value="${period.RTOrangeThreshold}" />
									<aui:input type="text" name="RTRedThreshold${status.count}" label="rtred-threshold" value="${period.RTRedThreshold}" />
									<aui:input type="text" name="RTMaxThreshold${status.count}" label="rtmax-threshold" value="${period.RTMaxThreshold}" />
								</div>
							</div>
						</div>
					</c:forEach>
				</aui:fieldset>
			</c:if>
				
			<!-- Sous lieux -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="sub-places">
				
				<ul class="full-borders tabular-list-group">
				
					<li class="list-group-item2">
						<div class="list-group-item-content">
							<h2><liferay-ui:message key="sub-place" /></h2>
						</div>
						<div class="list-group-item-content">
							<h2><liferay-ui:message key="delete" /></h2>
						</div>
					</li>
					<c:forEach var="subPlace" items="${dc.place.subPlaces}">
						<li class="list-group-item2">
							<div class="list-group-item-content">
								<liferay-portlet:renderURL varImpl="editSubPlaceURL">
									<portlet:param name="cmd" value="editSubPlace" />
									<portlet:param name="tab" value="subPlaces" />
									<portlet:param name="subPlaceId" value="${subPlace.subPlaceId}" />
									<portlet:param name="mvcPath" value="/place-bo-edit-subplace.jsp" />
								</liferay-portlet:renderURL>
								<a href="${editSubPlaceURL}" target="_blank">${subPlace.name}</a>
							</div>
							<div class="list-group-item-content">
								<aui:input name="suppression" label="none" type="checkbox" value="${subPlace.subPlaceId}" helpMessage="delete-help" />
							</div>
						</li>
					</c:forEach>
					
				</ul>	

				<aui:button cssClass="btn-lg" href="${manageSubPlacesURL}"
					type="button" value="manage-sub-places" target="_blank" />
					
			</aui:fieldset>

		</aui:fieldset-group>

		<aui:button-row>
			<aui:input type="hidden" name="workflowAction" value="" />
			<c:if test="${(dc.hasPermission('ADD_PLACE') and empty dc.place or dc.hasPermission('EDIT_PLACE') and not empty dc.place) and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
			<c:if test="${not empty dc.place && dc.hasPermission('DELETE_PLACE') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deletePlaceURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>

<liferay-util:html-top>
	<script>
		var getscheduleExceptionRowJSPURL = '${scheduleExceptionRowURL}';
		var getperiodRowJSPURL = '${periodRowURL}';
	</script>
</liferay-util:html-top>
<liferay-util:html-bottom>
	<script
		src="/o/placebo/js/place-bo-edit-place.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>
