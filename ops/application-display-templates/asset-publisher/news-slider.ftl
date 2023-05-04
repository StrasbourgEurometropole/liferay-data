<!-- SLIDER ACTUALITE -->

<#if entries?size != 0 >

    <!-- Recuperation de la localisation de l'utilisateur -->
    <#setting locale = locale />

    <!-- Recuperation de l'URL de "base" du site -->
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
        <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
        <#assign homeURL = "/" />
    </#if>
    <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>

    <header class="ops-fullpage-header" data-vheight="100">	
        <div class="slick-carousel slick-basic-slider">

            <!-- Parcours des entites de l'asset publisher -->
            <#list entries as curEntry>

                <!-- Recuperation de l'entite -->
                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                <#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />
                <#assign imageURL ="" />
                <#if thumbnail?has_content>
                    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(thumbnail) />
                </#if>

                <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />

                <#-- Récupération de l'id du webcontent -->
                <#assign assetCategoryLocalServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"]>
                <#assign categoryList=assetCategoryLocalServiceUtil.getCategories("com.liferay.journal.model.JournalArticle",curEntry.classPK) >

                <div class="ops-item">
                    <figure class="fit-cover">
                        <img src="${imageURL}" width="1600" height="900" alt="Image article"/>
                    </figure>
                    <div class="ops-content-wrapper ops-content-wrapper-large ops-caption">
                        <span class="ops-typologie">
                            <#list categoryList as category>${category.getTitle(locale)}<#sep>, </#list>
                        </span>
                        <h1>${title}</h1>
                        <#if chapo?has_content>
                            <p>${chapo?replace("<[^>]*>", "", "r")[0..*100]}...</p>
                        </#if>
                        <a href="${homeURL}-/${curEntry.getAssetRenderer().getArticle().urlTitle}" class="ops-btn">
                            <@liferay_ui.message key="eu.ops.discover" />
                        </a>
                    </div>
                </div>

            </#list>

        </div>		
        <div class="ops-slick-nav"></div>
    </header>

<#else>
    <#-- Permet d'accèder plus facilement à la conf de l'asset publisher -->
    <div style="height: 20px"></div>
</#if>