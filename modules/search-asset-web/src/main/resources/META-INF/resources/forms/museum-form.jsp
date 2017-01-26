<%@ include file="/search-asset-init.jsp"%>

<h1 class="search-asset-result-count">
	<liferay-ui:message key="research" />
	<c:if test="${not dc.hideResultsBeforeSearch or dc.userSearch}">
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
	<!-- Type d'assets -->
	<c:if test="${fn:length(dc.classNames) gt 1}">
		<div class="asset-type-selection">
			<legend>
				<liferay-ui:message key="show" />
			</legend>
			<div class="asset-type-selection-control open">
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

	<!-- Vocabulaires -->
	<aui:input type="hidden" name="vocabulariesCount"
		value="${fn:length(dc.vocabularies)}" />
	<c:forEach items="${dc.vocabularies}" var="vocabulary"
		varStatus="vocStatus">
		<div class="vocabulary-selection">
			<legend>${vocabulary.getTitle(locale)}</legend>
			<div class="vocabulary-selection-control open">
				<!-- Dropdown -->
				<c:if
					test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'list'}">
					<aui:select name="vocabulary_${vocStatus.index}" label=""
						showEmptyOption="true">
						<c:forEach items="${vocabulary.categories}" var="category">
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

	<c:if test="${dc.dateField}">
		<div class="date-selection">
			<legend>
				<liferay-ui:message key="eu.dates" />
			</legend>
			<div class="date-selection-control open">
				<label><liferay-ui:message key="search-asset-from" /></label>
				<liferay-ui:input-date name="fromDate" nullable="true"
					cssClass="date-selector" dayParam="fromDay" dayValue="${dc.fromDay}" monthParam="fromMonth" monthValue="${dc.fromMonth}"
					yearParam="fromYear" yearValue="${dc.fromYear}" />
				<label><liferay-ui:message key="search-asset-to" /></label>
				<liferay-ui:input-date name="toDate" nullable="true"
					cssClass="date-selector" dayParam="toDay" dayValue="${dc.toDay}" monthParam="toMonth" monthValue="${dc.toMonth}"
					yearParam="toYear" yearValue="${dc.toYear}" />
			</div>
		</div>
		<div class="order-selection">
			<legend>
				<liferay-ui:message key="search-asset-sort" />
			</legend>
			<div class="order-selection-control open">
				<aui:input name="orderByCol" type="hidden" value="dates" />
				<aui:select name="orderByType" label="">
					<aui:option value="asc"
						label="dates-asc"
						selected="${dc.orderByType eq 'asc' and dc.orderByCol eq 'dates' }" />
					<aui:option value="desc"
						label="dates-desc"
						selected="${dc.orderByType eq 'desc' and dc.orderByCol eq 'dates'}" />
				</aui:select>
			</div>
		</div>
	</c:if>
	<c:if test="${not dc.dateField}">
		<aui:input name="orderByCol" type="hidden" value="score" />
		<aui:input name="orderByType" type="hidden" value="desc" />
	</c:if>

	<aui:input type="hidden" name="keywords" id="keywords" value="${dc.keywords}" />
</div>
	
<aui:button type="submit" value="search" />