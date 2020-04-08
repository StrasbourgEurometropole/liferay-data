package eu.strasbourg.portlet.form_send;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.portlet.form_send.context.EditFormSendDisplayContext;
import eu.strasbourg.portlet.form_send.context.ViewFormDisplayContext;
import eu.strasbourg.portlet.form_send.context.ViewFormSendDisplayContext;
import eu.strasbourg.portlet.form_send.context.ViewReportingDisplayContext;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldSignalementLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * @author angelique.champougny
 */
@Component(
		immediate=true,
		property= {
				"javax.portlet.version=3.0",
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

		//si on est sur la page des proposition du de signalement, on récupère le formInstanceId
		long formInstanceId = ParamUtil.getLong(renderRequest,"formInstanceId");
		renderRequest.setAttribute("formInstanceId", formInstanceId);

		//récupère le nom du formulaire
		DDMFormInstance formInstance = DDMFormInstanceLocalServiceUtil.fetchFormInstance(formInstanceId);
		String formInstanceName = "Proposition";
		if(Validator.isNotNull(formInstance))
			formInstanceName = formInstance.getName(Locale.FRANCE);
		renderRequest.setAttribute("formInstanceName", formInstanceName);

		//on set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editFormSend")){
			EditFormSendDisplayContext dc = new EditFormSendDisplayContext(renderRequest,renderResponse);
			renderRequest.setAttribute("dc",dc);
		} else if (cmd.equals("hideResponse")){
			long formSendRecordFieldId = ParamUtil.getLong(renderRequest,"formSendRecordFieldId");
			displayResponse(false, formSendRecordFieldId);
			ViewReportingDisplayContext dc = new ViewReportingDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("showResponse")){
			long formSendRecordFieldId = ParamUtil.getLong(renderRequest,"formSendRecordFieldId");
			displayResponse(true, formSendRecordFieldId);
			ViewReportingDisplayContext dc = new ViewReportingDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("viewFormSends")){
			ViewFormSendDisplayContext dc = new ViewFormSendDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("viewReportings")){
			ViewReportingDisplayContext dc = new ViewReportingDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else {
			ViewFormDisplayContext dc = new ViewFormDisplayContext(renderRequest,renderResponse);
			renderRequest.setAttribute("dc",dc);
		}
		super.render(renderRequest, renderResponse);
	}

	private Boolean displayResponse(boolean showResponse, long formSendRecordFieldId){
		// récupère tous les signalements qui ont cette réponse
		List<FormSendRecordFieldSignalement> signalements = FormSendRecordFieldSignalementLocalServiceUtil.findByFormSendRecordFieldId(formSendRecordFieldId);

		// pour chaque signalement, modifier leurs status par celui correspondant (4 -> afficher la réponse, 0 -> cacher la réponse)
		for (FormSendRecordFieldSignalement signalement : signalements) {
			signalement.setStatus(showResponse ? WorkflowConstants.STATUS_DENIED : WorkflowConstants.STATUS_APPROVED);
			FormSendRecordFieldSignalementLocalServiceUtil.updateFormSendRecordFieldSignalement(signalement);
		}
		return true;
	}
}