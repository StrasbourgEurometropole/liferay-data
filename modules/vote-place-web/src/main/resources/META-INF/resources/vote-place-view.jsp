<%@ include file="/vote-place-init.jsp" %>
<c:if test="${empty virtualHostName}">
    <c:set var="homeURL" value="/web/strasbourg.eu/"/>
</c:if>
<c:if test="${not empty virtualHostName}">
    <c:set var="homeURL" value="https://${virtualHostName}/"/>
</c:if>

<section id="vote">
    <%-- Récupère le fait de plier ou déplier ce widget dans la config de la personnalisation --%>
    <c:set value="${isFolded}" var="isFolded" />
    <div class="buttons">
        <%-- Vérifie si ce widget peut être plié dans la config de la personnalisation --%>
        <c:if test="${showRetractableButton}">
            <button class="${isFolded?'retractable-folded-wi':'retractable-unfolded-wi'}" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
        </c:if>
        <%-- Vérifie si ce widget peut être masqué dans la config de la personnalisation --%>
        <c:if test="${showDeleteButton}">
            <button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
        </c:if>
    </div>
    <h2>${title}</h2>
    <div class="detail" ${isFolded?'style="display: none;"':''}>
        <c:if test="${not empty error}">
            <div class="error"><liferay-ui:message key="eu.webservice-indispo" /></div>
        </c:if>
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
                                La recherche ne peut aboutir, vous pouvez prendre contact avec le bureau des &eacute;lections au 03.68.98.68.94.
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
                            <br><br>
					        <a href="https://www.service-public.fr/particuliers/vosdroits/N47" target="_blank" title="Consultez votre situation &eacute;lectorale (nouvelle fen&ecirc;tre)">Consultez votre situation &eacute;lectorale sur le site www.service-public.fr</a>
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
				                <!-- Electeur connu -->
                                Vous &ecirc;tes bien inscrit sur la liste &eacute;lectorale "${felecResponse.list}" de la Ville de Strasbourg.
                                <div class="poll-address">
                                    <div class="poll-number">Bureau de vote ${felecResponse.stationNumber}</div>
                                    <div>
                                        ${felecResponse.stationLabel}<br>
                                        ${felecResponse.address1}<br>
                                        <c:if test="${not empty felecResponse.address2}">${felecResponse.address2}<br></c:if>
                                        ${felecResponse.address3}
                                    </div>
                                </div>
                                <div>
                                    <c:if test="${felecResponse.codeList.equals('E')}">
                                        Remarque : vous ne pouvez voter qu'aux &eacute;lections europ&eacute;ennes.
                                    </c:if>
                                    <c:if test="${felecResponse.codeList.equals('M')}">
                                        Remarque : vous ne pouvez voter qu'aux &eacute;lections municipales.
                                    </c:if>
                                    <c:if test="${felecResponse.codeList.equals('C')}">
                                        Remarque : vous ne pouvez voter qu'aux &eacute;lections  municipales <strong>et</strong> europ&eacute;enne.
                                    </c:if>
                                </div>
                                <c:if test="${not empty felecResponse.center}">
                                    <div class="warning">
                                        ATTENTION, vous &ecirc;tes inscrit dans le centre de vote suivant : ${felecResponse.centerCountry} - ${felecResponse.center}
                                        <br>
                                        Pour les &eacute;lections Pr&eacute;sidentielle, L&eacute;gislatives, R&eacute;f&eacute;rendum national et Europ&eacute;ennes, vous serez inviter &agrave; voter dans votre centre de vote.
                                    </div>
                                </c:if>
                            </c:when>
                            <c:when test="${felecResponse.responseCode eq 3}">
                                <!-- Modification en cours -->
                                Une modification de votre inscription est en cours, merci de prendre contact avec le bureau des &eacute;lections au 03.68.98.68.94.
                            </c:when>
                            <c:when test="${felecResponse.responseCode eq 5}">
                                <!-- Inscription en cours -->
                                Votre inscription est en cours, pour plus d'information vous pouvez prendre contact avec le bureau des &eacute;lections au 03.68.98.68.94.
                            </c:when>
                        </c:choose>
                    </div>
                    <c:if test="${office != null && felecResponse.responseCode eq 2}">
                        <a href="#" class="add-favorites"
                            data-type="1"
                            data-title="${office.getAlias(locale)}"
                            data-url="${homeURL}lieu/-/entity/sig/${office.getSIGid()}/${office.getNormalizedAlias(locale)}"
                            data-id="${office.placeId}">
                            <span><liferay-ui:message key="eu.add-to-favorite" /></span>
                        </a>
                    </c:if>
                    <!-- <c:if test="${felecResponse.returnCard eq 'O'}">
                        <div class="error">
                            Votre carte d'&eacute;lecteur est disponible <a href="https://www.strasbourg.eu/lieu/-/entity/sig/462_SPC_38">&agrave; l'accueil du centre administratif</a>
                        </div>
                    </c:if>
                    <div align="right">
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
    </div>
</section>