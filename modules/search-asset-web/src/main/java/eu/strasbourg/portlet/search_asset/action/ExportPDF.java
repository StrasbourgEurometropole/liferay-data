package eu.strasbourg.portlet.search_asset.action;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.portlet.search_asset.constants.OfficialsConstants;
import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExportPDF {

	public static String domaine;
	public static PdfFont font;
	public static PdfFont fontBold;

	public static void printPDF(ResourceRequest req, ResourceResponse res, String exportType)
			throws PortletException, IOException, SystemException, PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay) req
				.getAttribute(WebKeys.THEME_DISPLAY);

		// récupération du domaine
		domaine = "http://localhost:8080";

		// génération du pdf
		font = PdfFontFactory.createRegisteredFont("Helvetica");
		fontBold = PdfFontFactory.createRegisteredFont("Helvetica-Bold");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter pdfWriter = new PdfWriter(baos);
		PdfDocument pdf = new PdfDocument(pdfWriter);
		try (Document document = new Document(pdf)) {
			document.setMargins(35f, 35f, 35f, 35f);
			document.setFont(font).setFontSize(12f);

			// image d'entête
			ImageData image = ImageDataFactory.create(domaine + "/o/searchassetweb/images/bandeau.jpg");
			Image img = new Image(image);
			float newWidth = 520;
			float newHeight = (img.getImageHeight() / img.getImageWidth()) * newWidth;
			img.scaleAbsolute(newWidth, newHeight).setMarginBottom(5f);
			document.add(img);

			// titre du PDF
			String titrePortlet = null;
			switch (exportType) {
				case OfficialsConstants.MUNICIPAL:
					titrePortlet = LanguageUtil.get(themeDisplay.getLocale(),
							"entete-annuaire-elus-communautaires-print");
					break;
				case OfficialsConstants.EUROMETROPOLE:
					titrePortlet = LanguageUtil.get(themeDisplay.getLocale(),
							"entete-annuaire-elus-municipaux-print");
					break;
				default:
					titrePortlet = LanguageUtil.get(themeDisplay.getLocale(),
							"entete-annuaire-elus-communautaires-print");
					break;
			}
			document.add(new Paragraph(titrePortlet.toUpperCase())
					.setFont(fontBold).setFontSize(20f)
					.setMarginBottom(5f));

			// élus
			printPDFPeople(document, req, themeDisplay, exportType);

			// bas de page
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Table table = new Table(new UnitValue[]{UnitValue.createPercentValue(22f), UnitValue.createPercentValue(78f)})
					.setWidth(UnitValue.createPercentValue(100f))
					.setBorder(Border.NO_BORDER).setBorderTop(new SolidBorder(new DeviceRgb(151, 191, 12), 1f));
			Cell cell = new Cell()
					.add(new Paragraph(sdf.format(new Date()))).setBorder(Border.NO_BORDER)
					.setPadding(0f).setMargin(0f);
			table.addCell(cell);

			cell = new Cell()
					.add(new Paragraph(LanguageUtil.get(themeDisplay.getLocale(), "footer-title")).setFont(fontBold))
					.add(new Paragraph(HtmlUtil.render(LanguageUtil.get(themeDisplay.getLocale(), "footer-content"))))
					.setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)
					.setPadding(0f).setMargin(0f);
			table.addCell(cell).setPadding(0f).setPaddingTop(1f).setMargin(0f);
			document.add(table);

			if (pdf != null)
				pdf.close();

			if (pdfWriter != null)
				pdfWriter.close();

			// ouverture du PDF dans le navigateur
			HttpServletResponse response = PortalUtil.getHttpServletResponse(res);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + titrePortlet + ".pdf");

			response.setContentLength(baos.size());
			OutputStream os = response.getOutputStream();
			if (os != null) {
				baos.writeTo(os);
				os.flush();
				os.close();
			}
		}
	}

	public static void printPDFPeople(Document document, ResourceRequest req, ThemeDisplay themeDisplay, String exportType)
			throws SystemException, PortalException, MalformedURLException, IOException {

		Table table = new Table(new UnitValue[]{UnitValue.createPercentValue(22f), UnitValue.createPercentValue(78f)})
				.setWidth(UnitValue.createPercentValue(100f)).setBorder(Border.NO_BORDER);

		List<Official> elus = getPeopleList(req);
		for (Official elu : elus) {
			// photo de l'élu
			ImageData image = ImageDataFactory.create(domaine + elu.getImageURL());
			Image img = new Image(image);
			float newWidth = 90;
			float newHeight = (img.getImageHeight() / img.getImageWidth()) * newWidth;
			img.scaleAbsolute(newWidth, newHeight).setPadding(0f).setMargins(0f, 0f, 0f, 0f);
			Cell cell = new Cell().add(img).setBorder(Border.NO_BORDER).setPaddings(10f, 0f, 10f, 0f);
			table.addCell(cell);

			// info de l'élu
			cell = new Cell().setKeepTogether(true)
					.add(new Paragraph(elu.getFirstName() + " " + elu.getLastName().toUpperCase()).add("\n\n")
							.setFont(fontBold).setFontSize(16f));
			switch (exportType) {
				case OfficialsConstants.MUNICIPAL:
					cell.add(printPDFMunicipal(elu, themeDisplay.getLocale()).add("\n\n"));
					cell.add(printPDFEurometropole(elu,themeDisplay.getLocale()));
					break;
				case OfficialsConstants.EUROMETROPOLE:
					cell.add(printPDFEurometropole(elu,themeDisplay.getLocale()).add("\n\n"));
					cell.add(printPDFMunicipal(elu, themeDisplay.getLocale()));
					break;
				default:
					cell.add(printPDFMunicipal(elu, themeDisplay.getLocale()).add("\n\n"));
					cell.add(printPDFEurometropole(elu,themeDisplay.getLocale()));
					break;
			}
			cell.setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(1f)).setPaddings(7f, 0f, 10f, 0f);
			table.addCell(cell);
		}
		document.add(table);

	}

	private static Paragraph printPDFMunicipal(Official elu, Locale locale)
			throws PortalException, IOException {

		Paragraph paragraph = new Paragraph().setFont(font).setFontSize(12f);

		if (elu.isEluMunicipal()) {
			paragraph.add(new Text(elu.getName(elu.getFonctionCity(), locale)).setFont(fontBold));

			if (Validator.isNotNull(elu.getThematicDelegation())) {
				paragraph.add("\n")
						.add(LanguageUtil.get(locale, "en-charge-de") + " : ");
				String cityMission = StringUtil.replaceFirst(
						elu.getThematicDelegation(locale), "<p>", "");
				cityMission = StringUtil.replaceFirst(cityMission, "</p>", "");
				List<IElement> elements = HtmlConverter.convertToElements(cityMission);
				for (IElement element : elements) {
					if(element.getClass().getName().equals("com.itextpdf.layout.element.List")){
						com.itextpdf.layout.element.List liste = (com.itextpdf.layout.element.List)element;
						liste.setListSymbol("disc").setMargin(0f).setFont(font).setFontSize(12f);
						List<IElement> sousElements = liste.getChildren();
						for (IElement sousElement : sousElements) {
							ListItem item = (ListItem)sousElement;
							item.setFont(font).setFontSize(12f);
						}
						paragraph.add("\n").add(liste);
					}else {
						paragraph.add((IBlockElement)element);
					}
				}
			}

			List<AssetCategory> quartiers = elu.getDistricts();
			if (Validator.isNotNull(quartiers) && !quartiers.isEmpty()) {
				paragraph.add("\n");
				paragraph.add(new Text(LanguageUtil.get(locale, "adjoint-de-quartier") + " : ").setFont(fontBold));
				StringBuilder strQuartiers = new StringBuilder();
				for (AssetCategory quartierElu : quartiers) {
					if (strQuartiers.length() > 0) {
						strQuartiers.append(", ");
					}
					strQuartiers.append(quartierElu.getName());
				}
				paragraph.add(HtmlUtil.render(strQuartiers.toString()));
			}

			if (Validator.isNotNull(elu.getPoliticalGroupCity())) {
				paragraph.add("\n")
						.add(new Text(HtmlUtil.render(LanguageUtil.get(locale, "groupe-politique") + " : ")).setFont(fontBold))
						.add("\n")
						.add(elu.getName(elu.getPoliticalGroupCity(), locale));
			}
		}
		return paragraph;
	}

	private static Paragraph printPDFEurometropole(Official elu, Locale locale)
			throws PortalException, IOException {

		Paragraph paragraph = new Paragraph().setFont(font).setFontSize(12f);

		if (elu.isEluEurometropole()) {
			paragraph.add(new Text(elu.getName(elu.getFonctionEurometropole(), locale)).setFont(fontBold));

			if (Validator.isNotNull(elu.getFonctionTown())) {
				paragraph.add(", " + elu.getName(elu.getFonctionTown(), locale));
			}

			if (Validator.isNotNull(elu.getTown())) {
				paragraph.add(" " + LanguageUtil.get(locale, "de-la-commune")+ " " + elu.getTown().getTitle(locale));
			}

			if (Validator.isNotNull(elu.getMissions())) {
				paragraph.add("\n")
						.add(LanguageUtil.get(locale, "en-charge-de") + " : ");
				String cusMission = StringUtil
						.replaceFirst(elu.getMissions(locale), "<p>", "");
				cusMission = StringUtil.replaceFirst(cusMission, "</p>", "");
				List<IElement> elements = HtmlConverter.convertToElements(cusMission);
				for (IElement element : elements) {
					if(element.getClass().getName().equals("com.itextpdf.layout.element.List")){
						com.itextpdf.layout.element.List liste = (com.itextpdf.layout.element.List)element;
						liste.setListSymbol("disc").setMargin(0f).setFont(font).setFontSize(12f);
						List<IElement> sousElements = liste.getChildren();
						for (IElement sousElement : sousElements) {
							ListItem item = (ListItem)sousElement;
							item.setFont(font).setFontSize(12f);
						}
						paragraph.add("\n").add(liste);
					}else {
						paragraph.add((IBlockElement)element);
					}
				}
			}

			if (Validator.isNotNull(elu.getPoliticalGroupEurometropole())) {
				paragraph.add("\n")
						.add(new Text(
								HtmlUtil.render(LanguageUtil.get(locale, "groupe-politique")
										+ " : " )).setFont(fontBold))
						.add("\n")
						.add(elu.getName(elu.getPoliticalGroupEurometropole(), locale));
			}
		}
		return paragraph;
	}

	private static List<Official> getPeopleList(ResourceRequest req)
			throws SystemException {

		List<Official> elus = new ArrayList<Official>();
		String[] officialsIds = ParamUtil.getString(req, "ids").split(",");
		for (String id : officialsIds) {
			if (Validator.isNotNull(id)) {
				elus.add(OfficialLocalServiceUtil
						.fetchOfficial(Long.parseLong(id)));
			}
		}

		return elus;
	}

}
