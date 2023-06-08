package eu.strasbourg.service.office.exporter.api;

import java.io.OutputStream;
import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.project.model.Initiative;

@ProviderType
public interface InitiativesXlsxExporter {
	
	void exportInitiatives(OutputStream stream, List<Initiative> initiatives);

}
