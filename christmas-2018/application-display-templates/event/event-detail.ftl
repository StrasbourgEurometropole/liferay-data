<!-- Détail événement -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign imageUrl = ""/>
<!-- image -->
<#if !entry.imageURL?has_content>
    <#assign imageUrl = entry.imageURL />
</#if>
<script>
    title = '${entry.getEventScheduleDisplay(locale)?js_string} - ${entry.getTitle(locale)?html?js_string}';
    description = '${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html?js_string}';
    imageUrl = '${imageUrl}';
</script>

<div class="small-container mns-wrapper-agenda-detail mns-fck">
    <div class="row">
        <div class="mns-aside-info col-sm-4">
            <figure class="fit-cover">
                <img src="${entry.getImageURL()}" alt="${entry.getTitle(locale)}" width="300" height="380" class="mns-img-agenda-top" />
                <figcaption>© ${entry.getImageCopyright(locale)}</figcaption>
            </figure>
            <div class="mns-info-pratiques mns-info-test">
                <div class="mns-sec-info">
                    <h3 class="mns-title-info"><@liferay_ui.message key="eu.infos-and-contact" /></h3>
                    <p>${entry.getPlaceAlias(locale)} 
                        <br> 
                        <#if entry.getPlaceAddress(locale)?has_content>
                            ${entry.getPlaceAddress(locale)} - 
                        </#if>
                        ${entry.placeZipCode} ${entry.getPlaceCity(locale)}
                    </p>
                    <#if entry.getWebsiteName(locale)?has_content && entry.getWebsiteURL(locale)?has_content>
                        <a href="${entry.getWebsiteURL(locale)}">${entry.getWebsiteName(locale)}</a>
                    </#if>
                    <#if entry.email?has_content>
                        <a href="mailto:${entry.email}">${entry.email}</a>
                    </#if>
                    <#if entry.phone?has_content>
                        <a href="tel:${entry.phone}">${entry.phone}</a>
                    </#if>
                </div>
            </div>
        </div>
        <div class="col-sm-8 mns-content-agenda-detail">
            <a href="#" class="add-favorites"
                data-type="2" 
                data-title="${entry.getTitle(locale)}" 
                data-url="${themeDisplay.getPortalURL()}${homeURL}event/-/entity/id/${entry.eventId}" 
                data-id="${entry.eventId}">
                <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
            </a>
            <div class="col-xs-12" style="float: none;">
                <span class="mns-event-date">${entry.getEventScheduleDisplay(locale)}</span>
                <h1>${entry.getTitle(locale)}</h1>
                <div class="mns-indic">
                    <div>
                        <span class="icon-ico-map-marker"></span>
                        <span>${entry.getPlaceAlias(locale)}</span>
                    </div>
                    <div>
                        <span class="icon-ico-type"></span>
                        <span>${entry.getTypeLabel(locale)}</span>
                    </div>
                </div>
                <span class="mns-line"></span>
                <p><strong>${entry.getSubtitle(locale)}</strong></p>
                <p>${entry.getDescription(locale)}</p>
            </div>
            <div class="mns-wrapper-info-more">
                <div class="mns-info-more col-sm-6">
                    <span class="mns-title-detail-actu"><@liferay_ui.message key="eu.dates-and-times" /></span>
                    <#list entry.eventPeriods as period>
                        <p>
                        ${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> : ${period.getTimeDetail(locale)}</#if>
                        </p>
                    </#list>
                </div>
                <div class="mns-info-more col-sm-6">
                    <#if entry.free == 1 || entry.getPrice(locale)?has_content>
                        <span class="mns-title-detail-actu"><@liferay_ui.message key="eu.prices" /></span>
                        <#if entry.free == 1>
                            <div class="free-event">
                                <@liferay_ui.message key="eu.free-event" />
                            </div>
                        </#if>
                        <p>${entry.getPrice(locale)}</p>
                    </#if>
                </div>
                <div class="mns-info-more col-sm-6">
                    <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                        <span class="mns-title-detail-actu"><@liferay_ui.message key="eu.services-for-disabled" /></span>
                        <#if entry.accessForBlind>
                            <span class="icon-ico-1"></span>
                        </#if>
                        <#if entry.accessForDeficient>
                            <span class="icon-ico-2"></span>
                        </#if>
                        <#if entry.accessForDeaf>
                            <span class="icon-ico-3"></span>
                        </#if>
                        <#if entry.accessForWheelchair>
                            <span class="icon-ico-4"></span>
                        </#if>
                        <#if entry.accessForElder>
                            <span class="icon-ico-5"></span>
                        </#if>
                        <p>
                            ${entry.getAccessForDisabled(locale)}
                        </p>
                    </#if>
                </div>
                <div class="mns-info-more col-sm-6">
                    <#if entry.getAccess(locale)?has_content>
                        <span class="mns-title-detail-actu"><@liferay_ui.message key="access" /></span>
                        <p>${entry.getAccess(locale)}</p>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
