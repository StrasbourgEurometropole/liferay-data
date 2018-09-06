<%@ include file="/search-asset-init.jsp"%>
<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-search-listing">
    <!-- File d'ariane -->
    <div class="pro-wrapper-top-listing">
        <div class="container">
            <div id="breadcrumb">
                <span>
                    <span>
                        <a href="${dc.getHomeURL()}"><liferay-ui:message key="eu.breadcrumb-home" /></a>
                        <span class="breadcrumb_last"><liferay-ui:message key="eu.breadcrumb-news" /></span>
                    </span>
                </span>
            </div>
            <div class="pro-wrapper-sort">
                <!-- Formulaire de recherche -->
                <aui:form action="${searchActionURL}" method="get" name="fm"
                    id="search-asset-form">
                    <liferay-portlet:renderURLParams varImpl="searchActionURL" />
                    <liferay-util:include page="/forms/placit-news-form.jsp"
                        servletContext="<%=application%>" />
                </aui:form>
            </div>
        </div>
    </div>
    <!-- Listing -->

    <aui:form method="post" name="fm">
        <section class="container pro-listing pro-listing-news" data-egalize=" > *">
        </section>
    </aui:form>

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

<style>
	.comment-portlet {
	    display: none !important;
	}
</style>