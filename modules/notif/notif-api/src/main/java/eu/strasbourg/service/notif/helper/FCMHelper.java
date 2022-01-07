package eu.strasbourg.service.notif.helper;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.notif.constants.SendStatus;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class FCMHelper {
    private final static Log log = LogFactoryUtil.getLog(FCMHelper.class);

    public static FirebaseApp initializeFCM(){
        FirebaseApp app = null;
        try {
            FileInputStream firebaseConfigPath = new FileInputStream(StrasbourgPropsUtil.getFCMConfigurationFile());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(firebaseConfigPath))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                app = FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
            else {
                app = FirebaseApp.getApps().get(0);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return app;
    }

    public static String generateNotifText (Notification notification){
        Locale locale = Locale.FRANCE;
        String body = "";
        if(Validator.isNotNull(notification.getSubtitle(locale))) {
            body += notification.getSubtitle(locale) + "\n\n";
        }
        body += notification.getContent(locale);
        return body;
    }

    public static String sendNotificationToTopic(Notification notification, String imageURL, String topic, String condition) {
        String notifText = generateNotifText(notification);

        Locale locale = Locale.FRANCE;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Map<String, String> datas = new HashMap<>();
        datas.put("title", notification.getTitle(locale));
        if(Validator.isNotNull(notification.getSubtitle(locale)))
            datas.put("subtitle", notification.getSubtitle(locale));
        datas.put("startDate", df.format(notification.getStartDate()));
        if(Validator.isNotNull(notification.getEndDate()))
            datas.put("endDate", df.format(notification.getEndDate()));
        datas.put("message", notification.getContent(locale));
        if(Validator.isNotNull(notification.getUrl(locale)))
            datas.put("url", notification.getUrl(locale));
        if(Validator.isNotNull(notification.getLabelUrl(locale)))
            datas.put("labelUrl", notification.getLabelUrl(locale));
        if(Validator.isNotNull(imageURL))
            datas.put("image", imageURL);
        if(notification.getIsAlert()==1)
            datas.put("isAlert", "true");
        else
            datas.put("isAlert", "false");

        return sendNotificationToTopic(notification.getTitle(locale), notifText, imageURL, topic, condition, datas);
    }

    public static String sendNotificationToTopic(String title, String body, String imageUrl, String topic, String condition, Map<String, String> datas) {
        initializeFCM();

        // envoi datas
        Message.Builder messageDatasBuilder = Message.builder().putAllData(datas);
        if(Validator.isNotNull(topic))
            messageDatasBuilder.setTopic(topic);
        else
            messageDatasBuilder.setCondition(condition);

        Message messageDatas = messageDatasBuilder.build();

        Gson gsonDatas = new GsonBuilder().setPrettyPrinting().create();
        String jsonDatasOutput = gsonDatas.toJson(messageDatas);
        String responseData = null;
        try {
            responseData = FirebaseMessaging.getInstance().send(messageDatas);
        } catch (Exception e) {
            log.error(e);
        }
        log.info("Sent datas to topic. Topic: " + topic + ", " + responseData + " msg " + jsonDatasOutput);

        try {
            Thread.sleep(1 * 1500);
        } catch (InterruptedException e) {
            log.error(e);
        }

        if(responseData.contains("fail")){
            return responseData;
        } else {
            // envoi notification
            com.google.firebase.messaging.Notification.Builder notifBuilder = com.google.firebase.messaging.Notification
                    .builder()
                    .setTitle(title)
                    .setBody(body);
            if(Validator.isNotNull(imageUrl))
                notifBuilder.setImage(imageUrl);

            Message.Builder messageNotifBuilder = Message.builder()
                    .putAllData(datas)
                    .setNotification(notifBuilder.build());
            if(Validator.isNotNull(topic))
                messageNotifBuilder.setTopic(topic);
            else
                messageNotifBuilder.setCondition(condition);

            Message messageNotif = messageNotifBuilder.build();

            Gson gsonNotif = new GsonBuilder().setPrettyPrinting().create();
            String jsonNotifOutput = gsonNotif.toJson(messageNotif);
            String responseNotif = null;
            try {
                responseNotif = FirebaseMessaging.getInstance().send(messageNotif);
            } catch (Exception e) {
                log.error(e);
            }
            log.info("Sent notif to topic. Topic: " + topic + ", " + responseNotif + " msg " + jsonNotifOutput);

            return responseNotif;
        }
    }
}
