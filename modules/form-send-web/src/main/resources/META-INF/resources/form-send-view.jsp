<%@ include file="/form-send-init.jsp" %>
<fmt:setLocale value="${locale}" />

<main class="seu-container">
    <div class="seu-view-plu" id="seu-plu">
        <c:if test="${dc.form != null}">
            <h1>${dc.title}</h1>
            <!-- Nombre de résultats -->
            <c:if test="${dc.searchContainer.total == 0}">
                <div class="seu-view-results">
                    <div class="seu-result-count">
                        Pas d'entr&eacute;es pour "${dc.title}".
                    </div>
                </div>
            </c:if>

            <c:if test="${dc.searchContainer.total gt 0}">
                <!-- Liste des résultats -->
                <c:set var="fieldsToShow" value="${dc.fieldsToShow}" />
                <aui:form method="post" name="fm">
                    <liferay-ui:search-container id="entriesSearchContainer"
                                searchContainer="${dc.searchContainer}">
                        <div class="forms">
                            <c:forEach items="${dc.paginatedResults}" var="record">
                                <div class="formSend">
                                    <fmt:formatDate value="${record.createDate}" type="date" var="createDate" pattern="dd/MM/yyyy" />
                                    <fmt:formatDate value="${record.createDate}" type="date" var="createHeure" pattern="HH:mm" />
                                    <h2 class="dateForm">Le ${createDate} &agrave; ${createHeure}</h2>
                                    <c:forEach var="recordField" items="${dc.getRecordFields(record.getDDMStorageId(), locale)}">
                                        <c:forEach var="field" items="${recordField}">
                                            <c:if test="${fn:contains(dc.fieldsToShow, field.key) && not empty field.value && field.value != '[]'}">
                                                <div style="margin-bottom: 20px; ">
                                                    <label>${dc.getLabel(field.key, locale)}</label><br>
                                                    <c:set var="type" value="${dc.getFieldType(field.key)}" />
                                                    <c:choose>
                                                        <c:when test="${type.equals('paragraph')}">
                                                            ${field.value}
                                                        </c:when>
                                                        <c:when test="${type.equals('text')}">
                                                            ${fn:toUpperCase(fn:substring(field.value, 0, 1))}${fn:toLowerCase(fn:substring(field.value, 1,fn:length(field.value)))}
                                                        </c:when>
                                                        <c:when test="${type.equals('select')}">
                                                            <c:if test="${!dc.isMultiple(field.key)}">
                                                                <c:forEach var="option" items="${dc.getOptions(field.key)}">
                                                                    <c:if test="${fn:contains(field.value, option.value)}">
                                                                        ${option.getLabel(locale)}
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                            <c:if test="${dc.isMultiple(field.key)}">
                                                                <c:set var="status" value="0" />
                                                                <c:forEach var="option" items="${dc.getOptions(field.key)}">
                                                                    <c:if test="${fn:contains(field.value, option.value)}">
                                                                        <c:set var="status" value="${status + 1}" />
                                                                        <c:if test="${status > 1}">
                                                                            <br>
                                                                        </c:if>
                                                                        <input type="checkbox" disabled checked> ${option.getLabel(locale)}
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${type.equals('radio')}">
                                                            <c:forEach var="option" items="${dc.getOptions(field.key)}">
                                                                <c:if test="${fn:contains(field.value, option.value)}">
                                                                    ${option.getLabel(locale)}
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:when test="${type.equals('date')}">
                                                            <fmt:parseDate value="${field.value}" pattern="yyyy-MM-dd" var="dateValue" type="both" />
                                                            <fmt:formatDate value="${dateValue}" type="date" var="newDateValue" pattern="dd/MM/yyyy" />
                                                            ${newDateValue}
                                                        </c:when>
                                                        <c:when test="${type.equals('checkbox')}">
                                                            <c:if test="${field.value}">
                                                                Oui
                                                            </c:if>
                                                            <c:if test="${!field.value}">
                                                                Non
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${type.equals('checkbox_multiple')}">
                                                            <c:set var="status" value="0" />
                                                            <c:forEach var="option" items="${dc.getOptions(field.key)}">
                                                                <c:if test="${fn:contains(field.value, option.value)}">
                                                                    <c:set var="status" value="${status + 1}" />
                                                                    <c:if test="${status > 1}">
                                                                        <br>
                                                                    </c:if>
                                                                    <input type="checkbox" disabled checked> ${option.getLabel(locale)}
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </div>

                        <!-- Pagination -->
                        <c:if test="${dc.pager.lastPage > 1}">
                            <ul class="seu-pagination unstyled">
                                <!-- Page précédente -->
                                <li class="seu-pagin-prev seu-pagin-item">
                                    <c:if test="${not dc.pager.onFirstPage}">
                                        <a class="seu-btn-square seu-bordered seu-core" data-action="prev" title="<liferay-ui:message key="go-to-previous-page" />"
                                           href="${dc.getURLForPage(dc.pager.currentPage - 1)}">
                                            <span class="seu-flexbox">
                                                <span class="seu-btn-text"><liferay-ui:message key="previous" /></span>
                                                <span class="seu-btn-arrow"></span>
                                            </span>
                                        </a>
                                    </c:if>
                                </li>

                                <c:forEach var="page" items="${dc.pager.pages}">
                                    <c:choose>
                                        <c:when test="${page.isALink() and not (page.index eq dc.pager.currentPage)}">
                                            <!-- Lien vers page -->
                                            <li class="seu-pagin-item">
                                                <a data-page="${page.index}" href="${dc.getURLForPage(page.index)}">
                                                    <span class="seu-flexbox">
                                                        <span class="seu-btn-text">${page.label}</span>
                                                    </span>
                                                </a>
                                            </li>
                                        </c:when>
                                        <c:when test="${page.isALink() and (page.index eq dc.pager.currentPage)}">
                                            <!-- Page en cours -->
                                            <li class="seu-pagin-item seu-is-active">
                                                <span class="seu-flexbox">
                                                    <span class="seu-btn-text">${page.label}</span>
                                                </span>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Texte -->
                                            <li class="seu-pagin-item">
                                                <span class="seu-flexbox">
                                                    <span class="seu-btn-text">${page.label}</span>
                                                </span>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <!-- Page suivante -->
                                <li class="seu-pagin-next seu-pagin-item">
                                    <c:if test="${not dc.pager.onLastPage}">
                                        <a class="seu-btn-square seu-bordered seu-core" title="<liferay-ui:message key="go-to-next-page" />"
                                        data-action="next" href="${dc.getURLForPage(dc.pager.currentPage + 1)}">
                                            <span class="seu-flexbox">
                                                <span class="seu-btn-text"><liferay-ui:message key="next" /></span>
                                                <span class="seu-btn-arrow"></span>
                                            </span>
                                        </a>
                                    </c:if>
                                </li>
                            </ul>
                        </c:if>
                    </liferay-ui:search-container>
                </aui:form>
            </c:if>
        </c:if>
    </div>
</main>

<style>

    .seu-result-count{
        margin-bottom: 50px;
    }

    .forms{
        margin-bottom: 50px;
    }

    .dateForm{
        text-align: center;
        font-weight: 700;
    }

    .formSend{
        position: relative;
    }

    .forms .formSend + .formSend {
        margin-top: 40px;
        padding-top: 40px;
    }

    .forms .formSend + .formSend:before {
        content: '';
        position: absolute;
        top: 0;
        left: -50px;
        width: calc(100% + 100px);
        height: 1px;
        background-color: #ddd;
    }

    @media only screen and (max-width: 767px){
        .forms .formSend + .formSend:before {
            left: 0;
            width: 100%;
        }
    }

    label{
        font-weight: 600;
        color: #505050;
    }

    .seu-pagination {
        position: relative;
        margin-bottom: 60px;
    }

</style>