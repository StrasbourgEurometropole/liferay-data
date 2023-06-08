<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
    <#assign homeURL = "" />
</#if>

<#if entries?has_content>
    <section id="expo-list">
        <div  class="content container">
            <div class="list">
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
                        <a href="${homeURL}${viewUrl}" aria-label="${title?html}" title="${title?html}" class="expo-thumbnail">
                            <img src="${imageURL}" alt="${title?html}" title="${title?html}" />
                            <div class="info">
                                <div class="title">
                                    <span>${title}</span>
                                </div>
                                <div class="dates">
                                    <span>${dates}</span>
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
                            </div>
                        </a>
                        <#if curEntry?counter % 8 == 0>
                                <div class="btn-more">
                                    <@liferay_ui.message key="eu.museum.more-expo" />
                                    <button class="btn" data-list="list-${curEntry?counter}" aria-label="<@liferay_ui.message key="eu.museum.more-expo" />"></button>
                                </div>
                            </div>
                            <div class="list list-${curEntry?counter}">
                        </#if>
                    </#if>
                </#list>
            </div>
        </div>
    </section>
</#if>

<script>
    $('.btn-more').click(function(element){
        $(this).hide();
        $("." + $(this).children('.btn').attr("data-list")).css('display', "flex");
    });
</script>