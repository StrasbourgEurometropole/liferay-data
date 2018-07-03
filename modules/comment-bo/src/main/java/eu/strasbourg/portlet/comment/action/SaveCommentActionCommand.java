package eu.strasbourg.portlet.comment.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

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

        } catch (PortalException e) {
            _log.error(e);
        }
        return false;
    }
}
