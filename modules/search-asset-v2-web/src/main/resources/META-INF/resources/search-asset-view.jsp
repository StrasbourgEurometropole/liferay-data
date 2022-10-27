<%@ include file="/search-asset-init.jsp" %>

<liferay-util:include page="/views/${dc.searchForm}.jsp" servletContext="<%=application %>" />


<liferay-util:body-top>
    <aui:script>
		var homeURL = '${homeURL}';
		var delta = ${dc.delta};
    </aui:script>
</liferay-util:body-top>
