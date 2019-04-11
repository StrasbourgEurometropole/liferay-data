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
  <link type="text/css" rel="stylesheet" href="/o/strasbourg-theme/css/webmag.css">

  <!-- Magnific Popup core JS file -->
  <script type="text/javascript" src="/o/strasbourg-theme/js/lightbox.js" charset="utf-8"></script> 

  <title>${the_title?replace('-', '|')}</title>
</head>

<#assign isWebmag = layout.getFriendlyURL()?starts_with("/lactu-") />
<#include "${full_templates_path}/nav_top.ftl" />
<#include "${full_templates_path}/navigation.ftl" />

<#if isWebmag>
  <#include "${full_templates_path}/webmag.ftl" />
<#else>
  <#include "${full_templates_path}/seu.ftl" />
</#if>

</html>