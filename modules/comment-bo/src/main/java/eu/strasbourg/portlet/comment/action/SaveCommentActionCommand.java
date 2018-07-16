package eu.strasbourg.portlet.comment.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.Date;

@Component(
        immediate = true,
        property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.COMMENT_BO,
        "mvc.command.name=saveComment"
        },
        service = MVCActionCommand.class
        )
public class SaveCommentActionCommand implements MVCActionCommand{

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    private CommentLocalService _commentLocalService;

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {
            ServiceContext sc = ServiceContextFactory.getInstance(actionRequest);

            long commentId = ParamUtil.getLong(actionRequest,"commentId");
            Comment comment;
            if (commentId==0){
                comment = _commentLocalService.createComment(sc);
            } else {
                comment = _commentLocalService.getComment(commentId);
            }
            String userName = ParamUtil.getString(actionRequest,"userName");
            comment.setUserName(userName);
            String commentaire = ParamUtil.getString(actionRequest,"commentaire");
            comment.setComment(commentaire);
            comment.setCreateDate(new Date());
            _commentLocalService.updateComment(comment);

        } catch (PortalException e) {
            _log.error(e);
        }
        return true;
    }

    /**
     * Validation des champs obligatoires
     */
    private boolean validate(ActionRequest request) {
        boolean isValid;

        // userName
        isValid = isValid(request, "userName", "userName-error");

        // Description
        if (isValid){
            isValid = isValid(request, "commentaire", "commentaire-error");
        }
        return isValid;
    }

    /**
     * méthode permettant la véridication des champs.
     * @param request la requete
     * @param field le champ à vérifier.
     * @param sessionError la session error.
     * @return le boolean.
     */
    private boolean isValid(ActionRequest request, String field, String sessionError) {
        boolean result = true;
        if (Validator.isNull(ParamUtil.getString(request, field))) {
            SessionErrors.add(request, sessionError);
            result = false;
        }
        return result;
    }

    @Reference(unbind = "-")
    protected void setCommentLocalService(CommentLocalService commentLocalService){
        _commentLocalService = commentLocalService;
    }
}
