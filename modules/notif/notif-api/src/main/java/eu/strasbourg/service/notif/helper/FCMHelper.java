package eu.strasbourg.service.notif.helper;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

public class FCMHelper {
    Logger logger = LoggerFactory.getLogger(FCMHelper.class);

    public FirebaseApp initializeFCM(){
        FirebaseApp app = null;
        try {
            FileInputStream firebaseConfigPath = new FileInputStream(StrasbourgPropsUtil.getFCMConfigurationFile());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(firebaseConfigPath))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                app = FirebaseApp.initializeApp(options);
                logger.info("Firebase application has been initialized");
            }
            else {
                app = FirebaseApp.getApps().get(0);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return app;
    }

    public String sendNotificationToTopic(Notification notification, String topic)
            throws InterruptedException, ExecutionException {
        Locale locale = Locale.FRANCE;
        FirebaseApp app = initializeFCM();
        Message message = Message.builder()
                        //.setApnsConfig(apnsConfig)
                        //.setAndroidConfig(androidConfig)
                        .setTopic(topic)
                        .setNotification(com.google.firebase.messaging.Notification
                            .builder()
                            .setTitle(notification.getTitle(locale))
                            .setBody(notification.getSubtitle(locale) + "\n" + notification.getContent(locale))
                            .build())
                        .build();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = FirebaseMessaging.getInstance(app).sendAsync(message).get();
        logger.info("Sent message to topic. Topic: " + topic + ", " + response + " msg " + jsonOutput);

        return response;
    }
}
