package eu.strasbourg.service.office.exporter.api;

import aQute.bnd.annotation.ProviderType;

import java.io.OutputStream;

@ProviderType
public interface OffersXlsxExporter {
	
	public void exportPublishedOffers(OutputStream stream);
	
}
