<%@ include file="/offer-init.jsp" %>

<div class="page-offre seu-container">
    <div class="generic-form">
        <h1><liferay-ui:message key="my-offers" /></h1>
        <p class="intro">${dc.getIntro()}</p>

        <h2><liferay-ui:message key="my-applications" /></h2>

        <div class="candidatures">
            <!-- Liste des résultats -->

            <c:if test="${dc.applicationSearchContainer.total == 0}">
                <liferay-ui:message key="no-application" />
            </c:if>
            <liferay-ui:search-container id="applicationsSearchContainer"
                        searchContainer="${dc.applicationSearchContainer}">
                <c:if test="${dc.applicationSearchContainer.total gt 0}">
                    <div class="candidaturesList">
                        <div class="candidatureInfo titre"><liferay-ui:message key="title-and-ref" /></div>
                        <div class="candidatureInfo code"><liferay-ui:message key="code" /></div>
                        <div class="candidatureInfo date"><liferay-ui:message key="date" /></div>
                        <div class="candidatureInfo statut"><liferay-ui:message key="statut" /></div>
                        <div class="candidatureInfo pdf"><liferay-ui:message key="annonce" /></div>
                        <c:forEach var="application" items="${dc.applicationPaginatedResults}">
                            <div class="candidatureDetail titre">${application.refPoste} ${application.libPoste}</div>
                            <div class="candidatureDetail code"><a href="${application.url}">${application.codeSuivi}</a></div>
                            <div class="candidatureDetail date">${application.date}</div>
                            <div class="candidatureDetail statut">${application.status}</div>
                            <div class="candidatureDetail pdf">
                                <liferay-portlet:actionURL name="exportPDF" var="exportPDFURL">
                                    <portlet:param name="refPoste" value="${application.refPoste}" />
                                </liferay-portlet:actionURL>
                                <a href="${exportPDFURL}" target="_blank"><liferay-ui:message key="pdf" /></a>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <div class="candidaturePagination">
                    <a class="btn-square bordered core" title="<liferay-ui:message key="see-offers" />"
                        href="${dc.searchOfferURL}">
                        <span class="flexbox">
                            <span class="btn-text"><liferay-ui:message key="see-offers" /></span>
                            <span class="btn-arrow"></span>
                        </span>
                    </a>

                    <ul class="mseu-pagination unstyled">
                        <c:if test="${dc.applicationPager.lastPage > 1}">
                            <!-- Pagination -->
                            <c:forEach var="page" items="${dc.applicationPager.pages}">
                                <c:choose>
                                    <c:when test="${page.isALink() and not (page.index eq dc.applicationPager.currentPage)}">
                                        <!-- Lien vers page -->
                                        <li class="pagin-item">
                                            <a data-page="${page.index}" href="${dc.getApplicationURLForPage(page.index)}">
                                                <span class="flexbox">
                                                    <span class="btn-text">${page.label}</span>
                                                </span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:when test="${page.isALink() and (page.index eq dc.applicationPager.currentPage)}">
                                        <!-- Page en cours -->
                                        <li class="pagin-item is-active">
                                            <span class="flexbox">
                                                <span class="btn-text">${page.label}</span>
                                            </span>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Texte -->
                                        <li class="pagin-item">
                                            <span class="flexbox">
                                                <span class="btn-text">${page.label}</span>
                                            </span>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                    </ul>

                    <c:if test="${dc.applicationSearchContainer.total gt 0}">
                        <a class="btn-square bordered core" title="<liferay-ui:message key="see-all" />"
                            data-action="next" href="${dc.proceduresURL}">
                            <span class="flexbox">
                                <span class="btn-text"><liferay-ui:message key="see-all" /></span>
                                <span class="btn-arrow"></span>
                            </span>
                        </a>
                    </c:if>
                </div>
            </liferay-ui:search-container>
        </div>

        <h2><liferay-ui:message key="my-alerts" /></h2>
        <div class="alertes">
            <p class="text">${dc.getTexte()}</p>
        </div>
    </div>
</div>


<style>
    .candidatureDetail.titre:before{
        content:"<liferay-ui:message key="title-and-ref" />";
    }
    .candidatureDetail.code:before{
        content:"<liferay-ui:message key="code" />";
    }
    .candidatureDetail.date:before{
        content:"<liferay-ui:message key="date" />";
    }
    .candidatureDetail.statut:before{
        content:"<liferay-ui:message key="statut" />";
    }
    .candidatureDetail.pdf:before{
        content:"<liferay-ui:message key="annonce" />";
    }

    .alerteDetail.titre:before{
        content:"<liferay-ui:message key="title" />";
    }
    .alerteDetail.filtre:before{
        content:"<liferay-ui:message key="keywords-categories" />";
    }
</style>