package eu.strasbourg.portlet.objtp.action;

import java.io.IOException;
import java.text.ParseException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import eu.strasbourg.service.objtp.service.FoundObjectLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.OBJTP_BO,
		"mvc.command.name=startImportObjtp" }, service = MVCActionCommand.class)
public class StartImportObjtpActionCommand implements MVCActionCommand {
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());


	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

			try {
				FoundObjectLocalServiceUtil.doImport();
			} catch (PortalException | IOException | ParseException e) {
				_log.equals(e);
			}

		
		return true;
	}

}
