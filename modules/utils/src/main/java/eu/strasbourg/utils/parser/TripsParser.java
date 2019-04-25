package eu.strasbourg.utils.parser;

import eu.strasbourg.utils.models.Trips;

public class TripsParser extends CSVParser<Trips> {

	public TripsParser(Class<Trips> className) {
		super(className);
	}
	
}
