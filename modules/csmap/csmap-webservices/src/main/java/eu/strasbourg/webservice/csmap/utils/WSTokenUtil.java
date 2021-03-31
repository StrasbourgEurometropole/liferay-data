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

}
