<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section id="expo-list">
    <div  class="content container">
        <#if entries?has_content>
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
                        <a href="" aria-label="${title}" title="${title}" class="expo-thumbnail">
                            <img src="${imageURL}" alt="${title}" title="${title}" />
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
                        <#if curEntry?counter % 8 == 0>
                                <div class="btn-more">
                                    <@liferay_ui.message key="eu.museum.more-expo" />
                                    <button class="btn" data-list="list-${curEntry?counter}"></button>
                                </div>
                            </div>
                            <div class="list list-${curEntry?counter}">
                        </#if>
                    </#if>
                </#list>
            </div>
        </#if>
    </div>
</section>

<script>
    $('.btn-more').click(function(element){
        $(this).hide();
        $("." + $(this).children('.btn').attr("data-list")).css('display', "flex");
    });
</script>