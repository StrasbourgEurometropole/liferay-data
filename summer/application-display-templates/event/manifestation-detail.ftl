<!-- Détail manifestation -->
<#setting locale = locale />

<div class="portlet-cus-event-asset-fo">
  <div class="details">

    <div class="event-description">
      <div class="event-info-block">
        <h2 class="h2-title f-left">${entry.getTitle(locale)}</h2>
        <div class="clearfix"></div>
        
        <p class="event-themes">
          ${entry.getTypeLabel(locale)} - ${entry.getThemeLabel(locale)}
        </p>
        
        <div class="event-info">
          <div class="f-left">
            <img src="${entry.getImageURL()}" alt="${entry.getTitle(locale)}" />
            <#if entry.getImageCopyright(locale)?has_content>
              <span class="copyright">
                  &copy; ${entry.getImageCopyright(locale)}
              </span>
            </#if>
          </div>
        
          <div class="f-right">
            <div class="date">
              ${entry.getManifestationScheduleDisplay(locale)}
            </div>
            <p class="terms">
              <#if entry.getPublics()?has_content>
                <@liferay_ui.message key="for-public" /> : ${entry.getPublicLabel(locale)}
              </#if>
                
            </p>
          </div>
          <div class="clearfix"></div>  
        </div>
      </div>
    </div>

    <div class="event-description">
      <div class="entry-content ck-editor-content">
        <div class="event-info-block">
          <h3 class="title-uppercase"><@liferay_ui.message key="eu.presentation" /></h3>
          
          <div class="ck-editor-content">
            <p>${entry.getDescription(locale)}</p>
          </div>        
        </div>


      <!-- Evénements de la manifestation -->
      <#if entry.getPublishedEvents()?has_content>
        <div class="event-info-block agenda">
          <h3 class="title-uppercase"><@liferay_ui["message"] key="eu.events" /></h3>
          <#list entry.getPublishedEvents() as event>
            <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
              <@liferay_portlet.param name="classPK" value="${event.getEventId()}" />
              <@liferay_portlet.param name="returnURL" value="${currentURL}" />
            </@liferay_portlet.renderURL>
            <div class="event portlet-event-item" style="font-size: 10px">
              <div class="entry-image"> 
                <a href="${detailURL}" title="${event.getTitle(locale)}"">
                  <img src="${event.getImageURL()}" alt="">
                </a>
              </div>
              <div class="entry-header">
                <span class="category">${event.getTypeLabel(locale)} - ${event.getThemeLabel(locale)}</span>
                <h2>
                  <a href="${detailURL}" title="L’Elsässer Owe">
                    ${event.getTitle(locale)}
                  </a>
                </h2>
                <span class="date">${event.getEventScheduleDisplay(locale)}</span>
                <div class="place"> ${event.getCity(locale)} - ${event.getPlaceAlias(locale)}</div>
              </div>
              <footer class="entry-meta"> 
                <time></time>
                <a href="${detailURL}" title="<@liferay_ui.message key="read-more" />" class="btn-more">
                  <@liferay_ui.message key="read-more" />
                </a>
                <div class="clearfix"></div>
              </footer> 
            </div>
          </#list>
        </div>
      </#if>

    </div>

  </div>
</div>
      