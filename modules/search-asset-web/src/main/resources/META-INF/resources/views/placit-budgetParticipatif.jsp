<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-search-listing">
     <div class="container">
        <div class="row">
            <div class="col-md-8 pro-bloc-listing-participation">
                <div id="breadcrumb">
		            <span>
		                <span><a href="${dc.getHomeURL()}"><liferay-ui:message key="eu.breadcrumb-home" /></a>
		                    <span class="breadcrumb_last"><liferay-ui:message key="eu.breadcrumb-budgetParticipatif" /></span>
		                </span>
		            </span>
                </div>

                 <div class="pro-wrapper-sort">
                    <c:choose>
                        <c:when test='${isUserloggedIn}'>
                            <a id="buttonDeposer" href="" class="pro-btn-yellow" data-toggle="modal" data-target="#modalPetition"><liferay-ui:message key="file-petition" /></a>
                        </c:when>
                        <c:otherwise>
                            <a id="buttonDeposer" href="" class="pro-btn-yellow" data-toggle="modal" data-target="#myModal"><liferay-ui:message key="file-petition" /></a>
                        </c:otherwise>
                    </c:choose>
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