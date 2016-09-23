<%@ include file="/formassembly-init.jsp"%>

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
			<aui:select label="form-id" name="formId">
				<c:forEach var="currentForm" items="${forms}" 	>
					<aui:option value="${currentForm.id}" selected="${currentForm.id eq formId}">${currentForm.name}</aui:option>
				</c:forEach>
			</aui:select>
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>