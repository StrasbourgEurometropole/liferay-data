<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${page.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<header class="mns-header" style="background: url(${page.expandoBridge.getAttribute('image')}) no-repeat center center /cover;">
    <div class="container mns-center">
        ${page.getDescription(locale)}
    </div>
</header>

<div class="container mns-wrapper-bread">
    <div class="mns-breadcrumbs">
        <#if !page.ancestors?has_content || page.ancestors?reverse[0].friendlyURL != '/accueil'>
            <a href="${homeURL}"><@liferay_ui.message key="home" /></a>
        </#if>
        <#list page.ancestors?reverse as ancestor>
            <a href="${homeURL}${ancestor.friendlyURL?remove_beginning('/')}">${ancestor.getName(locale)}</a>
        </#list>
        <span>${page.getName(locale)}</span>
    </div>
</div>

<style>
#mns-global .mns-nav .navbar-brand{
    background: url(/o/summer-2018-theme/images/logo-menu-big.png) no-repeat center center /contain;
    height: 186px;
}
#mns-global .mns-breadcrumbs {
    margin-top: 0;
}
</style>