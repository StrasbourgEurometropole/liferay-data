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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;
import eu.strasbourg.service.formSendRecordField.service.base.FormSendRecordFieldLocalServiceBaseImpl;
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.service.LikeLocalServiceUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the form send record field local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldLocalServiceBaseImpl
 * @see eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalServiceUtil
 */
public class FormSendRecordFieldLocalServiceImpl
	extends FormSendRecordFieldLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalServiceUtil} to access the form send record field local service.
	 */

	/**
	 * Retourne tous les formSendRecordField d'un groupe
	 */
	@Override
	public List<FormSendRecordField> getByGroupId(long groupId) {
		return this.formSendRecordFieldPersistence.findByGroupId(groupId);
	}

	/**
	 * Retourne tous les formSendRecordField par contentIKd et instanceId
	 */
	@Override
	public List<FormSendRecordField> getByContentAndInstanceId(long contentId, String instanceId) {
		return this.formSendRecordFieldPersistence.findByContentAndInstanceId(contentId, instanceId);
	}

	/**
	 * Retourne tous les formSendRecordField par contentId
	 */
	@Override
	public List<FormSendRecordField> getByContentId(long contentId) {
		return this.formSendRecordFieldPersistence.findByContentId(contentId);
	}

	/**
	 * Crée un formSendRecordField vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public FormSendRecordField createFormSendRecordField(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();

		FormSendRecordField formSendRecordField = this.formSendRecordFieldLocalService.createFormSendRecordField(pk);
		formSendRecordField.setGroupId(sc.getScopeGroupId());
		formSendRecordField.setUserId(sc.getUserId());
		formSendRecordField.setStatus(WorkflowConstants.STATUS_APPROVED);
		return formSendRecordField;
	}

	/**
	 * Met à jour un formSendRecordField et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public FormSendRecordField updateFormSendRecordField(FormSendRecordField formSendRecordField, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		formSendRecordField.setStatusByUserId(sc.getUserId());
		formSendRecordField.setStatusByUserName(user.getFullName());
		formSendRecordField.setStatusDate(sc.getModifiedDate());
		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			formSendRecordField.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			formSendRecordField.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		formSendRecordField = this.formSendRecordFieldLocalService.updateFormSendRecordField(formSendRecordField);
		this.updateAssetEntry(formSendRecordField, sc);
		this.reindex(formSendRecordField, false);

		return formSendRecordField;
	}

	/**
	 * Met à jour l'AssetEntry rattachée au formSendRecordField
	 */
	private void updateAssetEntry(FormSendRecordField formSendRecordField, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(
				sc.getUserId(),
				sc.getScopeGroupId(),
				formSendRecordField.getCreateDate(),
				formSendRecordField.getModifiedDate(),
				FormSendRecordField.class.getName(),
				formSendRecordField.getPrimaryKey(),
				formSendRecordField.getUuid(),
				0,
				sc.getAssetCategoryIds(),
				sc.getAssetTagNames(),
				true,
				formSendRecordField.isApproved(),
				formSendRecordField.getCreateDate(),
				null,
				formSendRecordField.getCreateDate(),
				null,
				ContentTypes.TEXT_HTML,
				formSendRecordField.getUserName(),
				formSendRecordField.getResponse(),
				formSendRecordField.getResponse(),
				null,
				null,
				0,
				0,
				null); // Priority

		// Réindexe le projet
		this.reindex(formSendRecordField, false);
	}

	/**
	 * Reindex le formSendRecordField dans le moteur de recherche
	 */
	private void reindex(FormSendRecordField formSendRecordField, boolean delete) throws SearchException {
		Indexer<FormSendRecordField> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(FormSendRecordField.class);
		if (delete) {
			indexer.delete(formSendRecordField);
		} else {
			indexer.reindex(formSendRecordField);
		}
	}

	/**
	 * Met à jour le statut du formSendRecordField par le framework workflow
	 */
	@Override
	public FormSendRecordField updateStatus(long userId, long entryId, int status,
											ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		FormSendRecordField formSendRecordField = this.getFormSendRecordField(entryId);
		formSendRecordField.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			formSendRecordField.setStatusByUserId(user.getUserId());
			formSendRecordField.setStatusByUserName(user.getFullName());
		}
		formSendRecordField.setStatusDate(new Date());
		formSendRecordField = this.formSendRecordFieldLocalService.updateFormSendRecordField(formSendRecordField);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(FormSendRecordField.class.getName(), formSendRecordField.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(formSendRecordField, false);

		return formSendRecordField;
	}

	/**
	 * Met à jour le statut du formSendRecordField "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(FormSendRecordField formSendRecordField, int status) throws PortalException {
		this.updateStatus(formSendRecordField.getUserId(), formSendRecordField.getFormSendRecordFieldId(), status, null,
				null);
	}

	/**
	 * Supprime un lien
	 */
	@Override
	public FormSendRecordField removeFormSendRecordField(long formSendRecordFieldId) throws PortalException {
		AssetEntry entry = this.assetEntryLocalService
				.fetchEntry(FormSendRecordField.class.getName(), formSendRecordFieldId);

		if (entry != null) {
			// Supprime l'AssetEntry
			this.assetEntryLocalService.deleteEntry(entry);
		}

		// Supprime le lien
		FormSendRecordField formSendRecordField = this.formSendRecordFieldPersistence.remove(formSendRecordFieldId);

		//supprime son indexation
		this.reindex(formSendRecordField,true);

		//Supprime les signalements
//		List<Signalement> signalements = SignalementLocalServiceUtil.findByFormSendRecordFieldId(formSendRecordFieldId);
//		if (signalements!=null&&!signalements.isEmpty()){
//			signalements.forEach(SignalementLocalServiceUtil::deleteSignalement);
//		}

		//supprime les likes
		List<Like> likes = formSendRecordField.getLikes();
		if (likes!=null&&!likes.isEmpty()){
			likes.forEach(LikeLocalServiceUtil::deleteLike);
		}

		return formSendRecordField;
	}
}