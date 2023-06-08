<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}"  prefix="og: http://ogp.me/ns#">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no ">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <@liferay_util["include"] page=top_head_include />
    <link type="text/css" rel="stylesheet" href="/o/strasbourg-theme/css/strasbourg.css">
    <link type="text/css" rel="stylesheet" href="/o/strasbourg-theme/css/libraries.css">
	  <link type="text/css" rel="stylesheet" href="/o/0-global-theme/css/hackliferay.css" />
    <#assign isWebmag = (layout.getFriendlyURL() == "/lactu" || layout.getFriendlyURL()?starts_with("/lactu-")) />
    <#if isWebmag>
      <link type="text/css" rel="stylesheet" href="/o/strasbourg-theme/css/webmag.css">
    </#if>


    <!-- Magnific Popup core JS file -->
    <script type="text/javascript" src="/o/strasbourg-theme/js/lightbox.js" charset="utf-8"></script> 
  	<script type="text/javascript" src="/o/0-global-theme/libs/tarteaucitron/tarteaucitron.js"></script>
    <script type="text/javascript" src="/o/0-global-theme/js/tarteaucitron.init.js"></script>

    <title>${the_title?replace('-', '|')}</title>

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
    
    <meta name="twitter:card" content="summary" />
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
  </head>

  <#include "${full_templates_path}/nav_top.ftl" />
  <#include "${full_templates_path}/navigation.ftl" />

  <#if isWebmag>
    <#include "${full_templates_path}/webmag.ftl" />
  <#else>
    <#include "${full_templates_path}/seu.ftl" />
  </#if>
</html>