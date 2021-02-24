<nav class="modal_connexion">
    <!-- HTML pour la modal de connexion -->
    <div class="pro-modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal">
            <div class="modal-dialog" role="document">           
					<div class="modal-content" style="background-color : #11ffee00">
						<div class="modal-header modal-connexion">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true"><span class="icon-ico-close"></span></span>
							</button>
						</div>  
							<div class="modal-body"> 
								<div class="pro-reagir">
									<div>
										<h3><@liferay.language key='eu.login' /></h3>
										<p><@liferay.language key='eu.help.need-connexion'/></p>                          
										<a href="${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))?html}" class="pro-btn-yellow" title="Connexion">
											<@liferay.language key='eu.login' />
										</a>									
									</div>
								</div>
							</div>
					</div><!-- /.modal-content -->      
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