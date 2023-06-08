package eu.strasbourg.portlet.graveyard.portlet.mapping;

import java.util.ArrayList;
import java.util.List;

public enum GraveyardMapping {
	NORD(1, "418_FUN_8", "Cimeti&egrave;re Nord"), 
	SUD(2, "423_FUN_13", "Cimeti&egrave;re Sud"), 
	OUEST(3, "412_FUN_2", "Cimeti&egrave;re Ouest"), 
	POLYGONE(4, "419_FUN_9", "Cimeti&egrave;re Polygone"), 
	SAINT_URBAIN(5, "422_FUN_12", "Cimeti&egrave;re Saint-Urbain"), 
	SAINT_GALL(6, "420_FUN_10", "Cimeti&egrave;re Saint-Gall"),
	SAINTE_HELENE(7, "414_FUN_4", "Cimeti&egrave;re Sainte-H&eacute;l&egrave;ne"), 
	SAINT_LOUIS(8, "421_FUN_11", "Cimeti&egrave;re Saint-Louis"),
	MUSULMAN(9, "413_FUN_3", "Cimeti&egrave;re Musulman");

	private long id;
	private String sigId;
	private String name;

	GraveyardMapping(int id, String sigId, String name) {
		this.id = id;
		this.sigId = sigId;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSigId() {
		return sigId;
	}

	public void setSigId(String sigId) {
		this.sigId = sigId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static GraveyardMapping get(long id) {
		for (GraveyardMapping e : values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public static List<GraveyardMapping> getAll() {
		List<GraveyardMapping> graveyards = new ArrayList<GraveyardMapping>();
		for (GraveyardMapping e : values()) {
			graveyards.add(e);
			}		
		return graveyards;
	}
}
