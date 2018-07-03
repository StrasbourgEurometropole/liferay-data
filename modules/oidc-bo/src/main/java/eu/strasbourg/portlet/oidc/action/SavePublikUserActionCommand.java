package eu.strasbourg.portlet.oidc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		immediate = true,
		property = { 
			"javax.portlet.name=" + StrasbourgPortletKeys.OIDC_BO,
			"mvc.command.name=savePublikUser"
		},
		service = MVCActionCommand.class
	)
public class SavePublikUserActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {
		
		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			
			// Validation
			boolean isValid = validate(request);
			if (!isValid) {
				// Si pas valide : on reste sur la page d'édition
				PortalUtil.copyRequestParameters(request, response);

				ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
				String portletName = (String) request
					.getAttribute(WebKeys.PORTLET_ID);
				PortletURL returnURL = PortletURLFactoryUtil.create(request,
					portletName, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);

				response.setRenderParameter("returnURL", returnURL.toString());
				response.setRenderParameter("cmd","editPublikUser");
				response.setRenderParameter("mvcPath","/oidc-bo-edit-publikuser.jsp");
				return false;
			}

			// Edition du projet
			long publikUserLiferayId = ParamUtil.getLong(request, "publikUserLiferayId");
			PublikUser publikUser;
			publikUser = _publikUserLocalService.getPublikUser(publikUserLiferayId);
			
			// ---------------------------------------------------------------
			// -------------------------- BANNISSEMENT -----------------------
			// ---------------------------------------------------------------

			// Definir le format de recuperation de date
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			// Date de bannissement
			Date banishDate = ParamUtil.getDate(request, "banishDate", dateFormat);
			
			Calendar todayDate = Calendar.getInstance();
			Calendar testDate = Calendar.getInstance();
			testDate.setTime(banishDate);
			
			boolean sameDay = todayDate.get(Calendar.YEAR) == testDate.get(Calendar.YEAR) &&
					todayDate.get(Calendar.DAY_OF_YEAR) == testDate.get(Calendar.DAY_OF_YEAR);
			
			if(!sameDay)
				publikUser.setBanishDate(banishDate);
			else
				publikUser.setBanishDate(null);

			// Description du bannissement
			String banishDescription = ParamUtil.getString(request, "banishDescription");
			publikUser.setBanishDescription(banishDescription);

			_publikUserLocalService.updatePublikUser(publikUser, sc);

		} catch (PortalException e) {
			_log.error(e);
		}
		return true;
	}
	
	/**
	 * Validation des champs obligatoires
	 */
	private boolean validate(ActionRequest request) {
		boolean isValid = true;
		
		// Aucun pour le moment
		
		return isValid;
	}
	
	@Reference(unbind = "-")
	protected void setPublikUserLocalService(PublikUserLocalService publikUserLocalService) {
		_publikUserLocalService = publikUserLocalService;
	}
	
	private PublikUserLocalService _publikUserLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
