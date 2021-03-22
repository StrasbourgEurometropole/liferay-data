package eu.strasbourg.webservice.csmap.exception.auth;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levé lors d'un problème lors de la génération d'un refreshToken
 */
public class RefreshTokenCreationFailedException extends Exception {

    public RefreshTokenCreationFailedException() {
        super(WSConstants.ERROR_REFREH_TOKEN_CREATION);
    }

    public RefreshTokenCreationFailedException(String msg) {
        super(WSConstants.ERROR_REFREH_TOKEN_CREATION + " : " + msg);
    }

    public RefreshTokenCreationFailedException(String msg, Throwable cause) {
        super(WSConstants.ERROR_REFREH_TOKEN_CREATION + " : " + msg, cause);
    }

    public RefreshTokenCreationFailedException(Throwable cause) {
        super(WSConstants.ERROR_REFREH_TOKEN_CREATION, cause);
    }

}
