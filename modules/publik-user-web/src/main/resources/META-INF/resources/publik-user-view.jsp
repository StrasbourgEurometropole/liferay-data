<%@ include file="/publik-user-init.jsp" %>

<liferay-util:include page="/views/signataires.jsp" servletContext="<%=application %>" />


<liferay-util:body-top>
    <aui:script>
		var homeURL = '${homeURL}';
		var delta = ${dc.delta};
    </aui:script>
</liferay-util:body-top>
