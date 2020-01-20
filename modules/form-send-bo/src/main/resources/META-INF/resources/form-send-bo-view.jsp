<%@ include file="/META-INF/resources/form-send-bo-init.jsp"%>

<c:set var="tab" value="${not empty param.tab ? param.tab : 'forms' }" />

<!-- Corps de la page et selection du listing a afficher -->
<c:choose>
    <c:when test="${tab eq 'forms'}">
        <liferay-util:include page="/form-send-bo-view-forms.jsp" servletContext="<%=application %>">
        </liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'viewFormSends'}">
        <liferay-util:include page="/form-send-bo-view-form-send.jsp" servletContext="<%=application %>">
        </liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'viewReportings'}">
        <liferay-util:include page="/form-send-bo-view-form-send.jsp" servletContext="<%=application %>">
        </liferay-util:include>
    </c:when>
</c:choose>
