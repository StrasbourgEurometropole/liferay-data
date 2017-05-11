/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.strasbourg.portlet.place.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=startImportCategories" }, service = MVCActionCommand.class)
public class StartImportCategoriesActionCommand implements MVCActionCommand {

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	private File categoriesFile = null;

	private String resultat;
	private String messagesErreurs;
	private List<String> listCategoryCrees;
	private List<String> listCategoryModifies;
	private List<String> listCategoryErreurs;
	private ServiceContext sc = null;
	private Locale locale = null;

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

		try {
			resultat = "SUCCES";
			messagesErreurs = "";
			listCategoryCrees = new ArrayList<String>();
			listCategoryModifies = new ArrayList<String>();
			listCategoryErreurs = new ArrayList<String>();
			sc = ServiceContextFactory.getInstance(request);
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

			ThemeDisplay td = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
			locale = td.getLocale();
			sc.setScopeGroupId(td.getCompanyGroupId());
			sc.setUserId(td.getUserId());
		} catch (PortalException e) {
			_log.error(e);
		}

		UploadPortletRequest uploadRequest = PortalUtil
				.getUploadPortletRequest(request);
		categoriesFile = uploadRequest.getFile("categories-file");

		_log.info("Start import");

		if (categoriesFile != null) {

			FileReader fr = null;
			try {
				fr = new FileReader(categoriesFile);
				BufferedReader br = new BufferedReader(fr);

				try {
					String line = br.readLine();
					String[] chaine = line.split(";");

					if (chaine.length == 3
							&& chaine[0].equals("Identifiant_Categories_SIG")
							&& chaine[1].equals(
									"Identifiant_Parent_Categories_SIG")) {

						traitementFichier(br);

					} else {
						messagesErreurs = "Le fichier ne respecte pas le formalisme attendu.";
						resultat = "ERREUR";
					}

					br.close();
					fr.close();
				} catch (IOException e1) {
					messagesErreurs = "Lecture du fichier impossible.";
					resultat = "ERREUR";
				}

			} catch (FileNotFoundException e) {
				messagesErreurs = "Fichier introuvable.";
				resultat = "ERREUR";
			}
		} else {
			messagesErreurs = "Aucun fichier choisi.";
			resultat = "ERREUR";
		}
		sendMail();
		_log.info("End import");

		return true;
	}

	public void traitementFichier(BufferedReader br) throws IOException {

		String idSIG = "";
		String idParentSIG = "";
		String nom = "";
		int ligne = 1;

		try {
			// Récupération du vocabulaire Type de lieu
			AssetVocabulary vocabulary = AssetVocabularyHelper
					.getGlobalVocabulary("Type de lieu");

			if (vocabulary != null) {

				String[] chaine;
				for (String line = br.readLine(); line != null; line = br
						.readLine()) {
					chaine = line.split(";");

					ligne++;

					idSIG = chaine.length > 0 ? chaine[0] : "";
					idParentSIG = chaine.length > 1 ? chaine[1] : "";
					nom = chaine.length > 2 ? chaine[2] : "";

					if (!idSIG.equals("") && !nom.equals("")) {

						// Récupération des catégories du
						// vocabulaire Type de lieu
						List<AssetCategory> categories = vocabulary
								.getCategories();

						// Récupère la catégorie
						AssetCategory selectCategory = null;
						for (AssetCategory category : categories) {
							if (AssetVocabularyHelper
									.getCategoryProperty(
											category.getCategoryId(), "SIG")
									.equals(idSIG)) {
								selectCategory = category;
								break;
							}
						}

						// Récupère la catégorie parent
						AssetCategory categoryParent = null;
						long idParent = 0;
						if (!idParentSIG.equals("")) {
							for (AssetCategory category : categories) {
								if (AssetVocabularyHelper
										.getCategoryProperty(
												category.getCategoryId(), "SIG")
										.equals(idParentSIG)) {
									categoryParent = category;
									idParent = categoryParent.getCategoryId();
									break;
								}
							}
						}

						if (idParentSIG.equals("") || categoryParent != null) {

							String[] categoryProperties = { "SIG:" + idSIG };

							if (selectCategory == null) {
								// le type de lieu n'existe pas encore
								try {
									_assetCategoryService.addCategory(
											sc.getScopeGroupId(), idParent,
											LocalizationUtil
													.getLocalizationMap(nom),
											null, vocabulary.getVocabularyId(),
											categoryProperties, sc);
									listCategoryCrees
											.add(ligneRetour(ligne, idSIG, nom)
													+ "<br>");
									_log.info("Type de lieu crée => "
											+ ligneRetour(ligne, idSIG, nom));
								} catch (Exception e) {

									listCategoryErreurs
											.add(ligneRetour(ligne, idSIG, nom)
													+ " => " + e.getMessage()
													+ ".<br>");
									_log.info(
											"Erreur à la création du type de lieu => "
													+ ligneRetour(ligne, idSIG,
															nom)
													+ " => " + e.getMessage()
													+ ".");
								}
							} else {
								// le type de lieu existe déjà, on ne la modifie
								// que si son titre français est différent du
								// nom
								if (!nom.equals(selectCategory
										.getTitle(locale))) {
									try {
										// ATTENTION, on ne modifie que le titre
										// en français
										Map<Locale, String> titres = selectCategory
												.getTitleMap();
										titres.replace(locale, nom);
										_assetCategoryService.updateCategory(
												selectCategory.getCategoryId(),
												idParent, titres, null,
												vocabulary.getVocabularyId(),
												categoryProperties, sc);

										listCategoryModifies.add(
												ligneRetour(ligne, idSIG, nom)
														+ "<br>");
										_log.info("Type de lieu modifié => "
												+ ligneRetour(ligne, idSIG,
														nom));
									} catch (Exception e) {
										resultat = "REUSSI avec des erreurs";
										listCategoryErreurs.add(
												ligneRetour(ligne, idSIG, nom)
														+ " => "
														+ e.getMessage()
														+ ".<br>");
										_log.info(
												"Erreur à la modification du type de lieu => "
														+ ligneRetour(ligne,
																idSIG, nom)
														+ " => "
														+ e.getMessage() + ".");
									}
								}
							}

						} else {
							resultat = "REUSSI avec des erreurs";
							listCategoryErreurs
									.add(ligneRetour(ligne, idSIG, nom)
											+ " => Le parent associ&eacute; &agrave; la ligne "
											+ ligne + " n'existe pas.<br>");
							_log.info(
									"Erreur à la création/modification du type de lieu => "
											+ ligneRetour(ligne, idSIG, nom)
											+ " => Le parent associ& à la ligne n'existe pas.");
						}

					} else {
						resultat = "REUSSI avec des erreurs";
						String erreur = ligneRetour(ligne, idSIG, nom);
						if (idSIG.equals("")) {
							erreur += "<br>Le champ Identifiant_Categories_SIG est manquant &agrave; la ligne "
									+ ligne;
						}
						if (nom.equals("")) {
							erreur += "<br>Le champ Nom_Categorie_SIG est manquant &agrave; la ligne "
									+ ligne;
						}
						erreur += "<br>";
						listCategoryErreurs.add(erreur);
						_log.info(
								"Erreur à la création/modification du type de lieu => "
										+ ligneRetour(ligne, idSIG, nom)
										+ " => champ Identifiant_Categories_SIG et/ou Nom_Categorie_SIG manquant(s).");
					}
				}
			} else {
				messagesErreurs = "Le vocabulaire Type de lieu n'existe pas.";
				resultat = "ERREUR";
			}
		} catch (PortalException e) {
			messagesErreurs = e.getMessage();
			resultat = "ERREUR";
		}
	}

	public String ligneRetour(int ligne, String idSIG, String nom) {
		return "N&deg; ligne : " + ligne + ", identifiant type de lieu : "
				+ idSIG + ", nom du type de lieu : " + HtmlUtil.escape(nom);
	}

	public void sendMail() {

		String environment = StrasbourgPropsUtil.getEnvironment();
		String titre = environment + " Journal d'import des categories - "
				+ resultat;
		String corps;
		if (resultat.equals("ERREUR")) {
			corps = "L'import du fichier ";
			if (categoriesFile != null) {
				corps += categoriesFile.getName();
			}
			corps += " n'a pas pu &ecirc;tre fait pour les raisons suivantes : <br>"
					+ messagesErreurs;
		} else {
			String dateImport = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			String heureImport = new SimpleDateFormat("HH:mm")
					.format(new Date());
			corps = "L'import du fichier " + categoriesFile.getName()
					+ " a &eacute;t&eacute; r&eacute;alis&eacute; avec succ&egrave;s le "
					+ dateImport + " &agrave; " + heureImport + ".<br>"
					+ "Cat&eacute;gories cr&eacute;&eacute;es ("
					+ listCategoryCrees.size() + ") :<br>";
			for (String lieuxCrees : listCategoryCrees) {
				corps += lieuxCrees;
			}
			corps += "Cat&eacute;gories modifi&eacute;es ("
					+ listCategoryModifies.size() + ") :<br>";
			for (String lieuxModifies : listCategoryModifies) {
				corps += lieuxModifies;
			}
			corps += "Cat&eacute;gories en erreur ("
					+ listCategoryErreurs.size() + ") :<br>";
			for (String lieuxErreurs : listCategoryErreurs) {
				corps += lieuxErreurs;
			}
		}

		String mailAddresses = StrasbourgPropsUtil.getPlaceImportMails();

		try {
			MailHelper.sendMailWithHTML("no-reply@no-reply-strasbourg.eu",
					mailAddresses, titre, corps);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Reference(unbind = "-")
	protected void setAssetCategoryService(
			AssetCategoryService assetCategoryService) {

		_assetCategoryService = assetCategoryService;
	}

	private AssetCategoryService _assetCategoryService;
}