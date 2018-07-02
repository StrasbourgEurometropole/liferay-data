package eu.strasbourg.portlet.notification.display.context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.notification.model.Notification;
import eu.strasbourg.service.notification.service.NotificationLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewNotificationsDisplayContext extends ViewListBaseDisplayContext<Notification> {
	private List<Notification> _notifications;
	
	public ViewNotificationsDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Notification.class, request, response);
	}

	public List<Notification> getNotifications() throws PortalException {
		if (this._notifications == null) {
			this._notifications = NotificationLocalServiceUtil.getNotifications(-1, -1).stream()
                    .filter(n -> !n.isSingleUser())
                    .collect(Collectors.toList());
		}
		return this._notifications;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.NOTIFICATION_BO, StrasbourgPortletKeys.NOTIFICATION_BO,
			actionId);
	}
	

	/**
	 * Retourne la liste des colonnes sur lesquelles on peut faire le tri
	 */
	@Override
	public String[] getOrderColumns() {
		return new String[] { "title", "publication-date",
			"status" };
	}
	/**
	 * Renvoie la colonne sur laquelle on fait le tri
	 */
	@Override
	public String getOrderByCol() {
		return ParamUtil.getString(this._request, "orderByCol",
			"publication-date");
	}

	/**
	 * Renvoie le nom de la colonne sur laquelle on fait le tri pour
	 * ElasticSearch
	 */
	@Override
	public String getOrderByColSearchField() {
		switch (this.getOrderByCol()) {
		case "title":
		case "alias":
			return "localized_title_fr_FR_sortable";
		case "publication-date":
			return "publishDate_sortable";
		case "status":
			return "status_sortable";
		default:
			return "publishDate_sortable";
		}
	}



}
