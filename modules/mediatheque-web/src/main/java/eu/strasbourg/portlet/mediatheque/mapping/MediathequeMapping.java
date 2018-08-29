package eu.strasbourg.portlet.mediatheque.mapping;

import java.util.ArrayList;
import java.util.List;

public enum MediathequeMapping {
	ADV("ADV","Document audio-visuel"),
	ALB("ALB","Albums jeunes"),
	ALS("ALS","Alsatique"),
	AUT("AUT","Patrimoine"),
	BDA("BDA","BD adultes"),
	BDJ("BDJ","BD jeunes"),
	CDA("CDA","Disques compacts"),
	CDJ("CDJ","Disques compacts jeunes"),
	CSP("CSP","Consultation sur Place"),
	DOA("DOA","Documentaires Adultes"),
	DOJ("DOJ","Documentaires Jeunes"),
	DVD12("DVD12","DVD -12 ans"),
	DVD16("DVD16","DVD -16 ans"),
	DVDAD("DVDAD","DVD Adultes Document"),
	DVDAF("DVDAF","DVD Adultes Fiction"),
	DVDJD("DVDJD","DVD Jeunes Document"),
	DVDJF("DVDJF","DVD Jeunes Fiction"),
	DVDRCA("DVDRCA","DVD-ROM consultables adultes"),
	DVDRCJ("DVDRCJ","DVD-ROM consultables jeunes"),
	DVDREA("DVDREA","DVD-ROM empruntables adultes"),
	DVDREJ("DVDREJ","DVD-ROM empruntables jeunes"),
	FIA("FIA","Fictions adultes"),
	FIJ("FIJ","Fictions jeunes"),
	FORM("FORM","Formation"),
	LEA("LEA","Livres à écouter adultes"),
	LEJ("LEJ","Livres à écouter jeunes"),
	MLA("MLA","Méthodes de langues adultes"),
	MLJ("MLJ","Méthodes de langues jeunes"),
	M33T("M33T","Microsillons"),
	NE("NE","Non pretable"),
	OAD("OAD","Artothèque dessin"),
	OAE("OAE","Artothèque estampe"),
	OAN("OAN","Artothèque numérique"),
	OAP("OAP","Artothèque photo"),
	ORA("ORA","Ouvrage de Référence Adultes"),
	ORJ("ORJ","Ouvrage de Référence jeunes"),
	PAR("PAR","partitions"),
	PEA("PEA","Périodiques adultes"),
	PEJ("PEJ","Périodiques jeunes"),
	RCA("RCA","CD-ROM consultables adultes"),
	RCJ("RCJ","CD-ROM consultables jeunes"),
	REA("REA","CD-ROM empruntables adultes"),
	REJ("REJ","CD-ROM empruntables jeunes");
	
	private String code;
	private String name;

	MediathequeMapping(String code, String name) {
		this.code = code;
		this.name = name;
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

	public static MediathequeMapping get(String code) {
		for (MediathequeMapping e : values()) {
			if (e.getCode().equals(code)) {
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
