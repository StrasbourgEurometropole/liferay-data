<!-- Détail événement -->
<#setting locale = locale />
<div class="event-detail">
  <div class="event-header">
    <h1 class="event-title">
      ${entry.getTitle(locale)}
    </h1>
    <#if entry.getSubtitle(locale)?has_content>
      <h4 class="event-subtitle">
        ${entry.getSubtitle(locale)}
      </h4>
    </#if>
    <div class="event-dates">
      ${entry.getEventScheduleDisplay(locale)}
    </div>
    
    <div class="event-address">
      ${entry.getPlaceAlias(locale)} 
      <br> 
      <#if entry.getPlaceAddress(locale)?has_content>
        ${entry.getPlaceAddress(locale)} - 
      </#if>
      ${entry.placeZipCode} ${entry.getPlaceCity(locale)}
    </div>
    <#if entry.phone?has_content>
      <div class="event-phone">
        <@liferay_ui.message key="phone" /> : <a href="tel:${entry.phone}">${entry.phone}</a>
      </div>
    </#if>
  </div>
  <div class="event-info">
    <div class="event-60">
      <div class="image-with-copyright-on-hover">
        <img src="${entry.getImageURL()}" class="lightbox">
        <#if entry.getImageCopyright(locale)?has_content>
          <div class="image-copyright">
              ${entry.getImageCopyright(locale)}
          </div>
        </#if>
      </div>
      <#if entry.getDescription(locale)?has_content >
        <div class="event-info-section event-description">
          <h4><@liferay_ui.message key="description" /></h4>
          ${entry.getDescription(locale)}
        </div>
      </#if>
      <#if entry.getPublishedManifestations()?has_content>
        <div class="event-info-section">
          <@liferay_ui.message key="eu.this-event-is-part-of" />
          <#list entry.getPublishedManifestations() as manifestation>
            <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
              <@liferay_portlet.param name="classPK" value="${manifestation.getManifestationId()}" />
              <@liferay_portlet.param name="returnURL" value="${currentURL}" />
            </@liferay_portlet.renderURL>
            <a href="${detailURL}">${manifestation.getTitle(locale)}</a>
          </#list>
        </div>
      </#if>
      <#if entry.getAccess(locale)?has_content >
        <div class="event-info-section event-access">
          <h4><@liferay_ui.message key="eu.access-and-services" /></h4>
          ${entry.getAccess(locale)}
        </div>
      </#if>
      <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
        <div class="event-info-section event-access-for-disabled">
          <h4><@liferay_ui.message key="eu.access-for-disabled" /></h4>
          <#if entry.hasAnyAccessForDisabled() >
            <div class="access-for-disabled-icons">
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
          ${entry.getAccessForDisabled(locale)}
        </div>
      </#if>
      <#if entry.email?has_content>
        <div class="event-info-section">
           <@liferay_portlet.actionURL var="contactURL" name="contact">
            <@liferay_portlet.param name="classPK" value="${entry.getEventId()}" />
            <@liferay_portlet.param name="to" value="${entry.email}" />
            <@liferay_portlet.param name="subject" value="Formulaire de contact - Agenda - ${entry.getTitle(locale)}" />
          </@liferay_portlet.actionURL>
          <h4><@liferay_ui.message key="contact" /></h4>
          <@liferay_ui.success key="mail-success" message="eu.mail-success" />
          <@liferay_ui.error key="all-fields-required" message="eu.all-fields-required" />
          <@liferay_ui.error key="invalid-mail" message="eu.invalid-mail" />
          <@liferay_ui.error key="recaptcha-error" message="eu.recaptcha-error" />
          <p class="error-message all-fields-required-message" style="display:none;">
            <@liferay_ui.message key="eu.all-fields-required" />
          </p>
          <p class="error-message invalid-mail-message" style="display:none;">
            <@liferay_ui.message key="eu.invalid-mail" />
          </p>

          <#if renderRequest.getAttribute("mailSent")?has_content && renderRequest.getAttribute("mailSent")>
              <p class="mail-success">
                <@liferay_ui.message key="eu.mail-success" />
              </p>
          </#if>
          <form action="${contactURL}" name="contactForm" method="post" class="contact-form">
            <div>
              <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName">
                <@liferay_ui.message key="firstname" /> *
              </label>
              <input type="text" class="first-name"
                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName"
                value="${renderRequest.getAttribute("firstName")!""}">  
            </div>
            <div>
              <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName">
                <@liferay_ui.message key="lastname" /> *
              </label>
              <input type="text" class="last-name"
                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName"
                value="${renderRequest.getAttribute("lastName")!""}">  
            </div>
            <div>
              <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email">
                <@liferay_ui.message key="email" /> *
              </label>
              <input type="text" class="email"
                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email"
                value="${renderRequest.getAttribute("email")!""}">  
            </div>
            <div>
              <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message">
                <@liferay_ui.message key="message" /> *
              </label>
              <textarea name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" class="message">${renderRequest.getAttribute("message")!""}</textarea>  
            </div>
            <div class="g-recaptcha" data-sitekey="${propsUtil.get('eu.strasbourg.recaptcha.public')}"></div>
            <div>
              <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail">
                
              <input type="checkbox" class="notification-email"
                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" checked>&nbsp;<@liferay_ui.message key="eu.do-you-want-a-notification" />
              </label>
            </div>
            <p>
              <input type="submit" value="<@liferay_ui.message key="send" />">
            </p>
            <p>
            * :  <@liferay_ui.message key="eu.required-field" />
            </p>
            <p class="privacy-policy">
              <label><@liferay_ui.message key="eu.privacy-policy" /></label>
              <#assign preferences = freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />
              <@liferay_portlet["runtime"]
                defaultPreferences="${preferences}"
                portletProviderAction=portletProviderAction.VIEW
                portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                settingsScope="group"
                instanceId="entityDetail" />
            </p>
          </form>
        </div>

      </#if>
    </div>
    <div class="event-40">
      <div class="event-info-section">
        <h4><@liferay_ui.message key="eu.dates-and-times" /></h4>
        <ul class="museum-dates-list">
          <#list entry.currentAndFuturePeriods as period>
            <li>${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> : ${period.getTimeDetail(locale)}</#if></li>
          </#list>
        </ul>
         <#if (entry.currentAndFuturePeriods?size > 5)>
            <div class="museum-line-left">
                <span class="museum-see-dates">
                        <span class="museum-more"><@liferay_ui.message key="eu.see-more" /></span>
                        <span class="museum-less"><@liferay_ui.message key="eu.see-less" /></span>
                </span>
            </div>
        </#if>
      </div>
      <#if entry.free == 1 || entry.getPrice(locale)?has_content>
        <div class="event-info-section">
          <h4><@liferay_ui.message key="eu.event-prices" /></h4>
          <#if entry.free == 1>
            <div class="free-event"><@liferay_ui.message key="eu.free-event" /></div>
          </#if>
          ${entry.getPrice(locale)}
        </div>
      </#if>
      <#if entry.getWebsiteName(locale)?has_content && entry.getWebsiteURL(locale)?has_content>
        <div class="event-info-section event-website">
          <h4><@liferay_ui.message key="eu.event-website" /></h4>
          <ul>
            <li>
              <a href="${entry.getWebsiteURL(locale)}" title="${entry.getWebsiteName(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">${entry.getWebsiteName(locale)}</a>  
            </li>
          </ul>
        </div>
      </#if>
    </div>
  </div>
</div>