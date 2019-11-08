package eu.strasbourg.portlet.form_send;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.form_send.context.EditFormSendDisplayContext;
import eu.strasbourg.portlet.form_send.context.ViewFormDisplayContext;
import eu.strasbourg.portlet.form_send.context.ViewFormSendDisplayContext;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;

/**
 * @author angelique.champougny
 */
@Component(
		immediate=true,
		property= {
				"com.liferay.portlet.instanceable=false",
				"com.liferay.portlet.footer-portlet-javascript=/js/form-send-bo-main.js",
				"com.liferay.portlet.header-portlet-css=/css/form-send-bo-main.css",
				"com.liferay.portlet.single-page-application=false",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/form-send-bo-view.jsp",
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class FormSendBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest,"tab");

		//si on est sur la page des proposition ou de signalement, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest,"returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton){
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL);
		}

		//si on est sur la page des proposition u de signalement, on récupère le recordSetId
		long recordSetId = ParamUtil.getLong(renderRequest,"recordSetId");
		renderRequest.setAttribute("recordSetId", recordSetId);

		//récupère le nom du formulaire
		DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.fetchRecordSet(recordSetId);
		String recordSetName = "Proposition";
		if(Validator.isNotNull(recordSet))
			recordSetName = recordSet.getName(Locale.FRANCE);
		renderRequest.setAttribute("recordSetName", recordSetName);

		//on set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editFormSend")){
			EditFormSendDisplayContext dc = new EditFormSendDisplayContext(renderRequest,renderResponse);
			renderRequest.setAttribute("dc",dc);
//		} else if (tab.equals("viewReportings")){
//			viewReportingDisplayContext dc = new viewReportingDisplayContext(renderRequest, renderResponse);
//			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("viewFormSends")){
				ViewFormSendDisplayContext dc = new ViewFormSendDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else {
			ViewFormDisplayContext dc = new ViewFormDisplayContext(renderRequest,renderResponse);
			renderRequest.setAttribute("dc",dc);
		}
		super.render(renderRequest, renderResponse);
	}
}