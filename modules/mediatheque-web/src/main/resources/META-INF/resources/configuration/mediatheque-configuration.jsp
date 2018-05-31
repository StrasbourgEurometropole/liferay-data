<%@ include file="/mediatheque-init.jsp"%>

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
			<aui:input name="errorMap" value="${error}" localized="true" type="editor" label="error-text" />
		</aui:fieldset>
		
		<aui:fieldset>
			<aui:input name="demarcheMap" value="${demarche}" localized="true" type="editor" label="demarche-text" />
		</aui:fieldset>
		
		<aui:fieldset>
			<aui:input name="retourURL" value="${retourURL}" type="text" label="retour-URL" />
		</aui:fieldset>
		
		<aui:fieldset>
			<aui:input name="contactURL" value="${contactURL}" type="text" label="contact-URL" />
		</aui:fieldset>
		
		<aui:fieldset>
			<aui:input name="mediathequeURL" value="${mediathequeURL}" type="text" label="mediatheque-URL" />
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