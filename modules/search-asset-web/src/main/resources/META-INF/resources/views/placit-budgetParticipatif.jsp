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
                            <a id="buttonDeposer" href="" class="pro-btn-yellow" data-toggle="modal" data-target="#modalBudget"><liferay-ui:message key="file-budget" /></a>
                        </c:when>
                        <c:otherwise>
                            <a id="buttonDeposer" href="" class="pro-btn-yellow" data-toggle="modal" data-target="#myModal"><liferay-ui:message key="file-budget" /></a>
                        </c:otherwise>
                    </c:choose>
 				</div>
            </div>
        </div>
    </div>
</div>