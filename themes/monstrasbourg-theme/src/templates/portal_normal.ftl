<!DOCTYPE html>

<#include init />

<html class="${root_css_class} mseu" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
  <#-- Si l'utilisateur n'est pas connecté avec un compte Liferay ni avec un compte Publik 
  (et qu'il n'est pas sur la page d'infos), on le redirige vers la page d'infos -->
  <#if !is_signed_in && !(request.session.getAttribute("publik_logged_in")!false) && layout.getFriendlyURL() != "/bienvenue">
      <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
        <#assign homeURL = "/web${layout.group.friendlyURL}/" />
      <#else>
        <#assign homeURL = "/" />
      </#if>
      ${themeDisplay.getResponse().sendRedirect(homeURL + 'bienvenue')} 
  </#if>
  <#-- Si l'utilisateur n'est pas connecté avec un compte Liferay mais qu'il est connecté
  avec un compte Publik et s'il est sur la page d'infos, on le redirige vers la page d'accueil -->
  <#if !is_signed_in && (request.session.getAttribute("publik_logged_in")!false) && layout.getFriendlyURL() == "/bienvenue">
      <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
        <#assign homeURL = "/web${layout.group.friendlyURL}/" />
      <#else>
        <#assign homeURL = "/" />
      </#if>
      ${themeDisplay.getResponse().sendRedirect(homeURL)}
  </#if>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, user-scalable=no">
  <@liferay_util["include"] page=top_head_include />
   
  <link type="text/css" rel="stylesheet" href="/o/monstrasbourg-theme/css/strasbourg.css">
  <title>${the_title?replace('-', '|')}</title>
</head>
<#assign isHome = layout.getFriendlyURL() == "/accueil" />

<body class="${css_class} no-js
     class_group_home <#if isHome>front<#else>not-front</#if>">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<script>
  window.loginURL = '${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))}';

  document.write("<script async src='/browser-sync/browser-sync-client.js?v=2.23.5'><\/script>".replace("HOST", location.hostname));
</script>

  <header class="header">
    <#include "${full_templates_path}/nav_top.ftl" />

    <nav id="nav-side">
      <#if request.session.getAttribute("publik_logged_in")!false>
        <!-- Notifications -->
        <@liferay_portlet["runtime"]
          portletProviderAction=portletProviderAction.VIEW
          portletName="eu_strasbourg_portlet_notification_NotificationViewerWebPortlet"
          instanceId="notifications"
        />
      </#if>

      <!-- Menu -->

      <#if layout.getFriendlyURL() != "/bienvenue">
        <#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
        <@liferay_portlet["runtime"]
          defaultPreferences="${freeMarkerPortletPreferences}"
          portletProviderAction=portletProviderAction.VIEW
          portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
          instanceId="menu"
          settingsScope="group" />
      </#if>
    </nav>
    
  </header>
 
  <main id="main-content">
   <div class="bg-banner" style="background-image: url(/o/monstrasbourg-theme/images/banner.jpg);"></div>
    <div class="custom-container">
      <#include "${full_templates_path}/home_banner.ftl" />
    
    <#if !isHome>
      <div class="card-box">  
        <#if layout.getFriendlyURL() != "/bienvenue">
          <@liferay.breadcrumbs />
        </#if>
    </#if>      
    <#if selectable>
      <@liferay_util["include"] page=content_include />
    <#else>
      ${portletDisplay.recycle()}

      ${portletDisplay.setTitle(the_title)}

      <@liferay_theme["wrap-portlet"] page="portlet.ftl">
        <@liferay_util["include"] page=content_include />
      </@>
    </#if>
    <#if !isHome>
      </div>
    <#else>
      <#--include "${full_templates_path}/content.ftl" /-->
    </#if>
    </div>
  </main>
  <#include "${full_templates_path}/footer.ftl" />

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

  <script type="text/javascript" src="/o/monstrasbourg-theme/js/strasbourg.js"></script>

  <@liferay_util["include"] page=body_bottom_include />

  <@liferay_util["include"] page=bottom_include />
<!-- endinject -->

</body>

</html>