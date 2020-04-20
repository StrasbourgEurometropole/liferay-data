<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}"  prefix="og: http://ogp.me/ns#">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no ">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <@liferay_util["include"] page=top_head_include />
    <link type="text/css" rel="stylesheet" href="/o/council-theme/css/council.css">
    <link type="text/css" rel="stylesheet" href="/o/council-theme/css/libraries.css">


    <!-- Magnific Popup core JS file -->
    <script type="text/javascript" src="/o/strasbourg-theme/js/lightbox.js" charset="utf-8"></script> 

    <title>${the_title?replace('-', '|')}</title>

  </head>

  <#include "${full_templates_path}/nav_top.ftl" />


<body class="${css_class} seu-no-js seu-body seu-not-front">
  
  <@liferay_ui["quick-access"] contentId="#main-content" />

  <@liferay_util["include"] page=body_top_include />

  <@liferay.control_menu />


  <script>
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
      <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
      <#assign homeURL = "/" />
    </#if>
    <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
    window.homeURL = '${homeURL}';
    window.loginURL = '${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))}';

    <#if request.session.getAttribute("publik_logged_in")!false>
      <#assign favoriteLocalService = serviceLocator.findService("eu.strasbourg.service.favorite.service.FavoriteLocalService") />
      <#assign favorites = favoriteLocalService.getByPublikUser(request.session.getAttribute("publik_internal_id")) />
      window.publikInternalId = '${request.session.getAttribute("publik_internal_id")}';
      window.userFavorites = [
        <#list favorites as favorite>
          {
            entityId: ${favorite.entityId},
            typeId: ${favorite.typeId}
          }<#sep>,</#sep>
        </#list>
      ];
    </#if>

  </script>
  <div class="seu">


    <header class="seu-header scrolled scrolled-hp">
      <@subnavtop "seu"/>
    </header>

    <#assign layoutImage = layout.expandoBridge.getAttribute('image') />
    <div class="region-post-header <#if layoutImage?has_content>has-banner</#if>">
      <#if layoutImage?has_content>
        <div class="region-banner" style="background-image: url(${layoutImage})">
        </div>
      </#if>
    </div>

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
  <script type="text/javascript" src="/o/council-theme/js/council.js"></script>

  <@liferay_util["include"] page=body_bottom_include />

  <@liferay_util["include"] page=bottom_include />
<!-- endinject --> 

</body>
</html>