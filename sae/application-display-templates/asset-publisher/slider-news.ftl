<#setting locale = locale />
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<div id="news">
    <a class="btn-square" title="<@liferay_ui.message key="sae.all-news" />" href="/actus">
        <span class="btn-text"><@liferay_ui.message key="sae.all-news" /></span> 
    </a>
    <h3><@liferay_ui.message key="sae.whats-news" /></h3>
    <!-- Slider main container -->
    <div class="swiper-news-container">

        <div class="swiper-wrapper">
            <!-- Slides -->
            <#list entries as curEntry>  
                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                <#assign catcher = docXml.valueOf("//dynamic-element[@name='catcher']/dynamic-content/text()") />
                <#assign illustration = docXml.valueOf("//dynamic-element[@name='illustration']/dynamic-content/text()") />
                <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
                <#assign imageURL ="" />
                <#if illustration?has_content>
                    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(illustration) />
                </#if>
                            
                <#assign publishDate = curEntry.getPublishDate() />
                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                <#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />
                <div class="swiper-slide news-content">
                    <img class="news-image" src="${imageURL}?imagePreview=1" loading="lazy" alt="${title}" />
                    <div class="news-meta">
                        <div class="news-title"> 
                            <span>${title}</span>
                        </div>
                        <span class="publication">
                                ${dateHelperService.displayShortDate(publishDate?date, locale)}
                        </span>
                        <div class="news-chapo"> 
                            <span>${catcher?replace("<[^>]*>", "", "r")}</span>
                        </div>
                        <a href="${viewURL}" title="${title}" class="news-link"> 
                            <@liferay_ui.message key='learn-more' />
                        </a>
                    </div>
                    <a href="${viewURL}" class="news-link-tablet"> 
                    </a>
                </div>              
            </#list>
        </div>

        <div class="swiper-news-pagination"></div>
    </div>
</div>


<@liferay_util["html-bottom"]>
    <script type="text/javascript">
        const swiperNews = new Swiper('.swiper-news-container', {
            // Optional parameters
            loop: true,
            slidesPerView: 1,

            // If we need pagination
            pagination: {
                el: '.swiper-news-pagination',
                clickable: true
            }
        });
    </script>
</@>