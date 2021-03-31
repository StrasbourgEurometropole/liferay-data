<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

	<@liferay_util["include"] page=top_head_include />

    <#assign currentUrlOG = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

    <#assign descriptionOG = '${layout.getDescription(locale)?replace("<[^>]*>", "", "r")?html?js_string}' />
    <#if !descriptionOG?has_content>
      <#assign descriptionOG = '${themeDisplay.siteGroup.expandoBridge.getAttribute("opengraph_default_description")}' />
    </#if>

    <#assign imageOG = '${layout.expandoBridge.getAttribute("image")}' />
    <#if !imageOG?has_content>
      <#assign imageOG = '${themeDisplay.siteGroup.expandoBridge.getAttribute("opengraph_default_image")}' />
    </#if>
    <#if imageOG?has_content && !imageOG?contains('http')>
      <#assign imageOG = '${themeDisplay.getPortalURL()}${imageOG}' />
    </#if>

    <#assign openGraph = {
      "twitter:card":"summary",
      "og:type":"website",
      "og:locale":"${locale}",
      "og:url":"${currentUrlOG}",
      "og:title":"${the_title_OG}",
      "og:description":'${descriptionOG}',
      "og:image":"${imageOG}",
      "og:image:width":"620",
      "og:image:height":"400"
    } />

    <#if request.getAttribute("LIFERAY_SHARED_OPENGRAPH")?has_content>
        <#assign openGraphCustom = request.getAttribute("LIFERAY_SHARED_OPENGRAPH")>
        <#list openGraphCustom?keys as keyOG>
          <#if openGraphCustom[keyOG]?has_content>
            <#assign openGraph = openGraph + {keyOG : openGraphCustom[keyOG]} >
          </#if>
          <#if !openGraphCustom[keyOG]?has_content && openGraph[keyOG]?has_content>
            <#assign openGraph = openGraph + {keyOG : openGraph[keyOG]} >
          </#if>
        </#list>
    </#if>

    <#list openGraph?keys as keyOG>
      <#assign valueOG = openGraph[keyOG]>
      <#if keyOG == "og:description" >
        <#assign valueOG = valueOG[0..*300] + (valueOG?length > 300)?then('...','') >
      </#if>
      <#if keyOG == "og:description" && valueOG?has_content >
          <meta property="${keyOG}" content="${valueOG}" />
      <#elseif keyOG?contains("og:image") && openGraph["og:image"]?has_content>
          <meta property="${keyOG}" content="${valueOG}" />
      <#elseif keyOG != "og:description" && !keyOG?contains("og:image")>
          <meta property="${keyOG}" content="${valueOG}" />
      </#if>
    </#list>

	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/strasbourg.css">
	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/leaflet.css">
	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/leaflet.fullscreen.css">
	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/MarkerCluster.css" />
	<link type="text/css" rel="stylesheet" href="/o/plateforme-citoyenne-theme/css/MarkerCluster.Default.css" />
  <script type="text/javascript" src="/o/0-global-theme/libs/tarteaucitron/tarteaucitron.js"></script>

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