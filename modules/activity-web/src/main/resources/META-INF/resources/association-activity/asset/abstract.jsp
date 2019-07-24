<%@include file="/association-activity/association-activity-init.jsp" %>

<liferay-portlet:renderURL plid="${fromSearchPortlet ? classNameLayoutId[entry.modelClassName] : 0 }" var="detailURL" portletName="${detailPortletName}">
	<liferay-portlet:param name="classPK" value="${entry.associationActivityId}" />
</liferay-portlet:renderURL>
<c:set var="detailURL" value="${(fn:split(detailURL, '?'))[0]}" />

Vignette activité d'association : <a href="${detailURL}">${entry.getName(locale)}</a>

Veuillez selectionner un modele dans la configuration du portlet
