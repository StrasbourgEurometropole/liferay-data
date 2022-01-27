package eu.strasbourg.webservice.numerique_responsable.constants;

public class WSConstants {

    /** Web service config */
    public static final String APP_GROUP_BASE = "/numerique-responsable-ws";
    public static final String APP_SEARCH_BASE = "/search";
    public static final String APP_CONTACT_BASE = "/contact";
    public static final String APP_SEARCH_NAME = "NUMRESP.Search.Rest";
    public static final String APP_CONTACT_NAME = "NUMRESP.Contact.Rest";

    /** JSON de réponse */
    // Global
    public static final String JSON_RESPONSE = "response";
    public static final String JSON_RESPONSE_CODE = "responseCode";
    public static final String JSON_ERROR_TECHNICAL = "errorTechnical";
    public static final String JSON_ERROR_DESCRIPTION = "errorDescription";
    // Search
    public static final String JSON_WS_NB_RESULT = "nbResult";
    public static final String JSON_WC_TITLE = "title";
    public static final String JSON_WC_SLUG = "slug";
    public static final String JSON_WC_SHORT_DESRIPTION = "shortDescription";
    public static final String JSON_CATEGORIES = "categories";
    public static final String JSON_WC_THUMBNAIL_IMAGE = "thumbnailImage";
    public static final String JSON_WC_PAGINATE = "paginate";
    public static final String JSON_WC_PREVIEW = "preview";
    public static final String JSON_WC_PAGES = "pages";
    public static final String JSON_WC_LABEL = "label";
    public static final String JSON_WC_URL = "URL";
    public static final String JSON_WC_NEXT = "next";

    /** Config */
    // Global
    public static final String GROUP_KEY_NUM_RESP = "site-numerique-responsable";
    public static final String GROUP_KEY_GLOBAL = "20116";
    public static final String STRUCTURE_NEWS = "actualite";
    public static final String STRUCTURE_RESSOURCES = "ressource";

    /** Erreurs */
    // Search
    public static final String ERROR_AUTHENTICATION = "An error occurs during Authentik authentication";

}
