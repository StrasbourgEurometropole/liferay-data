package eu.strasbourg.utils.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.utils.exception.FileAccessException;
import eu.strasbourg.utils.exception.FileFormatException;
import eu.strasbourg.utils.models.GTFSModel;

/**
 * Permet de la deserialisation des donnees des listes d'objets issues de GTFS
 */
public abstract class CSVParser<T extends GTFSModel> {

	private final Class<T> className;

	public CSVParser(Class<T> className) {
		this.className = className;
	}

	private T newClassIntance() {
		T entry;
		try {
			entry = className.newInstance();
		} catch (InstantiationException ie) {
			throw new RuntimeException(String.format("can not instantiate class %s", className.getName()), ie);
		} catch (IllegalAccessException iae) {
			throw new RuntimeException(String.format("can not access class %s", className.getName()), iae);
		}

		return entry;
	}

	public T parseEntry(String... data) {
		T entry = newClassIntance();
		try {
			entry.fromStringArray(data);
		} catch (FileFormatException e) {
			entry = null;
		}
		return entry;
	}

	public List<T> getAll(String file, String separator)throws FileAccessException {
		List<T> result = new ArrayList<T>();
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(file));
			int nbCol = newClassIntance().getNbEntry();
			if ((line = br.readLine()) != null) {
				while ((line = br.readLine()) != null) {
					String[] splitValue = line.split(separator,nbCol);
					T entry = parseEntry(splitValue);
					if (entry != null)
						result.add(entry);
				}
			}


		} catch (FileNotFoundException e) {
			throw new FileAccessException(e.getMessage());
		} catch (IOException e) {
			throw new FileAccessException(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					_log.error(e.getMessage(), e);
				}
			}
		}
		return result;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}
