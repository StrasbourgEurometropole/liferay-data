<header class="ops-fullpage-header ops-header-listing" data-vheight="100">
	<figure class="fit-cover">
		<img src="${image.getData()}" width="1600" height="900" alt="Orchestre"/>
	</figure>

	<!-- MISE EN AVANT - CONCERT -->
	<div class="ops-slider-highlight-saison">
		<!-- TITLE -->
		<div class="ops-content-wrapper ops-content-wrapper-large ops-title-page-listing"><h1>${title.getData()}</h1></div>

		<@liferay_portlet["runtime"]
		portletProviderAction=portletProviderAction.VIEW
		portletName="com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet"/>
		
	</div>
</header>