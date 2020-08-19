package eu.strasbourg.service.office.exporter.api;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.ejob.model.Offer;

import java.util.List;

@ProviderType
public interface OffersCsvExporter {

	public void exportOffers();

	public void exportOffers(List<Offer> offers);
	
}
