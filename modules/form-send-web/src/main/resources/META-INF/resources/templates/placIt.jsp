<%@ include file="/form-send-init.jsp" %>
<fmt:setLocale value="${locale}" />

<c:if test="${dc.form != null}">
    <h2 class="pro-title-propositions" id="propositions">${dc.title}</h2>
    <!-- Nombre de résultats -->
    <c:if test="${dc.searchContainer.total == 0}">
        <div class="seu-view-results">
            <div class="seu-result-count">
                Il n'y a aucune proposition pour le moment.
            </div>
        </div>
    </c:if>

    <c:if test="${dc.searchContainer.total gt 0}">
        <!-- Liste des résultats -->
        <c:set var="fieldsToShow" value="${dc.fieldsToShow}" />
        <aui:form method="post" name="fm" cssClass="pro-page-registre">
            <liferay-ui:search-container id="entriesSearchContainer"
                        searchContainer="${dc.searchContainer}">
                <c:forEach items="${dc.paginatedResults}" var="record">
                    <div class="pro-item-proposition">
                        <fmt:formatDate value="${record.createDate}" type="date" var="createHeure" pattern="HH:mm" />
                        <span class="pro-published"><time datetime="2019-06-12">Le ${dc.getShortDate(record.createDate, locale)} | <span>${createHeure}</span></time></span>
                        <c:forEach var="recordField" items="${dc.getRecordFields(record.getDDMStorageId(), locale)}">
                            <c:if test="${fn:contains(dc.fieldsToShow, recordField[1]) && not empty recordField[2] && recordField[2] != '[]'}">
                                <c:set var="type" value="${dc.getFieldType(recordField[1])}" />
                                <c:if test="${type.equals('text')}">
                                    <c:set var="formSendRecordField" value="${dc.getFormSendRecordField(record.getDDMStorageId(),recordField[0])}" />
                                    <span class="pro-title-question" id="rep_${formSendRecordField.formSendRecordFieldId}">${dc.getLabel(recordField[1], locale)}</span>
                                    <p>${dc.getTip(recordField[1], locale)}</p>
                                    <div class="pro-item-response pro-item-response-highlight">
                                        <c:if test="${! dc.isToShow(formSendRecordField.formSendRecordFieldId)}" >
                                            <div class="pro-bloc-texte">
                                                <p>
                                                    ${dc.texteModeration}
                                                </p>
                                            </div>
                                        </c:if>
                                        <c:if test="${dc.isToShow(formSendRecordField.formSendRecordFieldId)}" >
                                            <div class="pro-bloc-texte">
                                                <p>
                                                    ${fn:toUpperCase(fn:substring(recordField[2], 0, 1))}${fn:toLowerCase(fn:substring(recordField[2], 1,fn:length(recordField[2])))}
                                                </p>
                                            </div>
                                            <div class="pro-interactions">
                                                <a href="#pro-avis-like-pro" class="pro-like"
                                                    data-typeid="20"
                                                    data-isdislike="false"
                                                    data-title=""
                                                    data-entityid="${formSendRecordField.formSendRecordFieldId}"
                                                    data-entitygroupid="${formSendRecordField.groupId}">
                                                    ${formSendRecordField.nbLikes}
                                                </a>
                                                <a href="#pro-avis-dislike-pro" class="pro-dislike"
                                                    data-typeid="20"
                                                    data-isdislike="true"
                                                    data-title=""
                                                    data-entityid="${formSendRecordField.formSendRecordFieldId}"
                                                    data-entitygroupid="${formSendRecordField.groupId}">
                                                    ${formSendRecordField.nbDislikes}
                                                </a>
                                                <c:if test="${!dc.isUserBanned() && dc.hasUserSigned()}">
                                                    <div class="pro-action-link">
                                                        <a href="#Signaler" title="Signaler la proposition" data-toggle="modal" data-target="#modalSignaler" data-entityid="${formSendRecordField.formSendRecordFieldId}">
                                                            Signaler
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </c:if>
                                        <c:if test="${not empty formSendRecordField && not empty formSendRecordField.response}">
                                            <div class="pro-footer-response">
                                                <c:set var="user" value="${dc.getUser(formSendRecordField.responseUserId)}" />
                                                <div class="pro-author">
                                                    <figure role="group">
                                                        <img src="${user.getPortraitURL(themeDisplay)}" width="40" height="40" alt="Image participation">
                                                    </figure>
                                                    <div class="pro-meta-txt">
                                                        <p>R&eacute;ponse publi&eacute;e par :</p>
                                                        <p><strong>${user.getFullName()}</strong></p>
                                                    </div>
                                                </div>
                                                <div class="pro-txt">
                                                    <p>${formSendRecordField.response}</p>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>

                <!-- Pagination -->
                <c:if test="${dc.pager.lastPage > 1}">
                    <div class="pro-pagination">
                        <div>
                            <div class="row">
                                <div class="col-sm-6 col-xs-4 pull-left">
                                    <form method="get">
                                        <select id="select-page">
                                            <c:forEach var="page" items="${dc.pager.allPages}">
                                                <option value="${page.index}" data-url="${dc.getURLForPage(page.index)}#propositions" ${page.index == dc.pager.currentPage ? "selected" : ""}>
                                                    Page ${page.index} ( ${dc.pager.count > (page.index * dc.delta) ? dc.delta : (dc.pager.count - ((page.index - 1) * dc.delta))} )
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </form>
                                    <p class="hidden-xs">
                                        Affichage des r&eacute;sultats ${dc.pager.onFirstPage ? 1 : (((dc.pager.currentPage - 1) * dc.delta) + 1)}
                                         - ${dc.pager.onLastPage ? dc.pager.count : (dc.pager.currentPage * dc.delta)}
                                         parmi ${dc.pager.count}
                                    </p>
                                </div>

                                <!-- Pagination : liens de navigation -->
                                <div class="col-sm-6 col-xs-8 pull-right">
                                    <ul>
                                        <c:if test="${not dc.pager.onFirstPage}">
                                            <!-- Lien vers la premiere page -->
                                            <li>
                                                <a href="${dc.getURLForPage(1)}#propositions" class="hidden-sm hidden-xs pro-first" title="<liferay-ui:message key="go-to-first-page" />" data-action="first"><liferay-ui:message key="first" /></a>
                                            </li>
                                            <!-- Lien vers la page precedente page -->
                                            <li>
                                                <a href="${dc.getURLForPage(dc.pager.currentPage - 1)}#propositions" title="<liferay-ui:message key="go-to-previous-page" />" data-action="prev"><liferay-ui:message key="previous" /></a>
                                            </li>
                                        </c:if>
                                        <c:if test="${not dc.pager.onLastPage}">
                                            <!-- Lien vers la page suivante -->
                                            <li>
                                                <a href="${dc.getURLForPage(dc.pager.currentPage + 1)}#propositions" title="<liferay-ui:message key="go-to-next-page" />" data-action="next"><liferay-ui:message key="next" /></a>
                                            </li>
                                            <!-- Lien vers la derniere page -->
                                            <li>
                                                <a href="${dc.getURLForPage(dc.pager.lastPage)}#propositions" class="hidden-sm hidden-xs pro-last" title="<liferay-ui:message key="go-to-last-page" />" data-action="last"><liferay-ui:message key="last" /></a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </liferay-ui:search-container>
        </aui:form>
        <input type="hidden" id="nbContributions" value="${dc.records.size()}" />
        <input type="hidden" id="nbReponses" value="0" />
        <input type="hidden" id="nbReponsesVille" value="${dc.nbReponsesVille}" />
    </c:if>
</c:if>

<!-- SIGNALER LE COMMENTAIRE -->
<portlet:actionURL var="signalement" name="signalement">
	<portlet:param name="redirectURL" value="${redirectURL}"></portlet:param>
</portlet:actionURL>

<!-- HTML pour la modal de registre numérique -->
<div class="pro-modal pro-bloc-pcs-form pro-modal-center fade" id="modalSignaler" tabindex="-1" role="dialog" aria-labelledby="modalSignaler">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3>Signaler une proposition</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form id="form-signalements" method="post" action="${signalement}">
                <div class="pro-wrapper">
                    <h4>Choississez ci-dessous la raison de votre signalement :</h4>
                    <div class="form-group">
                        <label for="signalement">Motif de signalement</label>
                        <select name="<portlet:namespace />categorie">
                            <c:forEach var="categorie" items="${dc.categories}">
                                <option value="${categorie.categoryId}">${categorie.name}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="entityId" name="<portlet:namespace />entityId"/>
                    </div>
                </div>
                <div class="pro-form-submit">
                    <button type="submit" class="btn btn-default">Signaler</button>
                </div>
            </form>
        </div>
    </div>
</div>

<style>

    .seu-result-count{
        margin-bottom: 50px;
    }

    .seu-pagination {
        position: relative;
        margin-bottom: 60px;
    }

</style>

<script>
    $('#select-page').on('change', function(){
        window.location = $(this).find(":selected").data('url');
    })
</script>

<aui:script>
	// Gestion de l'affichage et du controle de l'action de signalement
    $("a[href='#Signaler']").click(function(e){
        var entityId=$(this).data('entityid');
        $("input[id='entityId']").val(entityId);
        //e.preventDefault();
        //$("#modalSignaler").modal();
    });
</aui:script>