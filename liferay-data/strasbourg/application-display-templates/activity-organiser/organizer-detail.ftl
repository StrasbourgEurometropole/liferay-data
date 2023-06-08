<!-- Détail organisateurs -->
<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:description":'${entry.getPresentation(locale)?replace("<[^>]*>", "", "r")?html}'
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="seu-container rte">
    <h1>${entry.getName(locale)}</h1>
    <#if entry.imageURL?has_content>
        <div style="float:right">
            <img src="${entry.imageURL}" />
        </div>
    </#if>

    <h2><@liferay_ui.message key="eu.presentation" /></h2>
    <#if entry.getPresentation(locale)?has_content>
        <div>
            ${entry.getPresentation(locale)}
        </div>
    </#if>
    <#if entry.phone?has_content>
        <h3><@liferay_ui.message key="eu.place.public-reception" /></h3>
        <#if entry.phone?has_content>
            <div>
                Tél. ${entry.phone}
            </div>
        </#if>
    </#if>
    <#if entry.getAddress(locale)?has_content>
        <h3><@liferay_ui.message key="address" /></h3>
        <div>
            ${entry.getAddress(locale)}
        </div>
    </#if>
    <#if entry.getSiteURL(locale)?has_content>
        <h3><@liferay_ui.message key="eu.website" /></h3>
        <div>
            <a href="${entry.getSiteURL(locale)}" title="<@liferay_ui.message key='eu.new-window' />" target="_blank" class="seu-external" />${entry.getSiteURL(locale)}</a>
        </div>
    </#if>
    <!-- Contact -->
    <#if entry.mail?has_content>
        <h2><@liferay_ui.message key="contact.contact-us" /></h2>
        <div class="seu-wi--collapsing <#if renderRequest.getAttribute('fromContactForm')?has_content && renderRequest.getAttribute('fromContactForm')>seu-first-opened</#if>">
            <div class="white-box">
                <div class="rte">
                    <@liferay_portlet.actionURL var="contactURL" name="contact">
                        <@liferay_portlet.param name="classPK" value="${entry.activityOrganizerId}" />
                        <@liferay_portlet.param name="to" value="${entry.mail}" />
                        <@liferay_portlet.param name="subject" value="Formulaire de contact - Organizer - ${entry.getName(locale)}" />
                    </@liferay_portlet.actionURL>
                    
                    <form id="contactForm" action="${contactURL}#contactForm" name="contactForm" method="post" class="seu-main-form">
                        <@liferay_ui.error key="all-fields-required" message="eu.all-fields-required" targetNode="#contactForm" />
                        <@liferay_ui.error key="invalid-mail" message="eu.invalid-mail" targetNode="#contactForm" />
                        <@liferay_ui.error key="recaptcha-error" message="eu.recaptcha-error" targetNode="#contactForm" />
                        
                        <fieldset>
                            <#if renderRequest.getAttribute("mailSent")?has_content && renderRequest.getAttribute("mailSent")>
                                <p class="mail-success">
                                   <@liferay_ui.message key="eu.mail-success" />
                                </p>
                            </#if>

                            <div class="inline-group">
                                <div class="widget widget-required">
                                    <div class="title">
                                        <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName"><@liferay_ui.message key="contact.lastname" /></label>
                                    </div>
                                    <div class="content">
                                        <input name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName" type="text" placeholder="<@liferay_ui.message key="contact.your-lastname" />" value="${renderRequest.getAttribute("lastName")!""}">
                                    </div>
                                </div>
                                <div class="widget widget-required">
                                    <div class="title">
                                        <label for="prenom"><@liferay_ui.message key="contact.firstname" /></label>
                                    </div>
                                    <div class="content">
                                        <input name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName" type="text" placeholder="<@liferay_ui.message key="contact.your-firstname" />" value="${renderRequest.getAttribute("firstName")!""}">
                                    </div>
                                </div>
                            </div>
                            <div class="widget widget-required">
                                <div class="title">
                                    <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email"><@liferay_ui.message key="contact.mail" /></label>
                                </div>
                                <div class="content">
                                    <input name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email" type="text" placeholder="<@liferay_ui.message key="contact.your-mail" />" value="${renderRequest.getAttribute("email")!""}">
                                </div>
                            </div>
                            <div class="widget widget-required">
                                <div class="title">
                                    <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message"><@liferay_ui.message key="contact.request" /></label>
                                </div>
                                <div class="content">
                                    <textarea name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" placeholder="<@liferay_ui.message key="contact.your-message" />" rows="5">${renderRequest.getAttribute("message")!""}</textarea>
                                </div>
                            </div>

                            <div>
                                <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail">

                                <input type="checkbox" class="notification-email"
                                    name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" checked>&nbsp;<@liferay_ui.message key="contact.receive-copy" />
                                </label>
                            </div>
                        </fieldset>
                        <div style="margin-top: 20px;" class="g-recaptcha" data-sitekey="${propsUtil.get('eu.strasbourg.recaptcha.public')}"></div>
                        <div style="padding: 20px 0;">
                            <p><@liferay_ui.message key="contact.default-privacy" /></p>
                        </div>
                        <div class="buttons submit">
                            <div class="SubmitWidget widget submit-button">
                                <div class="content">
                                    <button name="submit" value="<@liferay_ui.message key='send' />">
                                        <@liferay_ui.message key='send' />
                                    </button>
                                </div>
                            </div>
                            <div class="SubmitWidget widget cancel-button">
                                <div class="content">
                                    <button type="reset" class="cancel" name="cancel" value="<@liferay_ui.message key='cancel' />" formnovalidate="formnovalidate">
                                        <@liferay_ui.message key='cancel' />
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>
</div>
<style>
    .mail-success {
        background: #4CAF50;
        color: white;
        padding: 40px;
    }
</style>