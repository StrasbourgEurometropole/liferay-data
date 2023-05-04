<!-- VIGNETTE EVENEMENT GLOBAL SEARCH -->

<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#-- Récupération du service gestionnaire -->
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
<#assign asset = entry.getAssetEntry() />
<#assign managerList = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "service gestionnaire") />

<#assign showEntry = false />

<#list managerList as manager>
    <#if manager.getName() == "Orchestre Philharmonique" >
        <#assign showEntry = true />
    </#if>
</#list>

<#if showEntry >
    <div class="ops-col-33">
        <a href="${homeURL}detail-evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}" class="ops-card ops-card-concert">
            <div>
                <time>
                    ${entry.getEventScheduleDisplay(locale, false, true)}
                </time>
                <div class="ops-next-date" style="height:12px">
                    
                </div>
                <h3>${entry.getTitle(locale)}</h3>
                <div class="ops-img">
                    <figure class="fit-cover">
                        <#if entry.imageURL?has_content>
                            <img src='${entry.imageURL}' width="297" height="153" class="fit-cover" alt="${entry.getTitle(locale)}"/>
                        </#if>
                    </figure>
                    <span class="ops-typologie">${entry.getLabelTypologies(locale)}</span>
                </div>
                <div class="ops-content">
                    <span class="ops-songs"><strong>${entry.getComposer()}</strong></span>
                    <span class="ops-names">${entry.getDistribution(locale)}</span>
                </div>
            </div>
        </a>
    </div>
</#if>