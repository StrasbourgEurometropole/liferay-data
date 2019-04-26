package eu.strasbourg.utils.parser;

import eu.strasbourg.utils.models.StopTimesGTFS;

public class StopTimesParser extends CSVParser<StopTimesGTFS> {

	public StopTimesParser(Class<StopTimesGTFS> className) {
		super(className);
	}
	
}
