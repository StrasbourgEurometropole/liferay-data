<!-- SLIDER DES INITATIVES AVEC MARGE -->

<#if entries?size != 0 >

    <!-- Recuperation de la localisation de l'utilisateur -->
    <#setting locale = locale />

    <!-- Recuperation du créateur de l'initiative -->
    <#assign UserLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.UserLocalService")/>
    <#assign user = UserLocalService.getUser(entry.userId) />

    <!-- Recuperation de l'URL de "base" du site -->
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
        <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
        <#assign homeURL = "/" />
    </#if>

    <section class="pro-bloc-slider pro-slider-event">

        <div class="container">
		
            <div class="col-lg-10 col-lg-offset-1">
                <h2>Les ateliers de quartier (${entries?size})</h2>
                <a href="${themeDisplay.getPortalURL()}${homeURL}ateliers-quartier" class="pro-btn" title="Lien vers la page du Listing des ateliers">Voir tous les ateliers</a>
            </div>
            <div class="col-lg-10 col-lg-offset-1">
                <div class="owl-carousel owl-opacify owl-theme owl-cards">

                    <!-- Parcours des entites de l'asset publisher -->
                    <#list entries as curEntry>

                        <!-- Recuperation de l'entite -->
                        <#assign entry = curEntry.getAssetRenderer().getInitiative() />

                        <div class="item pro-bloc-card-initiative" data-linkall="a">
                            <div class="wrapper-card-initiative">
								<#if entry.getImageURL()?has_content>
									<figure role="group" class="fit-cover">
										<img src="${entry.getImageURL()}?imagePreview=1" loading="lazy" width="155" height="200" alt="Image atelier"/>
									</figure>
								</#if>
                                <div>
                                    <div class="pro-header-initiative">
                                        <figure role="group">
											<img src="${entry.getAuthorImageURL()}?imagePreview=1" loading="lazy" width="40" height="40" alt="Image de profil">
										</figure>
                                        <p>Atelier publié par :</p>
                                        <p><strong>${entry.getAuthorLabel()?html}</strong></p>
                                    </div>
                                    <div class="pro-content-initiative">
                                        <a href="${homeURL}detail-atelier/-/entity/id/${entry.initiativeId}" title="lien de la page"><h3>${entry.title?html}</h3></a>
                                        <span class="pro-time">Publié le <time datetime="${entry.getPublicationDateFr()}">${entry.getPublicationDateFr()}</time></span>
                                    </div>
                                </div>
                            </div>
                            <div class="pro-footer-initiative">
                                <div class="pro-avis">
                                    <span>${entry.getNbHelps()}</span>
                                </div>
                                <p>Citoyens-nes ont proposé leur aide</p>
                            </div>
                        </div>

                    </#list>
                </div>
            </div>
        </div>

    </section>
    
<#else>
    <div style="height:25px"><div>
</#if>