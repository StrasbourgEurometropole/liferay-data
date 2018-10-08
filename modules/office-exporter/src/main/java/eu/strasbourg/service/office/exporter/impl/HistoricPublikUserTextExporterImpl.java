package eu.strasbourg.service.office.exporter.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventParticipationLocalService;
import eu.strasbourg.service.agenda.service.EventParticipationLocalServiceUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.office.exporter.api.HistoricPublikUserTextExporter;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.service.ProjectFollowedLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;

@Component(immediate = true, property = {}, service = HistoricPublikUserTextExporter.class)
public class HistoricPublikUserTextExporterImpl implements HistoricPublikUserTextExporter {

	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language", this.getClass().getClassLoader());

	public void exportHistoric(OutputStream os, long publikUserIdsStr) {
		if (Validator.isNotNull(publikUserIdsStr)) {
			PublikUser publikUser = PublikUserLocalServiceUtil.fetchPublikUser(publikUserIdsStr);
			if (publikUser != null) {
				try {
					// Récupération du pacte
					if (Validator.isNotNull(publikUser.getPactSignature())) {
						DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
						String ligne = "Pacte signé le : " + df.format(publikUser.getPactSignature());
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						os.write(System.getProperty("line.separator").getBytes());
					}

					// Récupération des projets suivis
					List<ProjectFollowed> projectsFollowed = ProjectFollowedLocalServiceUtil.getByPublikId(publikUser.getPublikId()).stream()
							.sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!projectsFollowed.isEmpty()) {
						String ligne = "Projets suivis :";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (ProjectFollowed projectFollowed : projectsFollowed) {
							// récupération du projet
							Project project = ProjectLocalServiceUtil.fetchProject(projectFollowed.getProjectId());
							if(Validator.isNotNull(project)) {
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
					List<EventParticipation> eventParticipations = EventParticipationLocalServiceUtil.getByPublikUser(publikUser.getPublikId()).stream()
							.sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!eventParticipations.isEmpty()) {
						String ligne = "Participation à des évènements :";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (EventParticipation eventParticipation : eventParticipations) {
							// récupération de l'évènement
							Event event = EventLocalServiceUtil.fetchEvent(eventParticipation.getEventId());
							if(Validator.isNotNull(event)) {
								ligne = eventParticipation.getCreateDate() + " - " + event.getTitle();
							} else {
								ligne = eventParticipation.getCreateDate() + " - " + eventParticipation.getEventId();
							}
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
					}

					// Récupération des commentaires
					List<Comment> comments = CommentLocalServiceUtil.getByPublikId(publikUser.getPublikId()).stream()
							.sorted((c1, c2) -> c1.getCreateDate().compareTo(c2.getCreateDate()))
							.collect(Collectors.toList());
					if (!comments.isEmpty()) {
						String ligne = "Commentaires :";
						os.write(ligne.getBytes());
						os.write(System.getProperty("line.separator").getBytes());
						for (Comment comment : comments) {
							ligne = comment.getCreateDate() + " - " + comment.getComment();
							os.write(ligne.getBytes());
							os.write(System.getProperty("line.separator").getBytes());
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
