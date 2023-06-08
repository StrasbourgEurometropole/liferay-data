package eu.strasbourg.service.office.exporter.api;

import java.io.OutputStream;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Campaign;

@ProviderType
public interface CampaignDocxExporter {
	public void exportCampaign(OutputStream stream, Campaign campaign);
}
