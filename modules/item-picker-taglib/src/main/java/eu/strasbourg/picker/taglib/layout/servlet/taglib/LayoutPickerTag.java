
package eu.strasbourg.picker.taglib.layout.servlet.taglib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import eu.strasbourg.picker.taglib.layout.internal.servlet.ServletContextUtil;
import eu.strasbourg.utils.LayoutHelperImpl;

/**
 * @author Benjamin Bini
 */
public class LayoutPickerTag extends IncludeTag {

	public void setName(String name) {
		_name = name;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setRequired(String required) {
		_required = required;
	}

	public void setValue(String value) {
		_value = value;
	}

	public void setMultiple(String multiple) {
		_multiple = multiple;
	}

	public void setLocalized(String localized) {
		_localized = localized;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		_name = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("name", _name);
		request.setAttribute("label", _label);
		request.setAttribute("required", "true".equals(_required));
		request.setAttribute("value", "0".equals(_value) ? "" : _value);
		request.setAttribute("multiple", "true".equals(_multiple));
		request.setAttribute("localized", "true".equals(_localized));

		// Available locales
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		Set<Locale> availableLocalesSet = LanguageUtil
			.getAvailableLocales(themeDisplay.getScopeGroupId());
		Locale[] availableLocales;
		if ("true".equals(_localized)) {
			availableLocales = availableLocalesSet
				.toArray(new Locale[availableLocalesSet.size()]);
		} else {
			availableLocales = new Locale[] {
				themeDisplay.getSiteDefaultLocale() };
		}
		request.setAttribute("availableLocales", availableLocales);
		request.setAttribute("defaultLocale",
			themeDisplay.getSiteDefaultLocale());

		// Layouts
		// On a besoin de Map faisant correspondre :
		// * pour chaque langue, la liste des layouts
		// * pour chaque langue, la liste des ids
		// Cas sans localisation
		if (!"true".equals(_localized)) {
			Locale defaultLocale = themeDisplay.getSiteDefaultLocale();
			List<Layout> layouts = new ArrayList<Layout>();

			for (String layoutUuid : _value.split(",")) {
				Layout layout = LayoutLocalServiceUtil
					.fetchLayoutByUuidAndGroupId(layoutUuid,
						themeDisplay.getScopeGroupId(), false);
				if (layout != null) {
					layouts.add(layout);
				}
			}
			Map<Locale, List<Layout>> locale_layouts = new HashMap<Locale, List<Layout>>();
			Map<Locale, String> locale_layoutsUuids = new HashMap<Locale, String>();
			locale_layouts.put(defaultLocale, layouts);
			locale_layoutsUuids.put(defaultLocale, _value);
			request.setAttribute("locale_layouts", locale_layouts);
			request.setAttribute("locale_layoutsUuids", locale_layoutsUuids);
		}
		// avec localisation
		else {
			Map<Locale, List<Layout>> locale_layouts = new HashMap<Locale, List<Layout>>();
			Map<Locale, String> locale_layoutsUuids = LocalizationUtil
				.getLocalizationMap(_value);

			for (Entry<Locale, String> locale_layoutUuid : locale_layoutsUuids
				.entrySet()) {
				List<Layout> layouts = new ArrayList<Layout>();
				for (String layoutUuid : locale_layoutUuid.getValue()
					.split(",")) {
					Layout layout = LayoutLocalServiceUtil
						.fetchLayoutByUuidAndGroupId(layoutUuid,
							themeDisplay.getScopeGroupId(), false);
					if (layout != null) {
						layouts.add(layout);
					}
				}
				locale_layouts.put(locale_layoutUuid.getKey(), layouts);
			}
			request.setAttribute("locale_layouts", locale_layouts);
			request.setAttribute("locale_layoutsUuids", locale_layoutsUuids);
		}

		// ItemSelector URL
		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes = new ArrayList<ItemSelectorReturnType>();
		desiredItemSelectorReturnTypes.add(new UUIDItemSelectorReturnType());
		LayoutItemSelectorCriterion layoutItemSelectorCriterion = new LayoutItemSelectorCriterion();

		layoutItemSelectorCriterion
			.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);

		for (Locale locale : availableLocales) {
			PortletURL itemSelectorURL = ServletContextUtil.getItemSelector()
				.getItemSelectorURL(
					RequestBackedPortletURLFactoryUtil.create(request),
					"itemSelected" + _name + locale.getLanguage(),
					layoutItemSelectorCriterion);
			request.setAttribute("itemSelectorURL" + locale.getLanguage(),
				itemSelectorURL);
		}
		
		// On passe également le service permettant d'afficher le path des layouts
		request.setAttribute("layoutHelper", new LayoutHelperImpl());
	}

	private static final String _PAGE = "/layout/layout-picker-page.jsp";

	private String _label;
	private String _name;
	private String _required;
	private String _value;
	private String _multiple;
	private String _localized;
}