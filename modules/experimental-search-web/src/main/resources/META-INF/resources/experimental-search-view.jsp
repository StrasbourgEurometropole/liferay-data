<%@ include file="/experimental-search-init.jsp" %>

<c:choose>
	<c:when test="${empty template}">
		<liferay-ui:message key="no-config" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/templates/${template}.jsp" servletContext="<%=application %>" />
	</c:otherwise>
</c:choose>