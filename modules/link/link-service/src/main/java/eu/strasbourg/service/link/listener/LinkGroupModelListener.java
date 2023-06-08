package eu.strasbourg.service.link.listener;


import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalServiceUtil;

@Component(
	immediate = true,
	service = ModelListener.class
)
public class LinkGroupModelListener extends BaseModelListener<Group> {
	
	/**
	 *  A la suppression d'un groupe, on supprime les entités rattachées à ce groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
    	// Liens
		List<Link> links = LinkLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Link link : links) {
			try {
				LinkLocalServiceUtil.removeLink(link.getLinkId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}