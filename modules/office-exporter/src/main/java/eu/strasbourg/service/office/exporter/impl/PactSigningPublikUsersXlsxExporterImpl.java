package eu.strasbourg.service.office.exporter.impl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.office.exporter.api.PactSigningPublikUsersXlsxExporter;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author angelique zunino champougny
 */
@Component(
        immediate = true,
        service = PactSigningPublikUsersXlsxExporter.class
)
public class PactSigningPublikUsersXlsxExporterImpl implements PactSigningPublikUsersXlsxExporter {

    private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());

    private PublikUserLocalService publikUserLocalService;

    @Reference(unbind = "-")
    public void setPublikUserLocalService(PublikUserLocalService publikUserLocalService) {
        this.publikUserLocalService = publikUserLocalService;
    }

    @Override
    public void export(OutputStream stream) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Signataires");
        Object[][] exportData = {{
                LanguageUtil.get(bundle, "last-name"),
                LanguageUtil.get(bundle, "first-name"),
                LanguageUtil.get(bundle, "email")}};

        List<PublikUser> users = publikUserLocalService.getAllPublikUsers().stream()
                .filter(u -> Validator.isNotNull(u.getPactSignature())).collect(Collectors.toList());

        for (PublikUser user : users) {
            if(Validator.isNotNull(user.getPactSignature())) {
                Object[] row = {
                        getfield(user.getLastName()),
                        getfield(user.getFirstName()),
                        getfield(user.getEmail())};
                exportData = ArrayUtil.append(exportData, row);
            }
        }
        int rowIndex = 0;
        int columnIndex;
        for (Object[] objects : exportData) {
            Row row = sheet.createRow(rowIndex);
            columnIndex = 0;
            for (Object field : objects) {
                Cell cell = row.createCell(columnIndex);
                cell.setCellValue((String) field);
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

    private String getfield(String param) {
        String result = "";
        if (param != null && !param.isEmpty())
            result = param;
        return result;
    }

}
