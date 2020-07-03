/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.activity.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.activity.model.Practice;
import eu.strasbourg.service.activity.model.PracticeCategories;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Practice. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see PracticeLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface PracticeLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PracticeLocalServiceUtil} to access the practice local service. Add custom service methods to <code>eu.strasbourg.service.activity.service.impl.PracticeLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the practice to the database. Also notifies the appropriate model listeners.
	 *
	 * @param practice the practice
	 * @return the practice that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Practice addPractice(Practice practice);

	/**
	 * Creates a new practice with the primary key. Does not add the practice to the database.
	 *
	 * @param practiceId the primary key for the new practice
	 * @return the new practice
	 */
	@Transactional(enabled = false)
	public Practice createPractice(long practiceId);

	/**
	 * Crée une pratique vide avec une PK, non ajouté à la base de donnée
	 */
	public Practice createPractice(ServiceContext sc) throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the practice with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param practiceId the primary key of the practice
	 * @return the practice that was removed
	 * @throws PortalException if a practice with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public Practice deletePractice(long practiceId) throws PortalException;

	/**
	 * Deletes the practice from the database. Also notifies the appropriate model listeners.
	 *
	 * @param practice the practice
	 * @return the practice that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public Practice deletePractice(Practice practice);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.activity.model.impl.PracticeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.activity.model.impl.PracticeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Practice fetchPractice(long practiceId);

	/**
	 * Returns the practice matching the UUID and group.
	 *
	 * @param uuid the practice's UUID
	 * @param groupId the primary key of the group
	 * @return the matching practice, or <code>null</code> if a matching practice could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Practice fetchPracticeByUuidAndGroupId(String uuid, long groupId);

	/**
	 * Lance une recherche par mots-clés
	 */
	public List<Practice> findByKeyword(
		String keyword, long groupId, int start, int end);

	/**
	 * Compte de la recherche par mots-clés
	 */
	public long findByKeywordCount(String keyword, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entité
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	 * Retourne les pratiques d'une association
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Practice> getByAssociation(long associationId);

	/**
	 * Retourne toutes les éditions d'un groupe
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Practice> getByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the practice with the primary key.
	 *
	 * @param practiceId the primary key of the practice
	 * @return the practice
	 * @throws PortalException if a practice with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Practice getPractice(long practiceId) throws PortalException;

	/**
	 * Returns the practice matching the UUID and group.
	 *
	 * @param uuid the practice's UUID
	 * @param groupId the primary key of the group
	 * @return the matching practice
	 * @throws PortalException if a matching practice could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Practice getPracticeByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the practices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.activity.model.impl.PracticeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of practices
	 * @param end the upper bound of the range of practices (not inclusive)
	 * @return the range of practices
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Practice> getPractices(int start, int end);

	/**
	 * Returns all the practices matching the UUID and company.
	 *
	 * @param uuid the UUID of the practices
	 * @param companyId the primary key of the company
	 * @return the matching practices, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Practice> getPracticesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of practices matching the UUID and company.
	 *
	 * @param uuid the UUID of the practices
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of practices
	 * @param end the upper bound of the range of practices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching practices, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Practice> getPracticesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Practice> orderByComparator);

	/**
	 * Returns the number of practices.
	 *
	 * @return the number of practices
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPracticesCount();

	/**
	 * Retourne les activités triées par domaine de l'association
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PracticeCategories> getPracticesSortedByAssociation(
		long associationId);

	/**
	 * Supprime une entité
	 */
	public Practice removePractice(long practiceId) throws PortalException;

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(SearchContext searchContext) throws SearchException;

	/**
	 * Updates the practice in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param practice the practice
	 * @return the practice that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Practice updatePractice(Practice practice);

	/**
	 * Met à jour une pratique et l'enregistre en base de données
	 */
	public Practice updatePractice(Practice practice, ServiceContext sc)
		throws PortalException;

	/**
	 * Met à jour le statut de la pratique par le framework workflow
	 */
	public Practice updateStatus(long userId, long entryId, int status)
		throws PortalException;

}