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
    <script>
      title = '';
      description = '';
      imageUrl = '';
    </script> 

    <title>${the_title?replace('-', '|')}</title>
  </head>

  <#assign isWebmag = (layout.getFriendlyURL() == "/lactu" || layout.getFriendlyURL()?starts_with("/lactu-")) />
  <#include "${full_templates_path}/nav_top.ftl" />
  <#include "${full_templates_path}/navigation.ftl" />

  <#if isWebmag>
    <#include "${full_templates_path}/webmag.ftl" />
  <#else>
    <#include "${full_templates_path}/seu.ftl" />
  </#if>
    
  <script> 
    baliseOG = '<meta name="twitter:card" content="summary" />'
        + '<meta property="og:type" content="website" />';

    if(title == ''){
      title = '${the_title?replace('-', '|')?replace(' | Strasbourg.eu', '')}';
    }
    if(title != ''){
      baliseOG += '<meta property="og:title" content="' + title + '" />';
    }

    if(description == ''){
      description = '${layout.getDescription(locale)?replace("<[^>]*>", "", "r")?html?js_string}';
    }
    if(description != ''){
      baliseOG += '<meta property="og:description" content="' + description.substring(0,300) + (description.length > 300?"...":"") + '" />';
    } 

    baliseOG += '<meta property="og:url" content="' + window.location.href + '" />';

    if(imageUrl == ''){
      imageUrl = '${layout.expandoBridge.getAttribute('image')}';
      if(imageUrl == ''){ 
        imageUrl = '${themeDisplay.siteGroup.expandoBridge.getAttribute('opengraph_default_image')}'; 
      }
    }
    if(imageUrl != ''){  
      if(!imageUrl.includes('http')){
          imageUrl = '${themeDisplay.getPortalURL()}' + imageUrl;
      }
      baliseOG += '<meta property="og:image" content="' + imageUrl + '"/>'
        + '<meta property="og:image:width" content="620"/>'
        + '<meta property="og:image:height" content="400"/>';
    }

    $('head').append(baliseOG);
  </script>
</html>