package eu.strasbourg.utils.models;

import eu.strasbourg.utils.exception.FileFormatException;

public class StopsGTFS implements GTFSModel {
	
	private String stop_id;
	private String stop_code;
	private double stop_lat;
	private double stop_lon;
	private String stop_name;
	private String stop_url;
	private String stop_desc;

	@Override
	public void fromStringArray(String[] value) throws FileFormatException {
		try {
			this.stop_id = value[0];
			this.stop_code = value[1];
			this.stop_lat = Double.parseDouble(value[2]);
			this.stop_lon =  Double.parseDouble(value[3]);
			this.stop_name = value[4];
			this.stop_url = value[5];
			this.stop_desc = value[6];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new FileFormatException(e.getLocalizedMessage());
		} catch (NumberFormatException n) {
			throw new FileFormatException(n.getLocalizedMessage());
		}
	}

	/**
	 * @return the stop_id
	 */
	public String getStop_id() {
		return stop_id;
	}

	/**
	 * @param stop_id the stop_id to set
	 */
	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}
	
	/**
	 * @return the stop_code
	 */
	public String getStop_code() {
		return stop_code;
	}
	
	/**
	 * @param stop_code the stop_code to set
	 */
	public void setStop_code(String stop_code) {
		this.stop_code = stop_code;
	}
	
	/**
	 * @return the stop_lat
	 */
	public double getStop_lat() {
		return stop_lat;
	}

	/**
	 * @param stop_lat
	 *            the stop_lat to set
	 */
	public void setStop_lat(double stop_lat) {
		this.stop_lat = stop_lat;
	}

	/**
	 * @return the stop_lon
	 */
	public double getStop_lon() {
		return stop_lon;
	}

	/**
	 * @param stop_lon
	 *            the stop_lon to set
	 */
	public void setStop_lon(double stop_lon) {
		this.stop_lon = stop_lon;
	}
	
	/**
	 * @return the stop_name
	 */
	public String getStop_name() {
		return stop_name;
	}

	/**
	 * @param stop_name
	 *            the stop_name to set
	 */
	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	/**
	 * @return the stop_url
	 */
	public String getStop_url() {
		return stop_url;
	}

	/**
	 * @param stop_url
	 *            the stop_url to set
	 */
	public void setStop_url(String stop_url) {
		this.stop_url = stop_url;
	}
	
	/**
	 * @return the stop_desc
	 */
	public String getStop_desc() {
		return stop_desc;
	}

	/**
	 * @param stop_desc
	 *            the stop_desc to set
	 */
	public void setStop_desc(String stop_desc) {
		this.stop_desc = stop_desc;
	}

	@Override
	public int getNbEntry() {
		return 7;
	}

	@Override
	public String toString() {
		return "{" + (stop_id != null ? "\"stop_id\":\"" + stop_id + "\", " : "") 
				+ (stop_code != null ? "\"stop_code\":\"" + stop_code + "\", " : "")
				+ (stop_name != null ? "\"stop_name\":\"" + stop_name + "\", " : "") + " }";
	}

}
