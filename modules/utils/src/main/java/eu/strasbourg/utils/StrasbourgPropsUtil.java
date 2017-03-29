package eu.strasbourg.utils;

import com.liferay.portal.kernel.util.PropsUtil;

public class StrasbourgPropsUtil {
	
	public static String getEnvironment() {
		return PropsUtil.get("eu.strasbourg.environment");
	}
	
	public static String getURL() {
		return PropsUtil.get("eu.strasbourg.url");
	}
	
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
	
	public static String getAgendaImportMails() {
		return PropsUtil.get("eu.strasbourg.agenda.mail");
	}

	public static String getAgendaImportMailsForProvider(String provider) {
		return PropsUtil.get("eu.strasbourg.agenda.mail." + provider);
	}

	public static String getAgendaImportDirectory() {
		return PropsUtil.get("eu.strasbourg.agenda.file");
	}
	
	public static String getPlaceImportMails() {
		return PropsUtil.get("eu.strasbourg.place.mail");
	}

	public static String getAgendaPlatformURL() {
		return PropsUtil.get("eu.strasbourg.agenda.platform.url");
	}
	
	public static String getAgendaDetailURL() {
		return PropsUtil.get("eu.strasbourg.agenda.detail.url");
	}
}
