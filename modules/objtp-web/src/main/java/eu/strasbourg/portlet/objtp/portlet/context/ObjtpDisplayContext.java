package eu.strasbourg.portlet.objtp.portlet.context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.objtp.model.FoundObject;
import eu.strasbourg.service.objtp.model.ObjectCategory;
import eu.strasbourg.service.objtp.service.FoundObjectLocalServiceUtil;
import eu.strasbourg.service.objtp.service.ObjectCategoryLocalServiceUtil;
import eu.strasbourg.utils.Pager;


public class ObjtpDisplayContext {

	private PortletRequest request;
	private RenderResponse response;
	private ThemeDisplay themeDisplay;
	private List<ObjectCategory> objectCategories;
	private List<FoundObject> objects;
	private String categoryCodes;
	private List<FoundObject> objectsFilter;
	private SearchContainer<FoundObject> searchContainer;

	public ObjtpDisplayContext(PortletRequest request, RenderResponse response, String categoryCodes) {
		this.request = request;
		this.response = response;
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);		
		this.categoryCodes = categoryCodes;
		
	}
	
	/**
	 * Récupère tous les catégories d'objets trouvés
	 */
	public List<ObjectCategory> getObjectCategories() {
		if (objectCategories == null) {
			objectCategories = ObjectCategoryLocalServiceUtil.getObjectCategories(-1, -1);
		}
		return objectCategories;
	}
	
	/**
	 * Récupère tous les objets trouvés
	 */
	public List<FoundObject> getObjects() {
		if (objects == null) {
			objects = FoundObjectLocalServiceUtil.getFoundObjects(-1, -1);
		}
		return objects;
	}
	
	/**
	 * Récupère tous les objets trouvés selon la liste des catégories sélectionnés dans la config du portlet
	 * Ne récupère que les objets possédant une image
	 */
	public List<FoundObject> getObjectsFilter() {		
		this.objectsFilter = new ArrayList<FoundObject>();
		String[] arrayCategoryCodes = categoryCodes.split(",");
		for (String categoryCode : arrayCategoryCodes) {
			for (FoundObject foundObject : FoundObjectLocalServiceUtil.getFoundObjectByCategoryCode(categoryCode)) {
				// On ne récupère que les objets possédant une image
				if(Validator.isNotNull(foundObject.getImageUrl())) {
					objectsFilter.add(foundObject);
				}
			}		
		}	
		objectsFilter.sort(Comparator.comparing(FoundObject::getDate));
		Collections.reverse(objectsFilter);
		return objectsFilter;
	}
	
	
	/**
	 * Retourne les résultats entre les indexes start (inclu) et end (non inclu)
	 */
	public List<FoundObject> getPaginatedResults() {
		return ListUtil.subList(getObjectsFilter(), this.getSearchContainer().getStart(), this.getSearchContainer().getEnd());
	}

	/**
	 * Retourne le nombre total de résultats
	 */
	public int getCount() {
		return getObjectsFilter().size();
	}

	/**
	 * Retourne le searchContainer
	 */
	public SearchContainer<FoundObject> getSearchContainer() {
		if (searchContainer == null) {
			Map<String, String[]> parameterMap = request.getParameterMap();
			PortletURL iteratorURL = response.createRenderURL();
			iteratorURL.setParameters(parameterMap);
			searchContainer = new SearchContainer<FoundObject>(request, iteratorURL, null,
					"no-entries-were-found");
			searchContainer.setDelta(this.getDelta());
			searchContainer.setTotal(this.getCount());
			searchContainer.setResults(this.getObjectsFilter());
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
		return 20;
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

	
	
	
	
	
	
	
}
