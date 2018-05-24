<%@ include file="/search-asset-init.jsp"%>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-wrapper-top-listing">
	<div class="container">
		<div id="breadcrumb">
			<span> <span><a href="/">Accueil</a> <span
					class="breadcrumb_last">Listing des projets</span> </span>
			</span>
		</div>
	</div>
</div>


<section class="container">
	<div class="row">
		<div class="col-md-3 pro-bloc-facette">
			<span class="pro-affiner">Afficher votre recherche <span
				class="icon-ico-chevron-down"></span></span>

			<!-- Formulaire -->
			<aui:form action="${searchActionURL}" method="get" name="fm"
				id="search-asset-form">
				<liferay-portlet:renderURLParams varImpl="searchActionURL" />
				<liferay-util:include page="/forms/placit-projects-form.jsp"
					servletContext="<%=application %>" />
			</aui:form>


		</div>
		<div class="col-md-9 pro-wrapper-listing-video">
			<div class="pro-wrapper">
				<h2>Tous les projets</h2>
				<div class="pro-sort pro-dropdown">
					<a href="#">Tri A-Z</a>
					<ul>
						<li><a href="#">Esplanade</a></li>
						<li><a href="#">Gare</a></li>
					</ul>
				</div>
			</div>
			<div class="row pro-listing-video" data-egalize="> * > a">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class="item bloc-card-projet">
						<a href="detail-projet.php" title="lien de la page">
							<div class="img">
								<figure role="group">
									<img src='assets/images/medias/hp-projet-1.jpg'
										alt="Image agenda" width="360" height="242" class="fit-cover" />
								</figure>
								<span>Voir le projet</span>
							</div>
							<div class="content">
								<span class="location">Nom du quartier</span>
								<h3>
									Titre long du projet <br>sur deux lignes
								</h3>
								<div class="pro-wrap-thematique">
									<span>ThÃ©matique 1</span> <span>ThÃ©matique 2</span>
								</div>
							</div>
						</a>
						<ul>
							<li><a href="page.php" title="lien de la page" tabindex="-1">5
									Participation(s) en cours</a></li>
							<li><a href="page.php" title="lien de la page" tabindex="-1">2
									ÃvÃ©nement(s) Ã  venir</a></li>
							<li><a href="page.php" title="lien de la page" tabindex="-1">2
									idÃ©e(s) citoyenne(s)/Budget participatif</a></li>
							<li><a href="page.php" title="lien de la page" tabindex="-1">7
									PÃ©tition(s) ouverte(s)</a></li>
							<li><a href="page.php" title="lien de la page" tabindex="-1">34
									Initiative(s)</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>