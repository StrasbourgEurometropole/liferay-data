<!-- Détail activité -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:description":'${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}'
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="seu-container">
    <a href="#" class="add-favorites" 
        data-type="10" 
        data-title="${entry.getTitle(locale)}" 
        data-url="${themeDisplay.getPortalURL()}${homeURL}activite/-/entity/id/${entry.activityId}" 
        data-id="${entry.activityId}">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
    <h1>
        ${entry.getTitle(locale)}
        <div class="seu-event-categories" data-dot="1" style="word-wrap: break-word;">  
            ${entry.getTypesLabel(locale)}
        </div>
    </h1>

    <div class="rte">
        <h2><@liferay_ui.message key="eu.presentation" /></h2>
        ${entry.getDescription(locale)}

        <#if entry.publishedActivityCourses?has_content>
            <h2><@liferay_ui.message key="eu.activity.courses" /></h2>
            <#list entry.publishedActivityCourses as course>
                <div style="margin-bottom: 10px">
                    <a href="${homeURL}cours/-/entity/id/${course.activityCourseId}" title="${entry.getTitle(locale)}">
                        <div class="seu-btn-square--filled--second">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text">${course.getName(locale)}</span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </div>
                    </a>
                </div>
            </#list>
        </#if>
        <#if entry.filesURLs?has_content>
            <h2><@liferay_ui.message key="eu.useful-documents" /></h2>
            <#list entry.filesURLs as fileURL>
                <#assign file = fileEntryHelper.getFileEntryByRelativeURL(fileURL) />
                <#assign title = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
                <#assign size = fileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale) />
                <div class="seu-wi seu-media seu-wi-download">  
                    <div class="seu-media-container">  
                        <div class="seu-media-left"><div class="seu-media-picto"></div></div>  
                        <div class="seu-media-right">  
                            <div class="seu-media-text">  
                                <div class="seu-media-title">${title}</div>  
                                <p>${file.getExtension()?upper_case} - ${size}</p>  
                            </div>  
                            <a href="{url}" target="_blank" class="seu-media-download seu-btn-square seu-filled seu-second" title="${title} (<@liferay_ui.message key="eu.new-window" />)">  
                                <div class="seu-btn-text-editable">
                                    <span class="seu-flexbox">  
                                        <span class="seu-btn-text"><@liferay_ui.message key="download" /></span>  
                                        <span class="seu-btn-arrow">&nbsp;</span>  
                                    </span>
                                </div>  
                            </a>  
                        </div>  
                    </div>  
                </div>  
            </#list>
        </#if>
        <#if entry.videos?has_content>
            <h2>
                <#if (entry.videos?size == 1)>
                    <@liferay_ui.message key="eu.video" />
                <#else>
                    <@liferay_ui.message key="eu.videos" />
                </#if>
            </h2>
            <#list entry.videos as video>
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
        </#if>
        <#if entry.imagesURLs?has_content>
            <h2><@liferay_ui.message key="gallery_images" /></h2>
            <div class="seu-slider-int-container">
                <div class="seu-slider-overflow">
                    <div class="seu-slider">
                        <#list entry.imagesURLs as imageURL>
                            <#assign file = fileEntryHelper.getFileEntryByRelativeURL(imageURL) />
                            <#assign title = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
                            <#assign legend = fileEntryHelper.getImageLegend(file.getFileEntryId(), locale) />
                            <#assign copyright = fileEntryHelper.getImageCopyright(file.getFileEntryId(), locale) />
                            <div class="seu-item" style="background-image: url(${imageURL});">
                                <div class="seu-text">
                                    <#if title?has_content>
                                        <div class="seu-img-title" data-dot="1">${title}</div>
                                    </#if>
                                    <#if legend?has_content>
                                        <div class="seu-description" data-dot="2">${legend}</div>
                                    </#if>
                                    <#if copyright?has_content>
                                        <div class="seu-caption">© ${copyright}</div>
                                    </#if>
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
    </div>
</div>
