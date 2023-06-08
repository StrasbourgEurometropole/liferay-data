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
    window.loginURL = '${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))?html}';

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
      <header class="seu-header" id="th-header">
    <#else>
      <header class="seu-header  scrolled scrolled-hp" id="th-header">
    </#if>
      <@subnavtop "seu"/>
      <@subnavigation "seu"/>
    </header>

    <main>
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
    </main>
    
    <!-- Social Share sur chaque page - Apparait au moment du scroll de la page -->
    <div class="social-share">
        <input class="toggle-input" id="toggle-input" type="checkbox" /> 
        <label aria-hidden="true" aria-label="Partagez sur les réseaux sociaux" class="toggle" for="toggle-input">
          <span>Réseaux sociaux</span>
        </label>
        <ul class="network-list">
          <li class="facebook">
            <a aria-label="Partagez sur Facebook" data-href="#" id="sharefacebook" target="_blank" title="Lien de partage sur Facebook"></a>
          </li>
          <li class="twitter">
            <a aria-label="Partagez sur Twitter" id="sharetwitter" target="_blank" title="Lien de partage sur Twitter"></a>
          </li>
          <li class="linkedin">
            <a aria-label="Partagez sur LinkedIn" id="ShareLinkedIn" target="_blank" title="Lien de partage sur LinkedIn"></a>
          </li>
          <li class="mail">
            <a aria-label="Partagez par Email" id="ShareMail" title="Lien de partage par Email"></a>
          </li>
        </ul>
    </div>
    
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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.1/TweenMax.min.js"></script>
  <script type="text/javascript" src="/o/strasbourg-theme/js/strasbourg.js"></script>

  <@liferay_util["include"] page=body_bottom_include />

  <@liferay_util["include"] page=bottom_include />
<!-- endinject --> 

  <script type="text/javascript">
      window.onload = function(){
          var url = window.location.toString();
          document.getElementById("sharefacebook").setAttribute("href","https://www.facebook.com/sharer/sharer.php?u="+ encodeURIComponent(document.URL));
          document.getElementById("sharetwitter").setAttribute("href","https://twitter.com/intent/tweet?text="+url);
          document.getElementById("ShareLinkedIn").setAttribute("href","http://www.linkedin.com/shareArticle?mini=true&url="+url);
          document.getElementById("ShareMail").setAttribute("href","mailto:?body="+url);
      }
  </script>
</body>