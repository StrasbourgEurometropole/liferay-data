<%@ include file="/search-asset-init.jsp"%>
<div class="search-asset-fields">

	<!-- Champ date -->
	<c:if test="${dc.dateField}">
		<div class="date-selection">
			<legend>
				<liferay-ui:message key="eu.dates" />
			</legend>
			<div class="date-selection-control">
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

	<!-- Vocabulaires -->
	<aui:input type="hidden" name="vocabulariesCount"
		value="${fn:length(dc.vocabularies)}" />
	<c:forEach items="${dc.vocabularies}" var="vocabulary"
		varStatus="vocStatus">
		<div class="vocabulary-selection">
			<legend>${not empty vocabulary.getDescription(locale) ? vocabulary.getDescription(locale) : vocabulary.getTitle(locale)}</legend>
			<div class="vocabulary-selection-control">
				<!-- Dropdown -->
				<c:if
					test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'list'}">
					<aui:select name="vocabulary_${vocStatus.index}" label=""
						showEmptyOption="true" ignoreRequestValue="true">
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
		</div>
	</c:forEach>

	<!-- Type d'assets -->
	<c:if test="${fn:length(dc.classNames) gt 1}">
		<div class="asset-type-selection">
			<legend>
				<liferay-ui:message key="eu.search.asset.web.to-show" />
			</legend>
			<div class="asset-type-selection-control">
				<c:forEach items="${dc.classNames}" var="className"
					varStatus="classNameStatus">
					<aui:input type="checkbox" label="${className}"
						name="className"
						id="className_${classNameStatus.index}"
						value="${className}"
						checked="${fn:contains(dc.filterClassNamesString, className)}" />
				</c:forEach>
			</div>
		</div>
	</c:if>

	<aui:input type="hidden" name="keywords" id="keywords" value="${dc.keywords}" />
</div>