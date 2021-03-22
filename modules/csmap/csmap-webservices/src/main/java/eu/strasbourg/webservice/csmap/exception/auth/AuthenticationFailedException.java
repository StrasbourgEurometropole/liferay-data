package eu.strasbourg.webservice.csmap.exception.auth;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levé lors après l'echec d'authentification à Publik
 */
public class AuthenticationFailedException extends Exception {

    public AuthenticationFailedException() {
        super(WSConstants.ERROR_AUTHENTICATION);
    }

    public AuthenticationFailedException(String msg) {
        super(WSConstants.ERROR_AUTHENTICATION + " : " + msg);
    }

    public AuthenticationFailedException(String msg, Throwable cause) {
        super(WSConstants.ERROR_AUTHENTICATION + " : " + msg, cause);
    }

    public AuthenticationFailedException(Throwable cause) {
        super(WSConstants.ERROR_AUTHENTICATION, cause);
    }

}
