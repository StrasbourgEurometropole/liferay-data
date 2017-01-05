package eu.strasbourg.utils;

import com.liferay.portal.kernel.util.PropsUtil;

public class StrasbourgPropsUtil {
	public static String getRecaptchaSecretKey() {
		return PropsUtil.get("eu.strasbourg.recaptcha.secret");
	}
	
	public static String getRecaptchaPublicKey() {
		return PropsUtil.get("eu.strasbourg.recaptcha.public");
	}
	
	public static String getLegacyPlaceApiUrl() {
		return PropsUtil.get("eu.strasbourg.legacy.place.api.url");
	}
	
	public static String getLegacyPlaceApiAutocompleteUrl() {
		return PropsUtil.get("eu.strasbourg.legacy.place.api.autocomplete.url");
	}
}
