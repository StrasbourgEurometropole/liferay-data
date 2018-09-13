<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-search-listing">
    <div class="container">
        <div class="row">
            <div class="col-md-8 pro-bloc-listing-participation">
                <div id="breadcrumb">
                    <span>
                        <span><a href="${dc.getHomeURL()}"><liferay-ui:message key="eu.breadcrumb-home" /></a>
                            <span class="breadcrumb_last"><liferay-ui:message key="eu.breadcrumb-participation" /></span>
                        </span>
                    </span>
                </div>

                <div class="pro-wrapper-sort">
                    <span class="pro-legend"><liferay-ui:message key="eu.legend-participations" /></span>
                </div>

                <!-- Résultat -->
                <div class="row pro-wrapper-listing-participation">
                    <div class="col-xs-12">
                        <aui:form method="post" name="fm">
                            <div class="pro-listing-participation">
                            </div>
                        </aui:form>
                    </div>
                </div>
            </div>

            <div class="col-md-4 pro-wrapper-aside">
                <div class="pro-bloc-facette pro-bloc-facette-participation">
                    <span class="pro-affiner"><liferay-ui:message key="eu.refine-research" /> <span class="icon-ico-chevron-down"></span></span>

                    <!-- Formulaire -->
                    <aui:form action="${searchActionURL}" method="get" name="fm"
                        id="search-asset-form">
                        <liferay-portlet:renderURLParams varImpl="searchActionURL" />
                        <liferay-util:include page="/forms/placit-participations-form.jsp"
                            servletContext="<%=application%>" />
                    </aui:form>
                </div>


                <div class="pro-widget-participation">
                    <h4><liferay-ui:message key="eu.most.commented" /></h4>
                    <c:forEach var="participationMost" items="${participationListMostCommented}">
                        <a href="detail-participation.html" title="Lien vers Titre d'une participation">
                            <div class="pro-meta">
                                <span>${participationMost.getDistrictLabel(locale)}</span>
                                <span>${participationMost.getThematicsLabel(locale)}</span>
                            </div>
                            <h3>${participationMost.title}</h3>

                            <div class="pro-meta-footer">
                                <span class="pro-comments"><strong>${participationMost.getNbApprovedComments()}</strong>Commentaire(s)</span>
                                <div class="pro-avis">
                                    <span class="pro-like"><span class="icon-ico-like"></span> ${participationMost.nbLikes}</span>
                                    <span class="pro-dislike"><span class="icon-ico-like"></span> ${participationMost.nbDislikes}</span>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
                <div class="pro-widget-participation">
                    <h4><liferay-ui:message key="eu.less.commented" /></h4>
                    <c:forEach var="participationLess" items="${participationListLessCommented}">
                        <a href="detail-participation.html" title="Lien vers Titre d'une participation">
                            <div class="pro-meta">
                                <span>${participationLess.getDistrictLabel(locale)}</span>
                                <span>${participationLess.getThematicsLabel(locale)}</span>
                            </div>
                            <h3>${participationLess.title}</h3>
                            <div class="pro-meta-footer">
                                <span class="pro-comments"><strong>${participationLess.getNbApprovedComments()}</strong>Commentaire(s)</span>
                                <div class="pro-avis">
                                    <span class="pro-like"><span class="icon-ico-like"></span> ${participationLess.nbLikes}</span>
                                    <span class="pro-dislike"><span class="icon-ico-like"></span> ${participationLess.nbDislikes}</span>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <!-- Pagination -->
    <div class="pro-pagination">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-xs-4 pull-left">
                    <p class="hidden-xs"></p>
                </div>

                <!-- Pagination : liens de navigation -->
                <div class="col-sm-6 col-xs-8 pull-right">
                </div>
            </div>
        </div>
    </div>
</div>