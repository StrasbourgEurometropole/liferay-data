<%@ include file="/project-popup-init.jsp" %>


<portlet:resourceURL id="ContactInitiativeAuthor" var="ContactInitiativeAuthorURL">
</portlet:resourceURL>

<!-- MODAL CONTACTER LE PORTEUR -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalInitiativeContact"
	tabindex="-1" role="dialog" aria-labelledby="modalInitiativeContact">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="pro-modal-top">
				<h3><liferay-ui:message key="modal.initiative.contact.author.title"/></h3>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true"><span class="icon-multiply"></span></span>
				</button>
			</div>
			<form>
				<div class="pro-wrapper">
					<div class="pro-row">
						<div class="form-group form-half">
							<aui:input name="username" disabled="true" label="modal.user.username" required="true" value="${userConnected.get('last_name')}"/>
						</div>
						<div class="form-group form-half">
							<aui:input name="firstname" disabled="true" label="modal.user.firstname" required="true" value="${userConnected.get('first_name')}"/>
						</div>
					</div>
					<div class="pro-row">
						<div class="form-group form-half">
							<aui:input type="email" name="mail" disabled="true" label="modal.user.mail" required="true" value="${userConnected.get('email')}"/>
						</div>
						<div class="form-group form-half">
							<aui:input id="mobile" name="mobile" label="modal.user.mobile" placeholder="0611111111" maxlength="20"/>
						</div>
					</div>
					<div class="form-group">
						<aui:input id="initiativeContactAuthorSubject" name="initiativeContactAuthorSubject" required="true" 
	                        	label="modal.initiative.contact.author.subject" value=""
	                     />
					</div>
					<div class="form-group">
						<aui:input id="initiativeContactAuthorMessage" name="initiativeContactAuthorMessage" required="true" 
	                        	label="modal.initiative.contact.author.message" value="" type="textarea"
	                     />
					</div>
				</div>
				<div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="contact-initiative-author-legalage" value="legalage">
                        <label for="contact-initiative-author-legalage" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_legalage"/>
                        </label>
                    </div>
                </div>
                
                <div id="<portlet:namespace />alert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                
				<div class="pro-form-submit">
					<button id="submitContactButton" type="submit" class="btn btn-default"><liferay-ui:message key="modal.initiative.contact.author.submit.button"/></button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>

var namespaceContactAuthor = "<portlet:namespace />";
var mobile = "${userConnected.get('mobile')}" != 'null' ? "${userConnected.get('mobile')}" : "";

$(document).ready(function(){
	$("#"+namespaceContactAuthor+"mobile").val(mobile);
});

$('#submitContactButton').click( function(e){
	e.preventDefault();
	AUI().use('aui-io-request', function(A) {
		A.io.request('${ContactInitiativeAuthorURL}', {
			method : 'post',
			dataType: 'json',
			on: {
	            success: function(e) {
	            	$('#modalInitiativeContact').modal('hide')
			 	}
			 }
		});
	});
});

</script>