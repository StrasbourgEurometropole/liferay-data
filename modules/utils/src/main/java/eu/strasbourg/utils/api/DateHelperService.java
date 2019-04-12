package eu.strasbourg.utils.api;

import java.util.Date;
import java.util.Locale;

public interface DateHelperService {
    /**
     * Affichage cour d'une date en multilangue
     */
    public String displayShortDate(Date date, Locale locale);


    /**
     * Affichage long d'une date en multilangue
     */
    public String displayLongDate(Date date, Locale locale);
}
