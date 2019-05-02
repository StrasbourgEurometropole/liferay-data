<!-- SLIDER EVENT -->

<#-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<#-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="ops-bloc-slider-cards">

    <div class="ops-content-wrapper ops-content-wrapper-large">
        <h2>Prochains concerts</h2>

        <div class="slick-carousel slick-cards-slider">
            <#-- Parcours des entites de l'asset publisher -->
            <#list entries as curEntry>

                <#-- Recuperation de l'entite -->
                <#assign entry = curEntry.getAssetRenderer().getEvent() />
                <div class="ops-item">
                    <a href="${homeURL}detail-evenement/-/entity/id/${entry.eventId}" class="ops-card ops-card-concert">
                        <div>
                            <time><span>${entry.getEventScheduleDisplay(locale, false, true)}</span></time>
                            <div class="ops-next-date"></div>
                            <h3>${entry.getTitle(locale)}</h3>
                            <div class="ops-img">
                                <figure class="fit-cover">
                                    <#if entry.imageURL?has_content>
                                        <img src="${entry.imageURL}" width="330" height="170" alt="${entry.getTitle(locale)}" />
                                    </#if>
                                </figure>
                                <span class="ops-typologie">${entry.getThemeLabel(locale)}</span>
                            </div>
                            <div class="ops-content">
                                <span class="ops-songs"><strong>${entry.getComposer()}</strong></span>
                                <span class="ops-names">${entry.getDistribution()}</span>
                            </div>
                            <span class="ops-bottom-card">Acheter mon billet</span>
                        </div>
                    </a>
                </div>
            </#list>
        </div>
        <div class="ops-link-bottom">
            <a href="${homeURL}agenda" class="ops-btn">Voir l'agenda complet</a>
        </div>
    </div>
</div>