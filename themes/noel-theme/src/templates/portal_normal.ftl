<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Agence Thuria">
    <title>${the_title} - ${company_name}</title>

    <@liferay_util["include"] page=top_head_include />
    
    <link href="/o/noel-theme/css/t_main.css" rel="stylesheet">
    
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
                    <a href="http://www.strasbourg.eu/" target="_blank"><img src="/o/noel-theme/images/logo-strasbourg-eu.png" alt="Logo Strasbourg" width="183" height="40" /></a>
                </div>
                <div>
                    <!--  <a href="#" class="mns-w-fixe-1"><span>Carte interractive</span></a> -->
                    <a href="#" class="mns-w-fixe-2"><span>Pro & Presse</span></a>
                    <a href="#" class="active">FR</a>
                    <a href="#">EN</a>
                    <a href="#">DE</a>
                </div>
            </div>
            <#include "${full_templates_path}/navigation.ftl" />
        </header>
        <main>
            <!-- header full -->
            <header class="mns-header" style="background: url(/o/noel-theme/images/bg-homepage.jpg) no-repeat center center /cover;">
                <div class="container mns-center">
                    <h2>La magie de noël à Strasbourg</h2>
                    <span>Du 04  au 26 décembre 2017</span>
                </div>
            </header>
            <!-- filtres de recherches -->
            <div class="small-container mns-filtres row">
                <span class="icon-cross" id="cross-mobile"></span>
                <form action="index.html" method="get">
                    <div class="col-sm-4 col-xs-12">
                        <label>Je viens...</label>
                        <select name="venir" id="venir">
                            <option>En couple</option>
                            <option>En célibataire</option>
                            <option>En famille</option>
                            <option>Entre amis</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-12">
                        <label>Pendant...</label>
                        <select name="periode" id="periode">
                            <option>Ce week-end</option>
                            <option>Ce jour-ci</option>
                            <option>Ce mois</option>
                            <option>Cette année</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-12">
                        <label>Pour un(e)...</label>
                        <select name="evenement" id="evenement">
                            <option>Noël authentique</option>
                            <option>Noël venu d'ailleurs</option>
                            <option>Noël artistique et culturel</option>
                            <option>Noël spirituel et de partage</option>
                            <option>Découvertte de la ville</option>
                            <option>Noël nocturne</option>
                        </select>
                    </div>
                    <div class="col-md-12 text-center">
                        <input type="submit" class="btn-filtre" value="Trouver" />
                    </div>
                </form>
            </div>
            <div class="small-container mns-m-filtres-search row">
                <div class="col-xs-12">
                    <span class="label">Je viens...,Pendant...,Pour un(e)... ?</span>
                    <span id="search-mobile-filtres" class="btn-filtre">Recherchez</span>
                </div>
            </div>
            
            
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
    <script src="/o/noel-theme/js/t_main.js"></script>
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