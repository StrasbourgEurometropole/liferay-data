package eu.strasbourg.utils.parser;

import eu.strasbourg.utils.models.AgencyGTFS;

public class AgencyParser extends CSVParser<AgencyGTFS> {

	public AgencyParser(Class<AgencyGTFS> className) {
		super(className);
	}
	
}
