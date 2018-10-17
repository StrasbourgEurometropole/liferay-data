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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for BudgetPhase. This utility wraps
 * {@link eu.strasbourg.service.project.service.impl.BudgetPhaseLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see BudgetPhaseLocalService
 * @see eu.strasbourg.service.project.service.base.BudgetPhaseLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.BudgetPhaseLocalServiceImpl
 * @generated
 */
@ProviderType
public class BudgetPhaseLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.BudgetPhaseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the budget phase to the database. Also notifies the appropriate model listeners.
	*
	* @param budgetPhase the budget phase
	* @return the budget phase that was added
	*/
	public static eu.strasbourg.service.project.model.BudgetPhase addBudgetPhase(
		eu.strasbourg.service.project.model.BudgetPhase budgetPhase) {
		return getService().addBudgetPhase(budgetPhase);
	}

	/**
	* Creates a new budget phase with the primary key. Does not add the budget phase to the database.
	*
	* @param budgetPhaseId the primary key for the new budget phase
	* @return the new budget phase
	*/
	public static eu.strasbourg.service.project.model.BudgetPhase createBudgetPhase(
		long budgetPhaseId) {
		return getService().createBudgetPhase(budgetPhaseId);
	}

	/**
	* Deletes the budget phase from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetPhase the budget phase
	* @return the budget phase that was removed
	*/
	public static eu.strasbourg.service.project.model.BudgetPhase deleteBudgetPhase(
		eu.strasbourg.service.project.model.BudgetPhase budgetPhase) {
		return getService().deleteBudgetPhase(budgetPhase);
	}

	/**
	* Deletes the budget phase with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetPhaseId the primary key of the budget phase
	* @return the budget phase that was removed
	* @throws PortalException if a budget phase with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.BudgetPhase deleteBudgetPhase(
		long budgetPhaseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteBudgetPhase(budgetPhaseId);
	}

	public static eu.strasbourg.service.project.model.BudgetPhase fetchBudgetPhase(
		long budgetPhaseId) {
		return getService().fetchBudgetPhase(budgetPhaseId);
	}

	/**
	* Returns the budget phase matching the UUID and group.
	*
	* @param uuid the budget phase's UUID
	* @param groupId the primary key of the group
	* @return the matching budget phase, or <code>null</code> if a matching budget phase could not be found
	*/
	public static eu.strasbourg.service.project.model.BudgetPhase fetchBudgetPhaseByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchBudgetPhaseByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the budget phase with the primary key.
	*
	* @param budgetPhaseId the primary key of the budget phase
	* @return the budget phase
	* @throws PortalException if a budget phase with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.BudgetPhase getBudgetPhase(
		long budgetPhaseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBudgetPhase(budgetPhaseId);
	}

	/**
	* Returns the budget phase matching the UUID and group.
	*
	* @param uuid the budget phase's UUID
	* @param groupId the primary key of the group
	* @return the matching budget phase
	* @throws PortalException if a matching budget phase could not be found
	*/
	public static eu.strasbourg.service.project.model.BudgetPhase getBudgetPhaseByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBudgetPhaseByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the budget phase in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param budgetPhase the budget phase
	* @return the budget phase that was updated
	*/
	public static eu.strasbourg.service.project.model.BudgetPhase updateBudgetPhase(
		eu.strasbourg.service.project.model.BudgetPhase budgetPhase) {
		return getService().updateBudgetPhase(budgetPhase);
	}

	/**
	* Returns the number of budget phases.
	*
	* @return the number of budget phases
	*/
	public static int getBudgetPhasesCount() {
		return getService().getBudgetPhasesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the budget phases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget phases
	* @param end the upper bound of the range of budget phases (not inclusive)
	* @return the range of budget phases
	*/
	public static java.util.List<eu.strasbourg.service.project.model.BudgetPhase> getBudgetPhases(
		int start, int end) {
		return getService().getBudgetPhases(start, end);
	}

	/**
	* Returns all the budget phases matching the UUID and company.
	*
	* @param uuid the UUID of the budget phases
	* @param companyId the primary key of the company
	* @return the matching budget phases, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.project.model.BudgetPhase> getBudgetPhasesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getBudgetPhasesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of budget phases matching the UUID and company.
	*
	* @param uuid the UUID of the budget phases
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of budget phases
	* @param end the upper bound of the range of budget phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching budget phases, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.project.model.BudgetPhase> getBudgetPhasesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.BudgetPhase> orderByComparator) {
		return getService()
				   .getBudgetPhasesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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

	public static BudgetPhaseLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BudgetPhaseLocalService, BudgetPhaseLocalService> _serviceTracker =
		ServiceTrackerFactory.open(BudgetPhaseLocalService.class);
}