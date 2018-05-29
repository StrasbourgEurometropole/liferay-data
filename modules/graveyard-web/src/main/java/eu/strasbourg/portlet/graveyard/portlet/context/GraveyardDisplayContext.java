package eu.strasbourg.portlet.graveyard.portlet.context;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.graveyard.portlet.DefuntDTO;
import eu.strasbourg.portlet.graveyard.portlet.GraveyardResponse;
import eu.strasbourg.portlet.graveyard.portlet.GraveyardWebServiceClient;
import eu.strasbourg.portlet.graveyard.portlet.mapping.GraveyardMapping;
import eu.strasbourg.utils.Pager;

public class GraveyardDisplayContext {

	private ThemeDisplay themeDisplay;
	private RenderRequest request;
	private RenderResponse response;
	private GraveyardResponse graveyard;
	private List<DefuntDTO> defunts;
	private SearchContainer<DefuntDTO> searchContainer;
	private List<GraveyardMapping> concessions;

	public GraveyardDisplayContext(RenderRequest request, RenderResponse response) {
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		this.request = request;
		this.response = response;
	}

	public void setGraveyard(GraveyardResponse graveyard) {
		this.graveyard = graveyard;
	}

	public void recherche(RenderRequest request, RenderResponse response)
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
				this.setGraveyard(graveyardResponse);
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
		int deltaFromParam = ParamUtil.getInteger(this.request, "delta");
		if (deltaFromParam > 0) {
			return deltaFromParam;
		}
		return 4;
	}

	/**
	 * Retourne la liste des défunts
	 */
	public List<DefuntDTO> getResults() {
		if (this.defunts == null) {
			// Récupération des défunts
			List<DefuntDTO> defunts = graveyard.getDefunts();

			this.defunts = defunts;
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