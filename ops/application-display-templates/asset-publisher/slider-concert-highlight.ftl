<!-- SLIDER CONCERT A LA UNE -->

<#-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<#-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="ops-content-wrapper ops-content-wrapper-large slick-carousel slick-cards-slider">

    <#-- Parcours des entites de l'asset publisher -->
    <#list entries as curEntry>

        <#-- Recuperation de l'entite -->
        <#assign entry = curEntry.getAssetRenderer().getEvent() />

        <div class="ops-item">
            <div class="ops-card-saison-highlight">
                <figure class="fit-cover">
                    <#if entry.imageURL?has_content>
                        <img src="${entry.imageURL}" width="390" height="420" alt="${entry.getTitle(locale)}"/>
                    </#if>
                </figure>
                <div>
                    <time><span>${entry.getEventScheduleDisplay(locale, false, true)}</span></time>
                    <div class="ops-next-date"></div>
                    <h3>${entry.getTitle(locale)}</h3>
                    <span class="ops-typologie">${entry.getLabelTypologies(locale)}</span>
                    <div class="ops-content">
                        <span class="ops-songs"><strong>${entry.getComposer()}</strong></span>
                        <span class="ops-names">${entry.getDistribution()}</span>
                    </div>   
                    <#if entry.bookingURL?has_content>
                        <span class="ops-bottom-card"><a href="${entry.bookingURL}"><@liferay_ui.message key="eu.ops.buy.my.ticket" /></a></span>
                    </#if>          
                </div>
            </div>
        </div>

    </#list>
</div>