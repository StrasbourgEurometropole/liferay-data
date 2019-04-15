package eu.strasbourg.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

/*
 * Classe Helper pour tout ce qui concerne les dates
 *
 */
public class DateHelper {

	/**
	 * Retourne la liste des dates entre deux autres dates. La liste inclut la
	 * date de début et la date de fin
	 */
	public static List<Date> getDaysBetweenDates(Date startDate, Date endDate) {
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		while (calendar.getTime().before(endDate)
			|| calendar.getTime().equals(endDate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

	
	/**
	 * Affichage simple d'une période avec une date de début et une date de fin
	 */
	public static String displayPeriod(Date start, Date end, Locale locale) {
		String result = "";
		
		// Cas où une ou les deux dates sont null
		if (start == null || end == null) {
			return "";
		}

		long nbMillisecond = start.getTime();
		LocalDateTime startCal = LocalDateTime.ofInstant(Instant.ofEpochMilli(nbMillisecond), ZoneId.systemDefault());
		nbMillisecond = end.getTime();
		LocalDateTime endCal = LocalDateTime.ofInstant(Instant.ofEpochMilli(nbMillisecond), ZoneId.systemDefault());
		// Si la période dure 1 jour
		if (start.equals(end)) {
			if (locale.equals(Locale.FRANCE)) { // le dd MMMM yyyy
				DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
				result = "Le " + df.format(start);
			} else if (locale.equals(Locale.GERMANY)) {
				DateFormat df = new SimpleDateFormat("dd. MMMM yyyy", Locale.GERMANY);
				result = df.format(start);
			} else if (locale.equals(Locale.US)) { // dd MMMM yyyy
				DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
				result = df.format(start);
			}
		} else { // S'il dure plus longtemps
			if (locale.equals(Locale.FRANCE)) {
				result = "Du ";		
				if (startCal.getMonth() == endCal.getMonth()
					&& startCal.getYear() == endCal.getYear()) {
					DateFormat df = new SimpleDateFormat("dd", Locale.FRANCE);
					result += df.format(start);
				} else if (startCal.getYear() == endCal.getYear()) {
					DateFormat df = new SimpleDateFormat("dd MMMM", Locale.FRANCE);
					result += df.format(start);
				} else {
					DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
					result += df.format(start);
				}
				DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
				result += " au " + df.format(end);
			} else if (locale.equals(Locale.GERMANY)) {
				result = "Vom ";
				if (startCal.getMonth() == endCal.getMonth()
					&& startCal.getYear() == endCal.getYear()) {
					DateFormat df = new SimpleDateFormat("dd.", Locale.GERMANY);
					result += df.format(start);
				} else if (startCal.getYear() == endCal.getYear()) {
					DateFormat df = new SimpleDateFormat("dd. MMMM", Locale.GERMANY);
					result += df.format(start);
				} else {
					DateFormat df = new SimpleDateFormat("dd. MMMM yyyy", Locale.GERMANY);
					result += df.format(start);
				}
				DateFormat df = new SimpleDateFormat("dd. MMMM yyyy", Locale.GERMANY);
				result += " bis zum " + df.format(end);
			} else if (locale.equals(Locale.US)) {
				result = "From ";
				if (startCal.getMonth() == endCal.getMonth()
					&& startCal.getYear() == endCal.getYear()) {
					DateFormat df = new SimpleDateFormat("dd", Locale.US);
					result += df.format(start);
				} else if (startCal.getYear() == endCal.getYear()) {
					DateFormat df = new SimpleDateFormat("dd MMMM", Locale.US);
					result += df.format(start);;
				} else {
					DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
					result += df.format(start);
				}
				DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
				result += " to " + df.format(end);
			}
		}

		return result;
	}


	/**
	 * Affichage cour d'une date en multilangue
	 */
	public static String displayShortDate(Date date, Locale locale) {
		String result = "";

		// Cas où la date est null
		if (date == null) {
			return "";
		}

		if (locale.equals(Locale.FRANCE)) { // dd/MM/yyyy
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
			result = df.format(date);
		} else if (locale.equals(Locale.GERMANY)) { // dd.MM.yyyy
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
			result = df.format(date);
		} else if (locale.equals(Locale.US)) { // dd/MM/yyyy
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
			result = df.format(date);
		}

		return result;
	}


	/**
	 * Transforme un String en Date
	 */
	public static Date convertStringToDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date result = new Date();
		try {
			result = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}


	/**
	 * Affichage long d'une date en multilangue
	 */
	public static String displayLongDate(Date date, Locale locale) {
		String result = "";

		// Cas où la date est null
		if (date == null) {
			return "";
		}

		if (locale.equals(Locale.FRANCE)) { // EEE dd MMMM yyyy
			DateFormat df = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.FRANCE);
			result = df.format(date);
		} else if (locale.equals(Locale.GERMANY)) { // dd.MM.yyyy
			DateFormat df = new SimpleDateFormat("EEEE, 'den' dd. MMMM yyyy", Locale.GERMANY);
			result = df.format(date);
		} else if (locale.equals(Locale.US)) { // EEE dd MMMM yyyy
			DateFormat df = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.US);
			result = df.format(date);
		}

		return result;
	}
}
