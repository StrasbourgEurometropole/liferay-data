package eu.strasbourg.portlet.project.display.context;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

public class EditParticipationDisplayContext {
	
	private Participation _participation;
	private List<AssetCategory> _cities;
	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
	
	public EditParticipationDisplayContext(RenderRequest request,
		RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Participation getParticipation() {
		long participationId = ParamUtil.getLong(_request, "participationId");
		if (_participation == null && participationId > 0) {
			_participation = ParticipationLocalServiceUtil.fetchParticipation(participationId);
			
			/**
			 *  Vérification de l'éxistance des eventIds de la participation
			 *  @note Les evenements sont au fur et a mesure depublies et suprimes,
			 *  toutefois il est impossible de corriger la liste des ids correspondants
			 *  dans les participations pour cause de references circulaires.
			 *  L'erreur ne s'appliquant que pendant un itemcker puisqu'il parcourt la
			 *  liste des Ids afin d'y trouver les asset adequats, nous supprimons les references
			 *  a la volee pour retablir l'ordre etabli
			 */
			StringBuilder correctedEventIds = new StringBuilder();
			String eventIds = _participation.getEventsIds();
			
			if (!eventIds.equals("")) {
				for (String eventId : eventIds.split(",")) {
					try {
						if (EventLocalServiceUtil.fetchEvent(Long.parseLong(eventId)) != null) {
							correctedEventIds.append(eventId).append(",");
						}
					} catch (NumberFormatException e) {
						_log.error(e.getMessage() + " : " + eventId);
					}
				}
			}
			
			_participation.setEventsIds(correctedEventIds.toString());
		}
		return _participation;
	}
	
	/**
	 * Renvoie les indexes des lieux par défaut
	 */
	public String getDefaultPlaceIndexes() throws PortalException {
		if (this.getParticipation() != null) {
			List<PlacitPlace> places = this.getParticipation().getPlacitPlaces();
			String indexes = "0";
			for (int i = 1; i <= places.size(); i++) {
				indexes += "," + i;
			}
			return indexes;
		}
		return "";
	}
	
	/**
	 * Retourne la liste des villes
	 */
	public List<AssetCategory> getCities() throws PortalException {
		if (Validator.isNull(this._cities)) {
			AssetVocabulary territoriesVocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.TERRITORY);
			if (territoriesVocabulary != null) {
				this._cities = territoriesVocabulary.getCategories().stream().filter(c -> {
					try {
						return c.getAncestors().size() == 1;
					} catch (Exception e) {
						return false;
					}
				}).collect(Collectors.toList());
			}
		}
		return this._cities;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			_themeDisplay.getScopeGroupId(), _themeDisplay.getCompanyGroupId(),
			Participation.class.getName());
	}
		
	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
		this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.PROJECT_BO, StrasbourgPortletKeys.PROJECT_BO,
			actionId);
	}

	private Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}