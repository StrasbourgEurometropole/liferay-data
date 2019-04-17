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
    
    <#if isHome>
      <header class="seu-header">
    <#else>
      <header class="seu-header scrolled scrolled-hp">
    </#if>
      <div class="seu-scrolled-search-engine">
        <form action="${homeURL}recherche" method="get" class="seu-search">
          <button type="submit"></button>
          <label for="main_search_banner" class="sr-only"><@liferay.language key="to-research" /></label>
          <input type="text" name="_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_keywords" placeholder="<@liferay.language key="to-research" /> ..." id="main_search_banner">
          <input type="hidden" name="p_p_id" value="eu_strasbourg_portlet_search_asset_SearchAssetPortlet" />
          <input type="hidden" name="p_p_lifecycle" value="1" />
          <button type="button" class="seu-search-close"></button>
        </form>
      </div>
      <@subnavtop "seu"/>
      <@subnavigation "seu"/>
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