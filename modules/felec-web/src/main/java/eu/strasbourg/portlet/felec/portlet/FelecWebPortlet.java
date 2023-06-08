package eu.strasbourg.portlet.felec.portlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/felec-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class FelecWebPortlet extends MVCPortlet {

	@Override
	public void processAction(ActionRequest actionRequest,
		ActionResponse actionResponse) throws IOException, PortletException {
		String name = ParamUtil.getString(actionRequest, "name");

		String firstName = ParamUtil.getString(actionRequest, "firstname");
		Date birthDate = ParamUtil.getDate(actionRequest, "birthdate",
			new SimpleDateFormat("dd/MM/yyyy"));
		String birthPlace = ParamUtil.getString(actionRequest, "birthplace");
		if (Validator.isNull(name) || Validator.isNull(firstName) || Validator.isNull(birthPlace)
			|| Validator.isNull(ParamUtil.getString(actionRequest, "birthdate"))) {
			SessionErrors.add(actionRequest, "all-fields-required");
		} else if (name.contains("<") || firstName.contains("<")
			|| birthPlace.contains("<")) {
			SessionErrors.add(actionRequest, "invalid-characters");
		} else if (!ParamUtil.getString(actionRequest, "birthdate")
			.matches("^[0-3]?[0-9]/[0-1]?[0-9]/[1-2][0-9]{3}$")) {
			SessionErrors.add(actionRequest, "date-not-valid");
		} else {
			FelecResponse felecResponse = FelecWebServiceClient
				.getResponse(name, firstName, birthDate, birthPlace);
			actionRequest.setAttribute("felecResponse", felecResponse);
		}
		actionRequest.setAttribute("name", HtmlUtil.escape(name));
		actionRequest.setAttribute("firstname", HtmlUtil.escape(firstName));
		actionRequest.setAttribute("birthdate",
			ParamUtil.getString(actionRequest, "birthdate"));
		actionRequest.setAttribute("birthplace", HtmlUtil.escape(birthPlace));
		super.processAction(actionRequest, actionResponse);
	}
}