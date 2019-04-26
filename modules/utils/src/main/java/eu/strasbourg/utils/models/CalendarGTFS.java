package eu.strasbourg.utils.models;

import java.text.ParseException;
import java.util.Date;

import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.exception.FileFormatException;

public class CalendarGTFS implements GTFSModel {
	
	private int service_id;
	private boolean monday;
	private boolean tuesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;
	private boolean saturday;
	private boolean sunday;
	private Date start_date;
	private Date end_date;

	@Override
	public void fromStringArray(String[] value) throws FileFormatException {
		try {
			this.service_id = Integer.parseInt(value[0]);
			this.monday = value[1].equals("1");
			this.tuesday = value[2].equals("1");
			this.wednesday = value[3].equals("1");
			this.thursday = value[4].equals("1");
			this.friday = value[5].equals("1");
			this.saturday = value[6].equals("1");
			this.sunday = value[7].equals("1");

			this.start_date = DateHelper.getDateFromGTFSCalendar(value[8]);
			this.end_date = DateHelper.getDateFromGTFSCalendar(value[9]);
		} catch (ParseException e) {
			throw new FileFormatException(e.getLocalizedMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new FileFormatException(e.getLocalizedMessage());
		} catch (NumberFormatException n) {
			throw new FileFormatException(n.getLocalizedMessage());
		}
	}

	/**
	 * @return the service_id
	 */
	public int getService_id() {
		return service_id;
	}

	/**
	 * @param service_id
	 *            the service_id to set
	 */
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	/**
	 * @return the monday
	 */
	public boolean isMonday() {
		return monday;
	}

	/**
	 * @param monday
	 *            the monday to set
	 */
	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	/**
	 * @return the tuesday
	 */
	public boolean isTuesday() {
		return tuesday;
	}

	/**
	 * @param tuesday
	 *            the tuesday to set
	 */
	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}

	/**
	 * @return the wednesday
	 */
	public boolean isWednesday() {
		return wednesday;
	}

	/**
	 * @param wednesday
	 *            the wednesday to set
	 */
	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	/**
	 * @return the thursday
	 */
	public boolean isThursday() {
		return thursday;
	}

	/**
	 * @param thursday
	 *            the thursday to set
	 */
	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	/**
	 * @return the friday
	 */
	public boolean isFriday() {
		return friday;
	}

	/**
	 * @param friday
	 *            the friday to set
	 */
	public void setFriday(boolean friday) {
		this.friday = friday;
	}

	/**
	 * @return the saturday
	 */
	public boolean isSaturday() {
		return saturday;
	}

	/**
	 * @param saturday
	 *            the saturday to set
	 */
	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}

	/**
	 * @return the sunday
	 */
	public boolean isSunday() {
		return sunday;
	}

	/**
	 * @param sunday
	 *            the sunday to set
	 */
	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}

	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date
	 *            the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date
	 *            the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	@Override
	public int getNbEntry() {
		return 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"service_id\":" + service_id + ", \"monday\":" + monday + ", \"tuesday\":" + tuesday + ", \"wednesday\":" + wednesday
				+ ", \"thursday\":" + thursday + ", \"friday\":" + friday + ", \"saturday\":" + saturday + ", \"sunday\":" + sunday + ", "
				+ (start_date != null ? "\"start_date\":" + DateHelper.getShortDateFormat(start_date) + ", " : "") 
				+ (end_date != null ? "\"end_date\":" + DateHelper.getShortDateFormat(end_date) + "" : "") + "}";
	}

}
