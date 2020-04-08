<%@include file="/arret/arret-init.jsp" %>

<liferay-portlet:renderURL plid="${fromSearchPortlet ? classNameLayoutId[entry.modelClassName] : 0 }" var="detailURL" portletName="${detailPortletName}">
	<liferay-portlet:param name="classPK" value="${entry.arretId}" />
</liferay-portlet:renderURL>
<c:set var="detailURL" value="${(fn:split(detailURL, '?'))[0]}" />

Vignette lien : <a href="${detailURL}">${entry.getTitle()}</a>

Veuillez selectionner un modele dans la configuration du portlet
