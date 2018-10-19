package eu.strasbourg.service.office.exporter.impl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.office.exporter.api.BudgetsParticipatifsXlsxExporter;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
        Object[][] budgetParticipatifData = {{
                LanguageUtil.get(bundle, "budget-part-title"),
                LanguageUtil.get(bundle, "budget-part-description"),
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
                LanguageUtil.get(bundle, "budget-part-consultation-places-text"),
                LanguageUtil.get(bundle, "budget-part-consultation-places-body"),
                LanguageUtil.get(bundle, "budget-part-is-crush"),
                LanguageUtil.get(bundle, "budget-part-crush-comment"),
                LanguageUtil.get(bundle, "budget-part-categories-thematic"),
                LanguageUtil.get(bundle, "budget-part-categories-quartiers"),
                //LanguageUtil.get(bundle, "budget-part-categories-projet"),
                LanguageUtil.get(bundle, "budget-part-create-date"),
                LanguageUtil.get(bundle, "budget-part-user-name"),
                LanguageUtil.get(bundle, "budget-part-modified-date")
        }};

        // Parcours des budget et creation de la ligne a ajouter dans l'excel
        for (BudgetParticipatif budgetParticipatif : budgetsParticipatifs) {

            DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");

            Object[] budgetParticipatifRow = {
                    unescapeHtml4(budgetParticipatif.getTitle()),
                    unescapeHtml4(budgetParticipatif.getDescription()),
                    budgetParticipatif.getBudget(),
                    unescapeHtml4(budgetParticipatif.getMotif()),
                    unescapeHtml4(budgetParticipatif.getCitoyenLastname()),
                    unescapeHtml4(budgetParticipatif.getCitoyenFirstname()),
                    unescapeHtml4(budgetParticipatif.getCitoyenAdresse()),
                    Math.toIntExact(budgetParticipatif.getCitoyenPostalCode()),
                    budgetParticipatif.getCitoyenCity(),
                    budgetParticipatif.getCitoyenPhone(),
                    budgetParticipatif.getCitoyenMobile(),
                    budgetParticipatif.getCitoyenEmail(),
                    unescapeHtml4(budgetParticipatif.getPlaceTextArea()),
                    budgetParticipatif.getConsultationPlacesText(),
                    budgetParticipatif.getConsultationPlacesBody(),
                    budgetParticipatif.getIsCrush(),
                    budgetParticipatif.getCrushComment(),
                    budgetParticipatif.getThematicTitle(Locale.FRANCE),
                    budgetParticipatif.getDistrictLabel(Locale.FRANCE),
                    //budgetParticipatif.getProjectTitle(Locale.FRANCE),
                    dateFormat.format(budgetParticipatif.getCreateDate()),
                    budgetParticipatif.getUserName(),
                    dateFormat.format(budgetParticipatif.getModifiedDate())
            };

            budgetParticipatifData = ArrayUtil.append(budgetParticipatifData, budgetParticipatifRow);

        }

        // Parcours et ajout des donnees dans les cellules
        int rowIndex = 0;
        int columnIndex = 0;

        for (Object[] budgetParticipatifObject : budgetParticipatifData) {
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
            e.printStackTrace();
        }

    }

}
