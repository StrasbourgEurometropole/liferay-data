package eu.strasbourg.utils.exception;

/**
 * Exception levee lors d'un probleme de format de fichier
 */
public class FileFormatException extends Exception {

	private static final long serialVersionUID = 5204431278351381606L;

	public FileFormatException(String localizedMessage) {
		super(localizedMessage);
	}
	
}