package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.place.model.CacheJson;
import eu.strasbourg.service.place.model.Historic;
import eu.strasbourg.service.place.service.CacheJsonLocalServiceUtil;
import eu.strasbourg.service.place.service.HistoricLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
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
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/csmap-ws/place",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=CSMAP.Place.Rest",
		"auth.verifier.guest.allowed=true",
		"liferay.access.control.disable=true"
	},
	service = Application.class
)
public class PlaceApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.singleton(this);
	}

	@GET
	@Path("/get-places/{date}")
	public String getPlaces(
			@PathParam("date") Date date) {

		// On vérifie que la date est renseignée
		if (Validator.isNull(date))
			return WSResponseUtil.initializeError("Il manque la date").toString();

		JSONObject json = WSResponseUtil.initializeResponse();
		try {
			// On récupère tous les lieux qui ont été ajoutés
			List<CacheJson> ajouts = CacheJsonLocalServiceUtil.getByCreatedDateAndIsActive(date);
			JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
			for (CacheJson cache: ajouts) {
				jsonAjout.put(JSONFactoryUtil.createJSONObject(cache.getJsonLieu()));
			}
			json.put(WSConstants.JSON_ADD, jsonAjout);

			// On récupère tous les lieux qui ont été modifiés
			List<CacheJson> modifications = CacheJsonLocalServiceUtil.getByCreatedDateAndModifiedDateAndIsActive(date);
			JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
			for (CacheJson cache: modifications) {
				jsonModif.put(JSONFactoryUtil.createJSONObject(cache.getJsonLieu()));
			}
			json.put(WSConstants.JSON_UPDATE, jsonModif);

			JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
			// On récupère tous les lieux qui ont été dépubliés
			List<CacheJson> depubications = CacheJsonLocalServiceUtil.getByModifiedDateAndIsNotActive(date);
			for (CacheJson cache: depubications) {
				jsonSuppr.put(cache.getSigId());
			}

			// On récupère tous les lieux qui ont été supprimés
			List<Historic> suppressions = HistoricLocalServiceUtil.getBySuppressionDate(date);
			for (Historic histo: suppressions) {
				jsonSuppr.put(histo.getSigId());
			}
			json.put(WSConstants.JSON_DELETE, jsonSuppr);
		} catch (JSONException e) {
			e.printStackTrace();
			return WSResponseUtil.initializeError(e.getMessage()).toString();
		}

		return json.toString();
	}

	@GET
	@Path("/get-hours/{sigid}")
	public String getHours(
			@PathParam("sigid") String sigid) {

		// On vérifie que le sigid est renseigné
		if (Validator.isNull(sigid))
			return WSResponseUtil.initializeError("Il manque le sigid").toString();

		JSONObject json;
		try {
			// On récupère les horaires du lieu
			CacheJson cache = CacheJsonLocalServiceUtil.fetchCacheJson(sigid);
			if(Validator.isNull(cache))
				return WSResponseUtil.initializeError("Lieu introuvable dans le cache").toString();

			if(!cache.getIsActive())
				return WSResponseUtil.initializeError("Lieu inactif").toString();

			json = JSONFactoryUtil.createJSONObject(cache.getJsonHoraire());
			json.put(WSConstants.JSON_RESPONSE_CODE, 201);
		} catch (JSONException e) {
			e.printStackTrace();
			return WSResponseUtil.initializeError(e.getMessage()).toString();
		}
		return json.toString();
	}

	@GET
	@Path("/get-categories/{date}/{ids}")
	public String getCategories(
			@PathParam("date") Date date,
			@PathParam("ids") String ids) {

		// On vérifie que la date est renseignée
		if (Validator.isNull(date))
			return WSResponseUtil.initializeError("Il manque la date").toString();

		// On vérifie que les ids sont renseignés
		if (Validator.isNull(ids))
			return WSResponseUtil.initializeError("Il manque le ids").toString();

		JSONObject json = WSResponseUtil.initializeResponse();
		try {
			// On récupère les pictos du vocabulaire
			Map<String, DLFileEntry> pictos = FileEntryHelper.getPictoForVocabulary(VocabularyNames.PLACE_TYPE, "CSMap");
			// On récupère l'URL du picto par défaut
			String pictoDefaultURL = "";
			DLFileEntry picto = pictos.get("Defaut");
			if(picto != null)
				pictoDefaultURL = FileEntryHelper.getFileEntryURL(picto);

			// On récupère les catégories du vocabulaire des lieux
			AssetVocabulary placeTypeVocabulary = AssetVocabularyHelper
					.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
			if(Validator.isNull(placeTypeVocabulary))
				return WSResponseUtil.initializeError("Pas de vocabulaire " + VocabularyNames.PLACE_TYPE).toString();
			List<AssetCategory> categories = placeTypeVocabulary.getCategories();

			// On récupère toutes les catégories qui ont été ajoutées ou modifiées
			JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
			JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
			for (AssetCategory categ: categories) {
				// récupère l'URL du picto de la catégorie
				String pictoURL = "";
				picto = pictos.get(AssetVocabularyHelper.getCategoryProperty(categ.getCategoryId(),"SIG"));
				if(picto != null)
					pictoURL = FileEntryHelper.getFileEntryURL(picto);
				else
					pictoURL = pictoDefaultURL;
				if(date.before(categ.getCreateDate()))
					jsonAjout.put(AssetVocabularyHelper.categoryCSMapJSON(categ, pictoURL, false));
				else{
					if(date.before(categ.getModifiedDate()) || (picto != null && date.before(picto.getModifiedDate())))
						jsonModif.put(AssetVocabularyHelper.categoryCSMapJSON(categ, pictoURL, false));
				}
			}
			json.put(WSConstants.JSON_ADD, jsonAjout);
			json.put(WSConstants.JSON_UPDATE, jsonModif);

			// On récupère toutes les catégories qui ont été supprimées
			JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
			JSONArray idsJson = JSONFactoryUtil.createJSONArray(ids);
			for (int i = 0; i < idsJson.length(); i++) {
				if(AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary, idsJson.get(i).toString()) == null)
					jsonSuppr.put(idsJson.get(i));
			}
			json.put(WSConstants.JSON_DELETE, jsonSuppr);
		} catch (PortalException e) {
			e.printStackTrace();
			return WSResponseUtil.initializeError(e.getMessage()).toString();
		}
		return json.toString();
	}

}