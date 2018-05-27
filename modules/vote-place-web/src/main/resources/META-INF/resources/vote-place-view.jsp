<%@ include file="/vote-place-init.jsp" %>
<c:if test="${empty virtualHostName}">
    <c:set var="homeURL" value="/web/strasbourg.eu/"/>
</c:if>
<c:if test="${not empty virtualHostName}">
    <c:set var="homeURL" value="https://${virtualHostName}/"/>
</c:if>

<section id="vote">
    <h2><liferay-ui:message key="vote-place" /></h2>
	<div class="felec-response rte">
    	<c:if test="${not empty champsNull}">
			<!-- Tous les champs n'ont pas étés renseignés -->
			<div class="main-response">
				Pour conna&icirc;tre les coordonn&eacute;es de votre bureau de vote, merci de renseigner la ou les information(s) suivante(s) :
				<div class="incomplet">
					<c:forEach var="champs" varStatus="loopStatus" items="${champsNull}">
						${champs}
		        		<c:if test="${!loopStatus.last}">
		        			<br />
		 	        	</c:if>
					</c:forEach>
				</div>
			</div>
			<div align="center">
				<a href="${strasbourgPropsUtil.getPublikProfileURL()}" class="btn-square--bordered--core">
					<span class="flexbox">
						<span class="btn-text">Compl&eacute;ter mon compte</span>
						<span class="btn-arrow"></span>
					</span>
				</a>
			</div>
		</c:if>
    	<c:if test="${not empty felecResponse}">
			<c:if test="${felecResponse.responseCode eq 0 or felecResponse.responseCode eq 1 or felecResponse.responseCode eq 4 or felecResponse.responseCode eq 9}">
				<div class="error">
					<c:choose>
	       				<c:when test="${felecResponse.responseCode eq 9}">
							<!-- Requête trop vague -->
							Plusieurs &eacute;lecteurs r&eacute;pondent &agrave; ces crit&egrave;res, pr&eacute;cisez votre recherche.
	       				</c:when>
	       				<c:when test="${felecResponse.responseCode eq 4}">
							<!-- Radiation en cours -->
							Vous  &ecirc;tes en cours de radiation de la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg.<br>
							La date d'effet de cette radiation est ${felecResponse.applicationDate}.
	       				</c:when>
	       				<c:otherwise>
							<!-- Electeur inconnu -->
							Nous n'avons pas trouv&eacute; d'&eacute;lecteur pour les crit&egrave;res suivants<br>
							<strong>${firstName} ${lastName}, n&eacute;(e) le ${birthDate} &agrave; ${birthPlace}.</strong>
						</c:otherwise>
	       			</c:choose>
				</div>
				<c:if test="${felecResponse.responseCode != 9}">
					<div class="main-response">
						<a href="https://www.service-public.fr/particuliers/vosdroits/R16396" target="_blank" title="Inscrivez-vous en ligne (nouvelle fen&ecirc;tre)">
							Inscrivez-vous en ligne en moins de 10 minutes
						</a>
						<br>
						Une photographie de bonne qualit&eacute; de chaque pi&egrave;ce justificative est accept&eacute;e.
						<br>
						Attention : Vous devez vous inscrire avant le 31 d&eacute;cembre pour pouvoir voter l'ann&eacute;e suivante.
	 					<br><br>
						En cas de probl&egrave;me, vous pouvez contacter le 03 68 98 68 94 du lundi au vendredi de 8h00 &agrave; 17h00 en continu ou
						<a href="${homeURL}centre-administratif-mairies-quartier" target="_blank" title="centre administratif et mairies de quartier (nouvelle fen&ecirc;tre)">vous rendre au centre administratif ou en mairie de quartier</a>.
					</div>
					<div align="center">
						<a href="${strasbourgPropsUtil.getPublikProfileURL()}" class="btn-square--bordered--core">
							<span class="flexbox">
								<span class="btn-text">modifier mon compte</span>
								<span class="btn-arrow"></span>
							</span>
						</a>
					</div>
				</c:if>
			</c:if>
			<c:if test="${felecResponse.responseCode eq 2 or felecResponse.responseCode eq 3 or felecResponse.responseCode eq 5}">
				<!-- Electeur connu -->
				<div class="main-response">
					<c:choose>
        				<c:when test="${felecResponse.responseCode eq 2}">
							Vous &ecirc;tes bien inscrit sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg.
        				</c:when>
        				<c:when test="${felecResponse.responseCode eq 3}">
							<!-- Modification en cours -->
							Vous &ecirc;tes bien inscrit sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg.
							<div>Une modification de votre inscription est en cours et prendra effet au ${felecResponse.applicationDate}.</div>
							<div>A compter de cette date, vous pourrez voter au bureau de vote ${felecResponse.stationNumber}</div>
        				</c:when>
        				<c:when test="${felecResponse.responseCode eq 5}">
							<!-- Inscription en cours -->
							Vous &ecirc;tes en cours d'inscription sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg.Votre inscription sera effective au ${felecResponse.applicationDate}.
							<div>A compter de cette date, vous pourrez voter au bureau de vote ${felecResponse.stationNumber}</div>
        				</c:when>
				    </c:choose>
				</div>
				<c:if test="${not empty felecResponse.center}">
					<div class="warning">
						ATTENTION, vous &ecirc;tes inscrit dans le centre de vote suivant : ${felecResponse.centerCountry} - ${felecResponse.center}
						<br>
						Pour les &eacute;lections Pr&eacute;sidentielle, L&eacute;gislatives, R&eacute;f&eacute;rendum national et Europ&eacute;ennes, vous serez inviter &agrave; voter dans votre centre de vote.
					</div>
				</c:if>
				<div class="poll-address">
					<c:if test="${felecResponse.responseCode eq 2}">
						<div class="poll-number">Bureau de vote ${felecResponse.stationNumber}</div>
					</c:if>
					<div>
						${felecResponse.stationLabel}<br>
						${felecResponse.address1}<br>
						<c:if test="${not empty felecResponse.address2}">${felecResponse.address2}<br></c:if>
						${felecResponse.address3}
					</div>
				</div>
				<c:if test="${office != null}">
					<a href="#" class="add-favorites"
			            data-type="1" 
			            data-title="${office.getAlias(locale)}" 
			            data-url="${homeURL}lieu/-/entity/sig/${office.getSIGid()}"
			            data-id="${office.placeId}">
			            <span><liferay-ui:message key="eu.add-to-favorite" /></span>
			        </a>
				</c:if>
				<c:if test="${felecResponse.returnCard eq 'O'}">
					<div class="error">
						Votre carte d'&eacute;lecteur est disponible &agrave; l'accueil du centre administratif
					</div>
				</c:if>
				<!-- <div align="right">
					<a href="" class="btn-square--bordered--core">
						<span class="flexbox">
							<span class="btn-text">Localiser votre bureau de vote</span>
							<span class="btn-arrow"></span>
						</span>
					</a>
				</div> -->
			</c:if>
		</c:if>
	</div>
</section>