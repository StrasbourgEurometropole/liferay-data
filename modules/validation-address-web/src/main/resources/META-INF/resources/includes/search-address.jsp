<%@ include file="/validation-address-init.jsp" %>
<liferay-portlet:actionURL var="recordURL" name="record" />
<aui:form name="fm" action="${searchURL}" cssClass="view-filters">
	<div class="filter-line" style="align-items: flex-end;">
		<!-- Adresse -->
		<div class="widget widget-big" style="width: 100%;">
			<div class="title">
				<label for="query"><liferay-ui:message key="complet-address" /></label>
			</div>
			<div class="content">
				<input type="text" name="query" id="query"
					placeholder="<liferay-ui:message key='address-placeholder' />"  value="${query}">
			</div>
		</div>
	</div>
</aui:form>
	
<!-- RÃ©sultats -->
<aui:form name="fmChoice" action="${recordURL}" style="display: none;">
	<aui:input type="hidden" name="address"  />
	<aui:input type="hidden" name="zipCode"  />
	<aui:input type="hidden	" name="city"  />
	<aui:input type="hidden" name="lastName" value="${dc.lastName}" />
    <div class="webform-layout-box" style="margin: 20px 50px;">
        <div class="form-group">
			<div class="title">
			</div>
			<div class="content" id="address">
			</div>
        </div>
        <div class="form-group">
			<div class="title">
			</div>
			<div class="content">
				<!-- Validation -->
				<div class="widget">
					<button type="submit" class="btn-square filled core">
						<span class="flexbox">
							<span class="btn-text"><liferay-ui:message key="record-address" /></span>
							<span class="btn-arrow"></span>
						</span>
					</button>
				</div>
			</div>
		</div>
	</div>
</aui:form>

<liferay-util:html-bottom>
	<aui:script>
		define._amd = define.amd;
		define.amd = false;
	</aui:script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script>
		define.amd = define._amd;
		window.forcedCity = '';
	</script>
	<script
		src="/o/validation-address/js/validation-address.js"
		type="text/javascript"></script>
		
	<style>
		.alert-success {
			display: none;
		}
	</style>
</liferay-util:html-bottom>
