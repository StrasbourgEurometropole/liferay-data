package eu.strasbourg.portlet.edition.itemselector;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;

@Component(
	property = { "item.selector.view.order:Integer=200" },
	service = ItemSelectorView.class)
public class EditionGalleryItemSelectorView
	implements ItemSelectorView<EditionGalleryItemSelectorCriterion> {

	private ServletContext _servletContext;

	private static final List<ItemSelectorReturnType> _supportedItemSelectorReturnTypes = Collections
		.unmodifiableList(ListUtil.fromArray(
			new ItemSelectorReturnType[] { new URLItemSelectorReturnType() }));

	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(target = "(osgi.web.symbolicname=edition.web)", unbind = "-")
	public void setServletContext(ServletContext servletContext) {
		this._servletContext = servletContext;
	}

	@Override
	public Class<EditionGalleryItemSelectorCriterion> getItemSelectorCriterionClass() {
		return EditionGalleryItemSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return "Galeries";
	}

	@Override
	public boolean isShowSearch() {
		return true;
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		return true;
	}

	@Override
	public void renderHTML(ServletRequest servletRequest,
		ServletResponse servletResponse,
		EditionGalleryItemSelectorCriterion itemSelectorCriterion,
		PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) servletRequest
			.getAttribute(WebKeys.THEME_DISPLAY);

		boolean multiple = GetterUtil
			.getBoolean(servletRequest.getParameter("multiple"), false);

		int delta = GetterUtil.getInteger(
			servletRequest.getParameter(SearchContainer.DEFAULT_DELTA_PARAM),
			SearchContainer.DEFAULT_DELTA);

		int cur = GetterUtil.getInteger(
			servletRequest.getParameter(SearchContainer.DEFAULT_CUR_PARAM),
			SearchContainer.DEFAULT_CUR);

		String keywords = GetterUtil
			.getString(servletRequest.getParameter("keywords"));

		List<EditionGallery> galleries = EditionGalleryLocalServiceUtil.findByKeyword(keywords,
			themeDisplay.getScopeGroupId(), (delta * cur) - delta,
			((delta * cur) + delta));

		long galleriesCount = EditionGalleryLocalServiceUtil.findByKeywordCount(
			keywords, themeDisplay.getScopeGroupId());

		servletRequest.setAttribute("total", galleriesCount);
		servletRequest.setAttribute("galleries", galleries);
		servletRequest.setAttribute("portletURL", portletURL);
		servletRequest.setAttribute("itemSelectedEventName",
			itemSelectedEventName);
		servletRequest.setAttribute("multiple", multiple);

		ServletContext servletContext = getServletContext();
		
		RequestDispatcher requestDispatcher = servletContext
			.getRequestDispatcher(
				"/edition-gallery/item-selector/gallery-item-selector.jsp");

		requestDispatcher.include(servletRequest, servletResponse);
	}

}
