<%@ include file="/search-asset-init.jsp"%>

<h3><liferay-ui:message key="eu.videos-theme.search-engine" /></h3> 
<div class="grid-3"> 
	<div class="field"> 
		<aui:input type="text" name="keywords" value="${dc.keywords}" label="keywords" />
	</div> 

	<!-- Vocabulaires -->
	<aui:input type="hidden" name="vocabulariesCount"
		value="${fn:length(dc.vocabularies)}" />
	<c:forEach items="${dc.vocabularies}" var="vocabulary"
		varStatus="vocStatus">
		<div class="field"> 
			<label for="${vocabulary.getName()} }">${not empty vocabulary.getDescription(locale) ? vocabulary.getDescription(locale) : vocabulary.getTitle(locale)}</label> 
			<!-- Dropdown -->
			<c:if
				test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'list'}">
				<aui:select name="vocabulary_${vocStatus.index}" label=""
					showEmptyOption="false" ignoreRequestValue="true">
					<aui:option ><liferay-ui:message key="eu.videos-theme.all" /> ${not empty vocabulary.getDescription(locale) ? vocabulary.getDescription(locale) : vocabulary.getTitle(locale)}</aui:option>
					<c:forEach items="${dc.getDropdownRootCategories(vocabulary)}" var="category">
						<c:set var="category" value="${category}" scope="request"/>
						<c:set var="dc" value="${dc}" scope="request"/>
						<c:set var="level" value="0" scope="request" />
						<jsp:include page="/forms/category-option.jsp"/>
					</c:forEach>
				</aui:select>
			</c:if>
			<!-- Checkbox -->
			<c:if
				test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'check'}">
				<c:forEach items="${vocabulary.categories}" var="category"
					varStatus="catStatus">
					<aui:input type="checkbox" name="vocabulary_${vocStatus.index}"
						value="${category.categoryId}"
						checked="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}"
						id="vocabulary_${vocStatus.index}_${catStatus.index}"
						label="${category.getTitle(locale)}" />
				</c:forEach>
			</c:if>
			<!-- Radio -->
			<c:if
				test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'radio'}">
				<c:forEach items="${vocabulary.categories}" var="category"
					varStatus="catStatus">
					<aui:input type="radio" name="vocabulary_${vocStatus.index}"
						value="${category.categoryId}"
						checked="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}"
						id="vocabulary_${vocStatus.index}_${catStatus.index}"
						label="${category.getTitle(locale)}" />
				</c:forEach>
			</c:if>
		</div>
	</c:forEach>
	
	
	<!-- Tri -->
	<c:if test="${dc.displayDateSorting}">
		<div class="field"> 
			<label for="order">Ordre</label> 
			<div class="order-selection-control open">
				<aui:select name="sortFieldAndType" label="" showEmptyOption="false">
					<c:if test="${not empty dc.keywords or not empty dc.boostTagsNames}">
						<aui:option value="score,DESC" label="score-desc" />
					</c:if>
					<aui:option value="${dc.defaultSortField},ASC"
						label="eu.videos-theme.dates-desc" />
					<aui:option value="${dc.defaultSortField},DESC"
						label="eu.videos-theme.dates-asc" />
				</aui:select>
				<aui:input type="hidden" name="sortingChanged" value="false" />
			</div>
		</div>
	</c:if>
</div> 

<aui:button type="submit" value="search" />
