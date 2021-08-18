package eu.strasbourg.portlet.dynamic_search_asset.constants;

public class Constants {	
	/* formulaire de recherche */
	public static final String SEARCH_FORM_PLACIT = "placit";
	public static final String SEARCH_FORM_STRASBOURG = "strasbourg";

	/* utile pour les atgs de placit */
	public static final String PLACIT_TAG = "participer";

	/* utile pour toutes les entités */
	public static final String ATTRIBUTE_CLASSNAME = "className";
	public static final String ATTRIBUTE_LINK = "link"; // inutile pour événement
	public static final String ATTRIBUTE_TITLE = "title";
	public static final String ATTRIBUTE_CATEGORIES = "categories"; // inutiles pour les vignettes en mode placit
	public static final String ATTRIBUTE_LINK_ABSOLUTE = "linkAbsolute"; // inutiles pour journalArticle et pour les vignettes en mode placit
	public static final String ATTRIBUTE_ID = "id";// inutiles pour élu et pour les vignettes en mode placit

	/* utile pour édition, événement, manif, galerie dédition, activité, projet */
	public static final String ATTRIBUTE_DESCRIPTION = "description";

	/* utile pour journalArticle, vidéo, projet, pétition, initiative */
	public static final String ATTRIBUTE_IMAGE_URL = "imageURL";

	/* utile pour édition, événement, manif */
	public static final String ATTRIBUTE_SCHEDULE = "schedule";

	/* utile pour participation, pétition, budget participatif, initiative */
	public static final String ATTRIBUTE_CREATE_DATE = "createDate";

	/* utile pour vidéo, participation */
	public static final String ATTRIBUTE_NB_LIKES = "nbLikes";
	public static final String ATTRIBUTE_NB_DISLIKES = "nbDislikes";

	/* utile pour participation, budget participatif, initiative */
	public static final String ATTRIBUTE_AUTHOR = "author";
	public static final String ATTRIBUTE_AUTHOR_IMAGE_URL = "authorImageURL";

	/* utile pour élu */
	public static final String ATTRIBUTE_FIRST_NAME = "firstName";
	public static final String ATTRIBUTE_LAST_NAME = "lastName";
	public static final String ATTRIBUTE_FONTION_EURO = "fonctionEuro";
	public static final String ATTRIBUTE_FONCTION_CITY = "fonctionCity";

	/* utile pour événement */
	public static final String ATTRIBUTE_IS_USER_PARTICIPATE = "isUserPart";
	public static final String ATTRIBUTE_LINK_STRAS = "linkStras";
	public static final String ATTRIBUTE_FIRST_DATE = "firstDate";
	public static final String ATTRIBUTE_COMPLETE_ADDRESS = "completeAddress";
	public static final String ATTRIBUTE_NB_PART = "nbPart";

	/* utile pour journalArticle */
	public static final String NEWS_TAG_NAME = "actualite";
	public static final String ARTICLES_TAG_NAME = "article";
	public static final String ATTRIBUTE_CHAPO = "chapo";
	public static final String ATTRIBUTE_MODIFIED_DATE = "modifiedDate";
	public static final String ATTRIBUTE_TYPE = "type";
	public static final String ATTRIBUTE_GROUP_ID = "groupId";

	/* utile pour lieu */
	public static final String ATTRIBUTE_CITY = "city";

	/* utile pour vidéo */
	public static final String ATTRIBUTE_NB_VIEWS = "nbViews";

	/* utile pour participation */
	public static final String ATTRIBUTE_STATUT_CODE = "statusCode";
	public static final String ATTRIBUTE_TYPE_COLOR = "typeColor";
	public static final String ATTRIBUTE_TYPE_LABEL = "typeLabel";
	public static final String ATTRIBUTE_STATUS_DETAIL_LABEL = "statusDetailLabel";

	/* utile pour pétition */
	public static final String ATTRIBUTE_USER_NAME = "userName";
	public static final String ATTRIBUTE_PRO_DUREE_FR = "proDureeFR";
	public static final String ATTRIBUTE_POURCENTAGE_SIGNATURE = "pourcentageSignature";
	public static final String ATTRIBUTE_NOMBRE_SIGNATURE = "nombreSignature";
	public static final String ATTRIBUTE_QUOTA_SIGNATURE = "quotaSignature";

	/* utile pour budget participatif */
	public static final String ATTRIBUTE_BP_STATUT = "BPStatus";
	public static final String ATTRIBUTE_NB_SUPPORTS = "nbSupports";
	public static final String ATTRIBUTE_IS_NOT_DOABLE = "isNotDoable";

	/* utile pour initiative */
	public static final String ATTRIBUTE_NB_HELPS = "nbHelps";

	/* utile pour la constuction des liens */
	public static final String DETAIL_OFFICIAL_URL = "elu/-/entity/id/";
	public static final String DETAIL_EDITION_URL = "edition/-/entity/id/";
	public static final String DETAIL_EVENT_URL = "detail-evenement/-/entity/id/";
	public static final String DETAIL_EVENT_STRAS_URL = "evenement/-/entity/id/";
	public static final String DETAIL_MANIF_URL = "manifestation/-/entity/id/";
	public static final String DETAIL_GALERIE_URL = "galerie-editions/-/entity/id/";
	public static final String DETAIL_PLACE_URL = "lieu/-/entity/sig/";
	public static final String DETAIL_COURSE_URL = "cours/-/entity/id/";
	public static final String DETAIL_ACTIVITY_URL = "activite/-/entity/id/";
	public static final String DETAIL_VIDEO_URL = "detail-video/-/entity/id/";
	public static final String DETAIL_PARTICIPATION_URL = "detail-participation/-/entity/id/";
	public static final String DETAIL_PETITION_URL = "detail-petition/-/entity/id/";
	public static final String DETAIL_BUDGET_PARTICIPATIF_URL = "detail-budget-participatif/-/entity/id/";
	public static final String DETAIL_INITIATIVE_URL = "detail-initiative/-/entity/id/";
}
