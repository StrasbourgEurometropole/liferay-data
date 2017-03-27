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
	<aui:form action="${saveURL}" method="post" name="fm" enctype="multipart/form-data" onSubmit="validatePeriods(event);" >
		<aui:model-context bean="${dc.campaignEvent}"
			model="<%=CampaignEvent.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="campaignEventId" type="hidden" />
			<aui:input name="newStatus" type="hidden" value="" />
			
			<!-- Informations de contact privées-->
			<aui:fieldset collapsed="false" collapsible="true" label="private-contact-information">
				<!-- Nom, prénom, mail, téléphone -->
				<aui:input name="lastName" required="true" value="${not empty dc.campaignEvent ? dc.campaignEvent.lastName : themeDisplay.user.lastName}"/>
				<aui:input name="firstName" required="true" value="${not empty dc.campaignEvent ? dc.campaignEvent.firstName: themeDisplay.user.lastName}" />
				<aui:input name="phone" required="true" value="${not empty dc.campaignEvent ? dc.campaignEvent.phone : themeDisplay.user.phones[0].number}" />
				<aui:input name="email" required="true"  value="${not empty dc.campaignEvent ? dc.campaignEvent.email : themeDisplay.user.emailAddress}" />
				
				<!-- Service : service EMS ou autre -->
				<div class="service-type-selection">
					<label><input type="radio" value="emsService" name="serviceType" 
					<c:if test="${(not empty dc.campaignEvent.serviceId and dc.campaignEvent.serviceId gt 0) or empty dc.campaignEvent.service }">checked</c:if>> <liferay-ui:message key="ems-service" /></label><br>
					<label><input type="radio" value="otherService" name="serviceType"
						<c:if test="${(empty dc.campaignEvent.serviceId or dc.campaignEvent.serviceId eq 0) and not empty dc.campaignEvent.service }">checked</c:if>> <liferay-ui:message key="other-service" /></label><br><br>
						
					<div class="emsService" <c:if test="${(empty dc.campaignEvent.serviceId or dc.campaignEvent.serviceId eq 0) and not empty dc.campaignEvent.service }">style="display: none;"</c:if>>
						<aui:select name="serviceId" label="ems-service">
							<aui:option value="" label="" />
							<c:forEach var="service" items="${dc.EMSServices}">
								<aui:option value="${service.categoryId}"
									label="${service.getTitle(locale)}"
									selected="${service.categoryId eq dc.campaignEvent.serviceId}" />
							</c:forEach>
						</aui:select>
					</div>
					
					<div class="otherService" <c:if test="${(not empty dc.campaignEvent.serviceId and dc.campaignEvent.serviceId gt 0) or empty dc.campaignEvent.service }">style="display: none;"</c:if>>
						<aui:input name="service" />
					</div>
					
					<!-- Personne sur place -->
					<aui:input name="onSiteFirstName" />
					<aui:input name="onSiteLastName" />
					<aui:input name="onSitePhone" />
				</div>
			</aui:fieldset>
			
			<!-- Informations sur l'événement -->
			<aui:fieldset collapsed="true" collapsible="true" label="event-information">
				<!-- Titre, sous-titre, description -->
				<aui:input name="title" required="true" />
				<aui:input name="subtitle" />
				<aui:input name="description" label="eu.campaign.description" helpMessage="description-help" />
				
				<!-- Manifestations -->
				<label><liferay-ui:message key="manifestations" /></label>
				<select class="form-control" name="<portlet:namespace />manifestations"
					id="manifestations"
					placeholder="<liferay-ui:message key="manifestations" />" multiple>
					<c:forEach var="manifestation" items="${dc.manifestations}">
						<option value="${manifestation.idSource}" 
							<c:if test="${not empty dc.campaignEvent and fn:contains(dc.campaignEvent.manifestationsIds, manifestation.idSource)}">
								selected
							</c:if>
						>
							${manifestation.getTitle(locale)}
						</option>
					</c:forEach>
				</select>
			</aui:fieldset>
			
			<!-- Images -->
			<aui:fieldset collapsed="true" collapsible="true" label="images">		
				
				<!-- Image et image web -->
				<aui:input type="file" name="image">
					<aui:validator name="acceptFiles">'jpg,png'</aui:validator>
				</aui:input>
				<c:if test="${not empty dc.campaignEvent.imageId}">
					<p><img src="${dc.campaignEvent.imageURL}"></p>
				</c:if>
				
				<aui:input name="imageOwner">
					<aui:validator name="required"
						errorMessage="this-field-is-required">
						function() {
							return $('[name$=_image]').val().length > 0;
						}	
					</aui:validator>
				</aui:input>
				
				<c:if test="${dc.campaignEvent.isUserManagerOfTheEvent(themeDisplay.userId)}">
					<aui:input type="file" name="webImage">
						<aui:validator name="acceptFiles">'jpg,png'</aui:validator>
					</aui:input>
					<c:if test="${not empty dc.campaignEvent.webImageId}">
						<p><img src="${dc.campaignEvent.webImageURL}"></p>
					</c:if>
				</c:if>
				
			</aui:fieldset>
				
			<!-- Lieu -->
			<aui:fieldset collapsed="true" collapsible="true" label="place">	
				
				<!-- Autocomplete lieu -->
				<div class="place-autocomplete" <c:if test="${empty dc.campaignEvent.placeSIGId and not empty dc.campaignEvent.placeName }">style="display: none;"</c:if>>
					<div class="place-autocomplete-input-wrapper">
						<aui:input label="Choisir un lieu" type="text" name="place" value="" />
					</div>
					<span id="place-autocomplete-hidden-value">
						<aui:input type="hidden" name="placeSIGId" value="${dc.campaignEvent.placeSIGId}" />
					</span>
					<aui:input label="Lieu choisi" type="text" value="${dc.campaignEvent.getLegacyPlace(locale).alias}" name="selectedPlace" disabled="true" cssClass="selected-place" >
						<aui:validator name="required"
							errorMessage="this-field-is-required">
							function() {
								return jQuery('.place-autocomplete').css('display') !== 'none';
							}	
						</aui:validator>
					</aui:input>
					<a href="#" class="show-manual-place"><liferay-ui:message key="show-manual-place" /></a>
				</div>
				
				<!-- Saisie manuelle -->
				<div class="place-manual" <c:if test="${not empty dc.campaignEvent.placeSIGId or empty dc.campaignEvent.placeName }">style="display: none;"</c:if>>
					<aui:input name="placeName" helpMessage="place-name-help" required="true" >
						<aui:validator name="required"
							errorMessage="this-field-is-required">
							function() {
								return jQuery('.place-manual').css('display') !== 'none';
							}	
						</aui:validator>
					</aui:input>
					<aui:input name="placeStreetNumber" />
					<aui:input name="placeStreetName" />
					<aui:input name="placeZipCode" />
					<aui:select name="placeCityId" label="eu.campaign.city">
						<c:forEach var="city" items="${dc.cities}">
							<aui:option value="${city.categoryId}"
								label="${city.getTitle(locale)}"
								selected="${city.categoryId eq dc.campaignEvent.placeCityId or empty dc.campaignEvent.placeCityId && city.name eq 'Strasbourg'}" />
						</c:forEach>
					</aui:select>
					<aui:input name="placeCountry" value="${empty dc.campaignEvent.placeCountry ? 'France' : dc.campaignEvent.placeCountry}" />
					<a href="#" class="show-autocomplete-place"><liferay-ui:message key="show-autocomplete-place" /></a>
				</div>
				
			</aui:fieldset>
			
			<!-- Informations de contact public -->
			<aui:fieldset collapsed="true" collapsible="true" label="public-contact-information">
				<!-- Organisateur, téléphone, mail, adresse du site internet -->
				<aui:input name="promoter" />
				<aui:input name="publicPhone" helpMessage="public-phone-help" />
				<aui:input name="publicEmail" helpMessage="public-email-help">
					<aui:validator name="email" />
				</aui:input>
				<aui:input name="websiteName" />
				<aui:input name="websiteURL">
					<aui:validator name="url" />
				</aui:input>
			</aui:fieldset>
			
			<!-- Dates et horaires -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="dates-and-times">				
				
				<div class="event-periods-title">
					<p class="control-label"><liferay-ui:message key="event-period-creation" /></p>
				</div>
				
				<div class="add-dates-section">
					<span class="date-range" id="periodGenerator"><liferay-ui:message key="select-period-dates" /></span>
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
			<aui:fieldset collapsed="true" collapsible="true" label="event-price">
				<!-- Gratuité -->
				<div>
					<label><liferay-ui:message key="free-event" /></label>
					<aui:input name="free" value="0" type="radio" checked="${dc.campaignEvent.free eq 0}" label="no" />
					<aui:input name="free" value="1" type="radio" checked="${dc.campaignEvent.free eq 1}" label="yes" />
					<aui:input name="free" value="2" type="radio" checked="${dc.campaignEvent.free eq 2 or empty dc.campaignEvent.free}" label="not-communicated" />
				</div>
				
				<!-- Tarifs -->
				<aui:input label="event-price" name="price" cssClass="event-price"/>
			</aui:fieldset>
			
			<!-- Autres informations -->
			<aui:fieldset collapsed="true" collapsible="true" label="other-information">
			
				<!-- Campagne -->
				<aui:select name="campaignId" required="true" label="campaign">
					<aui:option value="" label="" />
					<c:forEach var="campaign" items="${dc.campaigns}">
						<aui:option value="${campaign.campaignId}"
							label="${campaign.getTitle(locale)}"
							selected="${campaign.campaignId eq dc.campaignEvent.campaignId}" />
					</c:forEach>
				</aui:select>
				
				<!-- Type / thême / publics -->
				<aui:select name="typeId" required="true" label="type">
					<aui:option value="" label="" />
					<c:forEach var="type" items="${dc.types}">
						<aui:option value="${type.categoryId}"
							label="${type.getTitle(locale)}"
							selected="${type.categoryId eq dc.campaignEvent.typeId}" />
					</c:forEach>
				</aui:select>
					
				<!-- TODO : thèmes de la campagne uniquement -->
				<aui:select name="themeId" required="true" label="theme">
					<aui:option value="" label="" />
					<c:forEach var="type" items="${dc.themes}">
						<aui:option value="${type.categoryId}"
							label="${type.getTitle(locale)}"
							selected="${type.categoryId eq dc.campaignEvent.themeId}" />
					</c:forEach>
				</aui:select>
			
				<label>
					<liferay-ui:message key="publics" />
					<span class="taglib-icon-help lfr-portal-tooltip" data-title="<liferay-ui:message key="publics-help" />"> <span class=""> <svg class="lexicon-icon lexicon-icon-question-circle-full" role="img" title="" viewBox="0 0 512 512">  
					<path class="lexicon-icon-outline" d="M256 0c-141.37 0-256 114.6-256 256 0 141.37 114.629 256 256 256s256-114.63 256-256c0-141.4-114.63-256-256-256zM269.605 360.769c-4.974 4.827-10.913 7.226-17.876 7.226s-12.873-2.428-17.73-7.226c-4.857-4.827-7.285-10.708-7.285-17.613 0-6.933 2.428-12.844 7.285-17.788 4.857-4.915 10.767-7.402 17.73-7.402s12.932 2.457 17.876 7.402c4.945 4.945 7.431 10.854 7.431 17.788 0 6.905-2.457 12.786-7.431 17.613zM321.038 232.506c-5.705 8.923-13.283 16.735-22.791 23.464l-12.99 9.128c-5.5 3.979-9.714 8.455-12.668 13.37-2.955 4.945-4.447 10.649-4.447 17.145v1.901h-34.202c-0.439-2.106-0.731-4.184-0.936-6.291s-0.321-4.301-0.321-6.612c0-8.397 1.901-16.413 5.705-24.079s10.24-14.834 19.309-21.563l15.185-11.322c9.070-6.7 13.605-15.009 13.605-24.869 0-3.57-0.644-7.080-1.901-10.533s-3.219-6.495-5.851-9.128c-2.633-2.633-5.969-4.71-9.977-6.291s-8.66-2.369-13.927-2.369c-5.705 0-10.561 1.054-14.571 3.16s-7.343 4.769-9.977 8.017c-2.633 3.247-4.594 7.022-5.851 11.322s-1.901 8.66-1.901 13.049c0 4.213 0.41 7.548 1.258 10.065l-39.877-1.58c-0.644-2.311-1.054-4.652-1.258-7.080-0.205-2.399-0.321-4.769-0.321-7.080 0-8.397 1.58-16.619 4.74-24.693s7.812-15.214 13.927-21.416c6.114-6.173 13.663-11.176 22.645-14.951s19.368-5.676 31.188-5.676c12.229 0 22.996 1.785 32.3 5.355 9.274 3.57 17.087 8.25 23.435 14.014 6.319 5.764 11.089 12.434 14.248 19.982s4.74 15.331 4.74 23.289c0.058 12.581-2.809 23.347-8.514 32.27z"></path>
					</svg> <span class="taglib-icon-label"> </span> </span> <span class="taglib-text hide-accessible"><liferay-ui:message key="publics-help" /></span> </span>
				</label>
				<select name="<portlet:namespace />publicsIds" label="publics" multiple>
					<c:forEach var="public" items="${dc.publics}">
						<option value="${public.categoryId}"
							<c:if test="${fn:contains(dc.campaignEvent.publicsIds, public.categoryId)}">
								selected
							</c:if>
						>
							${public.getTitle(locale)}
						</option>
					</c:forEach>
				</select>
				
			</aui:fieldset>
			
			<!-- Historique -->
			<c:if test="${not empty dc.campaignEvent}">
				<aui:fieldset collapsed="true" collapsible="true" label="history">
					<c:forEach var="status" items="${dc.campaignEvent.statusHistory}">
						<p><fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${status.date}" /> - <span class="${status.statusLabel}"><liferay-ui:message key="${status.statusLabel}" /></span><c:if test="${not empty status.comment}"> - ${status.comment}</c:if></p>
					</c:forEach>
				</aui:fieldset>
			</c:if>
		</aui:fieldset-group>

		
		<!-- Erreur potentielle sur titre et description -->
		<div class="language-error" style="display: none;">
			
		</div>
		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" name="save-as-draft" value="save-as-draft" />
			<aui:button cssClass="btn-lg" type="submit" name="save-and-submit" value="save-and-submit" />
			<c:if test="${not empty dc.campaignEvent 
						&& dc.campaignEvent.status eq 1 
						&& dc.campaignEvent.isUserManagerOfTheEvent(themeDisplay.userId)}">
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
	</aui:script>
	<link rel="stylesheet" href="/o/agendabo/css/vendors/choices.min.css">
	<link rel="stylesheet" href="/o/agendabo/css/vendors/daterangepicker.css">
	<script src="/o/agendabo/js/vendors/choices.min.js"></script>
	<script src="/o/agendabo/js/vendors/moment.min.js"></script>
	<script src="/o/agendabo/js/vendors/daterangepicker.js"></script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script>
		define.amd = define._amd;
	</script>
	<script src="/o/agendacampaignweb/js/campaign-edit.js"></script>
</liferay-util:html-bottom>
