package eu.strasbourg.portlet.comment.resource;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.office.exporter.api.CommentsXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

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
		String commentIds = ParamUtil.getString(resourceRequest, "commentIds");

		try {
			commentsXlsExporter.exportComments(resourceResponse.getPortletOutputStream(), commentIds);
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
}
