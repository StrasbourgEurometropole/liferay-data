package eu.strasbourg.service.like.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Enumeration des types d'entité pouvant recevoir des like/dislike
 * @author cedric.henry
 */
public enum LikeType {
	PLACE(1, "PLACE"),
	EVENT(2, "EVENT"),
	VIDEO(3, "VIDEO"),
	EDITION(4, "EDITION"),
	IMAGE(5, "IMAGE"),
	NEWS(6, "NEWS"),
	ARTICLE(7, "ARTICLE"),
	PROCEDURE(8, "PROCEDURE"),
	PAGE(9, "PAGE"),
	ACTIVITY(10, "ACTIVITY"),
	COURSE(11, "COURSE"),
	MANIFESTATION(12,"MANIFESTATION"),
	GALLERY(13,"GALLERY"),
	PROJECT(14, "PROJECT"),
	PARTICIPATION(15, "PARTICIPATION"),
	COMMENT(16, "COMMENT"),
	PETITION(17, "PETITION"),
	BUDGET_PARTICIPATIF(18, "BUDGET_PARTICIPATIF"),
	INITIATIVE(19, "INITIATIVE");
	
	private long id;
	private String name;

	LikeType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public static LikeType get(long id) {
		for (LikeType likeType : values()) {
			if (likeType.getId() == id) {
				return likeType;
			}
		}
		return null;
	}
	
	public static List<LikeType> getAll() {
		List<LikeType> likeTypes = new ArrayList<LikeType>();
		for (LikeType likeType : values()) {
			likeTypes.add(likeType);
		}
		return likeTypes;
	}

}
