package eu.strasbourg.utils.models;

import eu.strasbourg.utils.exception.FileFormatException;

/**
 * Defini la base des elements issus du GTFS
 */
public interface GTFSModel {
	
	public int getNbEntry();
	public void fromStringArray(String[] value) throws FileFormatException;
	
}