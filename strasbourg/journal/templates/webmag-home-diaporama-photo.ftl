<!-- Webmag home - diaporama photo -->

<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign viewURL = homeURL + "-/" + .vars['reserved-article-url-title'].data />

<div class="hp-diapo" style="background-image: url(${image.getData()});" data-scroll-animation>
    <div class="hp-diapo__text">
        <h2 class="waved-title waved-title--small waved-title--t-white waved-title--w-transparent hp-diapo__sup-title">Diaporama photos</h2>
        <a href="${viewURL}" class="hp-diapo__single-link unstyled" style="background-image: url(${image.getData()});">
            <h3 class="hp-diapo__title">${title.getData()}</h3>
        </a>
        <a href="${homeURL}diaporama" class="a-btn-main h-inverted icon-right transparent hp-diapo__link">
            <span class="flexbox"><i class="mag mag-arrow-right"></i>
                <span class="btn-text">Tous les diaporamas</span>
            </span>
        </a>
    </div>
</div>