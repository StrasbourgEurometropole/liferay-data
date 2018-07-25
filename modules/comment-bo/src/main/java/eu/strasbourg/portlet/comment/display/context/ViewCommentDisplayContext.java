package eu.strasbourg.portlet.comment.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewCommentDisplayContext extends ViewListBaseDisplayContext<Comment> {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private List<Comment> _comments;

    public ViewCommentDisplayContext(RenderRequest request, RenderResponse response) {
        super(Comment.class, request, response);
    }


    public List<Comment> getComments() throws PortalException {

        if (this._comments == null) {
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());
            //Création de la liste d'objet
            this._comments = populateComments(hits);
        }
        return this._comments;
    }

    private List<Comment> getAllComments() throws PortalException {
        Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());
        return populateComments(hits);
    }

    public String getAllProjectIds() throws PortalException {
        StringBuilder projectIds = new StringBuilder();
        for (Comment comment : this.getAllComments()) {
            if (projectIds.length() > 0) {
                projectIds.append(",");
            }
            projectIds.append(comment.getCommentId());
        }
        return projectIds.toString();
    }

    @Override
    public String getOrderByColSearchField() {
        String param = this.getOrderByCol();
        String result;
        switch (param) {
            case "userName":
                result="localized_title_fr_FR_sortable";
                break;
            case "reportings":
                result= "status_sortable";
                break;
            case "entityType":
                result= "localized_title_fr_FR_sortable";
                break;
            case "entityName":
                result= "localized_title_fr_FR_sortable";
                break;
            default:
                result= super.getOrderByColSearchField();
                break;
        }
        return result;
    }

    private List<Comment> populateComments(Hits hits) {
        List<Comment> results = new ArrayList<>();
        if (hits != null) {
            for (Document document :
                    hits.getDocs()) {
                Comment comment = CommentLocalServiceUtil.fetchComment(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (comment != null) {

                    results.add(comment);
                }
            }
        }
        return results;
    }

    public boolean hasPermission(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.COMMENT_BO,
                StrasbourgPortletKeys.COMMENT_BO,
                actionId);
    }
}
