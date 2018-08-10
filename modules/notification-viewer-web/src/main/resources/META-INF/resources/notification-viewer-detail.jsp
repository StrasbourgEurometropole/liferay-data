<%@ include file="./notification-viewer-init.jsp"%>

<c:set var="locale" value="${themeDisplay.getLocale() }" />
<fmt:formatDate value="${notification.publicationDate}"
var="datePubli" type="date" pattern="dd/MM/yyyy" />

<p class="seu-published">
	<liferay-ui:message key="eu.published-on" /> ${datePubli}
</p>
<h1>${notification.getTitle(locale)}</h1>

<div class="rte">
    ${notification.getDescription(locale)}
</div>
