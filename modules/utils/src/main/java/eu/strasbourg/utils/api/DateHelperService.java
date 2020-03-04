package eu.strasbourg.utils.api;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public interface DateHelperService {
	
    /**
     * Transforme un String en Date
     */
    public Date convertStringToDate(String date, String format);

    /**
     * Affichage cour d'une date en multilangue
     */
    public String displayShortDate(Date date, Locale locale);

    /**
     * Affichage long d'une date en multilangue
     */
    public String displayLongDate(Date date, Locale locale);
    
    /**
     * Fournit une chaine représentant une date
     */
	public String getShortDateFormat(Date dateTime);

	/**
	 * Fournit une chaine representant une date
	 */
	public Date getShortDateFormatFromString(String value);
	
	/**
	 * Converti une chaine en provenance du fichier GTFS en Date
	 */
	public Date getDateFromGTFSCalendar(String calendarGTFSDate) throws ParseException;
    
}
