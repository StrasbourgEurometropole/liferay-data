package eu.strasbourg.utils.exception;

/**
 * Exception levee lors d'un probleme d'acces fichier
 */
public class FileAccessException extends Exception {

	private static final long serialVersionUID = -3566094872148749931L;

	public FileAccessException(String localizedMessage) {
		super(localizedMessage);
	}
	
}
