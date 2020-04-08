package eu.strasbourg.service.oidc.scheduler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalService;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalServiceUtil;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.*;

import java.util.Date;

/**
 * Passe au statut "APPROVED" tous les événements et les manifestations dont la
 * publication a été programmée et dont la date de publication est désormais
 * dépassée
 */
@Component(immediate = true, service = PublikUsersAnonymized.class)
public class PublikUsersAnonymized extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Création du trigger "Tous les jours à 3h30"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, null, null, "0 30 3 * * ?");

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
				listenerClass, trigger);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		log.info("Start anonymisation");

		if(Boolean.parseBoolean(StrasbourgPropsUtil.getAnonymisationActivated())) {

			// Creation du contexte de la requete pour effectuer les actions dans Global
			ServiceContext sc = new ServiceContext();
			try {
				Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
				sc.setCompanyId(defaultCompany.getCompanyId());
				sc.setScopeGroupId(defaultCompany.getGroup().getGroupId());
				sc.setUserId(UserLocalServiceUtil.getDefaultUserId(sc.getCompanyId()));
				sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
				sc.setModifiedDate(new Date());
			} catch (PortalException e) {
				log.error(e);
			}

			// Creation de l'entree d'historique d'anonymisation
			AnonymisationHistoric anonymisationHistoric = AnonymisationHistoricLocalServiceUtil.createAnonymisationHistoric(sc);

			// Effectue l'anonymisation
			this._anonymisationHistoricLocalService.doAnonymisation(sc, anonymisationHistoric);

			// Sauvegarde de l'entree d'historique d'anonymisation
			this._anonymisationHistoricLocalService.updateAnonymisationHistoric(anonymisationHistoric, sc);

			// Envoie du mail de rapport
			anonymisationHistoric.sendMail();
		}else{
			log.info("L’anonymisation utilisateur est désactivée - Fin du traitement ");
		}

		log.info("Finish anonymisation");
	}

	@Reference(unbind = "-")
	protected void setAnonymisationHistoricLocalService(AnonymisationHistoricLocalService anonymisationHistoricLocalService) {
		_anonymisationHistoricLocalService = anonymisationHistoricLocalService;
	}

	@Reference(unbind = "-")
	protected void setPublikUserLocalService(PublikUserLocalService publikUserLocalService) {
		_publikUserLocalService = publikUserLocalService;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
			SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	private volatile SchedulerEngineHelper _schedulerEngineHelper;
	private AnonymisationHistoricLocalService _anonymisationHistoricLocalService;
	private PublikUserLocalService _publikUserLocalService;
	private TriggerFactory _triggerFactory;
	private Log log = LogFactoryUtil.getLog(this.getClass());
}
