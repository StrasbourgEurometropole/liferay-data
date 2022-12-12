package eu.strasbourg.service.office.exporter.api;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.project.model.Petition;

import java.io.OutputStream;
import java.util.List;

@ProviderType
public interface PublikUsersXlsxExporter {

    void export(OutputStream stream);
}
