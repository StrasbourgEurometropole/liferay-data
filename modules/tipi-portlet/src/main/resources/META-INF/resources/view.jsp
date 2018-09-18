<%@ include file="/init.jsp"%>
<div id="tipi">
	<c:if test="${empty form}">
		<p>Veuillez choisir un formulaire sur la page de configuration</p>
	</c:if>
	<c:if test="${not empty form}">
	    <script>
	        // On bootstrap l'URL de paiement
	        window.tipiURL = '${billingURL}';
	        window.tipiCallbackURL = '${callbackURL}';
	    </script>
	    <div class="bill-form ${form}">
	    	<h2>${formTitle}</h2>
	    	
	    	<c:if test="${form eq 'reom'}">
				<div class="reom-commune">Seules les communes suivantes sont concern&eacute;es : Achenheim, Breuschwickersheim, Hangenbieten, Kolbsheim, Osthoffen</div>
			</c:if>
	    	
	        <form action="" class="form" enctype="multipart/form-data" id="tipiForm" method="post" name="tipiForm">
	            <input id="code_appli" name="appCode" type="hidden" value="${appCode}" /> 
	            <input id="num_client" name="clientNumber" type="hidden" value="${clientNumber}" />
	            <div id="tipi-page">
	                <div style=
	                "position:relative; background:#f2f0e3 url('/o/tipi.portlet/images/${form}.jpg') top center no-repeat; background-size: contain; height:870px; z-index:1;">
		                <div class="year-field">
	                        <span class="year"><label for="year" id="field-label">(1) L'Ann&eacute;e :</label> *</span>
	                        <span class="field-input"><input class='inputText' id='year' maxlength='4' name="year"
	                        size='6' type='text' value="" /></span>
	                    </div>
	                    <div class="ref-field">
	                        <span class="ref"><label for="ref" id="field-label">(2) N&deg; Facture :</label>*</span>
	                        <span class="field-input"><input class="inputText" id="ref" maxlength="13" name="ref" size=
	                        "13" type="text" value="" /></span>
	                    </div>
	                    <div class="price-field">
	                        <span class="price"><label for="price" id="field-label">(3) Montant :</label>*</span>
	                        <span class="field-input"><input class="inputText" id="integerPrice" maxlength="4" name=
	                        "integerPrice" size="3" style="text-align: right;" type="text" value="">, <input class=
	                        "inputText" id="decimalPrice" maxlength="2" name="decimalPrice" size="1" type="text" value=
	                        ""> &euro;</span>
	                    </div>
                    </div>
	                <table id="table-of-fields">
	                    <tr>
	                        <td colspan="3">
	                            <div class="mail-field">
	                                <span class="mail"><label for="email" id="field-label">(4) Votre E-mail
	                                :</label>*</span> <span class="mail-input"><input class="inputText" id="email"
	                                maxlength="70" name="email" size="50" type="text" value=""></span>
	                            </div>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="3">
	                            <div class="mail-field">
	                                <span class="mail"><label for="emailConfirm" id="field-label">(5) Votre E-mail
	                                :</label>*</span> <span class="mail-input"><input class="inputText" id=
	                                "emailConfirm" maxlength="70" name="emailConfirm" size="50" type="text" value=
	                                ""></span> <span class="red">(confirmation)</span>
	                            </div>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="3">
	                            <div class="clear">
	                                &nbsp;
	                            </div>
	                            <div class="field">
	                                <label style="font-size: 12px;">*Champ obligatoire</label>
	                            </div>
	                        </td>
	                    </tr>
	                </table>		
	            </div>	
	            <p class="errors"></p>
	           
	            <div class="help">
	            	<div>
	            		<span style="color: red;">(1)</span> L'ann&eacute;e contient 4 caract&egrave;res
	            	</div>
	            	<div>
	            		<span style="color: red;">(2)</span> Num&eacute;ro de facture est compos&eacute; uniquement de chiffres
	            	</div>
	            	<div>
	            		<span style="color: red;">(3)</span> Le montant doit &ecirc;tre saisi avec les d&eacute;cimales apr&egrave;s la virgule
	            	</div>
	            	<div>
	            		<span style="color: red;">(4), (5)</span> Les adresses mails doivent &ecirc;tre valides et identiques
	            	</div>
	            </div> 
	            <div class="buttons">
	            	<input class="reset-button" name="Effacer" title="Effacer les champs de saisie" type="button" value="Effacer">
	            	<input class="button" name="submitBill" title="Valider le formulaire" type="submit" value="Envoyer"> 
	            	<input class="reset-button" style="float: right;" name="Fermer" onclick="window.location.href='http://' + window.location.hostname" title="Aller &agrave; la page principale" type="button" value="Fermer">
	            </div>
	            <div class="clear">
	                &nbsp;
	            </div>
	        </form>
	    </div>
	    <div class="clear">
	        &nbsp;
	    </div>
	</c:if>
</div>