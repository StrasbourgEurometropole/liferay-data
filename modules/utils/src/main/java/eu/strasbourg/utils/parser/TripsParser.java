package eu.strasbourg.utils.parser;

import eu.strasbourg.utils.models.TripsGTFS;

public class TripsParser extends CSVParser<TripsGTFS> {

	public TripsParser(Class<TripsGTFS> className) {
		super(className);
	}
	
}
