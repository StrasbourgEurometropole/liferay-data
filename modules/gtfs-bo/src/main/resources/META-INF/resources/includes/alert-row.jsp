<%@ include file="/gtfs-bo-init.jsp"%>
						
<div class="alert-label" id="${param.index}"><label><liferay-ui:message key="alert" /> ${param.index}</label></div>

<aui:input type="date" name="startDateAlert${param.index}" label="start-date-alert" value="${param.startDate}" />
<div class="alert-start-date" style="display: none">
	<liferay-ui:message key="this-field-is-required" />
</div>

<div class="alert-incorrect-date" style="display: none">
	<liferay-ui:message key="incorrect-date" />
</div>

<aui:input type="date" name="endDateAlert${param.index}" label="end-date-alert" value="${param.endDate}" />
<div class="alert-end-date" style="display: none">
	<liferay-ui:message key="this-field-is-required" />
</div>

<aui:input name="alertLigneAndDirection${param.index}" label="ligne-and-direction-alert" value="${param.ligneAndDirection}" localized="true" type="text"  />
<div class="alert-ligne-and-direction" style="display: none">
	<liferay-ui:message key="this-field-is-required" />
</div>

<aui:input name="alertPerturbation${param.index}" label="perturbation-alert" value="${param.perturbation}" localized="true" type="textarea"  />
<div class="alert-perturbation" style="display: none">
	<liferay-ui:message key="this-field-is-required" />
</div>



