<%@include file="/offer/offer-init.jsp" %>

<%-- Champ : Numero de publication --%>
<h3><liferay-ui:message key="eu.offer-publication-id" /></h3>
<p>${entry.getPublicationId()}</p>

<%-- Champ : Type de recrutement   --%>
<h3><liferay-ui:message key="eu.offer-type-recrutement" /></h3>
<p>${entry.typeRecrutement.getTitle(locale)}</p>

<%-- Champ : Interne/Externe--%>
<h3><liferay-ui:message key="eu.offer-type-publication" /></h3>
<p>${entry.typePublication.getTitle(locale)}</p>

<%-- Champ : Numero de poste --%>
<c:if test="${not empty entry.getPostNumber()}" >
    <h3><liferay-ui:message key="eu.offer-post-number" /></h3>
    <p>${entry.postNumber}</p>
</c:if>

<%-- Champ : Nom de la personne remplacée ou création de poste --%>
<c:if test="${not empty entry.getJobCreationDescription()}" >
    <h3><liferay-ui:message key="eu.offer-job-creation-description" /></h3>
    <p>${entry.getJobCreationDescription(locale)}</p>
</c:if>

<%-- Champ : Date de départ  --%>
<c:if test="${not empty entry.getStartDate()}" >
    <fmt:formatDate value="${entry.startDate}"
        var="formattedStartDate" type="date" pattern="dd/MM/yyyy" />
    <h3><liferay-ui:message key="eu.offer-start-date" /></h3>
    <p>${formattedStartDate}</p>
</c:if>

<%-- Champ : Motif  --%>
<c:if test="${not empty entry.getMotif()}" >
    <h3><liferay-ui:message key="eu.offer-motif" /></h3>
    <p>${entry.getMotif(locale)}</p>
</c:if>

<%-- Champ : Si contrat permanent
<c:if test="${not empty entry.getPermanentDescription(locale)}" >
    <h3><liferay-ui:message key="eu.offer-permanent-description" /></h3>
    <p>${entry.getPermanentDescription(locale)}</p>
</c:if>

<%-- Champ : Durée du contrat  --%>
<c:if test="${not empty entry.getDuration(locale)}" >
    <h3><liferay-ui:message key="eu.offer-duree-contrat" /></h3>
    <p>${entry.getDuration(locale)}</p>
</c:if>

<%-- Champ : Intitulé du poste  --%>
<h3><liferay-ui:message key="eu.offer-post" /></h3>
<p>${entry.getPost(locale)}</p>

<%-- Champ : Direction  --%>
<h3><liferay-ui:message key="eu.offer-direction" /></h3>
<p>${entry.direction.getTitle(locale)}</p>

<%-- Champ : Service  --%>
<c:if test="${not empty entry.service}" >
    <h3><liferay-ui:message key="eu.offer-service" /></h3>
    <p>${entry.service.getTitle(locale)}</p>
</c:if>

<%-- Champ : Temps complet ou non   --%>
<c:if test="${not empty entry.isFullTime}" >
    <h3><liferay-ui:message key="eu.offer-is-full-time" /></h3>
    <c:if test="${entry.isFullTime}" >
        <p><liferay-ui:message key="eu.offer-full-time-true" /></p>
    </c:if>
    <c:if test="${!entry.isFullTime}" >
        <p><liferay-ui:message key="eu.offer-full-time-false" /></p>
    </c:if>
    <%-- Champ : Si temps complet   --%>
    <c:if test="${not empty entry.getFullTimeDescription(locale)}" >
        <h3><liferay-ui:message key="eu.offer-full-time-description" /></h3>
        <p>${entry.getFullTimeDescription(locale)}</p>
    </c:if>
</c:if>

<%-- Champ : Choix des catégories, des filières et des grades   --%>
<c:set var="gradeRanges" value="${entry.gradeRanges}" />
<c:if test="${not empty gradeRanges}" >
    <c:forEach items="${gradeRanges}" var="gradeRange">
        <div style="border: solid 1px; padding: 10px;">
            <h4><liferay-ui:message key="eu.offer-category" /></h4>
            <p>
                ${gradeRange[0].getTitle(locale)}
            </p>

            <h4><liferay-ui:message key="eu.offer-filiere" /></h4>
            <p>
                ${gradeRange[1].getTitle(locale)}
            </p>

            <h4><liferay-ui:message key="eu.offer-grade-min" /></h4>
            <p>
                ${gradeRange[2].getTitle(locale)}
            </p>

            <h4><liferay-ui:message key="eu.offer-grade-max" /></h4>
            <p>
                ${gradeRange[3].getTitle(locale)}
            </p>
        </div>
    </c:forEach>
</c:if>

<%-- Champ : Niveau d'étude   --%>
<c:if test="${not empty entry.niveauEtude}" >
    <h3><liferay-ui:message key="eu.offer-niveau-etude" /></h3>
    <p>${entry.niveauEtude.getTitle(locale)}</p>
</c:if>

<%-- Champ : Introduction --%>
<h3><liferay-ui:message key="eu.offer-introduction" /></h3>
${entry.getIntroduction(locale)}

<%-- Champ : Activités --%>
<h3><liferay-ui:message key="eu.offer-activities" /></h3>
${entry.getActivities(locale)}

<%-- Champ : Profil --%>
<h3><liferay-ui:message key="eu.offer-profil" /></h3>
${entry.getProfil(locale)}

<%-- Champ : Condition d'exercice --%>
<h3><liferay-ui:message key="eu.offer-conditions" /></h3>
${entry.getConditions(locale)}

<%-- Champ : Avantages liés au poste --%>
<c:if test="${not empty entry.avantages}" >
    <h3><liferay-ui:message key="eu.offer-avantages" /></h3>
    ${entry.getAvantages(locale)}
</c:if>

<%-- Champ : Famille de métiers --%>
<c:if test="${not empty entry.famille}" >
    <h3><liferay-ui:message key="eu.offer-famille-metier" /></h3>
    <p>${entry.famille.getTitle(locale)}</p>
</c:if>

<%-- Champ : Date limite de candidatures --%>
<fmt:formatDate value="${entry.limitDate}"
    var="formattedLimitDate" type="date" pattern="dd/MM/yyyy" />
<h3><liferay-ui:message key="eu.offer-limit-date" /></h3>
<p>${formattedLimitDate}</p>

<%-- Champ : Nom du RE + contact téléphonique  --%>
<c:if test="${not empty entry.contactRE}" >
    <h3><liferay-ui:message key="eu.offer-nom-RE" /></h3>
    <p>${entry.contactRE.getTitle(locale)}</p>
</c:if>

<%-- Champ : Nom du RRH + contact téléphonique --%>
<c:if test="${not empty entry.contact}" >
    <h3><liferay-ui:message key="eu.offer-nom-RRH" /></h3>
    <p>${entry.contact}</p>
</c:if>

<%-- Champ : Envoi par mail de l’offre (lien + PDF) --%>
<c:if test="${not empty entry.emails}" >
    <h3><liferay-ui:message key="eu.offer-email" /></h3>
    <p>${entry.emails}</p>
</c:if>

<%-- Champ : Champ partage Linkedin  --%>
<h3><liferay-ui:message key="eu.offer-linkedin" /></h3>
<p>${entry.shareLinkedin?"oui":"non"}</p>

<%-- Champ : Date de début de publication d’une offre (programmation) --%>
<fmt:formatDate value="${entry.publicationStartDate}"
    var="formattedPublicationStartDate" type="date" pattern="dd/MM/yyyy" />
<h3><liferay-ui:message key="eu.offer-publication-start-date" /></h3>
<p>${formattedPublicationStartDate}</p>

<%-- Champ : Date de fin de publication d’une offre (programmation) --%>
<fmt:formatDate value="${entry.publicationEndDate}"
    var="formattedPublicationEndDate" type="date" pattern="dd/MM/yyyy" />
<h3><liferay-ui:message key="eu.offer-publication-end-date" /></h3>
<p>${formattedPublicationEndDate}</p>