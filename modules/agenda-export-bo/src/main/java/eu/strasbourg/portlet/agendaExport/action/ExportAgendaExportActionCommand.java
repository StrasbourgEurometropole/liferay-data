package eu.strasbourg.portlet.agendaExport.action;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.xml.bind.JAXBContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.service.AgendaExportLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_EXPORT_BO,
                "mvc.command.name=exportAgendaExport"
        },
        service = MVCActionCommand.class
)
public class ExportAgendaExportActionCommand implements MVCActionCommand{
	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getCompanyGroupId());

			/** Récupération des champs **/
			Map<Locale, String> title = LocalizationUtil.getLocalizationMap(request, "title");
			Map<Long, List<Long>> vocabularies = this.getCategories(request);
			LocalDate startDate = this.getDate(request,"startDate", "0");
			LocalDate endDate = this.getDate(request,"endDate", "0");
			String language = ParamUtil.getString(request, "language");

			Map<String, Serializable> attributes = sc.getAttributes();

			// Redirection (�vite double
			// requ�te POST si l'utilisateur actualise sa page)
//			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
//			String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
//			PortletURL renderUrl = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
//					PortletRequest.RENDER_PHASE);
//			response.sendRedirect(renderUrl.toString());



//			response.setRenderParameter("mvcPath", "/agenda-export-bo-edit-agenda-export.jsp");


			//response.sendRedirect("/agenda-export-bo-view-agenda-export.jsp");
		} catch (Exception e) {
			_log.error(e);
		}

		return true;
	}

	/**
	 *
	 * @param request
	 * @param field
	 * @param index
	 * @return Date | null
	 */
	private LocalDate getDate(ActionRequest request, String field, String index) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if(!Validator.isNotNull(ParamUtil.getString(request, field + index))) {
			return null;
		}

		Date date = ParamUtil.getDate(request, field + index, dateFormat);
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 *
	 * Récupération des catégories
	 * @param request
	 * @return Map<Long, List<Long>>
	 */
	private Map<Long, List<Long>> getCategories(ActionRequest request) {
		int vocabularyNumber = ParamUtil.getInteger(request, "vocabulary_number");
		Map<Long, List<Long>> vocabularies = new HashMap<>();
		for(int i = 0; i < vocabularyNumber; i++) {
			long vocabularyId = ParamUtil.getLong(request, "vocabulary_" + i + "_id");
			long[] categoryIds = ParamUtil.getLongValues(request, "vocabulary_" + i + "_select");
			if(categoryIds.length == 0) {
				continue;
			}
			List<Long> categories = new ArrayList<>();
			for (int j = 0; j < categoryIds.length; j++) {
				categories.add(categoryIds[j]);
			}
			vocabularies.put(vocabularyId, categories);
		}
		return vocabularies;
	}


	private AgendaExportLocalService _agendaExportLocalService;

	@Reference(unbind = "-")
	protected void setAgendaExportLocalService(AgendaExportLocalService agendaExportLocalService) {

		_agendaExportLocalService = agendaExportLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}
