<%@ include file="/council-init.jsp"%>

<c:choose>

    <%-- UTILISATEUR CONFIRME --%>
    <c:when test = "${dc.isConfirmedCouncilUser()}">

        <div class="council-flex council-web">
            <div class="detail-delib seu-container">

                <input type="hidden" id="deliberationId" value=""/>
                <input type="hidden" id="stage" value=""/>

                <%-- INCLUSION DES TEMPLATES --%>
                <%@ include file="/templates/message.jsp"%>
                <%@ include file="/templates/presentation-delib.jsp"%>
                <%@ include file="/templates/result.jsp"%>
                <%@ include file="/templates/vote-form.jsp"%>

            </div>
        </div>

        <%-- VARIABLES JS A PARTAGER --%>
        <liferay-util:html-top>
            <script>
                var namespace = "<portlet:namespace />";
                var groupId = ${dc.groupId};
                var useSkypeView = ${dc.configuration.useSkypeView()};
                var officialConnectedId = ${dc.officialId};
            </script>
        </liferay-util:html-top>

        <%-- INCLUSIONS DES JS --%>
        <liferay-util:html-bottom>
            <script src="/o/councilweb/js/council-init-javascript.js" type="text/javascript"></script>
            <script src="/o/councilweb/js/council-dynamic-view.js" type="text/javascript"></script>
        </liferay-util:html-bottom>

    </c:when>

    <c:otherwise>
        <div class="council-flex council-web">
            <div class="detail-delib seu-container">
                <h1 class="main-message-title">
                    <liferay-ui:message key='welcome.on.platform'/>
                </h1>

                <c:choose>

                    <%-- UTILISATEUR NON CONNECTE (LIFERAY OU PUBLIK) --%>
                    <c:when test = "${dc.isGuestUser()}">
                        <h2 class="main-message-text">
                            <liferay-ui:message key='to.continue.please.connect'/>
                        </h2>
                        <div class="btn-validate-vote seu-btn-line"
                                style="text-align: center; margin-top: 30px; margin-bottom: 30px;">
                            <a href="${dc.getPublikLoginURL()}"
                                    class="seu-btn-square seu-filled seu-second">
                                <span class="seu-flexbox">
                                    <span class="seu-btn-text">
                                        <liferay-ui:message key='connect.to.mon.strasbourg'/>
                                    </span>
                                </span>
                            </a>
                        </div>
                    </c:when>

                    <%-- UTILISATEUR PUBLIK SIMPLE --%>
                    <c:when test = "${dc.isSimplePublikUser()}">
                        <h2 class="main-message-text">
                            <liferay-ui:message key='site.for.officials.only'/>
                        </h2>
                    </c:when>

                    <%-- TEST DE PRESENCE SESSION --%>
                    <c:when test = "${dc.isSessionNotAvailable()}">
                        <h2 class="main-message-text">
                            <liferay-ui:message key='no.session.today'/>
                        </h2>
                    </c:when>

                    <%-- TEST DU BON TYPE D'ELU --%>
                    <c:when test = "${dc.isOfficialTypeMismatchOrNotActive()}">
                        <h2 class="main-message-text">
                            <liferay-ui:message key='session.reserved.to.${dc.getCouncilSessionType()}'/>
                        </h2>
                    </c:when>

                </c:choose>

            </div>
        </div>
    </c:otherwise>

</c:choose>