<!-- Détail manifestation -->
<#setting locale = locale />

<#assign imageUrl = ""/>
<!-- image -->
<#if entry.imageURL?has_content>
    <#if !entry.imageURL?contains('http')>
        <#assign imageUrl = themeDisplay.getPortalURL() />
    </#if>
    <#assign imageUrl = imageUrl + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getManifestationScheduleDisplay(locale)} - ${entry.getTitle(locale)?html}",
"og:description":'${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="small-container mns-agenda-detail mns-fck">
    <div class="row">
        <div class="col-sm-4 hidden-xs">
            <figure>
                <img src="${entry.getImageURL()}" alt="${entry.getTitle(locale)}" class="mns-img-agenda-top" />
                <figcaption>© ${entry.getImageCopyright(locale)}</figcaption>
            </figure>
        </div>
        <div class="col-sm-8 mns-content-agenda-detail" data-egalize=".mns-info-more">
            <div class="col-xs-12">
                <span class="mns-event-date">${entry.getManifestationScheduleDisplay(locale)}</span>
                <h1>${entry.getTitle(locale)}</h1>
                <div class="mns-indic">
                    <span class="icon-ico-type"></span>
                    <span>${entry.getTypeLabel(locale)}</span>
                </div>
                <span class="mns-line"></span>
                <p>${entry.getDescription(locale)}</p>
            </div>
        </div>
    </div>
</div>

<!-- Evénements de  la manifestations -->

<#if entry.getPublishedEvents()?has_content>
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
        <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
        <#assign homeURL = "/" />
    </#if>
    <div class="mns-agenda-detail" style="margin-top: 50px">
        <div class="mns-section-agenda">
            <div class="small-container">
                <div class="col-xs-12">
                    <h2><@liferay_ui.message key="eu.manifestation-events" /></h2>
                    <a href="${homeURL}agenda" class="link"><@liferay_ui.message key="eu.see-all-agenda" /></a>
                </div>
                <div class="owl-carousel owl-opacify owl-theme col-xs-12" id="owl-agenda">
                    <#list entry.getPublishedEvents() as event>
                        <div class="item">
                            <div class="mns-bloc-agenda" itemscope itemtype="http://schema.org/Event">
                                <a href="${homeURL}event/-/entity/id/${event.eventId}/${event.getNormalizedTitle(locale)}">
                                    <span class="date">${event.getEventScheduleDisplay(locale)}</span>
                                    <figure>
                                        <img src='${event.imageURL}' alt="${event.getTitle(locale)}" width="270" height="400" class="fit-cover" />
                                    </figure>
                                    <div>
                                        <div class="col-xs-6 mns-indic">
                                            <span class="icon-ico-map-marker"></span>
                                            <span itemprop="location" itemscope itemtype="http://schema.org/Place"><span itemprop="address" itemscope itemtype="http://schema.org/PostalAddress">${event.getPlaceAlias(locale)}</span></span>
                                        </div>
                                        <div class="col-xs-6 mns-indic">
                                            <span class="icon-ico-type"></span>
                                            <span>${event.getTypeLabel(locale)}</span>
                                        </div>
                                        <h3 itemprop="name">${event.getTitle(locale)}</h3>
                                        <span class="basic-link"><@liferay_ui.message key="eu.discover" /></span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</#if>