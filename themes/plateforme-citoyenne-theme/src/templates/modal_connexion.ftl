<nav class="modal_connexion">
    <!-- HTML pour la modal de connexion -->
    <div class="pro-modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal">
            <div class="modal-dialog" role="document">                              
					<#if request.session.getAttribute("publik_logged_in")!false>
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-ico-close"></span></span></button>
							</div>  
								<div class="modal-body"> 
									<div class="pro-txt" style="margin : 32px">
										<h2>Le Pacte</h2>
										<span class="pro-soustitre">Nul n'est censé l'ignorer</span>
										<p>Vous devez avoir préalablement lu & signé le Pacte de la Démocratie locale pour agir sur le site.</p>
										<a href="#" onClick="document.location.href=getUrlWithRedirection();" class="pro-btn-yellow" title="Aller vers la page du Pacte">
											Consulter le pacte
										</a>
									</div>
								</div>
						</div><!-- /.modal-content -->
					<#else>
						<div class="modal-content" style="background-color : #11ffee00">
							<div class="modal-header modal-connexion">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true"><span class="icon-ico-close"></span></span>
								</button>
							</div>  
								<div class="modal-body"> 
									<div class="pro-reagir">
										<div>
											<h3>Se connecter</h3>
											<p>Vous devez être identifié avec votre compte MonStrasbourg.eu pour agir sur le site.</p>                          
											<a href="${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))}" class="pro-btn-yellow" title="Connexion">
												Se connecter
											</a>									
										</div>
									</div>
								</div>
						</div><!-- /.modal-content -->
					</#if>           
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
</nav>

<#-- Script de gestion -->
<script type="text/javascript">
	function getUrlWithRedirection() {
		var pacteURL = "${themeDisplay.getPortalURL()}${homeURL}pacte";
		var currentURL = window.location.href;
		return pacteURL + "?redirectURL=" + encodeURIComponent(currentURL);
	}
</script>