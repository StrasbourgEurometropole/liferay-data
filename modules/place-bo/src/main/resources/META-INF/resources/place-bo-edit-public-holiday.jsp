<%@ include file="/place-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.place.model.PublicHoliday"%>

<liferay-portlet:renderURL varImpl="publicHolidaysURL">
	<portlet:param name="tab" value="publicHolidays" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deletePublicHoliday"
	var="deletePublicHolidayURL">
	<portlet:param name="cmd" value="deletePublicHoliday" />
	<portlet:param name="tab" value="publicHolidays" />
	<portlet:param name="publicHolidayId"
		value="${not empty dc.publicHoliday ? dc.publicHoliday.publicHolidayId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="savePublicHoliday"
	varImpl="savePublicHolidayURL">
	<portlet:param name="cmd" value="savePublicHoliday" />
	<portlet:param name="tab" value="publicHolidays" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${savePublicHolidayURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.publicHoliday}"
			model="<%=PublicHoliday.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="publicHolidayId" type="hidden" />

			<!-- Informations générale -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="general-information">

				<aui:input name="name" label="name-public-holiday">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="date" label="date-public-holiday">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="recurrent" type="toggle-switch"
					value="${not empty dc.publicHoliday ? dc.publicHoliday.recurrent : false}" />

			</aui:fieldset>

		</aui:fieldset-group>

		<aui:button-row>
			<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" type="submit" name="publish"
					value="publish" />
				<aui:button cssClass="btn-lg btn-default" type="submit"
					name="save-as-draft" value="save-as-draft" />
			</c:if>
			<c:if
				test="${not empty dc.publicHoliday and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deletePublicHolidayURL}" type="cancel"
					value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>