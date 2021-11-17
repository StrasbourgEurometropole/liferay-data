<%@include file="/notification/notification-init.jsp" %>

<%-- Champ : Service  --%>
<h3><liferay-ui:message key="eu.strasbourg.notif.service" /></h3>
<p>${service}</p>

<%-- Champ : Alerte  --%>
<h3><liferay-ui:message key="eu.strasbourg.notif.alerte" /></h3>
<p>${entry.isAlert == 1 ? 'Oui' : 'Non'}</p>

<%-- Champ : Nature  --%>
<h3><liferay-ui:message key="eu.strasbourg.notif.nature" /></h3>
<p>${nature}</p>

<%-- Champ : Date de diffusion --%>
<fmt:formatDate value="${entry.broadcastDate}"
    var="formattedbroadcastDate" type="date" pattern="dd/MM/yyyy" />
<h3><liferay-ui:message key="eu.strasbourg.notif.broadcast-date" /></h3>
<p>${formattedbroadcastDate}</p>

<%-- Champ : Titre --%>
<h3><liferay-ui:message key="eu.strasbourg.notif.title" /></h3>
<p>${entry.getTitle(locale)}</p>

<%-- Champ : Sous-Titre --%>
<c:if test='${not empty entry.getSubtitle(locale)}'>
    <h3><liferay-ui:message key="eu.strasbourg.notif.subtitle" /></h3>
    <p>${entry.getSubtitle(locale)}</p>
</c:if>

<%-- Champ : Date de début --%>
<fmt:formatDate value="${entry.startDate}"
    var="formattedstartDate" type="date" pattern="dd/MM/yyyy" />
<h3><liferay-ui:message key="eu.strasbourg.notif.start-date" /></h3>
<p>${formattedstartDate}</p>

<%-- Champ : Date de fin --%>
<fmt:formatDate value="${entry.endDate}"
    var="formattedendDate" type="date" pattern="dd/MM/yyyy" />
<h3><liferay-ui:message key="eu.strasbourg.notif.end-date" /></h3>
<p>${formattedendDate}</p>

<%-- Champ : Message --%>
<h3><liferay-ui:message key="eu.strasbourg.notif.message" /></h3>
<p>${entry.getContent(locale)}</p>

<%-- Champ : Label de l'URL --%>
<c:if test='${not empty entry.getLabelUrl(locale)}'>
    <h3><liferay-ui:message key="eu.strasbourg.notif.label-url" /></h3>
    <p>${entry.getLabelUrl(locale)}</p>
</c:if>

<%-- Champ : URL --%>
<c:if test='${not empty entry.getUrl(locale)}'>
    <h3><liferay-ui:message key="eu.strasbourg.notif.url" /></h3>
    <p>${entry.getUrl(locale)}</p>
</c:if>

<%-- Champ : Type de diffusion --%>
<h3><liferay-ui:message key="eu.strasbourg.notif.broadcast-type" /></h3>
<p>${broadcastType}</p>

<%-- Champ : Canaux de diffusion --%>
<h3><liferay-ui:message key="eu.strasbourg.notif.broadcast-channels" /></h3>
<p>${broadcastChannels}</p>