package eu.strasbourg.service.like.model;

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
import eu.strasbourg.service.like.model.LikeType;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.comment.model.Comment;

/**
 * Enumeration des types d'entité pouvant recevoir des like/dislike
 * @author cedric.henry
 */
public enum LikeType {
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
	GALLERY(13,"GALLERY", EditionGallery.class),
	PROJECT(14, "PROJECT", Project.class),
	PARTICIPATION(15, "PARTICIPATION", Participation.class),
	COMMENT(16, "COMMENT", Comment.class);
	
	private long id;
	private String name;
	private Class<?> likeClass;

	LikeType(int id, String name, Class<?> likeClass) {
		this.id = id;
		this.name = name;
		this.likeClass = likeClass;
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

	public Class<?> getLikeClass() {
		return likeClass;
	}

	public void setLikeClass(Class<?> likeClass) {
		this.likeClass = likeClass;
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
