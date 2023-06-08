<%@ include file="/resid-init.jsp"%>

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

        <!-- Indique si le webService est en maintenance -->
		<aui:fieldset>
            <aui:input type="checkbox" name="maintenance" value="${maintenance}" label="maintenance" />
		</aui:fieldset>
		
		<aui:fieldset>
			<aui:input name="liaisonURL" value="${liaisonURL}" type="text" label="liaison-URL" />
		</aui:fieldset>
		
		<aui:fieldset>
			<label><liferay-ui:message key="zone-pdf" /></label>
			<section id="newZone">
				<aui:button cssClass="btn-icon icon icon-plus icon-2x" type="button" onClick="addZone(); return false;"/>
				<liferay-ui:message key="new-zone" /><br>
			</section>
			<c:set var="nbZone" value="0"/>
			<c:forEach items="${zones}" var="zone" varStatus="status">
				<c:set var="zoneValue" value="${fn:split(zone, ';')}" />
				<div class="webform-layout-box zone-content zone-elt" style="display:flex;" id="${status.index}">
					<aui:input name="code" type="text" value="${zoneValue[0]}" label="Code de la zone" />
					<aui:input cssClass="urlPDF" name="url" type="text" value="${zoneValue[1]}" label="URL du PDF de la zone" />
					<div class="form-group" >
						<aui:button style="margin-top:24px;" cssClass="btn-icon icon icon-trash icon-2x btn-default" type="button" onClick="deleteZone(${status.index}); return false;" />
					</div>
				</div>
				<c:set var="nbZone" value="${nbZone + 1}"/>
			</c:forEach>
			<aui:input name="nbZone" type="hidden" value="${nbZone}" />
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

<style>
	fieldset {
		margin-bottom: 20px;
	}
	.zone-content{
		margin-top: 15px;
	}
	.zone-elt .form-group:nth-child(2){
		width: 100%;
	}
	.urlPDF{
      	margin: 0px 10px;
    	width: calc(100% - 20px);
    }
</style>
<liferay-util:html-bottom>
	<script
		src="/o/residweb/js/resid.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>