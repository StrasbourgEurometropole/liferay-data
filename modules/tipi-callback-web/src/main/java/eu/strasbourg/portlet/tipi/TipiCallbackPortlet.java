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

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.upload.UploadServletRequestImpl;

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
	
	private TipiEntryLocalService tipiEntryLocalService;
	
	public void setTipiEnryLocalService(TipiEntryLocalService tipiEntryLocalService) {
		this.tipiEntryLocalService = tipiEntryLocalService;
	}

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		// Utilisation de l'upload
		UploadServletRequestImpl uploadServletRequest = new UploadServletRequestImpl(
			PortalUtil.getHttpServletRequest(renderRequest));
		
		String objet = ParamUtil.getString(uploadServletRequest, "objet");
		String price = ParamUtil.getString(uploadServletRequest, "montant");
		String status = ParamUtil.getString(uploadServletRequest,"resultrans");
		String dateString = ParamUtil.getString(uploadServletRequest, "dattrans");
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("ddMMyyy");
		LocalDate transactionDate = LocalDate.parse(dateString, fmt);
		Date date = Date.from(transactionDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		this.tipiEntryLocalService.addPayment(date, objet, status, price);
		super.render(renderRequest, renderResponse);
	}

}