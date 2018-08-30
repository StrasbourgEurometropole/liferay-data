<!-- DETAIL D'UNE PETITION -->

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

        <!-- Recuperation du status de la petition (terminee, bientot, etc.) -->
<#assign petitionStatus = entry.getPetitionStatus() />

        <!-- Recuperation des thématiques de la petition -->
<#if entry.getThematicCategories()??>
<#assign petitionThematics = entry.getThematicCategories() />
        </#if>

        <!-- Recuperation des lieux lies a la petition -->
<#assign petitionPlaces = entry.getPlacitPlaces() />

        <!-- Recuperation de l'id de l'instance du portlet pour separer le metier des portlets doublons -->
<#assign instanceId = themeDisplay.getPortletDisplay().getId() />

        <!-- Initialisation des conteneurs de coordonnees GPS -->
<#assign petitionPlaceMercators = [] />
<#assign eventPlaceMercators = [] />

<div id="content" class="pro-page-detail pro-page-detail-initiative">

    <div class="container">

        <div class="col-lg-11 col-lg-offset-1">

            <article>
                <header>
                    <div class="pro-header-participation pro-theme-information">
                        <h1>${entry.title}</h1>
                        <div class="pro-wrapper-meta">
                            <div class="pro-statut"><span>Nouveau</span></div>
                            <div class="pro-meta">
                                <!-- Liste des quartiers de la participation -->
                                <span>${entry.getDistrictLabel(locale)}</span>
                                <!-- Liste des thématiques de la participation -->
                                <#if participationThematics?? >
                                    <#list participationThematics as participationThematic >
                                        <span>${participationThematic.getTitle(locale)}</span>
                                    </#list>
                                </#if>
                                <span>${petitionStatus}</span>
                                <span>Nom du projet</span>
                            </div>
                        </div>
                        <div class="pro-header-auteur">
                            <figure><!-- Si une image de la participation existe -->
                                <#if entry.getImageURL()?has_content>
                                    <img src="${entry.getImageURL()}" width="40" height="40" alt="Arrière plan page standard"/>
                                </#if>
                            </figure>
                            <p>pétition publiée le ${entry.getPublicationDate()?date?string['dd/MM/yyyy']} par :</p>
                            <p><strong>${entry.userName}</strong></p>
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
                                <#if entry.getImageCopyright(locale)??>
                                <#else>
                                    <figcaption>${entry.getImageCopyright(locale)}</figcaption>
                                </#if>
                            </figure>
                            <div class="pro-bookmark">
                                <div>
                                    <span class="icon-ico-cathedrale"></span>
                                    <p>Pétition soutenue par le Conseil de Quartier Meinau</p>
                                </div>
                            </div>
                        </div>
                        </#if>

                        <div class="row pro-bloc pro-bloc-texte">${entry.description}</div>

                        <div class="pro-contact-petition">
                            <span class="title">Pétition adressée à :</span>
                            <div>
                                <p><strong>Maire de Strasbourg</strong></p>
                                <@liferay_portlet["runtime"]
                                portletProviderAction=portletProviderAction.VIEW
                                portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                                instanceId="test"/>
                            </div>
                        </div>
                    </div>


                    <aside class="col-sm-4">
                        <div class="pro-push-avis">
                            <a href="#pro-approuv" class="pro-like" title="Cliquez pour approuver">
                                <span class="icon-ico-like"></span><strong>${entry.getNombreSignature()}</strong> <span>Approuver</span>
                            </a>
                            <a href="#pro-not-approuv" class="pro-dislike" title="Cliquez pour désapprouver">
                                <span class="icon-ico-like"></span><strong>${entry.getNombreSignature()}</strong> <span>Désapprouver</span>
                            </a>
                        </div>
                        <div class="bloc-iframe maps" data-theme="default" data-lat="48.5692059" data-lng="7.6920547" data-marker="true" data-markericon="event"
                             data-zoom="12" data-filter-options="filterMapDetail"></div>
                        <div class="pro-compteur">
                            <span class="pro-compt pro-compt-six">${entry.getNombreSignature()}</span>
                            <p>Citoyens(nes) ont signé</p>
                            <div class="pro-progress-bar">
                                <div class="pro-progress-container">
                                    <div style="width:${entry.getPourcentageSignature()}%"></div>
                                </div>
                                <p class="pro-txt-progress">Il manque ${entry.getSignataireNeeded()} soutien(s) — <span>il reste ${entry.getTodayExpirationDifferenceDays()} jour(s)</span></p>
                            </div>
                            <div class="pro-wrapper-links-petition">
                                <a href="#popin" class="pro-btn-yellow" title="Ouverture d'une pop-in pour signer la pétition" data-toggle="modal" data-target="#modalPetition">Signer la pétition</a>
                                <a href="#pro-link-commentaire" class="pro-btn-yellow" title="Scroll jusqu'à la zone de commentaire">Réagir</a>
                            </div>
                        </div>
                    </aside>
                </div>
            </article>
        </div>
    </div>
    <!-- Recuperation des thématiques de la vidéo -->
    <#assign suggestions = entry.getSuggestions(locale, 10) />
            <!-- Liste des thématiques de la vidéo -->
    <#if suggestions?size gt 0 >
        <section id="pro-link-evenement" class="pro-bloc-slider pro-slider-event">
            <div class="container">
                <div class="col-lg-10 col-lg-offset-1">
                    <h2>D’autres pétitions</h2>
                    <div class="pro-wrapper">
                        <a href="#deposerPetition" class="pro-btn-yellow" data-toggle="modal" data-target="#modalPetition">Déposer une pétition</a>
                        <a href="listing-petition.html" class="pro-btn">Toutes les pétitions</a>
                    </div>
                </div>
                <div class="col-lg-10 col-lg-offset-1">
                    <div class="owl-carousel owl-opacify owl-theme owl-cards">
                        <#list suggestions as suggestion >
                            <!-- Recuperation ddu pourcentage de signataires -->
                            <#assign pourcentage = suggestion.getPourcentageSignature()/>
                            <!-- Recuperation du status de la petition (terminee, bientot, etc.) -->
                            <#switch suggestion.getPetitionStatus()>
                                <#case "new">
                                    <#assign petitionStatus = "Nouvelle" />
                                    <#assign proDuree = "Fin dans " + suggestion.getTodayExpirationDifferenceDays() + " jour(s)" />
                                <#break>
                                <#case "failed">
                                    <#assign petitionStatus = "Non aboutie" />
                                    <#assign proDuree = "Terminée"  />
                                <#break>
                                <#case "in_progress">
                                    <#assign petitionStatus = "En cours" />
                                    <#assign proDuree = "Fin dans " + suggestion.getTodayExpirationDifferenceDays() + " jour(s)" />
                                <#break>
                                <#case "soon_finished">
                                    <#assign petitionStatus = "Bientôt terminée" />
                                    <#assign proDuree = "Fin dans " + suggestion.getTodayExpirationDifferenceDays() + " jour(s)" />
                                <#break>
                                <#case "completed">
                                    <#assign petitionStatus = "Aboutie" />
                                    <#assign proDuree = "Terminée"  />
                                <#break>
                            </#switch>
                            <div class="item pro-bloc-card-petition" data-linkall="a">
                                <div class="pro-header-petition">
                                    <figure role="group">
                                        <#if suggestion.getImageURL()?has_content>
                                        <img src="${suggestion.getImageURL()}" width="40" height="40" alt="Image petition"/>
                                        </#if>
                                    </figure>
                                    <p>Pétition publiée par :</p>
                                    <p><strong>${suggestion.getUserName()}</strong></p>
                                </div>
                                <div class="pro-content-petition">
                                    <a href="${homeURL}detail-petition/-/entity/id/${suggestion.petitionId}" title="lien de la page">
                                        <h3>${suggestion.title}</h3></a>
                                    <p>Pétition adressée à <u>la ville de Strasbourg</u></p>
                                    <span class="pro-time">Publiée le
                                        <time datetime="${suggestion.publicationDate?date?string['dd/MM/yyyy']}">${suggestion.publicationDate?date?string['dd/MM/yyyy']}</time> / <span class="pro-duree">${proDuree}</span></span>
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
    <!-- HTML pour la modal d'une pétition -->
    <div class="pro-modal fade" id="modalPetition" tabindex="-1" role="dialog" aria-labelledby="modalPetition">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <p>Modal pour le dépôt d'une pétition</p>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>