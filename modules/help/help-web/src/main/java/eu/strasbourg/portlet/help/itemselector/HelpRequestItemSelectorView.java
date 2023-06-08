package eu.strasbourg.portlet.help.itemselector;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpProposalLocalServiceUtil;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.servlet.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component(
        property = { "item.selector.view.order:Integer=200" },
        service = ItemSelectorView.class)
public class HelpRequestItemSelectorView  implements ItemSelectorView<HelpRequestItemSelectorCriterion> {

    private ServletContext _servletContext;

    private static final List<ItemSelectorReturnType> _supportedItemSelectorReturnTypes = Collections
            .unmodifiableList(ListUtil.fromArray(new URLItemSelectorReturnType()));

    public ServletContext getServletContext() {
        return _servletContext;
    }

    @Reference(target = "(osgi.web.symbolicname=eu.strasbourg.portlet.help.web)", unbind = "-")
    public void setServletContext(ServletContext servletContext) {
        this._servletContext = servletContext;
    }

    @Override
    public Class<HelpRequestItemSelectorCriterion> getItemSelectorCriterionClass() {
        return HelpRequestItemSelectorCriterion.class;
    }

    @Override
    public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
        return _supportedItemSelectorReturnTypes;
    }

    @Override
    public String getTitle(Locale locale) {
        return "HelpRequests";
    }

    @Override
    public boolean isVisible(ThemeDisplay themeDisplay) {
        return true;
    }

    @Override
    public void renderHTML(ServletRequest servletRequest,
                           ServletResponse servletResponse,
                           HelpRequestItemSelectorCriterion itemSelectorCriterion,
                           PortletURL portletURL, String itemSelectedHelpRequestName, boolean search)
            throws IOException, ServletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) servletRequest
                .getAttribute(WebKeys.THEME_DISPLAY);

        boolean multiple = GetterUtil.getBoolean(servletRequest.getParameter("multiple"), false);
        // TODO : A corriger lorsque portlet 3.0 OK sur itemSelectorPortlet
        portletURL.setParameter("multiple", String.valueOf(multiple));

        int delta = GetterUtil.getInteger(
                servletRequest.getParameter(SearchContainer.DEFAULT_DELTA_PARAM),
                SearchContainer.DEFAULT_DELTA);

        int cur = GetterUtil.getInteger(
                servletRequest.getParameter(SearchContainer.DEFAULT_CUR_PARAM),
                SearchContainer.DEFAULT_CUR);

        String keywords = GetterUtil
                .getString(servletRequest.getParameter("keywords"));

        List<HelpRequest> HelpRequests = HelpRequestLocalServiceUtil.findByKeyword(keywords,
                themeDisplay.getScopeGroupId(), (delta * cur) - delta,
                (delta * cur));
        long helpRequestsCount = HelpRequestLocalServiceUtil.findByKeywordCount(
                keywords, themeDisplay.getScopeGroupId());

        servletRequest.setAttribute("total", helpRequestsCount);
        servletRequest.setAttribute("helpRequests", HelpRequests);
        servletRequest.setAttribute("portletURL", portletURL);
        servletRequest.setAttribute("itemSelectedHelpRequestName",
                itemSelectedHelpRequestName);
        servletRequest.setAttribute("multiple", multiple);

        ServletContext servletContext = getServletContext();

        RequestDispatcher requestDispatcher = servletContext
                .getRequestDispatcher(
                        "/helpRequest/item-selector/helpRequest-item-selector.jsp");

        requestDispatcher.include(servletRequest, servletResponse);
    }
}
