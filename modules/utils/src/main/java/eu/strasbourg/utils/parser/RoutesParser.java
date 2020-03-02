package eu.strasbourg.utils.parser;

import eu.strasbourg.utils.models.RoutesGTFS;

public class RoutesParser extends CSVParser<RoutesGTFS> {

	public RoutesParser(Class<RoutesGTFS> className) {
		super(className);
	}
	
}
