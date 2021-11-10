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
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class FCMHelper {
    public final static Log log = LogFactoryUtil.getLog(FCMHelper.class);

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
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return app;
    }

    public static String sendNotificationToTopic(Notification notification, String topic)
            throws ExecutionException, InterruptedException {
        Locale locale = Locale.FRANCE;
        String title = notification.getTitle(locale);
        String body = notification.getSubtitle(locale) + "\n" + notification.getContent(locale);
        return sendNotificationToTopic(title, body, topic);
    }

    public static String sendNotificationToTopic(String title, String body, String topic)
            throws InterruptedException, ExecutionException {
        FirebaseApp app = initializeFCM();
        Message message = Message.builder()
                        //.setApnsConfig(apnsConfig)
                        //.setAndroidConfig(androidConfig)
                        .setTopic(topic)
                        .setNotification(com.google.firebase.messaging.Notification
                            .builder()
                            .setTitle(title)
                            .setBody(body)
                            //.setImage(stringImageUrl)
                            .build())
                        .build();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = FirebaseMessaging.getInstance(app).sendAsync(message).get();
        log.info("Sent message to topic. Topic: " + topic + ", " + response + " msg " + jsonOutput);

        return response;
    }
}
