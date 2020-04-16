package eu.strasbourg.portlet.oidc.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalService;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component(
		immediate = true,
		property = { 
			"javax.portlet.name=" + StrasbourgPortletKeys.OIDC_BO,
			"mvc.command.name=anonymisation"
		},
		service = MVCActionCommand.class
	)
public class AnonymiseActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

		try {
			if(Boolean.parseBoolean(StrasbourgPropsUtil.getAnonymisationActivated())) {
				// Changement du groupId du contexte de la requête pour effectuer les actions dans Global
				ServiceContext sc = ServiceContextFactory.getInstance(request);
				sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getCompanyGroupId());

				// Creation de l'entree d'historique d'anonymisation
				AnonymisationHistoric anonymisationHistoric = _anonymisationHistoricLocalService.createAnonymisationHistoric(sc);

				// Effectue l'anonymisation
				this._anonymisationHistoricLocalService.doAnonymisation(sc, anonymisationHistoric);

				// Sauvegarde de l'entree
				_anonymisationHistoricLocalService.updateAnonymisationHistoric(anonymisationHistoric, sc);

				// Envoie du mail de rapport
				anonymisationHistoric.sendMail();
			}else{
				SessionErrors.add(request, "anonymisation-forbidden");
				return false;
			}

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

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
