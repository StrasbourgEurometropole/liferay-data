package eu.strasbourg.portlet.comment.resource;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.office.exporter.api.CommentsXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.COMMENT_BO,
		"mvc.command.name=exportCommentsXlsx" }, service = MVCResourceCommand.class)
public class ExportCommentsToXlsxResourceCommand implements MVCResourceCommand {
	
	private CommentsXlsxExporter commentsXlsExporter;
	
	@Reference(unbind = "-")
	public void setCommentsXlsExporter(CommentsXlsxExporter commentsXlsExporter) {
		this.commentsXlsExporter = commentsXlsExporter;
	}

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition", "attachment; filename=Commentaires.xlsx");
		//String commentIds = ParamUtil.getString(resourceRequest, "commentIds");
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();

		try {
			commentsXlsExporter.exportComments(resourceResponse.getPortletOutputStream(), groupId);
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			_log.error(e.getMessage(), e);
		}

		return true;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
