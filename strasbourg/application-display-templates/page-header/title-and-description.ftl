<!-- Header titre et description -->
<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />
<main class="seu-container">
	<a href="#" class="add-favorites"
        data-type="9" 
        data-title="${layout.getName(locale)}" 
        data-url="${currentUrl}" 
        data-id="0">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
	</a>
    <h1>${layout.getName(locale)}</h1>
    <div class="hat">
        <p>${layout.getDescription(locale)}</p>
    </div>
</main>
