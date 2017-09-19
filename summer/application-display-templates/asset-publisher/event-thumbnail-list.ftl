<!-- Liste de vignettes événements -->
<#setting locale = locale />
<div class="portlet-cus-event-asset-fo">
  <div class="agenda">
    <div class="list-evt">
      <#list entries as curEntry>
        <#assign entry = curEntry.getAssetRenderer().getEvent() />

        <!-- Event : ${entry.getTitle(locale)} -->
       <div class="event portlet-event-item" headers="ishh_col-1">
        <div class="entry-image"> 
          <a href="/web/ete/detail-agenda/-/entity/id/${entry.getEventId()}" title="${entry.getTitle(locale)}">
            <img src="${entry.getImageURL()}" alt="">
          </a>
        </div>
        <div class="entry-header">
          <span class="category">
            <#assign categories = entry.getTypeLabel(locale) + " - " + entry.getThemeLabel(locale) />
            <#if categories?length < 100>
              ${categories}
            <#else>
              ${categories?substring(0,100)}...
            </#if>
            </span>
          <h2>
            <a href="/web/ete/detail-agenda/-/entity/id/${entry.getEventId()}" title="${entry.getTitle(locale)}">
              ${entry.getTitle(locale)}
            </a>
          </h2>
          <span class="date">${entry.getEventScheduleDisplay(locale)}</span>
          <div class="place"> ${entry.getCity(locale)} - ${entry.getPlaceAlias(locale)}</div>
        </div>
        <footer class="entry-meta"> 
          <time></time>
          <a href="/web/ete/detail-agenda/-/entity/id/${entry.getEventId()}" title="<@liferay_ui.message key="read-more" />" class="btn-more">
            <@liferay_ui.message key="read-more" />
          </a>
          <div class="clearfix"></div>
        </footer> 
      </div>
      </#list>
    </div>
  </div>
</div>