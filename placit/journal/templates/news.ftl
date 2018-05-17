<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />
<#assign layout = themeDisplay.getLayout() />


<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<@liferay_util["html-top"]>
    <meta property="og:title" content="${title.getData()?html}" />
    <meta property="og:description" content="${chapo.getData()?replace("<[^>]*>", "", "r")?html}" />
    <meta property="og:url" content="${currentUrl}" />
    <meta property="og:image" content="${themeDisplay.getPortalURL()}${thumbnail.getData()}" />
</@>

<article class="container pro-actu">
    <div class="col-md-10 col-md-offset-1 col-sm-12">
        <header>
            <span class="pro-time">Publiée le 
                <time datetime="2018-01-10">
                    ${.vars['reserved-article-display-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd MMMM yyyy")}
                </time>
            </span>
            <h1>${title.getData()}</h1>
            <div class="pro-meta">
                <span>${chapo.getData()}</span>
            </div>
            <#if image.getData()?has_content>
                <figure role="group">
                    <img src='${image.getData()}' alt="Image agenda" width="1160" height="593" class="fit-cover"/>
                    <figcaption>${image.getAttribute("alt")}</figcaption>
                </figure>
            </#if>
        </header>

        <div id="breadcrumb">
            <span>
                <span><a href="${homeURL}accueil">Accueil</a>
                    <a href="${homeURL}actualite">Toutes les actualités</a>
                    <span class="breadcrumb_last">${title.getData()}</span>
                </span>
            </span>
        </div>

        <div class="pro-content pro-bloc-texte col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
            ${content.getData()}
        </div>
    </div>
</article>