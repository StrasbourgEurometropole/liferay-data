<nav class="modal_connexion">
    <!-- HTML pour la modal de connexion -->
    <div class="pro-modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-ico-close"></span></span></button>
                    </div>
                    <div class="modal-body">                      
                        <#if request.session.getAttribute("publik_logged_in")!false>
							<div class="pro-txt">
								<h2>Le Pacte</h2>
								<span class="pro-soustitre">Nul n'est censé l'ignorer</span>
								<p>Vous devez avoir préalablement lu & signé le Pacte de la Démocratie locale pour agir sur le site.</p>
								<a href="${themeDisplay.getPortalURL()}${homeURL}pacte" class="pro-btn-yellow" title="Aller vers la page du Pacte">Consulter le pacte</a>
							</div>
                        <#else>
							<div class="pro-reagir">
								<div>
									<h3>Se connecter</h3>
									<p>Vous devez être identifié avec votre compte MonStrasbourg.eu pour agir sur le site.</p>                          
									<a href="${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))}" class="pro-btn-yellow" title="Connexion">Se connecter</a>									
								</div>
							</div>
                        </#if>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
</nav>