<%@ include file="/campaign-init.jsp"%>
<%@page import="eu.strasbourg.service.agenda.model.CampaignEvent"%>


<liferay-portlet:renderURL varImpl="eventsCampaignURL">
</liferay-portlet:renderURL>
<liferay-portlet:actionURL name="saveCampaignEvent"
	varImpl="saveURL" />
<liferay-portlet:actionURL name="deleteCampaignEvent"
	varImpl="deleteURL">
	<liferay-portlet:param name="campaignEventId" value="${dc.campaignEvent.campaignEventId}" />
</liferay-portlet:actionURL>	

<div class="container-fluid-1280 main-content-body">
	<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" value="back-to-list" />
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="periods-error" message="periods-error" />
	<liferay-ui:error key="types-error" message="types-error" />
	<liferay-ui:error key="themes-error" message="themes-error" />
	<liferay-ui:error key="campaign-period-error" message="campaign-period-error" />
	<aui:form action="${saveURL}" method="post" name="fm" enctype="multipart/form-data" onSubmit="validatePeriods(event);" >
		<aui:model-context bean="${dc.campaignEvent}"
			model="<%=CampaignEvent.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="campaignEventId" type="hidden" />
			<aui:input name="newStatus" type="hidden" value="" />
			
			<!-- Informations de contact privÃ©es-->
			<aui:fieldset collapsed="false" collapsible="false" label="private-contact-information">
				<div clas="row">
					<!-- Nom, prÃ©nom, mail, tÃ©lÃ©phone -->
					<div class="col-md-6">
						<aui:input name="lastName" required="true" value="${not empty dc.campaignEvent ? dc.campaignEvent.lastName : themeDisplay.user.lastName}"/>
						<aui:input name="firstName" required="true" value="${not empty dc.campaignEvent ? dc.campaignEvent.firstName: themeDisplay.user.firstName}" />
						<aui:input name="phone" required="true" value="${not empty dc.campaignEvent ? dc.campaignEvent.phone : themeDisplay.user.phones[0].number}" />
						<aui:input name="email" required="true"  value="${not empty dc.campaignEvent ? dc.campaignEvent.email : themeDisplay.user.emailAddress}" />
						
						
						<aui:fieldset collapsed="false" collapsible="false" label="organization-service">
							<!-- Service : service EMS ou autre -->
							<div class="service-type-selection">
								<label><input type="radio" value="emsService" name="serviceType" 
								<c:if test="${not empty dc.campaignEvent.serviceId and dc.campaignEvent.serviceId gt 0}">checked</c:if>> <liferay-ui:message key="ems-service" /></label><br>
								<label><input type="radio" value="otherService" name="serviceType"
									<c:if test="${not empty dc.campaignEvent.service}">checked</c:if>> <liferay-ui:message key="other-service" /></label><br><br>
									
								<div class="emsService" <c:if test="${empty dc.campaignEvent.serviceId or dc.campaignEvent.serviceId eq 0}">style="display: none;"</c:if>>
									<aui:select name="serviceId" label="ems-service">
										<aui:option value="" label="select-service" />
										<c:forEach var="service" items="${dc.EMSServices}">
											<aui:option value="${service.categoryId}"
												label="${service.getTitle(locale)}"
												selected="${service.categoryId eq dc.campaignEvent.serviceId}" />
										</c:forEach>
									</aui:select>
								</div>
								
								<div class="otherService" <c:if test="${empty dc.campaignEvent.service}">style="display: none;"</c:if>>
									<aui:input name="service" />
								</div>
							</div>
						</aui:fieldset>
					</div>
					
					<div class="col-md-5 col-md-offset-1">
						<aui:fieldset collapsed="false" collapsible="false" label="on-site-person">
							<!-- Personne sur place -->
							<aui:input name="onSiteFirstName" />
							<aui:input name="onSiteLastName" />
							<aui:input name="onSitePhone" />
						</aui:fieldset>
					</div>
				</div>
			</aui:fieldset>
			
			<!-- Informations sur l'Ã©vÃ©nement -->
			<aui:fieldset collapsed="true" collapsible="false" label="event-information">
				<!-- Titre, sous-titre, description -->
				<div class="row">
					<div class="col-md-6">
						<aui:input name="title" required="true" />
						<aui:input name="subtitle" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<aui:input name="description" label="eu.campaign.description" localized="true" type="textarea" helpMessage="description-help" />
						<!-- Hack pour ajouter une validation sur la description -->
						<div class="has-error">
							<aui:input type="hidden" name="descriptionValidatorInputHelper" value="placeholder">
								<aui:validator name="custom" errorMessage="this-field-is-required">
									function (val, fieldNode, ruleValue) {
										var validate = $('#<portlet:namespace />description_fr_FR').val().length > 0;
										if (!validate) {
											$("#<portlet:namespace />descriptionContainer").get(0).scrollIntoView();
										}
										return validate;
									}
								</aui:validator>
							</aui:input>							
						</div>
					</div>
				</div>
				
				<!-- Manifestations -->
				<div class="row">
					<div class="col-md-6">
						<label><liferay-ui:message key="manifestations" /></label>
						<select class="form-control" name="<portlet:namespace />manifestations"
							id="manifestations"
							placeholder="<liferay-ui:message key="select-manifestations" />" multiple>
							<c:forEach var="manifestation" items="${dc.manifestations}">
								<option value="${manifestation.idSource}" 
									<c:if test="${not empty dc.campaignEvent 
													and not empty dc.campaignEvent.manifestationsIds 
													and fn:contains(dc.campaignEvent.manifestationsIds, manifestation.idSource)}">
										selected
									</c:if>
								>
									${manifestation.getTitle(locale)}
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</aui:fieldset>
			
			<!-- Images -->
			<aui:fieldset collapsed="true" collapsible="false" label="images">		
				
				<div class="row">
					<div class="col-md-6">
						<!-- Image -->
						<aui:input type="file" name="image">
							<aui:validator name="acceptFiles">'jpg,png'</aui:validator>
						</aui:input>
						<c:if test="${not empty dc.campaignEvent.imageId}">
							<p><img src="${dc.campaignEvent.imageURL}"></p>
						</c:if>
						
						<!-- Auteur de l'image -->
						<aui:input name="imageOwner">
							<aui:validator name="required"
								errorMessage="this-field-is-required">
								function() {
									return $('[name$=_image]').val().length > 0;
								}	
							</aui:validator>
						</aui:input>
						
						<!-- Image pour le web -->
						<c:if test="${dc.campaignEvent.isUserManagerOfTheEvent(themeDisplay.userId)}">
							<aui:input type="file" name="webImage">
								<aui:validator name="acceptFiles">'jpg,png'</aui:validator>
							</aui:input>
							<c:if test="${not empty dc.campaignEvent.webImageId}">
								<p><img src="${dc.campaignEvent.webImageURL}" name="webImageUrl" ></p>
							</c:if>
							<!-- Permet de récupérer l'id de l'image posté par l'utilisateur -->
							<aui:input type="hidden" name="webImageId" />
							<aui:button cssClass="btn-image" type="button" name="use-same-picture" value="use-same-picture"/>

						</c:if>
					</div>
				</div>
				
				
			</aui:fieldset>
				
			<!-- Lieu -->
			<aui:fieldset collapsed="true" collapsible="false" label="place">	
				
				<!-- Autocomplete lieu -->
				<div class="place-autocomplete" <c:if test="${empty dc.campaignEvent.placeSIGId and not empty dc.campaignEvent.placeName }">style="display: none;"</c:if>>
					<div class="row">
						<div class="col-md-6">
							<div class="place-autocomplete-input-wrapper">
								<aui:input label="Choisir un lieu" type="text" name="place" value="" />
							</div>
							<span id="place-autocomplete-hidden-value">
								<aui:input type="hidden" name="placeSIGId" value="${empty dc.campaignEvent ? param.placeSIGId : dc.campaignEvent.placeSIGId}" />
							</span>
							<aui:input label="Lieu choisi" type="text" value="${empty dc.campaignEvent ? param.selectedPlace : dc.campaignEvent.getPlaceAlias(locale)}" name="selectedPlace" disabled="true" cssClass="selected-place" >
								<aui:validator name="required"
									errorMessage="this-field-is-required">
									function() {
										return jQuery('.place-autocomplete').css('display') !== 'none';
									}	
								</aui:validator>
							</aui:input>
						</div>
					</div>
					<aui:button id="showManualPlace" cssClass="show-manual-place" name="changeTimes" value="show-manual-place" />
				</div>
				
				<!-- Saisie manuelle -->
				<div class="place-manual" <c:if test="${not empty dc.campaignEvent.placeSIGId or empty dc.campaignEvent.placeName }">style="display: none;"</c:if>>
					<div class="row">
						<div class="col-md-4">
							<aui:input name="placeName" helpMessage="place-name-help" required="true" >
								<aui:validator name="required"
									errorMessage="this-field-is-required">
									function() {
										return jQuery('.place-manual').css('display') !== 'none';
									}	
								</aui:validator>
							</aui:input>
						</div>
					</div>
					<div class="row">
						<div class="col-md-1">
							<aui:input name="placeStreetNumber" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<aui:input name="placeStreetName" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">
							<aui:input name="placeZipCode" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<aui:select name="placeCityId" label="eu.campaign.city">
								<aui:option value="" label="select-city" />
								<c:forEach var="city" items="${dc.cities}">
									<aui:option value="${city.categoryId}"
										label="${city.getTitle(locale)}"
										selected="${city.categoryId eq dc.campaignEvent.placeCityId}" />
								</c:forEach>
							</aui:select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<aui:input name="placeCountry" value="${empty dc.campaignEvent.placeCountry ? 'France' : dc.campaignEvent.placeCountry}" />
						</div>
					</div>
					<aui:button id="showAutocompletePlace" cssClass="show-autocomplete-place" name="showAutocompletePlace" value="show-autocomplete-place" />
				</div>
				
			</aui:fieldset>
			
			<!-- Informations de contact public -->
			<aui:fieldset collapsed="true" collapsible="false" label="public-contact-information">
				<!-- Organisateur, tÃ©lÃ©phone, mail, adresse du site internet -->
				<div class="row">
					<div class="col-md-4">
						<aui:input name="promoter" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<aui:input name="publicPhone" helpMessage="public-phone-help" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<aui:input name="publicEmail" helpMessage="public-email-help">
							<aui:validator name="email" />
						</aui:input>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<aui:input name="websiteName" helpMessage="website-name-help" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-7">
						<aui:input name="websiteURL" helpMessage="website-url-help" >
							<aui:validator name="url" errorMessage="url-error" />
						</aui:input>
					</div>
				</div>
			</aui:fieldset>
			
			<!-- Dates et horaires -->
			<aui:fieldset collapsed="true" collapsible="false"
				label="dates-and-times">				
				
				<div class="event-periods-title">
					<p class="control-label"><liferay-ui:message key="event-period-creation" /> <span class="icon-asterisk text-warning"><span class="hide-accessible">Required</span></span></p>
				</div>
				
				<div class="add-dates-section">
					<aui:button id="periodGenerator" cssClass="date-range" name="periodGenerator" value="select-period-dates" />
				</div>
				
				<div class="change-times-section">
					<div class="event-periods-title">
						<p class="control-label"><liferay-ui:message key="update-current-language-times" /></p>
					</div>
					<div class="time-detail-generator-wrapper">
						<aui:input type="text" name="timeDetailGenerator" label="event-times" inlineField="true" helpMessage="event-times-help"/>
					</div>
					<aui:button id="changeTimes" name="changeTimes" value="update-times" />
				</div>
				
				<div class="event-periods-title">
					<p class="control-label"><liferay-ui:message key="event-periods" /></p>
				</div>
				<div id="date-fields">
					<div class="lfr-form-row lfr-form-row-inline">
						<div class="row-fields">
							<liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
								<liferay-util:param name="index" value="0" />
							</liferay-util:include>
						</div>
					</div>
						
					<c:forEach items="${dc.campaignEvent.periods}" var="period" varStatus="status">
						<div class="lfr-form-row lfr-form-row-inline">
							<div class="row-fields">
								<fmt:formatDate value="${period.startDate}" pattern="dd/MM/YYYY" type="date" var="formattedStartDate"/>
								<fmt:formatDate value="${period.endDate}" pattern="dd/MM/YYYY" type="date" var="formattedEndDate"/>
								<liferay-util:include page="/includes/period-row.jsp" servletContext="<%=application %>">
									<liferay-util:param name="index" value="${status.count}" />
									<liferay-util:param name="startDate" value="${formattedStartDate}" />
									<liferay-util:param name="endDate" value="${formattedEndDate}" />
									<liferay-util:param name="timeDetail" value="${period.timeDetail}" />
								</liferay-util:include>
							</div>
						</div>
					</c:forEach>
					<aui:input type="hidden" name="periodIndexes" value="${dc.defaultPeriodIndexes}" />
				</div>
				
			</aui:fieldset>
			
			
			<!-- Tarifs -->
			<aui:fieldset collapsed="true" collapsible="false" label="event-price">
				<!-- GratuitÃ© -->
				<div>
					<label><liferay-ui:message key="free-event" /></label>
					<aui:input name="free" value="1" type="radio" checked="${dc.campaignEvent.free eq 1}" label="yes" />
					<aui:input name="free" value="0" type="radio" checked="${dc.campaignEvent.free eq 0}" label="no" />
					<aui:input name="free" value="2" type="radio" checked="${dc.campaignEvent.free eq 2 or empty dc.campaignEvent.free}" label="not-communicated" />
				</div>
				
				<!-- Tarifs -->
				<div class="row">
					<div class="col-md-8">
						<aui:input label="event-price" name="price" localized="true" type="textarea"/>
					</div>
				</div>
			</aui:fieldset>
			
			<!-- Autres informations -->
			<aui:fieldset collapsed="true" collapsible="false" label="other-information">
				<div class="row">
					<!-- Campagne -->
					<div class="col-md-5">
						<aui:select name="campaignId" required="true" label="campaign">
							<aui:option value="" label="select-campaign" />
							<c:forEach var="campaign" items="${dc.campaigns}">
								<fmt:formatDate value="${campaign.getStartDate()}" pattern="dd/MM/YYYY" type="date" var="formattedStartCampaign"/>
								<fmt:formatDate value="${campaign.getEndDate()}" pattern="dd/MM/YYYY" type="date" var="formattedEndCampaign"/>
								<aui:option value="${campaign.campaignId}"
									label="${campaign.getTitle(locale)} (du ${formattedStartCampaign} au ${formattedEndCampaign})"
									selected="${campaign.campaignId eq dc.campaignEvent.campaignId}"
									localizeLabel="false" />
							</c:forEach>
						</aui:select>
					</div>
				</div>
				<div class="row">
					<!-- Types -->
					<div class="col-md-7 form-group">
						<label><liferay-ui:message key="types" /> <span class="icon-asterisk text-warning"><span class="hide-accessible">Required</span></span></label>
						<select name="<portlet:namespace />typesIds" label="types" multiple placeholder="<liferay-ui:message key="select-types" />">
							<c:forEach items="${dc.types}" var="category">
								<c:if test="${category.rootCategory}">
									<c:set var="category" value="${category}" scope="request"/>
									<c:set var="dc" value="${dc}" scope="request"/>
									<c:set var="level" value="0" scope="request" />
									<c:set var="categoryIds" value="${dc.campaignEvent.typesIds}" scope="request" />
									<jsp:include page="/category-option.jsp"/>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-7 form-group">
						<!-- ThÃ¨mes -->
						<label><liferay-ui:message key="themes" /> <span class="icon-asterisk text-warning"><span class="hide-accessible">Required</span></span></label>
						<select name="<portlet:namespace />themesIds" label="themes" multiple placeholder="<liferay-ui:message key="select-themes" />">
							<c:forEach items="${dc.themes}" var="category">
								<c:if test="${category.rootCategory}">
									<c:set var="category" value="${category}" scope="request"/>
									<c:set var="dc" value="${dc}" scope="request"/>
									<c:set var="level" value="0" scope="request" />
									<c:set var="categoryIds" value="${dc.campaignEvent.themesIds}" scope="request" />
									<jsp:include page="/category-option.jsp"/>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-7 form-group">
						<!-- Publics -->
						<label>
							<liferay-ui:message key="publics" />
							<span class="taglib-icon-help lfr-portal-tooltip" data-title="<liferay-ui:message key="publics-help" />"> <span class=""> <svg class="lexicon-icon lexicon-icon-question-circle-full" role="img" title="" viewBox="0 0 512 512">  
							<path class="lexicon-icon-outline" d="M256 0c-141.37 0-256 114.6-256 256 0 141.37 114.629 256 256 256s256-114.63 256-256c0-141.4-114.63-256-256-256zM269.605 360.769c-4.974 4.827-10.913 7.226-17.876 7.226s-12.873-2.428-17.73-7.226c-4.857-4.827-7.285-10.708-7.285-17.613 0-6.933 2.428-12.844 7.285-17.788 4.857-4.915 10.767-7.402 17.73-7.402s12.932 2.457 17.876 7.402c4.945 4.945 7.431 10.854 7.431 17.788 0 6.905-2.457 12.786-7.431 17.613zM321.038 232.506c-5.705 8.923-13.283 16.735-22.791 23.464l-12.99 9.128c-5.5 3.979-9.714 8.455-12.668 13.37-2.955 4.945-4.447 10.649-4.447 17.145v1.901h-34.202c-0.439-2.106-0.731-4.184-0.936-6.291s-0.321-4.301-0.321-6.612c0-8.397 1.901-16.413 5.705-24.079s10.24-14.834 19.309-21.563l15.185-11.322c9.070-6.7 13.605-15.009 13.605-24.869 0-3.57-0.644-7.080-1.901-10.533s-3.219-6.495-5.851-9.128c-2.633-2.633-5.969-4.71-9.977-6.291s-8.66-2.369-13.927-2.369c-5.705 0-10.561 1.054-14.571 3.16s-7.343 4.769-9.977 8.017c-2.633 3.247-4.594 7.022-5.851 11.322s-1.901 8.66-1.901 13.049c0 4.213 0.41 7.548 1.258 10.065l-39.877-1.58c-0.644-2.311-1.054-4.652-1.258-7.080-0.205-2.399-0.321-4.769-0.321-7.080 0-8.397 1.58-16.619 4.74-24.693s7.812-15.214 13.927-21.416c6.114-6.173 13.663-11.176 22.645-14.951s19.368-5.676 31.188-5.676c12.229 0 22.996 1.785 32.3 5.355 9.274 3.57 17.087 8.25 23.435 14.014 6.319 5.764 11.089 12.434 14.248 19.982s4.74 15.331 4.74 23.289c0.058 12.581-2.809 23.347-8.514 32.27z"></path>
							</svg> <span class="taglib-icon-label"> </span> </span> <span class="taglib-text hide-accessible"><liferay-ui:message key="publics-help" /></span> </span>
						</label>
						<select name="<portlet:namespace />publicsIds" label="publics" multiple placeholder="<liferay-ui:message key="select-publics" />">
							<c:forEach items="${dc.publics}" var="category">
								<c:if test="${category.rootCategory}">
									<c:set var="category" value="${category}" scope="request"/>
									<c:set var="dc" value="${dc}" scope="request"/>
									<c:set var="level" value="0" scope="request" />
									<c:set var="categoryIds" value="${dc.campaignEvent.publicsIds}" scope="request" />
									<jsp:include page="/category-option.jsp"/>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
			</aui:fieldset>
			
			<!-- Historique -->
			<c:if test="${not empty dc.campaignEvent}">
				<aui:fieldset collapsed="true" collapsible="false" label="history">
					<div class="status-history">
						<c:forEach var="status" items="${dc.campaignEvent.statusHistory}">
							<p><fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${status.date}" /> - <span class="${status.statusLabel}"><liferay-ui:message key="${status.statusLabel}" /></span><c:if test="${not empty status.comment}"> - ${status.comment}</c:if></p>
						</c:forEach>
					</div>
				</aui:fieldset>
			</c:if>
		</aui:fieldset-group>

		
		<!-- Erreur potentielle sur titre et description -->
		<div class="language-error" style="display: none;">
			
		</div>
		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" name="save-as-draft" value="save-as-draft" />
			<aui:button cssClass="btn-lg" type="submit" name="save-and-submit" value="save-and-submit" />
			<c:if test="${(not empty dc.campaignEvent
						&& dc.campaignEvent.status eq 1 
						&& dc.campaignEvent.isUserManagerOfTheEvent(themeDisplay.userId)) || dc.isAdministrator()}">
				<aui:button cssClass="btn-lg" type="submit" name="save-and-approve" value="save-and-approve" />
			</c:if>
			<c:if test="${not empty dc.campaignEvent 
						&& dc.campaignEvent.status eq 1 
						&& dc.campaignEvent.isUserManagerOfTheEvent(themeDisplay.userId)}">
				<aui:button cssClass="btn-lg" type="submit" name="save-and-deny" value="save-and-deny" />
			</c:if>
			<c:if test="${not empty dc.campaignEvent 
						&& dc.campaignEvent.status eq 8 
						&& dc.campaignEvent.isUserManagerOfTheEvent(themeDisplay.userId)}">
				<aui:button cssClass="btn-lg" type="submit" name="save-and-deny-deletion" value="save-and-deny-deletion" />
				<aui:button cssClass="btn-lg" type="button" name="delete" value="delete" href="${deleteURL}"/>
			</c:if>
			<c:if test="${not empty dc.campaignEvent and dc.campaignEvent.status ne 8}">
				<aui:button cssClass="btn-lg" type="submit" name="save-and-request-deletion" value="save-and-request-deletion" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>
<liferay-portlet:resourceURL var="placeAutocompleteURL">
</liferay-portlet:resourceURL>
<liferay-portlet:actionURL name="getPeriodRow" var="periodRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/period-row.jsp" />
</liferay-portlet:actionURL>
<liferay-util:html-bottom>
	<aui:script>
		define._amd = define.amd;
		define.amd = false;
		var namespace = '<portlet:namespace />';
		var placeAutocompleteURL = '${placeAutocompleteURL}';
		var getPeriodRowJSPURL = '${periodRowURL}';
		var themeLabels = ${dc.themeLabels};
		var campaignThemes = ${dc.campaignThemes};
		var eventThemes = '${dc.campaignEvent.themesIds}';
		var typeLabels = ${dc.typeLabels};
		var campaignTypes = ${dc.campaignTypes};
		var eventTypes = '${dc.campaignEvent.typesIds}';
		
		
		$('button[name=' + namespace + 'use-same-picture]').on('click', function(){

			$('input[name=' + namespace + 'webImageId]').val('${dc.campaignEvent.imageId}');
			$('img[name=webImageUrl]').attr('src', '${dc.campaignEvent.imageURL}');
		});
		
		
	</aui:script>
	<link rel="stylesheet" href="/o/agendabo/css/vendors/choices.min.css">
	<script src="/o/agendabo/js/vendors/choices.min.js"></script>
	<script src="/o/agendabo/js/vendors/moment.min.js"></script>
	<script src="/o/agendabo/js/vendors/daterangepicker.js"></script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script>
		define.amd = define._amd;
	</script>
	<script src="/o/agendacampaignweb/js/campaign-edit.js"></script>
</liferay-util:html-bottom>
<!-- Ajout du champ obligatoire conditionnel sur le select de la ville -->
<!-- (obligÃ© de passer par du JS car pas de aui:validator sur aui:select -->

<aui:script use="liferay-form">
	var form = Liferay.Form.get('<portlet:namespace />fm');
    var oldFieldRules = form.get('fieldRules');

    var newFieldRules = [
    	{
    		body: function () {
    			return jQuery('.place-manual').css('display') !== 'none';
			},
			custom: false,
			errorMessage: '<liferay-ui:message key="this-field-is-required" />',
			fieldName: '<portlet:namespace />placeCityId',
			validatorName: 'required'
		}
	];

	var fieldRules = oldFieldRules.concat(newFieldRules);
	form.set('fieldRules', fieldRules);
</aui:script>