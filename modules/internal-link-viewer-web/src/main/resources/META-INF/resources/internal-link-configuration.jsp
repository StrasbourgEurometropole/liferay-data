<%@ include file="/internal-link-init.jsp"%>

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
			<strasbourg-picker:layout name="linksUuids" label="pages" multiple="true" required="true" value="${linksUuids}" />
			
			<aui:select name="template" label="display">
				<aui:option value="default" label="default" />
				<aui:option value="strasbourg" label="strasbourg" selected="${template eq 'strasbourg'}"/>
				<aui:option value="strasbourg-blue" label="strasbourg-blue" selected="${template eq 'strasbourg-blue'}"/>
			</aui:select>
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>