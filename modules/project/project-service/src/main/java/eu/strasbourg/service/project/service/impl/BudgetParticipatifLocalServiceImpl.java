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

package eu.strasbourg.service.project.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.base.BudgetParticipatifLocalServiceBaseImpl;

/**
 * The implementation of the budget participatif local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.BudgetParticipatifLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetParticipatifLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil
 */
public class BudgetParticipatifLocalServiceImpl extends BudgetParticipatifLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil} to access the budget participatif local service.
	 */

	/**
	 * le logger
	 */
	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());


	/**
	 * Crée une participation vide avec une PK, non ajouté à la base de donnée
	 */
	public BudgetParticipatif createBudgetParticipatif(ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		long pk = counterLocalService.increment();
		BudgetParticipatif budget = this.budgetParticipatifLocalService
				.createBudgetParticipatif(pk);
		budget.setGroupId(sc.getScopeGroupId());
		budget.setStatus(WorkflowConstants.STATUS_DRAFT);
		return budget;
	}

	/**
	 * Méthode de mise à jour d'un budget
	 * @param budget le budget
	 * @param sc le service context
	 * @return le budget
	 * @throws PortalException exception
	 */
	public BudgetParticipatif updateBudgetParticipatif(BudgetParticipatif budget, ServiceContext sc) throws PortalException {
		if (sc.getWorkflowAction()==WorkflowConstants.ACTION_PUBLISH){
			budget.setStatus(WorkflowConstants.STATUS_APPROVED);
		}else {
			budget.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		updateBudgetParticipatif(budget);
		updateAssetEntry(budget,sc);
		reindex(budget,false);
		return budget;
	}

	/**
	 * Met à jour l'AssetEntry rattachée au budgetParticipatif
	 */
	private void updateAssetEntry(BudgetParticipatif budget, ServiceContext sc)
			throws PortalException {
		assetEntryLocalService.updateEntry(sc.getUserId(),sc.getScopeGroupId(),budget.getCreateDate(),
				budget.getModifiedDate(),BudgetParticipatif.class.getName(), budget.getPrimaryKey(),budget.getUuid(),
				0,sc.getAssetCategoryIds(),sc.getAssetTagNames(),true,budget.isApproved(),
				budget.getCreateDate(),null,budget.getCreateDate(),null, ContentTypes.TEXT_HTML,
				budget.getTitle(),budget.getDescription(),budget.getDescription(),null,null,
				0,0,null);
		reindex(budget,false);
	}

	/**
	 * Reindex la budget dans le moteur de recherche
	 */
	private void reindex(BudgetParticipatif budget, boolean delete) throws SearchException {
		Indexer<BudgetParticipatif> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(BudgetParticipatif.class);
		if (delete) {
			indexer.delete(budget);
		} else {
			indexer.reindex(budget);
		}
	}

}