package eu.strasbourg.portlet.tipi_callback_portlet.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.tipi.service.TipiEntryLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.TIPI_CALLBACK_PORTLET_WEB,
		"mvc.command.name=callbackAction" }, service = MVCActionCommand.class)
public class CallbackActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException {
		String objet = ParamUtil.getString(actionRequest, "objet");
		String montant = ParamUtil.getString(actionRequest, "montant");
		String resultrans = ParamUtil.getString(actionRequest, "resultrans");
		String dattrans = ParamUtil.getString(actionRequest, "dattrans");

		SimpleDateFormat sf = new SimpleDateFormat("ddMMyyyy");
		Date transactionDateTime = null;
		try {
			transactionDateTime = sf.parse(dattrans);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		TipiEntryLocalServiceUtil.addPayment(transactionDateTime, objet,
				resultrans, montant);
		return true;
	}
	
}