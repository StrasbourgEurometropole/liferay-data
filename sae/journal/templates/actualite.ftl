<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign request = serviceContext.getRequest()/>

<#assign imageUrl = ""/>
<!-- image -->
<#if illustration.getData()?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + illustration.getData()?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${title.getData()?html}",
"og:description":'${text.getData()?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}  

<style>
.class-inner .page-header {
  background-image: url(${illustration.getData()}) !important;
}
</style>

<div class="container">
	<h1>${title.getData()}</h1>
	<p>${text.getData()}</p>				
</div>