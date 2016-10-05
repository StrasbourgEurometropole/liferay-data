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

package eu.strasbourg.service.link.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Link. This utility wraps
 * {@link eu.strasbourg.service.link.service.impl.LinkLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see LinkLocalService
 * @see eu.strasbourg.service.link.service.base.LinkLocalServiceBaseImpl
 * @see eu.strasbourg.service.link.service.impl.LinkLocalServiceImpl
 * @generated
 */
@ProviderType
public class LinkLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.link.service.impl.LinkLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Recherche
	*/
	public static com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return getService().search(searchContext);
	}

	/**
	* Adds the link to the database. Also notifies the appropriate model listeners.
	*
	* @param link the link
	* @return the link that was added
	*/
	public static eu.strasbourg.service.link.model.Link addLink(
		eu.strasbourg.service.link.model.Link link) {
		return getService().addLink(link);
	}

	/**
	* Crée un lien vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.link.model.Link createLink(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createLink(sc);
	}

	/**
	* Creates a new link with the primary key. Does not add the link to the database.
	*
	* @param linkId the primary key for the new link
	* @return the new link
	*/
	public static eu.strasbourg.service.link.model.Link createLink(long linkId) {
		return getService().createLink(linkId);
	}

	/**
	* Deletes the link from the database. Also notifies the appropriate model listeners.
	*
	* @param link the link
	* @return the link that was removed
	*/
	public static eu.strasbourg.service.link.model.Link deleteLink(
		eu.strasbourg.service.link.model.Link link) {
		return getService().deleteLink(link);
	}

	/**
	* Deletes the link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param linkId the primary key of the link
	* @return the link that was removed
	* @throws PortalException if a link with the primary key could not be found
	*/
	public static eu.strasbourg.service.link.model.Link deleteLink(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLink(linkId);
	}

	public static eu.strasbourg.service.link.model.Link fetchLink(long linkId) {
		return getService().fetchLink(linkId);
	}

	/**
	* Returns the link matching the UUID and group.
	*
	* @param uuid the link's UUID
	* @param groupId the primary key of the group
	* @return the matching link, or <code>null</code> if a matching link could not be found
	*/
	public static eu.strasbourg.service.link.model.Link fetchLinkByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchLinkByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the link with the primary key.
	*
	* @param linkId the primary key of the link
	* @return the link
	* @throws PortalException if a link with the primary key could not be found
	*/
	public static eu.strasbourg.service.link.model.Link getLink(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLink(linkId);
	}

	/**
	* Returns the link matching the UUID and group.
	*
	* @param uuid the link's UUID
	* @param groupId the primary key of the group
	* @return the matching link
	* @throws PortalException if a matching link could not be found
	*/
	public static eu.strasbourg.service.link.model.Link getLinkByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLinkByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime un lien
	*/
	public static eu.strasbourg.service.link.model.Link removeLink(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeLink(linkId);
	}

	/**
	* Updates the link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param link the link
	* @return the link that was updated
	*/
	public static eu.strasbourg.service.link.model.Link updateLink(
		eu.strasbourg.service.link.model.Link link) {
		return getService().updateLink(link);
	}

	/**
	* Met à jour un lien et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.link.model.Link updateLink(
		eu.strasbourg.service.link.model.Link link,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateLink(link, sc);
	}

	/**
	* Met à jour le statut du lien
	*/
	public static eu.strasbourg.service.link.model.Link updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Returns the number of links.
	*
	* @return the number of links
	*/
	public static int getLinksCount() {
		return getService().getLinksCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.link.model.impl.LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.link.model.impl.LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Recherche par mot clés
	*/
	public static java.util.List<eu.strasbourg.service.link.model.Link> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Retourne les vocabulaires rattachés à l'entité Link
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne tous les liens d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.link.model.Link> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	/**
	* Returns a range of all the links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.link.model.impl.LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @return the range of links
	*/
	public static java.util.List<eu.strasbourg.service.link.model.Link> getLinks(
		int start, int end) {
		return getService().getLinks(start, end);
	}

	/**
	* Returns all the links matching the UUID and company.
	*
	* @param uuid the UUID of the links
	* @param companyId the primary key of the company
	* @return the matching links, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.link.model.Link> getLinksByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getLinksByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of links matching the UUID and company.
	*
	* @param uuid the UUID of the links
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching links, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.link.model.Link> getLinksByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.link.model.Link> orderByComparator) {
		return getService()
				   .getLinksByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Recherche par mot clés (compte)
	*/
	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	public static LinkLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LinkLocalService, LinkLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LinkLocalService.class);
}