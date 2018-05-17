package eu.strasbourg.portlet.graveyard.portlet.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.graveyard.portlet.DefuntDTO;
import eu.strasbourg.portlet.graveyard.portlet.GraveyardResponse;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.Pager;
import eu.strasbourg.utils.constants.VocabularyNames;

public class GraveyardDisplayContext {

	private ThemeDisplay themeDisplay;
	private RenderRequest request;
	private RenderResponse response;
	private GraveyardResponse graveyard;
	private List<DefuntDTO> defunts;
	private SearchContainer<DefuntDTO> searchContainer;
	private List<Place> concessions;

	public GraveyardDisplayContext(RenderRequest request, RenderResponse response) {
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		this.request = request;
		this.response = response;
	}

	public void setGraveyard(GraveyardResponse graveyard) {
		this.graveyard = graveyard;
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
	public List<Place> getConcessions() {
		if (this.concessions == null) {
			List<Place> concessions = new ArrayList<Place>();
			AssetVocabulary placeTypeVocabulary;
			try {
				placeTypeVocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
				AssetCategory graveyard = AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary,
						"Cat_07_02");
				List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil
						.getAssetCategoryAssetEntries(graveyard.getCategoryId());
				for (AssetEntry assetEntry : assetEntries) {
					concessions.add(PlaceLocalServiceUtil.fetchPlace(assetEntry.getClassPK()));
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
			this.concessions = concessions;
		}
		return this.concessions;
	}

	/**
	 * Retourne 0 si résultat ou retour avec erreur, 1 si erreur à l'envoi de la
	 * requête (manque un paramètre ...)
	 */
	public String getErr() {
		return this.graveyard.getErr();
	}

	/**
	 * Retourne le message d'erreur si err = 0
	 */
	public String getErreur() {
		return this.graveyard.getErreur();
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