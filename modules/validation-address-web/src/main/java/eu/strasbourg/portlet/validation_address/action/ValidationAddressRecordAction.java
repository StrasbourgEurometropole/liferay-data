package eu.strasbourg.portlet.validation_address.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.validation_address.ValidationAddressDisplayContext;
import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.VALIDATION_ADDRESS_WEB,
		"mvc.command.name=record" }, service = MVCActionCommand.class)
public class ValidationAddressRecordAction implements MVCActionCommand {

	private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference
	private AdictService adictService;

	/**
	 * Fonction appelée lors du clic sur le bouton enregistrer
	 */
	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		// Le display context
		ValidationAddressDisplayContext dc = new ValidationAddressDisplayContext(themeDisplay, request, adictService);
		request.setAttribute("dc", dc);

		// Adresse à enregistrer
		String address = ParamUtil.getString(request, "address");
		String zipCode = ParamUtil.getString(request, "zipCode");
		String city = ParamUtil.getString(request, "city");
		String lastName = ParamUtil.getString(request, "lastName");
		
		return PublikApiClient.setUserDetails(dc.getPublikID(), lastName, address, zipCode, city);
	}

}
