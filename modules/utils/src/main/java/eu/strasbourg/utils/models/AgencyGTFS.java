package eu.strasbourg.utils.models;

import eu.strasbourg.utils.exception.FileFormatException;

public class AgencyGTFS implements GTFSModel {

	private String agency_name;
	private String agency_url;
	private String agency_timezone;
	private String agency_phone;
	private String agency_fare_url;
	private String agency_lang;

	@Override
	public void fromStringArray(String[] value) throws FileFormatException {
		this.agency_name = value[0];
		this.agency_url = value[1];
		this.agency_timezone = value[2];
		this.agency_phone = value[3];
		this.agency_fare_url = value[4];
		this.agency_lang = value[5];
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public String getAgency_url() {
		return agency_url;
	}

	public void setAgency_url(String agency_url) {
		this.agency_url = agency_url;
	}

	public String getAgency_timezone() {
		return agency_timezone;
	}

	public void setAgency_timezone(String agency_timezone) {
		this.agency_timezone = agency_timezone;
	}

	public String getAgency_phone() {
		return agency_phone;
	}

	public void setAgency_phone(String agency_phone) {
		this.agency_phone = agency_phone;
	}

	public String getAgency_fare_url() {
		return agency_fare_url;
	}

	public void setAgency_fare_url(String agency_fare_url) {
		this.agency_fare_url = agency_fare_url;
	}

	public String getAgency_lang() {
		return agency_lang;
	}

	public void setAgency_lang(String agency_lang) {
		this.agency_lang = agency_lang;
	}
	
	@Override
	public int getNbEntry() {
		return 6;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{" + (agency_name != null ? "\"agency_name\":\"" + agency_name + "\", " : "")
				+ (agency_url != null ? "\"agency_url\":\"" + agency_url + "\", " : "") 
				+ (agency_timezone != null ? "\"agency_timezone\":\"" + agency_timezone + "\", " : "")
				+ (agency_phone != null ? "\"agency_phone\":\"" + agency_phone + "\", " : "")
				+ (agency_fare_url != null ? "\"agency_fare_url\":\"" + agency_fare_url + "\", " : "")
				+ (agency_lang != null ? "\"agency_lang\":\"" + agency_lang + "\"" : "") + "}";
	}

	

}
