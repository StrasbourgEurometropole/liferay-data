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
"og:title":"${title.data?html}",
"og:description":'${text.data?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<!-- Détail actualité -->
<div class="small-container mns-actu-detail mns-fck">
    <div class="mns-top-detail-img">
        <img src="${image.getData()}" alt="${title.data}" width="930" height="620" />
    </div>
    <div class="mns-detail-actu-content col-sm-9 col-sm-offset-3 col-xs-12">
        <h1>${title.data}</h1>
        <span class="publication"><@liferay_ui.message key="eu.published-on" /> ${displaydate}</span>
        <span class="mns-line"></span>
        ${text.data}
    </div>
</div>

<style>
    .page-header-portlet .mns-header-single h1,
    .page-header-portlet .mns-header-standard h1 {
        display: none;
    }
</style>
<script>
    $('.mns-header-standard').attr('class', 'mns-header-single');
</script>