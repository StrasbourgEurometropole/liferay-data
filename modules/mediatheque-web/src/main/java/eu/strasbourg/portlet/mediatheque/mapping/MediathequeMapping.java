package eu.strasbourg.portlet.mediatheque.mapping;

import java.util.ArrayList;
import java.util.List;

public enum MediathequeMapping {
	FIA(1, "FIA", "Fiche d'Information Applicative"), 
	DVDAF(2, "DVDAF", "DVD AF"), 
	DOA(3, "DOA", "(Dauphine On Air), webradio de l'Université Paris-Dauphine"), 
	BDJ(4, "BDJ", "Bande dessinée jeunesse"),
	ALB(5,"ALB", "Album");

	private long id;
	private String code;
	private String name;

	MediathequeMapping(int id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static MediathequeMapping get(long id) {
		for (MediathequeMapping e : values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public static List<MediathequeMapping> getAll() {
		List<MediathequeMapping> mediatheques = new ArrayList<MediathequeMapping>();
		for (MediathequeMapping e : values()) {
			mediatheques.add(e);
			}		
		return mediatheques;
	}
}
