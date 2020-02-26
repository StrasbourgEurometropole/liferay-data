package eu.strasbourg.service.social.scheduler;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.social.SocialService;
import eu.strasbourg.utils.JSONHelper;
import org.osgi.service.component.annotations.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Change le token instagram des portlet SocialWall qui doivent l'être
 */
@Component(immediate = true, service = AccessTokenRefresh.class)
public class AccessTokenRefresh extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
        // Tous les jours à 2h
        schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(
                getEventListenerClass(), getEventListenerClass(), "0 0 2 * * ?"));
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	private Log log = LogFactoryUtil.getLog(AccessTokenRefresh.class);

	@Override
	protected void doReceive(Message message) throws Exception {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// récupère les préférences des portlets socialWall présent
		List<PortletPreferences> preferences = PortletPreferencesLocalServiceUtil.getPortletPreferences();
		preferences = preferences.stream().filter(p -> p.getPortletId().contains("SocialWallPortlet")).collect(Collectors.toList());
		for (PortletPreferences preference : preferences ) {
			javax.portlet.PortletPreferences portletPreferences = PortletPreferencesFactoryUtil
					.fromXML(preference.getCompanyId(),preference.getOwnerId(),preference.getOwnerType(),
							preference.getPlid(), preference.getPortletId(),preference.getPreferences());
			String instagramCreateDate = portletPreferences.getValue("instagramCreateDate","");
			String instagramToken = portletPreferences.getValue("instagramToken","");

			// Vérifie si le token est toujours valide
			if(Validator.isNotNull(instagramCreateDate)) {
				LocalDate createDate = LocalDate.parse(instagramCreateDate, formatter);
				if (today.isEqual(createDate.plusDays(2))) {
					// on récupère un autre token
					Object[] stringData = {instagramToken};
					String apiURL = "https://graph.instagram.com/refresh_access_token?grant_type=ig_refresh_token&access_token=%s";
					apiURL = String.format(apiURL, stringData);
					try {
						JSONObject json = JSONHelper.readJsonFromURL(apiURL);
						String newInstagramToken = json.getString("access_token");

						// met à jour la configuration
						portletPreferences.setValue("instagramToken", newInstagramToken);
						portletPreferences.setValue("instagramCreateDate", today.format(formatter));
						portletPreferences.store();

						MultiVMPoolUtil.getPortalCache("instagram_cache").remove(instagramToken);
						MultiVMPoolUtil.getPortalCache("instagram_cache")
								.remove(instagramToken + "_last_update");
					} catch (Exception e) {
						log.error(e);
					}
				}
			}
		}
	}

	@Reference(unbind = "-")
	protected void setSocialService(SocialService socialService) {
		_socialService = socialService;
	}

	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper _schedulerEngineHelper;

	private SocialService _socialService;

}
