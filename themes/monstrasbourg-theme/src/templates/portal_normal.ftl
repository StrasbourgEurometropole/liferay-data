<!DOCTYPE html>

<#include init />

<html class="${root_css_class} mseu" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
      <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
      <#assign homeURL = "/" />
    </#if>
  <#-- Si l'utilisateur n'est pas connecté avec un compte Liferay ni avec un compte Publik 
  (et qu'il n'est pas sur la page de bienvenue ni sur la page de validation médiathèque), 
  on le redirige vers la page de bienvenue -->
  <#if !is_signed_in && !(request.session.getAttribute("publik_logged_in")!false) && layout.getFriendlyURL() != "/bienvenue" && layout.getFriendlyURL() != "/validation-mediatheque">
      ${themeDisplay.getResponse().sendRedirect(homeURL + 'bienvenue')} 
  </#if>
  <#-- Si l'utilisateur n'est pas connecté avec un compte Liferay mais qu'il est connecté
  avec un compte Publik et s'il est sur la page de bienvenue, on le redirige vers la page d'accueil -->
  <#if !is_signed_in && (request.session.getAttribute("publik_logged_in")!false) && layout.getFriendlyURL() == "/bienvenue">
      ${themeDisplay.getResponse().sendRedirect(homeURL)}
  </#if>

  <script>
    <#if request.session.getAttribute("publik_logged_in")!false>
      <#assign favoriteLocalService = serviceLocator.findService("eu.strasbourg.service.favorite.service.FavoriteLocalService") />
      <#assign favorites = favoriteLocalService.getByPublikUser(request.session.getAttribute("publik_internal_id")) />
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
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, user-scalable=no">
  <@liferay_util["include"] page=top_head_include />
   
  <link type="text/css" rel="stylesheet" href="/o/monstrasbourg-theme/css/strasbourg.css">
  <title>${the_title?replace('-', '|')}</title>
</head>
<#assign isHome = layout.getFriendlyURL() == "/accueil" />
<#assign isDistrict = layout.getFriendlyURL() == "/mon-quartier" />
<#assign isWelcome = layout.getFriendlyURL() == "/bienvenue" />

<body class="${css_class} no-js
     class_group_home <#if isHome || isDistrict>front<#else>not-front</#if> <#if isWelcome>welcome</#if> <#if isHome>home</#if>">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<script>
  window.loginURL = '${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))}';
</script>

  <header class="header">

    <div id="cookies" class="cookies-top clearfix" data-site-id="UA-999999">
      <div class="popup-content info">
        <div id="popup-text">
          <p>
            Ce site web utilise des cookies. En poursuivant votre navigation sur ce site, vous acceptez l'utilisation de cookies.
            Pour en savoir plus <a href="https://www.cnil.fr/fr/cookies-les-outils-pour-les-maitriser" target=_blank>Cliquez-ici</a>.
          </p>
        </div>
        <div id="popup-buttons">
          <button type="button" class="agree-button">
            <span class="sr-only">OK</span>
          </button>
        </div>
      </div>
    </div>

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
    <#if !isWelcome>
      <div class="bg-banner" style="background-image: url(/o/monstrasbourg-theme/images/banner.jpg);"></div>
    </#if>
    <#if !isWelcome>
      <div class="custom-container" >
        <#include "${full_templates_path}/home_banner.ftl" />
        <#if !(isHome || isDistrict)>
          <div class="card-box">  
            <@liferay.breadcrumbs />
        </#if>
    <#else> 
      <div id="welcome-page">
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
    <#if !(isHome || isDistrict || isWelcome)>
      </div>
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


  <#if  propsUtil.get('eu.strasbourg.environment') == "PROD"><!-- Piwik -->
    <script type="text/javascript">
      var _paq = _paq || [];
      /* tracker methods like "setCustomDimension" should be called before "trackPageView" */
      _paq.push(['trackPageView']);
      _paq.push(['enableLinkTracking']);
      (function() {
        var u="//piwik.entrouvert.org/";
        _paq.push(['setTrackerUrl', u+'piwik.php']);
        _paq.push(['setSiteId', '19']);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);
      })();
    </script>
    <!-- End Piwik Code -->
  </#if>
  
  <script>
    define.amd = define._amd;
  </script>

  <script type="text/javascript" src="/o/monstrasbourg-theme/js/strasbourg.js"></script>

  <@liferay_util["include"] page=body_bottom_include />

  <@liferay_util["include"] page=bottom_include />
<!-- endinject -->

</body>

</html>