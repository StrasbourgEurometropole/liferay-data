package eu.strasbourg.portlet.favorites.display.context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.favorites.configuration.FavoritesConfiguration;
import eu.strasbourg.portlet.favorites.display.FavoriteDisplay;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.utils.Pager;
import eu.strasbourg.utils.PortletHelper;

public class FavoritesDisplayContext {

	private PortletRequest request;
	private RenderResponse response;
	private ThemeDisplay themeDisplay;
	private FavoritesConfiguration configuration;

	private List<FavoriteDisplay> favorites;
	private List<FavoriteDisplay> lastFavorites;
	private SearchContainer<FavoriteDisplay> searchContainer;

	public FavoritesDisplayContext(PortletRequest request, RenderResponse response) {
		this.request = request;
		this.response = response;
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			this.configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(FavoritesConfiguration.class);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Récupère tous les favoris de l'utilisateur à afficher selon le filtre sélectionné
	 */
	public List<FavoriteDisplay> getFavorites() {
		if (favorites == null) {
			String publikUserId = "";
			HttpServletRequest servletRequest = ServiceContextThreadLocal.getServiceContext().getRequest();
			boolean isLoggedIn = SessionParamUtil.getBoolean(servletRequest, "publik_logged_in");
			if (isLoggedIn) {
				publikUserId = SessionParamUtil.getString(servletRequest, "publik_internal_id");
			}

			List<Favorite> userFavorites = FavoriteLocalServiceUtil.getByPublikUser(publikUserId);
			long favoriteTypeId = ParamUtil.getLong(request, "favoriteTypeId");
			if (favoriteTypeId > 0) {
				userFavorites = userFavorites.stream().filter(f -> f.getFavoriteType().getId() == favoriteTypeId)
						.collect(Collectors.toList());
			}

			List<FavoriteDisplay> favoritesDisplay = new ArrayList<FavoriteDisplay>();

			for (Favorite favorite : userFavorites) {
				favoritesDisplay.add(new FavoriteDisplay(favorite, publikUserId, themeDisplay));
			}
			favoritesDisplay.sort(Comparator.comparing(FavoriteDisplay::getFavoriteId));
			Collections.reverse(favoritesDisplay);
			favorites = favoritesDisplay;
		}
		return favorites;
	}
	
	public String getNoFavoriteText() {
		String noFavorites = "";
		Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.noFavoritesXML());
		for (Map.Entry<Locale, String> map : mapText.entrySet()) {
			if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
				noFavorites = HtmlUtil.unescape(map.getValue());
				break;
			}
		}
		if (Validator.isNull(noFavorites)) {
			noFavorites = "No configuration";
		}
		return noFavorites;
	}

	public List<FavoriteDisplay> getLastFavorites() {
		if (lastFavorites == null) {
			List<FavoriteDisplay> favoritesDisplay = getFavorites();

			if (favorites.size() > 4) {
				favoritesDisplay = favorites.subList(0, 4);
			}

			lastFavorites = favoritesDisplay;
		}
		return lastFavorites;
	}

	public GregorianCalendar getTodayCalendar() {
		GregorianCalendar jourChoisi = new GregorianCalendar();
		jourChoisi.set(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue() -1,
				LocalDateTime.now().getDayOfMonth());
		jourChoisi.set(Calendar.HOUR_OF_DAY, 0);
		jourChoisi.clear(Calendar.MINUTE);
		jourChoisi.clear(Calendar.SECOND);
		jourChoisi.clear(Calendar.MILLISECOND);
		GregorianCalendar selectedCalendar = new GregorianCalendar();
		selectedCalendar.setTime(jourChoisi.getTime());

		return selectedCalendar;
	}
	
	public String getPortletTitle(String defaultValue) {
		return PortletHelper.getPortletTitle(defaultValue, request);
	}

	/**
	 * Retourne les résultats entre les indexes start (inclu) et end (non inclu)
	 */
	public List<FavoriteDisplay> getPaginatedResults() {
		return ListUtil.subList(getFavorites(), this.getSearchContainer().getStart(), this.getSearchContainer().getEnd());
	}

	/**
	 * Retourne le nombre total de résultats
	 */
	public int getCount() {
		return getFavorites().size();
	}

	/**
	 * Retourne le searchContainer
	 */
	public SearchContainer<FavoriteDisplay> getSearchContainer() {
		if (searchContainer == null) {
			Map<String, String[]> parameterMap = request.getParameterMap();
			PortletURL iteratorURL = response.createRenderURL();
			iteratorURL.setParameters(parameterMap);
			searchContainer = new SearchContainer<FavoriteDisplay>(request, iteratorURL, null,
					"no-entries-were-found");
			searchContainer.setDelta(this.getDelta());
			searchContainer.setTotal(this.getCount());
			searchContainer.setResults(this.getFavorites());
		}
		return searchContainer;
	}

	/**
	 * Retourne le nombre notification à afficher par page
	 */
	public int getDelta() {
		int deltaFromParam = ParamUtil.getInteger(this.request, "delta");
		if (deltaFromParam > 0) {
			return deltaFromParam;
		}
		return 15;
	}

	/**
	 * Retourne le pager de la page
	 */
	public Pager getPager() {
		return new Pager(this.getSearchContainer().getTotal(), (int) this.getDelta(),
				this.getSearchContainer().getCur());
	}

	/**
	 * Retourne l'URL sur laquelle aller pour avoir X items par page
	 */
	public String getURLForDelta(long delta) {
		PortletURL url = this.getSearchContainer().getIteratorURL();
		url.setParameter("delta", String.valueOf(delta));
		String valueToReturn = url.toString();
		url.setParameter("delta", String.valueOf(this.getDelta()));
		return valueToReturn;
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

	public List<FavoriteType> getFavoritesType() {
		List<FavoriteType> favoritesType = FavoriteType.getAll();
		favoritesType.sort(Comparator.comparing(FavoriteType::getName));
		return favoritesType;
	}
	
	/**
	 * Récupère tous les favoris de l'utilisateur sans tenir compte du filtre
	 */
	public List<FavoriteDisplay> getAllFavorites() {
		String publikUserId = "";
		HttpServletRequest servletRequest = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(servletRequest, "publik_logged_in");
		if (isLoggedIn) {
			publikUserId = SessionParamUtil.getString(servletRequest, "publik_internal_id");
		}

		List<Favorite> userFavorites = FavoriteLocalServiceUtil.getByPublikUser(publikUserId);
		List<FavoriteDisplay> favoritesDisplay = new ArrayList<FavoriteDisplay>();

		for (Favorite favorite : userFavorites) {
			favoritesDisplay.add(new FavoriteDisplay(favorite, publikUserId, themeDisplay));
		}
		favoritesDisplay.sort(Comparator.comparing(FavoriteDisplay::getFavoriteId));
		Collections.reverse(favoritesDisplay);

		return favoritesDisplay;
	}
	
	/**
	 * Retourne la liste des types de favoris présent dans la liste de favoris de l'utilisateur
	 */
	public List<FavoriteType> getFavoritesTypeFromUserFavorites() {
		List<FavoriteType> favoritesType = FavoriteType.getAll();			
		List<Long> typesIdFromUserFavorites = getAllFavorites().stream().map(x->x.getTypeId()).collect(Collectors.toList());
		List<FavoriteType> favoriteTypeFromUserFavorite = new ArrayList<FavoriteType>();
		
		for (FavoriteType favoriteType : favoritesType) {
			if(typesIdFromUserFavorites.contains(favoriteType.getId())) {
				favoriteTypeFromUserFavorite.add(favoriteType);
			}
		}
		
		favoriteTypeFromUserFavorite.sort(Comparator.comparing(FavoriteType::getName));
		return favoriteTypeFromUserFavorite;
	}

}
