package eu.strasbourg.service.office.exporter.api;

import java.io.OutputStream;
import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.project.model.BudgetSupport;

@ProviderType
public interface BudgetSupportsXlsxExporter {
	
	public void exportBudgetSupports(OutputStream stream, long budgetParticipatifId);
	
	public void exportBudgetSupports(OutputStream stream, String budgetParticipatifTitle, List<BudgetSupport> budgetSupports);
	
}
