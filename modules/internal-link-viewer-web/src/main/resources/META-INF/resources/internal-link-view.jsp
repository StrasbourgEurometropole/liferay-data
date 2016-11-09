<%@ include file="/internal-link-init.jsp"%>

<div class="internal-link-viewer useful-links">
	<h3 class="useful-links-title">${customPortletTitle}</h3>
	<ul>
		<c:forEach items="${selectedLayouts}" var="layout">
			<liferay-portlet:renderURL plid="${layout.plid}" var="layoutURL" />
			<li><a href="${layoutURL}" title="${layout.getName(locale)}">${layout.getName(locale)}</a></li>
		</c:forEach>
	</ul>
</div>