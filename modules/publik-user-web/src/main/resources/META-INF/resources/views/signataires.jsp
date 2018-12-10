<%@ include file="/publik-user-init.jsp"%>
<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-wrapper-top-listing">
    <div class="container">
        <!-- File d'ariane -->
        <div id="breadcrumb">
            <span>
                <span>
                    <a href="${dc.getHomeURL()}"><liferay-ui:message key="eu.breadcrumb-home" /></a>
                    <span class="breadcrumb_last"><liferay-ui:message key="eu.breadcrumb-signataires" /></span>
                </span>
            </span>
        </div>
        <div class="pro-wrapper-sort">
            <!-- Formulaire de recherche -->
            <aui:form action="${searchActionURL}" method="get" name="fm"
                id="search-asset-form">
                <liferay-portlet:renderURLParams varImpl="searchActionURL" />
                <liferay-util:include page="/forms/signataires.jsp"
                    servletContext="<%=application%>" />
            </aui:form>
        </div>
    </div>
</div>

<!-- Listing -->
<aui:form method="post" name="fm">
    <div class="container">
        <div class="row pro-wrapper-signataire">
            <liferay-ui:search-container id="publikUsersSearchContainer"
                searchContainer="${dc.searchContainer}">
                <liferay-ui:search-container-results results="${dc.publikUsers}" />

                <liferay-ui:search-container-row
                    className="eu.strasbourg.service.oidc.model.PublikUser" modelVar="publikUser"
                    keyProperty="publikUserLiferayId" rowIdProperty="publikUserLiferayId">

                    <div class="col-md-6 pro-item-signataire">
                       <div>
                           <div class="pro-people">
                               <figure role="group">
                               		<img src="${publikUser.getImageURLOrSurrogate()}" width="75" height="75" alt="Image de profil"/>
                               </figure>
                               <div>
                                   <c:if test="${!empty publikUser.pactSignature}">
                                        <fmt:formatDate value="${publikUser.pactSignature}"
                                            var="formattedPactSignatureDate" type="date" pattern="dd/MM/yyyy' &agrave; 'HH'h'mm" />
                                       <span class="pro-time">
                                           Le <time datetime="${formattedPactSignatureDate}">${formattedPactSignatureDate}</time>
                                       </span>
                                   </c:if>
                                   <p class="pro-name">${publikUser.lastName} ${publikUser.firstName}</p>
                                   <span class="pro-fonction"></span>
                               </div>
                           </div>
                           <div class="pro-state-signature">
                               <span>
                                   A sign&eacute;
                               </span>
                           </div>
                       </div>
                   </div>

                </liferay-ui:search-container-row>
            </liferay-ui:search-container>

        </div>
    </div>
</aui:form>


<!-- Pagination -->
<div class="pro-pagination">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-xs-4 pull-left">

                <!-- Pagination : selecteur de page -->
                <c:if test="${dc.pager.lastPage > 1}">
                    <form action="/" method="get">
                        <label for="change-page" class="hide"
                            aria-labelledby="change-page" aria-hidden="true"
                            aria-label="change-page">Changer de page</label>
                            <select
                            id="change-page" name="change-page"
                            onchange="location = this.value;">
                            <c:forEach var="pageIndex" begin="1" end="${dc.pager.lastPage}">
                                <c:choose>
                                    <c:when test="${pageIndex != dc.pager.lastPage}">
                                        <option value="${dc.getURLForPage(pageIndex)}">
                                            <liferay-ui:message key="eu.page" /> ${pageIndex} (
                                            ${dc.pager.delta} )
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${dc.getURLForPage(pageIndex)}">
                                            <liferay-ui:message key="eu.page" /> ${pageIndex} (
                                            ${dc.pager.delta - ( dc.pager.lastPage * dc.pager.delta - dc.pager.count)}
                                            )
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </form>
                </c:if>

                <!-- Pagination : label -->
                <p class="hidden-xs">
                    <liferay-ui:message key="eu.show-results" />
                    <c:choose>
                        <c:when test="${dc.pager.count > 0}">
                                ${dc.pager.currentPage * dc.pager.delta - (dc.pager.delta - 1)}
                            </c:when>
                        <c:otherwise>
                                0
                            </c:otherwise>
                    </c:choose>
                    -
                    <c:choose>
                        <c:when test="${dc.pager.count < 1}">
                                0
                            </c:when>
                        <c:when test="${not dc.pager.onLastPage}">
                                ${dc.pager.currentPage * dc.pager.delta}
                            </c:when>
                        <c:when test="${dc.pager.onLastPage}">
                                ${dc.pager.currentPage * dc.pager.delta - (dc.pager.currentPage * dc.pager.delta - dc.pager.count)}
                            </c:when>
                    </c:choose>
                    <liferay-ui:message key="eu.among" />
                    ${dc.pager.count}.
                </p>
            </div>

            <!-- Pagination : liens de navigation -->
            <div class="col-sm-6 col-xs-8 pull-right">

                <c:if test="${dc.pager.lastPage > 1}">
                    <ul>

                        <!-- Lien vers la premiere page -->
                        <c:if test="${not dc.pager.onFirstPage}">
                            <li><a href="${dc.getURLForPage(1)}"
                                class="hidden-sm hidden-xs pro-first"
                                title="<liferay-ui:message key="eu.listing-link-to-first-page" />">
                                    <liferay-ui:message key="eu.first" />
                            </a></li>
                        </c:if>

                        <!-- Lien vers la page precedente page -->
                        <c:if test="${not dc.pager.onFirstPage}">
                            <li><a href="${dc.getURLForPage(dc.pager.currentPage - 1)}"
                                title="<liferay-ui:message key="eu.listing-link-to-previous-pag" />">
                                    <liferay-ui:message key="eu.previous" />
                            </a></li>
                        </c:if>

                        <!-- Lien vers la page suivante -->
                        <c:if test="${not dc.pager.onLastPage}">
                            <li><a href="${dc.getURLForPage(dc.pager.currentPage + 1)}"
                                title="<liferay-ui:message key="eu.listing-link-to-next-page" />">
                                    <liferay-ui:message key="eu.next" />
                            </a></li>
                        </c:if>

                        <!-- Lien vers la derniere page -->
                        <c:if test="${not dc.pager.onLastPage}">
                            <li><a href="${dc.getURLForPage(dc.pager.lastPage)}"
                                class="hidden-sm hidden-xs pro-last"
                                title="<liferay-ui:message key="eu.listing-link-to-last-page" />">
                                    <liferay-ui:message key="eu.last" />
                            </a></li>
                        </c:if>

                    </ul>
                </c:if>

            </div>
        </div>
    </div>
</div>

<script>
	// Change la valeur du selecteur de page par la valeur courante
	$(document).ready(function() {
		$('#change-page').prop('selectedIndex', ${dc.pager.currentPage - 1}).selectric('refresh');
	});
</script>