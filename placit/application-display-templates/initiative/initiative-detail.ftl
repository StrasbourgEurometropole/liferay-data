<!-- DETAIL D'UNE INITIATIVE -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation des thématiques -->
<#if entry.getThematicCategories()??>
    <#assign initiativeThematics = entry.getThematicCategories() />
</#if>

<!-- Recuperation du statut de l'initiative -->
<#assign participationStatus = entry.getStatusCategory().getTitle(locale) />

<#-- Initialisation des conteneurs de vignettes -->
<#assign initiativeJSON = entry.toJSON() />

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />
<#assign hasUserPactSign = request.session.getAttribute("has_pact_signed")!false />
<#assign isUserBanned = request.session.getAttribute("is_banish")!false />

<div class="pro-page-detail pro-page-detail-initiative">
	<div class="container">
		<div class="col-lg-11 col-lg-offset-1">
			<article>
				<header>
					<div class="pro-header-participation pro-theme-croissance">
						<h1>${entry.title}</h1>
						<div class="pro-wrapper-meta">
							<#if participationStatus?has_content>
								<div class="pro-statut" ><span style="background : #${entry.getStatusCategoryColor()};">${participationStatus}</span></div>
							</#if>
							<div class="pro-meta">
							
								<!-- Liste des quartiers  -->
								<span>${entry.getDistrictLabel(locale)}</span>

								<!-- Liste des thématiques -->
								<#if initiativeThematics?? >
									<#list initiativeThematics as initiativeThematic >
										<span>${initiativeThematic.getTitle(locale)}</span>
									</#list>
								</#if>

								<!-- Liste des projets -->						
								<#if initiativeProjects?? >
									<#list initiativeProjects as initiativeProject >
										<span>${initiativeProject.getTitle(locale)}</span>
									</#list>
								</#if>
							</div>
						</div>
						<div class="pro-header-auteur">
							<figure>
								<img src="${entry.authorImageURL}" width="40" height="40" alt="Image de l'auteur"/>
							</figure>
							<p>Initiative publiée le ${entry.publicationDate?date?string['dd/MM/yyyy']} par :</p>
							<p><strong>${entry.getAuthorLabel()}</strong></p>
						</div>
					</div>

					<div id="breadcrumb">
					<span>
						<span>
							<a href="${homeURL}">Accueil</a>
						<a href="${homeURL}initiatives">Initiatives</a>
						<span class="breadcrumb_last">${entry.title}</span>
						</span>
					</span>
					</div>
				</header>

				<div class="row pro-container-detail-event">
					<div class="col-sm-8">

						<!-- Test du choix du media : "true"/image, "false"/video --> 
						<#if entry.getMediaChoice() == false && entry.getVideoUrl() != "" >
							<div class="pro-bloc-texte pro-main-img">
								<div class="pro-bloc-video bloc-standard">
									<div class="mask-video">
										<figure class="o80" role="group">
											<!-- Si une image existe malgrès le choix d'une vidéo pour l'affichage,
											on la présente en tant qu'image de couverture  -->
											<#if entry.getImageURL()?has_content>
												<img src="${entry.getImageURL()}" alt="Couverture de la vidéo" width="960" height="600"/>
											</#if>
										</figure>
										<a href="#play" class="btn-ytbe" title="Lire la vidéo">
											<span class="pro-btn-video" title="Lire la vidéo"><span class="icon-ico-lecteur"></span>Voir la vidéo</span>
										</a>
									</div>
									<div class="embed-container" data-urlvideo="${entry.videoUrl}"></div>
								</div>
							</div>
						<#else>
							<div class="pro-main-img">
								<figure>
									<img src='${entry.getImageURL()}' alt="Image agenda" width="880" height="593" class="fit-cover"/>
									<figcaption>${entry.getImageCopyright(locale)}</figcaption>
								</figure>
							</div>
						</#if>

						<div class="pro-tabs">

							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"><a href="#description" aria-controls="description" role="tab" data-toggle="tab" title="Onglet de description">Description</a></li>
								<li role="presentation"><a href="#lieux" aria-controls="lieux" role="tab" data-toggle="tab" title="Onglet des aides">Ils aident</a></li>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">

								<!-- Description -->
								<div role="tabpanel" class="tab-pane fade in active pro-bloc-texte" id="description">
									<p>${entry.description}</p>
								</div>


								<!-- Les personnes ayant pris part à l'initiative -->
								<div role="tabpanel" class="tab-pane fade pro-bloc-texte" id="lieux">
									<p><strong>Liste des citoyens ayant proposé une aide au porteur de l'initiative.</strong></p>
									<p>Cette aide peut prendre plusieurs formes : </p>
									<ul>
										<li>Du temps</li>
										<li>De l'argent</li>
										<li>Un lieu</li>
										<li>Une expertise</li>
									</ul>
									<p>N'hésitez pas à proposer la votre si cette initiative fait echos.</p>
									
									<div class="row pro-wrapper-people">
										<div class="col-md-4 col-sm-6">
											<div>
												<figure class="fit-cover">
													<img src="assets/images/medias/comm-sylvie.jpg" width="200" height="140" alt="Image du quartier"/>
												</figure>
												<div>
													<time datetime="2018-02-1">Le 12/02/2018</time>
													<h3>Emeline Sidoyer</h3>
													<p>à proposé une aide financière</p>
												</div>
											</div>
										</div>
										<div class="col-md-4 col-sm-6">
											<div>
												<figure class="fit-cover">
													<img src="assets/images/medias/img-col-33-1.jpg" width="200" height="140" alt="Image du quartier"/>
												</figure>
												<div>
													<time datetime="2018-02-1">Le 12/02/2018</time>
													<h3>Richard Prelo</h3>
													<p>à proposé une aide financière</p>
												</div>
											</div>
										</div>
										<div class="col-md-4 col-sm-6">
											<div>
												<figure class="fit-cover">
													<img src="assets/images/medias/img-col-33-1.jpg" width="200" height="140" alt="Image du quartier"/>
												</figure>
												<div>
													<time datetime="2018-02-1">Le 12/02/2018</time>
													<h3>Maxime Porto</h3>
													<p>à proposé une aide financière</p>
												</div>
											</div>
										</div>
										<div class="col-md-4 col-sm-6">
											<div>
												<figure class="fit-cover">
													<img src="assets/images/medias/img-col-33-1.jpg" width="200" height="140" alt="Image du quartier"/>
												</figure>
												<div>
													<time datetime="2018-02-1">Le 12/02/2018</time>
													<h3>Jérémy M.</h3>
													<p>à proposé une aide financière</p>
												</div>
											</div>
										</div>
										<div class="col-md-4 col-sm-6">
											<div>
												<figure class="fit-cover">
													<img src="assets/images/medias/img-col-33-1.jpg" width="200" height="140" alt="Image du quartier"/>
												</figure>
												<div>
													<time datetime="2018-02-1">Le 12/02/2018</time>
													<h3>Jérémy M.</h3>
													<p>à proposé une aide financière</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


					<aside class="col-sm-4">
					
						<!-- Bloc : avis -->
                        <div class="pro-push-avis">
                            <#if isUserloggedIn && hasUserPactSign && !isUserBanned>
                                <a href="#pro-approuv" class="pro-like"
                                    data-typeid="19" 
                                    data-isdislike="false"
                                    data-title="${entry.getTitle()}" 
                                    data-entityid="${entry.initiativeId}"
                                    data-entitygroupid="${entry.groupId}"
                                    title="Cliquez pour approuver">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a href="#pro-not-approuv" class="pro-dislike"
                                    data-typeid="19" 
                                    data-isdislike="true"
                                    data-title="${entry.getTitle()}" 
                                    data-entityid="${entry.initiativeId}"
                                    data-entitygroupid="${entry.groupId}"
                                    title="Cliquez pour désapprouver">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            <#elseif isUserBanned>
                                <a class="pro-like" name="#IsBanned">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a class="pro-dislike" name="#IsBanned">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            <#else>
                            	<a class="pro-like" name="#Pact-sign">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a class="pro-dislike" name="#Pact-sign">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            </#if>
                        </div>
					
						<!-- Bloc : map -->
                        <div class="bloc-iframe leaflet-map" id="mapid" ></div>
						
						<div class="pro-wrapper-links">
						
							<#if isUserloggedIn && hasUserPactSign && !isUserBanned>
								<a href="#" class="pro-btn-yellow" title="Ouverture d'une pop-in pour contacter le porteur" data-toggle="modal" data-target="#modalInitiativeContact">Contacter le porteur</a>
							<#elseif isUserBanned>
								<a name="#IsBanned" class="pro-btn-yellow" title="Ouverture d'une pop-in pour proposer mon aide">Proposer mon aide</a>
							<#else>
								<a name="#Pact-sign" class="pro-btn-yellow" title="Ouverture d'une pop-in pour contacter le porteur">Contacter le porteur</a>
							</#if>
								
							<#if isUserloggedIn && hasUserPactSign && !isUserBanned>
								<a href="#popin" class="pro-btn-yellow" title="Ouverture d'une pop-in pour proposer mon aide" data-toggle="modal" data-target="#modalGiveInitiativeHelp">Proposer mon aide</a>
							<#elseif isUserBanned>
								<a name="#IsBanned"class="pro-btn-yellow" title="Ouverture d'une pop-in pour proposer mon aide">Proposer mon aide</a>
							<#else>
								<a name="#Pact-sign" class="pro-btn-yellow" title="Ouverture d'une pop-in pour proposer mon aide" >Proposer mon aide</a>
							</#if>
							
							<a href="#pro-link-commentaire" class="pro-btn-yellow" title="Scroll jusqu'à la zone de commentaire">Réagir</a>

						</div>
					</aside>
				</div>
			</article>

		</div>
	</div>


	<section id="pro-link-evenement" class="pro-bloc-slider pro-slider-event">
		<div class="container">
			<div class="col-lg-10 col-lg-offset-1">
				<h2>D’autres initiatives</h2>
				<div class="pro-wrapper">
						<#if isUserloggedIn && hasUserPactSign && !isUserBanned>
							<a href="#" class="pro-btn-yellow" title="Ouverture de la popup de dépot" data-toggle="modal" data-target="#modalSubmitInitiative">Déposer une initiative</a>
						<#elseif isUserBanned>
							<a name="#IsBanned" class="pro-btn-yellow" title="Ouverture de la popup de dépot">Déposer une initiative/a>
						<#else>
							<a name="#Pact-sign" class="pro-btn-yellow" title="Ouverture de la popup de dépot">Déposer une initiative</a>
						</#if>				
					<a href="${homeURL}initiatives" class="pro-btn">Toutes les initiatives</a>
				</div>
			</div>

			
		</div>
	</section>
</div>

<script>
    // Récupération des entités en JSON à afficher sur la map et ajout des données dynamiques manquantes
    var initiativeJSON = ${initiativeJSON};
    initiativeJSON.link = '${homeURL}detail-initiative/-/entity/id/${entry.initiativeId}';

    // Variable pointeur
    var initiativeMarkers = []

    $(document).ready(function() {
        // Gestion de la carte interactive
        // Notes : voir dans le theme placit "override/custom.js"

        //Création de la carte au centre de strasbourg
        leafletMap = getLeafletMap();

        // Création du cluster permettant le regroupement de points et le centrage
        markersCluster = L.markerClusterGroup();

        for(var i= 0; i < initiativeJSON.placitPlaces.length; i++) {
            var marker = getInitiativeMarker(
                initiativeJSON,
                [initiativeJSON.placitPlaces[i].mercatorY, initiativeJSON.placitPlaces[i].mercatorX]
            );

            // Ajout du point dans le Cluster de marqueurs
            markersCluster.addLayer(marker);
            // Ajout du marker dans le tempon
            initiativeMarkers.push(marker);
        }

        leafletMap.addLayer(markersCluster);

        // Adapter le zoom si des marqueurs existent
        if (markersCluster.getBounds().isValid()) {
            leafletMap.fitBounds(markersCluster.getBounds());
        }

    });
</script>