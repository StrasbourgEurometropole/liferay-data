<%@ include file="/pacte-init.jsp"%>

<portlet:resourceURL id="pacteSignature" var="pacteSignatureURL">
</portlet:resourceURL>

<div class="pro-page-pacte">
    <div class="container">
		<div class="row">
			<div class="pro-bloc-accordion" style="margin-top: 0px">
				<div class="col-sm-9 col-xs-12 pro-bloc-facette">
					<form method="get" action="/">
						<div class="pro-group">
							<fieldset class="pro-checkbox">
								<legend aria-hidden="true" class="hide">
									<liferay-ui:message key="pacte-legend" />
								</legend>
								<div>
									<input type="checkbox" name="zone_vdl" id="type_v_1" value="1">
									<label for="type_v_1"><liferay-ui:message
											key="pacte-label" /></label>
								</div>
							</fieldset>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="pro-bloc-prefooter">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 pro-signature-pacte">
				<!-- Ajouter la classe pro-disabled sur le <a> pour avoir l'etat desactive du bouton -->
				<a id="signPacte" href="#" <c:if test="${hasUserSigned}">class="active"</c:if>><!-- class="pro-disabled" -->
					<div class="pro-svg">
						<svg xmlns="http://www.w3.org/2000/svg" width="236.125"
							height="59.09" viewBox="0 0 236.125 59.09" role="img">
                                    <defs>
                                        <style>
										.cls-1 {
											fill: none;
											stroke: #000;
											stroke-width: 2px;
											fill-rule: evenodd;
										}
										</style>
                                    </defs>
                                    <path id="Forme_63"
								data-name="Forme 63" class="cls-1"
								d="M503,3845s24.047-19.45,31-32,2.16,22.27,13,22,44-13,44-13,6.457,15.31,28,2c10.7-6.61,2.414,6.51,31-1s26.523-35.57,16-31,5.617,23.09,28,24c28.344,1.15,42-8,42-8"
								transform="translate(-502 -3788.5)" />
                        	</svg>
						<span class="icon-ico-pencil"></span>
					</div>
					<c:if test="${hasUserSigned}"><h3><liferay-ui:message key="pacte-adhere" /></h3>
					</c:if>
					<c:if test="${!hasUserSigned}"><h3><liferay-ui:message key="pacte-sign" /></h3>
					</c:if>
				</a>
			</div>
		</div>
	</div>
	
	<!-- 
	<div class="pro-social-share-page">
		<span>Partager sur :</span>
		<ul>
			<li><a href="#" target="_blank"
				title="Lien de partage vers Facebook"
				aria-label="Lien de partage vers Facebook"><span
					class="icon-ico-facebook-with-circle"></span></a></li>
			<li><a href="" target="_blank"
				title="Lien de partage vers Twitter"
				aria-label="Lien de partage vers Twitter"><span
					class="icon-ico-twitter-with-circle"></span></a></li>
			<li class="pro-dropdown"><a href="#social-share"
				class="ico-share" title="Autres liens de partage"
				aria-label="Autres liens de partage"><span
					class="icon-ico-share-with-circle"></span></a>
				<ul id="sub-share">
					<li><a href="#" class="pro-btn-share btn-google-plus"
						target="_blank">Google+</a></li>
					<li><a target="_blank" href="#"
						class="pro-btn-share btn-linkedin">LinkedIn</a></li>
					<li><a href="mailto:" class="pro-btn-share btn-mail">Mail</a></li>
				</ul></li>
		</ul>
	</div>
	 -->
</div>
<!-- CONFIRMATION QUITTER -->
<!-- HTML pour confirmer la rÃ©siliation du pacte -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalQuitPacte" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.quit.title"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key="modal.quit.description" /></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirmQuit" onclick="callServeResource();" type="submit" class="pro-btn" value="Résilier"/>
                    <input id="buttonCancelQuit" type="reset" class="pro-btn"  data-dismiss="modal" value="Annuler"/>
                </div>
            </div>
        </div>
    </div>
</div>

<aui:script>

$(document).ready(function(){
    $('#modalQuitPacte').modal('hide');
});

$("#signPacte").click(function(e){
    var selector = '.pro-bloc-prefooter .pro-signature-pacte > a';
    if($(selector).hasClass('active')){
        console.log("oki doki");
        e.preventDefault();
        $("#modalQuitPacte").modal('show');
    }
    else callServeResource();
});

function callServeResource() {
	
	if(${isUserloggedIn}){
		if($("#type_v_1").is(':checked')) {
		    $('#modalQuitPacte').modal('hide');
            AUI().use('aui-io-request', function(A) {
                A.io.request('${pacteSignatureURL}', {
                    method : 'post',
                    data : {
                        <portlet:namespace/>clauses : $("#type_v_1").is(':checked')
                    },
                    on: {
                        success: function(e) {
                            var selector = '.pro-bloc-prefooter .pro-signature-pacte > a';
                            e.preventDefault();
                            $(selector).toggleClass('active');
                            if($(selector).hasClass('active')){
                                $('h3',selector).text('<liferay-ui:message key="pacte-adhere" />');
                                $('span',selector).css('display','none');
                                if($(selector).hasClass('pro-disabled')){
                                    $('h3',selector).text('<liferay-ui:message key="pacte-sign" />');
                                    $('span',selector).css('display','block');
                                }
                            }
                            else if($(selector).hasClass('pro-disabled')){
                                $('h3',selector).text('<liferay-ui:message key="pacte-sign" />');
                                $('span',selector).css('display','block');
                            }
                            else{
                                $('h3',selector).text('Signer');
                                $('span',selector).css('display','block');
                            }
                        }
                     }
                });
            });
		}
		else {
			alert('<liferay-ui:message key="pacte-clauses-check" />');
		}
	}
	else {
		$("#myModal").modal();
	}
}
</aui:script>