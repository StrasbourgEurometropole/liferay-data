<%@ include file="/search-asset-init.jsp"%>



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


<div class="col-sm-4 col-xs-12">
<c:set var="typesVocabulary" value="${vocabularyAccessor.getTypesNoel(themeDisplay.getLayout().getGroupId())}" />
	<label for="types">${typesVocabulary.getDescription(locale)}</label>
	<div class="content">
		<select class="" id="public" name="<portlet:namespace />vocabulary_0">
             <aui:option value="0"><liferay-ui:message key="no-theme" /> </aui:option>
             <c:forEach items="${dc.getSortedCategories(typesVocabulary)}" var="category">
				<c:set var="category" value="${category}" scope="request"/>
				<c:set var="dc" value="${dc}" scope="request"/>
				<c:set var="level" value="0" scope="request" />
				<jsp:include page="/forms/category-option.jsp"/>
			</c:forEach>
         </select>
	</div>
</div>
<div class="col-sm-4 col-xs-12">
<c:set var="tauxVocabulary" value="${vocabularyAccessor.getTauxNoel(themeDisplay.getLayout().getGroupId())}" />
	<label for="taux">${tauxVocabulary.getDescription(locale)}</label>
	<div class="content">
		<select class="" id="public" name="<portlet:namespace />vocabulary_1">
             <aui:option value="0"><liferay-ui:message key="no-type" /> </aui:option>
             <c:forEach items="${dc.getSortedCategories(tauxVocabulary)}" var="category">
				<c:set var="category" value="${category}" scope="request"/>
				<c:set var="dc" value="${dc}" scope="request"/>
				<c:set var="level" value="0" scope="request" />
				<jsp:include page="/forms/category-option.jsp"/>
			</c:forEach>
         </select>
	</div>
</div>
<div class="col-sm-4 col-xs-12">
<c:set var="dureeVocabulary" value="${vocabularyAccessor.getDureesNoel(themeDisplay.getLayout().getGroupId())}" />
	<label for="duree">${dureeVocabulary.getDescription(locale)}</label>
	<div class="content">
		<select class="" id="public" name="<portlet:namespace />vocabulary_2">
             <aui:option value="0"><liferay-ui:message key="no-duration" /> </aui:option>
             <c:forEach items="${dc.getSortedCategories(dureeVocabulary)}" var="category">
				<c:set var="category" value="${category}" scope="request"/>
				<c:set var="dc" value="${dc}" scope="request"/>
				<c:set var="level" value="0" scope="request" />
				<jsp:include page="/forms/category-option.jsp"/>
			</c:forEach>
         </select>
	</div>
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