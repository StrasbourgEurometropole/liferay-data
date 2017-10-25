<!DOCTYPE html>
<#include init />
<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
  <title>${the_title}</title>

  <link rel="stylesheet" type="text/css" href="/o/single-page-theme/css/custom-animations.css" />

  <@liferay_util["include"] page=top_head_include />
  
  <link rel="icon" type="image/png" href="/image/layout_set_logo?img_id=${layout.layoutSet.logoId}" data-bini="bini-test" />
</head>
<body class="${css_class}">
  <@liferay_ui["quick-access"] contentId="#main-content" />
  <@liferay_util["include"] page=body_top_include />
  <@liferay.control_menu />

  <div class="see">
  
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
      document.write('<script type="text/javascript" src="/o/single-page-theme/js/jquery-2.1.4.min.js?ver=1"></'+'script>');
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
  <script type="text/javascript" src="/o/single-page-theme/js/jquery.validate.min.js"></script>
  <script type="text/javascript" src="/o/single-page-theme/js/toastr.min.js"></script>
  <script type="text/javascript" src="/o/single-page-theme/js/jquery.waypoints.min.js"></script>
  <script type="text/javascript" src="/o/single-page-theme/js/jquery.plugin.js"></script>
  <script type="text/javascript" src="/o/single-page-theme/js/jquery.countTo.js"></script>
  <script type="text/javascript" src="/o/single-page-theme/js/jquery.appear.js"></script>
  <script type="text/javascript" src="/o/single-page-theme/js/masonry.pkgd.min.js"></script>
  <script type="text/javascript" src="/o/single-page-theme/js/modal-box.js"></script>
  <script type="text/javascript" src="/o/single-page-theme/js/ventcamp.js"></script>
  <script>
    define.amd = define._amd;
  </script>
  <!-- endinject -->
</body>
</html>