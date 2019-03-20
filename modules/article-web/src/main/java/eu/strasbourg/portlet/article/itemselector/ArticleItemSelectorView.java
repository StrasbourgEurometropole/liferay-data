package eu.strasbourg.portlet.article.itemselector;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component(
	property = { "item.selector.view.order:Integer=47"},
	service = ItemSelectorView.class)
public class ArticleItemSelectorView
	implements ItemSelectorView<ArticleItemSelectorCriterion> {

	private ServletContext _servletContext;

	private static final List<ItemSelectorReturnType> _supportedItemSelectorReturnTypes = Collections
		.unmodifiableList(ListUtil.fromArray(
			new ItemSelectorReturnType[] { new URLItemSelectorReturnType() }));

	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(target = "(osgi.web.symbolicname=article.web)", unbind = "-")
	public void setServletContext(ServletContext servletContext) {
		this._servletContext = servletContext;
	}

	@Override
	public Class<ArticleItemSelectorCriterion> getItemSelectorCriterionClass() {
		return ArticleItemSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return LanguageUtil.get(Locale.FRANCE, "eu.articles");
	}

	@Override
	public boolean isShowSearch() {
		return true;
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		return true;
	}

	public List<JournalArticle> getArticles(ServletRequest servletRequest){
		ThemeDisplay themeDisplay = (ThemeDisplay) servletRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		long folderActuId = 0;
		List<JournalFolder> foldersActualite = JournalFolderLocalServiceUtil.getFolders(themeDisplay.getScopeGroupId(), 0)
				.stream().filter(f -> f.getName().startsWith("Actualit")).collect(Collectors.toList());
		if(!foldersActualite.isEmpty())
			folderActuId = foldersActualite.get(0).getFolderId();
		long folderWebmagId = 0;
		List<JournalFolder> foldersWebmag = JournalFolderLocalServiceUtil.getFolders(themeDisplay.getScopeGroupId(), folderActuId)
				.stream().filter(f -> f.getName().equals("Webmag")).collect(Collectors.toList());
		if(!foldersWebmag.isEmpty())
			folderWebmagId = foldersWebmag.get(0).getFolderId();

		List<JournalArticle> articles = new ArrayList<JournalArticle>();
		List<JournalArticle> actus = JournalArticleLocalServiceUtil.getArticles(
				themeDisplay.getScopeGroupId(), folderActuId, 0, -1, -1);
		articles.addAll(actus);
		List<JournalArticle> webmags = JournalArticleLocalServiceUtil.getArticles(
				themeDisplay.getScopeGroupId(), folderWebmagId, 0, -1, -1);
		articles.addAll(webmags);

		return articles;
	}

	@Override
	public void renderHTML(ServletRequest servletRequest,
		ServletResponse servletResponse,
		ArticleItemSelectorCriterion itemSelectorCriterion,
		PortletURL portletURL, String itemSelectedArticleName, boolean search)
		throws IOException, ServletException {

		boolean multiple = GetterUtil
			.getBoolean(servletRequest.getParameter("multiple"), false);
		portletURL.setParameter("multiple", String.valueOf(multiple));

		int delta = GetterUtil.getInteger(
			servletRequest.getParameter(SearchContainer.DEFAULT_DELTA_PARAM),
			SearchContainer.DEFAULT_DELTA);

		int cur = GetterUtil.getInteger(
			servletRequest.getParameter(SearchContainer.DEFAULT_CUR_PARAM),
			SearchContainer.DEFAULT_CUR);

		List<JournalArticle> articles = getArticles(servletRequest);

		String keywords = GetterUtil
			.getString(servletRequest.getParameter("keywords"));
		if(Validator.isNotNull(keywords))
			articles = articles.stream().filter(a -> a.getTitle().contains(keywords)).collect(Collectors.toList());

		int articlesCount = articles.size();

		if(articlesCount < (delta * cur) - delta)
			cur = 1;

		articles = articles.subList(((delta * cur) - delta), articlesCount >= (delta * cur) ? (delta * cur) : articlesCount);


		servletRequest.setAttribute("total", articlesCount);
		servletRequest.setAttribute("articles", articles);
		servletRequest.setAttribute("portletURL", portletURL);
		servletRequest.setAttribute("itemSelectedArticleName",
			itemSelectedArticleName);
		servletRequest.setAttribute("multiple", multiple);

		ServletContext servletContext = getServletContext();
		
		RequestDispatcher requestDispatcher = servletContext
			.getRequestDispatcher(
				"/article/item-selector/article-item-selector.jsp");

		requestDispatcher.include(servletRequest, servletResponse);
	}

}
