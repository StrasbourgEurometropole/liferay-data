<!-- Détail élu -->
<#setting locale = locale />

<strong>genre : </strong>
<#if entry.gender == 1 >
	<@liferay_ui.message key="eu.male" />
<#else>
	<@liferay_ui.message key="eu.female" />
</#if>
<br>
<strong>nom : </strong>${entry.lastName}<br>
<strong>prénom : </strong>${entry.firstName}<br>
<strong>id photographie : </strong>${entry.imageId}<br>
<img src="${entry.imageURL}" class="lightbox">

<strong>fonction à la ville : </strong>
<#if entry.fonctionCity?has_content>
	${entry.getName(entry.fonctionCity,locale)}
</#if>
<br>
<strong>délégation(s) thématique(s) : </strong>${entry.getThematicDelegation(locale)}<br>
<strong>adjoint de quartier : </strong>
<#if entry.districts?has_content>
	<#list entry.districts as district>
	    - ${district.getTitle(locale)}<br>
	</#list>
</#if>
<br>
<strong>groupe politique ville : </strong>
<#if entry.politicalGroupCity?has_content>
	${entry.politicalGroupCity.getTitle(locale)}
</#if>
<br>

<strong>fonction au sein de l'Eurométropole : </strong>
<#if entry.fonctionEurometropole?has_content>
	${entry.getName(entry.fonctionEurometropole,locale)}
</#if>
<br>
<strong>missions : </strong>${entry.getMissions(locale)}<br>
<strong>fonction au sein de la commune : </strong>
<#if entry.fonctionTown?has_content>
	${entry.getName(entry.fonctionTown,locale)}
</#if>
<br>
<strong>commune d'appartenance : </strong>
<#if entry.town?has_content>
	${entry.town.getTitle(locale)}
</#if>
<br>
<strong>groupe politique Eurométropole : </strong>
<#if entry.politicalGroupEurometropole?has_content>
	${entry.politicalGroupEurometropole.getTitle(locale)}
</#if>
<br>

<strong>autres mandats : </strong><br>
<#if entry.othersMandates?has_content>
	<#list entry.othersMandates as mandat>
	    - ${entry.getName(mandat,locale)}<br>
	</#list>
</#if>
<strong>ancien mandats : </strong>
<#if entry.wasMinister >
	<@liferay_ui.message key="yes" />
<#else>
	<@liferay_ui.message key="no" />
</#if><br>

<strong>contact : </strong>${entry.getContact(locale)}<br>