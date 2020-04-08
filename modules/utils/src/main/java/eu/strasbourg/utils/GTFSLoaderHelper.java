package eu.strasbourg.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.strasbourg.utils.exception.FileAccessException;
import eu.strasbourg.utils.models.AgencyGTFS;
import eu.strasbourg.utils.models.CalendarGTFS;
import eu.strasbourg.utils.models.CalendarDatesGTFS;
import eu.strasbourg.utils.models.RoutesGTFS;
import eu.strasbourg.utils.models.StopTimesGTFS;
import eu.strasbourg.utils.models.StopsGTFS;
import eu.strasbourg.utils.models.TripsGTFS;
import eu.strasbourg.utils.parser.AgencyParser;
import eu.strasbourg.utils.parser.CalendarDatesParser;
import eu.strasbourg.utils.parser.CalendarParser;
import eu.strasbourg.utils.parser.RoutesParser;
import eu.strasbourg.utils.parser.StopTimesParser;
import eu.strasbourg.utils.parser.StopsParser;
import eu.strasbourg.utils.parser.TripsParser;

public class GTFSLoaderHelper {

	private static String COMMA_SEP = ",";
	private static String AGENCY_FILENAME = "agency.txt";
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
	public static Map<String, AgencyGTFS> readAgencyData(String directory) throws FileAccessException {
		AgencyParser agencyParser = new AgencyParser(AgencyGTFS.class);
		List<AgencyGTFS> agencys = agencyParser.getAll(directory + AGENCY_FILENAME, COMMA_SEP);
		Map<String, AgencyGTFS> mapAgencys = new HashMap<String, AgencyGTFS>();
		for (AgencyGTFS ag : agencys) {
			mapAgencys.put(ag.getAgency_name(), ag);
		}
		return mapAgencys;
	}
	
	/**
	 * Charge toutes les données Calendar en fonction du chemin fourni en paramètre
	 * 
	 * @param directory ex "D:\\MONDOSSIER\\"
	 * @return mapCalendars Une map des donnees Calendar avec comme clef le service_id
	 */
	public static Map<String, CalendarGTFS> readCalendarData(String directory) throws FileAccessException {
		CalendarParser calParser = new CalendarParser(CalendarGTFS.class);
		List<CalendarGTFS> calendars = calParser.getAll(directory + CALENDAR_FILENAME, COMMA_SEP);
		Map<String, CalendarGTFS> mapCalendars = new HashMap<String, CalendarGTFS>();
		for (CalendarGTFS c : calendars) {
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
	public static Map<String, List<CalendarDatesGTFS>> readCalendarDatesData(String directory) throws FileAccessException {
		CalendarDatesParser calDatesParser = new CalendarDatesParser(CalendarDatesGTFS.class);
		List<CalendarDatesGTFS> calDates = calDatesParser.getAll(directory + CALENDAR_DATES_FILENAME, COMMA_SEP);
		Map<String, List<CalendarDatesGTFS>> mapCalendarDates = new HashMap<String, List<CalendarDatesGTFS>>();
		for (CalendarDatesGTFS cal : calDates) {
			if (mapCalendarDates.containsKey(cal.getService_id())) {
				mapCalendarDates.get(cal.getService_id()).add(cal);
			} else {
				List<CalendarDatesGTFS> lst = new ArrayList<CalendarDatesGTFS>();
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
	public static Map<String, RoutesGTFS> readRoutesData(String directory) throws FileAccessException {
		RoutesParser routesParser = new RoutesParser(RoutesGTFS.class);
		Map<String, RoutesGTFS> mapRoutes = new HashMap<String, RoutesGTFS>();
		List<RoutesGTFS> routes = routesParser.getAll(directory + ROUTES_FILENAME, COMMA_SEP);
		for (RoutesGTFS route : routes) {
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
	public static Map<String, List<StopTimesGTFS>> readStopTimesData(String directory) throws FileAccessException {
		StopTimesParser stopTimesParser = new StopTimesParser(StopTimesGTFS.class);
		List<StopTimesGTFS> stopTimes = stopTimesParser.getAll(directory + STOPTIMES_FILENAME, COMMA_SEP);
		Map<String, List<StopTimesGTFS>> mapStopTimes = new HashMap<String, List<StopTimesGTFS>>();
		for (StopTimesGTFS stop : stopTimes) {
			if (mapStopTimes.containsKey(stop.getTrip_id())) {
				mapStopTimes.get(stop.getTrip_id()).add(stop);
			} else {
				List<StopTimesGTFS> lst = new ArrayList<StopTimesGTFS>();
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
	public static Map<String, StopsGTFS> readStopsData(String directory) throws FileAccessException {
		StopsParser stopsParser = new StopsParser(StopsGTFS.class);
		Map<String, StopsGTFS> mapStops = new HashMap<String, StopsGTFS>();
		List<StopsGTFS> stops = stopsParser.getAll(directory + STOPS_FILENAME, COMMA_SEP);
		for (StopsGTFS stop : stops) {
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
	public static Map<String, TripsGTFS> readTripsData(String directory) throws FileAccessException {
		TripsParser tripsParser = new TripsParser(TripsGTFS.class);
		List<TripsGTFS> trips = tripsParser.getAll(directory + TRIPS_FILENAME, COMMA_SEP);
		Map<String, TripsGTFS> mapTrips = new HashMap<String, TripsGTFS>();
		for (TripsGTFS trip : trips) {
			mapTrips.put(trip.getTrip_id(), trip);
		}
		return mapTrips;
	}
	
}
