package eu.strasbourg.service.gtfs.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.model.Direction;
import eu.strasbourg.service.gtfs.model.ImportHistoric;
import eu.strasbourg.service.gtfs.model.Ligne;
import eu.strasbourg.service.gtfs.model.Route;
import eu.strasbourg.service.gtfs.model.Stop;
import eu.strasbourg.service.gtfs.model.Trip;
import eu.strasbourg.service.gtfs.service.AgencyLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.CalendarDateLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.CalendarLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.DirectionLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.ImportHistoricLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.LigneLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.RouteLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.StopLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.StopTimeLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.TripLocalServiceUtil;
import eu.strasbourg.utils.GTFSLoaderHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.exception.FileAccessException;
import eu.strasbourg.utils.models.AgencyGTFS;
import eu.strasbourg.utils.models.CalendarDatesGTFS;
import eu.strasbourg.utils.models.CalendarGTFS;
import eu.strasbourg.utils.models.RoutesGTFS;
import eu.strasbourg.utils.models.StopTimesGTFS;
import eu.strasbourg.utils.models.StopsGTFS;
import eu.strasbourg.utils.models.TripsGTFS;

public class GTFSImporter {
	
	public final static Log log = LogFactoryUtil.getLog(GTFSImporter.class);
	
	private ServiceContext sc;
	private ImportHistoric importHistoric;

	public GTFSImporter (ServiceContext sc) {
		try {
			this.sc = sc;
			// Creation d'une entree d'historique d'import permettant de sauvegarder tous les elements
			// important relatif a l'import effectue (operations, resultat, erreur, etc)
			this.importHistoric = ImportHistoricLocalServiceUtil.createImportHistoric(this.sc);
			
		} catch (PortalException e) {
			log.error(e);
		}
	}
	
	/**
	 * Opere l'import
	 */
	public void doImport() {
		// Import des donnees du flux
		importGTFSData();
		
		// Convertion des donnees du flux vers des entitees liferay affichables
		convertGTFSData();
		
		// Sauvegarde de l'entree d'historique d'import
		ImportHistoricLocalServiceUtil.updateImportHistoric(this.importHistoric);
	}
	
	/**
	 * Recupere le GTFS et importe les donnees dans les tables tempons de ce service
	 */
	private void importGTFSData() {
		// Recuperation du chemin absolu vers les fichiers du GTFS
		String GTFSPath = StrasbourgPropsUtil.getGTFSPath();
		
		try {
			Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());
			this.importHistoric.addNewOperation(
					"################### GTFS Files ################### \n" +
					"#1/1# Starting import of GTFS"
			);
			
			// Recuperation des lignes
			Map<String, AgencyGTFS> mapAgencys;
			mapAgencys = GTFSLoaderHelper.readAgencyData(GTFSPath);
			AgencyLocalServiceUtil.importFromGTFS(mapAgencys);
			this.importHistoric.addNewOperation("Get " + mapAgencys.size() + " Agency entries");
			
			// Recuperation des calendrier
			Map<String, CalendarGTFS> mapCalendars;
			mapCalendars = GTFSLoaderHelper.readCalendarData(GTFSPath);
			CalendarLocalServiceUtil.importFromGTFS(mapCalendars);
			this.importHistoric.addNewOperation("Get " + mapCalendars.size() + " Calendar entries");
			
			// Recuperation des dates de calendrier
			Map<String, List<CalendarDatesGTFS>> mapCalendarDates;
			mapCalendarDates = GTFSLoaderHelper.readCalendarDatesData(GTFSPath);
			CalendarDateLocalServiceUtil.importFromGTFS(mapCalendarDates);
			this.importHistoric.addNewOperation("Get " + mapCalendarDates.size() + " CalendarDate keys");
			
			// Recuperation des lignes
			Map<String, RoutesGTFS> mapRoutes;
			mapRoutes = GTFSLoaderHelper.readRoutesData(GTFSPath);
			RouteLocalServiceUtil.importFromGTFS(mapRoutes);
			this.importHistoric.addNewOperation("Get " + mapRoutes.size() + " Route entries");
			
			// Recuperation des temps d'arrêt
			Map<String, List<StopTimesGTFS>> mapStopTimes;
			mapStopTimes = GTFSLoaderHelper.readStopTimesData(GTFSPath);
			StopTimeLocalServiceUtil.importFromGTFS(mapStopTimes);
			this.importHistoric.addNewOperation("Get " + mapStopTimes.size() + " StopTime keys");
			
			// Recuperation des routes
			Map<String, StopsGTFS> mapStops;
			mapStops = GTFSLoaderHelper.readStopsData(GTFSPath);
			StopLocalServiceUtil.importFromGTFS(mapStops);
			this.importHistoric.addNewOperation("Get " + mapStops.size() + " Stops entries");
			
			// Recuperation des voyages
			Map<String, TripsGTFS> mapTrips;
			mapTrips = GTFSLoaderHelper.readTripsData(GTFSPath);
			TripLocalServiceUtil.importFromGTFS(mapTrips);
			this.importHistoric.addNewOperation("Get " + mapTrips.size() + " Trips keys");
			
			Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
			long processTime = (endTimestamp.getTime() - startTimestamp.getTime()) / 1000;
			this.importHistoric.addNewOperation("Finishing files data import in " + processTime + " seconds.");
			
		} catch (FileAccessException | PortalException e) {
			this.importHistoric.setErrorDescription(e.toString());
			this.importHistoric.setResult(0);
			log.error(e);
		}
	}
	
	private void convertGTFSData() {
		
		Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());
		this.importHistoric.addNewOperation(
				"################### Convert GTFS data ###################"
		);
		
		try {
			/**
			 * Import des arrêts
			 */
			this.importHistoric.addNewOperation("#1/6# Starting stops conversion");
			
			// Liste des arrets à mettre à jour et nouvelles entrées
			List<Arret> arretsToUpdate = new ArrayList<Arret>();
			// Liste des arrets à potentiellement supprimer
			Map<String, Arret> arretsToRemove = ArretLocalServiceUtil.getAll();
			
			for (Stop stop : StopLocalServiceUtil.getAllStops()) {
				
				// Si il existe deja en base et qu'il est toujours d'actualite dans le GTFS, 
				// on le retire de ceux a supprimer et on recupere en meme temps l'element voulu
				Arret arret = arretsToRemove.remove(stop.getStop_id());
				
				// Si l'arret existe, on verifie les informations et on les met à jour si differentes
				if (arret != null) {
					boolean edition = false;
					if (!arret.getTitle().equals(stop.getStop_name())) {
						arret.setTitle(stop.getStop_name());
						edition = true;
						this.importHistoric.addNewOperation(
							"Stop n°" + stop.getStop_id() + " : change name for '" + stop.getStop_name() + "'"
						);
					}
					if (!arret.getCode().equals(stop.getStop_code())) {
						arret.setCode(stop.getStop_code());
						edition = true;
						this.importHistoric.addNewOperation(
							"Stop n°" + stop.getStop_id() + " : change code for '" + stop.getStop_code() + "'"
						);
					}
					if (!arret.getLatitude().equals(stop.getStop_lat())) {
						arret.setLatitude(stop.getStop_lat());
						edition = true;
						this.importHistoric.addNewOperation(
							"Stop n°" + stop.getStop_id() + " : change latitude for '" + stop.getStop_lat() + "'"
						);
					}
					if (!arret.getLongitude().equals(stop.getStop_lon())) {
						arret.setLongitude(stop.getStop_lon());
						edition = true;
						this.importHistoric.addNewOperation(
							"Stop n°" + stop.getStop_id() + " : change longitude for '" + stop.getStop_lon() + "'"
						);
					}
					// Si l'arret a subit une modification, on l'ajoute dans la liste des arrets a sauvegarder
					if (edition) {
						arretsToUpdate.add(arret);
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
				}
				
			}
		
			/**
			 * Import des lignes
			 */
			this.importHistoric.addNewOperation("#1/6# Starting routes conversion");
						
			// Liste des lignes à mettre à jour et nouvelles entrées
			List<Ligne> lignesToUpdate = new ArrayList<Ligne>();
			// Liste des lignes à potentiellement supprimer
			Map<String, Ligne> lignesToRemove = LigneLocalServiceUtil.getAll();
			
			for (Route route : RouteLocalServiceUtil.getAllRoutes()) {
				
				// Si elle existe deja en base et qu'elle est toujours d'actualite dans le GTFS, 
				// on la retire de ceux a supprimer et on recupere en meme temps l'element voulu
				Ligne ligne = lignesToRemove.remove(route.getRoute_id());
				
				// Si la ligne existe, on verifie les informations et on les met à jour si differentes
				if (ligne != null) {
					boolean edition = false;
					if (!ligne.getShortName().equals(route.getRoute_short_name())) {
						ligne.setShortName(route.getRoute_short_name());
						edition = true;
						this.importHistoric.addNewOperation(
							"Route n°" + route.getRoute_id() + " : change short name for '" + route.getRoute_short_name() + "'"
						);
					}
					if (!ligne.getTitle().equals(route.getRoute_long_name())) {
						ligne.setTitle(route.getRoute_long_name());
						edition = true;
						this.importHistoric.addNewOperation(
							"Route n°" + route.getRoute_id() + " : change long name for '" + route.getRoute_long_name() + "'"
						);
					}
					if (ligne.getType() != route.getRoute_type()) {
						ligne.setType(route.getRoute_type());
						edition = true;
						this.importHistoric.addNewOperation(
							"Route n°" + route.getRoute_id() + " : change type for '" + route.getRoute_type() + "'"
						);
					}
					if (!ligne.getBackgroundColor().equals(route.getRoute_color())) {
						ligne.setBackgroundColor(route.getRoute_color());
						edition = true;
						this.importHistoric.addNewOperation(
							"Route n°" + route.getRoute_id() + " : change background color for '" + route.getRoute_color() + "'"
						);
					}
					if (!ligne.getTextColor().equals(route.getRoute_text_color())) {
						ligne.setTextColor(route.getRoute_text_color());
						edition = true;
						this.importHistoric.addNewOperation(
							"Route n°" + route.getRoute_id() + " : change text color for '" + route.getRoute_text_color() + "'"
						);
					}
					// Si la ligne a subit une modification, on l'ajoute dans la liste des lignes a sauvegarder
					if (edition) {
						lignesToUpdate.add(ligne);
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
				}
				
			}

			/**
			 * Import des directions
			 */
			this.importHistoric.addNewOperation("#3/6# Starting routes conversion");
			
			// Liste des directions à mettre à jour et nouvelles entrées
			List <Direction> directionsToSave = new ArrayList<Direction>();
			// Liste des lignes à potentiellement supprimer
			Map<String, Direction> directionsToRemove = DirectionLocalServiceUtil.getAll();
			
			// Parcours des arrets pour trouver les lignes correspondantes
			for (Stop stop : StopLocalServiceUtil.getAllStops()) {
				
				List <Trip> trips = TripLocalServiceUtil.getTripAvailableForStop(stop.getStop_id());
				
				for (Trip trip: trips) {
					
					// Si il existe deja en base et qu'il est toujours d'actualite dans le GTFS, 
					// on le retire de ceux a supprimer et on recupere en meme temps l'element voulu
					Direction direction = directionsToRemove.remove(trip.getTrip_id());
					
					// Si la direction existe, rien a faire sinon on la creer
					if (direction == null) {
						// Creation du stop vide
						direction = DirectionLocalServiceUtil.createDirection(this.sc);
						// Completion des informations
						direction.setTripId(trip.getTrip_id());
						direction.setStopId(stop.getStop_id());
						direction.setRouteId(trip.getRoute_id());
						direction.setDestinationName(trip.getTrip_headsign());
						
						this.importHistoric.addNewOperation(
							"New link with direction detected  --> [ " +
									"id : " + trip.getTrip_id() + 
									", stop id : " + stop.getStop_id() + 
									", ligne id : " + trip.getRoute_id() + 
									", destination headsign : " + trip.getTrip_headsign() + "]"
						);
						
						directionsToSave.add(direction);
					}
				}
			}
			
			/**
			 * Time to work with DB !
			 */
			// Mettre à jour les arrets existants et sauvegarder les nouveaux
			ArretLocalServiceUtil.updateArrets(arretsToUpdate, this.sc);
			// Supprimer les arrets non parcourus
			this.importHistoric.addNewOperation("#4/6# Unpublish removed stop");
			ArretLocalServiceUtil.unpublishArrets(new ArrayList<Arret>(arretsToRemove.values()), this.importHistoric, this.sc);
			
			// Mettre à jour les lignes existantes et sauvegarder les nouvelles
			LigneLocalServiceUtil.updateLignes(lignesToUpdate, this.sc);
			// Supprimer les lignes non parcourues
			this.importHistoric.addNewOperation("#5/6# Unpublish removed route");
			LigneLocalServiceUtil.unpublishLignes(new ArrayList<Ligne>(lignesToRemove.values()), this.importHistoric, this.sc);
			
			// Sauvegarder les nouvelles directions
			DirectionLocalServiceUtil.updateDirections(directionsToSave, this.sc);
			// Supprimer les directions non parcourues
			this.importHistoric.addNewOperation("#6/6# Remove obsolet direction link");
			DirectionLocalServiceUtil.removeDirections(new ArrayList<Direction>(directionsToRemove.values()),this.importHistoric, this.sc);
			
		} catch (PortalException e) {
			this.importHistoric.setErrorDescription(e.toString());
			this.importHistoric.setResult(0);
			log.error(e);
		}
		
		Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
		long processTime = (endTimestamp.getTime() - startTimestamp.getTime()) / 1000;
		this.importHistoric.addNewOperation("Finishing files data conversion in " + processTime + " seconds.");
	}
	
	public ImportHistoric getImportHistoric() {
		return importHistoric;
	}

	public void setImportHistoric(ImportHistoric importHistoric) {
		this.importHistoric = importHistoric;
	}
	
}
