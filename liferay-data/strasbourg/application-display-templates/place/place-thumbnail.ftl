<!-- Vignette lieu -->

<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="wi-search-result wi-place-thumbnail">
    <div class="seu-result-left">
        <div class="seu-result-icon"></div>
    </div>
    <div class="seu-result-right">
        <a class="seu-result-content" href="${homeURL}lieu/-/entity/sig/${entry.getSIGid()}/${entry.getNormalizedAlias(locale)}">
            <h2 class="seu-result-title">${entry.getAlias(locale)}</h2>
            <div class="seu-result-catcher">${entry.getTypeLabel(locale)}</div>
        </a>
        <div class="seu-result-infos">
            <div class="seu-result-infos-top">
                ${entry.getCity(locale)}
            </div>
            <div class="seu-result-infos-bottom">
                <a href="#" class="seu-add-favorites"
                    data-type="1" 
                    data-title="${entry.getAlias(locale)}" 
                    data-url="${themeDisplay.getPortalURL()}${homeURL}lieu/-/entity/sig/${entry.getSIGid()}/${entry.getNormalizedAlias(locale)}" 
                    data-id="${entry.placeId}">
                    <span><@liferay_ui.message key='eu.add-to-favorite' /></span>
                </a>
            </div>
        </div>
    </div>

</div>
