package eu.strasbourg.portlet.graveyard.portlet.context;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.graveyard.portlet.DefuntDTO;
import eu.strasbourg.portlet.graveyard.portlet.GraveyardResponse;
import eu.strasbourg.portlet.graveyard.portlet.GraveyardWebServiceClient;
import eu.strasbourg.portlet.graveyard.portlet.configuration.GraveyardConfiguration;
import eu.strasbourg.portlet.graveyard.portlet.mapping.GraveyardMapping;
import eu.strasbourg.utils.Pager;

public class GraveyardDisplayContext {

	private ThemeDisplay themeDisplay;
	private GraveyardConfiguration configuration;
	private RenderRequest request;
	private RenderResponse response;
	private GraveyardResponse graveyard;
	private List<DefuntDTO> defunts;
	private SearchContainer<DefuntDTO> searchContainer;
	private List<GraveyardMapping> concessions;

	public GraveyardDisplayContext(RenderRequest request, RenderResponse response) {
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			this.configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(GraveyardConfiguration.class);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		this.request = request;
		this.response = response;
	}

	public GraveyardConfiguration getConfiguration() {
		return configuration;
	}

	public void setGraveyard(GraveyardResponse graveyard) {
		this.graveyard = graveyard;
	}

	public String getContactURL() {
		String contactURL = configuration.contactURL();
		if (Validator.isNull(contactURL)) {
			contactURL = "#";
		}
		return contactURL;
	}

	public String getLimit() {
		String limit = configuration.limit();
		if (Validator.isNull(limit)) {
			limit = "20";
		}
		return limit;
	}

	public void recherche(RenderRequest request, RenderResponse response)
			throws IOException, PortletException {

		// Permet de remonter la hiérarchie des Request
		HttpServletRequest httpRequest = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		
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
		
		String error = "";
		try {

			if (Validator.isNull(name)) {
				error += LanguageUtil.get(httpRequest, "required") + " " + LanguageUtil.get(httpRequest, "name-required");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if ((Validator.isNotNull(birthDateStartString) || Validator.isNotNull(birthDateEndString))
					&& !(Validator.isNotNull(birthDateStartString) && Validator.isNotNull(birthDateEndString))) {
				if (Validator.isNull(error)) {
					error += LanguageUtil.get(httpRequest, "required");
				}else {
					error += ", ";
				}
				error += LanguageUtil.get(httpRequest, "birthdates-required");
			}
			if ((Validator.isNotNull(deathDateStartString) || Validator.isNotNull(deathDateEndString))
					&& !(Validator.isNotNull(deathDateStartString) && Validator.isNotNull(deathDateEndString))) {
				if (Validator.isNull(error)) {
					error += "required";
				}else {
					error += ", ";
				}
				error += LanguageUtil.get(httpRequest, "deathdates-required");
			}
			Date birthDateStart = null, birthDateEnd = null;
			if (Validator.isNotNull(birthDateStartString))
				birthDateStart = sdf.parse(birthDateStartString);
			if (Validator.isNotNull(birthDateEndString))
				birthDateEnd = sdf.parse(birthDateEndString);
			if (Validator.isNotNull(birthDateStartString) && Validator.isNotNull(birthDateEndString)
					&& birthDateStart.after(birthDateEnd)) {
				if (Validator.isNotNull(error)) {
					error += "<br>";
				}
				error += LanguageUtil.get(httpRequest, "birthdates-not-valid");
			}

			Date deathDateStart = null, deathDateEnd = null;
			if (Validator.isNotNull(deathDateStartString))
				deathDateStart = sdf.parse(deathDateStartString);
			if (Validator.isNotNull(deathDateEndString))
				deathDateEnd = sdf.parse(deathDateEndString);
			if (Validator.isNotNull(deathDateStartString) && Validator.isNotNull(deathDateEndString)
					&& deathDateStart.after(deathDateEnd)) {
				if (Validator.isNotNull(error)) {
					error += "<br>";
				}
				error += LanguageUtil.get(httpRequest, "deathdates-not-valid");
			}
			if (name.contains("<") || firstName.contains("<") || deathPlace.contains("<")) {
				if (Validator.isNotNull(error)) {
					error += "<br>";
				}
				error += LanguageUtil.get(httpRequest, "invalid-characters");
			}
			if (SessionErrors.isEmpty(request)) {
				GraveyardResponse graveyardResponse = GraveyardWebServiceClient.getResponse(name, firstName,
						birthDateStart, birthDateEnd, deathDateStart, deathDateEnd, deathPlace, concession, this.getLimit());
				this.setGraveyard(graveyardResponse);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		request.setAttribute("error", error);
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

	public GraveyardResponse getGraveyard() {
		return this.graveyard;
	}

	public String getVirtualHostName() {
		Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(this.themeDisplay.getCompanyId(), "/strasbourg.eu");
		return group.getPublicLayoutSet().getVirtualHostname();
	}

	/**
	 * Retourne les cimetières
	 */
	public List<GraveyardMapping> getConcessions() {
		if (this.concessions == null) {
			this.concessions = GraveyardMapping.getAll();
		}
		return this.concessions;
	}

	/**
	 * Retourne le nombre défunts à afficher par page
	 */
	public int getDelta() {
		return 10;
	}

	/**
	 * Retourne la liste des défunts
	 */
	public List<DefuntDTO> getResults() {
		if (this.defunts == null) {
			// Récupération des défunts
			this.defunts = graveyard.getDefunts();

			// On parcours les résultats pour supprimer les décès d'avant 1998
			List<DefuntDTO> defuntsList = new ArrayList<DefuntDTO>();
			for (DefuntDTO defunt : this.defunts ) {
				defuntsList.add(defunt);
			}
			this.defunts = defuntsList;
		}

		return this.defunts;
	}

    /**
     * Retourne le nombre total de résultats
     */
    public int getResultCount() {
        return getResults().size();
    }

	/**
	 * Retourne le searchContainer
	 */
	public SearchContainer<DefuntDTO> getSearchContainer() {
		if (searchContainer == null) {
			Map<String, String[]> parameterMap = request.getParameterMap();
			PortletURL iteratorURL = this.response.createRenderURL();
			iteratorURL.setParameters(parameterMap);
			searchContainer = new SearchContainer<DefuntDTO>(request, iteratorURL, null, "no-entries-were-found");
			searchContainer.setDelta(this.getDelta());
			searchContainer.setTotal(this.getResultCount());
			searchContainer.setResults(this.defunts);
		}
		return searchContainer;
	}

	/**
	 * Retourne les résultats entre les indexes start (inclu) et end (non inclu)
	 */
	public List<DefuntDTO> getPaginatedResults() {
		return ListUtil.subList(getResults(), this.getSearchContainer().getStart(), this.getSearchContainer().getEnd());
	}

	/**
	 * Retourne le pager de la page
	 */
	public Pager getPager() {
		return new Pager(this.getSearchContainer().getTotal(), (int) this.getDelta(),
				this.getSearchContainer().getCur());
	}

	/**
	 * Retourne l'URL sur laquelle aller pour accéder à la Xième page
	 */
	public String getURLForPage(long pageIndex) {
		PortletURL url = this.getSearchContainer().getIteratorURL();
		url.setParameter("cur", String.valueOf(pageIndex));
		String valueToReturn = url.toString();
		url.setParameter("cur", String.valueOf(this.getSearchContainer().getCur()));
		return valueToReturn;
	}
}