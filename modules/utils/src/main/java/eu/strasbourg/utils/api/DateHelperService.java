package eu.strasbourg.utils.api;

import eu.strasbourg.utils.DateHelper;

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
}
