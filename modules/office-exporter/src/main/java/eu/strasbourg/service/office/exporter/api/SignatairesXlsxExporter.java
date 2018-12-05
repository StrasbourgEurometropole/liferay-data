package eu.strasbourg.service.office.exporter.api;

import java.io.OutputStream;
import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.project.model.Signataire;

@ProviderType
public interface SignatairesXlsxExporter {
	
	public void exportSignataires(OutputStream stream, long petitionId);
	
	public void exportSignataires(OutputStream stream, String petitionTitle, List<Signataire> signataires);
	
}
