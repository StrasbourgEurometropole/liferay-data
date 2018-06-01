package eu.strasbourg.portlet.pacte;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Pacte",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/pacte-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.PACTE_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PactePortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		super.render(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("pacteSignature")) {
				
				String publikUserID = getPublikID(resourceRequest);
				if (Validator.isNotNull(publikUserID)) {
					PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publikUserID);
					
					boolean clauses = ParamUtil.getBoolean(resourceRequest, "clauses");
					
					Enumeration<String> params = resourceRequest.getParameterNames();
					
					if(user.getPactSignature() != null)
						user.setPactSignature(null);
					else
						user.setPactSignature(new Date());
					
					PublikUserLocalServiceUtil.updatePublikUser(user);
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}

		super.serveResource(resourceRequest, resourceResponse);
	}
	
	// Récupération du publik ID avec la session
	private String getPublikID(PortletRequest resourceRequest) {

		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}