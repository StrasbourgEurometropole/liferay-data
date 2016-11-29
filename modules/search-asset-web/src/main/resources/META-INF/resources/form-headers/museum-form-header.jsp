<%@ include file="/search-asset-init.jsp" %>

<form id="keywords-deported-form">
	<aui:input type="text" name="deported-keywords" id="deported-keywords" value="${dc.keywords}" />
	<input type="submit" id="submit-search-form" value="<liferay-ui:message key="search" />" />
</form>