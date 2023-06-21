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
<#assign initiativeHelps = entry.getHelps() />

<#-- Initialisation des conteneurs de vignettes -->
<#assign initiativeJSON = entry.toJSON() />

<#-- Récupération de l'ID de l'utilisateur -->
<#assign userID = request.session.getAttribute("publik_internal_id")!"" />

<#-- Récupération de l'ID de l'utilisateur -->
<#assign isUserHelps = entry.isUserAlreadyHelp(userID) />

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />
<#assign hasUserPactSign = request.session.getAttribute("has_pact_signed")!false />
<#assign isUserBanned = request.session.getAttribute("is_banish")!false />


<#-- Récupération des liens médias de l'entité -->
<#assign imageURL = entry.getImageURL() />

<#assign imageUrlOG = ""/>
<!-- vignette -->
<#if entry.imageURL?has_content>
    <#assign imageUrlOG=themeDisplay.getPortalURL() + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.title?html}",
"og:description":'${entry.description?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrlOG}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="pro-page-detail pro-page-detail-initiative">
	<div class="container">
		<div class="col-lg-12">
			<article>
				<div class="row pro-container-detail-event">
					<div class="col-sm-8">
						<header>
							<div class="pro-header-participation pro-theme-croissance">
								<h1>${entry.title}</h1>
								<div class="pro-wrapper-meta">
									<#if entry.getStatusCategory()?has_content>
										<div class="pro-statut" ><span style="background : #${entry.getStatusCategoryColor()};">${entry.getStatusCategory().getTitle(locale)}</span></div>
									</#if>
									<div class="pro-meta">
									
										<!-- Liste des quartiers  -->
										<span class="prefix-location">${entry.getDistrictLabel(locale)}</span>
		
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
									<p>Atelier publié le ${entry.publicationDate?date?string['dd/MM/yyyy']} par :</p>
									<p><strong>${entry.getAuthorLabel()}</strong></p>
								</div>
							</div>
		
							<div id="breadcrumb">
							<span>
								<span>
									<a href="${homeURL}">Accueil</a>
								<a href="${homeURL}ateliers-quartier">Ateliers de quartier</a>
								<span class="breadcrumb_last">${entry.title}</span>
								</span>
							</span>
							</div>
						</header>
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
									<#if entry.getImageURL()?has_content>
										<img src='${entry.getImageURL()}' alt="Image agenda" width="880" height="593" class="fit-cover"/>
										<figcaption>${entry.getImageCopyright(locale)}</figcaption>
									</#if>
								</figure>
							</div>
						</#if>

						<div class="pro-tabs">

							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation"><a href="#description" class="active" aria-controls="description" role="tab" data-toggle="tab" title="Onglet de description">Description</a></li>
								<li role="presentation"><a href="#lieux" aria-controls="lieux" role="tab" data-toggle="tab" title="Onglet des aides">Ils/Elles aident</a></li>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">

								<!-- Description -->
								<div role="tabpanel" class="tab-pane fade in active pro-bloc-texte" id="description">
									<p>${entry.description}</p>
								</div>
								<#if entry.filesURLs?has_content>
									<div class="pro-bloc-texte pro-bloc-telechargements">
										<h3>Document(s) téléchargé(s)</h3>
										<div class="row">
											<#list entry.filesURLs as fileURL>
												<#assign file = fileEntryHelper.getFileEntryByRelativeURL(fileURL) />
												<#assign title = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
												<#assign size = fileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale) />
												<div class="col-sm-6">
													<a href="${fileURL}" download title="${title}">
														<span class="pro-filename">${title}</span>
														<span class="pro-poids">Poids ${size}</span>
													</a>
												</div>
											</#list>
										</div>
									</div>
								</#if>

								<!-- Les personnes ayant pris part à l'initiative -->
								<div role="tabpanel" class="tab-pane fade pro-bloc-texte" id="lieux">
									<p><strong>Liste des citoyens-nes ayant proposé une aide au porteur de l'atelier.</strong></p>
									<p>Cette aide peut prendre plusieurs formes : </p>
									<ul>
										<li>Du temps</li>
										<li>Un lieu</li>
										<li>Une expertise</li>
									</ul>
									<p>N'hésitez pas à proposer le votre si cet atelier fait echos.</p>
									
									<div class="row pro-wrapper-people">

										<#list initiativeHelps as initiativeHelp >
											<div class="col-md-4 col-sm-6">
												<div>
													<figure class="fit-cover">
														<img src="${initiativeHelp.getAuthorImageURL()}" width="200" height="140" alt="Image de profil"/>
													</figure>
													<div>
														<time datetime="2018-02-1">Le ${initiativeHelp.getCreateDate()?date?string['dd/MM/yyyy']}</time>
														<h3>${initiativeHelp.getAuthorLabel()!""}</h3>
														<p>a proposé ${initiativeHelp.getTypesLabel()}</p>
													</div>
												</div>
											</div>
										</#list>
										
									</div>
								</div>
							</div>
						</div>
					</div>

					<aside class="col-sm-4 side-atelier">
					
						
					
						<!-- Bloc : map -->
                        <div class="bloc-iframe leaflet-map" id="mapid" ></div>
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
						
						<div class="pro-wrapper-links">
						
							<#if isUserloggedIn && hasUserPactSign && !isUserBanned>
								<a href="#" class="pro-btn-black active" title="Ouverture d'une pop-in pour contacter le/la porteur-teuse " id="buttonContactInitiativeAuthor"
								data-toggle="modal" data-target="#modalInitiativeContact">Contacter le/la porteur-teuse </a>
							<#elseif isUserBanned>
								<a name="#IsBanned" class="pro-btn-black" title="Ouverture d'une pop-in pour contacter le/la porteur-teuse " id="buttonContactInitiativeAuthor">Contacter le/la porteur-teuse </a>
							<#else>
								<a name="#Pact-sign" class="pro-btn-black" title="Ouverture d'une pop-in pour contacter le/la porteur-teuse " id="buttonContactInitiativeAuthor">Contacter le/la porteur-teuse </a>
							</#if>
							
							<#if isUserloggedIn && hasUserPactSign && !isUserBanned>
								<#if isUserHelps >
									<a href="#popin" class="pro-btn-black active" title="Je souhaite retirer mon aide" 
										data-toggle="modal" data-target="#modalRemoveInitiativeHelp">Aide proposée, la retirer</a>
								<#else>
									<a href="#popin" class="pro-btn-black" title="Ouverture d'une pop-in pour proposer mon aide" 
										data-toggle="modal" data-target="#modalGiveInitiativeHelp">Proposer mon aide</a>
								</#if>
							<#elseif isUserBanned>
								<a name="#IsBanned"class="pro-btn-black" title="Ouverture d'une pop-in pour proposer mon aide">Proposer mon aide</a>
							<#else>
								<a name="#Pact-sign" class="pro-btn-black" title="Ouverture d'une pop-in pour proposer mon aide" >Proposer mon aide</a>
							</#if>
							
							<a href="#pro-link-commentaire" class="pro-btn-black" title="Scroll jusqu'à la zone de commentaire">Réagir</a>

						</div>
					</aside>
				</div>
			</article>

		</div>
	</div>


	<#-- Recuperation des suggestions du bp -->
    <#assign suggestions = entry.getSuggestions(request, 10) />
	
	<#if suggestions?size gt 0 >
		<section id="pro-link-atelier" class="pro-bloc-slider pro-slider-event">
            <div class="container">
                <div>
                    <h2>D’autres ateliers</h2>
                    <div class="pro-wrapper">
                        <a href="${homeURL}ateliers-quartier" class="pro-btn">Tous les ateliers</a>
                    </div>
                </div>

                <div>
                    <div class="owl-carousel owl-opacify owl-theme owl-cards">
					
						<#list suggestions as suggestion >
							
							<#assign imageURL = suggestion.getImageURL() />
							
							<#assign imagePortraitURL = suggestion.getAuthorImageURL() />
							<div class="item pro-bloc-card-budget" data-linkall="a">
								<figure role="group">
									<img src="${imageURL}" width="155" height="200" alt="Image de l'atelier"/>
								</figure>
								<div class="pro-header-budget">
									<figure role="group">
										<img src="${imagePortraitURL}" width="40" height="40" alt="Portrait du ${suggestion.getAuthorLabel()}"/>
									</figure>
									<p>Atelier publié par :</p>
									<p>
										<strong>${suggestion.getAuthorLabel()}</strong>
									</p>
									<div class="pro-info-top-right">
										<#if suggestion.getStatusCategory()?has_content >
										<span class="pro-encart-theme encart-budget">
											${suggestion.getStatusCategory().getTitle(locale)}
										</span>
										</#if>
										<#list suggestion.getThematicCategories()  as assetCategory>
										<span class="pro-encart-theme encart-budget">
											${assetCategory?string}
										</span>
										</#list>
									</div>
								</div>
								<div class="pro-content-budget">
                                <span class="prefix-location">
                                    Strasbourg
                                </span>
									<a href="${homeURL}detail-atelier/-/entity/id/${suggestion.initiativeId}" title="lien de la page de détail">
										<h3>${suggestion.title}</h3>
									</a>
									<span class="pro-time">
										Publié le 
                                    <time datetime="${suggestion.getPublicationDateFr()}">${suggestion.getPublicationDateFr()}</time>
                                </span>
								</div>

								<div class="pro-footer-budget">
									<p>
										<strong>${suggestion.getNbHelps()} Citoyens-nes</strong> ont soutenus cette initiative
									</p>
								</div>
							</div>						
						</#list>
                    </div>
                </div>
            </div>
        </section>
	</#if>
	
</div>

<script>
    // Récupération des entités en JSON à afficher sur la map et ajout des données dynamiques manquantes
    var initiativeJSON = ${initiativeJSON};
    initiativeJSON.link = '${homeURL}detail-atelier/-/entity/id/${entry.initiativeId}';

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