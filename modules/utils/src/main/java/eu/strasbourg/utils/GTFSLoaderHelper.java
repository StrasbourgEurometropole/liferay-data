package eu.strasbourg.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.strasbourg.utils.exception.FileAccessException;
import eu.strasbourg.utils.models.Calendar;
import eu.strasbourg.utils.models.CalendarDates;
import eu.strasbourg.utils.models.Routes;
import eu.strasbourg.utils.models.StopTimes;
import eu.strasbourg.utils.models.Stops;
import eu.strasbourg.utils.models.Trips;
import eu.strasbourg.utils.parser.CalendarDatesParser;
import eu.strasbourg.utils.parser.CalendarParser;
import eu.strasbourg.utils.parser.RoutesParser;
import eu.strasbourg.utils.parser.StopTimesParser;
import eu.strasbourg.utils.parser.StopsParser;
import eu.strasbourg.utils.parser.TripsParser;

public class GTFSLoaderHelper {

	private static String COMMA_SEP = ",";
	private static String CALENDAR_FILENAME = "calendar.txt";
	private static String CALENDAR_DATES_FILENAME = "calendar_dates.txt";
	private static String ROUTES_FILENAME = "routes.txt";
	private static String STOPTIMES_FILENAME = "stop_times.txt";
	private static String STOPS_FILENAME = "stops.txt";
	private static String TRIPS_FILENAME = "trips.txt";
	
	/**
	 * Charge toutes les données Calendar en fonction du chemin fourni en paramètre
	 * 
	 * @param directory ex "D:\\MONDOSSIER\\"
	 * @return mapCalendars Une map des donnees Calendar avec comme clef le service_id
	 */
	public static Map<Integer, Calendar> readCalendarData(String directory) throws FileAccessException {
		CalendarParser calParser = new CalendarParser(Calendar.class);
		List<Calendar> calendars = calParser.getAll(directory + CALENDAR_FILENAME, COMMA_SEP);
		Map<Integer, Calendar> mapCalendars = new HashMap<Integer, Calendar>();
		for (Calendar c : calendars) {
			mapCalendars.put(c.getService_id(), c);
		}
		return mapCalendars;
	}
	
	/**
	 * Charge toutes les données CalendarDates en fonction du chemin fourni en paramètre
	 * 
	 * @param directory ex "D:\\MONDOSSIER\\"
	 * @return mapCalendarDates Une map des donnees CalendarDates avec comme clef le service_id
	 */
	public static Map<Integer, List<CalendarDates>> readCalendarDatesData(String directory) throws FileAccessException {
		CalendarDatesParser calDatesParser = new CalendarDatesParser(CalendarDates.class);
		List<CalendarDates> calDates = calDatesParser.getAll(directory + CALENDAR_DATES_FILENAME, COMMA_SEP);
		Map<Integer, List<CalendarDates>> mapCalendarDates = new HashMap<Integer, List<CalendarDates>>();
		for (CalendarDates cal : calDates) {
			if (mapCalendarDates.containsKey(cal.getService_id())) {
				mapCalendarDates.get(cal.getService_id()).add(cal);
			} else {
				List<CalendarDates> lst = new ArrayList<CalendarDates>();
				lst.add(cal);
				mapCalendarDates.put(cal.getService_id(), lst);
			}
		}
		return mapCalendarDates;
	}
	
	/**
	 * Charge toutes les données Route en fonction du chemin fourni en paramètre
	 * 
	 * @param directory ex "D:\\MONDOSSIER\\"
	 * @return mapCalendars Une map des donnees Route avec comme clef le route_id
	 */
	public static Map<String, Routes> readRoutesData(String directory) throws FileAccessException {
		RoutesParser routesParser = new RoutesParser(Routes.class);
		Map<String, Routes> mapRoutes = new HashMap<String, Routes>();
		List<Routes> routes = routesParser.getAll(directory + ROUTES_FILENAME, COMMA_SEP);
		for (Routes route : routes) {
			mapRoutes.put(route.getRoute_id(), route);
		}
		return mapRoutes;
	}
	
	/**
	 * Charge toutes les données StopTimes en fonction du chemin fourni en paramètre
	 * 
	 * @param directory ex "D:\\MONDOSSIER\\"
	 * @return mapCalendars Une map des donnees Calendar avec comme clef le trip_id
	 */
	public static Map<String, List<StopTimes>> readStopTimesData(String directory) throws FileAccessException {
		StopTimesParser stopTimesParser = new StopTimesParser(StopTimes.class);
		List<StopTimes> stopTimes = stopTimesParser.getAll(directory + STOPTIMES_FILENAME, COMMA_SEP);
		Map<String, List<StopTimes>> mapStopTimes = new HashMap<String, List<StopTimes>>();
		for (StopTimes stop : stopTimes) {
			if (mapStopTimes.containsKey(stop.getTrip_id())) {
				mapStopTimes.get(stop.getTrip_id()).add(stop);
			} else {
				List<StopTimes> lst = new ArrayList<StopTimes>();
				lst.add(stop);
				mapStopTimes.put(stop.getTrip_id(), lst);
			}
		}
		return(mapStopTimes);
	}
	
	/**
	 * Charge toutes les données Stops en fonction du chemin fourni en paramètre
	 * 
	 * @param directory ex "D:\\MONDOSSIER\\"
	 * @return mapCalendars Une map des donnees Stops avec comme clef le stop_id
	 */
	public static Map<String, Stops> readStopsData(String directory) throws FileAccessException {
		StopsParser stopsParser = new StopsParser(Stops.class);
		Map<String, Stops> mapStops = new HashMap<String, Stops>();
		List<Stops> stops = stopsParser.getAll(directory + STOPS_FILENAME, COMMA_SEP);
		for (Stops stop : stops) {
			mapStops.put(stop.getStop_id(), stop);
		}
		return mapStops;
	}
	
	/**
	 * Charge toutes les données Trips en fonction du chemin fourni en paramètre
	 * 
	 * @param directory ex "D:\\MONDOSSIER\\"
	 * @return mapCalendars Une map des donnees Trips avec comme clef le service_id
	 */
	public static Map<String, Trips> readTripsData(String directory) throws FileAccessException {
		TripsParser tripsParser = new TripsParser(Trips.class);
		List<Trips> trips = tripsParser.getAll(directory + TRIPS_FILENAME, COMMA_SEP);
		Map<String, Trips> mapTrips = new HashMap<String, Trips>();
		for (Trips trip : trips) {
			mapTrips.put(trip.getTrip_id(), trip);
		}
		return mapTrips;
	}
	
}
