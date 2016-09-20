<%@ include file="/search-asset-init.jsp"%>
<%@page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil" %>
<%@page import="com.liferay.asset.kernel.model.AssetRendererFactory" %>

<liferay-portlet:actionURL portletConfiguration="${true}"
	varImpl="configurationActionURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">
	
		<aui:input name="cmd" type="hidden" value="update" />
	
		<aui:fieldset-group markupView="lexicon">
			<!-- Type d'asset -->
			<aui:fieldset collapsed="${false}" collapsible="${true}"
				label="asset-type">
				
				<div class="asset-types">
					<aui:input type="hidden" name="assetClassNamesCount"
						value="${fn:length(availableAssetRendererFactories)}" />
					<c:set var="i" value="${0}" />
					<c:forEach var="assetRendererFactory" varStatus="assetStatus"
						items="${availableAssetRendererFactories}">
						<c:set var="classNameIsChecked" value="${fn:contains(assetClassNamesIds, assetRendererFactory.classNameId)}" />
						<div class="asset-type-configuration">
							<%
			 						AssetRendererFactory assetRendererFactory = (AssetRendererFactory) pageContext.getAttribute("assetRendererFactory");
							%>
							<aui:input type="checkbox" name="assetClassNameId_${assetStatus.index}"
								label="<%= ResourceActionsUtil.getModelResource(locale, assetRendererFactory.getClassName()) %>"
								checked="${classNameIsChecked}"
								value="${assetRendererFactory.classNameId}" inlineField="true" />
								
							<aui:select name="templateKey_${assetStatus.index}" inlineField="true">
								<aui:option value="0">Sélectionnez un template</aui:option>
								<c:forEach var="template" items="${templatesList[assetStatus.index]}">
									<aui:option value="${template.templateKey}" selected="${fn:contains(templatesKeys, template.templateKey)}">${template.getName(locale)}</aui:option>
								</c:forEach>
							</aui:select>
							
							
							<aui:input type="text" placeholder="Friendly URL de la page détail" name="layoutFriendlyURL_${assetStatus.index}" inlineField="true" value="${classNameIsChecked ? layoutsFriendlyURLs[i] : ''}"/>
							
							<c:if test="${classNameIsChecked}">
								<c:set var="i" value="${i + 1}" />
							</c:if>
						</div>
					</c:forEach>
				</div>
			</aui:fieldset>
			
			<!-- Vocabulaires -->
			<aui:fieldset collapsed="${false}" collapsible="${true}"
				label="vocabularies">
				<label> 
					<liferay-ui:message key="select-vocabularies" />
				</label>
				<aui:input type="hidden" name="vocabulariesCount"
					value="${fn:length(vocabularies)}" />
				
				<c:forEach var="vocabulary" items="${vocabularies}"
					varStatus="vocStatus">
					<aui:input type="checkbox" name="vocabularyId_${vocStatus.index}"
						label="${vocabulary.name}" value="${vocabulary.vocabularyId}"
						checked="${fn:contains(vocabulariesIds, vocabulary.vocabularyId)}" />
				</c:forEach>
	
			</aui:fieldset>
			<aui:fieldset collapsed="${false}" collapsible="${true}"
				label="delta">
				<aui:input type="number" name="delta" value="${delta}" />
			</aui:fieldset>
		</aui:fieldset-group>
	
	
	
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

<style>
	.asset-types .asset-type-configuration .input-select-wrapper label,
	.asset-types .input-text-wrapper label {
	    display: none;
	    float: right;
	}
	
	.asset-types .input-select-wrapper {
	    width: 250px;
	}
	
	.asset-types .input-text-wrapper {
	    margin-bottom: 10px;
   		margin-left: 50px;
   		width: 250px;
	}
	.asset-types .asset-type-configuration select {
	    margin-left: 20px;
	    min-width: 150px;
	    margin-bottom: -5px;
	}
	
	.asset-types .input-checkbox-wrapper {
    width: 250px;
}
</style>