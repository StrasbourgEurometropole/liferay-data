package eu.strasbourg.portlet.resid.dossier;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.liferay.portal.kernel.json.JSONException;

import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class DossiersWebService {

	public static DossiersResponse getResponse(String identifiantOpenId) {
		DossiersResponse dossiersResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getResidantURL() + identifiantOpenId;
			HttpURLConnection httpConn = PasserelleHelper.readFromURL(url);
			dossiersResponse = new DossiersResponse(PasserelleHelper.readJson(httpConn));
		} catch (IOException | JSONException ex) {
			ex.printStackTrace();
		}

		return dossiersResponse;
	}

}
