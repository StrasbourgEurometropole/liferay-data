package eu.strasbourg.webservice.csmap.exception.jwt;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levé quand il n'existe pas de JWT dans le header quand une authentification est requise
 */
public class NoSubInJWTException extends Exception {

    public NoSubInJWTException() {
        super(WSConstants.ERROR_NO_SUB_IN_JWT);
    }

    public NoSubInJWTException(String msg) {
        super(WSConstants.ERROR_NO_SUB_IN_JWT + " : " + msg);
    }

    public NoSubInJWTException(String msg, Throwable cause) {
        super(WSConstants.ERROR_NO_SUB_IN_JWT + " : " + msg, cause);
    }

    public NoSubInJWTException(Throwable cause) {
        super(WSConstants.ERROR_NO_SUB_IN_JWT, cause);
    }

}
