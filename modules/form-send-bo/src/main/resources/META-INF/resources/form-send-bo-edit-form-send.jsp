<%@ include file="/form-send-bo-init.jsp"%>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveFormSend" varImpl="saveFormSendURL">
    <portlet:param name="tab" value="viewFormSends" />
    <portlet:param name="cmd" value="saveFormSend" />
    <portlet:param name="formInstanceId" value="${formInstanceId}" />
</liferay-portlet:actionURL>


<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

    <%-- Composant : formulaire de saisie de l'entite --%>
    <aui:form action="${saveFormSendURL}" method="post" name="fm">

        <%-- Propriete : definit l'entite de reference pour le formulaire--%>
        <aui:fieldset-group markupView="lexicon">

            <%-- Champ : (cache) PK de l'entite --%>
            <aui:input name="recordId" type="hidden" value="${dc.record.formInstanceRecordId}"/>

            <c:forEach items="${dc.getRecordFields(locale)}" var="recordField">
                <aui:fieldset collapsed="false" collapsible="true" label="${recordField[0]}">
                    ${recordField[1]}<br /><br />
                    <label>R&eacute;ponse</label>
                    <liferay-ui:input-editor contents="${recordField[3]}" width="80%" name="rep-ville_${recordField[2]}"/>
                </aui:fieldset>
            </c:forEach>

        </aui:fieldset-group>

        <%-- Composant : Menu de gestion de l'entite --%>
        <aui:button-row>
            <%-- Composant : bouton de valider a la liste des entites --%>
            <aui:button cssClass="btn-lg" type="submit" value="save" />

            <%-- Composant : bouton de retour a la liste des entites --%>
            <aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

        </aui:button-row>

    </aui:form>
</div>