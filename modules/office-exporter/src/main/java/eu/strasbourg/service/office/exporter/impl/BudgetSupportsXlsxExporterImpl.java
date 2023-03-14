package eu.strasbourg.service.office.exporter.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.office.exporter.api.BudgetSupportsXlsxExporter;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {},
	service = BudgetSupportsXlsxExporter.class
)
public class BudgetSupportsXlsxExporterImpl implements BudgetSupportsXlsxExporter {
	
	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
			this.getClass().getClassLoader());

	@Override
	public void exportBudgetSupports(OutputStream stream, long budgetParticipatifId) {
		// Recherche du budget en question
		BudgetParticipatif budgetParticipatif = _budgetParticipatifLocalService.fetchBudgetParticipatif(budgetParticipatifId);
		
		// Recuperation des participations de l'evenement
		List <BudgetSupport> budgetSupports = budgetParticipatif.getSupports();
		
		this.exportBudgetSupports(stream, budgetParticipatif.getTitle(), budgetSupports);
	}

	@Override
	public void exportBudgetSupports(OutputStream stream, String budgetParticipatifTitle, List<BudgetSupport> budgetSupports) {
		
		// Initialisation du document
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Creation du document
		XSSFSheet sheet = workbook.createSheet("Soutiens " + budgetParticipatifTitle);
		// Initialisation des colonnes
		Object[][] supportData = { {
			LanguageUtil.get(bundle, "lastname"),
			LanguageUtil.get(bundle, "firstname"),
			LanguageUtil.get(bundle, "birthday"),
			LanguageUtil.get(bundle, "address"),
			LanguageUtil.get(bundle, "postal-code"),
			LanguageUtil.get(bundle, "city"),
			LanguageUtil.get(bundle, "email"),
			LanguageUtil.get(bundle, "phone"),
			LanguageUtil.get(bundle, "mobile"),
			LanguageUtil.get(bundle, "support-date")
		} };
		
		// Parcours des participation et creation de la ligne a ajouter dans l'excel
		for (BudgetSupport budgetSupport : budgetSupports) {
			
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
			
			Object[] budgetSupportRow = { 
				budgetSupport.getCitoyenLastName(),
				budgetSupport.getCitoyenFirstname(),
				dateFormat.format(budgetSupport.getCitoyenBirthday()),
				budgetSupport.getCitoyenAddress(),
				budgetSupport.getCitoyenPostalCode(),
				budgetSupport.getCitoyenCity(),
				budgetSupport.getCitoyenMail(),
				budgetSupport.getCitoyenPhone(),
				budgetSupport.getCitoyenMobilePhone(),
				dateFormat.format(budgetSupport.getCreateDate())
			};
			
			supportData = ArrayUtil.append(supportData, budgetSupportRow);
			
		}
		
		// Parcours et ajout des donnees dans les cellules
		int rowIndex = 0;
		int columnIndex = 0;
		
		for (Object[] budgetSupportObject : supportData) {
			Row row = sheet.createRow(rowIndex);
			columnIndex = 0;
			for (Object field : budgetSupportObject) {
				Cell cell = row.createCell(columnIndex);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
				columnIndex++;
			}
			rowIndex++;
		}
		
		try {
			workbook.write(stream);
			workbook.close();
			stream.flush();
		} catch (IOException e) {
			_log.error(e.getMessage(), e);
		}

	}
	
	@Reference(unbind = "-")
	protected void setBudgetParticipatifLocalService(BudgetParticipatifLocalService budgetParticipatifLocalService) {
		this._budgetParticipatifLocalService = budgetParticipatifLocalService;
	}
	
	private BudgetParticipatifLocalService _budgetParticipatifLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
