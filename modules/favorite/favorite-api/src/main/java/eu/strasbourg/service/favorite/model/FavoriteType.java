package eu.strasbourg.service.favorite.model;

import java.util.ArrayList;
import java.util.List;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.model.Layout;

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.video.model.Video;

public enum FavoriteType {
	PLACE(1, "PLACE", Place.class), 
	EVENT(2, "EVENT", Event.class), 
	VIDEO(3, "VIDEO", Video.class), 
	EDITION(4, "EDITION", Edition.class), 
	IMAGE(5, "IMAGE", DLFileEntry.class), 
	NEWS(6, "NEWS", JournalArticle.class),
	ARTICLE(7, "ARTICLE", JournalArticle.class), 
	PROCEDURE(8, "PROCEDURE", String.class),
	PAGE(9, "PAGE", Layout.class),
	ACTIVITY(10, "ACTIVITY", Activity.class),
	COURSE(11, "COURSE", ActivityCourse.class),
	MANIFESTATION(12,"MANIFESTATION", Manifestation.class),
	GALLERY(13,"GALLERY", EditionGallery.class);

	private long id;
	private String name;
	private Class<?> favoriteClass;

	FavoriteType(int id, String name, Class<?> favoriteClass) {
		this.id = id;
		this.name = name;
		this.favoriteClass = favoriteClass;
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

	public Class<?> getFavoriteClass() {
		return favoriteClass;
	}

	public void setFavoriteClass(Class<?> favoriteClass) {
		this.favoriteClass = favoriteClass;
	}

	public static FavoriteType get(long id) {
		for (FavoriteType e : values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public static List<FavoriteType> getAll() {
		List<FavoriteType> favoritesType = new ArrayList<FavoriteType>();
		for (FavoriteType e : values()) {
			favoritesType.add(e);
			}		
		return favoritesType;
	}
}
