<!-- Entête liste des actes réglementaires et normatifs -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<main class="seu-container">
	<a href="#" class="add-favorites"
        data-type="9" 
        data-title="${layout.getName(locale)}" 
        data-url="${homeURL}${layout.friendlyURL?remove_beginning('/')}" 
        data-id="0">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
	</a>
    <h1>${layout.getName(locale)}</h1>
    <div class="hat">
        <div></div>
    </div>
    <div class="rte">
        <p>${layout.getDescription(locale)}</p>
    </div>
</main>

<style>
    .page-header{
        padding-bottom: 0;
    }
</style>