<%@ include file="/council-init.jsp"%>

<c:choose>

    <%-- UTILISATEUR CONFIRME --%>
    <c:when test = "${dc.isConfirmedCouncilUser()}">

        <div class="council-flex council-web">
            <div class="detail-delib seu-container">

                <input type="hidden" id="deliberationId" value=""/>
                <input type="hidden" id="stage" value=""/>
                <input type="hidden" id="absent" value=""/>

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
        	<script src="/o/councilweb/js/libs/platform.js" type="text/javascript"></script>
            <script src="/o/councilweb/js/council-init-javascript.js" type="text/javascript"></script>
            <script src="/o/councilweb/js/council-dynamic-view.js" type="text/javascript"></script>
            <script>
                  var obj = ${dc.fetchUserFront(officialConnectedId, userDeviceInfo)};
                  displayInfos(obj)
            </script>
        </liferay-util:html-bottom>

    </c:when>

    <c:otherwise>
        <div class="council-web">
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
                    <div class="java-front-message-wrapper">
                        <h2 class="main-message-text">
                            <liferay-ui:message key='site.for.officials.only'/>
                        </h2>
                        <p class="delib-refresh" onClick="document.location.reload(true)"><svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="#31455d"><path d="M9 12l-4.463 4.969-4.537-4.969h3c0-4.97 4.03-9 9-9 2.395 0 4.565.942 6.179 2.468l-2.004 2.231c-1.081-1.05-2.553-1.699-4.175-1.699-3.309 0-6 2.691-6 6h3zm10.463-4.969l-4.463 4.969h3c0 3.309-2.691 6-6 6-1.623 0-3.094-.65-4.175-1.699l-2.004 2.231c1.613 1.526 3.784 2.468 6.179 2.468 4.97 0 9-4.03 9-9h3l-4.537-4.969z"/></svg></p>
                    </div>
                </c:when>

                <%-- TEST DE PRESENCE SESSION --%>
                <c:when test = "${dc.isSessionNotAvailable()}">
                    <div class="java-front-message-wrapper">
                        <h2 class="main-message-text">
                            <liferay-ui:message key='no.session.today'/>
                        </h2>
                        <p class="delib-refresh" onClick="document.location.reload(true)"><svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="#31455d"><path d="M9 12l-4.463 4.969-4.537-4.969h3c0-4.97 4.03-9 9-9 2.395 0 4.565.942 6.179 2.468l-2.004 2.231c-1.081-1.05-2.553-1.699-4.175-1.699-3.309 0-6 2.691-6 6h3zm10.463-4.969l-4.463 4.969h3c0 3.309-2.691 6-6 6-1.623 0-3.094-.65-4.175-1.699l-2.004 2.231c1.613 1.526 3.784 2.468 6.179 2.468 4.97 0 9-4.03 9-9h3l-4.537-4.969z"/></svg></p>
                    </div>
                </c:when>

                <%-- TEST DU BON TYPE D'ELU --%>
                <c:when test = "${dc.isOfficialTypeMismatchOrNotActive()}">
                    <div class="java-front-message-wrapper">
                        <h2 class="main-message-text">
                            <liferay-ui:message key='session.reserved.to.others'/>
                        </h2>
                        <p class="delib-refresh" onClick="document.location.reload(true)"><svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="#31455d"><path d="M9 12l-4.463 4.969-4.537-4.969h3c0-4.97 4.03-9 9-9 2.395 0 4.565.942 6.179 2.468l-2.004 2.231c-1.081-1.05-2.553-1.699-4.175-1.699-3.309 0-6 2.691-6 6h3zm10.463-4.969l-4.463 4.969h3c0 3.309-2.691 6-6 6-1.623 0-3.094-.65-4.175-1.699l-2.004 2.231c1.613 1.526 3.784 2.468 6.179 2.468 4.97 0 9-4.03 9-9h3l-4.537-4.969z"/></svg></p>
                    </div>
                </c:when>

            </c:choose>

        </div>
    </c:otherwise>

</c:choose>