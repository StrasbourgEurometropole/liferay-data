<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-wrapper-top-listing" id="go-to-top">
    <div class="container">
        <div id="breadcrumb">
        <span>
            <span><a href="${dc.getHomeURL()}"><liferay-ui:message key="eu.breadcrumb-home" /></a>
                <span class="breadcrumb_last"><liferay-ui:message key="agenda" /></span>
            </span>
        </span>
        </div>
    </div>
</div>

<div class="pro-search-listing">
    <section class="container">
        <div class="row pro-listing-container" data-egalize=" > *">
            <div class="col-md-3 pro-bloc-facette">
                <span class="pro-affiner"><liferay-ui:message key="eu.refine-research" /> <span class="icon-ico-chevron-down"></span></span>

                <!-- Formulaire -->
                <aui:form action="${searchActionURL}" method="get" name="fm"
                    id="search-asset-form">
                    <liferay-portlet:renderURLParams varImpl="searchActionURL" />
                    <liferay-util:include page="/forms/placit-agenda-form.jsp"
                        servletContext="<%=application%>" />
                </aui:form>

            </div>

            <div class="col-md-5 pro-bloc-listing-event">

                <!-- Resultats -->
                <aui:form method="post" name="fm">
                    <div class="pro-listing-agenda">
                    </div>
                </aui:form>
            </div>

            <div class="col-md-4">
                <div class="pro-listing-map">
                    <div class="pro-fixed-map">
                        <div class="wrapper-map leaflet-map" id="mapid"></div>
                    </div>
                </div>
            </div>

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