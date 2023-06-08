<!-- VIGNETTE DE WEB CONTENTS POUR LA RECHERCHE GLOBALE -->

<#-- Chargement de la variable de localisation -->
<#setting locale = locale />

<#-- Récupération de DateHelper pour le format date -->
<#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />

<#-- Récupération de l'XML du contenu web -->
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />

<#-- Champs concernant une actualité -->
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
<#if !title?has_content><#assign title = entry.getTitle(locale) /></#if>
<#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
<#assign thumbnailURL ="" />
<#if thumbnail?has_content>
    <#assign thumbnailURL = assetPublisherTemplateHelperService.getDocumentUrl(thumbnail) />
</#if>


<#-- Champs concernant un membre -->
<#assign name = docXml.valueOf("//dynamic-element[@name='Name']/dynamic-content/text()")/>
<#assign post = docXml.valueOf("//dynamic-element[@name='post']/dynamic-content/text()")/>
<#assign bigImage = docXml.valueOf("//dynamic-element[@name='bigImage']/dynamic-content/text()")/>
<#assign bigImageURL ="" />
<#if bigImage?has_content>
    <#assign bigImageURL = assetPublisherTemplateHelperService.getDocumentUrl(bigImage) />
</#if>

<#-- Champs concernant une offre -->
<#assign postName = docXml.valueOf("//dynamic-element[@name='postName']/dynamic-content/text()")/>
<#assign contestDate = docXml.valueOf("//dynamic-element[@name='date']/dynamic-content/text()")/>

<#-- Création de l'URL de détail -->
<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />

<#if title?has_content && thumbnailURL?has_content && !contestDate?has_content>
    <!-- VIGNETTE ACTUALITE -->
    <#-- Récupération de la Typologie -->
    <#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
    <#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
    <#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', entry.resourcePrimKey) >
    <#assign typologiesList = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "typologie") />

    <div class="ops-col-33">
        <a href="${detailURL}" class="ops-card ops-card-article">
            <div>
                <figure class="fit-cover">
                    <img src="${thumbnailURL}" width="390" height="360" alt="Image article"/>
                </figure>
                <div class="ops-content-card-actu">
                    <div class="ops-meta-card-article">
                        <div class="ops-cats">
                            <#list typologiesList as typology>
                                <span class="ops-cat">${typology.getTitle(locale)}</span>
                            </#list>
                        </div>
                        <span class="ops-date-article">
                            <@liferay_ui.message key="eu.ops.published.on" /> <time datetime="${entry.getModifiedDate()?datetime?string('yyyy-MM-dd')}">${dateHelperService.displayShortDate(entry.getModifiedDate()?date, locale)}</time>
                        </span>
                    </div>
                    <h3>${title}</h3>
                    <span class="ops-link"><@liferay_ui.message key="eu.ops.discover" /></span>
                </div>
            </div>
        </a>
    </div>
<#elseif name?has_content && post?has_content && detailURL?has_content>
    <!-- VIGNETTE MEMBRE -->
    <div class="ops-col-33">
        <a href="${detailURL}" class="ops-card ops-card-member">
            <div>
                <#if bigImageURL?has_content>
                    <figure class="fit-cover">
                        <img src="${bigImageURL}" width="390" height="560" alt="Image membre"/>
                    </figure>
                <#else>
                    <div class="ops-no-photo"></div>
                </#if>
                <div class="ops-caption">
                    <h3>${name}</h3>
                    <span class="ops-function">${post}</span>
                </div>
            </div>
        </a>
    </div>
<#elseif title?has_content && contestDate?has_content>
    <!-- VIGNETTE OFFRE -->
    <div class="ops-col-33">
        <a href="${detailURL}" class="ops-card ops-card-emploi">
            <div>
                <span class="ops-date-concours">
                    <@liferay_ui.message key="eu.ops.contest.date" /> : <time datetime="${contestDate}">${dateHelperService.displayShortDate(contestDate?date.xs, locale)}</time>
                </span>
                <h3>${title}</h3>
                <span class="ops-link"><@liferay_ui.message key="eu.ops.learn.more" /></span>
            </div>
        </a>
    </div>
</#if>