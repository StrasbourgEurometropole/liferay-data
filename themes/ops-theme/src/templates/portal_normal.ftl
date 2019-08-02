<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="twitter:card" content="summary" />
	<meta property="og:type"               content="website" />
	<meta property="og:locale"             content="fr_FR" />
	<meta property="og:title"              content="${the_title_OG}" />
	<meta property="og:description"        content="${themeDisplay.siteGroup.expandoBridge.getAttribute('opengraph_default_description')}" />
	<meta property="og:image"              content="${themeDisplay.siteGroup.expandoBridge.getAttribute('opengraph_default_image')}" />
	
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
	
	<#include "${full_templates_path}/footer.ftl" />
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->

<script type="text/javascript" src="/o/ops-theme/js/ops.js"></script>

<!-- endinject -->

</body>

</html>