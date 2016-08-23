<%@ include file="/search-asset-init.jsp"%>
<%@page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil" %>
<%@page import="com.liferay.asset.kernel.model.AssetRendererFactory" %>

<liferay-portlet:actionURL portletConfiguration="${true}"
	varImpl="configurationActionURL" />

<aui:form action="${configurationActionURL}" method="post" name="fm">

	<aui:input name="cmd" type="hidden" value="update" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset collapsed="${false}" collapsible="${true}"
			label="asset-type">
			<aui:select label="choose-asset-type" name="assetClassNameId">
				<aui:option label="select" value="" />
				<c:forEach var="assetRendererFactory"
					items="${availableAssetRendererFactories}">
					<%
  						AssetRendererFactory assetRendererFactory = (AssetRendererFactory) pageContext.getAttribute("assetRendererFactory");
					%>
					<aui:option
						label="<%= ResourceActionsUtil.getModelResource(locale, assetRendererFactory.getClassName()) %>"
						selected="${assetClassNameId eq assetRendererFactory.classNameId}"
						value="${assetRendererFactory.classNameId }" />
				</c:forEach>
			</aui:select>

			<div id="vocabularies-section">
				<%@ include file="/search-asset-configuration-vocabularies.jsp"%>
			</div>
			
			<aui:input type="number" name="delta" value="${delta}" />

		</aui:fieldset>
	</aui:fieldset-group>



	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>
</aui:form>


<liferay-portlet:actionURL portletConfiguration="${true}"
	var="vocabulariesURL" windowState="exclusive" />
<script>
	jQuery(function() {
		jQuery('#${renderResponse.namespace}assetClassNameId').on('change', function() {
			var classNameId = jQuery('#${renderResponse.namespace}assetClassNameId')
					.val();
			jQuery.ajax({
				type : "POST",
				url : '${vocabulariesURL}'
						+ '&${renderResponse.namespace}cmd=update-vocabularies'
						+ '&${renderResponse.namespace}assetClassNameId='
						+ classNameId,
				dataType : "html",
				success : function(result) {
					jQuery('#vocabularies-section').html(result);
				}
			});
		});
	});
</script>