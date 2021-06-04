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

package eu.strasbourg.service.council.service.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.base.CouncilSessionLocalServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * The implementation of the council session local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.service.CouncilSessionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CouncilSessionLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil
 */
public class CouncilSessionLocalServiceImpl extends CouncilSessionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil} to access the council session local service.
	 */

	public final static Log log = LogFactoryUtil.getLog(CouncilSessionLocalServiceImpl.class);

	// Définit la durée pendant laquelle un conseil peut dépasser sur le jour suivant -> ici 6h
	final static private int DELTA_COUNCIL_TIME_HOURS = 6;

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public CouncilSession createCouncilSession(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = this.counterLocalService.increment();
		CouncilSession councilSession = this.councilSessionLocalService.createCouncilSession(pk);

		councilSession.setUserName(user.getFullName());
		councilSession.setUserId(sc.getUserId());
		councilSession.setGroupId(sc.getScopeGroupId());
		councilSession.setStatus(WorkflowConstants.STATUS_DRAFT);

		return councilSession;
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public CouncilSession updateCouncilSession(CouncilSession councilSession, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		councilSession.setStatusByUserId(sc.getUserId());
		councilSession.setStatusByUserName(user.getFullName());
		councilSession.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			councilSession.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			councilSession.setStatus(WorkflowConstants.STATUS_DRAFT);
		}

		// Ajout ou renommmage de la catégorie associée
		CouncilSession oldCouncilSession = this.councilSessionLocalService.fetchCouncilSession(
				councilSession.getCouncilSessionId());
		// Si elle existe déjà, c'est une mise à jour sinon on la créée
		if (oldCouncilSession != null) {
			// On déplace la catégorie si le type de conseil à changé
			// Récpère la catégorie de l'ancien conseil
			AssetVocabulary conseil = AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_SESSION, sc.getScopeGroupId());
			Type oldType = this.typeLocalService.fetchType(oldCouncilSession.getTypeId());
			AssetCategory oldTypeCategory = conseil.getCategories().stream()
					.filter(c -> c.getName().equals(oldType.getTitle())).findFirst().get();
			AssetCategory oldCouncilCategory = AssetVocabularyHelper.getChild(oldTypeCategory.getCategoryId())
					.stream().filter(c -> c.getName().equals(oldCouncilSession.getTitle())).findFirst().get();
			if (oldCouncilSession.getTypeId() != councilSession.getTypeId()) {
				// récupère la catégorie du type du nouveau conseil
				Type type = this.typeLocalService.fetchType(councilSession.getTypeId());
				AssetCategory typeCategory = conseil.getCategories().stream()
						.filter(c -> c.getName().equals(type.getTitle())).findFirst().get();
				if (Validator.isNotNull(typeCategory)) {
					AssetCategoryLocalServiceUtil.moveCategory(oldCouncilCategory.getCategoryId(), typeCategory.getCategoryId(),
							conseil.getVocabularyId(), sc);
				}
			}
			// On renomme la catégorie si le titre a été modifié
			if (!oldCouncilSession.getTitle().equals(councilSession.getTitle()))
				AssetVocabularyHelper.renameCategory(oldCouncilCategory, councilSession.getTitle());

		} else {
			// récupère la catégorie du type de conseil
			AssetVocabulary conseil = AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_SESSION, sc.getScopeGroupId());
			Type type = this.typeLocalService.fetchType(councilSession.getTypeId());
			AssetCategory typeCategory = conseil.getCategories().stream().filter(c -> c.getName().equals(type.getTitle())).findFirst().get();
			if(Validator.isNotNull(typeCategory)){
				// on créer la sous catégorie
				Map<Locale, String> titleMap = new HashMap<>();
				Locale locale = LocaleUtil.getSiteDefault();
				titleMap.put(locale, councilSession.getTitle());
				Map<Locale, String> descriptionMap = new HashMap<>();
				descriptionMap.put(locale, StringPool.BLANK);
				AssetCategoryLocalServiceUtil.addCategory(sc.getUserId(), sc.getScopeGroupId(), typeCategory.getCategoryId(), titleMap,
						descriptionMap, conseil.getVocabularyId(), null, sc);
			}
		}

		councilSession = this.councilSessionLocalService.updateCouncilSession(councilSession);

		this.updateAssetEntry(councilSession, sc);
		this.reindex(councilSession, false);

		return councilSession;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(CouncilSession councilSession, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				councilSession.getCreateDate(), // Date of creation
				councilSession.getModifiedDate(), // Date of modification
				CouncilSession.class.getName(), // Class name
				councilSession.getPrimaryKey(), // Class PK
				councilSession.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				councilSession.isApproved(), // Visible
				councilSession.getCreateDate(), // Start date
				null, // End date
				councilSession.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				councilSession.getTitle(), // Title
				councilSession.getTitle(), // Description
				councilSession.getTitle(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'entité
		this.reindex(councilSession, false);
	}

	/**
	 * Met à jour le statut de l'entité par le framework workflow
	 */
	@Override
	public CouncilSession updateStatus(long userId, long entryId, int status,
									 ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		CouncilSession councilSession = this.getCouncilSession(entryId);
		councilSession.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			councilSession.setStatusByUserId(user.getUserId());
			councilSession.setStatusByUserName(user.getFullName());
		}
		councilSession.setStatusDate(new Date());
		councilSession = this.councilSessionLocalService.updateCouncilSession(councilSession);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(CouncilSession.class.getName(), councilSession.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(councilSession, false);

		return councilSession;
	}

	/**
	 * Met à jour le statut de l'entité "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(CouncilSession councilSession, int status) throws PortalException {
		this.updateStatus(councilSession.getUserId(), councilSession.getCouncilSessionId(), status, null,
				null);
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public CouncilSession removeCouncilSession(long councilSessionId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(CouncilSession.class.getName(), councilSessionId);

		if (entry != null) {
			// Supprime les liens avec les catégories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
						categoryId, entry.getEntryId());
			}

			// Supprime les liens avec les étiquettes
			long[] tagIds = AssetEntryLocalServiceUtil
					.getAssetTagPrimaryKeys(entry.getEntryId());
			for (long tagId : tagIds) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagId,
						entry.getEntryId());
			}

			// Supprime lien avec les autres entries
			List<AssetLink> links = this.assetLinkLocalService
					.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Supprime l'AssetEntry
			AssetEntryLocalServiceUtil.deleteEntry(CouncilSession.class.getName(), councilSessionId);
		}

		// Suppression de la catégorie associée
		CouncilSession councilSession = this.councilSessionLocalService.fetchCouncilSession(councilSessionId);
		if (councilSession != null)
			AssetVocabularyHelper.removeCategory(councilSession.getTitle(), councilSession.getGroupId());

		// Supprime l'entité
		councilSession = this.councilSessionPersistence.remove(councilSessionId);

		// Supprime l'index
		this.reindex(councilSession, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				councilSession.getCompanyId(), councilSession.getGroupId(), CouncilSession.class.getName(),
				councilSession.getCouncilSessionId());

		return councilSession;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(CouncilSession councilSession, boolean delete) throws SearchException {
		Indexer<CouncilSession> indexer = IndexerRegistryUtil.nullSafeGetIndexer(CouncilSession.class);
		if (delete) {
			indexer.delete(councilSession);
		} else {
			indexer.reindex(councilSession);
		}
	}

	/**
	 * Retourne les conseils dont la date est égale ou supérieure à celle passée en paramètre
	 */
	public List<CouncilSession> getFutureCouncilSessions(Date date) {
		DynamicQuery dq = this.councilSessionLocalService.dynamicQuery();
		dq.add(RestrictionsFactoryUtil.ge("date", date));
		dq.addOrder(OrderFactoryUtil.asc("date"));

		return this.councilSessionLocalService.dynamicQuery(dq);
	}

	/**
	 * Recherche par Date de CouncilSession
	 */
	@Override
	public List<CouncilSession> findByDate(Date date){
		return this.councilSessionPersistence.findByDate(date);
	}

	/**
	 * Recherche par type de CouncilSession
	 */
	@Override
	public List<CouncilSession> findByTypeId(long typeId){
		return this.councilSessionPersistence.findByTypeId(typeId);
	}

	/**
	 * Si la date avec l'ID donné est déjà utilisé par une autre session
	 */
	@Override
	public boolean isDateAlreadyUsed(Date date, long councilSessionId) {
		boolean result = false;
		for (CouncilSession councilSession : this.findByDate(date)) {
			if (councilSession.getCouncilSessionId() != councilSessionId)
				result = true;
		}
		return result;
	}

	/**
	 * Recherche par titre de CouncilSession
	 */
	@Override
	public List<CouncilSession> findByTitre(String title){
		return this.councilSessionPersistence.findByTitle(title);
	}

	/**
	 * Si le titre avec l'ID donné est déjà utilisé par une autre session du même type de conseil
	 */
	@Override
	public boolean isTitleAlreadyUsedInCouncilTypeId(String title, long councilSessionId, long typeId) {
		boolean result = false;
		for (CouncilSession councilSession : this.findByTitre(title)) {
			if (councilSession.getCouncilSessionId() != councilSessionId && councilSession.getTypeId() == typeId)
				result = true;
		}
		return result;
	}

	/**
	 * Si le conseil a des délib
	 */
	@Override
	public boolean hasDelib(long councilSessionId) {
		boolean result = false;
		List<Deliberation> deliberations = this.deliberationLocalService.findByCouncilSessionId(councilSessionId);
		if(deliberations.size() > 0)
			result = true;
		return result;
	}

	/**
	 * Calcul de la date pour trouver le conseil
	 * Si la date du jour moins 6h est sur le jour d'avant, alors on fait la recherche sur le jour d'avant
	 */
	@Override
	public GregorianCalendar calculDateForFindCouncil() {

		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime currentDateMinusSixHours = LocalDateTime.now().minusHours(DELTA_COUNCIL_TIME_HOURS);

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);

		if (currentDateMinusSixHours.getDayOfMonth() != currentDate.getDayOfMonth()) {
			gc.setTime(Date.from(currentDateMinusSixHours.atZone(ZoneId.systemDefault()).toInstant()));
		}

		return gc;
	}
}