package eu.strasbourg.portlet.comment.display.context;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class EditCommentDisplayContext {


    private Comment _comment;

    private final RenderRequest _request;
    private final ThemeDisplay _themeDisplay;

    public EditCommentDisplayContext(RenderRequest request, RenderResponse response){
        this._request = request;
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
    }

    public Comment getComment(){
        Long commentId = ParamUtil.getLong(_request,"commentId");
        if (_comment == null && commentId>0){
            _comment = CommentLocalServiceUtil.fetchComment(commentId);
        }
        return _comment;
    }
}
