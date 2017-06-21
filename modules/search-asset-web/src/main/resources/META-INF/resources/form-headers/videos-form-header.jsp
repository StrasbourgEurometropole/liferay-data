<%@ include file="/search-asset-init.jsp" %>

<c:if test="${not dc.hideResultsBeforeSearch or dc.userSearch or param.paginate}">
	<h3 id="results-count">${dc.searchContainer.total }
		<c:choose>
			<c:when test="${dc.searchContainer.total le 1}">
				<liferay-ui:message
					key="result" />
			</c:when> 
			<c:otherwise>
				<liferay-ui:message
					key="results" />	
			</c:otherwise>
		</c:choose>
	</h3>
</c:if>


<div class="video-grid"> 
	<div class="category"> 
		<div class="grid-3">