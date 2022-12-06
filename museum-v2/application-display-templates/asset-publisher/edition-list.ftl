<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section id="editions" class="margin-bottom">
    <div  class="content container">
        <div class="infos">
            <h2><@liferay_ui.message key="eu.museum.editions" /></h2>
        </div>
        <#if entries?has_content>
            <div id="listEditions" class="list">
        	    <#list entries as curEntry>
                    <#assign edition = curEntry.getAssetRenderer().getEdition() />
                    <#assign detailURL = homeURL + "detail-edition/-/entity/id/" + edition.editionId />
                    <a href="${detailURL}" aria-label="${edition.getTitle(locale)}" title="${edition.getTitle(locale)}" class="edition-thumbnail-list">
                        <img src="${edition.getImageURL()}" alt="${edition.getTitle(locale)}" title="${edition.getTitle(locale)}" />
                        <div class="info">
                            <div class="title">
                                <span>${edition.getTitle(locale)}</span>
                            </div>
                            <div class="museums">
                                <span>${edition.getMuseumsLabel(locale)}</span>
                            </div>
                        </div>
                    </a>
                </#list>
            </div>
        </#if>
    </div>
</section>