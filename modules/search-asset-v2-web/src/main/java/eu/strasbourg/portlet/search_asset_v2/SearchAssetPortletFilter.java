package eu.strasbourg.portlet.search_asset_v2;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.portlet.search_asset_v2.constants.SearchAssetPortletKeys;
import eu.strasbourg.service.search.log.model.SearchLog;
import eu.strasbourg.service.search.log.service.SearchLogLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import java.io.IOException;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SearchAssetPortletKeys.SEARCHASSET },
	service = PortletFilter.class)
public class SearchAssetPortletFilter implements ActionFilter {

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ActionRequest request, ActionResponse response,
		FilterChain chain) throws IOException, PortletException {
		String detailURL = ParamUtil.getString(request, "detailURL");
		// Si on a une URL de détail c'est que l'on souhaite logger le choix de
		// l'utilsateur et partir vers cette page
		if (Validator.isNotNull(detailURL)) {
			long searchLogId = ParamUtil.getLong(request, "searchLogId");
			SearchLog searchLog = SearchLogLocalServiceUtil.fetchSearchLog(searchLogId);
			if (searchLog != null) {
				long userTargetClassId = ParamUtil.getLong(request, "userTargetClassId");
				long userTargetClassPK = ParamUtil.getLong(request, "userTargetClassPK");
				String userTargetTitle = ParamUtil.getString(request, "userTargetTitle");
				searchLog.setUserTargetClassId(userTargetClassId);
				searchLog.setUserTargetClassPK(userTargetClassPK);
				searchLog.setUserTargetTitle(userTargetTitle);
				SearchLogLocalServiceUtil.updateSearchLog(searchLog);
			}
			response.sendRedirect(detailURL);
		} else { // Sinon on continue sans rien faire
			chain.doFilter(request, response);
		}
	}
}
