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

package eu.strasbourg.service.comment.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.base.SignalementLocalServiceBaseImpl;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;

import java.util.List;

/**
 * The implementation of the signalement local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.comment.service.SignalementLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Romain Vergnais
 * @see SignalementLocalServiceBaseImpl
 * @see eu.strasbourg.service.comment.service.SignalementLocalServiceUtil
 */
public class SignalementLocalServiceImpl extends SignalementLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.comment.service.SignalementLocalServiceUtil} to access the signalement local service.
	 */

	public List<Signalement> getByGroupId(long groupId){
	    return signalementPersistence.findByGroupId(groupId);
    }

    /**
	 * Méthode qui permet de creer un signalement sans le persister.
	 * @param sc le serviceContext
	 * @return le signalement généré.
	 * @throws PortalException l'exception.
	 */
	@Override
	public Signalement createSignalement(ServiceContext sc) throws PortalException{
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		long pk = counterLocalService.increment();
		Signalement signalement = signalementLocalService.createSignalement(pk);
		signalement.setGroupId(sc.getScopeGroupId());
		signalement.setUserName(user.getFullName());
		signalement.setUserId(user.getUserId());
		signalement.setStatus(WorkflowConstants.STATUS_PENDING);
		return signalement;
	}

	/**
	 * Méthode qui permet de creer un signalement sans le persister.
	 * @param sc le serviceContext
	 * @param commentId l'identifiant du commentaire lié au signalement.
	 * @return le signalement.
	 * @throws PortalException l'exception.
	 */
	@Override
	public Signalement createSignalement(ServiceContext sc, long commentId) throws PortalException{
		long pk = counterLocalService.increment();
		Signalement signalement = signalementLocalService.createSignalement(pk);
		signalement.setCommentId(commentId);
		return signalement;
	}

	@Override
	public Signalement updateSignalement(Signalement signalement, ServiceContext sc, String publikUserId)
            throws PortalException{
		PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publikUserId);
		signalement.setGroupId(sc.getScopeGroupId());
	    signalement.setStatusByUserId(user.getPublikUserLiferayId());
        signalement.setUserName(user.getUserName());
        signalement.setUserId(user.getPublikUserLiferayId());
        signalement.setStatusDate(sc.getModifiedDate());
        if (sc.getWorkflowAction()==WorkflowConstants.ACTION_PUBLISH){
            signalement.setStatus(WorkflowConstants.STATUS_APPROVED);
        }else {
            signalement.setStatus(WorkflowConstants.STATUS_DRAFT);
        }
	    signalement = signalementLocalService.updateSignalement(signalement);
        if (signalement.isApproved()){
			reindex(signalement, false);
		} else {
        	reindex(signalement,true);
		}
        return signalement;
    }

    Signalement removeSignalement(long signalementId) throws PortalException{
		Signalement signalement =  signalementPersistence.remove(signalementId);
		reindex(signalement,true);
        return signalement;
    }

	public List<Signalement> findByCommentId(long commentId){
		return signalementPersistence.findByCommentId(commentId);
	}

    /**
     * Reindex le projet dans le moteur de recherche
     */
    private void reindex(Signalement signalement, boolean delete) throws SearchException {
        Indexer<Signalement> indexer = IndexerRegistryUtil
                .nullSafeGetIndexer(Signalement.class);
        if (delete) {
            indexer.delete(signalement);
        } else {
            indexer.reindex(signalement);
        }
    }

}