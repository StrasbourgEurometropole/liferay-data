<#if entries?size != 0 >

	<#-- Recuperation de la localisation de l'utilisateur -->
	<#setting locale = locale />
	<#assign isPlacite = false />

	<#-- Recuperation de l'URL de "base" du site -->
	<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
	    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
	<#else>
	    <#assign homeURL = "/" />
	</#if>

	<#-- Récupération de l'ID de l'utilisateur -->
	<#assign userID = request.session.getAttribute("publik_internal_id")!"" />

	<#-- Récupération du contexte de navigation de l'utilisateur -->
	<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />
	<#assign hasUserPactSign = request.session.getAttribute("has_pact_signed")!false />
	<#assign isUserBanned = request.session.getAttribute("is_banish")!false />

	<#-- Recuperation de l'id de l'instance du portlet pour separer le metier des portlets doublons -->
	<#assign instanceId = themeDisplay.getPortletDisplay().getId() />

	<#-- Compteur de résultat(s) -->
	<#assign resultsSize = 0 />	

	<section id="pro-link-evenement" class="pro-bloc-slider pro-slider-event slider-agenda">
	    <div class="container">

	        <div>
	            <h2 id="title-events-slider-${instanceId}">L’agenda</h2>
	            <a href="${homeURL}agenda" class="pro-btn" title="Lien vers la page de tout l'agenda">Tout voir</a>
	        </div>

	        <div>
	            <div class="owl-carousel owl-opacify owl-theme owl-cards">
	            
	                <#-- Parcours des entites de l'asset publisher -->
	                <#list entries as curEntry>

						<#list curEntry.getTags() as tag >
							<#if tag.getName() == "participer">
								<#assign isPlacite = true />
							</#if>
						</#list>			
					
	                    <#if isPlacite>

							<#-- Recuperation de l'entite -->
							<#assign entry = curEntry.getAssetRenderer().getEvent() />

							<#-- L'utilisateur participe-t-il ? -->
							<#assign isUserPartActive = entry.isUserParticipates(userID)?then("active", "") />

							<#if entry.getCurrentOrFuturePeriod()??>
								<#assign period = entry.getCurrentOrFuturePeriod() />
							<#else>
								<#assign period = "" />
							</#if>

							<#assign resultsSize++ />
							
							<a href="${homeURL}detail-evenement/-/entity/id/${entry.eventId}" title="lien de la page" class="item pro-bloc-card-event">
								<div>
									<div class="pro-header-event">
										<span class="pro-ico"><span class="icon-ico-debat"></span></span>
										<span class="pro-time"><#if period?has_content>Le ${period.startDate?string("dd MMMM yyyy")} <#if period.getTimeDetail(locale)?has_content> à ${period.getTimeDetail(locale)}</#if></#if></span>
										<p>À : ${entry.getPlaceAlias(locale)}</p>
										<h3 style="display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;
											overflow: hidden;text-overflow: ellipsis;height: 53px">
											${entry.getTitle(locale)}
										</h3>
									</div>
									<div class="pro-footer-event">
										<#if entry.isFinished() >
											<span class="pro-btn-action ${isUserPartActive}">
												Événement terminé
											</span>
										<#elseif hasUserPactSign && !isUserBanned>
											<span class="pro-btn-action ${isUserPartActive}"
												name="#Participe-${instanceId}"
												data-eventid="${entry.eventId}"
												data-groupid="${entry.groupId}">
												Je participe
											</span>
										<#elseif isUserBanned>
											<span class="pro-btn-action" name="#IsBanned">
												Je participe
											</span>
										<#else>
											<span class="pro-btn-action" name="#Pact-sign">
												Je participe
											</span>
										</#if>
										<span class="pro-number"><strong>${entry.getNbEventParticipations()}</strong> Participant(s)</span>
									</div>
								</div>
							</a>
	                    </#if>
						
						<#assign isPlacite = false />
	                </#list>

	            </div>
	        </div>

	    </div>
	</section>

	<script>
	    $(document).ready(function() {
	        $('#title-events-slider-${instanceId}').text("L'agenda (" + ${resultsSize} + ")");
			if(${resultsSize} == 0){
				$('#pro-link-evenement').hide();
			}
	    });
	</script>

</#if>