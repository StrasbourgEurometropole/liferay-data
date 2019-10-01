<!-- Vignette offre d'emploi -->

<#if entries?size != 0 >

    <!-- Chargement de la variable de localisation -->
    <#setting locale = locale />

    <#-- Création de l'URL de détail -->
    <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />

    <!-- WRAPPER offre d'emploi -->
    <div class="ops-listing-recrutement">
        <!-- Groupe d'emplois -->
        <div class="ops-col-wrapper ops-col-wrapper-large" data-egalize=".ops-card-emploi > div">

        <#list entries as entry>

            <#-- Récupération des champs du contenu web -->
            <#assign renderer  = entry.getAssetRenderer() />
            <#assign journalArticle   = renderer.getArticle() />
            <#assign docXml = saxReaderUtil.read(journalArticle.getContentByLocale(locale)) />
            <#assign rootElement = docXml.getRootElement() />
            <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
            <#assign detailURL = layoutHelper.getJournalArticleLayoutURL(journalArticle.groupId, journalArticle.articleId, themeDisplay) />

            <#list rootElement.elements() as ele >
                <#if "date" == ele.attributeValue("name") && ele.element("dynamic-content").getData()?has_content>
                    <#assign date = dateUtil.parseDate("yyyy-MM-dd", ele.element("dynamic-content").getData(), locale)/>                  
                </#if>
            </#list>
            <div class="ops-col-50">
                <a href="${detailURL}" class="ops-card ops-card-emploi">
                    <div>
                        <#if date?has_content>
                            <span class="ops-date-concours"><@liferay_ui.message key="eu.ops.contest.date" /> : ${date?string("dd.MM.yyyy")}</span>
                        </#if>
                        <h3>${title}</h3>
                        <span class="ops-link"><@liferay_ui.message key="eu.ops.learn.more" /></span>
                    </div>
                </a>
            </div>
        </#list>
        </div>
    </div>
</#if>