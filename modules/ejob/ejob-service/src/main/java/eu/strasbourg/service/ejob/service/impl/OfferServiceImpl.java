/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.ejob.service.impl;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.base.OfferServiceBaseImpl;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * The implementation of the offer remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.ejob.service.OfferService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferServiceBaseImpl
 */
public class OfferServiceImpl extends OfferServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>eu.strasbourg.service.ejob.service.OfferServiceUtil</code> to access the offer remote service.
	 */

	@Override
	public String exportOffer(Long offerId, Locale locale, ByteArrayOutputStream baos) throws IOException {

		// récupération de l'offre
		Offer offer = this.offerLocalService.fetchOffer(offerId);

		PdfFont font = PdfFontFactory.createRegisteredFont("Helvetica");
		PdfFont fontBold = PdfFontFactory.createRegisteredFont("Helvetica-Bold");

		// génération du pdf
		PdfWriter pdfWriter = new PdfWriter(baos);
		PdfDocument pdf = new PdfDocument(pdfWriter);

		try (Document document = new Document(pdf)) {

			document.setMargins(35f, 35f, 35f, 35f);
			document.setFont(font).setFontSize(12f).setFontColor(new DeviceRgb(49, 69, 93));

			// image
			Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId() , "/strasbourg.eu");
			ExpandoBridge ed = group.getExpandoBridge();
			String headerImage = GetterUtil.getString(ed.getAttribute("image_header_mail_contact"));
			ImageData image = ImageDataFactory.create(headerImage);
			Image img = new Image(image);
			document.add(img.setHorizontalAlignment(HorizontalAlignment.CENTER));

			// vagues
			String domaine = StrasbourgPropsUtil.getURL();
			image = ImageDataFactory.create(domaine + "/o/strasbourg-theme/images/vagues.jpg");
			img = new Image(image);
			document.add(img.setHorizontalAlignment(HorizontalAlignment.CENTER));

			if(offer.getStatus() == WorkflowConstants.STATUS_APPROVED && PortletHelper.isUserAuthorizedToConsultInternOffer(offer.getTypePublication().getName())) {
				// titre du PDF
				Paragraph paragraph = new Paragraph().setFont(fontBold).setFontSize(20f)
						.setMarginTop(-10f).setMarginBottom(30f)
						.setTextAlignment(TextAlignment.CENTER);
				paragraph.add(offer.getPost(locale));
				document.add(paragraph);

				// info de l'offre
				//  encadré gris
				paragraph = new Paragraph().setBackgroundColor(new DeviceRgb(221, 221, 221))
						.setPaddings(30f, 30f, 30f, 30f);
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-publication-id") + " : ").setFont(fontBold).setFontSize(12f));
				paragraph.add("\n");
				paragraph.add(offer.getPublicationId());
				paragraph.add("\n\n");

				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-direction") + " : ").setFont(fontBold).setFontSize(12f));
				paragraph.add("\n");
				paragraph.add(offer.getDirection().getTitle(locale));
				paragraph.add("\n\n");

				if (Validator.isNotNull(offer.getService())) {
					paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-service") + " : ").setFont(fontBold).setFontSize(12f));
					paragraph.add("\n");
					paragraph.add(offer.getService().getTitle(locale));
					paragraph.add("\n\n");
				}

				if (!offer.getOfferCategories().isEmpty() && !offer.getTypeRecrutement().getTitle(locale).equals("Stage")) {
					paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-filiere-categorie") + " : ").setFont(fontBold).setFontSize(12f));
					paragraph.add("\n");
					String categories = "";
					for (AssetCategory category : offer.getOfferCategories()) {
						if(Validator.isNotNull(categories))
							categories += ", ";
						categories += category.getName();
					}
					paragraph.add(categories);
					paragraph.add("\n\n");
				}

				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-type-recrutement") + " : ").setFont(fontBold).setFontSize(12f));
				paragraph.add("\n");
				paragraph.add(offer.getTypeRecrutement().getTitle(locale));
				if (Validator.isNotNull(offer.getPermanentDescription(locale)) && offer.getTypeRecrutement().getTitle(locale) != "Stage") {
					paragraph.add("\n");
					paragraph.add(offer.getPermanentDescription(locale));
				}
				paragraph.add("\n\n");

				if (!offer.getTypeRecrutement().getTitle(locale).equals("Stage")) {
					paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-is-full-time") + " : ").setFont(fontBold).setFontSize(12f));
					paragraph.add("\n");
					if (offer.getIsFullTime())
						paragraph.add(LanguageUtil.get(locale, "eu.offer-full-time-true"));
					else
						paragraph.add(LanguageUtil.get(locale, "eu.offer-full-time-false"));
					paragraph.add("\n\n");
				}

				List<List> gradeRanges = offer.getGradeRanges();
				if (Validator.isNotNull(gradeRanges) && !offer.getTypeRecrutement().getTitle(locale).equals("Stage")) {
					paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-grade") + " : ").setFont(fontBold).setFontSize(12f));
					paragraph.add("\n");
					String grades = "";
					for (List<AssetCategory> gradeRange : gradeRanges) {
						if(Validator.isNotNull(grades))
							grades += ", ";
						grades += gradeRange.get(2).getTitle(locale) +
							" " + LanguageUtil.get(locale, "eu.to") + " " +
							gradeRange.get(3).getTitle(locale);
					}
					paragraph.add(grades);
					paragraph.add("\n\n");
				}

				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-limit-date") + " : ").setFont(fontBold).setFontSize(12f));
				paragraph.add("\n");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				paragraph.add(sdf.format(offer.getLimitDate()));

				if (Validator.isNotNull(offer.getDuration(locale))) {
					paragraph.add("\n\n");
					paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-duree-contrat") + " : ").setFont(fontBold).setFontSize(12f));
					paragraph.add("\n");
					paragraph.add(offer.getDuration(locale));
				}

				if (Validator.isNotNull(offer.getNiveauEtude()) && offer.getTypeRecrutement().getTitle(locale).equals("Stage")) {
					paragraph.add("\n\n");
					paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-niveau-etude") + " : ").setFont(fontBold).setFontSize(12f));
					paragraph.add("\n");
					paragraph.add(offer.getNiveauEtude().getTitle(locale));
				}
				document.add(paragraph);

				// reste du document
				List<IElement> elements = HtmlConverter.convertToElements(offer.getIntroduction(locale));
				for (IElement element : elements) {
					document.add((IBlockElement) element);
				}

				paragraph = new Paragraph().setMarginBottom(-10f);
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-activities").toUpperCase() + " : ").setFont(fontBold).setFontSize(16f));
				document.add(paragraph);
				elements = HtmlConverter.convertToElements(offer.getActivities(locale));
				for (IElement element : elements) {
					document.add((IBlockElement) element);
				}

				paragraph = new Paragraph().setMarginBottom(-10f);
				paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-profil").toUpperCase() + " : ").setFont(fontBold).setFontSize(16f));
				document.add(paragraph);
				elements = HtmlConverter.convertToElements(offer.getProfil(locale));
				for (IElement element : elements) {
					document.add((IBlockElement) element);
				}

				if (Validator.isNotNull(offer.getAvantages(locale)) && !offer.getTypeRecrutement().getTitle(locale).equals("Stage")) {
					paragraph = new Paragraph().setMarginBottom(-10f);
					paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-avantages") + " : ").setFont(fontBold).setFontSize(12f));
					document.add(paragraph);
					elements = HtmlConverter.convertToElements(offer.getAvantages(locale));
					for (IElement element : elements) {
						document.add((IBlockElement) element);
					}
				}

				if (Validator.isNotNull(offer.getConditions(locale))) {
					paragraph = new Paragraph().setMarginBottom(-10f);
					paragraph.add(new Text(LanguageUtil.get(locale, "eu.offer-conditions") + " : ").setFont(fontBold).setFontSize(12f));
					document.add(paragraph);
					elements = HtmlConverter.convertToElements(offer.getConditions(locale));
					for (IElement element : elements) {
						document.add((IBlockElement) element);
					}
				}
			}else {
				// titre du PDF
				Paragraph paragraph = new Paragraph().setFont(fontBold).setFontSize(20f)
						.setMarginTop(-10f).setMarginBottom(30f)
						.setTextAlignment(TextAlignment.CENTER);
				if(offer.getStatus() == WorkflowConstants.STATUS_APPROVED)
					paragraph.add(LanguageUtil.get(locale, "eu.offer-not-visible"));
				else
					paragraph.add(LanguageUtil.get(locale, "eu.offer-unknow"));
				document.add(paragraph);
			}

			if (pdf != null)
				pdf.close();

			if (pdfWriter != null)
				pdfWriter.close();
		}

		return offer.getPost(locale);
	}

	@Override
	public JSONObject getOffer(String publicationId) throws PortalException {
		if (!isAuthorized()) {
			return error("not authorized");
		}

		Offer offer = this.offerLocalService.findByPublicationId(publicationId);
		if (offer == null || !offer.isApproved()) {
			return JSONFactoryUtil.createJSONObject();
		}
		return offer.toJSON();
	}

	private JSONObject error(String message) {
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}

	private boolean isAuthorized() {
		try {
			Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			long globalGroupId = defaultCompany.getGroup().getGroupId();
			return this.getPermissionChecker().hasPermission(globalGroupId, StrasbourgPortletKeys.EJOB_BO,
					StrasbourgPortletKeys.EJOB_BO, "VIEW");
		} catch (PortalException e) {
			return false;
		}
	}

	/*private PortletHelperService _portletHelperService;

	@Reference(unbind = "-")
	protected void setPortletHelperService(
			PortletHelperService portletHelperService) {

		_portletHelperService = portletHelperService;
	}*/
}