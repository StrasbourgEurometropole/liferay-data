<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${themeDisplay.layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<main class="seu-container" style="margin-bottom: 50px">
    <a href="#" class="add-favorites"
            data-type="7" 
            data-title="${title.getData()}" 
            data-url="${homeURL}${themeDisplay.layout.friendlyURL?remove_beginning('/')}" 
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