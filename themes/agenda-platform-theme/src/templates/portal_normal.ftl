<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<#if permissionChecker.isGroupAdmin(themeDisplay.scopeGroupId)>
	<@liferay.control_menu />
</#if>

<div class="top-header">
	<div class="container-fluid">
		<div class="row">
			<a href="http://strasbourg.eu">
				<img src="/o/agenda-platform-theme/images/strasbourg-logo.png" alt="Strasbourg.eu" />
			</a>
			<div class="login-link">
				<#if !is_signed_in>
					<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow"><@liferay_ui.message key="eu.login" /></a>
				<#else>
					<span style="color:white;padding-right: 90px;"><@liferay.language key="welcome" /> ${user_name} </span>
					<a data-redirect="${is_login_redirect_required?string}" href="/c/portal/logout" id="sign-out" rel="nofollow"><@liferay_ui.message key="eu.logout" /></a>
				</#if>
			</div>
		</div>
	</div>
</div>

<div id="wrapper">
	<header class="main-header">
		<div class="container-fluid">
			Plateforme de saisie d'événements
		</div>
	</header>

	<section class="container-fluid" id="content">
		<h1 class="hide-accessible">${the_title}</h1>
		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
	</section>

	<footer id="footer" role="contentinfo">
		<div class="container-fluid">
			<@liferay_portlet["runtime"]
				defaultPreferences="${freeMarkerPortletPreferences}"
				portletProviderAction=portletProviderAction.VIEW
				portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
		</div>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>