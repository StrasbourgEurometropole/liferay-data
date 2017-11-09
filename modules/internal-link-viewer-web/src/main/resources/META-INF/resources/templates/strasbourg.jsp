<%@ include file="/internal-link-init.jsp"%>

<div class="seu-wi seu-wi-crossreading">
    <div class="seu-container">
        <h2 class="seu-section-title">
            <span class="seu-title"><liferay-ui:message key="${strasbourgPortletTitle}" /></span>
        </h2>
        <div class="seu-wi-content">
			<c:forEach items="${selectedLayouts}" var="layout">
				<liferay-portlet:renderURL plid="${layout.plid}" var="layoutURL" />
	             <a href="${layoutURL}" 
	             class="seu-btn-square seu-bordered seu-core"  
	             title="${layout.getName(locale)}" >
	                 <span class="seu-flexbox">
	                     <span class="seu-btn-text">${layout.getName(locale)}</span>
	                     <span class="seu-btn-arrow"></span>
	                 </span>
	            </a>
        	</c:forEach>
        </div>
    </div>
</div>
