package eu.strasbourg.webservice.csmap.exception.jwt;

/**
 * Exception levé quand il n'existe pas de JWT dans le header quand une authentification est requise
 */
public class NoSubInJWTException extends Exception {

    public NoSubInJWTException() {
    }

    public NoSubInJWTException(String msg) {
        super(msg);
    }

    public NoSubInJWTException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSubInJWTException(Throwable cause) {
        super(cause);
    }

}
