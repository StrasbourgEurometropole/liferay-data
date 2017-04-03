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
		"mvc.command.name=startImportTerritories" }, service = MVCActionCommand.class)
public class StartImportTerritoriesActionCommand implements MVCActionCommand {

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	private File territoriesFile = null;

	private String resultat;
	private String messagesErreurs;
	private List<String> listCategoryCrees = new ArrayList<String>();
	private List<String> listCategoryModifies = new ArrayList<String>();
	private List<String> listCategoryErreurs = new ArrayList<String>();
	private ServiceContext sc = null;

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
			throws PortletException {

		try {
			resultat = "Succès";
			messagesErreurs = "";
			sc = ServiceContextFactory.getInstance(request);
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

			ThemeDisplay td = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
			sc.setScopeGroupId(td.getCompanyGroupId());
			sc.setUserId(td.getUserId());
		} catch (PortalException e) {
			_log.error(e);
		}

		UploadPortletRequest uploadRequest = PortalUtil
				.getUploadPortletRequest(request);
		territoriesFile = uploadRequest.getFile("territories-file");

		_log.info("Start import");

		if (territoriesFile != null) {

			FileReader fr = null;
			try {
				fr = new FileReader(territoriesFile);
				BufferedReader br = new BufferedReader(fr);

				try {
					String line = br.readLine();
					String[] chaine = line.split(";");

					if (chaine.length == 3
							&& chaine[1]
									.equals("Identifiant_Parent_Categorie_Territoire")
							&& chaine[2].equals("Nom_Categorie_Territoire")) {

						traitementFichier(br);

					} else {
						messagesErreurs = "Le fichier ne respecte pas le formalisme attendu.";
						resultat = "Erreur";
					}

					br.close();
					fr.close();
				} catch (IOException e1) {
					messagesErreurs = "Lecture du fichier impossible.";
					resultat = "Erreur";
				}
			} catch (FileNotFoundException e) {
				messagesErreurs = "Fichier introuvable.";
				resultat = "Erreur";
			}
		} else

		{
			messagesErreurs = "Aucun fichier choisi.";
			resultat = "Erreur";
		}
		_log.info("End import");

		sendMail();

		return true;
	}

	public void traitementFichier(BufferedReader br) throws IOException {

		String idTerritoire = "";
		String idParentTerritoire = "";
		String nom = "";
		int ligne = 1;

		try {

			// Récupération du vocabulaire Territoire
			AssetVocabulary vocabulary = AssetVocabularyHelper
					.getGlobalVocabulary("Territoire");

			if (vocabulary != null) {

				String[] chaine;
				for (String line = br.readLine(); line != null; line = br
						.readLine()) {
					chaine = line.split(";");

					ligne++;

					idTerritoire = chaine[0];
					idParentTerritoire = chaine[1];
					nom = chaine[2];

					if (!idTerritoire.equals("") && !nom.equals("")) {

						// Récupération des catégories du
						// vocabulaire Territoire
						List<AssetCategory> categories = vocabulary
								.getCategories();

						// Récupère la catégorie
						AssetCategory selectCategory = null;
						for (AssetCategory category : categories) {
							if (AssetVocabularyHelper
									.getCategoryProperty(
											category.getCategoryId(), "SIG")
									.equals(idTerritoire)) {
								selectCategory = category;
								break;
							}
						}

						// Récupère la catégorie parent
						AssetCategory categoryParent = null;
						long idParent = 0;
						if (!idParentTerritoire.equals("")) {
							for (AssetCategory category : categories) {
								if (AssetVocabularyHelper
										.getCategoryProperty(
												category.getCategoryId(), "SIG")
										.equals(idParentTerritoire)) {
									categoryParent = category;
									idParent = categoryParent.getCategoryId();
									break;
								}
							}
						}

						if (idParentTerritoire.equals("")
								|| categoryParent != null) {

							String[] categoryProperties = {
									"SIG:" + idTerritoire };

							if (selectCategory == null) {
								// le territoire n'existe pas encore
								try {
									_assetCategoryService.addCategory(
											sc.getScopeGroupId(), idParent,
											LocalizationUtil
													.getLocalizationMap(nom),
											null, vocabulary.getVocabularyId(),
											categoryProperties, sc);

									listCategoryCrees.add(ligneRetour(ligne,
											idTerritoire, nom) + "<br>");
									_log.info(
											"territoire crée => " + ligneRetour(
													ligne, idTerritoire, nom));
								} catch (Exception e) {
									resultat = "Réussi avec des erreurs";
									listCategoryErreurs.add(ligneRetour(ligne,
											idTerritoire, nom) + " => "
											+ e.getMessage() + ".<br>");
									_log.info(
											"Erreur à la création du territoire => "
													+ ligneRetour(ligne,
															idTerritoire, nom)
													+ " => " + e.getMessage()
													+ ".");
								}
							} else {
								// le territoire existe déjà, on ne le modifie
								// que si son titre français est différent du
								// nom
								if (!nom.equals(selectCategory
										.getTitle(Locale.FRENCH))) {
									try {
										// ATTENTION, on ne modifie que le titre
										// en français
										Map<Locale, String> titres = selectCategory
												.getTitleMap();
										titres.replace(Locale.FRENCH, nom);
										_assetCategoryService.updateCategory(
												selectCategory.getCategoryId(),
												idParent, titres, null,
												vocabulary.getVocabularyId(),
												categoryProperties, sc);

										listCategoryModifies.add(
												ligneRetour(ligne, idTerritoire,
														nom) + "<br>");
										_log.info("Territoire modifié => "
												+ ligneRetour(ligne,
														idTerritoire, nom));
									} catch (Exception e) {
										resultat = "Réussi avec des erreurs";
										listCategoryErreurs.add(ligneRetour(
												ligne, idTerritoire, nom)
												+ " => " + e.getMessage()
												+ ".<br>");
										_log.info(
												"Erreur à la modification du territoire => "
														+ ligneRetour(ligne,
																idTerritoire,
																nom)
														+ " => "
														+ e.getMessage() + ".");
									}
								}
							}
						} else {
							resultat = "Réussi avec des erreurs";
							listCategoryErreurs
									.add(ligneRetour(ligne, idTerritoire, nom)
											+ " => Le parent associ&eacute; &agrave; la ligne "
											+ ligne + " n'existe pas.<br>");
							_log.info(
									"Erreur à la création/modification du territoire => "
											+ ligneRetour(ligne, idTerritoire,
													nom)
											+ " => Le parent associé à la ligne n'existe pas.");
						}
					} else {
						resultat = "Réussi avec des erreurs";
						String erreur = ligneRetour(ligne, idTerritoire, nom);
						if (idTerritoire.equals("")) {
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
								"Erreur à la création/modification du territoire => "
										+ ligneRetour(ligne, idTerritoire, nom)
										+ " => champ Identifiant_Categories_SIG et/ou Nom_Categorie_SIG manquant(s).");
					}
				}
			} else {
				messagesErreurs = "Le vocabulaire Territoire n'existe pas.";
				resultat = "Erreur";
			}
		} catch (PortalException e) {
			messagesErreurs = e.getMessage();
			resultat = "Erreur";
		}
	}

	public String ligneRetour(int ligne, String idTerritoire, String nom) {
		return "N&deg; ligne : " + ligne + ", identifiant territoire : "
				+ idTerritoire + ", nom du territoire : " + nom;
	}

	public void sendMail() {

		String environment = StrasbourgPropsUtil.getEnvironment();
		String titre = environment + " Journal d'import des territoires - "
				+ resultat;
		String corps;
		if (resultat.equals("Erreur")) {
			corps = "L'import du fichier ";
			if (territoriesFile != null) {
				corps += territoriesFile.getName();
			}
			corps += " n'a pas pu &ecirc;tre fait pour les raisons suivantes : <br>"
					+ messagesErreurs;
		} else {
			String dateImport = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			String heureImport = new SimpleDateFormat("HH:mm")
					.format(new Date());
			corps = "L'import du fichier " + territoriesFile.getName()
					+ " a &eacute;t&eacute; r&eacute;alis&eacute; avec succ&egrave;s le "
					+ dateImport + " &agrave; " + heureImport + ".<br>"
					+ "Territoires cr&eacute;&eacute;s ("
					+ listCategoryCrees.size() + ") :<br>";
			for (String lieuxCrees : listCategoryCrees) {
				corps += lieuxCrees;
			}
			corps += "Territoires modifi&eacute;s ("
					+ listCategoryModifies.size() + ") :<br>";
			for (String lieuxModifies : listCategoryModifies) {
				corps += lieuxModifies;
			}
			corps += "Territoires en erreur (" + listCategoryErreurs.size()
					+ ") :<br>";
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