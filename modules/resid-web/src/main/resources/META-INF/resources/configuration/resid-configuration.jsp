<%@ include file="/resid-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">
	
		<aui:input name="cmd" type="hidden"
			value="update" />
	
		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />
		
		<aui:fieldset>
			<aui:input name="liaisonURL" value="${liaisonURL}" type="text" label="liaison-URL" />
		</aui:fieldset>
		
		<aui:fieldset>
			<aui:input name="residURL" value="${residURL}" type="text" label="resid-URL" />
		</aui:fieldset>
		
		<aui:fieldset>
			<aui:input name="zoneURL" value="${zoneURL}" type="text" label="zone-URL" />
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

<style>
	fieldset {
		margin-bottom: 20px;
	}
</style>