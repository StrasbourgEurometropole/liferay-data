<!-- DETAIL D'UNE PARTICIPATION -->

<#-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<#-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<#-- Recuperation du créateur de la participation -->
<#assign UserLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.UserLocalService")/>
<#assign user = UserLocalService.getUser(entry.getStatusByUserId()) />

<#-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#-- Récupération de l'ID de l'utilisateur -->
<#assign userID = request.session.getAttribute("publik_internal_id")!"" />

<#-- Recuperation du status de la participation (terminee, bientot, etc.) -->
<#assign participationStatus = entry.getParticipationStatus() />

<#-- Recuperation du type de la participation (information, concertation, etc.) -->
<#assign participationType = entry.getTypeCategory().getTitle(locale) />

<#-- Recuperation de la couleur hexa correspondant au type de la participation -->
<#assign participationColor = entry.getTypeCategoryColor() />

<#-- Recuperation des thématiques de la participation -->
<#if entry.getThematicCategories()??>
    <#assign participationThematics = entry.getThematicCategories() />
</#if>

<#-- Recuperation des evenements lies a la participation -->
<#assign participationEvents = entry.getEvents() />

<#-- Recuperation des lieux lies a la participation -->
<#assign participationPlaces = entry.getPlacitPlaces() />

<#-- Recuperation de l'id de l'instance du portlet pour separer le metier des portlets doublons -->
<#assign instanceId = themeDisplay.getPortletDisplay().getId() />

<#-- Initialisation des conteneurs de vignettes -->
<#assign participationJSON = entry.toJSON(themeDisplay) />
<#assign eventsJSON = [] />

<#assign imageUrlOG = ""/>
<!-- vignette -->
<#if entry.imageURL?has_content>
    <#assign imageUrlOG=themeDisplay.getPortalURL() + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.title?html}",
"og:description":'${entry.descriptionChapeau?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrlOG}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="pro-page-detail pro-page-detail-participation">

	<div class="container">

        <div class="col-lg-11 col-lg-offset-1">

            <article>
                <header>
                    <div class="pro-header-participation">
                        <span class="pro-encart-theme" style="background : #${participationColor}">
                            ${participationType}
                        </span>
                        <h1>${entry.title}</h1>
                        <div class="pro-meta">

                            <!-- Liste des quartiers de la participation -->
                            <span>${entry.getDistrictLabel(locale)}</span>

                            <!-- Liste des thématiques de la participation -->
                            <#if participationThematics?? >
                                <#list participationThematics as participationThematic >
                                    <span>${participationThematic.getTitle(locale)}</span>
                                </#list>
                            </#if>

                        </div>											
                        <div class="pro-header-auteur">
                            <figure>
                                <img src="${user.getPortraitURL(themeDisplay)}" width="40" height="40" alt="Image de l'auteur"/>
                            </figure>
                            <p>Participation publiée le ${entry.getPublicationDate()?date?string['dd/MM/yyyy']} par :</p>
                            <p><strong>${entry.getStatusByUserName()}</strong></p>
                        </div>
                    </div>

                    <div id="breadcrumb">
                    <span>
                        <span>
                            <a href="${homeURL}">Accueil</a>
                            <a href="${homeURL}participations">Participations</a>
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
                                <li role="presentation">
                                	<a href="#description" class="active" aria-controls="description" role="tab" data-toggle="tab" title="Onglet de description">Description</a>
                                </li>
                                <li role="presentation">
                                	<a href="#lieux" aria-controls="lieux" role="tab" data-toggle="tab" title="Onglet de Lieux de consultation">Lieux de consultation</a>
                                </li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">

                                <!-- Description -->
                                <div role="tabpanel" class="tab-pane fade in active pro-bloc-texte" id="description">
                                    <p class="pro-txt-intro">${entry.descriptionChapeau}</p>
                                    <p>${entry.descriptionBody}</p>
                                </div>


                                <!-- Lieux de consultations -->
                                <div role="tabpanel" class="tab-pane fade pro-bloc-texte" id="lieux">
                                    <p><#if entry.consultationPlacesBody?has_content> ${entry.consultationPlacesBody} </#if></p>
                                    <div class="row pro-wrapper-quartiers">

                                    	<!-- Item lieu -->
                                        <#if participationPlaces?has_content>
                                            <#list participationPlaces as place >
                                                <div class="col-md-4 col-sm-6">
                                                    <a>
                                                        <figure class="fit-cover">
                                                            <#if place.getImageURL()?has_content><img src=" ${place.getImageURL()} " width="200" height="140" alt="Image du quartier"/></#if>
                                                        </figure>
                                                        <div>
                                                            <span class="pro-name">${place.getCompleteAddress(locale)}</span>
                                                            <h3>
                                                                ${place.getPlaceAlias(locale)}
                                                            </h3>
                                                            <!--
                                                            <span class="pro-link">En savoir plus</span>
                                                            -->
                                                        </div>
                                                    </a>
                                                </div>
                                            </#list>
                                        <#else>
                                            Aucun lieu associé pour le moment
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <#if entry.filesURLs?has_content>
                            <div class="pro-bloc-texte pro-bloc-telechargements">
                                <h3>Documents à télécharger</h3>
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

                    </div>

                    <!-- Fiche de l'entité -->
                    <aside class="col-sm-4">

                        <!-- Bloc : avis -->
                        <div class="pro-push-avis">
                            <#if entry.isJudgeable() && request.session.getAttribute("has_pact_signed")!false>
                                <a href="#pro-approuv" class="pro-like"
                                    data-typeid="15" 
                                    data-isdislike="false"
                                    data-title="${entry.getTitle()}" 
                                    data-entityid="${entry.participationId}"
                                    data-entitygroupid="${entry.groupId}"
                                    title="Cliquez pour approuver">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a href="#pro-not-approuv" class="pro-dislike"
                                    data-typeid="15" 
                                    data-isdislike="true"
                                    data-title="${entry.getTitle()}" 
                                    data-entityid="${entry.participationId}"
                                    data-entitygroupid="${entry.groupId}"
                                    title="Cliquez pour désapprouver">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            <#elseif !request.session.getAttribute("has_pact_signed")?? || (request.session.getAttribute("has_pact_signed")?? && !request.session.getAttribute("has_pact_signed"))>
                                <a class="pro-like" name="#Pact-sign">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a class="pro-dislike" name="#Pact-sign">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            <#else>
                                <a class="pro-like">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a class="pro-dislike">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            </#if>
                        </div>

                        <!-- Bloc : map -->
                        <div class="bloc-iframe leaflet-map" id="mapid" ></div>

                        <!-- Bloc : compteur commentaires -->
                        <div class="pro-compteur">
                            <span class="pro-compt">${entry.nbApprovedCommentsLabel}</span>
                            <p>Citoyens(nes) ont réagi</p>
                            <a href="#pro-link-commentaire" class="pro-btn-yellow" title="Scroll jusqu'à la zone de commentaire">Réagir</a>
                        </div>

                        <!-- Bloc : à venir -->
                        <div class="pro-event-comming">
                            <a href="#pro-link-evenement" target="Evenement à venir">
                                <strong><#if participationEvents?has_content>${participationEvents?size}</#if></strong> Évènement(s) à venir
                            </a>
                        </div>

                        <!-- Bloc : contact -->
                        <div class="pro-contact">
                            <h4>Contact</h4>
                            <p>
                                <#if entry.contactName?has_content>
                                    <strong> ${entry.contactName}</strong><br>
                                    <#if entry.contactLine1?has_content> ${entry.contactLine1} </#if>
                                    <#if entry.contactLine2?has_content> <br>${entry.contactLine2} </#if>
                                <#else>
                                    <strong>Aucun contact renseigné pour le moment </strong><br>
                                </#if>
                            </p>
                            <#if entry.getContactPhoneNumber() != "">
                            	<a href="tel:${entry.contactPhoneNumber}" title="Numéro de téléphone : ${entry.contactPhoneNumber}">${entry.contactPhoneNumber}</a>
                            </#if>
                        </div>

                    </aside>
                </div>
            </article>


        </div>
    </div>

</div>

<#if participationEvents?size gt 0 >
	<section id="pro-link-evenement" class="pro-bloc-slider pro-slider-event">
		<div class="container">

			<div class="col-lg-10 col-lg-offset-1">
				<h2>L’agenda</h2>
				<a href="${homeURL}agenda" class="pro-btn" title="Lien vers la page de tout l'agenda">Voir Tout l’agenda</a>
			</div>

			<div class="col-lg-10 col-lg-offset-1">
				<div class="owl-carousel owl-opacify owl-theme owl-cards">
				
					<!-- Parcours des entites de l'asset publisher -->
					<#if participationEvents?has_content>
						<#list participationEvents as event >

							<#assign eventsJSON = eventsJSON + [event.toJSON(userID)] />
							<#assign isUserPartActive = event.isUserParticipates(userID)?then("active", "") />
							
							<a href="${homeURL}detail-evenement/-/entity/id/${event.eventId}/${event.getNormalizedTitle(locale)}" title="lien de la page" class="item pro-bloc-card-event">
								<div>
									<div class="pro-header-event">
										<span class="pro-ico"><span class="icon-ico-debat"></span></span>
										<span class="pro-time"><#if event.firstStartDate?has_content>Le ${event.firstStartDate?string("dd MMMM yyyy")}</#if></span>
										<p>À : ${event.getPlaceAlias(locale)}</p>
										<h3 style="display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;
											overflow: hidden;text-overflow: ellipsis;height: 53px">
											${event.getTitle(locale)}
										</h3>
									</div>
									<div class="pro-footer-event">
										<#if event.isFinished() >
											<span class="pro-btn-action ${isUserPartActive}">
												Événement terminé
											</span>
										<#elseif request.session.getAttribute("has_pact_signed")!false >
											<span class="pro-btn-action ${isUserPartActive}"
												name="#Participe-${instanceId}"
												data-eventid="${event.eventId}"
												data-groupid="${event.groupId}">
												Je participe
											</span>
										<#else>
											<span class="pro-btn-action ${isUserPartActive}" 
												name="#Pact-sign">
												Je participe
											</span>
										</#if>
										<span class="pro-number"><strong>${event.getNbEventParticipations()}</strong> Participant(s)</span>
									</div>
								</div>
							</a>
						
							</#list>
					<#else>
						Aucun événement associé pour le moment
					</#if>

				</div>
			</div>

		</div>
	</section>
</#if>
<!-- Cree le style de couleur hexa a la volee pour l'application de la couleur !-->
<#if participationColor?has_content>
    <style style="display: none" >
        .pro-page-detail-participation article header .pro-header-participation:before {
            background:#${participationColor};
        }
    </style>
</#if>

<script>
    // Récupération des entités en JSON à afficher sur la map et ajout des données dynamiques manquantes
    var participationJSON = ${participationJSON};
    participationJSON.link = '${homeURL}detail-participation/-/entity/id/${entry.participationId}';

    var eventsJSON = [
        <#list eventsJSON as eventJSON>
            ${eventJSON},
        </#list>
    ];

    $(document).ready(function() {

        // Gestion de la carte interactive
        // Notes : voir dans le theme placit "override/custom.js"

        //Création de la carte au centre de strasbourg
        leafletMap = getLeafletMap()

        // Définition des marqueurs
        var participationMarkerIcon = getMarkerIcon('participation');
        var eventMarkerIcon = getMarkerIcon('event');

        // Ajout des marqueurs sur la map
        var participationMarkers = [];
        var eventMarkers = [];
        
        // Création du cluster permettant le regroupement de points et le centrage
        var markersCluster = L.markerClusterGroup();

        var marker;

        for(var i= 0; i < participationJSON.placitPlaces.length; i++) {
            marker = getParticipationMarker(
                participationJSON,
                [participationJSON.placitPlaces[i].mercatorY, participationJSON.placitPlaces[i].mercatorX]
            );

            // Ajout du point dans le Cluster de marqueurs
            markersCluster.addLayer(marker);
            // Ajout du marker dans le tempon
            participationMarkers.push(marker);
        }

        for(var i= 0; i < eventsJSON.length; i++) {
            // notes : la participation à l'événement à été ajoutée dans le tableau lors du parcours
            // des évenements, d'où le [0] pour avoir le JSON et le [1] pour la participation à l'évenements
            var eventJSON = eventsJSON[i];
            // Ajout du lien vers le détail (effectué ici pour éviter le double parcours)
            eventJSON.link = '${homeURL}detail-evenement/-/entity/id/' +  eventJSON.id + '/' + eventJSON.normalizedTitle;

            marker = getEventMarker(eventJSON);

            markersCluster.addLayer(marker);
            eventMarkers.push(marker);
        }
        
        leafletMap.addLayer(markersCluster);
        
        // Adapter le zoom si des marqueurs existent
        if (markersCluster.getBounds().isValid()) {
            leafletMap.fitBounds(markersCluster.getBounds());
        }

    });

</script>