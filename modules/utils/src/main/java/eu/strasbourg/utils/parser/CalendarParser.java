package eu.strasbourg.utils.parser;

import eu.strasbourg.utils.models.CalendarGTFS;

public class CalendarParser extends CSVParser<CalendarGTFS> {

	public CalendarParser(Class<CalendarGTFS> className) {
		super(className);
	}
	
}
