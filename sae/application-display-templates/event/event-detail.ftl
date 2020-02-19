<!-- Détail événement -->
<#setting locale = locale />

<#assign imageUrl = ""/>
<!-- image -->
<#if entry.imageURL?has_content>
    <#if !entry.imageURL?contains('http')>
        <#assign imageUrl = themeDisplay.getPortalURL() />
    </#if>
    <#assign imageUrl = imageUrl + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getEventScheduleDisplay(locale)} - ${entry.getTitle(locale)?html}",
"og:description":'${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="portlet-cus-event-asset-fo">
  <div class="details">

    <div class="event-description">
      <div class="event-info-block">
        <h2 class="h2-title f-left">${entry.getTitle(locale)}</h2>
        <div class="clearfix"></div>
        <span class="event-subtitle">${entry.getSubtitle(locale)}</span>
        
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
              ${entry.getEventScheduleDisplay(locale)}
            </div>
            <p class="terms">
              <#if entry.getPublics()?has_content>
                <@liferay_ui.message key="for-public" /> : ${entry.getPublicLabel(locale)}
              </#if>
                
            </p>
            <div class="place">
              ${entry.getPlaceAlias(locale)}
            </div>
            <address>
              ${entry.getPlaceAddressHTML(locale)}
            </address>
          </div>
          <div class="clearfix"></div>  
        </div>
      </div>
    </div>

    <div class="event-info-block event-date-block">
      <h3 id="horaires">
        <@liferay_ui.message key="eu.dates-and-times" />
      </h3>
      <div class="ck-editor-content">
        <ul>
          <#list entry.eventPeriods as period>
            <li>${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> : ${period.getTimeDetail(locale)}</#if></li>
          </#list>
        </ul>
      </div>
    </div>

    <div class="event-description">
      <div class="entry-content ck-editor-content">
        <div class="event-info-block">
          <h3 class="title-uppercase"><@liferay_ui.message key="eu.presentation" /></h3>
          
          <div class="ck-editor-content">
            <p>${entry.getDescription(locale)}</p>
          </div>
          <p>           
            <#if entry.getPromoter()?has_content>
              <br>
              <strong><@liferay_ui.message key="eu.organized-by" /> : ${entry.getPromoter()}</strong>
            </#if> 
          </p>
        
        </div>

        <div class="col-50">

          <#if entry.free == 1 || entry.getPrice(locale)?has_content>
            <div class="event-info-block">
              <h4><@liferay_ui.message key="eu.event-prices" /></h4>
              <#if entry.free == 1>
                <div class="ck-editor-content"><p><@liferay_ui.message key="eu.free-event" /></p></div>
              </#if>
              <div class="ck-editor-content"><p>${entry.getPrice(locale)}</p></div>
            </div>
          </#if>

          <#if entry.getPhone()?has_content || entry.getWebsiteURL(locale)?has_content>
            <div class="event-info-block">
              <h4 class="no-top-margin">
                <@liferay_ui.message key="contact" />
              </h4>
              <div class="ck-editor-content">
                <ul>
                  <#if entry.getPhone()?has_content>
                    <li><@liferay_ui.message key="phone" /> : ${entry.getPhone()}</li>
                  </#if>
                  <#if entry.getWebsiteURL(locale)?has_content>
                    <li>
                      <@liferay_ui.message key="site" /> : 
                        <a href="${entry.getWebsiteURL(locale)}" title="${entry.getWebsiteName(locale)} (<@liferay_ui.message key='eu.new-window' />)" target="_blank">
                          ${entry.getWebsiteName(locale)}
                        </a>
                   </li>
                  </#if>
                </ul>
              </div>
           </div>
          </#if>

        </div>

        <div class="col-50">
          <#if entry.getAccess(locale)?has_content >
            <div class="event-info-block">
              <h4 class="no-top-margin event-access-mode"><@liferay_ui.message key="eu.access-and-services" /></h4>
              <div class="ck-editor-content">
                <p>${entry.getAccess(locale)}</p>
              </div>
            </div>
          </#if>
          <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
            <div class="event-info-block">
              <h4><@liferay_ui.message key="eu.access-for-disabled" /></h4>
              <#if entry.hasAnyAccessForDisabled() >
                <div class="pictos">
                  <#if entry.accessForWheelchair>
                      <img src="/o/agendaweb/images/access-for-wheelchair.png" 
                      title="<@liferay_ui.message key="eu.access-for-wheelchair" />"
                      alt="<@liferay_ui.message key="access-for-wheelchair" />">
                  </#if>
                  <#if entry.accessForBlind>
                      <img src="/o/agendaweb/images/access-for-blind.png" 
                      title="<@liferay_ui.message key="eu.access-for-blind" />"
                      alt="<@liferay_ui.message key="eu.access-for-blind" />">
                  </#if>
                  <#if entry.accessForDeaf>
                      <img src="/o/agendaweb/images/access-for-deaf.png" 
                      title="<@liferay_ui.message key="eu.access-for-deaf" />"
                      alt="<@liferay_ui.message key="access-for-deaf" />">
                 </#if>
                  <#if entry.accessForElder>
                      <img src="/o/agendaweb/images/access-for-elder.png" 
                      title="<@liferay_ui.message key="eu.access-for-elder" />"
                      alt="<@liferay_ui.message key="access-for-elder" />">
                  </#if>
                  <#if entry.accessForDeficient>
                      <img src="/o/agendaweb/images/access-for-deficient.png" 
                      title="<@liferay_ui.message key="eu.access-for-deficient" />"
                      alt="<@liferay_ui.message key="access-for-deficient" />">
                  </#if>
                </div>
              </#if>
              <div class="ck-editor-content">
                <p>${entry.getAccessForDisabled(locale)}</p>
              </div>
            </div>
          </#if>
        </div>
        
      <div class="clearer"></div>
      </div>
    </div>

  </div>
</div>

