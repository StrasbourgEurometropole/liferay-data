<%@ include file="/init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true" var="actionURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${actionURL}" method="post" name="fm">
		
		<aui:input name="cmd" type="hidden"
				value="update" />
		
		<h1>TIPI</h1>
		
		<aui:fieldset>
			<aui:select name="form">
				<aui:option value="0"/>
				<aui:option value="childhood" label="Facturation petite enfance" selected="${form eq 'childhood'}"/>
				<aui:option value="schoolRestaurant" label="Facturation restauration scolaire" selected="${form eq 'schoolRestaurant'}" />
				<aui:option value="afterSchool" label="Facturation de l'accueil p&eacute;riscolaire" selected="${form eq 'afterSchool'}" />
				<aui:option value="water" label="Facturation d'eau et d'assainissement" selected="${form eq 'water'}" />
				<aui:option value="reom" label="Facturation redevance ordures m&eacute;nag&egrave;res" selected="${form eq 'reom'}" />
				<aui:option value="rs" label="Facturation redevance sp&eacute;ciale d&eacute;chets" selected="${form eq 'rs'}" />				
			</aui:select>
			<aui:input type="hidden" name="form" />
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
</style>