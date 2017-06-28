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
			<h1></h1> 
			<nav class="nav-lang"> 
				<div class="center"> 
					<a href="http://www.strasbourg.eu/" title="Strasbourg.eu (nouvelle fenêtre)" class="logo-strasbourg" target="_blank"> 
						<img src="/o/tipi-theme/images/logo_strasbourg.png" alt="Strasbourg.eu"> 
					</a> 
					<ul> <li class="contact"> <a href="/web/tipi/pied-de-page/contact" title="Contact">Contact</a> </li> </ul> 
					<div class="clearfix"></div> 
				</div> 
			</nav> 
			<div class="logo"> 
				<a href="https://tipi.strasbourg.eu/fr/accueil" title="Retour à l'accueil - TIPI" style="display: block; width: 100%; height: 100%;"> </a> 
			</div> 
			<nav class="nav-primary"> 
				<div class="center"> 
					<a id="touch-menu" class="mobile-menu" href="#"><i class="icon-reorder"></i>Menu</a> 
					<ul class="menu"> 
						<li class="selected"> <a href="https://tipi.strasbourg.eu/fr/accueil"><span> Accueil</span></a> </li> 
						<li> <a href="https://tipi.strasbourg.eu/fr/facturation-petite-enfance"><span> Facturation petite enfance</span></a> </li> 
						<li> <a href="https://tipi.strasbourg.eu/fr/facturation-restauration-scolaire"><span> Facturation restauration scolaire</span></a> </li> 
						<li> <a href="https://tipi.strasbourg.eu/fr/facturation-periscolaire"><span> Facturation services périscolaires (APM-ALM)</span></a> </li> 
						<li> <a href="https://tipi.strasbourg.eu/fr/facture-d-eau-et-d-assainissement"><span> Facture d&amp;#039;eau et d&amp;#039;assainissement</span></a> </li> 
					</ul> 
				</div> 
			</nav> 
			<script>
			/*<![CDATA[*/
				$(document).ready(function(){
					var b=$("#touch-menu");
					var a=$(".menu");
					$(b).on("click",function(c){
						c.preventDefault();a.slideToggle()
					});
					$(window).resize(function(){
						var c=$(window).width();
						if(c>767&&a.is(":hidden")){
							a.removeAttr("style")
						}
					})
				});
			/*]]>*/
			</script> 
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
		<div class="footer-top"> 
			<div class="center"> 
				<div class="f-left"> 
					<ul class="nav-footer">
						<li> <a href="accueil" title="Accueil">Accueil</a></li> 
						<li> <a href="pied-de-page/contact" title="Contact">Contact</a></li> 
						<li> <a href="mentions-legales" title="Mentions légales">Mentions légales</a></li> 
					</ul> 
					<p class="footer-contact"> Ville et Eurométropole de Strasbourg © 2015</p> 
				</div> 
				<div class="f-right"> 
					<div class="social-network"> 
						<span>Suivez-nous</span> 
						<ul> 
							<li> <a class="btn-fb" href="https://www.facebook.com/strasbourg.eu" target="_blank" title="Page Facebook de Strasbourg.eu (nouvelle fenêtre)">Facebook</a></li> 
							<li> <a class="btn-twitter" href="https://twitter.com/strasbourg" target="_blank" title="Fil Twitter de Strasbourg.eu (nouvelle fenêtre)">Twitter</a></li> 
							<li> <a class="btn-daily" href="http://www.dailymotion.com/villedestrasbourg" target="_blank" title="Les vidéos de Strasbourg.eu (nouvelle fenêtre)">Dailymotion</a></li> 
							<li> <a class="btn-insta" href="https://instagram.com/strasbourg_eurometropole" target="_blank" title="Instagram de Strasbourg.eu (nouvelle fenêtre)">Instagram</a></li> 
							<li> <a class="btn-tumblr" href="http://www.strasblr.eu/" target="_blank" title="Strasblr : un autre regard sur Strasbourg (nouvelle fenêtre)">Tumblr</a></li> 
							<li> <a class="btn-netvibes" href="http://www.netvibes.com/villedestrasbourg" target="_blank" title="Page Netvibes de Strasbourg.eu (nouvelle fenêtre)">Netvibes</a></li> 
						</ul> 
					</div> 
				</div> 
				<div class="clearer"> &nbsp;</div> 
			</div> 
		</div>
		<div class="closure"> <div class="center"> <a href="http://www.strasbourg.eu" target="_blank" title="Strasbourg.eu">Strasbourg.eu</a></div> </div>
	</footer>
</div>

</body>

	<@liferay_util["include"] page=body_bottom_include />

	<@liferay_util["include"] page=bottom_include />

</html>