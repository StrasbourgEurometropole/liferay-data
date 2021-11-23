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
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


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

    public static Map<String,String> generateNotifText (Notification notification){
        Map<String,String> notifText = new HashMap<>();
        Locale locale = Locale.FRANCE;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        String title = notification.getTitle(locale);
        String body = notification.getSubtitle(locale) + "\n"
                + df.format(notification.getStartDate()) + " - "
                + df.format(notification.getEndDate()) + "\n\n"
                + notification.getContent(locale);
        notifText.put("title",title);
        notifText.put("body",body);
        return notifText;
    }

    public static String sendNotificationToTopic(Notification notification, String imageURL, String topic) {
        Map<String,String> notifText = generateNotifText(notification);
        return sendNotificationToTopic(notifText.get("title"), notifText.get("body"), imageURL, topic);
    }

    public static String sendNotificationToTopic(String title, String body, String imageUrl, String topic) {
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
                    .build();
        } else {
            message = Message.builder()
                    .setTopic(topic)
                    .setNotification(com.google.firebase.messaging.Notification
                            .builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
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
