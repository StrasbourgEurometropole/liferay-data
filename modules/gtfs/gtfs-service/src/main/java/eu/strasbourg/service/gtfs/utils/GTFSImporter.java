package eu.strasbourg.service.gtfs.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.gtfs.model.*;
import eu.strasbourg.service.gtfs.service.*;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.GTFSLoaderHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.utils.exception.FileAccessException;
import eu.strasbourg.utils.models.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Classe permettant d'effectuer l'import des donnees issues du flux GTFS
 * pour les incoporer aux entitees liferay definitives utilisees en front
 *
 * @author cedric.henry
 */
public class GTFSImporter {

	public final static Log log = LogFactoryUtil.getLog(GTFSImporter.class);

	private ServiceContext sc;
	private ImportHistoric importHistoric;

	private static String GTFS_URL = "https://opendata.cts-strasbourg.eu/google_transit.zip";

	/**
	 * Constructeur de base
	 * @param sc Contexte de la requete
	 * @param importHistoric Entite de suivi de l'import
	 */
	public GTFSImporter (ServiceContext sc, ImportHistoric importHistoric) {
		this.sc = sc;
		this.importHistoric = importHistoric;
	}

	/**
	 * Opere l'import
	 */
	public void doImport() {
		this.importHistoric.setStartDate(new Date());

		// Suppression des historiques d'import de plus de 3 mois
		int nbSuppressions = ImportHistoricLocalServiceUtil.deleteOldImportHistorics();
		this.importHistoric.addNewOperation("Cleaned up "+nbSuppressions+" import historics older than 3 months.");

		// Download gtfs file and chech hash
		File gtfsFile = null;
		File gtfsFolder = null;
		ImportHistoric latestImport = ImportHistoricLocalServiceUtil.getLatestImportHistoric(this.importHistoric);
		try {
			gtfsFile = getGTFSData();
			String gtfsChecksum = FileUtil.getMD5Checksum(gtfsFile);
			gtfsFolder = FileUtil.createTempFolder();
			FileUtil.unzip(gtfsFile, gtfsFolder);
			this.importHistoric.setGtfsFileHash(gtfsChecksum);

		} catch (IOException e) {
			this.importHistoric.setErrorDescription("Probleme survenu lors de l'acces aux fichiers du flux GTFS");
			this.importHistoric.setErrorStackTrace(e.toString());
			this.importHistoric.setResult(0);
			log.error(e);
			return;
		}

		boolean skipDataImport = latestImport != null && gtfsFile != null && latestImport.getResult() == 1 &&
			this.importHistoric.getGtfsFileHash().equals(latestImport.getGtfsFileHash());

		// Import des donnees du flux
		if (!skipDataImport) {
			importGTFSData(gtfsFolder.getAbsolutePath()+"/");
		}

		// Convertion des donnees du flux vers des entitees liferay affichables
		if (this.importHistoric.getErrorStackTrace().equals(""))
			convertGTFSData();

		this.importHistoric.setFinishDate(new Date());
	}

	/**
	 * Recupere le GTFS et importe les donnees dans les tables tempons de ce service
	 */
	private void importGTFSData(String gtfsFolderPath) {

		try {
			//Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());
			this.importHistoric.addNewOperation("################### GTFS Files ###################");
			this.importHistoric.addNewOperation("#1/1# Starting import of GTFS");

			// Recuperation des lignes
			Map<String, AgencyGTFS> mapAgencys;
			mapAgencys = GTFSLoaderHelper.readAgencyData(gtfsFolderPath);
			AgencyLocalServiceUtil.importFromGTFS(mapAgencys);
			this.importHistoric.addNewOperation("Get " + mapAgencys.size() + " Agency entries");
			//Timestamp agencyEndTimestamp = new Timestamp(System.currentTimeMillis());

			// Recuperation des calendriers
			Map<String, CalendarGTFS> mapCalendars;
			mapCalendars = GTFSLoaderHelper.readCalendarData(gtfsFolderPath);
			CalendarLocalServiceUtil.importFromGTFS(mapCalendars);
			this.importHistoric.addNewOperation("Get " + mapCalendars.size() + " Calendar entries");
			//Timestamp CalendarEndTimestamp = new Timestamp(System.currentTimeMillis());

			// Recuperation des exceptions au calendrier
			Map<String, List<CalendarDatesGTFS>> mapCalendarDates;
			mapCalendarDates = GTFSLoaderHelper.readCalendarDatesData(gtfsFolderPath);
			CalendarDateLocalServiceUtil.importFromGTFS(mapCalendarDates);
			this.importHistoric.addNewOperation("Get " + mapCalendarDates.size() + " CalendarDate entries");
			//Timestamp CalendarDatesEndTimestamp = new Timestamp(System.currentTimeMillis());


			// Recuperation des lignes
			Map<String, RoutesGTFS> mapRoutes;
			mapRoutes = GTFSLoaderHelper.readRoutesData(gtfsFolderPath);
			RouteLocalServiceUtil.importFromGTFS(mapRoutes);
			this.importHistoric.addNewOperation("Get " + mapRoutes.size() + " Route entries");
			//Timestamp routesEndTimestamp = new Timestamp(System.currentTimeMillis());

			// Recuperation des temps d'arrêt
			Map<String, List<StopTimesGTFS>> mapStopTimes;
			mapStopTimes = GTFSLoaderHelper.readStopTimesData(gtfsFolderPath);
			StopTimeLocalServiceUtil.importFromGTFS(mapStopTimes);
			this.importHistoric.addNewOperation("Get " + mapStopTimes.size() + " StopTime entries");
			//Timestamp stoptimesEndTimestamp = new Timestamp(System.currentTimeMillis());

			// Recuperation des routes
			Map<String, StopsGTFS> mapStops;
			mapStops = GTFSLoaderHelper.readStopsData(gtfsFolderPath);
			StopLocalServiceUtil.importFromGTFS(mapStops);
			this.importHistoric.addNewOperation("Get " + mapStops.size() + " Stops entries");
			//Timestamp stopsEndTimestamp = new Timestamp(System.currentTimeMillis());

			// Recuperation des voyages
			Map<String, TripsGTFS> mapTrips;
			mapTrips = GTFSLoaderHelper.readTripsData(gtfsFolderPath);
			TripLocalServiceUtil.importFromGTFS(mapTrips);
			this.importHistoric.addNewOperation("Get " + mapTrips.size() + " Trips keys");
			//Timestamp tripsEndTimestamp = new Timestamp(System.currentTimeMillis());

			//Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
			/*
			// Pour les logs savoir combien de temps chaque partie à prit
			long processTime = (endTimestamp.getTime() - startTimestamp.getTime()) / 1000;
			this.importHistoric.addNewOperation("Finishing files data import in " + processTime + " seconds.");
			this.importHistoric.addNewOperation("Time for Agency:" + (startTimestamp.getTime() - agencyEndTimestamp.getTime()));
			this.importHistoric.addNewOperation("Time for Calendar:" + (agencyEndTimestamp.getTime() - CalendarEndTimestamp.getTime()));
			this.importHistoric.addNewOperation("Time for CalendarDates:" + (CalendarEndTimestamp.getTime() - CalendarDatesEndTimestamp.getTime()));
			this.importHistoric.addNewOperation("Time for Routes:" + (CalendarDatesEndTimestamp.getTime() - routesEndTimestamp.getTime()));
			this.importHistoric.addNewOperation("Time for Stoptimes:" + (routesEndTimestamp.getTime() - stoptimesEndTimestamp.getTime()));
			this.importHistoric.addNewOperation("Time for Stops:" + (stoptimesEndTimestamp.getTime() - stopsEndTimestamp.getTime()));
			this.importHistoric.addNewOperation("Time for Trips:" + (stopsEndTimestamp.getTime() - tripsEndTimestamp.getTime()));
			*/
		} catch (PortalException e) {
			this.importHistoric.setErrorDescription("Probleme survenu lors de la lecture des donnees du flux GTFS");
			this.importHistoric.setErrorStackTrace(e.toString());
			this.importHistoric.setResult(0);
			log.error(e);
		} catch (FileAccessException e) {
			this.importHistoric.setErrorDescription("Probleme survenu lors de l'acces aux fichiers du flux GTFS");
			this.importHistoric.setErrorStackTrace(e.toString());
			this.importHistoric.setResult(0);
			log.error(e);
		}
	}

	private void convertGTFSData() {

		Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());
		this.importHistoric.addNewOperation("################### Convert GTFS data ###################");

		try {
			// Initialisation des compteurs
			int nbNewStops = 0;
			int nbUpdatedStops = 0;
			int nbRepublishedStops = 0;
			int nbNewLines = 0;
			int nbUpdatedLines = 0;
			int nbRepublishedLines = 0;

			/**
			 * Import des arrêts
			 */
			this.importHistoric.addNewOperation("#1/7# Starting stops conversion");

			// Liste des arrets à mettre à jour et nouvelles entrées
			List<Arret> arretsToUpdate = new ArrayList<>();
			// Liste des arrets à potentiellement depublier
			Map<String, Arret> arretsToUnpublish = ArretLocalServiceUtil.getAll();

			for (Stop stop : StopLocalServiceUtil.getAllStops()) {

				// Si il existe deja en base et qu'il est toujours d'actualite dans le GTFS,
				// on le retire de ceux a supprimer et on recupere en meme temps l'element voulu
				Arret arret = arretsToUnpublish.remove(stop.getStop_id());

				// Si l'arret existe, on verifie les informations et on les met à jour si differentes
				if (arret != null) {
					boolean edition = false;
					if (!arret.getTitle().equals(stop.getStop_name())) {
						arret.setTitle(stop.getStop_name());
						edition = true;
						this.importHistoric.addNewOperation(
								"Stop n. " + stop.getStop_id() + " : change name for '" + stop.getStop_name() + "'"
						);
					}
					if (!arret.getCode().equals(stop.getStop_code())) {
						arret.setCode(stop.getStop_code());
						edition = true;
						this.importHistoric.addNewOperation(
								"Stop n. " + stop.getStop_id() + " : change code for '" + stop.getStop_code() + "'"
						);
					}
					if (!arret.getLatitude().equals(stop.getStop_lat())) {
						arret.setLatitude(stop.getStop_lat());
						edition = true;
						this.importHistoric.addNewOperation(
								"Stop n. " + stop.getStop_id() + " : change latitude for '" + stop.getStop_lat() + "'"
						);
					}
					if (!arret.getLongitude().equals(stop.getStop_lon())) {
						arret.setLongitude(stop.getStop_lon());
						edition = true;
						this.importHistoric.addNewOperation(
								"Stop n. " + stop.getStop_id() + " : change longitude for '" + stop.getStop_lon() + "'"
						);
					}
					// Verification du statut et changement si reaparition
					if (arret.isDraft()) {
						// L'action par defaut d'un ServiceContext est la publication, mettre a jour l'entree suffit donc
						edition = true;
						nbRepublishedStops++;
						this.importHistoric.addNewOperation(
								"Stop n. " + stop.getStop_id() + " : going to be republished"
						);
					}

					// Si l'arret a subit une modification, on l'ajoute dans la liste des arrets a sauvegarder
					if (edition) {
						arretsToUpdate.add(arret);
						nbUpdatedStops++;
					}

				}
				// Sinon, on cree un nouvel arret
				else {
					// Creation de l'arret vide
					arret = ArretLocalServiceUtil.createArret(this.sc);
					// Completion des informations
					arret.setStopId(stop.getStop_id());
					arret.setTitle(stop.getStop_name());
					arret.setCode(stop.getStop_code());
					arret.setLatitude(stop.getStop_lat());
					arret.setLongitude(stop.getStop_lon());

					this.importHistoric.addNewOperation(
							"New stop detected  --> [ " +
									"id : " + stop.getStop_id() +
									", name : " + stop.getStop_name() +
									", code : " + stop.getStop_code() +
									", latitude : " + stop.getStop_lat() +
									", longitude : " + stop.getStop_lon() + "]"
					);

					arretsToUpdate.add(arret);
					nbNewStops++;
				}

			}

			/**
			 * Import des lignes
			 */
			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
			this.importHistoric.addNewOperation("#2/7# Starting routes conversion");

			// Liste des lignes à mettre à jour et nouvelles entrées
			List<Ligne> lignesToUpdate = new ArrayList<>();
			// Liste des lignes à potentiellement supprimer
			Map<String, Ligne> lignesToUnpublish = LigneLocalServiceUtil.getAll();

			for (Route route : RouteLocalServiceUtil.getAllRoutes()) {

				// Si elle existe deja en base et qu'elle est toujours d'actualite dans le GTFS,
				// on la retire de ceux a supprimer et on recupere en meme temps l'element voulu
				Ligne ligne = lignesToUnpublish.remove(route.getRoute_id());

				// Si la ligne existe, on verifie les informations et on les met à jour si differentes
				if (ligne != null) {
					boolean edition = false;
					if (!ligne.getShortName().equals(route.getRoute_short_name())) {
						ligne.setShortName(route.getRoute_short_name());
						edition = true;
						this.importHistoric.addNewOperation(
								"Route n. " + route.getRoute_id() + " : change short name for '" + route.getRoute_short_name() + "'"
						);
					}
					if (!ligne.getTitle().equals(route.getRoute_long_name())) {
						ligne.setTitle(route.getRoute_long_name());
						edition = true;
						this.importHistoric.addNewOperation(
								"Route n. " + route.getRoute_id() + " : change long name for '" + route.getRoute_long_name() + "'"
						);
					}
					if (ligne.getType() != route.getRoute_type()) {
						ligne.setType(route.getRoute_type());
						edition = true;
						this.importHistoric.addNewOperation(
								"Route n. " + route.getRoute_id() + " : change type for '" + route.getRoute_type() + "'"
						);
					}
					if (!ligne.getBackgroundColor().equals(route.getRoute_color())) {
						ligne.setBackgroundColor(route.getRoute_color());
						edition = true;
						this.importHistoric.addNewOperation(
								"Route n. " + route.getRoute_id() + " : change background color for '" + route.getRoute_color() + "'"
						);
					}
					if (!ligne.getTextColor().equals(route.getRoute_text_color())) {
						ligne.setTextColor(route.getRoute_text_color());
						edition = true;
						this.importHistoric.addNewOperation(
								"Route n. " + route.getRoute_id() + " : change text color for '" + route.getRoute_text_color() + "'"
						);
					}
					// Verification du statut et changement si reaparition
					if (ligne.isDraft()) {
						// L'action par defaut d'un ServiceContext est la publication, mettre a jour l'entree suffit donc
						edition = true;
						nbRepublishedLines++;
						this.importHistoric.addNewOperation(
								"Route n. " + route.getRoute_id() + " : going to be republished"
						);
					}

					// Si la ligne a subit une modification, on l'ajoute dans la liste des lignes a sauvegarder
					if (edition) {
						lignesToUpdate.add(ligne);
						nbUpdatedLines++;
					}

				}
				// Sinon, on cree une nouvelle ligne
				else {
					// Creation de la ligne vide
					ligne = LigneLocalServiceUtil.createLigne(this.sc);
					// Completion des informations
					ligne.setRouteId(route.getRoute_id());
					ligne.setShortName(route.getRoute_short_name());
					ligne.setTitle(route.getRoute_long_name());
					ligne.setType(route.getRoute_type());
					ligne.setBackgroundColor(route.getRoute_color());
					ligne.setTextColor(route.getRoute_text_color());

					this.importHistoric.addNewOperation(
							"New route detected  --> [ " +
									"id : " + route.getRoute_id() +
									", short name : " + route.getRoute_short_name() +
									", long name : " + route.getRoute_long_name() +
									", type : " + route.getRoute_type() +
									", background color : " + route.getRoute_color() +
									", text color : " + route.getRoute_text_color() + "]"
					);

					lignesToUpdate.add(ligne);
					nbNewLines++;
				}

			}

			// Mettre à jour les lignes existantes et sauvegarder les nouvelles
			// Notes : Elles sont save ici puisqu'une recherche sur ces dernieres est effectuee
			// 			directement apres.
			LigneLocalServiceUtil.updateLignes(lignesToUpdate, this.sc);

			/**
			 * Import des directions
			 */
			Timestamp timestamp3 = new Timestamp(System.currentTimeMillis());
			this.importHistoric.addNewOperation("#3/7# Starting direction conversion");

			// Liste des directions à mettre à jour et nouvelles entrées
			List <Direction> directionsToSave = new ArrayList<>();
			// Liste des lignes à supprimer
			List <Direction> directionsToRemove = DirectionLocalServiceUtil.getAll();

			// Récupération des catégories du vocabulaire Type d'arret
			AssetVocabulary vocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.ARRET_TYPE);
			AssetCategory busCateg = null, tramCateg = null;
			if(Validator.isNotNull(vocabulary)) {
				// Récupération des catégories du vocabulaire
				List<AssetCategory> categories = vocabulary
						.getCategories();

				// Récupère les catégories
				for (AssetCategory category : categories) {
					switch (AssetVocabularyHelper
							.getCategoryProperty(
									category.getCategoryId(), "TypeCTS")) {
						case "0": {
							tramCateg = category;
							break;
						}
						case "3": {
							busCateg = category;
							break;
						}
					}
				}
			}

			Timestamp timestamp3_sql;
			Timestamp timestamp3_dir;
			long sql_time = 0;
			long dir_time = 0;
			int stopNumber = 0;
			// Parcours des arrets pour trouver les lignes correspondantes
			for (Stop stop : StopLocalServiceUtil.getAllStops()) {
				stopNumber++;
				timestamp3_sql = new Timestamp(System.currentTimeMillis());
				List <Trip> trips = TripLocalServiceUtil.getTripAvailableForStop(stop.getStop_id());
				sql_time += System.currentTimeMillis() - timestamp3_sql.getTime();

				timestamp3_dir = new Timestamp(System.currentTimeMillis());
				int tripIndex = 0;

				for (Trip trip: trips) {

					tripIndex++;

					// Creation du stop vide
					Direction direction = DirectionLocalServiceUtil.createDirection(this.sc);
					// Completion des informations
					direction.setTripId(trip.getTrip_id());
					direction.setStopId(stop.getStop_id());
					direction.setRouteId(trip.getRoute_id());
					direction.setDestinationName(trip.getTrip_headsign());

					/*
					this.importHistoric.addNewOperation(
						"New link with direction detected  --> [ " +
								"id : " + trip.getTrip_id() +
								", stop id : " + stop.getStop_id() +
								", ligne id : " + trip.getRoute_id() +
								", destination headsign : " + trip.getTrip_headsign() + "]"
					);
					 */

					directionsToSave.add(direction);

					// On en profite pour mettre à jour le type de l'arret s'il est dans la liste d'edition
					// Operation à ne faire q'une fois
					if (tripIndex == 1) {
						Arret correspondingArret = arretsToUpdate.stream()
								.filter(arret -> stop.getStop_id().equals(arret.getStopId()))
								.findAny()
								.orElse(null);
						if (correspondingArret != null) {
							// On recupere la ligne de la direction pour obtenir le type de ligne
							Ligne ligne = LigneLocalServiceUtil.getByRouteId(direction.getRouteId());

							if (ligne != null) {
								// créer un nouveau SC
								ServiceContext scArret = new ServiceContext();
								scArret.setScopeGroupId(this.sc.getScopeGroupId());
								scArret.setCompanyId(this.sc.getCompanyId());
								scArret.setUserId(this.sc.getUserId());
								scArret.setWorkflowAction(this.sc.getWorkflowAction());
								scArret.setModifiedDate(new Date());

								// changer type d'arret
								correspondingArret.setType(ligne.getType());

								// ajout de la catégorie Bus/tram
								long categoryId = -1;
								switch (ligne.getType()) {
									case 0: {
										categoryId = Validator.isNotNull(tramCateg)?tramCateg.getCategoryId():-1;
										break;
									}
									case 3: {
										categoryId = Validator.isNotNull(busCateg)?busCateg.getCategoryId():-1;
										break;
									}
								}
								scArret.setAssetCategoryIds(new long[]{categoryId});

								// Mettre à jour l'arret
								ArretLocalServiceUtil.updateArret(correspondingArret, scArret);

							}

						}
					}
				}
				dir_time += System.currentTimeMillis() - timestamp3_dir.getTime();

			}

			// Supprimer les arrets non parcourus
			Timestamp timestamp4 = new Timestamp(System.currentTimeMillis());
			this.importHistoric.addNewOperation("#4/7# Unpublish removed stop");
			ArretLocalServiceUtil.unpublishArrets(
					new ArrayList<>(arretsToUnpublish.values()),
					this.importHistoric,
					this.sc
			);

			// Supprimer les lignes non parcourues
			Timestamp timestamp5 = new Timestamp(System.currentTimeMillis());
			this.importHistoric.addNewOperation("#5/7# Unpublish removed route");
			// On supprime de la liste les lignes deja depubliees
			List<Ligne> lignesToCheckStatus = new ArrayList<>(lignesToUnpublish.values());
			for (Ligne ligne : lignesToCheckStatus) {
				if (ligne.isDraft()) {
					lignesToUnpublish.remove(ligne.getRouteId());
				}
			}
			LigneLocalServiceUtil.unpublishLignes(
					new ArrayList<>(lignesToUnpublish.values()),
					this.importHistoric,
					this.sc
			);

			// Sauvegarder les nouvelles directions
			Timestamp timestamp6 = new Timestamp(System.currentTimeMillis());
			this.importHistoric.addNewOperation("#6/7# Remove old direction links");
			DirectionLocalServiceUtil.removeDirections(directionsToRemove);

			// Supprimer les directions non parcourues
			Timestamp timestamp7 = new Timestamp(System.currentTimeMillis());
			this.importHistoric.addNewOperation("#7/7# Add new direction links");
			DirectionLocalServiceUtil.updateDirections(directionsToSave, this.sc);

			/**
			 * Data conversion debrief
			 */
			this.importHistoric.addNewOperation("################### Final data debrief ###################");

			this.importHistoric.addNewOperation("Nb. new stops : " + nbNewStops);
			this.importHistoric.addNewOperation("Nb. updated stops : " + nbUpdatedStops);
			this.importHistoric.addNewOperation("Nb. unpublished stops : " + arretsToUnpublish.size());
			this.importHistoric.addNewOperation("Nb. republished stops : " + nbRepublishedStops);
			this.importHistoric.addNewOperation("Nb. new routes : " + nbNewLines);
			this.importHistoric.addNewOperation("Nb. updated routes : " + nbUpdatedLines);
			this.importHistoric.addNewOperation("Nb. unpublished routes : " + lignesToUnpublish.size());
			this.importHistoric.addNewOperation("Nb. republished routes : " + nbRepublishedLines);
			this.importHistoric.addNewOperation("Nb. new direction links : " + directionsToSave.size());
			this.importHistoric.addNewOperation("Nb. removed direction links : " + directionsToRemove.size());

			Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
			long processTime = (endTimestamp.getTime() - startTimestamp.getTime()) / 1000;
			this.importHistoric.addNewOperation("Finishing files data conversion in " + processTime + " seconds.");
			this.importHistoric.addNewOperation("Time for Import des arrets:" + (timestamp2.getTime() - startTimestamp.getTime()));
			this.importHistoric.addNewOperation("Time for Import des lignes:" + (timestamp3.getTime() - timestamp2.getTime()));
			this.importHistoric.addNewOperation("Time for requete SQL:" + (sql_time / 1000 )+ " seconds, avg request time (ms):"+(sql_time/stopNumber));
			this.importHistoric.addNewOperation("Time for creation directions:" + (dir_time / 1000 )+ " seconds, avg direction time (ms):"+(dir_time/stopNumber));
			this.importHistoric.addNewOperation("Time for mise a jour directions:" + (timestamp4.getTime() - timestamp3.getTime()));
			this.importHistoric.addNewOperation("Time for Depublication des arrets:" + (timestamp5.getTime() - timestamp4.getTime()));
			this.importHistoric.addNewOperation("Time for Depublication des lignes:" + (timestamp6.getTime() - timestamp5.getTime()));
			this.importHistoric.addNewOperation("Time for suppression directions:" + (timestamp7.getTime() - timestamp6.getTime()));
			this.importHistoric.addNewOperation("Time for ajouts directions:" + (endTimestamp.getTime() - timestamp7.getTime()));

			// Succes de l'import
			this.importHistoric.setResult(1);

		} catch (PortalException e) {
			this.importHistoric.setErrorDescription("Probleme survenu lors de la convertion des donnees GTFS");
			this.importHistoric.setErrorStackTrace(e.toString());
			this.importHistoric.setResult(0);
			log.error(e);
		}

	}

	public ImportHistoric getImportHistoric() {
		return importHistoric;
	}

	public void setImportHistoric(ImportHistoric importHistoric) {
		this.importHistoric = importHistoric;
	}

	public File getGTFSData()
			throws IOException {
		File gtfs_file = null;
		String fileURL = GTFS_URL;
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setConnectTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
		httpConn.setReadTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
		int responseCode = httpConn.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {

			InputStream inputStream = httpConn.getInputStream();
			gtfs_file = FileUtil.createTempFile(inputStream);

		} else {
			log.warn("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
		return gtfs_file;
	}

}
