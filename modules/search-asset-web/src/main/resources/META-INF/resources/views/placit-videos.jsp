<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-wrapper-top-listing">
    <div class="container">
        <div id="breadcrumb">
        <span>
            <span><a href="${dc.getHomeURL()}"><liferay-ui:message key="eu.breadcrumb-home" /></a></a>
                <span class="breadcrumb_last"><liferay-ui:message key="eu.breadcrumb-listing-video" /></span>
            </span>
        </span>
        </div>
    </div>
</div>

<div class="pro-search-listing">
    <section class="container">
        <div class="row">
            <div class="col-md-3 pro-bloc-facette">
                <span class="pro-affiner"><liferay-ui:message key="eu.refine-research" /> <span class="icon-ico-chevron-down"></span></span>

                <!-- Formulaire de recherche -->
                <aui:form action="${searchActionURL}" method="get" name="fm"
                    id="search-asset-form">
                    <liferay-portlet:renderURLParams varImpl="searchActionURL" />
                    <liferay-util:include page="/forms/placit-videos-form.jsp"
                        servletContext="<%=application%>" />
                </aui:form>
            </div>

            <!-- Résultat -->
            <aui:form method="post" name="fm">
                <div class="col-md-9 pro-wrapper-listing-video">
                    <div class="pro-wrapper">
                        <h2><liferay-ui:message key="eu.all-videos" /></h2>
                        <div class="pro-sort pro-dropdown">
                            <a id="sortType" href="#">Tri A-Z</a>
                            <ul>
                                <li><a href="javascript:sortVideo('asc');">Tri A-Z</a></li>
                                <li><a href="javascript:sortVideo('desc');">Tri Z-A</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="row pro-listing-video" data-egalize="> * > a">
                    </div>
                </div>

            </aui:form>
        </div>
    </section>

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