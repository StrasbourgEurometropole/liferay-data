<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />
<#assign request = serviceContext.getRequest()/>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:description":'${catcher.data?replace("<[^>]*>", "", "r")?html}'
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<!-- Détail article -->
<div class="mns-fck container mns-p50">
    <a href="#" class="add-favorites"
        data-type="7" 
        data-title="${title.data}" 
        data-url="${currentUrl}" 
        data-group-id=${themeDisplay.scopeGroupId}
        data-id="${.vars['reserved-article-id'].data}">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
    <div class="text-center">
        <p>${catcher.data}</p>
	</div>
    ${text.data}
</div>


