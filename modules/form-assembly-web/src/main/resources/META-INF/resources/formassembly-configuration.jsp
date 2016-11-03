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
			
			<c:forEach var="locale" items="${availableLocales}">
				<aui:select label="Formulaire ${locale.displayLanguage}" name="formId_${locale}" localized="true">
					<c:forEach var="currentForm" items="${forms}" 	>
						<aui:option value="${currentForm.id}" selected="${currentForm.id eq formIdMap[locale]}">${currentForm.name}</aui:option>
					</c:forEach>
				</aui:select>
			</c:forEach>
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>