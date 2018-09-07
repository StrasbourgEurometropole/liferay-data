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

<!-- Recuperation des lieux -->
<#assign initiativePlaces = entry.getPlacitPlaces() />

<!-- Initialisation des conteneurs de coordonnees GPS -->
<#assign initiativePlaceMercators = [] />
<#assign eventPlaceMercators = [] />

<!-- Est-ce que l'utilisateur est connecte ?-->
<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />

<div class="pro-page-detail pro-page-detail-initiative">
	<div class="container">
		<div class="col-lg-11 col-lg-offset-1">
			<article>
				<header>
					<div class="pro-header-participation pro-theme-croissance">
						<h1>${entry.title}</h1>
						<div class="pro-wrapper-meta">
							<div class="pro-statut"><span>${participationStatus}</span></div>
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
								<!-- Si une image existe -->
								<#if entry.getImageURL()?has_content>
									<img src="${entry.getImageURL()}" width="40" height="40" alt="Image de l'auteur"/>
								</#if>
							</figure>
							<p>Initiative publiée le ${entry.createDate?date?string['dd/MM/yyyy']} par :</p>
							<p><strong>${entry.author}</strong></p>
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
								<li role="presentation" class="active"><a href="#description" aria-controls="description" role="tab" data-toggle="tab" title="Onglet de
							description">Description</a></li>
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
									<p><strong>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</strong></p>
									<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
										ea commodo consequat.</p>
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
                            <#if request.session.getAttribute("has_pact_signed")!false>
                                <a href="#pro-approuv" class="pro-like"
                                    data-typeid="15" 
                                    data-isdislike="false"
                                    data-title="${entry.getTitle()}" 
                                    data-entityid="${entry.initiativeId}"
                                    data-entitygroupid="${entry.groupId}"
                                    title="Cliquez pour approuver">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a href="#pro-not-approuv" class="pro-dislike"
                                    data-typeid="15" 
                                    data-isdislike="true"
                                    data-title="${entry.getTitle()}" 
                                    data-entityid="${entry.initiativeId}"
                                    data-entitygroupid="${entry.groupId}"
                                    title="Cliquez pour désapprouver">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            <#elseif !request.session.getAttribute("has_pact_signed")?? || (request.session.getAttribute("has_pact_signed")?? && !request.session.getAttribute("has_pact_signed")) >
                                <a class="pro-like" name="#Pact-sign">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a class="pro-dislike" name="#Pact-sign">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            </#if>
                        </div>
					
						<div class="bloc-iframe maps" data-theme="default" data-lat="48.5692059" data-lng="7.6920547" data-marker="true" data-markericon="initiative"
							 data-zoom="12" data-filter-options="filterMapDetail"></div>
							 
						
						<div class="pro-wrapper-links">
						
							<#if isUserloggedIn>
								<a href="#" class="pro-btn-yellow" title="Ouverture d'une pop-in pour contacter le porteur" data-toggle="modal" data-target="#modalContacter">Contacter le
									porteur</a>
							<#else>
								<a href="#" class="pro-btn-yellow" title="Ouverture d'une pop-in pour contacter le porteur" data-toggle="modal" data-target="#myModal">Contacter le
								porteur</a>
							</#if>

								
							<#if isUserloggedIn>	
								<a href="#popin" class="pro-btn-yellow" title="Ouverture d'une pop-in pour proposer mon aide" data-toggle="modal" data-target="#modalAide">Proposer mon aide</a>
							<#else>
								<a href="#popin" class="pro-btn-yellow" title="Ouverture d'une pop-in pour proposer mon aide" data-toggle="modal" data-target="#myModal">Proposer mon aide</a>
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
					<a href="#popin" class="pro-btn-yellow" data-toggle="modal" data-target="#modalInitiative">Déposer une initiative</a>
					<a href="listing-initiative.html" class="pro-btn">Toutes les initiatives</a>
				</div>
			</div>

			
		</div>
	</section>

		<@liferay_portlet["runtime"]
		portletProviderAction=portletProviderAction.VIEW
		portletName="eu_strasbourg_portlet_project_ProjectPopupPortlet"
		instanceId="contactInitiativeAuthor"
		/>
		
		<@liferay_portlet["runtime"]
		portletProviderAction=portletProviderAction.VIEW
		portletName="eu_strasbourg_portlet_project_ProjectPopupPortlet"
		instanceId="giveInitiativeHelp"
		queryString="entityid=123456789"
		/>
	
</div>