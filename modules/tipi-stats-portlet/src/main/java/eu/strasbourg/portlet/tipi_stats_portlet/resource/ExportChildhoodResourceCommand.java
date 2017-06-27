package eu.strasbourg.portlet.tipi_stats_portlet.resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.tipi.model.TipiEntry;
import eu.strasbourg.service.tipi.service.TipiEntryLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.TIPI_STATS_PORTLET_WEB,
		"mvc.command.name=exportChildhood" }, service = MVCResourceCommand.class)
public class ExportChildhoodResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws PortletException {
		try {

			StringBundler csv = new StringBundler(); // StringBuilder du CSV

			// Le début est le même pour les deux modes
			csv.append("JOUR;TOT.;MONTANT TOT.;TOT. P;TOT. R");
			csv.append(
					";PETITE ENFANCE P;PETITE ENFANCE R;MONTANT PETITE ENFANCE;");
			csv.append("RESTO P;RESTO R;MONTANT RESTO;");
			csv.append("PERI P;PERI  R;MONTANT PERI");
			csv.append(CharPool.NEW_LINE);

			// On récupère les entries correspondant au mode sélectionné
			List<TipiEntry> allEntries = TipiEntryLocalServiceUtil
					.getTipiEntries(-1, -1);
			List<TipiEntry> entries = new ArrayList<TipiEntry>();
			for (TipiEntry entry : allEntries) {
				if (!entry.getType().equals("EA")) {
					entries.add(entry);
				}
			}

			// Pour chaque date on a une liste de TipiEntry, on crée une map
			// pour faire ce regroupement
			Map<Date, List<TipiEntry>> dateEntryListMap = new HashMap<Date, List<TipiEntry>>();
			// On parcourt les entries, afin de les classer dans la map
			for (TipiEntry entry : entries) {
				// On récupère la liste d'entries correspondant à la date de
				// l'entry courante
				List<TipiEntry> dateEntryList = dateEntryListMap
						.get(entry.getDate());
				// Si il n'y en a pas on en crée une
				if (dateEntryList == null) {
					dateEntryList = new ArrayList<TipiEntry>();
					dateEntryList.add(entry);
					dateEntryListMap.put(entry.getDate(), dateEntryList);
				} else {
					// Si elle existe, on ne fait qu'ajouter la liste à la
					// map
					dateEntryList.add(entry);
				}
			}
			// On construit notre CSV à partir de la map
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			for (List<TipiEntry> dateEntries : dateEntryListMap.values()) {
				// La date
				String dateString = sf.format(dateEntries.get(0).getDate());

				// Total
				int totalCount = 0;
				float totalPrice = 0;
				int totalPaid = 0;
				int totalRefused = 0;
				// Petite enfance
				int pfPaidCount = 0;
				int pfRefusedCount = 0;
				float pfPrice = 0;
				// Resto scolaire
				int rsPaidCount = 0;
				int rsRefusedCount = 0;
				float rsPrice = 0;
				// Periscolaire
				int gaPaidCount = 0;
				int gaRefusedCount = 0;
				float gaPrice = 0;
				for (TipiEntry entry : dateEntries) {
					totalCount += entry.getPaidCount()
							+ entry.getRefusedCount();
					totalPrice += ((float) entry.getTotal()) / 100;
					totalPaid += entry.getPaidCount();
					totalRefused += entry.getRefusedCount();
					if (entry.getType().equals("PF")) {
						pfPaidCount = entry.getPaidCount();
						pfRefusedCount = entry.getRefusedCount();
						pfPrice = ((float) entry.getTotal()) / 100;
					} else if (entry.getType().equals("RS")) {
						rsPaidCount = entry.getPaidCount();
						rsRefusedCount = entry.getRefusedCount();
						rsPrice = ((float) entry.getTotal()) / 100;
					} else if (entry.getType().equals("GA")) {
						gaPaidCount = entry.getPaidCount();
						gaRefusedCount = entry.getRefusedCount();
						gaPrice = ((float) entry.getTotal()) / 100;
					}
				}

				csv.append(dateString + ";" + totalCount + ";" + totalPrice
						+ ";" + totalPaid + ";" + totalRefused + ";");
				csv.append(pfPaidCount + ";" + pfRefusedCount + ";" + pfPrice
						+ ";");
				csv.append(rsPaidCount + ";" + rsRefusedCount + ";" + rsPrice
						+ ";");
				csv.append(gaPaidCount + ";" + gaRefusedCount + ";" + gaPrice);
				csv.append(CharPool.NEW_LINE);
			}
			String fileName = "export_tipi_enfance.csv";
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
