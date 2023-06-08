package eu.strasbourg.service.edition.service.persistence.impl;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.Session;

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.service.persistence.EditionFinder;

public class EditionFinderImpl extends EditionFinderBaseImpl
	implements EditionFinder {

	public List<Edition> findByKeywordsCategories(String keywords,
		long[] categoryIds) {

		Session session = null;
		try {
			/*
			 * Try to open a new Hibernate session and create a dynamic query to
			 * retrieve and return the desired list of entity objects
			 */
		} catch (Exception e) {
			// Exception handling
		} finally {
			closeSession(session);
		}

		return null;
		/*
		 * Return null only if there was an error returning the desired list of
		 * entity objects in the try block
		 */
	}

}
