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
 * Provides a wrapper for {@link BudgetSupportLocalService}.
 *
 * @author Cedric Henry
 * @see BudgetSupportLocalService
 * @generated
 */
@ProviderType
public class BudgetSupportLocalServiceWrapper
	implements BudgetSupportLocalService,
		ServiceWrapper<BudgetSupportLocalService> {
	public BudgetSupportLocalServiceWrapper(
		BudgetSupportLocalService budgetSupportLocalService) {
		_budgetSupportLocalService = budgetSupportLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _budgetSupportLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _budgetSupportLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _budgetSupportLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _budgetSupportLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetSupportLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetSupportLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the budget support to the database. Also notifies the appropriate model listeners.
	*
	* @param budgetSupport the budget support
	* @return the budget support that was added
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetSupport addBudgetSupport(
		eu.strasbourg.service.project.model.BudgetSupport budgetSupport) {
		return _budgetSupportLocalService.addBudgetSupport(budgetSupport);
	}

	/**
	* Methode de creation d'un budget participatif.
	*
	* @param sc Le contexte de la requete.
	* @return Le soutien cree.
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetSupport createBudgetSupport(
		com.liferay.portal.kernel.service.ServiceContext sc) {
		return _budgetSupportLocalService.createBudgetSupport(sc);
	}

	/**
	* Creates a new budget support with the primary key. Does not add the budget support to the database.
	*
	* @param budgetSupportId the primary key for the new budget support
	* @return the new budget support
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetSupport createBudgetSupport(
		long budgetSupportId) {
		return _budgetSupportLocalService.createBudgetSupport(budgetSupportId);
	}

	/**
	* Deletes the budget support from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetSupport the budget support
	* @return the budget support that was removed
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetSupport deleteBudgetSupport(
		eu.strasbourg.service.project.model.BudgetSupport budgetSupport) {
		return _budgetSupportLocalService.deleteBudgetSupport(budgetSupport);
	}

	/**
	* Deletes the budget support with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetSupportId the primary key of the budget support
	* @return the budget support that was removed
	* @throws PortalException if a budget support with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetSupport deleteBudgetSupport(
		long budgetSupportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetSupportLocalService.deleteBudgetSupport(budgetSupportId);
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetSupport fetchBudgetSupport(
		long budgetSupportId) {
		return _budgetSupportLocalService.fetchBudgetSupport(budgetSupportId);
	}

	/**
	* Returns the budget support matching the UUID and group.
	*
	* @param uuid the budget support's UUID
	* @param groupId the primary key of the group
	* @return the matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetSupport fetchBudgetSupportByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _budgetSupportLocalService.fetchBudgetSupportByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the budget support with the primary key.
	*
	* @param budgetSupportId the primary key of the budget support
	* @return the budget support
	* @throws PortalException if a budget support with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetSupport getBudgetSupport(
		long budgetSupportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetSupportLocalService.getBudgetSupport(budgetSupportId);
	}

	/**
	* Returns the budget support matching the UUID and group.
	*
	* @param uuid the budget support's UUID
	* @param groupId the primary key of the group
	* @return the matching budget support
	* @throws PortalException if a matching budget support could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetSupport getBudgetSupportByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _budgetSupportLocalService.getBudgetSupportByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the budget support in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param budgetSupport the budget support
	* @return the budget support that was updated
	*/
	@Override
	public eu.strasbourg.service.project.model.BudgetSupport updateBudgetSupport(
		eu.strasbourg.service.project.model.BudgetSupport budgetSupport) {
		return _budgetSupportLocalService.updateBudgetSupport(budgetSupport);
	}

	/**
	* Compter les soutiens d'un budget participatif donne
	*
	* @param budgetParticipatifId ID du budget participatif.
	* @return Nombre de soutiens
	*/
	@Override
	public int countBudgetSupportByBudgetParticipatifId(
		long budgetParticipatifId) {
		return _budgetSupportLocalService.countBudgetSupportByBudgetParticipatifId(budgetParticipatifId);
	}

	/**
	* Returns the number of budget supports.
	*
	* @return the number of budget supports
	*/
	@Override
	public int getBudgetSupportsCount() {
		return _budgetSupportLocalService.getBudgetSupportsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _budgetSupportLocalService.getOSGiServiceIdentifier();
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
		return _budgetSupportLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _budgetSupportLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _budgetSupportLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Recuperer les soutiens d'un budgte et d'un utilisateur donne
	*
	* @param budgetParticipatifId ID du budget participatif.
	* @param publikId ID publik de l'utilsiateur
	* @return Liste des soutiens
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetSupport> getBudgetSupportByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, java.lang.String publikUserId) {
		return _budgetSupportLocalService.getBudgetSupportByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId);
	}

	/**
	* Recuperer les soutiens d'un utilisateur donne
	*
	* @param publikId ID publik de l'utilsiateur
	* @return Liste des soutiens
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetSupport> getBudgetSupportByPublikId(
		java.lang.String publikId) {
		return _budgetSupportLocalService.getBudgetSupportByPublikId(publikId);
	}

	/**
	* Returns a range of all the budget supports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @return the range of budget supports
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetSupport> getBudgetSupports(
		int start, int end) {
		return _budgetSupportLocalService.getBudgetSupports(start, end);
	}

	/**
	* Recuperer les soutien par l'identifiant du budget participatif.
	*
	* @param budgetParticipatifId ID du budget participatif.
	* @return Liste des budgets participatifs
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetSupport> getBudgetSupportsByBudgetParticipatifId(
		long budgetParticipatifId) {
		return _budgetSupportLocalService.getBudgetSupportsByBudgetParticipatifId(budgetParticipatifId);
	}

	/**
	* Returns all the budget supports matching the UUID and company.
	*
	* @param uuid the UUID of the budget supports
	* @param companyId the primary key of the company
	* @return the matching budget supports, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetSupport> getBudgetSupportsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _budgetSupportLocalService.getBudgetSupportsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of budget supports matching the UUID and company.
	*
	* @param uuid the UUID of the budget supports
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching budget supports, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.BudgetSupport> getBudgetSupportsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.BudgetSupport> orderByComparator) {
		return _budgetSupportLocalService.getBudgetSupportsByUuidAndCompanyId(uuid,
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
		return _budgetSupportLocalService.dynamicQueryCount(dynamicQuery);
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
		return _budgetSupportLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Supprimer un soutien donne
	*
	* @param budgetSupportId Id du soutien
	*/
	@Override
	public void removeBudgetSupport(long budgetSupportId) {
		_budgetSupportLocalService.removeBudgetSupport(budgetSupportId);
	}

	@Override
	public BudgetSupportLocalService getWrappedService() {
		return _budgetSupportLocalService;
	}

	@Override
	public void setWrappedService(
		BudgetSupportLocalService budgetSupportLocalService) {
		_budgetSupportLocalService = budgetSupportLocalService;
	}

	private BudgetSupportLocalService _budgetSupportLocalService;
}