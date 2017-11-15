<%@ include file="/sectorized-init.jsp"%>

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
	
		<!-- Types de secteurs-->
		<aui:fieldset id="types" label="types">
			<c:forEach var="type" items="${allTypes}" varStatus="loopStatus">
				<aui:input value="${type.name}" type="checkbox" name="types" checked="${fn:contains(types, type.name)}" id="types_${loopStatus.index}" label="${type.name}" />
			</c:forEach>
		</aui:fieldset>
		
		<aui:fieldset id="perimeter" label="perimeter">
			<aui:input type="checkbox" name="forceStrasbourg" label="force-strasbourg" checked="${forceStrasbourg}" />
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
		
		<!-- Template -->
		<aui:fieldset id="template" label="template">
			<div class="display-template">
				<liferay-ddm:template-selector className="${className}"
					displayStyle="${displayStyle}"
					displayStyleGroupId="${displayStyleGroupId}"
					refreshURL="${refreshURL}" showEmptyOption="true" />
			</div>
		</aui:fieldset>
	</aui:form>
</div>