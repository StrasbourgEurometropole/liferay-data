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

import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import eu.strasbourg.service.project.exception.NoSuchBudgetSupportException;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.service.base.BudgetSupportLocalServiceBaseImpl;

/**
 * The implementation of the budget support local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.BudgetSupportLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetSupportLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil
 */
public class BudgetSupportLocalServiceImpl extends BudgetSupportLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil} to access the budget support local service.
	 */
	
	public static final String ANONYME = "ENTREE ANONYME";

	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
	/**
     * Methode de creation d'un budget participatif.
     * @param sc Le contexte de la requete.
     * @return Le soutien cree.
     */
    @Override
	public BudgetSupport createBudgetSupport(ServiceContext sc){
		long pk = this.counterLocalService.increment();
		
		BudgetSupport result = this.budgetSupportLocalService.createBudgetSupport(pk);
		result.setGroupId(sc.getScopeGroupId());
		
		return result;
	}
    
    /**
     * Supprimer un soutien donne
     * @param budgetSupportId Id du soutien
     */
    @Override
    public void removeBudgetSupport(long budgetSupportId){
		try {
			this.budgetSupportPersistence.remove(budgetSupportId);
		} catch (NoSuchBudgetSupportException e) {
			_log.error("Pas de soutien : ",e);
		}
	}
	
	/**
     * Recuperer les soutien par l'identifiant du budget participatif.
     * @param budgetParticipatifId ID du budget participatif.
     * @return Liste des budgets participatifs
     */
    @Override
    public List<BudgetSupport> getBudgetSupportsByBudgetParticipatifId(long budgetParticipatifId) {
        return this.budgetSupportPersistence.findByBudgetParticipatif(budgetParticipatifId);
    }
    
    /**
     * Compter les soutiens d'un budget participatif donne
     * @param budgetParticipatifId ID du budget participatif.
     * @return Nombre de soutiens
     */
    @Override
    public int countBudgetSupportByBudgetParticipatifId(long budgetParticipatifId) {
        return this.budgetSupportPersistence.countByBudgetParticipatif(budgetParticipatifId);
    }
    
    /**
     * Recuperer les soutiens d'un utilisateur donne
     * @param publikId ID publik de l'utilsiateur
     * @return Liste des soutiens
     */
    @Override
	public List<BudgetSupport> getBudgetSupportByPublikId(String publikId){
        return this.budgetSupportPersistence.findByPublikUserId(publikId);
    }
    
    /**
     * Recuperer les soutiens d'un budgte et d'un utilisateur donne
     * @param budgetParticipatifId ID du budget participatif.
     * @param publikId ID publik de l'utilsiateur
     * @return Liste des soutiens
     */
    @Override
	public List<BudgetSupport> getBudgetSupportByBudgetParticipatifIdAndPublikUserId(long budgetParticipatifId, String publikUserId) {
        return this.budgetSupportPersistence.findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId, publikUserId);
    }
	
}