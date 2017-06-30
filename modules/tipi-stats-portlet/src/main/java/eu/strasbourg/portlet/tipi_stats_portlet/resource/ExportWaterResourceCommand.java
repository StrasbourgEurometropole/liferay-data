package eu.strasbourg.portlet.tipi_stats_portlet.resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.tipi.model.TipiEntry;
import eu.strasbourg.service.tipi.service.TipiEntryLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.TIPI_STATS_PORTLET_WEB,
		"mvc.command.name=exportWater" }, service = MVCResourceCommand.class)
public class ExportWaterResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws PortletException {
		try {
			StringBundler csv = new StringBundler(); // StringBuilder du CSV

			// Le début est le même pour les deux modes
			csv.append("JOUR;TOT.;MONTANT TOT.;TOT. P;TOT. R");
			csv.append(CharPool.NEW_LINE);

			// On récupère les entries correspondant au mode sélectionné
			List<TipiEntry> allEntries = TipiEntryLocalServiceUtil
					.getTipiEntries(-1, -1);
			List<TipiEntry> entries = new ArrayList<TipiEntry>();
			for (TipiEntry entry : allEntries) {
				if (entry.getType().equals("EA")) {
					entries.add(entry);
				}
			}

			// Si on est en mode "EAU", les entries correspondent directement
			// aux lignes du fichier CSV
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			for (TipiEntry entry : entries) {
				int totalCount = entry.getPaidCount() + entry.getRefusedCount();
				csv.append(sf.format(entry.getDate()));
				csv.append(";" + totalCount);
				csv.append(";" + ((float) entry.getTotal()) / 100);
				csv.append(";" + entry.getPaidCount());
				csv.append(";" + entry.getRefusedCount());
				csv.append(CharPool.NEW_LINE);
			}
			String fileName = "export_tipi_eau.csv";
			byte[] bytes = csv.toString().replace('.', ',').getBytes();
			String contentType = ContentTypes.APPLICATION_TEXT;
			PortletResponseUtil.sendFile(resourceRequest, resourceResponse,
					fileName, bytes, contentType);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
