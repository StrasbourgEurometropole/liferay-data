<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">
	<script src="/o/museum-theme/js/vendors/jquery.magnific-popup.js"></script>

	<@liferay_ui["quick-access"] contentId="#main-content" />

	<@liferay_util["include"] page=body_top_include />

	<@liferay.control_menu />

	<div id="mobile-menu">
		<@liferay_portlet["runtime"]
			defaultPreferences="${freeMarkerPortletPreferences}"
			portletProviderAction=portletProviderAction.VIEW
			portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
			instanceId="public-mobile"
			settingsScope="group" />

		<form method="get" id="mobile-search-form" action="#">
			<input type="search" placeholder="Rechercher" value="" name="s" id="s">
			<input class="search" type="submit" id="search" value="GO">
		</form>
		<div class="mobile-main-menu-nav">
			<span class="to-start"></span>
			<span class="previous">Retour</span>
		</div>
		<nav class="mobile-main-menu">

			<@menu items=nav_items isSubMenu=false linkOnlyIfNoSubMenu=false ulClass="main-menu open" depth=0 maxDepth=0 />
			
		</div>
	</div>
	<div id="page">
		<nav class="header-top">
			<div class="header-top-inner">
				<div class="strasbourg-eu-logo">
					<a href="http://strasbourg.eu" class="strasbourg-eu-link" target="_blank">
						<img src="/o/museum-theme/images/logos/strasbourg-logo.png" alt="">
					</a>
				</div>
				<@liferay_portlet["runtime"]
					defaultPreferences="${freeMarkerPortletPreferences}"
					portletProviderAction=portletProviderAction.VIEW
					portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
					instanceId="langue"
					settingsScope="group" />
			</div>
		</nav>
		<div class="title-header">
			<h1 class="site-title"><a href="/web${layout.group.friendlyURL}">Musées de la ville de Strasbourg</a></h1>
			<div class="mobile-menu-toggle">
				<span></span>
			</div>
			<ul class="secondary-menu" role="nav">
				<li class="access-by-public-menu">
					<@liferay_portlet["runtime"]
						defaultPreferences="${freeMarkerPortletPreferences}"
						portletProviderAction=portletProviderAction.VIEW
						portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						instanceId="public"
						settingsScope="group" />
				</li>
				<li class="main-search">
					<form method="get" id="main-search-form" action="#">
						<input type="search" placeholder="Rechercher" value="" name="s" id="s">
						<input class="search" type="submit" id="search" value="GO">
					</form>
				</li>
			</ul>
		</div>
		<nav class="menu-header">
			<@menu items=nav_items isSubMenu=false linkOnlyIfNoSubMenu=true ulClass="main-menu" depth=0 maxDepth=1  />
		</nav>
		<#assign colorSchemeId = theme_display.getColorSchemeId() >
		<#if layout.friendlyURL != "/accueil">
			<div class="breadcrumb-wrapper">
				<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
				<@liferay_portlet["runtime"]
				defaultPreferences="${freeMarkerPortletPreferences}"
				portletProviderAction=portletProviderAction.VIEW
				portletName="com_liferay_site_navigation_breadcrumb_web_portlet_SiteNavigationBreadcrumbPortlet"
				settingsScope="group" />
				${freeMarkerPortletPreferences.reset()}
			</div>
		</#if>
		<#if colorSchemeId != "01">
			<nav class="museum-header">
				<ul class="museum-menu" role="nav">
					<#list nav_items as rootItem>
						<#if rootItem.isSelected()>
						 	<#list rootItem.getChildren() as museumItem>
						 		<#if museumItem.isSelected() || museumItem.isChildSelected()>
									<li class="menu-item home-item">
										<a href="${museumItem.getURL()}">
											<i class="home-icon"></i>
										</a>
									</li>
						 			<#list museumItem.getChildren() as museumMenuItem>
										<li class="menu-item <#if museumMenuItem.isSelected() || museumMenuItem.isChildSelected()>active</#if>">
											<a href="${museumMenuItem.getURL()}">${museumMenuItem.getName()}</a>
						 						<#if museumMenuItem.hasChildren()>
						 							<ul class="museum-submenu" role="nav">
							 							<#list museumMenuItem.getChildren() as museumSubmenuItem>
							 								<li class="menu-item submenu-item <#if museumSubmenuItem.isSelected() || museumSubmenuItem.isChildSelected()>active</#if>">
							 									<a href="${museumSubmenuItem.getURL()}">
							 										${museumSubmenuItem.getName()}
							 									</a>
							 								</li>
							 							</#list>
						 							</ul>
						 						</#if>
										</li>
									</#list>
								</#if>
							</#list>
						</#if>
					</#list>
				</ul>
			</nav>
		</#if>
		<div id="page-content">

			<#if has_navigation && is_setup_complete>
				<#include "${full_templates_path}/navigation.ftl" />
			</#if>
		</div>


		<div class="container" id="wrapper">
			<section id="content" >
				<h1 class="hide-accessible">${the_title}</h1>

				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}

					${portletDisplay.setTitle(the_title)}

					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>
			</section>
		</div>
		<footer id="footer" role="contentinfo">
			<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
			<@liferay_portlet["runtime"]
				defaultPreferences="${freeMarkerPortletPreferences}"
				portletProviderAction=portletProviderAction.VIEW
				portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
			${freeMarkerPortletPreferences.reset()}
		</footer>
	</div>
<!--
	<header id="banner" role="banner">
		<div id="heading">
			<h1 class="site-title">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>
			</h1>
		</div>
-->
	</header>


<script>
	window.homeURL = '/web${layout.group.friendlyURL}';
</script>
<script src="/o/museum-theme/js/vendors/owl.carousel.min.js"></script>
<script src="/o/museum-theme/js/vendors/jquery.dotdotdot.min.js"></script>
<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

<#if !is_signed_in>
	<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
</#if>
</body>
</html>