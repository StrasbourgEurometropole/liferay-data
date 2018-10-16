package eu.strasbourg.service.office.exporter.api;

import java.io.OutputStream;
import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.project.model.BudgetParticipatif;

@ProviderType
public interface BudgetsParticipatifsXlsxExporter {
	
	public void exportBudgetsParticipatifs(OutputStream stream, List<BudgetParticipatif> budgetsParticipatifs);
	
}
