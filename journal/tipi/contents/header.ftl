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