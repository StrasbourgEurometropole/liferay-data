package eu.strasbourg.service.office.exporter.api;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.council.model.Official;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@ProviderType
public interface CouncilOfficialsXlsxExporter {

    void exportOfficials(OutputStream stream, List<Official> officials) throws IOException;

}
