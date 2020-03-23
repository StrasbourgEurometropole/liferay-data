<#assign originalLocale = locale>
<#setting locale = localeUtil.getDefault()>
<#assign displaydate = .vars['reserved-article-display-date'].data>
<#assign displaydate = displaydate?date("EEE, d MMM yyyy HH:mm:ss Z")>
<#setting date_format = "d MMMM yyyy">
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
"og:description":'${text.data?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<!-- Détail actualité -->
<div class="container mns-actu-detail mns-fck">
    <div class="mns-top-detail-img">
        <img src="${image.getData()}" alt="${title.data}" />
    </div>
    <span class="publication"><@liferay_ui.message key="eu.published-on" /> ${displaydate}</span>
    <div class="mns-detail-actu-content col-xs-12">
        <div class="mns-title">
            <h1>${title.data}</h1>
        </div>
        <span class="mns-line"></span>
        ${text.data}
    </div>
</div>
