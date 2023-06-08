<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
    <#assign homeURL = "" />
</#if>
<#if entries?has_content>
    <section id="expo" class="margin-bottom">
        <div  class="content container">
            <h2>
                <@liferay_ui.message key="eu.museum.expos" />
                <span><@liferay_ui.message key="eu.museum.collection.discover" /></span>
            </h2>
            <div class="slider">
                <div class="swiper">
                    <div class="swiper-wrapper">
                        <#list entries as curEntry>
                            <#if curEntry?has_content && curEntry.getAssetRenderer()?has_content && curEntry.getAssetRenderer().getArticle()?has_content>
                                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                                <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                                <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
                                <#assign imageURL ="" />
                                <#if image?has_content>
                                    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
                                </#if>
                                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                                <#assign dates = docXml.valueOf("//dynamic-element[@name='dates']/dynamic-content/text()") />
                                <#assign viewUrl = "" />
                                <#assign link = docXml.valueOf("//dynamic-element[@name='link']/dynamic-content/text()") />
                                <#if link?has_content>
                                    <#assign linkArray = link?split("@") />
                                    <#assign layoutLocalService = serviceLocator.findService('com.liferay.portal.kernel.service.LayoutLocalService') />
                                    <#assign pageLayout = layoutLocalService.getLayout(linkArray[2]?number, false, linkArray[0]?number) />
                                    <#assign viewUrl = pageLayout.getFriendlyURL() />
                                </#if>
                                <div class="swiper-slide">
                                    <a href="${homeURL}${viewUrl}" aria-label="${title?html}" title="${title?html}" class="expo-thumbnail">
                                        <img src="${imageURL}" alt="${title?html}" title="${title?html}" />
                                        <div class="info">
                                            <div class="title">
                                                <span>${title}</span>
                                            </div>
                                            <div class="museums">
                                                <#if curEntry.categories?first?has_content>
                                                    <#assign vocabularyLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetVocabularyLocalService") />
                                                    <span>
                                                        <#assign nbMusees = 0 />
                                                        <#list curEntry.categories as category>
                                                            <#assign vocabulary = vocabularyLocalService.getVocabulary(category.vocabularyId) />
                                                            <#if vocabulary.name == "Musées">
                                                                <#if nbMusees gt 0 >
                                                                    , 
                                                                </#if>
                                                                ${category.getTitle(locale)}
                                                                <#assign nbMusees++ />
                                                            </#if>
                                                        </#list>
                                                    </span>
                                                </#if>
                                            </div>
                                            <div class="dates">
                                                <span>${dates}</span>
                                            </div>
                        
                                            <button class="button1" aria-label="<@liferay_ui.message key="eu.museum.know-more" />" title='<@liferay_ui.message key="eu.museum.know-more" />'>
                                                <span class="points">
                                                    <span class="trait">
                                                        <span class="background">
                                                            <span>
                                                                <@liferay_ui.message key="eu.museum.know-more" />
                                                            </span>
                                                        </span>
                                                    </span>
                                                </span>
                                            </button>
                                        </div>
                                    </a>
                                </div>
                            </#if>
                        </#list>
                    </div>
                    
                    <div class="swipper-buttons">
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>
            </div>
            
            <button id="btn-all-expos" class="button1" aria-label="<@liferay_ui.message key="eu.museum.all-expo" />" title='<@liferay_ui.message key="eu.museum.all-expo" />'>
                <span class="points">
                    <span class="trait">
                        <span class="background">
                            <span>
                                <@liferay_ui.message key="eu.museum.all-expo" />
                            </span>
                        </span>
                    </span>
                </span>
            </button>
        </div>
    </section>
</#if>

<script>
    $("#btn-all-expos").click(function(){
      location.href= '${themeDisplay.getPortalURL()}${homeURL}/expositions'
    });
</script>