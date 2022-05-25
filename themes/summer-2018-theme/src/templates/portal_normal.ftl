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
    
    <link href="/o/summer-2018-theme/css/t_main.css" rel="stylesheet">
	  <link type="text/css" rel="stylesheet" href="/o/0-global-theme/css/hackliferay.css" />
    <link href="https://fonts.googleapis.com/css?family=Montserrat:100,300,400,500,600,700" rel="stylesheet">

  <script>
      window.userFavorites = [
      ];
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


    <#assign currentUrlOG = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

    <#assign descriptionOG = '${layout.getDescription(locale)?replace("<[^>]*>", "", "r")?html?js_string}' />
    <#if !descriptionOG?has_content>
      <#assign descriptionOG = '${themeDisplay.siteGroup.expandoBridge.getAttribute("opengraph_default_description")}' />
    </#if> 

    <#assign imageOG = '${layout.expandoBridge.getAttribute("image")}' />
    <#if !imageOG?has_content>
      <#assign imageOG = '${themeDisplay.siteGroup.expandoBridge.getAttribute("opengraph_default_image")}' />
    </#if> 
    <#if imageOG?has_content && !imageOG?contains('http')>
      <#assign imageOG = '${themeDisplay.getPortalURL()}${imageOG}' />
    </#if> 
    
    <#assign openGraph = {
      "og:type":"website",
      "og:locale":"${locale}",
      "og:url":"${currentUrlOG}",
      "og:title":"${the_title_OG}",
      "og:description":'${descriptionOG}',
      "og:image":"${imageOG}",
      "og:image:width":"620",
      "og:image:height":"400"
    } />

    <#if request.getAttribute("LIFERAY_SHARED_OPENGRAPH")?has_content>
        <#assign openGraphCustom = request.getAttribute("LIFERAY_SHARED_OPENGRAPH")>   
        <#list openGraphCustom?keys as keyOG>  
          <#if openGraphCustom[keyOG]?has_content>
            <#assign openGraph = openGraph + {keyOG : openGraphCustom[keyOG]} >
          </#if>
          <#if !openGraphCustom[keyOG]?has_content && openGraph[keyOG]?has_content>
            <#assign openGraph = openGraph + {keyOG : openGraph[keyOG]} >
          </#if>
        </#list>
    </#if>
    
    <meta name="twitter:card" content="summary" />
    <#list openGraph?keys as keyOG>
      <#assign valueOG = openGraph[keyOG]> 
      <#if keyOG == "og:description" >
        <#assign valueOG = valueOG[0..*300] + (valueOG?length > 300)?then('...','') > 
      </#if>
      <#if keyOG == "og:description" && valueOG?has_content >
          <meta property="${keyOG}" content="${valueOG}" />
      <#elseif keyOG?contains("og:image") && openGraph["og:image"]?has_content>
          <meta property="${keyOG}" content="${valueOG}" />
      <#elseif keyOG != "og:description" && !keyOG?contains("og:image")>
          <meta property="${keyOG}" content="${valueOG}" />
      </#if>
    </#list>

		<!-- Magnific Popup core JS file -->
		<script type="text/javascript" src="${javascript_folder}/vendor/lightbox.js" charset="utf-8"></script> 
  	<script type="text/javascript" src="/o/0-global-theme/libs/tarteaucitron/tarteaucitron.js"></script>
    <script type="text/javascript" src="/o/0-global-theme/js/tarteaucitron.init.js"></script>

  </head>


  <body class="${css_class}">

    <@liferay_ui["quick-access"] contentId="#main-content" />

    <@liferay_util["include"] page=body_top_include />

    <@liferay.control_menu />

    <div id="mns-global">
        <header id="mns-header">
          <div id="layer"></div>
            <!-- Top header bar -->
            <div class="mns-top-header">
                <div>
                    <a href="http://www.strasbourg.eu/" target="_blank"><img src="/o/summer-2018-theme/images/logo-strasbourg-eu.png" alt="Logo Strasbourg" width="183" height="40" /></a>
                </div>
                <div>
                    <!-- a href="#" class="mns-w-fixe-1"><span>Carte interractive</span></a>
                    <a href="#" class="mns-w-fixe-2"><span>Pro & Presse</span></a -->
                    <a href="${homeURL}/contact" class="mns-contact-link" title="Contact">
                      <span class="mns-contact">
                        <span class="mns-picto-contact"></span>
                        <span class="mns-text">Contact</span>
                      </span>    
                    </a>
                    <#assign entity = themeDisplay.getURLCurrent()?keep_after(layout.friendlyURL)?keep_before('\\?','r') />
                    <#if homeURL != "">
                        <a href="/fr${homeURL}${layout.friendlyURL}${entity}" title="Français"  class="${(locale.language =='fr')?then('active','')}">FR</a>  
                        <a href="/de${homeURL}${layout.friendlyURL}${entity}" title="Deutsch" class="${(locale.language =='de')?then('active','')}" >DE</a>
                        <a href="/en${homeURL}${layout.friendlyURL}${entity}" title="English" class="${(locale.language =='en')?then('active','')}" >EN</a>
                    <#else>
                        <a href="/fr${layout.friendlyURL}${entity}" title="Français"  class="${(locale.language =='fr')?then('active','')}">FR</a>  
                        <a href="/de${layout.friendlyURL}${entity}" title="Deutsch" class="${(locale.language =='de')?then('active','')}" >DE</a>
                        <a href="/en${layout.friendlyURL}${entity}" title="English" class="${(locale.language =='en')?then('active','')}" >EN</a>
                    </#if>
                </div>
            </div>
            <#include "${full_templates_path}/navigation.ftl" />
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

        <!-- Social Share sur chaque page - Apparait au moment du scroll de la page -->
        <div class="social-share">
            <input class="toggle-input" id="toggle-input" type="checkbox" aria-label="Réseaux sociaux" /> 
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

        <!-- Footer -->
        <footer class="mns-footer">
            <@liferay_portlet["runtime"]
                portletProviderAction=portletProviderAction.VIEW
                portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                instanceId="footer"
                settingsScope="group" />
        </footer>
    </div>

    <@liferay_util["include"] page=body_bottom_include />
    <@liferay_util["include"] page=bottom_include />
    <script src="/o/summer-2018-theme/js/t_main.js"></script>
    <script type="text/javascript">
      if ($(window).width() >= 1280) {
        $(window).stellar();
      }
    </script>    

    <script type="text/javascript">
        window.onload = function(){
            var url = window.location.toString();
            document.getElementById("sharefacebook").setAttribute("href","https://www.facebook.com/sharer/sharer.php?u="+ encodeURIComponent(document.URL));
            document.getElementById("sharetwitter").setAttribute("href","https://twitter.com/intent/tweet?text="+url);
            document.getElementById("ShareLinkedIn").setAttribute("href","http://www.linkedin.com/shareArticle?mini=true&url="+url);
            document.getElementById("ShareMail").setAttribute("href","mailto:?body="+url);
        }
    </script>
		
		<!-- Lightbox implementation and Vendors JS -->
		<script src="${javascript_folder}/lightbox-custom.js" charset="utf-8"></script>  

  </body>
</html>