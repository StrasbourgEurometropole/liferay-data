<%@ include file="/search-asset-init.jsp"%>

<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />

	<!-- BLOC FACETTES DE TRI -->
	<!-- Recherche par dates -->
	<c:if test="${dc.dateField}">
		<div class="ops-facette-checkbox ops-dropdown">
			<a href="#" class="selected"></a> <a href="#"><liferay-ui:message key="eu.search.asset.web.ops.agenda.month" /></a>
			<ul>
				<c:forEach begin="0" end="14" varStatus="loop">
					<li>
						<label>${dc.getMonthYearTitle(loop.index, locale)}
							<input type="checkbox" id="ops_index_${loop.index}" name="<portlet:namespace />fromMonthLoop" value="${loop.index}" />
							<span></span>
						</label>
					</li>
					
					<c:if test="${dc.getFromMonthLoopValue() == loop.index}">
						<c:set var="monthId" value="ops_index_${loop.index}" scope="page"/>
					</c:if>
				</c:forEach>
				
				<input type="hidden" name="<portlet:namespace />fromDay" value="1" />
			</ul>
		</div>
	</c:if>

	<div class="ops-facette-checkbox ops-dropdown">
		<a href="#" class="selected"></a> <a href="#"><liferay-ui:message key="eu.search.asset.web.ops.agenda.subscriptions" /></a>
		<ul>
			<c:set var="subscriptionVocabulary" value="${vocabularyAccessor.getEventSubscriptionTypes(groupID)}" />
			<c:forEach items="${dc.getDropdownRootCategories(subscriptionVocabulary)}" var="category">
			
				<li><label>${category.getTitle(locale)}<input type="checkbox" 
				id="${category.categoryId}" name="<portlet:namespace />vocabulary_1" value="${category.categoryId}" /><span></span></label></li>
				
				<c:if test="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}">
					<c:set var="subscriptionId" value="${category.categoryId}" scope="page"/>
				</c:if>
				
			</c:forEach>
		</ul>
	</div>


	<div class="ops-facette-checkbox ops-dropdown">
		<a href="#" class="selected"></a> <a href="#"><liferay-ui:message key="eu.search.asset.web.ops.agenda.typology" /></a>
		<ul>
			<c:set var="typologieVocabulary" value="${vocabularyAccessor.getEventTypologies(groupID)}" />
			<c:forEach items="${dc.getDropdownRootCategories(typologieVocabulary)}" var="category">
			
				<li><label>${category.getTitle(locale)}<input type="checkbox" 
				id="${category.categoryId}" name="<portlet:namespace />vocabulary_0" value="${category.categoryId}" /><span></span></label></li>
				
				<c:if test="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}">
					<c:set var="typologieId" value="${category.categoryId}" scope="page"/>
				</c:if>
				
			</c:forEach>
		</ul>
	</div> 
	
	<aui:input type="hidden" name="vocabulariesCount" value="2" />

	<!-- FACETTE RECHERCHE  -->
	<div class="ops-facette-search">
		<label for="ops-intern-search" aria-hidden="true">Rechercher</label> <input type="text" id="ops-intern-search" value="${dc.keywords}" name="<portlet:namespace />keywords" placeholder="<liferay-ui:message key="please-enter-keyword" />" /> <span class="icon-ico-search"></span>
	</div>


	<!-- SOUMETTRE LE FORMULAIRE -->
	<div class="ops-button-submit">
		<input type="submit" class="ops-btn" value="<liferay-ui:message key="eu.search.asset.web.ops.agenda.send" />" />
	</div>
<script>

$(document).ready(function () {
	
	<c:if test="${!empty typologieId}">
		$('#${typologieId}').click();
	</c:if>
	<c:if test="${!empty subscriptionId}">
		$('#${subscriptionId}').click();
	</c:if>
	<c:if test="${!empty monthId}">
		$('#${monthId}').click();
	</c:if>
    
});
	
</script>