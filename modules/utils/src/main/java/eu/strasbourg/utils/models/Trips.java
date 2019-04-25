package eu.strasbourg.utils.models;

import eu.strasbourg.utils.exception.FileFormatException;

public class Trips implements GTFSModel {

	private String route_id;
	private int service_id;
	private String trip_id;
	private String trip_headsign;
	private int direction_id;
	private String block_id;

	@Override
	public void fromStringArray(String[] value) throws FileFormatException {
		try {
			this.route_id = value[0];
			this.service_id = Integer.parseInt(value[1]);
			this.trip_id = value[2];
			this.trip_headsign = value[3].replace("\"", "");
			this.direction_id = Integer.parseInt(value[4]);
			this.block_id = value[5];
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
	 * @param route_id the route_id to set
	 */
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	/**
	 * @return the service_id
	 */
	public int getService_id() {
		return service_id;
	}

	/**
	 * @param service_id the service_id to set
	 */
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	/**
	 * @return the trip_id
	 */
	public String getTrip_id() {
		return trip_id;
	}

	/**
	 * @param trip_id the trip_id to set
	 */
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	/**
	 * @return the trip_headsign
	 */
	public String getTrip_headsign() {
		return trip_headsign;
	}

	/**
	 * @param trip_headsign the trip_headsign to set
	 */
	public void setTrip_headsign(String trip_headsign) {
		this.trip_headsign = trip_headsign;
	}

	/**
	 * @return the direction_id
	 */
	public int getDirection_id() {
		return direction_id;
	}

	/**
	 * @param direction_id the direction_id to set
	 */
	public void setDirection_id(int direction_id) {
		this.direction_id = direction_id;
	}

	/**
	 * @return the block_id
	 */
	public String getBlock_id() {
		return block_id;
	}

	/**
	 * @param block_id the block_id to set
	 */
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}

	@Override
	public int getNbEntry() {
		return 6;
	}

	@Override
	public String toString() {
		return "\"trips\":{" + (route_id != null ? "\"route_id\":\"" + route_id + "\", " : "") 
				+ "\"service_id\":" + service_id + ", "
				+ (trip_id != null ? "\"trip_id\":\"" + trip_id + "\", " : "") 
				+ (trip_headsign != null ? "\"trip_headsign\":\"" + trip_headsign + "\", " : "")
				+ "\"direction_id\":" + direction_id + ", " 
				+ (block_id != null && !block_id.isEmpty() ? "\"block_id\":\"" + block_id + "\", " : "") + " }";
	}

}
