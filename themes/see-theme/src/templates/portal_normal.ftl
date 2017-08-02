<!DOCTYPE html>
<#include init />
<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
  <title>Accueil - Semaine de l'Entrepreneur Européen</title>
  <link rel="shortcut icon" href="/o/see-theme/images/favicon.ico">
  <link rel="apple-touch-icon" href="/o/see-theme/images/apple-touch-icon.jpg">
  <link rel="apple-touch-icon" sizes="72x72" href="/o/see-theme/images/apple-touch-icon-72x72.jpg">
  <link rel="apple-touch-icon" sizes="114x114" href="/o/see-theme/images/apple-touch-icon-114x114.jpg">
  <link rel="stylesheet" type="text/css" href="/o/see-theme/css/custom-animations.css" />

  <@liferay_util["include"] page=top_head_include />
</head>
<body class="${css_class}">
  <@liferay_ui["quick-access"] contentId="#main-content" />
  <@liferay_util["include"] page=body_top_include />
  <@liferay.control_menu />

  <div class="see">
    <!-- Header langue -->
    <nav class="nav-lang">
      <div class="center">
        <a href="http://www.europtimist.eu/" title="Strasbourg.eu (nouvelle fenêtre)" class="logo-strasbourg" target="_blank"> <img src="/o/see-theme/images/logo_strasbourg.png" alt="Strasbourg.eu"> </a> 
        <ul>
          <li class="active"><a accesskey="1" href="http://www.strasbourgaimesesetudiants.eu/accueil?p_p_id=82&amp;p_p_lifecycle=1&amp;p_p_state=normal&amp;p_p_mode=view&amp;_82_struts_action=%2Flanguage%2Fview&amp;languageId=fr_FR" title="Français">FR</a></li>
          <li><a accesskey="1" href="http://www.strasbourgaimesesetudiants.eu/accueil?p_p_id=82&amp;p_p_lifecycle=1&amp;p_p_state=normal&amp;p_p_mode=view&amp;_82_struts_action=%2Flanguage%2Fview&amp;languageId=de_DE" title="Deutsch">DE</a></li>
        </ul>
        <div class="clearfix"></div>
      </div>
    </nav>

    <section id="content">
      <#if selectable>
        <@liferay_util["include"] page=content_include />
      <#else>
        ${portletDisplay.recycle()}
        ${portletDisplay.setTitle(the_title)}
        <@liferay_theme["wrap-portlet"] page="portlet.ftl" />
        <@liferay_util["include"] page=content_include />
      </#if>
    </section>

  </div>

  <@liferay_util["include"] page=body_bottom_include />
  <@liferay_util["include"] page=bottom_include />
  <!-- inject:js -->
  <script type="text/javascript">
    if(typeof jQuery == 'undefined'){
      document.write('<script type="text/javascript" src="/o/see-theme/js/jquery-2.1.4.min.js?ver=1"></'+'script>');
    }
  </script>
  <script>
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
      <#assign homeURL = "/web${layout.group.friendlyURL}" />
    <#else>
      <#assign homeURL = "/" />
    </#if>
    window.homeURL = '${homeURL}';
    define._amd = define.amd;
    define.amd = false;
  </script>
  <script src="https://maps.googleapis.com/maps/api/js?v=3&libraries=places&key=AIzaSyAZ_4b-Rip0JyK5Ti8yKOxXKjKpjfcBvdM"></script>
  <script type="text/javascript" src="/o/see-theme/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/jquery.validate.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/toastr.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/jquery.waypoints.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/jquery.plugin.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/jquery.countTo.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/masonry.pkgd.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/modal-box.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/ventcamp.js"></script>
  <script>
    define.amd = define._amd;
  </script>
  <!-- endinject -->
</body>
</html>