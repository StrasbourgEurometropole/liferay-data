<!-- Vignette membre -->

<#if entries?size != 0 >

    <!-- Chargement de la variable de localisation -->
    <#setting locale = locale />
    
    <#-- Création de l'URL de détail -->
    <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />

    <#-- récupération de l'instance id de l'asset publisher pour generer un instance id unique pour le web content display -->
    <#assign portletDisplay = themeDisplay.getPortletDisplay()/>
    <#assign portletid = portletDisplay.getId()/> 

    <!-- WRAPPER LISTING RECRUTEMENT -->
    <div class="ops-group-orchestre ops-content-wrapper">

        <@liferay_portlet["runtime"]
        portletProviderAction=portletProviderAction.VIEW
        portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
        instanceId="separateur_${portletid}"/>

        <ul data-egalize=".ops-card-people">
        <#list entries as entry>

            <#-- Récupération des champs du contenu web -->
            <#assign renderer  = entry.getAssetRenderer() />
            <#assign journalArticle   = renderer.getArticle() />
            <#assign docXml = saxReaderUtil.read(journalArticle.getContentByLocale(locale)) />
            <#assign rootElement = docXml.getRootElement() />
            <#assign name = docXml.valueOf("//dynamic-element[@name='Name']/dynamic-content/text()")/>
            <#assign post = docXml.valueOf("//dynamic-element[@name='post']/dynamic-content/text()")/>
            <#assign image = docXml.valueOf("//dynamic-element[@name='smallImage']/dynamic-content/text()")/>
            <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
            <#assign imageURL ="" />
            <#if image?has_content>
                <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
            </#if>
            <#assign detailURL = layoutHelper.getJournalArticleLayoutURL(journalArticle.groupId, journalArticle.articleId, themeDisplay) />

            <li>
            <#if detailURL?has_content>
                <a href="${detailURL}" class="ops-card-people">
            <#else>
                <div class="ops-card-people">
            </#if>
                    <#if image?has_content>
                        <figure class="fit-cover">
                                <img src="${imageURL}" width="80" height="80" alt="${name}"/>
                        </figure>
                    </#if>
                    <div class="ops-info-people">
                        <span class="ops-function">${post}</span>
                        <span class="ops-name">${name}</span>
                        <#if detailURL?has_content><span class="ops-link"><@liferay_ui.message key="eu.ops.learn.more" /></span></#if>
                    </div>
            <#if detailURL?has_content>
                </a>
            <#else>
                </div>
            </#if>
            </li>
        </#list>
        </ul>
    </div>
</#if>