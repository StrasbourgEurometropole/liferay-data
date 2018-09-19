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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import eu.strasbourg.service.project.exception.NoSuchSignataireException;
import eu.strasbourg.service.project.model.Signataire;
import eu.strasbourg.service.project.service.base.SignataireLocalServiceBaseImpl;

import javax.portlet.PortletException;
import java.util.List;

/**
 * The implementation of the signataire local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.SignataireLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see SignataireLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.SignataireLocalServiceUtil
 */
public class SignataireLocalServiceImpl extends SignataireLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.SignataireLocalServiceUtil} to access the signataire local service.
	 */
	public static final String ANONYME = "ENTREE ANONYME";

	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    /**
     * méthode permettant de récuperer les signataires par l'identifiant de la pétition.
     * @param petitionId l'identifiant de la pétition.
     * @return la liste des signataires.
     */
    @Override
    public List<Signataire> getSignatairesByPetitionId(long petitionId) {
        return signatairePersistence.findByPetition(petitionId);
    }

    /**
     * méthode permettant de récuperer les signataires par l'identifiant de la pétition.
     * @param petitionId l'identifiant de la pétition.
     * @return la liste des signataires.
     */
    public int countSignataireByPetitionId(long petitionId) {
        return signatairePersistence.countByPetition(petitionId);
    }

    @Override
    public void removeSignataire(long signataireId){
		try {
			signatairePersistence.remove(signataireId);
		} catch (NoSuchSignataireException e) {
			_log.error("pas de signataire : ",e);
		}
	}

    /**
     * méthode de creation de signataire.
     * @param sc le context.
     * @return le signataire crée.
     */
    @Override
	public Signataire createSignataire(ServiceContext sc){
		long pk = counterLocalService.increment();
		Signataire result = signataireLocalService.createSignataire(pk);
		result.setGroupId(sc.getScopeGroupId());
		return result;
	}

    /**
     * méthode permettant de compter le nombre de faux signataire
     * @param petitionId la pétition concernée
     * @return le nombre de signataires
     */
    @Override
	public int countFakeSignataireByPetition(long petitionId){
		return signatairePersistence.countByPetitionIdAndSignataireName(petitionId,ANONYME);
	}

	public List<Signataire> findSignatairesByPetitionIdAndSignataireName(long petitionId, String signataireName) throws PortletException {
    	if (petitionId==0||signataireName==null||signataireName.isEmpty())
    		throw new PortletException("erreur dans les parametres d'entrée");
		return signatairePersistence.findByPetitionIdAndSignataireName(petitionId,signataireName);
	}

    /**
     * méthode de création de faux signataires
     * @param petitionId la pétition concernée
     * @param nombreCreation le nombre de creation souhaité.
     */
    @Override
	public void createFakeSignataire(long petitionId, int nombreCreation) {
        int nombreFauxSignataires = countFakeSignataireByPetition(petitionId);
        if (nombreCreation != nombreFauxSignataires) {
            if (nombreFauxSignataires > 0) {
                List<Signataire> signataires = signatairePersistence.findByPetitionIdAndSignataireName(petitionId, ANONYME);
                for (Signataire signataire : signataires) {
                    deleteSignataire(signataire);
                }
            }
            for (int i = 0; i < nombreCreation; i++) {
                long pk = counterLocalService.increment();
                Signataire result = signatairePersistence.create(pk);
                result.setPetitionId(petitionId);
                result.setSignataireName(ANONYME);
                result = signatairePersistence.update(result);
            }
        }
    }
}