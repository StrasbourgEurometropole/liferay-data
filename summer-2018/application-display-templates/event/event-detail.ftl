<!-- Détail événement -->
<#setting locale = locale />
<div class="container mns-agenda-detail mns-fck">
    <div class="row">
        <div class="col-sm-3 hidden-xs">
            <figure>
                <img src="${entry.getImageURL()}" alt="${entry.getTitle(locale)}" class="mns-img-agenda-top" />
                <figcaption>© ${entry.getImageCopyright(locale)}</figcaption>
            </figure>
            <div class="mns-info-pratiques mns-info-test">
                <div class="mns-sec-info">
                    <h3 class="mns-title-info">Infos et contact</h3>
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
        <div class="col-sm-9 mns-content-agenda-detail" >
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

            <!--Calcul pour permettre de prendre pile la place qu'il faut aux 4 colonnes d'infos s'il y en a des manquantes-->
            <#assign i=1/>
            <#if entry.free == 1 || entry.getPrice(locale)?has_content>
                <#assign i=i+1>
            </#if>
            <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                <#assign i=i+1>
            </#if>
            <#if entry.getAccess(locale)?has_content>
                <#assign i=i+1>
            </#if>

            <#if i ==4>
                <#assign column=6>
            <#else>
                <#assign column= 12/i />
            </#if>

            <div class="mns-info-more col-sm-${column}">
                <span class="mns-title-detail-actu"><@liferay_ui.message key="eu.dates-and-times" /></span>
                <#list entry.eventPeriods as period>
                    <p>
                    ${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> : ${period.getTimeDetail(locale)}</#if>
                    </p>
                </#list>
            </div>
            <#if entry.free == 1 || entry.getPrice(locale)?has_content>
                <div class="mns-info-more col-sm-${column}">                 
                    <span class="mns-title-detail-actu">Tarifs</span>
                    <#if entry.free == 1>
                        <div class="free-event">
                            <@liferay_ui.message key="eu.free-event" />
                        </div>
                    </#if>
                    <p>${entry.getPrice(locale)}</p>
                </div>
            </#if>
            <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                <div class="mns-info-more col-sm-${column}">
                    <span class="mns-title-detail-actu" style="padding-bottom: 10px;">Services aux Handicapés</span>
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
                </div>
            </#if>
            <#if entry.getAccess(locale)?has_content>
                <div class="mns-info-more col-sm-${column}">
                    <span class="mns-title-detail-actu">Transport</span>
                    <p>${entry.getAccess(locale)}</p>
                </div>
            </#if>
        </div>
    </div>
</div>