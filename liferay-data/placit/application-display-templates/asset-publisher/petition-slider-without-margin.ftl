<!-- SLIDER DE PETITIONS SANS MARGE -->

<#if entries?size != 0 >

    <!-- Recuperation de la localisation de l'utilisateur -->
    <#setting locale = locale />

    <!-- Recuperation du créateur de la pétition -->
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
            <div>
                <h2>Les pétitions (${entries?size})</h2>
                <a href="${themeDisplay.getPortalURL()}${homeURL}petitions" class="pro-btn" title="Lien vers la page du Listing des pétitions">Tout voir</a>
            </div>
            <div>
                <div class="owl-carousel owl-opacify owl-theme owl-cards">

                    <!-- Parcours des entites de l'asset publisher -->
                    <#list entries as curEntry>

                        <!-- Recuperation de l'entite -->
                        <#assign entry = curEntry.getAssetRenderer().getPetition() />

                        <div class="item pro-bloc-card-petition" data-linkall="a">
                            <div class="pro-header-petition">
                                <figure role="group">
                                    <img src="${entry.getAuthorImageURL()}?imagePreview=1" loading="lazy" width="40" height="40" alt="Image de profil">
                                </figure>
                                <p>Pétition publiée par :</p>
                                <p><strong>${entry.getAuthorLabel()?html}</strong></p>
                            </div>
                            <div class="pro-content-petition">
                                <a href="${homeURL}detail-petition/-/entity/id/${entry.petitionId}" title="lien de la page"><h3>${entry.title?html}</h3></a>
                                <p>Pétition adressée à <u>la ville de Strasbourg</u></p>
                                <span class="pro-time">Publiée le <time datetime="${entry.getPublicationDateFr()}">${entry.getPublicationDateFr()}</time> / <span class="pro-duree">${entry.getProDureeFR()}</span></span>
                            </div>
                            <div class="pro-footer-petition">
                                <div class="pro-progress-bar">
                                    <div class="pro-progress-container">
                                        <div style="width:${entry.getPourcentageSignature()}%"></div>
                                    </div>
                                    <p class="pro-txt-progress"><strong>${entry.getNombreSignature()}</strong> Signataire(s) sur ${entry.getQuotaSignature()} nécessaires</p>
                                </div>
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