<%@ include file="../objtp-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<c:set var="categoriesLabel"><liferay-ui:message key="objtp-found-object-categories" /></c:set>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="cmd" type="hidden"
				value="update" />
		
			<aui:input name="redirect" type="hidden"
				value="${configurationRenderURL}" />
				
			<aui:input name="title" type="text" label="Titre"
				value="${title}" />

			<aui:fieldset collapsed="false" collapsible="true"
				label="${categoriesLabel}">
				<aui:input type="hidden" name="categoriesCount"
								value="${fn:length(allCategories)}" />
				
				<c:forEach var="category" varStatus="categoryStatus" items="${allCategories}">
					<c:set var="categoryIsChecked"
						   value="${fn:contains(categoryCodes, category.code)}" />
										
					<aui:input type="checkbox"
								name="categoryCode_${categoryStatus.index}"
								label="${category.name}"
								checked="${categoryIsChecked}"
								value="${category.code}" inlineField="true"/>					
				</c:forEach>
			</aui:fieldset>
		</aui:fieldset-group>
		
		
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

 <style>
 .form-group.form-group-inline.form-inline.input-checkbox-wrapper {
 	width: 200px;
 	vertical-align: top;
 }
 </style>