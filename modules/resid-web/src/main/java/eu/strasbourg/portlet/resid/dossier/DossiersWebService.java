package eu.strasbourg.portlet.resid.dossier;

import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.net.HttpURLConnection;

public class DossiersWebService {

	public static DossiersResponse getResponse(String identifiantOpenId) {
		DossiersResponse dossiersResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getResidantWebServiceURL() + identifiantOpenId;
			HttpURLConnection httpConn = PasserelleHelper.readFromURL(url);
			if(httpConn.getContentType().contains("application/json")) {
				dossiersResponse = new DossiersResponse(PasserelleHelper.readJson(httpConn));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dossiersResponse;
	}

}
