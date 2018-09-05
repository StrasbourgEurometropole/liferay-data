<%@ include file="/project-popup-init.jsp" %>
<portlet:actionURL var="filePetition" name="filePetition">
	<portlet:param name="cmd" value="filePetition" />
</portlet:actionURL>
<!-- DEPOSER UNE NOUVELLE PETITION -->
<!-- HTML pour la modal de pétition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalPetition" tabindex="-1" role="dialog" aria-labelledby="modalPetition">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.filepetition.title"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form id="form-file-petition" method="post" action="${filePetition}">
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.filepetition.information"/></h4>
                    <div class="form-group">
                        <aui:input name="title" label="modal.filepetition.information.title" required="true"/>
                    </div>
                    <div class="form-group">
                        <aui:input type="textarea" name="description" label="modal.filepetition.information.description" required="true"/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <label for="petition"><liferay-ui:message key="modal.filepetition.information.projet"/></label>
                            <select name="<portlet:namespace />project">
                                <c:forEach var="project" items="${projects}">
                                    <option value="${project.categoryId}">${project.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="territoire"><liferay-ui:message key="modal.filepetition.information.territoire"/></label>
                            <select name="<portlet:namespace />quartier">
                                <c:forEach var="quartier" items="${quartiers}">
                                    <option value="${quartier.categoryId}">${quartier.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="thematique"><liferay-ui:message key="modal.filepetition.information.thematique"/></label>
                            <select name="<portlet:namespace />theme">
                                <c:forEach var="theme" items="${thematics}">
                                    <option value="${theme.categoryId}">${theme.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="pro-txt-form">
                        <p><liferay-ui:message key="modal.filepetition.information.mayor"/></p>
                    </div>
                </div>
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.filepetition.user"/></h4>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <aui:input name="username" label="modal.user.username" required="true" placeholder="Dupond"/>
                        </div>
                        <div class="form-group form-triple">
                            <aui:input name="firstname" label="modal.user.firstname" required="true" placeholder="Jean"/>
                        </div>
                        <div class="form-group form-triple">
                            <aui:input name="birthday" label="modal.user.birthday" required="true" placeholder="jj/mm/aaaa"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input name="address" label="modal.user.address" required="true"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <aui:input name="city" label="modal.user.city" required="true" placeholder="Strasbourg"/>
                            </div>
                            <div class="form-code">
                                <aui:input name="postalcode" label="modal.user.postalcode" required="true" placeholder="67XXX"/>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input name="mail" label="modal.user.mail" required="true" placeholder="jean.dupond@gmail.com"/>
                        </div>
                        <div class="form-group form-half">
                            <aui:input name="phone" label="modal.user.phone" required="true" placeholder="0611111111"/>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="file-petition-legalage" value="legalage">
                        <label for="file-petition-legalage"><liferay-ui:message key="modal.legalage"/></label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="file-petition-cnil" value="cnil">
                        <label for="file-petition-cnil"><liferay-ui:message key="modal.cnil"/></label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i><liferay-ui:message key="modal.filepetition.condition"/></i></p>
                    <p><liferay-ui:message key="modal.filepetition.contact"/></p>
                </div>
                <div class="pro-form-submit">
                    <button type="submit" class="btn btn-default"><liferay-ui:message key="modal.filepetition.submit"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
