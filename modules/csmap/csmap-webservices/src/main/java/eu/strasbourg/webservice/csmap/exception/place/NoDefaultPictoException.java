package eu.strasbourg.webservice.csmap.exception.place;

import eu.strasbourg.webservice.csmap.constants.WSConstants;

/**
 * Exception levé quand aucun picto par défaut n'est présent dans la doclib
 */
public class NoDefaultPictoException extends Exception {

    public NoDefaultPictoException() {
        super(WSConstants.ERROR_NO_DEFAULT_PICTO);
    }

    public NoDefaultPictoException(String msg) {
        super(WSConstants.ERROR_NO_DEFAULT_PICTO + " : " + msg);
    }

    public NoDefaultPictoException(String msg, Throwable cause) {
        super(WSConstants.ERROR_NO_DEFAULT_PICTO + " : " + msg, cause);
    }

    public NoDefaultPictoException(Throwable cause) {
        super(WSConstants.ERROR_NO_DEFAULT_PICTO, cause);
    }

}
