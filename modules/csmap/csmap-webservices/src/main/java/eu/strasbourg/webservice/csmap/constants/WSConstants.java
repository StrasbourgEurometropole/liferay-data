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

    /** Publik */
    public static final String REDIRECT_URI = "http://localhost:8080/static.html";
    public static final String ID_TOKEN = "id_token";
    public static final String SUB = "sub";

    /** Token config */
    public static final int TOKEN_LENGTH = 255;
    public static final int JWT_VALIDITY_SECONDS = 3600;

    /** JSON de réponse */
    // Global
    public static final String JSON_RESPONSE_CODE = "responseCode";
    public static final String JSON_ERROR_DESCRIPTION = "errorDescription";

    // Auth
    public static final String JSON_REFRESH_TOKEN = "refreshToken";
    public static final String JSON_JWT_CSM = "JWT_CSM";

    // Place
    public static final String JSON_ADD = "ADD";
    public static final String JSON_UPDATE = "UPDATE";
    public static final String JSON_DELETE = "DELETE";

    // Profil
    public static final String JSON_LAST_NAME = "lastname";
    public static final String JSON_FIRST_NAME = "firstname";
    public static final String JSON_EMAIL = "email";
    public static final String JSON_ADDRESS = "address";
    public static final String JSON_POSTAL_CODE = "postalCode";
    public static final String JSON_CITY = "city";
    public static final String JSON_IMAGE_URL = "imageURL";

    /** Erreurs */
    // Auth
    public static final String ERROR_INVALID_TOKEN = "Invalid token receives during authentication : ";
    public static final String ERROR_AUTHENTICATION = "An error occurs during Authentik authentication : ";
    public static final String ERROR_REFRESH_TOKEN_VALIDATION_FAILED = "An error occurs during refresh token validation : ";
    public static final String ERROR_REFRESH_TOKEN_INVALID = "Refresh token is not longer valid : ";
    public static final String ERROR_REFREH_TOKEN_CREATION = "An error occurs during refresh token creation : : ";

}
