<!-- SLIDER AGENDA -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <h2>L’agenda</h2>
        <div class="owl-carousel owl-opacify owl-theme owl-cards">
        
            <!-- Parcours des entites de l'asset publisher -->
            <#list entries as curEntry>
            
           <div class="item pro-bloc-card-participation pro-theme-information" data-linkall="a">
                        <div>
                            <div class="pro-header-participation">
                                <figure role="group">
                                    <img src="assets/images/medias/comm-mathilde.jpg" width="40" height="40" alt="Arrière plan page standard"/>
                                </figure>
                                <p>Concertation publiée par :</p>
                                <p><strong>Ville de Strasbourg</strong></p>
                            </div>
                            <div class="pro-content-participation">
                                <a href="detail-participation.html" title="lien de la page"><h3>Titre de l’Évènement<br>Sur deux lignes</h3></a>
                                <span class="pro-time">Publiée le <time datetime="2018-01-10">10/04/2018</time> / <span class="pro-duree">Fin dans 11 jours</span></span>
                            </div>
                            <div class="pro-footer-participation">
                                <a href="detail-participation.html#pro-link-commentaire" class="pro-form-style" title="Lien vers la page détail Participation - Lien des commentaires">Réagissez...</a>
                            </div>
                        </div>
                    </div> 
           
            </#list>
        </div>       
    </div>
</section>