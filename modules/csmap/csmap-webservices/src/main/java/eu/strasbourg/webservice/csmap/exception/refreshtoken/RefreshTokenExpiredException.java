package eu.strasbourg.webservice.csmap.exception.refreshtoken;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levé lors de la vérification d'un refresh token expiré
 */
public class RefreshTokenExpiredException extends Exception {

    public RefreshTokenExpiredException() {
        super(WSConstants.ERROR_REFRESH_TOKEN_EXPIRED);
    }

    public RefreshTokenExpiredException(String msg) {
        super(WSConstants.ERROR_REFRESH_TOKEN_EXPIRED + " : " + msg);
    }

    public RefreshTokenExpiredException(String msg, Throwable cause) {
        super(WSConstants.ERROR_REFRESH_TOKEN_EXPIRED + " : " + msg, cause);
    }

    public RefreshTokenExpiredException(Throwable cause) {
        super(WSConstants.ERROR_REFRESH_TOKEN_EXPIRED, cause);
    }

}
