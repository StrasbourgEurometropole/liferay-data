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
    <liferay-portlet:actionURL name="contact" var="contactURL" />
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
                            <aui:input cssClass="form-control" name="contact.lastname" value="${param.lastName}" placeholder="Dupond" />
                        </div>
                        <div class="form-group form-half">
                            <aui:input cssClass="form-control" name="contact.firstname" value="${param.firstName}" placeholder="Jean" />
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input cssClass="form-control" name="contact.mail" value="${param.mailFrom}" placeholder="jean.dupond@gmail.com" />
                        </div>
                        <div class="form-group form-half">
                            <aui:input cssClass="form-control" name="contact.phone" value="${param.phone}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <aui:input cssClass="form-control" name="contact.object" value="${param.object}"/>
                    </div>
                    <div class="form-group">
                        <aui:input type="textarea" cssClass="form-control" name="contact.request" value="${param.content}" />
                    </div>
                </div>
            </div>

            <div class="pro-optin form-checkbox">
                <div class="container pro-max-900">
                    <input type="checkbox" id="placit-form-condition" value="optin">
                    <label for="placit-form-condition">${privacyText}</label>
                </div>
            </div>
            <div class="pro-form-submit">
                <button type="submit" class="btn btn-default"><liferay-ui:message key="contact.send" /></button>
            </div>
        </form>
    </c:if>
</div>