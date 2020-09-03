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
                    <table>
                        <thead>
                            <tr>
                                <td width="25%"><liferay-ui:message key="title-and-ref" /></td>
                                <td width="25%"><liferay-ui:message key="code" /></td>
                                <td width="25%"><liferay-ui:message key="date" /></td>
                                <td width="25%"><liferay-ui:message key="statut" /></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="application" items="${dc.applicationPaginatedResults}">
                                <tr>
                                    <td></td>
                                    <td><a href="${application.url}">${application.codeSuivi}</a></td>
                                    <td>${application.date}</td>
                                    <td>${application.status}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>

                <ul class="mseu-pagination unstyled">
                    <li class="pagin-prev pagin-item">
                            <a class="btn-square bordered core" title="<liferay-ui:message key="see-offers" />"
                                href="${dc.searchOfferURL}">
                                <span class="flexbox">
                                    <span class="btn-text"><liferay-ui:message key="see-offers" /></span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                    </li>
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

                    <c:if test="${dc.applicationSearchContainer.total gt 0}">
                        <li class="pagin-next pagin-item">
                            <a class="btn-square bordered core" title="<liferay-ui:message key="see-all" />"
                                data-action="next" href="">
                                <span class="flexbox">
                                    <span class="btn-text"><liferay-ui:message key="see-all" /></span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </liferay-ui:search-container>
        </div>

        <p class="text">${dc.getTexte()}</p>

        <h2><liferay-ui:message key="my-alerts" /></h2>

        <div class="alertes">
            <!-- Liste des résultats -->

            <c:if test="${dc.alertSearchContainer.total == 0}">
                <liferay-ui:message key="no-alert" />
            </c:if>

            <liferay-ui:search-container id="alertSearchContainer"
                        searchContainer="${dc.alertSearchContainer}">
                <c:if test="${dc.alertSearchContainer.total gt 0}">
                    <table>
                        <thead>
                            <tr>
                                <td width="20%"><liferay-ui:message key="title" /></td>
                                <td width="60%"><liferay-ui:message key="keywords" /></td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="alert" items="${dc.alerts}">
                                <portlet:actionURL name="deleteAlert" var="deleteAlert">
                                    <portlet:param name="alertId" value="${alert.alertId}"></portlet:param>
                                </portlet:actionURL>

                                <tr>
                                    <td>${alert.name}</td>
                                    <td>${alert.keyWord}</td>
                                    <td>
                                        <a href="${deleteAlert}" class="delete-alert">Supprimer</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </liferay-ui:search-container>

            <ul class="mseu-pagination unstyled">
                <li class="pagin-next pagin-item">
                    <a class="btn-square bordered core" title="<liferay-ui:message key="create-another-alert" />"
                        data-action="next" href="${dc.searchOfferURL}">
                        <span class="flexbox">
                            <span class="btn-text"><liferay-ui:message key="create-another-alert" /></span>
                            <span class="btn-arrow"></span>
                        </span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>