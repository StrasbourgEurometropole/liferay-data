package eu.strasbourg.service.office.exporter.impl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.office.exporter.api.InitiativesXlsxExporter;
import eu.strasbourg.service.project.model.Initiative;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.apache.commons.text.StringEscapeUtils.unescapeHtml4;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@Component(
        immediate = true,
        service = InitiativesXlsxExporter.class
)
public class InitiativesXlsxExporterImpl implements InitiativesXlsxExporter {

    private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());

    @Override
    public void exportInitiatives(OutputStream stream, List<Initiative> initiatives) {
        // Initialisation du document
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Creation du document
        XSSFSheet sheet = workbook.createSheet("Export initiatives");

        // Initialisation des colonnes
        Object[][] initiativeData = {{
                LanguageUtil.get(bundle, "initiative-title"),
                LanguageUtil.get(bundle, "initiative-description"),
                LanguageUtil.get(bundle, "initiative-create-date"),
                LanguageUtil.get(bundle, "initiative-author"),
                LanguageUtil.get(bundle, "initiative-email"),
                LanguageUtil.get(bundle, "initiative-modified-date"),
                LanguageUtil.get(bundle, "initiative-status"),
                LanguageUtil.get(bundle, "initiative-place-text-area"),
                LanguageUtil.get(bundle, "thematic"),
                LanguageUtil.get(bundle, "districts"),
                LanguageUtil.get(bundle, "project"),
                LanguageUtil.get(bundle, "user-liferay")
        }};

        // Parcours des budget et creation de la ligne a ajouter dans l'excel
        for (Initiative initiative : initiatives) {
            Object[] initiativeRow = {
                    getfield(unescapeHtml4(initiative.getTitle())),
                    getfield(unescapeHtml4(initiative.getDescription())),
                    getfield(initiative.getCreateDate()),
                    getfield(unescapeHtml4(initiative.getAuthorLabel())),
                    getfield(unescapeHtml4(initiative.getAuthorEmail())),
                    getfield(initiative.getModifiedDate()),
                    getfield(initiative.getStatusCategory() != null ? initiative.getStatusCategory().getTitle(Locale.FRANCE) : ""),
                    getfield(unescapeHtml4(initiative.getPlaceTextArea())),
                    getfield(initiative.getThematicsLabel(Locale.FRANCE)),
                    getfield(initiative.getDistrictLabel(Locale.FRANCE)),
                    getfield(initiative.getProjectName()),
                    getfield(initiative.getUserName())
            };

            initiativeData = ArrayUtil.append(initiativeData, initiativeRow);
        }

        // Parcours et ajout des donnees dans les cellules
        int rowIndex = 0;
        int columnIndex = 0;

        for (Object[] initiativeObject : initiativeData) {
            Row row = sheet.createRow(rowIndex);
            columnIndex = 0;
            for (Object field : initiativeObject) {
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