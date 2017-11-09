<%@ include file="/internal-link-init.jsp"%>
<div class="seu-container">
    <div class="rte">
        <h2>
            <liferay-ui:message key="${strasbourgPortletTitle}" />
        </h2>
    </div>
    <c:forEach items="${selectedLayouts}" var="layout" varStatus="loopStatus">
        <a href="${layoutURL}" 
            title="${layout.getName(locale)}"
            class="seu-btn-square seu-bordered seu-core" >
            <span class="seu-btn-text"> 
                ${layout.getName(locale)}
            </span>
            <span class="seu-btn-arrow"></span>
        </a>
        <c:if test="${loopStatus.index eq 0}">
        	<br>
        </c:if>
    </c:forEach>
</div>
