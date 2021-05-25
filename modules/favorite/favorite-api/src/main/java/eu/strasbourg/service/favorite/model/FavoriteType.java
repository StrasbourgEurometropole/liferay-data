package eu.strasbourg.service.favorite.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.model.Layout;

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.video.model.Video;

public enum FavoriteType {
	PLACE(1, "PLACE", Place.class,true, true),
	EVENT(2, "EVENT", Event.class,true, true),
	VIDEO(3, "VIDEO", Video.class,false, true),
	EDITION(4, "EDITION", Edition.class,false, true),
	IMAGE(5, "IMAGE", DLFileEntry.class,false, true),
	NEWS(6, "NEWS", JournalArticle.class,false, true),
	ARTICLE(7, "ARTICLE", JournalArticle.class,false, true),
	PROCEDURE(8, "PROCEDURE", String.class,false, true),
	PAGE(9, "PAGE", Layout.class,false, true),
	ACTIVITY(10, "ACTIVITY", Activity.class,false, true),
	COURSE(11, "COURSE", ActivityCourse.class,false, true),
	MANIFESTATION(12,"MANIFESTATION", Manifestation.class,true, true),
	GALLERY(13,"GALLERY", EditionGallery.class,false, true),
	ARRET(14,"ARRET", Arret.class,true, true),
	SEARCH(101,"SEARCH", null, true, false),
	FILTER(102,"FILTER", null, true, false);


	private long id;
	private String name;
	private Class<?> favoriteClass;
	private boolean isLiferay;
	private boolean isCSMap;

	FavoriteType(int id, String name, Class<?> favoriteClass, boolean isCSMap, boolean isLiferay) {
		this.id = id;
		this.name = name;
		this.favoriteClass = favoriteClass;
		this.isCSMap = isCSMap;
		this.isLiferay = isLiferay;

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

	public boolean getIsLiferay() {
		return isLiferay;
	}

	public void setIsLiferay(boolean isLiferay) {
		this.isLiferay = isLiferay;
	}

	public boolean getIsCSMap() {
		return isCSMap;
	}

	public void setIsCSMap(boolean isCSMap) {
		this.isCSMap = isCSMap;
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

	public static List<Long>getAllIsLiferayIds(){
		List<FavoriteType> favoritesTypes = getAll();
		List<FavoriteType> favoritesTypesIsLiferay = new ArrayList<>(favoritesTypes.stream().filter(f -> f.getIsLiferay()).collect(Collectors.toList()));
		List<Long> favoritesTypeIsLiferayIds = new ArrayList<>();
		for(FavoriteType favoriteType : favoritesTypesIsLiferay){
			favoritesTypeIsLiferayIds.add(favoriteType.getId());
		}
		return favoritesTypeIsLiferayIds;
	}

	public static List<Long>getAllIsCSMapIds(){
		List<FavoriteType> favoritesTypes = getAll();
		List<FavoriteType> favoritesTypesIsCSMap = new ArrayList<>(favoritesTypes.stream().filter(f -> f.getIsCSMap()).collect(Collectors.toList()));
		List<Long> favoritesTypeIsCSMapIds = new ArrayList<>();
		for(FavoriteType favoriteType : favoritesTypesIsCSMap){
			favoritesTypeIsCSMapIds.add(favoriteType.getId());
		}
		return favoritesTypeIsCSMapIds;
	}
}
