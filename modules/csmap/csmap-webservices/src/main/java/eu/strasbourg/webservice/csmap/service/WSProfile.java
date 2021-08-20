package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Set;

public class WSProfile {

    public static final String PATH = "api/users/";

    /**
     * Permet d'envoyer la requête à Entrouvert
     */
    public static JSONObject sendRequest(String profilePicture, String publikUserId) throws IOException {

        String url = buildUrl(publikUserId);

        // Construction de la requête
        // Patch ne fonctionne pas avec URLConnection, il faut contourner et simuler un post
        allowMethods("PATCH");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        // Authentification
        String username = StrasbourgPropsUtil.getPublikClientId();
        String password = StrasbourgPropsUtil.getPublikClientSecret();
        String encoded = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));

        // Création du paramètre
        String urlParameters = URLEncoder.encode("photo", "UTF-8") +"=" + URLEncoder.encode(profilePicture, "UTF-8");
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        // Set toutes les Propriétés/méthodes pour notre appel HTTP
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setDoOutput(true);
        connection.setRequestMethod("PATCH");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.write(postData);
        }

        connection.connect();
        JSONObject responseJson = JSONFactoryUtil.createJSONObject();
        responseJson.put("code", connection.getResponseCode());
        responseJson.put("message", connection.getResponseMessage());

        connection.disconnect();

        return responseJson;
    }

    private static String buildUrl(String publikUserId) {

        String url = StrasbourgPropsUtil.getEntrouvertURL();
        return url + PATH + publikUserId + "/";
    }

    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/*static field*/, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
