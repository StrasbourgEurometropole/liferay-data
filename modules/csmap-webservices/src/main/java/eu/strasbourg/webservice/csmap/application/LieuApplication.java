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
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/lieu",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Lieu.Rest",
		"auth.verifier.guest.allowed=true",
		"liferay.access.control.disable=true"

	},
	service = Application.class
)
public class LieuApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/{date}")
	public String getLieu(
			@PathParam("date") Date date) {
		JSONObject json = JSONFactoryUtil.createJSONObject();

		try {
			// récupère tous les lieux qui ont été ajoutés
			List<CacheJson> ajouts = CacheJsonLocalServiceUtil.getByCreatedDateAndIsActive(date);
			JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
			for (CacheJson cache: ajouts) {
				jsonAjout.put(JSONFactoryUtil.createJSONObject(cache.getJsonLieu()));
			}
			json.put("ADD", jsonAjout);

			// récupère tous les lieux qui ont été modifiés
			List<CacheJson> modifications = CacheJsonLocalServiceUtil.getByCreatedDateAndModifiedDateAndIsActive(date);
			JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
			for (CacheJson cache: modifications) {
				jsonModif.put(JSONFactoryUtil.createJSONObject(cache.getJsonLieu()));
			}
			json.put("UPDATE", jsonModif);

			JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
			// récupère tous les lieux qui ont été dépubliés
			List<CacheJson> depubications = CacheJsonLocalServiceUtil.getByModifiedDateAndIsNotActive(date);
			for (CacheJson cache: depubications) {
				jsonSuppr.put(cache.getSigId());
			}

			// récupère tous les lieux qui ont été supprimés
			List<Historic> suppressions = HistoricLocalServiceUtil.getBySuppressionDate(date);
			for (Historic histo: suppressions) {
				jsonSuppr.put(histo.getSigId());
			}
			json.put("DELETE", jsonSuppr);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json.toString();
	}

	@GET
	@Path("/horaires/{sigid}")
	public String getHoraires(
			@PathParam("sigid") String sigid) {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		try {
			CacheJson cache = CacheJsonLocalServiceUtil.fetchCacheJson(sigid);
			if(Validator.isNotNull(cache) && cache.getIsActive()) {
				json = JSONFactoryUtil.createJSONObject(cache.getJsonHoraire());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	@GET
	@Path("/category/{date}/{ids}")
	public String getCategoryMap(
			@PathParam("date") Date date,
			@PathParam("ids") String ids) {
		JSONObject json = JSONFactoryUtil.createJSONObject();

		Map<String, DLFileEntry> pictos = FileEntryHelper.getPictoForVocabulary(VocabularyNames.PLACE_TYPE, "CSMap");
		DLFileEntry picto = pictos.get("Defaut");
		String pictoDefaultURL = "";
		if(picto == null)
			pictoDefaultURL = FileEntryHelper.getFileEntryURL(picto);

		try {
			AssetVocabulary placeTypeVocabulary = AssetVocabularyHelper
					.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
			List<AssetCategory> categories = placeTypeVocabulary.getCategories();

			JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
			JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
			for (AssetCategory categ: categories) {
				// récupère le picto
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
			json.put("ADD", jsonAjout);
			json.put("UPDATE", jsonModif);

			// récupère toutes les catégories qui ont été supprimées
			JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
			JSONArray idsJson = JSONFactoryUtil.createJSONArray(ids);
			for (int i = 0; i < idsJson.length(); i++) {
				if(AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary, idsJson.get(i).toString()) == null)
					jsonSuppr.put(idsJson.get(i));
			}
			json.put("DELETE", jsonSuppr);
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

}