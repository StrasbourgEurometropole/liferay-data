<!-- Détail événement -->
<#setting locale = locale />
<#assign classNameService = serviceLocator.findService("com.liferay.portal.kernel.service.ClassNameService") />
<#assign templateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.TemplateHelperService") />

<section id="event-detail" class="margin-top margin-bottom">
    <div  class="content container">
      <div class="event-header">

        <#if entry.getActivityTypeLabel(locale)?has_content >
            <div class="event-type" >
              ${entry.getActivityTypeLabel(locale)}
            </div>
        </#if>
        
        <h1 class="event-title">
          ${entry.getTitle(locale)}
        </h1>

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

        <div class="image-with-copyright-on-hover">
          <img src="${entry.getImageURL()}" class="lightbox" alt="${entry.getTitle(locale)?html}" title="${entry.getTitle(locale)?html}">
          <#if entry.getImageCopyright(locale)?has_content>
            <div class="copyright"><span>C</span><span>${entry.getImageCopyright(locale)}</span></div>
          </#if>
        </div>

        <#if entry.getDescription(locale)?has_content >
          <div class="event-presentation">
            <h2><@liferay_ui.message key="eu.museum.presentation" /></h2>
            ${entry.getDescription(locale)}
          </div>
        </#if>
      </div>

      <div class="event-info">
        <div class="thumbnail event-horaire">
          <h2><@liferay_ui.message key="eu.dates-and-times" /></h2>
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
          <div class="thumbnail event-price">
            <h2><@liferay_ui.message key="eu.event-prices" /></h2>
            <#if entry.free == 1>
              <div class="free-event"><@liferay_ui.message key="eu.free-event" /></div>
            </#if>
            ${entry.getPrice(locale)}
          </div>
        </#if>
        
        <#if entry.getAccess(locale)?has_content >
          <div class="thumbnail event-access">
            <h2><@liferay_ui.message key="eu.access-and-services" /></h2>
            ${entry.getAccess(locale)}
          </div>
        </#if>

        <#if entry.getBookingDescription(locale)?has_content >
          <div class="thumbnail event-booking">
            <h2><@liferay_ui.message key="eu.booking" /></h2>
            ${entry.getBookingDescription(locale)}
            <a href="${entry.getBookingURL()}" class="button1" aria-label="<@liferay_ui.message key="eu.museum.booking" /> (<@liferay_ui.message key="eu.new-window" />))" title="<@liferay_ui.message key="eu.museum.booking" /> (<@liferay_ui.message key="eu.new-window" />))" target="_blank"><@liferay_ui.message key="eu.museum.booking" /></a>
          </div>
        </#if>

        <#if entry.getRegistration() >
          <div class="thumbnail event-registration">
            <h2><@liferay_ui.message key="eu.registration" /></h2>
            <p>
              <strong><@liferay_ui.message key="eu.museum.start-date" /> :</strong> ${entry.getRegistrationStartDate()?date?string.long}<br/>
              <strong><@liferay_ui.message key="eu.museum.end-sate" /> :</strong> ${entry.getRegistrationEndDate()?date?string.long}<br/>
              <strong><@liferay_ui.message key="eu.museum.nb-place" /> :</strong> ${entry.getMaxGauge()}
            </p>
          </div>
        </#if>

        <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
          <div class="thumbnail event-access-for-disabled">
            <h2><@liferay_ui.message key="eu.museum.access-for-disabled" /></h2>
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
      </div>

      <#if entry.email?has_content>
        <div class="event-contact">
          <@liferay_portlet.actionURL var="contactURL" name="contact">
          <@liferay_portlet.param name="classPK" value="${entry.getEventId()}" />
          <@liferay_portlet.param name="to" value="${entry.email}" />
          <@liferay_portlet.param name="subject" value="Formulaire de contact - Agenda - ${entry.getTitle(locale)}" />
          </@liferay_portlet.actionURL>
          <h2><@liferay_ui.message key="contact" /></h2>
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
          <form action="${contactURL}" name="contactForm" id="contactForm" method="post" class="contact-form">
            <div>
              <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName">
                <@liferay_ui.message key="lastname" /> *
              </label>
              <input type="text" class="last-name"
                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName"
                id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName"
                value="${renderRequest.getAttribute("lastName")!""}">  
            </div>
            <div>
              <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName">
                <@liferay_ui.message key="firstname" /> *
              </label>
              <input type="text" class="first-name"
                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName"
                id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName"
                value="${renderRequest.getAttribute("firstName")!""}">  
            </div>
            <div>
              <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email">
                <@liferay_ui.message key="eu.museum.email" /> *
              </label>
              <input type="text" class="email"
                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email"
                id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email"
                value="${renderRequest.getAttribute("email")!""}">  
            </div>
            <div>
              <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message">
                <@liferay_ui.message key="message" /> *
              </label>
              <textarea name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message"  id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" class="message">${renderRequest.getAttribute("message")!""}</textarea>  
            </div>
            <div>
              <label class="notif" for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail">
                
              <input type="checkbox" class="notification-email"
                name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" checked>&nbsp;<@liferay_ui.message key="eu.do-you-want-a-notification" />
              </label>
            </div>
            <div class="g-recaptcha" data-sitekey="${propsUtil.get('eu.strasbourg.recaptcha.public')}"></div>
            <p>
                  <a href="$('#contactForm').submit()" class="button1" aria-label="<@liferay_ui.message key="send" />" title="<@liferay_ui.message key="send" />"><@liferay_ui.message key="send" /></a>
            </p>
            <p>
            * :  <@liferay_ui.message key="eu.required-field" />
            </p>
            <p class="privacy-policy">
              <p><@liferay_ui.message key="contact.default-privacy" /></p>
            </p>
          </form>
        </div>
      </#if>
    </div>
</section>