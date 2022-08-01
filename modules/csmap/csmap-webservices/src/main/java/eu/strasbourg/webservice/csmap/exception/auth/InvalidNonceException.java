package eu.strasbourg.webservice.csmap.exception.auth;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levé lors de la vérification du nonce présent dans l'ID_TOKEN renvoyé par authentik
 */
public class InvalidNonceException extends Exception {

    public InvalidNonceException() {
        super(WSConstants.ERROR_INVALID_NONCE);
    }

    public InvalidNonceException(String msg) {
        super(WSConstants.ERROR_INVALID_NONCE + " : " + msg);
    }

    public InvalidNonceException(String msg, Throwable cause) {
        super(WSConstants.ERROR_INVALID_NONCE + " : " + msg, cause);
    }

    public InvalidNonceException(Throwable cause) {
        super(WSConstants.ERROR_INVALID_NONCE, cause);
    }

}
