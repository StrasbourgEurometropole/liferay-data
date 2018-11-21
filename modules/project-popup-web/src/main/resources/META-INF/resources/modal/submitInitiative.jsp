<%@ include file="/project-popup-init.jsp" %>

<portlet:resourceURL id="SubmitInitiative" var="submitInitiativeURL">
</portlet:resourceURL>

<div class="pro-modal pro-bloc-pcs-form pro-modal-initiative fade" id="modalInitiative" tabindex="-1" role="dialog" aria-labelledby="modalInitiative">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3>Déposer une initiative</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form>
                <div class="pro-wrapper">
                    <h4>Informations sur l'initiative</h4>
                    <div class="form-group">
                        <label for="titre">Titre de l'initiative <span class="required">*</span></label>
                        <input type="text" class="form-control" id="initiativetitre"/>
                    </div>
                    <div class="form-group">
                        <label for="description">Description <span>*</span></label>
                        <textarea id="initiativedescription" class="form-control" rows="3"></textarea>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <label for="quartiers">Quartiers/territoires</label>
                            <select name="<portlet:namespace />quartier">
                                <c:forEach var="quartier" items="${quartiers}">
                                    <option value="${quartier.categoryId}">${quartier.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="thematique-2">Thématiques</label>
                            <select name="<portlet:namespace />theme">
                                <c:forEach var="theme" items="${thematics}">
                                    <option value="${theme.categoryId}">${theme.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="projet-2">Projets</label>
                            <select name="<portlet:namespace />project">
                                <c:forEach var="project" items="${projects}">
                                    <option value="${project.categoryId}">${project.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <label for="photo">Ajouter une photo</label>
                            <div class="input-group input-file" name="Fichier1">
                                <input type="text" id="photo" class="form-control"/>
                                <span class="input-group-btn"><button class="btn btn-default btn-choose" type="button">Parcourir</button></span>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <label for="video">Ajouter une vidéo</label>
                            <input type="text" class="form-control" id="video" placeholder="Lien youtube, viméo, dailymotion"/>
                        </div>
                    </div>
                </div>
                <div class="pro-wrapper">
                    <h4>Informations sur l'initiateur</h4>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <label for="nom-3">Nom <span class="required">*</span></label>
                            <input type="text" class="form-control" id="nom-3" placeholder="Dupond"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="prenom-3">Prénom</label>
                            <input type="text" class="form-control" id="prenom-3" placeholder="Jean"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="date-3">Date de naissance <span class="required">*</span></label>
                            <input type="text" class="form-control frm_date" id="date-3" placeholder="jj/mm/aaaa" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="adresse-3">Adresse <span class="required">*</span></label>
                            <input type="text" class="form-control" id="adresse-3" />
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <label for="city-3">Ville <span class="required">*</span></label>
                                <input type="text" class="form-control" id="city-3" />
                            </div>
                            <div class="form-code">
                                <label for="code-3">Code postal <span class="required">*</span></label>
                                <input type="text" class="form-control" id="code-3" />
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="email-3">Adresse mail <span class="required">*</span></label>
                            <input type="email" class="form-control" id="email-3" placeholder="jean.dupond@gmail.com">
                        </div>
                        <div class="form-group form-half">
                            <label for="tel-3">Téléphone <span class="required">*</span></label>
                            <input type="text" class="form-control" id="tel-3"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <label for="photo-profil-3">Ajouter une photo de profil</label>
                            <div class="input-group input-file" name="Fichier1">
                                <input type="text" id="photo-profil-3" class="form-control"/>
                                <span class="input-group-btn"><button class="btn btn-default btn-choose" type="button">Parcourir</button></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="optin-3" value="optin">
                        <label for="optin-3">Je certifie sur l'honneur être âgé-e de seize ans ou plus, habiter Strasbourg, ne pas être élu-e au Conseil municipal de la ville de Strasbourg, ainsi
                            que l'exactitude des renseignements ci-dessus. Un justificatif pourra être demandé par la Ville lors de la première rencontre.</label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="optin-10" value="optin">
                        <label for="optin-10">Je consens XXXX (Conditions CNIL à préciser)</label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i>Les services de la Ville vous recontacteront par mail ou par téléphone, dans les meilleurs délais avant deux mois, afin de vous indiquer si la pétition est recevable et
                        si elle pourra ainsi être diffusée pour être signée.</i></p>
                    <p>Contact Mission Participation citoyenne : XXXX</p>
                </div>
                <div class="pro-form-submit">
                    <button id="submitInitiativeButton" type="submit" class="btn btn-default">Déposer l'initiative</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>

$('#submitInitiativeButton').click( function(e){
	e.preventDefault();
	
	var response = validateForm();
    if (response){
	   	AUI().use('aui-io-request', function(A) {
	   		A.io.request('${submitInitiativeURL}', {
	   			method : 'post',
	   			dataType: 'json',
	   			on: {
	   	            success: function(e) {
	   	            	$('#modalInitiative').modal('hide')
	   			 	}
	   			 }
	   		});
	   	});
    }
	
	
});

var namespace = "<portlet:namespace />";
function validateForm()
{
    var result = true;
    var title = $("#"+namespace+"initiativetitle").val();
    var description = $("#"+namespace+"initiativedescription").val();
    var birthday = $("#"+namespace+"initiativebirthday").val();
    var city = $("#"+namespace+"initiativecity").val();
    var address = $("#"+namespace+"initiativeaddress").val();
    var postalcode = $("#"+namespace+"initiativepostalcode").val();
    var phone = $("#"+namespace+"initiativephone").val();
    var legalage = $("#file-petition-legalage").is(":checked");
    var cnil = $("#file-petition-cnil").is(":checked");
    var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");

    if (petitiontitle==null || petitiontitle==""){
        $("#"+namespace+"petitiontitle").css({ "box-shadow" : "0 0 10px #CC0000" });
        result = false;
    }else $("#"+namespace+"petitiontitle").css({ "box-shadow" : "" });

    if (petitiondescription==null || petitiondescription==""){
        $("#"+namespace+"petitiondescription").css({ "box-shadow" : "0 0 10px #CC0000" });
        result = false;
    }else $("#"+namespace+"petitiondescription").css({ "box-shadow" : "" });

    if (birthday==null || birthday==""){
        $("#"+namespace+"initiativebirthday").css({ "box-shadow" : "0 0 10px #CC0000" });
        result = false;
    }else $("#"+namespace+"initiativebirthday").css({ "box-shadow" : "" });

    if (city==null || city==""){
        $("#"+namespace+"initiativecity").css({ "box-shadow" : "0 0 10px #CC0000" });
        result = false;
    }else $("#"+namespace+"initiativecity").css({ "box-shadow" : "" });

    if (address==null || address==""){
        $("#"+namespace+"initiativeaddress").css({ "box-shadow" : "0 0 10px #CC0000" });
        result = false;
    }else $("#"+namespace+"initiativeaddress").css({ "box-shadow" : "" });

    if (postalcode==null || postalcode==""){
        $("#"+namespace+"initiativepostalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
        result = false;
    }else if(!regex.test(postalcode)){
        $("#"+namespace+"initiativepostalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
        alert("Merci de respecter la syntaxe d'un code postal");
        result = false;
    }
    else $("#"+namespace+"postalcode").css({ "box-shadow" : "" });

    if (phone==null || phone==""){
        $("#"+namespace+"phone").css({ "box-shadow" : "0 0 10px #CC0000" });
        result = false;
    }else $("#"+namespace+"phone").css({ "box-shadow" : "" });

    if (!legalage)
        result = false;

    if (!cnil)
        result = false;

    if (!result)
        $("#sendalert").removeClass("hidden");
    else $("#sendalert").addClass("hidden");
    return result;
}

</script>