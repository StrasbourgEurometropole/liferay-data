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
            <#assign detailURL = layoutHelper.getJournalArticleLayoutURL(journalArticle.groupId, journalArticle.articleId, themeDisplay) />

            <li>
                <a href="${detailURL}" class="ops-card-people">
                    <figure class="fit-cover">
                            <img src="${image}" width="80" height="80" alt="${name}"/>
                    </figure>
                    <div class="ops-info-people">
                        <span class="ops-function">${post}</span>
                        <span class="ops-name">${name}</span>
                        <span class="ops-link">En savoir plus</span>
                    </div>
                </a>
            </li>
        </#list>
        </ul>
    </div>
</#if>