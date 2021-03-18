package eu.strasbourg.webservice.csmap.exception.jwt;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levé quand il n'existe pas de JWT dans le header quand une authentification est requise
 */
public class NoJWTInHeaderException extends Exception {

    public NoJWTInHeaderException() {
        super(WSConstants.ERROR_NO_JWT_IN_HEADER);
    }

    public NoJWTInHeaderException(String msg) {
        super(WSConstants.ERROR_NO_JWT_IN_HEADER + " : " + msg);
    }

    public NoJWTInHeaderException(String msg, Throwable cause) {
        super(WSConstants.ERROR_NO_JWT_IN_HEADER + " : " + msg, cause);
    }

    public NoJWTInHeaderException(Throwable cause) {
        super(WSConstants.ERROR_NO_JWT_IN_HEADER, cause);
    }

}
