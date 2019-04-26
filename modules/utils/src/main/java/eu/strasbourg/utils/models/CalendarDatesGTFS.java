package eu.strasbourg.utils.models;

import java.text.ParseException;
import java.util.Date;

import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.exception.FileFormatException;

public class CalendarDatesGTFS implements GTFSModel {

	private int service_id;
	private Date date;
	private int exception_type;

	@Override
	public void fromStringArray(String[] value) throws FileFormatException {
		try {
			this.service_id = Integer.parseInt(value[0]);
			this.date = DateHelper.getDateFromGTFSCalendar(value[1]);
			this.exception_type = Integer.parseInt(value[2]);
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
	 * @param service_id  the service_id to set
	 */
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the exception_type
	 */
	public int getException_type() {
		return exception_type;
	}

	/**
	 * @param exception_type the exception_type to set
	 */
	public void setException_type(int exception_type) {
		this.exception_type = exception_type;
	}

	@Override
	public int getNbEntry() {
		return 3;
	}

	@Override
	public String toString() {
		return "{\"service_id\":" + service_id + ",\"date\":" + DateHelper.getShortDateFormat(date) + ", \"exception_type\":" + exception_type + "}";
	}
	
}
