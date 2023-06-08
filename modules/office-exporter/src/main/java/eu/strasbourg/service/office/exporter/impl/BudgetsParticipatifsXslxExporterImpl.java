package eu.strasbourg.service.office.exporter.impl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.office.exporter.api.BudgetsParticipatifsXlsxExporter;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static org.apache.commons.text.StringEscapeUtils.unescapeHtml4;

@Component(
        immediate = true,
        service = BudgetsParticipatifsXlsxExporter.class
)
public class BudgetsParticipatifsXslxExporterImpl implements BudgetsParticipatifsXlsxExporter {

    private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());

    @Override
    public void exportBudgetsParticipatifs(OutputStream stream, List<BudgetParticipatif> budgetsParticipatifs) {
        // Initialisation du document
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Creation du document
        XSSFSheet sheet = workbook.createSheet("Export budget participatif");

        
        // Initialisation des colonnes
        List<List<Object>> budgetParticipatifData = new ArrayList<List<Object>>();
        
        budgetParticipatifData.add(
        		Arrays.asList(
                LanguageUtil.get(bundle, "budget-part-phase"),
                LanguageUtil.get(bundle, "budget-part-statut"),
                LanguageUtil.get(bundle, "budget-part-title"),
                LanguageUtil.get(bundle, "budget-part-description"),
                LanguageUtil.get(bundle, "budget-part-summary"),
                LanguageUtil.get(bundle, "budget-part-nb-votes"),
                LanguageUtil.get(bundle, "budget-part-create-date"),
                LanguageUtil.get(bundle, "budget-part-modified-date"),
                LanguageUtil.get(bundle, "budget-part-budget"),
                LanguageUtil.get(bundle, "budget-part-motif"),
                LanguageUtil.get(bundle, "budget-part-lastname"),
                LanguageUtil.get(bundle, "budget-part-firstname"),
                LanguageUtil.get(bundle, "budget-part-address"),
                LanguageUtil.get(bundle, "budget-part-postalcode"),
                LanguageUtil.get(bundle, "budget-part-city"),
                LanguageUtil.get(bundle, "budget-part-phone"),
                LanguageUtil.get(bundle, "budget-part-mobile"),
                LanguageUtil.get(bundle, "budget-part-email"),
                LanguageUtil.get(bundle, "budget-part-place-text-area"),
                LanguageUtil.get(bundle, "budget-part-is-crush"),
                LanguageUtil.get(bundle, "budget-part-crush-comment"),
                LanguageUtil.get(bundle, "thematic"),
                LanguageUtil.get(bundle, "districts"),
                LanguageUtil.get(bundle, "project"),
                LanguageUtil.get(bundle, "user-liferay"),
                LanguageUtil.get(bundle, "budget-part-place-name"),
                LanguageUtil.get(bundle, "budget-part-place-mercator-x"),
                LanguageUtil.get(bundle, "budget-part-place-mercator-y")
		));
        	
        // Parcours des budget et creation de la ligne a ajouter dans l'excel
        for (BudgetParticipatif budgetParticipatif : budgetsParticipatifs) {
        	
        	
        	String placesNames = String.join("\n", budgetParticipatif.getPlacitPlaces()
        			.stream()
        			.map(pl->pl.getPlaceAlias(Locale.FRANCE))
        			.collect(Collectors.toList()));
        	
        	String placesX = String.join("\n", budgetParticipatif.getPlacitPlaces()
        			.stream()
        			.map(pl->pl.getMercatorX())
        			.collect(Collectors.toList()));
        	
        	String placesY = String.join("\n", budgetParticipatif.getPlacitPlaces()
        			.stream()
        			.map(pl->pl.getMercatorY())
        			.collect(Collectors.toList()));
        			
        			
        	budgetParticipatifData.add(
        	Arrays.asList(
                    getfield(unescapeHtml4(budgetParticipatif.getPhaseTitleLabel())),
                    getfield(unescapeHtml4(budgetParticipatif.getBudgetParticipatifStatusTitle(Locale.FRANCE))),
                    getfield(unescapeHtml4(budgetParticipatif.getTitle())),
                    getfield(unescapeHtml4(budgetParticipatif.getDescription())),
                    getfield(unescapeHtml4(budgetParticipatif.getSummary())),
                    getfield(Long.toString(budgetParticipatif.getNbSupports())),
                    getfield(budgetParticipatif.getCreateDate()),
                    getfield(budgetParticipatif.getModifiedDate()),
                    getfield(budgetParticipatif.getBudget()),
                    getfield(unescapeHtml4(budgetParticipatif.getMotif())),
                    getfield(unescapeHtml4(budgetParticipatif.getCitoyenLastname())),
                    getfield(unescapeHtml4(budgetParticipatif.getCitoyenFirstname())),
                    getfield(unescapeHtml4(budgetParticipatif.getCitoyenAdresse())),
                    getfield(budgetParticipatif.getCitoyenPostalCode()),
                    getfield(budgetParticipatif.getCitoyenCity()),
                    getfield(budgetParticipatif.getCitoyenPhone()),
                    getfield(budgetParticipatif.getCitoyenMobile()),
                    getfield(budgetParticipatif.getCitoyenEmail()),
                    getfield(unescapeHtml4(budgetParticipatif.getPlaceTextArea())),
                    getfield(budgetParticipatif.getIsCrush()),
                    getfield(budgetParticipatif.getCrushComment()),
                    getfield(budgetParticipatif.getThematicsLabel(Locale.FRANCE)),
                    getfield(budgetParticipatif.getDistrictLabel(Locale.FRANCE)),
                    getfield(budgetParticipatif.getProjectName()),
                    getfield(budgetParticipatif.getUserName()),
                    getfield(placesNames),
                    getfield(placesX),
                    getfield(placesY)
        			));
        }

        // Parcours et ajout des donnees dans les cellules
        int rowIndex = 0;
        int columnIndex = 0;

        for (List<Object> budgetParticipatifObject : budgetParticipatifData) {
            Row row = sheet.createRow(rowIndex);
            columnIndex = 0;
            for (Object field : budgetParticipatifObject) {
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

    private String getfield(Date param) {
        DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
        String result = "";
        if (param != null)
            result = dateFormat.format(param);
        return result;
    }

    private String getfield(String param) {
        String result = "";
        if (param != null && !param.isEmpty())
            result = param;
        return result;
    }

    private String getfield(boolean param) {
        return param ? "oui" : "non";
    }

    private String getfield(long param) {
        String result = "";
        if (param != 0L)
            result = String.valueOf(param);
        return result;
    }

    private String getfield(int param) {
        String result = "";
        if (param != 0)
            result = String.valueOf(Math.toIntExact(param));
        return result;
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
