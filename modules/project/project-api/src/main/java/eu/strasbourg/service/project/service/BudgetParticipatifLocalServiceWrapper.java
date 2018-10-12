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

package eu.strasbourg.service.project.service;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BudgetParticipatifLocalService}.
 *
 * @author Cedric Henry
 * @see BudgetParticipatifLocalService
 * @generated
 */
@ProviderType
public class BudgetParticipatifLocalServiceWrapper
	implements BudgetParticipatifLocalService,
		ServiceWrapper<BudgetParticipatifLocalService> {
	public BudgetParticipatifLocalServiceWrapper(
		BudgetParticipatifLocalService budgetParticipatifLocalService) {
		_budgetParticipatifLocalService = budgetParticipatifLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _budgetParticipatifLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _budgetParticipatifLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _budgetParticipatifLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _budgetParticipatifLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetParticipatifLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetParticipatifLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the budget participatif to the database. Also notifies the appropriate model listeners.
	*
	* @param budgetParticipatif the budget participatif
	* @return the budget participatif that was added
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif addBudgetParticipatif(
		eu.strasbourg.service.project.model.BudgetParticipatif budgetParticipatif) {
		return _budgetParticipatifLocalService.addBudgetParticipatif(budgetParticipatif);
	}

	/**
	* Creates a new budget participatif with the primary key. Does not add the budget participatif to the database.
	*
	* @param budgetParticipatifId the primary key for the new budget participatif
	* @return the new budget participatif
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif createBudgetParticipatif(
		long budgetParticipatifId) {
		return _budgetParticipatifLocalService.createBudgetParticipatif(budgetParticipatifId);
	}

	/**
	* Deletes the budget participatif from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetParticipatif the budget participatif
	* @return the budget participatif that was removed
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif deleteBudgetParticipatif(
		eu.strasbourg.service.project.model.BudgetParticipatif budgetParticipatif) {
		return _budgetParticipatifLocalService.deleteBudgetParticipatif(budgetParticipatif);
	}

	/**
	* Deletes the budget participatif with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetParticipatifId the primary key of the budget participatif
	* @return the budget participatif that was removed
	* @throws PortalException if a budget participatif with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif deleteBudgetParticipatif(
		long budgetParticipatifId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetParticipatifLocalService.deleteBudgetParticipatif(budgetParticipatifId);
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif fetchBudgetParticipatif(
		long budgetParticipatifId) {
		return _budgetParticipatifLocalService.fetchBudgetParticipatif(budgetParticipatifId);
	}

	/**
	* Returns the budget participatif matching the UUID and group.
	*
	* @param uuid the budget participatif's UUID
	* @param groupId the primary key of the group
	* @return the matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif fetchBudgetParticipatifByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _budgetParticipatifLocalService.fetchBudgetParticipatifByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the budget participatif with the primary key.
	*
	* @param budgetParticipatifId the primary key of the budget participatif
	* @return the budget participatif
	* @throws PortalException if a budget participatif with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif getBudgetParticipatif(
		long budgetParticipatifId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetParticipatifLocalService.getBudgetParticipatif(budgetParticipatifId);
	}

	/**
	* Returns the budget participatif matching the UUID and group.
	*
	* @param uuid the budget participatif's UUID
	* @param groupId the primary key of the group
	* @return the matching budget participatif
	* @throws PortalException if a matching budget participatif could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif getBudgetParticipatifByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetParticipatifLocalService.getBudgetParticipatifByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the budget participatif in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param budgetParticipatif the budget participatif
	* @return the budget participatif that was updated
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetParticipatif updateBudgetParticipatif(
		eu.strasbourg.service.project.model.BudgetParticipatif budgetParticipatif) {
		return _budgetParticipatifLocalService.updateBudgetParticipatif(budgetParticipatif);
	}

	/**
	* Returns the number of budget participatifs.
	*
	* @return the number of budget participatifs
	*/
	@Override
	public int getBudgetParticipatifsCount() {
		return _budgetParticipatifLocalService.getBudgetParticipatifsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _budgetParticipatifLocalService.getOSGiServiceIdentifier();
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
		return _budgetParticipatifLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _budgetParticipatifLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _budgetParticipatifLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the budget participatifs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of budget participatifs
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetParticipatif> getBudgetParticipatifs(
		int start, int end) {
		return _budgetParticipatifLocalService.getBudgetParticipatifs(start, end);
	}

	/**
	* Returns all the budget participatifs matching the UUID and company.
	*
	* @param uuid the UUID of the budget participatifs
	* @param companyId the primary key of the company
	* @return the matching budget participatifs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetParticipatif> getBudgetParticipatifsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _budgetParticipatifLocalService.getBudgetParticipatifsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of budget participatifs matching the UUID and company.
	*
	* @param uuid the UUID of the budget participatifs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching budget participatifs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetParticipatif> getBudgetParticipatifsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.BudgetParticipatif> orderByComparator) {
		return _budgetParticipatifLocalService.getBudgetParticipatifsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _budgetParticipatifLocalService.dynamicQueryCount(dynamicQuery);
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
		return _budgetParticipatifLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public BudgetParticipatifLocalService getWrappedService() {
		return _budgetParticipatifLocalService;
	}

	@Override
	public void setWrappedService(
		BudgetParticipatifLocalService budgetParticipatifLocalService) {
		_budgetParticipatifLocalService = budgetParticipatifLocalService;
	}

	private BudgetParticipatifLocalService _budgetParticipatifLocalService;
}