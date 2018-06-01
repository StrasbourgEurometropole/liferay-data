<%@ include file="../comments-init.jsp"%>

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
			<!-- Mode d'affichage -->
			<aui:select name="orderBy">
				<aui:option value="asc" selected="${template eq 'asc'}">
					<liferay-ui:message key="order-by-asc" />
				</aui:option>
				<aui:option value="desc" selected="${template eq 'desc'}">
					<liferay-ui:message key="order-by-desc" />
				</aui:option>
			</aui:select>
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>