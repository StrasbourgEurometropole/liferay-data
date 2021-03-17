package eu.strasbourg.webservice.csmap.exception.jwt;

/**
 * Exception levé quand il n'existe pas de JWT dans le header quand une authentification est requise
 */
public class NoJWTInHeaderException extends Exception {

    public NoJWTInHeaderException() {
    }

    public NoJWTInHeaderException(String msg) {
        super(msg);
    }

    public NoJWTInHeaderException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoJWTInHeaderException(Throwable cause) {
        super(cause);
    }

}
