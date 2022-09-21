<!DOCTYPE html>

<#include init />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
	<#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
	<#assign homeURL = "/" />
</#if>

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

	<head>
		<title>${the_title}</title>

		<meta content="initial-scale=1.0, width=device-width" name="viewport" />
		<link type="text/css" rel="stylesheet" href="/o/0-global-theme/css/hackliferay.css" />
		<script type="text/javascript" src="/o/0-global-theme/libs/tarteaucitron/tarteaucitron.js"></script>
		<script type="text/javascript" src="/o/0-global-theme/js/tarteaucitron.init.js"></script>

		<@liferay_util["include"] page=top_head_include />
	</head>

	<body class="${css_class}">
		<script src="/o/museum-v2-theme/js/vendors/jquery.magnific-popup.min.js"></script>

		<@liferay_ui["quick-access"] contentId="#main-content" />

		<@liferay_util["include"] page=body_top_include />

		<@liferay.control_menu />

		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>

		<footer id="footer" role="contentinfo">
			<@liferay_portlet["runtime"]
				defaultPreferences="${freeMarkerPortletPreferences}"
				portletProviderAction=portletProviderAction.VIEW
				portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
		</footer>

		<script type="text/javascript" src="/o/museum-v2-theme/js/tarteaucitron.custom.js"></script>
		<script>
			window.homeURL = '${homeURL}';
		</script>
		<script src="/o/museum-v2-theme/js/vendors/owl.carousel.min.js"></script>
		<script src="/o/museum-v2-theme/js/vendors/jquery.dotdotdot.min.js"></script>
		
		<@liferay_util["include"] page=body_bottom_include />

		<@liferay_util["include"] page=bottom_include />

		<!-- inject:js -->
		<!-- endinject -->

	</body>
</html>