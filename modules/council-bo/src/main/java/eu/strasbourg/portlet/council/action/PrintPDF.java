package eu.strasbourg.portlet.council.action;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;

import javax.portlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PrintPDF {

	public static String domaine;
	public static PdfFont fontBold;

	public static void printPDF(ActionRequest req, ActionResponse res, String titreConseil, String titreDeliberation, List<Vote> votes)
			throws IOException, SystemException {

		// récupération du domaine
		domaine = "http://localhost:8080";

		// génération du pdf
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter pdfWriter = new PdfWriter(baos);
		PdfDocument pdf = new PdfDocument(pdfWriter);
		fontBold = PdfFontFactory.createRegisteredFont("Helvetica-Bold");
		// mise en page paysage
		pdf.setDefaultPageSize(PageSize.A4.rotate());
		try (Document document = new Document(pdf)) {
			document.setMargins(60f, 0f, 60f, 0f);
			document.setFont(fontBold).setFontSize(12f);

			// titre du PDF
			Table table = new Table(new float[1]).useAllAvailableWidth()
					.setHeight(500f)
					.setBackgroundColor(new DeviceRgb(238, 236, 225));
			Cell cell = new Cell().setKeepTogether(true)
					.add(new Paragraph(titreConseil.toUpperCase() + "\n" + titreDeliberation))
					.setPaddingLeft(15f).setPaddingRight(1.5f)
					.setTextAlignment(TextAlignment.CENTER)
					.setFontSize(13f).setBorder(Border.NO_BORDER);
			table.addCell(cell);
			document.add(table);

			// image d'entête
			ImageData image = ImageDataFactory.create(domaine + "/o/councilbo/images/logo_strasbourg_vert.jpg");
			Image img = new Image(image);
			float newWidth = 140;
			float newHeight = (img.getImageHeight() / img.getImageWidth()) * newWidth;
			img.scaleAbsolute(newWidth, newHeight)
			.setFixedPosition(0f,500f);
			document.add(img);

			table = new Table(new UnitValue[]{UnitValue.createPercentValue(10.5f), UnitValue.createPercentValue(89.5f)})
				.useAllAvailableWidth()
			.setFixedPosition(1,1f,60f,850f);

			// votes pour
			List<Vote> votesPour = votes.stream().filter(v -> v.getResult().equals("Pour")).collect(Collectors.toList());
			// colonne
			cell = new Cell().add(new Paragraph("Pour").setFontSize(15f))
					.add(new Paragraph(""+votesPour.size()).setFontSize(28f).setMarginTop(70f))
					.setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(new DeviceRgb(153, 204, 0)).setHeight(245f)
					.setBorder(new SolidBorder(ColorConstants.BLACK, 2f));
			table.addCell(cell);
			// liste élus
			String listePour = "";
			for (Vote vote : votesPour) {
				Official elu = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
				if(!listePour.isEmpty())
					listePour += ", ";
				listePour += elu.getLastname().toUpperCase() + "-" + elu.getFirstname();
			}
			cell = new Cell().setKeepTogether(true).setVerticalAlignment(VerticalAlignment.MIDDLE)
					.add(new Paragraph(listePour)
						.setFontColor(new DeviceRgb(85, 104, 59)));
			cell.setBorder(Border.NO_BORDER);
			table.addCell(cell);

			// votes contre
			List<Vote> votesContre = votes.stream().filter(v -> v.getResult().equals("Contre")).collect(Collectors.toList());
			// colonne
			cell = new Cell().add(new Paragraph("Contre").setFontSize(15f))
					.add(new Paragraph(""+votesContre.size()).setFontSize(28f))
					.setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(new DeviceRgb(255, 0, 0)).setHeight(70f)
					.setBorder(new SolidBorder(ColorConstants.BLACK, 2f));
			table.addCell(cell);
			// liste élus
			String listeContre = "";
			for (Vote vote : votesContre) {
				Official elu = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
				if(!listeContre.isEmpty())
					listeContre += ", ";
				listeContre += elu.getLastname().toUpperCase() + "-" + elu.getFirstname();
			}
			cell = new Cell().setKeepTogether(true).setVerticalAlignment(VerticalAlignment.BOTTOM)
					.add(new Paragraph(listeContre)
							.setFontColor(new DeviceRgb(255, 0, 0)));
			cell.setBorder(Border.NO_BORDER);
			table.addCell(cell);

			// abstention
			List<Vote> abstentions = votes.stream().filter(v -> v.getResult().equals("Abstention")).collect(Collectors.toList());
			// colonne
			cell = new Cell().add(new Paragraph("Abstention").setFontSize(15f))
					.add(new Paragraph(""+abstentions.size()).setFontSize(28f))
					.setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(new DeviceRgb(85, 142, 213)).setHeight(70f)
					.setBorder(new SolidBorder(ColorConstants.BLACK, 2f));
			table.addCell(cell);
			// liste élus
			String listeAbstention = "";
			for (Vote vote : abstentions) {
				Official elu = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
				if(!listeAbstention.isEmpty())
					listeAbstention += ", ";
				listeAbstention += elu.getLastname().toUpperCase() + "-" + elu.getFirstname();
			}
			cell = new Cell().setKeepTogether(true).setVerticalAlignment(VerticalAlignment.BOTTOM)
					.add(new Paragraph(listeAbstention)
							.setFontColor(new DeviceRgb(99, 77, 124)));
			cell.setBorder(Border.NO_BORDER);
			table.addCell(cell);
			document.add(table);

			if (pdf != null)
				pdf.close();

			if (pdfWriter != null)
				pdfWriter.close();

			// ouverture du PDF dans le navigateur
			HttpServletResponse response = PortalUtil.getHttpServletResponse(res);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + titreDeliberation + ".pdf");

			response.setContentLength(baos.size());
			OutputStream os = response.getOutputStream();
			if (os != null) {
				baos.writeTo(os);
				os.flush();
				os.close();
			}

			File deliberationpdf = new File("./" + titreDeliberation.substring(0,9) + ".pdf");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(deliberationpdf);
				fos.write(baos.toByteArray());
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
