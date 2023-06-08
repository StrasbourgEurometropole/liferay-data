<!-- Webmag - breve -->
<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign request = serviceContext.getRequest()/>

<#assign imageUrl = ""/>
<!-- image -->
<#if image.getData()?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + image.getData()?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${title.getData()?html}",
"og:description":'${chapo.getData()?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

<main class="seu-container" style="margin-bottom: 50px">
    <div class="detail-line">
        <div class="filler"></div>
        <p class="seu-published">
            <@liferay_ui.message key="eu.published-on" /> 
            ${.vars['reserved-article-display-date'].getData()?date("EEE, dd MMM yyyy hh:mm:ss Z")?string["dd/MM/yyyy"]} 
           - <@liferay_ui.message key="eu.modified-on" /> 
            ${.vars['reserved-article-modified-date'].getData()?date("EEE, dd MMM yyyy hh:mm:ss Z")?string["dd/MM/yyyy"]} 
        </p>
    </div>
    <h1>
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
</main>
<style>
.search-asset-portlet, .page-header {
    display: none !important;
}
</style>