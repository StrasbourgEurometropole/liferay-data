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

package eu.strasbourg.service.oidc.service.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.service.EventParticipationLocalServiceUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.comment.service.SignalementLocalServiceUtil;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldSignalementLocalServiceUtil;
import eu.strasbourg.service.interest.model.UserInterest;
import eu.strasbourg.service.interest.service.UserInterestLocalServiceUtil;
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.service.LikeLocalServiceUtil;
import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.UserNotificationStatusLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.model.impl.PublikUserImpl;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.oidc.service.base.PublikUserLocalServiceBaseImpl;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Signataire;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.InitiativeHelp;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectFollowedLocalServiceUtil;
import eu.strasbourg.service.project.service.SignataireLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeHelpLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The implementation of the publik user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.oidc.service.PublikUserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PublikUserLocalServiceBaseImpl
 * @see eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil
 */
public class PublikUserLocalServiceImpl extends PublikUserLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil} to access
	 * the publik user local service.
	 */

	@Override
	public PublikUser createPublikUser() {
		long pk = this.counterLocalService.increment();
		return this.createPublikUser(pk);
	}
	
	/**
	 * Met à jour un utilisateur Publik et l'enregistre en base
	 * @return L'utilisateur Publik modifié
	 */
	@Override
	public PublikUser updatePublikUser(PublikUser publikUser, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		
		publikUser.setUserName(user.getFullName());
		publikUser.setUserId(sc.getUserId());
		
		publikUser = this.publikUserLocalService.updatePublikUser(publikUser);
		
		return publikUser;
	}

	@Override
	public PublikUser getByPublikUserId(String publikUserId) {
		return this.publikUserPersistence.fetchByPublikId(publikUserId);
	}

	/**
	 * Enregistre ou update l'utilisateur en base
	 */
	@Override
	public void updateUserInfoInDatabase(String internalId, String accessToken, String givenName,
										  String familyName, String email, String photo) {
		if (internalId != null && internalId.length() > 0) {
			PublikUser user = publikUserLocalService.getByPublikUserId(internalId);
			if (user == null) {
				user = publikUserLocalService.createPublikUser();
				user.setPublikId(internalId);
			}
			user.setAccessToken(accessToken);
			user.setFirstName(givenName);
			user.setLastName(familyName);
			user.setEmail(email);
			user.setImageURL(photo);
			publikUserLocalService.updatePublikUser(user);
		}
	}
	
	/**
	 * Rechercher tous les utilisateurs Publik directement via l'outil de persistance
	 * sans critere
	 * @return Liste de tous les utilisateurs Publik
	 */
	@Override
	public List<PublikUser> getAllPublikUsers() {
		return this.publikUserPersistence.findAll();
	}

	/**
	 * Rechercher des utilisateurs Publik directement via l'outil de persistance
	 * avec pagination
	 * @param start Debut de l'index de recherche pour la pagination
	 * @param end Fin de l'index de recherche pour la pagination
	 * @param sortField Champ de tri utilisé
	 * @param isSortDesc La liste est-elle triée par ordre decroissant ?
	 * @return Liste des utilisateurs trouvés
	 */
	@Override
	public List<PublikUser> getPublikUsers(int start, int end, String sortField, boolean isSortDesc) {
		
		// Creation du comparateur permettant le tri et la selection de l'ordre de tri
		OrderByComparator<PublikUser> orderByComparator = OrderByComparatorFactoryUtil.create(
				PublikUserImpl.TABLE_NAME,
				sortField, 
				isSortDesc);
		
		// Lancement de la recherche
		List <PublikUser> results = this.publikUserPersistence.findAll(start, end, orderByComparator);
		
		return results;
	}
	
	/**
	 * Rechercher des utilisateurs Publik directement via l'outil de persistance
	 * sans pagination
	 * @param sortField Champ de tri utilisé
	 * @param isSortDesc La liste est-elle triée par ordre decroissant ?
	 * @return Liste des utilisateurs trouvés
	 */
	@Override
	public List<PublikUser> getPublikUsers(String sortField, boolean isSortDesc) {
		
		// Creation du comparateur permettant le tri et la selection de l'ordre de tri
		OrderByComparator<PublikUser> orderByComparator = OrderByComparatorFactoryUtil.create(
				PublikUserImpl.TABLE_NAME,
				sortField, 
				isSortDesc);
		
		// Lancement de la recherche
		List <PublikUser> results = this.publikUserPersistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, orderByComparator);
		
		return results;
	}
	
	/**
	 * Rechercher des utilisateurs Publik directement via l'outil de persistance
	 * avec recherche et pagination
	 * @param start Debut de l'index de recherche pour la pagination
	 * @param end Fin de l'index de recherche pour la pagination
	 * @param keywords Mots-clefs pour la recherche sur le nom, prenom, email
	 * @param sortField Champ de tri utilisé
	 * @param isSortDesc La liste est-elle triée par ordre decroissant ?
	 * @return Liste des utilisateurs trouvés
	 */
	@Override
	public List<PublikUser> getPublikUsers(int start, int end, String keywords, String sortField, boolean isSortDesc) {
		
		if (keywords != null && !keywords.isEmpty()) {
			// Creation du comparateur permettant le tri et la selection de l'ordre de tri
			OrderByComparator<PublikUser> orderByComparator = OrderByComparatorFactoryUtil.create(
					PublikUserImpl.TABLE_NAME,
					sortField, 
					isSortDesc);
		
			// Creation de la recherche dynamique utilisant le mot clef
			// cf : DynamicQuery de Hibernate
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PublikUserImpl.class,
					PortalClassLoaderUtil.getClassLoader());
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
			disjunction.add(PropertyFactoryUtil.forName("lastName").like("%"+keywords+"%"));
			disjunction.add(PropertyFactoryUtil.forName("firstName").like("%"+keywords+"%"));
			disjunction.add(PropertyFactoryUtil.forName("email").like("%"+keywords+"%"));
			dynamicQuery.add(disjunction);
		
			// Lancement de la recherche
			List <PublikUser> results = this.publikUserPersistence.findWithDynamicQuery(dynamicQuery, start, end, orderByComparator);
			
			return results;
		} else {
			return this.getPublikUsers(start, end, sortField, isSortDesc);
		}
	}
	
	/**
	 * Rechercher tous les utilisateurs Publik directement via l'outil de persistance
	 * avec recherche mais sans pagination
	 * @param keywords Mots-clefs pour la recherche sur le nom, prenom, email
	 * @param sortField Champ de tri utilisé
	 * @param isSortDesc La liste est-elle triée par ordre decroissant ?
	 * @return Liste des utilisateurs trouvés
	 */
	@Override
	public List<PublikUser> getPublikUsers(String keywords, String sortField, boolean isSortDesc) {
		
		if (keywords != null && !keywords.isEmpty()) {
			// Creation du comparateur permettant le tri et la selection de l'ordre de tri
			OrderByComparator<PublikUser> orderByComparator = OrderByComparatorFactoryUtil.create(
					PublikUserImpl.TABLE_NAME,
					sortField, 
					isSortDesc);
			
			// Creation de la recherche dynamique utilisant le mot clef
			// cf : DynamicQuery de Hibernate
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PublikUserImpl.class,
					PortalClassLoaderUtil.getClassLoader());
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
			disjunction.add(PropertyFactoryUtil.forName("lastName").like("%"+keywords+"%"));
			disjunction.add(PropertyFactoryUtil.forName("firstName").like("%"+keywords+"%"));
			disjunction.add(PropertyFactoryUtil.forName("email").like("%"+keywords+"%"));
			dynamicQuery.add(disjunction);
			
			// Lancement de la recherche
			List <PublikUser> results = this.publikUserPersistence.findWithDynamicQuery(dynamicQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS, orderByComparator);
			
			return results;
		} else {
			return this.getPublikUsers(sortField, isSortDesc);
		}
		
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public PublikUser removePublikUser(String publikUserId) {

		// Supprime le lien avec les intérêts
		List<UserInterest> userInterests = UserInterestLocalServiceUtil.getByPublikUserId(publikUserId);
		for (UserInterest userInterest : userInterests) {
			UserInterestLocalServiceUtil.deleteUserInterest(userInterest);
		}

		// Supprime le lien avec les notifications
		List<UserNotificationStatus> notifications = UserNotificationStatusLocalServiceUtil.getByPublikUserId(publikUserId);
		for (UserNotificationStatus notification : notifications) {
			UserNotificationStatusLocalServiceUtil.deleteUserNotificationStatus(notification);
		}

		// Supprimé l'entité
		PublikUser user = this.getByPublikUserId(publikUserId);
		this.publikUserPersistence.remove(user);


		return user;
	}

	private List<PublikUser> getUserHasSignedPacte(){
		List<PublikUser> publikUserList = getAllPublikUsers();
		return publikUserList.stream().filter(publikUser -> publikUser.getPactSignature()!=null).collect(Collectors.toList());
	}

	/**
	 * méthode permettant de récupererer le nombre de signataire.
	 * @return le nombre de signataire.
	 */
	public long getCountUserHasSignedPacte(){
		List<PublikUser> publikUserList = getUserHasSignedPacte();
		return publikUserList.size();
	}

	/**
	 * Anonymise l'utilisateur pour placit
	 * suppression de la signature du pacte,
	 * anonymisation de ProjectFollowed, EventParticipation, Petition, Signataire, BudgetParticipatif,
	 *     BudgetSupport, Initiative, InitiativeHelp, Comment et Like
	 */
	@Override
	public void anonymisedUserPlacit(PublikUser anonymUser, PublikUser publikUser) {

		// Anonymisation des informations utilisateur dans publikuser
		publikUser.setPactSignature(null);
		publikUser.setPactDisplay(false);
		PublikUserLocalServiceUtil.updatePublikUser(publikUser);

		// Anonymisation des informations utilisateur dans projets suivis
		List<ProjectFollowed> projectsFollowed = ProjectFollowedLocalServiceUtil
				.getByPublikId(publikUser.getPublikId());
		if (!projectsFollowed.isEmpty()) {
			for (ProjectFollowed projectFollowed : projectsFollowed) {
				projectFollowed.setPublikUserId(anonymUser.getPublikId());
				// Mise à jour en base
				ProjectFollowedLocalServiceUtil.updateProjectFollowed(projectFollowed);
			}
		}

		// Anonymisation des informations utilisateur dans participation à des
		// évènements
		List<EventParticipation> eventParticipations = EventParticipationLocalServiceUtil
				.getByPublikUser(publikUser.getPublikId());
		if (!eventParticipations.isEmpty()) {
			for (EventParticipation eventParticipation : eventParticipations) {
				eventParticipation.setPublikUserId(anonymUser.getPublikId());
				// Mise à jour en base
				EventParticipationLocalServiceUtil.updateEventParticipation(eventParticipation);
			}
		}

		// Anonymisation des informations utilisateur dans pétition
		List<Petition> petitions = PetitionLocalServiceUtil.getByPublikUserID(publikUser.getPublikId());
		if (!petitions.isEmpty()) {
			for (Petition petition : petitions) {
				petition.setPetitionnaireFirstname(anonymUser.getFirstName());
				petition.setPetitionnaireLastname(anonymUser.getLastName());
				petition.setPetitionnaireAdresse("");
				petition.setPetitionnairePostalCode(0);
				petition.setPetitionnaireCity("");
				petition.setPetitionnaireBirthday(null);
				petition.setPetitionnairePhone("");
				petition.setPetitionnaireEmail(anonymUser.getEmail());
				petition.setPublikId(anonymUser.getPublikId());
				// Mise à jour en base
				PetitionLocalServiceUtil.updatePetition(petition);
			}
		}

		// Anonymisation des informations utilisateur dans les signatures des pétitions
		List<Signataire> signataires = SignataireLocalServiceUtil
				.getSignataireByPublikId(publikUser.getPublikId());
		if (!signataires.isEmpty()) {
			for (Signataire signataire : signataires) {
				signataire.setSignataireFirstname(anonymUser.getFirstName());
				signataire.setSignataireName(anonymUser.getLastName());
				signataire.setAddress("");
				signataire.setPostalCode(0);
				signataire.setCity("");
				signataire.setBirthday(null);
				signataire.setPhone("");
				signataire.setMobilePhone("");
				signataire.setMail(anonymUser.getEmail());
				signataire.setPublikUserId(anonymUser.getPublikId());
				// Mise à jour en base
				SignataireLocalServiceUtil.updateSignataire(signataire);
			}
		}

		// Anonymisation des informations utilisateur dans les budgets participatifs
		List<BudgetParticipatif> budgetParticipatifs = BudgetParticipatifLocalServiceUtil.
				getByPublikUserID(publikUser.getPublikId());
		if (!budgetParticipatifs.isEmpty()) {
			for (BudgetParticipatif budgetParticipatif : budgetParticipatifs) {
				budgetParticipatif.setCitoyenFirstname(anonymUser.getFirstName());
				budgetParticipatif.setCitoyenLastname(anonymUser.getLastName());
				budgetParticipatif.setCitoyenAdresse("");
				budgetParticipatif.setCitoyenPostalCode(0);
				budgetParticipatif.setCitoyenCity("");
				budgetParticipatif.setCitoyenBirthday(null);
				budgetParticipatif.setCitoyenPhone("");
				budgetParticipatif.setCitoyenMobile("");
				budgetParticipatif.setCitoyenEmail(anonymUser.getEmail());
				budgetParticipatif.setPublikId(anonymUser.getPublikId());
				// Mise à jour en base
				BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(budgetParticipatif);
			}
		}

		// Anonymisation des informations utilisateur dans les soutiens des budget participatif
		List<BudgetSupport> budgetSupports = BudgetSupportLocalServiceUtil.
				getBudgetSupportByPublikId(publikUser.getPublikId());
		if (!budgetSupports.isEmpty()) {
			for (BudgetSupport budgetSupport : budgetSupports) {
				budgetSupport.setCitoyenFirstname(anonymUser.getFirstName());
				budgetSupport.setCitoyenLastName(anonymUser.getLastName());
				budgetSupport.setCitoyenAddress("");
				budgetSupport.setCitoyenPostalCode(0);
				budgetSupport.setCitoyenCity("");
				budgetSupport.setCitoyenBirthday(null);
				budgetSupport.setCitoyenPhone("");
				budgetSupport.setCitoyenMobilePhone("");
				budgetSupport.setCitoyenMail(anonymUser.getEmail());
				budgetSupport.setPublikUserId(anonymUser.getPublikId());
				// Mise à jour en base
				BudgetSupportLocalServiceUtil.updateBudgetSupport(budgetSupport);
			}
		}

		// Anonymisation des informations utilisateur dans les initiatives
		List<Initiative> initiatives = InitiativeLocalServiceUtil.
				getByPublikUserID(publikUser.getPublikId());
		if (!initiatives.isEmpty()) {
			for (Initiative initiative : initiatives) {
				initiative.setPublikId(anonymUser.getPublikId());
				// Mise à jour en base
				InitiativeLocalServiceUtil.updateInitiative(initiative);
			}
		}

		// Anonymisation des informations utilisateur dans les aides aux initiatives
		List<InitiativeHelp> helps = InitiativeHelpLocalServiceUtil.
				getByPublikUserId(publikUser.getPublikId());
		if (!helps.isEmpty()) {
			for (InitiativeHelp help : helps) {
				help.setPublikUserId(anonymUser.getPublikId());
				// Mise à jour en base
				InitiativeHelpLocalServiceUtil.updateInitiativeHelp(help);
			}
		}

		// Anonymisation des informations utilisateur dans les commentaires
		List<Comment> comments = CommentLocalServiceUtil.getByPublikId(publikUser.getPublikId());
		if (!comments.isEmpty()) {
			for (Comment comment : comments) {
				comment.setPublikId(anonymUser.getPublikId());
				// Mise à jour en base
				CommentLocalServiceUtil.updateComment(comment);
			}
		}

		// Anonymisation des informations utilisateur dans les likes/dislikes
		List<Like> likes = LikeLocalServiceUtil.getByPublikUser(publikUser.getPublikId());
		if (!likes.isEmpty()) {
			for (Like like : likes) {
				like.setPublikUserId(anonymUser.getPublikId());
				// Mise à jour en base
				try {
					LikeLocalServiceUtil.updateLike(like);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
		}

		// Anonymisation des informations utilisateur dans les signalements
		List<Signalement> signalements = SignalementLocalServiceUtil.getByPublikId(publikUser.getPublikId());
		if (!signalements.isEmpty()) {
			for (Signalement signalement : signalements) {
				signalement.setPublikId(anonymUser.getPublikId());
				// Mise à jour en base
				SignalementLocalServiceUtil.updateSignalement(signalement);
			}
		}

		// Anonymisation des informations utilisateur dans les signalements de réponses à un formulaire
		List<FormSendRecordFieldSignalement> formSendRecordFieldSignalements = FormSendRecordFieldSignalementLocalServiceUtil.getByPublikId(publikUser.getPublikId());
		if (!signalements.isEmpty()) {
			for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : formSendRecordFieldSignalements) {
				formSendRecordFieldSignalement.setPublikId(anonymUser.getPublikId());
				// Mise à jour en base
				FormSendRecordFieldSignalementLocalServiceUtil.updateFormSendRecordFieldSignalement(formSendRecordFieldSignalement);
			}
		}
	}

}