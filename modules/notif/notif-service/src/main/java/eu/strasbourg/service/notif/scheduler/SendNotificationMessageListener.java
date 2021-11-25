package eu.strasbourg.service.notif.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.*;
import eu.strasbourg.service.notif.constants.BroadcastChannel;
import eu.strasbourg.service.notif.constants.SendStatus;
import eu.strasbourg.service.notif.constants.TypeBroadcast;
import eu.strasbourg.service.notif.helper.FCMHelper;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.NotificationLocalService;
import eu.strasbourg.service.notif.service.ServiceNotifLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Exporte automatiquement les campagnes au format JSON et les place dans le dossier d'import.
 */
@Component(immediate = true, service = SendNotificationMessageListener.class)
public class SendNotificationMessageListener
		extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Call service to be sure they are "awake"
		this._notificationLocalService.getClass();

		// Maintenant + 5 min pour ne pas lancer le scheduler au Startup du module
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		Date fiveMinutesFromNow = now.getTime();

		// Création du trigger "Toutes les 5 minutes"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, fiveMinutesFromNow, null,
				5, TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
				listenerClass, trigger);

		_schedulerEngineHelper.register(
				this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		this.log.info("Start sending notifications");
		List<Notification> notifs = _notificationLocalService.getNotificationsToSend();
		for(Notification notif : notifs){
			notif.setSendStatusCsmap(SendStatus.SENDING.getId());
			_notificationLocalService.updateNotification(notif);
			for(String broadcastChannel : notif.getBroadcastChannels().split(","))
				if(Integer.valueOf(broadcastChannel) == BroadcastChannel.CSMAP.getId()) {
					String topic;
					String imageUrl = null;
					ServiceNotif service = ServiceNotifLocalServiceUtil.getServiceNotif(notif.getServiceId());
					if(service.getPictoId()!=0){
						imageUrl = StrasbourgPropsUtil.getURL() + FileEntryHelper.getFileEntryURL(service.getPictoId());
					} else {
						imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/250px-Image_created_with_a_mobile_phone.png";
					}
					if (notif.getTypeBroadcast() == TypeBroadcast.DISTRICT.getId()){
						topic = AssetVocabularyHelper.getCategoryProperty(notif.getDistrict(), "SIG");
					} else if (notif.getTypeBroadcast() == TypeBroadcast.DEFAULT.getId()){
						topic = "SERVICE_" + service.getServiceId();
					} else {
						topic = "all";
					}
					String response = FCMHelper.sendNotificationToTopic(notif, imageUrl, topic);
					if(response.contains("fail")){
						notif.setSendStatusCsmap(SendStatus.ERROR.getId());
					} else {
						notif.setSendStatusCsmap(SendStatus.SEND.getId());
					}
					notif.setIsSend(true);
					_notificationLocalService.updateNotification(notif);
				}
		}
		this.log.info("Finish sending notifications");
	}

	@Reference(unbind = "-")
	protected void setNotificationLocalService(NotificationLocalService notificationLocalService) {
		_notificationLocalService = notificationLocalService;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
			SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	private volatile SchedulerEngineHelper _schedulerEngineHelper;
	private NotificationLocalService _notificationLocalService;
	private TriggerFactory _triggerFactory;
	private final Log log = LogFactoryUtil.getLog(this.getClass());
}
