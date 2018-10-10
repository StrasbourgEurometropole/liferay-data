<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${page.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="mns-page-experience">
    <div class="mns-header mns-header-fullpage">
        <figure class="fit-cover">
                <img src="${page.expandoBridge.getAttribute('image')}" width="1600" height="900" alt="Détail d'une expérience">
            </figure>
            <div class="container mns-caption">
                ${page.getDescription(locale)}
            </div>
        <div class="small-container mns-wrapper-bread">
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
    </div>
</div>