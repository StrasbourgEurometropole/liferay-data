<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no ">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <@liferay_util["include"] page=top_head_include />
  <link type="text/css" rel="stylesheet" href="/o/strasbourg-theme/css/strasbourg.css">

  <title>${layout.getName(locale)} - ${company_name}</title>
</head>

<#assign isHome = layout.getFriendlyURL() == "/accueil" />
<body class="${css_class} seu-no-js seu-body 
  <#if isHome>
    seu-front
  <#else>
    seu-not-front
  </#if>
">
  
  <@liferay_ui["quick-access"] contentId="#main-content" />

  <@liferay_util["include"] page=body_top_include />

  <@liferay.control_menu />

  <script>
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
      <#assign homeURL = "/web${layout.group.friendlyURL}" />
    <#else>
      <#assign homeURL = "/" />
    </#if>
    window.homeURL = '${homeURL}';
  </script>
  <div class="seu">
    <header class="seu-header">
      <div class="seu-scrolled-search-engine">
        <form action="post" class="seu-search">
          <button type="submit"></button>
          <label for="main_search_banner" class="sr-only">Rechercher</label>
          <input type="text" name="search" placeholder="Rechercher ..." id="main_search_banner">
          <button type="button" class="seu-search-close"></button>
        </form>
      </div>
      <#include "${full_templates_path}/nav_top.ftl" />
      <#include "${full_templates_path}/navigation.ftl" />
    </header>

    <!--Banner-->
    <#if isHome>
      <#include "${full_templates_path}/home_banner.ftl" />
    <#else>
      <#assign layoutImage = layout.expandoBridge.getAttribute('image') />
      <div class="region-post-header <#if layoutImage?has_content>has-banner</#if>">
        <#if layoutImage?has_content>
          <div class="region-banner" style="background-image: url(${layoutImage})">
          </div>
        </#if>
        <@liferay.breadcrumbs />
      </div>
    </#if>

    <#if selectable>
      <@liferay_util["include"] page=content_include />
    <#else>
      ${portletDisplay.recycle()}
      ${portletDisplay.setTitle(the_title)}
      <@liferay_theme["wrap-portlet"] page="portlet.ftl" />
      <@liferay_util["include"] page=content_include />
    </#if>
    
    <@liferay_portlet["runtime"]
      portletProviderAction=portletProviderAction.VIEW
      portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
      instanceId="footer"
      settingsScope="group" />
  </div>

  <!-- inject:js -->
  <script type="text/javascript">
    if(typeof jQuery == 'undefined'){
      document.write('<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></'+'script>');
    }
  </script>
  <script>
    define._amd = define.amd;
    define.amd = false;
  </script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.1/TweenMax.min.js"></script>
  <script>
    define.amd = define._amd;
  </script>
  <script type="text/javascript" src="/o/strasbourg-theme/js/strasbourg.js"></script>

  <@liferay_util["include"] page=body_bottom_include />

  <@liferay_util["include"] page=bottom_include />
<!-- endinject -->
</body>

</html>