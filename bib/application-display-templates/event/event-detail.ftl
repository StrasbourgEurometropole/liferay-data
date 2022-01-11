<!-- Détail événement -->
<#setting locale = locale />
<div class="event-detail" style="margin-left:60px; margin-right:60px">
  <div class="event-header">
  
    <div class="go-back-to-agenda">
        <a class="go-back-to-agenda" href="https://bibliotheques-ideales.strasbourg.eu/agenda"><p><span>←</span> Retourner au programme</p></a>
    </div>
  
    <h4 class="event-title">
      ${entry.getTitle(locale)}
    </h4>
    
    <!-- Test sous-titre -->
    <h5 style="font-size: 30px;">${entry.getSubtitle(locale)}</h5>
    <!-- -->
    
    <p><strong>
    <#assign categories = entry.getTypeLabel(locale) />
        ${categories}
    </strong></p>
   
    <div class="event-dates">
    <h6>
    <ul>
          <#list entry.eventPeriods as period>
            <li>${period.getDisplay(locale)}<#if period.getTimeDetail(locale)?has_content> à ${period.getTimeDetail(locale)}</#if></li>
          </#list>
        </ul></h6>
    </div>
    
    <div class="event-address">
      ${entry.getPlaceAlias(locale)} 
      <br> <i>
      <#if entry.getPlaceAddress(locale)?has_content>
        ${entry.getPlaceAddress(locale)} - 
      </#if>
      ${entry.placeZipCode} ${entry.getPlaceCity(locale)}
    </div>
    <#if entry.phone?has_content>
      <div class="event-phone">
        <@liferay_ui.message key="phone" /> : <a href="tel:${entry.phone}">${entry.phone}</a>
      </div>
    </#if></i>
  </div>
  <div class="event-info">
    <div class="event-60"><div class="flex-container">
        <div class="image-with-copyright-on-hover">
          <img src="${entry.getImageURL()}">
          <#if entry.getImageCopyright(locale)?has_content>
            <div class="image-copyright" style="font-style: italic;">
                ${entry.getImageCopyright(locale)}
            </div>
          </#if>
        </div>
        <div class="event-booking">
        <#if entry.getBookingDescription(locale)?has_content >
          <div class="event-booking-description">
            ${entry.getBookingDescription(locale)}
          </div>
        </#if>
        <#if entry.getBookingURL()?has_content >
          <div class="event-booking-url">
            <a class="event_bouton" href="${entry.getBookingURL()}"><@liferay_ui.message key="eu.booking" /></a>
          </div>
        </#if>
        </div>
      </div>
      <#if entry.getDescription(locale)?has_content >
        <div class="event-info-section event-description">
          <h5></h5>
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
      
      <!-- Affichage lien réservation - 2020 -->
    <#if entry.getPrice(locale)?has_content>
               
            <h6><br /><br />Réservation</h6>
            <p><a href="${entry.getPrice(locale)}" target="_blank">Réservez votre place</a></p>

</#if>
<!-- Affichage lien réservation - 2020 -->
      
      <#if entry.getAccess(locale)?has_content >
        <div class="event-info-section event-access">
          <h6>Infos pratiques</h6>
          ${entry.getAccess(locale)}
        </div>
      </#if>
      <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
        <div class="event-info-section event-access-for-disabled">
          <h6>Accès et services</h6>
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
          <h5><@liferay_ui.message key="contact" /></h5>
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
                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" checked> <@liferay_ui.message key="eu.do-you-want-a-notification" />
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
              <#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
              <@liferay_portlet["runtime"]
                defaultPreferences="${freeMarkerPortletPreferences}"
                portletProviderAction=portletProviderAction.VIEW
                portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                settingsScope="group"
                instanceId="entityDetail" />
              ${freeMarkerPortletPreferences.reset()}
            </p>
          </form>
        </div>

      </#if>
    </div>
    <div class="event-40">
      <div class="event-info-section">
       
      </div>
      <#if entry.getWebsiteName(locale)?has_content && entry.getWebsiteURL(locale)?has_content>
        <div class="event-info-section event-website">
          
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