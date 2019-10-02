<!-- DETAIL D'UN BUDGET PARTICIPATIF -->

<#-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<#-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#assign homeURL2 = "/web${layout.group.friendlyURL}" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#-- Récupération de l'ID de l'utilisateur -->
<#assign userID = request.session.getAttribute("publik_internal_id")!"" />

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />
<#assign hasUserPactSign = request.session.getAttribute("has_pact_signed")!false />
<#assign isUserBanned = request.session.getAttribute("is_banish")!false />

<#-- Récuperation du statut BP et affichage ou non du motif si le budget n'est pas retenu pour la réalisation -->
<#assign statusBP = entry.getBudgetParticipatifStatusTitle(locale) >
<#assign motifPrintable = false >
<#if statusBP == "Non faisable" || statusBP == "Non recevable" || statusBP == "Non retenu" || statusBP == "Annulé" || statusBP == "Suspendu" && entry.motif?has_content >
    <#assign motifPrintable = true >
</#if>

<#-- Recuperation de la couleur hexa correspondant au type de la participation -->
<#assign statusColor = entry.getBudgetParticipatifStatusCategoryColor() />

<#-- Initialisation des conteneurs de vignettes -->
<#assign budgetParticipatifJSON = entry.toJSON(userID) />

<#-- Récupération des liens médias de l'entité -->
<#assign videoURL = entry.videoUrl />
<#assign imageURL = entry.getImageURL() />

<#-- L'entité est elle en période de vote -->
<#assign isVotable = entry.isVotable() />

<#-- Est-ce l'autheur de l'entité ? -->
<#assign isAuthor = entry.publikId == userID />

<#-- L'entité peut être modifiée -->
<#assign isEditable = entry.isEditable() />

<#-- Récupération des liens médias de l'entité -->
<#assign videoURL = entry.videoUrl />
<#assign imageURL = entry.getImageURL() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />
<#assign imageFullURL = themeDisplay.getPortalURL() + imageURL />

<#assign AssetEntryService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService")/>
<#assign LayoutLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.LayoutLocalService")/>
<#assign assets = AssetEntryService.getAssetCategoryAssetEntries(entry.getPhaseCategory().getCategoryId())  />

<#-- Récupération de la page de listing de BP qui correspond au BP affiché. Chaque page de listing est configurée avec la catégorie qui correspond à la phase -->
<#list assets as ass>
    <#if ass.getClassName() == "com.liferay.portal.kernel.model.Layout">
        <#assign abc = LayoutLocalService.getLayout(ass.getClassPK())/>
        <#assign pageListing = abc.getFriendlyURL()/>
        <#break>
    </#if>
</#list>

<#-- Liste des infos a partager -->
<#assign openGraph = {"og:title":"${entry.title}",
"og:description":'${entry.description?replace("<[^>]*>", "", "r")?html}', 
"og:url":"${currentUrl}",
"og:type":"article",
"og:image":"${imageFullURL}",
"og:image:width":"450",
"og:image:height":"298"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}


<div class="pro-page-detail pro-page-detail-initiative">
    <#-- <div class="pro-timer"><p>Il reste 10 jours, 14 heures et 18 minutes pour voter</p></div> -->

    <div class="container">

        <div class="col-lg-11 col-lg-offset-1">

            <article>
                <header>
                    <div class="pro-header-participation">
                        <h1>${entry.title}</h1>
                        <div class="pro-wrapper-meta">
                            <div class="pro-statut" >
                                <span style="background : #${statusColor}">
                                    ${entry.getBudgetParticipatifStatusTitle(locale)}
                                </span>
                            </div>
                            <div class="pro-meta">
                                <#assign districtsLabel = entry.getDistrictLabel(locale) />
                                <#assign themacticsLabel = entry.getThematicsLabel(locale) />
                                <#assign projectLabel = entry.getProjectName() />

                                <#if districtsLabel?has_content ><span>${districtsLabel}</span></#if>
                                <#if themacticsLabel?has_content ><span>${themacticsLabel}</span></#if>
                                <#if projectLabel?has_content ><span>${projectLabel}</span></#if>
                            </div>
                        </div>
                        <div class="pro-header-auteur">
                            <figure>
                                <img src="${entry.getAuthorImageURL()}" width="40" height="40" alt="Arrière plan page standard"/>
                            </figure>
                            <p>Projet déposé le
                                <time>${entry.getCreateDate()?date?string['dd/MM/yyyy']}</time>
                                par :
                            </p>
                            <p><strong>${entry.getAuthor()}</strong></p>
                        </div>
                    </div>

                    <div id="breadcrumb">
                        <span>
                            <span>
                                <a href="${homeURL}">Accueil</a>
                                <#if pageListing??>
                                    <a href="${homeURL2}${pageListing}">Listings des projets</a>
                                </#if>
                                <span class="breadcrumb_last">${entry.title}</span>
                            </span>
                        </span>
                    </div>
                     <div class="pro-summary pro-bloc-texte">
                        <p>${entry.summary}</p>
                    </div>
                </header>

                <div class="row pro-container-detail-event">
                    <div class="col-sm-8">

                        <!-- INTEGRATION D'UNE IMAGE -->
                        <#if videoURL?has_content >
                            <!-- INTEGRATION D'UNE VIDEO -->
                            <div class="pro-bloc-texte pro-main-img">
                                <div class="pro-bloc-video bloc-standard">
                                    <div class="mask-video">
                                        <figure class="o80" role="group">
                                            <#if imageURL?has_content >
                                                <img src="${imageURL}" alt="Couverture de la vidéo" width="960" height="600"/>
                                            </#if>
                                        </figure>
                                        <a href="#play" class="btn-ytbe" title="Lire la vidéo">
                                            <span class="pro-btn-video" title="Lire la vidéo"><span class="icon-ico-lecteur"></span>Voir la vidéo</span>
                                        </a>
                                    </div>
                                    <div class="embed-container" data-urlvideo="${videoURL}"></div>
                                </div>
                            </div>
                        <#elseif imageURL?has_content >
                            <!-- INTEGRATION D'UNE IMAGE -->
                            <div class="pro-main-img">
                                <figure>
                                    <img src='${imageURL}' alt="Image Budget" width="880" height="593" class="fit-cover"/>
                                </figure>
                            </div>
                        </#if>

                        <#if motifPrintable >
                            <div class="pro-highlight pro-bloc-texte pro-theme-non-faisable">
                                <div class="pro-statut">
                                    <span style="color :#fff; background : #${statusColor}">
                                        Pourquoi ce projet est-il "${statusBP}" ?
                                    </span>
                                </div>
                                <p>${entry.motif}</p>
                            </div>
                        </#if>

                        <#if entry.isCrush && entry.crushComment?has_content >
                            <div class="pro-highlight-coup-de-coeur pro-bloc-texte">
                                <p class="pro-txt-intro">
                                    ${entry.crushComment}
                                </p>
                            </div>
                        </#if>
                        <#if entry.getParent()?has_content >
                            <div class="row pro-bloc pro-bloc-texte">
                                Ce projet a été fusionné dans le projet <a href="${homeURL}detail-budget-participatif/-/entity/id/${entry.getParent().budgetParticipatifId}">${entry.getParent().title}</a>
                            </div>
                        </#if>
                        <div class="row pro-bloc pro-bloc-texte">
                            ${entry.description}
                        </div>
                    </div>

                    <!-- Fiche de l'entité -->
                    <aside class="col-sm-4">

                        <!-- Bloc : Coup de coeur -->
                        <#if entry.isCrush >
                            <div class="pro-coup-de-coeur">
                                <p>Coup de cœur du conseil de quartier</p>
                            </div>
                        </#if>

                        <!-- Bloc : map -->
                        <div class="bloc-iframe leaflet-map" id="mapid" ></div>

                        <!-- Bloc : actions -->
                        <div class="pro-wrapper-aside-budget">

                            <!-- Encart : Budget -->
                            <#if entry.budget?has_content>
                                <p><span class="pro-euros">€</span> <strong>Budget : </strong>${entry.budget}</p>
                            </#if>

                            ${entry.getBPMessageState(request)}

                            <#if isVotable> <#-- Est votable -->
                                <#assign nbSupportForActivePhase = entry.getPhase().getNumberOfVote()>
                                <#if isUserloggedIn && hasUserPactSign && !isUserBanned> <#-- Utilisateur connecté et ayant signé le pacte -->
                                    <#assign nbSupportOfUserForEntry = entry.getNbSupportOfUser(userID) >
                                    <#assign nbSupportOfUserForActivePhase = entry.getNbSupportOfUserInActivePhase(userID) >
                                    
                                    <a href="#Support" data-nbsupports="${nbSupportOfUserForEntry}" class="pro-btn-yellow" data-toggle="modal" data-target="#modalVote">${entry.getBPbuttonMessageState(request)}</a>
                                    <p class="pro-txt-vote">Il vous reste <strong  id="nbUserSupports">${nbSupportForActivePhase - nbSupportOfUserForActivePhase}</strong> possibilité(s) de voter pour un projet</p>
                                    <a href="#RemoveSupport" class="pro-btn-yellow">
                                        Retirer vote (<strong  id="nbUserEntrySupports">${nbSupportOfUserForEntry}</strong>)
                                    </a>

                                    <script>
                                        $(document).ready(function() {
                                            // Cacher le bouton de vote si l'utilisateur a déjà utilisé les siens
                                            if (${nbSupportOfUserForActivePhase} >= ${nbSupportForActivePhase}) {
                                                $("[href='#Support']").hide();
                                            }
                                            // Cacher le bouton de retrait de vote si l'utilisateur n'a jamais voté pour ce projet
                                            if (${nbSupportOfUserForEntry} < 1) {
                                                $("[href='#RemoveSupport']").hide();
                                            }
                                        });
                                    </script>

                                <#elseif isUserBanned> <#--  -->
                                    <a href="#" name="#IsBanned" class="pro-btn-yellow" data-toggle="modal" data-target="#modalVote">${entry.getBPbuttonMessageState(request)}</a>
                                <#else> <#--  -->
                                    <a href="#" name="#Pact-sign" class="pro-btn-yellow" data-toggle="modal" data-target="#modalVote">${entry.getBPbuttonMessageState(request)}</a>
                                    <p class="pro-txt-vote">Il vous reste <strong>${nbSupportForActivePhase}</strong> possibilités de voter pour un projet</p>
                                </#if>
                            <#elseif isEditable && isUserloggedIn && hasUserPactSign && !isUserBanned && isAuthor>
                                <a href="#showModalEditBudget" data-toggle="modal" data-target="#modalEditBudget" class="pro-btn-yellow">MODIFIER</a> 
							<#else>
                                <a href="#" class="pro-btn-yellow" id="voteButton">${entry.getBPbuttonMessageState(request)}</a>
                            </#if>
                            
                            <a href="#pro-link-commentaire" class="pro-btn-yellow" title="Scroll jusqu'à la zone de commentaire">Réagir</a>
                        </div>
                    </aside>

                </div>
            </article>

        </div>
    </div>
	

    <#assign childs = entry.getChilds() />

    <#if childs?has_content>
        <#assign bpsSlider = childs />
    <#else>
        <#-- Recuperation des suggestions du bp -->
        <#assign bpsSlider = entry.getSuggestions(request, 10)  />
    </#if>
	
    
	
	<#if bpsSlider?size gt 0 >
		<section id="pro-link-evenement" class="pro-bloc-slider pro-slider-event">
            <div class="container">
                <div class="col-lg-10 col-lg-offset-1">
                    <#if childs?has_content>
                        <h2>Projet fusionné avec</h2>
                    <#else>
                        <h2>D’autres projets citoyens</h2>
                    </#if>
                    
                    <div class="pro-wrapper">
                        <a href="${homeURL}projets-budget-participatif" class="pro-btn">Tous les projets</a>
                    </div>
                </div>

                <div class="col-lg-10 col-lg-offset-1">
                    <div class="owl-carousel owl-opacify owl-theme owl-cards">
					
						<#list bpsSlider as suggestion >
							
							<#-- Recuperation de la couleur hexa correspondant au type du bp -->
							<#assign statusColor = suggestion.getBudgetParticipatifStatusCategoryColor() />

							<#assign imageURL = suggestion.getAuthorImageURL() />
							
							<#if suggestion.isNotDoable()>
								<#assign classFaisable = "pro-theme-non-faisable"/>
							<#else>
								<#assign classFaisable = "pro-theme-faisable" />
							</#if>
							
							<div class="item pro-bloc-card-budget ${classFaisable}" data-linkall="a">
                                <#if suggestion.getImageURL()?has_content>
									<figure role="group" class="fit-cover">
										<img src="${suggestion.getImageURL()}" width="155" height="200" alt="Image du budget participatif"/>
									</figure>
								</#if>
								<div class="pro-header-budget">
									<#if imageURL?has_content >
										<figure role="group">
											<img src="${imageURL}" width="40" height="40" alt="Image du budget participatif"/>
										</figure>
									</#if>
									<p>Projet déposé par :</p>
									<p><strong>${suggestion.getAuthor()}</strong></p>
									<div class="pro-info-top-right">
										<span class="pro-encart-theme" style="background : #${statusColor}">
											${suggestion.getBudgetParticipatifStatusTitle(locale)}
										</span>
									</div>
								</div>
								<div class="pro-content-budget">
									<a href="${homeURL}detail-budget-participatif/-/entity/id/${suggestion.budgetParticipatifId}" title="lien de la page de détail">
										<h3>${suggestion.title}</h3>
									</a>
									<p>Projet adressée à <u>la ville de Strasbourg</u></p>
									<span class="pro-time">
										Publiée le <time datetime="${suggestion.createDate?date?string['dd/MM/yyyy']}">${suggestion.createDate?date?string['dd/MM/yyyy']}</time>
									</span>
								</div>
								<div class="pro-footer-budget">
									<p>
										<#if suggestion.isNotDoable()>
											Ce projet a été étudié et déclaré "${suggestion.getBudgetParticipatifStatusTitle(locale)}"
										<#else>
											<strong>${suggestion.getNbSupports()}</strong> vote(s) pour ce projet
										</#if>									
									</p>
								</div>
							</div>						
						</#list>
                    </div>
                </div>
            </div>
        </section>
	</#if>	
</div>

<script>
    // Récupération des entités en JSON à afficher sur la map et ajout des données dynamiques manquantes
    var budgetParticipatifJSON = ${budgetParticipatifJSON};
    budgetParticipatifJSON.link = '${homeURL}detail-budget-participatif/-/entity/id/${entry.budgetParticipatifId}';

    // Variable pointeur
    var budgetParticipatifMarkers = []

    $(document).ready(function() {
        // Gestion de la carte interactive
        // Notes : voir dans le theme placit "override/custom.js"

        //Création de la carte au centre de strasbourg
        leafletMap = getLeafletMap();

        // Définition des marqueurs
        var budgetParticipatifMarkerIcon = getMarkerIcon('budget-participatif');

        // Création du cluster permettant le regroupement de points et le centrage
        markersCluster = L.markerClusterGroup();

        for(var i= 0; i < budgetParticipatifJSON.placitPlaces.length; i++) {
            var marker = getBudgetParticipatifMarker(
                budgetParticipatifJSON,
                [budgetParticipatifJSON.placitPlaces[i].mercatorY, budgetParticipatifJSON.placitPlaces[i].mercatorX]
            );

            // Ajout du point dans le Cluster de marqueurs
            markersCluster.addLayer(marker);
            // Ajout du marker dans le tempon
            budgetParticipatifMarkers.push(marker);
        }

        leafletMap.addLayer(markersCluster);

        // Adapter le zoom si des marqueurs existent
        if (markersCluster.getBounds().isValid()) {
            leafletMap.fitBounds(markersCluster.getBounds());
        }

        $("#voteButton:empty").hide(); 

    });
</script>