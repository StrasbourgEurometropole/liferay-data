<%@ include file="/event-viewer-init.jsp" %>

<c:choose>
	<c:when test="${noconfig}">
	<liferay-ui:message key="no-configuration-yet" />
</c:when>
	<c:otherwise>
		<liferay-ddm:template-renderer
	    className="${className}"
	    contextObjects="${contextObjects}"
	    displayStyle="${displayStyle}"
	    displayStyleGroupId="${displayStyleGroupId}"
	    entries="${entries}"
			>
			<liferay-ui:message key="please-select-a-template" />
		</liferay-ddm:template-renderer>
	</c:otherwise>
</c:choose>
