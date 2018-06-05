<nav class="modal_connexion">
    <!-- HTML pour la modal de connexion -->
    <div class="pro-modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-ico-close"></span></span></button>
                    </div>
                    <div class="modal-body">                      
                        <#if request.session.getAttribute("publik_logged_in")>
                            <div class="col-sm-6">
                                <div class="pro-txt">
                                    <h2>Le Pacte</h2>
                                    <span class="pro-soustitre">Nul n'est censé l'ignorer</span>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                    <a href="${themeDisplay.getPortalURL()}${homeURL}pacte" class="pro-btn-yellow" title="Aller vers la page du Pacte">Consulter le pacte</a>
                                </div>
                            </div>
                        </#if>
                        <#if !request.session.getAttribute("publik_logged_in")>
                            <div class="col-sm-6">
                                <div class="pro-reagir">
                                    <div>
                                        <h3>Se connecter</h3>
                                        <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboa commodo consequat.</p>                          
                                        <a href="${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))}" class="pro-btn-yellow" title="Connexion">Se connecter</a>
                                        
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </div>
                    <div class="modal-header"/>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
</nav>