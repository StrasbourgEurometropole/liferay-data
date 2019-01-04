package eu.strasbourg.service.office.exporter.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalService;
import eu.strasbourg.service.office.exporter.api.CommentsXlsxExporter;

import static org.apache.commons.text.StringEscapeUtils.unescapeHtml4;
@Component(
        immediate = true,
        property = {},
        service = CommentsXlsxExporter.class)
public class CommentsXlsxExporterImpl implements CommentsXlsxExporter {

    private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());

    private CommentLocalService commentLocalService;

    @Reference(unbind = "-")
    protected void setCommentLocalService(CommentLocalService commentLocalService) {
        this.commentLocalService = commentLocalService;
    }

    public void exportComments(OutputStream stream, String commentIdsStr) {
        List<Comment> comments = new ArrayList<Comment>();
        for (String commentIdStr : commentIdsStr.split(",")) {
            if (Validator.isNotNull(commentIdStr)) {
                Comment comment = commentLocalService.fetchComment(Long.valueOf(commentIdStr));
                if (comment != null) {
                    comments.add(comment);
                }
            }
        }
        exportComments(stream, comments);
    }

    public void exportComments(OutputStream stream, List<Comment> comments) {
    	// Initialisation du document
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Creation du document
        XSSFSheet sheet = workbook.createSheet("Commentaires");
        DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
        
        // Initialisation des colonnes
        Object[][] commentData = {{
        	LanguageUtil.get(bundle, "commentType"),
        	LanguageUtil.get(bundle, "commentName"),
            LanguageUtil.get(bundle, "modification-date"),
            LanguageUtil.get(bundle, "comment-level"),
            LanguageUtil.get(bundle, "comment")}};
        
        // Parcours des commentaires et creation de la ligne a ajouter dans l'excel
        for (Comment comment : comments) {
            String dateCreate = dateFormat.format(comment.getModifiedByUserDate() == null ? comment.getCreateDate() : comment.getModifiedByUserDate());
            String languageId = LocaleUtil.toLanguageId(Locale.FRANCE);
            String title = LocalizationUtil.getLocalization(comment.getAssetEntryTitle(), languageId);

            Object[] commentRow = {
            		unescapeHtml4(comment.getTypeAssetEntry()),
            		title,
                    dateCreate,
                    comment.getLevel(),
                    unescapeHtml4(comment.getComment())
                    };
            
            commentData = ArrayUtil.append(commentData, commentRow);
        }

        int rowIndex = 0;
        int columnIndex = 0;
        for (Object[] commentObject : commentData) {
            Row row = sheet.createRow(rowIndex);
            columnIndex = 0;
            for (Object field : commentObject) {
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
