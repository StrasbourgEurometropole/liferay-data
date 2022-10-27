<%@ include file="/search-asset-init.jsp"%>

<div class="col-xs-12">
	<div class="row mns-affiner">
      <span class="mns-more hidden-xs"><liferay-ui:message key="refine-your-search" /></span>
    </div>
	
	<!-- Champ mots-clés -->
	<div class="row form-group">
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

		<div class="row form-group date-selection">
			<div class="mns-label-top">
				<label><liferay-ui:message key="eu.dates" /></label>
			</div>

            <c:if test="${dc.displayDatesButtons}">
                <div class="buttons">
                    <c:set var="dateSelected" value="${dc.dateSelected}" />
                    <input type="hidden" name="<portlet:namespace />dateSelected" value="" />" />
                    <a id="today" class="mns-btn ${dateSelected == 'today' ? 'active' : ''}"><liferay-ui:message key="today" /></a>
                    <a id="tomorrow" class="mns-btn ${dateSelected == 'tomorrow' ? 'active' : ''}"><liferay-ui:message key="tomorrow" /></a>
                    <a id="week-end" class="mns-btn ${dateSelected == 'week-end' ? 'active' : ''}"><liferay-ui:message key="eu.this-week-end" /></a>
                </div>
            </c:if>

			<span class="mns-ico-date">
                <input name="from" data-type="date" type="text" id="<portlet:namespace />fromDate" placeholder="JJ/MM/AAAA"
                    value="${dc.fromDay}/${dc.fromMonthValue lt 10 ? '0' :''}${dc.fromMonthValue}/${dc.fromYear}">
                <input type="hidden" name="<portlet:namespace />fromDay" data-name="fromDay" value="${dc.fromDay}" />
                <input type="hidden" name="<portlet:namespace />fromMonth" data-name="fromMonth" value="${dc.fromMonthIndex}" />
                <input type="hidden" name="<portlet:namespace />fromYear" data-name="fromYear" value="${dc.fromYear}" />
			</span>
			<span class="between-form">
				<liferay-ui:message key="search-asset-to" />
			</span>
			<span class="mns-ico-date">
                <input name="to" data-type="date" type="text" id="<portlet:namespace />toDate" placeholder="JJ/MM/AAAA"
                    value="${dc.toDay}/${dc.toMonthValue lt 10 ? '0' :''}${dc.toMonthValue}/${dc.toYear}">
                <input type="hidden" name="<portlet:namespace />toDay" data-name="toDay" value="${dc.toDay}" />
                <input type="hidden" name="<portlet:namespace />toMonth" data-name="toMonth" value="${dc.toMonthIndex}" />
                <input type="hidden" name="<portlet:namespace />toYear" data-name="toYear" value="${dc.toYear}" />
			</span>
		</div>
	</c:if>
	
	<!-- Vocabulaires -->
	<aui:input type="hidden" name="vocabulariesCount"
		value="${fn:length(dc.vocabularies)}" />
	<c:forEach items="${dc.vocabularies}" var="vocabulary"
		varStatus="vocStatus">
		<div class="row form-group">
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

<liferay-util:html-bottom>
	<script src="/o/searchassetv2web/js/bloc-date.js"></script>
</liferay-util:html-bottom>


<style>
    .mns-p-list-agenda .mns-z-filtres-search .buttons{
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
        margin-bottom: 30px;
    }

    .mns-p-list-agenda .mns-z-filtres-search .buttons .mns-btn{
        border: solid 1px #baa56f;
        text-align: center;
    }

    .mns-p-list-agenda .mns-z-filtres-search .buttons .mns-btn.active{
        background: #baa56f;
        color: white;
    }

    @media only screen and (max-width: 767px){
        .mns-p-list-agenda .mns-z-filtres-search .buttons .msn-btn{
            width: 100%;
        }
    }
</style>