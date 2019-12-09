package eu.strasbourg.portlet.oidc.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalService;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true,
		property = { "javax.portlet.name=" + StrasbourgPortletKeys.OIDC_BO,
		"mvc.command.name=anonymisation" },
		service = MVCResourceCommand.class)
public class AnonymisationResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
		try {
			// Changement du groupId du contexte de la requête pour effectuer les actions dans Global
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getCompanyGroupId());

			// Creation de l'entree d'historique d'anonymisation
			AnonymisationHistoric anonymisationHistoric = AnonymisationHistoricLocalServiceUtil.createAnonymisationHistoric(sc);

			// Effectue l'anonymisation
			this._anonymisationHistoricLocalService.doAnonymisation(sc, anonymisationHistoric);

			// Sauvegarde de l'entree
			AnonymisationHistoricLocalServiceUtil.updateAnonymisationHistoric(anonymisationHistoric, sc);

			// Envoie du mail de rapport
			anonymisationHistoric.sendMail();

		} catch (PortalException e) {
			_log.info(e);
		}

		return true;
	}

	private AnonymisationHistoricLocalService _anonymisationHistoricLocalService;

	@Reference(unbind = "-")
	protected void setAnonymisationHistoricLocalService(AnonymisationHistoricLocalService anonymisationHistoricLocalService) {
		_anonymisationHistoricLocalService = anonymisationHistoricLocalService;
	}

	/**
	 * le log
	 */
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
