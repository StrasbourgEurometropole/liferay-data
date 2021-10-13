package eu.strasbourg.portlet.notif.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.notif.constants.BroadcastChannel;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.service.notif.service.NotificationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.NOTIF_BO,
                "mvc.command.name=saveNotification"
        },
        service = MVCActionCommand.class
)
public class SaveNotificationActionCommand implements MVCActionCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    private ThemeDisplay themeDisplay;
    private long notificationId;
    // Défini le format de date à utiliser pour les champs temporels
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {
        try {
            // Récupération du contexte de la requêtes
            ServiceContext sc = ServiceContextFactory.getInstance(request);
            themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);


            // Validation
            boolean isValid;
            // Si duplication
            boolean isDuplication = ParamUtil.getBoolean(request, "new");
            if (isDuplication) {
                isValid = true;
            }else
                isValid = this.validate(request);
            if (!isValid) {
                // Si pas valide : on reste sur la page d'édition
                PortalUtil.copyRequestParameters(request, response);

                String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
                PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                        PortletRequest.RENDER_PHASE);

                response.setRenderParameter("returnURL", returnURL.toString());
                response.setRenderParameter("cmd", "editNotification");
                response.setRenderParameter("mvcPath", "/notif-bo-edit-notification.jsp");
                return false;
            }

            // Si édition ou création d'une nouvelle entrée
            Notification notification;
            if (this.notificationId == 0) {
                notification = _notificationLocalService.createNotification(sc);
            } else {
                notification = _notificationLocalService.getNotification(this.notificationId);
            }

            // Si duplication
            if (isDuplication)
                notification = _notificationLocalService.duplicateNotification(sc, notification);

            // Champ : service
            long serviceId = ParamUtil.getLong(request, "service");
            notification.setServiceId(serviceId);

            // Champ : is alerte
            int isAlert = ParamUtil.getInteger(request, "notificationType");
            notification.setIsAlert(isAlert);

            // Champ : nature
            Long natureId = ParamUtil.getLong(request, "nature");
            notification.setNatureId(natureId);

            // Champ : date de diffusion
            String broadcastDateString = ParamUtil.getString(request, "broadcastDate");
            String broadcastDateTimeString = ParamUtil.getString(request, "broadcastDateTime");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date broadcastDate = GetterUtil.getDate(broadcastDateString + " " + broadcastDateTimeString, sdf);
            notification.setBroadcastDate(broadcastDate);

            // Champ : titre
            Map<Locale, String> title = LocalizationUtil
                    .getLocalizationMap(request, "title");
            notification.setTitleMap(title);

            // Champ : sous-titre
            Map<Locale, String> subtitle = LocalizationUtil
                    .getLocalizationMap(request, "subtitle");
            notification.setSubtitleMap(subtitle);

            // Champ : date de début
            Date startDate = ParamUtil.getDate(request,
                    "startDate" , dateFormat);
            LocalDateTime start = new Timestamp(startDate.getTime())
                    .toLocalDateTime().withHour(0).withMinute(0).withSecond(0).withNano(0);
            notification.setStartDate(Timestamp.valueOf(start));
            notification.setSubtitleMap(subtitle);

            // Champ : date de fin
            Date endDate = ParamUtil.getDate(request,
                    "endDate" , dateFormat);
            LocalDateTime end = new Timestamp(endDate.getTime())
                    .toLocalDateTime().withHour(0).withMinute(0).withSecond(0).withNano(0);
            notification.setEndDate(Timestamp.valueOf(end));

            // Champ : message
            Long messageId = ParamUtil.getLong(request, "message");
            notification.setMessageId(messageId);

            // Champ : content
            Map<Locale, String> content = LocalizationUtil
                    .getLocalizationMap(request, "content");
            notification.setContentMap(content);

            // Champ : labelUrl
            Map<Locale, String> labelUrl = LocalizationUtil
                    .getLocalizationMap(request, "labelUrl");
            notification.setLabelUrlMap(labelUrl);

            // Champ : url
            Map<Locale, String> url = LocalizationUtil
                    .getLocalizationMap(request, "url");
            notification.setUrlMap(url);

            // Champ : broadcast-type
            Long broadcastType = ParamUtil.getLong(request, "broadcast-type");
            notification.setTypeBroadcast(broadcastType);
            notification.setUrlMap(url);

            // Champ : quartier
            if(broadcastType == 3) {
                Long district = ParamUtil.getLong(request, "district");
                notification.setDistrict(district);
            }

            // Champ : broadcast-channels
            String broadcastChannels = ParamUtil.getString(request, "broadcast-channels");
            notification.setBroadcastChannels(broadcastChannels);

            // mise à 1 du sendStatus des channels choisis
            for (String broadcastChannelId : broadcastChannels.split(",")) {
                // on réinitialise les statuts d'envoi si le scheduler n'est pas encore passé dessus
                if(notification.getSendStatusCsmap() == 1)
                    notification.setSendStatusCsmap(0);
                if(notification.getSendStatusTwitter() == 1)
                    notification.setSendStatusTwitter(0);
                if(notification.getSendStatusMonst() == 1)
                    notification.setSendStatusMonst(0);
                if(notification.getSendStatusMail() == 1)
                    notification.setSendStatusMail(0);
                if(notification.getSendStatusSegur() == 1)
                    notification.setSendStatusSegur(0);

                // on met à jour les statuts d'envoi si le scheduler n'est pas encore passé dessus
                BroadcastChannel broadcastChannel = BroadcastChannel.get(Long.parseLong(broadcastChannelId));
                switch (broadcastChannel.getStatusField()){
                    case "sendStatusCsmap":
                        if(notification.getSendStatusCsmap() == 0)
                            notification.setSendStatusCsmap(1);
                        break;
                    case "sendStatusTwitter":
                        if(notification.getSendStatusTwitter() == 0)
                            notification.setSendStatusTwitter(1);
                        break;
                    case "sendStatusMonst":
                        if(notification.getSendStatusMonst() == 0)
                            notification.setSendStatusMonst(1);
                        break;
                    case "sendStatusMail":
                        if(notification.getSendStatusMail() == 0)
                            notification.setSendStatusMail(1);
                        break;
                    case "sendStatusSegur":
                        if(notification.getSendStatusSegur() == 0)
                            notification.setSendStatusSegur(1);
                        break;
                }
            }

            _notificationLocalService.updateNotification(notification, sc);

        } catch (Exception e) {
            log.error(e);
        }
        return true;
    }

    /**
     * Validation de la requête
     */
    private boolean validate(ActionRequest request) {
        boolean isValid = true;

        this.notificationId = ParamUtil.getLong(request, "notificationId");

        // ServiceId
        if (Validator.isNull(ParamUtil.getLong(request, "service"))) {
            SessionErrors.add(request, "service-error");
            isValid = false;
        }

        // NatureId
        if (Validator.isNull(ParamUtil.getLong(request, "nature"))) {
            SessionErrors.add(request, "nature-error");
            isValid = false;
        }

        // Date de début
        if (Validator.isNull(ParamUtil.getDate(request, "broadcastDate", dateFormat))) {
            SessionErrors.add(request, "broadcast-date-error");
            isValid = false;
        }

        // titre
        if (Validator.isNull(ParamUtil.getString(request, "title"))) {
            SessionErrors.add(request, "title-error");
            isValid = false;
        }

        // Date de début
        if (Validator.isNull(ParamUtil.getDate(request, "startDate", dateFormat))) {
            SessionErrors.add(request, "start-date-error");
            isValid = false;
        }

        // Date de fin
        if (Validator.isNull(ParamUtil.getDate(request, "endDate", dateFormat))) {
            SessionErrors.add(request, "end-date-error");
            isValid = false;
        }

        // Contenu
        if (Validator.isNull(ParamUtil.getString(request, "content"))) {
            SessionErrors.add(request, "content-error");
            isValid = false;
        }

        // Type de diffusion
        long broadcastType = ParamUtil.getLong(request, "broadcast-type");
        if (Validator.isNull(broadcastType)) {
            SessionErrors.add(request, "broadcast-type-error");
            isValid = false;
        }

        // Quartier
        if(broadcastType == 3) {
            if (Validator.isNull(ParamUtil.getLong(request, "district"))) {
                SessionErrors.add(request, "district-error");
                isValid = false;
            }
        }

        // Canaux de diffusion
        if (Validator.isNull(ParamUtil.getLong(request, "broadcast-channels"))) {
            SessionErrors.add(request, "broadcast-channels-error");
            isValid = false;
        }

        return isValid;
    }

    @Reference(unbind = "-")
    protected void setNotificationLocalService(NotificationLocalService notificationLocalService) {
        _notificationLocalService = notificationLocalService;
    }

    private NotificationLocalService _notificationLocalService;

}
