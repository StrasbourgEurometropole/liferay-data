<%@include file="/edition-gallery/gallery-init.jsp" %>

<liferay-portlet:renderURL plid="${fromSearchPortlet ? classNameLayoutId[entry.modelClassName] : 0 }" var="detailURL" portletName="${detailPortletName}">
	<liferay-portlet:param name="classPK" value="${entry.galleryId}" />
</liferay-portlet:renderURL>
<c:set var="detailURL" value="${(fn:split(detailURL, '?'))[0]}" />

Vignette galerie edition : <a href="${detailURL}">${entry.getTitle(locale)}</a>

Veuillez selectionner un modele dans la configuration du portlet
