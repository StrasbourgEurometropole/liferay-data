package eu.strasbourg.service.comment.listener;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;

@Component(
		immediate = true,
		service = ModelListener.class
	)
public class CommentGroupModelListener extends BaseModelListener<Group> {
	
	/**
	 *  A la suppression d'un groupe, on supprime les entités rattachées à ce groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
    			
		// Commentaires
		List<Comment> comments = CommentLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Comment comment : comments) {
			try {
				CommentLocalServiceUtil.removeComment(comment.getCommentId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
