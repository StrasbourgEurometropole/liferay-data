package eu.strasbourg.portlet.objtp.portlet.context;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.objtp.model.ObjectCategory;
import eu.strasbourg.service.objtp.service.ObjectCategoryLocalServiceUtil;


public class ObjtpDisplayContext {

	private PortletRequest request;
	private RenderResponse response;
	private ThemeDisplay themeDisplay;
	private List<ObjectCategory> objectCategories;
	

	public ObjtpDisplayContext(PortletRequest request, RenderResponse response) {
		this.request = request;
		this.response = response;
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);		
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
}
