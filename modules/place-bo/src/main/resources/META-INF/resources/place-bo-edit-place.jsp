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
				
				<aui:input name="serviceAndActivities"   />
				<!-- Hack pour ajouter une validation sur les services et activitÃÂ©s -->
				<div class="has-error">
					<aui:input type="hidden" name="serviceAndActivitiesValidatorInputHelper" value="placeholder" />
				</div>
				
				<aui:input name="characteristics" helpMessage="characteristics-help" />
				<!-- Hack pour ajouter une validation sur les caractÃÂ©ristiques -->
				<div class="has-error">
					<aui:input type="hidden" name="characteristicsValidatorInputHelper" value="placeholder" />
				</div>

				<aui:input name="contenuTooltipCarto" />
				
			</aui:fieldset>
				
			<!-- MÃÂ©dias -->
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
							
							<aui:input name="siteURL" helpMessage="url-help-message">
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
				
			<!-- AccÃÂ©s -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="acces">
				
				<aui:input name="access" label="access-mod" helpMessage="access-mod-help" />
				<!-- Hack pour ajouter une validation sur le mode d'accÃÂ¨s -->
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
				
				<aui:input name="subjectPublicHolidays" label="subject-public-holidays" type="toggle-switch" 
					value="${not empty dc.place ? dc.place.subjectToPublicHoliday : true}" />

				<aui:input name="hasURLSchedule" label="has-url-schedule" type="toggle-switch"
					value="${not empty dc.place ? dc.place.hasURLSchedule : true}" />

				<div class="URLSchedule">
                    <aui:input name="scheduleLinkName" label="period-label" value="${dc.place.scheduleLinkName}" localized="true" type="text" >
                        <aui:validator name="require" errorMessage="this-field-is-required" />
                    </aui:input>
                    <div class="place-period-label" style="display: none">
                        <liferay-ui:message key="this-field-is-required" />
                    </div>

                    <aui:input name="scheduleLinkURL" label="period-url" value="${dc.place.scheduleLinkURL}" localized="true" type="text" >
                        <aui:validator name="url"/>
                        <aui:validator name="require" errorMessage="this-field-is-required" />
                    </aui:input>
                    <div class="place-period-url" style="display: none">
                        <liferay-ui:message key="this-field-is-required" />
                    </div>
                </div>


				<div class="PeriodTime">
                    <!-- PÃÂ©riodes & horaires -->
                    <aui:fieldset collapsed="false" collapsible="true"
                        label="period-time">

                        <aui:input name="periodsIndexes" type="hidden" />

                        <div class="nav-tabs">
                            <ul class="nav nav-tabs" role="tablist">
                                <c:set var="nbPeriod" value="0"/>
                                <c:forEach items="${dc.place.periods}" var="period" varStatus="status">
                                    <li role="presentation"
                                        <c:if test="${status.count == 1}">
                                            class="active"
                                        </c:if>
                                     id="onglet${nbPeriod}" >
                                        <a aria-controls="period${nbPeriod}" href="#period${nbPeriod}" data-toggle="tab" role="tab">
                                            <liferay-ui:message key="period" /> ${status.count}
                                            <span class="btn-icon icon icon-trash" onClick="deletePeriod(${nbPeriod}); return false;"></span>
                                        </a>
                                    </li>
                                    <c:set var="nbPeriod" value="${nbPeriod + 1}"/>
                                </c:forEach>
                                <li role="presentation"
                                    <c:if test="${empty dc.place.periods}">
                                        class="active"
                                    </c:if>
                                 id="addPeriod" >
                                    <a aria-controls="add" onClick="addPeriod(); return false;" data-toggle="tab" role="tab" aria-expanded="true"><span class="btn-icon icon icon-plus"></span></a>
                                </li>
                            </ul>
                        </div>

                        <div class="tab-content">
                            <aui:input name="nbPeriod" type="hidden" value="${nbPeriod}" />
                            <c:forEach items="${dc.place.periods}" var="period" varStatus="status">
                                <fmt:formatDate value="${period.startDate}" pattern="yyyy-MM-dd" type="date" var="formattedStartDate"/>
                                <fmt:formatDate value="${period.endDate}" pattern="yyyy-MM-dd" type="date" var="formattedEndDate"/>
                                <liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="${status.count - 1}" />
                                    <liferay-util:param name="disabled" value="false" />
                                    <liferay-util:param name="name" value="${period.name}" />
                                    <liferay-util:param name="defaultPeriod" value="${period.defaultPeriod}" />
                                    <liferay-util:param name="startDate" value="${formattedStartDate}" />
                                    <liferay-util:param name="endDate" value="${formattedEndDate}" />
                                    <liferay-util:param name="alwaysOpen" value="${period.alwaysOpen}" />
                                    <liferay-util:param name="periodId" value="${period.periodId}" />
                                    <liferay-util:param name="nbSlot" value="${fn:length(period.slots)}" />
                                    <c:set var="slotJour" value="" />
                                    <c:set var="slotStartHour" value="" />
                                    <c:set var="slotEndHour" value="" />
                                    <c:set var="slotComment" value="" />
                                    <c:forEach items="${period.slots}" var="slot">
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
                                <c:if test="${empty dc.place.periods}">
                                    class="tab-pane active fade in"
                                </c:if>
                                <c:if test="${not empty dc.place.periods}">
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
                            <c:if test="${empty dc.place.scheduleExceptions}">
                                <aui:input type="hidden" name="shedulesExceptionsIndexes" value="1" />
                            </c:if>
                            <c:if test="${not empty dc.place.scheduleExceptions}">
                                <aui:input type="hidden" name="shedulesExceptionsIndexes" value="${dc.getDefaultIndexes(fn:length(dc.place.scheduleExceptions))}" />
                            </c:if>
                        </div>

                    </aui:fieldset>
                </div>
				
				<!-- Horaires particuliers -->
				<div class="schedule-exception">
					<aui:input name="exceptionalSchedule" label="exceptionals-schedules" />
					<!-- Hack pour ajouter une validation sur les horaires particuliers -->
					<div class="has-error">
						<aui:input type="hidden" name="exceptionalScheduleValidatorInputHelper" value="placeholder"/>
					</div>
				</div>	
					
			</aui:fieldset>
				
			<!-- Informations complÃÂ©mentaires -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="add-information">
				
				<aui:input name="displayEvents" type="toggle-switch" value="${not empty dc.place ? dc.place.displayEvents : true}" />
				
				<aui:input name="additionalInformation" label="required-additionalInformation"  />
				<!-- Hack pour ajouter une validation sur les inforrmations complÃÂ©mentaires -->
				<div class="has-error">
					<aui:input type="hidden" name="additionalInformationValidatorInputHelper" value="placeholder"/>
				</div>	
				
			</aui:fieldset>
				
			<!-- FrÃÂ©quentation temps rÃÂ©el -->
			
			<c:if test="${dc.place.isEnabled()}">
				<aui:fieldset collapsed="false" collapsible="true"
					label="attendance" >
					<aui:input name="RTExternalId" />
							
					<c:forEach items="${dc.place.periods}" var="period" varStatus="status">
						<liferay-util:include page="/includes/attendance-row.jsp" servletContext="<%=application %>">
							<liferay-util:param name="indexPeriod" value="${status.count -1}" />
							<liferay-util:param name="name" value="${period.nameCurrentValue}" />
							<liferay-util:param name="green" value="${period.RTGreenThreshold}" />
							<liferay-util:param name="orange" value="${period.RTOrangeThreshold}" />
							<liferay-util:param name="red" value="${period.RTRedThreshold}" />
							<liferay-util:param name="max" value="${period.RTMaxThreshold}" />
						</liferay-util:include>
					</c:forEach>
				</aui:fieldset>
			</c:if>
				
			<!-- Sous lieux -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="sub-places">
				
				<c:if test="${not empty dc.place.subPlaces}">
					<div id="subPlaces">
					
						<table border="1">
							<tr>
								<th>
									<strong><liferay-ui:message key="sub-place" /></strong>
								</th>
								<th >
									<strong><liferay-ui:message key="delete" /></strong>
								</th>
							</tr>
							<c:forEach var="subPlace" items="${dc.place.subPlaces}">
								<tr>
									<td >
										<liferay-portlet:renderURL varImpl="editSubPlaceURL">
											<portlet:param name="cmd" value="editSubPlace" />
											<portlet:param name="tab" value="subPlaces" />
											<portlet:param name="subPlaceId" value="${subPlace.subPlaceId}" />
											<portlet:param name="mvcPath" value="/place-bo-edit-subplace.jsp" />
										</liferay-portlet:renderURL>
										<a href="${editSubPlaceURL}" target="_blank">${subPlace.name}</a>
									</td>
									<td >
										<aui:input name="suppression" label="none" type="checkbox" value="${subPlace.subPlaceId}" helpMessage="delete-help" />
									</td>
								</tr>
							</c:forEach>
						</table>
						
					</div>
				</c:if>

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
	var getperiodRowJSPURL = '${periodRowURL}';
	var getslotRowJSPURL = '${slotRowURL}';
	var getattendanceRowJSPURL = '${attendanceRowURL}';
	var getscheduleExceptionRowJSPURL = '${scheduleExceptionRowURL}';
	</script>
</liferay-util:html-top>
<liferay-util:html-bottom>
	<script
		src="/o/placebo/js/place-bo-edit-place.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>
