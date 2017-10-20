<%@ include file="/contact-form-init.jsp" %>

<div class="seu-container">
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
		<form action="${contactURL}" method="post" class="seu-main-form">
			<liferay-ui:error key="unknown-error" message="eu.unknown-error" targetNode=".seu-main-form" />
		   	<liferay-ui:error key="recaptcha-error" message="eu.recaptcha-error" targetNode=".seu-main-form" />
		   	<liferay-ui:error key="email-error" message="email-error" targetNode=".seu-main-form" />
		   	<liferay-ui:error key="lastname-error" message="lastname-error" targetNode=".seu-main-form" />
		   	<liferay-ui:error key="firstname-error" message="firstname-error" targetNode=".seu-main-form" />
		   	<liferay-ui:error key="content-error" message="content-error" targetNode=".seu-main-form" />
		   	<liferay-ui:error key="invalid-mail-error" message="eu.invalid-mail-error" targetNode=".seu-main-form" />
		      
		    <fieldset>
		        <div class="widget">
		            <div class="title"><label for="name"><liferay-ui:message key="contact.lastname" /></label></div>
		            <div class="content">
		            	<input type="text" id="lastName" name="lastName" value="${param.lastName}">
		            </div>
		        </div>                
		        <div class="widget">
		            <div class="title"><label for="name"><liferay-ui:message key="contact.firstname" /></label></div>
		            <div class="content">
		            	<input type="text" id="firstName" name="firstName" value="${param.firstName}">
		            </div>
		        </div>                
		        <div class="widget">
		            <div class="title"><label for="emailFrom"><liferay-ui:message key="contact.mail" /></label></div>
		            <div class="content">
		            	<input type="text" id="emailFrom" name="emailFrom" value="${param.emailFrom}">
		            </div>
		        </div>          
			    <div class="widget">
			        <div class="title"><label for="request"><liferay-ui:message key="contact.request" /></label></div>
			        <div class="content">
			            <textarea rows="8" id="demande" name="content">${param.content}</textarea>
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
			
			<div style="padding: 20px 0;">
				${privacyText}
			</div>
		    <div class="buttons submit">
		        <div class="SubmitWidget widget submit-button">
		        	<div class="content">
		        		<button name="submit" value="<liferay-ui:message key="contact.send" />">
		        			<liferay-ui:message key="contact.send" />
		        		</button>
	        		</div>
		        </div>
		        <div class="SubmitWidget widget cancel-button">
		        	<div class="content">
		        		<button type="reset" class="cancel" name="cancel" value="Annuler" formnovalidate="formnovalidate">
		        			<liferay-ui:message key="cancel" />
	        			</button>
	       			</div>
		        </div>
		    </div>
		</form>
	</c:if>
   	<c:if test="${param.mailSent}">
   		<div class="seu-main-form">
   			<div class="mail-success">
	   			<liferay-ui:message key="success" />
	   		</div>
	   		<div class="buttons submit">
	   			<div class="SubmitWidget widget previous-button">
					<div class="content">
						<liferay-portlet:renderURL var="formURL" />
						<a href="${formURL}" title="<liferay-ui:message key="contact.new-request" />">
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
	.lfr-alert-container {
	    position: static;
	    padding: 40px;
	    margin-bottom: 25px;
	    background: #EF5350;
	}
	
	.lfr-alert-container .lfr-alert-wrapper {
	    margin-bottom: 0;
	    padding: 0;
	    height: auto !important;
	}
	
	.lfr-alert-container .lfr-alert-wrapper + .lfr-alert-wrapper {
		margin-top: 15px;
	}
	
	.lfr-alert-container .alert-danger {
	    background: none;
	    border: none;
	    margin: 0;
	    padding: 0;
	    color: white;
	    line-height: 25px
	}
	
	.lfr-alert-container .alert-danger button,
	.lfr-alert-container .alert-danger .lead {
	    display: none;
	}
	
	.mail-success {
		background: #4CAF50;
		color: white;
		padding: 40px;
	}
	.previous-button {
		margin-top: 40px;
	}
	.previous-button button {
		margin-top: 40px;
		font-weight: normal;
	}
</style>