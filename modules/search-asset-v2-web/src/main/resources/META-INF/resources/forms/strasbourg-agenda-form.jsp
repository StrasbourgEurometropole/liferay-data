<%@ include file="/search-asset-init.jsp"%>

<div class="seu-filter-line">
    <div class="widget">
        <div class="title">
            <label for="date-start"><liferay-ui:message key="eu.event.from-date" /></label>
        </div>
        <div class="content">
            <input name="from" data-type="date" type="text" id="date-start" placeholder="JJ/MM/AAAA" 
            	value="${dc.fromDay}/${dc.fromMonthValue}/${dc.fromYear}">
            <input type="hidden" name="<portlet:namespace />fromDay" data-name="fromDay" value="${dc.fromDay}" />
            <input type="hidden" name="<portlet:namespace />fromMonth" data-name="fromMonth" value="${dc.fromMonthIndex}" />
            <input type="hidden" name="<portlet:namespace />fromYear" data-name="fromYear" value="${dc.fromYear}" />
        </div>
    </div>
    <div class="widget">
        <div class="title">
            <label for="date-end"><liferay-ui:message key="eu.event.to" /></label>
        </div>
        <div class="content">
            <input name="to" data-type="date" type="text" id="date-end" placeholder="JJ/MM/AAAA" 
            	value="${dc.toDay}/${dc.toMonthValue}/${dc.toYear}">
            <input type="hidden" name="<portlet:namespace />toDay" data-name="toDay" value="${dc.toDay}" />
            <input type="hidden" name="<portlet:namespace />toMonth" data-name="toMonth" value="${dc.toMonthIndex}" />
            <input type="hidden" name="<portlet:namespace />toYear" data-name="toYear" value="${dc.toYear}" />
        </div>
    </div>
    <div class="widget widget-big">
        <div class="title">
            <label for="name"><liferay-ui:message key="keywords" /></label>
        </div>
        <div class="content">
            <input type="text" id="name" name="<portlet:namespace />keywords" 
            	placeholder="<liferay-ui:message key="please-enter-keyword" />" value="${dc.keywords}">
        </div>
    </div>
            <div class="widget">
            <div class="title">
                <label for="theme"><liferay-ui:message key="eu.place" /></label>
            </div>
            <div class="content">
				<select  class="" name="idSIGPlace" >
				</select>
			</div>				
		</div>
</div>
<c:set var="showAdvancedSearch" value="${not empty dc.filterCategoriesIdsString}" />
<c:if test="${showAdvancedSearch}">
	<c:set var="displayBlockIfAdvancedSearch" value="style=\"display: block;\"" />
	<c:set var="openIfAdvancedSearch" value="opened" />
</c:if>
<div class="seu-btn-line advanced">
    <button type="button" class="seu-btn-square seu-filled seu-second seu-trigger-advanced-search ${openIfAdvancedSearch}">
        <span class="seu-flexbox">
            <span class="seu-btn-text"><liferay-ui:message key="advanced-search" /></span>
            <span class="seu-btn-arrow"></span>
        </span>
    </button>
</div>
<div class="seu-filter-advanced-search" ${displayBlockIfAdvancedSearch}>
    <div class="seu-filter-line">
        <div class="widget">
            <div class="title">
                <label for="public"><liferay-ui:message key="eu.event-public" /></label>
            </div>
            <div class="content">
                <select class="" id="public" multiple="multiple" name="<portlet:namespace />vocabulary_0">
                    <option value="" disabled><liferay-ui:message key="eu.event-choose-public" /></option>
                    <c:set var="publicVocabulary" value="${vocabularyAccessor.eventPublics}" />
                    <c:forEach items="${dc.getDropdownRootCategories(publicVocabulary)}" var="category">
						<c:set var="category" value="${category}" scope="request"/>
						<c:set var="dc" value="${dc}" scope="request"/>
						<c:set var="level" value="0" scope="request" />
						<jsp:include page="/forms/category-option.jsp"/>
					</c:forEach>
                </select>
            </div>
        </div>
        <div class="widget">
            <div class="title">
                <label for="type"><liferay-ui:message key="eu.event-type" /></label>
            </div>
            <div class="content">
                <select class="" id="type" multiple="multiple" name="<portlet:namespace />vocabulary_1">
                    <option value="" disabled><liferay-ui:message key="eu.event-choose-type" /></option>
                    <c:set var="typeVocabulary" value="${vocabularyAccessor.eventTypes}" />
                    <c:forEach items="${dc.getDropdownRootCategories(typeVocabulary)}" var="category">
						<c:set var="category" value="${category}" scope="request"/>
						<c:set var="dc" value="${dc}" scope="request"/>
						<c:set var="level" value="0" scope="request" />
						<jsp:include page="/forms/category-option.jsp"/>
					</c:forEach>
                </select>
            </div>
        </div>
        <div class="widget">
            <div class="title">
                <label for="theme"><liferay-ui:message key="eu.event-theme" /></label>
            </div>
            <div class="content">
                <select class="" id="theme" multiple="multiple" name="<portlet:namespace />vocabulary_2">
                    <option value="" disabled><liferay-ui:message key="eu.event-choose-theme" /></option>
                    <c:set var="themeVocabulary" value="${vocabularyAccessor.eventThemes}" />
                    <c:forEach items="${dc.getDropdownRootCategories(themeVocabulary)}" var="category">
						<c:set var="category" value="${category}" scope="request"/>
						<c:set var="dc" value="${dc}" scope="request"/>
						<c:set var="level" value="0" scope="request" />
						<jsp:include page="/forms/category-option.jsp"/>
					</c:forEach>
                </select>
            </div>
        </div>
        <div class="widget">
            <div class="title">
                <label for="territoire"><liferay-ui:message key="eu.event-territory" /></label>
            </div>
            <div class="content">
                <select class="" id="territoire" multiple="multiple" name="<portlet:namespace />vocabulary_3">
                    <option value="" disabled><liferay-ui:message key="eu.event-choose-territory" /></option>
                    <c:set var="territoryVocabulary" value="${vocabularyAccessor.territories}" />
                    <c:forEach items="${dc.getDropdownRootCategories(territoryVocabulary)}" var="category">
						<c:set var="category" value="${category}" scope="request"/>
						<c:set var="dc" value="${dc}" scope="request"/>
						<c:set var="level" value="0" scope="request" />
						<jsp:include page="/forms/category-option.jsp"/>
					</c:forEach>
                </select>
            </div>
        </div>
    </div>                    
</div>

<aui:input type="hidden" name="vocabulariesCount" value="4" />

<div class="seu-btn-line">
	<liferay-portlet:renderURL var="agendaURL" />
	<button type="button" onclick="window.location.href = '${agendaURL}'" class="seu-btn-square seu-bordered seu-core"> 
	    <span class="seu-flexbox">            
	        <span class="seu-btn-text" style="margin-right: 0"><liferay-ui:message key="cancel" /></span>
	    </span>
	</button>

    <button type="submit" class="seu-btn-square seu-filled seu-core">
        <span class="seu-flexbox">
            <span class="seu-btn-text"><liferay-ui:message key="search" /></span>
            <span class="seu-btn-arrow"></span>
        </span>
    </button>
</div>

<liferay-util:html-bottom>
<aui:script>
	var idSIGPlace = '${dc.idSIGPlace}' ;
</aui:script>
	
	<script src="/o/searchassetweb/js/strasbourg-agenda.js"></script>
</liferay-util:html-bottom>

	