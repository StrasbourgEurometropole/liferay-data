package eu.strasbourg.portlet.comment.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewCommentDisplayContext  extends ViewListBaseDisplayContext<Comment> {

    private List<Comment> _comments;

    public ViewCommentDisplayContext(RenderRequest request, RenderResponse response) {
        super(Comment.class, request, response);
    }


    public List<Comment> getComments() throws PortalException {

        if (this._comments==null){
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());

            //Création de la liste d'objet
            List<Comment> results = new ArrayList<>();
            if (hits != null){
                for (Document document :
                        hits.getDocs()) {
                    Comment comment = CommentLocalServiceUtil.fetchComment(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                    if (comment != null) {
                        results.add(comment);
                    }
                }
            }
            this._comments = results;
        }
        return this._comments;
    }
}
