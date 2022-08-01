<!-- Détail cours -->
<#setting locale = locale />

<#assign uriHelper = serviceLocator.findService("eu.strasbourg.utils.api.UriHelperService")/>

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:description":'${entry.getPresentation(locale)?replace("<[^>]*>", "", "r")?html}'
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="seu-container">
    <a href="#" class="add-favorites"
        data-type="11" 
        data-title="${entry.getName(locale)}" 
        data-url="${themeDisplay.getPortalURL()}${homeURL}cours/-/entity/id/${entry.activityCourseId}" 
        data-id="${entry.activityCourseId}">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
    <h1>
        ${entry.getName(locale)}
        <div class="seu-event-categories" data-dot="1" style="word-wrap: break-word;">  
            ${entry.getTypesLabels(locale)}
        </div>
    </h1>

    <div class="rte">
        <h2><@liferay_ui.message key="eu.presentation" /></h2>
        <p>${entry.getPresentation(locale)}</p>
            
        <strong>
            <@liferay_ui.message key="eu.activity.part-of-activity" /> <a href="${homeURL}activite/-/entity/id/${entry.activityId}" title="${entry.activity.getTitle(locale)}">${entry.activity.getTitle(locale)}</a>
        </strong>

        <#if entry.publics?has_content>
            <h3>
                <#if (entry.getPublics()?size == 1)>
                    <@liferay_ui.message key="eu.activity.public" />
                </#if>
                <#if (entry.getPublics()?size > 1)>
                    <@liferay_ui.message key="eu.activity.publics" />
                </#if>
            </h3>
            ${entry.getPublicsLabel(locale)}
        </#if>
        <#if entry.getArrangements(locale)?has_content>
            <h3>
                <@liferay_ui.message key="eu.activity.arrangements" />
            </h3>
            ${entry.getArrangements(locale)}
        </#if>
        <#if entry.getPrice(locale)?has_content>
            <h3>
                <@liferay_ui.message key="eu.prices" />
            </h3>
            ${entry.getPrice(locale)}
        </#if>
        <#if entry.getOrganizerName(locale)?has_content>
            <h3>
                <@liferay_ui.message key="eu.activity.manager" />
            </h3>
            <a href="${homeURL}organisateur-activites/-/entity/id/${entry.organizerId}">${entry.getOrganizerName(locale)}</a>
        </#if>
    </div>
        
        <#if entry.getDocumentURLs()?has_content >
            <div class="rte"><h2><@liferay_ui.message key="eu.documents" /></h2></div>
            <div class="seu-wi-content">
                <#assign documents = entry.getDocuments() />
                <#list documents?keys as title>
                    <a href="${documents[title]}" class="seu-btn-square seu-bordered seu-core" title="${title}">
                        <span class="seu-flexbox">
                            <span class="seu-btn-text">${title}</span>
                            <span class="seu-btn-arrow"></span>
                        </span>
                    </a><br/>
                </#list>
            </div>
        </#if>
        
        <#assign images = entry.getImageIds() />
        <#if images?has_content>
            <div class="rte"><h2><@liferay_ui.message key="gallery_images" /></h2></div>
            <div class="seu-slider-int-container">
                <div class="seu-slider-overflow">
                    <div class="seu-slider">
                        <#list images?split(",") as imageId>
                            <div class="seu-item" style="background-image: url(${entry.getImageURL(imageId?number)});">
                                <div class="seu-text">
                                    <div class="seu-img-title" data-dot="1">${entry.getImageTitle(imageId?number, locale)}</div>
                                    <div class="seu-description" data-dot="2">${entry.getImageLegend(imageId?number, locale)}</div>
                                    <div class="seu-caption">${entry.getImageCopyright(imageId?number, locale)}</div>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
                <div class="owl-nav">
                    <button class="seu-owl-prev">
                        <span class="seu-picto"></span>
                    </button>
                    <button class="seu-owl-next">
                        <span class="seu-picto"></span>
                    </button>
                </div>
            </div>
        </#if>
        
        <#assign videos = entry.getVideos() />
        <#if videos?has_content>
            <div class="rte"><h2><@liferay_ui.message key="eu.video" /></h2></div>
            <div class="seu-collapsing-box">
                <#list videos as video>
                    <div class="seu-wi seu-media seu-wi-embed">
                        <div class="seu-media-container">
                            <div class="seu-media-left">
                                <div class="seu-media-picto"></div>
                            </div>
                            <div class="seu-media-right">
                                <div class="seu-media-text">
                                    <div class="seu-media-title">${video.getTitle(locale)}</div>
                                    <p class="seu-media-description">${video.getDescription(locale)}</p> 
                                </div>
                            </div>
                            <div class="seu-media-bottom">
                                <div class="seu-media-ratio">
                                    ${video.getPlayer(locale)}
                                </div>
                            </div>
                        </div>
                    </div>   
                </#list>
            </div>
        </#if>

    <#assign agenda = entry.getCourseAgenda(themeDisplay.scopeGroupId, locale) />
    <#if agenda.periods?has_content>
        <div class="rte"><h2><@liferay_ui.message key="eu.activity.places-and-schedules" /></h2></div>
        <div class="row">
            <div class="col-md-9" style="padding: 50px 30px; background-color: #f6f6f6; margin-bottom: 50px;">
                <div class="seu-wi seu-wi-schedules" >
                    <div class="tab-list">
                        <div class="tab-menu-rwd">
                            <#list agenda.periods as period>
                                <button class="tab-toggle <#if period?is_first>current</#if>" data-tab-target="${period?index}">
                                    ${period.periodName}
                                </button>
                            </#list>
                        </div>
                        <#list agenda.periods as period>
                            <button class="tab-toggle <#if period?is_first>current</#if>" data-tab-target="${period?index}">
                                ${period.periodName}
                            </button>
                        </#list>
                    </div>
                    <#list agenda.periods as period>
                        <div class="tab-content <#if period?is_first>tabbed</#if>" data-tab-index="${period?index}">
                            <h3 class="hidden">${period.periodName}</h3>
                            <#list period.places as periodPlace>
                                <div style="margin-bottom: 20px">
                                    <div class="tab-title">${periodPlace.placeName}</div>
                                    <#if periodPlace.placeSigId?has_content>
                                        <div class="rte" style="margin-top: -5px; margin-bottom: 10px;">
                                            <a href="${homeURL}lieu/-/entity/sig/${periodPlace.placeSigId}/${uriHelper.normalizeToFriendlyUrl(periodPlace.placeName)}"><@liferay_ui.message key="eu.activity.see-place-detail" /></a>
                                        </div>
                                    </#if>
                                    <ul class="schedule-list" style="margin-bottom: 10px;">
                                        <#list 0..6 as day>
                                            <#assign schedules = periodPlace.getSchedulesForDay(day) />
                                            <#if schedules?has_content>
                                                <li>
                                                    <span><@liferay_ui.message key="${periodPlace.getDayName(day)}" /></span>
                                                    <span>
                                                        <#list schedules as schedule>
                                                            <div>${schedule.startTime} - ${schedule.endTime}</div>
                                                            <#if schedule.getComments(locale)?has_content>
                                                                <div style="margin-top: -10px;">(${schedule.getComments(locale)})</div>
                                                            </#if>
                                                        </#list>
                                                    </span>
                                                </li>
                                            </#if>
                                        </#list>
                                    </ul>
                                </div>
                            </#list>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    <#else>
        <div class="rte"><h2><@liferay_ui.message key="places" /></h2></div>
        <div class="row seu-wi-schedules">
            <div class="col-md-9" style="padding: 50px 30px; background-color: #f6f6f6; margin-bottom: 50px;">
                <#list entry.getPlaceSIGIds(locale) as sigId>
                    <div class="tab-title">${entry.getPlaceNames(locale)[sigId?index]}</div>
                    <div class="rte" style="margin-top: -5px; margin-bottom: 10px;">
                        <a href="${homeURL}lieu/-/entity/sig/${sigId}/${uriHelper.normalizeToFriendlyUrl(entry.getPlaceNames(locale)[sigId?index])}"><@liferay_ui.message key="eu.activity.see-place-detail" /></a>
                    </div>
                </#list>
            </div>
        </div>
    </#if>
</div>

<#if entry.imageURL?has_content>
    <script>
        if ($('.region-banner').length == 0) {
            var bannerHtml = '<div class="region-banner"></div>';
            $('.region-post-header').addClass('has-banner').prepend(bannerHtml);
        }
    </script>
    <style>
        .region-banner {
            background-image: url(${entry.imageURL}) !important;
        }
    </style>
</#if>