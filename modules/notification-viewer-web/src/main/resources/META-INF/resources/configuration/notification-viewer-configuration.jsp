<%@ include file="../notification-viewer-init.jsp"%>

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
			
		<aui:input name="showAllUrl" type="text" label="Voir tous url"
			value="${showAllURL}" />
		
		<aui:fieldset>
			<!-- Mode d'affichage -->
			<aui:select name="template">
				<aui:option value="notification-viewer-view" selected="${template eq 'notification-viewer-view'}">
					<liferay-ui:message key="menu" />
				</aui:option>
				<aui:option value="notification-viewer-all" selected="${template eq 'notification-viewer-all'}">
					<liferay-ui:message key="toutes" />
				</aui:option>
			</aui:select>
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>