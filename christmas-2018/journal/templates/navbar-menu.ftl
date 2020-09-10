<#if menuNavigation.getSiblings()?has_content>
    <#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
    <#assign themeDisplay = serviceContext.getThemeDisplay() />
    <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
    <#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />
    <ul id="inline-account-menu" class="unstyled"> 
        <#list menuNavigation.getSiblings() as menu>
            <li><a id="menu-item-${menu?index}" href="${menu.link.getData()}" class="account-link ${menu.classMenu.getData()}"  <#if menu.newWindow.data == "true">target="_blank"</#if> title="${menu.data} <#if menu.newWindow.data == "true">(nouvelle fenêtre)</#if>"></a></li>
        </#list>
        <li><a href="${layoutHelper.getPublikLogoutURL(currentUrl)?html}" title="Se déconnecter" class="account-link logout"></a></li>
    </ul>
    <ul id="account-menu" class="unstyled" style="display: none;">
        <#list menuNavigation.getSiblings() as menu>
            <li><a id="menu-item-${menu?index}" href="${menu.link.getData()}" class="account-link ${menu.classMenu.getData()}"  <#if menu.newWindow.data == "true">target="_blank"</#if> title="${menu.data} <#if menu.newWindow.data == "true">(nouvelle fenêtre)</#if>">${menu.getData()}</a></li>
        </#list>
        <li><a href="${layoutHelper.getPublikLogoutURL(currentUrl)?html}" title="Se déconnecter" class="account-link logout">Se déconnecter</a></li>
    </ul>
</#if>
<#if menuNavigation.getSiblings()?has_content>
    <style>
        <#list menuNavigation.getSiblings() as menu>
            <#if !menu.classMenu.getData()?has_content >
                #menu-item-${menu?index}:before { 
                    background-image: url(${menu.illustration.getData()}); 
                }
            </#if>
        </#list>
    </style>
</#if>