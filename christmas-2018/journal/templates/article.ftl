<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<!-- Détail article -->
<div class="mns-fck text-center container mns-p50">
    <a href="#" class="add-favorites"
        data-type="7" 
        data-title="${title.data}" 
        data-url="${currentUrl}" 
        data-group-id=${themeDisplay.scopeGroupId}
        data-id="${.vars['reserved-article-id'].data}">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
    <div class="text-center">
	    ${catcher.data}
	</div>
    ${text.data}
</div>


