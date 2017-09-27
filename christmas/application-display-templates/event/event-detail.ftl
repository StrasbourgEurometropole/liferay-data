<!-- Détail événement -->
<#setting locale = locale />
<div class="small-container mns-agenda-detail mns-fck">
    <div class="row">
        <div class="col-sm-4 hidden-xs">
            <figure>
                <img src="${entry.getImageURL()}" alt="${entry.getTitle(locale)}" class="mns-img-agenda-top" />
                <figcaption>© ${entry.getImageCopyright(locale)}</figcaption>
            </figure>
            <div class="mns-info-pratiques mns-info-test">
                <div class="mns-sec-info">
                    <h3 class="mns-title-info">Infos & Contact</h3>
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
        <div class="col-sm-8 mns-content-agenda-detail" data-egalize=".mns-info-more">
            <div class="col-xs-12">
                <span class="mns-event-date">${entry.getEventScheduleDisplay(locale)}</span>
                <h1>${entry.getTitle(locale)}</h1>
                <div class="mns-indic">
                    <span class="icon-ico-map-marker"></span>
                    <span>${entry.getPlaceAlias(locale)}</span>
                    <span class="icon-ico-type"></span>
                    <span>${entry.getTypeLabel(locale)}</span>
                </div>
                <span class="mns-line"></span>
                <p><strong>${entry.getSubtitle(locale)}</strong></p>
                <p>${entry.getDescription(locale)}</p>
            </div>
            <div class="mns-info-more col-sm-6">
                <span class="mns-title-detail-actu">Dates & horraires</span>
                <#list entry.eventPeriods as period>
                    <p>
                    ${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> : ${period.getTimeDetail(locale)}</#if>
                    </p>
                </#list>
            </div>
            <div class="mns-info-more col-sm-6">
                <#if entry.free == 1 || entry.getPrice(locale)?has_content>
                    <span class="mns-title-detail-actu">Tarifs</span>
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
                    <span class="mns-title-detail-actu">Services aux Handicapés</span>
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
                    <p>
                        ${entry.getAccessForDisabled(locale)}
                    </p>
                </#if>
            </div>
            <div class="mns-info-more col-sm-6">
                <#if entry.getAccess(locale)?has_content>
                    <span class="mns-title-detail-actu">Accès</span>
                    <p>${entry.getAccess(locale)}</p>
                </#if>
            </div>
        </div>
    </div>
</div>