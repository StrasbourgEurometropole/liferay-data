<#assign isHome = layout.getFriendlyURL() == "/lactu-home" />
<#assign isPortraitList = layout.getFriendlyURL() == "/lactu-portraits" />

<body class="${css_class} smag  
  <#if isHome>
    front
  <#else>
    not-front seu-not-front
  </#if>
">
  
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
    
  <header class="smag-header scrolled-hp">
      <div class="smag-scrolled-search-engine">
          <form action="${homeURL}recherche" method="get" class="smag-search smag-container">
              <button type="submit"></button>
              <label for="main_search_banner" class="sr-only">Rechercher</label>
              <input type="text" name="_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_keywords" placeholder="Rechercher ..." id="main_search_banner">
              <input type="hidden" name="p_p_id" value="eu_strasbourg_portlet_search_asset_SearchAssetPortlet" />
              <input type="hidden" name="p_p_lifecycle" value="1" />
              <button type="button" class="smag-search-close"></button>
          </form>
      </div>
    <@subnavtop "smag"/>
    <@subnavigation "smag"/>
  </header>

  <!--Banner-->
  <#if !isHome >
    <#if isPortraitList >
      <@liferay_portlet["runtime"]
          portletProviderAction=portletProviderAction.VIEW
          portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
          instanceId="header-portrait"
          settingsScope="group" />
    </#if>
    <main class="seu">
        <div class="smag-container">

            <!-- Ariane -->
            <@liferay.breadcrumbs />
  </#if>

  <#if selectable>
    <@liferay_util["include"] page=content_include />
  <#else>
    ${portletDisplay.recycle()}
    ${portletDisplay.setTitle(the_title)}
    <@liferay_theme["wrap-portlet"] page="portlet.ftl" />
    <@liferay_util["include"] page=content_include />
  </#if>
  
  <#if !isHome >
        </div>
    </main>
  </#if>
    
  <@liferay_portlet["runtime"]
    portletProviderAction=portletProviderAction.VIEW
    portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
    instanceId="footer"
    settingsScope="group" />

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
  <script type="text/javascript" src="/o/strasbourg-theme/js/conf.js"></script>
  <script type="text/javascript" src="/o/strasbourg-theme/js/strasbourg.js"></script>
  <script type="text/javascript" src="/o/strasbourg-theme/js/webmag.js"></script>
  <script type="text/javascript" src="/o/strasbourg-theme/js/libraries.js"></script>

  <@liferay_util["include"] page=body_bottom_include />

  <@liferay_util["include"] page=bottom_include />
<!-- endinject -->
</body>