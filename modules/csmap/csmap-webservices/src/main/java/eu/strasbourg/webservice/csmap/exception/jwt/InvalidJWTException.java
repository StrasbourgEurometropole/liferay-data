package eu.strasbourg.webservice.csmap.exception.jwt;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levé lors de la vérification d'un JWT non valide
 */
public class InvalidJWTException extends Exception {

    public InvalidJWTException() {
        super(WSConstants.ERROR_INVALID_TOKEN);
    }

    public InvalidJWTException(String msg) {
        super(WSConstants.ERROR_INVALID_TOKEN + " : " + msg);
    }

    public InvalidJWTException(String msg, Throwable cause) {
        super(WSConstants.ERROR_INVALID_TOKEN + " : " + msg, cause);
    }

    public InvalidJWTException(Throwable cause) {
        super(WSConstants.ERROR_INVALID_TOKEN, cause);
    }

}
