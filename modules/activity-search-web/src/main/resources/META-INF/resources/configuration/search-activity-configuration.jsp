<%@ include file="/search-activity-init.jsp"%>


<liferay-portlet:actionURL portletConfiguration="<%=true%>"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%=true%>"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="<%=configurationActionURL%>" method="post" name="fm">
	
		<aui:input name="cmd" type="hidden"
			value="update" />
	
		<aui:input name="redirect" type="hidden"
			value="<%=configurationRenderURL%>" />
			
		<aui:fieldset>
		    <div class="display-template">
		        <liferay-ddm:template-selector
		            className="<%= Activity.class.getName() %>"
		            displayStyle="${displayStyle}"
		            displayStyleGroupId="${displayStyleGroupId}"
		            refreshURL="${refreshURL}"
		            showEmptyOption="<%= true %>"
		        />
		    </div>
		    
			<strasbourg-picker:layout name="detailPageUuid" label="detail-page" 
				multiple="false" required="false" value="${detailPageUuid}" />
				
		</aui:fieldset>
	
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>