
package eu.strasbourg.picker.taglib.file.servlet.taglib;

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
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import eu.strasbourg.picker.taglib.FileObject;
import eu.strasbourg.picker.taglib.file.internal.servlet.ServletContextUtil;

/**
 * @author Benjamin Bini
 */
public class FilePickerTag extends IncludeTag {

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
			.getAvailableLocales(themeDisplay.getSiteGroupIdOrLiveGroupId());
		Locale[] availableLocales;
		if ("true".equals(_localized)) {
			availableLocales = availableLocalesSet
				.toArray(new Locale[availableLocalesSet.size()]);
		} else {
			availableLocales = new Locale[] { new Locale("fr") };
		}
		request.setAttribute("availableLocales", availableLocales);

		// Fichiers
		// On a besoin de Map faisant correspondre :
		// * pour chaque langue, la liste des fichiers
		// * pour chaque langue, la liste des ids
		// Cas sans localisation
		if (!"true".equals(_localized)) {
			Locale defaultLocale = new Locale("fr");
			List<FileObject> files = new ArrayList<FileObject>();

			for (String fileId : _value.split(",")) {
				if (Validator.isNumber(fileId) && Long.parseLong(fileId) > 0) {
					FileObject file = new FileObject(Long.parseLong(fileId));
					if (file != null && file.getId() > 0) {
						files.add(file);
					}
				}
			}
			Map<Locale, List> language_files = new HashMap<Locale, List>();
			Map<Locale, String> language_fileIds = new HashMap<Locale, String>();
			language_files.put(defaultLocale, files);
			language_fileIds.put(defaultLocale, _value);
			request.setAttribute("locale_files", language_files);
			request.setAttribute("locale_filesIds", language_fileIds);
		}
		// avec localisation
		else {
			Map<Locale, List> locale_files = new HashMap<Locale, List>();
			Map<Locale, String> locale_filesIds = LocalizationUtil
				.getLocalizationMap(_value);

			for (Entry<Locale, String> locale_fileId : locale_filesIds
				.entrySet()) {
				List<FileObject> files = new ArrayList<FileObject>();
				for (String fileId : locale_fileId.getValue().split(",")) {
					if (Validator.isNumber(fileId)
						&& Long.parseLong(fileId) > 0) {
						FileObject file = new FileObject(
							Long.parseLong(fileId));
						if (file != null && file.getId() > 0) {
							files.add(file);
						}
					}
				}
				locale_files.put(locale_fileId.getKey(), files);
			}
			request.setAttribute("locale_files", locale_files);
			request.setAttribute("locale_filesIds", locale_filesIds);
		}

		// ItemSelector URL
		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes = new ArrayList<>();
		desiredItemSelectorReturnTypes
			.add(new FileEntryItemSelectorReturnType());
		FileItemSelectorCriterion fileItemSelectorCriterion = new FileItemSelectorCriterion();
		fileItemSelectorCriterion
			.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);

		for (Locale locale : availableLocales) {
			PortletURL itemSelectorURL = ServletContextUtil.getItemSelector()
				.getItemSelectorURL(
					RequestBackedPortletURLFactoryUtil.create(request),
					"itemSelected" + _name + locale.getLanguage(),
					fileItemSelectorCriterion);
			request.setAttribute("itemSelectorURL" + locale.getLanguage(),
				itemSelectorURL);
		}
	}

	private static final String _PAGE = "/file/file-picker-page.jsp";

	private String _label;
	private String _name;
	private String _required;
	private String _value;
	private String _multiple;
	private String _localized;

}