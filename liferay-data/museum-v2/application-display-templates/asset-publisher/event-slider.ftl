<#setting locale = locale />
<#setting datetime_format="iso">
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />

<#if entries?has_content>
    <section id="events" class="margin-bottom">
        <div  class="content container">
            <h2>${portletHelper.getPortletTitle('eu.museum.moment', renderRequest)}</h2>
            <div class="slider">
                <div class="swiper">
                    <div class="swiper-wrapper">
                        <#list entries as curEntry>
                            <#assign event = curEntry.getAssetRenderer().getEvent() />
                            <#assign detailURL = homeURL + "evenement/-/entity/id/" + event.eventId + "/" + event.getNormalizedTitle(locale) />
                            <div class="swiper-slide">
                                <a href="${detailURL}" aria-label="${event.getTitle(locale)?html}" title="${event.getTitle(locale)?html}" class="event-thumbnail" style="background-image: url(${event.getImageURL()})">
                                    <#if event.getActivityTypeLabel(locale)?has_content>
                                        <div class="visit">
                                            <span>${event.getActivityTypeLabel(locale)}</span>
                                        </div>
                                    </#if>
                                    <div class="info">
                                        <div class="title">
                                            <span>${event.getTitle(locale)}</span>
                                        </div>
                                        <div class="dates">
                                            <span>${event.getEventScheduleDisplay(locale)}</span>
                                        </div>
                                        <div class="museums">
                                            <span>${event.getMuseumsLabel(locale)}</span>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </#list>
                    </div>
                    
                    <div class="swipper-buttons">
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>
            </div>
            
            <button id="btn-all-events" class="button1" aria-label="<@liferay_ui.message key="eu.museum.all-events" />" title='<@liferay_ui.message key="eu.museum.all-events" />'>
                <span class="points">
                    <span class="trait">
                        <span class="background">
                            <span>
                                <@liferay_ui.message key="eu.museum.all-events" />
                            </span>
                        </span>
                    </span>
                </span>
            </button>
        </div>
    </section>
</#if>

<script>
    $("#btn-all-events").click(function(){
      location.href='${themeDisplay.getPortalURL()}${homeURL}agenda'
    });
</script>