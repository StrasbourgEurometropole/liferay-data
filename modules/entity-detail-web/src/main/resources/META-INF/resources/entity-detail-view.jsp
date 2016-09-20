<%@ include file="/entity-detail-init.jsp" %>

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