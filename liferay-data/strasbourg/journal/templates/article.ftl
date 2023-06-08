<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign request = serviceContext.getRequest()/>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:description":'${chapo.getData()?replace("<[^>]*>", "", "r")?html}'
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<main class="seu-container" style="margin-bottom: 50px">
    <a href="#" class="add-favorites"
            data-type="7" 
            data-title="${title.getData()}" 
            data-url="${currentUrl}" 
            data-group-id=${themeDisplay.scopeGroupId}
            data-id="${.vars['reserved-article-id'].data}">
            <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
    <h1>${title.getData()}</h1>
    <div class="hat">
        <div>
            ${chapo.getData()}
        </div>
    </div>
    <div class="rte">
        ${content.getData()}
    </div>
</main>