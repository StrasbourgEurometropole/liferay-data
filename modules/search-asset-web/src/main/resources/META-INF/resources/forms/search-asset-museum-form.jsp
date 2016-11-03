<%@ include file="/search-asset-init.jsp" %>

<h1 class="search-asset-result-count">
	Recherche
	<span>${fn:length(dc.entries)} <liferay-ui:message key="results" /></span>
</h1>
<div class="search-asset-fields">
	<!-- Type d'assets -->
	<c:if test="${fn:length(dc.classNames) gt 1}">
		<div class="asset-type-selection">
			<legend>
				<liferay-ui:message key="show" />
			</legend>
			<div class="asset-type-selection-control open">
				<aui:input type="hidden" name="classNamesCount"
					value="${fn:length(dc.classNames)}" />
				<c:forEach items="${dc.classNames}" var="className"
					varStatus="classNameStatus">
					<aui:input type="checkbox" label="${className}"
						name="className_${classNameStatus.index}" value="${className}"
						checked="${fn:contains(dc.filterClassNamesString, className)}" />
				</c:forEach>
			</div>
		</div>
	</c:if>

	<!-- Vocabulaires -->
	<aui:input type="hidden" name="vocabulariesCount"
		value="${fn:length(dc.vocabularies)}" />
	<c:forEach items="${dc.vocabularies}" var="vocabulary"
		varStatus="vocStatus">
		<div class="vocabulary-selection">
			<legend>${vocabulary.getTitle(locale)}</legend>
			<div class="vocabulary-selection-control open">
				<!-- Dropdown -->
				<c:if test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'list'}">
					<aui:select name="vocabulary_${vocStatus.index}"
						label="" showEmptyOption="true">
						<c:forEach items="${vocabulary.categories}" var="category">
							<aui:option value="${category.categoryId}"
								label="${category.getTitle(locale)}"
								selected="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}" />
						</c:forEach>
					</aui:select>
				</c:if>
				<!-- Checkbox -->
				<c:if test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'check'}">
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
				<c:if test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'radio'}">
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
		</div>
	</c:forEach>
	<aui:input type="hidden" name="keywords" id="keywords" />
</div>