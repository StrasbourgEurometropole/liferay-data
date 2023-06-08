<#setting locale = locale />
<#setting date_format="d MMMM yyyy">
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />

<section id="big-news" class="margin-bottom">
    <div class="content container">

        <#if entries?has_content>
            <#list entries as curEntry> 
                <#if curEntry?has_content && curEntry.getAssetRenderer()?has_content && curEntry.getAssetRenderer().getArticle()?has_content>
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
                    <div class="info">
                        <h2 class="desktop">${portletHelper.getPortletTitle('eu.museum.big-news', renderRequest)}</h2>
                        
                        <p class="title">
                            <span>${title}</span>
                        </p>
                        <div class="date">
                            <date><@liferay_ui["message"] key="eu.published-on" /> ${publishDate?date}</date>
                        </div>
                        <div class="museums">
                            <#if curEntry.categories?first?has_content>
                                <#assign vocabularyLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetVocabularyLocalService") />
                                <#list curEntry.categories as category>
                                    <#assign vocabulary = vocabularyLocalService.getVocabulary(category.vocabularyId) />
                                    <#if vocabulary.name == "Musées">
                                        <span>${category.getTitle(locale)}</span><#sep>,
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <a href="${viewURL}" aria-label="${title?html}" title="${title?html}" class="button1" ><@liferay_ui["message"] key="eu.museum.read" /></a>
                    </div>
                    <img src="${imageURL}" alt="${title?html}" title="${title?html}" />
                    <h2 class="mobile">${portletHelper.getPortletTitle('eu.museum.big-news', renderRequest)}</h2>
                </#if>
        	</#list>
        </#if>
    </div>
</section>