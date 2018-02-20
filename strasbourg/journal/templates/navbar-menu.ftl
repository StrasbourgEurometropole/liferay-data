<#if menuNavigation.getSiblings()?has_content>
    <#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
    <#assign themeDisplay = serviceContext.getThemeDisplay() />
    <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
    <#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />
    <ul id="account-menu" class="unstyled" style="display: none;">
        <#list menuNavigation.getSiblings() as menu>
            <li><a id="menu-item-${menu?index}" href="${menu.link.getData()}" class="account-link ${menu.classMenu.getData()}">${menu.getData()}</a></li>
        </#list>
        <li><a href="${layoutHelper.getPublikLogoutURL(currentUrl)}" class="account-link logout">Se déconnecter</a></li>
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