<%@ include file="/entity-detail-init.jsp" %>

<c:if test="${not empty entry}">
	<liferay-ddm:template-renderer
	    className="${entry.className}"
	    contextObjects="${contextObjects}"
	    displayStyle="${displayStyle}"
	    displayStyleGroupId="${displayStyleGroupId}"
	    entries="${entries}"
	>
		<liferay-ui:asset-display
			assetEntry="${entry}"
			assetRenderer="${entry.assetRenderer}"
			assetRendererFactory="${entry.assetRendererFactory}"
			template="full_content"
		/>
		
	</liferay-ddm:template-renderer>
</c:if>
<c:if test="${empty entry}">
	<p>Entit&eacute; inconnue</p>
</c:if>