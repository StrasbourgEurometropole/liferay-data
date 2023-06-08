<%@include file="/activity-course/activity-course-init.jsp" %>

<liferay-portlet:renderURL plid="${fromSearchPortlet ? classNameLayoutId[entry.modelClassName] : 0 }" var="detailURL" portletName="${detailPortletName}">
	<liferay-portlet:param name="classPK" value="${entry.activityCourseId}" />
</liferay-portlet:renderURL>
<c:set var="detailURL" value="${(fn:split(detailURL, '?'))[0]}" />

Vignette cours d'activité : <a href="${detailURL}">${entry.getName(locale)}</a>

Veuillez selectionner un modele dans la configuration du portlet
