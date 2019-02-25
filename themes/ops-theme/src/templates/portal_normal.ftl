<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta property="og:type"               content="website" />
	<meta property="og:locale"             content="fr_FR" />
	<meta property="og:title"              content="Orchestre philharmonique de Strasbourg" />
	<meta property="og:description"        content="Philharmonique de Strasbourg, Actualité : concert, billetterie, orchestre, choeur, actions éducatives, Euterpe, Les Clés de l'OPS." />
	<meta property="og:image"              content="https://www.strasbourg.eu/documents/976405/1013671/Home-visuel-haut-de-page_europe.jpg/73d6f660-4800-c32d-741a-1a3834d0b468" />
	
	<@liferay_util["include"] page=top_head_include />

	<link type="text/css" rel="stylesheet" href="/o/ops-theme/css/ops.css">
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



<div id="ops-wrapper">

	<#include "${full_templates_path}/nav_top.ftl" />

	<main>
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
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->

<script type="text/javascript" src="/o/ops-theme/js/ops.js"></script>

<!-- endinject -->

</body>

</html>