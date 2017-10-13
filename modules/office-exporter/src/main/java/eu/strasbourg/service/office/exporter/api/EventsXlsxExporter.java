package eu.strasbourg.service.office.exporter.api;

import java.io.OutputStream;
import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Event;

@ProviderType
public interface EventsXlsxExporter {
	
	public void exportEvents(OutputStream stream, String eventIdStr);
	
	public void exportEvents(OutputStream stream, List<Event> events);
}
