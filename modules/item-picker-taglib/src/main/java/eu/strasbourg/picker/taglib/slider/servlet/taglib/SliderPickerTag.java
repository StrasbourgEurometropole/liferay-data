
package eu.strasbourg.picker.taglib.slider.servlet.taglib;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;
import eu.strasbourg.picker.taglib.layout.internal.servlet.ServletContextUtil;
import eu.strasbourg.portlet.agenda.itemselector.EventItemSelectorCriterion;
import eu.strasbourg.portlet.article.itemselector.ArticleItemSelectorCriterion;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Bini
 */
public class SliderPickerTag extends IncludeTag {

	public void setName(String name) {
		_name = name;
	}

	public void setValue(String value) {
		_value = value;
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
		request.setAttribute("value", "0".equals(_value) ? "" : _value);
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

		// Evènements
		List<Event> events = new ArrayList<>();
		for (String entityId : _value.split(",")) {
			if (Validator.isNumber(entityId) && Long.parseLong(entityId) > 0) {
				Event event;
				event = EventLocalServiceUtil.fetchEvent(Long.parseLong(entityId));
				if (event != null) {
					events.add(event);
				}
			}
		}
		request.setAttribute("events", events);

		// webcontent
		List<JournalArticle> articles = new ArrayList<JournalArticle>();
		for (String resourcePrimKey : _value.split(",")) {
			if (Validator.isNotNull(resourcePrimKey)) {
				JournalArticle article;
				article = JournalArticleLocalServiceUtil.fetchLatestArticle(Long.parseLong(resourcePrimKey), 0);
				if (article != null) {
					articles.add(article);
				}
			}
		}
		request.setAttribute("articles", articles);

		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes = new ArrayList<>();
		desiredItemSelectorReturnTypes.add(new URLItemSelectorReturnType());

		// Evènement
		EventItemSelectorCriterion eventItemSelectorCriterion = new EventItemSelectorCriterion();
		eventItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
				desiredItemSelectorReturnTypes);
		PortletURL itemEventSelectorURL = eu.strasbourg.picker.taglib.file.internal.servlet.ServletContextUtil.getItemSelector()
				.getItemSelectorURL(
						RequestBackedPortletURLFactoryUtil.create(request),
						"itemSelected" + _name, eventItemSelectorCriterion);
		// Si l'attribut "global" est "true", on se met sur le groupe global en
		// modifiant l'URL
		String itemEventSelectorURLString = itemEventSelectorURL.toString();
		request.setAttribute("itemEventSelectorURL", itemEventSelectorURLString);

		// Contenu web
		ArticleItemSelectorCriterion articleItemSelectorCriterion = new ArticleItemSelectorCriterion();
		articleItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
				desiredItemSelectorReturnTypes);
		PortletURL itemArticleSelectorURL = eu.strasbourg.picker.taglib.file.internal.servlet.ServletContextUtil.getItemSelector()
				.getItemSelectorURL(
						RequestBackedPortletURLFactoryUtil.create(request),
						"itemSelected" + _name, articleItemSelectorCriterion);
		// Si l'attribut "global" est "true", on se met sur le groupe global en
		// modifiant l'URL
		String itemArticleSelectorURLString = itemArticleSelectorURL.toString();
		request.setAttribute("itemArticleSelectorURL", itemArticleSelectorURLString);

	}

	private static final String _PAGE = "/slider/slider-picker-page.jsp";

	private String _name;
	private String _value;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}