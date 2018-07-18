<!-- VIGNETTE EVENEMENT -->

<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<a href="${homeURL}detail-evenement/-/entity/id/${entry.eventId}" title="lien de la page" class="item pro-bloc-card-event"
    <#if entry.getMercatorY()?has_content >
        data-lat="${entry.getMercatorY()}" 
        data-lng="${entry.getMercatorX()}"
    </#if>
    >
    <div>
        <div class="pro-header-event">
            <span class="pro-ico"><span class="icon-ico-debat"></span></span>
            <span class="pro-time"><time datetime="2018-01-10">${entry.getEventScheduleDisplay(locale)}</time></span>
            <p>À : ${entry.getPlaceAlias(locale)}</p>
            <h3>${entry.getTitle(locale)}</h3>
        </div>
        <div class="pro-footer-event">
            <span class="pro-number"><strong>${entry.getNbEventParticipations()}</strong> Participant(s)</span>
        </div>
    </div>
</a>