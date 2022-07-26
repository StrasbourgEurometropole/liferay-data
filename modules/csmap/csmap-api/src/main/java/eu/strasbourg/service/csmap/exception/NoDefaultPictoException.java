package eu.strasbourg.service.csmap.exception;

/**
 * Exception levé quand aucun picto par défaut n'est présent dans la doclib
 */
public class NoDefaultPictoException extends Exception {

    public NoDefaultPictoException() {
        super("No default picto available for this application");
    }

    public NoDefaultPictoException(String msg) {
        super("No default picto available for this application" + " : " + msg);
    }

    public NoDefaultPictoException(String msg, Throwable cause) {
        super("No default picto available for this application" + " : " + msg, cause);
    }

    public NoDefaultPictoException(Throwable cause) {
        super("No default picto available for this application", cause);
    }

}
