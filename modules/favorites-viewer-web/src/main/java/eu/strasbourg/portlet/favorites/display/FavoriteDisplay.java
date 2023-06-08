package eu.strasbourg.portlet.favorites.display;

import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * @author jeremy.zwickert
 *
 */
public class FavoriteDisplay {
	
	private String publikUserId;
	private String title;
	private long entityId;
	private long typeId;
	private String typeName;
	private String url;
	private long groupId;
	private long favoriteId;

	private Place place;
	private Event event;
	private Video video;
	private Edition edition;
	private DLFileEntry image;
	private JournalArticle news;
	private JournalArticle article;
	private String procedure;
	private Layout page;
	private Activity activity;
	private ActivityCourse course;
	private Manifestation manifestation;
	private EditionGallery gallery;
	private Arret arret;
	
	private String newsLabel;
	
	public FavoriteDisplay(Favorite favorite, String publikUserId, ThemeDisplay themeDisplay){
		this.publikUserId = publikUserId;
		this.title = favorite.getTitle();
		this.url =favorite.getUrl();
		this.entityId = favorite.getEntityId();
		this.typeId = favorite.getTypeId();		
		this.typeName= FavoriteType.get(favorite.getTypeId()).getName();
		this.groupId = favorite.getEntityGroupId();
		this.favoriteId = favorite.getFavoriteId();

		if(favorite.getAssetEntry() != null){
			if(favorite.getTypeId() == FavoriteType.PLACE.getId())
				this.place = (Place) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
			else if(favorite.getTypeId() == FavoriteType.EVENT.getId())
				this.event = (Event) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
//			else if(favorite.getTypeId() == FavoriteType.VIDEO.getId())
//				this.video = (Video) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
			else if(favorite.getTypeId() == FavoriteType.EDITION.getId())
				this.edition = (Edition) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
//			else if(favorite.getTypeId() == FavoriteType.IMAGE.getId())
//				this.image = (DLFileEntry) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
//			else if(favorite.getTypeId() == FavoriteType.ARTICLE.getId())
//				this.article = (JournalArticle) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
//			else if(favorite.getTypeId() == FavoriteType.PROCEDURE.getId())
//				this.procedure = (String) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
//			else if(favorite.getTypeId() == FavoriteType.PAGE.getId())
//				this.page = (Layout) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
			else if(favorite.getTypeId() == FavoriteType.ACTIVITY.getId())
				this.activity = (Activity) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
			else if(favorite.getTypeId() == FavoriteType.COURSE.getId())
				this.course = (ActivityCourse) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
			else if(favorite.getTypeId() == FavoriteType.MANIFESTATION.getId())
				this.manifestation = (Manifestation) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
			else if(favorite.getTypeId() == FavoriteType.GALLERY.getId())
				this.gallery = (EditionGallery) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
			else if(favorite.getTypeId() == FavoriteType.ARRET.getId())
				this.arret = (Arret) favorite.getAssetEntry().getAssetRenderer().getAssetObject();
		}
		if(favorite.getTypeId() == FavoriteType.NEWS.getId())
		{
			JournalArticle article = JournalArticleLocalServiceUtil.fetchLatestArticle(favorite.getEntityGroupId(), String.valueOf(favorite.getEntityId()),WorkflowConstants.STATUS_APPROVED);
			if (article!= null) {
				this.news = article;

				AssetEntry newsEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(), article.getResourcePrimKey());
				List<AssetCategory> newsTypes = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(newsEntry, "type d'actualite");
				String label = "";
				for (AssetCategory typeCategory : newsTypes) {
					if (label.length() > 0) {
						label += ", ";
					}
					label += typeCategory.getTitle(themeDisplay.getLocale());
				}
				this.newsLabel = label;
			}
		}
		
	}
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPublikUserId() {
		return publikUserId;
	}
	public void setPublikUserId(String publikUserId) {
		this.publikUserId = publikUserId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public Edition getEdition() {
		return edition;
	}
	public void setEdition(Edition edition) {
		this.edition = edition;
	}
	public DLFileEntry getImage() {
		return image;
	}
	public void setImage(DLFileEntry image) {
		this.image = image;
	}
	public JournalArticle getNews() {
		return news;
	}
	public void setNews(JournalArticle news) {
		this.news = news;
	}
	public JournalArticle getArticle() {
		return article;
	}
	public void setArticle(JournalArticle article) {
		this.article = article;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public Layout getPage() {
		return page;
	}
	public void setPage(Layout page) {
		this.page = page;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public ActivityCourse getCourse() {
		return course;
	}
	public void setCourse(ActivityCourse course) {
		this.course = course;
	}
	public Manifestation getManifestation() {
		return manifestation;
	}
	public void setManifestation(Manifestation manifestation) {
		this.manifestation = manifestation;
	}
	public EditionGallery getGallery() {
		return gallery;
	}
	public void setGallery(EditionGallery gallery) {
		this.gallery = gallery;
	}
	public Arret getArret() {
		return arret;
	}
	public void setArret(Arret gallery) {
		this.arret = arret;
	}
	public String getNewsLabel() {
		return newsLabel;
	}
	public void setNewsLabel(String newsLabel) {
		this.newsLabel = newsLabel;
	}
	public long getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(long favoriteId) {
		this.favoriteId = favoriteId;
	}

	
}
