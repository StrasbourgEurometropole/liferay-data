<%@ include file="/resid-init.jsp" %>

<section id="resid">
    <h2><liferay-ui:message key="account-resid" /></h2>
	<!-- Etape 2 -->
	<c:if test="${not empty error}">
		<!-- Message d'erreur -->
		<div class="error"><liferay-ui:message key="${error}" /></div>
	</c:if>
	<c:if test="${empty error}">
		<c:forEach items="${dc.dossierResponse.dossiers}" var="dossier" varStatus="count"> 
			<div name="${dossier.numeroDossier}" class="dossier">
				<c:if test="${count.index gt 0}" >
					<div class="btn-more"><liferay-ui:message key="dossier-x" arguments="${dossier.numeroDossier}" /></div>
					<div class="btn-minus hide"><liferay-ui:message key="dossier-x" arguments="${dossier.numeroDossier}" /></div>
				</c:if>
				<c:if test="${!(count.index gt 0)}" >
					<div class="btn-more hide"><liferay-ui:message key="dossier-x" arguments="${dossier.numeroDossier}" /></div>
					<div class="btn-minus"><liferay-ui:message key="dossier-x" arguments="${dossier.numeroDossier}" /></div>
				</c:if>
			</div>
			<div id="dossier${dossier.numeroDossier}" <c:if test="${count.index gt 0}" > class="hide" </c:if> style="margin-bottom: 40px;"> 
				<c:if test="${dossier.dateFinValiditeDossier != null}">
			        <c:set var="today" value="${dc.today}" />
			        <c:if test="${today.isAfter(dossier.dateFinValiditeDossier)}">
			        	<div class="warning">
			        		<strong><liferay-ui:message key="warning" /></strong><br>
			        		<liferay-ui:message key="expire-validity-text" />
			        	</div>
			        </c:if>
				</c:if>
				<c:set var="forfaits" value="${dossier.forfaits}" />
				<c:if test="${empty forfaits and dossier.zone.typeUsager.code == 'RESID'}">
		        	<div class="warning">
		        		<strong><liferay-ui:message key="warning" /></strong><br>
		        		<liferay-ui:message key="no-forfait-text" />
		        	</div>
				</c:if>
			    <div class="webform-layout-box">
					<!-- Contractant principal -->
					<c:set var="contractant" value="${dossier.contractantPrincipal}" />
			        <div class="form-group">
		    			<div class="upper-title"><label><liferay-ui:message key="resid" /></label></div>
						<!-- Nom -->
						<div class="title">
							<label><liferay-ui:message key="name" /></label>
						</div>
						<div class="content">
							<p>${contractant.prenom} ${contratant.nom}</p>
						</div>
						<!-- Adresse -->
						<c:set var="adresse" value="${contractant.adresse}" />
						<div class="title">
							<label><liferay-ui:message key="address" /></label>
						</div>
						<div class="content">
							<p>
								<c:if test="${not empty adresse.numero}">
									${adresse.numero}
								</c:if>
								<c:if test="${not empty adresse.extension}">
									 ${adresse.extension}
								</c:if>
								<c:if test="${not empty adresse.voie}">
									 ${adresse.voie}
								</c:if>
								<c:if test="${not empty adresse.complementVoie}">
									<br>${adresse.complementVoie}
								</c:if>
								<c:if test="${not empty adresse.complementVoie2}">
									<br>${adresse.complementVoie2}
								</c:if>
								<c:if test="${not empty adresse.codePostal}">
									<br>${adresse.codePostal}
								</c:if>
								<c:if test="${not empty adresse.ville}">
									 ${adresse.ville}
								</c:if>
							</p>
						</div>
			        </div>
				
					<!-- Dossier -->
					<c:set var="zone" value="${dossier.zone}" />
			        <div class="form-group">
		    			<div class="upper-title"><label><liferay-ui:message key="dossier" /></label></div>
						<!-- Zone -->
						<div class="title">
							<label><liferay-ui:message key="zone" /></label>
						</div>
						<div class="content">
							<a href="${dc.zoneURL}" target="_blank" title="<liferay-ui:message key="zone-text-x" arguments="${zone.code}" /> - ${zone.intitule}(<liferay-ui:message key="eu.new-window"/>)" >
								${zone.intitule}
							</a>
						</div>
						<!-- fin de validité du titre -->
						<div class="title">
							<label><liferay-ui:message key="validity-resident-title-end" /></label>
						</div>
						<div class="content">
							<p>
								<c:if test="${dossier.dateFinValiditeDossier != null}">
									<fmt:parseDate value="${dossier.dateFinValiditeDossier}" pattern="yyyy-MM-dd" var="expireValidity" type="both" />
									<fmt:formatDate value="${expireValidity}" type="date" var="newexpireValidity" pattern="dd/MM/yyyy" />
									${newexpireValidity}
								</c:if>
							</p>
						</div>
						<!-- Type -->
						<div class="title">
							<label><liferay-ui:message key="type" /></label>
						</div>
						<div class="content">
							<p>
								<c:if test="${dossier.zone.typeUsager.code == 'RESID'}">
									<liferay-ui:message key="type-resid" />
								</c:if>
								<c:if test="${dossier.zone.typeUsager.code == 'RESIDEO'}">
									<liferay-ui:message key="type-resideo" />
								</c:if>
							</p>
						</div>
			        </div>
				</div>
				
			    <div class="webform-layout-box">
					<!-- Véhicule principal -->
					<c:set var="vehiculePrincipal" value="${dossier.vehiculePrincipal}" />
					<c:if test="${not empty vehiculePrincipal}">
				        <div class="form-group">
			    			<div class="upper-title"><label><liferay-ui:message key="principal-vehicule" /></label></div>
							<!-- immatriculation -->
							<div class="title">
								<label>
									<c:if test="${!vehiculePrincipal.hasCarteGriseProvisoire()}">
										<liferay-ui:message key="immatriculation" />
									</c:if>
									<c:if test="${vehiculePrincipal.hasCarteGriseProvisoire()}">
										<liferay-ui:message key="immatriculation-provisoire" />
									</c:if>
								</label>
							</div>
							<div class="content">
								<p>${vehiculePrincipal.immatriculation}</p>
							</div>
							<!-- validité carte grise -->
							<c:if test="${vehiculePrincipal.hasCarteGriseProvisoire()}">
								<div class="title">
									<label><liferay-ui:message key="validity" /></label>
								</div>
								<div class="content">
									<p>
										<c:if test="${vehiculePrincipal.dateFinValidite != null}">
											<fmt:parseDate value="${vehiculePrincipal.dateFinValidite}" pattern="yyyy-MM-dd" var="expireValidity" type="both" />
											<fmt:formatDate value="${expireValidity}" type="date" var="newexpireValidity" pattern="dd/MM/yyyy" />
											<liferay-ui:message key="until" /> ${newexpireValidity}
										</c:if>
									</p>
								</div>
							</c:if>
				        </div>
			        </c:if>
				
					<!-- Véhicule principal temporaire -->
					<c:set var="vehiculePrincipalTemporaire" value="${dossier.vehiculePrincipalTemporaire}" />
					<c:if test="${not empty vehiculePrincipalTemporaire}">
				        <div class="form-group">
			    			<div class="upper-title"><label><liferay-ui:message key="temporary-vehicule" /></label></div>
							<!-- immatriculation -->
							<div class="title">
								<label><liferay-ui:message key="immatriculation" /></label>
							</div>
							<div class="content">
								<p>${vehiculePrincipalTemporaire.immatriculation}</p>
							</div>
							<!-- validity -->
							<div class="title">
								<label><liferay-ui:message key="validity" /></label>
							</div>
							<div class="content">
								<p>
									<c:if test="${vehiculePrincipalTemporaire.dateFinValidite != null}">
										<fmt:parseDate value="${vehiculePrincipalTemporaire.dateFinValidite}" pattern="yyyy-MM-dd" var="expireValidity" type="both" />
										<fmt:formatDate value="${expireValidity}" type="date" var="newexpireValidity" pattern="dd/MM/yyyy" />
										<liferay-ui:message key="until" /> ${newexpireValidity}
									</c:if>
								</p>
							</div>
				        </div>
			   		</c:if>
				</div>
				
				<!-- Forfaits-->
				<c:if test="${not empty forfaits}">
				    <div class="webform-layout-box" id="forfaits">
				        <div class="form-group">
				    		<div class="upper-title"><label><liferay-ui:message key="forfaits" /></label></div>
				    		<ul>
								<c:forEach items="${forfaits}" var="forfait" varStatus="status">
									<c:if test="${status.first}">
										<c:set var="startForfait" value="${forfait.dateDebut}" />
										<c:set var="endForfait" value="${forfait.dateFin}" />
									</c:if>
									<c:if test="${!status.first}">
										<c:choose>
										    <c:when test="${endForfait.plusDays(1) == forfait.dateDebut}">
												<c:set var="endForfait" value="${forfait.dateFin}" />
										    </c:when>    
										    <c:otherwise>
												<fmt:parseDate value="${startForfait}" pattern="yyyy-MM-dd" var="startDate" type="both" />
												<fmt:formatDate value="${startDate}" type="date" var="newStartDate" pattern="dd/MM/yyyy" />
												<fmt:parseDate value="${endForfait}" pattern="yyyy-MM-dd" var="endDate" type="both" />
												<fmt:formatDate value="${endDate}" type="date" var="newEndDate" pattern="dd/MM/yyyy" />
												<c:set value="${newStartDate}-${newEndDate}" var="dates"/>
												<li>
													<liferay-ui:message key="dates-x-x" arguments="${fn:split(dates , '-')}"/> 
												</li>
												<c:set var="startForfait" value="${forfait.dateDebut}" />
												<c:set var="endForfait" value="${forfait.dateFin}" />
										    </c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
								<fmt:parseDate value="${startForfait}" pattern="yyyy-MM-dd" var="startDate" type="both" />
								<fmt:formatDate value="${startDate}" type="date" var="newStartDate" pattern="dd/MM/yyyy" />
								<fmt:parseDate value="${endForfait}" pattern="yyyy-MM-dd" var="endDate" type="both" />
								<fmt:formatDate value="${endDate}" type="date" var="newEndDate" pattern="dd/MM/yyyy" />
								<c:set value="${newStartDate}-${newEndDate}" var="dates"/>
								<li>
									<liferay-ui:message key="dates-x-x" arguments="${fn:split(dates , '-')}"/> 
								</li> 
				    		</ul>
				    	</div>
					</div>
				</c:if>
				
				<!-- Véhicule secondaire -->
				<c:set var="vehiculeSecondaire" value="${dossier.vehiculeSecondaire}" />
				<c:if test="${not empty vehiculeSecondaire}">
				    <div class="webform-layout-box">
				        <div class="form-group">
			    			<div class="upper-title"><label><liferay-ui:message key="secondary-vehicule" /></label></div>
							<div class="title">
							</div>
							<div class="content">
								<p>
									<liferay-ui:message key="secondary-vehicule-text-x" arguments="${vehiculeSecondaire.immatriculation}" />
								</p>
							</div>
				        </div>
					</div>
				</c:if>
			</div>
		</c:forEach>
	</c:if>
	
    <div class="form-group">
		<div class="content" align="center">
			<a href="${dc.residURL}" target="_blank" class="btn-square--bordered--core" title="<liferay-ui:message key="resid-site"/>(<liferay-ui:message key="eu.new-window"/>)">
				<span class="flexbox">
					<span class="btn-text">
						<liferay-ui:message key="resid-site"/>
					</span>
					<span class="btn-arrow"></span>
				</span>
			</a>
		</div>
	</div>
</section>
