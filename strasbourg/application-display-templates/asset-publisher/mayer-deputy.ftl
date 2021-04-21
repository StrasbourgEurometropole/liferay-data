<!-- Maire de la commune -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="seu-wi seu-wi-trombinoscope">
    <#if entries?has_content>
        <#assign official = entries[0].assetRenderer.official />
        <div class="seu-container">
            <h2 class="seu-section-title">
                <span class="seu-title">
                    ${official.getName(official.fonctionTown, locale)} 
                    <@liferay_ui.message key="eu.official.of" /> ${official.getTown().getTitle(locale)}
                </span>
            </h2>
            <div class="seu-wi-content">
                <div class="seu-wi-text">
                <div class="seu-wi-title">${official.firstName} ${official.lastName}</div>
                </div>
            </div>
            <div class="seu-btn-line">
                <a href="${homeURL}elu/-/entity/id/${official.officialId}" class="seu-btn-square seu-bordered seu-core" title="<@liferay_ui.message key="learn-more" />">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text"><@liferay_ui.message key="learn-more" /></span>
                        <span class="seu-btn-arrow"></span>
                    </span>
                </a>
            </div>
        </div>
    </#if>
</div>
