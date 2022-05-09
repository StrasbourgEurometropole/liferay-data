<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${page.group.friendlyURL}" />
<#else>
    <#assign homeURL = "" />
</#if>
<header class="mns-header-single">
    <div class="container">
        <div class="mns-wrapper-bread">
            <div class="mns-breadcrumbs">
                 <#if !page.ancestors?has_content || page.ancestors?reverse[0].friendlyURL != '/accueil'>
                    <a href="${homeURL}/"><@liferay_ui.message key="home" /></a>
                </#if>
                <#list page.ancestors?reverse as ancestor>
                    <a href="${homeURL}${ancestor.friendlyURL}">${ancestor.getName(locale)}</a>
                </#list>
                <span>${page.getName(locale)}</span>
            </div>
        </div>
    </div>
</header>

<script>
    $('.navbar.mns-nav').addClass('mns-nav-no-header');
</script>