package eu.strasbourg.utils.models;

import eu.strasbourg.utils.exception.FileFormatException;

public class StopTimesGTFS implements GTFSModel {

	private String trip_id;
	private StopTime arrival_time;
	private StopTime departure_time;
	private String stop_id;
	private int stop_sequence;
	private int pickup_type;
	private int drop_off_type;

	@Override
	public void fromStringArray(String[] value) throws FileFormatException {
		try {
			this.trip_id = value[0];
			this.arrival_time = new StopTime();
			arrival_time.fromString(value[1]);
			this.departure_time = new StopTime();
			this.departure_time.fromString(value[2]);
			this.stop_id = value[3];
			this.stop_sequence = Integer.parseInt(value[4]);
			this.pickup_type = Integer.parseInt(value[5]);
			this.drop_off_type = Integer.parseInt(value[6]);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new FileFormatException(e.getLocalizedMessage());
		} catch (NumberFormatException n) {
			throw new FileFormatException(n.getLocalizedMessage());
		}
	}

	/**
	 * @return the trip_id
	 */
	public String getTrip_id() {
		return trip_id;
	}

	/**
	 * @param trip_id
	 *            the trip_id to set
	 */
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	/**
	 * @return the arrival_time
	 */
	public StopTime getArrival_time() {
		return arrival_time;
	}

	/**
	 * @param arrival_time
	 *            the arrival_time to set
	 */
	public void setArrival_time(StopTime arrival_time) {
		this.arrival_time = arrival_time;
	}

	/**
	 * @return the departure_time
	 */
	public StopTime getDeparture_time() {
		return departure_time;
	}

	/**
	 * @param departure_time
	 *            the departure_time to set
	 */
	public void setDeparture_time(StopTime departure_time) {
		this.departure_time = departure_time;
	}

	/**
	 * @return the stop_id
	 */
	public String getStop_id() {
		return stop_id;
	}

	/**
	 * @param stop_id
	 *            the stop_id to set
	 */
	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	/**
	 * @return the stop_sequence
	 */
	public int getStop_sequence() {
		return stop_sequence;
	}

	/**
	 * @param stop_sequence
	 *            the stop_sequence to set
	 */
	public void setStop_sequence(int stop_sequence) {
		this.stop_sequence = stop_sequence;
	}

	/**
	 * @return the pickup_type
	 */
	public int getPickup_type() {
		return pickup_type;
	}

	/**
	 * @param pickup_type
	 *            the pickup_type to set
	 */
	public void setPickup_type(int pickup_type) {
		this.pickup_type = pickup_type;
	}

	/**
	 * @return the drop_off_type
	 */
	public int getDrop_off_type() {
		return drop_off_type;
	}

	/**
	 * @param drop_off_type
	 *            the drop_off_type to set
	 */
	public void setDrop_off_type(int drop_off_type) {
		this.drop_off_type = drop_off_type;
	}

	@Override
	public int getNbEntry() {
		return 7;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{" + (trip_id != null ? "\"trip_id\":\"" + trip_id + "\", " : "")
				+ (arrival_time != null ? "\"arrival_time\":" + arrival_time.toString() + ", " : "")
				+ (departure_time != null ? "\"departure_time\":" + departure_time.toString() + ", " : "") 
				+ (stop_id != null ? "\"stop_id\":\"" + stop_id + "\", " : "")
				+ "\"stop_sequence\":" + stop_sequence + ", "
				+ "\"pickup_type\":" + pickup_type + ", \"drop_off_type\":" + drop_off_type + ", " + "}";
	}

}
