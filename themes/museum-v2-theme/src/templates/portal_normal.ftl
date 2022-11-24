<!DOCTYPE html>

<#include init />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
	<#assign homeURL = "/web${layout.group.friendlyURL}" />
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
			seu-front
		<#else>
			seu-not-front
		</#if>
	">

		<@liferay_ui["quick-access"] contentId="#main-content" />
		<@liferay_util["include"] page=body_top_include />
		<@liferay.control_menu />

		<div id="global">
			<header id="header-top">
				<div class="header-top-inner container">
					<div class="strasbourg-eu-logo">
						<a href="http://strasbourg.eu" class="strasbourg-eu-link" target="_blank" aria-label="strasbourg.eu" title="strasbourg.eu">
							<img src="/o/museum-v2-theme/images/logos/strasbourg-logo.png" alt="strasbourg.eu" title="strasbourg.eu">
						</a>
					</div>
					<@liferay_portlet["runtime"]
						portletProviderAction=portletProviderAction.VIEW
						portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						instanceId="langue"
						settingsScope="group" />
				</div>
			</header>

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