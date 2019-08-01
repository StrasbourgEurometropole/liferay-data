<%@ include file="/project-popup-init.jsp" %>

<liferay-util:include page="/modal/${popupTemplateId}.jsp" servletContext="<%=application %>" />

<c:if test="${isSignedIn}">
    <div style="border: solid red 5px; padding: 20px">
        <h1>Popups participer</h1>
        <p>Formulaire : ${(popupTemplateId == 'filePetitionDisable') ? 'filePetition' : popupTemplateId}</p>
    </div>
</c:if>