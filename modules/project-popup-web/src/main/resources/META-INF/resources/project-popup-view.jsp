<%@ include file="/project-popup-init.jsp" %>

<p>
	<b><liferay-ui:message key="projectpopup.caption"/></b>
</p>

<liferay-util:include page="/modal/${popupTemplateId}.jsp" servletContext="<%=application %>" />