package eu.strasbourg.portlet.comment.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
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
            boolean isValid = validate(actionRequest);
            if (!isValid){

                // Si pas valide : on reste sur la page d'édition
                PortalUtil.copyRequestParameters(actionRequest, actionResponse);

                ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
                        .getAttribute(WebKeys.THEME_DISPLAY);
                String portletName = (String) actionRequest
                        .getAttribute(WebKeys.PORTLET_ID);
                PortletURL returnURL = PortletURLFactoryUtil.create(actionRequest,
                        portletName, themeDisplay.getPlid(),
                        PortletRequest.RENDER_PHASE);

                actionResponse.setRenderParameter("returnURL", returnURL.toString());
                actionResponse.setRenderParameter("cmd","editComment");
                actionResponse.setRenderParameter("mvcPath","/comment-bo-edit-comment.jsp");
                return false;
            }
            long commentId = ParamUtil.getLong(actionRequest,"commentId");
            Comment comment;
            if (commentId==0){
                comment = _commentLocalService.createComment(sc);
                comment.setCreateDate(new Date());
            } else {
                comment = _commentLocalService.getComment(commentId);
                comment.setModifiedDate(new Date());
            }
            String userName = ParamUtil.getString(actionRequest,"userName");
            if (userName!=null&&!userName.isEmpty()){
                comment.setUserName(userName);
            }
            boolean approved = ParamUtil.getBoolean(actionRequest,"status");
            comment.setStatus(approved ? 0 : 1);
            String commentaire = ParamUtil.getString(actionRequest,"comment");
            _log.info("nouveau commentaire : "+commentaire);
            comment.setComment(commentaire);
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
        return isValid(request, "comment", "commentaire-error");
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
