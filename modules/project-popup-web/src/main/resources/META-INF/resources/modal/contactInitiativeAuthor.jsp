<%@ include file="/project-popup-init.jsp" %>


<portlet:resourceURL id="ContactInitiativeAuthor" var="ContactInitiativeAuthorURL">
</portlet:resourceURL>

<!-- MODAL CONTACTER LE PORTEUR -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalContacter"
	tabindex="-1" role="dialog" aria-labelledby="modalContacter">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="pro-modal-top">
				<h3>Contacter le porteur</h3>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true"><span class="icon-multiply"></span></span>
				</button>
			</div>

			<form>
				<div class="pro-wrapper">
					<div class="pro-row">
						<div class="form-group form-half">
							<label for="nom-2">Nom <span class="required">*</span></label> <input
								type="text" class="form-control" id="nom-2" />
						</div>
						<div class="form-group form-half pro-form-error">
							<label for="prenom-2">Prénom <span class="required">*</span></label>
							<input type="text" class="form-control" id="prenom-2" />
						</div>
					</div>
					<div class="pro-row">
						<div class="form-group form-half">
							<label for="email-2">Adresse mail <span class="required">*</span></label>
							<input type="email" class="form-control" id="email-2">
						</div>
						<div class="form-group form-half">
							<label for="tel-2">Téléphone <span class="required">*</span></label>
							<input type="text" class="form-control" id="tel-2" />
						</div>
					</div>
					<div class="form-group">
						<label for="sujet">Sujet <span class="required">*</span></label> <input
							type="text" class="form-control" id="sujet" />
					</div>
					<div class="form-group">
						<label for="message">Message <span>*</span></label>
						<textarea id="message" class="form-control" rows="3"></textarea>
					</div>
				</div>
				<div class="pro-optin form-checkbox">
					<div>
						<input type="checkbox" id="optin-2" value="optin"> <label
							for="optin-2">Je consens XXXX (Conditions CNIL à
							préciser)</label>
					</div>
				</div>
				<div class="pro-form-submit">
					<button id="submitButton" type="submit" class="btn btn-default">Contacter le
						porteur</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>

$('#submitButton').click( function(){
	AUI().use('aui-io-request', function(A) {
		A.io.request('${ContactInitiativeAuthorURL}', {
			method : 'post',
			dataType: 'json',
			on: {
	            success: function(e) {
	            	alert('success');
			 	}
			 }
		});
	});
});

</script>