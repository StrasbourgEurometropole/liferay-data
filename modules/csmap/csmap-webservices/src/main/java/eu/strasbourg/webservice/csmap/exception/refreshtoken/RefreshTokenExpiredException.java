package eu.strasbourg.webservice.csmap.exception.refreshtoken;

/**
 * Exception levé lors de la vérification d'un
 */
public class RefreshTokenExpiredException extends Exception {

    public RefreshTokenExpiredException() {
    }

    public RefreshTokenExpiredException(String msg) {
        super(msg);
    }

    public RefreshTokenExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RefreshTokenExpiredException(Throwable cause) {
        super(cause);
    }

}
