package eu.strasbourg.portlet.agendaExport.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import eu.strasbourg.service.agenda.model.AgendaExportPeriod;
import eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.service.AgendaExportLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_EXPORT_BO,
                "mvc.command.name=saveAgendaExport"
        },
        service = MVCActionCommand.class
)
public class SaveAgendaExportActionCommand implements MVCActionCommand{
	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getCompanyGroupId());
			long agendaExportId = ParamUtil.getLong(request, "agendaExportId");
			AgendaExport agendaExport;
			if (agendaExportId == 0) {
				agendaExport = _agendaExportLocalService.createAgendaExport(sc);
			} else {
				agendaExport = _agendaExportLocalService.getAgendaExport(agendaExportId);
				agendaExport.setNew(false);	
			}

			boolean isCopy = ParamUtil.getBoolean(request, "isCopy");
			if(isCopy) {
				agendaExport =_agendaExportLocalService.copyAgendaExport(sc, agendaExport);		
			}

			Map<Locale, String> title = LocalizationUtil.getLocalizationMap(request, "title");
			agendaExport.setTitleMap(title);

			//Enregistrement des champs, lorsqu'on ne copie pas l'objet
			if(!isCopy) {

				/** Période de l'événement **/
				this.deletePeriods(agendaExport);
				this.savePeriod(request, agendaExport);

				/** Catégories **/
				this.saveCategories(request, agendaExport);

				/** Langue **/
				String language = ParamUtil.getString(request, "language");
				agendaExport.setLanguage(language);

				/** Format d'export **/
				String formatExport = ParamUtil.getString(request, "exportFormat");
				agendaExport.setExportFormat(formatExport);
			}
			
			_agendaExportLocalService.updateAgendaExport(agendaExport, sc);

			// Redirection (évite double)
			// requête POST si l'utilisateur actualise sa page)
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
			PortletURL renderUrl = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);
			response.sendRedirect(renderUrl.toString());
		} catch (Exception e) {
			_log.error(e);
		}

		return true;
	}

	/**
	 * Suppression des anciennes périodes
	 * @param agendaExport
	 */
	private void deletePeriods(AgendaExport agendaExport) {
		List<AgendaExportPeriod> oldPeriods = agendaExport.getAgendaExportPeriods();
		for (AgendaExportPeriod period : oldPeriods) {
			_agendaExportPeriodLocalService.deleteAgendaExportPeriod(period);
		}
	}

	/**
	 * Sauvegarde de la période
	 * @param request
	 * @param agendaExport
	 * @throws PortalException
	 */
	private void savePeriod(ActionRequest request, AgendaExport agendaExport) throws PortalException {
		String periodIndex = "0";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if (!(
				Validator.isNotNull(ParamUtil.getString(request, "startDate" + periodIndex)) &&
				Validator.isNotNull(ParamUtil.getString(request, "endDate" + periodIndex))
			)) {
			return;
		}

		Date startDate = ParamUtil.getDate(request, "startDate" + periodIndex, dateFormat);
		Date endDate = ParamUtil.getDate(request, "endDate" + periodIndex, dateFormat);

		AgendaExportPeriod agendaExportPeriod = _agendaExportPeriodLocalService.createAgendaExportPeriod();
		agendaExportPeriod.setStartDate(startDate);
		agendaExportPeriod.setEndDate(endDate);
		agendaExportPeriod.setAgendaExportId(agendaExport.getAgendaExportId());
		this._agendaExportPeriodLocalService.updateAgendaExportPeriod(agendaExportPeriod);
	}

	/**
	 * Remplissage du champ eventCategories de l'entité agendaExport
	 * @param request
	 * @param agendaExport
	 */
	private void saveCategories(ActionRequest request, AgendaExport agendaExport) {
		int vocabularyNumber = ParamUtil.getInteger(request, "vocabulary_number");
		JSONObject vocabularies = JSONFactoryUtil.createJSONObject();
		for(int i = 0; i < vocabularyNumber; i++) {
			long vocabularyId = ParamUtil.getLong(request, "vocabulary_" + i + "_id");
			long[] categoryIds = ParamUtil.getLongValues(request, "vocabulary_" + i + "_select");
			if(categoryIds.length == 0) {
				continue;
			}
			JSONArray categories = JSONFactoryUtil.createJSONArray();
			for (int j = 0; j < categoryIds.length; j++) {
				categories.put(categoryIds[j]);
			}
			vocabularies.put(Long.toString(vocabularyId), categories);
		}
		agendaExport.setEventCategories(vocabularies.toString());
	}

	private AgendaExportLocalService _agendaExportLocalService;

	@Reference(unbind = "-")
	protected void setAgendaExportLocalService(AgendaExportLocalService agendaExportLocalService) {

		_agendaExportLocalService = agendaExportLocalService;
	}

	private AgendaExportPeriodLocalService _agendaExportPeriodLocalService;

	@Reference(unbind = "-")
	protected void setAgendaExportPeriodLocalService(AgendaExportPeriodLocalService agendaExportPeriodLocalService) {

		_agendaExportPeriodLocalService = agendaExportPeriodLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}
