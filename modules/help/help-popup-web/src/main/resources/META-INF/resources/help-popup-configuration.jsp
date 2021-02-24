<%@ include file="/help-popup-init.jsp"%>
<%@page
	import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetRendererFactory"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:error key="wrong-friendly-url" message="wrong-friendly-url" />
	<aui:form action="${configurationActionURL}" method="post" name="fm" >

		<aui:input name="cmd" type="hidden" value="update" />

		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />
		
		<aui:fieldset-group markupView="lexicon">
			<!-- Affichage -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="display">

				<!-- Formulaire de recherche -->
				<aui:select name="popupTemplateId">
					<aui:option value="submitHelpProposal" selected="${popupTemplateId eq 'submitHelpProposal'}">
						<liferay-ui:message key="submitHelpProposal" />
					</aui:option>
					<aui:option value="submitHelpRequest" selected="${popupTemplateId eq 'submitHelpRequest'}">
						<liferay-ui:message key="submitHelpRequest" />
					</aui:option>
					<aui:option value="desactivateHelp" selected="${popupTemplateId eq 'desactivateHelp'}">
						<liferay-ui:message key="desactivateHelp" />
					</aui:option>
				</aui:select>
			</aui:fieldset>

		</aui:fieldset-group>

		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>
