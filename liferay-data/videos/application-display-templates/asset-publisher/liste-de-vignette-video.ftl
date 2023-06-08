<!-- Liste de vignette Vidéo -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<#if entries?has_content>
    <div class="video-grid"> 
        <div class="category"> 
          <div class="container"> 
            <#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
            <#assign normalizer = serviceLocator.findService("com.liferay.portal.kernel.util.FriendlyURLNormalizer") />
            <#assign portletTitle = portletHelper.getPortletTitle('channel', renderRequest) />
            <#assign normalizedTitle = normalizer.normalize(portletTitle) />
            <h3 class="category-title"><a href="${homeURL}${normalizedTitle}">${portletTitle}</a></h3> 
          <div class="grid-3"> 
            <#list entries as curEntry>
                <#assign video = curEntry.getAssetRenderer().getVideo() />
                <#assign videoCssClass = "" />
                <#if (curEntry?index == 3)>
                  <#assign videoCssClass = "more mobile-hidden" />
                </#if>
                <#if (curEntry?index == 4) || (curEntry?index == 5)>
                  <#assign videoCssClass = "show-wide-tablet" />
                </#if>
                <#if (curEntry?index >= 4)>
                  <#assign videoCssClass = videoCssClass + " more desktop-only"/>
                </#if>
                <div class="video-thumbnail ${videoCssClass}"> 
                  <a href="${homeURL}video/-/entity/id/${video.videoId}">
                    <img src="${video.getImageURL()}">
                  </a> 
                  <div class="meta"> 
                    <div class="title">
                      <a href="${homeURL}video/-/entity/id/${video.videoId}">${video.getTitle(locale)}</a>
                    </div> 
                    <div class="provider">${video.getProvidersLabel(locale)}</div> 
                    <div class="date">${video.getPublicationDate()?string("dd/MM/yyyy")}</div> 
                  </div> 
                </div> 
            </#list>
        </div>
            <div role="button" class="btn btn-more"></div> 
            <div class="see-all hidden"> 
              <a href="${homeURL}${normalizedTitle}"><@liferay.language key="eu.videos-theme.see-all-videos" /></a> 
            </div> 
          </div>
        </div>
    </div>
</#if>