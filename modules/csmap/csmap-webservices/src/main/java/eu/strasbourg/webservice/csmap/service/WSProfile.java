package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class WSProfile {

    public static final String PATH = "api/users/";

    /**
     * Permet d'envoyer la requête à Entrouvert
     */
    public static JSONObject sendRequest(String profilePicture, String publikUserId) throws IOException {
        return sendRequest(profilePicture, publikUserId, StrasbourgPropsUtil.getWebServiceDefaultTimeout());
    }

    public static JSONObject sendRequest(String profilePicture, String publikUserId, int timeOut) throws IOException {

        String url = buildUrl(publikUserId);

        // Construction de la requête
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setConnectTimeout(timeOut);
        connection.setReadTimeout(timeOut);

        // Authentification
        String username = StrasbourgPropsUtil.getPublikUserName();
        String password = StrasbourgPropsUtil.getPublikPassword();
        String encoded = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));

        // Création du paramètre
        String urlParameters = URLEncoder.encode("photo", "UTF-8") +"=" + URLEncoder.encode(profilePicture, "UTF-8");
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        // Set toutes les Propriétés/méthodes pour notre appel HTTP
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setDoOutput(true);
        // Normalement c'est du patch sur l'API, mais PATCH est aps dispo en JAVA
        // Mais heureusement pour nous l'API prend du PUT de la même façon
        connection.setRequestMethod("PUT");
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
}
