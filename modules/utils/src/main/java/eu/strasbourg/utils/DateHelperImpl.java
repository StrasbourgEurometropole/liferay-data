package eu.strasbourg.utils;

import eu.strasbourg.utils.api.DateHelperService;
import eu.strasbourg.utils.api.FileEntryHelperService;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Implémentation du service DateHelper N'est qu'une couche
 * accessible par les templates FreeMarker qui délègue le travail à une classe
 * helper
 */
@Component(
		immediate = true,
		property = {},
		service = DateHelperService.class)
public class DateHelperImpl implements DateHelperService {

	@Override
	public Date convertStringToDate(String date, String format) {
		return DateHelper.convertStringToDate(date, format);
	}

	@Override
	public String displayShortDate(Date date, Locale locale) {
		return DateHelper.displayShortDate(date, locale);
	}


	@Override
	public String displayLongDate(Date date, Locale locale) {
		return DateHelper.displayLongDate(date, locale);
	}
}
