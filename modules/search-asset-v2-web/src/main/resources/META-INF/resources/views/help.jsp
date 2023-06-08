<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-search-listing" id="go-to-top">
	<div class="container">
	    <div class="row">
			<!-- Blocs lateraux -->
			<div class="col-md-4 order-md-last pro-wrapper-aside">

				<!-- Bloc : formulaire -->
				<div class="pro-bloc-facette pro-bloc-facette-help">
					<span class="pro-affiner"><liferay-ui:message key="eu.refine-research" /> <span class="icon-ico-chevron-down"></span></span>

					<!-- Formulaire -->
					<aui:form action="${searchActionURL}" method="get" name="fm" id="search-asset-form">
						<liferay-portlet:renderURLParams varImpl="searchActionURL" />
						<liferay-util:include page="/forms/help-form.jsp" servletContext="<%=application%>" />
					</aui:form>

				</div>

			</div>

			<!-- Listing -->
	        <div class="col-md-8 pro-listing">

	            <div id="breadcrumb">
			        <span>
			            <span><a href="${dc.getHomeURL()}"><liferay-ui:message key="eu.breadcrumb-home" /></a>
			                <span class="breadcrumb_last"><liferay-ui:message key="eu.breadcrumb-help-request" /></span>
			            </span>
			        </span>
	            </div>

	            <!-- Affiner votre recherche - Tablette portrait + Mobile -->
                <div class="pro-inside-affine-search"></div>

	            <div class="row pro-wrapper-listing-general">
                    <div class="col-xs-12">

                        <!-- Resultats -->
                        <aui:form method="post" name="fm">
                            <div class="pro-listing-help"></div>
                        </aui:form>

                    </div>
                </div>

	        </div>
	    </div>
	</div>

	<!-- Pagination -->
	<div class="pro-pagination help-pagination">
	    <div class="container">
	        <div class="row">
	            <div class="col-sm-6 col-xs-4 pull-left">
	                <p class="hidden-xs"></p>
	            </div>

	            <!-- Pagination : liens de navigation -->
	            <div class="col-sm-6 col-xs-8 pull-right"></div>
	        </div>
	    </div>
	</div>

</div>