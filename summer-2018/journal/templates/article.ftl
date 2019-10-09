<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign request = serviceContext.getRequest()/>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:description":'${catcher.data?replace("<[^>]*>", "", "r")?html}'
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<!-- Détail article -->
<div class="mns-fck mns-bloc-article container mns-p50">
    <div>
    	<div class="mns-wrapper-title">
	        <h1 class="mns-title">
	            ${title.data}
	        </h1>
        </div>
        <div class="mns-catcher">
            ${catcher.data}
        </div>
    </div>
    ${text.data}
</div>