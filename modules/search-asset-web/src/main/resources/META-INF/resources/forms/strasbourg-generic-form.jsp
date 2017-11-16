<%@ include file="/search-asset-init.jsp"%>

<div class="seu-filter-line">

	<c:if test="${dc.dateField}">
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
	</c:if>
	
	<div class="widget widget-big">
        <div class="title">
            <label for="name">
                <liferay-ui:message key="keywords" />
            </label>
        </div>
        <div class="content">
            <input type="text" id="name" name="<portlet:namespace />keywords" 
                placeholder="<liferay-ui:message key="please-enter-keyword" />" value="${dc.keywords}">
        </div>
    </div>
	<c:forEach items="${dc.vocabularies}" var="vocabulary"
		varStatus="vocStatus">
	    <div class="widget">
	        <div class="title">
	            <label for="vocabulary_${vocStatus.index}">
	                ${not empty vocabulary.getDescription(locale) ? vocabulary.getDescription(locale) : vocabulary.getTitle(locale)}
	            </label>
	        </div>
	        <div class="content">
	            <select class="" id="vocabulary_${vocStatus.index}" multiple="multiple" name="<portlet:namespace />vocabulary_${vocStatus.index}">
	                <option value="" disabled></option>
	                <c:forEach items="${dc.getSortedCategories(vocabulary)}" var="category">
	                    <c:set var="category" value="${category}" scope="request"/>
	                    <c:set var="dc" value="${dc}" scope="request"/>
	                    <c:set var="level" value="0" scope="request" />
	                    <jsp:include page="/forms/category-option.jsp"/>
	                </c:forEach>
	            </select>
	        </div>
	    </div>
    </c:forEach>
</div>
<aui:input type="hidden" name="vocabulariesCount" value="${fn:length(dc.vocabularies)}" />
<div class="seu-btn-line">
    <liferay-portlet:renderURL var="formURL" />
    <button type="button" onclick="window.location.href = '${formURL}'" class="seu-btn-square seu-bordered seu-core">
        <span class="seu-flexbox">
            <span class="seu-btn-text" style="margin-right: 0">
                <liferay-ui:message key="cancel" />
            </span>
        </span>
    </button>
    <button type="submit" class="seu-btn-square seu-filled seu-core">
        <span class="seu-flexbox">
            <span class="seu-btn-text">
                <liferay-ui:message key="search" />
            </span>
            <span class="seu-btn-arrow"></span>
        </span>
    </button>
</div>