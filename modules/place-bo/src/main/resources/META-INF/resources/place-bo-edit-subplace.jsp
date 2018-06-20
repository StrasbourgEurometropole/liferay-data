<%@ include file="/place-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.place.model.SubPlace"%>

<liferay-portlet:renderURL varImpl="subPlacesURL">
	<portlet:param name="tab" value="subPlaces" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteSubPlace"
	var="deleteSubPlaceURL">
	<portlet:param name="cmd" value="deleteSubPlace" />
	<portlet:param name="tab" value="subPlaces" />
	<portlet:param name="subPlaceId"
		value="${not empty dc.subPlace ? dc.subPlace.subPlaceId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveSubPlace"
	varImpl="saveSubPlaceURL">
	<portlet:param name="cmd" value="saveSubPlace" />
	<portlet:param name="tab" value="subPlaces" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveSubPlaceURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.subPlace}"
			model="<%=SubPlace.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="subPlaceId" type="hidden" />

			<!-- Informations gÃ©nÃ©rale -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="general-information">
				
				<aui:input name="name" label="name-subplace">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>
				
				<aui:input name="description" label="description-subplace" />
				<!-- Hack pour ajouter une validation sur la description -->
				<div class="has-error">
					<aui:input type="hidden" name="descriptionValidatorInputHelper" value="placeholder">
						<aui:validator name="custom" errorMessage="requested-description-error">
							function (val, fieldNode, ruleValue) {
								var validate = $('#_eu_strasbourg_portlet_place_PlaceBOPortlet_description_fr_FR').val().length > 0;
								if (!validate) {
									$("#_eu_strasbourg_portlet_place_PlaceBOPortlet_descriptionContainer").get(0).scrollIntoView();
								}
								return validate;
							}
						</aui:validator>
					</aui:input>
				</div>
					
				<strasbourg-picker:entity label="eu.strasbourg.service.place.model.Place-first"
					name="placeId" value="${dc.subPlace.placeId}"
					type="eu.strasbourg.service.place.model.Place"
					multiple="false" />

			</aui:fieldset>
				
			<!-- Horaires -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="horaire">
				
				<!-- PÃ©riodes & horaires -->
				<aui:fieldset collapsed="false" collapsible="true"
					label="period-time">
					
					<aui:input name="periodsIndexes" type="hidden" />
				
				    <div class="nav-tabs">
				        <ul class="nav nav-tabs" role="tablist">
							<c:set var="nbPeriod" value="0"/>
							<c:forEach items="${dc.subPlace.periods}" var="period" varStatus="status">
				            	<li role="presentation"
				            		<c:if test="${status.count == 1}">
				            			class="active"
				            		</c:if>
				            	 id="onglet${nbPeriod}" >
				            		<a aria-controls="period${nbPeriod}" href="#period${nbPeriod}" data-toggle="tab" role="tab">
					            		<liferay-ui:message key="period" /> ${status.count} 
					            	</a>
				            	</li>
								<c:set var="nbPeriod" value="${nbPeriod + 1}"/>
							</c:forEach>
				        </ul>
				    </div>
				
				    <div class="tab-content">
						<aui:input name="nbPeriod" type="hidden" value="${nbPeriod}" />
						<c:forEach items="${dc.subPlace.periods}" var="period" varStatus="status">
							<fmt:formatDate value="${period.startDate}" pattern="yyyy-MM-dd" type="date" var="formattedStartDate"/>
							<fmt:formatDate value="${period.endDate}" pattern="yyyy-MM-dd" type="date" var="formattedEndDate"/>
							<liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
								<liferay-util:param name="index" value="${status.count - 1}" />
								<liferay-util:param name="disabled" value="true" />
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
								<c:set var="slotComment" value="" />
								<c:forEach items="${period.getSlots(dc.subPlace.subPlaceId)}" var="slot">
									<c:if test="${not empty slotJour}">
										<c:set var="slotJour" value="${slotJour},${slot.dayOfWeek}" />
										<c:set var="slotStartHour" value="${slotStartHour},${slot.startHour}" />
										<c:set var="slotEndHour" value="${slotEndHour},${slot.endHour}" />
										<c:set var="slotComment" value="${slotComment} | ${slot.comment}" />
									</c:if>
									<c:if test="${empty slotJour}">
										<c:set var="slotJour" value="${slot.dayOfWeek}" />
										<c:set var="slotStartHour" value="${slot.startHour}" />
										<c:set var="slotEndHour" value="${slot.endHour}" />
										<c:set var="slotComment" value="${slot.comment}" />
									</c:if>
								</c:forEach>
								<liferay-util:param name="slotJours" value="${slotJour}" />
								<liferay-util:param name="slotStartHours" value="${slotStartHour}" />
								<liferay-util:param name="slotEndHours" value="${slotEndHour}" />
								<liferay-util:param name="slotComment" value="${slotComment}" />
							</liferay-util:include>
						</c:forEach>
						<div role="tabpanel" 
							<c:if test="${empty dc.subPlace.periods}">
								class="tab-pane active fade in"
							</c:if>
							<c:if test="${not empty dc.subPlace.periods}">
								class="tab-pane fade in"
							</c:if>
						id="noPeriod">
							<liferay-ui:message key="no-period" />
						</div>
				    </div>
					
				</aui:fieldset>
					
				<!-- Fermetures exceptionnelles -->
				<aui:fieldset collapsed="false" collapsible="true"
					label="exceptional-schedule">
				
					<div id="date-fields">
						<c:if test="${empty dc.subPlace.scheduleExceptions}">
							<div class="lfr-form-row lfr-form-row-inline">
								<div class="row-fields">
									<liferay-util:include page="/includes/exceptional-schedule-row.jsp" servletContext="<%=application %>">
										<liferay-util:param name="index" value="1" />
									</liferay-util:include>
								</div>
							</div>
						</c:if>
					
						<c:forEach items="${dc.subPlace.scheduleExceptions}" var="scheduleException" varStatus="status">
							<div class="lfr-form-row lfr-form-row-inline">
								<div class="row-fields">
									<fmt:formatDate value="${scheduleException.startDate}" pattern="yyyy-MM-dd" type="date" var="formattedStartDate"/>
									<fmt:formatDate value="${scheduleException.endDate}" pattern="yyyy-MM-dd" type="date" var="formattedEndDate"/>
									<liferay-util:include page="/includes/exceptional-schedule-row.jsp" servletContext="<%=application %>">
										<liferay-util:param name="index" value="${status.count}" />
										<liferay-util:param name="startHour1" value="${scheduleException.getStartHour(0)}" />
										<liferay-util:param name="endHour1" value="${scheduleException.getEndHour(0)}" />
										<liferay-util:param name="firstComment" value="${scheduleException.firstComment}" />
										<liferay-util:param name="startHour2" value="${scheduleException.getStartHour(1)}" />
										<liferay-util:param name="endHour2" value="${scheduleException.getEndHour(1)}" />
										<liferay-util:param name="secondComment" value="${scheduleException.secondComment}" />
										<liferay-util:param name="startHour3" value="${scheduleException.getStartHour(2)}" />
										<liferay-util:param name="endHour3" value="${scheduleException.getEndHour(2)}" />
										<liferay-util:param name="thirdComment" value="${scheduleException.thirdComment}" />
										<liferay-util:param name="startHour4" value="${scheduleException.getStartHour(3)}" />
										<liferay-util:param name="endHour4" value="${scheduleException.getEndHour(3)}" />
										<liferay-util:param name="fourthComment" value="${scheduleException.fourthComment}" />
										<liferay-util:param name="startHour5" value="${scheduleException.getStartHour(4)}" />
										<liferay-util:param name="endHour5" value="${scheduleException.getEndHour(4)}" />
										<liferay-util:param name="fifthComment" value="${scheduleException.fifthComment}" />
										<liferay-util:param name="comment" value="${scheduleException.comment}" />
										<liferay-util:param name="startDate" value="${formattedStartDate}" />
										<liferay-util:param name="endDate" value="${formattedEndDate}" />
										<liferay-util:param name="closed" value="${scheduleException.closed}" />
									</liferay-util:include>
								</div>
							</div>
						</c:forEach>
						<c:if test="${empty dc.subPlace.scheduleExceptions}">
							<aui:input type="hidden" name="shedulesExceptionsIndexes" value="1" />
						</c:if>
						<c:if test="${not empty dc.subPlace.scheduleExceptions}">
							<aui:input type="hidden" name="shedulesExceptionsIndexes" value="${dc.getDefaultIndexes(fn:length(dc.subPlace.scheduleExceptions))}" />
						</c:if>
					</div>
					
				</aui:fieldset>
					
			</aui:fieldset>

		</aui:fieldset-group>

		<aui:button-row>
			<aui:input type="hidden" name="workflowAction" value="" />
			<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" type="submit" name="publish"
					value="eu.publish" />
				<aui:button cssClass="btn-lg btn-default" type="submit"
					name="save-as-draft" value="save-as-draft" />
			</c:if>
			<c:if
				test="${not empty dc.subPlace and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deleteSubPlaceURL}" type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>

<liferay-util:html-top>
	<script>
	var getperiodRowJSPURL = '${periodRowURL}';
	var getslotRowJSPURL = '${slotRowURL}';
	var getattendanceRowJSPURL = '${attendanceRowURL}';
	
	var getscheduleExceptionRowJSPURL = '${scheduleExceptionRowURL}';
	</script>
</liferay-util:html-top>
<liferay-util:html-bottom>
	<script
		src="/o/placebo/js/place-bo-edit-subplace.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>