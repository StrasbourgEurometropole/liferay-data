<%@ include file="/search-asset-init.jsp"%>

<h1 class="search-asset-result-count">
	<liferay-ui:message key="research" />
	<c:if test="${not dc.hideResultsBeforeSearch or dc.userSearch or param.paginate}">
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
	</c:if>
</h1>
<div class="search-asset-fields">

	<!-- Type d'asset -->
	<c:if test="${dc.displayAssetType}">
        <c:set var="classNamesOrStructures" value="${dc.classNamesOrStructures}" />
        <c:if test="${fn:length(classNamesOrStructures) gt 1}">
            <div class="asset-type-selection">
                <legend>
                    <liferay-ui:message key="show" />
                </legend>
                <div class="asset-type-selection-control open">
                    <c:forEach items="${classNamesOrStructures}" var="classNameOrStructure"
                        varStatus="classNameOrStructureStatus">
                        <aui:input type="checkbox" label="${classNameOrStructure[1]}"
                            name="classNamesOrStructures"
                            id="classNamesOrStructures_${classNameOrStructureStatus.index}"
                            value="${classNameOrStructure[0]}"
                            checked="${fn:contains(dc.filterClassNamesOrStructuresString, classNameOrStructure[0])}" />
                    </c:forEach>
                </div>
            </div>
        </c:if>
	</c:if>

	<!-- Vocabulaires -->
	<aui:input type="hidden" name="vocabulariesCount"
		value="${fn:length(dc.vocabularies)}" />
	<c:forEach items="${dc.vocabularies}" var="vocabulary"
		varStatus="vocStatus">
		<div class="vocabulary-selection">
			<legend>${not empty vocabulary.getDescription(locale) ? vocabulary.getDescription(locale) : vocabulary.getTitle(locale)}</legend>
			<div class="vocabulary-selection-control open">
				<!-- Dropdown -->
				<c:if
					test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'list'}">
					<aui:select name="vocabulary_${vocStatus.index}" label=""
						showEmptyOption="true" ignoreRequestValue="true">
						<c:forEach items="${dc.getSortedCategories(vocabulary)}" var="category">
							<aui:option value="${category.categoryId}"
								label="${category.getTitle(locale)}"
								selected="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}" />
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
		</div>
	</c:forEach>

	<!-- Champ date -->
	<c:if test="${dc.dateField}">
		<div class="date-selection">
			<legend>
				<liferay-ui:message key="eu.dates" />
			</legend>
			<div class="date-selection-control open">
				<label><liferay-ui:message key="search-asset-from" /></label>
				<liferay-ui:input-date name="fromDate" nullable="true"
					cssClass="date-selector" dayParam="fromDay" dayValue="${dc.fromDay}" monthParam="fromMonth" monthValue="${dc.fromMonthIndex}"
					yearParam="fromYear" yearValue="${dc.fromYear}" />
				<label><liferay-ui:message key="search-asset-to" /></label>
				<liferay-ui:input-date name="toDate" nullable="true"
					cssClass="date-selector" dayParam="toDay" dayValue="${dc.toDay}" monthParam="toMonth" monthValue="${dc.toMonthIndex}"
					yearParam="toYear" yearValue="${dc.toYear}" />
			</div>
		</div>
	</c:if>
	
	<!-- Tri -->
	<c:if test="${dc.displayDateSorting}">
		<div class="order-selection">
			<legend>
				<liferay-ui:message key="search-asset-sort" />
			</legend>
			<div class="order-selection-control open">
				<aui:select name="sortFieldAndType" label="" showEmptyOption="true">
					<c:if test="${not empty dc.keywords or not empty dc.boostTagsNames}">
						<aui:option value="score,desc" label="score-desc" />
					</c:if>
					<aui:option value="${dc.defaultSortField},asc"
						label="dates-asc" />
					<aui:option value="${dc.defaultSortField},desc"
						label="dates-desc" />
				</aui:select>
				<aui:input type="hidden" name="sortingChanged" value="false" />
			</div>
		</div>
	</c:if>

	<aui:input type="hidden" name="keywords" id="keywords" value="${dc.keywords}" />
</div>
	
<aui:button type="submit" value="search" />