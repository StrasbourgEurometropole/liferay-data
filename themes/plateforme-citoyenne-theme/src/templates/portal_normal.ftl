<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

	<@liferay_util["include"] page=top_head_include />

	<#if request.getAttribute("LIFERAY_SHARED_OPENGRAPH")?has_content>
		<#assign openGraph = request.getAttribute("LIFERAY_SHARED_OPENGRAPH")>	
		<#assign keys = openGraph?keys>

		<#list keys as key>
			<meta property="${key}" content="${openGraph[key]}" />
		</#list>
	<#else>
		<meta property="og:type"               content="website" />
		<meta property="og:locale"               content="fr_FR" />
		<meta property="og:title"              content="Eurométropole de Strasbourg" />
		<meta property="og:description"        content="Site communautaire de l'eurométropole de Strasbourg" />
		<meta property="og:image"              content="https://www.strasbourg.eu/documents/976405/1013671/Home-visuel-haut-de-page_europe.jpg/73d6f660-4800-c32d-741a-1a3834d0b468" />
	</#if>

	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/strasbourg.css">
	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/leaflet.css">
	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/leaflet.fullscreen.css">
	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/MarkerCluster.css" />
	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/MarkerCluster.Default.css" />

	<title>${the_title?replace('-', '|')}</title>
	
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div id="th-global">
	
	<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />

	<#include "${full_templates_path}/nav_top.ftl" />

	<#include "${full_templates_path}/modal_connexion.ftl" />
	<#include "${full_templates_path}/modal_banned.ftl" />

	<main id="content" class="pro-overflow-visible">
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
	</main>

	<#include "${full_templates_path}/footer.ftl" />
	<#include "${full_templates_path}/confirm_quit_modal.ftl" />
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->

<script type="text/javascript" src="/o/plateforme-citoyenne-theme/js/strasbourg.js"></script>

<!-- endinject -->

</body>

</html>