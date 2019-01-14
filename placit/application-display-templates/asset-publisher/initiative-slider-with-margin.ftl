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
                <h2>Les initiatives (${entries?size})</h2>
                <a href="${themeDisplay.getPortalURL()}${homeURL}initiatives" class="pro-btn" title="Lien vers la page du Listing des initiatives">Voir toutes les initiatives</a>
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
										<img src="${entry.getImageURL()}" width="155" height="200" alt="Image initiative"/>
									</figure>
								</#if>
                                <div>
                                    <div class="pro-header-initiative">
                                        <figure role="group">
											<img src="${entry.getAuthorImageURL()}" width="40" height="40" alt="Image de profil">
										</figure>
                                        <p>Initiative publiée par :</p>
                                        <p><strong>${entry.getAuthorLabel()}</strong></p>
                                    </div>
                                    <div class="pro-content-initiative">
                                        <a href="${homeURL}detail-initiative/-/entity/id/${entry.initiativeId}" title="lien de la page"><h3>${entry.title}</h3></a>
                                        <span class="pro-time">Publiée le <time datetime="${entry.getPublicationDateFr()}">${entry.getPublicationDateFr()}</time></span>
                                    </div>
                                </div>
                            </div>
                            <div class="pro-footer-initiative">
                                <div class="pro-avis">
                                    <span>${entry.getNbHelps()}</span>
                                </div>
                                <p>Citoyens soutiennent cette initiative</p>
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