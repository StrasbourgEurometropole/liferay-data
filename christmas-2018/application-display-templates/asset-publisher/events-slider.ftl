<!-- Slider événements -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="mns-agenda-detail">
    <div class="mns-section-agenda">
        <div class="small-container">
            <#if entries?has_content>
                <div class="col-xs-12">
                    <h2><@liferay_ui.message key="related-events" /></h2>
                    <a href="/agenda" class="link"><@liferay_ui.message key="eu.see-all-agenda" /></a>
                </div>
                <div class="owl-carousel owl-opacify owl-theme col-xs-12" id="owl-agenda">
                    <#list entries as curEntry>
                        <#if (curEntry?index == 20)>
                            <#break>
                        </#if>
                        <#assign event = curEntry.getAssetRenderer().getEvent() />
                        <div class="item">
                            <div class="mns-bloc-agenda" itemscope itemtype="http://schema.org/Event">
                                <a href="${homeURL}event/-/entity/id/${event.eventId}/${event.getNormalizedTitle(locale)}">
                                    <span class="date">${event.getEventScheduleDisplay(locale)}</span>
                                    <figure>
                                        <img src='${event.getImageURL()}' alt="${event.getTitle(locale)}" width="270" height="400" class="fit-cover" />
                                    </figure>
                                    <div>
                                        <div class="mns-indic">
                                            <div>
                                                <span class="icon-ico-map-marker"></span>
                                                <span itemprop="location" itemscope itemtype="http://schema.org/Place"><span itemprop="address" itemscope itemtype="http://schema.org/PostalAddress">${event.getPlaceAlias(locale)}</span></span>       
                                            </div>
                                            <div>
                                                <span class="icon-ico-type"></span>
                                                <span>${event.getTypeLabel(locale)}</span>
                                            </div>
                                        </div>
                                        <h3 itemprop="name">${event.getTitle(locale)}</h3>
                                        <span class="basic-link"><@liferay_ui.message key="eu.discover" /></span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </#list>
                </div>
            </#if>
        </div>
    </div>
</div>