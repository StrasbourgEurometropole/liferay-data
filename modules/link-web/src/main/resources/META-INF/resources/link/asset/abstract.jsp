<%@include file="/link/link-init.jsp" %>

<liferay-portlet:renderURL plid="${fromSearchPortlet ? classNameLayoutId[entry.modelClassName] : 0 }" var="detailURL" portletName="${detailPortletName}">
	<liferay-portlet:param name="classPK" value="${entry.linkId}" />
</liferay-portlet:renderURL>
<c:set var="detailURL" value="${(fn:split(detailURL, '?'))[0]}" />

Link (jsp abstract) : <a href="${detailURL}">${entry.getTitle(locale)}</a>
