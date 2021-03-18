package eu.strasbourg.webservice.csmap.exception.refreshtoken;

/**
 * Exception levé lors d'un problème lors de la génération d'un refreshToken
 */
public class RefreshTokenCreationFailedException extends Exception {

    public RefreshTokenCreationFailedException() {
    }

    public RefreshTokenCreationFailedException(String msg) {
        super(msg);
    }

    public RefreshTokenCreationFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RefreshTokenCreationFailedException(Throwable cause) {
        super(cause);
    }

}
