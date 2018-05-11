<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
<#assign httpServletRequest = serviceContext.getRequest()>

<section id="vote">
    <h2><liferay-ui:message key="vote-place" /></h2>
	<div class="felec-response rte">
    	<c:if test="${not empty champsNull}">
			<!-- Tous les champs n'ont pas étés renseignés -->
			<div class="main-response">
				Pour conna&icirc;tre les coordonn&eacute;es de votre bureau de vote, merci de renseigner la ou les information(s) suivante(s) :<br />
				<c:forEach items="${champsNull}" var="champs" varStatus="loopStatus">
					${champs}
	        		<c:if test="${!loopStatus.last}">
	        			<br />
	 	        	</c:if>
				</c:foreach>
			</div>
		</c:if>
    	<c:if test="${not empty felecResponse}">
			<c:if test="${felecResponse.responseCode eq 1 or felecResponse.responseCode eq 0}">
				<!-- Electeur inconnu -->
				<div class="main-response">
					Nous n'avons pas trouv&eacute; d'&eacute;lecteur pour les crit&egrave;res suivants
				</div>
				<div>
					<strong>${firstname} ${name}, n&eacute;(e) le ${birthdate} &agrave; ${birthplace}.</strong>
				</div>
				<div>
					<a href="https://www.service-public.fr/particuliers/vosdroits/R16396" target="_blank" title="Inscrivez-vous en ligne (nouvelle fen&ecirc;tre)">Inscrivez-vous en ligne en moins de 10 minutes</a> 
					<br>
					(une photographie de bonne qualit&eacute; de chaque pi&egrave;ce justificative est accept&eacute;e).
					<br>
					Vous devez vous inscrire avant le 31 d&eacute;cembre pour pouvoir voter l'ann&eacute;e suivante.
 					<br><br>
					En cas de problème, vous pouvez contacter le 03 68 98 68 94 du lundi au vendredi de 8h00 à 17h00 en continu ou 
					<a>vous rendre au centre administratif ou en mairie de quartier</a>. 
					<div align="center">
						<a href="${strasbourgPropsUtil.getPublikProfileURL()}" class="btn-square--bordered--core">
							<span class="flexbox">
								<span class="btn-text"><liferay-ui:message key="modify-account" /></span>
								<span class="btn-arrow"></span>
							</span>
						</a>
					</div>
				</div>
			</c:if>
			<c:if test="${felecResponse.responseCode eq 2}">
				<!-- Electeur connu -->
				<div class="main-response">Vous &ecirc;tes bien inscrit sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg </div>
				<div class="poll-address">
					<div class="poll-number">Bureau de vote ${felecResponse.stationNumber}</div>
					<div>
						${felecResponse.stationLabel}<br>
						${felecResponse.address1}<br>
						<c:if test="${not empty felecResponse.address2}">${felecResponse.address2}<br></c:if>
						${felecResponse.address3}
					</div>
				</div>
				<c:if test="${not empty felecResponse.center}">
					<div class="warning">
						ATTENTION, vous &ecirc;tes inscrit dans le centre de vote suivant : ${felecResponse.centerCountry} - ${felecResponse.center}
						<br>
						Pour les &eacute;lections Pr&eacute;sidentielle, L&eacute;gislatives, R&eacute;f&eacute;rendum national et Europ&eacute;ennes, vous devrez obligatoirement voter dans votre centre de vote.
					</div>
				</c:if>
			</c:if>
			<c:if test="${felecResponse.responseCode eq 3}">
				<!-- Modification en cours -->
				<div class="main-response">
					Vous &ecirc;tes bien inscrit sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg
				</div>
				<div>
					Une modification de votre inscription est en cours et prendra effet au ${felecResponse.applicationDate}.
					<br>
					A compter de cette date, vous pourrez voter au bureau de vote suivant.
				</div>
				<div class="poll-address">
					<div class="poll-number">Bureau de vote ${felecResponse.stationNumber}</div>
					<div>
						${felecResponse.stationLabel}<br>
						${felecResponse.address1}<br>
						<c:if test="${not empty felecResponse.address2}">${felecResponse.address2}<br></c:if>
						${felecResponse.address3}>
					</div>
				</div>
				<c:if test="${not empty felecResponse.center}">
					<div class="warning">
						ATTENTION, vous &ecirc;tes inscrit dans le centre de vote suivant : ${felecResponse.centerCountry} - ${felecResponse.center}
 					<br> 
						Pour les &eacute;lections Pr&eacute;sidentielle, L&eacute;gislatives, R&eacute;f&eacute;rendum national et Europ&eacute;ennes, vous devrez obligatoirement voter dans votre centre de vote.
					</div>
				</c:if>
			</c:if>
			<c:if test="${felecResponse.responseCode eq 4}">
				<!-- Radiation en cours -->
				<div class="main-response">
					Vous  &ecirc;tes en cours de radiation de la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg.
				</div>
				<div>
					La date d'effet de cette radiation est ${felecResponse.applicationDate}.
					Inscrivez-vous en ligne en moins de 10 minutes (une photographie de bonne qualit&eacute; de chaque pi&egrave;ce justificative est accept&eacute;e).
					<a href="https://www.service-public.fr/particuliers/vosdroits/R16396" target="_blank" title="Inscrivez-vous en ligne (nouvelle fen&ecirc;tre)">Inscrivez-vous en ligne en moins de 10 minutes</a> (une photographie de bonne qualit&eacute; de chaque pi&egrave;ce justificative est accept&eacute;e).
				</div>
				<div class="warning">
					<strong>Attention</strong> : vous devez vous inscrire avant le 31 d&eacute;cembre pour pouvoir voter l'ann&eacute;e suivante.
				</div>
			</c:if>
			<c:if test="${felecResponse.responseCode eq 5}">
				<!-- Inscription en cours -->
				<div class="main-response">
					Vous &ecirc;tes en cours d'inscription sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg
				</div>
				<div>>
					Votre inscription sera effective au ${felecResponse.applicationDate}.
					A compter de cette date, vous pourrez voter au bureau de vote :
				</div>
				<div class="poll-address">
					${felecResponse.stationLabel}<br>
					${felecResponse.address1}<br>
					<c:if test="${not empty felecResponse.address2}">${felecResponse.address2}<br></c:if>
					${felecResponse.address3}
				</div>
				<c:if test="${not empty felecResponse.center}">
					<div class="warning">
						ATTENTION, vous &ecirc;tes inscrit dans le centre de vote suivant : ${felecResponse.centerCountry} - ${felecResponse.center}
						<br>
						Pour les &eacute;lections Pr&eacute;sidentielle, L&eacute;gislatives, R&eacute;f&eacute;rendum national et Europ&eacute;ennes, vous devrez obligatoirement voter dans votre centre de vote.
					</div>
				</c:if>
			</c:if>
			<c:if test="${felecResponse.responseCode eq 9}">
				<!-- RequÃÂªte trop vague -->
				<div class="main-response">
					Plusieurs &eacute;lecteurs r&eacute;pondent &agrave; ces crit&egrave;res, pr&eacute;cisez votre recherche.
				</div>
			</c:if>
		</c:if>
	</div>
</section>