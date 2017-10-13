<%@ include file="/contact-form-init.jsp" %>

<div class="small-container mns-page-contact">
    <div class="row">
        <div class="col-sm-8">
        	<liferay-ui:error key="unknown-error" message="eu.unknown-error" targetNode=".mns-page-contact .col-sm-8" />
        	<liferay-ui:error key="recaptcha-error" message="eu.recaptcha-error" targetNode=".mns-page-contact .col-sm-8" />
        	<liferay-ui:error key="email-error" message="email-error" targetNode=".mns-page-contact .col-sm-8" />
        	<liferay-ui:error key="lastname-error" message="lastname-error" targetNode=".mns-page-contact .col-sm-8" />
        	<liferay-ui:error key="firstname-error" message="firstname-error" targetNode=".mns-page-contact .col-sm-8" />
        	<liferay-ui:error key="content-error" message="content-error" targetNode=".mns-page-contact .col-sm-8" />
        	<liferay-ui:error key="invalid-mail-error" message="eu.invalid-mail-error" targetNode=".mns-page-contact .col-sm-8" />
            <c:if test="${not param.mailSent}">
	        	<c:if test="${not empty descriptionText}">
		            <p class="mns-intro-form">
		                ${descriptionText}
		            </p>
	            </c:if>
	            <liferay-portlet:actionURL name="contact" var="contactURL" />
	            <form method="post" action="${contactURL}">
	                <div class="form-group">
	                    <label for="name"><liferay-ui:message key="contact.lastname" /></label>
	                    <input type="text" class="form-control" id="lastName" name="lastName" value="${param.lastName}">
	                </div>
	                <div class="form-group">
	                    <label for="firstname"><liferay-ui:message key="contact.firstname" /></label>
	                    <input type="text" class="form-control" id="firstName" name="firstName" value="${param.firstName}">
	                </div>
	                <div class="form-group">
	                    <label for="email"><liferay-ui:message key="contact.mail" /></label>
	                    <input type="email" class="form-control" id="emailFrom" name="emailFrom" value="${param.emailFrom}">
	                </div>
	                <div class="form-group" style="visibility: hidden;">
	                    <label for="tel">Phone</label>
	                    <input type="tel" class="form-control" id="tel">
	                </div>
	                <div class="form-group mns-textarea">
	                    <label for="demande"><liferay-ui:message key="contact.request" /></label>
	                    <textarea class="form-control" rows="3" id="demande" name="content">${param.content}</textarea>
	                    <span><liferay-ui:message key="contact.required-fields" /></span>
	                </div>
	                <div class="checkbox">
	                    <label>
		                    <input type="checkbox" name="sendCopy" value="true" <c:if test="${param.sendCopy}">checked</c:if>>
		                    <span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
	                      	<liferay-ui:message key="contact.receive-copy" />
	                    </label>
	                </div>
	                <div class="recaptcha" style="margin-top: 20px;">
	                	<div class="g-recaptcha" data-sitekey="${recaptchaKey}"></div>
	                </div>
	                <div class="mns-submit">
	                    <span></span>
	                    <input type="submit" id="submit" value="<liferay-ui:message key="contact.send" />">
	                </div>
	            </form>
	            <c:if test="${not empty privacyText}">
		            <p class="mns-outro-form">
		            	${privacyText}
		            </p>
	            </c:if>
	    	</c:if>
	    	<c:if test="${param.mailSent}">
	    		<div class="mail-success">
	    			<liferay-ui:message key="success" />
	    		</div>
	    	</c:if>
        </div>
        <aside class="col-sm-4">
          <liferay-portlet:runtime
            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
            instanceId="info" />
        </aside>
    </div>
</div>
<style>
	.lfr-alert-container {
	    position: static;
	    border: 1px solid #EF5350;
	    padding: 5px 0;
	    margin-bottom: 25px;
	    border-radius: 5px;
	}
	.lfr-alert-container .lfr-alert-wrapper {
	    margin: 0;
	    height: auto !important;
	}
	
	.lfr-alert-container .alert-danger {
	    background: none;
	    border: none;
	    margin: 0;
	    padding: 0;
	    color: #EF5350;
	    line-height: 25px
	}
	
	.lfr-alert-container .alert-danger button,
	.lfr-alert-container .alert-danger .lead {
	    display: none;
	}
</style>