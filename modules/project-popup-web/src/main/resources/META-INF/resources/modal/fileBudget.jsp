<%@ include file="/project-popup-init.jsp" %>
<portlet:resourceURL id="fileBudget" var="fileBudgetURL">
</portlet:resourceURL>
<!-- DEPOSER UN NOUVEAU BUDGET -->
<!-- HTML pour la modal de budget -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalBudget" tabindex="-1" role="dialog" aria-labelledby="modalProjet">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="pro-modal-top">
                        <h3>Soumettre un projet</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
                    </div>

                    <form>
                        <div class="pro-wrapper">
                            <h4>Informations sur le projet</h4>
                            <div class="form-group">
                                <label for="titre">Titre du projet <span class="required">*</span></label>
                                <input type="text" class="form-control" id="titre"/>
                            </div>
                            <div class="form-group">
                                <label for="description">Description <span>*</span></label>
                                <textarea id="description" class="form-control" rows="3"></textarea>
                            </div>
                            <div class="pro-row">
                                <div class="form-group form-half">
                                    <label for="quartiers">Quartiers</label>
                                    <select id="quartiers">
                                        <option>Krutenau</option>
                                        <option>Wacken</option>
                                    </select>
                                </div>
                                <div class="form-group form-half">
                                    <label for="lieux">Lieux</label>
                                    <input type="text" class="form-control" id="lieux"/>
                                </div>
                            </div>
                            <div class="pro-row">
                                <div class="form-group form-half">
                                    <label for="thematiques">Thématiques</label>
                                    <select id="thematiques">
                                        <option>Thématique 1</option>
                                        <option>Thématique 2</option>
                                    </select>
                                </div>
                                <div class="form-group form-half">
                                    <label for="projets">Projets</label>
                                    <select id="projets">
                                        <option>Projets 1</option>
                                        <option>Projets 2</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lieux">Lieu(x)</label>
                                <input type="text" class="form-control" id="lieux"/>
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
                            <h4>Informations sur le pétitionnaire</h4>
                            <div class="pro-row">
                                <div class="form-group form-half">
                                    <label for="nom">Nom <span class="required">*</span></label>
                                    <input type="text" class="form-control" id="nom" placeholder="Dupond"/>
                                </div>
                                <div class="form-group form-half">
                                    <label for="prenom">Prénom <span class="required">*</span></label>
                                    <input type="text" class="form-control" id="prenom" placeholder="Jean"/>
                                </div>
                            </div>
                            <div class="pro-row">
                                <div class="form-group form-half">
                                    <label for="adresse">Adresse <span class="required">*</span></label>
                                    <input type="text" class="form-control" id="adresse"/>
                                </div>
                                <div class="form-group form-half">
                                    <div class="form-city">
                                        <label for="city">Ville <span class="required">*</span></label>
                                        <input type="text" class="form-control" id="city"/>
                                    </div>
                                    <div class="form-code">
                                        <label for="code">Code postal <span class="required">*</span></label>
                                        <input type="text" class="form-control" id="code"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email">Adresse mail <span class="required">*</span></label>
                                <input type="email" class="form-control" id="email" placeholder="jean.dupond@gmail.com">
                            </div>
                            <div class="pro-row">
                                <div class="form-group form-half">
                                    <label for="tel_fixe">Téléphone Fixe</label>
                                    <input type="text" class="form-control" id="tel_fixe"/>
                                </div>
                                <div class="form-group form-half">
                                    <label for="tel_mobile">Téléphone mobile</label>
                                    <input type="text" class="form-control" id="tel_mobile"/>
                                </div>
                            </div>
                        </div>
                        <div class="pro-optin form-checkbox">
                            <div>
                                <input type="checkbox" id="optin" value="optin">
                                <label for="optin">Je dispose des droits lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
                                    aliqua.</label>
                            </div>
                        </div>
                        <div class="pro-optin form-checkbox">
                            <div>
                                <input type="checkbox" id="optin-2" value="optin">
                                <label for="optin-2">Un participant au budget participatif je certifie lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                    labore et dolore magna aliqua.</label>
                            </div>
                        </div>
                        <div class="pro-form-submit">
                            <button type="submit" class="btn btn-default">Soumettre le projet</button>
                        </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->


<!-- CONFIRMATION NOUVEAU BUDGET -->
<!-- HTML pour la modal de confirmation de nouvelle pétition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalConfirmerBudget" tabindex="-1" role="dialog" aria-labelledby="modalConfirmerBudget">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='confirm-budget'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='file-budget-ok'/></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-budget-ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>


<!-- ERREUR NOUVELLE BUDGET -->
<!-- HTML pour la modal d'erreur de nouvelle pÃÂ©tition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalErrorBudget" tabindex="-1" role="dialog" aria-labelledby="modalErrorBudget">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='error-budget'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-budget-ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<!-- CONFIRMATION QUITTER BUDGET -->
<!-- HTML pour la modal de quitter le formulaire de pÃÂ©tition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalQuitBudget" tabindex="-1" role="dialog" aria-labelledby="modalQuitBudget">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='quit-budget'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='file-budget-quit'/></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-budget-quit"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

	var namespace = "<portlet:namespace />";
	var saved_address = "${userConnected.get('address')}";
	var saved_zipCode = "${userConnected.get('zipcode')}";
	var saved_city = "${userConnected.get('city')}";
	var saved_dateNaiss = "${formattedDate}";
	var saved_phone = "${userConnected.get('phone')}";
	var saved_mobile = "${userConnected.get('mobile')}";

    $(document).ready(function(){
        $('#modalConfirmerBudget').modal('hide');
        $('#modalErrorBudget').modal('hide');
        $('#checkboxSaveInfo').hide();

        $('#buttonDeposer').click(function(event){
            resetValues();
        });
    });

    $("#sendBudget").click(function(event){
        event.preventDefault();

        var response = validateForm();
        if (response){
            var budgetTitleValue = $("#"+namespace+"budgettitle").val();
            var budgetDescriptionValue = $("#"+namespace+"budgetdescription").val();
            var birthdayValue = $("#"+namespace+"birthday").val();
            var addressValue = $("#"+namespace+"address").val();
            var cityValue = $("#"+namespace+"city").val();
            var postalcodeValue = $("#"+namespace+"postalcode").val();
            var phoneValue = $("#"+namespace+"phone").val();
            var mobileValue = $("#"+namespace+"mobile").val();
            var projectValue = $("#"+namespace+"project").val();
            var quartierValue = $("#"+namespace+"quartier").val();
            var themeValue = $("#"+namespace+"theme").val();
            var consultationPlacesTextValue = $("#"+namespace+"budgetlieux").val();
            var saveInfoValue = $("#save-info").is(":checked");
            var lastNameValue = $("#"+namespace+"username").val();
            var firstNameValue = $("#"+namespace+"firstname").val();
            var emailValue = $("#"+namespace+"mail").val();
            AUI().use('aui-io-request', function(A) {
                A.io.request('${fileBudgetURL}', {
                    method : 'POST',
                    dataType: 'json',
                    data:{
                        <portlet:namespace/>budgettitle:budgetTitleValue,
                        <portlet:namespace/>budgetdescription:budgetDescriptionValue,
                        <portlet:namespace/>birthday:birthdayValue,
                        <portlet:namespace/>address:addressValue,
                        <portlet:namespace/>city:cityValue,
                        <portlet:namespace/>postalcode:postalcodeValue,
                        <portlet:namespace/>phone:phoneValue,
                        <portlet:namespace/>mobile:mobileValue,
                        <portlet:namespace />project:projectValue,
                        <portlet:namespace />quartier:quartierValue,
                        <portlet:namespace />theme:themeValue,
                        <portlet:namespace />consultationPlacesText:consultationPlacesTextValue,
                        <portlet:namespace />saveinfo:saveInfoValue,
                        <portlet:namespace />lastname:lastNameValue,
                        <portlet:namespace />firstname:firstNameValue,
                        <portlet:namespace />email:emailValue
                    },
                    on: {
                        success: function(e) {
                            var data = this.get('responseData');
                            if(data.result){
                                $('#modalBudget').modal('hide');
                                if(data.savedInfo){
                                    saved_dateNaiss = $("#"+namespace+"birthday").val();
                                    saved_city = $("#"+namespace+"city").val();
                                    saved_address = $("#"+namespace+"address").val();
                                    saved_zipCode = $("#"+namespace+"postalcode").val();
                                    if($("#"+namespace+"phone").val() != "")
                                        saved_phone = $("#"+namespace+"phone").val();
                                    if($("#"+namespace+"mobile").val() != "")
                                        saved_mobile = $("#"+namespace+"mobile").val();
                                }
                                $('#modalConfirmerBudget').modal('show');
                            }else{
                                $("#modalErrorBudget h4").text(data.message);
                                $('#modalErrorBudget').modal('show');
                            }
                        }
                    }
                });
             });
        }
    });

    $('#modalConfirmerBudget #buttonConfirm').click(function(event){
        $('#modalConfirmerBudget').modal('hide');
    });

    $('#modalErrorBudget #buttonConfirm').click(function(event){
        $('#modalErrorBudget').modal('hide');
    });

    function resetValues()
    {
        $("#"+namespace+"budgettitle").val("");
        $("#"+namespace+"budgetdescription").val("");
        $("#"+namespace+"budgetlieux").val("");
        $("#"+namespace+"project option[value='0']").prop('selected', true);
        $("#"+namespace+"project").selectric();
        $("#"+namespace+"quartier option[value='0']").prop('selected', true);
        $("#"+namespace+"quartier").selectric();
        $("#"+namespace+"theme option[value='0']").prop('selected', true);
        $("#"+namespace+"theme").selectric();
        $('#checkboxSaveInfo #save-info').prop('checked', false);
        $('#checkboxSaveInfo').hide();
        $("#file-budget-legalage").prop("checked", false);
        $("#file-budget-cnil").prop("checked", false);
        $("#"+namespace+"birthday").val(saved_dateNaiss);
        $("#"+namespace+"city").val(saved_city);
        $("#"+namespace+"address").val(saved_address);
        $("#"+namespace+"postalcode").val(saved_zipCode);
        $("#"+namespace+"phone").val(saved_phone);
        $("#"+namespace+"mobile").val(saved_mobile);
    }

    function checkValues(){
        if($("#"+namespace+"birthday").val() != saved_dateNaiss || $("#"+namespace+"address").val() != saved_address ||
        $("#"+namespace+"city").val() != saved_city || $("#"+namespace+"postalcode").val() != saved_zipCode ||
        $("#"+namespace+"phone").val() != saved_phone || $("#"+namespace+"mobile").val() != saved_mobile){
            $('#checkboxSaveInfo #save-info').prop('checked', true);
            $('#checkboxSaveInfo').show();
        }else{
            $('#checkboxSaveInfo #save-info').prop('checked', false);
            $('#checkboxSaveInfo').hide();
        }
    }

    function validateForm()
    {
        var result = true;
        var budgettitle = $("#"+namespace+"budgettitle").val();
        var budgetdescription = $("#"+namespace+"budgetdescription").val();
        var birthday = $("#"+namespace+"birthday").val();
        var city = $("#"+namespace+"city").val();
        var address = $("#"+namespace+"address").val();
        var postalcode = $("#"+namespace+"postalcode").val();
        var legalage = $("#file-budget-legalage").is(":checked");
        var cnil = $("#file-budget-cnil").is(":checked");
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");

        if (budgettitle==null || budgettitle==""){
            $("#"+namespace+"budgettitle").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"budgettitle").css({ "box-shadow" : "" });

        if (budgetdescription==null || budgetdescription==""){
            $("#"+namespace+"budgetdescription").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"budgetdescription").css({ "box-shadow" : "" });

        if (birthday==null || birthday==""){
            $("#"+namespace+"birthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"birthday").css({ "box-shadow" : "" });

        if (city==null || city==""){
            $("#"+namespace+"city").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"city").css({ "box-shadow" : "" });

        if (address==null || address==""){
            $("#"+namespace+"address").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"address").css({ "box-shadow" : "" });

        if (postalcode==null || postalcode==""){
            $("#"+namespace+"postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else if(!regex.test(postalcode)){
            $("#"+namespace+"postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            alert("Merci de respecter la syntaxe d'un code postal");
            result = false;
        }
        else $("#"+namespace+"postalcode").css({ "box-shadow" : "" });

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