package eu.strasbourg.service.office.exporter.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventParticipationLocalServiceUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.service.LikeLocalServiceUtil;
import eu.strasbourg.service.office.exporter.api.HistoricPublikUserTextExporter;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.InitiativeHelp;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.model.Signataire;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeHelpLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectFollowedLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.service.project.service.SignataireLocalServiceUtil;

@Component(immediate = true, property = {}, service = HistoricPublikUserTextExporter.class)
public class HistoricPublikUserTextExporterImpl implements HistoricPublikUserTextExporter {

	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language", this.getClass().getClassLoader());

	public void exportHistoric(OutputStream os, long publikUserIdsStr) {
		if (Validator.isNotNull(publikUserIdsStr)) {
			PublikUser publikUser = PublikUserLocalServiceUtil.fetchPublikUser(publikUserIdsStr);
			if (publikUser != null) {
				try {
					String ligne =  LanguageUtil.get(bundle,"historique") + " :  " + publikUser.getFirstName() + " "
							+ publikUser.getLastName();
					os.write(ligne.getBytes());
					os.write(System.getProperty("line.separator").getBytes());
					os.write(System.getProperty("line.separator").getBytes());

					// Récupération du pacte
					if (Validator.isNotNull(publikUser.getPactSignature())) {
						DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
						ligne = LanguageUtil.get(bundle,"pacte") + " : " + df.format(publikUser.getPactSignature());
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						os.write(System.getProperty("line.separator").getBytes());
					}

					// Récupération des projets suivis
					List<ProjectFollowed> projectsFollowed = ProjectFollowedLocalServiceUtil
							.getByPublikId(publikUser.getPublikId()).stream()
							.sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!projectsFollowed.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"projets") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (ProjectFollowed projectFollowed : projectsFollowed) {
							// récupération du projet
							Project project = ProjectLocalServiceUtil.fetchProject(projectFollowed.getProjectId());
							if (Validator.isNotNull(project)) {
								ligne = projectFollowed.getCreateDate() + " - " + project.getTitle();
							} else {
								ligne = projectFollowed.getCreateDate() + " - " + projectFollowed.getProjectId();
							}
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
						os.write(System.getProperty("line.separator").getBytes());
					}

					// Récupération des participation à des évènements
					List<EventParticipation> eventParticipations = EventParticipationLocalServiceUtil
							.getByPublikUser(publikUser.getPublikId()).stream()
							.sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!eventParticipations.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"participations") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (EventParticipation eventParticipation : eventParticipations) {
							// récupération de l'évènement
							Event event = EventLocalServiceUtil.fetchEvent(eventParticipation.getEventId());
							if (Validator.isNotNull(event)) {
								ligne = eventParticipation.getCreateDate() + " - " + event.getTitle(Locale.FRANCE);
							} else {
								ligne = eventParticipation.getCreateDate() + " - " + eventParticipation.getEventId();
							}
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
						os.write(System.getProperty("line.separator").getBytes());
					}

					// Récupération des pétitions
					List<Petition> petitions = PetitionLocalServiceUtil.getByPublikUserID(publikUser.getPublikId())
							.stream().sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!petitions.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"petitions") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (Petition petition : petitions) {
							ligne = petition.getCreateDate() + " - " + petition.getTitle();
							if (petition.isApproved())
								ligne += " : " + LanguageUtil.get(bundle,"approuved");
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
						os.write(System.getProperty("line.separator").getBytes());
					}

					// Récupération des signatures aux pétitions
					List<Signataire> signataires = SignataireLocalServiceUtil
							.getSignataireByPublikId(publikUser.getPublikId()).stream()
							.sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!signataires.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"petitions-signed") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (Signataire signataire : signataires) {
							// récupération de la pétition
							Petition petition = PetitionLocalServiceUtil.fetchPetition(signataire.getPetitionId());
							if (Validator.isNotNull(petition)) {
								ligne = signataire.getCreateDate() + " - " + petition.getTitle();
							} else {
								ligne = signataire.getCreateDate() + " - " + signataire.getPetitionId();
							}
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
						os.write(System.getProperty("line.separator").getBytes());
					}
					
					// Récupération des BP
					List<BudgetParticipatif> budgets = BudgetParticipatifLocalServiceUtil.getByPublikUserID(publikUser.getPublikId())
							.stream().sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!budgets.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"budgets") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (BudgetParticipatif budget : budgets) {
							ligne = budget.getCreateDate() + " - " + budget.getTitle();
							if (budget.isApproved())
								ligne += " : " + LanguageUtil.get(bundle,"approuved");
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
						os.write(System.getProperty("line.separator").getBytes());
					}
					
					// Récupération des BP votés
					List<BudgetSupport> supports = BudgetSupportLocalServiceUtil.getBudgetSupportByPublikId(publikUser.getPublikId())
							.stream().sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!supports.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"budgets-signed") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (BudgetSupport support : supports) {
							// récupération du bp
							BudgetParticipatif budget = BudgetParticipatifLocalServiceUtil.fetchBudgetParticipatif(support.getBudgetParticipatifId());
							if (Validator.isNotNull(budget)) {
								ligne = support.getCreateDate() + " - " + budget.getTitle();
							} else {
								ligne = support.getCreateDate() + " - " + support.getBudgetParticipatifId();
							}
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
						os.write(System.getProperty("line.separator").getBytes());
					}
					
					
					// Récupération des initiatives
					List<Initiative> initiatives = InitiativeLocalServiceUtil.getByPublikUserID(publikUser.getPublikId())
							.stream().sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!initiatives.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"initiatives") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (Initiative initiative : initiatives) {
							ligne = initiative.getCreateDate() + " - " + initiative.getTitle();
							if (initiative.isApproved())
								ligne += " : " + LanguageUtil.get(bundle,"approuved");
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
						os.write(System.getProperty("line.separator").getBytes());
					}
					
					// Récupération des initiatives aidées
					List<InitiativeHelp> helps = InitiativeHelpLocalServiceUtil.getByPublikUserId(publikUser.getPublikId())
							.stream().sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!supports.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"initiatives-help") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (InitiativeHelp help : helps) {
							// récupération de l'initiative
							Initiative initiative = InitiativeLocalServiceUtil.fetchInitiative(help.getInitiativeId());
							if (Validator.isNotNull(initiative)) {
								ligne = help.getCreateDate() + " - " + initiative.getTitle();
							} else {
								ligne = help.getCreateDate() + " - " + help.getInitiativeId();
							}
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
						os.write(System.getProperty("line.separator").getBytes());
					}
					
					
					// Récupération des commentaires
					List<Comment> comments = CommentLocalServiceUtil.getByPublikId(publikUser.getPublikId()).stream()
							.sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!comments.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"commentaires") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (Comment comment : comments) {
							ligne = comment.getCreateDate() + " - " + comment.getText() + " - "
									+ comment.getAssetEntryTitle();
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
						os.write(System.getProperty("line.separator").getBytes());
					}

					// Récupération des likes/dislikes
					List<Like> likes = LikeLocalServiceUtil.getByPublikUser(publikUser.getPublikId());
					if (!likes.isEmpty()) {
						ligne = LanguageUtil.get(bundle,"likes-dislikes") + " : ";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (Like like : likes) {
							ligne = like.getTitle() + " - ";
							if (like.isIsDislike())
								ligne += LanguageUtil.get(bundle,"dislike");
							else
								ligne += LanguageUtil.get(bundle,"like");
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
					}
				} catch (IOException e) {
					_log.error(e.getMessage(), e);
				}

			}
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
