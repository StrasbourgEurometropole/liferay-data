package eu.strasbourg.service.project.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
		immediate = true,
		service = ModelListener.class
	)
public class ProjectGroupModelListener extends BaseModelListener<Group> {
	
	/**
	 *  A la suppression d'un groupe, on supprime les entités rattachées à ce groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
    			
		// Projet
		List<Project> projects = ProjectLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Project project : projects) {
			try {
				ProjectLocalServiceUtil.removeProject(project.getProjectId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}

		// Participation
		List<Participation> participations = ParticipationLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Participation participation : participations) {
			try {
				ParticipationLocalServiceUtil.removeParticipation(participation.getParticipationId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}

		// Petition
		List<Petition> petitions= PetitionLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Petition petition : petitions) {
			try {
				PetitionLocalServiceUtil.removePetition(petition.getPetitionId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}

	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
