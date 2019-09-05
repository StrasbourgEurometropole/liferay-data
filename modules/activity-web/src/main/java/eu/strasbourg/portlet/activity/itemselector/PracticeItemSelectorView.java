package eu.strasbourg.portlet.activity.itemselector;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.activity.model.Practice;
import eu.strasbourg.service.activity.service.PracticeLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.servlet.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component(
	property = { "item.selector.view.order:Integer=210" },
	service = ItemSelectorView.class)
public class PracticeItemSelectorView
	implements ItemSelectorView<PracticeItemSelectorCriterion> {

	private ServletContext _servletContext;

	private PracticeLocalService practiceLocalService;

	@Reference(unbind = "-")
	public void setPracticeLocalService(
			PracticeLocalService practiceLocalService) {
		this.practiceLocalService = practiceLocalService;
	}

	private static final List<ItemSelectorReturnType> _supportedItemSelectorReturnTypes = Collections
		.unmodifiableList(ListUtil.fromArray(
			new ItemSelectorReturnType[] { new URLItemSelectorReturnType() }));

	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(target = "(osgi.web.symbolicname=activity.web)", unbind = "-")
	public void setServletContext(ServletContext servletContext) {
		this._servletContext = servletContext;
	}

	@Override
	public Class<PracticeItemSelectorCriterion> getItemSelectorCriterionClass() {
		return PracticeItemSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return "Practice";
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
	    PracticeItemSelectorCriterion itemSelectorCriterion,
		PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) servletRequest
			.getAttribute(WebKeys.THEME_DISPLAY);

		boolean multiple = GetterUtil
			.getBoolean(servletRequest.getParameter("multiple"), false);
		portletURL.setParameter("multiple", String.valueOf(multiple));

		int delta = GetterUtil.getInteger(
			servletRequest.getParameter(SearchContainer.DEFAULT_DELTA_PARAM),
			SearchContainer.DEFAULT_DELTA);

		int cur = GetterUtil.getInteger(
			servletRequest.getParameter(SearchContainer.DEFAULT_CUR_PARAM),
			SearchContainer.DEFAULT_CUR);

		String keywords = GetterUtil
			.getString(servletRequest.getParameter("keywords"));

		List<Practice> practices = practiceLocalService
			.findByKeyword(keywords, themeDisplay.getScopeGroupId(),
				(delta * cur) - delta, ((delta * cur) + delta));

		long practicesCount = practiceLocalService
			.findByKeywordCount(keywords, themeDisplay.getScopeGroupId());

		servletRequest.setAttribute("total", practicesCount);
		servletRequest.setAttribute("practices", practices);
		servletRequest.setAttribute("portletURL", portletURL);
		servletRequest.setAttribute("itemSelectedEventName",
			itemSelectedEventName);
		servletRequest.setAttribute("multiple", multiple);

		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher = servletContext
			.getRequestDispatcher(
				"/practice/item-selector/practice-item-selector.jsp");

		requestDispatcher.include(servletRequest, servletResponse);
	}

}
