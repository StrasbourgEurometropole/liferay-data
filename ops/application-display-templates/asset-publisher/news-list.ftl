<!-- BLOC - LISTING DES ACTUALITES -->

<#if entries?size != 0 >

    <#-- Recuperation de la localisation de l'utilisateur -->
    <#setting locale = locale />

    <#-- Récupération de DateHelper pour le format date -->
    <#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />
    <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>

    <#-- Recuperation de l'URL de "base" du site -->
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
        <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
        <#assign homeURL = "/" />
    </#if>

    <section class="ops-bloc-listing-actus">

        <h2 class="ops-content-wrapper"><@liferay_ui.message key="eu.ops.general.news" /></h2>
        <span class="icon-ico-ops"></span>

        <div class="ops-col-wrapper">

            <div class="ops-col-50">

                <#-- Recuperation du premier element -->
                <#assign firstEntry = entries?first />

                <!-- Recuperation des informations du premier élément -->
                <#assign docXml = saxReaderUtil.read(firstEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                <#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />
                <#assign imageURL ="" />
                <#if thumbnail?has_content>
                    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(thumbnail) />
                </#if>

                <#-- Récupération de l'id du webcontent -->
                <#assign assetCategoryLocalServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"]>
                <#assign categoryList=assetCategoryLocalServiceUtil.getCategories("com.liferay.journal.model.JournalArticle",firstEntry.classPK) >

                <#-- Récupération de l'article -->
                <#assign firstJournal = firstEntry.getAssetRenderer().getArticle()>

                <a href="${homeURL}-/${firstJournal.urlTitle}" class="ops-actu ops-first-actu">
                    <figure class="fit-cover">
                        <img src="${imageURL}?imagePreview=1" width="530" height="353" alt="Image article"/>
                    </figure>
                    <div>
                        <div class="ops-meta-card-article">
                            <#--  <div class="ops-cats">
                                <#list categoryList as category>
                                    <span class="ops-cat">${category.getTitle(locale)}</span>
                                </#list>
                            </div>  -->
                            <span class="ops-date-article">
                                <@liferay_ui.message key="eu.ops.published.on" /> <time datetime="${firstJournal.displayDate?datetime?string('yyyy-MM-dd')}">${dateHelperService.displayShortDate(firstJournal.displayDate?date, locale)}</time>
                            </span>
                        </div>
                        <h3>${title}</h3>
                    </div>
                </a>

            </div>

            <div class="ops-col-50">
                <div class="ops-listing-actus">

                    <#list entries as curEntry>
                        <#if curEntry?counter gt 1 &&  curEntry?counter lt 5 >

                            <!-- Recuperation de l'entite -->
                            <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                            <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                            <#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />
                            <#assign imageURL ="" />
                            <#if thumbnail?has_content>
                                <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(thumbnail) />
                            </#if>

                            <#-- Récupération de l'id du webcontent -->
                            <#assign assetCategoryLocalServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"]>
                            <#assign categoryList=assetCategoryLocalServiceUtil.getCategories("com.liferay.journal.model.JournalArticle",curEntry.classPK)>

                            <#-- Récupération de l'article -->
                            <#assign journal = curEntry.getAssetRenderer().getArticle()>

                            <a href="${homeURL}-/${journal.urlTitle}" class="ops-actu">
                                <figure class="fit-cover">
                                    <img src="${imageURL}?imagePreview=1" width="200" height="130" alt="Image article"/>
                                </figure>
                                <div>
                                    <div class="ops-meta-card-article">
                                        <#--  <div class="ops-cats">
                                            <#list categoryList as categoryList>
                                                <span class="ops-cat">${categoryList.getName()}</span>
                                            </#list>
                                        </div>  -->
                                        <span class="ops-date-article">
                                            <@liferay_ui.message key="eu.ops.published.on" /> <time datetime="${journal.displayDate?datetime?string('yyyy-MM-dd')}">${dateHelperService.displayShortDate(journal.displayDate?date, locale)}</time>
                                        </span>
                                    </div>
                                    <h3>${title}</h3>
                                </div>
                            </a>

                        </#if>
                    </#list>
                    
                </div>
            </div>
        </div>

        <div class="ops-content-wrapper ops-aligncenter">
            <a href="${homeURL}magazine" class="ops-btn"><@liferay_ui.message key="eu.ops.see.all.news" /></a>
        </div>

    </section>

<#else>
    <#-- Permet d'accèder plus facilement à la conf de l'asset publisher -->
    <div style="height: 20px"></div>
</#if>