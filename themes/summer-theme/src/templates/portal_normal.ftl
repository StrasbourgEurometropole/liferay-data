<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
	
	<link href='https://fonts.googleapis.com/css?family=Lato:400,700,900,300' rel='stylesheet' type='text/css'>
	
	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class} site-ete">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />
<@liferay.control_menu />
	<div class="home">
		<div class="wrapper">
			<div class="header-back">
				<header class="header header-${locale.language}">
					<nav class="nav-lang">
						<div class="center">
							<a href="http://www.strasbourg.eu/" title="Strasbourg.eu (nouvelle fenêtre)" class="logo-strasbourg" target="_blank"> <img src="/o/summer-theme/images/2014/logo_strasbourg.png" alt="Strasbourg.eu"> </a>
							<ul>
								<li <#if locale.language != "fr">style="display:none;"</#if> >
									<a accesskey="1" href="http://ete.strasbourg.eu/evenement">
										Proposer un évènement
									</a>
								</li>
								<li <#if locale.language == "fr"> class="active" </#if> ><a accesskey="1" href="/fr/web/ete${layout.friendlyURL}" title="Français">FR</a></li>
								<li <#if locale.language == "de"> class="active" </#if> ><a accesskey="1" href="/de/web/ete${layout.friendlyURL}" title="Deutsch">DE</a></li>
								<li <#if locale.language == "en"> class="active" </#if> ><a accesskey="1" href="/en/web/ete${layout.friendlyURL}" title="English">EN</a></li>
							</ul>
							<div class="clearfix"></div>
						</div>
					</nav>
					<h1 class="logo logo-home 
						<#if locale.language == "en"> logo-en </#if> 
						<#if locale.language == "de"> logo-de </#if>">
						<a href="/web${layout.group.friendlyURL}"></a>
					</h1>
					
					<#if layout.getGroup().isStagingGroup()>
						<style>
							.portlet-borderless-bar {
								display: none;
							}
						</style>
					</#if>
					<#include "${full_templates_path}/navigation.ftl" />
				</header>
			</div>
			<div class="breadcrumbs-bar">
				<@liferay.breadcrumbs />
			</div>
			<div id="content">
				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}

					${portletDisplay.setTitle(the_title)}

					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>

		    	<div class="clearfix"></div>
				<div class="btn-top" title="<@liferay_ui.message key="back-to-top" />"></div>
			</div>
					
			<footer class="footer" role="contentinfo">
				<#include "${full_templates_path}/footer.ftl" />
			</footer>
		</div>
	</div>
					
	
    <script src="${javascript_folder}/vendor/jquery.uniform.js"></script>
    <script src="${javascript_folder}/vendor/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${javascript_folder}/vendor/jquery.bxslider.min.js"></script>
    <script src="${javascript_folder}/vendor/owl.carousel.js"></script>
    <script src="${javascript_folder}/vendor/lightbox.js"></script>
    <script src="${javascript_folder}/custom.carousel.js"></script>
    <script src="${javascript_folder}/lightbox-custom.js"></script>
	<@liferay_util["include"] page=body_bottom_include />
	<@liferay_util["include"] page=bottom_include />
</body>
</html>