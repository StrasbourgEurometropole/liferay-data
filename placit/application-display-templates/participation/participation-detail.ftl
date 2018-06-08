<!-- DETAIL D'UNE PARTICIPATION -->

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

<!-- Recuperation du status de la participation (terminee, bientot, etc.) -->
<#assign participationStatus = entry.getParticipationStatus() />

<!-- Recuperation du type de la participation (information, concertation, etc.) -->
<#assign participationType = entry.getTypeCategory().getTitle(locale) />

<#switch participationType>
    <#case "Information">
        <#assign cssParticipationType = "pro-theme-information" />
        <#break>
    <#case "Consultation">
        <#assign cssParticipationType = "pro-theme-consultation" />
        <#break>
    <#case "Co-construction">
        <#assign cssParticipationType = "pro-theme-co-construire" />
        <#break>
    <#case "Concertation">
        <#assign cssParticipationType = "pro-theme-concertation" />
        <#break>
    <#case "Enquête publique">
        <#assign cssParticipationType = "pro-theme-brun" />
        <#break>
    <#default>
        <#assign cssParticipationType = "" />
        <#break>
</#switch>

<!-- Recuperation des quartiers de la participation -->
<#if entry.getDistrictCategories()??>
    <#assign participationDistricts = entry.getDistrictCategories() />
</#if>

<!-- Recuperation des thématiques de la participation -->
<#if entry.getThematicCategories()??>
    <#assign participationThematics = entry.getThematicCategories() />
</#if>

<!-- Recuperation des evenements lies a la participation -->
<#assign participationEvents = entry.getEvents() />

<!-- Recuperation des evenements lies a la participation -->
<#assign participationPlaces = entry.getPlaces() />

<div class="pro-page-detail pro-page-detail-participation">

	<div class="container">

        <div class="col-lg-11 col-lg-offset-1">

            <article>
                <header>
                    <div class="pro-header-participation ${cssParticipationType}">
                        <h1>${entry.title}</h1>
                        <div class="pro-meta">

                            <!-- Liste des quartiers de la participation -->
                        	<#if participationDistricts?? >
                            	<#list participationDistricts as participationDistrict >
                            		<span>${participationDistrict.getTitle(locale)}</span>
                            	</#list>
                            </#if>

                            <!-- Liste des thématiques de la participation -->
                            <#if participationThematics?? >
                                <#list participationThematics as participationThematic >
                                    <span>${participationThematic.getTitle(locale)}</span>
                                </#list>
                            </#if>

                        </div>
                        <div class="pro-header-auteur">
                            <figure>
                            	<!-- Si une image de la participation existe -->
                                <#if entry.getImageURL()?has_content>
                                	<img src="${entry.getImageURL()}" width="40" height="40" alt="Arrière plan page standard"/>
                                </#if>
                            </figure>
                            <p>Participation publiée le ${entry.getPublicationDate()?date?string['dd/MM/yyyy']} par :</p>
                            <p><strong>${entry.author}</strong></p>
                        </div>
                    </div>

                    <div id="breadcrumb">
                    <span>
                        <span>
                            <a href="${homeURL}">Accueil</a>
                            <a href="${homeURL}participation">Participations</a>
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
                                <li role="presentation" class="active">
                                	<a href="#description" aria-controls="description" role="tab" data-toggle="tab" title="Onglet de description">Description</a>
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
                                                            <img src="<#if place.getImageURL()?has_content> ${place.getImageURL()} </#if>" width="200" height="140" alt="Image du quartier"/>
                                                        </figure>
                                                        <div>
                                                            <span class="pro-name">${place.getDistrict(locale)}</span>
                                                            <h3>
                                                                ${place.getAlias(locale)}
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

                        <div class="pro-bloc-texte pro-bloc-telechargements">
                            <h3>Documents à télécharger</h3>
                            <div class="row">

                            	<#if entry.filesURLs?has_content>
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
						        <#else>
						        	Aucun document associé pour le moment
						        </#if>

                            </div>
                        </div>

                    </div>


                    <aside class="col-sm-4">
                        <div class="pro-event-comming">
                            <a href="#pro-link-evenement" target="Evenement à venir">
                                <strong><#if participationEvents?has_content>${participationEvents?size}</#if></strong> Évènement(s) à venir
                            </a>
                        </div>
                        <div class="pro-contact">
                            <h4>Contact</h4>
                            <p>
                                <strong><#if entry.getContactName() != ""> ${entry.contactName} <#else> Aucun contact renseigné pour le moment </#if></strong><br>
                                <#if entry.getContactLine1() != ""> ${entry.contactLine1} </#if>
                                <#if entry.getContactLine2() != ""> <br>${entry.contactLine2} </#if>
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

<section id="pro-link-evenement" class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <div class="col-lg-10 col-lg-offset-1">
            <h2>L'agenda</h2>
            <a href="${homeURL}agenda" class="pro-btn">Voir Tout l’agenda</a>
        </div>

        <div class="col-lg-10 col-lg-offset-1">
            <div class="owl-carousel owl-opacify owl-theme owl-cards">


                <#if participationEvents?has_content>
                    <#list participationEvents as event >

                        <!-- Separation du titre de l'evenement  en deux parties -->
                        <#assign eventTitle = event.getTitle(locale) >
                        <#if eventTitle?length gt 15 && eventTitle?index_of(" ", 15) != -1 >
                            <#assign breakIndex = eventTitle?index_of(" ", 15) >
                            <#assign eventTitleFirstPart = eventTitle?substring(0, breakIndex) />
                            <#assign eventTitleSecondPart = eventTitle?substring(breakIndex, eventTitle?length) />
                        <#else>
                            <#assign eventTitleFirstPart = eventTitle />
                        </#if>

                        <a href="${homeURL}detail-evenement/-/entity/id/${event.eventId}" title="lien de la page" class="item pro-bloc-card-event">
                            <div>
                                <div class="pro-header-event">
                                    <span class="pro-ico"><span class="icon-ico-debat"></span></span>
                                    <span class="pro-time">
                                        <#if event.firstStartDate?has_content>
                                            Le <time datetime="2018-01-10">${event.firstStartDate?string("dd MMMM yyyy")}</time>
                                        </#if>
                                    </span>
                                    <p>À : ${event.getPlaceAlias(locale)}<br></p>
                                    <h3>
                                        ${eventTitleFirstPart}
                                        <br>
                                        <#if eventTitleSecondPart?has_content>${eventTitleSecondPart}</#if>
                                    </h3>
                                </div>
                                <div class="pro-footer-event">
                                    <!--
                                    <span class="pro-btn-action active">Je participe</span>
                                    <span class="pro-number"><strong>4537</strong> Participant(s)</span>
                                    -->
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