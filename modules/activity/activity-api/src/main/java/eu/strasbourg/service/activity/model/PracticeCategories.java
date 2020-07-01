package eu.strasbourg.service.activity.model;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class PracticeCategories{

	private long entry_id;
	private String domaine;
	private String specialite;
	private String sous_specialite;
	private String sous_sous_specialite;
	private String pratique;
	private List<String> publics;
	private List<String> districts;
	private List<String> accessibilities;

	public PracticeCategories(Object[] values) {
		this.entry_id = ((BigInteger) values[0]).longValue();
		this.domaine = (String) values[1];
		this.specialite = (String) values[2];
		this.sous_specialite = (String) values[3];
		this.sous_sous_specialite = (String) values[4];
		this.pratique = (String) values[5];
		String value = (String) values[6];
		this.publics = Arrays.asList(value.split("\\|\\|"));
		value = (String) values[7];
		if(Validator.isNotNull(value))
			this.districts = Arrays.asList(value.split("\\|\\|"));
		else
			this.districts = new ArrayList<>();
		value = (String) values[8];
		if(Validator.isNotNull(value))
			this.accessibilities = Arrays.asList(value.split("\\|\\|"));
		else
			this.accessibilities = new ArrayList<>();
	}

	public long getEntry_id() {
		return entry_id;
	}

	public String getDomaine(Locale locale){
		String languageId = LocaleUtil.toLanguageId(locale);
		return LocalizationUtil.getLocalization(domaine, languageId);
	}

	public String getSpecialite(Locale locale){
		String languageId = LocaleUtil.toLanguageId(locale);
		return LocalizationUtil.getLocalization(specialite, languageId);
	}

	public String getSous_specialite(Locale locale){
		String languageId = LocaleUtil.toLanguageId(locale);
		return LocalizationUtil.getLocalization(sous_specialite, languageId);
	}

	public String getSous_sous_specialite(Locale locale){
		String languageId = LocaleUtil.toLanguageId(locale);
		return LocalizationUtil.getLocalization(sous_sous_specialite, languageId);
	}

	public String getPratique(Locale locale){
		String languageId = LocaleUtil.toLanguageId(locale);
		return LocalizationUtil.getLocalization(pratique, languageId);
	}

	public String getPublics(Locale locale){
		String publics = "";
		String languageId = LocaleUtil.toLanguageId(locale);
		for (String _public : this.publics ) {
			if(publics.length() > 0)
				publics += ", ";
			publics += LocalizationUtil.getLocalization(_public, languageId);
		}
		return publics;
	}

	public String getDistricts(Locale locale){
		String districts = "";
		String languageId = LocaleUtil.toLanguageId(locale);
		for (String district : this.districts ) {
			if(districts.length() > 0)
				districts += ", ";
			districts += LocalizationUtil.getLocalization(district, languageId);
		}
		return districts;
	}

	public String getAccessibilities(Locale locale){
		String accessibilities = "";
		String languageId = LocaleUtil.toLanguageId(locale);
		for (String accessibility : this.accessibilities ) {
			if(accessibilities.length() > 0)
				accessibilities += ", ";
			accessibilities += LocalizationUtil.getLocalization(accessibility, languageId);
		}
		return accessibilities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"entry_id\":\"" + entry_id + "\", "
				+ (domaine != null ? "\"domaine\":\"" + domaine + "\", " : "")
				+ (specialite != null ? "\"specialite\":\"" + specialite + "\", " : "")
				+ (sous_specialite != null ? "\"sous_specialite\":\"" + sous_specialite + "\", " : "")
				+ (sous_sous_specialite != null ? "\"sous_sous_specialite\":\"" + sous_sous_specialite + "\", " : "")
				+ (pratique != null ? "\"pratique\":\"" + pratique + "\", " : "")
				+ (publics != null ? "\"publics\":\"" + publics + "\", " : "")
				+ (districts != null ? "\"districts\":\"" + districts + "\", " : "")
				+ (accessibilities != null ? "\"accessibilities\":\"" + accessibilities + "\"" : "") + "}";
	}
}
