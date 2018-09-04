package eu.strasbourg.portlet.projectpopup.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		immediate = true,
		property = { 
			"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
			"mvc.command.name=giveInitiativeHelp"
		},
		service = MVCActionCommand.class
	)
public class GiveInitiativeHelpActionCommand implements MVCActionCommand {

	private static final String SHARED_ASSET_ID = "LIFERAY_SHARED_assetEntryID";
	
	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {
		
		try {
			long entityID = getPortletAssetEntryId(request);
			String a = "a";
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	/**
	 * Recupere l'ID de l'assetEntry du detail de la page
	 * @throws PortalException 
	 */
	private long getPortletAssetEntryId(PortletRequest request) throws PortalException {
		
		// Recupertation des informations de la requete
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		PortletSession portletSession = request.getPortletSession();
		
		if (portletSession.getAttribute(SHARED_ASSET_ID, PortletSession.APPLICATION_SCOPE) != null) {
			return (long) portletSession.getAttribute(SHARED_ASSET_ID,
					PortletSession.APPLICATION_SCOPE);
		}
		
		return -1;
	}
}