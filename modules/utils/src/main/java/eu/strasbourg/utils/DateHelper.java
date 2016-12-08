package eu.strasbourg.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import com.liferay.portal.kernel.util.CalendarFactoryUtil;

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
		
		Calendar startCal = CalendarFactoryUtil.getCalendar(start.getTime());
		Calendar endCal = CalendarFactoryUtil.getCalendar(end.getTime());
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
				if (startCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH)
					&& startCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)) {
					DateFormat df = new SimpleDateFormat("dd", Locale.FRANCE);
					result += df.format(start);
				} else if (startCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)) {
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
				if (startCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH)
					&& startCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)) {
					DateFormat df = new SimpleDateFormat("dd.", Locale.GERMANY);
					result += df.format(start);
				} else if (startCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)) {
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
				if (startCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH)
					&& startCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)) {
					DateFormat df = new SimpleDateFormat("dd", Locale.US);
					result += df.format(start);
				} else if (startCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)) {
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
}
