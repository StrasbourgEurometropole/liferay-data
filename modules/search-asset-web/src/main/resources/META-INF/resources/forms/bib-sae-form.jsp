<%@ include file="/search-asset-init.jsp"%>

<div class="formHeader">
	<div class="formTitle"><h1>${dc.getPortletTitle('agenda')}</h1></div>
	<div class="clearer"></div>
</div>
<div class="search-asset-fields portlet-cus-event-asset-fo">
	<div class="block-filter">
		<div class="col-filter">
			<!-- Mots clÃ©s -->
			<div class="keyword-field">
				<aui:input type="text" name="keywords" value="${dc.keywords}" label="keywords" />
			</div>

			<!-- Vocabulaires -->
			<aui:input type="hidden" name="vocabulariesCount"
				value="${fn:length(dc.vocabularies)}" />
			<c:forEach items="${dc.vocabularies}" var="vocabulary"
				varStatus="vocStatus">
				<div class="vocabulary-selection">
					<div class="vocabulary-selection-control open">
						<!-- Dropdown -->
						<c:if
							test="${dc.vocabulariesControlTypes[vocStatus.index] eq 'list'}">
							<aui:select name="vocabulary_${vocStatus.index}" label="${not empty vocabulary.getDescription(locale) ? vocabulary.getDescription(locale) : vocabulary.getTitle(locale)}">
								<aui:option value="" label="all" />
								<c:forEach items="${dc.getDropdownRootCategories(vocabulary)}" var="category">
									<c:set var="category" value="${category}" scope="request"/>
									<c:set var="dc" value="${dc}" scope="request"/>
									<c:set var="level" value="0" scope="request" />
									<jsp:include page="/forms/category-option.jsp"/>
								</c:forEach>
							</aui:select>
						</c:if>
					</div>
				</div>
			</c:forEach>

			<!-- Champ date -->
			<c:if test="${dc.dateField}">
				<div class="date-selection">
					<label>
						<liferay-ui:message key="filter-by-date" />
					</label>
					<div class="date-selection-control open">
						<div>
							<label><liferay-ui:message key="search-asset-from" /></label>
							<liferay-ui:input-date name="fromDate" nullable="true"
								cssClass="date-selector" dayParam="fromDay" dayValue="${dc.fromDay}" monthParam="fromMonth" monthValue="${dc.fromMonthIndex}"
								yearParam="fromYear" yearValue="${dc.fromYear}" />
						</div>
						<div>
							<label><liferay-ui:message key="search-asset-to" /></label>
							<liferay-ui:input-date name="toDate" nullable="true"
								cssClass="date-selector" dayParam="toDay" dayValue="${dc.toDay}" monthParam="toMonth" monthValue="${dc.toMonthIndex}"
								yearParam="toYear" yearValue="${dc.toYear}" />
						</div>
					</div>
				</div>
			</c:if>
			<div style="width: 100%">
				<input type="submit" value="OK" class="btn-submit" />
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>