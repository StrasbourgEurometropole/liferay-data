<#setting locale = locale />
<#setting date_format="d MMMM yyyy">
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
    <#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />

<#if entries?has_content>
    <section id="news" class="margin-bottom">
        <div class="content container">
                
            <button class="button1" id="btn-all-news" aria-label="<@liferay_ui.message key="eu.museum.all-news" />" title='<@liferay_ui.message key="eu.museum.all-news" />'>
                <span class="points">
                    <span class="trait">
                        <span class="background">
                            <span>
                                <@liferay_ui.message key="eu.museum.all-news" />
                            </span>
                        </span>
                    </span>
                </span>
            </button>
            
            <h2>${portletHelper.getPortletTitle('eu.museum.news', renderRequest)}</h2>


            <div class="slider">
                <div class="swiper">
                    <div class="swiper-wrapper">
                        <#assign listEntries = (entries?size gt 3)?then(entries?sequence[0..2], entries) />
                        <#list listEntries as curEntry> 
                            <#if curEntry?has_content && curEntry.getAssetRenderer()?has_content && curEntry.getAssetRenderer().getArticle()?has_content>
                                <#assign newsMuseums = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(curEntry, "musees") />
                                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                                <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
                                <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                                <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
                                <#assign imageURL ="" />
                                <#if image?has_content>
                                    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
                                </#if>
                                <#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />
                                <#assign publishDate = curEntry.getPublishDate() />
                                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                                <div class="swiper-slide">
                                    <a href="${viewURL}"    aria-label="${title?html}" title="${title?html}" class="news-thumbnail" style="background-image: url(${imageURL});<#if entries?size == 1 && curEntry?index  == 0 >height:280px;</#if>">
                                        <div class="info">
                                            <div class="date">
                                                <date><@liferay_ui["message"] key="eu.published-on" /> ${publishDate?date}</date>
                                            </div>
                                            <div class="museums">
                                                <#if newsMuseums?first?has_content>
                                                    <#assign vocabularyLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetVocabularyLocalService") />
                                                    <#assign nbMusees = 0 />
                                                    <#assign musees = ""/>
                                                    <#list newsMuseums as category>
                                                        <#assign vocabulary = vocabularyLocalService.getVocabulary(category.vocabularyId) />
                                                        <#if vocabulary.name == "Musées">
                                                            <#assign nbMusees++ />
                                                            <#assign musees>
                                                                ${musees}  <li>${category.getTitle(locale)}</li>
                                                            </#assign>
                                                        </#if>
                                                    </#list>
                                                    <span>
                                                        ${newsMuseums?first.getTitle(locale)}
                                                        <#if nbMusees gt 2>
                                                            <@liferay_ui["message"] key="eu.museum.and-x" arguments="${nbMusees - 1}"/>
                                                        <#elseif nbMusees gt 1>
                                                            <@liferay_ui["message"] key="eu.museum.and"/>
                                                        </#if>
                                                    </span>
                                                    <ul class="list-museums">
                                                        ${musees}
                                                    </ul>
                                                </#if>
                                            </div>
                                            <p class="title">
                                                <span>${title}</span>
                                            </p>
                                        </div>
                                    </a>
                                </div>
                            </#if>
                        </#list>
                    </div>
                    <div class="swiper-button-prev"></div>
                    <div class="swiper-button-next"></div>
                </div>
                
            </div>
            
            <a href="${homeURL}actualite" class="button1" aria-label="<@liferay_ui.message key="eu.museum.all-news" />" title="<@liferay_ui.message key="eu.museum.all-news" />"><@liferay_ui.message key="eu.museum.all-news" /></a>
        </div>
    </section>
</#if>

<script>
    $("#btn-all-news").click(function(){
      location.href= '${themeDisplay.getPortalURL()}${homeURL}actualite'
    });
</script>