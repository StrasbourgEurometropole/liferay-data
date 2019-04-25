package eu.strasbourg.utils.models;

import java.util.Date;

import eu.strasbourg.utils.exception.FileFormatException;

public class StopTime {

	private int hours, minutes, seconds;
	private boolean midnightJump = false;

	public void fromString(String value) throws FileFormatException {
		try {
			String[] split = value.split(":");
			this.hours = Integer.parseInt(split[0]);
			if (this.hours > 24) {
				this.hours = this.hours - 24;
				this.midnightJump = true;
			}
			this.minutes = Integer.parseInt(split[1]);
			this.seconds = Integer.parseInt(split[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new FileFormatException(e.getLocalizedMessage());
		} catch (NumberFormatException n) {
			throw new FileFormatException(n.getLocalizedMessage());
		}

	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes
	 *            the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the seconds
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * @param seconds
	 *            the seconds to set
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	/**
	 * @return the midnightJump
	 */
	public boolean isMidnightJump() {
		return midnightJump;
	}

	/**
	 * @param midnightJump
	 *            the midnightJump to set
	 */
	public void setMidnightJump(boolean midnightJump) {
		this.midnightJump = midnightJump;
	}

	public Date getTime() {
		Date result = null;
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(java.util.Calendar.MILLISECOND, 0);
		cal.set(java.util.Calendar.HOUR_OF_DAY, this.hours);
		cal.set(java.util.Calendar.MINUTE, this.minutes);
		cal.set(java.util.Calendar.SECOND, this.seconds);
		result = cal.getTime();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"time\":\"" + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + "\", \"Jump\":"
				+ midnightJump + "}";
	}
	
}
