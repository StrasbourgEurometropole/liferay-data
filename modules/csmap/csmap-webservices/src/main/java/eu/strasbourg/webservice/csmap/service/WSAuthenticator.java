package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ContentTypes;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.Component;

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

/**
 * Service s'occuppant de valider l'authentification entre l'application CSMAP et Authentik
 */
@Component(
        immediate = true,
        property = {},
        service = WSAuthenticator.class
)
public class WSAuthenticator {

    /**
     * Envoi de la requête vers l'IdP afin de récupérer un access token et l'id token
     */
    public JSONObject sendTokenRequest(String code) throws IOException {
        // Récupération des URL/URI configurables
        String authURL = StrasbourgPropsUtil.getPublikTokenURL();
        String redirectURI = StrasbourgPropsUtil.getCSMAPPublikRedirectURI();

        // Initialisation de la requête
        HttpURLConnection connection = (HttpURLConnection) new URL(authURL).openConnection();
        connection.setRequestMethod("POST");

        // Authentification
        String username = StrasbourgPropsUtil.getCSMAPPublikClientId();
        String password = StrasbourgPropsUtil.getCSMAPPublikClientSecret();
        String encoded = Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        connection.setRequestProperty("Authorization", "Basic " + encoded);

        // Paramètres
        String parameters = "grant_type=authorization_code&code=" + code + "&redirect_uri=" + redirectURI;
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

    private JSONObject readJsonFromInputStream(InputStream is) throws IOException, JSONException {
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return JSONFactoryUtil.createJSONObject(jsonText);
        } finally {
            is.close();
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
