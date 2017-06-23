<!DOCTYPE html>


<#include init />

<#assign currentGroup = layout.getGroup() />

<html class="<@liferay.language key="lang.dir" />" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} | tipi.strasbourg.eu</title>

	<!-- jQuery 1.9.1 -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>

	<link rel="stylesheet" href="${images_folder}/../css/vendor/normalize.css">
	<link rel="stylesheet" href="${images_folder}/../css/vendor/owl.carousel.css">
	<link rel="stylesheet" href="${images_folder}/../css/vendor/owl.theme.css">
	<link rel="stylesheet" href="${images_folder}/../css/vendor/owl.transitions.css">
	<link rel="stylesheet" href="${images_folder}/../css/vendor/lightbox.css">

	<link rel="stylesheet" href="${images_folder}/../css/sass_main.css">
	<link rel="stylesheet" href="${images_folder}/../css/lr6/custom.css" />
	<link rel="stylesheet" href="${images_folder}/../css/tipi-style.css" />


	<@liferay_util["include"] page=top_head_include />
	<link rel="icon" type="image/png" href="${images_folder}/logo/favicon.png" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
</head>

<body class="${css_class}">
<!-- Google Analytics -->

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div id="page">
	<div id="wrapper" class="wrapper">
		<header class="header"> 
			<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
	       	<@liferay_portlet["runtime"]
				defaultPreferences="${freeMarkerPortletPreferences}"
				portletProviderAction=portletProviderAction.VIEW
				portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
				instanceId="header"
				settingsScope="group" />
			${freeMarkerPortletPreferences.reset()}
		</header>

		<div id="content">
			<section class="ariane">
	            <div class="center">
	                <div class="fil-ariane">
						<div class="breadcrumbs-bar">
							<@liferay.breadcrumbs />
						</div>
	                </div>
	                <div class="clearfix"></div>
	            </div>
	        </section>	
			<section class>
				<div class="center">		
					<#if selectable>
						<@liferay_util["include"] page=content_include />
					<#else>
						${portletDisplay.recycle()}
						${portletDisplay.setTitle(the_title)}
						<@liferay_theme["wrap-portlet"] page="portlet.ftl">
							<@liferay_util["include"] page=content_include />
						</@>
					</#if> 
				</div>
	        </section>	
		</div>
	</div>
	<div class="footer-back-to-top" title="Haut de page"></div>
	<footer class="footer">
		<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
       	<@liferay_portlet["runtime"]
			defaultPreferences="${freeMarkerPortletPreferences}"
			portletProviderAction=portletProviderAction.VIEW
			portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
			instanceId="footer"
			settingsScope="group" />
		${freeMarkerPortletPreferences.reset()}
	</footer>
</div>

</body>

	<@liferay_util["include"] page=body_bottom_include />

	<@liferay_util["include"] page=bottom_include />

</html>