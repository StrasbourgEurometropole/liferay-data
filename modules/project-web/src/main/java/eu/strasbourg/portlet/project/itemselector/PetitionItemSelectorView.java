package eu.strasbourg.portlet.project.itemselector;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author alexandre.quere
 */
public class PetitionItemSelectorView implements ItemSelectorView<PetitionItemSelectorCriterion> {

    private ServletContext _servletContext;

    private static final List<ItemSelectorReturnType> _supportedItemSelectorReturnTypes =
            Collections.unmodifiableList(ListUtil.fromArray(
                    new ItemSelectorReturnType[] { new URLItemSelectorReturnType() }));

    public ServletContext getServletContext(){
        return _servletContext;
    }

    @Reference(target = "(osgi.web.symbolicname=project.web)", unbind = "-")
    public void set_servletContext(ServletContext _servletContext) {
        this._servletContext = _servletContext;
    }

    @Override
    public Class<PetitionItemSelectorCriterion> getItemSelectorCriterionClass() {
        return PetitionItemSelectorCriterion.class;
    }

    @Override
    public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
        return _supportedItemSelectorReturnTypes;
    }

    @Override
    public String getTitle(Locale locale) {
        return "Petitions";
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
                           PetitionItemSelectorCriterion itemSelectorCriterion,
                           PortletURL portletURL, String itemSelectedPetitionName,
                           boolean search) throws IOException, ServletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) servletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        boolean multiple = GetterUtil.getBoolean(servletRequest.getParameter("multiple"), false);
        portletURL.setParameter("multiple",String.valueOf(multiple));

        int delta = GetterUtil.getInteger(servletRequest.getParameter(SearchContainer.DEFAULT_DELTA_PARAM),
                SearchContainer.DEFAULT_DELTA);

        int cur = GetterUtil.getInteger(
                servletRequest.getParameter(SearchContainer.DEFAULT_CUR_PARAM),
                SearchContainer.DEFAULT_CUR);

        String keywords = GetterUtil.getString(servletRequest.getParameter("keywords"));

        List<Petition> petitions = PetitionLocalServiceUtil.findByKeyword(keywords,themeDisplay.getScopeGroupId(),((delta*cur)+delta),((delta*cur)+delta));

        long petitionsCount = PetitionLocalServiceUtil.findByKeywordCount(keywords,themeDisplay.getScopeGroupId());

        servletRequest.setAttribute("total",petitionsCount);
        servletRequest.setAttribute("petitions",petitions);
        servletRequest.setAttribute("portletURL",portletURL);
        servletRequest.setAttribute("itemSelectedPetitionName",itemSelectedPetitionName);
        servletRequest.setAttribute("multiple",multiple);

        ServletContext servletContext = getServletContext();

        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/project/item-selector/petition-item-selector.jsp");

        requestDispatcher.include(servletRequest,servletResponse);

    }
}
