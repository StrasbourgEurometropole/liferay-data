<!DOCTYPE html>

<#include init />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
  <#assign homeURL = "/" />
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
    <link href="https://fonts.googleapis.com/css?family=Montserrat:100,300,400,500,600,700" rel="stylesheet">
  </head>


  <body class="${css_class}">

    <@liferay_ui["quick-access"] contentId="#main-content" />

    <@liferay_util["include"] page=body_top_include />

    <@liferay.control_menu />

    <div id="mns-global">
        <div id="layer"></div>
        <header>
            <!-- Top header bar -->
            <div class="mns-top-header">
                <div>
                    <a href="http://www.strasbourg.eu/" target="_blank"><img src="/o/summer-2018-theme/images/logo-strasbourg-eu.png" alt="Logo Strasbourg" width="183" height="40" /></a>
                </div>
                <div>
                    <!-- a href="#" class="mns-w-fixe-1"><span>Carte interractive</span></a>
                    <a href="#" class="mns-w-fixe-2"><span>Pro & Presse</span></a -->
                    <a href="${homeURL}contact" class="mns-contact-link" title="Contact">
                      <span class="mns-contact">
                        <span class="mns-picto-contact"></span>
                        <span class="mns-text">Contact</span>
                      </span>    
                    </a>
                    <#if homeURL != "/">
                        <a href="/fr${homeURL}${layout.friendlyURL}">FR</a>
                        <a href="/de${homeURL}${layout.friendlyURL}" title="Deutsch">DE</a>
                        <a href="/en${homeURL}${layout.friendlyURL}" title="English">EN</a>
                    <#else>
                        <a href="/fr${layout.friendlyURL}">FR</a>
                        <a href="/de${layout.friendlyURL}" title="Deutsch">DE</a>
                        <a href="/en${layout.friendlyURL}" title="English">EN</a>
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
    <script>
    define._amd = define.amd;
    define.amd = false;
    </script>
    <script src="/o/summer-2018-theme/js/t_main.js"></script>
    <script>
    define.amd = define._amd;
    </script>
    <script type="text/javascript">
      if ($(window).width() >= 1280) {
        $(window).stellar();
      }
    </script>


    <#if  propsUtil.get('eu.strasbourg.environment') == "PROD">
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-33301756-4"></script> [^]
        <script>
          window.dataLayer = window.dataLayer || [];
          function gtag(){dataLayer.push(arguments);}
          gtag('js', new Date());

          gtag('config', 'UA-16973980-1');
        </script>
    </#if>

  </body>
</html>