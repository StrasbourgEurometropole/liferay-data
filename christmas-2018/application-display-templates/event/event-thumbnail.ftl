<!-- Vignette événement -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="mns-bloc-agenda" itemscope itemtype="http://schema.org/Event">
    <a href="${homeURL}event/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}">
        <span class="date">${entry.getEventScheduleDisplay(locale)}</span>
        <figure>
            <img src='${entry.getImageURL()}' alt="${entry.getTitle(locale)}" width="270" height="400" class="fit-cover" />
        </figure>
        <div>
            <div class="row">
                <div class="col-xs-6 mns-indic">
                    <span class="icon-ico-map-marker"></span>
                    <span itemprop="location" itemscope itemtype="http://schema.org/Place" style="margin: 0">
                        <span itemprop="address" itemscope itemtype="http://schema.org/PostalAddress" style="margin: 0">
                            ${entry.getPlaceAlias(locale)[0..*20]}<#if (entry.getPlaceAlias(locale)?length > 20)>...</#if>
                        </span>
                    </span>
                </div>
                <div class="col-xs-6 mns-indic">
                    <span class="icon-ico-type"></span>
                    <span style="margin: 0">${entry.getTypeLabel(locale)[0..*20]}<#if (entry.getTypeLabel(locale)?length > 20)>...</#if></span>
                </div>
            </div>
            <h3 itemprop="name">${entry.getTitle(locale)[0..*40]}<#if (entry.getTitle(locale)?length > 40)>...</#if></h3>
            <span class="basic-link"><@liferay_ui.message key="eu.discover" /></span>
        </div>
    </a>
</div>
