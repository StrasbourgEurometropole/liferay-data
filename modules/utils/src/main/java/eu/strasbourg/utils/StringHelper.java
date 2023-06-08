package eu.strasbourg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.Collator;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

public class StringHelper {

	/**
	 * Retourne true si s1 et s2 sont les mêmes chaînes de caractère, sans
	 * prendre en compte les différences d'accentuation
	 */
	public static boolean compareIgnoringAccentuation(String s1, String s2) {
		Collator instance = Collator.getInstance();

		// This strategy mean it'll ignore the accents
		instance.setStrength(Collator.NO_DECOMPOSITION);

		return instance.compare(s1, s2) == 0;
	}

	public static String readStringFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			return readAll(rd);
		} finally {
			is.close();
		}
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
