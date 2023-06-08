package eu.strasbourg.portlet.favorites.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;

import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.service.FavoriteLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + StrasbourgPortletKeys.FAVORITES_VIEWER_WEB,
			"mvc.command.name=deleteFavorite" },
		service = MVCActionCommand.class)
public class DeleteFavoriteActionCommand implements MVCActionCommand{

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) {
		try {
			long favoriteId = ParamUtil.getLong(request, "favoriteId");
			
			Favorite favoriteToDelete = _favoriteLocalService.fetchFavorite(favoriteId);
			
			if(favoriteToDelete == null)
				// Possiblement plusieurs onglets d'ouvert et déjà supprimé sur l'un d'eux
				return true;
			
			String publikUserId =null;
			HttpServletRequest servletRequest = ServiceContextThreadLocal.getServiceContext().getRequest();
			boolean isLoggedIn = SessionParamUtil.getBoolean(servletRequest, "publik_logged_in");
			if (isLoggedIn) {
			    publikUserId = SessionParamUtil.getString(servletRequest, "publik_internal_id");
			}
			
			if(favoriteToDelete.getPublikUserId().compareTo(publikUserId) !=0)
				return false;					
			
			_favoriteLocalService.deleteFavorite(favoriteId);
		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
	}
	
	private FavoriteLocalService _favoriteLocalService;
	
	@Reference(unbind = "-")
	protected void setFavoriteLocalService(
		FavoriteLocalService favoriteLocalService) {

		_favoriteLocalService = favoriteLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
