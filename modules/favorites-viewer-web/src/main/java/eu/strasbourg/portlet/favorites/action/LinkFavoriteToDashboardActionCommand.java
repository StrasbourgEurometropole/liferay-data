package eu.strasbourg.portlet.favorites.action;

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
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + StrasbourgPortletKeys.FAVORITES_VIEWER_WEB,
			"mvc.command.name=linkFavoriteToDashboard" },
		service = MVCActionCommand.class)
public class LinkFavoriteToDashboardActionCommand implements MVCActionCommand{

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) {
		long favoriteId = ParamUtil.getLong(request, "favoriteId");

		Favorite favoriteToAdd = _favoriteLocalService.fetchFavorite(favoriteId);

		if(favoriteToAdd == null)
			// Possiblement plusieurs onglets d'ouvert et supprimé sur l'un d'eux
			return true;

		if(favoriteToAdd.isOnDashboard()){
			// on supprime le favori du tableau de bord
			favoriteToAdd.setOnDashboardDate(null);
			_favoriteLocalService.updateFavorite(favoriteToAdd);
		}else{
			// on ajoute le favori au tableau de bord
			favoriteToAdd.setOnDashboardDate(new Date());
		}
		_favoriteLocalService.updateFavorite(favoriteToAdd);


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
