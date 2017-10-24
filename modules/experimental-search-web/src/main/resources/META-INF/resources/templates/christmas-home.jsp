<%@ include file="/experimental-search-init.jsp" %>

<liferay-portlet:actionURL var="searchURL" name="search" />

<div class="small-container mns-filtres row">
    <span class="icon-cross" id="cross-mobile"></span>
    <form action="${searchURL}" method="post">
        <div class="col-sm-4 col-xs-12">
            <label>${criteria1}</label>
            <select name="criteria1" id="venir">
            	<c:forEach var="option" items="${criteria1Options}" varStatus="loop">
                	<option value="${loop.index}">${option}</option>
            	</c:forEach>
            </select>
        </div>
        <div class="col-sm-4 col-xs-12">
            <label><liferay-ui:message key="when" /></label>
            <select name="period" id="periode">
                <option value="today"><liferay-ui:message key="today" /></option>
                <option value="tomorrow"><liferay-ui:message key="tomorrow" /></option>
                <option value="weekend"><liferay-ui:message key="next-week-end" /></option>
                <option value="later"><liferay-ui:message key="later" /></option>
            </select>
        </div>
        <div class="col-sm-4 col-xs-12">
            <label>${criteria2}</label>
            <select name="criteria2" id="evenement">
            	<c:forEach var="option" items="${criteria2Options}" varStatus="loop">
                	<option value="${loop.index}">${option}</option>
            	</c:forEach>
            </select>
        </div>
        <div class="col-md-12 text-center">
            <input type="submit" class="btn-filtre" value="<liferay-ui:message key="find" />" />
        </div>
    </form>
</div>
<div class="small-container mns-m-filtres-search row">
    <div class="col-xs-12">
        <span class="label">${criteria1} <liferay-ui:message key="when" /> ${criteria2} ?</span>
        <span id="search-mobile-filtres" class="btn-filtre"><liferay-ui:message key="search" /></span>
    </div>
</div>