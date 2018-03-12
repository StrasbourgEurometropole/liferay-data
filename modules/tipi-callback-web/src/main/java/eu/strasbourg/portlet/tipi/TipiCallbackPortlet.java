package eu.strasbourg.portlet.tipi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.tipi.service.TipiEntryLocalService;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.required-namespaced-parameters=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class TipiCallbackPortlet extends MVCPortlet {

	

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		
		// Permet de remonter la hiérarchie des Request
		HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
				
		String price = ParamUtil.getString(request, "montant"); 
		if (Validator.isNotNull(price)) {
			String objet = ParamUtil.getString(request, "objet");
			String status = ParamUtil.getString(request,"resultrans");
			String dateString = ParamUtil.getString(request,"dattrans");

			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("ddMMyyyy");
			LocalDate transactionDate = LocalDate.parse(dateString, fmt);
			Date date = Date.from(transactionDate
				.atStartOfDay(ZoneId.systemDefault()).toInstant());

			_tipiEntryLocalService.addPayment(date, objet, status, price);
		}
		super.render(renderRequest, renderResponse);
	}
	private TipiEntryLocalService _tipiEntryLocalService;

	@Reference(unbind = "-")
	protected void setTipiEntryLocalService(
			TipiEntryLocalService tipiEntryLocalService) {

		_tipiEntryLocalService = tipiEntryLocalService;
	}
}