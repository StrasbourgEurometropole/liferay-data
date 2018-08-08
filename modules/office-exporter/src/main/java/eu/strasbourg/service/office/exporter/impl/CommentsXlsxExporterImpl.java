package eu.strasbourg.service.office.exporter.impl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalService;
import eu.strasbourg.service.office.exporter.api.CommentsXlsxExporter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


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
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("comments");

        Object[][] commentData = {{LanguageUtil.get(bundle, "type"), LanguageUtil.get(bundle, "title"),
                LanguageUtil.get(bundle, "modification-date"), LanguageUtil.get(bundle, "comment-level"),
                LanguageUtil.get(bundle, "comment")}};

        for (Comment comment : comments) {
            DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
            String dateCreate = dateFormat.format(comment.getCreateDate());

            String languageId = LocaleUtil.toLanguageId(Locale.FRANCE);
            String title = LocalizationUtil.getLocalization(comment.getAssetEntryTitle(), languageId);

            Object[] commentRow = {comment.getTypeAssetEntry(), title,
                    dateCreate, comment.getLevel(), comment.getComment()};
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
