<!-- Evénements du jour -->
<#setting locale = locale />
<section class="section schedule-section align-center">
  <div class="container">
    <div class="schedule">
      <div class="tab-content">
        <div class="day-x tab-content-schedule">
          <div class="panel-group">
            <#list entries as curEntry>
              <#assign entry = curEntry.getAssetRenderer().getEvent() />
                <div class="panel schedule-item">
                  <div class="lecture-icon-wrapper">
                    <!-- Image ou icône -->
                  </div>
                  <a data-toggle="collapse" href="#${entry.eventId}" class="schedule-item-toggle">
                    <strong class="time highlight"><span class="icon icon-office-24"></span>${entry.getEventPeriods()[0].getTimeDetail(locale)}</strong>
                    <h6 class="title">${entry.getTitle(locale)}<i class="icon icon-arrows-06"></i></h6>
                  </a>
                  <div id="${entry.eventId}" class="panel-collapse collapse <#if curEntry?index == 0> in </#if> schedule-item-body">
                    <article>
                      <p class="description">${entry.getDescription(locale)}</p>
                      <strong class="highlight speaker-name">${entry.getCity(locale)} - ${entry.getPlaceAlias(locale)}</strong>
                    </article>
                  </div>
                </div>
            </#list>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>