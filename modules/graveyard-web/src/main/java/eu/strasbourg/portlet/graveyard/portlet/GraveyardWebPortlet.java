package eu.strasbourg.portlet.graveyard.portlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.portlet.graveyard.portlet.context.GraveyardDisplayContext;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Rechercher un d&eacute;funt",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/graveyard-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class GraveyardWebPortlet extends MVCPortlet {


	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		GraveyardDisplayContext dc = new GraveyardDisplayContext(request, response);
		String rechercher = ParamUtil.getString(request, "rechercher");
		if(rechercher.equals("lancer")){
			recherche(request, response, dc);
		}
		request.setAttribute("dc", dc);
		super.render(request, response);
	}

	public void recherche(RenderRequest request, RenderResponse response, GraveyardDisplayContext dc)
			throws IOException, PortletException {
		String name = ParamUtil.getString(request, "name");
		String firstName = ParamUtil.getString(request, "firstname");

		Boolean birthDateUnknows = ParamUtil.getBoolean(request, "birthdateunknown");
		String birthDateStartString, birthDateEndString = null;
		if (birthDateUnknows) {
			birthDateStartString = ParamUtil.getString(request, "birthdatestart");
			birthDateEndString = ParamUtil.getString(request, "birthdateend");
		} else {
			birthDateStartString = birthDateEndString = ParamUtil.getString(request, "birthdate");
		}

		Boolean deathDateUnknows = ParamUtil.getBoolean(request, "deathdateunknown");
		String deathDateStartString, deathDateEndString = null;
		if (deathDateUnknows) {
			deathDateStartString = ParamUtil.getString(request, "deathdatestart");
			deathDateEndString = ParamUtil.getString(request, "deathdateend");
		} else {
			deathDateStartString = deathDateEndString = ParamUtil.getString(request, "deathdate");
		}

		String deathPlace = ParamUtil.getString(request, "deathplace");
		String concession = ParamUtil.getString(request, "concession");
		try {

			if (Validator.isNull(name)) {
				if (SessionErrors.isEmpty(request)) {
					SessionErrors.add(request, "required");
				}
				SessionErrors.add(request, "name-required");
			}
			if (Validator.isNull(firstName)) {
				if (SessionErrors.isEmpty(request)) {
					SessionErrors.add(request, "required");
				}
				SessionErrors.add(request, "firstname-required");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if ((Validator.isNotNull(birthDateStartString) || Validator.isNotNull(birthDateEndString))
					&& !(Validator.isNotNull(birthDateStartString) && Validator.isNotNull(birthDateEndString))) {
				if (SessionErrors.isEmpty(request)) {
					SessionErrors.add(request, "required");
				}
				SessionErrors.add(request, "birthdates-required");
			}
			if ((Validator.isNotNull(deathDateStartString) || Validator.isNotNull(deathDateEndString))
					&& !(Validator.isNotNull(deathDateStartString) && Validator.isNotNull(deathDateEndString))) {
				if (SessionErrors.isEmpty(request)) {
					SessionErrors.add(request, "required");
				}
				SessionErrors.add(request, "deathdates-required");
			}
			Date birthDateStart = null, birthDateEnd = null;
			if (Validator.isNotNull(birthDateStartString))
				birthDateStart = sdf.parse(birthDateStartString);
			if (Validator.isNotNull(birthDateEndString))
				birthDateEnd = sdf.parse(birthDateEndString);
			if (Validator.isNotNull(birthDateStartString) && Validator.isNotNull(birthDateEndString)
					&& birthDateStart.after(birthDateEnd)) {
				SessionErrors.add(request, "birthdates-not-valid");
			}

			Date deathDateStart = null, deathDateEnd = null;
			if (Validator.isNotNull(deathDateStartString))
				deathDateStart = sdf.parse(deathDateStartString);
			if (Validator.isNotNull(deathDateEndString))
				deathDateEnd = sdf.parse(deathDateEndString);
			if (Validator.isNotNull(deathDateStartString) && Validator.isNotNull(deathDateEndString)
					&& deathDateStart.after(deathDateEnd)) {
				SessionErrors.add(request, "deathdates-not-valid");
			}
			if (name.contains("<") || firstName.contains("<") || deathPlace.contains("<")) {
				SessionErrors.add(request, "invalid-characters");
			}
			if (SessionErrors.isEmpty(request)) {
				GraveyardResponse graveyardResponse = GraveyardWebServiceClient.getResponse(name, firstName,
						birthDateStart, birthDateEnd, deathDateStart, deathDateEnd, deathPlace, concession);
				dc.setGraveyard(graveyardResponse);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		request.setAttribute("name", HtmlUtil.escape(name));
		request.setAttribute("firstname", HtmlUtil.escape(firstName));
		request.setAttribute("birthdateunknown", birthDateUnknows);
		request.setAttribute("birthdate", ParamUtil.getString(request, "birthdate"));
		request.setAttribute("birthdatestart", birthDateStartString);
		request.setAttribute("birthdateend", birthDateEndString);
		request.setAttribute("deathdateunknown", deathDateUnknows);
		request.setAttribute("deathdate", ParamUtil.getString(request, "deathdate"));
		request.setAttribute("deathdatestart", deathDateStartString);
		request.setAttribute("deathdateend", deathDateEndString);
		request.setAttribute("deathplace", HtmlUtil.escape(deathPlace));
		request.setAttribute("concession", concession);
	}

}