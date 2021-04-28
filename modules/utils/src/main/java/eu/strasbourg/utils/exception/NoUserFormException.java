package eu.strasbourg.utils.exception;

/**
 * Exception levee lors d'un probleme de format de fichier
 */
public class NoUserFormException extends Exception {

	private static final long serialVersionUID = 5204431278351381606L;

	public NoUserFormException()  {
		super("No user form found");
	}

	public NoUserFormException(String msg) {
		super("No user form found : " + msg);
	}

	public NoUserFormException(String msg, Throwable cause) {
		super("No user form found : " + msg, cause);
	}

	public NoUserFormException(Throwable cause) {
		super("No user form found", cause);
	}
	
}