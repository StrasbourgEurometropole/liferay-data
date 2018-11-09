<%@ include file="/init.jsp"%>

<portlet:actionURL var="saveProfilURL" name="saveProfil">
	<portlet:param name="redirectURL" value="${redirectURL}" />
	<portlet:param name="cmd" value="saveProfil" />
</portlet:actionURL>

<c:if test="${!isUserloggedIn}">
	<script>
        $("#myModal").modal("show");
    </script>
</c:if>

<c:if test="${isUserloggedIn}">
	<div class="pro-bloc-dashboard">
		<div class="container pro-user">
			<a href="#pro-onglet-account">
				<figure>
					<img
						src="/o/plateforme-citoyenne-theme/images/medias/user_female_portrait.png"
						width="40" height="40" alt="Nom de l'utilisateur" />
				</figure> <span><liferay-ui:message key="dashboard.front.profil" /></span>
			</a> <span><liferay-ui:message key="dashboard.front.welcome" /><span
				id="pro-user-name">${userConnected.get('first_name')}
					${userConnected.get('last_name')}</span></span>
		</div>

		<div class="container pro-wrapper-dashboard">

			<!-- ONGLET ACTIVITE -->
			<div id="pro-onglet-activite">
				<div class="pro-title-dashboard col-xs-12">
					<h1>
						<liferay-ui:message key="dashboard.front.myactivity" />
					</h1>
					<span></span>
				</div>


				<div class="col-lg-3 col-sm-6 col-xs-12">
					<a href="#pro-link-listing-projet" class="pro-item pro-item-projet">
						<div class="pro-item-center">
							<span class="pro-number">${projectFollowedsCount}</span> <span
								class="pro-txt"><liferay-ui:message
									key="dashboard.front.project" /></span>
						</div>
					</a> <a href="#pro-link-listing-event" class="pro-item pro-item-agenda">
						<div class="pro-item-center">
							<span class="pro-number">${eventCount}</span> <span
								class="pro-txt"><liferay-ui:message
									key="dashboard.front.event" /></span>
						</div>
						<div class="pro-link-dashboard">
							<span class="pro-txt"><liferay-ui:message
									key="dashboard.front.event.goto" /></span>
						</div>
					</a>
				</div>


				<div class="col-lg-3 col-sm-6 col-xs-12">
					<div class="pro-item pro-item-petition">
						<div class="pro-item-center">
							<span class="pro-number">${petitionsFiledCount+petitionSignedCount}</span>
							<span class="pro-txt"><liferay-ui:message
									key="dashboard.front.petition" /></span>
						</div>
						<div class="pro-link-dashboard">
							<a href="#pro-link-listing-petition-signe" class="pro-txt"><strong>${petitionSignedCount}</strong>
								<liferay-ui:message key="dashboard.front.petition.signed" /></a> <a
								href="#pro-link-listing-petition-depose" class="pro-txt"><strong>${petitionsFiledCount}</strong>
								<liferay-ui:message key="dashboard.front.petition.filed" /></a>
						</div>
					</div>
				</div>

				<c:if test="${initiativeFiledCount+initiativeAidesCount ne 0}">
					<div class="col-lg-3 col-sm-6 col-xs-12">
						<div class="pro-item pro-item-initiative">
							<div class="pro-item-center">
								<div>
									<span class="icon-ico-initiative"></span> <span class="pro-txt"><liferay-ui:message
											key="dashboard.front.initiative" /></span>
								</div>
							</div>
							<div class="pro-bloc-link-dashboard">
								<a href="#pro-link-listing-initiative-signe" class="pro-txt"><strong>${initiativeFiledCount}</strong><span><liferay-ui:message
											key="dashboard.front.initiative.signed" /></span></a> <a
									href="#pro-link-listing-initiative-aide" class="pro-txt"><strong>${initiativeAidesCount}</strong><span><liferay-ui:message
											key="dashboard.front.initiative.aidees" /></span></a>
							</div>
						</div>
					</div>
				</c:if>
					<div class="col-lg-3 col-sm-6 col-xs-12">
						<div class="pro-item pro-item-budget">
							<div class="pro-item-center">
								<p></p>
							</div>
							<span class="pro-title"><liferay-ui:message
									key="dashboard.front.budget" /></span>
							<div class="pro-link-dashboard">
								<a href="#pro-link-listing-projet-soumis" class="pro-txt"><strong>${budgetFiledCount}</strong>
									<span><liferay-ui:message
											key="dashboard.front.budget.filed" /></span></a> <a
									href="#pro-link-listing-projet-vote" class="pro-txt"><strong>${budgetVotedCount}</strong>
									<span><liferay-ui:message
											key="dashboard.front.budget.voted" /></span></a>
							</div>
							<div class="pro-info-vote">
								<span><liferay-ui:message
										key="dashboard.front.budget.reliquat" /> ${voteLeft}<liferay-ui:message
										key="dashboard.front.budget.reliquat2" /></span>
							</div>
						</div>
					</div>
			</div>


			<!-- ONGLET ACCOUNT -->
			<div id="pro-onglet-account" class="pro-hide">
				<div class="pro-title-dashboard col-xs-12">
					<h2>
						<liferay-ui:message key="dashboard.account.title" />
					</h2>
					<span></span>
				</div>
				<div class="col-xs-12">
					<a href="#pro-onglet-activite" class="pro-btn-back"><liferay-ui:message
							key="dashboard.account.information" /></a>
				</div>

				<form id="form-save-profil" method="post" action="${saveProfilURL}">
					<div class="pro-wrapper col-md-3">
						<div class="profile">
							<div class="photo">
								<input type="file" accept="image/*">
								<div class="photo__helper">
									<div class="photo__frame photo__frame--circle">
										<img
											src="/o/plateforme-citoyenne-theme/images/medias/user_female_portrait.png"
											width="185" height="185" alt="Image" class="pro-img-bg" />
										<canvas class="photo__canvas"></canvas>

										<div class="pro-photo-hover">
											<span class="icon-ico-user"></span>
											<p>
												<liferay-ui:message key="dashboard.account.profile.picture" />
											</p>
										</div>
										<div class="message is-wrong-image-size">
											<p>
												<liferay-ui:message
													key="dashboard.account.profile.picture.error" />
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="pro-wrapper col-md-9">
						<div class="pro-row">
							<div class="form-group form-third">
								<aui:input name="username" disabled="true"
									label="dashboard.account.profile.username" required="true"
									value="${userConnected.get('last_name')}" />
							</div>
							<div class="form-group form-third">
								<aui:input name="firstname" disabled="true"
									label="dashboard.account.profile.firstname" required="true"
									value="${userConnected.get('first_name')}" />
							</div>
							<div class="form-group form-third">
								<c:if test="${userConnected.get('birthdate') ne 'null'}">
									<fmt:parseDate pattern="yyyy-MM-dd"
										value="${userConnected.get('birthdate')}"
										var="parsedStatusDate" />
								</c:if>
								<aui:input name="birthday" cssClass="frm_date"
									label="dashboard.account.profile.birthday" required="true"
									placeholder="jj/mm/aaaa" value="${parsedStatusDate}" />
							</div>
						</div>
						<div class="pro-row">
							<div class="form-group form-half">
								<aui:input name="address"
									label="dashboard.account.profile.address" required="true"
									value="${userConnected.get('address')}" />
							</div>
							<div class="form-group form-half">
								<div class="form-city">
									<aui:input name="city" label="dashboard.account.profile.city"
										required="true" placeholder="Strasbourg"
										value="${userConnected.get('city')}" />
								</div>
								<div class="form-code">
									<aui:input name="postalcode"
										label="dashboard.account.profile.postalcode" required="true"
										placeholder="67XXX" value="${userConnected.get('zipcode')}" />
								</div>
							</div>
						</div>
						<div class="pro-row">
							<div class="form-group form-third">
								<aui:input type="email" name="mail" disabled="true"
									label="dashboard.account.profile.mail" required="true"
									value="${userConnected.get('email')}" />
							</div>
							<div class="form-group form-third">
								<aui:input type="number" name="phone"
									label="dashboard.account.profile.phone"
									placeholder="0311111111" value="${userConnected.get('phone')}" />
							</div>
							<div class="form-group form-third">
								<aui:input type="number" name="mobile"
									label="dashboard.account.profile.mobile"
									placeholder="0611111111" value="${userConnected.get('mobile')}" />
							</div>
						</div>
						<div class="pro-form-submit pro-row">
							<button type="submit" class="btn btn-default">
								<liferay-ui:message key="dashboard.account.profile.button.save" />
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- LISTING DE TUILES -->
	<!--     projets -->
	<c:if test="${projectFollowedsCount != 0}">
		<section id="pro-link-listing-projet" class="pro-bloc-slider">
			<div class="container">
				<h2>
					<liferay-ui:message key="dashboard.thumbnail.project.title" />
					(${projectFollowedsCount})
				</h2>
				<a href="${homeURL}projets" class="pro-btn"
					title="<liferay-ui:message key='dashboard.thumbnail.project.link.title'/>">
					<liferay-ui:message key="dashboard.thumbnail.project.link" />
				</a>

				<!-- SlIDER LISTE DES PROJETS - TOUS LES PROJETS -->
				<div id="pro-projet-all"
					class="owl-carousel owl-opacify owl-theme owl-cards owl-projet">
					<c:forEach var="projectFollowed" items="${projectFolloweds}">
						<div class="item bloc-card-projet">
							<a href="${projectFollowed.detailURL}"
								title="<liferay-ui:message key='dashboard.thumbnail.link'/>">
								<div class="img">
									<figure role="group">
										<img src='${projectFollowed.imageURL}' alt="Image agenda"
											width="360" height="242" class="fit-cover" />
									</figure>
									<span><liferay-ui:message
											key="dashboard.thumbnail.project.goto" /></span>
								</div>
								<div class="content">
									<span class="location">${projectFollowed.getDistrictLabel(locale)}</span>
									<h3>${projectFollowed.title}</h3>
								</div>
							</a>
							<ul>
								<c:if
									test="${fn:length(projectFollowed.getParticipations()) != 0}">
									<li><a
										href="${projectFollowed.detailURL}#pro-link-participation"
										title="<liferay-ui:message key='dashboard.thumbnail.link'/>"
										tabindex="-1">${fn:length(projectFollowed.getParticipations())}
											<liferay-ui:message
												key="dashboard.thumbnail.project.participation" />
									</a></li>
								</c:if>
								<c:if test="${fn:length(projectFollowed.getEvents()) != 0}">
									<li><a
										href="${projectFollowed.detailURL}#pro-link-evenement"
										title="<liferay-ui:message key='dashboard.thumbnail.link'/>"
										tabindex="-1">${fn:length(projectFollowed.getEvents())} <liferay-ui:message
												key="dashboard.thumbnail.project.events" /></a></li>
								</c:if>
								<c:if test="${fn:length(projectFollowed.getPetitions()) != 0}">
									<li><a
										href="${projectFollowed.detailURL}#pro-link-petition"
										title="<liferay-ui:message key='dashboard.thumbnail.link'/>"
										tabindex="-1">${fn:length(projectFollowed.getPetitions())}
											<liferay-ui:message
												key="dashboard.thumbnail.project.petition" />
									</a></li>
								</c:if>
								<!--<li><a href="#" title="<liferay-ui:message key='dashboard.thumbnail.link'/>" tabindex="-1">0 <liferay-ui:message key="dashboard.thumbnail.project.budget"/></a></li>-->
								<!--<li><a href="#" title="<liferay-ui:message key='dashboard.thumbnail.link'/>" tabindex="-1">0 <liferay-ui:message key="dashboard.thumbnail.project.initiative"/></a></li>-->
							</ul>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
	</c:if>
	<!--     event -->
	<div class="pro-wrapper-list-dashboard">
		<c:if test="${eventCount != 0}">
			<section id="pro-link-listing-event"
				class="pro-bloc-slider pro-slider-event">
				<div class="container">
					<h2>Mon agenda (${eventCount})</h2>
					<a href="${homeURL}agenda" class="pro-btn"
						title="<liferay-ui:message key='dashboard.thumbnail.agenda.title'/>">
						<liferay-ui:message key="dashboard.thumbnail.agenda" />
					</a>

					<div class="owl-carousel owl-opacify owl-theme owl-cards">
						<c:forEach var="event" items="${event}">
							<div class="item pro-bloc-card-event" data-linkall="a">
								<div>
									<div class="pro-header-event">
										<span class="pro-ico"><span class="icon-ico-debat"></span></span>
										<span class="pro-time"><time datetime="2018-01-10">${event.getEventScheduleDisplay(locale)}</time></span>
										<p>
											<liferay-ui:message key="dashboard.thumbnail.agenda.a" />
											${event.getPlaceAlias(locale)}
										</p>
										<a
											href="${homeURL}detail-evenement/-/entity/id/${event.eventId}"
											title="<liferay-ui:message key='dashboard.thumbnail.link'/>"><h3>${event.getTitle(locale)}</h3></a>
									</div>
									<div class="pro-footer-event">
										<span class="pro-btn-action active"><liferay-ui:message
												key="dashboard.thumbnail.agenda.participe" /></span> <span
											class="pro-number"><strong>${event.getNbEventParticipations()}</strong>
											<liferay-ui:message
												key="dashboard.thumbnail.agenda.participant" /></span>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</c:if>

		<!-- Tuile des pétitions signés -->
		<c:if test="${petitionSignedCount ne 0}">
			<section id="pro-link-listing-petition-signe"
				class="pro-bloc-slider pro-slider-event">
				<div class="container">
					<h2>
						<liferay-ui:message key="dashboard.thumbnail.petition.signed.mine" />
						(${petitionSignedCount})
					</h2>
					<a href="${homeURL}petitions" class="pro-btn"
						title="<liferay-ui:message key='dashboard.thumbnail.petition.main.alt'/>"><liferay-ui:message
							key="dashboard.thumbnail.petition.main" /></a>

					<div class="owl-carousel owl-opacify owl-theme owl-cards">
						<c:forEach var="petitionSigned" items="${petitionSigned}">
							<div class="item pro-bloc-card-petition" data-linkall="a">
								<div class="pro-header-petition">
									<!--                                     <figure role="group"> -->
									<!--                                         <img src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg" width="40" height="40" alt=<liferay-ui:message key="dashboard.thumbnail.petition.img.alt"/>/> -->
									<!--                                     </figure> -->
									<p>
										<liferay-ui:message key="dashboard.thumbnail.petition.from" />
									</p>
									<p>
										<strong>${petitionSigned.petitionnaireFirstname}
											${petitionSigned.petitionnaireLastname}</strong>
									</p>
								</div>
								<div class="pro-content-petition">
									<a
										href="${homeURL}detail-petition/-/entity/id/${petitionSigned.petitionId}"
										title="<liferay-ui:message key='dashboard.thumbnail.link'/>"><h3>${petitionSigned.title}</h3></a>
									<p>
										<liferay-ui:message key="dashboard.thumbnail.petition.to" />
									</p>
									<span class="pro-time"><liferay-ui:message
											key="dashboard.thumbnail.petition.publish.date" /> <time
											datetime="${petitionSigned.getPublicationDateFr()}">${petitionSigned.getPublicationDateFr()}</time>
										/ <span class="pro-duree">${petitionSigned.getProDureeFR()}</span></span>
								</div>
								<div class="pro-footer-petition">
									<div class="pro-progress-bar">
										<div class="pro-progress-container">
											<div
												style="width:${petitionSigned.getPourcentageSignature()}%"></div>
										</div>
										<p class="pro-txt-progress">
											<strong>${petitionSigned.getNombreSignature()} </strong>
											<liferay-ui:message
												key="dashboard.thumbnail.petition.progress" />
											${petitionSigned.getQuotaSignature()}
											<liferay-ui:message
												key="dashboard.thumbnail.petition.progress2" />
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</c:if>

		<!-- Tuile des pétitions déposés -->
		<c:if test="${petitionsFiledCount != 0}">
			<section id="pro-link-listing-petition-depose"
				class="pro-bloc-slider pro-slider-event">
				<div class="container">
					<h2>
						<liferay-ui:message key="dashboard.thumbnail.petition.filed.mine" />
						(${petitionsFiledCount})
					</h2>
					<a href="${homeURL}petitions" class="pro-btn"
						title="<liferay-ui:message key='dashboard.thumbnail.petition.main.alt'/>"><liferay-ui:message
							key="dashboard.thumbnail.petition.main" /></a>

					<div class="owl-carousel owl-opacify owl-theme owl-cards">
						<c:forEach var="petitionFiled" items="${petitionsFiled}">
							<div class="item pro-bloc-card-petition" data-linkall="a">
								<div class="pro-header-petition">
									<!--                                     <figure role="group"> -->
									<!--                                         <img src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg" width="40" height="40" alt=<liferay-ui:message key="dashboard.thumbnail.petition.img.alt"/>/> -->
									<!--                                     </figure> -->
									<p>
										<liferay-ui:message key="dashboard.thumbnail.petition.from" />
									</p>
									<p>
										<strong>${petitionFiled.petitionnaireFirstname}
											${petitionFiled.petitionnaireLastname}</strong>
									</p>
								</div>
								<div class="pro-content-petition">
									<a
										href="${homeURL}detail-petition/-/entity/id/${petitionFiled.petitionId}"
										title="<liferay-ui:message key='dashboard.thumbnail.link'/>"><h3>${petitionFiled.title}</h3></a>
									<p>
										<liferay-ui:message key="dashboard.thumbnail.petition.to" />
									</p>
									<span class="pro-time"><liferay-ui:message
											key="dashboard.thumbnail.petition.publish.date" /> <time
											datetime="${petitionFiled.getPublicationDateFr()}">${petitionFiled.getPublicationDateFr()}</time>
										/ <span class="pro-duree">${petitionFiled.getProDureeFR()}</span></span>
								</div>
								<div class="pro-footer-petition">
									<div class="pro-progress-bar">
										<div class="pro-progress-container">
											<div
												style="width:${petitionFiled.getPourcentageSignature()}%"></div>
										</div>
										<p class="pro-txt-progress">
											<strong> ${petitionFiled.getNombreSignature()} </strong>
											<liferay-ui:message
												key="dashboard.thumbnail.petition.progress" />
											${petitionFiled.getQuotaSignature()}
											<liferay-ui:message
												key="dashboard.thumbnail.petition.progress2" />
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</c:if>


		<!-- Tuile des initiatives -->
		<c:if test="${initiativeFiledCount != 0}">
			<section id="pro-link-listing-initiative-signe"
				class="pro-bloc-slider pro-slider-event">
				<div class="container">
					<h2>Mes initiatives signées (6)</h2>
					<a href="listing-initiative.html" class="pro-btn"
						title="Lien vers la page du Listing des évènements">Toutes les
						initiatives</a>

					<div class="owl-carousel owl-opacify owl-theme owl-cards">

						<div class="item pro-bloc-card-initiative" data-linkall="a">
							<div class="wrapper-card-initiative">
								<div>
									<div class="pro-header-initiative">
										<figure role="group">
											<img
												src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg"
												width="40" height="40" alt="Arrière plan page standard" />
										</figure>
										<p>Initiative publiée par :</p>
										<p>
											<strong>Sylvie M.</strong>
										</p>
									</div>
									<div class="pro-content-initiative">
										<div class="pro-wrapper-meta">
											<div class="pro-meta">
												<span>Quartier</span> <span>Thématique</span> <span>Nom
													du projet</span>
											</div>
										</div>
										<a href="detail-initiative.html" title="lien de la page"><h3>
												Titre de l’initiative<br>Sur deux lignes
											</h3></a> <span class="pro-time">Publiée le <time
												datetime="2018-01-10">10/04/2018</time></span>
									</div>
								</div>
							</div>
							<div class="pro-footer-initiative">
								<div class="pro-avis">
									<span>188</span>
								</div>
								<p>Citoyens soutiennent cette initiative</p>
							</div>
						</div>
					</div>
				</div>
			</section>
		</c:if>


		<!-- Tuile des initiatives -->
		<c:if test="${initiativeAidesCount != 0}">
			<section id="pro-link-listing-initiative-aide"
				class="pro-bloc-slider pro-slider-event">
				<div class="container">
					<h2>Mes initiatives aidées (11)</h2>
					<a href="listing-initiative.html" class="pro-btn"
						title="Lien vers la page du Listing des évènements">Toutes les
						initiatives</a>

					<div class="owl-carousel owl-opacify owl-theme owl-cards">

						<div class="item pro-bloc-card-initiative" data-linkall="a">
							<div class="wrapper-card-initiative">
								<div>
									<div class="pro-header-initiative">
										<figure role="group">
											<img
												src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg"
												width="40" height="40" alt="Arrière plan page standard" />
										</figure>
										<p>Initiative publiée par :</p>
										<p>
											<strong>Sylvie M.</strong>
										</p>
									</div>
									<div class="pro-content-initiative">
										<div class="pro-wrapper-meta">
											<div class="pro-meta">
												<span>Quartier</span> <span>Thématique</span> <span>Nom
													du projet</span>
											</div>
										</div>
										<a href="detail-initiative.html" title="lien de la page"><h3>
												Titre de l’initiative<br>Sur deux lignes
											</h3></a> <span class="pro-time">Publiée le <time
												datetime="2018-01-10">10/04/2018</time></span>
									</div>
								</div>
							</div>
							<div class="pro-footer-initiative">
								<div class="pro-avis">
									<span>188</span>
								</div>
								<p>Citoyens soutiennent cette initiative</p>
							</div>
						</div>
					</div>
				</div>
			</section>
		</c:if>

		<!-- Tuile des budgets -->
		<c:if test="${budgetFiledCount != 0}">
			<section id="pro-link-listing-projet-soumis"
				class="pro-bloc-slider pro-slider-event">
				<div class="container">
					<h2><liferay-ui:message key="dashboard.thumbnail.bp.filed.mine" />
						(${budgetFiledCount})</h2>
					<a href="${homeURL}projets-budget-participatif" class="pro-btn"
						title="<liferay-ui:message key='dashboard.thumbnail.bp.main.alt'/>"><liferay-ui:message
							key="dashboard.thumbnail.bp.main" /></a>

					<div class="owl-carousel owl-opacify owl-theme owl-cards">
						<c:forEach var="budgetFiled" items="${budgetFiled}">
							
							<c:choose >
								<c:when test="${budgetFiled.isNotDoable()}">
									<c:set var="classFaisable" value="pro-theme-non-faisable" scope="page" />
								</c:when>
								<c:otherwise>
									<c:set var="classFaisable" value="pro-theme-faisable" scope="page" />
								</c:otherwise>
							</c:choose>
						
							<div class="item pro-bloc-card-budget ${classFaisable}"
								data-linkall="a">
								<div class="pro-header-budget">
									<figure>
										<img
											src="/o/plateforme-citoyenne-theme/images/medias/user_female_portrait.png"
											width="40" height="40" alt="Nom de l'utilisateur" />
									</figure>
									<p><liferay-ui:message key="dashboard.thumbnail.bp.from" /></p>
									<p>
										<strong>${budgetFiled.citoyenFirstname}
											${budgetFiled.citoyenLastname}</strong>
									</p>
									<div class="pro-info-top-right">
										<span class="pro-encart-theme" style="background:#${budgetFiled.getBudgetParticipatifStatusCategoryColor()}">${budgetFiled.getBudgetParticipatifStatusTitle(locale)}</span>
									</div>
								</div>
								<div class="pro-content-budget">
								
									<a
										href="${homeURL}detail-budget-participatif/-/entity/id/${budgetFiled.budgetParticipatifId}"
										title="<liferay-ui:message key='dashboard.thumbnail.link'/>"><h3>${budgetFiled.title}</h3></a>
								
									<p>
										<liferay-ui:message key="dashboard.thumbnail.bp.to" />
									</p>
										<span class="pro-time"><liferay-ui:message
										key="dashboard.thumbnail.bp.publish.date" /> <time
										datetime="${budgetFiled.getPublicationDateFr()}">${budgetFiled.getPublicationDateFr()}</time>
									/ <span class="pro-duree"></span></span>
											
								</div>
								<div class="pro-footer-budget">
									<p>
										<c:choose >
											<c:when test="${budgetFiled.isNotDoable()}">
												<liferay-ui:message key="dashboard.thumbnail.bp.not.doable" />
											</c:when>
											<c:otherwise>
												<strong>${budgetFiled.getNbSupports()}</strong> <liferay-ui:message key="dashboard.thumbnail.bp.doable" />
											</c:otherwise>
										</c:choose>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</c:if>


		<!-- Tuile des budgets -->
		<c:if test="${budgetVotedCount != 0}">
			<section id="pro-link-listing-projet-vote"
				class="pro-bloc-slider pro-slider-event">
				<div class="container">
					<h2><liferay-ui:message key="dashboard.thumbnail.bp.signed.mine" /> (${budgetVotedCount})</h2>
					<a href="${homeURL}projets-budget-participatif" class="pro-btn"
						title="<liferay-ui:message key='dashboard.thumbnail.bp.main.alt'/>"><liferay-ui:message
							key="dashboard.thumbnail.bp.main" /></a>

					<div class="owl-carousel owl-opacify owl-theme owl-cards">
						<c:forEach var="budgetFiled" items="${budgetVoted}">
						
							<c:choose >
								<c:when test="${budgetVoted.isNotDoable()}">
									<c:set var="classFaisable" value="pro-theme-non-faisable" scope="page" />
								</c:when>
								<c:otherwise>
									<c:set var="classFaisable" value="pro-theme-faisable" scope="page" />
								</c:otherwise>
							</c:choose>
						
							<div class="item pro-bloc-card-budget  ${classFaisable}"
								data-linkall="a">
								<div class="pro-header-budget">
									<figure>
										<img
											src="/o/plateforme-citoyenne-theme/images/medias/user_female_portrait.png"
											width="40" height="40" alt="Nom de l'utilisateur" />
									</figure>
									<p><liferay-ui:message key="dashboard.thumbnail.bp.from" /></p>
									<p>
										<strong>${budgetVoted.citoyenFirstname}
											${budgetVoted.citoyenLastname}</strong>
									</p>
									<div class="pro-info-top-right">
										<span class="pro-encart-theme" style="background:#${budgetVoted.getBudgetParticipatifStatusCategoryColor()}">${budgetVoted.getBudgetParticipatifStatusTitle(locale)}</span>
									</div>
								</div>
								<div class="pro-content-budget">
									<a
										href="${homeURL}detail-budget-participatif/-/entity/id/${budgetVoted.budgetParticipatifId}"
										title="<liferay-ui:message key='dashboard.thumbnail.link'/>"><h3>${budgetVoted.title}</h3></a>
								
									<p>
										<liferay-ui:message key="dashboard.thumbnail.bp.to" />
									</p>
									<span class="pro-time"><liferay-ui:message
										key="dashboard.thumbnail.bp.publish.date" /> <time
										datetime="${budgetVoted.getPublicationDateFr()}">${budgetVoted.getPublicationDateFr()}</time>
									/ <span class="pro-duree"></span></span>
								<div class="pro-footer-budget">
									<p>
										<c:choose >
											<c:when test="${budgetVoted.isNotDoable()}">
												<liferay-ui:message key="dashboard.thumbnail.bp.not.doable" />
											</c:when>
											<c:otherwise>
												<strong>${budgetVoted.getNbSupports()}</strong> <liferay-ui:message key="dashboard.thumbnail.bp.doable" />
											</c:otherwise>
										</c:choose>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</c:if>
	</div>

	<a href="#backtop" class="pro-btn-back-top"><span
		class="icon-ico-chevron-down"></span></a>
</c:if>