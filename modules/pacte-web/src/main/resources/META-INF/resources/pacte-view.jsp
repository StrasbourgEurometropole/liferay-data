<%@ include file="/pacte-init.jsp"%>

<portlet:resourceURL id="pacteSignature" var="pacteSignatureURL">
</portlet:resourceURL>

<div id="content" class="pro-page-pacte">
    <section class="container">
        <div class="row">

            <div class="col-xs-12 pro-wrapper-title">
                <h1><span><liferay-ui:message key="pacte.content.title.le"/></span><liferay-ui:message key="pacte.content.title.pacte"/></h1>
                <span class="pro-surtitre"><liferay-ui:message key="pacte.content.title.subtitle"/></span>
            </div>
            <div class="pro-wrapper-content">
                <div class="col-xs-12">
                    <div id="breadcrumb">
                        <span>
                            <span>
                                <a href="index.html"><liferay-ui:message key="pacte.content.breadcrumb.accueil"/></a>
                                <span class="breadcrumb_last"><liferay-ui:message key="pacte.content.breadcrumb.pacte"/></span>
                            </span>
                        </span>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="pro-preambule">
                        <h2 class="pro-title"><liferay-ui:message key="pacte.content.preambule.title"/></h2>
                        <p><liferay-ui:message key="pacte.content.preambule.paragraphe.un"/></p>
                        <p><liferay-ui:message key="pacte.content.preambule.paragraphe.deux"/></p>
                        <figure class="fit-cover" role="group">
                            <img src="/o/plateforme-citoyenne-theme/images/medias/img-pacte.jpg" width="546" height="170" alt="<liferay-ui:message key="pacte.content.preambule.image.alt"/>"/>
                        </figure>
                        <h3><liferay-ui:message key="pacte.content.preambule.paragraphe.trois"/></h3>
                        <p><liferay-ui:message key="pacte.content.preambule.paragraphe.quatre"/></p>
                        <p><liferay-ui:message key="pacte.content.preambule.paragraphe.cinq"/></p>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="pro-annexes">
                        <h3><liferay-ui:message key="pacte.content.annexe"/></h3>
                        <ul>
                            <li><a href="#" download title="<liferay-ui:message key='pacte.content.annexe.title'/>">Calendrier prévisionnel de mise en œuvre des principales actions du pacte pour la démocratie à Strasbourg.pdf<span class="pro-poids">
                            <liferay-ui:message key="pacte.content.annexe.weight"/> 283ko</span></a>
                            </li>
                            <li><a href="#" download title="<liferay-ui:message key='pacte.content.annexe.title'/>">Préambule des élèves des collèges du Parc et de Kléber.pdf<span class="pro-poids">
                             <liferay-ui:message key="pacte.content.annexe.weight"/> 263ko</span></a>
                            </li>
                            <li><a href="#" download title="<liferay-ui:message key='pacte.content.annexe.title'/>">Projet de délibération.pdf<span class="pro-poids">
                             <liferay-ui:message key="pacte.content.annexe.weight"/> 380ko</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="pro-wrapper-pacte">
                <div>
                    <div class="pro-wrapper">
                        <span class="pro-big-letter">P</span>
                        <h2><liferay-ui:message key="pacte.content.valeurs"/></h2>
                        <h3><liferay-ui:message key="pacte.content.valeurs.subtitle"/></h3>
                        <p><liferay-ui:message key="pacte.content.valeurs.content"/></p>
                    </div>

                    <div class="pro-wrapper">
                        <span class="pro-big-letter pro-big-letter-a">A</span>
                        <h2><liferay-ui:message key="pacte.content.principes"/></h2>
                        <h3><liferay-ui:message key="pacte.content.principes.subtitle1"/></h3>
                        <p><liferay-ui:message key="pacte.content.principes.subtitle1.content1"/></p>
                        <p><liferay-ui:message key="pacte.content.principes.subtitle1.content2"/></p>

                        <h3><liferay-ui:message key="pacte.content.principes.subtitle2"/></h3>
                        <p><liferay-ui:message key="pacte.content.principes.subtitle2.content1"/></p>
                        <p><liferay-ui:message key="pacte.content.principes.subtitle2.content2"/></p>

                        <h3><liferay-ui:message key="pacte.content.principes.subtitle3"/></h3>
                        <p><liferay-ui:message key="pacte.content.principes.subtitle3.content1"/></p>
                    </div>
                </div>

                <div>
                    <div class="pro-wrapper">
                        <span class="pro-big-letter">C</span>
                        <h2><liferay-ui:message key="pacte.content.engagements"/></h2>
                        <h3><liferay-ui:message key="pacte.content.engagements.subtitle1"/></h3>
                        <p><liferay-ui:message key="pacte.content.engagements.subtitle1.content"/></p>

                        <h3><liferay-ui:message key="pacte.content.engagements.subtitle2"/></h3>
                        <p><liferay-ui:message key="pacte.content.engagements.subtitle2.content"/></p>

                        <h3><liferay-ui:message key="pacte.content.engagements.subtitle3"/></h3>
                        <p><liferay-ui:message key="pacte.content.engagements.subtitle3.content1"/></p>
                        <p><liferay-ui:message key="pacte.content.engagements.subtitle3.content2"/></p>
                        <p><liferay-ui:message key="pacte.content.engagements.subtitle3.content3"/></p>
                    </div>

                    <div class="pro-wrapper">
                        <span class="pro-big-letter pro-big-letter-t">T</span>
                        <h2><liferay-ui:message key="pacte.content.effectivite"/></h2>
                        <p><liferay-ui:message key="pacte.content.effectivite.content1"/></p>
                        <p><liferay-ui:message key="pacte.content.effectivite.content2"/></p>
                        <p><liferay-ui:message key="pacte.content.effectivite.content3"/></p>
                    </div>

                    <span class="pro-big-letter pro-big-letter-e">E</span>
                </div>
            </div>






        </div>
    </section>


    <div class="pro-bloc-prefooter pro-sticky-bar">
        <div class="container">
            <div class="row pro-pencil">
                <div class="col-md-6 col-xs-12">
                    <div class="pro-bloc-pcs-form">
                        <form>
                            <div class="pro-optin form-checkbox">
                                <div>
                                    <input type="checkbox" id="optin-2" value="optin">
                                    <label for="optin-2"><liferay-ui:message key="pacte.label"/></label>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-6 col-xs-12">
                    <div>
                        <p><a href="page-signataire.html">3600 <liferay-ui:message key="pacte.adhere.personnes"/></a> <liferay-ui:message key="pacte.adhere"/></p>
                        <span class="pro-you"><liferay-ui:message key="pacte.adhere.and.you"/></span>
                    </div>
                    <a href="#SignerPacte" class="pro-btn-signer"><liferay-ui:message key="pacte.sign"/></a>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- CONFIRMATION QUITTER -->
<!-- HTML pour confirmer la résiliation du pacte -->
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
                    <input id="buttonConfirmQuit" onclick="callServeResource();" type="submit" class="pro-btn" value="R�silier"/>
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
			alert('<liferay-ui:message key="pacte.clauses.check" />');
		}
	}
	else {
		$("#myModal").modal();
	}
}
</aui:script>