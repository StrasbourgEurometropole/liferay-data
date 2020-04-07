package eu.strasbourg.utils.parser;

import eu.strasbourg.utils.models.StopsGTFS;

public class StopsParser extends CSVParser<StopsGTFS> {
	
	public StopsParser(Class<StopsGTFS> className) {
		super(className);
	}

}
