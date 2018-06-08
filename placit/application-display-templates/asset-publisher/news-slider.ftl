<!-- SLIDER NEWS -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />

<section class="pro-bloc-select">
    <div class="container">
        <div class="pro-intro">
            <h2>S’informer</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
                ullamco laboris nisi ut aliquip</p>
            <a href="${themeDisplay.getPortalURL()}${homeURL}actualites" class="pro-btn" title="Lien vers la page de toutes les actualités">Voir Toutes les actus</a>
        </div>

        <div class="row">

    		<!-- Parcours des entites de l'asset publisher -->
            <#list entries as curEntry>

            <!-- Recuperation de l'entite -->
             <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
             <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
             <#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />
             <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />

             <#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', curEntry.getAssetRenderer().getArticle().resourcePrimKey) >
             <#assign territoires = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "territoire") />

	            <div class="col-md-3 col-sm-6 col-xs-12">
	                <a href="${homeURL}actualites/-/entity/id/${curEntry.getAssetRenderer().getArticle().articleId}" class="pro-bloc-actu" title="Lien vers la page de détail de l'article">
	                    <div class="img">
	                        <figure role="group">
	                            <img src='${thumbnail}' alt="Image agenda" width="360" height="174" class="fit-cover"/>
	                        </figure>
	                        <span>
	                        	<#list territoires as t>
									${t.getTitle(locale)}<#sep>, </#sep>
								</#list>									
							</span>
	                    </div>
	                    <div class="content">
	                        <span class="publication">
                             Publié le ${curEntry.getAssetRenderer().getDisplayDate()?date}
	                        </span>
	                        <h3>${title}</h3>
	                        <p>${chapo?replace("<[^>]*>", "", "r")[0..*100]}...</p>
	                        <span class="link">Lire la suite</span>
	                    </div>
	                </a>
	            </div> 

            </#list>

        </div>
    </div>
</section>