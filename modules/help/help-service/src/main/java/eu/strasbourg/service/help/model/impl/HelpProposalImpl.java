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

package eu.strasbourg.service.help.model.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.annotation.versioning.ProviderType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * The extended model implementation for the HelpProposal service. Represents a row in the &quot;help_HelpProposal&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.help.model.HelpProposal<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class HelpProposalImpl extends HelpProposalBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a help proposal model instance should use the {@link eu.strasbourg.service.help.model.HelpProposal} interface instead.
	 */
	public HelpProposalImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché a cette proposition d'aide
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(HelpProposal.class.getName(),
				this.getHelpProposalId());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cette proposition d'aide (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
				.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Retourne la class du statut d'activité de la proposition d'aide (
	 */
	@Override
	public String getActivityStatusClass() {
		AssetCategory ActivityStatusCategory = this.getActivitStatusCategory();
		if (ActivityStatusCategory != null) {
			return AssetVocabularyHelper.getCategoryProperty(ActivityStatusCategory.getCategoryId(), "class");
		} else {
			return "";
		}
	}

	/**
	 * Retourne le statut d'activité de la proposition d'aide (
	 */
	@Override
	public String getActivityStatusTitle(Locale locale) {
		AssetCategory ActivityStatusCategory = this.getActivitStatusCategory();
		if (ActivityStatusCategory != null) {
			return ActivityStatusCategory.getTitle(locale);
		} else {
			return "";
		}
	}

	/**
	 * Retourne la catégorie statut activite proposition d'aide de l'aide
	 */
	@Override
	public AssetCategory getActivitStatusCategory() {
		List<AssetCategory> assetCategories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.HELP_PROPOSAL_ACTIVITY_STATUS);
		if (assetCategories.size() > 0) {
			return assetCategories.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Retourne la class du statut de modération de la proposition d'aide (
	 */
	@Override
	public String getModerationStatusClass() {
		AssetCategory ModerationStatusCategory = this.getModerationStatusCategory();
		if (ModerationStatusCategory != null) {
			return AssetVocabularyHelper.getCategoryProperty(ModerationStatusCategory.getCategoryId(), "class");
		} else {
			return "";
		}
	}

	/**
	 * Retourne le statut de modération de la proposition d'aide (
	 */
	@Override
	public String getModerationStatusTitle(Locale locale) {
		AssetCategory ModerationStatusCategory = this.getModerationStatusCategory();
		if (ModerationStatusCategory != null) {
			return ModerationStatusCategory.getTitle(locale);
		} else {
			return "";
		}
	}

	/**
	 * Retourne la catégorie statut activite proposition d'aide de l'aide
	 */
	@Override
	public AssetCategory getModerationStatusCategory() {
		List<AssetCategory> assetCategories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.HELP_PROPOSAL_MODERATION_STATUS);
		if (assetCategories.size() > 0) {
			return assetCategories.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Retourne les type d'aide de la proposition d'aide
	 */
	@Override
	public List<AssetCategory> getHelpProposalTypeCategories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.HELP_PROPOSAL_TYPE);
	}

	/**
	 * Retourne une chaine des localisations correspondant
	 */
	@Override
	public String getLocalisationLabel(Locale locale) {
		List<AssetCategory> districts = getDistrictCategories();
		List<AssetCategory> cities = getCityCategories();
		return AssetVocabularyHelper.getDistrictTitle(locale, districts, cities);
	}

	/**
	 * Retourne les sous-catégories 'Territoire' correspondant aux villes de la initiative
	 * @return : null si vide, sinon la liste des catégories
	 */
	@Override
	public List<AssetCategory> getCityCategories() {
		List<AssetCategory> territories = this.getTerritoryCategories();
		List<AssetCategory> cities = new ArrayList<AssetCategory>();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 1) {
					cities.add(territory);
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return cities;
	}

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la proposition d'aide
	 * @return : null si vide, sinon la liste des catégories
	 */
	@Override
	public List<AssetCategory> getDistrictCategories() {
		List<AssetCategory> territories = this.getTerritoryCategories();
		List<AssetCategory> districts = new ArrayList<AssetCategory>();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 2) {
					districts.add(territory);
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return districts;
	}

	/**
	 * Retourne les catégories 'Territoire' correspondant aux pays de la proposaition d'aide
	 */
	@Override
	public List<AssetCategory> getTerritoryCategories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.TERRITORY);
	}

	/**
	 * Retourne le nom de du depositaire sous forme "Truc M." ou le "Au nom de ..."
	 */
	@Override
	public String getAuthorLabel() {
		PublikUser author = this.getAuthor();
		if (this.getInTheNameOf() != "" && this.getInTheNameOf() != null) {
			return this.getInTheNameOf();
		}
		else if (author != null) {
			return StringUtil.upperCaseFirstLetter(author.getFirstName())
					+ " "
					+  StringUtil.toUpperCase(StringUtil.shorten(author.getLastName(), 2, "."));
		} else {
			return null;
		}

	}

	/**
	 * Retourne l'adresse mail du depositaire s'il existe
	 */
	@Override
	public String getAuthorEmail() {
		PublikUser author = this.getAuthor();
		if (author != null) {
			return author.getEmail();
		} else {
			return "";
		}
	}

	/**
	 * Retourne le nom prenom du depositaire s'il existe
	 */
	@Override
	public String getAuthorNameLabel() {
		PublikUser author = this.getAuthor();
		if (author != null) {
			return author.getFirstName()
					+ " "
					+  author.getLastName();
		} else {
			return "";
		}
	}

	/**
	 * Retourne l'utilisateur Publik depositaire
	 * @return
	 */
	public PublikUser getAuthor() {
		return PublikUserLocalServiceUtil.getByPublikUserId(this.getPublikId());
	}


	/**
	 * Retourne le nombre de demandes d'aides pour cette proposition
	 */
	@Override
	public int getNbHelpRequests() {
		return HelpRequestLocalServiceUtil.getByHelpProposalId(this.getHelpProposalId()).size();
	}

	/**
	 *
	 * @return La date de publication au format français jj/mm/aaaa
	 */
	public String getPublicationDateFr(){
		Date date = this.getAssetEntry().getPublishDate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		return FileEntryHelper.getFileEntryURL(this.getImageId());
	}

	/**
	 * Retourne le copyright de l'image principale
	 */
	@Override
	public String getImageCopyright(Locale locale) {
		return FileEntryHelper.getImageCopyright(this.getImageId(), locale);
	}

	/**
	 * Retourne la version JSON de l'entité
	 *
	 * @throws PortalException
	 */
	@Override
	public JSONObject toJSON() throws PortalException {
		// Initialisation des variables tempons et résultantes
		JSONObject jsonHelpProposal = JSONFactoryUtil.createJSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat unformattedDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Champs : Gestion
		jsonHelpProposal.put("id", this.getHelpProposalId());
		jsonHelpProposal.put("createDate", dateFormat.format(this.getCreateDate()));
		jsonHelpProposal.put("unformattedCreateDate", unformattedDateFormat.format(this.getCreateDate()));
		jsonHelpProposal.put("userName", HtmlUtil.stripHtml(HtmlUtil.escape(this.getUserName())));

		// Champs : Generaux
		jsonHelpProposal.put("title", HtmlUtil.stripHtml(HtmlUtil.escape(this.getTitle())));
		jsonHelpProposal.put("author", HtmlUtil.stripHtml(HtmlUtil.escape(this.getAuthorLabel())));
		jsonHelpProposal.put("description", this.getDescription());
		jsonHelpProposal.put("address", this.getAddress());
		jsonHelpProposal.put("city", this.getCity());
		jsonHelpProposal.put("postalCode", this.getPostalCode());
		jsonHelpProposal.put("modifiedByUserDate", this.getModifiedByUserDate());
		jsonHelpProposal.put("spokenLanguages", this.getSpokenLanguages());

		// Champs : Médias
		jsonHelpProposal.put("imageId", this.getImageId());

		// Liste des Ids des catégories Thématiques
		/*
		JSONArray jsonThematics = AssetVocabularyHelper.getExternalIdsJSONArray(this.getThematicCategories());
		if (jsonThematics.length() > 0) {
			jsonHelpProposal.put("thematics", jsonThematics);
		}
		*/

		return jsonHelpProposal;
	}
}