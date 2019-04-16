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
			results="${events}"
		/>

		<liferay-ui:search-container-row
			className="eu.strasbourg.service.agenda.model.Event"
			modelVar="event" cssClass="event-row" keyProperty="eventId" rowIdProperty="eventId"
		>
			<liferay-ui:search-container-column-text cssClass="content-column"
				name="title" truncate="true"
				value="${event.titleCurrentValue}" />

			<fmt:formatDate value="${event.publicationDate}"
				var="formattedPublicationDate" type="date" pattern="dd/MM/yyyy" />
			<liferay-ui:search-container-column-text cssClass="content-column"
				name="publication-date" truncate="true"
				value="${formattedPublicationDate}" />

			<fmt:formatDate value="${event.modifiedDate}"
				var="formattedModifiedDate" type="date" pattern="dd/MM/yyyy HH:mm" />
			<liferay-ui:search-container-column-text cssClass="content-column"
				name="modified-date" truncate="true"
				value="${formattedModifiedDate}" />

            <c:if test="${event.lastEndDate != null}">
                <fmt:formatDate value="${event.lastEndDate}"
                    var="formattedLastEndDate" type="date" pattern="dd/MM/yyyy" />
            </c:if>
            <liferay-ui:search-container-column-text cssClass="content-column"
                name="last-end-date" truncate="true"
                value="${event.lastEndDate != null ? formattedLastEndDate : ''}" />

			<liferay-ui:search-container-column-text name="status">
				<aui:workflow-status markupView="lexicon" showIcon="false"
					showLabel="false" status="${event.status}" />
				<div class="data" data-id="${event.eventId}" data-title="${event.getTitle(locale)}" data-publication-date="${formattedPublicationDate}" data-last-end-date="${event.lastEndDate != null ? formattedLastEndDate : ''}"></div>
			</liferay-ui:search-container-column-text>
			
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator  />
	</liferay-ui:search-container>
</div>

<aui:script>
	$('.event-row input[type=checkbox]').on('change', function() {
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
			var publicationDate = $('.info .data').data('publication-date');
			var lastEndDate = $('.info .data').data('last-end-date');
			Liferay.Util.getOpener().Liferay.fire('${itemSelectedEventName}', {
				data: {
					entityId: id,
					title: title,
                    publicationDate: publicationDate,
                    lastEndDate: lastEndDate
				}
			});
		} else {
			var dataDivs = $('.info .data');
			var dataToSend = [];
			for (var i = 0; i < dataDivs.length; i++) {
				dataToSend.push({
					entityId: $(dataDivs[i]).data('id'),
                    title: $(dataDivs[i]).data('title'),
                    publicationDate: $(dataDivs[i]).data('publication-date'),
                    lastEndDate: $(dataDivs[i]).data('last-end-date')
				});
			}
				
			Liferay.Util.getOpener().Liferay.fire('${itemSelectedEventName}', {
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