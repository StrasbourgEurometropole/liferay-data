package eu.strasbourg.service.office.exporter.api;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.comment.model.Comment;

import java.io.OutputStream;
import java.util.List;

@ProviderType
public interface HistoricPublikUserTextExporter {
	
	public void exportHistoric(OutputStream fichier, long publikUserIdStr);
}
