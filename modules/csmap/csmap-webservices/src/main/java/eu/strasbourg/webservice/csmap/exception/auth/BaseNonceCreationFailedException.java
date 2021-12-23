package eu.strasbourg.webservice.csmap.exception.auth;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levée lors d'un problème lors de la génération d'un baseNonce
 */
public class BaseNonceCreationFailedException extends Exception {

    public BaseNonceCreationFailedException() {
        super(WSConstants.ERROR_BASE_NONCE_CREATION);
    }

    public BaseNonceCreationFailedException(String msg) {
        super(WSConstants.ERROR_BASE_NONCE_CREATION + " : " + msg);
    }

    public BaseNonceCreationFailedException(String msg, Throwable cause) {
        super(WSConstants.ERROR_BASE_NONCE_CREATION + " : " + msg, cause);
    }

    public BaseNonceCreationFailedException(Throwable cause) {
        super(WSConstants.ERROR_BASE_NONCE_CREATION, cause);
    }

}
