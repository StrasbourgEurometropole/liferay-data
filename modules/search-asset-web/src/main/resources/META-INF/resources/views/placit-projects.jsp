<%@ include file="/search-asset-init.jsp"%>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-wrapper-top-listing">
	<div class="container">
		<div id="breadcrumb">
			<span> <span><a href="/">Accueil</a> <span
					class="breadcrumb_last">Listing des projets</span> </span>
			</span>
		</div>
	</div>
</div>


<section class="container">
	<div class="row">
		<div class="col-md-3 pro-bloc-facette">
			<span class="pro-affiner">Afficher votre recherche <span
				class="icon-ico-chevron-down"></span></span>

			<!-- Formulaire -->
			<aui:form action="${searchActionURL}" method="get" name="fm"
				id="search-asset-form">
				<liferay-portlet:renderURLParams varImpl="searchActionURL" />
				<liferay-util:include page="/forms/placit-projects-form.jsp"
					servletContext="<%=application%>" />
			</aui:form>
		</div>
		<aui:form method="post" name="fm">
			<liferay-ui:search-container id="entriesSearchContainer"
				searchContainer="${dc.searchContainer}">

				<div class="col-md-9 pro-wrapper-listing-video">
					<div class="pro-wrapper">
						<h2>Tous les projets</h2>
						<div class="pro-sort pro-dropdown">
							<a href="#">Tri A-Z</a>
							<ul>
								<li><a href="#">Esplanade</a></li>
								<li><a href="#">Gare</a></li>
							</ul>
						</div>
					</div>
					<div class="row pro-listing-video" data-egalize="> * > a">

						<liferay-ui:search-container-results results="${dc.entries}" />
						<liferay-ui:search-container-row
							className="com.liferay.asset.kernel.model.AssetEntry"
							modelVar="entry" keyProperty="entryId" rowIdProperty="entryId">
							<c:set var="className" value="${entry.className}" />

							<liferay-ddm:template-renderer className="${className}"
								contextObjects="${dc.getTemplateContextObjects(entry)}"
								displayStyle="${dc.templatesMap[entry.className]}"
								displayStyleGroupId="${themeDisplay.scopeGroupId}"
								entries="${dc.templateEntries }">
								<liferay-ui:asset-display assetEntry="${entry}"
									assetRenderer="${entry.assetRenderer}"
									assetRendererFactory="${entry.assetRendererFactory}"
									template="abstract" />
							</liferay-ddm:template-renderer>

						</liferay-ui:search-container-row>
					</div>
				</div>

			</liferay-ui:search-container>
		</aui:form>
	</div>
</section>