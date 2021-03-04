<%@ include file="/help-popup-init.jsp" %>

<!-- Modal d'alerte de proposition d'aide inactive -->
<div class="pro-modal pro-bloc-pcs-form fade" id="inactive-help-proposal-modal" tabindex="-1" role="dialog" aria-labelledby="inactive-help-proposal-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.inactive.help.proposal.alert.title"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key="modal.inactive.help.proposal.alert.message"/></h4>
            </div>
        </div>
    </div>
</div>