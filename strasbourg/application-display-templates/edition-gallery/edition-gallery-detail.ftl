<!-- Détail galerie d'éditions -->

<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign imageUrl = ""/>
<!-- image -->
<#if entry.imageURL?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getTitle(locale)?html}",
"og:description":'${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="seu-container wi-edition-detail">
    <a href="#" class="add-favorites"
        data-type="13" 
        data-title="${entry.getTitle(locale)}" 
        data-url="${themeDisplay.getPortalURL()}${homeURL}galerie-editions/-/entity/id/${entry.galleryId}"
        data-id="${entry.galleryId}">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
    <h1>${entry.getTitle(locale)}</h1>
    <div class="rte">
        <div class="text-center">
            <img src="${entry.imageURL}" title="${entry.getTitle(locale)}" />
        </div>
        <h2><@liferay_ui.message key="description" /></h2>
        ${entry.getDescription(locale)}

        <div>
            <strong>Nombre d'éditions : ${entry.publishedEditions?size}</strong>
        </div>
    </div>


    <#if entry.publishedEditions?has_content>
        <#list entry.publishedEditions?reverse as edition>
            <div class="wi-search-result wi-edition-thumbnail">
                <div class="seu-result-left seu-result-thumbnail">
                    <a href="${homeURL}edition/-/entity/id/${edition.editionId}" title="${edition.getTitle(locale)}">
                        <div class="thumbnail-background" style="background-image: url(${edition.imageURL});"></div>
                    </a>
                </div>
                <div class="seu-result-right">
                    <a class="seu-result-content" href="${homeURL}edition/-/entity/id/${edition.editionId}" title="${edition.getTitle(locale)}">
                        <h2 class="seu-result-title">${edition.getTitle(locale)}</h2>
                        <div class="seu-result-catcher">
                            ${edition.getDescription(locale)?replace("<[^>]*>", "", "r")[0..*100]}...
                        </div>
                        <#if edition.getTypes()?has_content>
                            <div class="seu-result-category">
                                <#list edition.getTypes() as type>
                                    ${type.getTitle(locale)}
                                    <#sep>, </#sep>
                                </#list>
                            </div>
                        </#if>
                    </a>
                    <div class="seu-result-infos">
                        <div class="seu-result-infos-top">
                            <#if edition.getDiffusionDateMonth()?has_content>
                                ${edition.getDiffusionDateMonth()} /
                            </#if> ${edition.getDiffusionDateYear()}
                        </div>
                        <div class="seu-result-infos-bottom"> 
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </#if>
</div>