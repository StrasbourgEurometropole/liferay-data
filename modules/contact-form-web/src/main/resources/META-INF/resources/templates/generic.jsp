<%@ include file="/contact-form-init.jsp" %>

<div class="contact-form-portlet-page container">
	<c:if test="${not empty title}">
		<h1>${title}</h1>
	</c:if>
	<c:if test="${not empty descriptionText}">
		<div style="margin-bottom: 40px">
		    ${descriptionText}
		</div>
    </c:if>
    <c:if test="${not param.mailSent}">
	    <liferay-portlet:actionURL name="contact" var="contactURL" />
		<form action="${contactURL}" method="post" class="main-form">
			<liferay-ui:error key="unknown-error" message="eu.unknown-error" targetNode=".main-form" />
		   	<liferay-ui:error key="email-error" message="email-error" targetNode=".main-form" />
		   	<liferay-ui:error key="lastname-error" message="lastname-error" targetNode=".main-form" />
		   	<liferay-ui:error key="firstname-error" message="firstname-error" targetNode=".main-form" />
		   	<liferay-ui:error key="content-error" message="content-error" targetNode=".main-form" />
		   	<liferay-ui:error key="invalid-mail-error" message="eu.invalid-mail-error" targetNode=".main-form" />
		      
		    <fieldset>
		        <div class="widget">
		            <div class="title"><label for="lastName"><liferay-ui:message key="contact.lastname" /></label></div>
		            <div class="content">
		            	<input type="text" id="lastName" name="lastName" value="${param.lastName}" required>
		            </div>
		        </div>                
		        <div class="widget">
		            <div class="title"><label for="firstName"><liferay-ui:message key="contact.firstname" /></label></div>
		            <div class="content">
		            	<input type="text" id="firstName" name="firstName" value="${param.firstName}" required>
		            </div>
		        </div>                
		        <div class="widget">
		            <div class="title"><label for="emailFrom"><liferay-ui:message key="contact.mail" /></label></div>
		            <div class="content">
		            	<input type="text" id="emailFrom" name="emailFrom" value="${param.emailFrom}" required>
		            </div>
		        </div>          
			    <div class="widget">
			        <div class="title"><label for="demande"><liferay-ui:message key="contact.request" /></label></div>
			        <div class="content">
			            <textarea rows="8" id="demande" name="content" required>${param.content}</textarea>
			        </div>
			    </div>    
			    <div class="widget">
				    <label>
				    	<input type="checkbox" name="sendCopy" value="true" <c:if test="${param.sendCopy}">checked</c:if>>
				    	<liferay-ui:message key="contact.receive-copy" />
				    </label>
			    </div>       
		    </fieldset>         
		    
			<div class="recaptcha" style="margin-top: 20px;">
				<div class="g-recaptcha" data-sitekey="${recaptchaKey}"></div>
			</div>
            <liferay-ui:error key="recaptcha-error" message="eu.recaptcha-error" targetNode=".recaptcha" />
			
			<div style="padding: 20px 0;">
				${privacyText}
			</div>
		    <div class="buttons submit">
		        <div class="SubmitWidget widget submit-button">
		        	<div class="content">
		        		<button name="submit" aria-label="<liferay-ui:message key="contact.send" />" title="<liferay-ui:message key="contact.send" />" value="<liferay-ui:message key="contact.send" />">
		        			<liferay-ui:message key="contact.send" />
		        		</button>
	        		</div>
		        </div>
		        <div class="SubmitWidget widget cancel-button">
		        	<div class="content">
		        		<button type="reset" class="cancel" aria-label="<liferay-ui:message key="cancel" />" title="<liferay-ui:message key="cancel" />" name="cancel" value="Annuler" formnovalidate="formnovalidate">
		        			<liferay-ui:message key="cancel" />
	        			</button>
	       			</div>
		        </div>
		    </div>
		</form>
	</c:if>
   	<c:if test="${param.mailSent}">
   		<div class="main-form">
   			<div class="mail-success">
	   			<liferay-ui:message key="success" />
	   		</div>
	   		<div class="buttons submit">
	   			<div class="SubmitWidget widget previous-button">
					<div class="content">
						<liferay-portlet:renderURL var="formURL" />
						<a href="${formURL}" aria-label="<liferay-ui:message key="contact.new-request" />" title="<liferay-ui:message key="contact.new-request" />">
							<button name="previous" value="<liferay-ui:message key="new-request" />" formnovalidate="formnovalidate">
								<liferay-ui:message key="contact.new-request" />
							</button>
						</a>
					</div>
				</div>
    		</div>
   		</div>
   		
   	</c:if>
</div>

<style>
</style>

<liferay-util:html-bottom>
    <link href="/o/contactformweb/css/generic.css" rel="stylesheet"></script>
</liferay-util:html-bottom>