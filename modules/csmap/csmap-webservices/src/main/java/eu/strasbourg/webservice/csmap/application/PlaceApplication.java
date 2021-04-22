package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.place.model.CacheJson;
import eu.strasbourg.service.place.model.Historic;
import eu.strasbourg.service.place.service.CacheJsonLocalServiceUtil;
import eu.strasbourg.service.place.service.HistoricLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.place.NoDefaultPictoException;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author angelique.champougny
 * @author jeremy.zwickert
 * @author cédric.henry
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_PLACE_BASE,
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_PLACE_NAME,
		"auth.verifier.guest.allowed=true",
		"liferay.access.control.disable=true"
	},
	service = Application.class
)
public class PlaceApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.singleton(this);
	}

	private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

	@GET
	@Produces("application/json")
	@Path("/get-places")
	public Response getPlaces() {
		return getPlaces("0");
	}

	@GET
	@Produces("application/json")
	@Path("/get-places/{last_update_time}")
	public Response getPlaces(
			@PathParam("last_update_time") String lastUpdateTimeString) {

		// On vérifie que lastUpdateTimeString est renseigné
		if (Validator.isNull(lastUpdateTimeString))
			return WSResponseUtil.buildErrorResponse(400,"Il manque le paramètre last_update_time");

		// On transforme la date string en date
		Date lastUpdateTime;
		try {
			long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
			lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
		}catch (Exception e) {
			return WSResponseUtil.buildErrorResponse(400,"Format de date incorrect");
		}

		JSONObject json = JSONFactoryUtil.createJSONObject();

		try {
			// On récupère tous les lieux qui ont été ajoutés
			List<CacheJson> ajouts = CacheJsonLocalServiceUtil.getByCreatedDateAndIsActive(lastUpdateTime);
			JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
			for (CacheJson cache: ajouts) {
				jsonAjout.put(JSONFactoryUtil.createJSONObject(cache.getJsonLieu()));
			}
			json.put(WSConstants.JSON_ADD, jsonAjout);

			// On récupère tous les lieux qui ont été modifiés
			List<CacheJson> modifications = CacheJsonLocalServiceUtil.getByCreatedDateAndModifiedDateAndIsActive(lastUpdateTime);
			JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
			for (CacheJson cache: modifications) {
				jsonModif.put(JSONFactoryUtil.createJSONObject(cache.getJsonLieu()));
			}
			json.put(WSConstants.JSON_UPDATE, jsonModif);

			JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
			// On récupère tous les lieux qui ont été dépubliés
			List<CacheJson> depubications = CacheJsonLocalServiceUtil.getByModifiedDateAndIsNotActive(lastUpdateTime);
			for (CacheJson cache: depubications) {
				jsonSuppr.put(cache.getSigId());
			}

			// On récupère tous les lieux qui ont été supprimés
			List<Historic> suppressions = HistoricLocalServiceUtil.getBySuppressionDate(lastUpdateTime);
			for (Historic histo: suppressions) {
				jsonSuppr.put(histo.getSigId());
			}
			json.put(WSConstants.JSON_DELETE, jsonSuppr);
		} catch (JSONException e) {
			log.error(e);
			return WSResponseUtil.buildErrorResponse(500, e.getMessage());
		}

		return WSResponseUtil.buildOkResponse(json);
	}

	@GET
	@Produces("application/json")
	@Path("/get-hours/{sigid}")
	public Response getHours(
			@PathParam("sigid") String sigid) {

		// On vérifie que le sigid est renseigné
		if (Validator.isNull(sigid))
			return WSResponseUtil.buildErrorResponse(400, "Il manque le sigid");

		JSONObject json = JSONFactoryUtil.createJSONObject();

		// On récupère le cache horaires du lieu
		CacheJson cache = CacheJsonLocalServiceUtil.fetchCacheJson(sigid);
		if(Validator.isNotNull(cache) && cache.getIsActive()) {
			try {
				json = JSONFactoryUtil.createJSONObject(cache.getJsonHoraire());
				json.put(WSConstants.JSON_RESPONSE_CODE, 200);
			} catch (JSONException e) {
				log.error(e);
				return WSResponseUtil.buildErrorResponse(500, e.getMessage());
			}
		}

		return WSResponseUtil.buildOkResponse(json);
	}

	@GET
	@Produces("application/json")
	@Path("/get-emergencies/{last_update_time}")
	public Response getEmergencies(@PathParam("last_update_time") String lastUpdateTimeString){

		// On vérifie que lastUpdateTimeString est renseigné
		if (Validator.isNull(lastUpdateTimeString))
			return WSResponseUtil.buildErrorResponse(400,
					"Il manque le paramètre last_update_time");

		// On transforme la date string en date
		Date lastUpdateTime;
		try {
			long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
			lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
		}catch (Exception e) {
			return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
		}

		JSONObject json = JSONFactoryUtil.createJSONObject();

		try {

			// On récupère les pictos du vocabulaire
			Map<String, DLFileEntry> pictos = FileEntryHelper.getPictoForVocabulary(VocabularyNames.PLACE_TYPE, "CSMap");

			// On récupère l'URL du picto par défaut
			String pictoDefaultURL = "";
			DLFileEntry picto = pictos.get("Defaut");
			if (Validator.isNull(picto))
				throw new NoDefaultPictoException();
			pictoDefaultURL = FileEntryHelper.getFileEntryURL(picto);

			// On récupère les catégories du vocabulaire des lieux
			AssetVocabulary placeTypeVocabulary = AssetVocabularyHelper
					.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
			List<AssetCategory> categories = new ArrayList<>();
			if(Validator.isNotNull(placeTypeVocabulary))
				categories = placeTypeVocabulary.getCategories();

			// On récupère toutes les catégories qui ont été ajoutées ou modifiées
			JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
			JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

			for (AssetCategory categ: categories) {
				// récupère l'URL du picto de la catégorie
				String pictoURL;
				picto = pictos.get(AssetVocabularyHelper.getCategoryProperty(categ.getCategoryId(),"SIG"));
				boolean updatePicto = false;

				if (picto != null) {
					pictoURL = FileEntryHelper.getFileEntryURL(picto);
					updatePicto = lastUpdateTime.before(picto.getModifiedDate());
				} else
					pictoURL = pictoDefaultURL;

				if (lastUpdateTime.before(categ.getCreateDate()))
					jsonAjout.put(AssetVocabularyHelper.categoryCSMapJSON(categ, pictoURL, true));
				else if (lastUpdateTime.before(categ.getModifiedDate()) || updatePicto)
					jsonAjout.put(AssetVocabularyHelper.categoryCSMapJSON(categ, pictoURL, updatePicto));

			}

			json.put(WSConstants.JSON_ADD, jsonAjout);
			json.put(WSConstants.JSON_UPDATE, jsonModif);

			// On récupère toutes les catégories qui ont été supprimées
			JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
			if(Validator.isNotNull(placeTypeVocabulary))
				for (int i = 0; i < idsJson.length(); i++) {
					if(AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary, idsJson.get(i).toString()) == null)
						jsonSuppr.put(idsJson.get(i));
				}
			json.put(WSConstants.JSON_DELETE, jsonSuppr);
		} catch (PortalException | NoDefaultPictoException e) {
			log.error(e);
			return WSResponseUtil.buildErrorResponse(500, e.getMessage());
		}

		return WSResponseUtil.buildOkResponse(json);
	}

	@GET
	@Produces("application/json")
	@Path("/get-categories/{last_update_time}/{ids_category}")
	public Response getCategories(
			@PathParam("last_update_time") String lastUpdateTimeString,
			@PathParam("ids_category") String ids) {

		// On vérifie que lastUpdateTimeString est renseigné
		if (Validator.isNull(lastUpdateTimeString))
			return WSResponseUtil.buildErrorResponse(400,
					"Il manque le paramètre last_update_time");

		// On transforme la date string en date
		Date lastUpdateTime;
		try {
			long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
			lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
		}catch (Exception e) {
			return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
		}

		// On vérifie que les ids sont renseignés
		if (Validator.isNull(ids))
			return WSResponseUtil.buildErrorResponse(400, "Il manque le paramètre ids_category");

		// On vérifie le format de ids_category
		JSONObject idsCategoryParam;
		JSONArray idsJson;
		try {
			idsCategoryParam = JSONFactoryUtil.createJSONObject(ids);
			idsJson = idsCategoryParam.getJSONArray(WSConstants.PARAM_IDS_CATEGORY);
		}catch (Exception e) {
			return WSResponseUtil.buildErrorResponse(400, "Format json de ids_category incorrect");
		}

		JSONObject json = JSONFactoryUtil.createJSONObject();

		try {
			// On récupère les pictos du vocabulaire
			Map<String, DLFileEntry> pictos = FileEntryHelper.getPictoForVocabulary(VocabularyNames.PLACE_TYPE, "CSMap");

			// On récupère l'URL du picto par défaut
			String pictoDefaultURL = "";
			DLFileEntry picto = pictos.get("Defaut");
			if (Validator.isNull(picto))
				throw new NoDefaultPictoException();
			pictoDefaultURL = FileEntryHelper.getFileEntryURL(picto);

			// On récupère les catégories du vocabulaire des lieux
			AssetVocabulary placeTypeVocabulary = AssetVocabularyHelper
					.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
			List<AssetCategory> categories = new ArrayList<>();
			if(Validator.isNotNull(placeTypeVocabulary))
				categories = placeTypeVocabulary.getCategories();

			// On récupère toutes les catégories qui ont été ajoutées ou modifiées
			JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
			JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

			for (AssetCategory categ: categories) {
				// récupère l'URL du picto de la catégorie
				String pictoURL;
				picto = pictos.get(AssetVocabularyHelper.getCategoryProperty(categ.getCategoryId(),"SIG"));
				boolean updatePicto = false;

				if (picto != null) {
					pictoURL = FileEntryHelper.getFileEntryURL(picto);
					updatePicto = lastUpdateTime.before(picto.getModifiedDate());
				} else
					pictoURL = pictoDefaultURL;

				if (lastUpdateTime.before(categ.getCreateDate()))
					jsonAjout.put(AssetVocabularyHelper.categoryCSMapJSON(categ, pictoURL, true));
				else if (lastUpdateTime.before(categ.getModifiedDate()) || updatePicto)
					jsonAjout.put(AssetVocabularyHelper.categoryCSMapJSON(categ, pictoURL, updatePicto));

			}

			json.put(WSConstants.JSON_ADD, jsonAjout);
			json.put(WSConstants.JSON_UPDATE, jsonModif);

			// On récupère toutes les catégories qui ont été supprimées
			JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
			if(Validator.isNotNull(placeTypeVocabulary))
				for (int i = 0; i < idsJson.length(); i++) {
					if(AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary, idsJson.get(i).toString()) == null)
						jsonSuppr.put(idsJson.get(i));
				}
			json.put(WSConstants.JSON_DELETE, jsonSuppr);
		} catch (PortalException | NoDefaultPictoException e) {
			log.error(e);
			return WSResponseUtil.buildErrorResponse(500, e.getMessage());
		}

		return WSResponseUtil.buildOkResponse(json);
	}

}