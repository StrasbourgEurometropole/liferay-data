package eu.strasbourg.service.office.exporter.impl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.office.exporter.api.CouncilOfficialsXlsxExporter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Component(
        immediate = true,
        service = CouncilOfficialsXlsxExporter.class
)
public class CouncilOfficialsXlsxExporterImpl implements CouncilOfficialsXlsxExporter {

    private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());

    @Override
    public void exportOfficials(OutputStream stream, List<Official> officials) throws IOException {
        // Initialisation du document
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Creation du document
        XSSFSheet sheet = workbook.createSheet("Export budget participatif");

        // Initialisation des colonnes
        List<List<Object>> officialsData = new ArrayList<>();
        officialsData.add(
                Arrays.asList(
                        LanguageUtil.get(bundle, "eu.office.exporter.lastname"),
                        LanguageUtil.get(bundle, "eu.office.exporter.firstname"),
                        LanguageUtil.get(bundle, "eu.office.exporter.email"),
                        LanguageUtil.get(bundle, "eu.office.exporter.is.municipal"),
                        LanguageUtil.get(bundle, "eu.office.exporter.is.eurometropolitan"),
                        LanguageUtil.get(bundle, "eu.office.exporter.is.active")
                )
        );

        // Parcours des entités et creation des lignes de données a ajouter dans l'excel
        for (Official official : officials) {
            officialsData.add(
                Arrays.asList(
                        this.getfield(official.getLastname()),
                        this.getfield(official.getFirstname()),
                        this.getfield(official.getEmail()),
                        this.getfield(official.getIsMunicipal()),
                        this.getfield(official.getIsEurometropolitan()),
                        this.getfield(official.getIsActive())
                )
            );
        }

        // Parcours et ajout des donnees dans les cellules
        int rowIndex = 0;
        int columnIndex = 0;

        for (List<Object> officialObject : officialsData) {
            Row row = sheet.createRow(rowIndex);
            columnIndex = 0;
            for (Object field : officialObject) {
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

        // Ecriture et envoie du document dans le flux donné
        workbook.write(stream);
        workbook.close();
        stream.flush();

    }

    /**
     * Récupération d'un champ String
     */
    private String getfield(String param) {
        String result = "";
        if (param != null && !param.isEmpty())
            result = param;
        return result;
    }

    /**
     * Récupération d'un champ boolean
     */
    private String getfield(boolean param) {
        return param ? "oui" : "non";
    }

}
