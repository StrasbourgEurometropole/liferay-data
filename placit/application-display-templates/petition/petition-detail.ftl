<#-- DETAIL D'UNE PETITION -->

<#-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<#-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<#-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#-- Récupération de l'ID de l'utilisateur -->
<#assign userID = request.session.getAttribute("publik_internal_id")!"" />

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />
<#assign hasUserPactSign = request.session.getAttribute("has_pact_signed")!false />
<#assign isUserBanned = request.session.getAttribute("is_banish")!false />

<#-- L'utilisateur participe-t-il ? -->
<#assign hasUserSigned = entry.hasUserSigned(userID)?then("active", "") >

<#-- Recuperation des thématiques de la petition -->
<#if entry.getThematicCategories()??>
    <#assign petitionThematics = entry.getThematicCategories() />
</#if>

<#-- Recuperation des thématiques de la petition -->
<#if entry.getProjectCategory()??>
    <#assign petitionProject = entry.getProjectCategory() />
</#if>

<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />

<#-- Recuperation de l'id de l'instance du portlet pour separer le metier des portlets doublons -->
<#assign instanceId = themeDisplay.getPortletDisplay().getId() />

<#assign signataireNeeded = entry.getSignataireNeeded() />
<#assign isJudgeable = entry.isJudgeable() />

<#-- Initialisation des conteneurs de vignettes -->
<#assign petitionJSON = entry.toJSON(userID) />

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

<div id="content" class="pro-page-detail pro-page-detail-initiative">

    <div class="container">

        <div class="col-lg-11 col-lg-offset-1">

            <article>
                <header>
                    <div class="pro-header-participation pro-theme-information">
                        <h1>${entry.title}</h1>
                        <div class="pro-wrapper-meta">
                            <div class="pro-statut"><span>${entry.getFrontStatusFR()}</span></div>
                            <div class="pro-meta">
                                <#-- Liste des quartiers de la petition -->
                                <span>${entry.getDistrictLabel(locale)}</span>
                                <#-- Liste des thématiques de la petition -->
                                <#if petitionThematics?? >
                                    <#list petitionThematics as petitionThematic >
                                        <span>${petitionThematic.getTitle(locale)}</span>
                                    </#list>
                                </#if>
                                <#if petitionProject?? >
                                    <span>${petitionProject.getTitle(locale)}</span>
                                </#if>
                            </div>
                        </div>
                        <div class="pro-header-auteur">
                            <figure><#-- Si une image de la petition existe -->
                                <img src="${entry.getAuthorImageURL()}" width="40" height="40" alt="Arrière plan page standard"/>
                            </figure>
                            <p>pétition publiée le ${entry.getPublicationDate()?date?string['dd/MM/yyyy']} par :</p>
                            <p><strong>${entry.getAuthorLabel()}</strong></p>
                        </div>
                    </div>

                    <div id="breadcrumb">
                        <span>
                            <span>
                                <a href="${homeURL}">Accueil</a>
                                <a href="${homeURL}petitions">Listing des pétitions</a>
                                <span class="breadcrumb_last">${entry.title}</span>
                            </span>
                        </span>
                    </div>
                </header>

                <div class="row pro-container-detail-event">
                    <div class="col-sm-8">
                        <#-- Test du choix du media : "true"/image, "false"/video -->
                        <#if entry.getMediaChoice() == false && entry.getVideoUrl() != "" >
                            <div class="pro-bloc-texte pro-main-img">
                                <div class="pro-bloc-video bloc-standard">
                                    <div class="mask-video">
                                        <figure class="o80" role="group">
                                            <#-- Si une image existe malgrès le choix d'une vidéo pour l'affichage,
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
                        <#else>
                            <div class="pro-main-img">
                                <figure>
                                    <img src='${entry.getImageURL()}' alt="Image agenda" width="880" height="593" class="fit-cover"/>
                                    <#if !entry.getImageCopyright(locale)??>
                                        <figcaption>${entry.getImageCopyright(locale)}</figcaption>
                                    </#if>
                                </figure>
                        </#if>
                            <#if entry.isSupported >
                                <div class="pro-bookmark">
                                    <div>
                                        <span class="icon-ico-cathedrale"></span>
                                        <p>Pétition soutenue par${entry.supportedBy}</p>
                                    </div>
                                </div>
                            </#if>
                        </div>

                        <div class="row pro-bloc pro-bloc-texte">
                            <#if entry.summary?has_content>
                                <h4>Résumé</h4>
                                <p>${entry.summary}</p>
                            </#if>
                        </div>

                        <div class="row pro-bloc pro-bloc-texte">${entry.description}</div>
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
                        <div class="pro-contact-petition">
                            <span class="title">Pétition adressée à :</span>
                            <div>
                                <p><strong>Maire de Strasbourg</strong></p>

                                <@liferay_portlet["runtime"]
                                portletProviderAction=portletProviderAction.VIEW
                                portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
								instanceId="${entry.getPetitionId()}"/>

                            </div>
                        </div>
                    </div>

                    <#-- Blocs lateraux -->
                    <aside class="col-sm-4">

                        <#-- Bloc : avis -->
                        <div class="pro-push-avis">
                            <#if isJudgeable && isUserloggedIn && hasUserPactSign && !isUserBanned>
                                <a href="#pro-approuv" class="pro-like"
                                    data-typeid="17"
                                    data-isdislike="false"
                                    data-title="${entry.getTitle()}"
                                    data-entityid="${entry.petitionId}"
                                    data-entitygroupid="${entry.groupId}"
                                    title="Cliquez pour approuver">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a href="#pro-not-approuv" class="pro-dislike"
                                    data-typeid="17"
                                    data-isdislike="true"
                                    data-title="${entry.getTitle()}"
                                    data-entityid="${entry.petitionId}"
                                    data-entitygroupid="${entry.groupId}"
                                    title="Cliquez pour désapprouver">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            <#elseif !hasUserPactSign && !isUserBanned>
                                <a href="#" class="pro-like" name="#Pact-sign">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a href="#" class="pro-dislike" name="#Pact-sign">
                                    <span class="icon-ico-like"></span><strong>${entry.nbDislikes}</strong> <span>Désapprouver</span>
                                </a>
                            <#elseif isUserBanned>
                                <a href="#" class="pro-like" name="#IsBanned">
                                    <span class="icon-ico-like"></span><strong>${entry.nbLikes}</strong> <span>Approuver</span>
                                </a>
                                <a href="#" class="pro-dislike" name="#IsBanned">
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

                        <#-- Bloc : map -->
                        <div class="bloc-iframe leaflet-map" id="mapid" ></div>

                        <#-- Bloc : compteur -->
                        <div class="pro-compteur">
                            <span class="pro-compt pro-compt-six">${entry.getNombreSignatureBoard()}</span>
                            <p>Citoyens(nes) ont signé</p>
                            <div class="pro-progress-bar">
                                <div class="pro-progress-container">
                                    <div style="width:${entry.getPourcentageSignature()}%"></div>
                                </div>
                                <#if signataireNeeded!=0>
                                    <p class="pro-txt-progress">Il manque ${signataireNeeded} soutien(s) — <span>il reste ${entry.getTodayExpirationDifferenceDays()} jour(s)</span></p>
                                </#if>
                            </div>
                            <div class="pro-wrapper-links-petition">
                                <#if isJudgeable>
                                    <#if hasUserSigned?has_content>
                                        <a id="signButton" href="#popin" class="pro-btn-yellow ${hasUserSigned}">
                                            Petition signée
                                        </a>
                                    <#elseif isUserloggedIn && hasUserPactSign && !isUserBanned >
                                        <a id="signButton" href="#popin" class="pro-btn-yellow ${hasUserSigned}" title="Ouverture d'une pop-in pour signer la pétition" data-toggle="modal" data-target="#modalSigner">
                                            Signer la pétition
                                        </a>
                                    <#elseif !hasUserPactSign && !isUserBanned>
                                        <a id="signButton" href="#popin" class="pro-btn-yellow" name="#Pact-sign">
                                            Signer la pétition
                                        </a>
                                    <#elseif isUserBanned>
                                        <a id="signButton" href="#popin" class="pro-btn-yellow" name="#IsBanned">
                                            Signer la pétition
                                        </a>
                                    </#if>
                                <#else>
                                    <a id="signButton" href="#popin" class="pro-btn-yellow  ${hasUserSigned}" title="La pétition est terminée" data-toggle="modal">
                                        <#if hasUserSigned?has_content>
                                            Petition signée et terminée
                                        <#else>
                                            Vous ne pouvez plus signer
                                        </#if>
                                    </a>
                                </#if>
                                <a href="#pro-link-commentaire" class="pro-btn-yellow" title="Scroll jusqu'à la zone de commentaire">Réagir</a>
                            </div>
                        </div>

                    </aside>
                </div>
            </article>
        </div>
    </div>

    <#-- Recuperation des suggéstions de la pétition -->
    <#assign suggestions = entry.getSuggestions(request, 10) />

    <#-- Liste des suggéstions -->
    <#if suggestions?size gt 0 >
        <section id="pro-link-evenement" class="pro-bloc-slider pro-slider-event">
            <div class="container">
                <div class="col-lg-10 col-lg-offset-1">
                    <h2>D’autres pétitions</h2>
                    <div class="pro-wrapper">
                        <#if isUserloggedIn && hasUserPactSign && !isUserBanned>
                            <a id="buttonDeposer" href="#deposerPetition" class="pro-btn-yellow" data-toggle="modal" data-target="#modalPetition">Déposer une pétition</a>
                        <#elseif !hasUserPactSign>
                            <a class="pro-btn-yellow" name="#Pact-sign">Déposer une pétition</a>
                        <#elseif isUserBanned>
                            <a class="pro-btn-yellow" name="#IsBanned">Déposer une pétition</a>
                        </#if>
                        <a href="${homeURL}petitions" class="pro-btn">Toutes les pétitions</a>
                    </div>
                </div>
                <div class="col-lg-10 col-lg-offset-1">
                    <div class="owl-carousel owl-opacify owl-theme owl-cards">

                        <#list suggestions as suggestion >

                            <#-- Recuperation du pourcentage de signataires -->
                            <#assign pourcentage = suggestion.getPourcentageSignature()/>

                            <div class="item pro-bloc-card-petition" data-linkall="a">
                                <div class="pro-header-petition">
                                    <figure role="group">
                                        <img src="${suggestion.getAuthorImageURL()}" width="40" height="40" alt="Image petition"/>
                                    </figure>
                                    <p>Pétition publiée par :</p>
                                    <p><strong>${suggestion.getUserName()}</strong></p>
                                </div>
                                <div class="pro-content-petition">
                                    <a href="${homeURL}detail-petition/-/entity/id/${suggestion.petitionId}" title="lien de la page">
                                        <h3>${suggestion.title}</h3>
                                    </a>
                                    <p>Pétition adressée à <u>la ville de Strasbourg</u></p>
                                    <span class="pro-time">Publiée le
                                        <time datetime="${suggestion.publicationDate?date?string['dd/MM/yyyy']}">${suggestion.publicationDate?date?string['dd/MM/yyyy']}</time> / <span class="pro-duree">${suggestion.getProDureeFR()}</span>
                                    </span>
                                </div>
                                <div class="pro-footer-petition">
                                    <div class="pro-progress-bar">
                                        <div class="pro-progress-container">
                                            <div style="width:${pourcentage}%"></div>
                                        </div>
                                        <p class="pro-txt-progress"><strong>${suggestion.getNombreSignature()}</strong> Signataire(s) sur ${suggestion.getQuotaSignature()} nécessaires</p>
                                    </div>
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
    var petitionJSON = ${petitionJSON};
    petitionJSON.link = '${homeURL}detail-petition/-/entity/id/${entry.petitionId}';

    $(document).ready(function() {
        // Gestion de la carte interactive
        // Notes : voir dans le theme placit "override/custom.js"

        //Création de la carte au centre de strasbourg
        leafletMap = getLeafletMap()

        // Définition du marqueur
        var petitionMarkerIcon = getMarkerIcon('petition');

        // Ajout des marqueurs sur la map
        var petitionMarkers = [];
        
        // Création du cluster permettant le regroupement de points et le centrage
        var markersCluster = L.markerClusterGroup();

        var marker;

        for(var i= 0; i < petitionJSON.placitPlaces.length; i++) {
            marker = getPetitionMarker(
                petitionJSON,
                [petitionJSON.placitPlaces[i].mercatorY, petitionJSON.placitPlaces[i].mercatorX]
            );

            // Ajout du point dans le Cluster de marqueurs
            markersCluster.addLayer(marker);
            // Ajout du marker dans le tempon
            petitionMarkers.push(marker);
        }
            
        leafletMap.addLayer(markersCluster);
        
        // Adapter le zoom si des marqueurs existent
        if (markersCluster.getBounds().isValid()) {
            leafletMap.fitBounds(markersCluster.getBounds());
        }
        
    });
</script>