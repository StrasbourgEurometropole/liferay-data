package eu.strasbourg.webservice.csmap.exception.authentication;

/**
 * Exception levé lors après l'echec d'authentification à Publik
 */
public class AuthenticationFailedException extends Exception {

    public AuthenticationFailedException() {
    }

    public AuthenticationFailedException(String msg) {
        super(msg);
    }

    public AuthenticationFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationFailedException(Throwable cause) {
        super(cause);
    }

}
