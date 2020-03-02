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

package eu.strasbourg.service.formSendRecordField.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;
import eu.strasbourg.service.formSendRecordField.service.base.FormSendRecordFieldSignalementLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the form send record field signalement local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldSignalementLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalementLocalServiceBaseImpl
 * @see eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldSignalementLocalServiceUtil
 */
public class FormSendRecordFieldSignalementLocalServiceImpl
	extends FormSendRecordFieldSignalementLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldSignalementLocalServiceUtil} to access the form send record field signalement local service.
	 */

	public List<FormSendRecordFieldSignalement> getByGroupId(long groupId){
		return formSendRecordFieldSignalementPersistence.findByGroupId(groupId);
	}

	/**
	 * Retourne tous les signalementsd'une réponse à un formulaire d'un utilisateur
	 */
	@Override
	public List<FormSendRecordFieldSignalement> getByPublikId(String publikId) {

		return this.formSendRecordFieldSignalementPersistence.findByPublikId(publikId);
	}

	/**
	 * Permet de creer un signalement sans le persister.
	 * @param sc le serviceContext
	 * @return le signalement.
	 * @throws PortalException l'exception.
	 */
	@Override
	public FormSendRecordFieldSignalement createFormSendRecordFieldSignalement(ServiceContext sc) throws PortalException{
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();
		FormSendRecordFieldSignalement signalement = formSendRecordFieldSignalementLocalService.createFormSendRecordFieldSignalement(pk);

		signalement.setGroupId(sc.getScopeGroupId());

		if (user != null) {
			signalement.setUserName(user.getFullName());
			signalement.setUserId(sc.getUserId());
		}

		return signalement;
	}

	@Override
	public FormSendRecordFieldSignalement updateFormSendRecordFieldSignalement(FormSendRecordFieldSignalement signalement, ServiceContext sc)
			throws PortalException{
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		if (user != null) {
			signalement.setStatusByUserId(sc.getUserId());
			signalement.setStatusByUserName(user.getFullName());
		}

		signalement.setStatusDate(sc.getModifiedDate());

		signalement = formSendRecordFieldSignalementLocalService.updateFormSendRecordFieldSignalement(signalement);

		if (signalement.isApproved()){
			reindex(signalement, false);
		} else {
			reindex(signalement,true);
		}
		return signalement;
	}

	FormSendRecordFieldSignalement removeFormSendRecordFieldSignalement(long signalementId) throws PortalException{
		FormSendRecordFieldSignalement signalement =  formSendRecordFieldSignalementPersistence.remove(signalementId);
		reindex(signalement,true);
		return signalement;
	}

	public List<FormSendRecordFieldSignalement> findByFormSendRecordFieldId(long formSendRecordFieldId){
		return formSendRecordFieldSignalementPersistence.findByFormSendRecordFieldId(formSendRecordFieldId);
	}

	/**
	 * Reindex le projet dans le moteur de recherche
	 */
	private void reindex(FormSendRecordFieldSignalement signalement, boolean delete) throws SearchException {
		Indexer<FormSendRecordFieldSignalement> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(FormSendRecordFieldSignalement.class);
		if (delete) {
			indexer.delete(signalement);
		} else {
			indexer.reindex(signalement);
		}
	}
}