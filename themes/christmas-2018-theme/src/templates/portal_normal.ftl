<!DOCTYPE html>

<#include init />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
  <#assign homeURL = "" />
</#if>
<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1, user-scalable=no,minimal-ui">
    <meta name="author" content="Agence Thuria">
    <title>${the_title}</title>

    <@liferay_util["include"] page=top_head_include />
    
    <link href="/o/christmas-2018-theme/css/style.css" rel="stylesheet">

  <script>
    <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
    window.homeURL = '${homeURL}/';
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
    
  </head>


  <body class="${css_class}">

    <@liferay_ui["quick-access"] contentId="#main-content" />

    <@liferay_util["include"] page=body_top_include />

    <@liferay.control_menu />

    <header id="mns-header">
    <div id="layer"></div>
    
        <!-- Top header bar -->
        <div class="mns-top-header">
            <div>
                <a href="http://www.strasbourg.eu/" target="_blank"><img src="/o/christmas-2018-theme/images/logo-strasbourg-eu.png" alt="Logo Strasbourg" width="183" height="40" /></a>
            </div>
            <div class="menu">
                <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
                <#if request.session.getAttribute("publik_logged_in")!false>
                  <#assign notificationService = serviceLocator.findService("eu.strasbourg.service.notification.service.UserNotificationStatusLocalService") />
                  <div class="nav-account nav-btn">
                    <button id="trigger-account-menu" onClick="javascript: location='${layoutHelper.getDashboardURL()}';">
                      <span class="flexbox">
                        <#assign notifCount = notificationService.getUnreadNotificationCount(request.session.getAttribute("publik_internal_id")) />
                        <span class="picto">
                            <#if (notifCount > 0)>
                                <span class="notif-amount">${notifCount}</span>
                            </#if>
                        </span>
                        <a href="${layoutHelper.getDashboardURL()}" style="text-decoration: none; width: auto;" title="<@liferay_ui.message key='eu.dashboard' />" class="connect">
                          <span class="text">${request.session.getAttribute("publik_given_name")}&nbsp;${request.session.getAttribute("publik_family_name")[0..0]}.</span>
                        </a>
                        <span class="arrow" style="display: none;"></span>
                      </span>
                    </button>
                    <!-- Menu connecté -->
                    <@liferay_portlet["runtime"]
                      portletProviderAction=portletProviderAction.VIEW
                      portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                      instanceId="loggedinmenu"
                      settingsScope="group" />
                    </div>
                <#else>
                  <a href="${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))}" title="<@liferay_ui.message key='eu.login.strasbourg' />" class="connect">
                    <span class="flexbox">
                      <span class="picto"></span>
                      <span class="text"><@liferay_ui.message key='eu.login.strasbourg' /></span>
                    </span>    
                  </a>
                </#if>
                <a href="/carte" class="mns-w-fixe-1"><span><@liferay_ui.message key='dynamic-map' /></span></a>
                <a href="/pro-presse" class="mns-w-fixe-2"><span><@liferay_ui.message key='pro-and-press' /></span></a>
                <a href="#" class="hidden-xs hidden-sm menu-search"><span class="icon-search"></span></a>
                <a href="/fr${homeURL}${layout.friendlyURL}">FR</a>
                <a href="/de${homeURL}${layout.friendlyURL}" title="Deutsch">DE</a>
                <a href="/en${homeURL}${layout.friendlyURL}" title="English">EN</a>
            </div>
        </div>
        <#assign isExperientiel = layout.getFriendlyURL() == "/experientiel" />
        <#if !isExperientiel>
            <#include "${full_templates_path}/navigation.ftl" />
        <#else>
            <#include "${full_templates_path}/experientiel.ftl" />
        </#if>
    </header>
    <main>
       
            <#if selectable>
                <@liferay_util["include"] page=content_include />
            <#else>
                ${portletDisplay.recycle()}
                ${portletDisplay.setTitle(the_title)}
                <@liferay_theme["wrap-portlet"] page="portlet.ftl" />
                <@liferay_util["include"] page=content_include />
            </#if>

       
    </main>
    <#if !isExperientiel>
    <!-- Bouton de partage -->
    <div class="mns-share-button fadein">
        <input class="toggle-input" id="toggle-input" type="checkbox">
        <label for="toggle-input" class="toggle"></label>
        <ul class="network-list">
            <li class="facebook">
                <a href="https://www.facebook.com/Marche.Noel.Strasbourg/">Share on Facebook</a>
                <span>230</span>
            </li>
            <li class="twitter">
                <a href="https://twitter.com/strasbourg?lang=fr">Share on Twitter</a>
                <span>320</span>
            </li>
        </ul>
    </div>

    <!-- Footer -->
        <footer id="mns-footer">
            <@liferay_portlet["runtime"]
                portletProviderAction=portletProviderAction.VIEW
                portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                instanceId="footer"
                settingsScope="group" />
        </footer>
    </#if>

    <@liferay_util["include"] page=body_bottom_include />
    <@liferay_util["include"] page=bottom_include />
    <script>
    define._amd = define.amd;
    define.amd = false;
    </script>
    <script src="/o/christmas-2018-theme/js/t_main.js"></script>
    <script>
    define.amd = define._amd;
    </script>
    <script type="text/javascript">
      if ($(window).width() >= 1280) {
        $(window).stellar();
      }
    </script>


  </body>
</html>