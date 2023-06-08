<%@ include file="/family-space-init.jsp"%>

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

        <!-- Indique si le webService est en maintenance -->
		<aui:fieldset>
            <aui:input type="checkbox" name="maintenance" value="${maintenance}" label="maintenance" />
		</aui:fieldset>
		
		<aui:fieldset>
			<aui:input name="addLunchURL" value="${addLunchURL}" type="text" label="add-lunch-url" />
		</aui:fieldset>

		<aui:fieldset>
			<aui:input name="linkAccountURL" value="${linkAccountURL}" type="text" label="link-account-url" />
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