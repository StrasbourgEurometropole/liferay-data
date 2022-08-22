<!-- Vignette événement -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<div class="mns-bloc-agenda" itemscope itemtype="http://schema.org/Event">
    <a href="${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}">
        <span class="date">${entry.getEventScheduleDisplay(locale)}</span>
        <figure>
            <img src='${entry.getImageURL()}' alt="${entry.getTitle(locale)}" width="270" height="400" class="fit-cover" />
        </figure>
        <div>
            <div class="col-xs-12 mns-indic">
                <span  class="ico-map-marker" itemprop="location" itemscope itemtype="http://schema.org/Place"><span itemprop="address" itemscope itemtype="http://schema.org/PostalAddress">${entry.getPlaceAlias(locale)[0..*30]}<#if (entry.getPlaceAlias(locale)?length > 30)>...</#if></span></span>
            </div>
            <div class="col-xs-12 mns-indic">
                <span class="ico-type">${entry.getTypeLabel(locale)[0..*45]}<#if (entry.getTypeLabel(locale)?length > 45)>...</#if></span>
            </div>
            <h3 itemprop="name">${entry.getTitle(locale)}</h3>
            <span class="basic-link"><@liferay_ui.message key="eu.discover" /></span>
        </div>
    </a>
</div>