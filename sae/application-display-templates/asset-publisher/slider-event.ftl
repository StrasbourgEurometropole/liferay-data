<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<div class="portlet-agenda">
    <div class="agenda-body agenda-carousel owl-agenda owl-carousel owl-theme">  
        <#list entries as curEntry>
            <#assign entry = curEntry.getAssetRenderer().getEvent() />

            <div class="event-content"> 
                <a href="${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}">
                    <img class="event-image" src="${entry.getImageURL()}" />
                </a>
                <div class="event-meta"> 
                    <span class="event-category">${entry.getThemeLabel(locale)}</span>
                    <span class="event-date">
                        <a href="${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}">
                            ${entry.getEventScheduleDisplay(locale)}
                        </a>
                    </span>
                    <a class="btn-tps-forts" href="${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}">
                        <h4>${entry.getTitle(locale)}</h4> 
                    </a>
                </div>
            </div>
        </#list>
    </div>
    <#if agendaURL?has_content && agendaURLText?has_content>
    <div class="link-all"><a href="${agendaURL}">${agendaURLText}</a></div>
    </#if>
</div>