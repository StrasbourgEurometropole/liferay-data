package eu.strasbourg.webservice.csmap.utils;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ContentTypes;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class WSAuthUtil {

    /**
     * Envoi de la requête vers l'IdP afin de récupérer un access token et l'id
     * token
     */
    public static JSONObject sendTokenRequest(HttpServletRequest request, String code) throws IOException {
        // Si oui, on récupère un token et les infos de l'utilisateur
        String authURL = StrasbourgPropsUtil.getPublikTokenURL();

        HttpURLConnection connection = (HttpURLConnection) new URL(authURL).openConnection();

        connection.setRequestMethod("POST");

        // Authentification
        String username = StrasbourgPropsUtil.getPublikClientId();
        String password = StrasbourgPropsUtil.getPublikClientSecret();
        String encoded = Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        connection.setRequestProperty("Authorization", "Basic " + encoded);

        // Paramètres
        String parameters = "grant_type=authorization_code&code=" + code + "&redirect_uri=" + getDomainRoot(request);
        byte[] postData = parameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-Type", ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED);
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.write(postData);
        }

        // Résultat
        try {
            InputStream is = connection.getInputStream();
            return readJsonFromInputStream(is);
        } catch (Exception ex) {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
            String str = readAll(rd);
            System.out.println(str);
            return null;
        }

    }

    public static JSONObject readJsonFromInputStream(InputStream is) throws IOException, JSONException {
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return JSONFactoryUtil.createJSONObject(jsonText);
        } finally {
            is.close();
        }
    }

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String getDomainRoot(HttpServletRequest request) {
        return request.getRequestURL().toString()
                .replace(request.getPathInfo(), "")
                .replace(request.getServletPath(), "");
    }

}
