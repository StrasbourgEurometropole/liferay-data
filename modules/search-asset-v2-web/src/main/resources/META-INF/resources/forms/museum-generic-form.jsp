<%@ include file="/search-asset-init.jsp"%>
<div class="search-asset-fields">

	<!-- Champ date -->
	<c:if test="${dc.dateField}">
		<div class="date-selection">
			<legend>
				<liferay-ui:message key="eu.dates" />
			</legend>
            <c:if test="${dc.displayDatesButtons}">
                <c:set var="dateSelected" value="${dc.dateSelected}" />
                <input type="hidden" name="<portlet:namespace />dateSelected" value="" />
                <div class="filter-line">
                    <a id="today" class="button1 ${dateSelected == 'today' ? 'active' : ''}" aria-label="<liferay-ui:message key="today" />" title="<liferay-ui:message key="today" />" ><liferay-ui:message key="today" /></a>
                    <a id="tomorrow" class="button1 ${dateSelected == 'tomorrow' ? 'active' : ''}" aria-label="<liferay-ui:message key="tomorrow" />" title="<liferay-ui:message key="tomorrow" />" ><liferay-ui:message key="tomorrow" /></a>
                    <a id="week-end" class="button1 ${dateSelected == 'week-end' ? 'active' : ''}" aria-label="<liferay-ui:message key="eu.this-week-end" />" title="<liferay-ui:message key="eu.this-week-end" />" ><liferay-ui:message key="eu.this-week-end" /></a>
                </div>
            </c:if>
			<div class="date-selection-control">
                <label for="<portlet:namespace />fromDate">
                    <liferay-ui:message key="search-asset-from" />
                </label>
                <span class="mns-ico-date">
                    <input class="date" name="from" data-type="date" type="text" id="<portlet:namespace />fromDate" placeholder="JJ/MM/AAAA"
                        value="${dc.fromDay}/${dc.fromMonthValue lt 10 ? '0' :''}${dc.fromMonthValue}/${dc.fromYear}" aria-label="<liferay-ui:message key="eu.event.from" />">
                    <input type="hidden" name="<portlet:namespace />fromDay" data-name="fromDay" value="${dc.fromDay}" />
                    <input type="hidden" name="<portlet:namespace />fromMonth" data-name="fromMonth" value="${dc.fromMonthIndex}" />
                    <input type="hidden" name="<portlet:namespace />fromYear" data-name="fromYear" value="${dc.fromYear}" />
                </span>
                <label for="<portlet:namespace />toDate">
                    <liferay-ui:message key="search-asset-to" />
                </label>
                <span class="mns-ico-date">
                    <input class="date" name="to" data-type="date" type="text" id="<portlet:namespace />toDate" placeholder="JJ/MM/AAAA"
                        value="${dc.toDay}/${dc.toMonthValue lt 10 ? '0' :''}${dc.toMonthValue}/${dc.toYear}" aria-label="<liferay-ui:message key="eu.event.to" />">
                    <input type="hidden" name="<portlet:namespace />toDay" data-name="toDay" value="${dc.toDay}" />
                    <input type="hidden" name="<portlet:namespace />toMonth" data-name="toMonth" value="${dc.toMonthIndex}" />
                    <input type="hidden" name="<portlet:namespace />toYear" data-name="toYear" value="${dc.toYear}" />
                </span>
			</div>
		</div>
	</c:if>

	<!-- Vocabulaires -->
	<aui:input type="hidden" name="vocabulariesCount"
		value="${fn:length(dc.vocabularies)}" />
	<c:forEach items="${dc.vocabularies}" var="vocabulary"
		varStatus="vocStatus">
		<div class="vocabulary-selection">
			<legend>
			    <label for="vocabulary_${vocStatus.index}">${not empty vocabulary.getDescription(locale) ? vocabulary.getDescription(locale) : vocabulary.getTitle(locale)}</label>
            </legend>
			<div class="vocabulary-selection-control">
				<!-- Dropdown -->
				<c:if test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'list'}">
                    <select id="vocabulary_${vocStatus.index}" name="<portlet:namespace />vocabulary_${vocStatus.index}">
                        <option value="" disabled><liferay-ui:message key="eu.search.asset.web.select" /></option>
                        <option value="-1">&nbsp;</option>
                        <c:forEach items="${dc.getDropdownRootCategories(vocabulary)}" var="category">
                            <c:set var="category" value="${category}" scope="request"/>
                            <c:set var="dc" value="${dc}" scope="request"/>
                            <c:set var="level" value="0" scope="request" />
                            <jsp:include page="/forms/category-option.jsp"/>
                        </c:forEach>
                    </select>
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

    <div class="keywords-selection">
        <legend>
            <liferay-ui:message key="keywords" />
        </legend>
        <div class="keywords-selection-control">
            <input type="text" name="<portlet:namespace />keywords" id="<portlet:namespace />keywords" value="${dc.keywords}" placeholder='<liferay-ui:message key="eu.search.asset.web.keyword" />' />
        </div>
    </div>
</div>

<a href="javascript:$('.search-asset-form form').submit()" id="submit" class="button1" aria-label="<liferay-ui:message key="search" />" title="<liferay-ui:message key="search" />"><liferay-ui:message key="search" /></a>

<liferay-util:html-bottom>
	<script src="/o/searchassetv2web/js/bloc-date.js"></script>
</liferay-util:html-bottom>