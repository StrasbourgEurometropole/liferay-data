<%@ include file="/contact-form-init.jsp"%>

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
	
		<aui:fieldset id="general" label="general">
			
			<!-- Affichage -->
			<aui:select name="template" label="display">
				<aui:option value="default" label="default" />
				<aui:option value="christmas" label="christmas" selected="${template eq 'christmas'}"/>
			</aui:select>
			
			<!-- Titre -->
			<aui:input type="text" name="title" value="${title}" label="form-name" localized="true"/>
			
			<!-- Email -->
			<aui:input type="text" name="email" value="${email}" label="email" />
			
			<!-- Texte de description -->
			<aui:input name="descriptionText" value="${descriptionText}" localized="true" type="editor" label="description-text" />
			
			<!-- Texte "confidentialité" -->
			<aui:input name="privacyText" value="${privacyText}" localized="true" type="editor" label="privacy-text" />
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>