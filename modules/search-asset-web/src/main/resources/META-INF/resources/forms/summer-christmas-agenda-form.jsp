<%@ include file="/search-asset-init.jsp"%>

<div class="col-xs-12">
	<div class="mns-affiner">
      <span class="mns-more hidden-xs"><liferay-ui:message key="refine-your-search" /></span>
    </div>
	
	<!-- Champ mots-clés -->
	<div class="form-group">
        <div class="mns-label-top">
				<label><liferay-ui:message key="keywords" /></label>
			</div>
        <div class="content">
            <input type="text" id="name" name="<portlet:namespace />keywords" 
                placeholder="<liferay-ui:message key="please-enter-keyword" />" value="${dc.keywords}">
        </div>
    </div>
	
	<!-- Champ date -->
	<c:if test="${dc.dateField}">
		<div class="form-group date-selection">
			<div class="mns-label-top">
				<label><liferay-ui:message key="eu.dates" /></label>
			</div>
			<span class="mns-ico-date">
				<liferay-ui:input-date name="fromDate" nullable="true"
					cssClass="date-selector" dayParam="fromDay" dayValue="${dc.fromDay}" monthParam="fromMonth" monthValue="${dc.fromMonthIndex}"
					yearParam="fromYear" yearValue="${dc.fromYear}" />
			</span>
			<span class="between-form">
				<liferay-ui:message key="search-asset-to" />
			</span>
			<span class="mns-ico-date">
				<liferay-ui:input-date name="toDate" nullable="true"
					cssClass="date-selector" dayParam="toDay" dayValue="${dc.toDay}" monthParam="toMonth" monthValue="${dc.toMonthIndex}"
					yearParam="toYear" yearValue="${dc.toYear}" />
			</span>
		</div>
	</c:if>
	
	<!-- Vocabulaires -->
	<aui:input type="hidden" name="vocabulariesCount"
		value="${fn:length(dc.vocabularies)}" />
	<c:forEach items="${dc.vocabularies}" var="vocabulary"
		varStatus="vocStatus">
		<div class="form-group">
			<div class="mns-label-top">
				<label>${not empty vocabulary.getDescription(locale) ? vocabulary.getDescription(locale) : vocabulary.getTitle(locale)}</label>
				<div class="mns-expand-collapse expanded" data-checkboxes-id="${vocStatus.count}">
					<div class="mns-filter-expand" ></div>
					<div class="mns-filter-collapse"></div>
				</div>
			</div>
			<div class="checkbox" id="checkbox-${vocStatus.count}">
				<c:forEach items="${dc.getDropdownRootCategories(vocabulary)}" var="category"
					varStatus="catStatus">
					<aui:input type="checkbox" name="vocabulary_${vocStatus.index}"
						value="${category.categoryId}"
						checked="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}"
						id="vocabulary_${vocStatus.index}_${catStatus.index}"
						label="${category.getTitle(locale)}" />
				</c:forEach>
			</div>
		</div>
	</c:forEach>
	
	<aui:input type="hidden" name="keywords" id="keywords" value="${dc.keywords}" />
		
	<div class="mns-submit">
		<span></span>
		<input type="submit" id="submit" value="<liferay-ui:message key="refresh-search" />">
	</div>
</div>