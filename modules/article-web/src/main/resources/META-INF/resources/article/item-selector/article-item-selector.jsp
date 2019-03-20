<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>

<%@ page import="com.liferay.journal.model.JournalArticle" %>

<%@page import="com.liferay.portal.kernel.dao.search.RowChecker"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<div id="<portlet:namespace />productsImageSelectorWrapper" class="container-fluid-1280 main-content-body">

	<liferay-ui:search-container
		emptyResultsMessage="no-entries-were-found"
		iteratorURL="${portletURL}"
		total="${total}"
		rowChecker="<%=new RowChecker(renderResponse) %>"
	>
		<liferay-ui:search-container-results
			results="${articles}"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.journal.model.JournalArticle"
			modelVar="article" cssClass="article-row" keyProperty="resourcePrimKey" rowIdProperty="resourcePrimKey"
		>
			<liferay-ui:search-container-column-text cssClass="content-column"
				name="title" truncate="true"
				value="${article.titleCurrentValue}" />

			<fmt:formatDate value="${article.createDate}"
				var="formattedCreateDate" type="date" pattern="dd/MM/yyyy" />
			<liferay-ui:search-container-column-text cssClass="content-column"
				name="create-date" truncate="true"
				value="${formattedCreateDate}" />

			<fmt:formatDate value="${article.modifiedDate}"
				var="formattedModifiedDate" type="date" pattern="dd/MM/yyyy HH:mm" />
			<liferay-ui:search-container-column-text cssClass="content-column"
				name="modified-date" truncate="true"
				value="${formattedModifiedDate}" />

            <c:if test="${article.expirationDate != null}">
                <fmt:formatDate value="${article.expirationDate}"
                    var="formattedExpirationDate" type="date" pattern="dd/MM/yyyy" />
            </c:if>
            <liferay-ui:search-container-column-text cssClass="content-column"
                name="expiration-date" truncate="true"
                value="${article.expirationDate != null ? formattedExpirationDate : ''}" />

			<liferay-ui:search-container-column-text name="status">
				<aui:workflow-status markupView="lexicon" showIcon="false"
					showLabel="false" status="${article.status}" />
				<div class="data" data-id="${article.resourcePrimKey}" data-title="${article.getTitle(locale)}" data-create-date="${formattedCreateDate}" data-expiration-date="${article.expirationDate != null ? formattedExpirationDate : ''}"></div>
			</liferay-ui:search-container-column-text>
			
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator  />
	</liferay-ui:search-container>
</div>

<aui:script>
	$('.article-row input[type=checkbox]').on('change', function() {
		var multiple = ${multiple};
		if (!multiple) {
			if (this.checked) {
				$('input[type=checkbox]').prop('checked', false);
				$('input[type=checkbox]').parents('tr').removeClass('info');
				this.checked = true;
				$(this).parents('tr').addClass('info');
			}
			var id = $('.info .data').data('id');
			var title = $('.info .data').data('title');
			var createDate = $('.info .data').data('create-date');
			var expirationDate = $('.info .data').data('expiration-date');
			Liferay.Util.getOpener().Liferay.fire('${itemSelectedArticleName}', {
				data: {
					entityId: id,
					title: title,
                    createDate: createDate,
                    expirationDate: expirationDate
				}
			});
		} else {
			var dataDivs = $('.info .data');
			var dataToSend = [];
			for (var i = 0; i < dataDivs.length; i++) {
				dataToSend.push({
					entityId: $(dataDivs[i]).data('id'),
                    title: $(dataDivs[i]).data('title'),
                    createDate: $(dataDivs[i]).data('create-date'),
                    expirationDate: $(dataDivs[i]).data('expiration-date')
				});
			}
				
			Liferay.Util.getOpener().Liferay.fire('${itemSelectedArticleName}', {
				data: dataToSend
			});
		}
	});
</aui:script>
<style>
	.table-first-header input {
	    display: none;
	}
</style>