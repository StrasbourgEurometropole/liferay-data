package eu.strasbourg.webservice.csmap.exception.auth;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levée quand il n'existe pas de code_verifier lors de l'authentification d'une personne
 */
public class NoCodeVerifierException extends Exception {

    public NoCodeVerifierException() {
        super(WSConstants.ERROR_NO_CODE_VERIFIER);
    }

    public NoCodeVerifierException(String msg) {
        super(WSConstants.ERROR_NO_CODE_VERIFIER + " : " + msg);
    }

    public NoCodeVerifierException(String msg, Throwable cause) {
        super(WSConstants.ERROR_NO_CODE_VERIFIER + " : " + msg, cause);
    }

    public NoCodeVerifierException(Throwable cause) {
        super(WSConstants.ERROR_NO_CODE_VERIFIER, cause);
    }

}
