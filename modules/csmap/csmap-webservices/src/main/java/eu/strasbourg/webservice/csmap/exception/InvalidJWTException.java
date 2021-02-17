package eu.strasbourg.webservice.csmap.exception;

/**
 * Exception levé lors de la vérification d'un JWT non valide
 */
public class InvalidJWTException extends Exception {

    public InvalidJWTException() {
    }

    public InvalidJWTException(String msg) {
        super(msg);
    }

    public InvalidJWTException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidJWTException(Throwable cause) {
        super(cause);
    }

}
