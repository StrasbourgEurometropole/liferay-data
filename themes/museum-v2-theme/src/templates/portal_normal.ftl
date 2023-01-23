<!DOCTYPE html>

<#include init />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
	<#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
	<#assign homeURL = "/" />
</#if>
<#assign isHome = layout.getFriendlyURL() == "/accueil" />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

	<head>
		<title>${the_title}</title>

		<meta content="initial-scale=1.0, width=device-width" name="viewport" />
		
		<link type="text/css" rel="stylesheet" href="/o/0-global-theme/css/hackliferay.css" />
		<link rel="stylesheet" href="${css_folder}/builders/swiper.min.css" />
		<link type="text/css" rel="stylesheet" href="${css_folder}/museum-v2.css" media="screen" />

    	
		<script type="text/javascript" src="/o/0-global-theme/libs/tarteaucitron/tarteaucitron.js"></script>
		<script type="text/javascript" src="/o/0-global-theme/js/tarteaucitron.init.js"></script>
		<script src="${javascript_folder}/vendors/swiper.min.js"></script>
		<script src="${javascript_folder}/vendors/masonry.pkgd.min.js"></script>
		
		<@liferay_util["include"] page=top_head_include />
	</head>

	<body class="${css_class}
		<#if isHome>
			front
		<#else>
			not-front
		</#if>
	">

		<@liferay_ui["quick-access"] contentId="#main-content" />
		<@liferay_util["include"] page=body_top_include />
		<@liferay.control_menu />

		<div id="global">
			<header id="pre-header">
				<div class="pre-header-inner container">
					<div class="strasbourg-eu-logo">
						<a href="http://strasbourg.eu" class="strasbourg-eu-link" target="_blank" aria-label="strasbourg.eu" title="strasbourg.eu">
							<img src="/o/museum-v2-theme/images/logos/strasbourg-logo.png" alt="strasbourg.eu" title="strasbourg.eu">
						</a>
					</div>
					<@liferay_portlet["runtime"]
						portletProviderAction=portletProviderAction.VIEW
						portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						instanceId="menu-preheader"
						settingsScope="group" />
				</div>
			</header>

			<section id="header-top">
				<div id="acces"  class="hidden">
					<@liferay_portlet["runtime"]
						portletProviderAction=portletProviderAction.VIEW
						portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						instanceId="acces"
						settingsScope="group" />
				</div>
				<div class="header-top-inner container">
					<a class="title" href="${homeURL}">Musées de la ville de Strasbourg</a>
					<span id="access-by-public" class="access-by-public-menu-title show-acces"><@liferay_ui.message key="eu.museum.acces" /></span>
					<div id="search-mobile" class="search"></div>
					<form method="get" id="main-search-form" action="${homeURL}recherche" class="hidden">
						<input type="hidden" name="p_p_id" value="eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet" />
						<input type="search" name="_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_keywords" placeholder="Rechercher" value="" >
						<input class="search" type="submit" id="search" value="GO">
					</form>
				</div>
			</section>

			<section id="menu-desktop">
				<div class="container">	
      				<div id="burger">
						<div class="burger-inner">
							<span></span>
						</div>
						<div class="title">
							<@liferay_ui.message key="eu.museum.menu" />
						</div>
					</div>
				</div>
				<div id="nav" class="hidden">
					<div class="nav-quicklink">
						<@liferay_portlet["runtime"]
							portletProviderAction=portletProviderAction.VIEW
							portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
							instanceId="quicklink"
							settingsScope="group" />
					</div>
					<div class="nav-thumbnails">
						<@liferay_portlet["runtime"]
							portletProviderAction=portletProviderAction.VIEW
							portletName="com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"
							instanceId="navigation" />
					</div>
				</div>
			</section>

			<section id="menu-smartphone">
				<div id="more" class="hidden">
					<div id="closed"></div>
					<div class="title"><@liferay_ui.message key="eu.museum.choice-language" /></div>
					<@liferay_portlet["runtime"]
						portletProviderAction=portletProviderAction.VIEW
						portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						instanceId="langues"
						settingsScope="group" />
					<div class="title"><@liferay_ui.message key="eu.museum.acces" /></div>
					<@liferay_portlet["runtime"]
						portletProviderAction=portletProviderAction.VIEW
						portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						instanceId="acces"
						settingsScope="group" />
				</div>
				<div class="menu-mobile content">
					<@liferay_portlet["runtime"]
						portletProviderAction=portletProviderAction.VIEW
						portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						instanceId="menu-smartphone"
						settingsScope="group" />
					<a id="see-more" href="" class="more"  aria-label="<@liferay_ui.message key="eu.museum.more" />" title="<@liferay_ui.message key="eu.museum.more" />">
						<svg width="16" height="16" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg" stroke="white" stroke-width="1.2" stroke-linecap="round">
							<line x1="8.2" y1="0.6" x2="8.2" y2="15.4" />
							<line x1="15.4" y1="7.80001" x2="0.6" y2="7.80001"/>
						</svg>
						<@liferay_ui.message key="eu.museum.more" />
					</a> 
				</div>
			</section>

			<main id="main">
				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}
					${portletDisplay.setTitle(the_title)}
					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>
			</main>

			<footer id="footer">
				<@liferay_portlet["runtime"]
				portletProviderAction=portletProviderAction.VIEW
				portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
				instanceId="footer"
				settingsScope="group" />
			</footer>
		</div>

		<script type="text/javascript" src="${javascript_folder}/tarteaucitron.custom.js"></script>
		<script src="${javascript_folder}/vendors/select2.min.js"></script>
		<script src="${javascript_folder}/vendors/jquery-ui.min.js"></script>
		<script src="${javascript_folder}/vendors/jquery-ui-datepicker-fr.js"></script>
		<script>
			window.homeURL = '${homeURL}';
		</script>

		<@liferay_util["include"] page=body_bottom_include />
		<@liferay_util["include"] page=bottom_include />

	</body>
</html>