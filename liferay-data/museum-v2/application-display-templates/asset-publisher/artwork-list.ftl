<#setting locale = locale />
<#setting datetime_format="iso">
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />

<section id="oeuvre">
    <div  class="content container">
        <div class="infos">
            <h2>${portletHelper.getPortletTitle('eu.museum.collection', renderRequest)}</h2>
            <p><@liferay_ui.message key="eu.museum.collection.description" /></p>
            <a href="https://musees-strasbourg.skin-web.org/" target="_blank" class="button1" aria-label="<@liferay_ui.message key="eu.museum.all-collection" /> (<@liferay_ui.message key="eu.new-window" />)" title="<@liferay_ui.message key="eu.museum.all-collection" /> (<@liferay_ui.message key="eu.new-window" />)"><@liferay_ui.message key="eu.museum.all-collection" /></a>
        </div>
        <#if entries?has_content>
            <div id="listCollections" class="list">
                <div class="gutter-sizer"></div>
        	    <#list entries as curEntry>
                    <#if curEntry?has_content && curEntry.getAssetRenderer()?has_content && curEntry.getAssetRenderer().getArticle()?has_content>
                        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                        <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                        <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
                        <#assign imageURL ="" />
                        <#if image?has_content>
                            <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
                            <#assign imageJSON = image?replace("\\u2019","'")?eval />
                            <#assign alt = imageJSON.alt />
                        </#if>
                        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                        <#assign link = docXml.valueOf("//dynamic-element[@name='link']/dynamic-content/text()") />
                        <a href="${link}" target="_blank" aria-label="${title?html} (<@liferay_ui.message key="eu.new-window" />)" title="${title?html} (<@liferay_ui.message key="eu.new-window" />)" class="oeuvre-thumbnail">
                            <img src="${imageURL}" alt="${alt?html}" title="${alt?html}" />
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
                            </div>
                        </a>
                    </#if>
                </#list>
            </div>
        </#if>
    </div>
</section>