package eu.strasbourg.portlet.notification.portlet.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.notification.configuration.NotificationConfiguration;
import eu.strasbourg.portlet.notification.model.display.NotificationDisplay;
import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.UserNotificationStatusLocalServiceUtil;
import eu.strasbourg.utils.Pager;

public class NotificationViewerDisplayContext {

	private RenderRequest request;
	private RenderResponse response;
	private ThemeDisplay themeDisplay;
	private List<UserNotificationStatus> usrNotifStatus;
	private List<NotificationDisplay> results;
	private SearchContainer<NotificationDisplay> searchContainer;
	private NotificationConfiguration configuration;
	private Log log = LogFactoryUtil.getLog(this.getClass());

	public NotificationViewerDisplayContext(RenderRequest request, RenderResponse response) {
		this.request = request;
		this.response = response;
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			configuration = this.themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(NotificationConfiguration.class);
		} catch (Exception e) {
			log.error(e);
		}
	}

	private List<UserNotificationStatus> getListNotifications(){
		if (this.usrNotifStatus == null) {
			// Récupération du publik ID avec la session
			LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
			HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
			String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
			
			// Récupération de la liste des notifications de l'utilisateur
			List<UserNotificationStatus> notifications = UserNotificationStatusLocalServiceUtil
					.getByPublikUserId(internalId).stream()
					.filter(c -> c.getNotification() != null).sorted((f1, f2) -> f2.getNotification()
							.getPublicationDate().compareTo(f1.getNotification().getPublicationDate()))
					.collect(Collectors.toList());

			String template = configuration.template();
			if (Validator.isNull(template)) {
				template = "notification-viewer-view";
			}
			
			// Si l'on est dans le menu, il ne faut que les notifications non lues
			if (!template.equals("notification-viewer-all")) {
				notifications.removeIf(c -> c.isRead());
			}
			
			this.usrNotifStatus = notifications;
		}
		
		return this.usrNotifStatus;
	}
	
	
	public List<NotificationDisplay> getResults() {
		if (this.results == null) {
			List<NotificationDisplay> notifications = new ArrayList<NotificationDisplay>();

			// Création de la liste des notifications à afficher en fonction de
			// la notification, de son statut et de l'utilisateur
			for (UserNotificationStatus un : this.getListNotifications()) {
				NotificationDisplay nd = new NotificationDisplay();
				nd.setTitle(un.getNotification().getTitle(request.getLocale()));
				nd.setRead(un.isRead());
				nd.setDate(un.getNotification().getPublicationDate());
				nd.setNotificationId(un.getNotificationId());
				notifications.add(nd);
			}
			List<List<NotificationDisplay>> result = new ArrayList<List<NotificationDisplay>>();
			result.add(notifications);
			this.results = notifications;
		}
		return this.results;
	}

	/**
	 * Retourne les résultats entre les indexes start (inclu) et end (non inclu)
	 */
	public List<NotificationDisplay> getPaginatedResults() {
		return ListUtil.subList(getResults(), this.getSearchContainer().getStart(), this.getSearchContainer().getEnd());
	}

	/**
	 * Retourne le nombre total de résultats
	 */
	public int getResultCount() {
		return getResults().size();
	}

	/**
	 * Retourne le nombre de notifications non lue
	 */
	public long getResultUnreadCount() {
		long notifCount = this.getListNotifications().stream().filter(c -> !c.isRead()).count();
		return notifCount;
	}

	/**
	 * Retourne le searchContainer
	 */
	public SearchContainer<NotificationDisplay> getSearchContainer() {
		if (searchContainer == null) {
			Map<String, String[]> parameterMap = request.getParameterMap();
			PortletURL iteratorURL = response.createRenderURL();
			iteratorURL.setParameters(parameterMap);
			searchContainer = new SearchContainer<NotificationDisplay>(request, iteratorURL, null,
					"no-entries-were-found");
			searchContainer.setDelta(this.getDelta());
			searchContainer.setTotal(this.getResultCount());
			searchContainer.setResults(results);
		}
		return searchContainer;
	}

	/**
	 * Retourne le nombre notification à afficher par page
	 */
	public int getDelta() {
		int deltaFromParam = ParamUtil.getInteger(this.request, "delta");
		if (deltaFromParam > 0) {
			return deltaFromParam;
		}
		return 12;
	}

	/**
	 * Retourne le pager de la page
	 */
	public Pager getPager() {
		return new Pager(this.getSearchContainer().getTotal(), (int) this.getDelta(),
				this.getSearchContainer().getCur());
	}

	/**
	 * Retourne l'URL sur laquelle aller pour avoir X items par page
	 */
	public String getURLForDelta(long delta) {
		PortletURL url = this.getSearchContainer().getIteratorURL();
		url.setParameter("delta", String.valueOf(delta));
		String valueToReturn = url.toString();
		url.setParameter("delta", String.valueOf(this.getDelta()));
		return valueToReturn;
	}

	/**
	 * Retourne l'URL sur laquelle aller pour accéder à la Xième page
	 */
	public String getURLForPage(long pageIndex) {
		PortletURL url = this.getSearchContainer().getIteratorURL();
		url.setParameter("cur", String.valueOf(pageIndex));
		String valueToReturn = url.toString();
		url.setParameter("cur", String.valueOf(this.getSearchContainer().getCur()));
		return valueToReturn;
	}
}