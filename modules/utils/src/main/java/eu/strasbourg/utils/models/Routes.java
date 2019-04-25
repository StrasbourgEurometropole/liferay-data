package eu.strasbourg.utils.models;

import eu.strasbourg.utils.exception.FileFormatException;

public class Routes implements GTFSModel {

	private String route_id;
	private String route_short_name;
	private String route_long_name;
	private String route_desc;
	private int route_type;
	private String route_color;
	private String route_text_color;

	@Override
	public void fromStringArray(String[] value) throws FileFormatException {
		try {
			this.route_id = value[0];
			this.route_short_name = value[1].replace("\"", "");
			this.route_long_name = value[2].replace("\"", "");
			this.route_desc = value[3];
			this.route_type = Integer.parseInt(value[4]);
			this.route_color = value[5];
			this.route_text_color = value[6];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new FileFormatException(e.getLocalizedMessage());
		} catch (NumberFormatException n) {
			throw new FileFormatException(n.getLocalizedMessage());
		}
	}

	/**
	 * @return the route_id
	 */
	public String getRoute_id() {
		return route_id;
	}

	/**
	 * @param route_id
	 *            the route_id to set
	 */
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	/**
	 * @return the route_short_name
	 */
	public String getRoute_short_name() {
		return route_short_name;
	}

	/**
	 * @param route_short_name
	 *            the route_short_name to set
	 */
	public void setRoute_short_name(String route_short_name) {
		this.route_short_name = route_short_name;
	}

	/**
	 * @return the route_long_name
	 */
	public String getRoute_long_name() {
		return route_long_name;
	}

	/**
	 * @param route_long_name
	 *            the route_long_name to set
	 */
	public void setRoute_long_name(String route_long_name) {
		this.route_long_name = route_long_name;
	}

	/**
	 * @return the route_desc
	 */
	public String getRoute_desc() {
		return route_desc;
	}

	/**
	 * @param route_desc
	 *            the route_desc to set
	 */
	public void setRoute_desc(String route_desc) {
		this.route_desc = route_desc;
	}

	/**
	 * @return the route_type
	 */
	public int getRoute_type() {
		return route_type;
	}

	/**
	 * @param route_type
	 *            the route_type to set
	 */
	public void setRoute_type(int route_type) {
		this.route_type = route_type;
	}

	/**
	 * @return the route_color
	 */
	public String getRoute_color() {
		return route_color;
	}

	/**
	 * @param route_color
	 *            the route_color to set
	 */
	public void setRoute_color(String route_color) {
		this.route_color = route_color;
	}

	/**
	 * @return the route_text_color
	 */
	public String getRoute_text_color() {
		return route_text_color;
	}

	/**
	 * @param route_text_color
	 *            the route_text_color to set
	 */
	public void setRoute_text_color(String route_text_color) {
		this.route_text_color = route_text_color;
	}

	@Override
	public int getNbEntry() {
		return 9;
	}

	@Override
	public String toString() {
		return "{" + (route_id != null ? "\"route_id\":\"" + route_id + "\", " : "")
				+ (route_short_name != null && !route_short_name.isEmpty() ? "\"route_short_name\":\"" + route_short_name + "\", " : "")
				+ (route_long_name != null && !route_long_name.isEmpty() ? "\"route_long_name\":\"" + route_long_name + "\", " : "")
				+ (route_desc != null ? "\"route_desc\":\"" + route_desc + "\", " : "") + "\"route_type\":" + route_type + ", "
				+ (route_color != null && !route_color.isEmpty()? "\"route_color\":\"" + route_color + "\", " : "")
				+ (route_text_color != null && !route_text_color.isEmpty()? "\"route_text_color\":\"" + route_text_color + "\"" : "") + "}";
	}

}
