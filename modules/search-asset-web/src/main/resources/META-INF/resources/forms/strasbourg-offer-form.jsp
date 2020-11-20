<%@ include file="/search-asset-init.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="seu-filter-line row">

	<div class="widget widget-big">
        <div class="title">
            <label for="name">
                <liferay-ui:message key="keywords" />
            </label>
        </div>
        <div class="content">
            <input type="text" id="keyword" name="<portlet:namespace />keywords"
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
	            <select class="vocabularies-offer" id="vocabulary_${vocStatus.index}" multiple="multiple" name="<portlet:namespace />vocabulary_${vocStatus.index}">
	                <option value="" disabled></option>
	                <c:forEach items="${dc.getDropdownRootCategories(vocabulary)}" var="category">
                        <c:choose>
                            <c:when test="${dc.compare(vocabulary.name, '(E-Job) Filieres')}">
                                <aui:option value="${category.categoryId}"
                                    label="${category.getTitle(locale)}"
                                    selected="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="category" value="${category}" scope="request"/>
                                <c:set var="dc" value="${dc}" scope="request"/>
                                <c:set var="level" value="0" scope="request" />
                                <jsp:include page="/forms/category-option.jsp"/>
                            </c:otherwise>
                        </c:choose>
	                </c:forEach>
	            </select>
	        </div>
	    </div>
    </c:forEach>
</div>
<aui:input type="hidden" name="vocabulariesCount" value="${fn:length(dc.vocabularies)}" />
<div class="seu-btn-line">
    <liferay-portlet:renderURL var="formURL" />
    <button type="button" class="seu-btn-square seu-bordered seu-core" id="createAlert">
        <span class="seu-flexbox">
            <span class="seu-btn-alert"></span>
            <span class="seu-btn-text" style="margin-right: 0">
                <liferay-ui:message key="create-alert" />
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


<div id="create-alert-form" class="formCreateAlerte">
    <div class="seu-filter-line">
        <div class="widget widget-big" id="formAlert">
            <div class="title">
                <label for="name">
                    <liferay-ui:message key="alert-name" />
                </label>
            </div>
            <div class="content">
                <input type="text" id="alertName" maxlength="75" name="<portlet:namespace />alertName">
            </div>
        </div>
    </div>

    <button type="button" class="seu-btn-square seu-filled seu-core" id="RecordAlert">
        <span class="seu-flexbox">
            <span class="seu-btn-text">
                <liferay-ui:message key="save" />
            </span>
            <span class="seu-btn-arrow"></span>
        </span>
    </button>
</div>

<liferay-util:html-bottom>
	<script src="/o/searchassetweb/js/strasbourg-offer.js"></script>
</liferay-util:html-bottom>