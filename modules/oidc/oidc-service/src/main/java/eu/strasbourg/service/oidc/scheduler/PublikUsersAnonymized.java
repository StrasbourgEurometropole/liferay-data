package eu.strasbourg.service.oidc.scheduler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalService;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalServiceUtil;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import org.osgi.service.component.annotations.*;

import java.util.Date;

/**
 * Passe au statut "APPROVED" tous les événements et les manifestations dont la
 * publication a été programmée et dont la date de publication est désormais
 * dépassée
 */
@Component(immediate = true, service = PublikUsersAnonymized.class)
public class PublikUsersAnonymized extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
		// Tous les jours a 4h
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(
				getEventListenerClass(), getEventListenerClass(), "0 30 3 * * ?"));

		_schedulerEngineHelper.register(this, schedulerEntryImpl,
				DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	private Log log = LogFactoryUtil.getLog(PublikUsersAnonymized.class);

	@Override
	protected void doReceive(Message message) throws Exception {
		log.info("Start anonymisation");

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
			_log.error(e);
		}

		// Creation de l'entree d'historique d'anonymisation
		AnonymisationHistoric anonymisationHistoric = AnonymisationHistoricLocalServiceUtil.createAnonymisationHistoric(sc);

		// Effectue l'anonymisation
		this._anonymisationHistoricLocalService.doAnonymisation(sc, anonymisationHistoric);

		// Sauvegarde de l'entree d'historique d'anonymisation
		this._anonymisationHistoricLocalService.updateAnonymisationHistoric(anonymisationHistoric, sc);

		// Envoie du mail de rapport
		anonymisationHistoric.sendMail();

		log.info("Finish anonymisation");
	}

	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper _schedulerEngineHelper;

	@Reference(unbind = "-")
	private AnonymisationHistoricLocalService _anonymisationHistoricLocalService;

	@Reference(unbind = "-")
	private PublikUserLocalService _publikUserLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass());

}
