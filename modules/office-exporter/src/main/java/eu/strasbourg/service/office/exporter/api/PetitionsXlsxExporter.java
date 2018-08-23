package eu.strasbourg.service.office.exporter.api;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.project.model.Petition;

import java.io.OutputStream;
import java.util.List;

/**
 * @author alexandre.quere
 */
@ProviderType
public interface PetitionsXlsxExporter {

    void exportPetitions(OutputStream stream, String petitionIdsStr);

    void exportPetitions(OutputStream stream, List<Petition> petitions);
}
