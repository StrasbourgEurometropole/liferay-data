package eu.strasbourg.portlet.favorites.display.context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;

import eu.strasbourg.portlet.favorites.display.FavoriteDisplay;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.utils.PortletHelper;

public class FavoritesDisplayContext {

	private PortletRequest request;
	private ThemeDisplay themeDisplay;

	private List<FavoriteDisplay> favorites;
	private List<FavoriteDisplay> lastFavorites;

	public FavoritesDisplayContext(PortletRequest request, ThemeDisplay themeDisplay) {
		this.request = request;
		this.themeDisplay = themeDisplay;
	}

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
		jourChoisi.set(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
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

	public int getCount() {
		return getFavorites().size();
	}

	public List<FavoriteType> getFavoritesType() {
		List<FavoriteType> favoritesType = FavoriteType.getAll();
		favoritesType.sort(Comparator.comparing(FavoriteType::getName));
		return favoritesType;
	}

}
