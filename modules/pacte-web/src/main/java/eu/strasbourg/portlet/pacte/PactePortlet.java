package eu.strasbourg.portlet.pacte;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

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
	
	private static final String HAS_PACT_SIGNED_ATTRIBUTE = "has_pact_signed";
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		String publikUserID = getPublikID(renderRequest);
		long nbSignataires = PublikUserLocalServiceUtil.getCountUserHasSignedPacte();
		if(Validator.isNotNull(publikUserID)) {
			PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publikUserID);
			renderRequest.setAttribute("hasUserSigned", Validator.isNotNull(user.getPactSignature()));
			renderRequest.setAttribute("isDisplayListing", Validator.isNotNull(user.getPactDisplay()));
			renderRequest.setAttribute("isUserloggedIn", true);
		}
		else
			renderRequest.setAttribute("isUserloggedIn", false);
		renderRequest.setAttribute("nbSignataires",nbSignataires);
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
					
					//Vérification si la checkbox de clause de lecture du pacte est bien cochée
					boolean isClausesChecked = ParamUtil.getBoolean(resourceRequest, "clauses");
					//L'usager veut bien êtes listé avec les signataires du site
					boolean isDisplayListing = ParamUtil.getBoolean(resourceRequest, "isDisplayListing");
					
					//On sauvegarde la date de signature du pate. S'il était déjà signé on reset
					if(isClausesChecked) {
						HttpServletRequest  request = ServiceContextThreadLocal.getServiceContext().getRequest();
						
						if(user.getPactSignature() != null) {
							user.setPactSignature(null);
							user.setPactDisplay(false);
							request.getSession().setAttribute(HAS_PACT_SIGNED_ATTRIBUTE, false);
						} else {
							user.setPactSignature(new Date());
							user.setPactDisplay(isDisplayListing);
							request.getSession().setAttribute(HAS_PACT_SIGNED_ATTRIBUTE, true);
						}
					}
					
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