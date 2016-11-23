
package eu.strasbourg.picker.taglib.journal.servlet.taglib;

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
import com.liferay.journal.item.selector.criterion.JournalItemSelectorCriterion;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import eu.strasbourg.picker.taglib.layout.internal.servlet.ServletContextUtil;

/**
 * @author Benjamin Bini
 */
public class JournalPickerTag extends IncludeTag {

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

		// Articles
		// On a besoin de Map faisant correspondre :
		// * pour chaque langue, la liste des articles
		// * pour chaque langue, la liste des ids
		// Cas sans localisation
		if (!"true".equals(_localized)) {
			Locale defaultLocale = themeDisplay.getSiteDefaultLocale();
			List<JournalArticle> articles = new ArrayList<JournalArticle>();

			for (String articleUuid : _value.split(",")) {
				JournalArticle article = JournalArticleLocalServiceUtil
					.fetchJournalArticleByUuidAndGroupId(articleUuid,
						themeDisplay.getScopeGroupId());
				if (article != null) {
					articles.add(article);
				}
			}
			Map<Locale, List<JournalArticle>> locale_articles = new HashMap<Locale, List<JournalArticle>>();
			Map<Locale, String> locale_articlesUuids = new HashMap<Locale, String>();
			locale_articles.put(defaultLocale, articles);
			locale_articlesUuids.put(defaultLocale, _value);
			request.setAttribute("locale_articles", locale_articles);
			request.setAttribute("locale_articlesUuids", locale_articlesUuids);
		}
		// avec localisation
		else {
			Map<Locale, List<JournalArticle>> locale_articles = new HashMap<Locale, List<JournalArticle>>();
			Map<Locale, String> locale_articlesUuids = LocalizationUtil
				.getLocalizationMap(_value);

			for (Entry<Locale, String> locale_articleUuid : locale_articlesUuids
				.entrySet()) {
				List<JournalArticle> articles = new ArrayList<JournalArticle>();
				for (String articleUuid : locale_articleUuid.getValue()
					.split(",")) {
					JournalArticle article = JournalArticleLocalServiceUtil
						.fetchJournalArticleByUuidAndGroupId(articleUuid,
							themeDisplay.getScopeGroupId());
					if (article != null) {
						articles.add(article);
					}
				}
				locale_articles.put(locale_articleUuid.getKey(), articles);
			}
			request.setAttribute("locale_articles", locale_articles);
			request.setAttribute("locale_articlesUuids", locale_articlesUuids);
		}

		// ItemSelector URL
		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes = new ArrayList<ItemSelectorReturnType>();
		desiredItemSelectorReturnTypes.add(new UUIDItemSelectorReturnType());
		JournalItemSelectorCriterion journalItemSelectorCriterion = new JournalItemSelectorCriterion();
		
		journalItemSelectorCriterion
			.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);

		for (Locale locale : availableLocales) {
			PortletURL itemSelectorURL = ServletContextUtil.getItemSelector()
				.getItemSelectorURL(
					RequestBackedPortletURLFactoryUtil.create(request),
					"itemSelected" + _name + locale.getLanguage(),
					journalItemSelectorCriterion);
			request.setAttribute("itemSelectorURL" + locale.getLanguage(),
				itemSelectorURL);
		}
	}

	private static final String _PAGE = "/journal/journal-picker-page.jsp";

	private String _label;
	private String _name;
	private String _required;
	private String _value;
	private String _multiple;
	private String _localized;
}