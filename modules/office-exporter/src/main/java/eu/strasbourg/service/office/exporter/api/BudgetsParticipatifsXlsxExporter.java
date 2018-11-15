package eu.strasbourg.service.office.exporter.api;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.project.model.BudgetParticipatif;

import java.io.OutputStream;
import java.util.List;

@ProviderType
public interface BudgetsParticipatifsXlsxExporter {
	
	void exportBudgetsParticipatifs(OutputStream stream, List<BudgetParticipatif> budgetsParticipatifs);
	
}
