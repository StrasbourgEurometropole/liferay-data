package eu.strasbourg.webservice.csmap.exception.auth;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levée lors de la vérification d'un base nonce expiré
 */
public class BaseNonceExpiredException extends Exception {

    public BaseNonceExpiredException() {
        super(WSConstants.ERROR_BASE_NONCE_EXPIRED);
    }

    public BaseNonceExpiredException(String msg) {
        super(WSConstants.ERROR_BASE_NONCE_EXPIRED + " : " + msg);
    }

    public BaseNonceExpiredException(String msg, Throwable cause) {
        super(WSConstants.ERROR_BASE_NONCE_EXPIRED + " : " + msg, cause);
    }

    public BaseNonceExpiredException(Throwable cause) {
        super(WSConstants.ERROR_BASE_NONCE_EXPIRED, cause);
    }

}
