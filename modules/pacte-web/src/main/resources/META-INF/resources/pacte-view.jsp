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
                                <a href="/accueil"><liferay-ui:message key="pacte.content.breadcrumb.accueil"/></a>
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
                            <li><a href="#" download title="<liferay-ui:message key='pacte.content.annexe.title'/>">become_a_star_citizen.pdf<span class="pro-poids">
                            <liferay-ui:message key="pacte.content.annexe.weight"/> 283ko</span></a>
                            </li>
                            <li><a href="#" download title="<liferay-ui:message key='pacte.content.annexe.title'/>">Messer_era.pdf<span class="pro-poids">
                             <liferay-ui:message key="pacte.content.annexe.weight"/> 263ko</span></a>
                            </li>
                            <li><a href="#" download title="<liferay-ui:message key='pacte.content.annexe.title'/>">the_next_step_is_UEE.pdf<span class="pro-poids">
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

            <div class="pro-bloc-accordion">
                <div class="col-xs-12"><h2><liferay-ui:message key="pacte.content.article"/></h2></div>

                <div class="panel-group col-sm-9" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h3 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" title="Nom de l'article">
                                    <liferay-ui:message key="pacte.content.article.premier"/>
                                </a>
                            </h3>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                <p><liferay-ui:message key="pacte.content.article.premier.intro"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.premier.titre1"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.premier.titre1.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.premier.titre1.content2"/></p>
                                <p><liferay-ui:message key="pacte.content.article.premier.titre1.content3"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.premier.titre2"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.premier.titre2.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.premier.titre2.content2"/></p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h3 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo" title="Nom de l'article">
                                    <liferay-ui:message key="pacte.content.article.deux"/>
                                </a>
                            </h3>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <p><liferay-ui:message key="pacte.content.article.deux.intro"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.deux.titre1"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.deux.titre1.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.deux.titre1.content2"/></p>
                                <p><liferay-ui:message key="pacte.content.article.deux.titre1.content3"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.deux.titre2"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.deux.titre2.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.deux.titre2.content2"/></p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h3 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree" title="Nom de l'article">
                                    <liferay-ui:message key="pacte.content.article.trois"/>
                                </a>
                            </h3>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
                            <div class="panel-body">
                                <p><liferay-ui:message key="pacte.content.article.trois.intro"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.trois.titre1"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.trois.titre1.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.trois.titre1.content2"/></p>
                                <p><liferay-ui:message key="pacte.content.article.trois.titre1.content3"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.trois.titre2"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.trois.titre2.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.trois.titre2.content2"/></p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFour">
                            <h3 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="true" aria-controls="collapseFour" title="Nom de l'article">
                                    <liferay-ui:message key="pacte.content.article.quatre"/>
                                </a>
                            </h3>
                        </div>
                        <div id="collapseFour" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFour">
                            <div class="panel-body">
                                <p><liferay-ui:message key="pacte.content.article.quatre.intro"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.quatre.titre1"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.quatre.titre1.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.quatre.titre1.content2"/></p>
                                <p><liferay-ui:message key="pacte.content.article.quatre.titre1.content3"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.quatre.titre2"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.quatre.titre2.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.quatre.titre2.content2"/></p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFive">
                            <h3 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="true" aria-controls="collapseFive" title="Nom de l'article">
                                    <liferay-ui:message key="pacte.content.article.cinq"/>
                                </a>
                            </h3>
                        </div>
                        <div id="collapseFive" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFive">
                            <div class="panel-body">
                                <p><liferay-ui:message key="pacte.content.article.cinq.intro"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.cinq.titre1"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.cinq.titre1.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.cinq.titre1.content2"/></p>
                                <p><liferay-ui:message key="pacte.content.article.cinq.titre1.content3"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.cinq.titre2"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.cinq.titre2.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.cinq.titre2.content2"/></p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingSix">
                            <h3 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="true" aria-controls="collapseSix" title="Nom de l'article">
                                    <liferay-ui:message key="pacte.content.article.six"/>
                                </a>
                            </h3>
                        </div>
                        <div id="collapseSix" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingSix">
                            <div class="panel-body">
                                <p><liferay-ui:message key="pacte.content.article.six.intro"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.six.titre1"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.six.titre1.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.six.titre1.content2"/></p>
                                <p><liferay-ui:message key="pacte.content.article.six.titre1.content3"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.six.titre2"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.six.titre2.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.six.titre2.content2"/></p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingSeven">
                            <h3 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="true" aria-controls="collapseSeven" title="Nom de l'article">
                                    <liferay-ui:message key="pacte.content.article.sept"/>
                                </a>
                            </h3>
                        </div>
                        <div id="collapseSeven" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingSeven">
                            <div class="panel-body">
                                <p><liferay-ui:message key="pacte.content.article.sept.intro"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.sept.titre1"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.sept.titre1.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.sept.titre1.content2"/></p>
                                <p><liferay-ui:message key="pacte.content.article.sept.titre1.content3"/></p>
                                <h4><liferay-ui:message key="pacte.content.article.sept.titre2"/></h4>
                                <p><liferay-ui:message key="pacte.content.article.sept.titre2.content1"/></p>
                                <p><liferay-ui:message key="pacte.content.article.sept.titre2.content2"/></p>
                            </div>
                        </div>
                    </div>
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
                                    <input type="checkbox" id="type_v_2" value="optin">
                                    <label for="type_v_2"><liferay-ui:message key="pacte.label"/></label>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-6 col-xs-12">
                    <div>
                        <p><a href="/signataires">${nbSignataires} <liferay-ui:message key="pacte.adhere.personnes"/></a> <liferay-ui:message key="pacte.adhere"/></p>
                        <span class="pro-you"><liferay-ui:message key="pacte.adhere.and.you"/></span>
                    </div>
                    <c:if test="${hasUserSigned}">
                        <a id="SignerPacte" href="#" class="pro-btn-signer active"><liferay-ui:message key="pacte.already.adhere"/></a>
                    </c:if>
                    <c:if test="${!hasUserSigned}">
                        <a id="SignerPacte" href="#" class="pro-btn-signer"><liferay-ui:message key="pacte.sign"/></a>
                    </c:if>
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
                    <input id="buttonConfirmQuit" onclick="callServeResource();" type="submit" class="pro-btn" value="<liferay-ui:message key='modal.quit.resilier'/>"/>
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

$("#SignerPacte").click(function(e){
    if($(this).hasClass('active')){
        console.log("oki doki");
        e.preventDefault();
        $("#modalQuitPacte").modal('show');
    }
    else callServeResource();
});

function callServeResource() {
	
	if(${isUserloggedIn}){
		if($("#type_v_2").is(':checked')) {
		    $('#modalQuitPacte').modal('hide');
            AUI().use('aui-io-request', function(A) {
                A.io.request('${pacteSignatureURL}', {
                    method : 'post',
                    data : {
                        <portlet:namespace/>clauses : $("#type_v_2").is(':checked')
                    },
                    on: {
                        success: function(e) {
                            e.preventDefault();
                            $("#SignerPacte").toggleClass('active');
                            if($("#SignerPacte").hasClass('active')){
                                $("#SignerPacte").text('<liferay-ui:message key="pacte.already.adhere" />');
                            }
                            else{
                                $("#SignerPacte").text('<liferay-ui:message key="pacte.sign" />');
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