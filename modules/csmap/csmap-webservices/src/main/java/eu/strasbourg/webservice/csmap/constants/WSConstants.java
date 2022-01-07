package eu.strasbourg.webservice.csmap.constants;

public class WSConstants {

    /** Web service config */
    public static final String APP_GROUP_BASE = "/csmap-ws";
    public static final String APP_AUTH_BASE = "/auth";
    public static final String APP_AUTH_NAME = "CSMAP.Auth.Rest";
    public static final String APP_PLACE_BASE = "/place";
    public static final String APP_PLACE_NAME = "CSMAP.Place.Rest";
    public static final String APP_PROFILE_BASE = "/profile";
    public static final String APP_PROFILE_NAME = "CSMAP.Profile.Rest";
    public static final String APP_ACCOUNT_DATA_BASE = "/account-data";
    public static final String APP_ACCOUNT_DATA_NAME = "CSMAP.Account.Data.Rest";
    public static final String APP_VARIOUS_DATA_BASE = "/various-data";
    public static final String APP_VARIOUS_DATA_NAME = "CSMAP.Various.Data.Rest";
    public static final String APP_SETTINGS_BASE = "/settings";
    public static final String APP_SETTINGS_NAME = "CSMAP.Settings.Data.Rest";
    public static final String APP_EVENT_BASE = "/event";
    public static final String APP_EVENT_NAME = "CSMAP.Event.Rest";
    public static final String APP_FAVORITE_BASE = "/favorite";
    public static final String APP_FAVORITE_NAME = "CSMAP.Favorite.Rest";
    public static final String APP_TRANSPORT_BASE = "/transport";
    public static final String APP_TRANSPORT_NAME = "CSMAP.Transport.Rest";


    /** Publik */
    public static final String REDIRECT_URI = "http://localhost:8080/static.html";
    public static final String ID_TOKEN = "id_token";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String SUB = "sub";
    public static final String NONCE = "nonce";
    public static final String GIVEN_NAME = "given_name";
    public static final String FAMILY_NAME = "family_name";
    public static final String EMAIL = "email";
    public static final String ACCORD_PLACIT = "accord_placit";
    public static final String LISTING_PLACIT = "accord_placit_listing";

    /** Token config */
    public static final int TOKEN_LENGTH = 255;
    public static final int JWT_VALIDITY_SECONDS = 3600;
    public static final int JWT_VALIDITY_LEEWAY = 10;
    public static final String JWT_HEADER_NAME = "JWT_CSM";

    /** Paramètres */
    // Place
    public static final String PARAM_IDS_CATEGORY = "ids_category";
    public static final String PARAM_LAST_UPDATE_TIME_DEFAULT = "2000-01-01";

    /** JSON de réponse */
    // Global
    public static final String JSON_RESPONSE = "response";
    public static final String JSON_RESPONSE_CODE = "responseCode";
    public static final String JSON_ERROR_DESCRIPTION = "errorDescription";
    public static final String JSON_LANGUAGE_FRANCE = "fr_FR";
    public static final String JSON_LANGUAGE_GERMANY = "de_DE";
    public static final String JSON_LANGUAGE_US = "en_US";
    // Auth
    public static final String JSON_REFRESH_TOKEN = "refreshToken";
    public static final String JSON_JWT_CSM = "JWT_CSM";
    public static final String JSON_BASE_NONCE = "baseNonce";
    public static final int BASE_NONCE_LENGTH = 36;
    public static final int BASE_NONCE_VALIDITY_SECONDS = 1800;
    // Place
    public static final String JSON_ADD = "ADD";
    public static final String JSON_UPDATE = "UPDATE";
    public static final String JSON_DELETE = "DELETE";
    public static final String JSON_PLACE_NAME = "name";
    public static final String JSON_PLACE_OPDENDATA_URL= "openDataURL";
    public static final String JSON_PLACE_TYPES= "types";
    // Place catégories
    public static final String JSON_CATEG_ID = "id";
    public static final String JSON_PARENT_ID = "parentId";
    public static final String JSON_NAME = "name";
    public static final String JSON_PICTO = "picto";
    public static final String JSON_PICTO_URL = "pictoURL";
    public static final String JSON_MAJ = "maj";
    // Profil
    public static final String JSON_LAST_NAME = "lastname";
    public static final String JSON_FIRST_NAME = "firstname";
    public static final String JSON_EMAIL = "email";
    public static final String JSON_ADDRESS = "address";
    public static final String JSON_POSTAL_CODE = "postalCode";
    public static final String JSON_CITY = "city";
    public static final String JSON_IMAGE_URL = "imageURL";
    public static final String JSON_DISTRICT = "district";
    // Account-Data
    public static final String JSON_TITLE = "title";
    public static final String JSON_STATUS = "status";
    public static final String JSON_URL = "url";
    // Various-Data
    public static final String JSON_WC_ID = "id";
    public static final String JSON_DATE = "date";
    public static final String JSON_WC_TITLE = "title";
    public static final String JSON_WC_ORDER = "order";
    public static final String JSON_WC_NUMBER = "number";
    public static final String JSON_WC_COLOR = "color";
    public static final String JSON_WC_FONT_COLOR = "fontColor";
    public static final String JSON_WC_BACKGROUND_COLOR = "backgroundColor";
    public static final String JSON_WC_CATEGORY_ID = "categoryId";
    public static final String JSON_WC_CATEGORY_ORDER = "categoryOrder";
    public static final String JSON_WC_CATEGORY_TITLE = "categoryTitle";
    public static final String JSON_WC_CATEGORY_CONTENT = "categoryContent";
    public static final String JSON_WC_EMERGENCY_NUMBERS = "emergencyNumbers";
    public static final String JSON_WC_EMERGENCY_HELPS = "emergencyHelps";
    public static final String JSON_WC_CONTENT = "content";
    public static final String JSON_WC_TEXT = "text";
    public static final String JSON_SUBTITLE = "subtitle";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_WC_URL = "url";
    public static final String JSON_WC_PICTO = "picto";
    // Settings
    public static final String JSON_SETTINGS_REPORTURL = "reportUrl";
    public static final String JSON_SETTINGS_CODE = "code";
    public static final String JSON_SETTINGS_ISACTIVE = "isActive";
    public static final String TOPIC_ALERTE = "alerte";
    public static final String JSON_SETTINGS_VERSION = "version";
    // Agenda
    public static final String JSON_AGENDA_PRINCIPAL = "principal";
    public static final String JSON_AGENDA_THEMATIQUE = "thematique";
    public static final String JSON_IDS = "ids";


    /** Config */
    // Global
    public static final String GROUP_KEY_CSMAP = "CSMAP";

    // Place
    public static final String FOLDER_POI_SIMPLE = "POI simple";
    public static final String STRUCTURE_POI_SIMPLE = "POI simple";

    // Various-Data
    public static final String GROUP_KEY_STRAS = "Strasbourg.eu";
    public static final String TAG_CSMAP = "csmap";
    public static final String STRUCTURE_BREVE = "Breve";
    public static final String STRUCTURE_SOCIAL_NETWORK = "R\u00e9seaux sociaux";
    public static final String STRUCTURE_EMERGENCY_NUMBER = "Num\u00e9ro d'urgence";
    public static final String STRUCTURE_EMERGENCY_HELP = "Aide d'urgence";
    public static final String FOLDER_DIVERS = "Divers";
    public static final String FOLDER_EMERGENCY_NUMBERS = "Num\u00e9ros urgence";
    public static final String FOLDER_EMERGENCY_HELPS = "Aides urgence";
    public static final String FOLDER_SOCIAL_NETWORK = "R\u00e9seaux sociaux";
    public static final String GENERAL_CONDITIONS = "Mentions l\u00e9gales";
    public static final String ACCESSIBILITY = "Accessibilit\u00e9";

    // Settings
    public static final String FOLDER_PARAMETRAGE = "Param\u00e9trage";
    public static final String STRUCTURE_SIGNALEMENT = "Signalement";
    public static final String STRUCTURE_STATUT_MODULES = "Statut des modules";
    public static final String STRUCTURE_LAST_VERSION = "Derni\u00e8re version recommand\u00e9e";

    /** Erreurs */
    // Global
    public static final String ERROR_NO_JWT_IN_HEADER = "No JWT in header";
    public static final String ERROR_INVALID_TOKEN = "Invalid token received during authentication";
    public static final String ERROR_NO_SUB_IN_JWT = "No sub in JWT";
    public static final String ERROR_SUB_NOT_RECOGNISE = "Sub not recognise";
    // Auth
    public static final String ERROR_AUTHENTICATION = "An error occurs during Authentik authentication";
    public static final String ERROR_REFRESH_TOKEN_INVALID = "Refresh token invalid";
    public static final String ERROR_REFRESH_TOKEN_EXPIRED = "Refresh token is no longer valid";
    public static final String ERROR_REFREH_TOKEN_CREATION = "An error occurs during refresh token creation";
    public static final String ERROR_BASE_NONCE_CREATION = "An error occurs during refresh token creation";
    public static final String ERROR_BASE_NONCE_EXPIRED = "Base Nonce is no longer valid";
    public static final String ERROR_NO_SUCH_BASE_NONCE = "No such Base Nonce in database";
    public static final String ERROR_NO_CODE_VERIFIER = "No code verifier in parameter";
    public static final String ERROR_INVALID_NONCE = "Invalid nonce received during authentication";
    // Place
    public static final String ERROR_NO_DEFAULT_PICTO = "No default picto available for this application";

}
