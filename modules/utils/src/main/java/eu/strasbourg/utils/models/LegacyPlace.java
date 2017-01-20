package eu.strasbourg.utils.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.utils.JSONHelper;

public class LegacyPlace {
	
	private String SIGId;

	private String alias;

	private String street; // rue
	private String zipCode; // codePostal
	private String city; // ville

	private String phone;
	private String email;

	private String picture; // illustration

	private String presentation;
	private String servicesAndActivities; // services
	private String features; // caracteristiques
	private String moreInformation; // infosComplementaires

	private String access; // modeAcces
	
	private String price; // tarifs

	private String accessForDisabled; // descriptionAccesHandicap
	private boolean accessForBlind; // accesHandicap > map > Physical disability
	private boolean accessForWheelchair; // accesHandicap > map > Visual
										 // impairment
	private boolean accessForDeaf; // accesHandicap > map > Intellectual
								   // disability
	private boolean accessForElder; // accesHandicap > map > Elderly person
	private boolean accessForDeficient; // accesHandicap > map > Hearing
										// impairment

	private String websiteName; // nomSiteInternet
	private String website; // urlSiteInternet
	private String facebookName; // nomFacebook
	private String facebookURL; // urlFacebook

	private Map<String, String> nextDaysSchedule; // horaires > map
	private List<String> nextDays;
	private List<String> nextSchedules;
	private String exceptionalSchedule; // horaireExceptionnel
	private Map<String, String> exceptionalOpenings; // ouvertureExceptionnelle > map
	private Map<String, String> exceptionalClosings; // fermetureExceptionnelle > map
	
	/**
	 * Retourne un nouvel objet "LegacyPlace" à partir de l'id SIG du lieu
	 */
	public static LegacyPlace fromSIGId(String SIGId, Locale locale) {
		String url = PropsUtil.get("eu.strasbourg.legacy.place.api.url");
		url = url.replace("[SIGID]", SIGId);
		url = url.replace("[LOCALE]", locale.getLanguage());
		try {
			JSONObject json = JSONHelper.readJsonFromURL(url);
			LegacyPlace legacyPlace = LegacyPlace.fromJSONObject(json, locale);
			return legacyPlace;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Retourne un nouvel objet "LegacyPlace" à partir du JSON du lieu
	 */
	public static LegacyPlace fromJSONObject(JSONObject json, Locale locale) {
		try {
			LegacyPlace legacyPlace = new LegacyPlace();
			legacyPlace.setSIGId(json.getString("idSurfs"));
			legacyPlace.setAlias(json.getString("nomLieu"));
			legacyPlace.setStreet(json.getString("rue"));
			legacyPlace.setZipCode(json.getString("codePostal"));
			legacyPlace.setCity(json.getString("ville"));
			legacyPlace.setPhone(json.getString("telephone"));
			legacyPlace.setEmail(json.getString("email"));
			legacyPlace.setPicture(json.getString("picture"));
			legacyPlace.setPresentation(json.getString("presentation"));
			legacyPlace.setServicesAndActivities(json.getString("services"));
			legacyPlace.setFeatures(json.getString("caracteristiques"));
			legacyPlace
				.setMoreInformation(json.getString("infosComplementaires"));
			legacyPlace.setAccess(json.getString("modeAcces"));
			legacyPlace.setAccessForDisabled(
				json.getString("descriptionAccesHandicap"));

			JSONObject accessForDisabledMap = json
				.getJSONObject("accesHandicap").getJSONObject("map");
			legacyPlace.setAccessForBlind(
				accessForDisabledMap.getBoolean("Visual impairment"));
			legacyPlace.setAccessForWheelchair(
				accessForDisabledMap.getBoolean("Physical disability"));
			legacyPlace.setAccessForDeaf(
				accessForDisabledMap.getBoolean("Hearing impairment"));
			legacyPlace.setAccessForElder(
				accessForDisabledMap.getBoolean("Elderly person"));
			legacyPlace.setAccessForDeficient(
				accessForDisabledMap.getBoolean("Intellectual disability"));
			
			legacyPlace.setPrice(json.getString("tarifs"));

			legacyPlace.setWebsiteName(json.getString("nomSiteInternet"));
			legacyPlace.setWebsite(json.getString("urlSiteInternet"));
			legacyPlace.setFacebookName(json.getString("nomFacebook"));
			legacyPlace.setFacebookURL(json.getString("urlFacebook"));

			// Programme des 7 jours suivants
			JSONObject nextDaysScheduleMapWrapper = json
				.getJSONObject("horaires");
			DateTimeFormatter dtfFrom = DateTimeFormatter.ofPattern("yyyyMMdd");
			DateTimeFormatter dtfTo = DateTimeFormatter
				.ofPattern("dd MMMM yyyy", locale);
			Map<String, String> nextDaysSchedule = new HashMap<String, String>();
			List<String> nextDays = new ArrayList<String>();
			List<String> nextSchedules = new ArrayList<String>();
			if (nextDaysScheduleMapWrapper != null) {
				JSONObject nextDaysScheduleMap = nextDaysScheduleMapWrapper
					.getJSONObject("map");
				if (nextDaysScheduleMap != null) {
					LocalDate date = LocalDate.now();
					for (int i = 0; i < 7; i++) {
						String schedule = nextDaysScheduleMap
							.getString(dtfFrom.format(date));
						nextDays.add(dtfTo.format(date));
						if (Validator.isNull(schedule) || schedule.contains("Closed*")) {
							nextDaysSchedule.put(dtfTo.format(date), LanguageUtil.get(locale, "eu.closed"));
							nextSchedules.add(LanguageUtil.get(locale, "eu.closed"));
						} else {
							nextDaysSchedule.put(dtfTo.format(date), schedule);
							nextSchedules.add(schedule);
						}
						date = date.plusDays(1);
					}
					legacyPlace.setNextDaysSchedule(nextDaysSchedule);
					legacyPlace.setNextDays(nextDays);
					legacyPlace.setNextSchedules(nextSchedules);
				}
			}
			
			// Ouvertures exceptionnelles
			JSONObject exceptionalOpeningsMapWrapper = json.getJSONObject("ouvertureExceptionnelle");
			if (exceptionalOpeningsMapWrapper != null) {
				JSONObject exceptionalOpeningsMap = exceptionalOpeningsMapWrapper.getJSONObject("map");
				Iterator<String> iterator = exceptionalOpeningsMap.keys();
				Map<String, String> exceptionalOpenings = new HashMap<String, String>();
				while (iterator.hasNext()) {
					String key = iterator.next();
					exceptionalOpenings.put(key, (String) exceptionalOpeningsMap.get(key));
				}
				legacyPlace.setExceptionalOpenings(exceptionalOpenings);
			}
			
			
			
			// Fermetures exceptionnelles
			JSONObject exceptionalClosingsMapWrapper = json.getJSONObject("fermetureExceptionnelle");
			if (exceptionalClosingsMapWrapper != null) {
				JSONObject exceptionalClosingsMap = exceptionalClosingsMapWrapper.getJSONObject("map");
				Iterator<String> iterator = exceptionalClosingsMap.keys();
				Map<String, String> exceptionalClosings = new HashMap<String, String>();
				while (iterator.hasNext()) {
					String key = iterator.next();
					exceptionalClosings.put(key, (String) exceptionalClosingsMap.get(key));
				}
				legacyPlace.setExceptionalClosings(exceptionalClosings);
			}
			
			legacyPlace.setExceptionalSchedule(json.getString("horaireExceptionnel"));
			
			return legacyPlace;
		} catch (Exception ex) {
			return null;
		}
	}

	public boolean hasAnyAccessForDisabled() {
		return this.getAccessForBlind() || this.getAccessForDeaf()
			|| this.getAccessForWheelchair() || this.getAccessForDeficient()
			|| this.getAccessForElder();
	}

	public String getSIGId() {
		return SIGId;
	}

	public void setSIGId(String sIGId) {
		SIGId = sIGId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getServicesAndActivities() {
		return servicesAndActivities;
	}

	public void setServicesAndActivities(String servicesAndActivities) {
		this.servicesAndActivities = servicesAndActivities;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getMoreInformation() {
		return moreInformation;
	}

	public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getAccessForDisabled() {
		return accessForDisabled;
	}

	public void setAccessForDisabled(String accessForDisabled) {
		this.accessForDisabled = accessForDisabled;
	}

	public boolean getAccessForBlind() {
		return accessForBlind;
	}

	public void setAccessForBlind(boolean accessForBlind) {
		this.accessForBlind = accessForBlind;
	}

	public boolean getAccessForWheelchair() {
		return accessForWheelchair;
	}

	public void setAccessForWheelchair(boolean accessForWheelchair) {
		this.accessForWheelchair = accessForWheelchair;
	}

	public boolean getAccessForDeaf() {
		return accessForDeaf;
	}

	public void setAccessForDeaf(boolean accessForDeaf) {
		this.accessForDeaf = accessForDeaf;
	}

	public boolean getAccessForElder() {
		return accessForElder;
	}

	public void setAccessForElder(boolean accessForElder) {
		this.accessForElder = accessForElder;
	}

	public boolean getAccessForDeficient() {
		return accessForDeficient;
	}

	public void setAccessForDeficient(boolean accessForDeficient) {
		this.accessForDeficient = accessForDeficient;
	}

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebookName() {
		return facebookName;
	}

	public void setFacebookName(String facebookName) {
		this.facebookName = facebookName;
	}

	public String getFacebookURL() {
		return facebookURL;
	}

	public void setFacebookURL(String facebookURL) {
		this.facebookURL = facebookURL;
	}

	public Map<String, String> getNextDaysSchedule() {
		return nextDaysSchedule;
	}

	public void setNextDaysSchedule(Map<String, String> nextDaysSchedule) {
		this.nextDaysSchedule = nextDaysSchedule;
	}

	public String getExceptionalSchedule() {
		return exceptionalSchedule;
	}

	public void setExceptionalSchedule(String exceptionalSchedule) {
		this.exceptionalSchedule = exceptionalSchedule;
	}

	public Map<String, String> getExceptionalOpenings() {
		return exceptionalOpenings;
	}

	public void setExceptionalOpenings(Map<String, String> exceptionalOpenings) {
		this.exceptionalOpenings = exceptionalOpenings;
	}

	public Map<String, String> getExceptionalClosings() {
		return exceptionalClosings;
	}

	public void setExceptionalClosings(Map<String, String> exceptionalClosings) {
		this.exceptionalClosings = exceptionalClosings;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<String> getNextDays() {
		return nextDays;
	}

	public void setNextDays(List<String> nextDays) {
		this.nextDays = nextDays;
	}

	public List<String> getNextSchedules() {
		return nextSchedules;
	}

	public void setNextSchedules(List<String> nextSchedules) {
		this.nextSchedules = nextSchedules;
	}
}
