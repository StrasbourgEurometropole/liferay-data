package eu.strasbourg.portlet.activity.display.context;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.activity.model.Association;
import eu.strasbourg.service.activity.model.AssociationActivity;
import eu.strasbourg.service.activity.service.AssociationLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.*;
import java.util.stream.Collectors;

public class EditAssociationDisplayContext {

	private Association association;
	private final RenderRequest request;
	private final ThemeDisplay themeDisplay;

	public EditAssociationDisplayContext(RenderRequest request,
                                         RenderResponse response) {
		this.request = request;
		this.themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Association getAssociation() throws PortalException {
		long associationId = ParamUtil.getLong(request, "associationId");
		if (association == null && associationId > 0) {
			association = AssociationLocalServiceUtil
				.getAssociation(associationId);
		}
		return association;
	}

	/**
	 * Retourne l'entité éditée
	 */
	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil
			.getAvailableLocales(themeDisplay.getScopeGroupId());
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}
	
	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return themeDisplay.getPermissionChecker().hasPermission(
			this.themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.ACTIVITY_BO,
			StrasbourgPortletKeys.ACTIVITY_BO, actionId);
	}

	/**
	 * Renvoie les indexes des activités par défaut
	 */
	public String getDefaultIndexes(int length) {
		String indexes = "";
		for (int i = 1; i <= length; i++) {
			if (Validator.isNotNull(indexes)) {
				indexes += ",";
			}
			indexes += i;
		}
		return indexes;
	}

	/**
	 * Renvoie les catégories Id de l'activité
	 */
	public String getAssociationActivityCategoriesIds(int associationActivityId){
		String categoriesIdsString = "";
		List<Long> categoriesIds =  AssetCategoryLocalServiceUtil.getCategories(AssociationActivity.class.getName(),associationActivityId).stream()
				.map(c -> c.getCategoryId()).collect(Collectors.toList());
		categoriesIdsString =  categoriesIds.stream().map(Object::toString).collect(Collectors.joining(","));

		return categoriesIdsString;
	}
}
