<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />
<#assign imageUrl = themeDisplay.getPortalURL() + layout.expandoBridge.getAttribute('image') />
<#assign layout = themeDisplay.getLayout() />

<@liferay_util["html-top"]>
    <meta property="og:title" content="${title.getData()?html}" />
    <meta property="og:description" content="${chapo.getData()?replace("<[^>]*>", "", "r")?html}" />
    <meta property="og:url" content="${currentUrl}" />
    <meta property="og:image" />
    <meta property="og:image:height" />
    <meta property="og:image:width" />
</@>

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

<script> 
    function getMeta(url, callback) {
        var img = new Image();
        img.src = url;
        img.onload = function() { callback(this.width, this.height); }
    }
    
    getMeta("${imageUrl}", function(width, height) { 
        document.querySelector('[property="og:image"]').setAttribute("content", "${imageUrl}");
        document.querySelector('[property="og:image:width"]').setAttribute("content", width);
        document.querySelector('[property="og:image:height"]').setAttribute("content", height);
    });

</script>