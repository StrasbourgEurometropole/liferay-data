package eu.strasbourg.webservice.csmap.utils;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class WSTokenUtil {

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

    public static String generateRandomToken(int length) {
        return StringUtil.randomString(length);
    }

    /**
     * Hashage de la valeur du RefreshTOken via SHA-256 avec un pepper
     *
     * @param refreshTokenValue
     * @return String (refreshToken hashed)
     * @throws NoSuchAlgorithmException
     */
    public static String hashToken(String refreshTokenValue) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] refreshTokenHashed = digest.digest(refreshTokenValue.getBytes(StandardCharsets.UTF_8));
        return String.format("%064x", new java.math.BigInteger(1, refreshTokenHashed));
    }

    public static boolean isRefreshTokensDateValid(Date date, int validityDate) {
        boolean result = false;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, validityDate);

        Date tokenDatePlusValidityDays = c.getTime();
        Date now = new Date();

        if (now.compareTo(tokenDatePlusValidityDays) < 0)
            result = true;

        return result;
    }

    public static boolean isBaseNonceDateValid(Date date, int validitySecond) {
        boolean result = false;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, validitySecond);

        Date tokenDatePlusValiditySeconds = c.getTime();
        Date now = new Date();

        if (now.compareTo(tokenDatePlusValiditySeconds) < 0)
            result = true;

        return result;
    }

    public static String hashCodeVerifier(String codeVerifier) throws NoSuchAlgorithmException {
        String codeChallenge = null;

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash256 = digest.digest(codeVerifier.getBytes(StandardCharsets.UTF_8));

        codeChallenge = Base64.getEncoder().encodeToString(hash256);

        return codeChallenge;
    }
}
