<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign request = serviceContext.getRequest()/>
<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:description":'${content.getData()?replace("<[^>]*>", "", "r")?html}'
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

<div class="container">
<h1>${title.getData()}</h1>
  ${content.getData()}
</div>

<style>
.class-inner .page-header {
  background-image: url(${backgroundPicture.getData()}) !important;
}
</style>