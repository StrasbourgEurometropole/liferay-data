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

    public static String sendNotificationToTopic(Notification notification, String imageURL, String topic) {
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

        return sendNotificationToTopic(notification.getTitle(locale), notifText, imageURL, topic, datas);
    }

    public static String sendNotificationToTopic(String title, String body, String imageUrl, String topic, Map<String, String> datas) {
        initializeFCM();
        Message message;

        if(Validator.isNotNull(imageUrl)){
            message = Message.builder()
                    .setTopic(topic)
                    .setNotification(com.google.firebase.messaging.Notification
                            .builder()
                            .setTitle(title)
                            .setBody(body)
                            .setImage(imageUrl)
                            .build())
                    .putAllData(datas)
                    .build();
        } else {
            message = Message.builder()
                    .setTopic(topic)
                    .setNotification(com.google.firebase.messaging.Notification
                            .builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .putAllData(datas)
                    .build();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            log.error(e);
        }
        log.info("Sent message to topic. Topic: " + topic + ", " + response + " msg " + jsonOutput);

        return response;
    }
}
