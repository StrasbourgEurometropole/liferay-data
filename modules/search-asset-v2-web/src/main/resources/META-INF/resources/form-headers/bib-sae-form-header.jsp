<%@ include file="/search-asset-init.jsp" %>
<div class="subtitle results-number">
	<span>${dc.searchContainer.total }
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
	</span>
</div>
<div class="portlet-cus-event-asset-fo">
	<div class="agenda">
		<div class="list-evt">