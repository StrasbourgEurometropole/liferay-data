<%@ include file="/contact-form-init.jsp" %>
<div class="container">
    <div id="breadcrumb" class="pro-bloc">
        <span>
            <span><a href="index.html">Accueil</a>
                <span class="breadcrumb_last">Nous contacter</span>
            </span>
        </span>
    </div>
</div>
<div class="container">
    <div class="pro-bloc pro-bloc-texte pro-max-900 aligncenter">
        <c:if test="${not empty title}">
            <h2>${title}</h2>
        </c:if>
        <c:if test="${not empty descriptionText}">
            <p>${descriptionText}</p>
        </c:if>
    </div>
</div>


<div class="pro-bloc-pcs-form pro-form-page-contact">
 <c:if test="${not param.mailSent}">
    <liferay-portlet:actionURL name="contact" var="contactURL">
		<portlet:param name="placit" value="placit"></portlet:param>
    </liferay-portlet:actionURL>
        <form action="${contactURL}" method="post" class="seu-main-form">
            <liferay-ui:error key="unknown-error" message="eu.unknown-error" targetNode=".seu-main-form" />
            <liferay-ui:error key="email-error" message="email-error" targetNode=".seu-main-form" />
            <liferay-ui:error key="lastname-error" message="lastname-error" targetNode=".seu-main-form" />
            <liferay-ui:error key="firstname-error" message="firstname-error" targetNode=".seu-main-form" />
            <liferay-ui:error key="content-error" message="content-error" targetNode=".seu-main-form" />
            <liferay-ui:error key="invalid-mail-error" message="eu.invalid-mail-error" targetNode=".seu-main-form" />

            <div class="container pro-max-900">
                <div class="pro-wrapper">
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input cssClass="form-control" name="contact.lastname" placeholder="Dupond" />
                        </div>
                        <div class="form-group form-half">
                            <aui:input cssClass="form-control" name="contact.firstname" placeholder="Jean" />
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input cssClass="form-control" name="contact.mail" placeholder="jean.dupond@gmail.com" />
                        </div>
                        <div class="form-group form-half">
                            <aui:input cssClass="form-control" name="contact.phone" />
                        </div>
                    </div>
                     <div class="form-group">
                        <aui:input cssClass="form-control" name="contact.object" />
                    </div>
                    <div class="form-group">
                        <aui:input type="textarea" cssClass="form-control" name="contact.request"/>
                    </div>
                </div>
            </div>

            <div class="pro-optin form-checkbox">
                <div class="container pro-max-900">
                    <input type="checkbox" name="sendCopy" id="sendCopy">
                    <label for="sendCopy"><liferay-ui:message key="contact.receive-copy" /></label>
                </div>
            </div>
            <div class="pro-form-submit">
                <input type="hidden" id="placit" name="<portlet:namespace />placit"/>
                <button id="submit_placit_form" type="submit" class="btn btn-default"><liferay-ui:message key="contact.send" /></button>
            </div>
        </form>
    </c:if>
</div>
