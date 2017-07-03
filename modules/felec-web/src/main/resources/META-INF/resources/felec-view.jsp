<%@ include file="/felec-init.jsp" %>

<%-- Action URL du formulaire --%>
<portlet:actionURL var="searchURL" />

<aui:form name="fm" action="${searchURL}">
	
	<!-- Nom -->
	<aui:input type="text" name="name" label="felec.name" />
	
	<!-- Prénom -->
	<aui:input type="text" name="first-name" label="felec.firstname" />
	
	<!-- Date de naissance -->
	<aui:input type="text" name="birthdate" label="felec.birthdate" helpMessage="felec.birthdate.exemple" />
	
	<!-- Lieu de naissance -->
	<aui:input type="text" name="birthplace" label="felec.birthplace" />
	
	<!-- Validation -->
	<aui:button type="submit" value="search" />
</aui:form>


<!-- Messages d'erreur -->
<liferay-ui:error key="all-fields-required" message="all-fields-required" />
<liferay-ui:error key="date-not-valid" message="date-not-valid" />
<liferay-ui:error key="invalid-characters" message="invalid-characters" />


<!-- Résultats -->
<c:if test="${not empty felecResponse}">
	<div class="felec-response">
		<c:if test="${felecResponse.responseCode eq 1 or felecResponse.responseCode eq 0}">
			Nous n'avons pas trouv&eacute; d'&eacute;lecteur pour les crit&egrave;res suivants :
			${firstname} ${name}, n&eacute;(e) le ${birthdate} &agrave; ${birthplace}.
			<br>
			<br>
			<a href="https://www.service-public.fr/particuliers/vosdroits/R16396" target="_blank" title="(nouvelle fen&ecirc;tre)">Inscrivez-vous en ligne en moins de 10 minutes</a> (une photographie de bonne qualit&eacute; de chaque pi&egrave;ce justificative est accept&eacute;e).
			<br><br>
			<strong>Attention</strong> : vous devez vous inscrire avant le 31 d&eacute;cembre pour pouvoir voter l'ann&eacute;e suivante.
		</c:if>
		<c:if test="${felecResponse.responseCode eq 2}">
			Vous &ecirc;tes bien inscrit sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg,
			au bureau de vote ${felecResponse.stationNumber} : 
			<br><br>
			${felecResponse.stationLabel}<br>
			${felecResponse.address1}<br>
			<c:if test="${not empty felecResponse.address2}">${felecResponse.address2}<br></c:if>
			${felecResponse.address3}
			<br><br>
			<c:if test="${not empty felecResponse.center}">
				ATTENTION, vous &ecirc;tes inscrit dans le centre de vote suivant : ${felecResponse.centerCountry} - ${felecResponse.center}
				<br>
				Pour les &eacute;lections Pr&eacute;sidentielle, L&eacute;gislatives, R&eacute;f&eacute;rendum national et Europ&eacute;ennes, vous devrez obligatoirement voter dans votre centre de vote.
			</c:if>
		</c:if>
		<c:if test="${felecResponse.responseCode eq 3}">
			Vous &ecirc;tes bien inscrit sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg. 
			<br><br>
			Une modification de votre inscription est en cours et prendra effet au ${felecResponse.applicationDate}.
			<br><br>
			A compter de cette date, vous pourrez voter au bureau de vote ${felecResponse.stationNumber} :
			<br>
			${felecResponse.stationLabel}<br>
			${felecResponse.address1}<br>
			<c:if test="${not empty felecResponse.address2}">${felecResponse.address2}<br></c:if>
			${felecResponse.address3}<br>
			<c:if test="${not empty felecResponse.center}">
				ATTENTION, vous &ecirc;tes inscrit dans le centre de vote suivant : ${felecResponse.centerCountry} - ${felecResponse.center}
				<br>
				Pour les &eacute;lections Pr&eacute;sidentielle, L&eacute;gislatives, R&eacute;f&eacute;rendum national et Europ&eacute;ennes, vous devrez obligatoirement voter dans votre centre de vote.
			</c:if>
		</c:if>
		<c:if test="${felecResponse.responseCode eq 4}">
			Vous  &ecirc;tes en cours de radiation de la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg. 
			<br><br>
			La date d'effet de cette radiation est ${felecResponse.applicationDate}.
			Inscrivez-vous en ligne en moins de 10 minutes (une photographie de bonne qualit&eacute; de chaque pi&egrave;ce justificative est accept&eacute;e).
			<a href="https://www.service-public.fr/particuliers/vosdroits/R16396" target="_blank" title="(nouvelle fen&ecirc;tre)">Inscrivez-vous en ligne en moins de 10 minutes</a> (une photographie de bonne qualit&eacute; de chaque pi&egrave;ce justificative est accept&eacute;e).
			<br><br>
			<strong>Attention</strong> : vous devez vous inscrire avant le 31 d&eacute;cembre pour pouvoir voter l'ann&eacute;e suivante.
		</c:if>
		<c:if test="${felecResponse.responseCode eq 5}">
			Vous &ecirc;tes en cours d'inscription sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg.
			<br><br>
			Votre inscription sera effective au ${felecResponse.applicationDate}.
			A compter de cette date, vous pourrez voter au bureau de vote :
			<br>
			${felecResponse.stationLabel}<br>
			${felecResponse.address1}<br>
			<c:if test="${not empty felecResponse.address2}">${felecResponse.address2}<br></c:if>
			${felecResponse.address3}
			<br><br>
			<c:if test="${not empty felecResponse.center}">
				ATTENTION, vous &ecirc;tes inscrit dans le centre de vote suivant : ${felecResponse.centerCountry} - ${felecResponse.center}
				<br>
				Pour les &eacute;lections Pr&eacute;sidentielle, L&eacute;gislatives, R&eacute;f&eacute;rendum national et Europ&eacute;ennes, vous devrez obligatoirement voter dans votre centre de vote.
			</c:if>
		</c:if>
		<c:if test="${felecResponse.responseCode eq 9}">
			Plusieurs &eacute;lecteurs r&eacute;pondent &agrave; ces crit&egrave;res. Pr&eacute;cisez votre recherche.
		</c:if>
	</div>
</c:if>
