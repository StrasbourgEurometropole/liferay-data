<!-- Vignette manifestation -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<div class="mns-bloc-agenda" itemscope itemtype="http://schema.org/Event">
    <a href="${homeURL}manifestation/-/entity/id/${entry.manifestationId}">
        <span class="date">${entry.getManifestationScheduleDisplay(locale)}</span>
        <figure>
            <img src='${entry.getImageURL()}' alt="${entry.getTitle(locale)}" width="270" height="400" class="fit-cover" />
        </figure>
        <div>
            <div class="mns-indic" style="text-align: center">
                <span class="icon-ico-type"></span>
                <span>${entry.getTypeLabel(locale)}</span>
            </div>
            <h3 itemprop="name">${entry.getTitle(locale)}</h3>
            <span class="basic-link"><@liferay_ui.message key="eu.discover" /></span>
        </div>
    </a>
</div>