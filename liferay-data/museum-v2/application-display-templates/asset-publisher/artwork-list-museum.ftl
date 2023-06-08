<#setting locale = locale />
<#setting datetime_format="iso">
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<#assign jsonFactoryUtil = serviceLocator.findService("com.liferay.portal.kernel.json.JSONFactoryUtil") />

<section id="oeuvre" class="detail-museum margin-bottom">
    <div  class="content container">
        <div class="infos">
            <h2>${portletHelper.getPortletTitle('eu.museum.collection', renderRequest)}</h2>
            
            <button id="btn-all-collection" class="button1" aria-label="<@liferay_ui.message key="eu.museum.all-collection" /> (<@liferay_ui.message key="eu.new-window" />)" title='<@liferay_ui.message key="eu.museum.all-collection" /> (<@liferay_ui.message key="eu.new-window" />)'>
                <span class="points">
                    <span class="trait">
                        <span class="background">
                            <span>
                                <@liferay_ui.message key="eu.museum.all-collection" />
                            </span>
                        </span>
                    </span>
                </span>
            </button>
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
                        <#assign imageDescription = "">
                        <#if image?has_content>
                            <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
                            <#assign imageDescription = jsonFactoryUtil.createJSONObject(image).alt>
                        </#if>
                        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                        <#assign link = docXml.valueOf("//dynamic-element[@name='link']/dynamic-content/text()") />
                        <a href="${link}" target="_blank" aria-label="${title?html} (<@liferay_ui.message key="eu.new-window" />)" title="${title?html} (<@liferay_ui.message key="eu.new-window" />)" class="oeuvre-thumbnail">
                            <img src="${imageURL}" alt="${imageDescription?html}" title="${imageDescription?html}" />
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

<script>
    $("#btn-all-collection").click(function(){
      window.open('https://musees-strasbourg.skin-web.org/', '_blank').focus();
    });
</script>