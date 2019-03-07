<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />
<#assign imageUrl = themeDisplay.getPortalURL() + image.getData() />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<@liferay_util["html-top"]>
    <meta property="og:title" content="${title.getData()?html}" />
    <meta property="og:description" content="${chapo.getData()?replace("<[^>]*>", "", "r")?html}" />
    <meta property="og:url" content="${currentUrl}" />
    <meta property="og:image" />
    <meta property="og:image:height" />
    <meta property="og:image:width" />
</@>

<main class="smag" style="padding-top: 105px !important;">
    <div class="smag-container">
        <div class="detail-line" style="justify-content: normal">
            <div class="filler"></div>
            <p class="seu-published">
            <@liferay_ui.message key="eu.published-on" /> ${.vars['reserved-article-display-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")} 
            - <@liferay_ui.message key="eu.modified-on" /> ${.vars['reserved-article-modified-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")}
            </p>
        </div>
        <h1 style="width: 100%">
            ${title.getData()}
        </h1>
        <div class="hat">
            <div>
                ${chapo.getData()}
            </div>
        </div>
        <div class="rte">
            ${content.getData()}
        </div>
    </div>
</main>
<#if panoramique.getData()?has_content>
    <style>
        .bg-banner {
            background-image: url(${panoramique.getData()}) !important;
        }
    </style>
</#if>
<style>
.search-asset-portlet, .page-header {
    display: none !important;
}
</style>

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