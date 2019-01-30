<!-- Webmag - Vidéo -->
<#setting locale = locale />
<#assign GroupLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService")>
<#assign videoFriendlyURL = GroupLocalService.fetchGroup(themeDisplay.getCompanyId(),"Vidéos").getFriendlyURL()>
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign videoURL = "/web${videoFriendlyURL}/" />
<#else>
    <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
    <#assign videoURL = currentURL?keep_before("strasbourg.eu") + "videos.strasbourg.eu/" />
    <#if videoURL?split("://")?size == 2>
        <#assign videoURL = videoURL?split("://")?last />
    </#if>
</#if>
<#list entries as curEntry>
    <#assign entry = curEntry.getAssetRenderer().getVideo() />
    <#assign viewURL = videoURL + "video/-/entity/id/" + entry.getVideoId() />

    <div id="hp-video" class="hp-video" style="background-image: url(${entry.getImageURL()});" data-scroll-animation>
        <div class="hp-video__text">
            <h2 class="waved-title waved-title--small waved-title--t-core waved-title--w-grey waved-title--center hp-video__sup-title">Vidéo à la une</h2>
            <a href="${viewURL}" class="hp-video__single-link unstyled"  style="background-image: url(${entry.getImageURL()});">
                <h3 class="hp-video__title">${entry.getTitle(locale)}</h3>
                <i class="mag-player hp-video__player"></i>
            </a>
            <a href="http://videos.strasbourg.eu/" class="unstyled a-btn-main h-inverted icon-right transparent-core-dark hp-video__link">
                <span class="flexbox"><i class="mag mag-arrow-right"></i>
                    <span class="btn-text">Toutes les vidéos</span>
                </span>
            </a>
        </div>
    </div>
</#list>