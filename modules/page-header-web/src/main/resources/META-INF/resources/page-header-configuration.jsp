<%@ include file="/page-header-init.jsp"%>

<%@ page import="com.liferay.portal.kernel.util.Constants"%>

<liferay-portlet:actionURL portletConfiguration="<%=true%>"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%=true%>"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="<%=configurationActionURL%>" method="post" name="fm">
	
		<aui:input name="<%=Constants.CMD%>" type="hidden"
			value="<%=Constants.UPDATE%>" />
	
		<aui:input name="redirect" type="hidden"
			value="<%=configurationRenderURL%>" />
	
		<aui:fieldset>
			<aui:input name="imageCredit" type="text" value="${imageCredit}" />
		</aui:fieldset>
		
		<aui:fieldset>
		    <div class="display-template">
		        <liferay-ddm:template-selector
		            className="<%= LayoutSet.class.getName() %>"
		            displayStyle="${displayStyle}"
		            displayStyleGroupId="${displayStyleGroupId}"
		            refreshURL="${refreshURL}"
		            showEmptyOption="<%= true %>"
		        />
		    </div>
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>