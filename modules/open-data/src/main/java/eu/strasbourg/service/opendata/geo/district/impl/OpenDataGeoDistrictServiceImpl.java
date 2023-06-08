package eu.strasbourg.service.opendata.geo.district.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.opendata.geo.address.OpenDataGeoAddressService;
import eu.strasbourg.service.opendata.geo.district.OpenDataGeoDistrictService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Angélique ZUNINO CHAMPOUGNY
 */
@Component(immediate = true, property = {}, service = OpenDataGeoDistrictService.class)
public class OpenDataGeoDistrictServiceImpl implements OpenDataGeoDistrictService {

    private Log log = LogFactoryUtil.getLog(this.getClass());

    /**
     * Récupère l'URL du endPoint pour les quartiers
     */
    private String getDistrictsURL() {
        String openDataBaseURL = StrasbourgPropsUtil.getOpenDataBaseURL();
        String districtURL = openDataBaseURL + StrasbourgPropsUtil.getOpenDataDistricts();

        return districtURL;
    }

    /**
     * Récupère le json de la réponse de l'URL
     */
    private JSONArray getRecord(String url) throws Exception {
        return getRecord(url, StrasbourgPropsUtil.getWebServiceDefaultTimeout());
    }

    /**
     * Récupère le json de la réponse de l'URL
     */
    private JSONArray getRecord(String url, int timeOut) throws Exception {
        JSONObject response = JSONHelper.readJsonFromURL(url, timeOut);
        JSONArray records = response.getJSONArray("records");

        return records;
    }

    /**
     * Retourne la catégorie du quartier de l'utilisateur
     */
    @Override
    public AssetCategory getDistrictByAddress(String address, String zipCode, String city) throws Exception {
        return getDistrictByAddress(address, zipCode, city, StrasbourgPropsUtil.getWebServiceDefaultTimeout()) ;
    }

    /**
     * Retourne la catégorie du quartier de l'utilisateur
     */
    @Override
    public AssetCategory getDistrictByAddress(String address, String zipCode, String city, int timeOut) throws Exception {
        AssetCategory district = null;
        JSONArray coordinates = openDataGeoAddressService.getCoordinateForAddress(address, zipCode, city, timeOut);
        String sigId = null;
        if(coordinates.length()>0)
            sigId = getSigIdForCoordinates(coordinates.get(0).toString(),
                    coordinates.get(1).toString(),timeOut);
        if (Validator.isNotNull(sigId)) {
            AssetVocabulary territoryVocabulary;
            try {
                territoryVocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
                district = AssetVocabularyHelper.getCategoryByExternalId(territoryVocabulary, sigId);
            } catch (PortalException e) {
                _log.error(e.getMessage() + " : " + VocabularyNames.TERRITORY);
            }
        }

        return district;
    }

    /**
     * Retourne les coordonnées d'un quartier
     */
    @Override
    public JSONObject getCoordinatesForSigId(String sigID) throws Exception {
        JSONObject coordinatesZone = JSONFactoryUtil.createJSONObject();
        String url = getDistrictsURL() + "&refine.districtcode=" + HtmlUtil.escapeURL(sigID);
        JSONArray records = getRecord(url);
        if (records.length() > 0) {
            JSONObject fields = records.getJSONObject(0).getJSONObject("fields");
            coordinatesZone = fields.getJSONObject("geo_shape");
        }

        return coordinatesZone;
    }

    /**
     * Retourne le sigID du lieu pour les coordonnées "x" et "y"
     */
    @Override
    public String getSigIdForCoordinates(String x, String y) throws Exception {
        return getSigIdForCoordinates(x, y, StrasbourgPropsUtil.getWebServiceDefaultTimeout());
    }

    /**
     * Retourne le sigID du lieu pour les coordonnées "x" et "y"
     */
    @Override
    public String getSigIdForCoordinates(String x, String y, int timeOut) throws Exception {
        String sigId = null;
        String latitude = HtmlUtil.escape(x);
        String longitude = HtmlUtil.escape(y);
        String url = getDistrictsURL() + "&geofilter.distance=" + latitude + "," + longitude + ",0";
        JSONArray records = getRecord(url, timeOut);
        if (records.length() > 0) {
            JSONObject fields = records.getJSONObject(0).getJSONObject("fields");
            sigId = fields.getString("districtcode");
        }

        return sigId;
    }

    private OpenDataGeoAddressService openDataGeoAddressService;

    @Reference(unbind = "-")
    public void setOpenDataGeoAddressService(OpenDataGeoAddressService openDataGeoAddressService) {
        this.openDataGeoAddressService = openDataGeoAddressService;
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}