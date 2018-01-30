<!DOCTYPE html>

<#include init />

<#assign GroupIdGlobal = themeDisplay.getCompanyGroupId() />
<#if locale.language == "en">
	<#assign lang = "en_US" />
	<#assign websiteUrl = "//www.en.strasbourg.eu" />
<#else>
	<#if locale.language == "fr">
		<#assign lang = "fr_FR" />
		<#assign websiteUrl = "//www.strasbourg.eu" />
	<#else>
		<#if locale.language == "de">
			<#assign lang = "de_DE" />
			<#assign websiteUrl = "//www.de.strasbourg.eu" />
		</#if>
	</#if>
</#if>


<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

	<head>

		<meta content="initial-scale=1.0, width=device-width" name="viewport" />

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>${the_title} | ${languageUtil.get(locale, "site.title.ond")}</title>
		<meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=0.8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta property="fb:admins" content="1325544060" />
		
		<!-- Normalize, jQuery Slider et jQuery UI) -->
	    <link rel="stylesheet" href="${css_folder}/lr6/normalize.css">
	    <link rel="stylesheet" href="${css_folder}/lr6/jquery.bxslider.css">
	    <link rel="stylesheet" href="${css_folder}/lr6/jquery-ui-1.10.3.custom.min.css">
	    <link rel="stylesheet" href="${css_folder}/lr6/jquery.colorbox.css" >

	    <!-- custom.css et thèmes liferay -->
		<@liferay_util["include"] page=top_head_include />
		
		<!-- Google fonts -->
		<link href='//fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
	    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
		<link href='//fonts.googleapis.com/css?family=Lato:400,700,900,300' rel='stylesheet' type='text/css' />
	       
		<!-- jQuery 1.9.1 -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>

		<!-- Magnific Popup core JS file -->
		<script type="text/javascript" src="${javascript_folder}/vendor/lightbox.js" charset="utf-8"></script> 
	</head>

	<body class="ond ${css_class}<#if layout.getFriendlyURL() == '/accueil'> home</#if>">

		<@liferay_ui["quick-access"] contentId="#main-content" />

		<@liferay_util["include"] page=body_top_include />

		<@liferay.control_menu />
					
		<div id="wrapper" class="wrapper">

		 	<!-- HEADER -->
		    <header class="header">
		        <nav class="nav-lang">
		            <div class="center">
						<a href="//www.strasbourg.eu/" title="Strasbourg.eu (<@liferay_ui.message key='new-window' />)" class="logo_strasbourg" target="_blank"> 
		                    <img src="${images_folder}/../img/logo_strasbourg.png" alt="Strasbourg.eu" />
		                </a>
		                <ul>
		                    <li class="contact">
		                        <a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}/pied-de-page/contact" title="Contact"><@liferay_ui.message key='contact' /></a>
		                    </li>
		                </ul>
		                <div class="clearfix"></div>
		            </div>
		        </nav>
		        <h1 class="title"><@liferay_ui.message key='site.title.ond' /></h1>
		        <div class="logo">
		        	<a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}" title="<@liferay_ui.message key='breadcrumb-home' /> - <@liferay_ui.message key='site.title.ond' />">
		               <img src="${images_folder}/../img/logo.png" alt="<@liferay_ui.message key='site.title.ond' />" />
		           </a>
		       </div>
		       <!-- HEADEROND -->
				<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
		        <#if layout.getFriendlyURL() == "/accueil">
			       	<@liferay_portlet["runtime"]
						defaultPreferences="${freeMarkerPortletPreferences}"
						portletProviderAction=portletProviderAction.VIEW
						portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						instanceId="header-home"
						settingsScope="group" />
				<#else>
			       	<@liferay_portlet["runtime"]
						defaultPreferences="${freeMarkerPortletPreferences}"
						portletProviderAction=portletProviderAction.VIEW
						portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
						instanceId="header-pages"
						settingsScope="group" />
				</#if>
				${freeMarkerPortletPreferences.reset()}
		       <!-- /HEADEROND -->

				<#if has_navigation>
					<#include "${full_templates_path}/navigation.ftl" />
				</#if>

		    </header>
		    <!-- END HEADER -->

			<div class="content">
				<section class="ariane" style="margin-top: 0px;">
		            <div class="center">
		                <div class="fil-ariane">
		                    <p>
			                    <@liferay_ui.message key='you-are-here' /> : 
								<#if layout.getFriendlyURL() != "/accueil">
			                    	<a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}"><@liferay_ui.message key='breadcrumb-home' /></a> >
								</#if> 
							</p> 
							<div class="breadcrumbs-bar">
								<@liferay.breadcrumbs />
							</div>
		                </div>
		                <aside class="quick-links-medias">
		                    <ul>
		                        <li><a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}/medias/videos" class="videos" title="<@liferay_ui.message key='gallery_videos' />"><@liferay_ui.message key='gallery_videos' /></a></li>
		                        <li><a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}/medias/visuels" class="images" title="<@liferay_ui.message key='gallery_images' />"><@liferay_ui.message key='gallery_images' /></a></li>
		                    </ul>
		                </aside>
		                <div class="clearfix"></div>
		            </div>
		        </section>
				<!-- EDITORIAL -->
				<section class id="aui-3-2-0PR1-1157">
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
				<!-- END EDITORIAL -->	   	
			</div>

			<!-- FOOTER -->
			<a href="#" class="btn-top" title="Haut de page"><span><@liferay_ui.message key='eu.ond-theme.top' /></span></a>
		    <footer class="footer">
		        
				<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
				<@liferay_portlet["runtime"]
					defaultPreferences="${freeMarkerPortletPreferences}"
					portletProviderAction=portletProviderAction.VIEW
					portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
					instanceId="footer"
					settingsScope="group" />
				${freeMarkerPortletPreferences.reset()}
		        <div class="closure">
		            <div class="center">
		                <a href="${websiteUrl}" title="Strasbourg.eu (<@liferay_ui.message key='new-window' />)" target="_blank">Strasbourg.eu</a>
		            </div>
		        </div>
		    </footer>
		    <!-- END FOOTER -->
		</div>

		<@liferay_util["include"] page=body_bottom_include />

		<@liferay_util["include"] page=bottom_include />

		<!-- inject:js -->
		<!-- endinject -->
		
		<!-- Lightbox implementation and Vendors JS -->
		<script src="${javascript_folder}/lightbox-custom.js" charset="utf-8"></script>  
		<script src="${javascript_folder}/vendor/jquery.uniform.js"></script>
	    <script src="${javascript_folder}/vendor/jquery-ui-1.10.3.custom.min.js"></script>
	    <script src="${javascript_folder}/vendor/jquery.bxslider.min.js"></script>
		<!-- <script src="${javascript_folder}/vendor/retina-1.1.0.min.js" charset="utf-8"></script>  -->
	    <script src="${javascript_folder}/vendor/tooltip.js" charset="utf-8"></script>
		<script src="${javascript_folder}/custom.js" charset="utf-8"></script>  

	</body>
</html>