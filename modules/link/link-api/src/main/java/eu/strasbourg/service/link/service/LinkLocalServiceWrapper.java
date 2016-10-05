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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LinkLocalService}.
 *
 * @author BenjaminBini
 * @see LinkLocalService
 * @generated
 */
@ProviderType
public class LinkLocalServiceWrapper implements LinkLocalService,
	ServiceWrapper<LinkLocalService> {
	public LinkLocalServiceWrapper(LinkLocalService linkLocalService) {
		_linkLocalService = linkLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _linkLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _linkLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _linkLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _linkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _linkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _linkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Recherche
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _linkLocalService.search(searchContext);
	}

	/**
	* Adds the link to the database. Also notifies the appropriate model listeners.
	*
	* @param link the link
	* @return the link that was added
	*/
	@Override
	public eu.strasbourg.service.link.model.Link addLink(
		eu.strasbourg.service.link.model.Link link) {
		return _linkLocalService.addLink(link);
	}

	/**
	* Crée un lien vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.link.model.Link createLink(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _linkLocalService.createLink(sc);
	}

	/**
	* Creates a new link with the primary key. Does not add the link to the database.
	*
	* @param linkId the primary key for the new link
	* @return the new link
	*/
	@Override
	public eu.strasbourg.service.link.model.Link createLink(long linkId) {
		return _linkLocalService.createLink(linkId);
	}

	/**
	* Deletes the link from the database. Also notifies the appropriate model listeners.
	*
	* @param link the link
	* @return the link that was removed
	*/
	@Override
	public eu.strasbourg.service.link.model.Link deleteLink(
		eu.strasbourg.service.link.model.Link link) {
		return _linkLocalService.deleteLink(link);
	}

	/**
	* Deletes the link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param linkId the primary key of the link
	* @return the link that was removed
	* @throws PortalException if a link with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.link.model.Link deleteLink(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _linkLocalService.deleteLink(linkId);
	}

	@Override
	public eu.strasbourg.service.link.model.Link fetchLink(long linkId) {
		return _linkLocalService.fetchLink(linkId);
	}

	/**
	* Returns the link matching the UUID and group.
	*
	* @param uuid the link's UUID
	* @param groupId the primary key of the group
	* @return the matching link, or <code>null</code> if a matching link could not be found
	*/
	@Override
	public eu.strasbourg.service.link.model.Link fetchLinkByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _linkLocalService.fetchLinkByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the link with the primary key.
	*
	* @param linkId the primary key of the link
	* @return the link
	* @throws PortalException if a link with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.link.model.Link getLink(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _linkLocalService.getLink(linkId);
	}

	/**
	* Returns the link matching the UUID and group.
	*
	* @param uuid the link's UUID
	* @param groupId the primary key of the group
	* @return the matching link
	* @throws PortalException if a matching link could not be found
	*/
	@Override
	public eu.strasbourg.service.link.model.Link getLinkByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _linkLocalService.getLinkByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime un lien
	*/
	@Override
	public eu.strasbourg.service.link.model.Link removeLink(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _linkLocalService.removeLink(linkId);
	}

	/**
	* Updates the link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param link the link
	* @return the link that was updated
	*/
	@Override
	public eu.strasbourg.service.link.model.Link updateLink(
		eu.strasbourg.service.link.model.Link link) {
		return _linkLocalService.updateLink(link);
	}

	/**
	* Met à jour un lien et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.link.model.Link updateLink(
		eu.strasbourg.service.link.model.Link link,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _linkLocalService.updateLink(link, sc);
	}

	/**
	* Met à jour le statut du lien
	*/
	@Override
	public eu.strasbourg.service.link.model.Link updateStatus(long userId,
		long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _linkLocalService.updateStatus(userId, entryId, status, sc,
			workflowContext);
	}

	/**
	* Returns the number of links.
	*
	* @return the number of links
	*/
	@Override
	public int getLinksCount() {
		return _linkLocalService.getLinksCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _linkLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _linkLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _linkLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _linkLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Recherche par mot clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.link.model.Link> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _linkLocalService.findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Retourne les vocabulaires rattachés à l'entité Link
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _linkLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne tous les liens d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.link.model.Link> getByGroupId(
		long groupId) {
		return _linkLocalService.getByGroupId(groupId);
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
	@Override
	public java.util.List<eu.strasbourg.service.link.model.Link> getLinks(
		int start, int end) {
		return _linkLocalService.getLinks(start, end);
	}

	/**
	* Returns all the links matching the UUID and company.
	*
	* @param uuid the UUID of the links
	* @param companyId the primary key of the company
	* @return the matching links, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.link.model.Link> getLinksByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _linkLocalService.getLinksByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<eu.strasbourg.service.link.model.Link> getLinksByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.link.model.Link> orderByComparator) {
		return _linkLocalService.getLinksByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _linkLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _linkLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Recherche par mot clés (compte)
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _linkLocalService.findByKeywordCount(keyword, groupId);
	}

	@Override
	public LinkLocalService getWrappedService() {
		return _linkLocalService;
	}

	@Override
	public void setWrappedService(LinkLocalService linkLocalService) {
		_linkLocalService = linkLocalService;
	}

	private LinkLocalService _linkLocalService;
}