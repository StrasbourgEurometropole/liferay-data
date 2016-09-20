<%@ include file="/entity-detail-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">
	
		<aui:input name="cmd" type="hidden"
			value="update" />
	
		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />
	
		<aui:fieldset>
			<aui:select label="asset-type" name="className">
				<c:forEach var="currentClassName" items="${classNames}" varStatus="cnStatus">
					<aui:option value="${currentClassName}" selected="${currentClassName eq className}">${classNamesLabels[cnStatus.index]}</aui:option>
				</c:forEach>
			</aui:select>
		</aui:fieldset>
		
		<div id="asset-and-adt-selection">
			<c:if test="${not empty className}">
				<%@include file="/entity-detail-configuration-selectors.jsp" %>
			</c:if>
		</div>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>
<liferay-portlet:actionURL portletConfiguration="true"
	var="classNameChangeURL" windowState="exclusive" />
<script>
	jQuery(function() {
		jQuery('#${renderResponse.namespace}className').on('change', function() {
			var className = jQuery('#${renderResponse.namespace}className')
					.val();
			jQuery.ajax({
				type : "POST",
				url : '${classNameChangeURL}'
						+ '&${renderResponse.namespace}cmd=classname-change'
						+ '&${renderResponse.namespace}className='
						+ className,
				dataType : "html",
				success : function(result) {
					jQuery('#asset-and-adt-selection').html(result);
				}
			});
		});
	});
</script>