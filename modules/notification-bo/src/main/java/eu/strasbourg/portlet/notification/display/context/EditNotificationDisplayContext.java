package eu.strasbourg.portlet.notification.display.context;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.notification.model.Notification;
import eu.strasbourg.service.notification.service.NotificationLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

public class EditNotificationDisplayContext {
	public EditNotificationDisplayContext(RenderRequest request, RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Notification getNotification() throws PortalException {
		long notificationId = ParamUtil.getLong(_request, "notificationId");
		if (_notification == null && notificationId > 0) {
			_notification = NotificationLocalServiceUtil.getNotification(notificationId);
		}

		return _notification;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
		Locale[] availableLocales = availableLocalesSet.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	/**
	 * Retourne la liste des types de centre d'intérêt
	 */
	public List<AssetCategory> getTypes() throws PortalException {
		if (_types == null) {
			_types = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.NOTIFICATION_TYPE).getCategories();
		}
		return _types;
	}

	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(_themeDisplay.getCompanyId(),
				_themeDisplay.getCompanyGroupId(), Notification.class.getName());
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(this._themeDisplay.getCompanyGroupId(),
				StrasbourgPortletKeys.NOTIFICATION_BO, StrasbourgPortletKeys.NOTIFICATION_BO, actionId);
	}

	private Notification _notification;
	private List<AssetCategory> _types;

	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
}
